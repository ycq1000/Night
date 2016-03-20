package com.example.ycq.data.net;

import com.example.ycq.data.model.Article;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by qiang on 2015-12-3.
 */
public class CloudArticle {
    public Observable<Article> getArticle(final int id) {
        return Observable.create(new Observable.OnSubscribe<Article>() {
            @Override
            public void call(Subscriber<? super Article> subscriber) {
                String title = "hehe";
                switch (id % 4) {
                    case 1:
                        title = "从容";
                        break;
                    case 2:
                        title = "夜行";
                        break;
                    case 3:
                        title = "百鬼夜行";
                        break;
                }
                switch (id % 4) {

                }
                subscriber.onNext(new Article(title));
            }
        });
    }
}
