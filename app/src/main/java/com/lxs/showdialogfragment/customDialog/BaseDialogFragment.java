package com.lxs.showdialogfragment.customDialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lxs.showdialogfragment.Print;
import com.lxs.showdialogfragment.R;

/**
 * Description：
 * User: lixishuang
 * Date: 2016-06-28
 * Time: 10:46
 */
public abstract class BaseDialogFragment extends DialogFragment implements BaseDialogFragmentInter, View.OnClickListener {

    protected View view;
    protected ImageView close;
    protected Button confirm;
    protected Button cancel;
    protected int dialogAnim = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getArguments(getArguments());
        dialogAnim = getDialogFragmentAnim();
        setStyle(DialogFragment.STYLE_NO_TITLE, 0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        if (dialogAnim != 0) {
            getDialog().getWindow().setWindowAnimations(dialogAnim);
        }
        view = inflater.inflate(getContentViewId(), container, false);
        close = (ImageView) view.findViewById(R.id.fragment_dialog_close);
        confirm = (Button) view.findViewById(R.id.fragment_dialog_confirm);
        cancel = (Button) view.findViewById(R.id.fragment_dialog_cancel);
        if (close != null) {
            close.setOnClickListener(this);
        }
        if (confirm != null) {
            confirm.setOnClickListener(this);
        }
        if (cancel != null) {
            cancel.setOnClickListener(this);
        }
        initView();
        initListener();
        initData();
        return view;
    }

    @Override
    public void setDialogStyle(DialogStyle... dialogStyles) {
        if (dialogStyles != null && dialogStyles.length > 0) {
            for (DialogStyle dialogStyle : dialogStyles) {
                if (dialogStyle == DialogStyle.BACKGROUND) {
                    LinearLayout layout = (LinearLayout) view.findViewById(R.id.fragment_dialog_layout);
                    if (layout != null) {
                        setBGStyle(dialogStyle, layout);
                    } else {
                        Print.w(this.getClass().getSimpleName() + " -> 没有Dialog布局!");
                    }
                    continue;
                }
                if (dialogStyle == DialogStyle.TITLE) {//Title文字
                    RelativeLayout titleLayout = (RelativeLayout) view.findViewById(R.id.fragment_dialog_title_layout);
                    TextView title = (TextView) view.findViewById(R.id.fragment_dialog_title);
                    if (titleLayout != null && title != null) {
                        setTitleStyle(dialogStyle, titleLayout, title);
                    } else {
                        Print.w(this.getClass().getSimpleName() + " -> 没有Title布局!");
                    }
                    continue;
                }
                if (dialogStyle == DialogStyle.CLOSE) {//关闭按钮
                    if (close != null) {
                        setBtnStyle(dialogStyle, close);
                    } else {
                        Print.w(this.getClass().getSimpleName() + " -> 没有关闭按钮!");
                    }
                }
                if (dialogStyle == DialogStyle.CONTENT) {//内容
                    TextView content = (TextView) view.findViewById(R.id.fragment_dialog_content);
                    if (content != null) {
                        setContentStyle(dialogStyle, content);
                    } else {
                        Print.w(this.getClass().getSimpleName() + " -> 没有内容布局!");
                    }
                }
                if (dialogStyle == DialogStyle.CONFIRM) {//确认按钮
                    if (confirm != null) {
                        setBtnStyle(dialogStyle, confirm);
                    } else {
                        Print.w(this.getClass().getSimpleName() + " -> 没有确认按钮!");
                    }
                }
                if (dialogStyle == DialogStyle.CANCEL) {//取消按钮
                    if (cancel != null) {
                        setBtnStyle(dialogStyle, cancel);
                    } else {
                        Print.w(this.getClass().getSimpleName() + " -> 没有取消按钮!");
                    }
                }
            }
        }
    }

    /**
     * 添充背景
     *
     * @param dialogStyle
     */
    private void setBGStyle(DialogStyle dialogStyle, LinearLayout layout) {
        if (dialogStyle.resId != 0) {
            layout.setBackgroundResource(dialogStyle.resId);
        }
    }

    /**
     * 添充TITLE数据
     *
     * @param dialogStyle
     */
    private void setTitleStyle(DialogStyle dialogStyle, RelativeLayout titleLayout, TextView title) {
        if (dialogStyle.visibility) {
            title.setText(dialogStyle.content);
            if (dialogStyle.resId != 0) {
                titleLayout.setBackgroundResource(dialogStyle.resId);
            }
        } else {
            titleLayout.setVisibility(View.GONE);
        }
    }

    /**
     * 添充数据
     *
     * @param dialogStyle
     */
    private void setContentStyle(DialogStyle dialogStyle, TextView content) {
        if (!TextUtils.isEmpty(dialogStyle.content)) {
            content.setText(dialogStyle.content);
        }
    }

    /**
     * 按钮样式
     *
     * @param dialogStyle
     * @param view
     */
    private void setBtnStyle(DialogStyle dialogStyle, View view) {
        if (view instanceof ImageView) {
            if (dialogStyle.visibility) {
                ((ImageView) view).setImageResource(dialogStyle.resId);
            }
            return;
        }
        if (view instanceof Button) {
            if (dialogStyle.visibility && !TextUtils.isEmpty(dialogStyle.content)) {
                ((Button) view).setText(dialogStyle.content);
                if (dialogStyle.resId != 0) {
                    view.setBackgroundResource(dialogStyle.resId);
                }
            } else {
                view.setVisibility(View.GONE);
            }
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.fragment_dialog_close) {
            Print.i(this.getClass().getSimpleName() + " ->关闭按钮点击");
            close();
            dismiss();
        }
        if (view.getId() == R.id.fragment_dialog_confirm) {
            Print.i(this.getClass().getSimpleName() + " ->确定按钮点击");
            confirm();
        }
        if (view.getId() == R.id.fragment_dialog_cancel) {
            Print.i(this.getClass().getSimpleName() + " ->取消按钮点击");
            cancel();
            dismiss();
        }
    }
}
