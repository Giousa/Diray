package com.zmm.diary.repository;

import com.zmm.diary.bean.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/12/29
 * Email:65489469@qq.com
 */
public interface CollectionRepository extends JpaRepository<Collection,String> {

    List<Collection> findCollectionsByHotspotIdAndActive(String hotspotId,boolean activity);

    List<Collection> findCollectionsByUserIdAndActive(String userId,boolean activity);

    Collection findCollectionByUserIdAndHotspotId(String userId, String hotspotId);

}
