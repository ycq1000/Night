package com.example.ycq.data.utils;

/*import com.squareup.okhttp.okhttp3.MediaType;
import com.squareup.okhttp.okhttp3.MultipartBuilder;
import com.squareup.okhttp.okhttp3.OkHttpClient;
import com.squareup.okhttp.okhttp3.Request;
import com.squareup.okhttp.okhttp3.RequestBody;*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.util.Map;

/**
 * Created by qiang on 2015-12-3.
 */
public class NetRequest {
    /*private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final MediaType FORM = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");

    private static OkHttpClient mOkHttpClient = new OkHttpClient();*/

    private NetRequest() { }

    public static String get(String url) throws IOException {
        return get(url, null, null, null);
    }

    public static String get(String url, Map<String, String> params) throws IOException {
        return get(url, params, null, null);
    }

    public static String get(String url, Map<String, String> params, CookieStore cookieStore) throws IOException {
        return get(url, params, cookieStore, null);
    }

    public static String get(String url, Map<String, String> params, CookieStore cookieStore, String tag) throws IOException {
        /*if(null != cookieStore) {
            mOkHttpClient.setCookieHandler(new CookieManager(cookieStore, CookiePolicy.ACCEPT_ALL));
        }
        Request.Builder builder = new Request.Builder();
        String formatUrl;

        if(null != params && params.size() > 0) {
            QueryParam queryParam = new QueryParam(params);
            formatUrl = queryParam.buildUrlString(url);
        } else {
            formatUrl = url;
        }

        if(null != tag) {
            builder.tag(tag);
        }

        Request request = builder.url(formatUrl).build();
        return mOkHttpClient.newCall(request).execute().body().string();*/
        return "";
    }

    public static String post(CookieStore cookieStore, String url, Map<String, String> params, String tag) throws IOException {
        /*mOkHttpClient.setCookieHandler(new CookieManager(cookieStore, CookiePolicy.ACCEPT_ALL));
        Request.Builder builder = new Request.Builder();
        if(null != tag) {
            builder.tag(tag);
        }
        String strParams = null;
        if(null != params && params.size() > 0) {
            strParams = new QueryParam(params).getQueryString();
        }
        RequestBody body = RequestBody.create(FORM, strParams);
        Request request = builder.url(url).post(body).build();
        return mOkHttpClient.newCall(request).execute().body().string();*/
        return "";
    }

    public static void downLoad(String savePath, String url, Map<String, String> params, String tag) throws IOException {
        /*Request.Builder builder = new Request.Builder();
        String formatUrl;

        if(null != params && params.size() > 0) {
            QueryParam queryParam = new QueryParam(params);
            formatUrl = queryParam.buildUrlString(url);
        } else {
            formatUrl = url;
        }

        if(null != tag) {
            builder.tag(tag);
        }
        Request request = builder.url(formatUrl).build();*/
    }

    public static String upload(CookieStore cookieStore, String filePath, String url, Map<String, String> params, String tag) throws IOException {
        /*File f = new File(filePath);
        if(!f.exists() || !f.isFile()) {
            throw new FileNotFoundException();
        }
        mOkHttpClient.setCookieHandler(new CookieManager(cookieStore, CookiePolicy.ACCEPT_ALL));
        String strParams = null;
        if(null != params && params.size() > 0) {
            strParams = new QueryParam(params).getQueryString();
        }
        RequestBody body = new MultipartBuilder().type(MultipartBuilder.FORM).addFormDataPart(
                "file",
                f.getName(),
                RequestBody.create(MediaType.parse("contentType"), f)
        ).build();
        Request.Builder builder = new Request.Builder().url(url).post(body);
        if(null != tag) {
            builder.tag(tag);
        }
        Request request = builder.url(url).post(body).build();
        return mOkHttpClient.newCall(request).execute().body().string();*/
        return "";
    }

    public static void cancel(String tag) {
        if(null != tag) {
            //mOkHttpClient.cancel(tag);
        }
    }

    public static void clearCookie(CookieStore cookieStore) {
        cookieStore.removeAll();
    }
}
