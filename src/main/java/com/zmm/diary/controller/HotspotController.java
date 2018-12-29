package com.zmm.diary.controller;

import com.zmm.diary.bean.ResultVO;
import com.zmm.diary.enums.ResultEnum;
import com.zmm.diary.service.HotspotService;
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
@RequestMapping("/hotspot")
public class HotspotController {

    @Autowired
    private HotspotService hotspotService;

    @PostMapping(value = "/addHotspot")
    public ResultVO addHotspot(@RequestParam("userId")String userId,
                               @RequestParam("content")String content,
                               @RequestParam("type")String type,
                               @RequestParam(value="uploadFile",required=false) MultipartFile file)  {

        if(TextUtils.isEmpty(type)){
            return ResultVO.error(ResultEnum.TYPE_EMPTY);
        }

        try {

            String path = UploadOSSUtils.uploadSinglePic(file,type);

            return hotspotService.addHotspot(userId,content,path);

        } catch (Exception e) {
            e.printStackTrace();
            return ResultVO.error(ResultEnum.PIC_UPLOAD_FAILURE);

        }
    }

    @GetMapping(value = {"/deleteHotspot/{id}","/deleteHotspot"})
    public ResultVO deleteHotspot(@PathVariable(value = "id",required = false)String id){

        return hotspotService.deleteHotspot(id);
    }

    @GetMapping("/findHotspotsByUId")
    public ResultVO findAllRecords(@RequestParam("userId") String userId,
                                   @RequestParam(value = "page",defaultValue = "0") Integer page,
                                   @RequestParam(value = "size",defaultValue = "4") Integer size){

        if(StringUtils.isEmpty(userId)){
            return ResultVO.error(ResultEnum.PARAM_ERROR);
        }

        return hotspotService.findHotspotsByUId(userId, new PageRequest(page, size, Sort.Direction.DESC,"createTime"));
    }

    @GetMapping("/findAllHotspots")
    public ResultVO findAllRecords(@RequestParam(value = "page",defaultValue = "0") Integer page,
                                   @RequestParam(value = "size",defaultValue = "4") Integer size){


        return hotspotService.findAllHotspots(new PageRequest(page, size, Sort.Direction.DESC,"createTime"));
    }

    @GetMapping(value = {"/findHotspotById"})
    public ResultVO findHotspotById(@RequestParam(value = "userId")String userId,
                                    @RequestParam(value = "hotspotId")String hotspotId){

        if(StringUtils.isEmpty(userId) || StringUtils.isEmpty(hotspotId)){
            return ResultVO.error(ResultEnum.PARAM_ERROR);
        }

        return hotspotService.findHotspotById(userId,hotspotId);
    }

    @GetMapping(value = {"/appreciateConfirm"})
    public ResultVO appreciateHotspot(@RequestParam(value = "userId")String userId,
                                      @RequestParam(value = "hotspotId")String hotspotId){

        if(StringUtils.isEmpty(userId) || StringUtils.isEmpty(hotspotId)){
            return ResultVO.error(ResultEnum.PARAM_ERROR);
        }

        return hotspotService.appreciateHotspot(userId,hotspotId);
    }

}
