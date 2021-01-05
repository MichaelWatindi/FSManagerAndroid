package edu.uab.tindi.mobilecomputingassignment003;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;




public class DetailsFragment extends Fragment {
    public static final String EXTRA ="extra";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState) {
        super.onActivityCreated(saveInstanceState) ;

        Bundle aBundle = getArguments() ;
        if (aBundle != null) {
            String name = aBundle.getString("") ;
            String instructor = aBundle.getString("") ;
            String textBook = aBundle.getString("") ;
            String room = aBundle.getString("") ;
            String theDescription = aBundle.getString("") ;

            AcademicCourse theCourse =
                    new AcademicCourse(aBundle.getString("NAME"), aBundle.getString("INSTRUCTOR")) ;
            theCourse.Room( aBundle.getString("ROOM")) ;
            theCourse.TextBook( aBundle.getString("TEXTBOOK")) ;
            theCourse.Description( aBundle.getString("DESCRIPTION")); ;
            SetAcademicCourseDetails(theCourse);
        }
    }

    public void SetAcademicCourseDetails(AcademicCourse anAcademicCourse){
        TextView nameField = (TextView)getView().findViewById(R.id.CourseName) ;
        nameField.setText(anAcademicCourse.Name()) ;

        TextView instructorField = (TextView)getView().findViewById(R.id.CourseInstructor) ;
        instructorField.setText(anAcademicCourse.Instructor()) ;

        TextView TextbookField = (TextView)getView().findViewById(R.id.CourseTextBook) ;
        TextbookField.setText(anAcademicCourse.TextBook()) ;

        TextView roomField = (TextView)getView().findViewById(R.id.CourseRoom) ;
        roomField.setText(anAcademicCourse.Room()) ;

        TextView descriptionField = (TextView)getView().findViewById(R.id.CourseDescription) ;
        descriptionField.setText(anAcademicCourse.Description()) ;
    }
}
