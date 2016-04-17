package com.yb.simplemvpdemo.model;

import android.util.Log;

import com.yb.simplemvpdemo.LogUtils;
import com.yb.simplemvpdemo.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/4/17.
 */
public class MainModel {
    String baseUrl="http://www.weather.com.cn/adat/sk/";
    private static final String TAG="MainModel";
    private List<MainModelBean> list = new ArrayList<>();
    private MainPresenter mainPresenter;
    public MainModel(MainPresenter mainPresenter){
        this.mainPresenter=mainPresenter;
    }
    public void getData(){
        LogUtils.log(TAG);
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        WeatherService service=retrofit.create(WeatherService.class);
        service.getModelBean("101010100")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber());
    }
    interface WeatherService{
        @GET("{cityId}"+".html")
        Observable<MainModelBean> getModelBean(@Path("cityId") String cityId);
    }
    class MySubscriber extends Subscriber<MainModelBean>{
        @Override
        public void onCompleted() {
            Log.d("MainActivity", "completed");
            mainPresenter.loadDataSuccess(list);
        }

        @Override
        public void onError(Throwable e) {
            Log.d("MySubscriber", "e:" + e);
            mainPresenter.loadDataFailure();
        }

        @Override
        public void onNext(MainModelBean mainModelBean) {
            list.add(mainModelBean);
            LogUtils.log(TAG);
        }
    }
}
