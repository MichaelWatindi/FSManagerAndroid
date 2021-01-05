package edu.uab.tindi.mobilecomputingassignment002;

/**
 * Created by Mike on 9/27/2017.
 */

public class AcademicCourse {

    String _name ;
    String _instructor ;
    String _description ;
    String _textBook ;
    String _room ;

    public AcademicCourse(String aName, String anInstructor )   {
        this._name = aName ;
        this._instructor = anInstructor ;
    }

    public String Name() {
        return _name ;
    }

    public void Name( String aName ) {
        this._name = aName  ;
    }

    public String Instructor() {
        return _instructor ;
    }

    public void Instructor( String anInstructor ) {
        this._instructor = anInstructor ;
    }

    public String TextBook() {
        return _textBook ;
    }

    public void TextBook( String theTextBook ) {
        this._textBook = theTextBook ;
    }

    public String Dexcription() {
        return _description ;
    }

    public void Description( String aDescription ) {
        this._description = aDescription ;
    }

    public String Room() {
        return _room ;
    }

    public void Room( String aRoom ) {
        this._room = aRoom ;
    }
}
