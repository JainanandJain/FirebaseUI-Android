package com.os.fastlap.delegates;

import com.os.fastlap.util.constants.S;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;








/*
 * Created by anandj on 7/11/2017.
 */

public interface MyApiEndpointInterface {

    @FormUrlEncoded
    @POST(S.sign_in_api)
    Observable<Response<ResponseBody>> loginUser(@Field(S.email) String title, @Field(S.password) String pass, @Field(S.deviceType) String deviceType, @Field(S.deviceToken) String deviceToken,
                                                 @Field(S.deviceInfo_SERIAL) String deviceInfo_SERIAL, @Field(S.deviceInfo_MODEL) String deviceInfo_MODEL,
                                                 @Field(S.deviceInfo_Manufacture) String deviceInfo_Manufacture, @Field(S.deviceInfo_brand) String deviceInfo_brand,
                                                 @Field(S.deviceInfo_versioncode) String deviceInfo_versioncode, @Field(S.deviceInfo_deviceType) String deviceInfo_deviceType,
                                                 @Field(S.deviceInfo_deviceToken) String deviceInfo_deviceToken);

    @FormUrlEncoded
    @POST(S.sign_up_api)
    Observable<Response<ResponseBody>> signupUser(@Field(S.firstName) String first_name, @Field(S.lastName) String lName, @Field(S.username) String username, @Field(S.email) String email,
                                                  @Field(S.password) String password, @Field(S.mobileNumber) String mobileNumber, @Field(S.dateOfBirth) String dateOfBirth, @Field(S.language) String language,
                                                  @Field(S.nationality) String nationality, @Field(S.location) String location, @Field(S.voucherCode) String voucherCode, @Field(S.latitude) String latitude,
                                                  @Field(S.longitude) String longitude, @Field(S.deviceType) String provider, @Field(S.deviceToken) String deviceToken, @Field(S.role) String userType);

    @FormUrlEncoded
    @POST(S.verify_otp_api)
    Observable<Response<ResponseBody>> verifyOtp(@Field(S.id) String id, @Field(S.otp) String otp);

    @FormUrlEncoded
    @POST(S.social_social_api)
    Observable<Response<ResponseBody>> socialLogin(@Field(S.firstName) String first_name, @Field(S.lastName) String last_name, @Field(S.username) String username, @Field(S.language) String language,
                                                   @Field(S.voucherCode) String voucherCode, @Field(S.latitude) String latitude, @Field(S.longitude) String longitude, @Field(S.deviceType) String provider,
                                                   @Field(S.deviceToken) String device_token, @Field(S.email) String email, @Field(S.socialId) String device_info, @Field(S.socialLoginType) String socialLoginType,
                                                   @Field(S.password) String password, @Field(S.image) String profiel_image_url, @Field(S.deviceInfo_SERIAL) String deviceInfo_SERIAL,
                                                   @Field(S.deviceInfo_MODEL) String deviceInfo_MODEL, @Field(S.deviceInfo_Manufacture) String deviceInfo_Manufacture,
                                                   @Field(S.deviceInfo_brand) String deviceInfo_brand, @Field(S.deviceInfo_versioncode) String deviceInfo_versioncode,
                                                   @Field(S.deviceInfo_deviceType) String deviceInfo_deviceType, @Field(S.deviceInfo_deviceToken) String deviceInfo_deviceToken, @Field(S.personalInfodateOfBirth) String birthday, @Field(S.cover) String cover, @Field(S.location) String location, @Field(S.aboutDescritpion) String about);

    @FormUrlEncoded
    @POST(S.forgot_password_api)
    Observable<Response<ResponseBody>> forgotPassword(@Field(S.email) String email);

    @FormUrlEncoded
    @POST(S.logout_api)
    Observable<Response<ResponseBody>> logout(@Field(S.id) String id, @Field(S.deviceToken) String device_token);

    @FormUrlEncoded
    @POST(S.resend_otp_api)
    Observable<Response<ResponseBody>> resendOtp(@Field(S.id) String id);

    @POST(S.getNationality_api)
    Observable<Response<ResponseBody>> getNationality();

    @FormUrlEncoded
    @POST(S.getstaticPage_api)
    Observable<Response<ResponseBody>> getStaticPage(@Field(S.slug) String alug);

    @FormUrlEncoded
    @POST(S.changePassword_api)
    Observable<Response<ResponseBody>> changePassword(@Field(S.id) String id, @Field(S.password) String password, @Field(S.new_password) String new_password);

    @GET(S.vehicleTypeList_api)
    Observable<Response<ResponseBody>> getVehicleType();

    @FormUrlEncoded
    @POST(S.vehicleBrandList_api)
    Observable<Response<ResponseBody>> getVehicleBrand(@Field(S.vehicleTypeId) String vehicle_id);

    @FormUrlEncoded
    @POST(S.vehicleModelList_api)
    Observable<Response<ResponseBody>> getVehicleModel(@Field(S.vehicleTypeId) String vehicle_id, @Field(S.vehicleBrandId) String vehicle_brand_id);

    @FormUrlEncoded
    @POST(S.vehicleVersionList_api)
    Observable<Response<ResponseBody>> getVehicleVersion(@Field(S.vehicleTypeId) String vehicle_id, @Field(S.vehicleBrandId) String vehicle_brand_id, @Field(S.vehicleModelId) String vehicle_model_id);

    @FormUrlEncoded
    @POST(S.vehicleYearList_api)
    Observable<Response<ResponseBody>> getVehicleYear(@Field(S.vehicleTypeId) String vehicle_id, @Field(S.vehicleBrandId) String vehicle_brand_id, @Field(S.vehicleModelId) String vehicle_model_id,
                                                      @Field(S.vehicleVersionId) String vehicle_version_id);

    @GET(S.vehicleYear1List_api)
    Observable<Response<ResponseBody>> getVehicleYear1();

    @FormUrlEncoded
    @POST(S.vehicleTyreBrandList_api)
    Observable<Response<ResponseBody>> getVehicleTyreBrand(@Field(S.vehicleTypeId) String vehicle_id);

    @FormUrlEncoded
    @POST(S.vehicleTyreModelList_api)
    Observable<Response<ResponseBody>> getVehicleTyreModel(@Field(S.vehicleTypeId) String vehicle_id, @Field(S.vehicleTyreBrandId) String vehicle_tyre_brandid);

    @FormUrlEncoded
    @POST(S.listUserVehicle_api)
    Observable<Response<ResponseBody>> getVehicleList(@Field(S.userId) String user_id);

    @FormUrlEncoded
    @POST(S.removeUserVehicle_api)
    Observable<Response<ResponseBody>> removeUserVehicle(@Field(S.userId) String user_id, @Field(S.userVehicleId) String userVehicleId);

    @FormUrlEncoded
    @POST(S.userClothList_api)
    Observable<Response<ResponseBody>> userClothList(@Field(S.userId) String userId);

    @FormUrlEncoded
    @POST(S.clothBrandList_api)
    Observable<Response<ResponseBody>> getClothBrand(@Field(S.userId) String user_id);

    @FormUrlEncoded
    @POST(S.clothModelList_api)
    Observable<Response<ResponseBody>> getClothModel(@Field(S.userId) String user_id, @Field(S.clothBrandId) String brand_id);

    @FormUrlEncoded
    @POST(S.clothColorList_api)
    Observable<Response<ResponseBody>> getClothColor(@Field(S.userId) String user_id);

    @Multipart
    @POST(S.userAddCloths_api)
    Observable<Response<ResponseBody>> addUserCloth(@Part(S.clothName) RequestBody bodyclothName, @Part(S.clothBrandId) RequestBody bodyclothBrandID, @Part(S.clothModelName) RequestBody bodyclothModelID,
                                                    @Part(S.clothYearName) RequestBody bodyclothYearID, @Part(S.userId) RequestBody bodyuserId, @Part(S.clothColorName) RequestBody bodyclothColorID,
                                                    @Part MultipartBody.Part firstImage);

    @FormUrlEncoded
    @POST(S.clothYearList_api)
    Observable<Response<ResponseBody>> getClothYear(@Field(S.userId) String user_id, @Field(S.clothBrandId) String brand_id, @Field(S.clothModelId) String model_id);

    @FormUrlEncoded
    @POST(S.removeUserCloth_api)
    Observable<Response<ResponseBody>> removeUserCloth(@Field(S.userId) String user_id, @Field(S.userClothId) String userClothId);

    @Multipart
    @POST(S.addUserVehicle_api)
    Observable<Response<ResponseBody>> addUserVehicle(@Part(S.vehicleTypeId) RequestBody vehicleTypeId, @Part(S.vehicleBrandId) RequestBody vehicleBrandId, @Part(S.vehicleModelId) RequestBody vehicleModelId,
                                                      @Part(S.vehicleVersionId) RequestBody vehicleVersionId, @Part(S.userId) RequestBody userId, @Part(S.vehicleTyreBrandId) RequestBody vehicleTyreBrandId,
                                                      @Part(S.vehicleTyreModelId) RequestBody vehicleTyreModelId, @Part(S.vehicleYearId) RequestBody vehicleYearId, @Part(S.description) RequestBody description,@Part(S.photoCount) RequestBody bodyphotoCount,@Part MultipartBody.Part... images);

    @Multipart
    @POST(S.editUserVehicle_api)
    Observable<Response<ResponseBody>> editUserVehicle(@Part(S.vehicleTypeId) RequestBody vehicleTypeId, @Part(S.vehicleBrandId) RequestBody vehicleBrandId, @Part(S.vehicleModelId) RequestBody vehicleModelId,
                                                       @Part(S.vehicleVersionId) RequestBody vehicleVersionId, @Part(S.userId) RequestBody userId, @Part(S.vehicleTyreBrandId) RequestBody vehicleTyreBrandId,
                                                       @Part(S.vehicleTyreModelId) RequestBody vehicleTyreModelId, @Part(S.vehicleYearId) RequestBody vehicleYearId,
                                                       @Part(S.userVehicleId) RequestBody userVehicleId, @Part(S.description) RequestBody description, @Part(S.removeImage) RequestBody removeImage,@Part(S.photoCount) RequestBody bodyphotoCount,@Part MultipartBody.Part... images);


    @FormUrlEncoded
    @POST(S.contactUs_api)
    Observable<Response<ResponseBody>> sendContactUs(@Field(S.userId) String user_id, @Field(S.title) String title, @Field(S.message) String message);

    @FormUrlEncoded
    @POST(S.userAbout_api)
    Observable<Response<ResponseBody>> getUserAbout(@Field(S.userId) String user_id,@Field(S.anotherUserId) String anotherUserId);

    @Multipart
    @POST(S.updateAbout_api)
    Observable<Response<ResponseBody>> updateUserAbout(@Part(S.userId) RequestBody user_id, @Part(S.location_response) RequestBody location, @Part(S.aboutDescritpion) RequestBody aboutDescritpion,
                                                       @Part(S.phone) RequestBody phone, @Part(S.website) RequestBody website, @Part(S.language_response) RequestBody language,
                                                       @Part(S.email) RequestBody email, @Part(S.imgCount) RequestBody imgCount, @Part(S.removeFamilyImg) RequestBody removeFamilyImg,
                                                       @Part MultipartBody.Part... file);

    @FormUrlEncoded
    @POST(S.editGeneralInfo_api)
    Observable<Response<ResponseBody>> getUpdateInfo(@Field(S.userId) String userId, @Field(S.firstName) String fname, @Field(S.lastName) String lname, @Field(S.username) String username,
                                                     @Field(S.dateOfBirth) String dob, @Field(S.nationality) String nationality, @Field(S.location) String city);

    @FormUrlEncoded
    @POST(S.userUpdateTimeSystem_api)
    Observable<Response<ResponseBody>> setTimeSetting(@Field(S.userId) String userId, @Field(S.timeSystem) String timeformat);

    @FormUrlEncoded
    @POST(S.userUpdateMetricSystem_api)
    Observable<Response<ResponseBody>> setMetricSetting(@Field(S.userId) String userId, @Field(S.metrics) String metrics);

    @FormUrlEncoded
    @POST(S.userUpdateWeekStartDay_api)
    Observable<Response<ResponseBody>> setWeekStartDay(@Field(S.userId) String userId, @Field(S.weekStartDay) String weekStartDay);

    @FormUrlEncoded
    @POST(S.userDeviceConnected_api)
    Observable<Response<ResponseBody>> userDeviceConnected(@Field(S.userId) String user_id, @Field(S.deviceToken) String deviceToken);

    @FormUrlEncoded
    @POST(S.deviceDisconnectById_api)
    Observable<Response<ResponseBody>> deviceDisconnectById(@Field(S.userId) String user_id, @Field(S.deviceId) String deviceId);

    @FormUrlEncoded
    @POST(S.deviceDisconnect_api)
    Observable<Response<ResponseBody>> deviceDisconnect(@Field(S.userId) String user_id, @Field(S.deviceToken) String deviceToken);

    @FormUrlEncoded
    @POST(S.userUpdateProfileStatus_api)
    Observable<Response<ResponseBody>> setAccountDeactivate(@Field(S.userId) String userId);

    @GET(S.getLanguage_api)
    Observable<Response<ResponseBody>> getLanguage();

    @FormUrlEncoded
    @POST(S.trackManageStatus_api)
    Observable<Response<ResponseBody>> changeFavTrackStatus(@Field(S.userId) String userId, @Field(S.TrackId) String trackId, @Field(S.userTrackId) String userTrackId, @Field(S.status) String status);

    @FormUrlEncoded
    @POST(S.userListing_api)
    Observable<Response<ResponseBody>> getAppUser(@Field(S.userId) String userId);

    @FormUrlEncoded
    @POST(S.trackListingAll_api)
    Observable<Response<ResponseBody>> trackListingAll(@Field(S.userId) String userId);

    @FormUrlEncoded
    @POST(S.myFriendList_api)
    Observable<Response<ResponseBody>> myFriendList(@Field(S.userId) String userId, @Field(S.anotherUserId) String anotherId);

    @FormUrlEncoded
    @POST(S.myFriendList_api)
    Observable<Response<ResponseBody>> mutualFriends(@Field(S.userId) String userId, @Field(S.anotherId) String anotherId);

    @GET(S.feelingList_api)
    Observable<Response<ResponseBody>> getFeelings();

    @Multipart
    @POST(S.updateUserImg_api)
    Observable<Response<ResponseBody>> UpdateImage(@Part(S.userId) RequestBody bodyuserId, @Part MultipartBody.Part firstImage);

    @FormUrlEncoded
    @POST(S.myFriendBlockList_api)
    Observable<Response<ResponseBody>> myFriendBlockList(@Field(S.userId) String userId);

    @FormUrlEncoded
    @POST(S.userBlockStatus_api)
    Observable<Response<ResponseBody>> userBlockStatus(@Field(S.userFriendId) String userFriendId, @Field(S.status) String status, @Field(S.userId) String userId, @Field(S.blockUserId) String blockUserId,
                                                       @Field(S.userBlockId) String userBlockId);

    @Multipart
    @POST(S.postSave_api)
    Observable<Response<ResponseBody>> postSave(@Part(S.userId) RequestBody user_id, @Part(S.userVehicleId) RequestBody userVehicleId, @Part(S.trackId) RequestBody trackId,
                                                @Part(S.position_text) RequestBody position_text, @Part(S.feelingId) RequestBody feelingId, @Part(S.photoCount) RequestBody photoCount,
                                                @Part(S.videoCount) RequestBody videoCount, @Part(S.description) RequestBody description, @Part(S.friendId) RequestBody friendId, @Part(S.speed) RequestBody speed, @Part(S.weather) RequestBody weather, @Part MultipartBody.Part... imageVideo);


    @FormUrlEncoded
    @POST(S.userFriend_api)
    Observable<Response<ResponseBody>> userFriend(@Field(S.fromUserId) String fromUserId, @Field(S.toUserId) String toUserId, @Field(S.status) String status, @Field(S.userFriendId) String userFriendId);

    @FormUrlEncoded
    @POST(S.postLikeByUser_api)
    Observable<Response<ResponseBody>> postLikeByUser(@Field(S.userId) String userId, @Field(S.postId) String postId, @Field(S.postLikeId) String postLikeId, @Field(S.status) String status);

    @FormUrlEncoded
    @POST(S.postComment_api)
    Observable<Response<ResponseBody>> postComment(@Field(S.postId) String postId, @Field(S.userId) String userId, @Field(S.comment) String comment, @Field(S.userPostCommentId) String userPostCommentId, @Field(S.edit) String edit);

    @FormUrlEncoded
    @POST(S.postCommentLike_api)
    Observable<Response<ResponseBody>> postCommentLike(@Field(S.postId) String postId, @Field(S.userId) String userId, @Field(S.userPostCommentId) String userPostCommentId, @Field(S.status) String status);

    @FormUrlEncoded
    @POST(S.postCommentListing_api)
    Observable<Response<ResponseBody>> postCommentListing(@Field(S.userId) String userId, @Field(S.postId) String postId);

    @FormUrlEncoded
    @POST(S.postCommentRemove_api)
    Observable<Response<ResponseBody>> postCommentRemove(@Field(S.userId) String userId, @Field(S.userPostCommentId) String userPostCommentId);

    @FormUrlEncoded
    @POST(S.postListing_api)
    Observable<Response<ResponseBody>> postListing(@Field(S.userId) String userId, @Field(S.page) String page);

    @FormUrlEncoded
    @POST(S.postLikeUserListing_api)
    Observable<Response<ResponseBody>> postLikeUserList(@Field(S.userId) String userId, @Field(S.postId) String post_id);

    @FormUrlEncoded
    @POST(S.postCommentReply_api)
    Observable<Response<ResponseBody>> postCommentReply(@Field(S.postId) String postId, @Field(S.userId) String userId, @Field(S.comment) String comment, @Field(S.userPostCommentId) String userPostCommentId, @Field(S.UserPostCommentReplyId) String userPostCommentReplyId, @Field(S.edit) String edit);

    @FormUrlEncoded
    @POST(S.postCommentReplyListing_api)
    Observable<Response<ResponseBody>> postCommentReplyListing(@Field(S.userId) String userId, @Field(S.postId) String postId, @Field(S.userPostCommentId) String userPostCommentId);

    @FormUrlEncoded
    @POST(S.postCommentLikeUserListing_api)
    Observable<Response<ResponseBody>> commentLikeUserList(@Field(S.userId) String userId, @Field(S.postId) String post_id, @Field(S.userPostCommentId) String postComment_id);

    @FormUrlEncoded
    @POST(S.postCommentReplyLikeUserListing_api)
    Observable<Response<ResponseBody>> replyLikeUserList(@Field(S.userId) String userId, @Field(S.userPostCommentReplyId) String userPostCommentReplyId);

    @FormUrlEncoded
    @POST(S.postCommentReplyLike_api)
    Observable<Response<ResponseBody>> postCommentReplyLike(@Field(S.userId) String userId, @Field(S.postId) String postId, @Field(S.userPostCommentId) String userPostCommentId, @Field(S.userPostCommentReplyId) String userPostCommentReplyId, @Field(S.status) String status);

    @FormUrlEncoded
    @POST(S.postCommentReplyRemove_api)
    Observable<Response<ResponseBody>> postReplyRemove(@Field(S.userId) String userId, @Field(S.userPostCommentReplyId) String userPostCommentReplyId);

    @FormUrlEncoded
    @POST(S.userNotificationList_api)
    Observable<Response<ResponseBody>> userNotificationList(@Field(S.userId) String userId);

    @FormUrlEncoded
    @POST(S.userChangeNotificationStatus_api)
    Observable<Response<ResponseBody>> notificationStatusChanged(@Field(S.userId) String userId, @Field(S.notificationId) String notification_id, @Field(S.status) String status);

    @FormUrlEncoded
    @POST(S.myFriendRequestList_api)
    Observable<Response<ResponseBody>> pendingRequest(@Field(S.userId) String userId);

    @FormUrlEncoded
    @POST(S.userFriend_api)
    Observable<Response<ResponseBody>> confirmPendingRequest(@Field(S.fromUserId) String fromUserId, @Field(S.toUserId) String toUserId, @Field(S.userFriendId) String userFriendId, @Field(S.status) String status);

    @FormUrlEncoded
    @POST(S.searchList_api)
    Observable<Response<ResponseBody>> searchUser(@Field(S.userId) String userId, @Field(S.searchKeyword) String text);

    @FormUrlEncoded
    @POST(S.trackInfo_api)
    Observable<Response<ResponseBody>> trackInfo(@Field(S.userId) String userId, @Field(S.trackId) String trackId);

    @FormUrlEncoded
    @POST(S.trackDiaryPost_api)
    Observable<Response<ResponseBody>> trackDiaryData(@Field(S.userId) String userId, @Field(S.trackId) String trackId);

    @FormUrlEncoded
    @POST(S.eventListByTrack_api)
    Observable<Response<ResponseBody>> trackEvents(@Field(S.userId) String userId, @Field(S.trackId) String trackId, @Field(S.type) String type,@Field(S.groupTypeId) String groupTypeId);

    @Multipart
    @POST(S.createGroup_api)
    Observable<Response<ResponseBody>> createUserGroup(@Part(S.userId) RequestBody bodyuserId, @Part(S.groupTypeId) RequestBody bodyVehicleTypeId, @Part(S.userVehicleId) RequestBody bodyVehicleBrandID, @Part(S.trackId) RequestBody bodyTrackID, @Part(S.privacy) RequestBody bodyPrivacy, @Part(S.name) RequestBody bodyGroupname, @Part(S.description) RequestBody bodyDesc, @Part MultipartBody.Part profileImage, @Part MultipartBody.Part coverImage);

    @GET(S.groupTypeList_api)
    Observable<Response<ResponseBody>> vehicleTypeList();

    @FormUrlEncoded
    @POST(S.groupInfo_api)
    Observable<Response<ResponseBody>> getGroupAbout(@Field(S.userId) String userId, @Field(S.groupId) String groupId);

    @FormUrlEncoded
    @POST(S.userGroupRequest_api)
    Observable<Response<ResponseBody>> joinedGroup(@Field(S.userId) String userId, @Field(S.groupId) String groupId, @Field(S.status) String status);

    @FormUrlEncoded
    @POST(S.groupMemberList_api)
    Observable<Response<ResponseBody>> getGroupMembersList(@Field(S.userId) String userId, @Field(S.groupId) String groupId);

    @FormUrlEncoded
    @POST(S.groupListing_api)
    Observable<Response<ResponseBody>> getGroupList(@Field(S.userId) String userId, @Field(S.type) String type,@Field(S.groupTypeId) String groupTypeId);

    @FormUrlEncoded
    @POST(S.groupEventList_api)
    Observable<Response<ResponseBody>> getGroupEvents(@Field(S.userId) String userId, @Field(S.groupId) String groupId, @Field(S.type) String type,@Field(S.groupTypeId) String groupTypeId);

    @FormUrlEncoded
    @POST(S.eventCustomerList_api)
    Observable<Response<ResponseBody>> getEventMembersList(@Field(S.userId) String userId, @Field(S.eventId) String eventId);

    @FormUrlEncoded
    @POST(S.eventInfo_api)
    Observable<Response<ResponseBody>> getEventInfo(@Field(S.userId) String userId, @Field(S.eventId) String eventId);

    @FormUrlEncoded
    @POST(S.galleryListing_api)
    Observable<Response<ResponseBody>> getGroupGallery(@Field(S.userId) String userId, @Field(S.groupId) String groupId);

    @FormUrlEncoded
    @POST(S.galleryListing_api)
    Observable<Response<ResponseBody>> getTrackGallery(@Field(S.userId) String userId, @Field(S.trackId) String trackId);

    @FormUrlEncoded
    @POST(S.userEventListing_api)
    Observable<Response<ResponseBody>> getUserEvents(@Field(S.userId) String userId, @Field(S.type) String type,@Field(S.groupTypeId) String groupTypeId);

    @FormUrlEncoded
    @POST(S.userGalleryListing)
    Observable<Response<ResponseBody>> getUserGallery(@Field(S.userId) String userId);

    @FormUrlEncoded
    @POST(S.myTrackLapList_api)
    Observable<Response<ResponseBody>> getTrackTimeLap(@Field(S.userId) String userId, @Field(S.trackId) String trackId);

    @FormUrlEncoded
    @POST(S.updateAllNotificationToRead)
    Observable<Response<ResponseBody>> setNotificationSettingsChanged(@Field(S.userId) String userId);

    @FormUrlEncoded
    @POST(S.allTrackLapListings)
    Observable<Response<ResponseBody>> getMyLapsListing(@Field(S.userId) String userId);

    @FormUrlEncoded
    @POST(S.lapsInTrackVersion)
    Observable<Response<ResponseBody>> getMyLapsListingFriends(@Field(S.userId) String userId,@Field(S.groupTypeId) String groupTypeId);

    @FormUrlEncoded
    @POST(S.albumListingPhoto_api)
    Observable<Response<ResponseBody>> getGalleryPhoto(@Field(S.userId) String userId, @Field(S.type) String type,@Field(S.groupTypeId) String groupTypeId);

    @FormUrlEncoded
    @POST(S.albumListingVideo_api)
    Observable<Response<ResponseBody>> getGalleryVideo(@Field(S.userId) String userId, @Field(S.type) String type,@Field(S.groupTypeId) String groupTypeId);

    @FormUrlEncoded
    @POST(S.upcomingEvent_api)
    Observable<Response<ResponseBody>> getExploreData(@Field(S.userId) String userId, @Field(S.groupTypeId) String groupTypeId);

    @FormUrlEncoded
    @POST(S.getWeather_api)
    Observable<Response<ResponseBody>> getWeatherDetail(@Field(S.userId) String userId, @Field(S.latitude_response) String trackLatPos, @Field(S.longitude_response) String trackLongPos);

    @FormUrlEncoded
    @POST(S.upcomingEventByDate_api)
    Observable<Response<ResponseBody>> getExploreByDate(@Field(S.userId) String userId, @Field(S.dateReq) String dates, @Field(S.groupTypeId) String groupTypeId);

    @FormUrlEncoded
    @POST(S.myGroupPostListing_api)
    Observable<Response<ResponseBody>> getGroupDiaryData(@Field(S.userId) String userId, @Field(S.groupId) String groupId, @Field(S.page) String page);

    @FormUrlEncoded
    @POST(S.preferredTrack_api)
    Observable<Response<ResponseBody>> getPreferedTrackData(@Field(S.userId) String userId);

    @FormUrlEncoded
    @POST(S.myPostListing_api)
    Observable<Response<ResponseBody>> myPostListing(@Field(S.userId) String userId, @Field(S.page) String page);

    @FormUrlEncoded
    @POST(S.advertList_api)
    Observable<Response<ResponseBody>> getAdvertisementData(@Field(S.userId) String userId);

    @FormUrlEncoded
    @POST(S.subscriptionLists_api)
    Observable<Response<ResponseBody>> getSubscriptionList(@Field(S.userId) String userId);

    @FormUrlEncoded
    @POST(S.postShared_api)
    Observable<Response<ResponseBody>> postShareByUser(@Field(S.userId) String userId, @Field(S.postId) String postId);

    @FormUrlEncoded
    @POST(S.voucherConfirmation_api)
    Observable<Response<ResponseBody>> getVoucherDetail(@Field(S.userId) String userId, @Field(S.voucherName) String voucherCode);

    @FormUrlEncoded
    @POST(S.userSubscriptions_api)
    Observable<Response<ResponseBody>> getSubscription(@Field(S.userId) String userId, @Field(S.subscriptionPackageId) String subscriptionPackageId, @Field(S.voucherCodeId) String voucherCodeId, @Field(S.amount) String payAmount, @Field(S.subscriptionEndDate) String subscriptionEndDate, @Field(S.stripeToken) String tokenId);

    @FormUrlEncoded
    @POST(S.getChatFriendAndGroups_api)
    Observable<Response<ResponseBody>> myChatRooms(@Field(S.userId) String userId);

    @FormUrlEncoded
    @POST(S.addCartAlbum_api)
    Observable<Response<ResponseBody>> addCartAlbum(@Field(S.userId) String userId, @Field(S.albumId) String albumId);

    @FormUrlEncoded
    @POST(S.viewAlbumCart_api)
    Observable<Response<ResponseBody>> viewAlbumCart(@Field(S.userId) String userId);

    @FormUrlEncoded
    @POST(S.removeCartAlbum_api)
    Observable<Response<ResponseBody>> removeCartAlbum(@Field(S.userId) String userId, @Field(S.albumId) String albumId, @Field(S.albumCartId) String albumCartId);

    @FormUrlEncoded
    @POST(S.albumCheckout_api)
    Observable<Response<ResponseBody>> albumCheckout(@Field(S.userId) String userId, @Field(S.totalAmountReq) String totalAmountReq, @Field(S.stripeToken) String albumCartId);

    @FormUrlEncoded
    @POST(S.trackDetailByLatLong_api)
    Observable<Response<ResponseBody>> getTrackDetailByLatLong(@Field(S.userId) String trackDetailByLatlong, @Field(S.latitude_response) String latitude, @Field(S.longitude_response) String longitude);

    @FormUrlEncoded
    @POST(S.userUpdateMapView_api)
    Observable<Response<ResponseBody>> setDefaultMapview(@Field(S.userId) String userId, @Field(S.mapView) String view);

    @GET(S.vehicleTypeList_api)
    Observable<Response<ResponseBody>> getreportcauseList();

    @FormUrlEncoded
    @POST(S.reportAbuse_api)
    Observable<Response<ResponseBody>> reportPost(@Field(S.userId) String userId, @Field(S.postId) String post_id, @Field(S.comment) String report);

    @GET(S.youtubeData)
    Observable<Response<ResponseBody>> getYoutubeUrlData(@Query("part") String snippet, @Query("id") String videoID, @Query("key") String apiKey);

    @Multipart
    @POST(S.uploadLapTimeData)
    Observable<Response<ResponseBody>> uploadLapTimeData(@Part(S.userId) RequestBody bodyuserId, @Part(S.gpsDevices) RequestBody bodygpsDevices, @Part(S.softwareVersion) RequestBody bodysoftwareVersion, @Part(S.numberOfLapsFound) RequestBody bodynumberOfLapsFound,
                                                         @Part(S.trackId) RequestBody bodytrackId, @Part(S.trackVersionId) RequestBody bodytrackVersionId, @Part(S.dateOfRecording) RequestBody bodylapDate, @Part(S.weather) RequestBody bodyweather,
                                                         @Part(S.vehicleTyreBrandId) RequestBody bodyvehicleTyreBrandId, @Part(S.vehicleTyreModelId) RequestBody bodyvehicleTyreModelId, @Part(S.photoCount) RequestBody bodyphotoCount, @Part(S.youtubeURL) RequestBody bodyyoutubeURL,
                                                         @Part(S.description) RequestBody bodydescription, @Part(S.time) RequestBody bodylapTime, @Part(S.userVehicleId) RequestBody bodyvehicleId, @Part(S.selectedLaps) RequestBody bodyselectedValue, @Part(S.groupTypeId) RequestBody bodyGroupTypeId, @Part(S.rentVehicle) RequestBody bodyRentVehicle, @Part MultipartBody.Part filePart, @Part MultipartBody.Part... images);

    @Multipart
    @POST(S.newTrackRequest)
    Observable<Response<ResponseBody>> sendRequestForm(@Part(S.userId) RequestBody bodyuserId, @Part(S.trackName) RequestBody bodytrackName, @Part(S.address) RequestBody bodyaddress,
                                                       @Part(S.website) RequestBody bodywebsite, @Part(S.description) RequestBody bodydescription, @Part(S.email) RequestBody bodyemail, @Part(S.numbers) RequestBody bodycontact, @Part(S.groupTypeId) RequestBody bodytracktypeId, @Part MultipartBody.Part filePart);

    @FormUrlEncoded
    @POST(S.changeEmailAddress)
    Observable<Response<ResponseBody>> changeEmail(@Field(S.userId) String id, @Field(S.verificationEmailId) String email);

    @FormUrlEncoded
    @POST(S.removeNotification_api)
    Observable<Response<ResponseBody>> removeNotification(@Field(S.notificationId) String id, @Field(S.recieverId) String email);

    @GET
    Call<ResponseBody> downloadFileWithDynamicUrlSync(@Url String fileUrl);

    @Multipart
    @POST(S.updateSessions)
    Observable<Response<ResponseBody>> updateUploadLapTimeData(@Part(S.userId) RequestBody bodyuserId, @Part(S.sessionId) RequestBody bodysessionId, @Part(S.sessionIndex) RequestBody bodysessionIndex, @Part(S.laps) RequestBody bodyLapData, @Part(S.photoCount) RequestBody bodyphotoCount, @Part(S.weather) RequestBody bodyweather, @Part(S.vehicleTyreBrandId) RequestBody bodyvehicletyreBrandId, @Part(S.vehicleTyreModelId) RequestBody bodyvehicletyreModelId, @Part(S.notes) RequestBody bodynotes, @Part(S.youtubeURL) RequestBody bodyyoutubeUrl,@Part(S.lapTimeId) RequestBody bodylapTimeId, @Part MultipartBody.Part... images);

    @FormUrlEncoded
    @POST(S.postInfo)
    Observable<Response<ResponseBody>> postInfo(@Field(S.userId) String userId, @Field(S.postId) String postId);

    @Multipart
    @POST(S.checkLapTimeCSV)
    Observable<Response<ResponseBody>> uploadLapTimeFile(@Part MultipartBody.Part filePart);

    @FormUrlEncoded
    @POST(S.userSubscribeList)
    Observable<Response<ResponseBody>> userSubscribeList(@Field(S.userId) String userId);

    @FormUrlEncoded
    @POST(S.albumLike)
    Observable<Response<ResponseBody>> albumLikeByUser(@Field(S.userId) String userId, @Field(S.albumId) String postId, @Field(S.status) String status);

    @FormUrlEncoded
    @POST(S.albumComment)
    Observable<Response<ResponseBody>> albumComment(@Field(S.albumId) String postId, @Field(S.userId) String userId, @Field(S.comment) String comment,
                                                    @Field(S.userAlbumCommentId) String userPostCommentId, @Field(S.edit) String edit);

    @FormUrlEncoded
    @POST(S.albumCommentLike)
    Observable<Response<ResponseBody>> albumCommentLike(@Field(S.albumId) String postId, @Field(S.userId) String userId, @Field(S.userAlbumCommentId) String userPostCommentId, @Field(S.status) String status);

    @FormUrlEncoded
    @POST(S.albumCommentListing)
    Observable<Response<ResponseBody>> albumCommentListing(@Field(S.userId) String userId, @Field(S.albumId) String postId);

    @FormUrlEncoded
    @POST(S.albumCommentRemove_api)
    Observable<Response<ResponseBody>> albumCommentRemove(@Field(S.userId) String userId, @Field(S.userAlbumCommentId) String userPostCommentId);

    @FormUrlEncoded
    @POST(S.albumLikeUserListing)
    Observable<Response<ResponseBody>> albumLikeUserList(@Field(S.userId) String userId, @Field(S.albumId) String post_id);

    @FormUrlEncoded
    @POST(S.albumCommentLikeUserListing)
    Observable<Response<ResponseBody>> getCommentAlbumLikeUserList(@Field(S.userId) String userId, @Field(S.userAlbumCommentId) String postComment_id);

    @FormUrlEncoded
    @POST(S.albumCommentReply)
    Observable<Response<ResponseBody>> albumCommentReply(@Field(S.albumId) String postId, @Field(S.userId) String userId, @Field(S.comment) String comment,
                                                         @Field(S.userAlbumCommentId) String userPostCommentId, @Field(S.userAlbumCommentReplyId) String userPostCommentReplyId, @Field(S.edit) String edit);

    @FormUrlEncoded
    @POST(S.albumCommentReplyListing)
    Observable<Response<ResponseBody>> albumCommentReplyListing(@Field(S.userId) String userId, @Field(S.albumId) String postId, @Field(S.userAlbumCommentId) String userPostCommentId);

    @FormUrlEncoded
    @POST(S.albumCommentReplyLike_api)
    Observable<Response<ResponseBody>> albumCommentReplyLike(@Field(S.userId) String userId, @Field(S.albumId) String postId, @Field(S.userAlbumCommentId) String userPostCommentId,
                                                             @Field(S.userAlbumCommentReplyId) String userPostCommentReplyId, @Field(S.status) String status);

    @FormUrlEncoded
    @POST(S.albumCommentReplyRemove)
    Observable<Response<ResponseBody>> albumReplyRemove(@Field(S.userId) String userId, @Field(S.userAlbumCommentReplyId) String userPostCommentReplyId);

    @FormUrlEncoded
    @POST(S.albumCommentReplyLikeUserListing)
    Observable<Response<ResponseBody>> replyAlbumLikeUserList(@Field(S.userId) String userId, @Field(S.userAlbumCommentReplyId) String userPostCommentReplyId);

    @FormUrlEncoded
    @POST(S.reportAbuseAlbum)
    Observable<Response<ResponseBody>> reportAlbum(@Field(S.userId) String userId, @Field(S.albumId) String post_id, @Field(S.comment) String report);

    @FormUrlEncoded
    @POST(S.albumShared_api)
    Observable<Response<ResponseBody>> albumShareByUser(@Field(S.userId) String userId, @Field(S.albumId) String postId);

    @Multipart
    @POST(S.addalbum)
    Observable<Response<ResponseBody>> addAlbum(@Part(S.userId) RequestBody bodyuserId, @Part(S.name) RequestBody bodyalbumName, @Part(S.description) RequestBody bodyalbumDescription,
                                                @Part(S.photoCount) RequestBody bodyphotoCount, @Part(S.videoCount) RequestBody bodyvideoCount, @Part(S.dateOfEvent) RequestBody bodydateofevent,
                                                @Part(S.trackId) RequestBody bodytrackId, @Part(S.groupTypeId) RequestBody bodygrouptypeId, @Part MultipartBody.Part... images);

    @Multipart
    @POST(S.updateAlbum_api)
    Observable<Response<ResponseBody>> editAlbum(@Part(S.userId) RequestBody bodyuserId, @Part(S.name) RequestBody bodyalbumName, @Part(S.description) RequestBody bodyalbumDescription,
                                                 @Part(S.photoCount) RequestBody bodyphotoCount, @Part(S.videoCount) RequestBody bodyvideoCount, @Part(S.dateOfEvent) RequestBody bodydateofevent,
                                                 @Part(S.trackId) RequestBody bodytrackId, @Part(S.removeImage) RequestBody removeImg1, @Part(S.albumId) RequestBody eventId, @Part(S.groupTypeId) RequestBody bodygrouptypeId, @Part MultipartBody.Part... images);

    @Multipart
    @POST(S.updateAlbum_api)
    Observable<Response<ResponseBody>> editAlbum(@Part(S.userId) RequestBody bodyuserId, @Part(S.name) RequestBody bodyalbumName, @Part(S.description) RequestBody bodyalbumDescription,
                                                 @Part(S.photoCount) RequestBody bodyphotoCount, @Part(S.videoCount) RequestBody bodyvideoCount, @Part(S.dateOfEvent) RequestBody bodydateofevent,
                                                 @Part(S.trackId) RequestBody bodytrackId, @Part(S.removeImage) RequestBody removeImg1, @Part(S.albumId) RequestBody eventId, @Part(S.groupTypeId) RequestBody bodygrouptypeId);

    @FormUrlEncoded
    @POST(S.eventTicketPurchase)
    Observable<Response<ResponseBody>> getEventTicketPurchase(@Field(S.userId) String userId, @Field(S.eventId) String postId, @Field(S.token) String tokenId, @Field(S.quantity) String quantity,
                                                              @Field(S.amount) String amount, @Field(S.ticketDescription) String s, @Field(S.presetsId) String presetsId);

    @FormUrlEncoded
    @POST(S.postRemove_api)
    Observable<Response<ResponseBody>> postRemove(@Field(S.userId) String userId, @Field(S.postId) String postId);

    @Multipart
    @POST(S.updatePost_api)
    Observable<Response<ResponseBody>> updatePost(@Part(S.userId) RequestBody user_id, @Part(S.userVehicleId) RequestBody userVehicleId, @Part(S.trackId) RequestBody trackId,
                                                  @Part(S.position_text) RequestBody position_text, @Part(S.feelingId) RequestBody feelingId, @Part(S.photoCount) RequestBody photoCount,
                                                  @Part(S.videoCount) RequestBody videoCount, @Part(S.description) RequestBody description, @Part(S.friendId) RequestBody friendId,
                                                  @Part(S.speed) RequestBody speed, @Part(S.weather) RequestBody weather, @Part(S.removeFriendTag) RequestBody removeFriendTag,
                                                  @Part(S.removeImage) RequestBody removeImage, @Part(S.postId) RequestBody requestBody, @Part MultipartBody.Part... imageVideo);

    @FormUrlEncoded
    @POST(S.trackChangeHomeStatus_api)
    Observable<Response<ResponseBody>> changeHomeTrackStatus(@Field(S.userId) String userId, @Field(S.trackId) String trackId, @Field(S.status) String status);

    @FormUrlEncoded
    @POST(S.generalInformationData)
    Observable<Response<ResponseBody>> generalInformationData(@Field(S.userId) String userId);

    @FormUrlEncoded
    @POST(S.advertLike)
    Observable<Response<ResponseBody>> adLikeByUser(@Field(S.userId) String userId, @Field(S.advertisementId) String advertisementId, @Field(S.status) String status);

    @FormUrlEncoded
    @POST(S.advertComment)
    Observable<Response<ResponseBody>> adComment(@Field(S.advertisementId) String postId, @Field(S.userId) String userId, @Field(S.comment) String comment, @Field(S.advertisementCommentId) String userPostCommentId, @Field(S.edit) String edit);

    @FormUrlEncoded
    @POST(S.advertLikeUserListing)
    Observable<Response<ResponseBody>> adLikeUserList(@Field(S.userId) String userId, @Field(S.advertisementId) String post_id);

    @FormUrlEncoded
    @POST(S.advertCommentListing_api)
    Observable<Response<ResponseBody>> adCommentListing(@Field(S.userId) String userId, @Field(S.advertisementId) String postId);

    @FormUrlEncoded
    @POST(S.advertCommentRemove_api)
    Observable<Response<ResponseBody>> adCommentRemove(@Field(S.userId) String userId, @Field(S.advertisementCommentId) String userPostCommentId);

    @FormUrlEncoded
    @POST(S.albumRemove)
    Observable<Response<ResponseBody>> albumDelete(@Field(S.userId) String userId, @Field(S.albumId) String albumId);

    @FormUrlEncoded
    @POST(S.albumInfo)
    Observable<Response<ResponseBody>> albumInfo(@Field(S.userId) String userId, @Field(S.albumId) String albumId);

    @FormUrlEncoded
    @POST(S.groupJoinRequestSend)
    Observable<Response<ResponseBody>> sentInviteRequest(@Field(S.userId) String userId, @Field(S.groupId) String groupId, @Field(S.friendId) String friendId);

    @FormUrlEncoded
    @POST(S.myFriendListforGroup)
    Observable<Response<ResponseBody>> myFriendListforGroup(@Field(S.userId) String userId, @Field(S.groupId) String groupId);

    @FormUrlEncoded
    @POST(S.myPendingGroupRequest)
    Observable<Response<ResponseBody>> getPendingGroupRequest(@Field(S.userId) String userId);

    @FormUrlEncoded
    @POST(S.userGroupJoin)
    Observable<Response<ResponseBody>> sendGroupRequestAction(@Field(S.userId) String userId, @Field(S.groupId) String groupId, @Field(S.status) String status);

    @GET
    Observable<Response<ResponseBody>> readJsonFileData(@Url String url);

    @FormUrlEncoded
    @POST(S.userAddToAdminList)
    Observable<Response<ResponseBody>> makeGroupAdmin(@Field(S.groupAdminId) String userId, @Field(S.groupId) String groupId, @Field(S.memberID) String memberId, @Field(S.status) String status);

    @FormUrlEncoded
    @POST(S.userGroupMemberRemove)
    Observable<Response<ResponseBody>> removeFromGroup(@Field(S.userId) String userId, @Field(S.groupId) String groupId, @Field(S.friendId) String memberId);

    @FormUrlEncoded
    @POST(S.sessionData)
    Observable<Response<ResponseBody>> getSessionsData(@Field(S.userId) String userId);

    @FormUrlEncoded
    @POST(S.removeSessionImage)
    Observable<Response<ResponseBody>> deleteSessionImage(@Field(S.userId) String userId,@Field(S.sessionId) String sessionId,@Field(S.sessionImgId) String image_id);

    @FormUrlEncoded
    @POST(S.updateLapStatus1)
    Observable<Response<ResponseBody>> updateLap(@Field(S.userId)String userId,@Field(S.updateData) String updateData);

    @FormUrlEncoded
    @POST(S.userUpdateGroupType)
    Observable<Response<ResponseBody>> updatevehicleType(@Field(S.userId) String userId,@Field(S.groupTypeId) String selectedId);

    @GET(S.trackConditionList)
    Observable<Response<ResponseBody>> getTrackCondition(@Query("userId") String userId);

    @FormUrlEncoded
    @POST(S.preferredTrackNew)
    Observable<Response<ResponseBody>> getPreferedTrackDataFilter(@Field(S.userId) String userId,@Field(S.vehicleTypeId) String vehicleTypeId,@Field(S.vehicleBrandId) String vehicleBrandID,@Field(S.vehicleYearFrom) String fromVehicleYearId,@Field(S.vehicleYearTo) String toVehicleYearId,@Field(S.weather) String trackCondition,@Field(S.trackId) String trackId);

    @FormUrlEncoded
    @POST(S.trackProfile)
    Observable<Response<ResponseBody>> getTrackProfile(@Field(S.userId) String userId,@Field(S.trackId) String track_id);
}
