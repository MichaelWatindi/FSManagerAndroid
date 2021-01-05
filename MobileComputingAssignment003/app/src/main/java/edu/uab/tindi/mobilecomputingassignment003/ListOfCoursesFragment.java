package edu.uab.tindi.mobilecomputingassignment003;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class ListOfCoursesFragment extends Fragment {

    ListView lvListOfCourses ;
    HashMap<String, String> Data = new HashMap<String, String>() ;
    private OnItemSelectedListener listener ;
    ArrayList<AcademicCourse> acAcademicCourses = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup      container,
                             Bundle         savedInstanceState) {

        View aView = inflater.inflate(R.layout.fragment_list, container, false) ;
        lvListOfCourses = (ListView) aView.findViewById(R.id.CourseListView) ;
        CreateCourses() ;

        CourseAdapter aCourseAdapter =
                new CourseAdapter(getActivity(), R.layout.course_list_view_items, acAcademicCourses) ;
        lvListOfCourses.setAdapter(aCourseAdapter) ;

        lvListOfCourses.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View aView, int position, long id){

                try{
                    AcademicCourse anAcademicCourse = (AcademicCourse)  lvListOfCourses.getItemAtPosition(position) ;
                    listener.onCourseSelected(anAcademicCourse) ;
                }catch(Exception e) {
                    String strError = e.getMessage() ;
                    int i = 0;
                    i = i + 1 ;
                }
                // listener.onD
            }
        });
        // Inflate the layout for this fragment
        return aView;
    }

    @Override
    public void onAttach(Context aContext) {
        super.onAttach(aContext) ;
        if(aContext instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) aContext ;
        } else {
            throw new ClassCastException(aContext.toString() + "Interface OnItemSelectedListener not Implemented") ;
        }
    }

    private void CreateCourses() {
        acAcademicCourses.clear();
        AcademicCourse acCourse001 =
                new AcademicCourse("EE 637 - Design of Modern Computers with Digital Integrated Circuits", "Dr Karthik Linga");

        acCourse001.Description("This course will be focused on teaching the basic design flow of digital computing chips. " +
                "The students will be exposed to all levels of the chip design flow. The course will involve design projects.");
        // acCourse001.Instructor("") ;
        acCourse001.TextBook("CMOS VLSI Design: A Circuits and Systems Perspective, Neil Weste");
        acCourse001.Room("BEC 267");

        acAcademicCourses.add(acCourse001);

        AcademicCourse acCourse002 =
                new AcademicCourse("EE 616 - CMOS Analog Integrated Circuits", "Dr. Mohammad Rafiqul Haider");

        acCourse002.Description("This course will cover basic building blocks of CMOS analog VLSI design, MOSFET theory, " +
                "short channel device and nonlinear effects, current mirrors, current-reference generator, " +
                "operational transconductance amplifier, switched capacitor architecture, analog-to-digital " +
                "converter and digital-to-analog converter.");
        // acCourse002.Instructor("") ;
        acCourse002.TextBook("CMOS Circuit Design Layout and Simulation by R. J. Baker");
        acCourse002.Room("BEC 154");

        acAcademicCourses.add(acCourse002);

        AcademicCourse acCourse003 =
                new AcademicCourse("EE 654 - Mobile Computing", "Dr Leon Jololian");

        acCourse003.Description("This course introduces fundamental and advanced concepts in mobile computing.  " +
                "The course aims at the development of mobile applications, with a focus on the user interface, " +
                "application logic, and seamless access to backend services. ");
        // acCourse003.Instructor("") ;
        acCourse003.TextBook("Android Programming: The Big Nerd Ranch Guide");
        acCourse003.Room("BEC 154");

        acAcademicCourses.add(acCourse003);
    }
}
