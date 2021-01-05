package edu.uab.tindi.mobilecomputingassignment006b;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FrgListBooks extends Fragment {
    ListView            listView_listOfBooks ;
    ArrayList<Book>     listOfBooks = new ArrayList<>() ;
    private FrgListBookActionListener theListener ;

    public FrgListBooks() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup      container,
                             Bundle         savedInstanceState) {

        View theView = inflater.inflate(R.layout.fragment_frg_list_books, container, false) ;

        listView_listOfBooks = (ListView) theView.findViewById(R.id.listView_ListBooks) ;
        BooksAdapter adapterListOfBooks
                = new  BooksAdapter(getActivity(), R.layout.books_list_view_items, listOfBooks) ;
        listView_listOfBooks.setAdapter(adapterListOfBooks) ;

        listView_listOfBooks.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book theBook = (Book) listView_listOfBooks.getItemAtPosition(position) ;
                theListener.OnItemOnListClicked(theBook) ;
            }
        });
        // Inflate the dialog_book_filter for this fragment
       return theView ;
    }

    public void SetObjects(ArrayList<Book> theListOfBooks){

        this.listOfBooks = theListOfBooks ;
    }

    public interface FrgListBookActionListener{
        void OnItemOnListClicked(Book theBook) ;
    }

    public void onAttach(Context theContext) {
        super.onAttach(theContext);
        if (theContext instanceof FrgListBookActionListener) {
            theListener = (FrgListBookActionListener) theContext ;
        } else {
            throw new ClassCastException(theContext.toString() + " NO INTERFACE") ;
        }
    }


}
