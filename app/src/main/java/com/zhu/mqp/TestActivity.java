package com.zhu.mqp;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.toprand.netty_server.NettyClient;
import com.toprand.netty_server.SendDate;
import com.toprand.netty_server.data.ReceiveClient;
import com.zhu.mqp.control.service.ClientService;


public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        //ButterArrayProcess.bind(this);

        findViewById(R.id.fl_main_experience).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NettyClient.Instance.get().send(String.valueOf(System.currentTimeMillis()));
            }
        });

        findViewById(R.id.fl_main_design).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //绑定服务
                Intent client = new Intent(TestActivity.this, ClientService.class);
                bindService(client, conn, BIND_AUTO_CREATE);

//                Observable.create(new ObservableOnSubscribe<Bitmap>() {
//                    @Override
//                    public void subscribe(Emitter<Bitmap> emitter) {
//                        emitter.onNext(null);
//                    }
//                }).map(new Function<Bitmap, Bitmap>() {
//                    @Override
//                    public Bitmap next(Bitmap s) {
//                        if (s != null) {
//                            return s;
//                        } else {
//                            Log.e(">>>>", "内存" + s + ",Thread:" + Thread.currentThread().getName());
//                            return null;
//                        }
//                    }
//                }).map(new Function<Bitmap, Bitmap>() {
//                    @Override
//                    public Bitmap next(Bitmap s) {
//                        if (s != null) {
//                            return s;
//                        } else {
//                            Log.e(">>>>", "磁盘" + s + ",Thread:" + Thread.currentThread().getName());
//                            return BitmapFactory.decodeResource(TestActivity.this.getResources(), R.mipmap.img_lotus);
//                        }
//                    }
//                }).map(new Function<Bitmap, Bitmap>() {
//                    @Override
//                    public Bitmap next(Bitmap s) {
//                        if (s != null) {
//                            return s;
//                        } else {
//                            Log.e(">>>>", "网络" + s + ",Thread:" + Thread.currentThread().getName());
//                            return null;
//                        }
//                    }
//                })
//                        .observeOn()
//                        .subscribeOn()
//                        .subscribe(new Observer<Bitmap>() {
//                            @Override
//                            public void onSubscribe() {
//                                Log.e(">>>>", "订阅成功");
//                            }
//
//                            @Override
//                            public void onNext(Bitmap o) {
//                                Log.e(">>>>", "订阅内容：" + o + "," + Thread.currentThread().getName());
//                                if (o == null) return;
//                                ViewGroup view = findViewById(R.id.fl_main_design);
//                                ImageView imageView = (ImageView) view.getChildAt(0);
//                                imageView.setImageBitmap(o);
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                                Log.e(">>>>", "出错" + e.getMessage());
//                            }
//
//                            @Override
//                            public void onComplete() {
//                                Log.e(">>>>", "订阅内容完成>>>>>>>>>>>>>>>>>>>");
//                            }
//                        });

            }
        });

    }


    //服务绑定的连接对象
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            ClientService.ClientReceive receive = (ClientService.ClientReceive) service;
            ClientService clientService = receive.getServer();
            clientService.setReceiveData(new ReceiveClient(){
                @Override
                public void onReceive(String msg) {
                    super.onReceive(msg);
                }

                @Override
                public void onSuccess(SendDate<String> sendDate) {
                    super.onSuccess(sendDate);
                }

            });

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}