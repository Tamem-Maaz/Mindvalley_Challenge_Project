package com.talpro213.mindvalley_tamim_maaz_android_test;

import android.app.Activity;
import android.os.Handler;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ActivityUnitTestCase;
import android.test.InstrumentationTestCase;
import android.test.SingleLaunchActivityTestCase;
import android.test.suitebuilder.annotation.SmallTest;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.talpro213.masyncdownloadlib.interfaces.IMDownloadDataType;
import com.talpro213.masyncdownloadlib.models.MDownloadDataType;
import com.talpro213.masyncdownloadlib.models.MDownloadDataTypeImage;
import com.talpro213.masyncdownloadlib.utilities.MProviderMDownloadDataType;

import junit.framework.TestCase;

import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import cz.msebera.android.httpclient.Header;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class MADLibUnitTest extends InstrumentationTestCase   {

    public void testCallMultiRequests() throws Throwable {
        final CountDownLatch signal = new CountDownLatch(1);
        final MProviderMDownloadDataType mProvider = MProviderMDownloadDataType.getInstance();

        final MDownloadDataType mDataTypeImage1 = new MDownloadDataTypeImage("https://images.unsplash.com/profile-1464495186405-68089dcd96c3?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=32&w=32&s=63f1d805cffccb834cf839c719d91702",
                new InterfaceForDataType("Image-1"));
        final MDownloadDataType mDataTypeImage2 = new MDownloadDataTypeImage("https://images.unsplash.com/profile-1464495186405-68089dcd96c3?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=64&w=64&s=ef631d113179b3137f911a05fea56d23",
                new InterfaceForDataType("Image-2"));
        final MDownloadDataType mDataTypeImage3 = new MDownloadDataTypeImage("https://images.unsplash.com/profile-1464495186405-68089dcd96c3?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=128&w=128&s=622a88097cf6661f84cd8942d851d9a2",
                new InterfaceForDataType("Image-3"));
        MDownloadDataType mDataTypeImage4 = new MDownloadDataTypeImage("https://images.unsplash.com/profile-1464495186405-68089dcd96c3?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=64&w=64&s=ef631d113179b3137f911a05fea56d23",
                new InterfaceForDataType("Image-4"));
        MDownloadDataType mDataTypeImage5 = new MDownloadDataTypeImage("https://images.unsplash.com/profile-1464495186405-68089dcd96c3?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=64&w=64&s=ef631d113179b3137f911a05fea56d23",
                new InterfaceForDataType("Image-5"));
        MDownloadDataType mDataTypeImage6 = new MDownloadDataTypeImage("https://images.unsplash.com/profile-1464495186405-68089dcd96c3?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=32&w=32&s=63f1d805cffccb834cf839c719d91702",
                new InterfaceForDataType("Image-6"));

        final MDownloadDataType mDataTypeJson1 = new MDownloadDataTypeImage("http://pastebin.com/raw/wgkJgazE", new InterfaceForDataType("JSON-1"));
        final MDownloadDataType mDataTypeJson2 = new MDownloadDataTypeImage("http://pastebin.com/raw/wgkJgazE", new InterfaceForDataType("JSON-2"));
        MDownloadDataType mDataTypeJson3 = new MDownloadDataTypeImage("http://pastebin.com/raw/wgkJgazE", new InterfaceForDataType("JSON-3"));
        System.out.println("********** Call runTestOnUiThread **********");

        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.e("Test", "Test Hear");
                System.out.println("********** Test Start **********");
                // Get Images
                mProvider.getRequest(mDataTypeImage1);
                mProvider.getRequest(mDataTypeImage2);
                mProvider.getRequest(mDataTypeImage3);

                // Get JSON
                mProvider.getRequest(mDataTypeJson1);

                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }

                mProvider.getRequest(mDataTypeJson2);

                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                }
                while (!mProvider.isRequestDone()){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                signal.countDown();
            }
        });



        try {
            signal.await(30, TimeUnit.SECONDS); // wait for callback
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assert mDataTypeImage1.getData() != null && mDataTypeImage1.getData().length > 0;
        assert mDataTypeImage2.getData() != null && mDataTypeImage2.getData().length > 0;
        assert mDataTypeImage3.getData() != null && mDataTypeImage3.getData().length > 0;

        assert mDataTypeJson1.getData() != null && mDataTypeJson1.getData().length > 0;
        assert mDataTypeJson2.getData() != null && mDataTypeJson2.getData().length > 0;
    }

    public class InterfaceForDataType implements IMDownloadDataType {
        private String name;

        public InterfaceForDataType(String name) {
            this.name = name;
        }

        @Override
        public void onStart(MDownloadDataType mDownloadDataType) {
            System.out.println("********** OnStart Call **********");
            System.out.println("********** Request: " + name + " **********");
            System.out.println("Come From: " + mDownloadDataType.comeFrom);
            System.out.println("Data Type: " + mDownloadDataType.getmDataType().toString());
            System.out.println("Data Length: " + mDownloadDataType.getData().length);
            System.out.println("Url: " + mDownloadDataType.getUrl());
            System.out.println("********** End Request **********");
        }

        @Override
        public void onSuccess(MDownloadDataType mDownloadDataType) {
            System.out.println("********** onSuccess Call **********");
            System.out.println("********** Request: " + name + " **********");
            System.out.println("Come From: " + mDownloadDataType.comeFrom);
            System.out.println("Data Type: " + mDownloadDataType.getmDataType().toString());
            System.out.println("Data Length: " + mDownloadDataType.getData().length);
            System.out.println("Url: " + mDownloadDataType.getUrl());
            System.out.println("********** End Request **********");
        }

        @Override
        public void onFailure(MDownloadDataType mDownloadDataType, int statusCode, byte[] errorResponse, Throwable e) {
            System.out.println("********** onFailure Call **********");
            System.out.println("Come From: " + mDownloadDataType.comeFrom);
            System.out.println("Data Type: " + mDownloadDataType.getmDataType().toString());
            System.out.println("Data Length: " + mDownloadDataType.getData().length);
            System.out.println("Url: " + mDownloadDataType.getUrl());
            System.out.println("********** End Request **********");
        }

        @Override
        public void onRetry(MDownloadDataType mDownloadDataType, int retryNo) {
            System.out.println("********** onRetry Call **********");
            System.out.println("Come From: " + mDownloadDataType.comeFrom);
            System.out.println("Data Type: " + mDownloadDataType.getmDataType().toString());
            System.out.println("Data Length: " + mDownloadDataType.getData().length);
            System.out.println("Url: " + mDownloadDataType.getUrl());
            System.out.println("********** End Request **********");
        }
    }

}