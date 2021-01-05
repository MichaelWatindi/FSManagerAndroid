package edu.uab.tindi.mobilecomputingassignment004;

import android.view.View;
import android.widget.AdapterView;

/**
 * Created by Mike on 11/3/2017.
 */


public class CustomOnUserSelectedListener implements AdapterView.OnItemSelectedListener {
    public void onItemSelected(AdapterView<?>   parent,
                               View aView,
                               int              position,
                               long             id) {
        Task theCurrentTask = (Task) parent.getItemAtPosition(position) ;
        int i = theCurrentTask.ID();

    }


    public void onNothingSelected(AdapterView<?> arg0){

        int i = 120 ;
        i = 1 + 9 ;
        System.out.println(Integer.toString(i)) ;
    }
}