package edu.uab.tindi.mobilecomputingassignment006;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ACTMainActivity extends AppCompatActivity {
    ListView            listViewBooksList ;
    ArrayList<Book>     listOfBooks = new ArrayList<>() ;
    BooksDAO            theBooksDAO ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState) ;
        setContentView(R.layout.activity_actmain) ;
        listViewBooksList = (ListView) findViewById(R.id.listView_ListBooks) ;
        theBooksDAO = new BooksDAO(this) ;
        theBooksDAO.Open() ;
        CreateSampleContent() ;
        listOfBooks = theBooksDAO.GetAll() ;
        BooksAdapter adapterListOfBooks
                = new BooksAdapter( this,
                                    R.layout.books_list_view_items,
                                    listOfBooks) ;
        listViewBooksList.setAdapter(adapterListOfBooks) ;
        listViewBooksList.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    public void onItemClick(AdapterView<?> parent, View theView, int position, long id) {
                        Book theBook = (Book) listViewBooksList.getItemAtPosition(position) ;
                        final Dialog dlgBookDetails = new Dialog(ACTMainActivity.this) ;

                        dlgBookDetails.setContentView(R.layout.dialog_book_details) ;
                        dlgBookDetails.setTitle("Book") ;

                        TextView textView_bookID        = (TextView) dlgBookDetails.findViewById(R.id.textView_bookId) ;
                        EditText editText_bookTitle     = (EditText) dlgBookDetails.findViewById(R.id.editText_bookTitle) ;
                        EditText editText_bookAuthor    = (EditText) dlgBookDetails.findViewById(R.id.editText_bookAuthor) ;
                        EditText editText_bookYear      = (EditText) dlgBookDetails.findViewById(R.id.editText_bookYear) ;
                        EditText editText_bookPages     = (EditText) dlgBookDetails.findViewById(R.id.editText_bookNumberOfPages) ;
                        EditText editText_bookISBN      = (EditText) dlgBookDetails.findViewById(R.id.editText_bookISBN) ;

                        try {
                            String strID = Integer.toString(theBook.ID());
                            textView_bookID.setText(strID);
                            editText_bookTitle.setText(theBook.Title());
                            editText_bookAuthor.setText(theBook.Authors());

                            String strYear = Integer.toString(theBook.Year());
                            editText_bookYear.setText(strYear);

                            String strPages = Integer.toString(theBook.NumberOfPages());
                            editText_bookPages.setText(strPages);

                            editText_bookISBN.setText(theBook.ISBN());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        /*
                        editText_bookTitle.setText(theBook.Title());
                        editText_bookAuthor.setText(theBook.Authors());
                        editText_bookYear.setText(theBook.Year());
                        editText_bookPages.setText(theBook.NumberOfPages());
                        editText_bookISBN.setText(theBook.ISBN());
                        */

                        Button dlgButtonOK = (Button) dlgBookDetails.findViewById(R.id.button_Ok) ;
                        dlgButtonOK.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dlgBookDetails.dismiss();
                            }
                        });

                        dlgBookDetails.show();

                    }
                }
        );

        theBooksDAO.Close() ;
    }

    @Override
    protected void onResume() {
        theBooksDAO.Open() ;
        super.onResume();
    }

    @Override
    protected void onPause() {
        theBooksDAO.Close();
        super.onPause();
    }

    private void CreateSampleContent() {
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

        theBooksDAO.DeleteAll() ;
        theBooksDAO.Add(book001);
        theBooksDAO.Add(book002);
        theBooksDAO.Add(book004);
        theBooksDAO.Add(book005);
        theBooksDAO.Add(book006);
    }
}
