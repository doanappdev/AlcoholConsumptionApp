package uc.edu.itp.drugandalcohol.model;

/**
 * Created by AppDeveloper on 24/10/2014.
 */
public class LocationData
{

    private static String mLatitude;
    private static String mLongitude;
    private static String mId;

    public LocationData()
    {

    }

    public LocationData(String id, String lat, String longitude)
    {
        mId = id;
        mLatitude = lat;
        mLongitude = longitude;
    }

    /*
     * Set the user id
     * @param id
     *      id to set
     */
    public final void setId(String id)
    {
        mId = id;
    }

    /*
     * Return the users id
     */
    public String getId()
    {
        return mId;
    }

    /*
     * Set the users latitude location
     * @param lat
     */
    public final void setLat(String lat)
    {
        mLatitude = lat;
    }

    /*
     * Return the users latitude location
     */
    public String getLat()
    {
        return mLatitude;
    }

    /*
     * Set the users longitude location
     * @param lat
     */
    public final void setLong(String longitude)
    {
        mLongitude = longitude;
    }

    /*
    * Return the users latitude location
    */
    public String getLongitude()
    {
        return mLongitude;
    }

}
