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
import com.qujiali.jiaogegongren.bean.WorkerProficiencyEntity;
import com.qujiali.jiaogegongren.common.model.SelectSexWheelViewTwo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Xrf
 * @date : 2019/3/2414:47
 * desc   :选择性别弹出框
 */
public class SelectDialog extends Dialog {

    private Context context;
    private SelectSexWheelViewTwo selSex;
    List<WorkerProficiencyEntity> strings;

    public SelectDialog(Context context, List<WorkerProficiencyEntity> strings) {
        super(context, R.style.SelectPicDialog);
        this.context = context;
        this.strings = strings;

    }

    public interface SelectSexListener {
        void setSelectSex(String str, String typeId);
    }

    SelectSexListener selectSexListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_selectsextwo);//这行一定要写在前面
        selSex = findViewById(R.id.selSexTwo);
        TextView txt_confirm = findViewById(R.id.txt_confirm);
        TextView txt_cancel = findViewById(R.id.txt_cancel);
        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);
        selSex.setOffset(1);
        List<String> strings1 = new ArrayList<>();
        for (WorkerProficiencyEntity dsa : strings) {
            strings1.add(dsa.getDictLabel());
        }
        selSex.setItems(strings);
        txt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String seletedItem = selSex.getSeletedItem();
                selectSexListener.setSelectSex(seletedItem, selSex.getSeletedIdItem());
                dismiss();
            }
        });
        txt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void show(SelectSexListener selectSexListener) {
        this.selectSexListener = selectSexListener;
        show();
    }
}

