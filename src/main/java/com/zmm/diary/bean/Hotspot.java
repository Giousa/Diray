package com.zmm.diary.bean;

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
 * Description:
 * Author:zhangmengmeng
 * Date:2018/11/1
 * Email:65489469@qq.com
 */

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
public class Hotspot {

    @Id
    private String id;

    private String uId;

    private String pic;

    private String content;

    private int collect;

    @CreatedDate
    private Date createTime;

    @LastModifiedDate
    private Date updateTime;
}
