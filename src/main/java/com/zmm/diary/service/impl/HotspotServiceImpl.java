package com.zmm.diary.service.impl;

import com.zmm.diary.bean.Hotspot;
import com.zmm.diary.bean.ResultVO;
import com.zmm.diary.bean.User;
import com.zmm.diary.bean.vo.HotspotDTO;
import com.zmm.diary.bean.vo.UserDTO;
import com.zmm.diary.enums.ResultEnum;
import com.zmm.diary.repository.HotspotRepository;
import com.zmm.diary.repository.UserRepository;
import com.zmm.diary.service.HotspotService;
import com.zmm.diary.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/12/25
 * Email:65489469@qq.com
 */
@Service
public class HotspotServiceImpl implements HotspotService {

    @Autowired
    private HotspotRepository hotspotRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResultVO add(String userId, String content, String pic) {

        if(StringUtils.isEmpty(userId) || StringUtils.isEmpty(content) || StringUtils.isEmpty(pic)){
            return ResultVO.error(ResultEnum.PARAM_ERROR);
        }

        Hotspot hotspot = new Hotspot();
        hotspot.setId(KeyUtil.getKeyId());
        hotspot.setUId(userId);
        hotspot.setContent(content);
        hotspot.setPic(pic);

        return ResultVO.ok(hotspotRepository.save(hotspot));
    }

    @Override
    public ResultVO delete(String hotspotId) {

        if(StringUtils.isEmpty(hotspotId)){
            return ResultVO.error(ResultEnum.PARAM_ERROR);
        }

        try {
            hotspotRepository.deleteById(hotspotId);

            return ResultVO.ok();
        }catch (Exception e){

            return ResultVO.error(ResultEnum.DELETE_FAILURE);
        }
    }

    @Override
    public ResultVO findHotspotsByUId(String userId, Pageable pageable) {

        Page<Hotspot> hotspots = hotspotRepository.findHotspotsByUId(userId, pageable);

        List<Hotspot> hotspotList = hotspots.getContent();

        return ResultVO.ok(hotspotList);
    }

    @Override
    public ResultVO findAllHotspots(Pageable pageable) {

        //TODO 取巧方式，后期修改
        Page<Hotspot> hotspots = hotspotRepository.findAll(pageable);

        List<HotspotDTO> hotspotDTOList = new ArrayList<>();
        List<Hotspot> hotspotList = hotspots.getContent();
        for (Hotspot hotspot:hotspotList) {
            HotspotDTO hotspotDTO = new HotspotDTO();
            BeanUtils.copyProperties(hotspot,hotspotDTO);

            Optional<User> byId = userRepository.findById(hotspot.getUId());
            User user = byId.get();

            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user,userDTO);

            hotspotDTO.setAuthor(userDTO);

            hotspotDTOList.add(hotspotDTO);
        }


        return ResultVO.ok(hotspotDTOList);
    }

    @Override
    public ResultVO findHotspotById(String hotspotId) {

        if(StringUtils.isEmpty(hotspotId)){
            return ResultVO.error(ResultEnum.PARAM_ERROR);
        }

        Optional<Hotspot> hotspotOptional = hotspotRepository.findById(hotspotId);
        if(!hotspotOptional.isPresent()){
            return ResultVO.error(ResultEnum.HOTSPOT_NOT_EXIST);
        }

        Hotspot hotspot = hotspotOptional.get();

        HotspotDTO hotspotDTO = new HotspotDTO();
        BeanUtils.copyProperties(hotspot,hotspotDTO);

        Optional<User> byId = userRepository.findById(hotspot.getUId());
        User user = byId.get();

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user,userDTO);

        hotspotDTO.setAuthor(userDTO);

        return ResultVO.ok(hotspotDTO);
    }
}
