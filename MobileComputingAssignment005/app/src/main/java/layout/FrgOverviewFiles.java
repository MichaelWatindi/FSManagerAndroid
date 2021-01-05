package layout;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

import edu.uab.tindi.mobilecomputingassignment005.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FrgOverviewFiles extends Fragment {

    ListView            listViewFilesList ;
    ArrayList<String>   listOfFileNames = new ArrayList<>() ;
    private FilesOverviewListener theListener ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // GetListOfFiles()
        listOfFileNames = GetListOfFiles() ;
        View theView = inflater.inflate(R.layout.fragment_frg_overview_files, container, false) ;

        listViewFilesList = (ListView) theView.findViewById(R.id.listView_OverviewFilesListView) ;
        ArrayAdapter<String> arrayAdapterFilesList
                = new ArrayAdapter<String>( getActivity(),
                                            android.R.layout.simple_list_item_1,
                                            listOfFileNames) ;
        listViewFilesList.setAdapter(arrayAdapterFilesList) ;

        listViewFilesList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final String strNameOfFile = (String) listViewFilesList.getItemAtPosition(position);
                AlertDialog alertDialogConfirm = new AlertDialog.Builder(getActivity()).create();
                alertDialogConfirm.setTitle(strNameOfFile) ;
                alertDialogConfirm.setMessage("Are you sure you want to open this file?");
                alertDialogConfirm.setButton(AlertDialog.BUTTON_NEUTRAL, "CANCEL", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                        dialog.dismiss();
                    }
                });

                alertDialogConfirm.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                        theListener.OnFileAction("OPEN", strNameOfFile, "") ;
                    }
                });
                alertDialogConfirm.show();
            }
        });

        registerForContextMenu(listViewFilesList) ;
        return theView;
    }

    public ArrayList<String> GetListOfFiles(){
        //  File f = new File() ;
        ArrayList<String> strArrayListOfFileNames = new  ArrayList<String>() ;
        try {
            String thePath = getActivity().getFilesDir().getAbsolutePath();
            File directory = new File(thePath) ;
            File[] files = directory.listFiles() ;
            for (int i = 0; i < files.length; i++){
                strArrayListOfFileNames.add(files[i].getName()) ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return strArrayListOfFileNames ;
    }

    public interface FilesOverviewListener{
        void OnFileAction(String theAction, String fileName, String otherInformation ) ;
    }

    @Override
    public void onAttach(Context theContext) {
        super.onAttach(theContext) ;
        if(theContext instanceof  FilesOverviewListener){
            theListener =(FilesOverviewListener) theContext ;
        } else {
            throw new ClassCastException(theContext.toString() + " NO INTERFACE") ;
        }
    }

    @Override
    public void onCreateContextMenu (ContextMenu theMenu,
                                     View theView,
                                     ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(theMenu, theView, menuInfo) ;
        if(theView.getId() == R.id.listView_OverviewFilesListView) {
            theMenu.setHeaderTitle("Options") ;
            theMenu.setHeaderIcon(android.R.drawable.ic_delete) ;
            theMenu.add("Open") ;
            theMenu.add("Delete") ;
            theMenu.add("Rename") ;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info
                = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        int listPosition = info.position ;
        String fileName = (String) listViewFilesList.getItemAtPosition(listPosition) ;
        theListener.OnFileAction((String) item.getTitle(), fileName, "") ;
        return true ;
    }
}
