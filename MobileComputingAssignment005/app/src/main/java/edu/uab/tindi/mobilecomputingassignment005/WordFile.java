package edu.uab.tindi.mobilecomputingassignment005;

import java.io.File;

/**
 * Created by Mike on 11/22/2017.
 */

public class WordFile {
    private int    _id ;
    private String  _name ;
    public  File    _file ;
    private String  _directory ;
    private int     _user_ID ;

    public WordFile(){}

    public WordFile(String name) {

    }

    public void     ID(int anID) {}
    public int      ID() { return _id ; }

    public void     Name(String aName) {}
    public String   Name() { return "" ; }

    public void     Directory(String aName) {}
    public String   Directory() { return "" ; }

    public void     User_ID(int theUserID) {}
    public int      User_ID() { return _id ; }


}
