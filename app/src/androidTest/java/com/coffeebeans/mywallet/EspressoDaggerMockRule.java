package com.coffeebeans.mywallet;

import android.support.test.InstrumentationRegistry;

import com.coffeebeans.mywallet.di.ApplicationComponent;
import com.coffeebeans.mywallet.di.DaggerActivityComponent;

import it.cosenonjaviste.daggermock.DaggerMockRule;

public class EspressoDaggerMockRule extends DaggerMockRule<ApplicationComponent> {

    public EspressoDaggerMockRule(Object... modules) {
        super(ApplicationComponent.class,modules);
        set(component -> {
            BaseApplication app = getApp();
            app.setApplicationComponent(component);
            app.setActivityComponent(DaggerActivityComponent.builder().applicationComponent(component).build());
        });
    }

    private static BaseApplication getApp() {
        return (BaseApplication) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
    }
}
