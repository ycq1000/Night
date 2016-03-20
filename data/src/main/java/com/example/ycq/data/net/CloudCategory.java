package com.example.ycq.data.net;

import com.example.ycq.data.model.Category;
import com.example.ycq.data.model.PagingObject;
import com.example.ycq.data.utils.NetRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.net.CookieStore;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by qiang on 2015-12-3.
 */
public class CloudCategory {
    private Gson gson = new Gson();
    public Observable<Category> getCategory(final Map<String, String> params, final CookieStore cookieStore) {
        return Observable.create(new Observable.OnSubscribe<Category>() {
            @Override
            public void call(Subscriber<? super Category> subscriber) {
                try {
                    String response = NetRequest.get("http://ycq.test.ycqtest.com/service/word.php", params, cookieStore);
                    List<Category> category = gson.fromJson(response, new TypeToken<List<Category>>(){}.getType());
                    subscriber.onNext(category.get(0));
                } catch (Exception ex) {
                    subscriber.onError(new Throwable(ex.getMessage()));
                }
            }
        });
    }

    public Observable<PagingObject<Category>> getList() {
        return Observable.create(new Observable.OnSubscribe<PagingObject<Category>>() {
            @Override
            public void call(Subscriber<? super PagingObject<Category>> subscriber) {
                try {
                    String response = NetRequest.get("http://ycq.test.ycqtest.com/service/word.php");


                } catch (Exception ex) {

                }
            }
        });
    }
}
