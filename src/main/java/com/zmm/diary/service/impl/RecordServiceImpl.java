package com.zmm.diary.service.impl;

import com.zmm.diary.bean.Record;
import com.zmm.diary.bean.ResultVO;
import com.zmm.diary.enums.ResultEnum;
import com.zmm.diary.repository.RecordRepository;
import com.zmm.diary.service.RecordService;
import com.zmm.diary.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/12/4
 * Email:65489469@qq.com
 */
@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordRepository recordRepository;

    @Override
    public ResultVO add(String userId,String content,String pics) {

        if(StringUtils.isEmpty(userId) || StringUtils.isEmpty(content)){
            return ResultVO.error(ResultEnum.PARAM_ERROR);
        }

        Record record = new Record();

        record.setUId(userId);
        record.setContent(content);
        record.setPics(pics);

        if(record.getCreateTime() == null){
            record.setCreateTime(new Date());
        }

        record.setId(KeyUtil.getKeyId());

        return ResultVO.ok(recordRepository.save(record));
    }

    @Override
    public ResultVO delete(String id) {

        try {
            recordRepository.deleteById(id);

            return ResultVO.ok();
        }catch (Exception e){

            return ResultVO.error(ResultEnum.DELETE_FAILURE);
        }
    }

    @Override
    public ResultVO findAllRecords(String userId, Pageable pageable) {


        Page<Record> records = recordRepository.findRecordsByUId(userId, pageable);

        List<Record> recordList = records.getContent();

        return ResultVO.ok(recordList);
    }
}
