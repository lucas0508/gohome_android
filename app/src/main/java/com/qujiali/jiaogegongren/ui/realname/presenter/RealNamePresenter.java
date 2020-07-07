package com.qujiali.jiaogegongren.ui.realname.presenter;

import com.qujiali.jiaogegongren.bean.RealNameEntity;
import com.qujiali.jiaogegongren.common.base.BasePresenter;
import com.qujiali.jiaogegongren.common.base.IBaseModel;
import com.qujiali.jiaogegongren.common.model.widget.DoubleTimeSelectDialog;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.common.net.ResponseEntity;
import com.qujiali.jiaogegongren.ui.realname.activity.IRealNameView;
import com.qujiali.jiaogegongren.ui.realname.model.RealNameNextModel;
import com.qujiali.jiaogegongren.ui.realname.model.impl.IRealNameNextModel;

import java.util.Map;

public class RealNamePresenter extends BasePresenter {

    private IRealNameView mRealNameView;

    private IRealNameNextModel mCertificationNextModel = new RealNameNextModel();

    public RealNamePresenter(IRealNameView mRealNameView) {
        this.mRealNameView = mRealNameView;
    }

    /**
     * 上传身份证信息
     *
     * @param idCardA
     * @param idCardB
     */
    public void uploadCertificationInfo(String idCardA, String idCardB) {
        Map<String, Object> formData = getEmptyFormData();
        formData.put("pictureOne", idCardA);
        formData.put("pictureTwo", idCardB);
        mCertificationNextModel.uploadCertificationInfo(formData, new IBaseModel.OnCallbackListener() {
            @Override
            public void callback(ResponseEntity res) {
                if (HttpProvider.isSuccessful(res.getCode())) {
                    mRealNameView.uploadCertificationInfoSuccess();
                } else {
                    mRealNameView.uploadCertificationInfoFail(res.getMsg());
                }
            }
        });
    }


    public void queryRealNameData() {
        mCertificationNextModel.queryCertificationInfo(res -> {
            if (HttpProvider.isSuccessful(res.getCode())) {
                mRealNameView.queryCertificationInfoSuccess((RealNameEntity) res.getData());
            } else {
                mRealNameView.queryCertificationInfoFail(res.getMsg());
            }
        });
    }
}
