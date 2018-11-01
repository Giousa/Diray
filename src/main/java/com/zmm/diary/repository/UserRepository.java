package com.zmm.diary.repository;

import com.zmm.diary.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/11/1
 * Email:65489469@qq.com
 */
public interface UserRepository extends JpaRepository<User,String> {

    List<User> findByUsername(String username);
}
