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
 * Date:2018/12/25
 * Email:65489469@qq.com
 */
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
public class CommentReply {

    @Id
    private String id;

    private String commentId;

    private String fromUid;

    private String toUid;

    private String content;

    @CreatedDate
    private Date createTime;

}
