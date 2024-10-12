// Generated by view binder compiler. Do not edit!
package com.example.chatbox.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.chatbox.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityWinDialogBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final TextView messageTV;

  @NonNull
  public final AppCompatButton startNewBtn;

  private ActivityWinDialogBinding(@NonNull RelativeLayout rootView, @NonNull TextView messageTV,
      @NonNull AppCompatButton startNewBtn) {
    this.rootView = rootView;
    this.messageTV = messageTV;
    this.startNewBtn = startNewBtn;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityWinDialogBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityWinDialogBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_win_dialog, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityWinDialogBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.messageTV;
      TextView messageTV = ViewBindings.findChildViewById(rootView, id);
      if (messageTV == null) {
        break missingId;
      }

      id = R.id.startNewBtn;
      AppCompatButton startNewBtn = ViewBindings.findChildViewById(rootView, id);
      if (startNewBtn == null) {
        break missingId;
      }

      return new ActivityWinDialogBinding((RelativeLayout) rootView, messageTV, startNewBtn);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
