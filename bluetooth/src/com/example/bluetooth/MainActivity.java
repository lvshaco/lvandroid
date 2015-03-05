package com.example.bluetooth;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
    //获得蓝牙适配器
    BluetoothAdapter mAdapter=BluetoothAdapter.getDefaultAdapter();
    //蓝牙请求打开
    private static final int REQUEST_ENABLE=0x1;
    /* 请求能够被搜索 */
    private static final int REQUEST_DISCOVERABLE = 0x2;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
}
  //打开蓝牙
public void onEnableButtonClicked(View view){
    
    if(!mAdapter.isEnabled()){
        //弹出对话框提示用户
        Intent enabler=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(enabler,REQUEST_ENABLE);
        /**
         * 不做提示打开
         * mAdapter.enable();
         */
    }else{
        new AlertDialog.Builder(this)
        .setMessage("蓝牙已打开！")
        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        })
        .setNegativeButton("取消", null).show();
    }
}
    //关闭蓝牙
    public void onDisableButtonClicked(View view){
        mAdapter.disable();
    }
    /* 使设备能够被搜索 */
    public void onMakeDiscoverableButtonClicked(View view)
    {

        Intent enabler = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(enabler, REQUEST_DISCOVERABLE);
    }
    /* 开始搜索 */
    public void onStartDiscoveryButtonClicked(View view)
    {

        Intent enabler = new Intent(this, DiscoveryActivity.class);
        startActivity(enabler);
    }
    /* 客户端 */
    public void onOpenClientSocketButtonClicked(View view)
    {

        Intent enabler = new Intent(this, ClientSocketActivity.class);
        startActivity(enabler);
    }
    /* 服务端 */
    public void onOpenServerSocketButtonClicked(View view)
    {

        Intent enabler = new Intent(this, ServerSocketActivity.class);
        startActivity(enabler);
    }
    /* OBEX服务器 */
    public void onOpenOBEXServerSocketButtonClicked(View view)
    {

        Intent enabler = new Intent(this, OBEXActivity.class);
        startActivity(enabler);
    }
}
