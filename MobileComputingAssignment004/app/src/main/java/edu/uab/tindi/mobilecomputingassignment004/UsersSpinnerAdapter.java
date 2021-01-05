package edu.uab.tindi.mobilecomputingassignment004;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mike on 10/28/2017.
 */

public class UsersSpinnerAdapter extends ArrayAdapter<User> {
    ArrayList<User> listOfUsers = new ArrayList<>() ;

    public UsersSpinnerAdapter(Context          context,
                               int              resourceId ,
                               ArrayList<User>  objects ) {
        super( context, resourceId, objects ) ;
        listOfUsers = objects ;

    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    public View getCustomView(int position, View v, ViewGroup parent) {

        User aProjectUser = getItem(position) ;
        if(v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.users_list_view, parent, false) ;
        }
        TextView lTVuserID      = (TextView) v.findViewById(R.id.listTextViewUserID) ;
        TextView lTVuserName    = (TextView) v.findViewById(R.id.listTextViewUserName) ;

        try{
            int idOfUser = listOfUsers.get(position).ID();
            lTVuserID.setText(Integer.toString(idOfUser)) ;
            lTVuserName.setText(listOfUsers.get(position).Name()) ;
        }
        catch ( Exception generalException) {
            String errorMessage = generalException.getMessage() ;
            System.out.println(errorMessage) ;
        }
        return v;
    }
    @Override

    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }
}
