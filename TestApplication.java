package com.altrosmart.dagger;

import com.altrosmart.AltroSmartApp;

public class TestApplication extends AltroSmartApp {

    private static TestApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    @Override
    protected void initAppComponent() {
        sAppComponent = TestAppInjector.init(this);
    }

    public static TestAppComponent getAppComponent() {
        return (TestAppComponent) sAppComponent;
    }

    public static TestApplication getInstance() {
        return sInstance;
    }
}
