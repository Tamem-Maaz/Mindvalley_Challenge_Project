package com.talpro213.masyncdownloadlib.interfaces;

import com.talpro213.masyncdownloadlib.models.MDownloadDataType;

/**
 * Created by Tamim Maaz on 9/18/2016.
 */
public interface IMProvider {
    public void markAsDone(MDownloadDataType mDownloadDataType);
    public void onFailure(MDownloadDataType mDownloadDataType);
    public void markAsCancel(MDownloadDataType mDownloadDataType);
}
