package edu.uab.tindi.mobilecomputingassignment006b;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mike on 12/12/2017.
 */

public class BooksAdapter extends ArrayAdapter<Book> {
    ArrayList<Book> _listOfBooks = new ArrayList<>() ;

    public BooksAdapter(Context         theContext,
                        int             textViewResourceID,
                        ArrayList<Book> objects){
        super(theContext, textViewResourceID, objects) ;
        _listOfBooks = objects ;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Book thisBook = getItem(position) ;
        if (convertView == null) {
            convertView
                    = LayoutInflater.from(getContext()).inflate(R.layout.books_list_view_items, parent, false);
        }
        TextView textView_bookPosition   = (TextView) convertView.findViewById(R.id.textView_bookListNumber) ;
        TextView textView_bookTitle      = (TextView) convertView.findViewById(R.id.textView_bookTitle) ;

        textView_bookPosition.setText(Integer.toString(position + 1)) ;
        textView_bookTitle.setText(thisBook.Title()) ;
        return convertView ;

    }

}   //  End class Adapter
