package edu.uab.tindi.mobilecomputingassignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public final static String TXT = "edu.uab.tindi.mobileComputing.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnBtnSendTextMessage_click(View view){
        Intent intentSendTxt = new Intent( this, SendTextActivity.class ) ;

        EditText edtTxtMessage = (EditText) findViewById(R.id.editTxt_TxtMsg) ;
        String message = edtTxtMessage.getText().toString() ;
        intentSendTxt.putExtra(TXT, message);
        startActivity(intentSendTxt) ;
    }

    public void OnBtnSendEmailMessage_click(View view) {
        Intent intentSendEmail = new Intent( this, SendEmailActivity.class ) ;

        EditText edtTxtEmail = (EditText) findViewById(R.id.editTxt_emailMsgInput) ;
        String message = edtTxtEmail.getText().toString() ;
        intentSendEmail.putExtra(TXT, message);
        startActivity(intentSendEmail) ;

    }
}
