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

    List<Correlate> findAllByUserIdAndActive(String userId, boolean activity);

    List<Correlate> findCorrelatesByUserIdAndActive(String userId, boolean activity, Pageable pageable);

    Correlate findCorrelateByUserIdAndAuthorId(String userId, String authorId);


}
