package com.zmm.diary.service;

import com.zmm.diary.bean.ResultVO;
import org.springframework.data.domain.Pageable;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2019/1/2
 * Email:65489469@qq.com
 */
public interface CorrelateService {

    ResultVO findAllFollowers(String userId, Pageable pageable);

    ResultVO findFollowersByUserId(String userId, Pageable pageable);

    ResultVO findFunsByUserId(String userId, Pageable pageable);

    ResultVO deleteFollower(String authorId);
}
