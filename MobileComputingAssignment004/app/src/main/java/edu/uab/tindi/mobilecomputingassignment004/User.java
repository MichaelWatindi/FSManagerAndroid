package edu.uab.tindi.mobilecomputingassignment004;

/**
 * Created by Mike on 10/20/2017.
 */

public class User
{
    String  _name ;
    int     _id ;

    public User( String aName ) {
        _name = aName ;
    }

    public String Name() {
        return _name ;
    }

    public void Name(String theTitle)    {
        this._name = theTitle ;
    }

    public int ID() {
        return _id ;
    }
    public void ID(int theID) {
        this._id = theID ;
    }

}
