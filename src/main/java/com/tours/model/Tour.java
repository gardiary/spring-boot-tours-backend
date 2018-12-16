package com.tours.model;

/**
 * Created by gardiary on 10/12/18.
 */
public class Tour extends BaseModel {
    private Long externalId;
    private String name;
    private String longDesc;

    public Tour() {
    }

    public Tour(Long externalId, String name, String longDesc) {
        this.externalId = externalId;
        this.name = name;
        this.longDesc = longDesc;
    }

    public Long getExternalId() {
        return externalId;
    }

    public void setExternalId(Long externalId) {
        this.externalId = externalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }
}
