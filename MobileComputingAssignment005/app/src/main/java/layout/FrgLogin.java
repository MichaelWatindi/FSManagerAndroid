package layout;

import android.content.Context;
import android.database.SQLException;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.uab.tindi.mobilecomputingassignment005.R;
import edu.uab.tindi.mobilecomputingassignment005.User;
import edu.uab.tindi.mobilecomputingassignment005.UsersDAO;

public class FrgLogin extends Fragment {

    EditText        edtTextUserEmail ;
    EditText        editTextPassword ;
    TextView        textViewPasswordHint ;
    ArrayList<User> allUsers ;
    UsersDAO        theUsersDA0 ;

    private FrgLoginListener theListener ;
    public FrgLogin(){


    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup      container,
                             Bundle         savedInstanceState) {
        View theView = inflater.inflate(R.layout.fragment_frg_login, container, false) ;

        Button btnLogin = (Button) theView.findViewById(R.id.btn_Login)  ;
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // theListener.OnLoginListener() ;
                OnBtnLogin_Clicked();
            }
        });

        Button btnCreateContent = (Button) theView.findViewById(R.id.btn_CreateContent) ;
        btnCreateContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                theListener.OnCreateContent();
            }
        });

        edtTextUserEmail = (EditText) theView.findViewById(R.id.edtTxt_UserEmail) ;
        edtTextUserEmail.setText("mark") ;

        editTextPassword = (EditText) theView.findViewById(R.id.editText_Password) ;
        editTextPassword.setText("123");

        TextView txtViewPasswordHint = (TextView) theView.findViewById(R.id.txtView_HintPassword) ;
        txtViewPasswordHint.setText(" mark, password 123 \n john, password 123 \n paul password 123");

        // Inflate the layout for this fragment

        return theView ;
    }

    public interface FrgLoginListener{
        void OnLoginListener() ;
        void OnCreateContent() ;
        void OnLogin(String theEmail, String thePassword) ;
    }

    private void OnBtnLogin_Clicked(){
        // Check Login Credentials
        try {
            theUsersDA0 = new UsersDAO(getActivity()) ;
            theUsersDA0.Open() ;

            String aLoginEmail      = edtTextUserEmail.getText().toString().trim() ;
            String aLoginPassword   = editTextPassword.getText().toString().trim() ;

            User aLoginUser = theUsersDA0.GetByEmail(aLoginEmail) ;

            if (aLoginUser == null){
                return ;
            }
            if(aLoginUser.Password().trim().equals(aLoginPassword)) {
                theListener.OnLoginListener();
            }
            else {
                Toast.makeText(getActivity(),
                                "LOGIN FAILED, \n WRONG USERNAME OR PASSWORD",
                                Toast.LENGTH_SHORT).show();
            }

            theUsersDA0.Close() ;
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onAttach(Context theContext) {
        super.onAttach(theContext) ;
        if(theContext instanceof  FrgLoginListener){
            theListener =(FrgLoginListener) theContext ;
        } else {
            throw new ClassCastException(theContext.toString() + " NO INTERFACE") ;
        }
    }

}
