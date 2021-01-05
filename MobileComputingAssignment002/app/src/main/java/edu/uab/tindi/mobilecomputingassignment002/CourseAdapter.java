package edu.uab.tindi.mobilecomputingassignment002;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mike on 9/28/2017.
 */

public class CourseAdapter extends ArrayAdapter<AcademicCourse> {
    ArrayList<AcademicCourse> academicCourseList = new ArrayList<>() ;

    public CourseAdapter(Context context, int textViewResourceId, ArrayList<AcademicCourse> objects) {
        super(context, textViewResourceId, objects) ;

        academicCourseList = objects ;
    }   // End Course Adapter

    @Override
    public  int getCount() {
        return super.getCount();
    }   // End int getCount

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        AcademicCourse course = getItem(position) ;

        if (v == null)
            v = LayoutInflater.from(getContext()).inflate(R.layout.course_list_view_items, parent, false) ;

        TextView textView = (TextView) v.findViewById(R.id.listViewTextView) ;
        textView.setText(academicCourseList.get(position).Name());
        return v ;
    }

}   // End of class CourseAdapter
