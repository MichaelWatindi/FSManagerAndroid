package edu.uab.tindi.mobilecomputingassignment003;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CourseActivity extends AppCompatActivity {

    private Button returnButton ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        returnButton = (Button) findViewById(R.id.btnCourseActBack);
        returnButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { finish();  }
        });

        Intent intent = getIntent() ;

        TextView txtName = (TextView) findViewById(R.id.CourseName) ;
        txtName.setText(intent.getStringExtra("NAME"));

        TextView txtInst = (TextView) findViewById(R.id.CourseInstructor) ;
        txtInst.setText(intent.getStringExtra("INSTRUCTOR"));

        TextView txtText = (TextView) findViewById(R.id.CourseTextBook) ;
        txtText.setText(intent.getStringExtra("TEXTBOOK"));

        TextView txtRoom = (TextView) findViewById(R.id.CourseRoom) ;
        txtRoom.setText(intent.getStringExtra("ROOM"));

        TextView txtDesc = (TextView) findViewById(R.id.CourseDescription) ;
        txtDesc.setText(intent.getStringExtra("DESCRIPTION"));
    }

}
