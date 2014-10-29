package uc.edu.itp.drugandalcohol.model;

/**
 * Created by wh0-r-u on 28/10/2014.
 *
 * Using singleton pattern so data from this class can be
 * accessed from other activities and fragments.
 * The singleton insures that only one object can be created
 * by this class at a time.
 */
public class GameScore {
    // declare member variables as static
    // so data does not get lost when activity/fragment
    // closes
    private static int mScore;
    private static int mHits;
    private static int mMisses;
    private static boolean mAnyTntHit;
    private static String mTimeText;

    private static GameScore mInstance = null;

    // constructor
    protected GameScore() {}

    public static synchronized GameScore getInstance()
    {
        // check if object for class has been created already
        if(mInstance == null){ mInstance = new GameScore(); }

        // when GameScore object is created we set these values to default values
        //mScore = 0;
        //mHits = 0;
        //mMisses = 0;
        //mAnyTntHit = false;
        //mTimeText = "0:00";

        return mInstance;
    }

    //Getter Methods
    public int getScore() { return mScore; }
    public int getHits() { return mHits; }
    public int getMisses() { return mMisses; }
    public boolean getAnyTntHit() { return mAnyTntHit; }
    public String getTimeText() { return mTimeText; }

    //Setter Methods
    public void setScore(int score) {mScore = score; }
    public void setHits(int hits) { mHits = hits; }
    public void setMisses(int misses) { mMisses = misses; }
    public void setAnyTntHit(boolean anyTntHit) { mAnyTntHit = anyTntHit; }
    public void setTimeText(String timeText) { mTimeText = timeText; }
}
