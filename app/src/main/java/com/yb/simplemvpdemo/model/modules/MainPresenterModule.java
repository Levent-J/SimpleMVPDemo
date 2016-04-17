package com.yb.simplemvpdemo.model.modules;

import com.yb.simplemvpdemo.ui.MainView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2016/4/17.
 */
@Module
public class MainPresenterModule {
    private MainView mainView;
    public MainPresenterModule(MainView mainView){
        this.mainView=mainView;
    }
    @Provides
    public MainView provideMainView(){
        return mainView;
    }
}
