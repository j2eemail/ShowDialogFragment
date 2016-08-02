package com.lxs.showdialogfragment.customDialog;

import android.os.Bundle;
import android.widget.Toast;

import com.lxs.showdialogfragment.Parameter;
import com.lxs.showdialogfragment.Print;
import com.lxs.showdialogfragment.R;

/**
 * Description：
 * User: lixishuang
 * Date: 2016-08-02
 * Time: 10:29
 */
public class DialogFragmentImpl extends BaseDialogFragment {

    private DialogListener dialogListener;
    private String title;
    private String content;
    private String confirm;
    private String cancel;
    private int anim;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_dialog;
    }

    @Override
    public void getArguments(Bundle bundle) {
        title = bundle.getString(Parameter.KEY_TITLE);
        content = bundle.getString(Parameter.KEY_CONTENT);
        confirm = bundle.getString(Parameter.KEY_CONFIRM);
        cancel = bundle.getString(Parameter.KEY_CANCEL);
        anim = bundle.getInt(Parameter.KEY_ANIM);
    }

    @Override
    public int getDialogFragmentAnim() {
        return anim;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        DialogStyle titleStyle = DialogStyle.TITLE.init(title);
        DialogStyle contentStyle = DialogStyle.CONTENT.init(content);
        DialogStyle confirmStyle = DialogStyle.CONFIRM.init(confirm);
        DialogStyle cancelStyle = DialogStyle.CANCEL.init(cancel);
        setDialogStyle(titleStyle, contentStyle, confirmStyle, cancelStyle);
    }

    @Override
    public void close() {
        Toast.makeText(getContext(), "close", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void confirm() {
        Toast.makeText(getContext(), "confirm", Toast.LENGTH_SHORT).show();
        if (dialogListener != null) {
            DialogParameter dialogParameter = new DialogParameter();
            dialogParameter.id = 1000;
            dialogListener.confirm(dialogParameter);
            dismiss();
        } else {
            Print.d("must implement DialogListener");
        }
    }

    @Override
    public void cancel() {
        Toast.makeText(getContext(), "cancel", Toast.LENGTH_SHORT).show();
    }

    public void setDialogListener(DialogListener dialogListener) {
        this.dialogListener = dialogListener;
    }
}
