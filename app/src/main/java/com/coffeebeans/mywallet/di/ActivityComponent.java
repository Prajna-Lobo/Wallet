package com.coffeebeans.mywallet.di;

import com.coffeebeans.mywallet.ui.MainActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
}
