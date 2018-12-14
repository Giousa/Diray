package com.zmm.diary.repository;

import com.zmm.diary.bean.Note;
import com.zmm.diary.bean.Record;
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
public interface RecordRepository extends JpaRepository<Record,String> {

    @Query(value = "SELECT * FROM diary.record where to_days(create_time)=to_days(?) and u_id=?", nativeQuery = true)
    List<Note> findRecordsByCreateTime(String createTime, String userId);

    Page<Record> findRecordsByUId(String userId, Pageable pageable);

}
