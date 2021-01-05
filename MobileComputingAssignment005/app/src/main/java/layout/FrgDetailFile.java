package layout;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import edu.uab.tindi.mobilecomputingassignment005.R;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class FrgDetailFile extends Fragment {

    TextView textViewFileName ;
    EditText editTextFileContents ;
    String   theFileName ;
    View     glView ;

    private ClipboardManager myClipboard;
    private ClipData myClip;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View theView = inflater.inflate(R.layout.fragment_frg_detail_file, container, false);
        glView = theView ;
        File theFile = GetFile() ;
        String fileName     = theFile.getName() ;
        String fileContents = GetFileString(fileName);

        textViewFileName = (TextView) theView.findViewById(R.id.TextView_FileName) ;
        textViewFileName.setText(fileName) ;

        editTextFileContents = (EditText) theView.findViewById(R.id.editText_FileContents) ;
        editTextFileContents.setText(fileContents) ;
        registerForContextMenu(editTextFileContents) ;
        return theView;
    }

    public File GetFile(){
        return  new File(GetFileName()) ;
    }

    private String GetFileName(){
        Bundle arguments = getArguments() ;
        String fileName = "" ;
        if(arguments != null) {
            fileName = arguments.getString("INFO") ;
        }
        theFileName = fileName ;
        return fileName ;
    }

    private String GetFileString(String fileName) {
        String thePath = getActivity().getFilesDir().getAbsolutePath() ;
        File theFile = new File(thePath + "/" + fileName) ;
        String result = "" ;
        if( theFile.exists() ) {
            FileInputStream fis = null ;

            try {
                fis = new FileInputStream(theFile) ;
                char current ;
                while(fis.available() > 0){
                    current = (char) fis.read() ;
                    result = result + String.valueOf(current) ;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch(Exception e){
                e.printStackTrace();
            }

        }
        return result ;
    }


    @Override
    public void onCreateContextMenu(ContextMenu                 theMenu,
                                    View                        theView,
                                    ContextMenu.ContextMenuInfo menuInfo){
        if(theView.getId() == R.id.editText_FileContents) {
            theMenu.add("Copy") ;
            theMenu.add("Cut") ;
            theMenu.add("Paste") ;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info
                = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        String inputText = GetInputText() ;
        String selectedText = GetSelectedText() ;
        final String s = item.getTitle().toString();
        switch (s) {
            case "Copy": //  Copy
            {
                ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("", selectedText);
                clipboard.setPrimaryClip(clip);
            }
                break;
            case "Cut": //  Cut
            {
                ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("", selectedText);
                clipboard.setPrimaryClip(clip);
                inputText = inputText.replace(selectedText, "") ;
                EditText edtTxt = (EditText)glView.findViewById(R.id.editText_FileContents);
                edtTxt.setText(inputText) ;
            }
                break;
            case "Paste": //  Paste
            {
                final ClipboardManager clipBoard = (ClipboardManager) getActivity().getSystemService(CLIPBOARD_SERVICE);
                String temp = "" ;
                temp = clipBoard.toString() ;
                EditText edtTxt = (EditText)glView.findViewById(R.id.editText_FileContents);
                edtTxt.setText(temp) ;
            }
                break;
            default:
                return super.onContextItemSelected(item);
        }       

        return true ;
    }

    private String GetInputText() {
        EditText inputText = (EditText)glView.findViewById(R.id.editText_FileContents);
        String inputString = inputText.getText().toString();
        return inputString ;
    }

    private String GetSelectedText() {
        EditText editTxt = (EditText) glView.findViewById(R.id.editText_FileContents) ;

        int startSelection = editTxt.getSelectionStart() ;
        int endSelection = editTxt.getSelectionEnd() ;
        return editTxt.getText().toString().substring(startSelection, endSelection) ;
    }

    public void CloseFile(){
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

    public void SaveFile() {
        String fileContents = GetInputText() ;
        String thePath = getActivity().getFilesDir().getAbsolutePath() ;
        String fileName = GetFileName() ;
        try {
            FileWriter fw = new FileWriter(new File(thePath + "/" + fileName)) ;
            BufferedWriter out = new BufferedWriter(fw) ;
            out.write(fileContents);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
