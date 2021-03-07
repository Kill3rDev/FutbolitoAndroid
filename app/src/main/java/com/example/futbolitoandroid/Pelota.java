package com.example.futbolitoandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Property;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import static java.security.AccessController.getContext;

public class Pelota extends View implements SensorEventListener {

    Paint pincel = new Paint();
    int alto, ancho;
    int tamaño=40;
    int borde=12;
    float ex=0,ey=0,ez=0;
    String X,Y,Z;
    //private Property<View, Float> x1;

    public  Pelota (Context interfaz){
        super(interfaz);
        SensorManager nsAdmin = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        Sensor snsrotacion = nsAdmin.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        nsAdmin.registerListener(this,snsrotacion,SensorManager.SENSOR_DELAY_FASTEST);
        Display pantalla=((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        ancho=pantalla.getWidth();
        alto=pantalla.getHeight();
    }

    @Override
    public void onSensorChanged(SensorEvent cambio) {

        ex-=cambio.values[0];
        X=Float.toString(ex);
        if (ex<(tamaño+borde)){
            ex=(tamaño+borde);
        }else if (ex>ancho-(tamaño+borde)){
            ex=ancho-(tamaño+borde);
        }


        ey+=cambio.values[1];
        Y=Float.toString(ey);
        if (ey<(tamaño+borde)){
            ey=(tamaño+borde);
        }else if (ey>(alto-tamaño-170)){
            ey=alto-tamaño-170;
        }

        ey=cambio.values[2];
        Z=String.format("%.2f",ez);
        invalidate();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public  void onDraw(Canvas lienzo){
        pincel.setColor(Color.GREEN);
        lienzo.drawCircle(ex,ey,ez+tamaño,pincel);
        pincel.setColor(Color.BLACK);
        lienzo.drawText("Héctor P",ex-35,ey+3,pincel);
    }
}
