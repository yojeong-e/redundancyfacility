package org.foodsafety.importfood.redundancyfacility.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass
@Getter
@Setter
public class BaseTimeEntity {

    //ToDo : String to LocalDateTime
    @CreatedDate
    @Column(updatable = false)
    private String regTime;

    @LastModifiedDate
    private String updateTime;


}
