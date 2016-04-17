package com.yb.simplemvpdemo.presenter;

import com.yb.simplemvpdemo.LogUtils;
import com.yb.simplemvpdemo.model.MainModel;
import com.yb.simplemvpdemo.model.MainModelBean;
import com.yb.simplemvpdemo.ui.MainView;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Administrator on 2016/4/17.
 */
public class MainPresenter {
    private MainView mainView;


    public MainModel mainModel=new MainModel(this);

    @Inject @Singleton
    public MainPresenter(MainView mainView) {
        this.mainView=mainView;
        //mainModel=new MainModel(this);
    }
    public void getData(){
        mainView.showProgressBar();
        mainModel.getData();
    }
    public void loadDataSuccess(List<MainModelBean> list) {

        LogUtils.log(list.get(0)+"");
        mainView.showData(list.get(0));

    }
    public void loadDataFailure(){
        mainView.showData(null);
    }
}
