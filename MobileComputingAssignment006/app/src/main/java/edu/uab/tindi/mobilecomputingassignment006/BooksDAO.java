package edu.uab.tindi.mobilecomputingassignment006;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Mike on 12/10/2017.
 */

public class BooksDAO {
    private DBHandlerBooks  booksDBHandler ;
    private SQLiteDatabase  booksDB ;

    public BooksDAO(Context aContext) {
        try {
            booksDBHandler = new DBHandlerBooks(aContext) ;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void Open() throws SQLException {
        try {
            booksDB = booksDBHandler.getWritableDatabase() ;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Close() {
        booksDBHandler.close() ;
    }

    public void Add(Book aNewBook) {
        ContentValues userValues = new ContentValues() ;
        this.Open() ;
        try {
            userValues.put(booksDBHandler.COLUMN_BOOK_TITLE, aNewBook.Title()) ;
            userValues.put(booksDBHandler.COLUMN_BOOK_AUTHOR, aNewBook.Authors()) ;
            userValues.put(booksDBHandler.COLUMN_BOOK_YEAR, aNewBook.Year()) ;
            userValues.put(booksDBHandler.COLUMN_BOOK_NUMOFPAGES, aNewBook.NumberOfPages()) ;
            userValues.put(booksDBHandler.COLUMN_BOOK_ISBN, aNewBook.ISBN()) ;
            booksDB.insert(booksDBHandler.TB_BOOKS, null, userValues) ;
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.Close() ;
    }

    public long Add(ContentValues theValues){
        this.Open();
        long rowId  = 0 ;
        try {
            rowId = booksDB.insert(booksDBHandler.TB_BOOKS, null, theValues) ;
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.Close();
        return rowId ;

    }

    public ArrayList<String> GetAllTitles(){
        return new ArrayList<>() ;
    }

    public String GetAllSQL()
    {
        String SQLGetAllUsers = "SELECT "
                + "   " + booksDBHandler.COLUMN_BOOK_ID
                + " , " + booksDBHandler.COLUMN_BOOK_TITLE
                + " , " + booksDBHandler.COLUMN_BOOK_AUTHOR
                + " , " + booksDBHandler.COLUMN_BOOK_YEAR
                + " , " + booksDBHandler.COLUMN_BOOK_NUMOFPAGES
                + " , " + booksDBHandler.COLUMN_BOOK_ISBN
                + " FROM "
                +   booksDBHandler.TB_BOOKS ;
        return SQLGetAllUsers ;
    }

    public Cursor GetTheseBooks(String sqlSelect, String sqlWhere){
        String sqlGetBooks = sqlSelect + sqlWhere ;

        try {
            Cursor aCursor = booksDB.rawQuery(sqlGetBooks, null) ;
            return aCursor ;
        } catch (Exception e) {
            e.printStackTrace();
            return null ;
        }
    }

    public ArrayList<Book> GetArrayFromCursor(Cursor aCursor) {
        return new ArrayList<>() ;
    }

    public ArrayList<Book> GetAll(){
        this.Open();
        ArrayList<Book> listOfAllBooks = new ArrayList<>() ;

        String sqlGetAllUsers = GetAllSQL() ;

        try {
            Cursor aCursor = booksDB.rawQuery(sqlGetAllUsers, null) ;

            if (aCursor.moveToFirst()){
                do {
                    Book aBook = new Book() ;
                    aBook.ID(Integer.parseInt(aCursor.getString(0))) ;
                    aBook.Title(aCursor.getString(1)) ;
                    aBook.Authors(aCursor.getString(2)) ;
                    aBook.Year(Integer.parseInt(aCursor.getString(3))) ;
                    aBook.NumberOfPages(Integer.parseInt(aCursor.getString(4))) ;
                    aBook.ISBN(aCursor.getString(5)) ;
                    listOfAllBooks.add(aBook) ;
                } while (aCursor.moveToNext()) ;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null ;
        }

        this.Close();
        return listOfAllBooks ;
    }

    public Cursor GetForExternalUse(String whereClause){
        this.Open();

        String sqlGetAllUsers = GetAllSQL() ;
        if (whereClause != null){
            sqlGetAllUsers = sqlGetAllUsers + " " + whereClause ;
        }

        try {
            return  booksDB.rawQuery(sqlGetAllUsers, null) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;

    }

    public Book GetByID(int aUserID){
        this.Open();
        String getAllSQL = GetAllSQL() ;
        String sqlWhere = " WHERE " + booksDBHandler.COLUMN_BOOK_ID + " = " + aUserID ;
        Cursor aCursor = GetTheseBooks(getAllSQL, sqlWhere) ;
        ArrayList<Book> theBooks = GetArrayFromCursor(aCursor) ;
        this.Close();
        if (theBooks != null) {

            return theBooks.get(0);
        }
        return null ;
    }

    public Book GetByAuthor(String anAuthor) {
        this.Open() ;
        String getAllSQL = GetAllSQL() ;
        String sqlWhere = " WHERE " + booksDBHandler.COLUMN_BOOK_AUTHOR + " = " + anAuthor ;
        Cursor aCursor = GetTheseBooks(getAllSQL, sqlWhere) ;
        ArrayList<Book> theBooks = GetArrayFromCursor(aCursor) ;
        this.Open() ;
        if (theBooks != null) {
            return theBooks.get(0);
        }
        return null ;
    }

    public Book GetByTitle(String aTitle) {
        this.Open() ;
        String getAllSQL = GetAllSQL() ;
        String sqlWhere = " WHERE " + booksDBHandler.COLUMN_BOOK_TITLE + " = " + aTitle ;
        Cursor aCursor = GetTheseBooks(getAllSQL, sqlWhere) ;
        ArrayList<Book> theBooks = GetArrayFromCursor(aCursor) ;
        this.Open() ;
        if (theBooks != null) {
            return theBooks.get(0);
        }
        return null ;
    }

    private void GetByText(String fieldName, String fieldValue) {}

    public int Update(int bookID){
        return 0 ;
    }

    public void DeleteAll() {
        try {
            this.Open();
            String SQLGetUserWithID  = " DELETE  FROM " + booksDBHandler.TB_BOOKS ;
            booksDB.execSQL(SQLGetUserWithID) ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.Close();
    }

    public int Delete(String selection, String[] selectionArgs){
       int count = booksDB.delete(booksDBHandler.TB_BOOKS, selection, selectionArgs) ;
        return count ;
    }

    public void DeleteByID(int aUserID) {
        try {
            this.Open();
            String SQLGetUserWithID  = " DELETE  FROM " + booksDBHandler.TB_BOOKS + " WHERE " + booksDBHandler.COLUMN_BOOK_ID + " = " + aUserID;
            booksDB.execSQL(SQLGetUserWithID) ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.Close();
    }

    public void DeleteByWhereClause(String theWhereClause){
        try {
            this.Open();
            String SQLGetUserWithID  = " DELETE  FROM " + booksDBHandler.TB_BOOKS + " WHERE " + theWhereClause;
            booksDB.execSQL(SQLGetUserWithID) ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.Close();
    }

    // GetByID()
}
