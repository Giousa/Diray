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
import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "参数id不能为空")
    private String id;

    private String username;

    @JsonIgnore
    private String password;

    private String type;

    private String icon;

    private String nickname;

    private Integer gender;

    private Integer age;

    private Integer height;

    private Integer weight;

    private String birthday;

    private String sign;

    private int releases;

    private int funs;

    @JsonIgnore
    private int active;

    @CreatedDate
    private Date createTime;

    @JsonIgnore
    @LastModifiedDate
    private Date updateTime;
}
