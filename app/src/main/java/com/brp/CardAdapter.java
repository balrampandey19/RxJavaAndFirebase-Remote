package com.brp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.brp.ImageHelper.GlideImageHelper;
import com.brp.Model.GettyConfig;
import com.brp.Model.Image;

import java.util.List;

/**
 * Created by Gamezop on 18/10/16.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private Activity mActivity;

    private int itemLayout;
    private List<Image> data;
    private Image item;
    private GlideImageHelper helper;

    public CardAdapter(Activity v, int itemLayout,GlideImageHelper glideImageHelper,List<Image> configs) {
        mActivity = v;
        this.itemLayout = itemLayout;
        this.helper=glideImageHelper;
        this.data=configs;

    }
    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override public void onBindViewHolder(final ViewHolder holder, int position) {

        item=data.get(position);
        helper.loadImage(item.getDisplaySizes().get(0).getUri(),holder.image,0.5f,R.drawable.placeholder_gray,R.drawable.placeholder_gray);
holder.click.setTag(position);
        holder.click.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Integer ii = (Integer) view.getTag();

        Intent intent=new Intent(mActivity,ImagePreview.class);
        intent.putExtra(ImagePreview.URL,data.get(ii).getDisplaySizes().get(0).getUri());
        mActivity.startActivity(intent);

    }
});

    }



    @Override public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image,click;
        public ViewHolder(View itemView) {
            super(itemView);
            image=(ImageView)itemView.findViewById(R.id.itemImage);
            click=(ImageView)itemView.findViewById(R.id.click);


        }

    }
}
