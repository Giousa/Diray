package com.zmm.diary.repository;

import com.zmm.diary.bean.Hotspot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/11/1
 * Email:65489469@qq.com
 */
public interface HotspotRepository extends JpaRepository<Hotspot,String> {

    Page<Hotspot> findHotspotsByUId(String userId, Pageable pageable);

}
