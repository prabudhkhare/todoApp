package com.spring.arm.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseEntity implements Persistable, Serializable {
    private static final long serialVersionUID = 1L;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on" ,nullable = false, updatable = false)
    private Date createdOn;

    @Column(name="created_by",updatable = false)
    private String createdBy;

    @Column(name="record_status", columnDefinition="char default 'C'",insertable = false)
    private char recordStatus='C';

    @Transient
    private boolean isNew=false;

    private void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    @Override
    public Object getId() {
        return null;
    }

    /** Recommended but not mandatory to be called on objects for new records to be inserted into the database.
     * If not called then 'Select' query will be executed before 'insert' query.
     * */
    public void markNew(){
        this.isNew=true;
    }


}
