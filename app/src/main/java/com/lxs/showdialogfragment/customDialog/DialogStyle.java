package com.lxs.showdialogfragment.customDialog;

/**
 * Description：
 * User: lixishuang
 * Date: 2016-06-28
 * Time: 9:53
 */
public enum DialogStyle {

    BACKGROUND, TITLE, CLOSE, CONTENT, CONFIRM, CANCEL;

    private DialogStyle() {

    }

    //是否显示
    public boolean visibility = true;
    //内容
    public String content;
    //资源ID
    public int resId;
    //按钮
    public String btnContent;

    public DialogStyle init(boolean visibility) {//隐藏TITLE或关闭按钮或取消按钮
        this.visibility = visibility;
        return this;
    }

    public DialogStyle init(String content) {//设置TITLE标题或显示内容
        this.content = content;
        return this;
    }

    public DialogStyle init(String content, int resId) {//设置TITLE标题及TITLE背景色
        this.content = content;
        this.resId = resId;
        return this;
    }

    public DialogStyle init(int resId) {//设置关闭按钮
        this.resId = resId;
        return this;
    }

    public DialogStyle init(String content, String btnContent) {//按钮
        this.content = content;
        this.btnContent = btnContent;
        return this;
    }

    public DialogStyle init(String content, int resId, String btnContent) {//按钮更改背景
        this.content = content;
        this.resId = resId;
        this.btnContent = btnContent;
        return this;
    }
}
