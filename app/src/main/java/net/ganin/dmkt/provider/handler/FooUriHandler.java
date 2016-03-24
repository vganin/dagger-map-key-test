package net.ganin.dmkt.provider.handler;

import android.net.Uri;

class FooUriHandler implements UriHandler {

    @Override
    public String query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return "foo";
    }
}
