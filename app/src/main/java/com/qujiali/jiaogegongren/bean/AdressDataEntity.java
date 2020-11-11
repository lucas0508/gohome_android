package com.qujiali.jiaogegongren.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.contrarywind.interfaces.IPickerViewData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AdressDataEntity implements Parcelable , IPickerViewData {


    private List<RowsBean> rows;

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    @Override
    public String getPickerViewText() {
        return null;
    }

    public static class RowsBean implements Serializable{

        private String enable;
        private String adCode;
        private String name;
        private List<ChildBeanXX> child;

        public String getEnable() {
            return enable;
        }

        public void setEnable(String enable) {
            this.enable = enable;
        }

        public String getAdCode() {
            return adCode;
        }

        public void setAdCode(String adCode) {
            this.adCode = adCode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ChildBeanXX> getChild() {
            return child;
        }

        public void setChild(List<ChildBeanXX> child) {
            this.child = child;
        }

        public static class ChildBeanXX implements Parcelable {
            /**
             * enable : 2
             * adCode : 110000
             * name : 北京市
             * child : [{"enable":"3","adCode":"110100","name":"北京市","child":[{"enable":"4","adCode":"110101","name":"东城区"},{"enable":"4","adCode":"110102","name":"西城区"},{"enable":"4","adCode":"110105","name":"朝阳区"},{"enable":"4","adCode":"110106","name":"丰台区"},{"enable":"4","adCode":"110107","name":"石景山区"},{"enable":"4","adCode":"110108","name":"海淀区"},{"enable":"4","adCode":"110109","name":"门头沟区"},{"enable":"4","adCode":"110111","name":"房山区"},{"enable":"4","adCode":"110112","name":"通州区"},{"enable":"4","adCode":"110113","name":"顺义区"},{"enable":"4","adCode":"110114","name":"昌平区"},{"enable":"4","adCode":"110115","name":"大兴区"},{"enable":"4","adCode":"110116","name":"怀柔区"},{"enable":"4","adCode":"110117","name":"平谷区"},{"enable":"4","adCode":"110118","name":"密云区"},{"enable":"4","adCode":"110119","name":"延庆区"}]}]
             */

            private String enable;
            private String adCode;
            private String name;

            private List<ChildBeanX> child;

            public String getEnable() {
                return enable;
            }

            public void setEnable(String enable) {
                this.enable = enable;
            }

            public String getAdCode() {
                return adCode;
            }

            public void setAdCode(String adCode) {
                this.adCode = adCode;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<ChildBeanX> getChild() {
                return child;
            }

            public void setChild(List<ChildBeanX> child) {
                this.child = child;
            }

            public static class ChildBeanX implements Parcelable {
                /**
                 * enable : 3
                 * adCode : 110100
                 * name : 北京市
                 * child : [{"enable":"4","adCode":"110101","name":"东城区"},{"enable":"4","adCode":"110102","name":"西城区"},{"enable":"4","adCode":"110105","name":"朝阳区"},{"enable":"4","adCode":"110106","name":"丰台区"},{"enable":"4","adCode":"110107","name":"石景山区"},{"enable":"4","adCode":"110108","name":"海淀区"},{"enable":"4","adCode":"110109","name":"门头沟区"},{"enable":"4","adCode":"110111","name":"房山区"},{"enable":"4","adCode":"110112","name":"通州区"},{"enable":"4","adCode":"110113","name":"顺义区"},{"enable":"4","adCode":"110114","name":"昌平区"},{"enable":"4","adCode":"110115","name":"大兴区"},{"enable":"4","adCode":"110116","name":"怀柔区"},{"enable":"4","adCode":"110117","name":"平谷区"},{"enable":"4","adCode":"110118","name":"密云区"},{"enable":"4","adCode":"110119","name":"延庆区"}]
                 */

                private String enable;
                private String adCode;
                private String name;
                private List<ChildBean> child;

                public String getEnable() {
                    return enable;
                }

                public void setEnable(String enable) {
                    this.enable = enable;
                }

                public String getAdCode() {
                    return adCode;
                }

                public void setAdCode(String adCode) {
                    this.adCode = adCode;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public List<ChildBean> getChild() {
                    return child;
                }

                public void setChild(List<ChildBean> child) {
                    this.child = child;
                }

                public static class ChildBean implements Parcelable {
                    /**
                     * enable : 4
                     * adCode : 110101
                     * name : 东城区
                     */

                    private String enable;
                    private String adCode;
                    private String name;
                    private String addressCode;
                    private boolean ischeck;

                    public String getAddresscode() {
                        return addressCode;
                    }

                    public void setAddresscode(String addresscode) {
                        this.addressCode = addresscode;
                    }

                    public boolean isIscheck() {
                        return ischeck;
                    }

                    public void setIscheck(boolean ischeck) {
                        this.ischeck = ischeck;
                    }

                    public String getEnable() {
                        return enable;
                    }

                    public void setEnable(String enable) {
                        this.enable = enable;
                    }

                    public String getAdCode() {
                        return adCode;
                    }

                    public void setAdCode(String adCode) {
                        this.adCode = adCode;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }


                    @Override
                    public int describeContents() {
                        return 0;
                    }

                    @Override
                    public void writeToParcel(Parcel dest, int flags) {
                        dest.writeString(this.enable);
                        dest.writeString(this.adCode);
                        dest.writeString(this.addressCode);
                        dest.writeString(this.name);
                    }

                    public ChildBean() {
                    }

                    protected ChildBean(Parcel in) {
                        this.enable = in.readString();
                        this.adCode = in.readString();
                        this.addressCode=in.readString();
                        this.name=in.readString();
                    }

                    public static final Creator<ChildBean> CREATOR = new Creator<ChildBean>() {
                        @Override
                        public ChildBean createFromParcel(Parcel source) {
                            return new ChildBean(source);
                        }

                        @Override
                        public ChildBean[] newArray(int size) {
                            return new ChildBean[size];
                        }
                    };
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.enable);
                    dest.writeString(this.adCode);
                    dest.writeString(this.name);
                    dest.writeTypedList(this.child);
                }

                public ChildBeanX() {
                }

                protected ChildBeanX(Parcel in) {
                    this.enable = in.readString();
                    this.adCode = in.readString();
                    this.name = in.readString();
                    this.child = in.createTypedArrayList(ChildBean.CREATOR);
                }

                public static final Creator<ChildBeanX> CREATOR = new Creator<ChildBeanX>() {
                    @Override
                    public ChildBeanX createFromParcel(Parcel source) {
                        return new ChildBeanX(source);
                    }

                    @Override
                    public ChildBeanX[] newArray(int size) {
                        return new ChildBeanX[size];
                    }
                };
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.enable);
                dest.writeString(this.adCode);
                dest.writeString(this.name);
                dest.writeTypedList(this.child);
            }

            public ChildBeanXX() {
            }

            protected ChildBeanXX(Parcel in) {
                this.enable = in.readString();
                this.adCode = in.readString();
                this.name = in.readString();
                this.child = in.createTypedArrayList(ChildBeanX.CREATOR);
            }

            public static final Creator<ChildBeanXX> CREATOR = new Creator<ChildBeanXX>() {
                @Override
                public ChildBeanXX createFromParcel(Parcel source) {
                    return new ChildBeanXX(source);
                }

                @Override
                public ChildBeanXX[] newArray(int size) {
                    return new ChildBeanXX[size];
                }
            };
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.rows);
    }

    public AdressDataEntity() {
    }

    protected AdressDataEntity(Parcel in) {
        this.rows = new ArrayList<RowsBean>();
        in.readList(this.rows, RowsBean.class.getClassLoader());
    }

    public static final Creator<AdressDataEntity> CREATOR = new Creator<AdressDataEntity>() {
        @Override
        public AdressDataEntity createFromParcel(Parcel source) {
            return new AdressDataEntity(source);
        }

        @Override
        public AdressDataEntity[] newArray(int size) {
            return new AdressDataEntity[size];
        }
    };
}
