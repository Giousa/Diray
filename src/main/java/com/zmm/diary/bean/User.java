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
public class User {

    @Id
    private String id;

    private String username;

    private String password;

    private String type;

    private String icon;

    private String nickname;

    private Integer gender;

    private Integer age;

    private Integer height;

    private Integer weight;

    private String birthday;

    private Integer active;

    @CreatedDate
    private Date createTime;

//    @JsonSerialize(using = Date2LongSerializer.class)
    @LastModifiedDate
    private Date updateTime;
}
