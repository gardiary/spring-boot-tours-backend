package com.tours.persistence.entity;

import javax.persistence.*;

/**
 * Created by gardiary on 10/12/18.
 */
@Entity
@Table(name="TOURS")
public class TourEntity extends BaseEntity {

    @Column(name="external_id")
    private Long externalId;

    @Column(name="name")
    private String name;

    @Column(name="long_desc", columnDefinition = "text")
    private String longDesc;

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

    @Override
    public String toString() {
        return "TourEntity{" +
                "id=" + super.getId() +
                ", externalId=" + externalId +
                ", name='" + name + '\'' +
                ", longDesc='" + longDesc + '\'' +
                '}';
    }
}
