package edu.uab.tindi.mobilecomputingassignment004;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Mike on 10/20/2017.
 */

public class Task {
    int     _id ;
    String  _title ;
    Date    _timeAssigned ;
    Date    _timeToBeCompleted ;
    boolean _completed ;
    String  _project ;
    int     _user_ID ;
    String  _note ;

    public Task(String theTitle) {
        Title(theTitle) ;
        this._timeAssigned      = new Date() ;
        this._timeToBeCompleted = new Date() ;
        Completed(false) ;
        Project("HOME") ;
        UserID(0);
    }

    public int ID() {
        return _id ;
    }
    public void ID(int theUserID) {
        this._user_ID = theUserID;
    }

    public String Title() {
        return _title ;
    }
    public void Title(String theTitle)    {
        theTitle = theTitle.trim();
        if(theTitle == ""){ theTitle = "New Task on " +(new Date().toString()) ; }
        this._title = theTitle ;
    }

    public Date TimeAssigned() {
        return _timeAssigned ;
    }
    public void TimeAssigned(Date theTimeAssigned){
        this._timeAssigned = theTimeAssigned ;
    }

    public Date TimeToBeCompleted() {
        return _timeToBeCompleted ;
    }
    public void TimeToBeCompleted(Date fName){
        this._timeToBeCompleted = fName ;
    }

    public boolean Completed() {
        return _completed ;
    }
    public void Completed(boolean IsComplete) {
        this._completed = IsComplete ;
    }

    public String Project() {
        return _project ;
    }
    public void Project(String aProject) {
        this._project = aProject ;
    }

    public int UserID() {
        return _user_ID;
    }
    public void UserID(int theUserID) {
        this._user_ID = theUserID;
    }

    public String Note() {
        return _note ;
    }
    public void Note(String aNote) {
        this._note = aNote ;
    }
}
