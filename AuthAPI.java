package com.os.fastlap.data;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.os.fastlap.activity.AddPostActivity;
import com.os.fastlap.activity.AllUsersActivity;
import com.os.fastlap.activity.DashboardActivity;
import com.os.fastlap.activity.GalleryActivity;
import com.os.fastlap.activity.SettingMenuActivity;
import com.os.fastlap.activity.UploadLapTimeActivity;
import com.os.fastlap.activity.community.EventActivity;
import com.os.fastlap.activity.community.UserGroupsActivity;
import com.os.fastlap.activity.group.CreateGroupActivity;
import com.os.fastlap.activity.group.GroupProfileActivity;
import com.os.fastlap.activity.group.InviteGroupMemberActivity;
import com.os.fastlap.activity.mylaps.MyLapsActivity;
import com.os.fastlap.activity.mylaps.MyLapsRaceActivity;
import com.os.fastlap.activity.profile.FriendsProfileActivity;
import com.os.fastlap.beans.BeanAddAlbum;
import com.os.fastlap.beans.BeanAddGroup;
import com.os.fastlap.beans.BeanAlbum;
import com.os.fastlap.beans.BeanStep3Data;
import com.os.fastlap.beans.BeanTicketPurchase;
import com.os.fastlap.beans.BeanUploadData;
import com.os.fastlap.beans.PagerBean;
import com.os.fastlap.constant.FastLapApplication;
import com.os.fastlap.delegates.MyApiEndpointInterface;
import com.os.fastlap.util.MyProgressDialog;
import com.os.fastlap.util.MySharedPreferences;
import com.os.fastlap.util.Util;
import com.os.fastlap.util.constants.S;

import java.io.File;
import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * Created by anandj on 7/24/2017.
 */

public class AuthAPI extends BaseAPI {

    private MyProgressDialog mProgressDialog;
    private String TAG = AuthAPI.class.getSimpleName();

    // Parameterize  constructor for initialize progress dialog
    public AuthAPI(Context context) {
        mProgressDialog = new MyProgressDialog(context);
    }

    public void login(final Context context, String email_user, String password, String deviceType, String deviceToken, String deviceInfo_SERIAL, String deviceInfo_MODEL,
                      String deviceInfo_Manufacture, String deviceInfo_brand, String deviceInfo_versioncode, String deviceInfo_deviceType, String deviceInfo_deviceToken) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).loginUser(email_user, password, deviceType, deviceToken, deviceInfo_SERIAL,
                deviceInfo_MODEL, deviceInfo_Manufacture, deviceInfo_brand, deviceInfo_versioncode, deviceInfo_deviceType, deviceInfo_deviceToken).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.sign_in_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void signup(final Context context, String fName, String lName, String username, String email, String password, String mobileNumber, String dateOfBirth, String language,
                       String nationality, String location, String voucherCode,
                       String latitude, String longitude, String provider, String deviceToken) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).signupUser(fName, lName, username, email, password, mobileNumber, dateOfBirth, language, nationality, location
                , voucherCode, latitude, longitude, provider, deviceToken, S.user_type).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.sign_up_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void verifyOtp(final Context context, String id, String otp) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).verifyOtp(id, otp).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.verify_otp_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void socialLogin(final Context context, String fName, String lName, String username, String language, String voucherCode, String latitude, String longitude, String deviceType,
                            String deviceToken, String email, String socialId, String socialLoginType, String password, String profileImageURL, String deviceInfo_SERIAL, String deviceInfo_MODEL,
                            String deviceInfo_Manufacture, String deviceInfo_brand, String deviceInfo_versioncode, String deviceInfo_deviceType, String deviceInfo_deviceToken, String birthday, String cover, String location, String about) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).socialLogin(fName, lName, username, language, voucherCode, latitude, longitude, deviceType, deviceToken,
                email, socialId, socialLoginType, password, profileImageURL, deviceInfo_SERIAL, deviceInfo_MODEL, deviceInfo_Manufacture, deviceInfo_brand, deviceInfo_versioncode,
                deviceInfo_deviceType, deviceInfo_deviceToken, birthday, cover, location, about).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.social_social_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void forgotPassword(final Context context, String email) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).forgotPassword(email)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.forgot_password_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void logout(final Context context, String id, String device_token) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).logout(id, device_token)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.logout_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void resendOtp(final Context context, String id) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).resendOtp(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.resend_otp_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getNationality(final Context context) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getNationality()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.getNationality_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getVehicleType(final Context context) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getVehicleType()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.vehicleTypeList_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getClothBrand(final Context context, String user_id) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getClothBrand(user_id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.clothBrandList_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getVehicleBrand(final Context context, String type_id) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getVehicleBrand(type_id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.vehicleBrandList_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getVehicleModel(final Context context, String type_id, String brand_id) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getVehicleModel(type_id, brand_id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.vehicleModelList_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getVehicleVersion(final Context context, String type_id, String brand_id, String model_id) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getVehicleVersion(type_id, brand_id, model_id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.vehicleVersionList_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getVehicleYear(final Context context, String type_id, String brand_id, String model_id, String version_id) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getVehicleYear(type_id, brand_id, model_id, version_id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.vehicleYearList_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getVehicleYear1(final Context context) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getVehicleYear1()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.vehicleYear1List_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getVehicleTyreBrand(final Context context, String type_id) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getVehicleTyreBrand(type_id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        mProgressDialog.dismiss();
                        handleResponse(responseBodyResponse, S.vehicleTyreBrandList_api, context);
                        mProgressDialog.dismiss();

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getVehicleTyreModel(final Context context, String type_id, String brand_id) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getVehicleTyreModel(type_id, brand_id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.vehicleTyreModelList_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getStaticPageInfo(final Context context, String slug) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getStaticPage(slug)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.getstaticPage_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void changePassword(final Context context, String id, String password, String new_password) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).changePassword(id, password, new_password)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.changePassword_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void changeEmail(final Context context, String id, String email) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).changeEmail(id, email)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.changeEmailAddress, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void getVehicleList(final Context context, String user_id) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getVehicleList(user_id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.listUserVehicle_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void deleteVechicleItem(final Context context, String user_id, String userVehicleId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).removeUserVehicle(user_id, userVehicleId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.removeUserVehicle_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void userClothList(final Context context, String userId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).userClothList(userId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.userClothList_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void getClothModel(final Context context, String brand_id, String user_id) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getClothModel(user_id, brand_id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.clothModelList_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getClothColor(final Context context, String user_id) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getClothColor(user_id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.clothColorList_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void addCloth(final Context context, String clothName, String clothBrandID, String clothModelID, String clothYearID, String clothColorID, String userId, PagerBean images) {
        mProgressDialog.show();
        Observable<Response<ResponseBody>> call;
        RequestBody requestFileWall1;
        MultipartBody.Part firstImage = null;

        MyApiEndpointInterface myApiEndpointInterface = FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class);
        RequestBody bodyclothName = RequestBody.create(MediaType.parse("text/plain"), clothName);
        RequestBody bodyclothBrandID = RequestBody.create(MediaType.parse("text/plain"), clothBrandID);
        RequestBody bodyclothModelID = RequestBody.create(MediaType.parse("text/plain"), clothModelID);
        RequestBody bodyclothYearID = RequestBody.create(MediaType.parse("text/plain"), clothYearID);
        RequestBody bodyuserId = RequestBody.create(MediaType.parse("text/plain"), userId);
        RequestBody bodyclothColorID = RequestBody.create(MediaType.parse("text/plain"), clothColorID);

        if (images != null) {
            requestFileWall1 = RequestBody.create(MediaType.parse("multipart/form-data"), images.getFile());
            firstImage = MultipartBody.Part.createFormData(S.img1, images.getFileName(), requestFileWall1);
        }
        call = myApiEndpointInterface.addUserCloth(bodyclothName, bodyclothBrandID, bodyclothModelID, bodyclothYearID, bodyuserId, bodyclothColorID, firstImage);

        if (call != null) {
            call.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Response<ResponseBody>>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                        }

                        @Override
                        public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                            handleResponse(responseBodyResponse, S.userAddCloths_api, context);
                            mProgressDialog.dismiss();
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            mProgressDialog.dismiss();
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
        }
    }

    public void getClothYear(final Context context, String brand_id, String model_id, String user_id) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getClothYear(user_id, brand_id, model_id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.clothYearList_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void deleteClothItem(final Context context, String user_id, String userClothId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).removeUserCloth(user_id, userClothId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.removeUserCloth_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void addVehicle(final Context context, String vehicleTypeId, String vehicleBrandId, String vehicleModelId, String vehicleVersionId, String userId, String vehicleTyreBrandId,
                           String vehicleTyreModelId,
                           String vehicleYearId, String description, ArrayList<PagerBean> images) {
        mProgressDialog.show();
        Observable<Response<ResponseBody>> call = null;
        RequestBody requestFileWall1;

        MultipartBody.Part image[] = new MultipartBody.Part[images.size()];

        MyApiEndpointInterface myApiEndpointInterface = FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class);
        RequestBody bodyvehicleTypeId = RequestBody.create(MediaType.parse("text/plain"), vehicleTypeId);
        RequestBody bodyvehicleBrandId = RequestBody.create(MediaType.parse("text/plain"), vehicleBrandId);
        RequestBody bodyvehicleModelId = RequestBody.create(MediaType.parse("text/plain"), vehicleModelId);
        RequestBody bodyvehicleVersionId = RequestBody.create(MediaType.parse("text/plain"), vehicleVersionId);
        RequestBody bodyuserId = RequestBody.create(MediaType.parse("text/plain"), userId);
        RequestBody bodyvehicleTyreBrandId = RequestBody.create(MediaType.parse("text/plain"), vehicleTyreBrandId);
        RequestBody bodyvehicleTyreModelId = RequestBody.create(MediaType.parse("text/plain"), vehicleTyreModelId);
        RequestBody bodyvehicleYearId = RequestBody.create(MediaType.parse("text/plain"), vehicleYearId);
        RequestBody bodydescription = RequestBody.create(MediaType.parse("text/plain"), description);
        RequestBody bodyphotoCount = RequestBody.create(MediaType.parse("text/plain"), images.size() + "");

        for (int i = 0; i < images.size(); i++) {
            if (images.get(i).isNew()) {
                requestFileWall1 = RequestBody.create(MediaType.parse("multipart/form-data"), images.get(i).getFile());
                image[i] = MultipartBody.Part.createFormData(S.img + (i + 1), images.get(i).getFileName(), requestFileWall1);
            }
        }

        call = myApiEndpointInterface.addUserVehicle(bodyvehicleTypeId, bodyvehicleBrandId, bodyvehicleModelId, bodyvehicleVersionId, bodyuserId, bodyvehicleTyreBrandId,
                bodyvehicleTyreModelId, bodyvehicleYearId, bodydescription, bodyphotoCount, image);

        if (call != null) {
            call.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Response<ResponseBody>>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                        }

                        @Override
                        public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                            handleResponse(responseBodyResponse, S.addUserVehicle_api, context);
                            mProgressDialog.dismiss();
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            mProgressDialog.dismiss();
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
        }


    }

    public void editVehicleList(final Context context, String vehicleTypeId, String vehicleBrandId, String vehicleModelId, String vehicleVersionId, String userId, String vehicleTyreBrandId,
                                String vehicleTyreModelId, String vehicleYearId, String description, String userVehicleId, String removeImg, ArrayList<PagerBean> images) {
        mProgressDialog.show();
        Observable<Response<ResponseBody>> call = null;
        RequestBody requestFileWall1;

        MultipartBody.Part image[] = new MultipartBody.Part[images.size()];

        MyApiEndpointInterface myApiEndpointInterface = FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class);
        RequestBody bodyvehicleTypeId = RequestBody.create(MediaType.parse("text/plain"), vehicleTypeId);
        RequestBody bodyvehicleBrandId = RequestBody.create(MediaType.parse("text/plain"), vehicleBrandId);
        RequestBody bodyvehicleModelId = RequestBody.create(MediaType.parse("text/plain"), vehicleModelId);
        RequestBody bodyvehicleVersionId = RequestBody.create(MediaType.parse("text/plain"), vehicleVersionId);
        RequestBody bodyuserId = RequestBody.create(MediaType.parse("text/plain"), userId);
        RequestBody bodyvehicleTyreBrandId = RequestBody.create(MediaType.parse("text/plain"), vehicleTyreBrandId);
        RequestBody bodyvehicleTyreModelId = RequestBody.create(MediaType.parse("text/plain"), vehicleTyreModelId);
        RequestBody bodyvehicleYearId = RequestBody.create(MediaType.parse("text/plain"), vehicleYearId);
        RequestBody bodydescription = RequestBody.create(MediaType.parse("text/plain"), description);
        RequestBody bodyuserVehicleId = RequestBody.create(MediaType.parse("text/plain"), userVehicleId);
        RequestBody bodremoveImg = RequestBody.create(MediaType.parse("text/plain"), removeImg);
        RequestBody bodphotocount = RequestBody.create(MediaType.parse("text/plain"), images.size() + "");

        for (int i = 0; i < images.size(); i++) {
            if (images.get(i).isNew()) {
                requestFileWall1 = RequestBody.create(MediaType.parse("multipart/form-data"), images.get(i).getFile());
                image[i] = MultipartBody.Part.createFormData(S.img + (i + 1), images.get(i).getFileName(), requestFileWall1);

            }
        }

        call = myApiEndpointInterface.editUserVehicle(bodyvehicleTypeId, bodyvehicleBrandId, bodyvehicleModelId, bodyvehicleVersionId, bodyuserId,
                bodyvehicleTyreBrandId, bodyvehicleTyreModelId, bodyvehicleYearId, bodyuserVehicleId, bodydescription, bodremoveImg, bodphotocount, image);

        if (call != null) {
            call.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Response<ResponseBody>>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                        }

                        @Override
                        public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                            handleResponse(responseBodyResponse, S.editUserVehicle_api, context);
                            mProgressDialog.dismiss();
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            mProgressDialog.dismiss();
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
        }
    }


    public void sendContactUs(final Context context, String title, String message, String userId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).sendContactUs(userId, title, message)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.contactUs_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getUserAbout(final Context context, String userId, String anotherUserId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getUserAbout(userId, anotherUserId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.userAbout_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void updateAbout(final Context context, String location, String aboutDescritpion, String phone, String website, String userId, String language, String email,
                            ArrayList<PagerBean> family_photo_pagerBeanArrayList, int count, String removeString) {
        mProgressDialog.show();
        MultipartBody.Part images[] = new MultipartBody.Part[count];
        RequestBody requestFileWall1;

        for (int i = 1; i < family_photo_pagerBeanArrayList.size(); i++) {
            if (!family_photo_pagerBeanArrayList.get(i).isEdit()) {
                requestFileWall1 = RequestBody.create(MediaType.parse("multipart/form-data"), family_photo_pagerBeanArrayList.get(i).getFile());
                images[i - 1] = MultipartBody.Part.createFormData(S.img + i, family_photo_pagerBeanArrayList.get(i).getFileName(), requestFileWall1);
            }
        }
        RequestBody locationRequest = RequestBody.create(MediaType.parse("text/plain"), location);
        RequestBody aboutDescritpionRequest = RequestBody.create(MediaType.parse("text/plain"), aboutDescritpion);
        RequestBody phoneRequest = RequestBody.create(MediaType.parse("text/plain"), phone);
        RequestBody websiteRequest = RequestBody.create(MediaType.parse("text/plain"), website);
        RequestBody userIdRequest = RequestBody.create(MediaType.parse("text/plain"), userId);
        RequestBody languageRequest = RequestBody.create(MediaType.parse("text/plain"), language);
        RequestBody emailRequest = RequestBody.create(MediaType.parse("text/plain"), email);
        RequestBody imageCountRequest = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(count));
        RequestBody removeStringRequest = RequestBody.create(MediaType.parse("text/plain"), removeString);


        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).updateUserAbout(userIdRequest, locationRequest, aboutDescritpionRequest, phoneRequest,
                websiteRequest, languageRequest, emailRequest, imageCountRequest, removeStringRequest, images)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.updateAbout_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void updateInfo(final Context context, String fname, String lname, String username, String dob, String nationality, String city, String userId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getUpdateInfo(userId, fname, lname, username, dob, nationality, city)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.editGeneralInfo_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void setTimeSetting(final Context context, String userId, String timeformat) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).setTimeSetting(userId, timeformat)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.userUpdateTimeSystem_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void setMetricSetting(final Context context, String userId, String metrics) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).setMetricSetting(userId, metrics)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.userUpdateMetricSystem_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void setWeekStartDay(final Context context, String userId, String weekStartDay) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).setWeekStartDay(userId, weekStartDay)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.userUpdateWeekStartDay_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void userDeviceConnected(String userId, String deviceToken, final Context context) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).userDeviceConnected(userId, deviceToken)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.userDeviceConnected_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void deviceDisconnectById(final Context context, String deviceId, String userId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).deviceDisconnectById(userId, deviceId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.deviceDisconnectById_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void disconnectAllTv(final Context context, String userId, String deviceToken) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).deviceDisconnect(userId, deviceToken)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.deviceDisconnect_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void setAccountDeactivate(final Context context, String userId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).setAccountDeactivate(userId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.userUpdateProfileStatus_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getLanguage(final Context context) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getLanguage()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.getLanguage_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void changeFavTrackStatus(final Context context, String userId, String TrackId, String userTrackId, String status) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).changeFavTrackStatus(userId, TrackId, userTrackId, status)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.trackManageStatus_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getAppUser(final Context context, String userId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getAppUser(userId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.userListing_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void trackListingAll(final Context context, String userId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).trackListingAll(userId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.trackListingAll_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void myFriendList(final Context context, String userId, String anotherId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).myFriendList(userId, anotherId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        if (context instanceof FriendsProfileActivity)
                            handleResponse(responseBodyResponse, S.myFriendList_api, context);
                        else if (context instanceof SettingMenuActivity)
                            ((SettingMenuActivity) context).myFriendListWebserviceResponse(Util.convertRetrofitResponce(responseBodyResponse));
                        else if (context instanceof AddPostActivity)
                            ((AddPostActivity) context).myFriendListWebserviceResponse(Util.convertRetrofitResponce(responseBodyResponse));
                        else if (context instanceof CreateGroupActivity)
                            ((CreateGroupActivity) context).myFriendListWebserviceResponse(Util.convertRetrofitResponce(responseBodyResponse));
                        else if (context instanceof AllUsersActivity)
                            ((AllUsersActivity) context).myFriendListWebserviceResponse(Util.convertRetrofitResponce(responseBodyResponse));
                        else if (context instanceof InviteGroupMemberActivity)
                            ((InviteGroupMemberActivity) context).myFriendListWebserviceResponse(Util.convertRetrofitResponce(responseBodyResponse));
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void mutualFriends(final Context context, String userId, String anotherId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).mutualFriends(userId, anotherId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.mutualFriends_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getFeelings(final Context context) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getFeelings()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.feelingList_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void UpdateImage(final Context context, String userId, PagerBean pagerBean, int select_image_type) {
        mProgressDialog.show();
        Observable<Response<ResponseBody>> call;
        RequestBody requestFileWall1;
        MultipartBody.Part firstImage;

        MyApiEndpointInterface myApiEndpointInterface = FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class);

        RequestBody bodyuserId = RequestBody.create(MediaType.parse("text/plain"), userId);

        requestFileWall1 = RequestBody.create(MediaType.parse("multipart/form-data"), pagerBean.getFile());

        if (select_image_type == 0) {
            firstImage = MultipartBody.Part.createFormData(S.image, pagerBean.getFileName(), requestFileWall1);
        } else {
            firstImage = MultipartBody.Part.createFormData(S.coverImage, pagerBean.getFileName(), requestFileWall1);
        }

        call = myApiEndpointInterface.UpdateImage(bodyuserId, firstImage);

        if (call != null) {
            call.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Response<ResponseBody>>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                        }

                        @Override
                        public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                            handleResponse(responseBodyResponse, S.updateUserImg_api, context);
                            mProgressDialog.dismiss();
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            mProgressDialog.dismiss();
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
        }
    }

    public void myFriendBlockList(final Context context, String userId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).myFriendBlockList(userId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.myFriendBlockList_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void userBlockStatus(final Context context, String userFriendId, String status, String userId, String blockUserId, String userBlockId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).userBlockStatus(userFriendId, status, userId, blockUserId, userBlockId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.userBlockStatus_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void postSave(final Context context, String userId, String userVehicleId, String trackId, String position_text, String feelingId, String description,
                         ArrayList<PagerBean> family_photo_pagerBeanArrayList, String friendId, int thubnail, String speed, String weather) {
        mProgressDialog.show();
        Observable<Response<ResponseBody>> call;
        MultipartBody.Part images[] = new MultipartBody.Part[family_photo_pagerBeanArrayList.size() + thubnail];
        RequestBody requestFileWall1;
        int cntImg = 0, cntVideo = 0, cntImage = 0;
        for (int i = 0; i < family_photo_pagerBeanArrayList.size(); i++) {
            String name = "";
            requestFileWall1 = RequestBody.create(MediaType.parse("multipart/form-data"), family_photo_pagerBeanArrayList.get(i).getFile());
            if (family_photo_pagerBeanArrayList.get(i).getFileType().equalsIgnoreCase(S.image)) {
                cntImg++;
                name = S.img + (cntImg);
            } else {
                cntVideo++;
                name = S.video + (cntVideo);
                String thumb = S.thumb + (cntVideo);
                RequestBody requestFileWall1Thumb = RequestBody.create(MediaType.parse("multipart/form-data"), family_photo_pagerBeanArrayList.get(i).getThumbnailFile());
                images[cntImage] = MultipartBody.Part.createFormData(thumb, family_photo_pagerBeanArrayList.get(i).getThumbnailFile().getName(), requestFileWall1Thumb);
                cntImage++;
            }
            images[cntImage] = MultipartBody.Part.createFormData(name, family_photo_pagerBeanArrayList.get(i).getFileName(), requestFileWall1);
            cntImage++;
        }

        RequestBody bodyuserId = RequestBody.create(MediaType.parse("text/plain"), userId);
        RequestBody bodyuserVehicleId = RequestBody.create(MediaType.parse("text/plain"), userVehicleId);
        RequestBody bodytrackId = RequestBody.create(MediaType.parse("text/plain"), trackId);
        RequestBody bodyposition_text = RequestBody.create(MediaType.parse("text/plain"), position_text);
        RequestBody bodyfeelingId = RequestBody.create(MediaType.parse("text/plain"), feelingId);
        RequestBody bodyCount = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(cntImg));
        RequestBody bodyVideoCount = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(cntVideo));
        RequestBody bodydescription = RequestBody.create(MediaType.parse("text/plain"), description);
        RequestBody friendsId = RequestBody.create(MediaType.parse("text/plain"), friendId);
        RequestBody speedId = RequestBody.create(MediaType.parse("text/plain"), speed);
        RequestBody weatherId = RequestBody.create(MediaType.parse("text/plain"), weather);


        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).postSave(bodyuserId, bodyuserVehicleId, bodytrackId, bodyposition_text, bodyfeelingId, bodyCount,
                bodyVideoCount, bodydescription, friendsId, speedId, weatherId, images)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.postSave_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void updatePost(final Context context, String userId, String userVehicleId, String trackId, String position_text, String feelingId, String description,
                           ArrayList<PagerBean> family_photo_pagerBeanArrayList, String friendId, int thubnail, String speed, String weather, String removeImage, String removeFriendTag, String id) {
        mProgressDialog.show();
        Observable<Response<ResponseBody>> call;
        MultipartBody.Part images[] = new MultipartBody.Part[family_photo_pagerBeanArrayList.size() + thubnail];
        RequestBody requestFileWall1;
        int cntImg = 0, cntVideo = 0, cntImage = 0;
        for (int i = 0; i < family_photo_pagerBeanArrayList.size(); i++) {
            if (!family_photo_pagerBeanArrayList.get(i).isEdit()) {
                String name = "";
                requestFileWall1 = RequestBody.create(MediaType.parse("multipart/form-data"), family_photo_pagerBeanArrayList.get(i).getFile());
                if (family_photo_pagerBeanArrayList.get(i).getFileType().equalsIgnoreCase(S.image)) {
                    cntImg++;
                    name = S.img + (cntImg);
                } else {
                    cntVideo++;
                    name = S.video + (cntVideo);
                    String thumb = S.thumb + (cntVideo);
                    RequestBody requestFileWall1Thumb = RequestBody.create(MediaType.parse("multipart/form-data"), family_photo_pagerBeanArrayList.get(i).getThumbnailFile());
                    images[cntImage] = MultipartBody.Part.createFormData(thumb, family_photo_pagerBeanArrayList.get(i).getThumbnailFile().getName(), requestFileWall1Thumb);
                    cntImage++;
                }
                images[cntImage] = MultipartBody.Part.createFormData(name, family_photo_pagerBeanArrayList.get(i).getFileName(), requestFileWall1);
                cntImage++;
            }
        }

        RequestBody bodyuserId = RequestBody.create(MediaType.parse("text/plain"), userId);
        RequestBody bodyuserVehicleId = RequestBody.create(MediaType.parse("text/plain"), userVehicleId);
        RequestBody bodytrackId = RequestBody.create(MediaType.parse("text/plain"), trackId);
        RequestBody bodyposition_text = RequestBody.create(MediaType.parse("text/plain"), position_text);
        RequestBody bodyfeelingId = RequestBody.create(MediaType.parse("text/plain"), feelingId);
        RequestBody bodyCount = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(cntImg));
        RequestBody bodyVideoCount = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(cntVideo));
        RequestBody bodydescription = RequestBody.create(MediaType.parse("text/plain"), description);
        RequestBody friendsId = RequestBody.create(MediaType.parse("text/plain"), friendId);
        RequestBody speedId = RequestBody.create(MediaType.parse("text/plain"), speed);
        RequestBody weatherId = RequestBody.create(MediaType.parse("text/plain"), weather);
        RequestBody removeImageResponce = RequestBody.create(MediaType.parse("text/plain"), removeImage);
        RequestBody removeFrdTagResponce = RequestBody.create(MediaType.parse("text/plain"), removeFriendTag);
        RequestBody postId = RequestBody.create(MediaType.parse("text/plain"), id);


        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).updatePost(bodyuserId, bodyuserVehicleId, bodytrackId, bodyposition_text, bodyfeelingId, bodyCount,
                bodyVideoCount, bodydescription, friendsId, speedId, weatherId, removeFrdTagResponce, removeImageResponce, postId, images)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.updatePost_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void userFriend(final Context context, String fromUserId, String toUserId, String status, String userFriendId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).userFriend(fromUserId, toUserId, status, userFriendId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.userFriend_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void postLikeByUser(final Context context, String userId, String postId, String postLikeId, String status) {

        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).postLikeByUser(userId, postId, postLikeId, status)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.postLikeByUser_api, context);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void postComment(final Context context, String postId, String userId, String comment, String userPostCommentId, String edit) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).postComment(postId, userId, comment, userPostCommentId, edit)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.postComment_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void postCommentLike(final Context context, String postId, String userId, String userPostCommentId, String status) {

        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).postCommentLike(postId, userId, userPostCommentId, status)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.postCommentLike_api, context);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void postCommentListing(final Context context, String userId, String postId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).postCommentListing(userId, postId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.postCommentListing_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void postCommentRemove(final Context context, String userId, String userPostCommentId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).postCommentRemove(userId, userPostCommentId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.postCommentRemove_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void postListing(final Context context, String userId, boolean refreshstatus, String page) {
        if (refreshstatus)
            mProgressDialog.show();

        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).postListing(userId, page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.postListing_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getPostLikeUserList(final Context context, String userId, String post_id) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).postLikeUserList(userId, post_id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.postLikeUserListing_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void postCommentReply(final Context context, String postId, String userId, String comment, String userPostCommentId, String UserPostCommentReplyId, String edit) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).postCommentReply(postId, userId, comment, userPostCommentId, UserPostCommentReplyId, edit)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.postCommentReply_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getPostCommentReplyListing(final Context context, String userId, String postId, String userPostCommentId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).postCommentReplyListing(userId, postId, userPostCommentId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.postCommentReplyListing_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void getCommentLikeUserList(final Context context, String userId, String post_id, String postComment_id) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).commentLikeUserList(userId, post_id, postComment_id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.postCommentLikeUserListing_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getReplyLikeUserList(final Context context, String userId, String userPostCommentReplyId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).replyLikeUserList(userId, userPostCommentReplyId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.postCommentReplyLikeUserListing_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void postCommentReplyLike(final Context context, String userId, String postId, String userPostCommentId, String userPostCommentReplyId, String status) {

        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).postCommentReplyLike(userId, postId, userPostCommentId, userPostCommentReplyId, status)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.postCommentReplyLike_api, context);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void postReplyRemove(final Context context, String userId, String userPostCommentReplyId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).postReplyRemove(userId, userPostCommentReplyId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.postCommentReplyRemove_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void userNotificationList(final Context context, String userId, final boolean prograssStatus) {
        if (prograssStatus)
            mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).userNotificationList(userId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.userNotificationList_api, context);
                        if (prograssStatus)
                            mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void setNotificationStatusChanged(final Context mContext, String userId, String notification_id) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).notificationStatusChanged(userId, notification_id, "1")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.userChangeNotificationStatus_api, mContext);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void pendingRequest(final Context mContext, String userId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).pendingRequest(userId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.myFriendRequestList_api, mContext);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void setConfirmPendingRequest(final Context mContext, String fromUserId, String toUserId, String userFriendId, String status) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).confirmPendingRequest(fromUserId, toUserId, userFriendId, status)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.userFriend_api, mContext);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void searchUser(final Context mContext, String userId, String text) {
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).searchUser(userId, text)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.searchList_api, mContext);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getTrackInfo(final Context mContext, String trackId, String userId) {
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).trackInfo(userId, trackId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.trackInfo_api, mContext);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getTrackDiaryData(final Context mContext, String userId, String trackId) {
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).trackDiaryData(userId, trackId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.trackDiaryPost_api, mContext);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getTrackEvents(final Context context, String userId, String trackId, final int type) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).trackEvents(userId, trackId, type + "", MySharedPreferences.getPreferences(context, S.vehicleTypeId))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        ((EventActivity) context).EventWebserviceResponse(Util.convertRetrofitResponce(responseBodyResponse), type);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void createUserGroup(final Context context, String userId, BeanAddGroup beanAddGroup) {
        mProgressDialog.show();
        Observable<Response<ResponseBody>> call;
        RequestBody requestFileProfile;
        RequestBody requestFileCover;

        MultipartBody.Part profileImage;
        MultipartBody.Part coverImage;

        MyApiEndpointInterface myApiEndpointInterface = FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class);
        RequestBody bodyuserId = RequestBody.create(MediaType.parse("text/plain"), userId);
        RequestBody bodyVehicleTypeId = RequestBody.create(MediaType.parse("text/plain"), beanAddGroup.getVehicleTypeId());
        RequestBody bodyVehicleBrandID = RequestBody.create(MediaType.parse("text/plain"), beanAddGroup.getVehicleBrandId());
        RequestBody bodyTrackID = RequestBody.create(MediaType.parse("text/plain"), beanAddGroup.getTrackId());
        RequestBody bodyPrivacy = RequestBody.create(MediaType.parse("text/plain"), beanAddGroup.getPrivacy());
        RequestBody bodyGroupname = RequestBody.create(MediaType.parse("text/plain"), beanAddGroup.getGroupName());
        RequestBody bodyDesc = RequestBody.create(MediaType.parse("text/plain"), beanAddGroup.getGroupDesc());

        requestFileProfile = RequestBody.create(MediaType.parse("multipart/form-data"), beanAddGroup.getProfilePagerBean().getFile());
        profileImage = MultipartBody.Part.createFormData(S.image, beanAddGroup.getProfilePagerBean().getFileName(), requestFileProfile);

        requestFileCover = RequestBody.create(MediaType.parse("multipart/form-data"), beanAddGroup.getCoverPagerBean().getFile());
        coverImage = MultipartBody.Part.createFormData(S.coverImage, beanAddGroup.getCoverPagerBean().getFileName(), requestFileCover);

        call = myApiEndpointInterface.createUserGroup(bodyuserId, bodyVehicleTypeId, bodyVehicleBrandID, bodyTrackID, bodyPrivacy, bodyGroupname, bodyDesc, profileImage, coverImage);

        if (call != null) {
            call.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Response<ResponseBody>>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                        }

                        @Override
                        public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                            handleResponse(responseBodyResponse, S.createGroup_api, context);
                            mProgressDialog.dismiss();
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            mProgressDialog.dismiss();
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
        }
    }

    public void getVehicleTypeList(final Context context, String userId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).vehicleTypeList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.groupTypeList_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getGroupAbout(final Context context, String userId, String groupId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getGroupAbout(userId, groupId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.groupInfo_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void joinedGroup(final Context context, String userId, String groupId, String status, final int position) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).joinedGroup(userId, groupId, status)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        //  handleResponse(responseBodyResponse, S.userGroupRequest_api, context);
                        if (context instanceof UserGroupsActivity)
                            ((UserGroupsActivity) context).groupjoinWebserviceResponse(position, (Util.convertRetrofitResponce(responseBodyResponse)));
                        else if (context instanceof GroupProfileActivity)
                            ((GroupProfileActivity) context).groupjoinWebserviceResponse(position, (Util.convertRetrofitResponce(responseBodyResponse)));
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getGroupMembersList(final Context context, String userId, String groupId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getGroupMembersList(userId, groupId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.groupMemberList_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getGroupList(final Context context, String userId, int type) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getGroupList(userId, String.valueOf(type), MySharedPreferences.getPreferences(context, S.vehicleTypeId))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.groupListing_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getGroupEvents(final Context context, String userId, String groupId, final int type) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getGroupEvents(userId, groupId, type + "", MySharedPreferences.getPreferences(context, S.vehicleTypeId))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        // handleResponse(responseBodyResponse, S.groupEventList_api, context);
                        ((EventActivity) context).EventWebserviceResponse(Util.convertRetrofitResponce(responseBodyResponse), type);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getEventMembersList(final Context context, String userId, String eventId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getEventMembersList(userId, eventId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.eventCustomerList_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getEventInfo(final Context context, String userId, String eventId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getEventInfo(userId, eventId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.eventInfo_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getGroupGallery(final Context context, String userId, String groupId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getGroupGallery(userId, groupId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.galleryListing_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getTrackGallery(final Context context, String userId, String trackId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getTrackGallery(userId, trackId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.galleryListing_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getUserEvents(final Context context, String userId, final int type) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getUserEvents(userId, type + "", MySharedPreferences.getPreferences(context, S.vehicleTypeId))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        //  handleResponse(responseBodyResponse, S.userEventListing_api, context);
                        ((EventActivity) context).EventWebserviceResponse(Util.convertRetrofitResponce(responseBodyResponse), type);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getUserGallery(final Context context, String userId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getUserGallery(userId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.galleryListing_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getTrackTimeLap(final Context context, String userId, String trackId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getTrackTimeLap(userId, trackId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.myTrackLapList_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void setNotificationSettingsChanged(final Context context, String userId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).setNotificationSettingsChanged(userId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.updateNotification_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getMyLapsListing(final Context context, String userId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getMyLapsListingFriends(userId, MySharedPreferences.getPreferences(context, S.vehicleTypeId))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.lapsInTrackVersion, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getMyLapsListingFriends(final Context context, String userId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getMyLapsListingFriends(userId, MySharedPreferences.getPreferences(context, S.vehicleTypeId))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.lapsInTrackVersion, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getGalleryPhoto(final Context context, String userId, final int type) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getGalleryPhoto(userId, type + "",MySharedPreferences.getPreferences(context,S.vehicleTypeId))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        //handleResponse(responseBodyResponse, S.albumListingPhoto_api, context);
                        ((GalleryActivity) context).getGalleryPhotoWebserviceResponse((Util.convertRetrofitResponce(responseBodyResponse)), type);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getGalleryVideo(final Context context, String userId, final int type) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getGalleryVideo(userId, type + "",MySharedPreferences.getPreferences(context,S.vehicleTypeId))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        ((GalleryActivity) context).getGalleryVideoWebserviceResponse((Util.convertRetrofitResponce(responseBodyResponse)), type);
                        // handleResponse(responseBodyResponse, S.albumListingVideo_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getExploreData(final Context context, String userId, String groupTypeId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getExploreData(userId, groupTypeId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.upcomingEvent_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getWeatherDetail(final Context context, String userId, String trackLatPos, String trackLongPos) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getWeatherDetail(userId, trackLatPos, trackLongPos)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.getWeather_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getExploreByDate(final Context context, String userId, String dates, String groupTypeId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getExploreByDate(userId, dates, groupTypeId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.upcomingEventByDate_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getGroupDiaryData(final Context mContext, String userId, String groupId, String page) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getGroupDiaryData(userId, groupId, page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        mProgressDialog.dismiss();
                        handleResponse(responseBodyResponse, S.myGroupPostListing_api, mContext);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getPreferedTrackData(final Context context, String userId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getPreferedTrackData(userId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        mProgressDialog.dismiss();
                        handleResponse(responseBodyResponse, S.preferredTrack_api, context);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void myPostListing(final Context context, String userId, boolean refreshstatus, String page) {
        if (refreshstatus)
            mProgressDialog.show();

        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).myPostListing(userId, page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.myPostListing_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getAdvertisementData(final Context context, String userId) {
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getAdvertisementData(userId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.advertList_api, context);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getSubscriptionList(final Context context, String userId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getSubscriptionList(userId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        mProgressDialog.dismiss();
                        handleResponse(responseBodyResponse, S.subscriptionLists_api, context);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void postShareByUser(final Context context, String userId, String postId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).postShareByUser(userId, postId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        mProgressDialog.dismiss();
                        handleResponse(responseBodyResponse, S.postShared_api, context);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getVoucherDetail(final Context context, String voucherCode, String userId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getVoucherDetail(userId, voucherCode)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        mProgressDialog.dismiss();
                        handleResponse(responseBodyResponse, S.voucherConfirmation_api, context);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getSubscription(final Context context, String userId, String subscriptionPackageId, String voucherCodeId, String payAmount, String subscriptionEndDate, String tokenId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getSubscription(userId, subscriptionPackageId, voucherCodeId, payAmount, subscriptionEndDate, tokenId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        mProgressDialog.dismiss();
                        handleResponse(responseBodyResponse, S.userSubscriptions_api, context);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void myChatRooms(final Context context, String userId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).myChatRooms(userId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        mProgressDialog.dismiss();
                        handleResponse(responseBodyResponse, S.getChatFriendAndGroups_api, context);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void addCartAlbum(final Context context, String userId, String albumId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).addCartAlbum(userId, albumId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        mProgressDialog.dismiss();
                        handleResponse(responseBodyResponse, S.addCartAlbum_api, context);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void viewAlbumCart(final Context context, String userId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).viewAlbumCart(userId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        mProgressDialog.dismiss();
                        handleResponse(responseBodyResponse, S.viewAlbumCart_api, context);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void removeCartAlbum(final Context context, String userId, String albumId, String albumCartId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).removeCartAlbum(userId, albumId, albumCartId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        mProgressDialog.dismiss();
                        handleResponse(responseBodyResponse, S.removeCartAlbum_api, context);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void albumCheckout(final Context context, String userId, String totalAmountReq, String stripeToken) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).albumCheckout(userId, totalAmountReq, stripeToken)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        mProgressDialog.dismiss();
                        handleResponse(responseBodyResponse, S.albumCheckout_api, context);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getTrackDetailByLatLong(final Context context, String userId, double latitude, double longitude) {
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getTrackDetailByLatLong(userId, latitude + "", longitude + "")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.trackDetailByLatLong_api, context);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void setDefaultMapview(final Context context, String userId, String view) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).setDefaultMapview(userId, view)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        mProgressDialog.dismiss();
                        handleResponse(responseBodyResponse, S.userUpdateMapView_api, context);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getreportcauseList(final Context context) {
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getreportcauseList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        mProgressDialog.dismiss();
                        handleResponse(responseBodyResponse, S.userUpdateMapView_api, context);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void reportPost(final Context context, String userId, String post_id, String report) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).reportPost(userId, post_id, report)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        mProgressDialog.dismiss();
                        handleResponse(responseBodyResponse, S.reportAbuse_api, context);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getYoutubeUrlData(final Context context, String videoID, String apiKey, final int position) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getThirdPartyRequestQueue().create(MyApiEndpointInterface.class).getYoutubeUrlData("snippet", videoID, apiKey)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        mProgressDialog.dismiss();
                        if (context instanceof UploadLapTimeActivity)
                            ((UploadLapTimeActivity) context).getYoutubeURLData(Util.convertRetrofitResponce(responseBodyResponse), position);
                        else if (context instanceof MyLapsActivity)
                            ((MyLapsActivity) context).getYoutubeURLData(Util.convertRetrofitResponce(responseBodyResponse), position);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void uploadLapTimeData(final Context context, String userId, BeanUploadData beanUploadData, ArrayList<BeanStep3Data> step3DataList, int imageCount) {
        mProgressDialog.show();
        Observable<Response<ResponseBody>> call;
        RequestBody requestFile;
        MultipartBody.Part filePart;

        MultipartBody.Part images[] = new MultipartBody.Part[imageCount];
        RequestBody requestFileWall1;

        int c = 0;

        for (int i = 0; i < step3DataList.size(); i++) {
            for (int j = 0; j < step3DataList.get(i).getImageList().size(); j++) {
                if (!step3DataList.get(i).getImageList().get(j).isDefault()) {
                    if (!step3DataList.get(i).getImageList().get(j).isEdit()) {
                        requestFileWall1 = RequestBody.create(MediaType.parse("multipart/form-data"), step3DataList.get(i).getImageList().get(j).getFile());
                        images[c] = MultipartBody.Part.createFormData((S.session + "_" + step3DataList.get(i).getSessionId() + "_" + j), step3DataList.get(i).getImageList().get(j).getFileName(), requestFileWall1);
                    }
                }
            }
        }

        requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), new File(beanUploadData.getFilePath()));
        filePart = MultipartBody.Part.createFormData(S.csv_file, new File(beanUploadData.getFilePath()).getName(), requestFile);

        MyApiEndpointInterface myApiEndpointInterface = FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class);

        RequestBody bodyuserId = RequestBody.create(MediaType.parse("text/plain"), userId);
        RequestBody bodygpsDevices = RequestBody.create(MediaType.parse("text/plain"), beanUploadData.getGpsDevice());
        RequestBody bodysoftwareVersion = RequestBody.create(MediaType.parse("text/plain"), beanUploadData.getSoftwareVersion());
        RequestBody bodynumberOfLapsFound = RequestBody.create(MediaType.parse("text/plain"), beanUploadData.getNumberofLapsFound());
        RequestBody bodytrackId = RequestBody.create(MediaType.parse("text/plain"), beanUploadData.getTrackId());
        RequestBody bodytrackVersionId = RequestBody.create(MediaType.parse("text/plain"), beanUploadData.getTrackVersionId());
        RequestBody bodylapDate = RequestBody.create(MediaType.parse("text/plain"), beanUploadData.getDateofRecording());
        RequestBody bodyweather = RequestBody.create(MediaType.parse("text/plain"), beanUploadData.getLapWeather());
        RequestBody bodyvehicleTyreBrandId = RequestBody.create(MediaType.parse("text/plain"), beanUploadData.getLapTyreBrandId());
        RequestBody bodyvehicleTyreModelId = RequestBody.create(MediaType.parse("text/plain"), beanUploadData.getLapTyreModelId());
        RequestBody bodyvehicleId = RequestBody.create(MediaType.parse("text/plain"), beanUploadData.getVehicleId());
        RequestBody bodyphotoCount = RequestBody.create(MediaType.parse("text/plain"), imageCount + "");
        RequestBody bodyyoutubeURL = RequestBody.create(MediaType.parse("text/plain"), beanUploadData.getLapVideoURL());
        RequestBody bodydescription = RequestBody.create(MediaType.parse("text/plain"), beanUploadData.getLapNotes());
        RequestBody bodylapTime = RequestBody.create(MediaType.parse("text/plain"), beanUploadData.getLapTime());
        RequestBody bodyselectedValue = RequestBody.create(MediaType.parse("text/plain"), beanUploadData.getSelectedValue().toString());
        RequestBody bodyGroupTypeId = RequestBody.create(MediaType.parse("text/plain"), beanUploadData.getGroupTypeId());
        RequestBody bodyRentVehicle = RequestBody.create(MediaType.parse("text/plain"), "");

        call = myApiEndpointInterface.uploadLapTimeData(bodyuserId, bodygpsDevices, bodysoftwareVersion, bodynumberOfLapsFound, bodytrackId, bodytrackVersionId, bodylapDate, bodyweather,
                bodyvehicleTyreBrandId, bodyvehicleTyreModelId, bodyphotoCount, bodyyoutubeURL, bodydescription, bodylapTime, bodyvehicleId, bodyselectedValue, bodyGroupTypeId, bodyRentVehicle, filePart, images);

        if (call != null) {
            call.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Response<ResponseBody>>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                        }

                        @Override
                        public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                            handleResponse(responseBodyResponse, S.uploadLaptime, context);
                            mProgressDialog.dismiss();
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            mProgressDialog.dismiss();
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
        }
    }

    public void sendRequestForm(final Context context, String trackName, String address, String website, Uri filePath, String description, String userId, String email, String contact, String tracktypeId) {
        mProgressDialog.show();
        Observable<Response<ResponseBody>> call;
        RequestBody requestFile;
        MultipartBody.Part filePart;

        requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), filePath.getPath());
        filePart = MultipartBody.Part.createFormData(S.lapData, new File(filePath.getPath()).getName(), requestFile);

        MyApiEndpointInterface myApiEndpointInterface = FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class);

        RequestBody bodyuserId = RequestBody.create(MediaType.parse("text/plain"), userId);
        RequestBody bodytrackName = RequestBody.create(MediaType.parse("text/plain"), trackName);
        RequestBody bodyaddress = RequestBody.create(MediaType.parse("text/plain"), address);
        RequestBody bodywebsite = RequestBody.create(MediaType.parse("text/plain"), website);
        RequestBody bodydescription = RequestBody.create(MediaType.parse("text/plain"), description);
        RequestBody bodyemail = RequestBody.create(MediaType.parse("text/plain"), email);
        RequestBody bodycontact = RequestBody.create(MediaType.parse("text/plain"), contact);
        RequestBody bodytracktypeId = RequestBody.create(MediaType.parse("text/plain"), tracktypeId);

        call = myApiEndpointInterface.sendRequestForm(bodyuserId, bodytrackName, bodyaddress, bodywebsite, bodydescription, bodyemail, bodycontact, bodytracktypeId, filePart);

        if (call != null) {
            call.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Response<ResponseBody>>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                        }

                        @Override
                        public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                            handleResponse(responseBodyResponse, S.newTrackRequest, context);
                            mProgressDialog.dismiss();
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            mProgressDialog.dismiss();
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
        }
    }

    public void getDownloadFile(final Context context, String url, final String id) {
        mProgressDialog.show();

        final String filename = new File(url).getName();


        Call<ResponseBody> call = FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).downloadFileWithDynamicUrlSync(url);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                mProgressDialog.dismiss();
                if (response.isSuccessful()) {

                    Log.d(TAG, "server contacted and has file");

                    if (context instanceof MyLapsRaceActivity) {
                        // ((MyLapsRaceActivity) context).writeResponseBodyToDisk(response.body(), id, filename);
                        // Log.d(TAG, "file download was a success? " + writtenToDisk);
                    }
                    // boolean writtenToDisk = writeResponseBodyToDisk(response.body(),id);
                    //  Log.d(TAG, "file download was a success? " + writtenToDisk);
                } else {
                    Log.d(TAG, "server contact failed");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "error");
                mProgressDialog.show();
            }
        });
    }

    public void removeNotification(final Context context, String notificationId, String recieverId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).removeNotification(notificationId, recieverId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        mProgressDialog.dismiss();
                        handleResponse(responseBodyResponse, S.removeNotification_api, context);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void updateUploadLapTime(final Context context, String userId, String sessionId, String sessionIndex, String lapJsonArrayString, int size, String weather, String vehicletyreBrandId, String vehicletyreModelId, String notes, String youtubeUrl, ArrayList<PagerBean> pagerBeanArrayList) {
        mProgressDialog.show();
        Observable<Response<ResponseBody>> call;

        MultipartBody.Part images[] = new MultipartBody.Part[pagerBeanArrayList.size()];
        RequestBody requestFileWall1;

        for (int i = 0; i < pagerBeanArrayList.size(); i++) {
            if (!pagerBeanArrayList.get(i).isEdit()) {
                requestFileWall1 = RequestBody.create(MediaType.parse("multipart/form-data"), pagerBeanArrayList.get(i).getFile());
                images[i] = MultipartBody.Part.createFormData(S.img + (i + 1), pagerBeanArrayList.get(i).getFileName(), requestFileWall1);
            }
        }

        MyApiEndpointInterface myApiEndpointInterface = FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class);

        RequestBody bodyuserId = RequestBody.create(MediaType.parse("text/plain"), userId);
        RequestBody bodysessionId = RequestBody.create(MediaType.parse("text/plain"), sessionId);
        RequestBody bodyLapData = RequestBody.create(MediaType.parse("text/plain"), lapJsonArrayString);
        RequestBody bodyphotoCount = RequestBody.create(MediaType.parse("text/plain"), size + "");
        RequestBody bodyweather = RequestBody.create(MediaType.parse("text/plain"), weather);
        RequestBody bodyvehicletyreBrandId = RequestBody.create(MediaType.parse("text/plain"), vehicletyreBrandId);
        RequestBody bodyvehicletyreModelId = RequestBody.create(MediaType.parse("text/plain"), vehicletyreModelId);
        RequestBody bodynotes = RequestBody.create(MediaType.parse("text/plain"), notes);
        RequestBody bodyyoutubeUrl = RequestBody.create(MediaType.parse("text/plain"), youtubeUrl);
        RequestBody bodysessionIndex = RequestBody.create(MediaType.parse("text/plain"), sessionIndex);
        RequestBody bodylapTimeId = RequestBody.create(MediaType.parse("text/plain"), "");

        call = myApiEndpointInterface.updateUploadLapTimeData(bodyuserId, bodysessionId, bodysessionIndex, bodyLapData, bodyphotoCount, bodyweather, bodyvehicletyreBrandId, bodyvehicletyreModelId, bodynotes, bodyyoutubeUrl, bodylapTimeId, images);

        if (call != null) {
            call.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Response<ResponseBody>>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                        }

                        @Override
                        public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                            handleResponse(responseBodyResponse, S.updateSessions, context);
                            mProgressDialog.dismiss();
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            mProgressDialog.dismiss();
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
        }
    }

    public void postInfo(final Context context, String userId, String postId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).postInfo(userId, postId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.postInfo, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void uploadLaptimeFile(final Context context, String preferences, BeanUploadData beanUploadData) {
        mProgressDialog.show();
        Observable<Response<ResponseBody>> call;
        RequestBody requestFile;
        MultipartBody.Part filePart;

        requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), new File(beanUploadData.getFilePath()));
        filePart = MultipartBody.Part.createFormData(S.csv_file, new File(beanUploadData.getFilePath()).getName(), requestFile);

        MyApiEndpointInterface myApiEndpointInterface = FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class);

        call = myApiEndpointInterface.uploadLapTimeFile(filePart);

        if (call != null) {
            call.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Response<ResponseBody>>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                        }

                        @Override
                        public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                            handleResponse(responseBodyResponse, S.checkLapTimeCSV, context);
                            mProgressDialog.dismiss();
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            mProgressDialog.dismiss();
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
        }
    }

    public void userSubscribeList(final Context context, String userId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).userSubscribeList(userId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.userSubscribeList, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void albumLikeByUser(final Context context, String userId, String postId, String postLikeId, String status) {

        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).albumLikeByUser(userId, postId, status)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.albumLike, context);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void albumComment(final Context context, String postId, String userId, String comment, String userPostCommentId, String edit) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).albumComment(postId, userId, comment, userPostCommentId, edit)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.albumComment, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void albumCommentLike(final Context context, String postId, String userId, String userPostCommentId, String status) {

        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).albumCommentLike(postId, userId, userPostCommentId, status)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.albumCommentLike, context);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void albumCommentListing(final Context context, String userId, String postId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).albumCommentListing(userId, postId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.albumCommentListing, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void albumCommentRemove(final Context context, String userId, String userPostCommentId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).albumCommentRemove(userId, userPostCommentId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.albumCommentRemove_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getAlbumLikeUserList(final Context context, String userId, String post_id) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).albumLikeUserList(userId, post_id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.albumLikeUserListing, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getCommentAlbumLikeUserList(final Context context, String userId, String postComment_id) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getCommentAlbumLikeUserList(userId, postComment_id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.albumCommentLikeUserListing, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void albumCommentReply(final Context context, String postId, String userId, String comment, String userPostCommentId, String UserPostCommentReplyId, String edit) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).albumCommentReply(postId, userId, comment, userPostCommentId, UserPostCommentReplyId, edit)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.albumCommentReply, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getAlbumCommentReplyListing(final Context context, String userId, String postId, String userPostCommentId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).albumCommentReplyListing(userId, postId, userPostCommentId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.albumCommentReplyListing, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void albumCommentReplyLike(final Context context, String userId, String postId, String userPostCommentId, String userPostCommentReplyId, String status) {

        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).albumCommentReplyLike(userId, postId, userPostCommentId, userPostCommentReplyId, status)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.albumCommentReplyLike_api, context);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void albumReplyRemove(final Context context, String userId, String userPostCommentReplyId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).albumReplyRemove(userId, userPostCommentReplyId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.albumCommentReplyRemove, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getAlbumReplyLikeUserList(final Context context, String userId, String userPostCommentReplyId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).replyAlbumLikeUserList(userId, userPostCommentReplyId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.albumCommentReplyLikeUserListing, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void reportAlbum(final Context context, String userId, String post_id, String report) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).reportAlbum(userId, post_id, report)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        mProgressDialog.dismiss();
                        handleResponse(responseBodyResponse, S.reportAbuseAlbum, context);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void albumShareByUser(final Context context, String userId, String postId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).albumShareByUser(userId, postId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        mProgressDialog.dismiss();
                        handleResponse(responseBodyResponse, S.albumShared_api, context);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void addAlbum(final Context context, String userId, BeanAddAlbum beanAddAlbum) {
        mProgressDialog.show();
        Observable<Response<ResponseBody>> call;

        MultipartBody.Part images[] = new MultipartBody.Part[beanAddAlbum.getPhotoCount() + beanAddAlbum.getVideoCount() + beanAddAlbum.getVideoCount()];
        RequestBody requestFileWall1;
        int cntImg = 0, cntVideo = 0, cntImage = 0;
        for (int i = 1; i < beanAddAlbum.getMediaArrayList().size(); i++) {

            String name = "";
            requestFileWall1 = RequestBody.create(MediaType.parse("multipart/form-data"), beanAddAlbum.getMediaArrayList().get(i).getFile());
            if (beanAddAlbum.getMediaArrayList().get(i).getFileType().equalsIgnoreCase(S.image)) {
                cntImg++;
                name = S.img + (cntImg);
            } else {
                cntVideo++;
                name = S.video + (cntVideo);
                String thumb = S.thumb + (cntVideo);
                RequestBody requestFileWall1Thumb = RequestBody.create(MediaType.parse("multipart/form-data"), beanAddAlbum.getMediaArrayList().get(i).getThumbnailFile());
                images[cntImage] = MultipartBody.Part.createFormData(thumb, beanAddAlbum.getMediaArrayList().get(i).getThumbnailFile().getName(), requestFileWall1Thumb);
                cntImage++;
            }
            images[cntImage] = MultipartBody.Part.createFormData(name, beanAddAlbum.getMediaArrayList().get(i).getFileName(), requestFileWall1);
            cntImage++;

        }
        MyApiEndpointInterface myApiEndpointInterface = FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class);

        RequestBody bodyuserId = RequestBody.create(MediaType.parse("text/plain"), userId);
        RequestBody bodyalbumName = RequestBody.create(MediaType.parse("text/plain"), beanAddAlbum.getAlbumName());
        RequestBody bodyalbumDescription = RequestBody.create(MediaType.parse("text/plain"), beanAddAlbum.getDescription());
        RequestBody bodyphotoCount = RequestBody.create(MediaType.parse("text/plain"), beanAddAlbum.getPhotoCount() + "");
        RequestBody bodyvideoCount = RequestBody.create(MediaType.parse("text/plain"), beanAddAlbum.getVideoCount() + "");
        RequestBody bodydateofevent = RequestBody.create(MediaType.parse("text/plain"), beanAddAlbum.getDateOfEvent() + "");
        RequestBody bodytrackId = RequestBody.create(MediaType.parse("text/plain"), beanAddAlbum.getTrackId() + "");
        RequestBody bodygrouptypeId = RequestBody.create(MediaType.parse("text/plain"), beanAddAlbum.getGroupTypeId() + "");

        call = myApiEndpointInterface.addAlbum(bodyuserId, bodyalbumName, bodyalbumDescription, bodyphotoCount, bodyvideoCount, bodydateofevent, bodytrackId, bodygrouptypeId, images);

        if (call != null) {
            call.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Response<ResponseBody>>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                        }

                        @Override
                        public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                            handleResponse(responseBodyResponse, S.addalbum, context);
                            mProgressDialog.dismiss();
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            mProgressDialog.dismiss();
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
        }
    }

    public void editAlbum(final Context context, String userId, BeanAddAlbum beanAddAlbum, BeanAlbum beanEditAlbum, String remove_id) {
        mProgressDialog.show();
        Observable<Response<ResponseBody>> call;

        MultipartBody.Part images[] = new MultipartBody.Part[beanAddAlbum.getPhotoCount() + beanAddAlbum.getVideoCount() + beanAddAlbum.getVideoCount()];
        RequestBody requestFileWall1;
        int cntImg = 0, cntVideo = 0, cntImage = 0;
        for (int i = 1; i < beanAddAlbum.getMediaArrayList().size(); i++) {
            if (!beanAddAlbum.getMediaArrayList().get(i).isEdit()) {
                String name = "";
                requestFileWall1 = RequestBody.create(MediaType.parse("multipart/form-data"), beanAddAlbum.getMediaArrayList().get(i).getFile());
                if (beanAddAlbum.getMediaArrayList().get(i).getFileType().equalsIgnoreCase(S.image)) {
                    cntImg++;
                    name = S.img + (cntImg);
                } else {
                    cntVideo++;
                    name = S.video + (cntVideo);
                    String thumb = S.thumb + (cntVideo);
                    RequestBody requestFileWall1Thumb = RequestBody.create(MediaType.parse("multipart/form-data"), beanAddAlbum.getMediaArrayList().get(i).getThumbnailFile());
                    images[cntImage] = MultipartBody.Part.createFormData(thumb, beanAddAlbum.getMediaArrayList().get(i).getThumbnailFile().getName(), requestFileWall1Thumb);
                    cntImage++;
                }
                images[cntImage] = MultipartBody.Part.createFormData(name, beanAddAlbum.getMediaArrayList().get(i).getFileName(), requestFileWall1);
                cntImage++;
            }


        }
        MyApiEndpointInterface myApiEndpointInterface = FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class);
        RequestBody bodytrackId;
        RequestBody bodyuserId = RequestBody.create(MediaType.parse("text/plain"), userId);
        RequestBody bodyalbumName = RequestBody.create(MediaType.parse("text/plain"), beanAddAlbum.getAlbumName());
        RequestBody bodyalbumDescription = RequestBody.create(MediaType.parse("text/plain"), beanAddAlbum.getDescription());
        RequestBody bodyphotoCount = RequestBody.create(MediaType.parse("text/plain"), beanAddAlbum.getPhotoCount() + "");
        RequestBody bodyvideoCount = RequestBody.create(MediaType.parse("text/plain"), beanAddAlbum.getVideoCount() + "");
        RequestBody bodydateofevent = RequestBody.create(MediaType.parse("text/plain"), beanAddAlbum.getDateOfEvent());
        RequestBody bodygrouptypeId = RequestBody.create(MediaType.parse("text/plain"), beanAddAlbum.getGroupTypeId() + "");

        if (beanAddAlbum.getTrackId() != null)
            bodytrackId = RequestBody.create(MediaType.parse("text/plain"), beanAddAlbum.getTrackId());
        else {
            bodytrackId = RequestBody.create(MediaType.parse("text/plain"), "");
        }
        RequestBody bodyremoveId = RequestBody.create(MediaType.parse("text/plain"), remove_id);
        RequestBody bodyevent = RequestBody.create(MediaType.parse("text/plain"), beanEditAlbum.get_id());

        if (beanAddAlbum.getMediaArrayList().size() != 0)
            call = myApiEndpointInterface.editAlbum(bodyuserId, bodyalbumName, bodyalbumDescription, bodyphotoCount, bodyvideoCount, bodydateofevent, bodytrackId, bodyremoveId, bodyevent, bodygrouptypeId, images);
        else
            call = myApiEndpointInterface.editAlbum(bodyuserId, bodyalbumName, bodyalbumDescription, bodyphotoCount, bodyvideoCount, bodydateofevent, bodytrackId, bodyremoveId, bodyevent, bodygrouptypeId);

        if (call != null) {
            call.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Response<ResponseBody>>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                        }

                        @Override
                        public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                            handleResponse(responseBodyResponse, S.updateAlbum_api, context);
                            mProgressDialog.dismiss();
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            mProgressDialog.dismiss();
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
        }
    }

    public void getEventTicketPurchase(final Context context, String userId, BeanTicketPurchase beanTicketPurchase, String tokenId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getEventTicketPurchase(userId, beanTicketPurchase.getEventId(), tokenId, beanTicketPurchase.getQuantity(), beanTicketPurchase.getAmount(), "", beanTicketPurchase.getPresetsId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        mProgressDialog.dismiss();
                        handleResponse(responseBodyResponse, S.eventTicketPurchase, context);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void deletePost(final Context context, String user_id, String post_id) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).postRemove(user_id, post_id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        mProgressDialog.dismiss();
                        handleResponse(responseBodyResponse, S.postRemove_api, context);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void changeHomeTrackStatus(final Context context, String userId, String TrackId, String status) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).changeHomeTrackStatus(userId, TrackId, status)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.trackChangeHomeStatus_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getGeneralInformationData(final Context context, String userId) {
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).generalInformationData(userId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.generalInformationData, context);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void adLikeByUser(final Context context, String userId, String postId, String status) {

        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).adLikeByUser(userId, postId, status)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.advertLike, context);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void adComment(final Context context, String postId, String userId, String comment, String userPostCommentId, String edit) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).adComment(postId, userId, comment, userPostCommentId, edit)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.advertComment, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getAdLikeUserList(final Context context, String userId, String post_id) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).adLikeUserList(userId, post_id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.advertLikeUserListing, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void adCommentListing(final Context context, String userId, String postId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).adCommentListing(userId, postId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.advertCommentListing_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void adCommentRemove(final Context context, String userId, String userPostCommentId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).adCommentRemove(userId, userPostCommentId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.advertCommentRemove_api, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void deleteAbluum(final Context context, String albumId, String userId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).albumDelete(userId, albumId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.albumRemove, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void albumInfo(final Context context, String userId, String albumId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).albumInfo(userId, albumId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.albumInfo, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void sentInviteRequest(final Context context, String userId, String GroupId, String friendId, final int postion) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).sentInviteRequest(userId, GroupId, friendId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        if (context instanceof InviteGroupMemberActivity)
                            ((InviteGroupMemberActivity) context).getInviteFriend(postion, Util.convertRetrofitResponce(responseBodyResponse));
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void myFriendListforGroup(final Context context, String userId, String groupId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).myFriendListforGroup(userId, groupId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        if (context instanceof InviteGroupMemberActivity)
                            ((InviteGroupMemberActivity) context).myFriendListWebserviceResponse(Util.convertRetrofitResponce(responseBodyResponse));

                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getPendingGroupRequest(final Context context, String userId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getPendingGroupRequest(userId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.myPendingGroupRequest, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void sendGroupRequestAction(final Context context, String userId, String groupId, String status) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).sendGroupRequestAction(userId, groupId, status)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.userGroupJoin, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void readJsonFileData(final Context context, String url, final int color, final Long milisecond) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getDemoRequestQueue().create(MyApiEndpointInterface.class).readJsonFileData(url)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        if (context instanceof MyLapsRaceActivity) {
                            ((MyLapsRaceActivity) context).getUrlData(Util.convertRetrofitResponce(responseBodyResponse), color, milisecond);
                        }
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void makeGroupAdmin(final Context context, String userId, String groupId, String memberId, String status) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).makeGroupAdmin(userId, groupId, memberId, status)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.userAddToAdminList, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void removeFromGroup(final Context context, String userId, String groupId, String memberId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).removeFromGroup(userId, groupId, memberId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.userGroupMemberRemove, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getSessionsData(final Context context, String userId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getSessionsData(userId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.sessionData, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void deleteSessionImage(final Context context, String userId, String sessionId, String image_id) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).deleteSessionImage(userId, sessionId, image_id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.removeSessionImage, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void updateLap(final Context context, String userId, String lapData) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).updateLap(userId, lapData)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.updateLapStatus1, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void UpdateVehicleType(final Context context, String userId, String selectedId) {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).updatevehicleType(userId, selectedId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.userUpdateGroupType, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getTrackCondition(final Context context, String userId)
    {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getTrackCondition(userId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse) {
                        handleResponse(responseBodyResponse, S.trackConditionList, context);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getPreferedTrackDataFilter(final Context context, String userId, String vehicleTypeId, String vehicleBrandID, String fromVehicleYearId, String toVehicleYearId, String trackCondition, String trackId, final int position)
    {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getPreferedTrackDataFilter(userId,vehicleTypeId,vehicleBrandID,fromVehicleYearId,toVehicleYearId,trackCondition,trackId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse)
                    {
                        if (context instanceof DashboardActivity)
                            ((DashboardActivity) context).getPreferredTrackNewResponse(Util.convertRetrofitResponce(responseBodyResponse),position);
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getTrackProfile(final Context context, String userId, String track_id)
    {
        mProgressDialog.show();
        FastLapApplication.getInstance().getRequestQueue().create(MyApiEndpointInterface.class).getTrackProfile(userId,track_id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse)
                    {
                          handleResponse(responseBodyResponse, S.trackProfile, context);
                          mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
