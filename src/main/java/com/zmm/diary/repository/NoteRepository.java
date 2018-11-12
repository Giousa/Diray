package com.zmm.diary.repository;

import com.zmm.diary.bean.Note;
import com.zmm.diary.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/11/1
 * Email:65489469@qq.com
 */
public interface NoteRepository extends JpaRepository<Note,String> {

    @Query(value = "select n.* from note n where n.to_days(create_time)=to_days(now())  and n.uId=#{userId}", nativeQuery = true)
    List<Note> findTodayNotesByUserId(String userId);

}
