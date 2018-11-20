package com.zmm.diary.service;

import com.zmm.diary.bean.Note;
import com.zmm.diary.bean.ResultVO;
import org.springframework.data.domain.Pageable;

import java.util.Date;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/11/12
 * Email:65489469@qq.com
 */
public interface NoteService {

    ResultVO add(Note note);

    ResultVO update(Note note);

    ResultVO delete(String id);

    ResultVO findNoteById(String id);

    ResultVO findToday(String userId);

    ResultVO findNotesByCreateTime(String userId,String createTime);

    ResultVO findAll(String userId, Pageable pageable);
}
