package com.qujiali.jiaogegongren.common.model.pop;


import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;

public class CommonPopupWindow extends PopupWindow {
    final PopupController controller;

    @Override
    public int getWidth() {
        return controller.mPopupView.getMeasuredWidth();
    }

    @Override
    public int getHeight() {
        return controller.mPopupView.getMeasuredHeight();
    }

    public interface ViewInterface {
        void getChildView(View view, int layoutResId);
    }

    private CommonPopupWindow(Context context) {
        controller = new PopupController(context, this);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        controller.setBackGroundLevel(1.0f);
    }

    public static class Builder {
        private final PopupController.PopupParams params;
        private ViewInterface listener;

        public Builder(Context context) {
            params = new PopupController.PopupParams(context);
        }

        /**
         * @param layoutResId 设置PopupWindow 布局ID
         * @return Builder
         */
        public Builder setView(int layoutResId) {
            params.mView = null;
            params.layoutResId = layoutResId;
            return this;
        }

        /**
         * @param view 设置PopupWindow布局
         * @return Builder
         */
        public Builder setView(View view) {
            params.mView = view;
            params.layoutResId = 0;
            return this;
        }

        /**
         * 设置子View
         *
         * @param listener ViewInterface
         * @return Builder
         */
        public Builder setViewOnclickListener(ViewInterface listener) {
            this.listener = listener;
            return this;
        }

        /**
         * 设置宽度和高度 如果不设置 默认是wrap_content
         *
         * @param width 宽
         * @return Builder
         */
        public Builder setWidthAndHeight(int width, int height) {
            params.mWidth = width;
            params.mHeight = height;
            return this;
        }

        /**
         * 设置背景灰色程度
         *
         * @param level 0.0f-1.0f
         * @return Builder
         */
        public Builder setBackGroundLevel(float level) {
            params.isShowBg = true;
            params.bg_level = level;
            return this;
        }

        /**
         * 是否可点击Outside消失
         *
         * @param touchable 是否可点击
         * @return Builder
         */
        public Builder setOutsideTouchable(boolean touchable) {
            params.isTouchable = touchable;
            return this;
        }

        /**
         * 设置动画
         *
         * @return Builder
         */
        public Builder setAnimationStyle(int animationStyle) {
            params.isShowAnim = true;
            params.animationStyle = animationStyle;
            return this;
        }

        public CommonPopupWindow create() {
            final CommonPopupWindow popupWindow = new CommonPopupWindow(params.mContext);
            params.apply(popupWindow.controller);
            if (listener != null && params.layoutResId != 0) {
                listener.getChildView(popupWindow.controller.mPopupView, params.layoutResId);
            }
            measureWidthAndHeight(popupWindow.controller.mPopupView);
            return popupWindow;
        }
    }

    /**
     * 测量View的宽高
     *
     * @param view View
     */
    public static void measureWidthAndHeight(View view) {
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 对数据进行排序
     *
     * @param list 要进行排序的数据源
     */
//    public static void sortData(List<ContactBean> list) {
//        if (list == null || list.size() == 0) return;
//        for (int i = 0; i < list.size(); i++) {
//            ContactBean bean = list.get(i);
//            String tag = Pinyin.toPinyin(bean.getName().substring(0, 1).charAt(0)).substring(0, 1);
//            if (tag.matches("[A-Z]")) {
//                bean.setIndexTag(tag);
//            } else {
//                bean.setIndexTag("#");
//            }
//        }
//        Collections.sort(list, new Comparator<ContactBean>() {
//            @Override
//            public int compare(ContactBean o1, ContactBean o2) {
//                if ("#".equals(o1.getIndexTag())) {
//                    return 1;
//                } else if ("#".equals(o2.getIndexTag())) {
//                    return -1;
//                } else {
//                    return o1.getIndexTag().compareTo(o2.getIndexTag());
//                }
//            }
//        });
//    }

    /**
     * @param beans 数据源
     * @return tags 返回一个包含所有Tag字母在内的字符串
     */
//    public static String getTags(List<ContactBean> beans) {
//        StringBuilder builder = new StringBuilder();
//        for (int i = 0; i < beans.size(); i++) {
//            if (!builder.toString().contains(beans.get(i).getIndexTag())) {
//                builder.append(beans.get(i).getIndexTag());
//            }
//        }
//        return builder.toString();
//    }
}
