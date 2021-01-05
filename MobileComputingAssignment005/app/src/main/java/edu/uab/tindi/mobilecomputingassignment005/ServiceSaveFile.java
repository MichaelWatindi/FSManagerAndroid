package edu.uab.tindi.mobilecomputingassignment005;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ServiceSaveFile extends Service {
    private String _fileName ;
    private String _fileContents ;

    public ServiceSaveFile() {
        _fileName = "" ;
        _fileContents = "" ;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void Initialize(String fileName, String fileContents){
        _fileName       = fileName ;
        _fileContents   = fileContents ;
    }
}
