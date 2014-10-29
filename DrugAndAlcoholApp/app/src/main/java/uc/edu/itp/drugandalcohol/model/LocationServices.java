package uc.edu.itp.drugandalcohol.model;

// import required for access to Azure cloud services
import com.microsoft.windowsazure.mobileservices.*;

import java.net.MalformedURLException;

/**
 * Created by AppDeveloper on 29/10/2014.
 * Using singleton pattern so data from this class can be
 * accessed from other activities and fragments.
 * The singleton insures that only one object can be created
 * by this class at a time.
 *
 * This class will connect to the Microsoft Azure services.
 * It is used to save location data to the SQL database so
 * another phone can access the users latitude longitude
 * location, which is part of the proximity alert user requirement
 */

public class LocationServices
{
    private String TAG = "LocationServices";

    /*
     * these objects are provide by microsoft, need to add jar files to access
     * class & methods associated with these objects
     * We have already added the jar files in the libs folder they are called
     * gson-2.2.2.jar & mobileservices-1.1.5.jar
     */
    private MobileServiceClient mClient;
    private MobileServiceTable mToDoItemTable;

    public LocationServices()
    {

    }
}
