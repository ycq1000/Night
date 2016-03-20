package com.example.ycq.data.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by qiang on 2015-12-3.
 */
public class QueryParam extends HashMap<String, String> {
    public QueryParam(Map<String, String> params) {
        this.putAll(params);

    }

    public String getQueryString(String paramsEncoding) {
        StringBuilder encodedQuerys = new StringBuilder();
        try {
            for(Map.Entry<String, String> entry : this.entrySet()) {
                encodedQuerys.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
                encodedQuerys.append("=");
                encodedQuerys.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
                encodedQuerys.append('&');
            }
            return encodedQuerys.toString().substring(0, encodedQuerys.length() - 1);
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException("");
        }



    }
    public String getQueryString() {
        return getQueryString("UTF-8");
    }

    public String buildUrlString(String url) {
        if(url.contains("?")) {
            return String.format("%s&%s", url, this.getQueryString());
        }
        return String.format("%s?%s", url, this.getQueryString());
    }
}
