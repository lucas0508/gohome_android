package com.qujiali.jiaogegongren.ui.main.fragment.view;
import com.qujiali.jiaogegongren.bean.VersionUpdateEntity;

import java.util.Map;


public interface IVersionUpdateView {

    /**
     * 版本更新成功
     */
    void updateVersionSuccess(VersionUpdateEntity versionUpdateEntity);

    /**
     * 版本更新失败
     */
    void updateVersionFail(String info);

}
