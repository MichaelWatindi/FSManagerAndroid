package edu.uab.tindi.mobilecomputingassignment005;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mike on 11/22/2017.
 */

public class DBHandlerFile extends SQLiteOpenHelper {
    private static final int    DB_VERSION              = 1 ;
    private static final String DB_NAME                 = "FileManagerDB.db" ;

    public static final  String TABLE_USERS             = "tFMSUsers" ;
    public static final  String TB_COLUMN_USERS_ID      = "User_ID" ;
    public static final  String TB_COLUMN_USERS_NAME    = "User_Name" ;
    public static final  String TB_COLUMN_USERS_EMAIL   = "User_Email" ;
    public static final  String TB_COLUMN_USERS_PASSWORD = "User_Password" ;

    public static final  String TABLE_FILES              = "tFMSFiles" ;
    public static final  String TB_COLUMN_FILE_ID        = "File_ID" ;
    public static final  String TB_COLUMN_FILE_DIRECTORY = "File_Directory" ;
    public static final  String TB_COLUMN_FILE_NAME      = "File_Name" ;

    public DBHandlerFile(Context aContext) {
        super(aContext, DB_NAME, null, DB_VERSION) ;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            String SQLCreateUsersTable ="CREATE TABLE IF NOT EXISTS " +
                    TABLE_USERS + " ( " + TB_COLUMN_USERS_ID        + " INTEGER PRIMARY KEY, "
                                        + TB_COLUMN_USERS_NAME      + " TEXT, "
                                        + TB_COLUMN_USERS_EMAIL     + " TEXT, "
                                        + TB_COLUMN_USERS_PASSWORD  + " TEXT "
                    + ")";

            db.execSQL(SQLCreateUsersTable) ;
        } catch (SQLException e) {
            e.printStackTrace();
        }


        // String SQLCreateFlesTable = "CREATE TABLE IF NOT EXIST " ;
    }

    @Override
    public void onUpgrade(SQLiteDatabase    db,
                          int               oldVer,
                          int               newVer) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS) ;
        db.execSQL("") ;
        onCreate(db) ;
    }

    public void ClearDatabase(){
        String sqlClearDB = "DELETE * FROM " + TABLE_USERS ;

    }

}
