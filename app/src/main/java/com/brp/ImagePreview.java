package com.brp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.brp.ImageHelper.GlideImageHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.ByteArrayOutputStream;
import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImagePreview extends AppCompatActivity {

    @BindView(R.id.imageViewPreview)
     ImageView imageViewPreview;
    private GlideImageHelper helper;
    public static String URL = "url";
    private AmazonS3 s3;
    private TransferUtility transferUtility;
    @BindView(R.id.uplaod)
     Button uplaod;
    private Activity mActivity;
    private File fileUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_preview);
        ButterKnife.bind(this);
        mActivity=this;
        helper = new GlideImageHelper(this);
        Intent intent = getIntent();
        String url = intent.getStringExtra(URL);
        Glide.with(this)
                .load(url)
                .downloadOnly(new SimpleTarget<File>() {
                    @Override
                    public void onResourceReady(File resource, GlideAnimation<? super File> glideAnimation) {
                        fileUpload=resource;

                    }
                });

        uplaod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFileToUpload();
            }
        });
        helper.loadImage(url,imageViewPreview,0.5f,R.drawable.placeholder_gray,R.drawable.placeholder_gray);


        credentialsProvider();
        setTransferUtility();

    }


    private void credentialsProvider() {
        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                getApplicationContext(),
                "us-east-1:5095e217-2e33-4f92-b29e-d1fff761dc24", // Identity Pool ID
                Regions.US_EAST_1 // Region
        );

        setAmazonS3Client(credentialsProvider);
    }

    private void setAmazonS3Client(CognitoCachingCredentialsProvider credentialsProvider) {
        s3 = new AmazonS3Client(credentialsProvider);
        s3.setRegion(Region.getRegion(Regions.US_EAST_1));

    }


    private void setTransferUtility() {

        transferUtility = new TransferUtility(s3, getApplicationContext());
    }

    private void setFileToUpload() {
        TransferObserver transferObserver = transferUtility.upload(
                "redcarpet-test1",     /* The bucket to upload to */
                "AKIAIUYL55HDNOJZ3AZA",    /* The key for the uploaded object */
                fileUpload       /* The file where the data to upload exists */
        );

        transferObserverListener(transferObserver);
    }

    private void transferObserverListener(TransferObserver transferObserver) {

        transferObserver.setTransferListener(new TransferListener() {

            @Override
            public void onStateChanged(int id, TransferState state) {
                Log.e("statechange", state + "");
            }

            @Override
            public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
                int percentage = (int) (bytesCurrent / bytesTotal * 100);
                Log.e("percentage", percentage + "");
            }

            @Override
            public void onError(int id, Exception ex) {
                Log.e("error", "error");
            }

        });
    }

}
