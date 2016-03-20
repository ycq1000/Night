package com.example.ycq.domain.executor;

import com.example.ycq.domain.UseCase;
import com.example.ycq.data.net.CloudArticle;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by qiang on 2015-12-3.
 */
public class ArticleDetailUseCase extends UseCase {
    private CloudArticle cloudArticle;
    private int id;

    public ArticleDetailUseCase(Scheduler postExecutionThread, int id) {
        super(postExecutionThread);
        this.cloudArticle = new CloudArticle();
        this.id = id;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.cloudArticle.getArticle(this.id);
    }
}
