package com.yb.simplemvpdemo.ui;

import com.yb.simplemvpdemo.model.MainModelBean;

/**
 * Created by Administrator on 2016/4/17.
 */
public interface MainView {
    void initView();

    void showData(MainModelBean bean);
    void showProgressBar();
    void hideProgressBar();

}
