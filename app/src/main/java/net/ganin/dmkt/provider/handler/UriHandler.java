package net.ganin.dmkt.provider.handler;

import android.net.Uri;

public interface UriHandler {
    String query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder);
}
