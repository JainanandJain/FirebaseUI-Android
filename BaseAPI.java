package com.os.fastlap.data;

import android.content.Context;

import com.os.fastlap.R;
import com.os.fastlap.activity.AddPostActivity;
import com.os.fastlap.activity.AlbumActivity;
import com.os.fastlap.activity.AlbumCommentActivity;
import com.os.fastlap.activity.AllUsersActivity;
import com.os.fastlap.activity.BaseActivity;
import com.os.fastlap.activity.CartAlbumActivity;
import com.os.fastlap.activity.ContactUsActivity;
import com.os.fastlap.activity.DashboardActivity;
import com.os.fastlap.activity.ExploreActivity;
import com.os.fastlap.activity.GalleryActivity;
import com.os.fastlap.activity.LoginActivity;
import com.os.fastlap.activity.MenuActivity;
import com.os.fastlap.activity.NotificationListActivity;
import com.os.fastlap.activity.OTPActivity;
import com.os.fastlap.activity.RecordCameraActivity;
import com.os.fastlap.activity.ReplyAlbumActivity;
import com.os.fastlap.activity.SearchActivity;
import com.os.fastlap.activity.SettingMenuActivity;
import com.os.fastlap.activity.SettingsActivity;
import com.os.fastlap.activity.SignupActivity;
import com.os.fastlap.activity.TracksListingActivity;
import com.os.fastlap.activity.UploadLapTimeActivity;
import com.os.fastlap.activity.community.CommunityMoreInfoActivity;
import com.os.fastlap.activity.community.EventActivity;
import com.os.fastlap.activity.community.UserGroupsActivity;
import com.os.fastlap.activity.dashboard.AdCommentActivity;
import com.os.fastlap.activity.dashboard.CommentActivity;
import com.os.fastlap.activity.dashboard.DashboardVehicleListActivity;
import com.os.fastlap.activity.dashboard.LikesUserActivity;
import com.os.fastlap.activity.dashboard.ReplyActivity;
import com.os.fastlap.activity.group.CreateGroupActivity;
import com.os.fastlap.activity.group.GroupDiaryActivity;
import com.os.fastlap.activity.group.GroupInfoActivity;
import com.os.fastlap.activity.group.GroupMemberActivity;
import com.os.fastlap.activity.group.GroupProfileActivity;
import com.os.fastlap.activity.mylaps.MyLapsActivity;
import com.os.fastlap.activity.mylaps.MyLapsRaceActivity;
import com.os.fastlap.activity.payment.AddCardDetail;
import com.os.fastlap.activity.profile.AddAboutInfoActivity;
import com.os.fastlap.activity.profile.AddAlbumActivity;
import com.os.fastlap.activity.profile.AddClothesActivity;
import com.os.fastlap.activity.profile.AddVehicleActivity;
import com.os.fastlap.activity.profile.DiaryActivity;
import com.os.fastlap.activity.profile.FriendsProfileActivity;
import com.os.fastlap.activity.profile.GalleryProfileActivity;
import com.os.fastlap.activity.profile.GarageProfileActivity;
import com.os.fastlap.activity.profile.InfoActivity;
import com.os.fastlap.activity.profile.LapsProfileActivity;
import com.os.fastlap.activity.profile.PendingGroupRequestActivity;
import com.os.fastlap.activity.profile.ProfileActivity;
import com.os.fastlap.activity.track.TrackDetailActivity;
import com.os.fastlap.activity.track.TrackDiaryActivity;
import com.os.fastlap.activity.track.TrackInfoActivity;
import com.os.fastlap.activity.track.TrackTimeLaps;
import com.os.fastlap.util.Util;
import com.os.fastlap.util.constants.S;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Response;

/*
 * Created by anandj on 7/24/2017.
 */

abstract class BaseAPI {
    void handleResponse(Response<ResponseBody> response, String api_name, Context context) {
        if (response.isSuccessful()) {
            switch (api_name) {
                case S.sign_in_api:
                    if (context instanceof LoginActivity)
                        ((LoginActivity) context).getResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.sign_up_api:
                    if (context instanceof SignupActivity)
                        ((SignupActivity) context).getResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.verify_otp_api:
                    if (context instanceof OTPActivity)
                        ((OTPActivity) context).getResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.social_social_api:
                    if (context instanceof LoginActivity)
                        ((LoginActivity) context).getSocialSignupWebserviceResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.forgot_password_api:
                    if (context instanceof LoginActivity)
                        ((LoginActivity) context).getForgotPasswordResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.logout_api:
                    if (context instanceof MenuActivity)
                        ((MenuActivity) context).getLogoutWebserviceResponse(Util.convertRetrofitResponce(response));
                    if (context instanceof SettingMenuActivity)
                        ((SettingMenuActivity) context).getLogoutWebserviceResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.resend_otp_api:
                    if (context instanceof OTPActivity)
                        ((OTPActivity) context).getResendOtpResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.getNationality_api:
                    if (context instanceof SignupActivity)
                        ((SignupActivity) context).getNationalityWebserviceResponse(Util.convertRetrofitResponce(response));
                    else if (context instanceof SettingMenuActivity)
                        ((SettingMenuActivity) context).getNationalityWebserviceResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.getstaticPage_api:
                    if (context instanceof SettingMenuActivity)
                        ((SettingMenuActivity) context).getStaticPageResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.changePassword_api:
                    if (context instanceof SettingMenuActivity)
                        ((SettingMenuActivity) context).getChangePasswordResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.vehicleTypeList_api:
                    if (context instanceof AddVehicleActivity)
                        ((AddVehicleActivity) context).getVehicleTypeResponse(Util.convertRetrofitResponce(response));
                    else if (context instanceof DashboardActivity)
                        ((DashboardActivity) context).getVehicleTypeResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.vehicleBrandList_api:
                    if (context instanceof AddVehicleActivity)
                        ((AddVehicleActivity) context).getVehicleBrandResponse(Util.convertRetrofitResponce(response));
                    else if (context instanceof DashboardActivity)
                        ((DashboardActivity) context).getVehicleBrandResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.vehicleModelList_api:
                    if (context instanceof AddVehicleActivity)
                        ((AddVehicleActivity) context).getVehicleModelResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.vehicleVersionList_api:
                    if (context instanceof AddVehicleActivity)
                        ((AddVehicleActivity) context).getVehicleVersionResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.vehicleYearList_api:
                    if (context instanceof AddVehicleActivity)
                        ((AddVehicleActivity) context).getVehicleYearResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.vehicleYear1List_api:
                    if (context instanceof DashboardActivity)
                    ((DashboardActivity) context).getVehicleYearResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.vehicleTyreBrandList_api:
                    if (context instanceof AddVehicleActivity)
                        ((AddVehicleActivity) context).getVehicleTyreBrandResponse(Util.convertRetrofitResponce(response));
                    else if (context instanceof UploadLapTimeActivity)
                        ((UploadLapTimeActivity) context).getVehicleTyreBrandResponse(Util.convertRetrofitResponce(response));
                    else if (context instanceof MyLapsActivity)
                        ((MyLapsActivity) context).getVehicleTyreBrandResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.vehicleTyreModelList_api:
                    if (context instanceof AddVehicleActivity)
                        ((AddVehicleActivity) context).getVehicleTyreModelResponse(Util.convertRetrofitResponce(response));
                    else if (context instanceof UploadLapTimeActivity)
                        ((UploadLapTimeActivity) context).getVehicleTyreModelResponse(Util.convertRetrofitResponce(response));
                    else if (context instanceof MyLapsActivity)
                        ((MyLapsActivity) context).getVehicleTyreModelResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.addUserVehicle_api:
                    if (context instanceof AddVehicleActivity)
                        ((AddVehicleActivity) context).getAddVehicleWebserviceResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.listUserVehicle_api:
                    if (context instanceof GarageProfileActivity) {
                        ((GarageProfileActivity) context).getVehicleListResponse(Util.convertRetrofitResponce(response));
                    } else if (context instanceof DashboardVehicleListActivity) {
                        ((DashboardVehicleListActivity) context).getVehicleListResponse(Util.convertRetrofitResponce(response));
                    } else if (context instanceof CreateGroupActivity) {
                        ((CreateGroupActivity) context).getVehicleListResponse(Util.convertRetrofitResponce(response));
                    }
                    break;
                case S.removeUserVehicle_api:
                    if (context instanceof GarageProfileActivity)
                        ((GarageProfileActivity) context).deleteVehicleListResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.userClothList_api:
                    if (context instanceof InfoActivity)
                        ((InfoActivity) context).getClothList(Util.convertRetrofitResponce(response));
                    break;
                case S.clothBrandList_api:
                    if (context instanceof AddClothesActivity)
                        ((AddClothesActivity) context).getClothBrand(Util.convertRetrofitResponce(response));
                    break;
                /*case S.clothModelList_api:
                    if (context instanceof AddClothesActivity)
                        ((AddClothesActivity) context).getClothModel(Util.convertRetrofitResponce(response));
                    break;
                case S.clothYearList_api:
                    if (context instanceof AddClothesActivity)
                        ((AddClothesActivity) context).getClothYear(Util.convertRetrofitResponce(response));
                    break;
                case S.clothColorList_api:
                    if (context instanceof AddClothesActivity)
                        ((AddClothesActivity) context).getClothColor(Util.convertRetrofitResponce(response));
                    break;*/
                case S.userAddCloths_api:
                    if (context instanceof AddClothesActivity)
                        ((AddClothesActivity) context).getAddClothResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.removeUserCloth_api:
                    if (context instanceof InfoActivity)
                        ((InfoActivity) context).getDeleteClothResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.editUserVehicle_api:
                    if (context instanceof AddVehicleActivity) {
                        ((AddVehicleActivity) context).getEditVehicleWebserviceResponse(Util.convertRetrofitResponce(response));
                    }
                    break;
                case S.contactUs_api:
                    if (context instanceof ContactUsActivity)
                        ((ContactUsActivity) context).getContactUsResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.userAbout_api:
                    if (context instanceof InfoActivity)
                        ((InfoActivity) context).getUserAboutResponse(Util.convertRetrofitResponce(response));
                    else if (context instanceof SettingMenuActivity)
                        ((SettingMenuActivity) context).getUserAboutResponse(Util.convertRetrofitResponce(response));
                    else if (context instanceof ProfileActivity)
                        ((ProfileActivity) context).getUserAboutResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.updateAbout_api:
                    if (context instanceof AddAboutInfoActivity)
                        ((AddAboutInfoActivity) context).updateUserInfo(Util.convertRetrofitResponce(response));
                    break;
                case S.editGeneralInfo_api:
                    if (context instanceof SettingMenuActivity)
                        ((SettingMenuActivity) context).getUpdateInfoResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.userUpdateTimeSystem_api:
                    if (context instanceof SettingMenuActivity)
                        ((SettingMenuActivity) context).getUpdatetimeSystemResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.userUpdateMetricSystem_api:
                    if (context instanceof SettingMenuActivity)
                        ((SettingMenuActivity) context).getUpdateMetricSystemResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.userUpdateWeekStartDay_api:
                    if (context instanceof SettingMenuActivity)
                        ((SettingMenuActivity) context).getUpdateWeekStartDayResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.userDeviceConnected_api:
                    if (context instanceof SettingMenuActivity)
                        ((SettingMenuActivity) context).userDeviceConnectedResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.deviceDisconnectById_api:
                    if (context instanceof SettingMenuActivity)
                        ((SettingMenuActivity) context).deviceDisconnectByIdResponse((Util.convertRetrofitResponce(response)));
                    break;
                case S.deviceDisconnect_api:
                    if (context instanceof SettingMenuActivity)
                        ((SettingMenuActivity) context).deviceDisconnect((Util.convertRetrofitResponce(response)));
                    break;
                case S.userUpdateProfileStatus_api:
                    if (context instanceof SettingMenuActivity)
                        ((SettingMenuActivity) context).accountDeactivate((Util.convertRetrofitResponce(response)));
                    break;
                case S.getLanguage_api:
                    if (context instanceof SignupActivity)
                        ((SignupActivity) context).getLanguage(Util.convertRetrofitResponce(response));
                    else if (context instanceof AddAboutInfoActivity)
                        ((AddAboutInfoActivity) context).getLanguage(Util.convertRetrofitResponce(response));
                    else if (context instanceof SettingsActivity)
                        ((SettingsActivity) context).getLanguage(Util.convertRetrofitResponce(response));
                    break;
                case S.trackManageStatus_api:
                    if (context instanceof InfoActivity)
                        ((InfoActivity) context).trackManageStatus((Util.convertRetrofitResponce(response)));
                    else if (context instanceof TracksListingActivity)
                        ((TracksListingActivity) context).trackManageStatus((Util.convertRetrofitResponce(response)));
                    else if (context instanceof TrackDetailActivity)
                        ((TrackDetailActivity) context).trackManageStatus((Util.convertRetrofitResponce(response)));
                    break;
                case S.userListing_api:
                    if (context instanceof AllUsersActivity)
                        //  ((AllUsersActivity) context).appUserResponse((Util.convertRetrofitResponce(response)));
                        break;
                case S.trackListingAll_api:
                    if (context instanceof TracksListingActivity)
                        ((TracksListingActivity) context).trackListingAllWebserviceResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.myFriendList_api:
                    if (context instanceof FriendsProfileActivity)
                        ((FriendsProfileActivity) context).myFriendListWebserviceResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.mutualFriends_api:
                    if (context instanceof FriendsProfileActivity)
                        ((FriendsProfileActivity) context).mutualFriendsWebserviceResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.feelingList_api:
                    if (context instanceof AddPostActivity)
                        ((AddPostActivity) context).feelingListWebserviceResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.updateUserImg_api:
                    if (context instanceof ProfileActivity)
                        ((ProfileActivity) context).updateImages(Util.convertRetrofitResponce(response));
                    break;
                case S.myFriendBlockList_api:
                    if (context instanceof SettingMenuActivity)
                        ((SettingMenuActivity) context).myFriendBlockListResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.userBlockStatus_api:
                    if (context instanceof SettingMenuActivity)
                        ((SettingMenuActivity) context).userBlockStatusResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.postSave_api:
                    if (context instanceof AddPostActivity)
                        ((AddPostActivity) context).savePostWebserviceResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.userFriend_api:
                    if (context instanceof ProfileActivity)
                        ((ProfileActivity) context).userFriendsWebserviceResponse(Util.convertRetrofitResponce(response));
                    else if (context instanceof FriendsProfileActivity)
                        ((FriendsProfileActivity) context).userFriendsWebserviceResponse(Util.convertRetrofitResponce(response));
                    else if (context instanceof FriendsProfileActivity)
                        ((FriendsProfileActivity) context).pendingRequestWebserviceResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.postLikeByUser_api:
                    if (context instanceof DashboardActivity)
                        ((DashboardActivity) context).postLikeByUserWebserviceResponse(Util.convertRetrofitResponce(response));
                    else if (context instanceof TrackDiaryActivity)
                        ((TrackDiaryActivity) context).postLikeByUserWebserviceResponse(Util.convertRetrofitResponce(response));
                    else if (context instanceof DiaryActivity)
                        ((DiaryActivity) context).postLikeByUserWebserviceResponse(Util.convertRetrofitResponce(response));
                    else if (context instanceof GroupDiaryActivity)
                        ((GroupDiaryActivity) context).postLikeByUserWebserviceResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.postCommentLike_api:
                    if (context instanceof CommentActivity)
                        ((CommentActivity) context).postCommentLikeWebserviceResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.postCommentListing_api:
                    if (context instanceof CommentActivity)
                        ((CommentActivity) context).postCommentListingWebserviceResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.postCommentRemove_api:
                    if (context instanceof CommentActivity)
                        ((CommentActivity) context).postCommentRemoveWebserviceResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.postListing_api:
                    if (context instanceof DashboardActivity)
                        ((DashboardActivity) context).postListingWebserviceResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.postLikeUserListing_api:
                    if (context instanceof LikesUserActivity)
                        ((LikesUserActivity) context).postLikeUserListingResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.postCommentReply_api:
                    if (context instanceof ReplyActivity)
                        ((ReplyActivity) context).postCommentReplyResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.postComment_api:
                    if (context instanceof CommentActivity)
                        ((CommentActivity) context).postCommentResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.postCommentReplyListing_api:
                    if (context instanceof ReplyActivity)
                        ((ReplyActivity) context).postCommentReplyListingResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.postCommentLikeUserListing_api:
                    if (context instanceof LikesUserActivity)
                        ((LikesUserActivity) context).postLikeUserListingResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.postCommentReplyLikeUserListing_api:
                    if (context instanceof LikesUserActivity)
                        ((LikesUserActivity) context).postLikeUserListingResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.postCommentReplyLike_api:
                    if (context instanceof ReplyActivity)
                        ((ReplyActivity) context).postCommentReplyLikeResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.postCommentReplyRemove_api:
                    if (context instanceof ReplyActivity)
                        ((ReplyActivity) context).postCommentReplyDeleteResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.userNotificationList_api:
                    if (context instanceof NotificationListActivity)
                        ((NotificationListActivity) context).userNotificationList((Util.convertRetrofitResponce(response)));
                    break;
                case S.myFriendRequestList_api:
                    if (context instanceof FriendsProfileActivity)
                        ((FriendsProfileActivity) context).userPendingRequestList((Util.convertRetrofitResponce(response)));
                    break;
                case S.searchList_api:
                    if (context instanceof SearchActivity)
                        ((SearchActivity) context).searchUserResponse((Util.convertRetrofitResponce(response)));
                    break;
                case S.trackInfo_api:
                    if (context instanceof TrackDetailActivity)
                        ((TrackDetailActivity) context).trackInfoResponse((Util.convertRetrofitResponce(response)));
                    else if (context instanceof TrackInfoActivity)
                        ((TrackInfoActivity) context).trackInfoResponse((Util.convertRetrofitResponce(response)));
                    break;
                case S.trackDiaryPost_api:
                    if (context instanceof TrackDiaryActivity)
                        ((TrackDiaryActivity) context).postListingWebserviceResponse((Util.convertRetrofitResponce(response)));
                    break;
                case S.createGroup_api:
                    if (context instanceof CreateGroupActivity)
                        ((CreateGroupActivity) context).createGroupWebserviceResponse((Util.convertRetrofitResponce(response)));
                    break;
                case S.groupTypeList_api:
                    if (context instanceof UserGroupsActivity)
                        ((UserGroupsActivity) context).vehicleTypeWebserviceResponse((Util.convertRetrofitResponce(response)));
                    else if (context instanceof MyLapsActivity)
                        ((MyLapsActivity) context).vehicleTypeWebserviceResponse((Util.convertRetrofitResponce(response)));
                    else if (context instanceof CreateGroupActivity)
                        ((CreateGroupActivity) context).vehicleTypeWebserviceResponse((Util.convertRetrofitResponce(response)));
                    else if (context instanceof ExploreActivity)
                        ((ExploreActivity) context).vehicleTypeWebserviceResponse((Util.convertRetrofitResponce(response)));
                    else if (context instanceof EventActivity)
                        ((EventActivity) context).vehicleTypeWebserviceResponse((Util.convertRetrofitResponce(response)));
                    else if (context instanceof AddAlbumActivity)
                        ((AddAlbumActivity) context).vehicleTypeWebserviceResponse((Util.convertRetrofitResponce(response)));
                    else if (context instanceof UploadLapTimeActivity)
                        ((UploadLapTimeActivity) context).vehicleTypeWebserviceResponse((Util.convertRetrofitResponce(response)));
                    else if (context instanceof GalleryActivity)
                        ((GalleryActivity) context).vehicleTypeWebserviceResponse((Util.convertRetrofitResponce(response)));
                    else if (context instanceof ContactUsActivity)
                        ((ContactUsActivity) context).vehicleTypeWebserviceResponse((Util.convertRetrofitResponce(response)));
                    else if (context instanceof SettingMenuActivity)
                        ((SettingMenuActivity) context).vehicleTypeWebserviceResponse((Util.convertRetrofitResponce(response)));
                    break;
                case S.groupInfo_api:
                    if (context instanceof GroupProfileActivity)
                        ((GroupProfileActivity) context).groupInfoWebserviceResponse((Util.convertRetrofitResponce(response)));
                    if (context instanceof GroupInfoActivity)
                        ((GroupInfoActivity) context).groupInfoWebserviceResponse((Util.convertRetrofitResponce(response)));
                    break;
               /* case S.userGroupRequest_api:

                    break;*/
                case S.groupMemberList_api:
                    if (context instanceof GroupInfoActivity)
                        ((GroupInfoActivity) context).groupMemberWebserviceResponse((Util.convertRetrofitResponce(response)));
                    if (context instanceof GroupMemberActivity)
                        ((GroupMemberActivity) context).groupMemberWebserviceResponse((Util.convertRetrofitResponce(response)));
                    break;
                case S.groupListing_api:
                    if (context instanceof UserGroupsActivity)
                        ((UserGroupsActivity) context).groupsWebserviceResponse((Util.convertRetrofitResponce(response)));
                    break;
                case S.groupEventList_api:
                    if (context instanceof EventActivity)
                        // ((EventActivity) context).EventWebserviceResponse((Util.convertRetrofitResponce(response)),0);
                        break;
                case S.eventCustomerList_api:
                    if (context instanceof CommunityMoreInfoActivity)
                        ((CommunityMoreInfoActivity) context).EventMembersWebserviceResponse((Util.convertRetrofitResponce(response)));
                    break;
                case S.eventInfo_api:
                    if (context instanceof CommunityMoreInfoActivity)
                        ((CommunityMoreInfoActivity) context).EventInfoWebserviceResponse((Util.convertRetrofitResponce(response)));
                    break;
                case S.galleryListing_api:
                    if (context instanceof GalleryProfileActivity)
                        ((GalleryProfileActivity) context).galleryListWebserviceResponse((Util.convertRetrofitResponce(response)));

                    break;
                case S.userEventListing_api:
                    if (context instanceof EventActivity)
                        // ((EventActivity) context).EventWebserviceResponse((Util.convertRetrofitResponce(response)),0);
                        break;
                case S.updateNotification_api:
                    if (context instanceof NotificationListActivity)
                        ((NotificationListActivity) context).updateNotificationWebserviceResponse((Util.convertRetrofitResponce(response)));
                    break;
                case S.allTrackLapListings:
                    if (context instanceof LapsProfileActivity)
                        ((LapsProfileActivity) context).getMyLapsWebserviceResponse((Util.convertRetrofitResponce(response)));
                    break;
                case S.allTrackLapListingWithFriends:
                    if (context instanceof MyLapsActivity)
                        ((MyLapsActivity) context).getMyLapsWebserviceResponse((Util.convertRetrofitResponce(response)));
                    break;
                case S.lapsInTrackVersion:
                    if (context instanceof MyLapsActivity)
                        ((MyLapsActivity) context).getMyLapsWebserviceResponse((Util.convertRetrofitResponce(response)));
                    else if (context instanceof LapsProfileActivity)
                        ((LapsProfileActivity) context).getMyLapsWebserviceResponse((Util.convertRetrofitResponce(response)));
                    else if (context instanceof TrackTimeLaps)
                        ((TrackTimeLaps) context).getMyLapsWebserviceResponse((Util.convertRetrofitResponce(response)));
                    break;
                case S.albumListingVideo_api:
                    if (context instanceof GalleryActivity)
                        //((GalleryActivity) context).getGalleryVideoWebserviceResponse((Util.convertRetrofitResponce(response)));
                        break;
                case S.albumListingPhoto_api:
                    if (context instanceof GalleryActivity)
                        //((GalleryActivity) context).getGalleryPhotoWebserviceResponse((Util.convertRetrofitResponce(response)));
                        break;
                case S.upcomingEvent_api:
                    if (context instanceof ExploreActivity)
                        ((ExploreActivity) context).getExploreDataWebserviceResponse((Util.convertRetrofitResponce(response)));
                    break;
                case S.upcomingEventByDate_api:
                    if (context instanceof ExploreActivity)
                        ((ExploreActivity) context).getExploreDataWebserviceResponse((Util.convertRetrofitResponce(response)));
                    break;
                case S.getWeather_api:
                    if (context instanceof ExploreActivity)
                        ((ExploreActivity) context).getWeatherDataWebserviceResponse((Util.convertRetrofitResponce(response)));
                    break;
                case S.myGroupPostListing_api:
                    if (context instanceof GroupDiaryActivity)
                        ((GroupDiaryActivity) context).postListingWebserviceResponse((Util.convertRetrofitResponce(response)));
                    break;
                case S.preferredTrack_api:
                    if (context instanceof DashboardActivity)
                        ((DashboardActivity) context).preferedTrackWebserviceResponse((Util.convertRetrofitResponce(response)));
                    break;
                case S.myPostListing_api:
                    if (context instanceof DiaryActivity)
                        ((DiaryActivity) context).postListingWebserviceResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.advertList_api:
                    if (context instanceof DashboardActivity)
                        ((DashboardActivity) context).advertisementListWebserviceResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.subscriptionLists_api:
                    if (context instanceof SettingsActivity)
                        ((SettingsActivity) context).subscriptionListWebserviceResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.postShared_api:
                    if (context instanceof DashboardActivity)
                        ((DashboardActivity) context).sharedPostWebserviceResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.voucherConfirmation_api:
                    if (context instanceof SettingsActivity)
                        ((SettingsActivity) context).voucherCodeWebserviceResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.userSubscriptions_api:
                    if (context instanceof AddCardDetail)
                        ((AddCardDetail) context).userSubscriptionWebserviceResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.getChatFriendAndGroups_api:
                    if (context instanceof AllUsersActivity)
                        ((AllUsersActivity) context).myFriendListWebserviceResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.addCartAlbum_api:
                    if (context instanceof AlbumActivity)
                        ((AlbumActivity) context).addCartAlbumWebserviceResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.viewAlbumCart_api:
                    if (context instanceof CartAlbumActivity)
                        ((CartAlbumActivity) context).viewAlbumCartWebservcieResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.removeCartAlbum_api:
                    if (context instanceof CartAlbumActivity)
                        ((CartAlbumActivity) context).removeCartAlbumWebserviceResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.albumCheckout_api:
                    if (context instanceof CartAlbumActivity)
                        ((CartAlbumActivity) context).albumCheckoutWebserviceResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.trackDetailByLatLong_api:
                    if (context instanceof RecordCameraActivity)
                        ((RecordCameraActivity) context).trackDetailByLatLongWebserviceResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.userUpdateMapView_api:
                    if (context instanceof SettingMenuActivity)
                        ((SettingMenuActivity) context).getDefaultMapViewResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.reportAbuse_api:
                    if (context instanceof SettingMenuActivity)
                        ((SettingMenuActivity) context).getDefaultMapViewResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.youtubeData:

                    break;
                case S.uploadLaptime:
                    if (context instanceof UploadLapTimeActivity)
                        ((UploadLapTimeActivity) context).getUploadLapTimeDataResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.newTrackRequest:
                    if (context instanceof ContactUsActivity)
                        ((ContactUsActivity) context).newTrackRequestResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.changeEmailAddress:
                    if (context instanceof SettingMenuActivity)
                        ((SettingMenuActivity) context).getChangeEmailResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.removeNotification_api:
                    if (context instanceof NotificationListActivity)
                        ((NotificationListActivity) context).removeNotification(Util.convertRetrofitResponce(response));
                    break;
                case S.updateSessions:
                    if (context instanceof MyLapsActivity)
                        ((MyLapsActivity) context).updateLapTimeResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.postInfo:
                    if (context instanceof CommentActivity)
                        ((CommentActivity) context).postDetailResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.checkLapTimeCSV:
                    if (context instanceof UploadLapTimeActivity)
                        ((UploadLapTimeActivity) context).checkLapTimeCSV(Util.convertRetrofitResponce(response));
                    break;
                case S.userSubscribeList:
                    if (context instanceof DashboardActivity)
                        ((DashboardActivity) context).userSubscribeList(Util.convertRetrofitResponce(response));
                    else if (context instanceof SettingsActivity)
                        ((SettingsActivity) context).userSubscribeList(Util.convertRetrofitResponce(response));
                    break;
                case S.albumLikeUserListing:
                    if (context instanceof LikesUserActivity)
                        ((LikesUserActivity) context).postLikeUserListingResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.albumComment:
                    if (context instanceof AlbumCommentActivity)
                        ((AlbumCommentActivity) context).postCommentResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.albumCommentListing:
                    if (context instanceof AlbumCommentActivity)
                        ((AlbumCommentActivity) context).postCommentListingWebserviceResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.albumCommentLikeUserListing:
                    if (context instanceof LikesUserActivity)
                        ((LikesUserActivity) context).postLikeUserListingResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.albumCommentRemove_api:
                    if (context instanceof AlbumCommentActivity)
                        ((AlbumCommentActivity) context).postCommentRemoveWebserviceResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.albumCommentReplyLike_api:
                    if (context instanceof ReplyAlbumActivity)
                        ((ReplyAlbumActivity) context).postCommentReplyResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.albumCommentReplyListing:
                    if (context instanceof ReplyAlbumActivity)
                        ((ReplyAlbumActivity) context).postCommentReplyListingResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.albumCommentReply:
                    if (context instanceof ReplyAlbumActivity)
                        ((ReplyAlbumActivity) context).postCommentReplyResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.albumCommentReplyRemove:
                    if (context instanceof ReplyAlbumActivity)
                        ((ReplyAlbumActivity) context).postCommentReplyDeleteResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.albumCommentReplyLikeUserListing:
                    if (context instanceof LikesUserActivity)
                        ((LikesUserActivity) context).postLikeUserListingResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.albumShared_api:
                    if (context instanceof GalleryActivity)
                        ((GalleryActivity) context).sharedPostWebserviceResponse(Util.convertRetrofitResponce(response));
                    else if (context instanceof AlbumActivity)
                        ((AlbumActivity) context).sharedPostWebserviceResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.addalbum:
                    if (context instanceof AddAlbumActivity)
                        ((AddAlbumActivity) context).addAlbumResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.updateAlbum_api:
                    if (context instanceof AddAlbumActivity)
                        ((AddAlbumActivity) context).editAlbumResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.eventTicketPurchase:
                    if (context instanceof AddCardDetail)
                        ((AddCardDetail) context).eventTicketPurchaseResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.postRemove_api:
                    if (context instanceof DashboardActivity)
                        ((DashboardActivity) context).postRemoveResponse(Util.convertRetrofitResponce(response));
                    else if (context instanceof DiaryActivity)
                        ((DiaryActivity) context).postRemoveResponse(Util.convertRetrofitResponce(response));
                    else if (context instanceof GroupDiaryActivity)
                        ((GroupDiaryActivity) context).postRemoveResponse(Util.convertRetrofitResponce(response));
                    else if (context instanceof TrackDiaryActivity)
                        ((TrackDiaryActivity) context).postRemoveResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.updatePost_api:
                    if (context instanceof AddPostActivity)
                        ((AddPostActivity) context).savePostWebserviceResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.trackChangeHomeStatus_api:
                    if (context instanceof TrackDetailActivity)
                        ((TrackDetailActivity) context).trackHomeStatus(Util.convertRetrofitResponce(response));
                    else if (context instanceof DashboardActivity)
                        ((DashboardActivity) context).trackHomeStatus(Util.convertRetrofitResponce(response));
                    break;
                case S.generalInformationData:
                    if (context instanceof BaseActivity)
                        ((BaseActivity) context).generalInformationDataResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.advertLikeUserListing:
                    if (context instanceof LikesUserActivity)
                        ((LikesUserActivity) context).postLikeUserListingResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.advertComment:
                    if (context instanceof AdCommentActivity)
                        ((AdCommentActivity) context).postCommentResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.advertCommentListing_api:
                    if (context instanceof AdCommentActivity)
                        ((AdCommentActivity) context).postCommentListingWebserviceResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.advertCommentRemove_api:
                    if (context instanceof AdCommentActivity)
                        ((AdCommentActivity) context).postCommentRemoveWebserviceResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.albumRemove:
                    if (context instanceof GalleryActivity)
                        ((GalleryActivity) context).albumRemoveWebserviceResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.albumInfo:
                    if (context instanceof AlbumCommentActivity)
                        ((AlbumCommentActivity) context).getGalleryPhotoWebserviceResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.myPendingGroupRequest:
                    if (context instanceof PendingGroupRequestActivity)
                        ((PendingGroupRequestActivity) context).getPendingRequestResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.userGroupJoin:
                    if (context instanceof PendingGroupRequestActivity)
                        ((PendingGroupRequestActivity) context).getRequestStatusResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.userAddToAdminList:
                    if (context instanceof GroupMemberActivity)
                        ((GroupMemberActivity) context).getRequestStatusResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.userGroupMemberRemove:
                    if (context instanceof GroupMemberActivity)
                        ((GroupMemberActivity) context).getRequestStatusResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.sessionData:
                    if (context instanceof MyLapsActivity)
                        ((MyLapsActivity) context).getSessionDataResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.removeSessionImage:
                    if (context instanceof MyLapsActivity)
                        ((MyLapsActivity) context).getDeleteImageSessionDataResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.updateLapStatus1:
                    if (context instanceof MyLapsActivity)
                        ((MyLapsActivity) context).getUpdateLapResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.userUpdateGroupType:
                    if (context instanceof SettingMenuActivity)
                        ((SettingMenuActivity) context).getUpdateVehicleTypeResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.trackConditionList:
                    if (context instanceof DashboardActivity)
                        ((DashboardActivity) context).getTrackConditionResponse(Util.convertRetrofitResponce(response));
                    break;
                case S.trackProfile:
                    if (context instanceof MyLapsRaceActivity)
                        ((MyLapsRaceActivity) context).getTrackProfileResponse(Util.convertRetrofitResponce(response));
                    break;


            }
        } else {
            String message = "";
            try {
                JSONObject jObjError = new JSONObject(response.errorBody().string());
                message = jObjError.getString(S.message);
            } catch (Exception e) {
                message = S.error_unknown;
                e.printStackTrace();
            }
            Util.showAlertDialog(context, context.getString(R.string.alert), message);
        }
    }
}
