package edu.uab.tindi.mobilecomputingassignment005;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

import layout.FrgDetailFile;
import layout.FrgLogin;
import layout.FrgOverviewFiles ;

enum App_CurrentView{
    LOGIN,
    OVERVIEW,
    DETAILED
}

public class ACTMainActivity
        extends AppCompatActivity
        implements  FrgLogin.FrgLoginListener,
                    FrgOverviewFiles.FilesOverviewListener
{
    UsersDAO        theUserDAO ;
    App_CurrentView appCurrentView ;
    int             theUserID ;
    boolean         isSampleContentCreated ;
    String          strFileToBeOpened ;

    public ACTMainActivity(){
        isSampleContentCreated = false ;
        appCurrentView = App_CurrentView.LOGIN ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actmain) ;

        theUserDAO = new UsersDAO(this) ;
        theUserDAO.Open();
        theUserID = 0 ;
        strFileToBeOpened = "" ;

        if(isSampleContentCreated == false){
            CreateSampleContent() ; // And this time make sure that all is working as expected.
            isSampleContentCreated = true ;
        }
        SetFrames(savedInstanceState) ;
        theUserDAO.Close();
    }

    @Override protected  void onResume(){
        theUserDAO.Open();
        super.onResume();
    }

    @Override protected void onPause(){
        theUserDAO.Close();
        super.onPause();

    }

    private void CreateSampleContent() {
        CreateSampleUsers() ;
        CreateSampleFiles() ;
    }

    private void CreateSampleUsers() {
        User userMark = new User() ;
        userMark.Name("Mark") ;
        userMark.Email("mark") ;
        userMark.Password("123") ;

        User userJohn = new User() ;
        userJohn.Name("John") ;
        userJohn.Email("john") ;
        userJohn.Password("123") ;

        User userPaul = new User() ;
        userPaul.Name("Paul") ;
        userPaul.Email("paul") ;
        userPaul.Password("123") ;

        theUserDAO.DeleteAllUsers() ;
        theUserDAO.Add(userMark) ;
        theUserDAO.Add(userJohn) ;
        theUserDAO.Add(userPaul) ;
    }

    private void CreateSampleFiles() {
        try {
            File sample001 = new File(this.getFilesDir(), "Sample001") ;
            WriteToFile(sample001, "Sample001 \nThis is the winter of our discontent. ") ;
            File sample002 = new File(this.getFilesDir(), "Sample002") ;
            WriteToFile(sample002, "sample002 \nThis is the winter of our discontent. ") ;
            File sample003 = new File(this.getFilesDir(), "Sample003") ;
            WriteToFile(sample003, "sample003 \nThis is the winter of our discontent. ") ;
            File sample004 = new File(this.getFilesDir(), "Sample004") ;
            WriteToFile(sample004, "sample004 \nThis is the winter of our discontent. ") ;
            File sample005 = new File(this.getFilesDir(), "Sample004") ;
            WriteToFile(sample005, "sample005 \nThis is the winter of our discontent. ") ;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void WriteToFile(File targetFile, String Content) {
        FileOutputStream foStream ;
        try {
            foStream = openFileOutput(targetFile.getName(), Context.MODE_PRIVATE) ;
            foStream.write(Content.getBytes()) ;
            foStream.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void OnLoginListener(){
        appCurrentView = App_CurrentView.OVERVIEW ;
        Bundle savedInstance = getIntent().getExtras();
        getSupportActionBar().hide();
        this.SetFrames(savedInstance) ;
    }

    public void OnCreateContent(){
        CreateSampleContent();
        this.Refresh();
    }

    public void Refresh(){
        finish();
        startActivity(getIntent());
    }

    public void OnLogin(String theEmail, String thePassword){
        return  ;
    }

    private void SetFrames(Bundle savedInstanceState){
        if (appCurrentView == App_CurrentView.LOGIN) {
            if (getResources().getBoolean(R.bool.twoPaneMode)) {
                // FrameLayout aFrame = (FrameLayout) findViewById(R.id.Frame_LandscapeOverview) ;
                // aFrame.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)) ;
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.Frame_LandscapeOverview, new FrgLogin());
                ft.commit();
            } else {
                if (savedInstanceState != null) {
                    getSupportFragmentManager().executePendingTransactions();
                    Fragment aFragment =
                            getSupportFragmentManager().findFragmentById(R.id.frame_PortraitContent) ;
                    if (aFragment != null)
                        getSupportFragmentManager().beginTransaction().remove(aFragment).commit() ;
                }
                FrgLogin loginFragment = new FrgLogin() ;
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_PortraitContent, loginFragment).commit();
            }
        }
        if(appCurrentView == App_CurrentView.OVERVIEW){
            if (getResources().getBoolean(R.bool.twoPaneMode)) {
                // FrameLayout aFrame = (FrameLayout) findViewById(R.id.Frame_LandscapeOverview) ;
                // aFrame.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)) ;
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.Frame_LandscapeOverview, new FrgOverviewFiles()) ;
                ft.addToBackStack(null) ;
                // ft.add(R.id.Frame_LandscapeOverview, new FrgOverviewFiles());
                ft.commit();
            } else {
                if (savedInstanceState != null) {
                    getSupportFragmentManager().executePendingTransactions();
                    Fragment aFragment =
                            getSupportFragmentManager().findFragmentById(R.id.frame_PortraitContent) ;
                    if (aFragment != null)
                        getSupportFragmentManager().beginTransaction().remove(aFragment).commit() ;
                }

                try {
                    FrgOverviewFiles frgOF = new FrgOverviewFiles();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_PortraitContent, frgOF).addToBackStack("").commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else if(appCurrentView == App_CurrentView.DETAILED){
            FrgDetailFile frgDetailFileView = new FrgDetailFile();
            Bundle args = new Bundle() ;
            args.putString("INFO", strFileToBeOpened) ;
            frgDetailFileView.setArguments(args) ;
            if (getResources().getBoolean(R.bool.twoPaneMode)) {
                // FrameLayout aFrame = (FrameLayout) findViewById(R.id.Frame_LandscapeOverview) ;
                // aFrame.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)) ;
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.Frame_LandscapeOverview, frgDetailFileView) ;
                ft.addToBackStack(null) ;
                // ft.add(R.id.Frame_LandscapeOverview, frgDetailFileView);
                ft.commit();
            } else {
                if (savedInstanceState != null) {
                    getSupportFragmentManager().executePendingTransactions();
                    Fragment aFragment =
                            getSupportFragmentManager().findFragmentById(R.id.frame_PortraitContent) ;
                    if (aFragment != null)
                        getSupportFragmentManager().beginTransaction().remove(aFragment).commit() ;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.frame_PortraitContent,
                        frgDetailFileView).addToBackStack("").commit();
            }
        }
    }

    public void OnFileAction(String theAction,
                             String fileName,
                             String otherInformation )
    {
        Bundle savedInstance = getIntent().getExtras() ;
        if(theAction.toLowerCase().equals("open") ){
            appCurrentView = App_CurrentView.DETAILED ;
            strFileToBeOpened = fileName ;
            getSupportActionBar().show();
            this.SetFrames(savedInstance) ;
        }
        if(theAction.toLowerCase().equals("rename") ){
            String thePath = getFilesDir().getAbsolutePath() ;
            // Get file name
            File theFile = new File(thePath + "/" + fileName) ;
            File theNewFile = new File(thePath + "/" + otherInformation) ;
            if (theFile.renameTo(theNewFile)) {
                Toast.makeText(getApplicationContext(), "File " + fileName + " renamed successfully.", Toast.LENGTH_SHORT).show();

            }
            else {
                Toast.makeText(getApplicationContext(), "File " + fileName + " could not be renamed", Toast.LENGTH_SHORT).show();
            }
            appCurrentView = App_CurrentView.OVERVIEW ;
            getSupportActionBar().hide();
            this.SetFrames(savedInstance) ;
        }

        if(theAction.toLowerCase().equals("delete")){
            String thePath = getFilesDir().getAbsolutePath() ;
            File theFile = new File(thePath + "/" + fileName) ;
            if (theFile.delete()) {
                Toast.makeText(getApplicationContext(), "File " + fileName + " deleted successfully.", Toast.LENGTH_SHORT).show();

            }
            else {
                Toast.makeText(getApplicationContext(), "File " + fileName + " could not be deleted", Toast.LENGTH_SHORT).show();
            }
            appCurrentView = App_CurrentView.OVERVIEW ;
            getSupportActionBar().hide();
            this.SetFrames(savedInstance) ;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu theMenu){
        //if(appCurrentView == App_CurrentView.DETAILED){
        getMenuInflater().inflate(R.menu.main_menu, theMenu);
        getSupportActionBar().hide();
        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId() ;
        switch(id){
            case R.id.menuItem_closeFile:
                Toast.makeText(getApplicationContext(), "File Closed", Toast.LENGTH_SHORT).show();
                if(appCurrentView == App_CurrentView.DETAILED) {
                    appCurrentView = App_CurrentView.OVERVIEW ;
                    Bundle savedInstance = getIntent().getExtras();
                    SetFrames(savedInstance);
                }
                break;
            case R.id.menuItem_saveExit:
                Toast.makeText(getApplicationContext(), "File Saved", Toast.LENGTH_SHORT).show();
                FrgDetailFile theDetailFragment = (FrgDetailFile) getSupportFragmentManager().findFragmentById(R.id.frame_PortraitContent) ;
                if (theDetailFragment != null){
                    theDetailFragment.SaveFile();

                }
                break;
            case R.id.menuItem_exitApplication:
                //Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                this.finish();
                break;

        }
        return super.onOptionsItemSelected(item) ;
    }
}


