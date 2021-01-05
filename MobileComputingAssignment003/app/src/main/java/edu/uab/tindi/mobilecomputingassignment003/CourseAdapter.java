package edu.uab.tindi.mobilecomputingassignment003;

/**
 * Created by Mike on 10/12/2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CourseAdapter extends ArrayAdapter<AcademicCourse> {
    static class ViewHolder {
        public TextView text ;
    }

    ArrayList<AcademicCourse> academicCourseList = new ArrayList<>() ;

    public CourseAdapter(Context                    context,
                         int                        textViewResourceId,
                         ArrayList<AcademicCourse>  objects) {
        super(context, textViewResourceId, objects) ;

        academicCourseList = objects ;
    }   // End Course Adapter

    @Override
    public  int getCount() {

        return super.getCount();
    }   // End int getCount

    @Override
    public View getView(int         position,
                        View        convertView,
                        ViewGroup   parent) {

        View v = convertView ;
        AcademicCourse course = getItem(position) ;

        if (v == null) {
            LayoutInflater anInflator = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
            v = anInflator.inflate(R.layout.course_list_view_items, null) ;
            ViewHolder aViewHolder = new ViewHolder() ;
            aViewHolder.text = (TextView)v.findViewById(R.id.listViewTextView) ;
            v.setTag(aViewHolder) ;
        }

        ViewHolder holder = (ViewHolder) v.getTag() ;
        AcademicCourse s = academicCourseList.get(position) ;
        holder.text.setText(s.Name()) ;
        TextView textView = (TextView) v.findViewById(R.id.listViewTextView) ;
        textView.setText(academicCourseList.get(position).Name());
        return v ;
    }

}   // End of class CourseAdapter

