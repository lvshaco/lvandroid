package com.example.vibratorui;

import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.view.Menu;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
    private ToggleButton toggleButton1=null;
    private ToggleButton toggleButton2=null;
    private ToggleButton toggleButton3=null;
    private Vibrator vibrator=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        vibrator=(Vibrator)this.getSystemService(VIBRATOR_SERVICE);
        toggleButton1=(ToggleButton)findViewById(R.id.toggleButton1);
        toggleButton2=(ToggleButton)findViewById(R.id.toggleButton2);
        toggleButton3=(ToggleButton)findViewById(R.id.toggleButton3);
        /*短震动*/
        toggleButton1.setOnCheckedChangeListener(new ToggleButton.OnCheckedChangeListener() {
            
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //设置震动周期，第二个参数为 -1表示只震动一次
                    vibrator.vibrate(new long[]{1000, 10, 100, 1000},-1);
                }else{
                    //取消震动
                    vibrator.cancel();
                }
            }
        });
        /*长震动*/
        toggleButton2.setOnCheckedChangeListener(new ToggleButton.OnCheckedChangeListener() {
            
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //设置震动周期，第二个参数为0则震动会一直持续
                if(isChecked){
                    vibrator.vibrate(new long[] { 100, 100, 100, 1000 }, 0);
                }
                else {
                    //取消震动
                    vibrator.cancel();
                }
            }
        });
        /*周期震动*/
        toggleButton3.setOnCheckedChangeListener(new ToggleButton.OnCheckedChangeListener() {
            
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //设置震动周期
                    vibrator.vibrate(new long[] { 1000, 50, 1000, 50, 1000 }, 0);
                }
                else {
                    //取消震动
                    vibrator.cancel();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
