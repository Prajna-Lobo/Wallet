package com.coffeebeans.mywallet;

import android.app.Application;

import com.coffeebeans.mywallet.di.ActivityComponent;
import com.coffeebeans.mywallet.di.ApplicationComponent;
import com.coffeebeans.mywallet.di.DaggerActivityComponent;
import com.coffeebeans.mywallet.di.DaggerApplicationComponent;
import com.coffeebeans.mywallet.di.TransactionModule;

public class BaseApplication extends Application {
    protected ApplicationComponent applicationComponent;
    protected ActivityComponent activityComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    protected void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .transactionModule(new TransactionModule())
                .build();

        this.activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(getApplicationComponent())
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

    public ActivityComponent getActivityComponent() {
        return this.activityComponent;
    }

    public void setApplicationComponent(ApplicationComponent applicationComponent) {
        this.applicationComponent = applicationComponent;
    }

    public void setActivityComponent(ActivityComponent activityComponent) {
        this.activityComponent = activityComponent;
    }
}
