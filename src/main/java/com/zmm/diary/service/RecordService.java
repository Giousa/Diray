package com.zmm.diary.service;

import com.zmm.diary.bean.ResultVO;
import org.springframework.data.domain.Pageable;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/11/12
 * Email:65489469@qq.com
 */
public interface RecordService {

    ResultVO add(String userId,String content,String pics);

    ResultVO delete(String id);

    ResultVO findAllRecords(String userId, Pageable pageable);
}
