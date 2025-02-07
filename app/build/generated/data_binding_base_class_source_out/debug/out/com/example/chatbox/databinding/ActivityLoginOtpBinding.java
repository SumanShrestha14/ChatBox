// Generated by view binder compiler. Do not edit!
package com.example.chatbox.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.chatbox.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityLoginOtpBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ProgressBar loginProgressBar;

  @NonNull
  public final EditText otp;

  @NonNull
  public final Toolbar toolbar;

  @NonNull
  public final Button verifyOTPBtn;

  private ActivityLoginOtpBinding(@NonNull LinearLayout rootView,
      @NonNull ProgressBar loginProgressBar, @NonNull EditText otp, @NonNull Toolbar toolbar,
      @NonNull Button verifyOTPBtn) {
    this.rootView = rootView;
    this.loginProgressBar = loginProgressBar;
    this.otp = otp;
    this.toolbar = toolbar;
    this.verifyOTPBtn = verifyOTPBtn;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityLoginOtpBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityLoginOtpBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_login_otp, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityLoginOtpBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.loginProgressBar;
      ProgressBar loginProgressBar = ViewBindings.findChildViewById(rootView, id);
      if (loginProgressBar == null) {
        break missingId;
      }

      id = R.id.otp;
      EditText otp = ViewBindings.findChildViewById(rootView, id);
      if (otp == null) {
        break missingId;
      }

      id = R.id.toolbar;
      Toolbar toolbar = ViewBindings.findChildViewById(rootView, id);
      if (toolbar == null) {
        break missingId;
      }

      id = R.id.verifyOTP_btn;
      Button verifyOTPBtn = ViewBindings.findChildViewById(rootView, id);
      if (verifyOTPBtn == null) {
        break missingId;
      }

      return new ActivityLoginOtpBinding((LinearLayout) rootView, loginProgressBar, otp, toolbar,
          verifyOTPBtn);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
