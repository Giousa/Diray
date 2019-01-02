package com.zmm.diary.repository;

import com.zmm.diary.bean.Hotspot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/11/1
 * Email:65489469@qq.com
 */
public interface HotspotRepository extends JpaRepository<Hotspot,String> {

    Page<Hotspot> findHotspotsByUId(String userId, Pageable pageable);

    List<Hotspot> findHotspotsByUId(String userId);

//    @Query(value = "SELECT * FROM diary.hotspot where to_days(create_time)=to_days(?) and u_id=?", nativeQuery = true)
//    Page<Hotspot> findHotspotsByUId(String userId, Pageable pageable);

}
