package edu.uab.tindi.mobilecomputingassignment005;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Mike on 11/26/2017.
 */

public class AdapterSpinnerUser extends ArrayAdapter<User> {
    ArrayList<User> listOfUsers = new ArrayList<>() ;

    public AdapterSpinnerUser(Context           context,
                               int              resourceID,
                               ArrayList<User>  objects) {
        super( context, resourceID, objects ) ;
        listOfUsers = objects ;
    }

    @Override
    public int getCount(){
        return super.getCount() ;
    }

    public void getCustomView(int           position,
                              View          aView,
                              ViewGroup     parent) {
        User aUser = getItem(position) ;

    }
}
