package com.example.weatherdemo.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherdemo.R;
import com.example.weatherdemo.net.Constant;
import com.example.weatherdemo.net.HttpSceneObservable;
import com.example.weatherdemo.net.MyCore;
import com.example.weatherdemo.net.ObservableParams;

import org.json.JSONObject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener{
    private static final String TAG = "MainActivity" ;

    TextView textView ;
    Subscription mGetWeaterSub ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text);
        initData();

        // 监听导航栏点击事件
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initData() {
        String url = "https://free-api.heweather.com/v5/weather?city=beijing"+"&key="+ Constant.KEY_WEATHER;

            mGetWeaterSub = Observable.just(new ObservableParams(url))
                    .map(new GetWeatherObservable())
                    .subscribeOn(MyCore.getCore().getSceneQueue().getOkClientScheduler())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SuccGet(),new ErrorGet());

    }

    class GetWeatherObservable extends HttpSceneObservable<Integer>{
        @Override
        protected Integer dealRespData(JSONObject respObj) {
            Log.d(TAG,"respObj:"+respObj);
            return Constant.ERR_OK;
        }
    }
    class SuccGet implements Action1<Integer>{

        @Override
        public void call(Integer integer) {
           Log.d(TAG,"integer:"+integer) ;
        }
    }
    class ErrorGet implements Action1<Throwable>{

        @Override
        public void call(Throwable throwable) {
            Log.d(TAG,"throwable:"+throwable) ;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId() ;

        switch (id){
            case R.id.nav_mycity:

                break;
            case R.id.nav_citylist:

                break;
            case R.id.nav_night:

                break;
            case R.id.nav_manage:

                break;
        }
        return true;
    }
}
