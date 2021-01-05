package edu.uab.tindi.mobilecomputingassignment006;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.CancellationSignal;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Mike on 12/13/2017.
 */

public class LibraryProvider extends ContentProvider{
    static final int    BOOKS           = 1;
    static final int    BOOKS_ID        = 2;
    static final String PROVIDER_NAME   = "edu.uab.tindi.mobilecomputingassignment006";
    static final String URL             = "content://" + PROVIDER_NAME + "/library" ;
    static final Uri    CONTENT_URI     = Uri.parse(URL) ;

    BooksDAO aBooksDAO = new BooksDAO(getContext()) ;

    static final UriMatcher uriMatcher ;

    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH) ;
        uriMatcher.addURI(LibraryContract.PROVIDER_NAME, "books", BOOKS ) ;
        uriMatcher.addURI(LibraryContract.PROVIDER_NAME, "books/#", BOOKS_ID) ;
    }

    @Override
    public boolean onCreate() {
        try {
            aBooksDAO.Open();
            return true ;
        } catch (SQLException e) {
            e.printStackTrace();
            return false ;
        }
    }

    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        String whereClause = "" ;
        switch (uriMatcher.match(uri)) {
            case BOOKS:
                //get all
                whereClause = null ;
                break;
            case BOOKS_ID:
                // Get individual book
                whereClause = " Book_ID = " + uri.getLastPathSegment();
                break;
            default:
                throw new IllegalArgumentException("Unknown URI "+ uri);
        }
        Cursor aCursor = aBooksDAO.GetForExternalUse(whereClause) ;
        return aCursor ;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            // Get all friend-birthday records
            case BOOKS:
                return "vnd.android.cursor.dir/vnd.example.books";
            // Get a particular friend
            case BOOKS_ID:
                return "vnd.android.cursor.item/vnd.example.books";
            default:
                throw new IllegalArgumentException("Err URI: " + uri);


        }
    }

    @Override
    public Uri insert( Uri uri,  ContentValues contentValues ) {
        long row = aBooksDAO.Add(contentValues) ;
        if(row > 0) {
            Uri newUri = ContentUris.withAppendedId(CONTENT_URI, row) ;
            getContext().getContentResolver().notifyChange(newUri, null) ;
            return newUri ;
        }
        throw new SQLException("Fail to add a new record into " + uri) ;
        // return null ;
    }

    @Override
    public int delete( Uri uri,  String selection,  String[] whereArgs) {
        int count = 0 ;
        switch (uriMatcher.match(uri)){
            case BOOKS_ID:
            case BOOKS:
                count = aBooksDAO.Delete(selection, whereArgs) ;
                break ;
        }

        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder, @Nullable CancellationSignal cancellationSignal) {
        return super.query(uri, projection, selection, selectionArgs, sortOrder, cancellationSignal);
    }
}
