package edu.uab.tindi.mobilecomputingassignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class SendTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState) ;
        setContentView(R.layout.activity_send_text_) ;

        Intent intentSendText = getIntent() ;
        String message = intentSendText.getStringExtra(MainActivity.TXT) ;

        EditText editTextMessage = (EditText) findViewById(R.id.editTxt_MsgToSend) ;
        editTextMessage.setText(message) ;
    }
}
