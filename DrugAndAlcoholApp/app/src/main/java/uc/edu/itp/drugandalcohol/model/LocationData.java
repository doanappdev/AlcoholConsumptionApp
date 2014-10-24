package uc.edu.itp.drugandalcohol.model;

/**
 * Created by AppDeveloper on 24/10/2014.
 */
public class LocationData
{
    /**
     * Latitude text
     */
    @com.google.gson.annotations.SerializedName("latitude")
    private String mLatitude;

    /**
     * Longitude text
     */
    @com.google.gson.annotations.SerializedName("longitude")
    private String mLongitude;

    /**
     * User Id
     */
    @com.google.gson.annotations.SerializedName("id")
    private String mId;

    public LocationData()
    {

    }

    /*
    @Override
    public String toString() {
        return getLongitude();
    }
    */

    public LocationData(String id, String lat, String longitude)
    {
        this.setId(id);
        this.setLat(lat);
        this.setLong(longitude);
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
