package com.os.fastlap.constant;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.SimpleProgressiveJpegConfig;
import com.facebook.login.LoginManager;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.os.fastlap.R;
import com.os.fastlap.activity.ChatActivity;
import com.os.fastlap.beans.BeanMessage;
import com.os.fastlap.util.Util;
import com.os.fastlap.util.constants.S;

import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by abhinava on 7/10/2017.
 */

public class FastLapApplication extends MultiDexApplication implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static FastLapApplication fastLapApplication;
    public static GoogleApiClient mGoogleApiClient;
    public static Location location;
    public DisplayImageOptions options;
    private LocationRequest mLocationRequest;
    private Retrofit retrofit;
    public static Context mCurrentContext;

    private Socket mSocket;

    {
        try {
            mSocket = IO.socket(S.CHAT_SERVER_URL);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public Socket getSocket() {
        return mSocket;
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private void socketInit() {
        mSocket.on(Socket.EVENT_CONNECT, onConnect);
        mSocket.on(Socket.EVENT_DISCONNECT, onDisconnect);
        mSocket.on(Socket.EVENT_CONNECT_ERROR, onConnectError);
        mSocket.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
        mSocket.connect();
    }

    private Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            new Handler(Looper.getMainLooper()) {
                @Override
                public void handleMessage(Message msg) {

                }
            };
        }
    };
    private Emitter.Listener onDisconnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            new Handler(Looper.getMainLooper()) {
                @Override
                public void handleMessage(Message msg) {

                }
            };
        }
    };
    private Emitter.Listener onConnectError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            new Handler(Looper.getMainLooper()) {
                @Override
                public void handleMessage(Message msg) {

                }
            };
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        socketInit();
        FacebookSdk.sdkInitialize(getApplicationContext());
        LoginManager.getInstance().logOut();
        AppEventsLogger.activateApp(this);
        fastLapApplication = this;
        initImageLoader(this);

        Util.checkGPSStatus(getApplicationContext());
        autoOnGPS();

        Fresco.initialize(this);

        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setProgressiveJpegConfig(new SimpleProgressiveJpegConfig())
                .setResizeAndRotateEnabledForNetwork(true)
                .setDownsampleEnabled(true)
                .build();
        Fresco.initialize(this, config);

        Bitmap default_bitmap2 = Util.drawableToBitmap(getResources().getDrawable(R.mipmap.profile_default));
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(new BitmapDrawable(getResources(), default_bitmap2))
                .showImageForEmptyUri(new BitmapDrawable(getResources(), default_bitmap2))
                .showImageOnFail(new BitmapDrawable(getResources(), default_bitmap2))
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();

    }

    public Retrofit getRequestQueue() {
        if (retrofit == null) {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(60, TimeUnit.SECONDS);
            builder.readTimeout(60, TimeUnit.SECONDS);
            builder.writeTimeout(60, TimeUnit.SECONDS);


            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

            // Can be Level.BASIC, Level.HEADERS, or Level.BODY
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            // add logging as last interceptor
            builder.addInterceptor(httpLoggingInterceptor); // <-- this is the important line!
            //builder.networkInterceptors().add(httpLoggingInterceptor);
            OkHttpClient okHttpClient = builder.build();

            retrofit = new Retrofit.Builder()

                    .baseUrl(S.BASE_URL)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

    public Retrofit getThirdPartyRequestQueue() {
        Retrofit retrofi = null;

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(60, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);


        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

        // Can be Level.BASIC, Level.HEADERS, or Level.BODY
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        // add logging as last interceptor
        builder.addInterceptor(httpLoggingInterceptor); // <-- this is the important line!
        //builder.networkInterceptors().add(httpLoggingInterceptor);
        OkHttpClient okHttpClient = builder.build();

        retrofi = new Retrofit.Builder()

                .baseUrl(S.YOUTUBE_FEEDS_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofi;
    }

    public Retrofit getDemoRequestQueue() {
        Retrofit retrofi = null;

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(60, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);


        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

        // Can be Level.BASIC, Level.HEADERS, or Level.BODY
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        // add logging as last interceptor
        builder.addInterceptor(httpLoggingInterceptor); // <-- this is the important line!
        //builder.networkInterceptors().add(httpLoggingInterceptor);
        OkHttpClient okHttpClient = builder.build();

        retrofi = new Retrofit.Builder()

                .baseUrl(S.FILE_BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofi;
    }

    /**
     * @return VivaLingApplication Single Instance
     */
    public static FastLapApplication getInstance() {
        return fastLapApplication;
    }

    /**
     * @return The Volley Request queue, the queue will be created if it is null
     */

    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(100 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
       /* LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);
        builder.setAlwaysShow(true);*/
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private String getCurrentTopActivity() {
        ActivityManager mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> RunningTask = mActivityManager.getRunningTasks(1);
        ActivityManager.RunningTaskInfo ar = RunningTask.get(0);
        return ar.topActivity.getClassName().toString();
    }

    public void autoOnGPS() {
        mGoogleApiClient = null;


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API).addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build();
        mGoogleApiClient.connect();
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(30 * 1000);
        locationRequest.setFastestInterval(5 * 1000);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true); // this is the key ingredient

        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi
                .checkLocationSettings(mGoogleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                final LocationSettingsStates state = result
                        .getLocationSettingsStates();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        // All location settings are satisfied. The client can
                        // initialize location
                        // requests here.
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied. But could be
                        // fixed by showing the user
                        // a dialog.
                        try {
                            // Show the dialog by calling
                            // startResolutionForResult(),
                            // and check the result in onActivityResult().
                            //   status.startResolutionForResult(this, 1000);
                        } catch (Exception e) {
                            // Ignore the error.
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // Location settings are not satisfied. However, we have
                        // no way to fix the
                        // settings so we won't show the dialog.
                        break;
                }
            }
        });
    }

    public void openInnerPNDialog(final BeanMessage beanMessage) {
        ((Activity) mCurrentContext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Dialog dialog = new Dialog(mCurrentContext);
                dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.layout_new_message);
                dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
                wmlp.gravity = Gravity.TOP | Gravity.LEFT;
                //  dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

                TextView invitation_received_msg_tv = (TextView) dialog.getWindow().findViewById(R.id.invitation_received_msg_tv);
                invitation_received_msg_tv.setText(beanMessage.getMessageSenderName() + " " + getString(R.string.send_you_a_new_message));
                ImageView iv = (ImageView) dialog.getWindow().findViewById(R.id.iv_userImage);
                ImageLoader.getInstance().displayImage(S.IMAGE_BASE_URL + beanMessage.getMessageSenderImage(), iv, Util.getImageLoaderOption(mCurrentContext));
                dialog.show();
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {

                        dialog.dismiss();
                    }
                }, 3000);

                dialog.findViewById(R.id.ll_main).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        String currentActivity = getCurrentTopActivity();
                        if (!currentActivity.equalsIgnoreCase(ChatActivity.class.getName())) {
                            Intent intent = new Intent(mCurrentContext, ChatActivity.class);
                            intent.putExtra(S.friendId, beanMessage.getChannelId());
                            intent.putExtra(S.userId, beanMessage.getMessageSenderId());
                            intent.putExtra(S.username, beanMessage.getMessageSenderName());
                            intent.putExtra(S.image, beanMessage.getMessageSenderImage());
                            intent.putExtra(S.CHAT_TYPE, beanMessage.getChatType());
                            mCurrentContext.startActivity(intent);
                        }
                    }
                });

            }
        });
    }

    public static Boolean isActivityRunning(Class activityClass) {
        ActivityManager activityManager = (ActivityManager) mCurrentContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = activityManager.getRunningTasks(Integer.MAX_VALUE);

        for (ActivityManager.RunningTaskInfo task : tasks) {
            if (activityClass.getCanonicalName().equalsIgnoreCase(task.baseActivity.getClassName()))
                return true;
        }

        return false;
    }


    public static boolean isActivityVisible() {
        return activityVisible;
    }

    public static void activityResumed() {
        activityVisible = true;
    }

    public static void activityPaused() {
        activityVisible = false;
    }

    private static boolean activityVisible;


}
