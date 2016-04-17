package com.yb.simplemvpdemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yb.simplemvpdemo.LogUtils;
import com.yb.simplemvpdemo.R;
import com.yb.simplemvpdemo.model.MainModelBean;
import com.yb.simplemvpdemo.model.component.DaggerMainComponent;
import com.yb.simplemvpdemo.model.component.MainComponent;
import com.yb.simplemvpdemo.model.modules.MainPresenterModule;
import com.yb.simplemvpdemo.presenter.MainPresenter;

import javax.inject.Inject;
import javax.inject.Singleton;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {
    private static final String TAG="MainActivity";

    @Bind(R.id.tv_displayWeather) TextView tv;
    @Bind(R.id.progressBar) ProgressBar progressBar;

    @Inject @Singleton
    public MainPresenter myPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();

    }

    public void displayWeather(View view) {
        LogUtils.log(TAG);
        myPresenter.getData();
    }
    @Override
    public void initView() {
        MainComponent component=DaggerMainComponent.builder().mainPresenterModule(new MainPresenterModule(this)).build();
        component.inject(this);
        //myPresenter = new MainPresenter(this);
    }

    @Override
    public void showData(MainModelBean mybean) {
        if(mybean==null){
            tv.setText("请求失败");
        }
        else{
            MainModelBean.WeatherinfoEntity bean = mybean.getWeatherinfo();
            String city=bean.getCity();
            String wd=bean.getWD();
            String ws=bean.getWS();
            String time=bean.getTime();
            String data="城市:"+city+"\n风向:"+wd+"\n风级:"+ws+"\n发布时间:"+time;
            LogUtils.log("fafa");
            tv.setText(data);
        }
        hideProgressBar();
    }
    public void showProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
    }
    public void hideProgressBar(){
        progressBar.setVisibility(View.GONE);
    }
}
