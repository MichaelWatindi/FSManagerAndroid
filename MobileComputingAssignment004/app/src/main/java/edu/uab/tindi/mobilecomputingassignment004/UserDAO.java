package edu.uab.tindi.mobilecomputingassignment004;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Mike on 10/26/2017.
 */

public class UserDAO {

    ProjectManagerDBHandler projectDBHandler ;
    private SQLiteDatabase db;

    public UserDAO(Context aContext) {
        projectDBHandler = new ProjectManagerDBHandler(aContext) ;

    }

    public void Open() {
        try {
            db = projectDBHandler.getWritableDatabase() ;
        }
        catch (Exception generalException) {
            String eMessage = generalException.getMessage() ;
            System.out.println(eMessage) ;
        }

    }

    void Close() {
        projectDBHandler.close();
    }

    void AddUser(User theNewUser) {
        ContentValues userValues = new ContentValues() ;
        try {
            this.Open();
            userValues.put(projectDBHandler.USERS_COLUMN_NAME, theNewUser.Name()) ;
            db.insert(projectDBHandler.USERS_TABLE, null, userValues) ;
        }
        catch (Exception generalException) {
            String exceptionMessage = generalException.getMessage();
            System.out.println(exceptionMessage) ;
        }
    }

    public User GetUser(int theUserID) {
        this.Open() ;
        String selectQuery = "SELECT * FROM " + projectDBHandler.USERS_TABLE
                + " WHERE " + projectDBHandler.USERS_COLUMN_ID + " = " + theUserID ;

        Cursor aCursor = db.rawQuery(selectQuery, null) ;
        if (aCursor != null)
        {
            aCursor.moveToFirst();
        }
        else {
            return null ;
        }
        User theUser = new User("NO Name") ;
        theUser.ID(Integer.parseInt(aCursor.getString(0))) ;
        theUser.Name(aCursor.getString(1)) ;
        return theUser ;
    }

    public int GetUserIDByName(String aName) {

        this.Open() ;
        String selectQuery = "SELECT * FROM " + projectDBHandler.USERS_TABLE
                + " WHERE " + projectDBHandler.USERS_COLUMN_NAME + " = '" + aName + "'" ;

        Cursor aCursor = db.rawQuery(selectQuery, null) ;
        if (aCursor != null)
        {
            aCursor.moveToFirst();
        }
        else {
            return - 1 ;
        }
        User theUser = new User("NO Name") ;
        theUser.ID(Integer.parseInt(aCursor.getString(0))) ;
        theUser.Name(aCursor.getString(1)) ;
        return theUser.ID() ;
    }

    public ArrayList<User> GetAllUsers() {
        this.Open();
        ArrayList<User> listOfUsers = new ArrayList<>() ;
        String selectQuery = "SELECT  * FROM "+ projectDBHandler.USERS_TABLE;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                User aUser = new User("");
                aUser.ID(Integer.parseInt(cursor.getString(0)));
                aUser.Name(cursor.getString(1));
                listOfUsers.add(aUser);
            } while (cursor.moveToNext());
        }
        return listOfUsers ;
    }

    void UpdateUser() {}

    void DeleteUser() {}

    void GetNumberOfUsers() {}

    void DeleteAllUsers() {
        this.Open();
        db.execSQL("Delete from " +
                projectDBHandler.USERS_TABLE);

    }
}
