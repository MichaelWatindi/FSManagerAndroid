package edu.uab.tindi.mobilecomputingassignment004;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;


public class FNavigationFragment extends Fragment {

    private InterFaceNavigationFragmentListener listener;
    private TextView txtViewUser_ID ;
    private EditText editText_NavigationSearchTask ;
    public String theUserID = "";
    private View view ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fnavigations, container, false) ;
        try {

            ACTNavigationActivity parentActivity = (ACTNavigationActivity) getActivity() ;
            String theUserID = parentActivity.theUserID ;

            txtViewUser_ID = (TextView)view.findViewById(R.id.txtView_FrgmntNavigationUserID) ;
            txtViewUser_ID.setText(theUserID) ;

            Button btnAddTask = (Button) view.findViewById(R.id.btn_AddTask) ;
            btnAddTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnNavigationItemClicked("ADD_TASK");
                }
            });

        }
        catch (Exception ex){
            String exMessage = ex.getMessage();
            System.out.println(exMessage);
        }


        Button btnDisplayAll = (Button) view.findViewById(R.id.btn_DisplayAll) ;
        btnDisplayAll.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                OnNavigationItemClicked("DISPLAY_ALL") ;
            }
        });

        Button btnDisplayCompleteTasks = (Button) view.findViewById(R.id.btn_DisplayCompleteTasks) ;
        btnDisplayCompleteTasks.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                OnNavigationItemClicked("DISPLAY_COMPLETE_TASKS") ;
            }
        });

        Button btnDisplayIncompleteTasks = (Button) view.findViewById(R.id.btn_DisplayIncompleteTasks) ;
        btnDisplayIncompleteTasks.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                OnNavigationItemClicked("DISPLAY_INCOMPLETE_TASKS") ;
            }
        });

        Button btnDisplayTasksDueOnDate = (Button) view.findViewById(R.id.btn_DisplayTasksDueOnDate) ;
        btnDisplayTasksDueOnDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                OnNavigationItemClicked("DISPLAY_TASKS_DUE_ON_DATE") ;
            }
        });

        Button btnSearchTasks = (Button) view.findViewById(R.id.btn_DisplayTasksSearched) ;
        btnSearchTasks.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                editText_NavigationSearchTask = (EditText)view.findViewById(R.id.editText_NavigationSearchTask);
                OnNavigationItemClicked("DISPLAY_TASKS_SEARCH", editText_NavigationSearchTask.getText().toString()) ;
            }
        });

        Button btnEditTasks = (Button) view.findViewById(R.id.btn_NavigationEditTask) ;
        btnEditTasks.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                EditText editText_NavigationIDOfTask = (EditText)view.findViewById(R.id.editText_NavigationIDOfTask) ;
                OnNavigationItemClicked("DISPLAY_TASKS_TO_BE_EDITED", editText_NavigationIDOfTask.getText().toString()) ;
            }
        });

        return view ;
    }

    public interface InterFaceNavigationFragmentListener {
        void onNavigationItemSelected(String info, String info2);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof InterFaceNavigationFragmentListener) {
            listener = (InterFaceNavigationFragmentListener) context;
        } else {
            throw new ClassCastException(context.toString() +
                    "-did not implement interface OnItemSelectedListener");
        }
    }

    public void OnNavigationItemClicked(String info){
        listener.onNavigationItemSelected(info, null);
    }

    public void OnNavigationItemClicked(String info, String info2){
        listener.onNavigationItemSelected(info,info2);
    }

    void OnBtnLogin_Click(View aView){}
    void btnAddTask_OnClick(View aView) {}

    public void GetUserName(String userID) {

    }

    public void newTask() {}
    public void DisplayAllTasks() {}
    public void DisplayCompletedTasks() {}
    public void DisplayIncompleteTasks() {}
    public void DisplayTaskDueOnDate() {}
    public void SearchTasks() {}
    public void EditTasks() {}

}
