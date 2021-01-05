package edu.uab.tindi.mobilecomputingassignment006b;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.uab.tindi.mobilecomputingassignment006b.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FRGMainFragment extends Fragment {


    public FRGMainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the dialog_book_filter for this fragment
        return inflater.inflate(R.layout.fragment_frgmain, container, false);
    }

}
