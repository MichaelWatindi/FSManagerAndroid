package edu.uab.tindi.mobilecomputingassignment004;

// import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class        ACTNavigationActivity
        extends     AppCompatActivity
        implements  FNavigationFragment.InterFaceNavigationFragmentListener {

    public String theUserID ;
    public ArrayList<Task> theListOfTasks ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actnavigation);
        Intent _naviIntent = getIntent();
        theUserID = _naviIntent.getStringExtra("USER_ID");
        theListOfTasks = new ArrayList<>() ;

        if (getResources().getBoolean(R.bool.twoPaneMode)) {
            FragmentManager aFragmentManager = getSupportFragmentManager();
            FragmentTransaction aFragmentTransaction = aFragmentManager.beginTransaction();
            aFragmentTransaction.add(R.id.fragment_NavigationLandscapeList, new FNavigationFragment());
            aFragmentTransaction.add(R.id.fragment_NavigationLandscapeDetails, new FAddTaskFragment());
            aFragmentTransaction.commit();
        } else {
            if (savedInstanceState != null) {
                getSupportFragmentManager().executePendingTransactions();
                Fragment aFragmentByID = getSupportFragmentManager().findFragmentById(R.id.fragment_PortraitContent);
                if (aFragmentByID != null) {
                    getSupportFragmentManager().beginTransaction().remove(aFragmentByID).commit();
                }
            }
            FNavigationFragment theNavigationFragment = new FNavigationFragment();
            FragmentManager aFragmentManager = getSupportFragmentManager();
            FragmentTransaction aFragmentTransaction = aFragmentManager.beginTransaction();
            aFragmentTransaction.replace(R.id.fragment_PortraitContent, (Fragment) theNavigationFragment);
            aFragmentTransaction.commit();

        }

        /*if(getResources().getBoolean(R.bool.twoPaneMode)){ return ; }

        if (savedInstanceState != null) {
            getSupportFragmentManager().executePendingTransactions() ;
            Fragment fragmentByID = getSupportFragmentManager().findFragmentById(R.id.fragment_PortraitContent) ;
            if(fragmentByID != null) {
                getSupportFragmentManager().beginTransaction().remove(fragmentByID).commit() ;
            }
        }

        FNavigationFragment theNavigationFragment = new FNavigationFragment() ;
        FrameLayout frameContainer = (FrameLayout) findViewById(R.id.fragment_PortraitContent) ;
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_PortraitContent).commit() ;

        FragmentManager afrgManager =  getSupportFragmentManager();
        FragmentTransaction aFrgTransaction = afrgManager.beginTransaction();
        aFrgTransaction.replace(R.id.fragment_container, (Fragment)theListFragment);
        aFrgTransaction.commit();*/

    }

    public void onNavigationItemSelected(String info, String info2) {
        if (info == "TASK_SAVE" || info == "TASK_CANCEL"){
            TaskDAO taskDAO = new TaskDAO(this) ;
            theListOfTasks = taskDAO.GetAllTasksForUser(theUserID) ;
            FTasksOverview anOverviewOfTasks = new FTasksOverview();
            anOverviewOfTasks.PassDataToFragment(theListOfTasks) ;

            if(getResources().getBoolean(R.bool.twoPaneMode)) {
                FragmentTransaction aFragTrans = getSupportFragmentManager().beginTransaction();
                aFragTrans.replace(R.id.fragment_NavigationLandscapeDetails, anOverviewOfTasks) ;
                aFragTrans.addToBackStack("LIST") ;
                aFragTrans.commit();
            }
            else {
                FragmentTransaction aFragmentTransaction = getSupportFragmentManager().beginTransaction() ;
                aFragmentTransaction.replace(R.id.fragment_PortraitContent, anOverviewOfTasks) ;
                aFragmentTransaction.addToBackStack("LIST") ;
                aFragmentTransaction.commit();
            }
        }
        else if (info == "ADD_TASK") {
            TaskDAO taskDAO = new TaskDAO(this) ;
            theListOfTasks = taskDAO.GetAllTasksForUser(theUserID) ;
            FAddTaskFragment anAddTaskFragment = new FAddTaskFragment();
            anAddTaskFragment.SetUserID(theUserID) ;

            if(getResources().getBoolean(R.bool.twoPaneMode)) {
                FragmentTransaction aFragTrans = getSupportFragmentManager().beginTransaction();
                aFragTrans.replace(R.id.fragment_NavigationLandscapeDetails, anAddTaskFragment) ;
                aFragTrans.addToBackStack("TASK_ADD_TASK") ;
                aFragTrans.commit();
            }
            else {
                FragmentTransaction aFragmentTransaction = getSupportFragmentManager().beginTransaction() ;
                aFragmentTransaction.replace(R.id.fragment_PortraitContent, anAddTaskFragment) ;
                aFragmentTransaction.addToBackStack(null) ;
                aFragmentTransaction.commit();
            }
        }
        else if (info == "DISPLAY_ALL") {
            TaskDAO taskDAO = new TaskDAO(this) ;
            theListOfTasks = taskDAO.GetAllTasksForUser(theUserID) ;
            FTasksOverview anOverviewOfTasks = new FTasksOverview();
            anOverviewOfTasks.PassDataToFragment(theListOfTasks) ;

            if(getResources().getBoolean(R.bool.twoPaneMode)) {

                FragmentTransaction aFragTrans = getSupportFragmentManager().beginTransaction();
                aFragTrans.replace(R.id.fragment_NavigationLandscapeDetails, anOverviewOfTasks) ;
                aFragTrans.addToBackStack("LIST") ;
                aFragTrans.commit();
            }
            else {
                FragmentTransaction aFragmentTransaction = getSupportFragmentManager().beginTransaction() ;
                aFragmentTransaction.replace(R.id.fragment_PortraitContent, anOverviewOfTasks) ;
                aFragmentTransaction.addToBackStack("LIST") ;
                aFragmentTransaction.commit();
            }
        }
        else if (info == "DISPLAY_COMPLETE_TASKS") {
            TaskDAO taskDAO = new TaskDAO(this) ;
            theListOfTasks = taskDAO.GetCompleteTasksForUser(theUserID) ;
            FTasksOverview anOverviewOfTasks = new FTasksOverview();
            anOverviewOfTasks.PassDataToFragment(theListOfTasks) ;

            if(getResources().getBoolean(R.bool.twoPaneMode)) {
                FragmentTransaction aFragTrans = getSupportFragmentManager().beginTransaction();
                aFragTrans.replace(R.id.fragment_NavigationLandscapeDetails, anOverviewOfTasks) ;
                aFragTrans.addToBackStack("LIST") ;
                aFragTrans.commit();
            }
            else {
                FragmentTransaction aFragmentTransaction = getSupportFragmentManager().beginTransaction() ;
                aFragmentTransaction.replace(R.id.fragment_PortraitContent, anOverviewOfTasks) ;
                aFragmentTransaction.addToBackStack("LIST") ;
                aFragmentTransaction.commit();
            }

        }
        else if (info == "DISPLAY_INCOMPLETE_TASKS") {
            TaskDAO taskDAO = new TaskDAO(this) ;
            theListOfTasks = taskDAO.GetIncompleteTasksForUser(theUserID) ;
            FTasksOverview anOverviewOfTasks = new FTasksOverview();
            anOverviewOfTasks.PassDataToFragment(theListOfTasks) ;

            if(getResources().getBoolean(R.bool.twoPaneMode)) {
                FragmentTransaction aFragTrans = getSupportFragmentManager().beginTransaction();
                aFragTrans.replace(R.id.fragment_NavigationLandscapeDetails, anOverviewOfTasks) ;
                aFragTrans.addToBackStack("LIST") ;
                aFragTrans.commit();
            }
            else {
                FragmentTransaction aFragmentTransaction = getSupportFragmentManager().beginTransaction() ;
                aFragmentTransaction.replace(R.id.fragment_PortraitContent, anOverviewOfTasks) ;
                aFragmentTransaction.addToBackStack("LIST") ;
                aFragmentTransaction.commit();
            }

        }
        else if (info == "DISPLAY_TASKS_DUE_ON_DATE") {
            TaskDAO taskDAO = new TaskDAO(this) ;
            theListOfTasks = taskDAO.GetTasksDueOnDate(theUserID) ;
            FTasksOverview anOverviewOfTasks = new FTasksOverview();
            anOverviewOfTasks.PassDataToFragment(theListOfTasks) ;

            if(getResources().getBoolean(R.bool.twoPaneMode)) {
                FragmentTransaction aFragTrans = getSupportFragmentManager().beginTransaction();
                aFragTrans.replace(R.id.fragment_NavigationLandscapeDetails, anOverviewOfTasks) ;
                aFragTrans.addToBackStack("LIST") ;
                aFragTrans.commit();
            }
            else {
                FragmentTransaction aFragmentTransaction = getSupportFragmentManager().beginTransaction() ;
                aFragmentTransaction.replace(R.id.fragment_PortraitContent, anOverviewOfTasks) ;
                aFragmentTransaction.addToBackStack("LIST") ;
                aFragmentTransaction.commit();
            }
        }
        else if (info == "DISPLAY_TASKS_SEARCH") {
            TaskDAO taskDAO = new TaskDAO(this) ;
            theListOfTasks = taskDAO.GetTasksSearched(theUserID, info2) ;
            FTasksOverview anOverviewOfTasks = new FTasksOverview();
            anOverviewOfTasks.PassDataToFragment(theListOfTasks) ;

            if(getResources().getBoolean(R.bool.twoPaneMode)) {
                FragmentTransaction aFragTrans = getSupportFragmentManager().beginTransaction();
                aFragTrans.replace(R.id.fragment_NavigationLandscapeDetails, anOverviewOfTasks) ;
                aFragTrans.addToBackStack("LIST") ;
                aFragTrans.commit();
            }
            else {
                FragmentTransaction aFragmentTransaction = getSupportFragmentManager().beginTransaction() ;
                aFragmentTransaction.replace(R.id.fragment_PortraitContent, anOverviewOfTasks) ;
                aFragmentTransaction.addToBackStack("LIST") ;
                aFragmentTransaction.commit();
            }
        }
        else if (info == "DISPLAY_TASKS_TO_BE_EDITED") {
            if(getResources().getBoolean(R.bool.twoPaneMode)) {
                FragmentTransaction aFragTrans = getSupportFragmentManager().beginTransaction();
                aFragTrans.replace(R.id.fragment_NavigationLandscapeDetails, new FAddTaskFragment()) ;
                aFragTrans.addToBackStack("TASK_ADD_TASK") ;
                aFragTrans.commit();
            }
            else {
                FragmentTransaction aFragmentTransaction = getSupportFragmentManager().beginTransaction() ;
                aFragmentTransaction.replace(R.id.fragment_PortraitContent, new FAddTaskFragment()) ;
                aFragmentTransaction.addToBackStack(null) ;
                aFragmentTransaction.commit();
            }
        }
        else{

        }

    }

    public void CreateSampleContent() {

    }
}


