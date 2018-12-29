package com.zmm.diary.repository;

import com.zmm.diary.bean.Appreciate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/12/29
 * Email:65489469@qq.com
 */
public interface AppreciateRepository extends JpaRepository<Appreciate,String> {

    List<Appreciate> findAppreciatesByHotspotId(String hotspotId);

    Appreciate findAppreciateByUserIdAndHotspotId(String userId, String hotspotId);
}
