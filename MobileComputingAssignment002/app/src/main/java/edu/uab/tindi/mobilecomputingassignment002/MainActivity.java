package edu.uab.tindi.mobilecomputingassignment002;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView courseListView ;
    ArrayList<String> courseList = new ArrayList<>() ;
    ArrayList<AcademicCourse> acAcademicCourses = new ArrayList<>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CreateCourses() ;
        courseListView = (ListView) findViewById(R.id.CourseListView) ;

        CourseAdapter acCourseAdapter =
                new CourseAdapter(this, android.R.layout.simple_list_item_1, acAcademicCourses) ;

        courseListView.setAdapter(acCourseAdapter) ;

        courseListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AcademicCourse aCourse = (AcademicCourse) courseListView.getItemAtPosition(position) ;
                String str = aCourse.Name();
                Toast.makeText(getBaseContext(), str, Toast.LENGTH_SHORT).show();

                Intent intentDisplayCourseDetails = new Intent(getApplicationContext(), CourseActivity.class) ;
                intentDisplayCourseDetails.putExtra("NAME", aCourse.Name()) ;
                intentDisplayCourseDetails.putExtra("INSTRUCTOR", aCourse.Instructor()) ;
                intentDisplayCourseDetails.putExtra("TEXTBOOK", aCourse.TextBook()) ;
                intentDisplayCourseDetails.putExtra("ROOM", aCourse.Room()) ;
                intentDisplayCourseDetails.putExtra("DESCRIPTION", aCourse.Dexcription()) ;

                startActivity(intentDisplayCourseDetails) ;
            }
        }) ;
    }

    private void CreateCourses() {
        AcademicCourse acCourse001 =
                new AcademicCourse("EE 637 - Design of Modern Computers with Digital Integrated Circuits", "Dr Karthik Linga" ) ;

        acCourse001.Description("This course will be focused on teaching the basic design flow of digital computing chips. " +
                "The students will be exposed to all levels of the chip design flow. The course will involve design projects.") ;
        // acCourse001.Instructor("") ;
        acCourse001.TextBook("CMOS VLSI Design: A Circuits and Systems Perspective, Neil Weste") ;
        acCourse001.Room("BEC 267") ;

        acAcademicCourses.add(acCourse001) ;

        AcademicCourse acCourse002 =
                new AcademicCourse("EE 616 - CMOS Analog Integrated Circuits", "Dr. Mohammad Rafiqul Haider" ) ;

        acCourse002.Description("This course will cover basic building blocks of CMOS analog VLSI design, MOSFET theory, " +
                "short channel device and nonlinear effects, current mirrors, current-reference generator, " +
                "operational transconductance amplifier, switched capacitor architecture, analog-to-digital " +
                "converter and digital-to-analog converter.") ;
        // acCourse002.Instructor("") ;
        acCourse002.TextBook("CMOS Circuit Design Layout and Simulation by R. J. Baker") ;
        acCourse002.Room("BEC 154") ;

        acAcademicCourses.add(acCourse002) ;

        AcademicCourse acCourse003 =
                new AcademicCourse("EE 654 - Mobile Computing", "Dr Leon Jololian" ) ;

        acCourse003.Description("This course introduces fundamental and advanced concepts in mobile computing.  " +
                "The course aims at the development of mobile applications, with a focus on the user interface, " +
                "application logic, and seamless access to backend services. ") ;
        // acCourse003.Instructor("") ;
        acCourse003.TextBook("Android Programming: The Big Nerd Ranch Guide") ;
        acCourse003.Room("BEC 154") ;

        acAcademicCourses.add(acCourse003) ;
    }
}
