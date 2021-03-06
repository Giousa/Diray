package com.zmm.diary.repository;

import com.zmm.diary.bean.Note;
import com.zmm.diary.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/11/1
 * Email:65489469@qq.com
 */
public interface NoteRepository extends JpaRepository<Note,String> {

    @Query(value = "SELECT * FROM diary.note where to_days(create_time)=to_days(now()) and u_id=?", nativeQuery = true)
    List<Note> findTodayNotesByUserId(String userId);

    @Query(value = "SELECT * FROM diary.note where to_days(create_time)=to_days(?) and u_id=?", nativeQuery = true)
    List<Note> findNotesByCreateTime(String createTime,String userId);

}
