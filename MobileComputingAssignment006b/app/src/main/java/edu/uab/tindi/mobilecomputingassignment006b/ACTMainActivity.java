package edu.uab.tindi.mobilecomputingassignment006b;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

enum App_CurrentView {
    MAIN_MODE ,
    EDIT_MODE ,
    EDIT_SELECT_MODE ,
    DELETE_SELECT_MODE ,
    LIST_SELECT_MODE ,
    OPTIONS_MODE ,
}

public class ACTMainActivity
        extends AppCompatActivity
        implements  FrgViewOptions.FrgViewOptionsListener,
                    FrgEditBook.OnEditBookInteractionListener,
                    FrgListBooks.FrgListBookActionListener

{
    App_CurrentView app_currentView ;
    ArrayList<Book> listOfBooks = new ArrayList<>();
    private static final int PERMISSION_TO_READ = 100 ;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState) ;
        app_currentView = App_CurrentView.MAIN_MODE ;
        InitializeContent() ;

        setContentView(R.layout.activity_actmain) ;
    }

    private void InitializeContent(){
        SetActionBarTitle("Books Library");
        SetFrames();
        SetTestContent();
        CheckForPermission() ;
    }

    private void CheckForPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, PERMISSION_TO_READ) ;
            }
        }
    }

    private void SetActionBarTitle(String theTitle){
        ActionBar theActionBar = getSupportActionBar() ;
        theActionBar.setTitle(theTitle) ;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu) ;
        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menuItem_viewBooks:
                //  Set Mode View Books
                app_currentView = App_CurrentView.OPTIONS_MODE ;
                SetFrames() ;
                return true ;
            case R.id.menuItem_addBook:
                app_currentView = App_CurrentView.EDIT_MODE ;
                SetFrames() ;
                //  Set Mode Add Books
                return true ;
            case R.id.menuItem_removeBook:
                app_currentView = App_CurrentView.DELETE_SELECT_MODE ;
                SetFrames() ;
                return true ;
            case R.id.menuItem_editBook:
                app_currentView = App_CurrentView.EDIT_SELECT_MODE ;
                SetFrames() ;
                return true ;
            case R.id.menuItem_clearScreen:
                app_currentView = App_CurrentView.MAIN_MODE ;
                SetFrames() ;
                return true ;
            case R.id.menuItem_saveBook:
                app_currentView = App_CurrentView.MAIN_MODE ;
                SetFrames() ;
                return true ;
            case R.id.menuItem_cancel:
                app_currentView = App_CurrentView.MAIN_MODE ;
                SetFrames() ;
                return true ;
        }
        return true ;
    }

    private void SetFrames(){
        Bundle savedInstanceState = getIntent().getExtras() ;
        if (app_currentView == App_CurrentView.MAIN_MODE){

            if (savedInstanceState != null ){
                getSupportFragmentManager().executePendingTransactions();
                Fragment aFragment = getSupportFragmentManager().findFragmentById(R.id.Frame_MainActivityPortraitContent) ;
                if (aFragment != null){
                    getSupportFragmentManager().beginTransaction().remove(aFragment).commit() ;
                }
            }
            FRGMainFragment mainFragment = new FRGMainFragment() ;
            getSupportFragmentManager().beginTransaction().replace(R.id.Frame_MainActivityPortraitContent, mainFragment).commit() ;

        }
        else if (app_currentView == App_CurrentView.OPTIONS_MODE){
            if (savedInstanceState != null ){
                getSupportFragmentManager().executePendingTransactions();
                Fragment aFragment = getSupportFragmentManager().findFragmentById(R.id.Frame_MainActivityPortraitContent) ;
                if (aFragment != null){
                    getSupportFragmentManager().beginTransaction().remove(aFragment).commit() ;
                }
            }
            FrgViewOptions mainFragment = new FrgViewOptions() ;
            getSupportFragmentManager().beginTransaction().replace(R.id.Frame_MainActivityPortraitContent, mainFragment).commit() ;
        }

        else if (app_currentView == App_CurrentView.EDIT_SELECT_MODE){
            if (savedInstanceState != null ){
                getSupportFragmentManager().executePendingTransactions();
                Fragment aFragment = getSupportFragmentManager().findFragmentById(R.id.Frame_MainActivityPortraitContent) ;
                if (aFragment != null){
                    getSupportFragmentManager().beginTransaction().remove(aFragment).commit() ;
                }
            }
            FrgListBooks mainFragment = new FrgListBooks() ;
            mainFragment.SetObjects(listOfBooks) ;
            getSupportFragmentManager().beginTransaction().replace(R.id.Frame_MainActivityPortraitContent, mainFragment).commit() ;
        }
        else if (app_currentView == App_CurrentView.LIST_SELECT_MODE){
            if (savedInstanceState != null ){
                getSupportFragmentManager().executePendingTransactions();
                Fragment aFragment = getSupportFragmentManager().findFragmentById(R.id.Frame_MainActivityPortraitContent) ;
                if (aFragment != null){
                    getSupportFragmentManager().beginTransaction().remove(aFragment).commit() ;
                }
            }
            FrgListBooks mainFragment = new FrgListBooks() ;
            mainFragment.SetObjects(listOfBooks) ;
            getSupportFragmentManager().beginTransaction().replace(R.id.Frame_MainActivityPortraitContent, mainFragment).commit() ;
        }
        else if (app_currentView == App_CurrentView.DELETE_SELECT_MODE){
            if (savedInstanceState != null ){
                getSupportFragmentManager().executePendingTransactions();
                Fragment aFragment = getSupportFragmentManager().findFragmentById(R.id.Frame_MainActivityPortraitContent) ;
                if (aFragment != null){
                    getSupportFragmentManager().beginTransaction().remove(aFragment).commit() ;
                }
            }
            FrgListBooks mainFragment = new FrgListBooks() ;
            mainFragment.SetObjects(listOfBooks) ;
            getSupportFragmentManager().beginTransaction().replace(R.id.Frame_MainActivityPortraitContent, mainFragment).commit() ;
        }
        else if (app_currentView == App_CurrentView.EDIT_MODE){
            if (savedInstanceState != null ){
                getSupportFragmentManager().executePendingTransactions();
                Fragment aFragment = getSupportFragmentManager().findFragmentById(R.id.Frame_MainActivityPortraitContent) ;
                if (aFragment != null){
                    getSupportFragmentManager().beginTransaction().remove(aFragment).commit() ;
                }
            }
            FrgEditBook mainFragment = new FrgEditBook() ;
            getSupportFragmentManager().beginTransaction().replace(R.id.Frame_MainActivityPortraitContent, mainFragment).commit() ;
            int i = 0 ;
            i = i + 1 ;
        }
    }

    private void SetTestContent(){
        Book book001 = new Book() ;
        book001.Title("To Kill a Mockingbird") ;
        book001.Authors("Harper Lee") ;
        book001.Year(1960) ;
        book001.NumberOfPages(230) ;
        book001.ISBN("ISBN, 978-3-16-148410-0");

        Book book002 = new Book() ;
        book002.Title("Anna Karenina") ;
        book002.Authors("Leo Tolstoy") ;
        book002.Year(1878) ;
        book002.NumberOfPages(130) ;
        book002.ISBN("ISBN, 978-3-16-148410-0");

        Book book003 = new Book() ;
        book003.Title("The Great Gatsby") ;
        book003.Authors("F. Scott Fitzgerald") ;
        book003.Year(1920) ;
        book003.NumberOfPages(200) ;
        book003.ISBN("ISBN, 978-3-16-148410-0");

        Book book004 = new Book() ;
        book004.Title("Things Fall Apart") ;
        book004.Authors("Chinua Achebe") ;
        book004.Year(1958) ;
        book004.NumberOfPages(200) ;
        book004.ISBN("ISBN, 978-3-16-148410-0") ;

        Book book005 = new Book() ;
        book005.Title("The Color Purple") ;
        book005.Authors("Alice Walker") ;
        book005.Year(1920) ;
        book005.NumberOfPages(400) ;
        book005.ISBN("ISBN, 978-3-16-148410-0") ;

        Book book006 = new Book() ;
        book006.Title("Don Quixote") ;
        book006.Authors("Miguel de Cervantes") ;
        book006.Year(1605) ;
        book006.NumberOfPages(150) ;
        book006.ISBN("ISBN, 978-3-16-148410-0") ;

        listOfBooks.add(book001) ;
        listOfBooks.add(book002) ;
        listOfBooks.add(book003) ;
        listOfBooks.add(book004) ;
        listOfBooks.add(book005) ;
        listOfBooks.add(book006) ;


    }

    @Override
    public void OnOptionsButtonPressed(String theCommand, String otherInfor) {

        if(theCommand.equals("VIEW_ALL")){
            app_currentView = App_CurrentView.EDIT_SELECT_MODE ;
            SetFrames() ;
        }
    }

    @Override
    public void onRequestPermissionsResult(int      requestCode,
                                           String[] permissions,
                                           int[]    grantResults) {
        switch(requestCode) {
            case PERMISSION_TO_READ:{
                //  If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
            }

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void OnItemOnListClicked(Book theBook){

    }

    public ArrayList<Book> GetAllBooks(){
        Uri UriLibraryBooksProvider = LibraryContract.CONTENT_URI ;
        ArrayList<Book> listOfAllBooks = new ArrayList<>() ;
        // int count = getContentResolver().delete(libraryBooksProvider, null, null) ;
        Cursor aCursor = getContentResolver().query(UriLibraryBooksProvider, null, null, null, "book");

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
        return listOfAllBooks ;

    }

    public void AddBook(Book theBook){
        ContentValues userValues = new ContentValues() ;

        try {
            userValues.put(LibraryContract.BOOK_TITLE, theBook.Title()) ;
            userValues.put(LibraryContract.BOOK_AUTHOR, theBook.Authors()) ;
            userValues.put(LibraryContract.BOOK_YEAR, theBook.Year()) ;
            userValues.put(LibraryContract.BOOK_PAGES, theBook.NumberOfPages()) ;
            userValues.put(LibraryContract.BOOK_ISBN, theBook.ISBN()) ;

            Uri uriBook = getContentResolver().insert(LibraryContract.CONTENT_URI, userValues) ;


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void UpdateBook(Book theBook){}
}
