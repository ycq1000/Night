package com.example.ycq.domain;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

public abstract class UseCase {
    private Subscription subscription = Subscriptions.empty();
    private Scheduler postExecutionThread;

    public UseCase(Scheduler postExecutionThread) {
        this.postExecutionThread = postExecutionThread;
    }

    public void execute(Subscriber subscriber) {
        this.subscription = this.buildUseCaseObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(this.postExecutionThread)
                .subscribe(subscriber);
    }

    public void execute(Action1 onNextAction) {
        this.subscription = this.buildUseCaseObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(this.postExecutionThread)
        .subscribe(onNextAction);
    }

    public void execute(Action1 onNextAction, Action1 onErrAction) {
        this.subscription = this.buildUseCaseObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(this.postExecutionThread)
                .subscribe(onNextAction, onErrAction);
    }

    public void unSubscriber(){
        if (this.subscription.isUnsubscribed()){
            this.subscription.unsubscribe();
        }
    }

    protected abstract Observable buildUseCaseObservable();
}
