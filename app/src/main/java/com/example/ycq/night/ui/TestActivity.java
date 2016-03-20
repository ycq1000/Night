package com.example.ycq.night.ui;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.renderscript.Type;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ycq.night.R;
import com.example.ycq.night.TeaEnumAdapter;
import com.example.ycq.night.Tt;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class TestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        TextView t = (TextView) findViewById(R.id.textView);
        Tt tt = new Tt();
        String json = "{'title':'标题','tea':2}";
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.registerTypeAdapter(Tt.Tea.class, new TeaEnumAdapter()).create();
        try {
            tt = gson.fromJson(json, Tt.class);
            t.setText(tt.getTea() +":"+tt.toString());
            t.setText(gson.toJson(tt, Tt.class));
        } catch (Exception e) {
            t.setText(e.getMessage());
        }
        //content(t);
        android.util.Log.e("ycq", "oncreate");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String x = savedInstanceState.getString("x");
        android.util.Log.e("ycq", "Restore:"+x);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("x", "xxxxxxxxxx");
        android.util.Log.e("ycq", "Save");
    }

    public void content(TextView view) {
        try {
            Cursor c = getContentResolver().query(ContactsContract.Data.CONTENT_URI,
                    new String[]{
                            android.provider.ContactsContract.Data._ID
                    }, null, null, null);
            if (c.moveToFirst()) {
                String number = c.getString(0);
                view.setText(number);
            }
        } catch (Exception e) {
            view.setText(e.getMessage());
        }
    }
}
