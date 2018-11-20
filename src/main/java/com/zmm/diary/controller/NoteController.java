package com.zmm.diary.controller;

import com.zmm.diary.bean.Note;
import com.zmm.diary.bean.ResultVO;
import com.zmm.diary.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/11/12
 * Email:65489469@qq.com
 */

@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping(value = "/addNote")
    public ResultVO addNote(@RequestBody Note note){

        return noteService.add(note);
    }

    @PostMapping(value = "/updateNote")
    public ResultVO updateNote(@RequestBody Note note){

        return noteService.update(note);
    }

    @GetMapping(value = {"/deleteNote/{id}","/deleteNote"})
    public ResultVO deleteNote(@PathVariable(value = "id",required = false)String id){

        return noteService.delete(id);
    }

    @GetMapping(value = {"/findNoteById/{id}","/findNoteById"})
    public ResultVO findNoteById(@PathVariable(value = "id",required = false)String id){

        return noteService.findNoteById(id);
    }

    @GetMapping(value = {"/findTodayNotesByUserId/{userId}","/findTodayNotesByUserId"})
    public ResultVO findTodayNotesByUserId(@PathVariable(value = "userId",required = false)String userId){

        return noteService.findToday(userId);
    }

    @GetMapping(value = {"/findNotesByCreateTime/{userId}/{createTime}","/findNotesByCreateTime"})
    public ResultVO findNotesByCreateTime(@PathVariable(value = "userId",required = false)String userId,
                                          @PathVariable(value = "createTime",required = false)String createTime){

        return noteService.findNotesByCreateTime(userId,createTime);
    }


}
