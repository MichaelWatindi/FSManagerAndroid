package edu.uab.tindi.mobilecomputingassignment004;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class FTasksOverview extends Fragment {
    ListView listViewTasks ;
    ArrayList<Task> theListOfTasks ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View theMainView = inflater.inflate(R.layout.fragment_ftasks_overview, container, false);

            int i = theListOfTasks.size();
            i = theListOfTasks.size() ;
            AdapterTasks tasksAdapter = new AdapterTasks(getActivity(), R.layout.users_list_view, theListOfTasks) ;
            listViewTasks = (ListView) theMainView.findViewById(R.id.listViewTasksOverviewTasks) ;
            listViewTasks.setAdapter(tasksAdapter) ;
        return theMainView ;
    }

    public void PassDataToFragment (ArrayList<Task> aListOfTasks) {
        theListOfTasks = new ArrayList<>() ;
        theListOfTasks = aListOfTasks ;
    }
}
