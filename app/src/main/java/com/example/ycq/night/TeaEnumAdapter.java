package com.example.ycq.night;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Created by ycg on 2016-03-02.
 */
public class TeaEnumAdapter extends TypeAdapter<Tt.Tea> {
    @Override
    public void write(JsonWriter out, Tt.Tea value) throws IOException {
        if(value == null) {
            out.value(Tt.Tea.UNKNOW.getTypeCode());
            return;
        }
        out.value(value.getTypeCode());
    }

    @Override
    public Tt.Tea read(JsonReader in) throws IOException {
        if(in.peek() == JsonToken.NULL) {
            in.nextNull();
            return Tt.Tea.UNKNOW;
        }
        int teaTypeCode = in.nextInt();
        return Tt.Tea.getTeaFromTypeCode(teaTypeCode);
    }
}
