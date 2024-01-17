//package com.example.project_final_ali.Controller;
//
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//
//import com.example.project_final_ali.Module.User;
//import com.example.project_final_ali.Ui.LoginActivity;
//
//public class SessionManager {
//
//    private static final String SHARED_PREF_NAME ="userToken";
//    private static final String KEY_NAME ="name";
//    private static final String KEY_EMAIL ="email";
//    private static final String KEY_ID ="user_id";
//    private static final String KEY_TOKEN="token";
//
//    private static SessionManager mInstance;
//    private static Context mCtx;
//
//    public SessionManager(Context context) {
//
//
//    }
//
//    public void userlogin(User user){
//        SharedPreferences sharedPreferences =
//                mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putInt(KEY_ID,user.getId());
//        editor.putString(KEY_NAME,user.getFullname());
//        editor.putString(KEY_EMAIL,user.getEmail());
//        editor.putString(KEY_ID,user.getType());
//
//        editor.putString(KEY_TOKEN,user.getToken());
//        editor.apply();
//    }
//
//    public boolean isLoggedIn(){
//        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//
//        return sharedPreferences.getString(KEY_ID,null) != null;
//
//    }
//
//    public void userlogout() {
//        SharedPreferences sharedPreferences =
//                mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.clear();
//        editor.apply();
//
//        mCtx.startActivity(new Intent( mCtx, LoginActivity.class));
//    }
//
//
////    public User getUser( User user){
////        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
////
////        return new User(
////                sharedPreferences.getInt(KEY_ID,-1),
////                sharedPreferences.getString(KEY_NAME,null),
////                sharedPreferences.getString(KEY_EMAIL,null));
////
////
////    }
//
//}
