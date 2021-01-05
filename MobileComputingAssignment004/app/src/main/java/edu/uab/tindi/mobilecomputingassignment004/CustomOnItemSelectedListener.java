package edu.uab.tindi.mobilecomputingassignment004;

import android.view.View;
import android.widget.AdapterView;

/**
 * Created by Mike on 10/29/2017.
 */

public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {
    public void onItemSelected(AdapterView<?>   parent,
                               View             aView,
                               int              position,
                               long             id) {
        User theCurrentUser = (User) parent.getItemAtPosition(position) ;

        LoginActivity theParentActivity = (LoginActivity) parent.getContext() ;
        theParentActivity.SetCurrentUserId(Integer.toString(theCurrentUser.ID())) ;

    }

    public void onNothingSelected(AdapterView<?> arg0){

        int i = 120 ;
        i = 1 + 9 ;
        System.out.println(Integer.toString(i)) ;
    }
}
