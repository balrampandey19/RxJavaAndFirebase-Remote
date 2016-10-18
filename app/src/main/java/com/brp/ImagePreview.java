package com.brp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.brp.ImageHelper.GlideImageHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImagePreview extends AppCompatActivity {

    @BindView(R.id.imageViewPreview)
    ImageView imageViewPreview;
    GlideImageHelper helper;
    public static String URL="url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_preview);
        ButterKnife.bind(this);
        helper=new GlideImageHelper(this);
        Intent intent=getIntent();
String url=intent.getStringExtra(URL);
        helper.loadImage(url,imageViewPreview,0.5f,R.drawable.placeholder_gray,R.drawable.placeholder_gray);


    }
}
