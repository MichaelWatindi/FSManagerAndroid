package edu.uab.tindi.mobilecomputingassignment006;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Mike on 12/10/2017.
 */

public class DBHandlerBooks extends SQLiteOpenHelper{

    private static final int    DB_VERSION = 1 ;
    private static final String DB_NAME = "DBBooks.db" ;

    public static final String  TB_BOOKS                = "tBooks" ;
    public static final String  COLUMN_BOOK_ID          = "Book_ID" ;
    public static final String  COLUMN_BOOK_TITLE       = "Book_Title" ;
    public static final String  COLUMN_BOOK_AUTHOR      = "Book_Author" ;
    public static final String  COLUMN_BOOK_YEAR        = "Book_Year" ;
    public static final String  COLUMN_BOOK_NUMOFPAGES  = "Book_NumberOfPages" ;
    public static final String  COLUMN_BOOK_ISBN        = "Book_ISBN" ;

    public DBHandlerBooks(Context aContext) {

        super(aContext, DB_NAME, null, DB_VERSION) ;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String SQLCreateUsersTable ="CREATE TABLE IF NOT EXISTS " +
                    TB_BOOKS + " ( "
                    + COLUMN_BOOK_ID            + " INTEGER PRIMARY KEY, "
                    + COLUMN_BOOK_TITLE         + " TEXT, "
                    + COLUMN_BOOK_AUTHOR        + " TEXT, "
                    + COLUMN_BOOK_YEAR          + " INTEGER, "
                    + COLUMN_BOOK_NUMOFPAGES    + " TEXT, "
                    + COLUMN_BOOK_ISBN          + " TEXT "
                    + ")";

            db.execSQL(SQLCreateUsersTable) ;
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int               oldVer,
                          int               newVer) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_BOOKS) ;
        onCreate(db) ;
    }

    public void ClearDatabase(){
        onUpgrade(this.getWritableDatabase(),0,0);
    }



}
