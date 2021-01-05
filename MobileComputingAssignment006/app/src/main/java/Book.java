/**
 * Created by Mike on 12/11/2017.
 */

public class Book {
    private int     _id ;
    private String  _title ;
    private String  _author ;
    private int     _year ;
    private int     _numberOfPages ;
    private String  _ISBN ;

    public int  ID() {
        return _id;
    }
    public void ID(int theid) {
        this._id = theid;
    }

    public String   Title() {
        return _title;
    }
    public void     Title(String theTitle) {
        this._title = theTitle;
    }

    public String   Author() {
        return _author;
    }
    public void     Author(String theAuthor) {
        this._author = theAuthor;
    }

    public int  Year() {
        return _year;
    }
    public void Year(int theYear) {
        this._year = theYear;
    }

    public int  NumberOfPages() {
        return _numberOfPages;
    }
    public void NumberOfPages(int theNumberOfPages) {
        this._numberOfPages = theNumberOfPages;
    }

    public String   ISBN() {
        return _ISBN;
    }
    public void     ISBN(String theISBN) {
        this._ISBN = theISBN;
    }


}
