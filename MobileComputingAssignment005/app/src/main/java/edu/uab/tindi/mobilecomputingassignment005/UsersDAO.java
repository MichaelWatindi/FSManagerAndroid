package edu.uab.tindi.mobilecomputingassignment005;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Mike on 11/25/2017.
 */

public class UsersDAO {
    private DBHandlerFile   filesDBHandler ;
    private SQLiteDatabase  theDB ;

    public UsersDAO(Context aContext) {
        try {
            filesDBHandler = new DBHandlerFile(aContext) ;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void Open() throws SQLException {
        try {
            theDB = filesDBHandler.getWritableDatabase() ;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Close() {
        filesDBHandler.close() ;
    }

    public void Add(User theNewUser) {
        ContentValues userValues = new ContentValues() ;
        this.Open() ;

        try {
            userValues.put(filesDBHandler.TB_COLUMN_USERS_NAME, theNewUser.Name()) ;
            userValues.put(filesDBHandler.TB_COLUMN_USERS_EMAIL, theNewUser.Email()) ;
            userValues.put(filesDBHandler.TB_COLUMN_USERS_PASSWORD, theNewUser.Password()) ;
            theDB.insert(filesDBHandler.TABLE_USERS, null, userValues) ;
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.Close() ;
    }

    public User GetByID(int theUserID) {
        this.Open() ;
        String SQLGetUserWithID  = "SELECT " + filesDBHandler.TB_COLUMN_USERS_ID
                + " , " +  filesDBHandler.TB_COLUMN_USERS_NAME
                + " , " + filesDBHandler.TB_COLUMN_USERS_EMAIL
                + " , " + filesDBHandler.TB_COLUMN_USERS_PASSWORD
                + " FROM "
                +   filesDBHandler.TABLE_USERS
                + " WHERE " +  filesDBHandler.TB_COLUMN_USERS_ID + " = " + theUserID ;

        Cursor aCursor = theDB.rawQuery(SQLGetUserWithID, null) ;
        if (aCursor != null) {
            aCursor.moveToFirst() ;
        }
        else {  return null;  }

        User theUser = new User() ;
        theUser.ID(Integer.parseInt(aCursor.getString(0))) ;
        theUser.Name(aCursor.getString(1)) ;
        theUser.Email(aCursor.getString(2)) ;
        theUser.Password(aCursor.getString(3)) ;
        this.Close() ;
        return theUser ;
    }

    public User GetByName(String aName) {
        this.Open() ;
        String SQLGetUserWithID  = "SELECT " + filesDBHandler.TB_COLUMN_USERS_ID
                + " , " +  filesDBHandler.TB_COLUMN_USERS_NAME
                + " , " + filesDBHandler.TB_COLUMN_USERS_EMAIL
                + " , " + filesDBHandler.TB_COLUMN_USERS_PASSWORD
                + " FROM "
                +   filesDBHandler.TABLE_USERS
                + " WHERE " +  filesDBHandler.TB_COLUMN_USERS_NAME + " = '" + aName + "'";

        Cursor aCursor = theDB.rawQuery(SQLGetUserWithID, null) ;
        if (aCursor != null) {
            aCursor.moveToFirst() ;
        }
        else {  return null;  }

        User theUser = new User() ;
        theUser.ID(Integer.parseInt(aCursor.getString(0))) ;
        theUser.Name(aCursor.getString(1)) ;
        theUser.Email(aCursor.getString(2)) ;
        theUser.Password(aCursor.getString(3)) ;
        this.Close() ;
        return theUser ;
    }

    public User GetByEmail(String anEmail){

        try {
            this.Open() ;
            String SQLGetUserWithID  = "SELECT " + filesDBHandler.TB_COLUMN_USERS_ID
                    + " , " +  filesDBHandler.TB_COLUMN_USERS_NAME
                    + " , " + filesDBHandler.TB_COLUMN_USERS_EMAIL
                    + " , " + filesDBHandler.TB_COLUMN_USERS_PASSWORD
                    + " FROM "
                    +   filesDBHandler.TABLE_USERS
                    + " WHERE " +  filesDBHandler.TB_COLUMN_USERS_EMAIL + " = '" + anEmail + "'";

            Cursor aCursor = theDB.rawQuery(SQLGetUserWithID, null) ;
            int iNumberOfRows = aCursor.getCount() ;
            if (aCursor != null && aCursor.getCount() > 0) {
                aCursor.moveToFirst() ;
            }
            else {
                return null;
            }

            User theUser = new User() ;
            String strID = aCursor.getString(0) ;
            int iIDOfUser = Integer.parseInt(strID.trim()) ;
            theUser.ID(Integer.parseInt(aCursor.getString(0))) ;
            theUser.Name(aCursor.getString(1)) ;
            theUser.Email(aCursor.getString(2)) ;
            theUser.Password(aCursor.getString(3)) ;

            if(theUser.Email().equals(anEmail))
            {
                int i = 0;
            }
            this.Close() ;
            return theUser ;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null ;
    }

    public ArrayList<User> GetAllUsers(){
        this.Open();
        ArrayList<User> listOfAllUsers = new ArrayList<>() ;

        String SQLGetAllUsers = "SELECT " + filesDBHandler.TB_COLUMN_USERS_ID
                + " , " +  filesDBHandler.TB_COLUMN_USERS_NAME
                + " , " + filesDBHandler.TB_COLUMN_USERS_EMAIL
                + " , " + filesDBHandler.TB_COLUMN_USERS_PASSWORD
                + " FROM "
                +   filesDBHandler.TABLE_USERS ;

        Cursor aCursor = theDB.rawQuery(SQLGetAllUsers, null) ;

        if (aCursor.moveToFirst()){
            do{
                User aUser = new User() ;
                aUser.ID(Integer.parseInt(aCursor.getString(0))) ;
                aUser.Name(aCursor.getString(1)) ;
                aUser.Email(aCursor.getString(2)) ;
                aUser.Password(aCursor.getString(3)) ;
                listOfAllUsers.add(aUser) ;
            } while (aCursor.moveToNext()) ;
        }

        this.Close();
        return new ArrayList<User>();
    }

    public void DeleteAllUsers(){
        try {
            this.Open();
            String SQLGetUserWithID  = " DELETE  FROM " + filesDBHandler.TABLE_USERS ;
            theDB.execSQL(SQLGetUserWithID) ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.Close();
    }


}
