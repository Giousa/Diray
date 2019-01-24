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
import com.zmm.diary.utils.KeyUtil;
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

    /**
     * 查询推荐关注  查询所有作者
     * @param userId
     * @param pageable
     * @return
     */
    @Override
    public ResultVO findAllFollowers(String userId, Pageable pageable) {

        if(StringUtils.isEmpty(userId)){
            return ResultVO.error(ResultEnum.PARAM_ERROR);
        }

        List<User> allUsers = userRepository.findAll();
        List<CorrelateDTO> authorList = new ArrayList<>();

        for (User user : allUsers) {

            //过滤掉本人
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

                //判断是否关注
                Correlate correlate = correlateRepository.findCorrelateByUserIdAndAuthorId(userId, user.getId());
                if(correlate == null){
                    correlateDTO.setAttention(false);
                }else {
                    if(correlate.isActive()){
                        correlateDTO.setAttention(true);
                    }else {
                        correlateDTO.setAttention(false);
                    }
                }

                authorList.add(correlateDTO);

            }
        }

        return ResultVO.ok(authorList);
    }

    /**
     * 查询所有关注  本意就是查询作者
     * @param userId
     * @param pageable
     * @return
     */
    @Override
    public ResultVO findFollowersByUserId(String userId, Pageable pageable) {

        List<Correlate> correlateList = correlateRepository.findAllByUserIdAndActive(userId, true, pageable);

        List<CorrelateDTO> authorList = new ArrayList<>();

        for (Correlate correlate : correlateList) {
            //根据作者id查询作者信息
            Optional<User> byId = userRepository.findById(correlate.getAuthorId());
            if(byId.isPresent()){

                User user = byId.get();

                CorrelateDTO correlateDTO = new CorrelateDTO();
                correlateDTO.setId(user.getId());
                correlateDTO.setUsername(user.getUsername());
                correlateDTO.setNickname(user.getNickname());
                correlateDTO.setIcon(user.getIcon());
                correlateDTO.setSign(user.getSign());
                correlateDTO.setAttention(true);
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

    /**
     * 查询所有粉丝  本意就是自己作为作者
     * @param userId
     * @param pageable
     * @return
     */
    @Override
    public ResultVO findFunsByUserId(String userId, Pageable pageable) {

        List<Correlate> correlateList = correlateRepository.findAllByAuthorIdAndActive(userId, true, pageable);


        List<CorrelateDTO> authorList = new ArrayList<>();
        for (Correlate correlate : correlateList) {

            //根据用户id查询用户信息
            Optional<User> byId = userRepository.findById(correlate.getUserId());

            if(byId.isPresent()){

                User user = byId.get();

                CorrelateDTO correlateDTO = new CorrelateDTO();
                correlateDTO.setId(user.getId());
                correlateDTO.setUsername(user.getUsername());
                correlateDTO.setNickname(user.getNickname());
                correlateDTO.setIcon(user.getIcon());
                correlateDTO.setSign(user.getSign());
                correlateDTO.setAttention(true);

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

    /**
     * 关注或取消关注
     * @param userId
     * @param authorId
     * @return
     */
    @Override
    public ResultVO correlateAuthor(String userId, String authorId) {

        Correlate correlate = correlateRepository.findCorrelateByUserIdAndAuthorId(userId, authorId);

        if(correlate != null){

            correlate.setActive(!correlate.isActive());
            correlateRepository.save(correlate);

            return correlate.isActive() ? ResultVO.ok("correlateConfirm") : ResultVO.ok("correlateCancel");

        }else {
            correlate = new Correlate();
            correlate.setId(KeyUtil.getKeyId());
            correlate.setUserId(userId);
            correlate.setAuthorId(authorId);
            correlate.setActive(true);

            correlateRepository.save(correlate);

            return ResultVO.ok("correlateConfirm");
        }
    }

}
