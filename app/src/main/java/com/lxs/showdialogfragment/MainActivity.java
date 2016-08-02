package com.lxs.showdialogfragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.lxs.showdialogfragment.customDialog.DialogFragmentImpl;
import com.lxs.showdialogfragment.customDialog.DialogListener;
import com.lxs.showdialogfragment.customDialog.DialogParameter;

public class MainActivity extends AppCompatActivity {

    int anim = R.style.fragmentDialogAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showDialogFragment(View view) {
        DialogFragmentImpl dialogFragmentImpl = new DialogFragmentImpl();
        Bundle bundle = new Bundle();
        bundle.putString(Parameter.KEY_TITLE, "测试Title");
        bundle.putString(Parameter.KEY_CONTENT, "测试内容");
        bundle.putString(Parameter.KEY_CONFIRM, "确定");
        bundle.putString(Parameter.KEY_CANCEL, "取消");
        bundle.putInt(Parameter.KEY_ANIM, anim);
        dialogFragmentImpl.setArguments(bundle);
        dialogFragmentImpl.setCancelable(false);
        dialogFragmentImpl.setDialogListener(new DialogListener() {
            @Override
            public void confirm(DialogParameter dialogParameter) {
                Toast.makeText(getApplicationContext(),"DialogParameter",Toast.LENGTH_SHORT).show();
            }
        });
        dialogFragmentImpl.show(getSupportFragmentManager(), "custom_dialog");
    }
}
