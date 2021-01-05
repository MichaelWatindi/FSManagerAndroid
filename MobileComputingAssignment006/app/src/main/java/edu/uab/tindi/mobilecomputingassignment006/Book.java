package edu.uab.tindi.mobilecomputingassignment006;

/**
 * Created by Mike on 12/10/2017.
 */

public class Book {
    private int     _id ;
    private String  _title ;
    private String  _isbn ;
    private String  _authors ;
    private int     _year ;
    private int     _numberOfPages ;

    public Book() {
        _id             = 0 ;
        _title          = "" ;
        _isbn           = "" ;
        _authors        = "" ;
        _year           = 1970 ;
        _numberOfPages  = 0 ;
    }

    public void     ID(int anID) { _id = anID ; }
    public int      ID() { return _id ; }

    public void     Title(String aTitle) { _title = aTitle ; }
    public String   Title() { return _title ; }

    public void     ISBN(String anISBN) { _isbn = anISBN ; }
    public String   ISBN() { return _isbn ; }

    public void     Authors(String someAuthors) { _authors = someAuthors ; }
    public String   Authors() { return _authors ; }

    public void     Year(int theYear) { _year = theYear ; }
    public int      Year() { return _year ; }

    public void     NumberOfPages(int theNumOfPages) { _numberOfPages = theNumOfPages ; }
    public int      NumberOfPages() { return _numberOfPages ; }

}
