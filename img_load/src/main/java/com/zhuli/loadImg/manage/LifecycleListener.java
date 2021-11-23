package com.zhuli.loadImg.manage;

import androidx.lifecycle.LifecycleObserver;

/**
 * An interface for listener to {@link android.app.Fragment} and {@link android.app.Activity}
 * lifecycle events.
 */
public interface LifecycleListener extends LifecycleObserver {

  /**
   * Callback for when {@link android.app.Fragment#onStart()}} or {@link
   * android.app.Activity#onStart()} is called.
   */
  void onStart();

  /**
   * Callback for when {@link android.app.Fragment#onStop()}} or {@link
   * android.app.Activity#onStop()}} is called.
   */
  void onStop();

  /**
   * Callback for when {@link android.app.Fragment#onDestroy()}} or {@link
   * android.app.Activity#onDestroy()} is called.
   */
  void onDestroy();
}