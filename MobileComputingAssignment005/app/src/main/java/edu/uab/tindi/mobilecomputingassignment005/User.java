package edu.uab.tindi.mobilecomputingassignment005;

/**
 * Created by Mike on 11/22/2017.
 */

public class User {
    private int     _id ;
    private String  _name ;
    private String  _password ;
    private String  _email ;

    public User(){
        _id         = 0 ;
        _name       = "" ;
        _password   = "" ;
        _email      = "" ;
    }

    public User(String theName,
                String thePassword,
                String theEmail){
        _id         = 0 ;
        _name       = theName ;
        _password   = thePassword ;
        _email      = theEmail ;
    }

    public void     ID(int anID) { _id = anID ; }
    public int      ID() { return _id ; }

    public void     Name(String aName) { _name = aName ; }
    public String   Name() { return _name ; }

    public void     Password(String thePassword) { _password = thePassword ; }
    public String   Password() { return _password ; }

    public void     Email(String anEmailAddress) { _email = anEmailAddress ; }
    public String   Email() { return _email ; }

    public boolean  CheckPassword(String aPassword) {
        if(aPassword == _password){
            return  true ;
        }
        return false ;
    }

}
