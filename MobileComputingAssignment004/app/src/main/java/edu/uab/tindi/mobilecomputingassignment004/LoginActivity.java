package edu.uab.tindi.mobilecomputingassignment004;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter ;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class    LoginActivity
        extends AppCompatActivity {
    ProjectManagerDBHandler aDBHandlerProject ;
    UserDAO                 aUserDAO ;
    TextView                listViewOfUsers ;
    Spinner                 spinnerPickUsers ;

    ArrayList<User>         allUsers ;
    String                  idOfActiveUser ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState) ;
        setContentView(R.layout.activity_login) ;
        aUserDAO = new UserDAO(this) ;
        idOfActiveUser = "" ;
        aUserDAO.Open();

        try {
            listViewOfUsers     = (TextView)findViewById(R.id.textViewListOfUsers) ;
            spinnerPickUsers    = (Spinner) findViewById(R.id.spinnerPickUser) ;

            InsertSampleData() ;
            allUsers            = aUserDAO.GetAllUsers() ;
            UsersSpinnerAdapter usersSpinnerAdpt = new UsersSpinnerAdapter(this, R.layout.users_list_view, allUsers) ;
            spinnerPickUsers.setAdapter(usersSpinnerAdpt) ;
            ShowAllUsers() ;
            aUserDAO.Close() ;

            spinnerPickUsers.setOnItemSelectedListener(new CustomOnItemSelectedListener()) ;

        }
        catch (Exception errorEx ) {
            String strError = errorEx.getMessage() ;
            System.out.println(strError) ;
        }

    }

    void InsertSampleData(){
        aDBHandlerProject = new ProjectManagerDBHandler(this);

        User User_Admn = new User("Admin") ;
        User User_Mark = new User("Mark") ;
        User User_Luke = new User("Luke") ;
        User User_John = new User("John") ;
        User User_Paul = new User("Paul") ;

        aUserDAO = new UserDAO(this) ;
        aUserDAO.DeleteAllUsers() ;
        aUserDAO.AddUser(User_Admn) ;
        aUserDAO.AddUser(User_Mark) ;
        aUserDAO.AddUser(User_Luke) ;
        aUserDAO.AddUser(User_John) ;
        aUserDAO.AddUser(User_Paul) ;

        TaskDAO aTaskDA0 = new TaskDAO(this) ;
        aTaskDA0.DeleteAllTasks() ;

        //  Mark
        
        Task task_Mark_001_Home = new Task("Task 001 user Mark, Home") ;
        task_Mark_001_Home.Project("Home") ;
        task_Mark_001_Home.UserID(aUserDAO.GetUserIDByName("Mark")) ;
        aTaskDA0.AddTask(task_Mark_001_Home) ;

        Task task_Mark_002_Home = new Task("Task 002 user Mark, Home") ;
        task_Mark_002_Home.Project("Home") ;
        task_Mark_002_Home.UserID(aUserDAO.GetUserIDByName("Mark")) ;
        aTaskDA0.AddTask(task_Mark_002_Home) ;

        Task task_Mark_003_Home = new Task("Task 003 user Mark, Home") ;
        task_Mark_003_Home.Project("Home") ;
        task_Mark_003_Home.UserID(aUserDAO.GetUserIDByName("Mark")) ;
        aTaskDA0.AddTask(task_Mark_003_Home) ;

        Task task_Mark_004_Home = new Task("Task 004 user Mark, Home") ;
        task_Mark_004_Home.Project("Home") ;
        task_Mark_004_Home.UserID(aUserDAO.GetUserIDByName("Mark")) ;
        aTaskDA0.AddTask(task_Mark_004_Home) ;

        Task task_Mark_005_Home = new Task("Task 005 user Mark, Home") ;
        task_Mark_005_Home.Project("Home") ;
        task_Mark_005_Home.UserID(aUserDAO.GetUserIDByName("Mark")) ;
        aTaskDA0.AddTask(task_Mark_005_Home) ;

        Task task_Mark_001_School = new Task("Task 001 user Mark, School") ;
        task_Mark_001_School.Project("School") ;
        task_Mark_001_School.UserID(aUserDAO.GetUserIDByName("Mark")) ;
        aTaskDA0.AddTask(task_Mark_001_School) ;

        Task task_Mark_002_School = new Task("Task 002 user Mark, School") ;
        task_Mark_002_School.Project("School") ;
        task_Mark_002_School.UserID(aUserDAO.GetUserIDByName("Mark")) ;
        aTaskDA0.AddTask(task_Mark_002_School) ;

        Task task_Mark_003_School = new Task("Task 003 user Mark, School") ;
        task_Mark_003_School.Project("School") ;
        task_Mark_003_School.UserID(aUserDAO.GetUserIDByName("Mark")) ;
        aTaskDA0.AddTask(task_Mark_003_School) ;

        Task task_Mark_004_School = new Task("Task 004 user Mark, School") ;
        task_Mark_004_School.Project("School") ;
        task_Mark_004_School.UserID(aUserDAO.GetUserIDByName("Mark")) ;
        aTaskDA0.AddTask(task_Mark_004_School) ;

        Task task_Mark_005_School = new Task("Task 005 user Mark, School") ;
        task_Mark_005_School.Project("School") ;
        task_Mark_005_School.UserID(aUserDAO.GetUserIDByName("Mark")) ;
        aTaskDA0.AddTask(task_Mark_005_School) ;

        Task task_Mark_001_Work = new Task("Task 001 user Mark, Work") ;
        task_Mark_001_Work.Project("Work") ;
        task_Mark_001_School.UserID(aUserDAO.GetUserIDByName("Mark")) ;
        aTaskDA0.AddTask(task_Mark_001_Work) ;

        Task task_Mark_002_Work = new Task("Task 002 user Mark, Work") ;
        task_Mark_002_Work.Project("Work") ;
        task_Mark_002_School.UserID(aUserDAO.GetUserIDByName("Mark")) ;
        aTaskDA0.AddTask(task_Mark_002_Work) ;

        Task task_Mark_003_Work = new Task("Task 003 user Mark, Work") ;
        task_Mark_003_Work.Project("Work") ;
        task_Mark_003_School.UserID(aUserDAO.GetUserIDByName("Mark")) ;
        aTaskDA0.AddTask(task_Mark_003_Work) ;

        Task task_Mark_004_Work = new Task("Task 004 user Mark, Work") ;
        task_Mark_004_Work.Project("Work") ;
        task_Mark_004_School.UserID(aUserDAO.GetUserIDByName("Mark")) ;
        aTaskDA0.AddTask(task_Mark_004_Work) ;

        Task task_Mark_005_Work = new Task("Task 005 user Mark, Work") ;
        task_Mark_005_Work.Project("Work") ;
        task_Mark_005_School.UserID(aUserDAO.GetUserIDByName("Mark")) ;
        aTaskDA0.AddTask(task_Mark_005_Work) ;

        // --------------------------------------------------------------
        //  Luke

        Task task_Luke_001_Home = new Task("Task 001 user Luke, Home") ;
        task_Luke_001_Home.Project("Home") ;
        task_Luke_001_Home.UserID(aUserDAO.GetUserIDByName("Luke")) ;
        aTaskDA0.AddTask(task_Luke_001_Home) ;

        Task task_Luke_002_Home = new Task("Task 002 user Luke, Home") ;
        task_Luke_002_Home.Project("Home") ;
        task_Luke_002_Home.UserID(aUserDAO.GetUserIDByName("Luke")) ;
        aTaskDA0.AddTask(task_Luke_002_Home) ;

        Task task_Luke_003_Home = new Task("Task 003 user Luke, Home") ;
        task_Luke_003_Home.Project("Home") ;
        task_Luke_003_Home.UserID(aUserDAO.GetUserIDByName("Luke")) ;
        aTaskDA0.AddTask(task_Luke_003_Home) ;

        Task task_Luke_004_Home = new Task("Task 004 user Luke, Home") ;
        task_Luke_004_Home.Project("Home") ;
        task_Luke_004_Home.UserID(aUserDAO.GetUserIDByName("Luke")) ;
        aTaskDA0.AddTask(task_Luke_004_Home) ;

        Task task_Luke_005_Home = new Task("Task 005 user Luke, Home") ;
        task_Luke_005_Home.Project("Home") ;
        task_Luke_005_Home.UserID(aUserDAO.GetUserIDByName("Luke")) ;
        aTaskDA0.AddTask(task_Luke_005_Home) ;

        Task task_Luke_001_School = new Task("Task 001 user Luke, School") ;
        task_Luke_001_School.Project("School") ;
        task_Luke_001_School.UserID(aUserDAO.GetUserIDByName("Luke")) ;
        aTaskDA0.AddTask(task_Luke_001_School) ;

        Task task_Luke_002_School = new Task("Task 002 user Luke, School") ;
        task_Luke_002_School.Project("School") ;
        task_Luke_002_School.UserID(aUserDAO.GetUserIDByName("Luke")) ;
        aTaskDA0.AddTask(task_Luke_002_School) ;

        Task task_Luke_003_School = new Task("Task 003 user Luke, School") ;
        task_Luke_003_School.Project("School") ;
        task_Luke_003_School.UserID(aUserDAO.GetUserIDByName("Luke")) ;
        aTaskDA0.AddTask(task_Luke_003_School) ;

        Task task_Luke_004_School = new Task("Task 004 user Luke, School") ;
        task_Luke_004_School.Project("School") ;
        task_Luke_004_School.UserID(aUserDAO.GetUserIDByName("Luke")) ;
        aTaskDA0.AddTask(task_Luke_004_School) ;

        Task task_Luke_005_School = new Task("Task 005 user Luke, School") ;
        task_Luke_005_School.Project("School") ;
        task_Luke_005_School.UserID(aUserDAO.GetUserIDByName("Luke")) ;
        aTaskDA0.AddTask(task_Luke_005_School) ;

        Task task_Luke_001_Work = new Task("Task 001 user Luke, Work") ;
        task_Luke_001_Work.Project("Work") ;
        task_Luke_001_School.UserID(aUserDAO.GetUserIDByName("Luke")) ;
        aTaskDA0.AddTask(task_Luke_001_Work) ;

        Task task_Luke_002_Work = new Task("Task 002 user Luke, Work") ;
        task_Luke_002_Work.Project("Work") ;
        task_Luke_002_School.UserID(aUserDAO.GetUserIDByName("Luke")) ;
        aTaskDA0.AddTask(task_Luke_002_Work) ;

        Task task_Luke_003_Work = new Task("Task 003 user Luke, Work") ;
        task_Luke_003_Work.Project("Work") ;
        task_Luke_003_School.UserID(aUserDAO.GetUserIDByName("Luke")) ;
        aTaskDA0.AddTask(task_Luke_003_Work) ;

        Task task_Luke_004_Work = new Task("Task 004 user Luke, Work") ;
        task_Luke_004_Work.Project("Work") ;
        task_Luke_004_School.UserID(aUserDAO.GetUserIDByName("Luke")) ;
        aTaskDA0.AddTask(task_Luke_004_Work) ;

        Task task_Luke_005_Work = new Task("Task 005 user Luke, Work") ;
        task_Luke_005_Work.Project("Work") ;
        task_Luke_005_School.UserID(aUserDAO.GetUserIDByName("Luke")) ;
        aTaskDA0.AddTask(task_Luke_005_Work) ;
        
        // --------------------------------------------------------------
        //  John

        Task task_John_001_Home = new Task("Task 001 user John, Home") ;
        task_John_001_Home.Project("Home") ;
        task_John_001_Home.UserID(aUserDAO.GetUserIDByName("John")) ;
        aTaskDA0.AddTask(task_John_001_Home) ;

        Task task_John_002_Home = new Task("Task 002 user John, Home") ;
        task_John_002_Home.Project("Home") ;
        task_John_002_Home.UserID(aUserDAO.GetUserIDByName("John")) ;
        aTaskDA0.AddTask(task_John_002_Home) ;

        Task task_John_003_Home = new Task("Task 003 user John, Home") ;
        task_John_003_Home.Project("Home") ;
        task_John_003_Home.UserID(aUserDAO.GetUserIDByName("John")) ;
        aTaskDA0.AddTask(task_John_003_Home) ;

        Task task_John_004_Home = new Task("Task 004 user John, Home") ;
        task_John_004_Home.Project("Home") ;
        task_John_004_Home.UserID(aUserDAO.GetUserIDByName("John")) ;
        aTaskDA0.AddTask(task_John_004_Home) ;

        Task task_John_005_Home = new Task("Task 005 user John, Home") ;
        task_John_005_Home.Project("Home") ;
        task_John_005_Home.UserID(aUserDAO.GetUserIDByName("John")) ;
        aTaskDA0.AddTask(task_John_005_Home) ;


        Task task_John_001_School = new Task("Task 001 user John, School") ;
        task_John_001_School.Project("School") ;
        task_John_001_School.UserID(aUserDAO.GetUserIDByName("John")) ;
        aTaskDA0.AddTask(task_John_001_School) ;

        Task task_John_002_School = new Task("Task 002 user John, School") ;
        task_John_002_School.Project("School") ;
        task_John_002_School.UserID(aUserDAO.GetUserIDByName("John")) ;
        aTaskDA0.AddTask(task_John_002_School) ;

        Task task_John_003_School = new Task("Task 003 user John, School") ;
        task_John_003_School.Project("School") ;
        task_John_003_School.UserID(aUserDAO.GetUserIDByName("John")) ;
        aTaskDA0.AddTask(task_John_003_School) ;

        Task task_John_004_School = new Task("Task 004 user John, School") ;
        task_John_004_School.Project("School") ;
        task_John_004_School.UserID(aUserDAO.GetUserIDByName("John")) ;
        aTaskDA0.AddTask(task_John_004_School) ;

        Task task_John_005_School = new Task("Task 005 user John, School") ;
        task_John_005_School.Project("School") ;
        task_John_005_School.UserID(aUserDAO.GetUserIDByName("John")) ;
        aTaskDA0.AddTask(task_John_005_School) ;

        Task task_John_001_Work = new Task("Task 001 user John, Work") ;
        task_John_001_Work.Project("Work") ;
        task_John_001_School.UserID(aUserDAO.GetUserIDByName("John")) ;
        aTaskDA0.AddTask(task_John_001_Work) ;

        Task task_John_002_Work = new Task("Task 002 user John, Work") ;
        task_John_002_Work.Project("Work") ;
        task_John_002_School.UserID(aUserDAO.GetUserIDByName("John")) ;
        aTaskDA0.AddTask(task_John_002_Work) ;

        Task task_John_003_Work = new Task("Task 003 user John, Work") ;
        task_John_003_Work.Project("Work") ;
        task_John_003_School.UserID(aUserDAO.GetUserIDByName("John")) ;
        aTaskDA0.AddTask(task_John_003_Work) ;

        Task task_John_004_Work = new Task("Task 004 user John, Work") ;
        task_John_004_Work.Project("Work") ;
        task_John_004_School.UserID(aUserDAO.GetUserIDByName("John")) ;
        aTaskDA0.AddTask(task_John_004_Work) ;

        Task task_John_005_Work = new Task("Task 005 user John, Work") ;
        task_John_005_Work.Project("Work") ;
        task_John_005_School.UserID(aUserDAO.GetUserIDByName("John")) ;
        aTaskDA0.AddTask(task_John_005_Work) ;

        
        
        // --------------------------------------------------------------
        //  Paul

        Task task_Paul_001_Home = new Task("Task 001 user Paul, Home") ;
        task_Paul_001_Home.Project("Home") ;
        task_Paul_001_Home.UserID(aUserDAO.GetUserIDByName("Paul")) ;
        aTaskDA0.AddTask(task_Paul_001_Home) ;

        Task task_Paul_002_Home = new Task("Task 002 user Paul, Home") ;
        task_Paul_002_Home.Project("Home") ;
        task_Paul_002_Home.UserID(aUserDAO.GetUserIDByName("Paul")) ;
        aTaskDA0.AddTask(task_Paul_002_Home) ;

        Task task_Paul_003_Home = new Task("Task 003 user Paul, Home") ;
        task_Paul_003_Home.Project("Home") ;
        task_Paul_003_Home.UserID(aUserDAO.GetUserIDByName("Paul")) ;
        aTaskDA0.AddTask(task_Paul_003_Home) ;

        Task task_Paul_004_Home = new Task("Task 004 user Paul, Home") ;
        task_Paul_004_Home.Project("Home") ;
        task_Paul_004_Home.UserID(aUserDAO.GetUserIDByName("Paul")) ;
        aTaskDA0.AddTask(task_Paul_004_Home) ;

        Task task_Paul_005_Home = new Task("Task 005 user Paul, Home") ;
        task_Paul_005_Home.Project("Home") ;
        task_Paul_005_Home.UserID(aUserDAO.GetUserIDByName("Paul")) ;
        aTaskDA0.AddTask(task_Paul_005_Home) ;


        Task task_Paul_001_School = new Task("Task 001 user Paul, School") ;
        task_Paul_001_School.Project("School") ;
        task_Paul_001_School.UserID(aUserDAO.GetUserIDByName("Paul")) ;
        aTaskDA0.AddTask(task_Paul_001_School) ;

        Task task_Paul_002_School = new Task("Task 002 user Paul, School") ;
        task_Paul_002_School.Project("School") ;
        task_Paul_002_School.UserID(aUserDAO.GetUserIDByName("Paul")) ;
        aTaskDA0.AddTask(task_Paul_002_School) ;

        Task task_Paul_003_School = new Task("Task 003 user Paul, School") ;
        task_Paul_003_School.Project("School") ;
        task_Paul_003_School.UserID(aUserDAO.GetUserIDByName("Paul")) ;
        aTaskDA0.AddTask(task_Paul_003_School) ;

        Task task_Paul_004_School = new Task("Task 004 user Paul, School") ;
        task_Paul_004_School.Project("School") ;
        task_Paul_004_School.UserID(aUserDAO.GetUserIDByName("Paul")) ;
        aTaskDA0.AddTask(task_Paul_004_School) ;

        Task task_Paul_005_School = new Task("Task 005 user Paul, School") ;
        task_Paul_005_School.Project("School") ;
        task_Paul_005_School.UserID(aUserDAO.GetUserIDByName("Paul")) ;
        aTaskDA0.AddTask(task_Paul_005_School) ;

        Task task_Paul_001_Work = new Task("Task 001 user Paul, Work") ;
        task_Paul_001_Work.Project("Work") ;
        task_Paul_001_School.UserID(aUserDAO.GetUserIDByName("Paul")) ;
        aTaskDA0.AddTask(task_Paul_001_Work) ;

        Task task_Paul_002_Work = new Task("Task 002 user Paul, Work") ;
        task_Paul_002_Work.Project("Work") ;
        task_Paul_002_School.UserID(aUserDAO.GetUserIDByName("Paul")) ;
        aTaskDA0.AddTask(task_Paul_002_Work) ;

        Task task_Paul_003_Work = new Task("Task 003 user Paul, Work") ;
        task_Paul_003_Work.Project("Work") ;
        task_Paul_003_School.UserID(aUserDAO.GetUserIDByName("Paul")) ;
        aTaskDA0.AddTask(task_Paul_003_Work) ;

        Task task_Paul_004_Work = new Task("Task 004 user Paul, Work") ;
        task_Paul_004_Work.Project("Work") ;
        task_Paul_004_School.UserID(aUserDAO.GetUserIDByName("Paul")) ;
        aTaskDA0.AddTask(task_Paul_004_Work) ;

        Task task_Paul_005_Work = new Task("Task 005 user Paul, Work") ;
        task_Paul_005_Work.Project("Work") ;
        task_Paul_005_School.UserID(aUserDAO.GetUserIDByName("Paul")) ;
        aTaskDA0.AddTask(task_Paul_005_Work) ;
       

        return ;

    }

    void OnBtnLogin_Click(View aView) {
        Intent intentNavigationPage = new Intent(
                this, ACTNavigationActivity.class) ;

        intentNavigationPage.putExtra("USER_ID", idOfActiveUser) ;
        startActivity(intentNavigationPage) ;
    }

    void SetCurrentUserId(String theCurrentUser) {
        idOfActiveUser = theCurrentUser ;
    }

    void ShowAllUsers() {
        String str = "";
        for (User u : allUsers) {
            String row = u.ID() + ": Name: " +  u.Name() ;
            str += row + "\n";
        }
        listViewOfUsers.setText(str);

    }

    @Override
    protected void onResume() {
        aUserDAO.Open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        aUserDAO.Close();
        super.onPause();
    }


}
