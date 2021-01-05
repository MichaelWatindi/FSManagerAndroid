package edu.uab.tindi.mobilecomputingassignment006b;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FrgEditBook.OnEditBookInteractionListener} interface
 * to handle interaction events.
 */
public class FrgEditBook extends Fragment {

    private OnEditBookInteractionListener mListener;

    public FrgEditBook() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the dialog_book_filter for this fragment
        View theView = inflater.inflate(R.layout.fragment_frg_edit_book, container, false) ;
        return theView ;
        // return inflater.inflate(R.dialog_book_filter.fragment_frg_edit_book, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnEditBookInteractionListener) {
            mListener = (OnEditBookInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnEditBookInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
