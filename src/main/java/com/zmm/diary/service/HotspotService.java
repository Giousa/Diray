package com.zmm.diary.service;

import com.zmm.diary.bean.ResultVO;
import org.springframework.data.domain.Pageable;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/11/12
 * Email:65489469@qq.com
 */
public interface HotspotService {

    ResultVO addHotspot(String userId, String content, String pic);

    ResultVO deleteHotspot(String hotspotId);

    ResultVO findHotspotsByUId(String userId,Pageable pageable);

    ResultVO findCollectionHotspotsByUId(String userId,Pageable pageable);

    ResultVO findAllHotspots(Pageable pageable);

    ResultVO findHotspotById(String userId,String hotspotId);

    ResultVO appreciateHotspot(String userId,String hotspotId);

    ResultVO collectionHotspot(String userId,String hotspotId);


}
