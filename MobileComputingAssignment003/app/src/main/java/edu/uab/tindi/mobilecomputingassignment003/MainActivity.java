package edu.uab.tindi.mobilecomputingassignment003;

import android.app.*;
import android.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager ;
import android.support.v4.app.FragmentTransaction ;
import java.util.ArrayList;

public class        MainActivity
        extends     AppCompatActivity
        implements  OnItemSelectedListener {

    ListView courseListView;
    ArrayList<AcademicCourse> acAcademicCourses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getResources().getBoolean(R.bool.twoPaneMode)){
            FragmentManager fm = getSupportFragmentManager() ;
            FragmentTransaction ft = fm.beginTransaction() ;
            ft.add(R.id.FrameListOfCourse, new edu.uab.tindi.mobilecomputingassignment003.ListOfCoursesFragment()) ;
            ft.add(R.id.FrameDetailsOfCourse, new DetailsFragment());
            ft.commit();
        }
        else {
            if(savedInstanceState != null) {
                getFragmentManager().executePendingTransactions();
                Fragment fragmentById =
                        getSupportFragmentManager().findFragmentById(R.id.fragment_container) ;
                if(fragmentById != null) {
                    getSupportFragmentManager().beginTransaction().remove(fragmentById).commit();
                }
            }

            ListOfCoursesFragment theListFragment = new ListOfCoursesFragment() ;
            FragmentManager afrgManager =  getSupportFragmentManager();
            FragmentTransaction aFrgTransaction = afrgManager.beginTransaction();
            aFrgTransaction.replace(R.id.fragment_container, (Fragment)theListFragment);
            aFrgTransaction.commit();
        }
    }

    public void onCourseSelected(AcademicCourse info) {
        if(getResources().getBoolean(R.bool.twoPaneMode)){
            DetailsFragment  frgDetails =
                    (DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.FrameDetailsOfCourse) ;
            frgDetails.SetAcademicCourseDetails(info) ;

        }
        else {
            DetailsFragment frgDetails = new DetailsFragment();
            Bundle args = new Bundle() ;
            args.putString("NAME", info.Name()) ;
            args.putString("INSTRUCTOR", info.Instructor());
            args.putString("DESCRIPTION", info.Description());
            args.putString("ROOM", info.Room());
            args.putString("TEXTBOOK", info.TextBook());
            frgDetails.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, frgDetails);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}

/*
public interface OnItemSelectedListener {
    void onCourseSelected(Aca info);
}*/
