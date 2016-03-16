package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_app_update_log")
public class ApkUploadLog {
    private int id;
    /***
     * apk path
     */
    private String apk_path;
    private String version;
    private String os_type;
    private String client_time;
    private String reserved;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApk_path() {
        return apk_path;
    }

    public void setApk_path(String apk_path) {
        this.apk_path = apk_path;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getOs_type() {
        return os_type;
    }

    public void setOs_type(String os_type) {
        this.os_type = os_type;
    }

    public String getClient_time() {
        return client_time;
    }

    public void setClient_time(String client_time) {
        this.client_time = client_time;
    }

    public String getReserved() {
        return reserved;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
    }

}
