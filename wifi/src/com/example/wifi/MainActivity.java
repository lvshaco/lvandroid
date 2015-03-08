package com.example.wifi;  
  
import java.util.List;    
import android.app.Activity;  
import android.content.BroadcastReceiver;  
import android.content.Context;  
import android.content.Intent;  
import android.content.IntentFilter;  
import android.net.wifi.ScanResult;  
import android.net.wifi.WifiManager;  
import android.os.Bundle;  
import android.view.Menu;  
import android.view.MenuItem;  
import android.widget.TextView;  
import java.lang.reflect.InvocationTargetException;  
import java.lang.reflect.Method;  
import java.util.Timer;  
import java.util.TimerTask;  
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.content.Context;  
import android.net.wifi.WifiConfiguration;  
import android.net.wifi.WifiManager;  
import android.util.Log;  
import java.lang.reflect.Method;
import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
 
public class MainActivity extends Activity {  
    private Button ui_open;
    private Button ui_scan;
    private TextView ui_hotspot_l;  
    private WifiManager wifimgr;  
    private StringBuilder sb;  
    private List<ScanResult> wifiList;  
    //private BroadcastReceiver wifiReceiver;  
    private WifiReceiver wifiReceiver;   

    /** Called when the activity is first created. */  
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.main);  
      
        ui_open=(Button)findViewById(R.id.open);
        ui_open.setText("open");
        
        ui_scan=(Button)findViewById(R.id.scan);
        ui_scan.setText("scan");

        ui_hotspot_l = (TextView)findViewById(R.id.hotspot_l);
        ui_hotspot_l.setText("...");

        wifimgr = (WifiManager) getSystemService(Context.WIFI_SERVICE);   

        WifiApProxy.init(wifimgr); 
        //通过按钮事件设置热点
        ui_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!WifiApProxy.isopened()) {
                    WifiApProxy.open("lvshaco001", "12345678");
                    ui_open.setText("close");
                } else {
                    WifiApProxy.close();
                    ui_open.setText("open");
                }
            }
        });

        ui_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { 
                /*wifiReceiver = new BroadcastReceiver() {
                    @Override  
                    public void onReceive(Context context, Intent intent) {  
                        wifiList = wifimgr.getScanResults();  
                        for (int i = 0; i < wifiList.size(); i++) {  
                            sb.append(new Integer(i + 1).toString() + ".");  
                            sb.append((wifiList.get(i)).toString()).append("\n\n");  
                        }  
                        ui_hotspot_l.setText(sb.toString());  
                    }  
                };*/
                wifiReceiver = new WifiReceiver();
                registerReceiver(wifiReceiver, new IntentFilter(  
                        WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));  
                wifimgr.startScan();
                ui_hotspot_l.setText("start scan ...");
            }
        });
    }  
  
    @Override  
    public boolean onCreateOptionsMenu(Menu menu) {  
        menu.add(0, 0, 0, "Refresh");  
        return super.onCreateOptionsMenu(menu);  
    }  
  
    @Override  
    public boolean onMenuItemSelected(int featureId, MenuItem item) {  
        wifimgr.startScan();  
        ui_hotspot_l.setText("Starting Scan");  
        return super.onMenuItemSelected(featureId, item);  
  
    }  
  
    @Override  
    protected void onPause() {  
        unregisterReceiver(wifiReceiver);  
        super.onPause();  
    }  
      
    @Override  
    protected void onResume() {  
        registerReceiver(wifiReceiver, new IntentFilter(  
                WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));  
        super.onResume();  
    }  
      
    private final class WifiReceiver extends BroadcastReceiver {  
        @Override  
        public void onReceive(Context context, Intent intent) {  
            ui_hotspot_l.setText("onReceive");
sb = new StringBuilder();  
            wifiList = wifimgr.getScanResults();  
            for (int i = 0; i < wifiList.size(); i++) {  
                sb.append(new Integer(i + 1).toString() + ".");  
                sb.append((wifiList.get(i)).toString()).append("\n\n");  
            }  
            ui_hotspot_l.setText(sb.toString());  
        }  
    }  
};

//public class MainActivity extends Activity {
    //private List<ScanResult> wifiList;
    //private WifiManager wifimgr;
    //private List<String> passableHotsPot;
    //private WifiReceiver wifiReceiver;
    //private boolean isConnected=false;
    //private Button connect;

    //@Override
    //public void onCreate(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        //init();
    //}

    //[> 初始化参数 <]
    //public void init() {
        //setContentView(R.layout.main);
        //connect=(Button)findViewById(R.id.connect);
        //wifimgr = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        //wifiReceiver = new WifiReceiver();
        ////通过按钮事件搜索热点
        //connect.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View v) {
                //wifimgr.startScan();
            //}
        //});     
    //}

    //[> 监听热点变化 <]
    //private final class WifiReceiver extends BroadcastReceiver {
        //@Override
        //public void onReceive(Context context, Intent intent) {
            //wifiList = wifimgr.getScanResults();
            //if (wifiList == null || wifiList.size() == 0 || isConnected)
                //return;
            //onReceiveNewNetworks(wifiList);
        //}
    //}
    
    //[>当搜索到新的wifi热点时判断该热点是否符合规格<]
    //public void onReceiveNewNetworks(List<ScanResult> wifiList){
        //passableHotsPot=new ArrayList<String>();
        //for(ScanResult result:wifiList){
            //System.out.println(result.SSID);
            //if((result.SSID).contains("YRCCONNECTION"))
                //passableHotsPot.add(result.SSID);
        //}
        //synchronized (this) {
            //connectToHotpot();
        //}
    //}
    
    //[>连接到热点<]
    //public void connectToHotpot(){
        //if(passableHotsPot==null || passableHotsPot.size()==0)
            //return;
        //WifiConfiguration wifiConfig=this.setWifiParams(passableHotsPot.get(0));
        //int wcgID = wifimgr.addNetwork(wifiConfig);
        //boolean flag=wifimgr.enableNetwork(wcgID, true);
        //isConnected=flag;
        //System.out.println("connect success? "+flag);
    //}
    
    //[>设置要连接的热点的参数<]
    //public WifiConfiguration setWifiParams(String ssid){
        //WifiConfiguration apConfig=new WifiConfiguration();
        //apConfig.SSID="\""+ssid+"\"";
        //apConfig.preSharedKey="\"12122112\"";
        //apConfig.hiddenSSID = true;
        //apConfig.status = WifiConfiguration.Status.ENABLED;
        //apConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
        //apConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
        //apConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
        //apConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
        //apConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
        //apConfig.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
        //return apConfig;
    //}
    
    //@Override
    //protected void onDestroy() {
        //super.onDestroy();
        //[>销毁时注销广播<]
        //unregisterReceiver(wifiReceiver);
    //}
//}

// wifi热点代理
class WifiApProxy {
    private static WifiManager wifimgr;

    public static void init(WifiManager wifimgr) {
        WifiApProxy.wifimgr = wifimgr;
    }

    public static void open(String ssid, String password) {
        if (isopened())
            return;
        if (wifimgr.isWifiEnabled()) {
            wifimgr.setWifiEnabled(false);
        }
        forceopen(ssid, password);
        
        TimerChecker timer = new TimerChecker() {
            @Override
            protected void ontick() {
                if (isopened()) {
                    this.exit();
                }
            }
        };
        timer.start(1000, 15);
    }

    public static void close() {
        if (isopened()) {
            forceclose();
        }
    }

    public static boolean isopened() {  
        try {  
            Method method = wifimgr.getClass().getMethod("isWifiApEnabled");  
            method.setAccessible(true);  
            return (Boolean) method.invoke(wifimgr);  
        } catch (NoSuchMethodException e) {  
            e.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return false;  
    } 

    private static void forceopen(String ssid, String password) {  
        try {  
            Method method1 = wifimgr.getClass().getMethod("setWifiApEnabled",  
                    WifiConfiguration.class, boolean.class);  
            WifiConfiguration netConfig = new WifiConfiguration();  
  
            netConfig.SSID = ssid;  
            netConfig.preSharedKey = password;  
  
            netConfig.allowedAuthAlgorithms  
                    .set(WifiConfiguration.AuthAlgorithm.OPEN);  
            netConfig.allowedProtocols
                    .set(WifiConfiguration.Protocol.RSN);  
            netConfig.allowedProtocols
                    .set(WifiConfiguration.Protocol.WPA);  
            netConfig.allowedKeyManagement  
                    .set(WifiConfiguration.KeyMgmt.WPA_PSK);  
            netConfig.allowedPairwiseCiphers  
                    .set(WifiConfiguration.PairwiseCipher.CCMP);  
            netConfig.allowedPairwiseCiphers  
                    .set(WifiConfiguration.PairwiseCipher.TKIP);  
            netConfig.allowedGroupCiphers  
                    .set(WifiConfiguration.GroupCipher.CCMP);  
            netConfig.allowedGroupCiphers  
                    .set(WifiConfiguration.GroupCipher.TKIP);  
            
            method1.invoke(wifimgr, netConfig, true);  
  
        } catch (IllegalArgumentException e) {  
            e.printStackTrace();  
        } catch (IllegalAccessException e) {  
            e.printStackTrace();  
        } catch (InvocationTargetException e) {  
            e.printStackTrace();  
        } catch (SecurityException e) {  
            e.printStackTrace();  
        } catch (NoSuchMethodException e) {  
            e.printStackTrace();  
        }  
    }  
  
    private static void forceclose() {  
        try {  
            Method method = wifimgr.getClass().getMethod("getWifiApConfiguration");  
            method.setAccessible(true);  
            WifiConfiguration config = (WifiConfiguration)method.invoke(wifimgr);

            Method method2 = wifimgr.getClass().getMethod("setWifiApEnabled", WifiConfiguration.class, boolean.class);  
            method2.setAccessible(true);
            method2.invoke(wifimgr, config, false);  
        } catch (NoSuchMethodException e) {  
            e.printStackTrace();  
        } catch (IllegalArgumentException e) {  
            e.printStackTrace();  
        } catch (IllegalAccessException e) {  
            e.printStackTrace();  
        } catch (InvocationTargetException e) {  
            e.printStackTrace();  
        }  
    }   
} 

// 定时检查器
abstract class TimerChecker {  
    private int cur_times = 0;  
    private int times = 1;  
    private int tick = 1000; 
    private boolean isrun = false;  
    private Thread thread = null;  
     
    public TimerChecker() {  
        thread = new Thread(new Runnable() {  
            @Override  
            public void run() {  
                while (!isrun) {  
                    cur_times ++;  
                    if (cur_times < times) {  
                        ontick();  
                        try {  
                            thread.sleep(tick);  
                        } catch (InterruptedException e) {  
                            e.printStackTrace();  
                            exit();  
                        }  
                    } else {  
                        ontimeout();
                        exit();
                    }  
                }  
            }  
        });  
    }  
    protected abstract void ontick();  
    private void ontimeout() {}
    public void start(int tick, int times) {  
        this.tick = tick;  
        this.times = times;  
        this.thread.start();  
    }  
    public void exit() {  
        this.isrun = true;  
    }  
} 
