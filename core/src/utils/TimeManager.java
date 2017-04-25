package utils;

/**
 * Created by victor on 4/2/17.
 */
public class TimeManager {
    private long startTime;
    private long totalPauseTime;
    private long pauseInit;
    public TimeManager(){
        pauseInit = 0;
        totalPauseTime = 0;
    }
    public void start(){
        //
        startTime = System.nanoTime();
    }
    public float getTime(){
        //
        return (System.nanoTime() - startTime - totalPauseTime)/1000000000f;
    }
    public void pause(){
        pauseInit = System.nanoTime();
    }
    public void unpause(){
        totalPauseTime+= System.nanoTime()- pauseInit;
    }
}
