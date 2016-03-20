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
import android.widget.TextView;

import com.amap.api.maps2d.model.Text;
import com.example.ycq.night.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class IntentTestActivity extends AppCompatActivity {
    private EditText content;
    private Button btnNext;
    private Button btnRequest1;
    private Button btnRequest2;
    private Button btnCall;
    private TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_test);
        this.content = (EditText) findViewById(R.id.content);
        this.btnNext = (Button) findViewById(R.id.next);
        this.btnRequest1 = (Button) findViewById(R.id.request1);
        this.btnRequest2 = (Button) findViewById(R.id.request2);
        this.result = (TextView) findViewById(R.id.result);
        this.btnCall = (Button) findViewById(R.id.call);

        this.btnNext.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntentTestActivity.this, Request1Activity.class);
                startActivity(intent);
            }
        });

        this.btnRequest1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] filelist = fileList();
                for(String f : filelist) {
                    android.util.Log.e("ycq", f);
                }
                /*try {
                    FileOutputStream fos = openFileOutput("fff.txt", Context.MODE_APPEND | Context.MODE_PRIVATE);
                    String w = "hello";
                    fos.write(w.getBytes());
                    fos.close();
                } catch (FileNotFoundException ex) {
                    android.util.Log.e("ycq", "FileNotFoundException:"+ex.getMessage());
                } catch (IOException ex) {
                    android.util.Log.e("ycq", "IOException:"+ex.getMessage());
                }*/
                /*try {
                    FileInputStream fos = openFileInput("fff.txt");
                    byte[] bytes = new byte[1024];
                    int readLen = 0;
                    while((readLen = fos.read(bytes)) > 0) {
                        android.util.Log.e("ycq", new String(bytes, 0, readLen));
                    }
                    fos.close();

                } catch (FileNotFoundException ex) {
                    android.util.Log.e("ycq", "FileNotFoundException:"+ex.getMessage());
                } catch (IOException ex) {
                    android.util.Log.e("ycq", "IOException:"+ex.getMessage());
                }*/

            }
        });

        this.btnRequest2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                File f = new File(Environment.DIRECTORY_PICTURES, "xx.jpg");
                result.setText(f.getAbsolutePath());
            }
        });

        this.btnCall.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:13967690058"));
                startActivity(intent);
            }
        });
    }
}
