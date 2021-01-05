package edu.uab.tindi.mobilecomputingassignment004;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mike on 11/2/2017.
 */

public class AdapterTasks extends ArrayAdapter<Task> {
    ArrayList<Task> listOfTasks = new ArrayList<>() ;

    public AdapterTasks(Context             context,
                        int                 resourceId ,
                        ArrayList<Task>     objects ) {
        super(context,resourceId, objects) ;
        listOfTasks = objects ;

    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    public View getCustomView(int position, View v, ViewGroup parent) {

        Task aProjectTask = getItem(position) ;
        if(v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.users_list_view, parent, false) ;
        }
        TextView lTVuserID      = (TextView) v.findViewById(R.id.listTextViewUserID) ;
        TextView lTVuserName    = (TextView) v.findViewById(R.id.listTextViewUserName) ;

        try{
            int idOfUser = listOfTasks.get(position).ID();
            lTVuserID.setText(Integer.toString(idOfUser)) ;
            lTVuserName.setText(listOfTasks.get(position).Title()) ;
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
