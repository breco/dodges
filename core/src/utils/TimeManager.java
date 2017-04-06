package utils;

/**
 * Created by victor on 4/2/17.
 */
public class TimeManager {
    public long startTime;

    public TimeManager(){

    }
    public void start(){
        startTime = System.nanoTime();
    }
    public float getTime(){
        return (System.nanoTime() - startTime)/1000000000f;
    }
}
