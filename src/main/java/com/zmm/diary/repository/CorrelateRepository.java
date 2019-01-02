package com.zmm.diary.repository;

import com.zmm.diary.bean.Correlate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/12/29
 * Email:65489469@qq.com
 */
public interface CorrelateRepository extends JpaRepository<Correlate,String> {

    //关注列表
    List<Correlate> findAllByUserIdAndActive(String userId, boolean activity);

    List<Correlate> findAllByUserIdAndActive(String userId, boolean activity, Pageable pageable);

    //粉丝列表
    List<Correlate> findAllByAuthorIdAndActive(String authorId,boolean activity);

    List<Correlate> findAllByAuthorIdAndActive(String authorId,boolean activity, Pageable pageable);

    List<Correlate> findCorrelatesByUserIdAndActive(String userId, boolean activity, Pageable pageable);

    //判断是否是本人
    Correlate findCorrelateByUserIdAndAuthorId(String userId, String authorId);

    void deleteByAuthorId(String authorId);

}
