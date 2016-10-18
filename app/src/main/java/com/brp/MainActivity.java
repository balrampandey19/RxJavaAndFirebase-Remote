package com.brp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.brp.ImageHelper.GlideImageHelper;
import com.brp.Model.GettyConfig;
import com.brp.Retrofit.ApiInterface;
import com.brp.Retrofit.MarioWithRx;
import com.brp.Utils.ItemOffsetDecoration;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Activity mActivity;
    GlideImageHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mActivity=this;
        helper=new GlideImageHelper(this);
        getImages();

    }


    public void getImages() {
        ApiInterface userService = MarioWithRx.createService(ApiInterface.class, "swz5kztr484wxwvv6mjzd95y");
        Observable<GettyConfig> call = userService.getImage("");
        call.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GettyConfig>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GettyConfig gettyConfig) {
                        GridLayoutManager layoutManager
                                = new GridLayoutManager(mActivity, 2);
                        recyclerView.setLayoutManager(layoutManager);
                        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(mActivity, 10);
                        recyclerView.addItemDecoration(itemDecoration);
                        recyclerView.setAdapter(new CardAdapter(mActivity,R.layout.item,helper,gettyConfig.getImages()));
                    }
                });

    }
}
