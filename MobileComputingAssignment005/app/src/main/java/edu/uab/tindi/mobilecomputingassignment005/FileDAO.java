package edu.uab.tindi.mobilecomputingassignment005;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Mike on 11/25/2017.
 */

public class FileDAO {
    private DBHandlerFile   fileDBHandler ;
    private SQLiteDatabase db ;

    public FileDAO(Context aContext) {
        fileDBHandler = new DBHandlerFile(aContext) ;
    }

    public void Open() {}

    public void Close() {}

    public void Add(WordFile theNewFile) {}

    public void GetAllFiles() {}

    public void GetByID(int id) {}

    public void DeleteAllFiles() {}
}
