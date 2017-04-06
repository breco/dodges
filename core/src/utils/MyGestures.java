package utils;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class MyGestures implements GestureDetector.GestureListener, InputProcessor {

    public static String message = "No gesture performed yet";
    //Gestures
    public static boolean pan = false;
    public static boolean tap = false;
    public static float tapX,tapY;
    public static float panX,panY;
    public static float dragX,dragY;
    public static boolean zoom = false, zoomOut = false, zoomMoving = false;
    public static float zoomInit, zoomFin;
    public static float longPX, longPY;
    public static boolean longPress;
    public static boolean touchDown = false;
    public static boolean touchDragged = false;
    public static Vector3 touchPos;
    public static boolean touchUp;
    public static Vector3 firstTouch, newDelta,newTouch,delta,diff;

    public GestureDetector gd;
    public MyGestures(){
        gd = new GestureDetector(this);
        touchPos = new Vector3();
        firstTouch = new Vector3();
        newTouch = new Vector3();
        delta = new Vector3();
        newDelta = new Vector3();
        diff = new Vector3();
    }

    public static void update(){
        tap = false;
        pan = false;
        zoom = false;
        longPress = false;
        touchDown = false;
        touchDragged = false;
        touchUp = false;
    }

    //TAP
    @Override
    public boolean tap(float x, float y, int count, int button) {
        message = "Tap performed, finger" + Integer.toString(button);
        touchPos.set(x,y,0);
        tap = true;
        tapX = x;
        tapY = y;
        return true;
    }
    public static boolean isTap(){
        return tap;
    }
    //

    // PAN
    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        //message = "Pan performed, delta:" + Float.toString(deltaX) +
        //        "," + Float.toString(deltaY);
        //System.out.println(message);
        pan = true;
        panX = deltaX;
        panY = deltaY;
        touchPos.x = x;
        touchPos.y = y;
        return true;
    }
    public static boolean isPan(){
        return pan;
    }

    // ZOOM

    @Override
    public boolean zoom(float initialDistance, float distance) {
        message = "Zoom performed, initial Distance:" + Float.toString(initialDistance) +
                " Distance: " + Float.toString(distance);
        zoom = true;
        zoomInit = initialDistance;
        System.out.println(Math.abs(distance - zoomFin));
        if(Math.abs(distance - zoomFin) <= 5){
            zoomMoving = false;
        }
        zoomFin = distance;
        zoomMoving = true;
        if(distance - initialDistance < 0) zoomOut = true;
        else zoomOut = false;
        return true;
    }
    public static boolean isZoom(){
        return zoom && zoomMoving;
    }

    //LONGPRESS

    @Override
    public boolean longPress(float x, float y) {
        message = "Long press performed";
        longPress = true;
        longPX = x;
        longPY = y;
        return true;
    }

    public static boolean isLongPress(){
        return longPress;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        touchDown = true;

        return true;
    }
    public static boolean isTouchDown(){
        return touchDown;
    }


    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        message = "Fling performed, velocity:" + Float.toString(velocityX) +
                "," + Float.toString(velocityY);
        return true;
    }




    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        message = "Pinch performed";
        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        message = "panStop";
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }




    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        firstTouch.set(screenX, screenY, 0);
        delta.set(0, 0, 0);

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        touchUp = true;
        return false;
    }

    public static boolean isTouchUp(){
        return touchUp;
    }


    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        touchDragged = true;
        newTouch.set(screenX, screenY, 0);
        newDelta = newTouch.cpy().sub(firstTouch);
        //Gdx.app.log("newDelta", newDelta.toString());
        diff = delta.cpy().sub(newDelta);
        //Gdx.app.log("diff",diff.toString());
        delta = newDelta;



        return false;
    }
    public static boolean isTouchDragged(){
        return touchDragged;
    }
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
    public static void resetDiff(){
        diff.set(0,0,0);
    }
}