package com.brp.ImageHelper;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.GifRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.facebook.device.yearclass.YearClass;

/**
 * Created by Kabir on 26-Sep-16.
 */

public class GlideImageHelper implements ImageHelper {

    private Glide glide;
    private RequestManager requestManager;

    private DiskCacheStrategy diskCacheStrategy = DiskCacheStrategy.ALL;

    private final boolean useLowMemory;

    public GlideImageHelper(@NonNull Context context) {
        glide = Glide.get(context);
        requestManager = Glide.with(context);
        useLowMemory = isLowMemoryDevice(context);
    }

    public GlideImageHelper(@NonNull Activity activity) {
        glide = Glide.get(activity);
        requestManager = Glide.with(activity);
        useLowMemory = isLowMemoryDevice(activity.getApplicationContext());
    }

    public GlideImageHelper(@NonNull FragmentActivity activity) {
        glide = Glide.get(activity);
        requestManager = Glide.with(activity);
        useLowMemory = isLowMemoryDevice(activity.getApplicationContext());
    }

    public GlideImageHelper(@NonNull Fragment fragment) {
        glide = Glide.get(fragment.getActivity());
        requestManager = Glide.with(fragment);
        useLowMemory = isLowMemoryDevice(fragment.getActivity().getApplicationContext());
    }

    public GlideImageHelper(@NonNull android.support.v4.app.Fragment fragment) {
        glide = Glide.get(fragment.getActivity());
        requestManager = Glide.with(fragment);
        useLowMemory = isLowMemoryDevice(fragment.getActivity().getApplicationContext());
    }

    private boolean isLowMemoryDevice(@NonNull Context context) {
        int year = YearClass.get(context);
        return year <= YearClass.CLASS_2011;
    }

    private DecodeFormat getFormat() {
        return useLowMemory ? DecodeFormat.PREFER_RGB_565 : DecodeFormat.PREFER_ARGB_8888;
    }

    @Override
    public void loadImage(@NonNull String path, @NonNull ImageView imageView) {
        loadImage(path, imageView, -1f, -1, -1, -1);
    }

    @Override
    public void loadImage(@NonNull String path, @NonNull ImageView imageView, float thumbnailQuality, int
            placeholderDrawableId, int errorDrawableId) {
        loadImage(path, imageView, thumbnailQuality, placeholderDrawableId, errorDrawableId, -1);
    }

    @Override
    public void loadImage(@NonNull String path, @NonNull ImageView imageView, float thumbnailQuality, int
            placeholderDrawableId, int errorDrawableId, int loaderAnimationId) {

        if (path.contains(".gif")) {

            GifRequestBuilder requestBuilder = requestManager.load(path).asGif();

            if (thumbnailQuality > 0f) {
                requestBuilder = requestBuilder.thumbnail(thumbnailQuality).dontAnimate();
            }
            if (placeholderDrawableId != -1) {
                requestBuilder.placeholder(placeholderDrawableId);
            }
            if (errorDrawableId != -1) {
                requestBuilder.error(errorDrawableId);
            }
            if (loaderAnimationId != -1) {
                requestBuilder = requestBuilder.animate(loaderAnimationId);
            }

            requestBuilder.diskCacheStrategy(diskCacheStrategy)
                    .into(imageView);
            return;
        }

        BitmapRequestBuilder requestBuilder = requestManager.load(path).asBitmap();

        if (thumbnailQuality > 0f) {
            requestBuilder = requestBuilder.thumbnail(thumbnailQuality).dontAnimate();
        }
        if (placeholderDrawableId != -1) {
            requestBuilder.placeholder(placeholderDrawableId);
        }
        if (errorDrawableId != -1) {
            requestBuilder.error(errorDrawableId);
        }
        if (loaderAnimationId != -1) {
            requestBuilder = requestBuilder.animate(loaderAnimationId);
        }

        requestBuilder.diskCacheStrategy(diskCacheStrategy)
                .format(getFormat())
                .into(imageView);
    }

    @Override
    public void loadResource(@DrawableRes int drawableId, @NonNull ImageView imageView) {
        requestManager.load(drawableId).into(imageView);
    }

    @Override
    public void releaseMemory() {
        glide.clearMemory();
    }
}
