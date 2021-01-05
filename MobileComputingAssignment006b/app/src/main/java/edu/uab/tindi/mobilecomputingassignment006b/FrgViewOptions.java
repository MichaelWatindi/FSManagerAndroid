package edu.uab.tindi.mobilecomputingassignment006b;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FrgViewOptions extends Fragment {
    private FrgViewOptionsListener theListener ;

    public FrgViewOptions() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the dialog_book_filter for this fragment
        View theView = inflater.inflate(R.layout.fragment_frg_view_options, container, false) ;
        Button btnViewAll       = (Button) theView.findViewById(R.id.Button_ViewAllBooks) ;
        Button btnViewByTitle   = (Button) theView.findViewById(R.id.Button_ViewBooksByTitle) ;
        Button btnViewByAuthor  = (Button) theView.findViewById(R.id.Button_ViewBooksByAuthor) ;
        Button btnViewByYear    = (Button) theView.findViewById(R.id.Button_ViewBooksByYear) ;

        btnViewAll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                theListener.OnOptionsButtonPressed("VIEW_ALL", "");
            }
        });

        btnViewByTitle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //  Show Dialog
                final Dialog dlgBookDetails = new Dialog(getActivity()) ;
                dlgBookDetails.setContentView(R.layout.dialog_book_filter) ;
                dlgBookDetails.setTitle("Book") ;

                TextView textView_filterPrompt = (TextView) dlgBookDetails.findViewById(R.id.textView_dlgBookFilterPrompt) ;
                textView_filterPrompt.setText("Enter Title") ;

                final EditText editText_filterInfo = (EditText) dlgBookDetails.findViewById(R.id.editText_dlgBookFilterInfo) ;

                Button button_bookFilterOk = (Button) dlgBookDetails.findViewById(R.id.button_dlgBookFilterOk) ;
                button_bookFilterOk.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        //  Get text from info textfield.
                        String theInfo = editText_filterInfo.getText().toString();
                        theListener.OnOptionsButtonPressed("TITLE", theInfo) ;
                        //  Send this information down to activity and have it display lists
                    }
                });
                Button button_bookFilterCancel = (Button) dlgBookDetails.findViewById(R.id.button_dlgBookFilterCancel) ;
                button_bookFilterCancel.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        //  Get text from info textfield.
                        //  Send this information down to activity and have it display lists
                        dlgBookDetails.dismiss();
                    }
                });
                dlgBookDetails.show();
            }
        });

        btnViewByAuthor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //  Show Dialog
                final Dialog dlgBookDetails = new Dialog(getActivity()) ;
                dlgBookDetails.setContentView(R.layout.dialog_book_filter) ;
                dlgBookDetails.setTitle("Book") ;

                TextView textView_filterPrompt = (TextView) dlgBookDetails.findViewById(R.id.textView_dlgBookFilterPrompt) ;
                textView_filterPrompt.setText("Enter Author") ;

                final EditText editText_filterInfo = (EditText) dlgBookDetails.findViewById(R.id.editText_dlgBookFilterInfo) ;

                Button button_bookFilterOk = (Button) dlgBookDetails.findViewById(R.id.button_dlgBookFilterOk) ;
                button_bookFilterOk.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        //  Get text from info textfield.
                        String theInfo = editText_filterInfo.getText().toString();
                        theListener.OnOptionsButtonPressed("AUTHOR", theInfo) ;
                        //  Send this information down to activity and have it display lists
                    }
                });
                Button button_bookFilterCancel = (Button) dlgBookDetails.findViewById(R.id.button_dlgBookFilterCancel) ;
                button_bookFilterCancel.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        //  Get text from info textfield.
                        //  Send this information down to activity and have it display lists
                        dlgBookDetails.dismiss();
                    }
                });
                dlgBookDetails.show();
            }
        });

        btnViewByYear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //  Show Dialog
                final Dialog dlgBookDetails = new Dialog(getActivity()) ;
                dlgBookDetails.setContentView(R.layout.dialog_book_filter) ;
                dlgBookDetails.setTitle("Book") ;

                TextView textView_filterPrompt = (TextView) dlgBookDetails.findViewById(R.id.textView_dlgBookFilterPrompt) ;
                textView_filterPrompt.setText("Enter Year") ;

                final EditText editText_filterInfo = (EditText) dlgBookDetails.findViewById(R.id.editText_dlgBookFilterInfo) ;
                editText_filterInfo.setInputType(InputType.TYPE_CLASS_NUMBER);

                Button button_bookFilterOk = (Button) dlgBookDetails.findViewById(R.id.button_dlgBookFilterOk) ;
                button_bookFilterOk.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        //  Get text from info textfield.
                        String theInfo = editText_filterInfo.getText().toString();
                        theListener.OnOptionsButtonPressed("YEAR", theInfo) ;
                        //  Send this information down to activity and have it display lists
                    }
                });
                Button button_bookFilterCancel = (Button) dlgBookDetails.findViewById(R.id.button_dlgBookFilterCancel) ;
                button_bookFilterCancel.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        //  Get text from info textfield.
                        //  Send this information down to activity and have it display lists
                        dlgBookDetails.dismiss();
                    }
                });
                dlgBookDetails.show();
            }
        });
        return theView ;
    }

    public interface FrgViewOptionsListener {
        void OnOptionsButtonPressed(String theCommand, String otherInfo) ;
    }

    public void onAttach(Context theContext) {
        super.onAttach(theContext);
        if (theContext instanceof FrgViewOptionsListener) {
            theListener = (FrgViewOptionsListener) theContext ;
        } else {
            throw new ClassCastException(theContext.toString() + " NO INTERFACE") ;
        }
    }

}
