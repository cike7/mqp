package com.zhu.mqp;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.navigation.NavigationView;
import com.tp.netty_client.ReceiveData;
import com.tp.netty_client.SendDate;
import com.tp.netty_client.model.ReceiveType;
import com.zhu.annotation.ButterKnifeProcess;
import com.zhu.mqp.control.handler.ReceiveHandler;
import com.zhu.mqp.control.service.ClientService;
import com.zhu.mqp.data.model.ChatMessageModel;
import com.zhu.mqp.ui.activity.MessageMenuActivity;
import com.zhu.mqp.ui.adapter.ChatMessageAdapter;
import com.zhu.processortest.BindView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @BindView(R.id.toolbar_main_view)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_main_view)
    NavigationView navigationView;

    @BindView(R.id.nav_main_bottom_view)
    BottomNavigationView bottomNavigationView;

    private ReceiveHandler<MainActivity> handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnifeProcess.bind(this);

        setSupportActionBar(toolbar);

        handler = new ReceiveHandler<>(MainActivity.this);

        //设置底部按钮大于3时也显示标题
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        //监听底部按钮点击
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
//                print("id:" + item.getItemId());
                return true;
            }
        });

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_theme, R.id.nav_help, R.id.nav_feedback)
                .setOpenableLayout(drawer)
                .build();

        //TODO 这里是将侧边菜单按钮点导航到对应的Fragment
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        requestLocationPermission();

        if(isNetworkConnected(this)){
            Intent intent = new Intent(MainActivity.this,ClientService.class);
            bindService(intent,conn, Context.BIND_AUTO_CREATE);
        }else {
            Toast.makeText(this,"请检查网络连接!",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_message:
                Intent intent = new Intent(this, MessageMenuActivity.class);
                startActivity(intent);
                break;
            case R.id.action_settings:

                break;
        }

        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }


    //服务绑定的连接对象
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            ClientService.ClientReceive receive = (ClientService.ClientReceive) service;
            ClientService clientService = receive.getServer();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    public static final int REQUEST_CODE_PERMISSIONS = 101;

    private void requestLocationPermission() {

        ActivityCompat.requestPermissions(this,
                new String[]{
                        Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,
                        Manifest.permission.VIBRATE,
                        Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS,
                        Manifest.permission.FOREGROUND_SERVICE,
                        Manifest.permission.SYSTEM_ALERT_WINDOW
                }, REQUEST_CODE_PERMISSIONS);

//        boolean foreground = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
//
//        if (foreground) {
//            boolean background = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED;
//
//            if (!background) {
//                ActivityCompat.requestPermissions(this,
//                        new String[]{
//                                Manifest.permission.ACCESS_BACKGROUND_LOCATION,
//                                Manifest.permission.ACCESS_COARSE_LOCATION,
//                                Manifest.permission.ACCESS_FINE_LOCATION,
//                                Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,
//                                Manifest.permission.VIBRATE,
//                                Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS,
//                                Manifest.permission.FOREGROUND_SERVICE,
//                                Manifest.permission.SYSTEM_ALERT_WINDOW
//                        }, REQUEST_CODE_PERMISSIONS);
//            }
//        } else {
//            ActivityCompat.requestPermissions(this,
//                    new String[]{
//                            Manifest.permission.ACCESS_BACKGROUND_LOCATION,
//                            Manifest.permission.ACCESS_COARSE_LOCATION,
//                            Manifest.permission.ACCESS_FINE_LOCATION,
//                            Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,
//                            Manifest.permission.VIBRATE,
//                            Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS,
//                            Manifest.permission.FOREGROUND_SERVICE,
//                            Manifest.permission.SYSTEM_ALERT_WINDOW
//                    }, REQUEST_CODE_PERMISSIONS);
//        }
    }


    /**
     * 请求权限返回结果
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSIONS) {

            for (int i = 0; i < permissions.length; i++) {
                if (permissions[i].equalsIgnoreCase(Manifest.permission.ACCESS_COARSE_LOCATION)) {
                    //foreground permission allowed
                    if (grantResults[i] >= 0) {
                        Toast.makeText(getApplicationContext(), "Foreground location permission allowed", Toast.LENGTH_SHORT).show();
                        continue;
                    } else {
                        Toast.makeText(getApplicationContext(), "Location Permission denied", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }

                if (permissions[i].equalsIgnoreCase(Manifest.permission.ACCESS_BACKGROUND_LOCATION)) {
                    if (grantResults[i] >= 0) {
                        Toast.makeText(getApplicationContext(), "Background location location permission allowed", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Background location location permission denied", Toast.LENGTH_SHORT).show();
                    }

                }
            }

        }
    }


    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

}