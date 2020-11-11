package com.qujiali.jiaogegongren.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.qujiali.jiaogegongren.R;
import java.util.Arrays;
import io.itimetraveler.widget.pickerselector.DateWheelPicker;

/**
 * @author : Xrf
 * @date : 2019/3/2414:47
 * desc   :选择性别弹出框
 */
public class SelectAgeDialog extends Dialog {

    private Context context;
    private DateWheelPicker selSex;

    public SelectAgeDialog(Context context) {
        super(context, R.style.SelectPicDialog);
        this.context = context;

    }

    public interface SelectSexListener {
        void setSelectSex(String str, String sex);
    }

    SelectSexListener selectSexListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_selectsex);//这行一定要写在前面

        selSex = findViewById(R.id.selSex);
        TextView txt_confirm = findViewById(R.id.txt_confirm);
        TextView txt_cancel = findViewById(R.id.txt_cancel);
        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);

        txt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void show(SelectSexListener selectSexListener) {
        this.selectSexListener=selectSexListener;
        show();
    }
}

