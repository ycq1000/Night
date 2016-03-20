package com.example.ycq.domain.executor;

import com.example.ycq.data.net.CloudCategory;
import com.example.ycq.domain.UseCase;

import java.net.CookieStore;
import java.util.Map;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by qiang on 2015-12-3.
 */
public class CategoryUseCase extends UseCase {
    private CloudCategory cloudCategory;
    private Map<String, String> params;
    private CookieStore cookieStore;

    public CategoryUseCase(Scheduler postExecutionThread, Map<String, String> params, CookieStore cookieStore) {
        super(postExecutionThread);
        this.cloudCategory = new CloudCategory();
        this.params = params;
        this.cookieStore = cookieStore;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return this.cloudCategory.getCategory(this.params, this.cookieStore);
    }
}
