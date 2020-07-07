package com.qujiali.jiaogegongren.bean;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

public class AddressEntity {

    private List<ProvinceBean> province;
    private List<CityBean> city;
    private List<DistrictBean> district;

    public List<ProvinceBean> getProvince() {
        return province;
    }

    public void setProvince(List<ProvinceBean> province) {
        this.province = province;
    }

    public List<CityBean> getCity() {
        return city;
    }

    public void setCity(List<CityBean> city) {
        this.city = city;
    }

    public List<DistrictBean> getDistrict() {
        return district;
    }

    public void setDistrict(List<DistrictBean> district) {
        this.district = district;
    }

    public static class ProvinceBean implements IPickerViewData{
        /**
         * p : 100000
         * i : 110000
         * n : 北京市
         */

        private String p;
        private String i;
        private String n;

        public String getP() {
            return p;
        }

        public void setP(String p) {
            this.p = p;
        }

        public String getI() {
            return i;
        }

        public void setI(String i) {
            this.i = i;
        }

        public String getN() {
            return n;
        }

        public void setN(String n) {
            this.n = n;
        }

        @Override
        public String getPickerViewText() {
            return n;
        }
    }

    public static class CityBean implements IPickerViewData{
        /**
         * p : 110000
         * i : 110100
         * n : 北京市
         */

        private String p;
        private String i;
        private String n;

        public String getP() {
            return p;
        }

        public void setP(String p) {
            this.p = p;
        }

        public String getI() {
            return i;
        }

        public void setI(String i) {
            this.i = i;
        }

        public String getN() {
            return n;
        }

        public void setN(String n) {
            this.n = n;
        }

        @Override
        public String getPickerViewText() {
            return n;
        }
    }

    public static class DistrictBean implements IPickerViewData {
        /**
         * p : 110100
         * i : 110101
         * n : 东城区
         */

        private String p;
        private String i;
        private String n;

        public String getP() {
            return p;
        }

        public void setP(String p) {
            this.p = p;
        }

        public String getI() {
            return i;
        }

        public void setI(String i) {
            this.i = i;
        }

        public String getN() {
            return n;
        }

        public void setN(String n) {
            this.n = n;
        }

        @Override
        public String getPickerViewText() {
            return n;
        }
    }
}
