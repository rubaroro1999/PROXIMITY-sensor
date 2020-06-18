package com.example.rubafikri.lab2;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager sensorManager;
    Sensor s;
    ImageView im;

    //TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        im = findViewById(R.id.im);


        //tv = findViewById(R.id.tv);
        sensorManager   = ( SensorManager ) getSystemService(this.SENSOR_SERVICE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) != null){
            s = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        }else{
            Toast.makeText(this, "not found", Toast.LENGTH_SHORT).show();
        }
    }
    MediaPlayer mp;
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY){

            //tv.setText("value :" + event.values[0] +"\n Max val:"+s.getMaximumRange());
           if (event.values[0] == s.getMaximumRange()){

               ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(im,"scaleX",3f);
               ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(im,"scaleY",3f);
               objectAnimator.setDuration(1000);
               objectAnimator1.setDuration(1000);
               AnimatorSet animatorSet = new AnimatorSet();
               animatorSet.play(objectAnimator).with(objectAnimator1);
               animatorSet.start();
               if (mp != null) {
                   mp.stop();
               }

           }else{
               mp = MediaPlayer.create(this, R.raw.mm);

               ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(im,"scaleX",0.2f);
               ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(im,"scaleY",0.2f);
               objectAnimator.setDuration(1000);
               objectAnimator1.setDuration(1000);
               AnimatorSet animatorSet = new AnimatorSet();
               animatorSet.play(objectAnimator).with(objectAnimator1);
               animatorSet.start();
               mp.start();

           }
           }
        }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }



}
