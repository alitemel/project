package com.ath2o.models;

public class VehicleRestRequestModel {

    private String searchCriteria;
    private String searchKey;

    public VehicleRestRequestModel(String searchCriteria, String searchKey) {
        this.searchCriteria = searchCriteria;
        this.searchKey = searchKey;
    }

    public String getSearchCriteria() {
        return searchCriteria;
    }

    public void setSearchCriteria(String searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }
}
