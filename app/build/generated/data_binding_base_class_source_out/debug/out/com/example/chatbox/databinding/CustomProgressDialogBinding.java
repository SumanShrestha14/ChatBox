// Generated by view binder compiler. Do not edit!
package com.example.chatbox.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.chatbox.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class CustomProgressDialogBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final Button cancelButton;

  @NonNull
  public final ProgressBar progress;

  private CustomProgressDialogBinding(@NonNull RelativeLayout rootView,
      @NonNull Button cancelButton, @NonNull ProgressBar progress) {
    this.rootView = rootView;
    this.cancelButton = cancelButton;
    this.progress = progress;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static CustomProgressDialogBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static CustomProgressDialogBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.custom_progress_dialog, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static CustomProgressDialogBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.cancelButton;
      Button cancelButton = ViewBindings.findChildViewById(rootView, id);
      if (cancelButton == null) {
        break missingId;
      }

      id = android.R.id.progress;
      ProgressBar progress = ViewBindings.findChildViewById(rootView, id);
      if (progress == null) {
        break missingId;
      }

      return new CustomProgressDialogBinding((RelativeLayout) rootView, cancelButton, progress);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
