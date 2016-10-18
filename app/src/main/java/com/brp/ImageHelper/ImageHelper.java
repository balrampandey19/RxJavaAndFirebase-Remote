package com.brp.ImageHelper;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.widget.ImageView;

/**
 * Created by admin on 11/16/15.
 */
public interface ImageHelper {

    void loadImage(@NonNull String path, @NonNull ImageView imageView);
    void loadImage(@NonNull String path, @NonNull ImageView imageView, float thumbnailQuality, int
            placeholderDrawableId, int errorDrawableId);
    void loadImage(@NonNull String path, @NonNull ImageView imageView, float thumbnailQuality, int
            placeholderDrawableId, int errorDrawableId, int loaderAnimationId);
    void loadResource(@DrawableRes int drawableId, @NonNull ImageView imageView);

    void releaseMemory();
}
