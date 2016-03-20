package com.example.ycq.night.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.bumptech.glide.Glide;
import com.example.ycq.night.MainApplication;
import com.example.ycq.night.R;
import com.example.ycq.night.utils.CookieManager;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import okio.Source;
import okio.Timeout;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class CustomActivity extends AppCompatActivity {
    private TextView textView;
    private Button btn;
    private Button btnLogin;
    private Button btnLogout;
    private Button btnClear;
    private MapView mapView;
    private EditText editText;
    private AMap aMap;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        this.textView = (TextView) findViewById(R.id.tv);
        this.btn = (Button) findViewById(R.id.btn);
        this.btnLogin = (Button) findViewById(R.id.login);
        this.btnLogout = (Button) findViewById(R.id.logout);
        this.btnClear = (Button) findViewById(R.id.clear);
        this.img = (ImageView) findViewById(R.id.img);
        this.editText = (EditText) findViewById(R.id.utext);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        aMap = mapView.getMap();



        this.btn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                /*observable
                        //.map(map)
                        .flatMap(new Func1<String, Observable<String>>() {
                            @Override
                            public Observable<String> call(String s) {
                                String[] strArr = new String[] {s+"+1A", s+"+2A"};
                                return Observable.from(strArr);
                            }
                        })
                        .throttleFirst(2000, TimeUnit.MILLISECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        //.subscribe(observer);
                        .subscribe(next, err, complete);
                Glide.with(CustomActivity.this)
                        .load("http://192.168.0.109/3.jpg")
                        .fitCenter()
                        .into(img);*/
                /*Observable.create(new Observable.OnSubscribe<ArrayList<Doc>>() {
                    @Override
                    public void call(Subscriber<? super ArrayList<Doc>> subscriber) {
                        subscriber.onNext(net());
                    }
                })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<ArrayList<Doc>>() {
                            @Override
                            public void call(ArrayList<Doc> doc) {
                                for(Doc d : doc) {
                                    textView.append("\n"+d.title+"  :=> "+d.summary);
                                }
                            }
                        });*/
                Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext(downLoadBackground());
                    }
                })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<String>() {
                            @Override
                            public void call(String s) {
                                textView.append("\n"+s);
                                /*Intent intent = new Intent();
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.setAction(android.content.Intent.ACTION_VIEW);
                                intent.setDataAndType(Uri.parse(s),
                                        "application/vnd.android.package-archive");
                                startActivity(intent);*/
                            }
                        });
            }
        });
        this.btnLogin.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext(postForm(editText.getText().toString(), true));
                    }
                })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<String>() {
                            @Override
                            public void call(String s) {
                                textView.append("\n"+s);
                            }
                        });
            }
        });
        this.btnLogout.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext(postForm("", false));
                    }
                })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<String>() {
                            @Override
                            public void call(String s) {
                                textView.append("\n"+s);
                            }
                        });
            }
        });
        this.btnClear.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                File file = getFilesDir();
                textView.setText(file.getAbsolutePath());
                MainApplication.getInstance().say();
            }
        });
    }
    private Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> subscriber) {
            /*try {
                Thread.sleep(20000);
            } catch (InterruptedException ex) {
                subscriber.onNext("Err:"+ex.getMessage());
            }*/
            long t = System.currentTimeMillis();
            long d = t + 1000 * 2;
            do {
                t = System.currentTimeMillis();
            } while (t < d);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            subscriber.onNext("随机值："+sdf.format(new Date()));
        }
    });
    private Observer<String> observer = new Observer<String>() {
        @Override
        public void onCompleted() {
            textView.append("\nonCompleted");
        }

        @Override
        public void onError(Throwable e) {
            textView.append("\nonError:"+e.getMessage());
        }

        @Override
        public void onNext(String s) {
            textView.append("\n"+s);
        }
    };
    private Func1<String, String> map = new Func1<String, String>() {
        @Override
        public String call(String s) {
            return String.format("%s map", s);
        }
    };
    private Action1<String> next = new Action1<String>() {
        @Override
        public void call(String s) {
            textView.append("\n"+s);
        }
    };
    private Action0 complete = new Action0() {
        @Override
        public void call() {

        }
    };
    private Action1<Throwable> err = new Action1<Throwable>() {
        @Override
        public void call(Throwable throwable) {

        }
    };

    private ArrayList<Doc> net() {
        ArrayList<Doc> list = new ArrayList<>();
        try {
            URL url = new URL("http://192.168.0.109/index.php");
            URLConnection connection = url.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
            int responseCode = httpURLConnection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK) {
                InputStream in = httpURLConnection.getInputStream();
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput(in, "utf-8");
                int eventType = xpp.getEventType();
                Doc doc = null;
                while(eventType != XmlPullParser.END_DOCUMENT) {
                    switch (eventType) {
                        case XmlPullParser.START_DOCUMENT:
                            android.util.Log.e("ycq", "START_DOCUMENT");
                            break;
                        case XmlPullParser.START_TAG:
                            if(xpp.getName().equals("title")) {
                                doc.title = xpp.nextText();
                            } else if(xpp.getName().equals("summary")) {
                                doc.summary = xpp.nextText();
                            } else if(xpp.getName().equals("item")) {
                                doc = new Doc();
                            }
                            break;
                        case XmlPullParser.END_TAG:
                            if(xpp.getName().equals("item")) {
                                list.add(doc);
                            }
                            break;
                    }
                    eventType = xpp.next();
                }
                in.close();
                return list;
                /*StringBuilder total = new StringBuilder();
                BufferedReader rd = new BufferedReader(new InputStreamReader(in));
                String line = "";
                while((line = rd.readLine()) != null) {
                    total.append(line);
                }
                in.close();
                return total.toString();*/
            }
        } catch (MalformedURLException ex) {

        } catch (IOException ex) {

        } catch (XmlPullParserException ex) {

        }
        return null;
    }
    class Doc {
        public String title;
        public String summary;
        public Doc() {}
        public Doc(String title, String summary) {
            this.title = title;
            this.summary = summary;
        }
    }

    private String get() {
        try {
            OkHttpClient client = new OkHttpClient.Builder().cookieJar(new CookieManager(CustomActivity.this)).build();
            HttpUrl.Builder builder = new HttpUrl.Builder();
            builder.scheme("http")
                    .host("192.168.0.109")
                    //.addPathSegment("public")
                    .addPathSegment("index.php")
                    .addQueryParameter("a", "<aa>");
            HttpUrl httpUrl = builder.build();
            android.util.Log.e("ycq", httpUrl.url().toString());
            Request request = new Request.Builder()
                    .url(httpUrl.url())
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException ex) {
           return "err:"+ex.getMessage();
        }
    }
    private String postJson() {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), "{\"name\":\"ffdsaf\"}");
        Request request = new Request.Builder()
                .url("http://192.168.0.109/server.php")
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException ex) {
            return "err:"+ex.getMessage();
        }
    }
    private String postForm(String username, boolean login) {
        OkHttpClient client = new OkHttpClient();
        client = new OkHttpClient.Builder()
                .cookieJar(new CookieManager(this))
                .build();

        FormBody.Builder formBuilder = new FormBody.Builder();
        if(!login) {
            formBuilder.add("logout", "1");
            formBuilder.add("username", "");
        } else {
            formBuilder.add("username", username);
            formBuilder.add("logout", "0");
        }
        FormBody formBody = formBuilder.build();

        Headers.Builder headerBuilder = new Headers.Builder();
        headerBuilder.add("User-Agent", "night/ycq");

        Headers headers = headerBuilder.build();




        Request request = new Request.Builder()
                .url("http://192.168.0.109/index.php")
                .headers(headers)
                .post(formBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if(response.isSuccessful()) {
                return response.body().string();
            }
            return "form err";
        } catch (IOException ex) {
            return ex.getMessage();
        }
    }
    private String downLoadBackground() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://192.168.0.109/3.jpg").build();
        try {
            Response response = client.newCall(request).execute();

            if(response.isSuccessful()) {
                File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

                if(!path.exists()) {
                    path.mkdirs();
                }
                File pic = new File(path, "j.jpg");
                android.util.Log.e("ycq", pic.getAbsolutePath());
                android.util.Log.e("ycq", getFilesDir().getAbsolutePath());
                android.util.Log.e("ycq", getCacheDir().getAbsolutePath());
                android.util.Log.e("ycq",getExternalCacheDir().getAbsolutePath());
                android.util.Log.e("ycq", getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath());
                android.util.Log.e("ycq", Environment.getExternalStorageDirectory().getAbsolutePath());
                android.util.Log.e("ycq", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath());
                android.util.Log.e("ycq", Environment.getDownloadCacheDirectory().getAbsolutePath());
                android.util.Log.e("ycq", Environment.getDataDirectory().getAbsolutePath());
                if(!pic.exists()) {
                    boolean fe = pic.createNewFile();
                    android.util.Log.e("ycq", Boolean.toString(fe));
                }

                FileOutputStream f = new FileOutputStream(pic, false);
                f.write(response.body().bytes());
                f.close();

                return pic.getAbsolutePath();
            }
        } catch (IOException ex) {
            return ex.getMessage();
        }
        return "";
    }
}