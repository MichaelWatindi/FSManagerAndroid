package edu.uab.tindi.mobilecomputingassignment006b;
/**
 * Created by Mike on 12/14/2017.
 */

import android.net.Uri;

/**
 * Created by Mike on 12/14/2017.
 */

public class LibraryContract {
    public static final String PROVIDER_NAME   = "edu.uab.tindi.mobilecomputingassignment006" ;
    public static final String URL             = "content://" + PROVIDER_NAME + "/books" ;
    public static final Uri    CONTENT_URI     = Uri.parse(URL) ;
    public static final String BOOK_ID         = "book_id" ;
    public static final String BOOK_TITLE      = "book_title" ;
    public static final String BOOK_AUTHOR     = "book_author" ;
    public static final String BOOK_YEAR       = "book_year" ;
    public static final String BOOK_PAGES      = "book_pages" ;
    public static final String BOOK_ISBN       = "book_isbn" ;
}