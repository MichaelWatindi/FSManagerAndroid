package edu.uab.tindi.mobilecomputingassignment004;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mike on 10/20/2017.
 */

public class ProjectManagerDBHandler extends SQLiteOpenHelper {
    private static final int    DB_VERSION          = 1 ;
    private static final String DB_NAME             = "ProjectManagerDB" ;

    public static final String USERS_TABLE          = "tPMSUser";
    public static final String USERS_COLUMN_ID      = "User_ID";
    public static final String USERS_COLUMN_NAME    = "User_Name";

    public static final String TASKS_TABLE          = "tTasks" ;
    public static final String TASKS_COLUMN_ID      = "Task_ID" ;
    public static final String TASKS_COLUMN_TITLE   = "Task_Title" ;
    public static final String TASKS_COLUMN_USER_ID = "Task_User_ID" ;
    public static final String TASKS_COLUMN_ASSIGNED = "Task_AssignedTime" ;
    public static final String TASKS_COLUMN_TIMEDUE = "Task_DueDate" ;
    public static final String TASKS_COLUMN_STATUS  = "Task_StatusComplete" ;
    public static final String TASKS_COLUMN_PROJECT = "Task_ProjectName" ;


    public ProjectManagerDBHandler(Context aContext) {
        super(aContext, DB_NAME, null, DB_VERSION) ;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTasksTableSQL = "CREATE TABLE IF NOT EXISTS " +
                TASKS_TABLE + "("   + TASKS_COLUMN_ID + " INTEGER PRIMARY KEY, "
                                    + TASKS_COLUMN_TITLE + " TEXT, "
                                    + TASKS_COLUMN_ASSIGNED + " TEXT, "
                                    + TASKS_COLUMN_TIMEDUE + " TEXT, "
                                    + TASKS_COLUMN_STATUS + " TEXT, "
                                    + TASKS_COLUMN_USER_ID + " INTEGER, "
                                    + TASKS_COLUMN_PROJECT + " TEXT"
                            + ")";

        String createUsersTableSQL = "CREATE TABLE " +
                USERS_TABLE + "("   + USERS_COLUMN_ID + " INTEGER PRIMARY KEY, "
                                    + USERS_COLUMN_NAME + " TEXT "
                            + ")";

        db.execSQL(createTasksTableSQL);
        db.execSQL(createUsersTableSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase    db,
                          int               oldVer,
                          int               newVer) {
        db.execSQL("DROP TABLE IF EXISTS "
                + USERS_TABLE) ;

        db.execSQL("DROP TABLE IF EXISTS "
                + TASKS_TABLE) ;
        onCreate(db);

    }

    void clearDatabase() {

    }
}
