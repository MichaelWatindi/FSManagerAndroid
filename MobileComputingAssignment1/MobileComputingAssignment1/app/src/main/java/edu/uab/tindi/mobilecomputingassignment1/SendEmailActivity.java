package edu.uab.tindi.mobilecomputingassignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class SendEmailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState) ;
        setContentView(R.layout.activity_send_email) ;

        Intent intentSendEmail = getIntent() ;
        String message = intentSendEmail.getStringExtra(MainActivity.TXT) ;

        EditText editTextMessage = (EditText) findViewById(R.id.editTxt_emailMessage) ;
        editTextMessage.setText(message) ;

    }
}
