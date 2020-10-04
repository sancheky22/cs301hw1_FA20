// @author Kyle Sanchez
package com.example.cs301hw1_fa20;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

import java.util.concurrent.ThreadLocalRandom;

public class Face extends SurfaceView{
    // Instance variables for different color values
    private int skinColor;
    private int eyeColor;
    private int hairColor;
    private int hairStyle;

    //Instance variables for different paint colors
    private Paint skinPaint;
    private Paint eyePaint;
    private Paint hairPaint;
    //private Paint


    // Constructor
    // calls the randomize function
    public Face(Context context, AttributeSet attrs){
        super(context, attrs);
        setWillNotDraw(false);
        randomize();
    }

    /**
     External Citation
     Date: 10 September 2020
     Problem: Didn't know how to make random int in specific rnage
     Resource:
     https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java
     Solution: Used line of example code from stack overflow
     */

    public void randomize(){
        // creates random rgb values (0-255) for skin,eye,hair

        // skin R,G,B
        int skinR = ThreadLocalRandom.current().nextInt(0,255);
        int skinG = ThreadLocalRandom.current().nextInt(0,255);
        int skinB = ThreadLocalRandom.current().nextInt(0,255);
        // eye R,G,B
        int eyeR = ThreadLocalRandom.current().nextInt(0,255);
        int eyeG = ThreadLocalRandom.current().nextInt(0,255);
        int eyeB = ThreadLocalRandom.current().nextInt(0,255);
        // hair R,G,B
        int hairR = ThreadLocalRandom.current().nextInt(0,255);
        int hairG = ThreadLocalRandom.current().nextInt(0,255);
        int hairB = ThreadLocalRandom.current().nextInt(0,255);

        // creates random colors from within specific range
        // set alpha to 255 (full opacity)
        int randSkin = Color.argb(255,skinR,skinG,skinB);
        int randEye = Color.argb(255,eyeR,eyeG,eyeB);
        int randHairCol  = Color.argb(255,hairR,hairG,hairB);
        int randHairSty =  ThreadLocalRandom.current().nextInt(1,3);

        // setting instance variables to randomized values
        //this.skinColor = randSkin;
        this.eyeColor = randEye;
        this.hairColor = randHairCol;
        this.hairStyle = randHairSty;

        // setting skin,eye,hair colors
        this.skinPaint = new Paint();
        this.skinColor = Color.rgb(skinR,skinB,skinG);
        this.skinPaint.setColor(skinColor);

        // setting eye color
        this.eyePaint = new Paint();
        this.eyeColor = Color.rgb(eyeR,eyeG,eyeB);
        this.eyePaint.setColor(eyeColor);

        //setting hair color
        this.hairPaint = new Paint();
        this.hairColor = Color.rgb(hairR,hairG,hairB);
        this.hairPaint.setColor(hairColor);
    }

   @Override
   public void onDraw(Canvas canvas){
       int cx = getWidth() /2;
       int cy = getHeight() /2;
       int rad = getWidth() /8;
       /*
       float left = cx - rad/2 - rad/3;
       float right = cx + rad/2 + rad/3;
       float top = cy - rad;
       float bottom = cy - rad/8;


        */
       drawFace(canvas,cx,cy,rad);
       drawEyes(canvas,cx,cy,rad);
   }

   public void drawFace(Canvas canvas, int cx, int cy, int rad){
        canvas.drawCircle(cx,cy,rad,skinPaint);
        invalidate();
   }

   public void drawEyes(Canvas canvas, int cx, int cy, int rad){
        canvas.drawCircle(850,400,40,eyePaint);
        canvas.drawCircle(1050,400,40,eyePaint);
        invalidate();
   }
}
