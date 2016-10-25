package com.talpro213.masyncdownloadlib.interfaces;

import com.talpro213.masyncdownloadlib.models.MDownloadDataType;

/**
 * Created by Tamim Maaz on 9/18/2016.
 */
public interface IMDownloadDataType {
    public void onStart(MDownloadDataType mDownloadDataType);

    public void onSuccess(MDownloadDataType mDownloadDataType);

    public void onFailure(MDownloadDataType mDownloadDataType, int statusCode, byte[] errorResponse, Throwable e);

    public void onRetry(MDownloadDataType mDownloadDataType, int retryNo);
}
