package com.example.uitest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //R.layout.main，就是在R.java中的R类定义的layout中main，格式为：R.layout.<layout的xml文件名字>，就是对应的res/layout/main.xml文件。
        setContentView(R.layout.main);
        TextView myTextView = (TextView) findViewById(R.id.myTextView);
        myTextView.setText("我的Activity\n........\n........................\n..................................\n..........................");
        Button myButton = (Button) findViewById(R.id.myButton);
        myButton.setText("我的按钮");
    }
}
