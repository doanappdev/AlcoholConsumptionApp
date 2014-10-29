package uc.edu.itp.drugandalcohol.reactiontest;

/**
 * Created by wh0-r-u on 23/10/2014.
 */

//This is primarily a data class for the player
//To adjust their settings

public class GameSettings {
    private boolean speedByTimer;
    private boolean randomiseSpeed;

    public GameSettings(boolean speedByTimer, boolean randomiseSpeed){
        this.speedByTimer = speedByTimer;
        this.randomiseSpeed = randomiseSpeed;
    }

    public boolean getSpeedByTimer(){
        return speedByTimer;
    }
    public void setSpeedByTimer(boolean value){
        speedByTimer = value;
    }

    public boolean getRandomiseSpeed(){
        return randomiseSpeed;
    }
    public void setRandomiseSpeed(boolean value){
        randomiseSpeed = value;
    }
}
