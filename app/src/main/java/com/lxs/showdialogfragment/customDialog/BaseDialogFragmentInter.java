package com.lxs.showdialogfragment.customDialog;

import android.os.Bundle;

/**
 * Description：
 * User: lixishuang
 * Date: 2016-06-28
 * Time: 10:28
 */
public interface BaseDialogFragmentInter {

    int getContentViewId();

    void getArguments(Bundle bundle);

    int getDialogFragmentAnim();

    void setDialogStyle(DialogStyle... dialogStyles);

    void initView();

    void initListener();

    void initData();

    void close();

    void confirm();

    void cancel();
}
