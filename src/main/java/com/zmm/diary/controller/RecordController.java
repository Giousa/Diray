package com.zmm.diary.controller;

import com.zmm.diary.bean.Record;
import com.zmm.diary.bean.ResultVO;
import com.zmm.diary.enums.ResultEnum;
import com.zmm.diary.service.RecordService;
import com.zmm.diary.utils.UploadOSSUtils;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/12/4
 * Email:65489469@qq.com
 */
@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @PostMapping(value = "/addRecordAndPics")
    public ResultVO addRecordAndPics(@RequestParam("userId")String userId,@RequestParam("content")String content,@RequestParam(value="uploadFiles",required=false) MultipartFile[] file) throws Exception {


        StringBuffer sb = new StringBuffer();

        for (MultipartFile mf : file) {
            if(!mf.isEmpty()){
                String pic = UploadOSSUtils.uploadSinglePic(mf,".jpg");
                sb.append(pic+",");
            }
        }

        String paths = sb.toString();
        if(!TextUtils.isEmpty(paths)){
            paths = paths.substring(0,paths.length()-1);
            return recordService.add(userId, content, paths);
        }else{
            return ResultVO.error(ResultEnum.PIC_UPLOAD_FAILURE);
        }
    }

    @PostMapping(value = "/addRecord")
    public ResultVO addRecord(@RequestParam("userId")String userId,@RequestParam("content")String content)  {

        return recordService.add(userId, content, null);
    }

    @GetMapping(value = {"/deleteRecord/{id}","/deleteRecord"})
    public ResultVO deleteRecord(@PathVariable(value = "id",required = false)String id){

        return recordService.delete(id);
    }

    @GetMapping("/findAllRecords")
    public ResultVO findAllRecords(@RequestParam("userId") String userId,
                                         @RequestParam(value = "page",defaultValue = "0") Integer page,
                                         @RequestParam(value = "size",defaultValue = "10") Integer size){

        if(StringUtils.isEmpty(userId)){
            return ResultVO.error(ResultEnum.PARAM_ERROR);
        }

        return recordService.findAllRecords(userId, new PageRequest(page, size, Sort.Direction.DESC,"createTime"));
    }
}
