package net.ganin.dmkt.provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.annotation.WorkerThread;

import net.ganin.dmkt.provider.handler.DaggerUriHandlerComponent;
import net.ganin.dmkt.provider.handler.UriHandler;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;

import javax.inject.Inject;

public class DumbContentProvider extends ContentProvider {

    @StringDef({ ID_FOO, ID_BAR })
    @Retention(RetentionPolicy.SOURCE)
    private @interface UriId {}

    public static final String ID_FOO = "foo";
    public static final String ID_BAR = "bar";

    private static final String AUTHORITY = "net.ganin.dmkt.provider";
    private static final Uri CONTENT_URI =
            Uri.parse("content://" + AUTHORITY);

    private static final String COLUMN_STRING_DATA = "string_data";
    private static final String[] COLUMNS = { COLUMN_STRING_DATA };

    @WorkerThread
    public static String request(@NonNull ContentResolver resolver, @NonNull @UriId String id) {
        Uri dataUri = Uri.withAppendedPath(CONTENT_URI, id);
        Cursor cursor = resolver.query(dataUri, null, null, null, null);
        if (cursor == null) {
            throw new RuntimeException("Null cursor was returned");
        }
        cursor.moveToFirst();
        return cursor.getString(0);
    }

    @Inject
    Map<String, UriHandler> handlers;

    @Override
    public boolean onCreate() {
        DaggerUriHandlerComponent.builder().build().inject(this);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String handlerId = uri.getPathSegments().get(0);
        String result = handlers.get(handlerId).query(uri, projection, selection, selectionArgs, sortOrder);
        MatrixCursor cursor = new MatrixCursor(COLUMNS, 1);
        cursor.addRow(new String[] { result });
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
