package edu.uab.tindi.mobilecomputingassignment004;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Mike on 10/21/2017.
 */

public class TaskDAO {
    ProjectManagerDBHandler projectDBHandler ;
    private SQLiteDatabase db ;

    public TaskDAO(Context theContext) {
        projectDBHandler = new ProjectManagerDBHandler(theContext) ;
    }

    public void Open() {
        db = projectDBHandler.getWritableDatabase() ;
    }

    public void Close() {
        projectDBHandler.close();
    }

    public void AddTask(Task theNewTask) {
        ContentValues taskValues = new ContentValues() ;

        this.Open();
        taskValues.put(projectDBHandler.TASKS_COLUMN_TITLE, theNewTask.Title()) ;
        taskValues.put(projectDBHandler.TASKS_COLUMN_ASSIGNED, theNewTask.TimeAssigned().toString()) ;
        taskValues.put(projectDBHandler.TASKS_COLUMN_TIMEDUE, theNewTask.TimeToBeCompleted().toString()) ;
        taskValues.put(projectDBHandler.TASKS_COLUMN_STATUS, theNewTask.Completed()) ;
        taskValues.put(projectDBHandler.TASKS_COLUMN_PROJECT, theNewTask.Project()) ;
        taskValues.put(projectDBHandler.TASKS_COLUMN_USER_ID, theNewTask.UserID()) ;

        db.insert(projectDBHandler.TASKS_TABLE, null, taskValues) ;
        this.Close();
    }

    public Task GetTask(int theTaskID) {
        this.Open() ;
        String selectQuery = "SELECT * FROM " + projectDBHandler.TASKS_TABLE
                + " WHERE " + projectDBHandler.USERS_COLUMN_ID + " = " + theTaskID ;

        Cursor dbCursor = db.query(
                projectDBHandler.TASKS_TABLE, new String[] {
                        projectDBHandler.TASKS_COLUMN_ID,
                        projectDBHandler.TASKS_COLUMN_TITLE,
                        projectDBHandler.TASKS_COLUMN_ASSIGNED,
                        projectDBHandler.TASKS_COLUMN_TIMEDUE,
                        projectDBHandler.TASKS_COLUMN_STATUS,
                        projectDBHandler.TASKS_COLUMN_PROJECT,
                        projectDBHandler.TASKS_COLUMN_USER_ID},
                projectDBHandler.TASKS_COLUMN_ID + "=?", new String[] {
                        String.valueOf(theTaskID) }, null, null, null, null);


        // Cursor aCursor = db.rawQuery(selectQuery, null) ;
//        if (aCursor != null) {
//            aCursor.moveToFirst();
//        }
//        else {
//            return null ;
//        }
        Task theTask = new Task("NO Name") ;
        try {

            theTask.ID(Integer.parseInt(dbCursor.getString(0))) ;
            theTask.Title(dbCursor.getString(1)) ;

            DateFormat df = new SimpleDateFormat("mm/dd/yyyy") ;
            theTask.TimeAssigned(df.parse(dbCursor.getString(2))) ;

            theTask.TimeToBeCompleted(df.parse(dbCursor.getString(3))) ;

            theTask.Completed(Boolean.valueOf(dbCursor.getString(4))) ;
            theTask.Project(dbCursor.getString(5));
            theTask.UserID(Integer.parseInt(dbCursor.getString(6))) ;
        }
        catch (Exception ex) {
            String errorMessage = ex.getMessage();
        }
        return theTask ;
    }

    public ArrayList<Task> getAllTasks() {
        this.Open() ;
        ArrayList<Task> listOfTasks = new ArrayList<>() ;
        String selectQuery = "SELECT * FROM " + projectDBHandler.TASKS_TABLE ;


        Cursor dbCursor = db.query(
                projectDBHandler.TASKS_TABLE, new String[] {
                        projectDBHandler.TASKS_COLUMN_ID,
                        projectDBHandler.TASKS_COLUMN_TITLE,
                        projectDBHandler.TASKS_COLUMN_ASSIGNED,
                        projectDBHandler.TASKS_COLUMN_TIMEDUE,
                        projectDBHandler.TASKS_COLUMN_STATUS,
                        projectDBHandler.TASKS_COLUMN_PROJECT,
                        projectDBHandler.TASKS_COLUMN_USER_ID},
                projectDBHandler.TASKS_COLUMN_ID + ">=", new String[] {
                        "0" }, null, null, null, null);


        Cursor aCursor = db.rawQuery(selectQuery, null) ;
        if (aCursor == null) {
            return null ;
        }
        if (aCursor.moveToFirst()) {
            do {
                Task theTask = new Task("NO Name") ;
                try {

                    theTask.ID(Integer.parseInt(dbCursor.getString(0))) ;
                    theTask.Title(dbCursor.getString(1)) ;

                    DateFormat df = new SimpleDateFormat("mm/dd/yyyy") ;
                    theTask.TimeAssigned(df.parse(dbCursor.getString(2))) ;

                    theTask.TimeToBeCompleted(df.parse(dbCursor.getString(3))) ;

                    theTask.Completed(Boolean.valueOf(dbCursor.getString(4))) ;
                    theTask.Project(dbCursor.getString(5));
                    theTask.UserID(Integer.parseInt(dbCursor.getString(6))) ;

                    listOfTasks.add(theTask) ;
                }
                catch (Exception ex) {
                    String errorMessage = ex.getMessage();
                }

            } while (aCursor.moveToNext()) ;
        }
        /*
        Task theTask = new Task("NO Name") ;
        try {

            theTask.ID(Integer.parseInt(dbCursor.getString(0))) ;
            theTask.Title(dbCursor.getString(1)) ;

            DateFormat df = new SimpleDateFormat("mm/dd/yyyy") ;
            theTask.TimeAssigned(df.parse(dbCursor.getString(2))) ;

            theTask.TimeToBeCompleted(df.parse(dbCursor.getString(3))) ;

            theTask.Completed(Boolean.valueOf(dbCursor.getString(4))) ;
            theTask.Project(dbCursor.getString(5));
            theTask.UserID(Integer.parseInt(dbCursor.getString(6))) ;
        }
        catch (Exception ex) {
            String errorMessage = ex.getMessage();
        }
        */

        return listOfTasks ;
    }

    public ArrayList<Task> GetAllTasksForUser(String userID) {
        this.Open();
        ArrayList<Task> listOfTasks = new ArrayList<>() ;
        String selectQuery = null;

        try {
            selectQuery = "SELECT  " +
                    projectDBHandler.TASKS_COLUMN_ID + " , " +
                    projectDBHandler.TASKS_COLUMN_TITLE + " , " +
                    projectDBHandler.TASKS_COLUMN_ASSIGNED + " , " +
                    projectDBHandler.TASKS_COLUMN_TIMEDUE + " , " +
                    projectDBHandler.TASKS_COLUMN_STATUS + " , " +
                    projectDBHandler.TASKS_COLUMN_PROJECT + " , " +
                    projectDBHandler.TASKS_COLUMN_USER_ID  +
                    " FROM " + projectDBHandler.TASKS_TABLE +
                    " WHERE " +
                                    projectDBHandler.TASKS_COLUMN_USER_ID + " = " + userID;

            Cursor dbCursor = db.rawQuery(selectQuery, null) ;
            if (dbCursor == null) {
                return null ;
            }
            if (dbCursor.moveToFirst()) {
                do {
                    Task theTask = new Task("NO Name") ;
                    try {

                        theTask.ID(Integer.parseInt(dbCursor.getString(0))) ;
                        String i = (dbCursor.getString(0)) ;
                        theTask.Title(dbCursor.getString(1)) ;

                        DateFormat df = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy") ;
                        theTask.TimeAssigned(df.parse(dbCursor.getString(2))) ;

                        theTask.TimeToBeCompleted(df.parse(dbCursor.getString(3))) ;

                        theTask.Completed(Boolean.valueOf(dbCursor.getString(4))) ;
                        theTask.Project(dbCursor.getString(5));
                        theTask.UserID(Integer.parseInt(dbCursor.getString(6))) ;

                        listOfTasks.add(theTask) ;
                    }
                    catch (Exception ex) {
                        String errorMessage = ex.getMessage();
                    }

                } while (dbCursor.moveToNext()) ;
            }

            this.Close();
            } catch (Exception e) {
                e.printStackTrace();
                int i = 0 ;
            }

            return listOfTasks ;
    }

    public ArrayList<Task> GetCompleteTasksForUser(String userID) {
        this.Open();
        ArrayList<Task> listOfTasks = new ArrayList<>() ;
        String selectQuery = null;

        try {
            selectQuery = "SELECT  " +
                    projectDBHandler.TASKS_COLUMN_ID + " , " +
                    projectDBHandler.TASKS_COLUMN_TITLE + " , " +
                    projectDBHandler.TASKS_COLUMN_ASSIGNED + " , " +
                    projectDBHandler.TASKS_COLUMN_TIMEDUE + " , " +
                    projectDBHandler.TASKS_COLUMN_STATUS + " , " +
                    projectDBHandler.TASKS_COLUMN_PROJECT + " , " +
                    projectDBHandler.TASKS_COLUMN_USER_ID  +
                    " FROM " + projectDBHandler.TASKS_TABLE +
                    " WHERE " +
                    projectDBHandler.TASKS_COLUMN_USER_ID + " = " + userID +
            " AND " + projectDBHandler.TASKS_COLUMN_STATUS + " = 1";

            Cursor dbCursor = db.rawQuery(selectQuery, null) ;
            if (dbCursor == null) {
                return null ;
            }
            if (dbCursor.moveToFirst()) {
                do {
                    Task theTask = new Task("NO Name") ;
                    try {

                        theTask.ID(Integer.parseInt(dbCursor.getString(0))) ;
                        String i = (dbCursor.getString(0)) ;
                        theTask.Title(dbCursor.getString(1)) ;

                        DateFormat df = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy") ;
                        theTask.TimeAssigned(df.parse(dbCursor.getString(2))) ;

                        theTask.TimeToBeCompleted(df.parse(dbCursor.getString(3))) ;

                        theTask.Completed(Boolean.valueOf(dbCursor.getString(4))) ;
                        theTask.Project(dbCursor.getString(5));
                        theTask.UserID(Integer.parseInt(dbCursor.getString(6))) ;

                        listOfTasks.add(theTask) ;
                    }
                    catch (Exception ex) {
                        String errorMessage = ex.getMessage();
                    }

                } while (dbCursor.moveToNext()) ;
            }

            this.Close();
        } catch (Exception e) {
            e.printStackTrace();
            int i = 0 ;
        }

        return listOfTasks ;
    }

    public ArrayList<Task> GetIncompleteTasksForUser(String userID) {
        this.Open();
        ArrayList<Task> listOfTasks = new ArrayList<>() ;
        String selectQuery = null;

        try {
            selectQuery = "SELECT  " +
                    projectDBHandler.TASKS_COLUMN_ID + " , " +
                    projectDBHandler.TASKS_COLUMN_TITLE + " , " +
                    projectDBHandler.TASKS_COLUMN_ASSIGNED + " , " +
                    projectDBHandler.TASKS_COLUMN_TIMEDUE + " , " +
                    projectDBHandler.TASKS_COLUMN_STATUS + " , " +
                    projectDBHandler.TASKS_COLUMN_PROJECT + " , " +
                    projectDBHandler.TASKS_COLUMN_USER_ID  +
                    " FROM " + projectDBHandler.TASKS_TABLE +
                    " WHERE " +
                    projectDBHandler.TASKS_COLUMN_USER_ID + " = " + userID +
                    " AND " + projectDBHandler.TASKS_COLUMN_STATUS + " = 0";

            Cursor dbCursor = db.rawQuery(selectQuery, null) ;
            if (dbCursor == null) {
                return null ;
            }
            if (dbCursor.moveToFirst()) {
                do {
                    Task theTask = new Task("NO Name") ;
                    try {

                        theTask.ID(Integer.parseInt(dbCursor.getString(0))) ;
                        String i = (dbCursor.getString(0)) ;
                        theTask.Title(dbCursor.getString(1)) ;

                        DateFormat df = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy") ;
                        theTask.TimeAssigned(df.parse(dbCursor.getString(2))) ;

                        theTask.TimeToBeCompleted(df.parse(dbCursor.getString(3))) ;

                        theTask.Completed(Boolean.valueOf(dbCursor.getString(4))) ;
                        theTask.Project(dbCursor.getString(5));
                        theTask.UserID(Integer.parseInt(dbCursor.getString(6))) ;

                        listOfTasks.add(theTask) ;
                    }
                    catch (Exception ex) {
                        String errorMessage = ex.getMessage();
                    }

                } while (dbCursor.moveToNext()) ;
            }

            this.Close();
        } catch (Exception e) {
            e.printStackTrace();
            int i = 0 ;
        }

        return listOfTasks ;
    }

    public ArrayList<Task> GetTasksDueOnDate(String userID) {
        this.Open();
        ArrayList<Task> listOfTasks = new ArrayList<>() ;
        String selectQuery = null;

        try {
            selectQuery = "SELECT  " +
                    projectDBHandler.TASKS_COLUMN_ID + " , " +
                    projectDBHandler.TASKS_COLUMN_TITLE + " , " +
                    projectDBHandler.TASKS_COLUMN_ASSIGNED + " , " +
                    projectDBHandler.TASKS_COLUMN_TIMEDUE + " , " +
                    projectDBHandler.TASKS_COLUMN_STATUS + " , " +
                    projectDBHandler.TASKS_COLUMN_PROJECT + " , " +
                    projectDBHandler.TASKS_COLUMN_USER_ID  +
                    " FROM " + projectDBHandler.TASKS_TABLE +
                    " WHERE " +
                    projectDBHandler.TASKS_COLUMN_USER_ID + " = " + userID +
                    " AND " + projectDBHandler.TASKS_COLUMN_STATUS + " = 0";

            Cursor dbCursor = db.rawQuery(selectQuery, null) ;
            if (dbCursor == null) {
                return null ;
            }
            if (dbCursor.moveToFirst()) {
                do {
                    Task theTask = new Task("NO Name") ;
                    try {

                        theTask.ID(Integer.parseInt(dbCursor.getString(0))) ;
                        String i = (dbCursor.getString(0)) ;
                        theTask.Title(dbCursor.getString(1)) ;

                        DateFormat df = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy") ;
                        theTask.TimeAssigned(df.parse(dbCursor.getString(2))) ;

                        theTask.TimeToBeCompleted(df.parse(dbCursor.getString(3))) ;

                        theTask.Completed(Boolean.valueOf(dbCursor.getString(4))) ;
                        theTask.Project(dbCursor.getString(5));
                        theTask.UserID(Integer.parseInt(dbCursor.getString(6))) ;

                        Calendar dateToday = Calendar.getInstance();
                        Calendar dateTaskDue = Calendar.getInstance();
                        dateToday.setTime(new Date());
                        dateTaskDue.setTime(theTask.TimeToBeCompleted());

                        if(dateToday.get(Calendar.YEAR) == dateTaskDue.get(Calendar.YEAR) &&
                                dateToday.get(Calendar.DAY_OF_YEAR) == dateTaskDue.get(Calendar.DAY_OF_YEAR)){
                            listOfTasks.add(theTask) ;
                        }


                    }
                    catch (Exception ex) {
                        String errorMessage = ex.getMessage();
                    }

                } while (dbCursor.moveToNext()) ;
            }

            this.Close();
        } catch (Exception e) {
            e.printStackTrace();
            int i = 0 ;
        }

        return listOfTasks ;
    }

    public ArrayList<Task> GetTasksSearched(String userID, String searchParams) {
        this.Open();
        ArrayList<Task> listOfTasks = new ArrayList<>() ;
        String selectQuery = null;

        try {
            selectQuery = "SELECT  " +
                    projectDBHandler.TASKS_COLUMN_ID + " , " +
                    projectDBHandler.TASKS_COLUMN_TITLE + " , " +
                    projectDBHandler.TASKS_COLUMN_ASSIGNED + " , " +
                    projectDBHandler.TASKS_COLUMN_TIMEDUE + " , " +
                    projectDBHandler.TASKS_COLUMN_STATUS + " , " +
                    projectDBHandler.TASKS_COLUMN_PROJECT + " , " +
                    projectDBHandler.TASKS_COLUMN_USER_ID  +
                    " FROM " + projectDBHandler.TASKS_TABLE +
                    " WHERE " +
                    projectDBHandler.TASKS_COLUMN_USER_ID + " = " + userID +
                    " AND " + projectDBHandler.TASKS_COLUMN_TITLE + " '%" + searchParams + "%'";

            Cursor dbCursor = db.rawQuery(selectQuery, null) ;
            if (dbCursor == null) {
                return null ;
            }
            if (dbCursor.moveToFirst()) {
                do {
                    Task theTask = new Task("NO Name") ;
                    try {

                        theTask.ID(Integer.parseInt(dbCursor.getString(0))) ;
                        String i = (dbCursor.getString(0)) ;
                        theTask.Title(dbCursor.getString(1)) ;

                        DateFormat df = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy") ;
                        theTask.TimeAssigned(df.parse(dbCursor.getString(2))) ;

                        theTask.TimeToBeCompleted(df.parse(dbCursor.getString(3))) ;

                        theTask.Completed(Boolean.valueOf(dbCursor.getString(4))) ;
                        theTask.Project(dbCursor.getString(5));
                        theTask.UserID(Integer.parseInt(dbCursor.getString(6))) ;

                        listOfTasks.add(theTask) ;
                    }
                    catch (Exception ex) {
                        String errorMessage = ex.getMessage();
                    }

                } while (dbCursor.moveToNext()) ;
            }

            this.Close();
        } catch (Exception e) {
            e.printStackTrace();
            int i = 0 ;
        }

        return listOfTasks ;
    }


    public void updateTask() {}

    public void deleteTask() {}

    void DeleteAllTasks() {
        this.Open();
        db.execSQL("Delete from " +
                projectDBHandler.TASKS_TABLE);

    }

    public void getNumberOfTasks() {}

    public void deleteAllTasks() {}
}
