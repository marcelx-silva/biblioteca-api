package com.newgo.bibliotecaapi.model;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/**
 * Classe created to Audit by tracking persisted data of entities who inherited from this class.
 * The AbstractAuditableEntity tracks the following data:
 * <strong>CreateDate</strong>, <strong>CreatedBy</strong>,
 * <strong>CreateDate</strong>, <strong>ModifiedDate</strong>,
 * <strong>ModifiedBy</strong>.
 * @author Thiago Marcelo
 * */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditableEntity {
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createDate;
    @CreatedBy
    protected String createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    protected Date lastModifiedDate;
    @LastModifiedBy
    protected String lastModifiedBy;

}
