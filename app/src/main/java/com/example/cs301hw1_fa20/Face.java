/**
 *  @author Kyle Sanchez
 *  Version 10/4/2020
 */
package com.example.cs301hw1_fa20;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceView;

import java.util.concurrent.ThreadLocalRandom;

public class Face extends SurfaceView{
    // Instance variables for different color values
    private int skinColor;
    private int eyeColor;
    private int hairColor;
    private int hairStyle; // public in order for MainActivity to have access to it

    //Instance variables for different paint colors
    private Paint skinPaint;
    private Paint eyePaint;
    private Paint hairPaint;


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
        int skinR = ThreadLocalRandom.current().nextInt(0,256);
        int skinG = ThreadLocalRandom.current().nextInt(0,256);
        int skinB = ThreadLocalRandom.current().nextInt(0,256);
        // eye R,G,B
        int eyeR = ThreadLocalRandom.current().nextInt(0,256);
        int eyeG = ThreadLocalRandom.current().nextInt(0,256);
        int eyeB = ThreadLocalRandom.current().nextInt(0,256);
        // hair R,G,B
        int hairR = ThreadLocalRandom.current().nextInt(0,256);
        int hairG = ThreadLocalRandom.current().nextInt(0,256);
        int hairB = ThreadLocalRandom.current().nextInt(0,256);

        // creates random colors from within specific range
        // set alpha to 255 (full opacity)
        int randSkin = Color.argb(255,skinR,skinG,skinB);
        int randEye = Color.argb(255,eyeR,eyeG,eyeB);
        int randHairCol  = Color.argb(255,hairR,hairG,hairB);
        int randHairSty =  ThreadLocalRandom.current().nextInt(1,4);

        // setting instance variables to randomized values
        this.skinColor = randSkin;
        this.eyeColor = randEye;
        this.hairColor = randHairCol;
        this.hairStyle = randHairSty;

        // setting skin paint to skincolor
        this.skinPaint = new Paint();
        this.skinPaint.setColor(skinColor);

        // setting eye paint to eye color
        this.eyePaint = new Paint();
        this.eyePaint.setColor(eyeColor);

        //setting hair paint to hair color
        this.hairPaint = new Paint();
        this.hairPaint.setColor(hairColor);
    }

   @Override
   public void onDraw(Canvas canvas){
       // sets the paint to their corresponding colors
       // needs to be done again because whenever SV is not invalidated by
       // the "Random Face button" you need to get the updated paint colors
       this.hairPaint.setColor(hairColor);
       this.eyePaint.setColor(eyeColor);
       this.skinPaint.setColor(skinColor);

       // gets width and height of SV
       // these ints are used to draw the face, eyes and hair styles
       // to match multiple screens(Hairstyle 3 doesn't do that however)
       int cx = getWidth() /2;
       int cy = getHeight() /2;
       int rad = getWidth() /8;

       // creates float values which are needed by draw arc and rect along with some of the hairstyles
       float left = cx - rad/2 - rad/3;
       float right = cx + rad/2 + rad/3;
       float top = cy - rad;
       float bottom = cy - rad/8;
       float centerX = cx;


       // calling my helper draw methods
       // to redraw aspects of the face
       drawFace(canvas,cx,cy,rad);
       drawEyes(canvas,cx,cy,rad);
       drawMouth(canvas,left,top,right,bottom);

       // depending on what the random hair style int is
       // draw the corresponding hairstyle (1-3)
       if(hairStyle==1){
           drawHairStyle1(canvas,left,top,right,bottom);
       }else if(hairStyle==2){
           drawHairStyle2(canvas,left,top,right,bottom);
       }else if(hairStyle==3){
           drawHairStyle3(canvas,left,top,right,bottom,centerX);
       }

   }

   // The helper methods below help draw the face

   //creates a face with a random color and takes in a center x and y and radius
   public void drawFace(Canvas canvas, int cx, int cy, int rad){
        // draws a circle in the middle of the SV that is the face
        canvas.drawCircle(cx,cy,rad,skinPaint);
        invalidate();
   }
   // similar to the drawFace method but instead draws two circles of equal size
   // on the SV with a random paint color
   public void drawEyes(Canvas canvas, int cx, int cy, int rad){
        canvas.drawCircle(cx-150,cy-80,40,eyePaint);
        canvas.drawCircle(cx+150,cy-80,40,eyePaint);
        invalidate();
   }

   // draws a mouth with the same paint color as the eyes (They always will match)
   public void drawMouth(Canvas canvas, float left,float top,float right,float bottom){
        canvas.drawRect(left+50,top+300,right-50,bottom+150,eyePaint);
        invalidate();
   }
   public void drawHairStyle1(Canvas canvas, float left,float top,float right,float bottom){
        // draws a bowl cut as a hair style with the random hair paint
        canvas.drawArc(left,top,right,bottom,180,180,false,hairPaint);
        invalidate();
   }

    public void drawHairStyle2(Canvas canvas, float left,float top,float right,float bottom){
        // draws a rect as a haircut just like a flattop hair cut and sets its paint as the random hair paint
        canvas.drawRect(left+50,top-75,right-50,bottom-150,hairPaint);
        invalidate();
    }

    public void drawHairStyle3(Canvas canvas, float left,float top,float right,float bottom,float centerX){
        // draws a pointy triangle haircut that looks kind of like a crown
        // NOTE: Doesn't draw properly on tablet but on the Pixel C emulator it does
        /**
         External Citation
         Date: 28 September 2020
         Problem: Didn't know how to draw a triangle that's filled in
         Resources:
         https://kylewbanks.com/blog/drawing-triangles-rhombuses-and-other-shapes-on-android-canvas         https://developer.android.com/reference/android/graphics/Path
         https://developer.android.com/reference/android/graphics/Path
         Solution: Used example code to use paths to draw triangles and read documentation
         to understand path class
         */

        hairPaint.setStrokeWidth(4);
        hairPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        hairPaint.setAntiAlias(true);

        // first triangle
        Path path1 = new Path();
        path1.moveTo(left+600, right-1350); // Top
        path1.lineTo(top+1000 , top+50); // Bottom left
        path1.lineTo(bottom+875,bottom); // Bottom right
        path1.lineTo(left+600, right-1350); // Back to Top
        path1.close();
        canvas.drawPath(path1, hairPaint);

        // second triangle
        Path path2 = new Path();
        path2.moveTo(centerX, right-1350); // Top
        path2.lineTo(top+1000 , top+50); // Bottom left
        path2.lineTo(top+675,top+50); // Bottom right
        path2.lineTo(centerX, right-1350); // Back to Top
        path2.close();
        canvas.drawPath(path2, hairPaint);

        // third triangle
        Path path3 = new Path();
        path3.moveTo(left-60, right-1350); // Top
        path3.lineTo(top+675 , top+50); // Bottom left
        path3.lineTo(bottom+250,bottom); // Bottom right
        path3.lineTo(left-60, right-1350); // Back to Top
        path3.close();
        canvas.drawPath(path3, hairPaint);

    }
    // setters for colors and style
    // since instance vars. are private
    public void setHairStyle(int x){
        this.hairStyle=x;
    }

    public void setSkinColor(int x){
        this.skinColor=x;
    }

    public void setEyeColor(int x){
        this.eyeColor=x;
    }

    public void setHairColor(int x){
        this.hairColor=x;
    }

    //getters for each color (skin,eye,hair)
    // returns value of current color
    public int getSkinColor(){
        return this.skinColor;
    }
    public int getHairColor(){
        return this.hairColor;
    }
    public int getEyeColor(){
        return this.eyeColor;
    }


}
