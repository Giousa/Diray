package com.zmm.diary.service.impl;

import com.zmm.diary.bean.*;
import com.zmm.diary.bean.vo.HotspotDTO;
import com.zmm.diary.bean.vo.HotspotDetailDTO;
import com.zmm.diary.bean.vo.UserDTO;
import com.zmm.diary.enums.ResultEnum;
import com.zmm.diary.repository.AppreciateRepository;
import com.zmm.diary.repository.CollectionRepository;
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

    @Autowired
    private AppreciateRepository appreciateRepository;

    @Autowired
    private CollectionRepository collectionRepository;

    @Override
    public ResultVO addHotspot(String userId, String content, String pic) {

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
    public ResultVO deleteHotspot(String hotspotId) {

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

            //作者信息
            Optional<User> byId = userRepository.findById(hotspot.getUId());
            User user = byId.get();

            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user,userDTO);

            hotspotDTO.setAuthor(userDTO);

            //点赞数
            List<Appreciate> appreciateList = appreciateRepository.findAppreciatesByHotspotIdAndActive(hotspot.getId(),true);
            if(appreciateList != null && appreciateList.size() > 0){
                hotspotDTO.setAppreciate(appreciateList.size());
            }else {
                hotspotDTO.setAppreciate(0);
            }

            hotspotDTOList.add(hotspotDTO);
        }


        return ResultVO.ok(hotspotDTOList);
    }

    @Override
    public ResultVO findHotspotById(String userId,String hotspotId) {

        Optional<Hotspot> hotspotOptional = hotspotRepository.findById(hotspotId);
        if(!hotspotOptional.isPresent()){
            return ResultVO.error(ResultEnum.HOTSPOT_NOT_EXIST);
        }

        Hotspot hotspot = hotspotOptional.get();

        HotspotDetailDTO hotspotDetailDTO = new HotspotDetailDTO();
        BeanUtils.copyProperties(hotspot,hotspotDetailDTO);

        //作者信息
        Optional<User> byId = userRepository.findById(hotspot.getUId());
        User user = byId.get();

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user,userDTO);

        hotspotDetailDTO.setAuthor(userDTO);

        //点赞数
        List<Appreciate> appreciateList = appreciateRepository.findAppreciatesByHotspotIdAndActive(hotspot.getId(),true);
        if(appreciateList != null && appreciateList.size() > 0){
            hotspotDetailDTO.setAppreciate(appreciateList.size());
        }else {
            hotspotDetailDTO.setAppreciate(0);
        }

        //判断当前用户是否点赞
        Appreciate appreciate = appreciateRepository.findAppreciateByUserIdAndHotspotId(userId, hotspotId);
        if(appreciate != null && appreciate.isActive()){
            hotspotDetailDTO.setHasAppreciate(true);
        }else {
            hotspotDetailDTO.setHasAppreciate(false);
        }

        //判断当前用户是否收藏
        Collection collection = collectionRepository.findCollectionByUserIdAndHotspotId(userId, hotspotId);
        if(collection != null && collection.isActive()){
            hotspotDetailDTO.setHasCollect(true);
        }else {
            hotspotDetailDTO.setHasCollect(false);
        }


        return ResultVO.ok(hotspotDetailDTO);
    }

    @Override
    public ResultVO appreciateHotspot(String userId, String hotspotId) {

        Appreciate appreciate = appreciateRepository.findAppreciateByUserIdAndHotspotId(userId, hotspotId);
        if(appreciate != null){

            appreciate.setActive(!appreciate.isActive());
            appreciateRepository.save(appreciate);

            return appreciate.isActive() ? ResultVO.ok("appreciateConfirm") : ResultVO.ok("appreciateCancel");

        }else {
            appreciate = new Appreciate();
            appreciate.setId(KeyUtil.getKeyId());
            appreciate.setUserId(userId);
            appreciate.setHotspotId(hotspotId);
            appreciate.setActive(true);
            appreciateRepository.save(appreciate);

            return ResultVO.ok("appreciateConfirm");

        }

    }


    @Override
    public ResultVO collectionHotspot(String userId, String hotspotId) {

        Collection collection = collectionRepository.findCollectionByUserIdAndHotspotId(userId, hotspotId);
        if(collection != null){

            collection.setActive(!collection.isActive());
            collectionRepository.save(collection);

            return collection.isActive() ? ResultVO.ok("collectionConfirm") : ResultVO.ok("collectionCancel");

        }else {
            collection = new Collection();
            collection.setId(KeyUtil.getKeyId());
            collection.setUserId(userId);
            collection.setHotspotId(hotspotId);
            collection.setActive(true);
            collectionRepository.save(collection);

            return ResultVO.ok("collectionConfirm");

        }
    }

}
