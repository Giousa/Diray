package com.zmm.diary.bean;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.util.Date;

/**
 * Description: 评论
 * Author:zhangmengmeng
 * Date:2018/12/25
 * Email:65489469@qq.com
 */
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
public class Comment {

    @Id
    private String id;

    private String hotspotId;

    private String content;

    private String fromUid;

    //点赞数
    private int praiseCount;

    private Date createTime;

}
