package com.qujiali.jiaogegongren.bean;

public class SettlementEntity {


    /**
     * worker : null
     * enterprise : null
     * role : NONE
     */

    private Object worker;
    private Object enterprise;
    private String role;

    public Object getWorker() {
        return worker;
    }

    public void setWorker(Object worker) {
        this.worker = worker;
    }

    public Object getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Object enterprise) {
        this.enterprise = enterprise;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
