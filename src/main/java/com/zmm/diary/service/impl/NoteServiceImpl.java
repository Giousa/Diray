package com.zmm.diary.service.impl;

import com.zmm.diary.bean.Note;
import com.zmm.diary.bean.ResultVO;
import com.zmm.diary.enums.ResultEnum;
import com.zmm.diary.repository.NoteRepository;
import com.zmm.diary.service.NoteService;
import com.zmm.diary.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/11/12
 * Email:65489469@qq.com
 */
@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public ResultVO add(Note note) {

        if(StringUtils.isEmpty(note.getUId()) || StringUtils.isEmpty(note.getTitle()) || StringUtils.isEmpty(note.getContent())){
            return ResultVO.error(ResultEnum.PARAM_ERROR);
        }

        note.setId(KeyUtil.getKeyId());

        return ResultVO.ok(noteRepository.save(note));
    }

    @Override
    public ResultVO update(Note note) {

        if(StringUtils.isEmpty(note.getId()) || StringUtils.isEmpty(note.getUId()) || StringUtils.isEmpty(note.getTitle()) || StringUtils.isEmpty(note.getContent())){
            return ResultVO.error(ResultEnum.PARAM_ERROR);
        }

        return ResultVO.ok(noteRepository.save(note));
    }

    @Override
    public ResultVO delete(String id) {

        if(StringUtils.isEmpty(id)){
            return ResultVO.error(ResultEnum.PARAM_ERROR);
        }

        noteRepository.deleteById(id);

        return ResultVO.ok("删除成功");
    }

    @Override
    public ResultVO findNoteById(String id) {

        if(StringUtils.isEmpty(id)){
            return ResultVO.error(ResultEnum.PARAM_ERROR);
        }

        Optional<Note> optionalNote = noteRepository.findById(id);

        if(optionalNote.isPresent()){
            return ResultVO.ok(optionalNote.get());
        }

        return ResultVO.error(ResultEnum.NOTE_HAS_EXIST);
    }

    @Override
    public ResultVO findToday(String userId) {

        if(StringUtils.isEmpty(userId)){
            return ResultVO.error(ResultEnum.PARAM_ERROR);
        }

        System.out.println("查询今天的数据参数 userid = "+userId);
        List<Note> todayNotesByUserId = noteRepository.findTodayNotesByUserId(userId);

        return ResultVO.ok(todayNotesByUserId);
    }

    @Override
    public ResultVO findAll(String userId, Pageable pageable) {
        return null;
    }
}
