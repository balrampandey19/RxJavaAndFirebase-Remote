package com.brp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.brp.ImageHelper.GlideImageHelper;
import com.brp.Model.GettyConfig;
import com.brp.Retrofit.ApiInterface;
import com.brp.Retrofit.MarioWithRx;
import com.brp.Utils.ItemOffsetDecoration;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    private Activity mActivity;
    private GlideImageHelper helper;
    private FirebaseRemoteConfig mFirebaseRemoteConfig;
    private static final String API_KEY = "Api_key";
    private String TAG = "RxJava Sample";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mActivity = this;
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build();
        mFirebaseRemoteConfig.setConfigSettings(configSettings);

        mFirebaseRemoteConfig.setDefaults(R.xml.remote_config_defaults);

        getRemoteApiKey();
        helper = new GlideImageHelper(this);


        getImages();

    }

        /* Get Remote Api Key using Firebase remote */


    private void getRemoteApiKey() {
        long cacheExpiration = 3600; // 1 hour in seconds.
        if (mFirebaseRemoteConfig.getInfo().getConfigSettings().isDeveloperModeEnabled()) {
            cacheExpiration = 0;
        }
        mFirebaseRemoteConfig.fetch(cacheExpiration)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Fetch Succeeded",
                                    Toast.LENGTH_SHORT).show();

                            mFirebaseRemoteConfig.activateFetched();
                        } else {
                            Toast.makeText(MainActivity.this, "Fetch Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                        Log.e("Api-key update=", mFirebaseRemoteConfig.getString(API_KEY));
                    }
                });
    }






    /* Get image list */

    private void getImages() {
        progressBar.setVisibility(View.VISIBLE);
        ApiInterface userService = MarioWithRx.createService(ApiInterface.class, API_KEY);
        Observable<GettyConfig> call = userService.getImage("");
        call.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GettyConfig>() {
                    @Override
                    public void onCompleted() {
                        Log.e(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError", e);

                    }

                    @Override
                    public void onNext(GettyConfig gettyConfig) {
                        progressBar.setVisibility(View.GONE);
                        GridLayoutManager layoutManager
                                = new GridLayoutManager(mActivity, 2);
                        recyclerView.setLayoutManager(layoutManager);
                        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(mActivity, 10);
                        recyclerView.addItemDecoration(itemDecoration);
                        recyclerView.setAdapter(new CardAdapter(mActivity, R.layout.item, helper, gettyConfig.getImages()));
                    }
                });

    }


}
