package com.yb.simplemvpdemo.model.component;

import com.yb.simplemvpdemo.model.modules.MainPresenterModule;
import com.yb.simplemvpdemo.ui.MainActivity;

import dagger.Component;

/**
 * Created by Administrator on 2016/4/17.
 */
@Component(modules = { MainPresenterModule.class})
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
