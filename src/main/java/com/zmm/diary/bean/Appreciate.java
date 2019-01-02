package com.zmm.diary.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.util.Date;

/**
 * Description: 点赞
 * Author:zhangmengmeng
 * Date:2018/12/27
 * Email:65489469@qq.com
 */
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
public class Appreciate {

    @Id
    private String id;

    private String userId;

    private String hotspotId;

    private boolean active;

    @JsonIgnore
    @CreatedDate
    private Date createTime;

    @JsonIgnore
    @LastModifiedDate
    private Date updateTime;

}
