package edu.uab.tindi.mobilecomputingassignment004;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Date;


public class FAddTaskFragment extends Fragment {
    private ArrayList<User> theListOfAllUsers ;
    private UserDAO         aUserDAO ;
    private Spinner         spinnerPickUser ;
    private int             theUserID ;

    private FNavigationFragment.InterFaceNavigationFragmentListener listener;
    EditText editTextAddTaskTitle ;
    EditText editTextAddDateDue ;
    EditText editTextAddDateAssigned ;
    EditText editTextAddTaskProject ;
    EditText editTextAddCompleted ;
    CheckBox checkBoxTaskCompleted ;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup      container,
                             Bundle         savedInstanceState) {
        // Inflate the layout for this fragment
        View theView = inflater.inflate(R.layout.fragment_fadd_task,container, false ) ;
        try {

            aUserDAO = new UserDAO(this.getActivity()) ;
            theListOfAllUsers = new ArrayList<>() ;
            theListOfAllUsers = aUserDAO.GetAllUsers() ;

            spinnerPickUser = (Spinner) theView.findViewById(R.id.spinnerAddTaskPickUser) ;

            editTextAddTaskTitle    = (EditText) theView.findViewById(R.id.editText_AddTaskTitle);
            editTextAddDateDue      = (EditText) theView.findViewById(R.id.editText_AddTaskDateDue);
            editTextAddDateAssigned = (EditText) theView.findViewById(R.id.editText_AddTaskDateAssigned);
            editTextAddTaskProject  = (EditText) theView.findViewById(R.id.editText_AddTaskProject);
            checkBoxTaskCompleted   = (CheckBox) theView.findViewById(R.id.chkbox_navigationCompleted) ;


            UsersSpinnerAdapter spinnerUserAdapter = new UsersSpinnerAdapter(this.getActivity(), R.layout.users_list_view, theListOfAllUsers) ;
            spinnerPickUser.setAdapter(spinnerUserAdapter) ;

            if (theListOfAllUsers.size() == 0)
            {
                spinnerPickUser.setVisibility(View.GONE) ;
            }
            //
            int i = 0;
            System.out.println(i) ;

            Button btnCancelTask = (Button) theView.findViewById(R.id.btn_AddTaskCancel) ;
            btnCancelTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    editTextAddTaskTitle.setText("") ;
                    editTextAddDateDue.setText("") ;
                    editTextAddDateAssigned.setText("") ;
                    editTextAddTaskProject.setText("") ;

                    OnNavigationItemClicked("TASK_SAVE");
                }
            });

            Button btnAddTask = (Button) theView.findViewById(R.id.btn_AddTaskSave) ;
            btnAddTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //  Get all texts and create a task
                    Task aNewTask = new Task("") ;
                    aNewTask.Title(editTextAddTaskTitle.getText().toString()) ;

                    boolean isComplete = false ;

                    if(checkBoxTaskCompleted.isChecked()){
                        isComplete = true;
                    }

                    aNewTask.TimeToBeCompleted(new Date());
                    aNewTask.TimeAssigned(new Date()) ;
                    aNewTask.Completed(isComplete) ;
                    aNewTask.Project(editTextAddTaskProject.getText().toString()) ;
                    aNewTask.UserID();
                    aNewTask.ID(theUserID) ;

                    editTextAddTaskTitle.setText("") ;
                    editTextAddDateDue.setText("") ;
                    editTextAddDateAssigned.setText("") ;
                    editTextAddTaskProject.setText("") ;

                    TaskDAO aNewTaskDAO = new TaskDAO(getActivity()) ;
                    aNewTaskDAO.AddTask(aNewTask) ;

                    OnNavigationItemClicked("TASK_SAVE");
                }
            });

        } catch(Exception ex) {
            String exMessage = ex.getMessage();
            System.out.println(exMessage) ;
        }
        return theView ;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FNavigationFragment.InterFaceNavigationFragmentListener) {
            listener = (FNavigationFragment.InterFaceNavigationFragmentListener) context;
        } else {
            throw new ClassCastException(context.toString() +
                    "-did not implement interface OnItemSelectedListener");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState) ;

        Bundle bundle = getArguments() ;
        if (bundle != null) {


        }
    }

    public void OnNavigationItemClicked(String info){
        listener.onNavigationItemSelected(info, null);
    }

    public void SetUserID(String userID) {
        theUserID = Integer.parseInt(userID) ;
        int i = theUserID ;
        int x = i ;
    }
    public void OnCheckBoxCompleted_Clicked(View view){

    }
}
