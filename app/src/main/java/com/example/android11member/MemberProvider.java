package com.example.android11member;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class MemberProvider extends ContentProvider {

    SQLiteDatabase mDatabase;

    public MemberProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        mDatabase = new DBOpenHelper(getContext(),"member.db",null,1).getWritableDatabase();
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor c = mDatabase.query("member",projection,selection,selectionArgs,null,null,sortOrder);
        Log.i("testLog","provider...call query()...");
        Log.i("testLog","c.moveToNext():"+c.moveToNext());
        while (c.moveToNext()){
            Log.i("testLog",""+c.getInt(c.getColumnIndex("num")));
            Log.i("testLog",""+c.getString(c.getColumnIndex("name")));
        }
        return c;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
