package com.example.chatbox.Utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.chatbox.Model.UserModel;

public class AndroidUtils {
    public static void setProfilePic(Context context, Uri imageUri, ImageView imageView){
        Glide.with(context).load(imageUri).apply(RequestOptions.circleCropTransform()).into(imageView);
    }
    public static void passUserModelAsIntent(Intent intent, UserModel model){
        intent.putExtra("username",model.getUsername());
        intent.putExtra("phone",model.getPhone());
        intent.putExtra("userId",model.getUserId());

    }


    public static boolean isTextInput(String message) {
        return message.matches(".*\\S.*");
    }
    public static boolean isImageInput(Context context, Uri uri) {
        ContentResolver contentResolver = context.getContentResolver();
        String type = contentResolver.getType(uri);

        return type != null && type.startsWith("image/");
    }

}
