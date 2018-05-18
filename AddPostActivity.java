package com.os.fastlap.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.BetterViewPager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.InputType;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.os.fastlap.R;
import com.os.fastlap.activity.dashboard.DashboardVehicleListActivity;
import com.os.fastlap.adapter.AddPostPagerAdapter;
import com.os.fastlap.adapter.VehicleTypeSpinnerAdapter;
import com.os.fastlap.adapter.settings.SettingBlockUserSuggestionListAdapter;
import com.os.fastlap.beans.BeanVehicleType;
import com.os.fastlap.beans.PagerBean;
import com.os.fastlap.beans.ProfileMyFriendsList;
import com.os.fastlap.beans.chatmodels.PersonalInfo;
import com.os.fastlap.beans.dashboardmodals.DashboardPostListBean;
import com.os.fastlap.constant.FastLapApplication;
import com.os.fastlap.data.AuthAPI;
import com.os.fastlap.delegates.OnImagePickerDialogSelect;
import com.os.fastlap.delegates.SpinnerSelectorInterface;
import com.os.fastlap.dialogs.ChooseImagePickerOptionDialog;
import com.os.fastlap.libs.Gallery;
import com.os.fastlap.util.MySharedPreferences;
import com.os.fastlap.util.Util;
import com.os.fastlap.util.Validation;
import com.os.fastlap.util.constants.I;
import com.os.fastlap.util.constants.S;
import com.os.fastlap.util.customclass.EditTextPlayRegular;
import com.os.fastlap.util.customclass.TextViewPlayBold;
import com.os.fastlap.util.customclass.TextViewPlayRegular;
import com.rw.keyboardlistener.KeyboardUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

import static com.os.fastlap.R.id.base_toggle_icon;
import static com.os.fastlap.R.id.speed_menu_ll;


/*
 * Created by abhinava on 8/16/2017.
 */

public class AddPostActivity extends AppCompatActivity implements View.OnClickListener, OnImagePickerDialogSelect, SpinnerSelectorInterface {
    Context context;
    private RelativeLayout actionBar;
    private AppCompatImageView baseToggleIcon;
    private TextViewPlayBold btnPost;
    private CircleImageView userImg;
    private TextViewPlayBold userNameTv;
    private EditTextPlayRegular postEdittext;
    private LinearLayout trackMenuLl;
    private LinearLayout vehicleMenuLl;
    private LinearLayout positionMenuLl;
    private LinearLayout speedMenuLl;
    private LinearLayout weatherMenuLl;
    private LinearLayout imageMenuLl;
    private LinearLayout feelingMenuLl;
    private static final int VEHICLE_ACTIVITY_RESULT_CODE = 0;
    private static final int TRACK_ACTIVITY_RESULT_CODE = 1;
    private RelativeLayout postHeaderRl;
    private LinearLayout bottomAttachRl;
    private TextViewPlayRegular txtTrackname;
    private LinearLayout tagFriendsMenuLl;
    private OnImagePickerDialogSelect onImagePickerDialogSelect;
    private int REQUEST_CAMERA_PROFILE = 34;
    private int SELECT_FILE_PROFILE = 45;
    public PagerBean pagerBean;
    public ArrayList<PagerBean> imageList = new ArrayList<>();
    private String TAG = AddPostActivity.class.getSimpleName();
    private FrameLayout homeImageLl;
    private LinearLayout llFeedImage;
    private ImageView homeRowFeedImageIv1;
    private ImageView homeRowFeedImageIv2;
    private LinearLayout llFeedImage2;
    private ImageView homeRowFeedImageIv3;
    private FrameLayout llInnerHomeRowFeedImageIv4;
    private ImageView homeRowFeedImageIv4;
    private LinearLayout llAlpha;
    private TextView ivPoints;
    private RelativeLayout media_Rl;
    private int width;
    private ArrayList<BeanVehicleType> feeling_array = new ArrayList<>();
    private ArrayList<BeanVehicleType> weather_array = new ArrayList<>();
    private AuthAPI authAPI;
    private SpinnerSelectorInterface spinnerSelectorInterface;
    private String vehicle_id = "", vehicle_name = "";
    private String track_name = "", track_id = "";
    private String feeling_name = "", feeling_id = "";
    private String weather_name = "", speed_name = "", position_name = "";
    private Dialog dialog1;
    private ArrayList<ProfileMyFriendsList> mMyFriendsLists;
    private SettingBlockUserSuggestionListAdapter spinDialogAdapter;
    private ArrayList<PagerBean> selectedImage = new ArrayList<>();
    private String select_value = "";
    private String feelingSpan = "";
    private String tagfriendspan = "";
    private String deafultSpna = "";
    private SpannableStringBuilder deafult_spna;
    private TextView txtTrackName, txtSelectTrackName, txtSelectVehicleName, txtBestPosition, txtSpeed, txtWeather;
    private Uri videoFileUri;
    private DashboardPostListBean post_details = null;
    private BetterViewPager viewpager;
    private ImageView arraowleft_iv;
    private ImageView arraowright_iv;
    private AddPostPagerAdapter customPagerAdapter;
    private String removeImage = "";
    private RelativeLayout keyboardToplayout;
    private LinearLayout expandLayout;
    private ImageView expand_icon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_post_activity);
        context = this;
        FastLapApplication.mCurrentContext = context;
        onImagePickerDialogSelect = this;
        spinnerSelectorInterface = this;
        authAPI = new AuthAPI(context);
        initView();
        authAPI.getFeelings(context);

        userNameTv.setText(MySharedPreferences.getPreferences(context, S.firstName_response) + " " + MySharedPreferences.getPreferences(context, S.lastName_response));
        ImageLoader.getInstance().displayImage(S.IMAGE_BASE_URL + MySharedPreferences.getPreferences(context, S.image), userImg, Util.getImageLoaderOption(context));
    }

    private void initView() {
        fillWeatherArray();
        deafultSpna = "<font color=#bd2436>" + MySharedPreferences.getPreferences(context, S.firstName_response) + " " + MySharedPreferences.getPreferences(context, S.lastName_response) + "</font>";
        mMyFriendsLists = new ArrayList<>();
        viewpager = findViewById(R.id.viewpager);
        arraowleft_iv = findViewById(R.id.arraowleft_iv);
        arraowright_iv = findViewById(R.id.arraowright_iv);
        txtSelectTrackName = findViewById(R.id.txtSelectTrackName);
        txtSelectVehicleName = findViewById(R.id.txtSelectVehicleName);
        txtBestPosition = findViewById(R.id.txtBestPosition);
        txtSpeed = findViewById(R.id.txtSpeed);
        txtWeather = findViewById(R.id.txtWeather);
        txtTrackName = findViewById(R.id.txtTrackName);
        actionBar = findViewById(R.id.action_bar);
        baseToggleIcon = findViewById(base_toggle_icon);
        btnPost = findViewById(R.id.btnPost);
        userImg = findViewById(R.id.user_img);
        userNameTv = findViewById(R.id.user_name_tv);
        postEdittext = findViewById(R.id.post_edittext);
        trackMenuLl = findViewById(R.id.track_menu_ll);
        media_Rl = findViewById(R.id.media_Rl);
        vehicleMenuLl = (LinearLayout) findViewById(R.id.vehicle_menu_ll);
        positionMenuLl = (LinearLayout) findViewById(R.id.position_menu_ll);
        speedMenuLl = (LinearLayout) findViewById(speed_menu_ll);
        weatherMenuLl = (LinearLayout) findViewById(R.id.weather_menu_ll);
        imageMenuLl = (LinearLayout) findViewById(R.id.image_menu_ll);
        feelingMenuLl = (LinearLayout) findViewById(R.id.feeling_menu_ll);
        postHeaderRl = (RelativeLayout) findViewById(R.id.post_header_rl);
        bottomAttachRl = (LinearLayout) findViewById(R.id.bottom_attach_rl);
        tagFriendsMenuLl = (LinearLayout) findViewById(R.id.tag_friends_menu_ll);

        homeImageLl = (FrameLayout) findViewById(R.id.home_image_ll);
        llFeedImage = (LinearLayout) findViewById(R.id.ll_feed_image);
        homeRowFeedImageIv1 = (ImageView) findViewById(R.id.home_row_feed_image_iv1);
        homeRowFeedImageIv2 = (ImageView) findViewById(R.id.home_row_feed_image_iv2);
        llFeedImage2 = (LinearLayout) findViewById(R.id.ll_feed_image2);
        homeRowFeedImageIv3 = (ImageView) findViewById(R.id.home_row_feed_image_iv3);
        llInnerHomeRowFeedImageIv4 = (FrameLayout) findViewById(R.id.ll_inner_home_row_feed_image_iv4);
        homeRowFeedImageIv4 = (ImageView) findViewById(R.id.home_row_feed_image_iv4);
        llAlpha = (LinearLayout) findViewById(R.id.ll_alpha);
        ivPoints = (TextView) findViewById(R.id.iv_points);

        keyboardToplayout = (RelativeLayout) findViewById(R.id.keyboardToplayout);
        expandLayout = (LinearLayout) findViewById(R.id.expandLayout);
        expand_icon = (ImageView) findViewById(R.id.expand_icon);

        spinDialogAdapter = new SettingBlockUserSuggestionListAdapter(context, mMyFriendsLists, true);

        customPagerAdapter = new AddPostPagerAdapter(context, selectedImage);
        viewpager.setAdapter(customPagerAdapter);


        imageList.clear();
        clickListner();

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        width = displaymetrics.widthPixels;

        if (getIntent().getExtras() != null) {
            post_details = (DashboardPostListBean) getIntent().getExtras().getSerializable(S.data);
            if (post_details != null) {
                postEdittext.setText(post_details.getDescription());
                postEdittext.setSelection(postEdittext.getText().length());
                if (post_details.getTrackData() != null) {
                    track_name = post_details.getTrackData().getName();
                    track_id = post_details.getTrackData().getId();
                    txtSelectTrackName.setText(track_name);
                }
                if (post_details.getBeanVehicleList() != null) {
                    vehicle_name = post_details.getBeanVehicleList().getVehicleBrandIdModal().getVehicleBrandName() + " " + post_details.getBeanVehicleList().getVehicleModelIdModal().getVehicleModelName() + " " + post_details.getBeanVehicleList().getVehicleTypeIdModal().getName();
                    vehicle_id = post_details.getBeanVehicleList().get_id();
                    txtSelectVehicleName.setText(vehicle_name);
                }
                if (!post_details.getSpeed().isEmpty()) {
                    speed_name = post_details.getSpeed();
                    txtSpeed.setText(speed_name + " " + context.getString(R.string.km_per_hour));
                }
                if (!post_details.getPosition_text().isEmpty()) {
                    position_name = post_details.getPosition_text();
                    txtBestPosition.setText(position_name);
                }
                if (!post_details.getWeather().isEmpty()) {
                    weather_name = post_details.getWeather();
                    txtWeather.setText(weather_name);
                }
                if (post_details.getDashboardFeelingIdBeans() != null) {
                    feeling_name = post_details.getDashboardFeelingIdBeans().getName();
                    feeling_id = post_details.getDashboardFeelingIdBeans().get_id();
                    feelingSpan = "<font color=#414141> - Feeling </font><font color=#bd2436>" +
                            Util.getEmoticon(Integer.parseInt(post_details.getDashboardFeelingIdBeans().getUnicodes().replace("U+", "0x").substring(2), 16)) + " " + feeling_name + "</font>";
                    userNameTv.setText(Html.fromHtml(deafultSpna + feelingSpan + tagfriendspan));
                }
                if (post_details.getDashboardCommentPostTagFriendsBeanses().size() != 0) {
                    if (mMyFriendsLists.size() == 0)
                        authAPI.myFriendList(context, MySharedPreferences.getPreferences(context, S.user_id), "");
                    else {
                        spinDialogAdapter.addMyFriendsListAll(mMyFriendsLists);
                        spinDialogAdapter.addSelectedTagFriends(post_details.getDashboardCommentPostTagFriendsBeanses());
                        tagfriendspan = " " + spinDialogAdapter.getSelectedFriendsNames();
                        userNameTv.setText((Html.fromHtml(deafultSpna + feelingSpan + tagfriendspan)));
                    }
                }
                if (post_details.getDashboardPostImagesBeans().size() != 0) {
                    selectedImage = new ArrayList<>();
                    for (int i = 0; i < post_details.getDashboardPostImagesBeans().size(); i++) {
                        PagerBean pagerBean = new PagerBean();
                        pagerBean.setImage_id(post_details.getDashboardPostImagesBeans().get(i).get_id());
                        pagerBean.setEdit(true);
                        if (!post_details.getDashboardPostImagesBeans().get(i).getType().equalsIgnoreCase(S.videoType))
                            pagerBean.setImage(post_details.getDashboardPostImagesBeans().get(i).getFileName());
                        else
                            pagerBean.setImage(post_details.getDashboardPostImagesBeans().get(i).getThumbName());
                        pagerBean.setFileType(post_details.getDashboardPostImagesBeans().get(i).getType());

                        selectedImage.add(pagerBean);
                    }
                    setShowMediaFiles(selectedImage);
                    /*customPagerAdapter = new AddPostPagerAdapter(context, selectedImage);
                    viewpager.setAdapter(customPagerAdapter);*/


                }
            }
        }

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int pos) {

                int imageSize = 0;
                imageSize = selectedImage.size();
                if (pos == 0) {
                    arraowleft_iv.setVisibility(View.GONE);
                } else if (imageSize > 1) {
                    arraowleft_iv.setVisibility(View.VISIBLE);
                }

                if ((imageSize - 1) == pos) {
                    arraowright_iv.setVisibility(View.GONE);
                } else if (imageSize > 1) {
                    arraowright_iv.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    private void fillWeatherArray() {

        BeanVehicleType beanVehicleType = new BeanVehicleType();
        beanVehicleType.setId("1");
        beanVehicleType.setName(getString(R.string.dry));

        weather_array.add(beanVehicleType);

        beanVehicleType = new BeanVehicleType();
        beanVehicleType.setId("1");
        beanVehicleType.setName(getString(R.string.cloud));

        weather_array.add(beanVehicleType);

        beanVehicleType = new BeanVehicleType();
        beanVehicleType.setId("1");
        beanVehicleType.setName(getString(R.string.rain));

        weather_array.add(beanVehicleType);

        beanVehicleType = new BeanVehicleType();
        beanVehicleType.setId("1");
        beanVehicleType.setName(getString(R.string.ice));

        weather_array.add(beanVehicleType);
    }

    private void clickListner() {
        baseToggleIcon.setOnClickListener(this);
        btnPost.setOnClickListener(this);
        vehicleMenuLl.setOnClickListener(this);
        trackMenuLl.setOnClickListener(this);
        positionMenuLl.setOnClickListener(this);
        speedMenuLl.setOnClickListener(this);
        weatherMenuLl.setOnClickListener(this);
        imageMenuLl.setOnClickListener(this);
        feelingMenuLl.setOnClickListener(this);
        tagFriendsMenuLl.setOnClickListener(this);
        arraowleft_iv.setOnClickListener(this);
        arraowright_iv.setOnClickListener(this);
        media_Rl.setOnClickListener(this);
        keyboardToplayout.setOnClickListener(this);
        expand_icon.setOnClickListener(this);

        KeyboardUtils.addKeyboardToggleListener(this, new KeyboardUtils.SoftKeyboardToggleListener() {
            @Override
            public void onToggleSoftKeyboard(boolean isVisible) {
                if (isVisible) {
                    keyboardToplayout.setVisibility(View.VISIBLE);
                    expandLayout.setVisibility(View.GONE);
                } else {
                    if (keyboardToplayout.getVisibility() == View.VISIBLE) {
                        keyboardToplayout.setVisibility(View.VISIBLE);
                        expandLayout.setVisibility(View.GONE);
                    } else {
                        keyboardToplayout.setVisibility(View.GONE);
                        expandLayout.setVisibility(View.VISIBLE);
                    }

                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.arraowleft_iv:
                int last_pos = viewpager.getCurrentItem();
                int next_post = last_pos - 1;
                viewpager.setCurrentItem(next_post);
                break;
            case R.id.arraowright_iv:
                int lastpos = viewpager.getCurrentItem();
                int nextpost = lastpos + 1;
                viewpager.setCurrentItem(nextpost);
                break;
            case base_toggle_icon:
                setResult(Activity.RESULT_CANCELED);
                finish();
                break;

            case R.id.btnPost:
                String description = postEdittext.getText().toString().trim();

                if (Validation.addPostValidation(selectedImage, description, btnPost, context)) {
                    //   String addmedia = MySharedPreferences.getPreferences(context, S.userSubscriptionMedia);

                    /*if (!TextUtils.isEmpty(addmedia))
                    {
                        int planaddMedia = Integer.parseInt(addmedia);
                        if (planaddMedia == -1) {
                            authAPI.postSave(context, MySharedPreferences.getPreferences(context, S.user_id), vehicle_id, track_id, txtBestPosition.getText().toString(), feeling_id, description, selectedImage, spinDialogAdapter.getSelectedFriendsList().toString(), getThumbnailCount(), speed_name, weather_name);
                        } else if (selectedImage.size() <= planaddMedia) {
                            authAPI.postSave(context, MySharedPreferences.getPreferences(context, S.user_id), vehicle_id, track_id, txtBestPosition.getText().toString(), feeling_id, description, selectedImage, spinDialogAdapter.getSelectedFriendsList().toString(), getThumbnailCount(), speed_name, weather_name);
                        } else {
                            Util.showSnackBar(btnPost, context.getString(R.string.according_to_your_subscription_plan_you_can_add_only) + " " + planaddMedia + context.getString(R.string.media_files));
                        }
                    } else {
                        Util.showSnackBar(btnPost, context.getString(R.string.please_subscribe_any_plan_for_add_media_files));
                    }*/

                    if (post_details == null)
                        authAPI.postSave(context, MySharedPreferences.getPreferences(context, S.user_id), vehicle_id, track_id, txtBestPosition.getText().toString(), feeling_id, description, selectedImage, spinDialogAdapter.getSelectedFriendsList().toString(), getThumbnailCount(), speed_name, weather_name);
                    else
                        authAPI.updatePost(context, MySharedPreferences.getPreferences(context, S.user_id), vehicle_id, track_id, txtBestPosition.getText().toString(), feeling_id, description, selectedImage, spinDialogAdapter.getSelectedFriendsListOnlyNew().toString(), getThumbnailCount(), speed_name, weather_name, removeImage, spinDialogAdapter.getRemoveFriendTag(), post_details.get_id());

                }

                break;

            case R.id.vehicle_menu_ll:
                Intent intent = new Intent(this, DashboardVehicleListActivity.class);
                startActivityForResult(intent, VEHICLE_ACTIVITY_RESULT_CODE);
                break;

            case R.id.track_menu_ll:
                Intent trackIntent = new Intent(context, TracksListingActivity.class);
                startActivityForResult(trackIntent, TRACK_ACTIVITY_RESULT_CODE);
                break;

            case R.id.position_menu_ll:
                showPositionTextDialog();
                break;

            case speed_menu_ll:
                showSpeedDialog();
                break;

            case R.id.weather_menu_ll:
                showWeatherDialog();
                break;

            case R.id.image_menu_ll:
                if (Build.VERSION.SDK_INT >= 23)
                    checkRequestPermission();
                else
                    showImageDialog();
                break;

            case R.id.feeling_menu_ll:
                showFeelingDialog(context.getResources().getString(R.string.select_mood), feeling_array, I.FEELING);
                break;
            case R.id.tag_friends_menu_ll:
                if (mMyFriendsLists.size() == 0)
                    authAPI.myFriendList(context, MySharedPreferences.getPreferences(context, S.user_id), "");
                else
                    showMyFriendsList();
                break;
            case R.id.media_Rl:
                if (selectedImage.size() == 0) {
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                }
                break;

            case R.id.keyboardToplayout:
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                keyboardToplayout.setVisibility(View.GONE);
                expandLayout.setVisibility(View.VISIBLE);

                KeyboardUtils.forceCloseKeyboard(keyboardToplayout);

                break;

            case R.id.expand_icon:
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                keyboardToplayout.setVisibility(View.VISIBLE);
                expandLayout.setVisibility(View.GONE);
                break;
        }
    }

    private int getThumbnailCount() {
        int length = 0;
        for (int i = 0; i < selectedImage.size(); i++) {
            if (selectedImage.get(i).getFileType().equalsIgnoreCase(S.video))
                length++;
        }
        return length;
    }

    private void showSpeedDialog() {
        final Dialog dialog1 = new Dialog(context);
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.setContentView(R.layout.spin_edit_dialog);
        TextView heading = (TextView) dialog1.findViewById(R.id.toolbarheading);
        final EditText edtSpeed = (EditText) dialog1.findViewById(R.id.edtSpeed);
        edtSpeed.setHint(getString(R.string.enter_speed));
        edtSpeed.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)});
        edtSpeed.setInputType(InputType.TYPE_CLASS_NUMBER);
        ImageView base_toggle_icon = (ImageView) dialog1.findViewById(R.id.base_toggle_icon);
        TextView done_tv = dialog1.findViewById(R.id.done_tv);
        base_toggle_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1.dismiss();
            }
        });
        done_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String speed = edtSpeed.getText().toString().trim();
                if (speed.isEmpty())
                    Util.showAlertDialog(context, getString(R.string.alert), getString(R.string.please_enter_speed));
                else {
                    dialog1.dismiss();
                    speed_name = speed;
                    txtSpeed.setText(speed_name + " km/h");
                    // postEdittext.setText(postEdittext.getText().toString() + " " + speed_name);
                }
            }
        });
        heading.setText(getString(R.string.speed));

        dialog1.setCancelable(true);
        dialog1.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog1.getWindow().setGravity(Gravity.CENTER);
        dialog1.show();
    }

    private void showPositionTextDialog() {
        final Dialog dialog1 = new Dialog(context);
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.setContentView(R.layout.spin_edit_dialog);
        TextView heading = (TextView) dialog1.findViewById(R.id.toolbarheading);
        final EditText edtSpeed = (EditText) dialog1.findViewById(R.id.edtSpeed);
        edtSpeed.setHint(getString(R.string.enter_your_best_position));
        edtSpeed.setFilters(new InputFilter[]{new InputFilter.LengthFilter(7)});
        ImageView base_toggle_icon = (ImageView) dialog1.findViewById(R.id.base_toggle_icon);
        TextView done_tv = dialog1.findViewById(R.id.done_tv);
        base_toggle_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1.dismiss();
            }
        });
        done_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String speed = edtSpeed.getText().toString().trim();
                if (speed.isEmpty())
                    Util.showAlertDialog(context, getString(R.string.alert), getString(R.string.please_enter_speed));
                else {
                    dialog1.dismiss();
                    position_name = speed;
                    txtBestPosition.setText(position_name);
                }
            }
        });
        heading.setText(getString(R.string.best_position));


        dialog1.setCancelable(true);
        dialog1.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog1.getWindow().setGravity(Gravity.CENTER);
        dialog1.show();
    }

    /* Post Save webservice response */
    public void savePostWebserviceResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            String msg = jsonObject.getString(S.message);
            if (jsonObject.getInt(S.status) == S.adi_status_success) {
                // JSONObject dataJson = jsonObject.getJSONObject(S.data);
                Util.showAlertDialogWithAction(context, getString(R.string.alert), msg, S.postSave_api);
            } else
                Util.showAlertDialog(context, getString(R.string.alert), msg);


        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    /* Post save response callback */
    public void postSaveResponseCallback() {
        setResult(Activity.RESULT_OK);
        finish();
    }

    private void checkRequestPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                    {
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.CAMERA,
                    }, 1);
        } else {
            showImageDialog();
        }
    }

    private void showWeatherDialog() {
        final Dialog dialog1 = new Dialog(context);
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.setContentView(R.layout.spin_dialog);
        TextView heading = (TextView) dialog1.findViewById(R.id.toolbarheading);
        RecyclerView recyclerView = (RecyclerView) dialog1.findViewById(R.id.recycler_view);
        ImageView base_toggle_icon = (ImageView) dialog1.findViewById(R.id.base_toggle_icon);
        TextView done_tv = dialog1.findViewById(R.id.done_tv);
        done_tv.setVisibility(View.GONE);
        base_toggle_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1.dismiss();
            }
        });
        heading.setText(getString(R.string.select_weather));

        VehicleTypeSpinnerAdapter spinDialogAdapter = new VehicleTypeSpinnerAdapter(context, weather_array, spinnerSelectorInterface, I.WEATHER, dialog1);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(spinDialogAdapter);

        dialog1.setCancelable(true);
        dialog1.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog1.getWindow().setGravity(Gravity.CENTER);
        dialog1.show();
    }

    public void showImageDialog() {
        ChooseImagePickerOptionDialog chooseImagePickerOptionDialog = new ChooseImagePickerOptionDialog(context, onImagePickerDialogSelect);
        chooseImagePickerOptionDialog.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
        chooseImagePickerOptionDialog.show();
        chooseImagePickerOptionDialog.makeVisible();
        pagerBean = null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], int[] grantResults) {
        int i = 0;
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showImageDialog();
            } else {
                //mSelectImageClass = new SelectImageClass(mContext, getActivity(), "");
                // System.exit(0);
            }
        }
    }

    /* myFriendList webservice response */
    public void myFriendListWebserviceResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            String msg = jsonObject.getString(S.message);
            if (jsonObject.getInt(S.status) == S.adi_status_success) {
                JSONArray jsonArray = jsonObject.getJSONArray(S.data);
                mMyFriendsLists.clear();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    ProfileMyFriendsList profileMyFriendsList = new ProfileMyFriendsList();
                    profileMyFriendsList.setId(jsonObject1.getString(S.id));
                    profileMyFriendsList.setUserId(jsonObject1.getString(S.userId));
                    profileMyFriendsList.setStatus(jsonObject1.getString(S.status));

                    PersonalInfo personalInfo = new PersonalInfo();
                    JSONObject personalInfoJson = jsonObject1.getJSONObject(S.personalInfo);
                    personalInfo.setFirstName(personalInfoJson.getString(S.firstName_response));
                    personalInfo.setLastName(personalInfoJson.getString(S.lastName_response));
                    personalInfo.setVoucherCode(personalInfoJson.getString(S.voucherCode_response));
                    personalInfo.setDateOfBirth(personalInfoJson.getString(S.dateOfBirth_response));
                    personalInfo.setMobileNumber(personalInfoJson.getString(S.mobileNumber_response));
                    personalInfo.setImage(personalInfoJson.getString(S.image));
                    personalInfo.setCoverImage(personalInfoJson.getString(S.coverImage));
                    personalInfo.setLanguage(personalInfoJson.getString(S.language_response));
                    profileMyFriendsList.setPersonalInfo(personalInfo);

                    mMyFriendsLists.add(profileMyFriendsList);
                }
                spinDialogAdapter.notifyDataSetChanged();
                if (post_details == null)
                    showMyFriendsList();
                else {
                    spinDialogAdapter.addSelectedTagFriends(post_details.getDashboardCommentPostTagFriendsBeanses());
                    tagfriendspan = " " + spinDialogAdapter.getSelectedFriendsNames();
                    userNameTv.setText((Html.fromHtml(deafultSpna + feelingSpan + tagfriendspan)));
                }
            } else
                Util.showAlertDialog(context, getString(R.string.alert), msg);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    private void showMyFriendsList() {
        dialog1 = new Dialog(context);
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.setContentView(R.layout.spin_dialog);
        TextView heading = (TextView) dialog1.findViewById(R.id.toolbarheading);
        EditText etSearch = (EditText) dialog1.findViewById(R.id.etSearch);
        RecyclerView recyclerView = (RecyclerView) dialog1.findViewById(R.id.recycler_view);
        ImageView base_toggle_icon = (ImageView) dialog1.findViewById(R.id.base_toggle_icon);
        TextView done_tv = dialog1.findViewById(R.id.done_tv);
        etSearch.setVisibility(View.VISIBLE);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                spinDialogAdapter.filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        done_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tagfriendspan = " " + spinDialogAdapter.getSelectedFriendsNames();
                userNameTv.setText((Html.fromHtml(deafultSpna + feelingSpan + tagfriendspan)));
                // userNameTv.setText(addClickablePart(), TextView.BufferType.SPANNABLE);
                dialog1.dismiss();
            }
        });
        base_toggle_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1.dismiss();
            }
        });
        heading.setText(getString(R.string.select_friends));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(spinDialogAdapter);
        spinDialogAdapter.addMyFriendsListAll(mMyFriendsLists);
        dialog1.setCancelable(true);
        dialog1.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog1.getWindow().setGravity(Gravity.CENTER);
        dialog1.show();
    }

    @Override
    public void OnCameraSelect() {
        EasyImage.openCamera(this, REQUEST_CAMERA_PROFILE);
    }

    @Override
    public void OnGallerySelect() {/*
        Intent intent = new Intent(this, AlbumSelectActivity.class);
//set limit on number of images that can be selected, default is 10
        intent.putExtra(Constants.INTENT_EXTRA_LIMIT, 5);
        startActivityForResult(intent, SELECT_FILE_PROFILE);*/
        /*

        EasyImage.openGallery(this, SELECT_FILE_PROFILE, true);*/

        Intent intent = new Intent(this, Gallery.class);
        //Set the title
        intent.putExtra("title", "Select media");
        startActivityForResult(intent, SELECT_FILE_PROFILE);
    }

    @Override
    public void onVideoSelect() {
        // create new Intentwith with Standard Intent action that can be
        // sent to have the camera application capture an video and return it.
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

        // create a file to save the video
        videoFileUri = Util.getOutputMediaFileUri(I.MEDIA_TYPE_VIDEO, context);

        // set the image file name
        intent.putExtra(MediaStore.EXTRA_OUTPUT, videoFileUri);

        // set the video image quality to high
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);

        // set permission
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        // start the Video Capture Intent
        startActivityForResult(intent, I.CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == VEHICLE_ACTIVITY_RESULT_CODE) {
            if (resultCode == RESULT_OK) {
                vehicle_name = data.getStringExtra(S.vehiclename);
                vehicle_id = data.getStringExtra(S.vehicleId);

                txtSelectVehicleName.setText(vehicle_name);

                //postEdittext.setText(postEdittext.getText().toString() + " " + vehicle_name);

            }
        } else if (requestCode == TRACK_ACTIVITY_RESULT_CODE) {
            if (resultCode == RESULT_OK) {
                track_name = data.getStringExtra(S.trackname);
                track_id = data.getStringExtra(S.trackId);

                txtSelectTrackName.setText(track_name);
                //postEdittext.setText(postEdittext.getText().toString() + " " + track_name);

            }
        } else if (requestCode == SELECT_FILE_PROFILE) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> selectionResult = data.getStringArrayListExtra("result");
                int i0 = selectionResult.size();

                //  selectedImage.clear();

                for (int i = 0; i < selectionResult.size(); i++) {
                    PagerBean pagerBean = new PagerBean();
                    File file = new File(selectionResult.get(i));

                    pagerBean.setFile(file);
                    pagerBean.setEdit(false);
                    pagerBean.setFileName(file.getName());
                    pagerBean.setFileURl(Uri.fromFile(file).toString());
                    pagerBean.setFileType(Util.isImageOrVideoFile(file.getPath()), file.getPath(), context);
                    selectedImage.add(pagerBean);
                }

                setShowMediaFiles(selectedImage);

            }
        } else if (requestCode == I.CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null && data.getData() != null) {
                PagerBean pagerBean = new PagerBean();
                File file = new File(Util.getFilePathFromURI(context, data.getData()));

                pagerBean.setFile(file);
                pagerBean.setEdit(false);
                pagerBean.setFileName(file.getName());
                pagerBean.setFileURl(data.getData().toString());
                pagerBean.setFileType(Util.isImageOrVideoFile(file.getPath()), file.getPath(), context);
                selectedImage.add(pagerBean);
                setShowMediaFiles(selectedImage);
            } else {
                PagerBean pagerBean = new PagerBean();
                pagerBean.setEdit(false);
                File file = new File(Util.getFilePathFromURI(context, videoFileUri));

                pagerBean.setFile(file);
                pagerBean.setFileName(file.getName());
                pagerBean.setFileURl(videoFileUri.getPath());
                pagerBean.setFileType(Util.isImageOrVideoFile(file.getPath()), file.getPath(), context);
                selectedImage.add(pagerBean);
                setShowMediaFiles(selectedImage);
            }

            //Log.e("filepathvideo", data.getData().getPath());
        }

        EasyImage.handleActivityResult(requestCode, resultCode, data, AddPostActivity.this, new DefaultCallback() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                Log.e(TAG, e.toString());
            }

            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource imageSource, int i) {

                String imageAbsolutePath = imageFile.getAbsolutePath();
                String imageFileName = imageFile.getName();

            /*    pagerBean = new PagerBean(imageFile, imageAbsolutePath, imageFileName, false, false, "");
                imageList.add(pagerBean);
*/
                PagerBean pagerBean = new PagerBean();

                pagerBean.setFile(imageFile);
                pagerBean.setEdit(false);
                pagerBean.setFileName(imageFile.getName());
                pagerBean.setFileURl(imageFile.getPath());
                pagerBean.setFileType(S.image, imageFile.getPath(), context);
                selectedImage.add(pagerBean);
                setShowMediaFiles(selectedImage);
            }


        });
    }

    public void setRemoveImages(String id) {
        if (removeImage.isEmpty())
            removeImage = id;
        else
            removeImage = removeImage.concat("," + id);

        setLeftRight();
    }

    private void setLeftRight() {
        int imageSize = 0;
        imageSize = selectedImage.size() - 1;
        if (viewpager.getCurrentItem() == 0) {
            arraowleft_iv.setVisibility(View.GONE);
        } else if (imageSize > 1) {
            arraowleft_iv.setVisibility(View.VISIBLE);
        }

        if ((imageSize - 1) == viewpager.getCurrentItem()) {
            arraowright_iv.setVisibility(View.GONE);
        } else if (imageSize > 1) {
            arraowright_iv.setVisibility(View.VISIBLE);
        }
    }

    public void setShowMediaFiles(ArrayList<PagerBean> imageList) {
        customPagerAdapter = new AddPostPagerAdapter(context, imageList);
        viewpager.setAdapter(customPagerAdapter);

        if (imageList.size() == 1) {
            arraowleft_iv.setVisibility(View.GONE);
            arraowright_iv.setVisibility(View.GONE);
        } else {
            arraowleft_iv.setVisibility(View.GONE);
            arraowright_iv.setVisibility(View.VISIBLE);
        }
    }

    /*public void SetShowMediaFiles(ArrayList<PagerBean> imageList) {
        selectedImage.clear();
        selectedImage.addAll(imageList);
        if (selectedImage.size() == 1) {
            LinearLayout.LayoutParams prm1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            homeRowFeedImageIv1.setLayoutParams(prm1);

            LinearLayout.LayoutParams ll_prm = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            llFeedImage.setLayoutParams(ll_prm);

            homeImageLl.setVisibility(View.VISIBLE);
            llFeedImage.setVisibility(View.VISIBLE);
            llFeedImage2.setVisibility(View.GONE);

            homeRowFeedImageIv1.setVisibility(View.VISIBLE);
            homeRowFeedImageIv2.setVisibility(View.GONE);
            homeRowFeedImageIv3.setVisibility(View.GONE);
            homeRowFeedImageIv4.setVisibility(View.GONE);
            llInnerHomeRowFeedImageIv4.setVisibility(View.GONE);
            ivPoints.setVisibility(View.GONE);
            llAlpha.setVisibility(View.GONE);

            if (selectedImage.get(0).getFileType().equalsIgnoreCase(S.image)) {
                String decoded = Uri.fromFile(selectedImage.get(0).getFile()).toString();
                ImageLoader.getInstance().displayImage(decoded, homeRowFeedImageIv1);
            } else if (selectedImage.get(0).getFileType().equalsIgnoreCase(S.video))
                homeRowFeedImageIv1.setImageBitmap(selectedImage.get(0).getThumbnail());

        } else if (imageList.size() == 2) {
            LinearLayout.LayoutParams prm1 = new LinearLayout.LayoutParams(width / 2, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            homeRowFeedImageIv1.setLayoutParams(prm1);
            homeRowFeedImageIv3.setLayoutParams(prm1);

            *//*LinearLayout.LayoutParams ll_prm = new LinearLayout.LayoutParams(width, width / 2);
            llFeedImage.setLayoutParams(ll_prm);*//*

            homeImageLl.setVisibility(View.VISIBLE);
            llFeedImage.setVisibility(View.VISIBLE);
            llFeedImage2.setVisibility(View.VISIBLE);

            homeRowFeedImageIv1.setVisibility(View.VISIBLE);
            homeRowFeedImageIv2.setVisibility(View.GONE);
            homeRowFeedImageIv3.setVisibility(View.VISIBLE);
            homeRowFeedImageIv4.setVisibility(View.GONE);
            llInnerHomeRowFeedImageIv4.setVisibility(View.GONE);
            ivPoints.setVisibility(View.GONE);
            llAlpha.setVisibility(View.GONE);

            String type = selectedImage.get(0).getFileType();
            if (type.equalsIgnoreCase(S.image)) {
                String decoded = Uri.fromFile(selectedImage.get(0).getFile()).toString();
                ImageLoader.getInstance().displayImage(decoded, homeRowFeedImageIv1);
            } else if (type.equalsIgnoreCase(S.video))
                homeRowFeedImageIv1.setImageBitmap(selectedImage.get(0).getThumbnail());

            String type1 = selectedImage.get(1).getFileType();
            if (type1.equalsIgnoreCase(S.image)) {
                String decoded = Uri.fromFile(selectedImage.get(1).getFile()).toString();
                ImageLoader.getInstance().displayImage(decoded, homeRowFeedImageIv3);
            } else if (type1.equalsIgnoreCase(S.video))
                homeRowFeedImageIv3.setImageBitmap(selectedImage.get(1).getThumbnail());

         *//*   String decoded1 = Uri.decode(selectedImage.get(0).getFileURl());
            ImageLoader.getInstance().displayImage(decoded1, homeRowFeedImageIv1);*//*

           *//* String decoded2 = Uri.decode(selectedImage.get(1).getFileURl());
            ImageLoader.getInstance().displayImage(decoded2, homeRowFeedImageIv3);*//*

        } else if (imageList.size() == 3) {
            LinearLayout.LayoutParams prm1 = new LinearLayout.LayoutParams(width / 2, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            homeRowFeedImageIv3.setLayoutParams(prm1);
            llFeedImage.setLayoutParams(prm1);
            llFeedImage2.setLayoutParams(prm1);

            LinearLayout.LayoutParams prm = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 0.5f);
            homeRowFeedImageIv1.setLayoutParams(prm);
            homeRowFeedImageIv2.setLayoutParams(prm);

            homeImageLl.setVisibility(View.VISIBLE);
            llFeedImage.setVisibility(View.VISIBLE);
            llFeedImage2.setVisibility(View.VISIBLE);

            homeRowFeedImageIv1.setVisibility(View.VISIBLE);
            homeRowFeedImageIv2.setVisibility(View.VISIBLE);
            homeRowFeedImageIv3.setVisibility(View.VISIBLE);
            homeRowFeedImageIv4.setVisibility(View.GONE);
            llInnerHomeRowFeedImageIv4.setVisibility(View.GONE);
            ivPoints.setVisibility(View.GONE);
            llAlpha.setVisibility(View.GONE);

            String type = selectedImage.get(0).getFileType();
            if (type.equalsIgnoreCase(S.image)) {
                String decoded = Uri.fromFile(selectedImage.get(0).getFile()).toString();
                ImageLoader.getInstance().displayImage(decoded, homeRowFeedImageIv1);
            } else if (type.equalsIgnoreCase(S.video))
                homeRowFeedImageIv1.setImageBitmap(selectedImage.get(0).getThumbnail());

            String type1 = selectedImage.get(1).getFileType();
            if (type1.equalsIgnoreCase(S.image)) {
                String decoded = Uri.fromFile(selectedImage.get(1).getFile()).toString();
                ImageLoader.getInstance().displayImage(decoded, homeRowFeedImageIv2);
            } else if (type1.equalsIgnoreCase(S.video))
                homeRowFeedImageIv2.setImageBitmap(selectedImage.get(1).getThumbnail());

            String type2 = selectedImage.get(2).getFileType();
            if (type2.equalsIgnoreCase(S.image)) {
                String decoded = Uri.fromFile(selectedImage.get(2).getFile()).toString();
                ImageLoader.getInstance().displayImage(decoded, homeRowFeedImageIv3);
            } else if (type2.equalsIgnoreCase(S.video))
                homeRowFeedImageIv3.setImageBitmap(selectedImage.get(2).getThumbnail());

          *//*  String decoded1 = Uri.decode(selectedImage.get(0).getFileURl());
            ImageLoader.getInstance().displayImage(decoded1, homeRowFeedImageIv1);

            String decoded2 = Uri.decode(selectedImage.get(1).getFileURl());
            ImageLoader.getInstance().displayImage(decoded2, homeRowFeedImageIv2);

            String decoded3 = Uri.decode(selectedImage.get(2).getFileURl());
            ImageLoader.getInstance().displayImage(decoded3, homeRowFeedImageIv3);*//*

        } else if (imageList.size() == 4) {
            LinearLayout.LayoutParams prm1 = new LinearLayout.LayoutParams(width / 2, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            llFeedImage.setLayoutParams(prm1);
            llFeedImage2.setLayoutParams(prm1);

            LinearLayout.LayoutParams prm = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 0.5f);
            homeRowFeedImageIv1.setLayoutParams(prm);
            homeRowFeedImageIv2.setLayoutParams(prm);
            homeRowFeedImageIv3.setLayoutParams(prm);
            llInnerHomeRowFeedImageIv4.setLayoutParams(prm);

            homeImageLl.setVisibility(View.VISIBLE);
            llFeedImage.setVisibility(View.VISIBLE);
            llFeedImage2.setVisibility(View.VISIBLE);

            homeRowFeedImageIv1.setVisibility(View.VISIBLE);
            homeRowFeedImageIv2.setVisibility(View.VISIBLE);
            homeRowFeedImageIv3.setVisibility(View.VISIBLE);
            homeRowFeedImageIv4.setVisibility(View.VISIBLE);
            llInnerHomeRowFeedImageIv4.setVisibility(View.VISIBLE);
            ivPoints.setVisibility(View.GONE);
            llAlpha.setVisibility(View.GONE);

            String type = selectedImage.get(0).getFileType();
            if (type.equalsIgnoreCase(S.image)) {
                String decoded = Uri.fromFile(selectedImage.get(0).getFile()).toString();
                ImageLoader.getInstance().displayImage(decoded, homeRowFeedImageIv1);
            } else if (type.equalsIgnoreCase(S.video))
                homeRowFeedImageIv1.setImageBitmap(selectedImage.get(0).getThumbnail());

            String type1 = selectedImage.get(1).getFileType();
            if (type1.equalsIgnoreCase(S.image)) {
                String decoded = Uri.fromFile(selectedImage.get(1).getFile()).toString();
                ImageLoader.getInstance().displayImage(decoded, homeRowFeedImageIv2);
            } else if (type1.equalsIgnoreCase(S.video))
                homeRowFeedImageIv2.setImageBitmap(selectedImage.get(1).getThumbnail());

            String type2 = selectedImage.get(2).getFileType();
            if (type2.equalsIgnoreCase(S.image)) {
                String decoded = Uri.fromFile(selectedImage.get(2).getFile()).toString();
                ImageLoader.getInstance().displayImage(decoded, homeRowFeedImageIv3);
            } else if (type2.equalsIgnoreCase(S.video))
                homeRowFeedImageIv3.setImageBitmap(selectedImage.get(2).getThumbnail());

            String type3 = selectedImage.get(3).getFileType();
            if (type3.equalsIgnoreCase(S.image)) {
                String decoded = Uri.fromFile(selectedImage.get(3).getFile()).toString();
                ImageLoader.getInstance().displayImage(decoded, homeRowFeedImageIv4);
            } else if (type3.equalsIgnoreCase(S.video))
                homeRowFeedImageIv4.setImageBitmap(selectedImage.get(3).getThumbnail());

          *//*  String decoded1 = Uri.decode(selectedImage.get(0).getFileURl());
            ImageLoader.getInstance().displayImage(decoded1, homeRowFeedImageIv1);

            String decoded2 = Uri.decode(selectedImage.get(1).getFileURl());
            ImageLoader.getInstance().displayImage(decoded2, homeRowFeedImageIv2);

            String decoded3 = Uri.decode(selectedImage.get(2).getFileURl());
            ImageLoader.getInstance().displayImage(decoded3, homeRowFeedImageIv3);

            String decoded4 = Uri.decode(selectedImage.get(3).getFileURl());
            ImageLoader.getInstance().displayImage(decoded4, homeRowFeedImageIv4);*//*

        } else if (imageList.size() > 4) {
            LinearLayout.LayoutParams prm1 = new LinearLayout.LayoutParams(width / 2, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            llFeedImage.setLayoutParams(prm1);
            llFeedImage2.setLayoutParams(prm1);

            LinearLayout.LayoutParams prm = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 0.5f);
            homeRowFeedImageIv1.setLayoutParams(prm);
            homeRowFeedImageIv2.setLayoutParams(prm);
            homeRowFeedImageIv3.setLayoutParams(prm);
            llInnerHomeRowFeedImageIv4.setLayoutParams(prm);

            homeImageLl.setVisibility(View.VISIBLE);
            llFeedImage.setVisibility(View.VISIBLE);
            llFeedImage2.setVisibility(View.VISIBLE);

            homeRowFeedImageIv1.setVisibility(View.VISIBLE);
            homeRowFeedImageIv2.setVisibility(View.VISIBLE);
            homeRowFeedImageIv3.setVisibility(View.VISIBLE);
            homeRowFeedImageIv4.setVisibility(View.VISIBLE);
            ivPoints.setVisibility(View.VISIBLE);
            llAlpha.setVisibility(View.VISIBLE);

            ivPoints.setText("+" + (imageList.size() - 4));

            String type = selectedImage.get(0).getFileType();
            if (type.equalsIgnoreCase(S.image)) {
                String decoded = Uri.fromFile(selectedImage.get(0).getFile()).toString();
                ImageLoader.getInstance().displayImage(decoded, homeRowFeedImageIv1);
            } else if (type.equalsIgnoreCase(S.video))
                homeRowFeedImageIv1.setImageBitmap(selectedImage.get(0).getThumbnail());

            String type1 = selectedImage.get(1).getFileType();
            if (type1.equalsIgnoreCase(S.image)) {
                String decoded = Uri.fromFile(selectedImage.get(1).getFile()).toString();
                ImageLoader.getInstance().displayImage(decoded, homeRowFeedImageIv2);
            } else if (type1.equalsIgnoreCase(S.video))
                homeRowFeedImageIv2.setImageBitmap(selectedImage.get(1).getThumbnail());

            String type2 = selectedImage.get(2).getFileType();
            if (type2.equalsIgnoreCase(S.image)) {
                String decoded = Uri.fromFile(selectedImage.get(2).getFile()).toString();
                ImageLoader.getInstance().displayImage(decoded, homeRowFeedImageIv3);
            } else if (type2.equalsIgnoreCase(S.video))
                homeRowFeedImageIv3.setImageBitmap(selectedImage.get(2).getThumbnail());

            String type3 = selectedImage.get(3).getFileType();
            if (type3.equalsIgnoreCase(S.image)) {
                String decoded = Uri.fromFile(selectedImage.get(3).getFile()).toString();
                ImageLoader.getInstance().displayImage(decoded, homeRowFeedImageIv4);
            } else if (type3.equalsIgnoreCase(S.video))
                homeRowFeedImageIv4.setImageBitmap(selectedImage.get(3).getThumbnail());
*//*
            String decoded1 = Uri.decode(selectedImage.get(0).getFileURl());
            ImageLoader.getInstance().displayImage(decoded1, homeRowFeedImageIv1);

            String decoded2 = Uri.decode(selectedImage.get(1).getFileURl());
            ImageLoader.getInstance().displayImage(decoded2, homeRowFeedImageIv2);

            String decoded3 = Uri.decode(selectedImage.get(2).getFileURl());
            ImageLoader.getInstance().displayImage(decoded3, homeRowFeedImageIv3);

            String decoded4 = Uri.decode(selectedImage.get(3).getFileURl());
            ImageLoader.getInstance().displayImage(decoded4, homeRowFeedImageIv4);*//*

        } else {
            homeImageLl.setVisibility(View.GONE);
        }
    }*/

    public void showFeelingDialog(String string, ArrayList<BeanVehicleType> feeling_array, int vehicleBrand) {
        final Dialog dialog1 = new Dialog(context);
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.setContentView(R.layout.spin_dialog);
        TextView heading = (TextView) dialog1.findViewById(R.id.toolbarheading);
        RecyclerView recyclerView = (RecyclerView) dialog1.findViewById(R.id.recycler_view);
        ImageView base_toggle_icon = (ImageView) dialog1.findViewById(R.id.base_toggle_icon);
        TextView done_tv = dialog1.findViewById(R.id.done_tv);
        done_tv.setVisibility(View.GONE);
        base_toggle_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1.dismiss();
            }
        });
        heading.setText(string);

        VehicleTypeSpinnerAdapter spinDialogAdapter = new VehicleTypeSpinnerAdapter(context, feeling_array, spinnerSelectorInterface, vehicleBrand, dialog1);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(spinDialogAdapter);

        dialog1.setCancelable(true);
        dialog1.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog1.getWindow().setGravity(Gravity.CENTER);
        dialog1.show();
    }

    public void feelingListWebserviceResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            String msg = jsonObject.getString(S.message);
            if (jsonObject.getInt(S.status) == S.adi_status_success) {
                feeling_array.clear();
                JSONArray jsonArray = jsonObject.getJSONArray(S.data);
                for (int i = 0; i < jsonArray.length(); i++) {
                    BeanVehicleType beanFeelings = new BeanVehicleType();
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    beanFeelings.setId(jsonObject1.getString(S._id));
                    beanFeelings.setName(jsonObject1.getString(S.name));
                    beanFeelings.setUnicode(jsonObject1.getString(S.unicodes));
                    feeling_array.add(beanFeelings);
                }
            } else
                Util.showAlertDialog(context, getString(R.string.alert), msg);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    @Override
    public void vehicleTypeName(String value, String id, int type) {
    }

    @Override
    public void vehicleTypeName(String value, String id, int type, int pos) {
        if (type == I.FEELING) {
            feeling_name = value;
            feeling_id = id;
            feelingSpan = "<font color=#414141> - Feeling </font><font color=#bd2436>" +
                    Util.getEmoticon(Integer.parseInt(feeling_array.get(pos).getUnicode().replace("U+", "0x").substring(2), 16)) + " " + feeling_name + "</font>";
            userNameTv.setText(Html.fromHtml(deafultSpna + feelingSpan + tagfriendspan));
        } else if (type == I.WEATHER) {
            weather_name = value;
            txtWeather.setText(weather_name);
        }
    }
}
