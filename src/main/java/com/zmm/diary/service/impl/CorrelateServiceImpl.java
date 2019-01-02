package com.zmm.diary.service.impl;

import com.zmm.diary.bean.Correlate;
import com.zmm.diary.bean.Hotspot;
import com.zmm.diary.bean.ResultVO;
import com.zmm.diary.bean.User;
import com.zmm.diary.bean.vo.CorrelateDTO;
import com.zmm.diary.enums.ResultEnum;
import com.zmm.diary.repository.CorrelateRepository;
import com.zmm.diary.repository.HotspotRepository;
import com.zmm.diary.repository.UserRepository;
import com.zmm.diary.service.CorrelateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2019/1/2
 * Email:65489469@qq.com
 */
@Service
public class CorrelateServiceImpl implements CorrelateService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CorrelateRepository correlateRepository;

    @Autowired
    private HotspotRepository hotspotRepository;

    @Override
    public ResultVO findAllFollowers(String userId, Pageable pageable) {

        if(StringUtils.isEmpty(userId)){
            return ResultVO.error(ResultEnum.PARAM_ERROR);
        }

        List<User> userList = userRepository.findAllById(userId,pageable);
        List<CorrelateDTO> authorList = new ArrayList<>();
        for (User user : userList) {

            if(!user.getId().equals(userId)){

                CorrelateDTO correlateDTO = new CorrelateDTO();
                correlateDTO.setId(user.getId());
                correlateDTO.setUsername(user.getUsername());
                correlateDTO.setNickname(user.getNickname());
                correlateDTO.setIcon(user.getIcon());
                correlateDTO.setSign(user.getSign());
                //发布数
                List<Hotspot> hotspotList = hotspotRepository.findHotspotsByUId(user.getId());
                if(hotspotList != null && hotspotList.size() > 0){
                    correlateDTO.setReleases(hotspotList.size());
                }
                //粉丝数
                List<Correlate> correlateFunsList = correlateRepository.findAllByAuthorIdAndActive(user.getId(), true);
                if(correlateFunsList != null && correlateFunsList.size() > 0){
                    correlateDTO.setFuns(correlateFunsList.size());
                }

                authorList.add(correlateDTO);

            }

        }

        return ResultVO.ok(authorList);
    }

    @Override
    public ResultVO findFollowersByUserId(String userId, Pageable pageable) {

        List<Correlate> correlateList = correlateRepository.findAllByUserIdAndActive(userId, true, pageable);

        List<CorrelateDTO> authorList = new ArrayList<>();

        for (Correlate correlate : correlateList) {
            Optional<User> byId = userRepository.findById(correlate.getAuthorId());
            if(byId.isPresent()){

                User user = byId.get();

                CorrelateDTO correlateDTO = new CorrelateDTO();
                correlateDTO.setId(user.getId());
                correlateDTO.setUsername(user.getUsername());
                correlateDTO.setNickname(user.getNickname());
                correlateDTO.setIcon(user.getIcon());
                correlateDTO.setSign(user.getSign());
                //发布数
                List<Hotspot> hotspotList = hotspotRepository.findHotspotsByUId(user.getId());
                if(hotspotList != null && hotspotList.size() > 0){
                    correlateDTO.setReleases(hotspotList.size());
                }
                //粉丝数
                List<Correlate> correlateFunsList = correlateRepository.findAllByAuthorIdAndActive(user.getId(), true);
                if(correlateFunsList != null && correlateFunsList.size() > 0){
                    correlateDTO.setFuns(correlateFunsList.size());
                }

                authorList.add(correlateDTO);

            }
        }

        return ResultVO.ok(authorList);

    }

    @Override
    public ResultVO findFunsByUserId(String userId, Pageable pageable) {

        List<Correlate> correlateList = correlateRepository.findAllByAuthorIdAndActive(userId, true, pageable);


        List<CorrelateDTO> authorList = new ArrayList<>();
        for (Correlate correlate : correlateList) {

            Optional<User> byId = userRepository.findById(correlate.getUserId());

            if(byId.isPresent()){

                User user = byId.get();

                CorrelateDTO correlateDTO = new CorrelateDTO();
                correlateDTO.setId(user.getId());
                correlateDTO.setUsername(user.getUsername());
                correlateDTO.setNickname(user.getNickname());
                correlateDTO.setIcon(user.getIcon());
                correlateDTO.setSign(user.getSign());
                //发布数
                List<Hotspot> hotspotList = hotspotRepository.findHotspotsByUId(user.getId());
                if(hotspotList != null && hotspotList.size() > 0){
                    correlateDTO.setReleases(hotspotList.size());
                }
                //粉丝数
                List<Correlate> correlateFunsList = correlateRepository.findAllByAuthorIdAndActive(user.getId(), true);
                if(correlateFunsList != null && correlateFunsList.size() > 0){
                    correlateDTO.setFuns(correlateFunsList.size());
                }

                authorList.add(correlateDTO);

            }
        }

        return ResultVO.ok(authorList);
    }

    @Override
    public ResultVO deleteFollower(String authorId) {

        try {
            correlateRepository.deleteByAuthorId(authorId);

            return ResultVO.ok("删除成功");
        }catch (Exception e){

            return ResultVO.error(ResultEnum.DELETE_FAILURE);
        }
    }
}
