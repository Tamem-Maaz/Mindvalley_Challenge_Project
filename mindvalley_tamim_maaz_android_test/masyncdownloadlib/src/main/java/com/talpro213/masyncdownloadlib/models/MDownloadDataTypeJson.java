package com.talpro213.masyncdownloadlib.models;

import com.google.gson.Gson;
import com.talpro213.masyncdownloadlib.interfaces.IMDownloadDataType;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Tamim Maaz on 9/18/2016.
 */
public class MDownloadDataTypeJson extends MDownloadDataType {

    public MDownloadDataTypeJson(String url, IMDownloadDataType imDownloadDataType) {
        super(url, MDataType.JSON, imDownloadDataType);
    }

    @Override
    public MDownloadDataType getCopyOfMe(IMDownloadDataType imDownloadDataType) {
        MDownloadDataType mDownloadDataType = new MDownloadDataTypeJson(this.getUrl(), imDownloadDataType);
        return mDownloadDataType;
    }

    public String getJsonText() {
        try {
            String str = new String(getData(), "UTF-8");
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public Object getJson(Type type) {
        Gson gson = new Gson();
        return gson.fromJson(getJsonText(), type);
    }
}
