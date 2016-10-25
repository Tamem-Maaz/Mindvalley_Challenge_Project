package com.talpro213.mindvalley_tamim_maaz_android_test.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.talpro213.masyncdownloadlib.interfaces.IMDownloadDataType;
import com.talpro213.masyncdownloadlib.models.MDownloadDataType;
import com.talpro213.masyncdownloadlib.models.MDownloadDataTypeImage;
import com.talpro213.masyncdownloadlib.utilities.MProviderMDownloadDataType;
import com.talpro213.mindvalley_tamim_maaz_android_test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tamim Maaz on 9/20/2016.
 */


public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private MProviderMDownloadDataType mProvider;

    // Keep all Images in array
    public List<String> imgUrlArray = new ArrayList<>();

    // Constructor
    public ImageAdapter(Context c, List<String> imgUrlArray) {
        mContext = c;
        this.imgUrlArray = imgUrlArray;
        mProvider = MProviderMDownloadDataType.getInstance();
    }

    public void setImgUrlArray(List<String> imgUrlArray) {
        this.imgUrlArray = imgUrlArray;
    }

    @Override
    public int getCount() {
        return imgUrlArray.size();
    }

    @Override
    public Object getItem(int position) {
        return imgUrlArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ImageView imageView = new ImageView(mContext);

        MDownloadDataType mDataTypeImageCancel = new MDownloadDataTypeImage(imgUrlArray.get(position), new IMDownloadDataType() {
            @Override
            public void onStart(MDownloadDataType mDownloadDataType) {

            }

            @Override
            public void onSuccess(MDownloadDataType mDownloadDataType) {
                imageView.setImageBitmap(((MDownloadDataTypeImage) mDownloadDataType).getImageBitmap());
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setLayoutParams(new GridView.LayoutParams(250, 250));
            }

            @Override
            public void onFailure(MDownloadDataType mDownloadDataType, int statusCode, byte[] errorResponse, Throwable e) {
                imageView.setImageResource(R.drawable.ic_menu_gallery);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setLayoutParams(new GridView.LayoutParams(250, 250));
            }

            @Override
            public void onRetry(MDownloadDataType mDownloadDataType, int retryNo) {

            }
        });
        mProvider.getRequest(mDataTypeImageCancel);
        return imageView;
    }

}