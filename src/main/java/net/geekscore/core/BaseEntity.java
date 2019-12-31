package net.geekscore.core;

import java.io.Serializable;

public class BaseEntity implements Serializable {

    private String id;

    private String createdBy;

    private String updatedBy;

    private Long createdTS = System.currentTimeMillis();

    private Long updatedTS = System.currentTimeMillis();

    private boolean deleted = Boolean.FALSE;

    private Integer version = 0;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Long getCreatedTS() {
        return this.createdTS;
    }

    public void setCreatedTS(Long createdTS) {
        this.createdTS = createdTS;
    }

    public Long getUpdatedTS() {
        return this.updatedTS;
    }

    public void setUpdatedTS(Long updatedTS) {
        this.updatedTS = updatedTS;
    }

    public boolean isDeleted() {
        return this.deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
