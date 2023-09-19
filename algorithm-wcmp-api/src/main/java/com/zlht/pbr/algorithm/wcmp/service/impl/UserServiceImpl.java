package com.zlht.pbr.algorithm.wcmp.service.impl;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zlht.pbr.algorithm.wcmp.client.ManagementClient;
import com.zlht.pbr.algorithm.wcmp.client.WeChatClient;
import com.zlht.pbr.algorithm.wcmp.configuration.WcServerConfiguration;
import com.zlht.pbr.algorithm.wcmp.dao.entity.SpotHistory;
import com.zlht.pbr.algorithm.wcmp.dao.entity.User;
import com.zlht.pbr.algorithm.wcmp.dao.mapper.LinkCodeAndAppIdMapMapper;
import com.zlht.pbr.algorithm.wcmp.dao.mapper.SpotHistoryMapper;
import com.zlht.pbr.algorithm.wcmp.dao.mapper.StudyRecordMapper;
import com.zlht.pbr.algorithm.wcmp.dao.mapper.UserMapper;
import com.zlht.pbr.algorithm.wcmp.enums.Status;
import com.zlht.pbr.algorithm.wcmp.model.StudyData;
import com.zlht.pbr.algorithm.wcmp.service.TokenServiceI;
import com.zlht.pbr.algorithm.wcmp.service.UserServiceI;
import com.zlht.pbr.algorithm.wcmp.utils.RandomGeneratorUtils;
import com.zlht.pbr.algorithm.wcmp.utils.Result;
import com.zlht.pbr.algorithm.wcmp.utils.TimeUtils;
import com.zlht.pbr.algorithm.wcmp.utils.WxBizDataCryptUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author zijian Wang
 */
@Service
public class UserServiceImpl extends BaseServiceImpl implements UserServiceI {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private StudyRecordMapper studyRecordMapper;

    @Autowired
    WcServerConfiguration wcServerConfiguration;

    @Autowired
    private ManagementClient managementClient;

    @Autowired
    private WeChatClient weChatServiceClient;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LinkCodeAndAppIdMapMapper linkCodeAndAppIdMapMapper;

    @Autowired
    private TokenServiceI tokenServiceI;

    @Autowired
    private SpotHistoryMapper spotHistoryMapper;

    /**
     * 通过用户ID获取学习数据
     *
     * @param linkCode
     * @param userId
     * @return
     */
    @Override
    public Result<StudyData> getUserStudyDataByUserId(String linkCode, int userId) {

        Result<StudyData> result = new Result<>();
        //今日

        //总打开天数

//      本周时间范围
        Map<String, Date> weekDateMap = TimeUtils.getCurrentWeekRange(new Date());
        Date startOfWeek = weekDateMap.get("startOfWeek");
        Date endOfWeek = weekDateMap.get("endOfWeek");
//      本月
        Map<String, Date> monthDateMap = TimeUtils.getCurrentMonthRange(new Date());
        Date startOfMonth = monthDateMap.get("startOfMonth");
        Date endOfMonth = monthDateMap.get("endOfMonth");

        //今日学习时间
        int studyTimeToday = studyRecordMapper.getStudyTimeToday(linkCode, userId);
        //近一周学习时间
        int studyTimeWeek = studyRecordMapper.getStudyTimeWeek(linkCode, userId, startOfWeek, endOfWeek);
        //本月学习时间
        int studyTimeMonth = studyRecordMapper.getStudyTimeMonth(linkCode, userId, startOfMonth, endOfMonth);
        //总签到
        int checkInTotal = studyRecordMapper.getCheckInTotal(linkCode, userId);
        //近一周签到
        int checkInDaysWeekCount = studyRecordMapper.getCheckInDaysWeek(linkCode, userId, startOfWeek, endOfWeek);
        //本月
        int checkInDaysMonthCount = studyRecordMapper.getCheckInDaysMonth(linkCode, userId, startOfMonth, endOfMonth);
        //周点位
        List<Map<String, Object>> pointsWeek = studyRecordMapper.getPointsWeek(linkCode, userId, startOfWeek, endOfWeek);
        //月点位
        List<Map<String, Object>> pointsMonth = studyRecordMapper.getPointMonth(linkCode, userId, startOfMonth, endOfMonth);

        StudyData studyData = new StudyData();
        studyData.setStudyTimeToday(studyTimeToday);
        studyData.setStudyTimeWeek(studyTimeWeek);
        studyData.setStudyTimeMonth(studyTimeMonth);
        studyData.setCheckInTotal(checkInTotal);
        studyData.setCheckInDaysWeek(checkInDaysWeekCount);
        studyData.setCheckInDaysMonth(checkInDaysMonthCount);
        studyData.setPointsWeek(pointsWeek);
        studyData.setPointMonth(pointsMonth);

        result.setCode(Status.SUCCESS.getCode());
        result.setMsg(Status.SUCCESS.getMsg());
        result.setData(studyData);
        return result;
    }

    @Override
    public Map<String, Object> getUserAbilityByUserId(String linkCode, int userId) {
        Map<String, Object> averageMap = new HashMap<>(3);

        Map<String, Object> map = new HashMap<>(3);
        QueryWrapper<SpotHistory> queryWrapper = new QueryWrapper();
        queryWrapper.eq("link_code", linkCode);
        queryWrapper.eq("user_id", userId);
        List<SpotHistory> spotHistoryList = spotHistoryMapper.selectList(queryWrapper);

        // 创建累加器
        Map<String, Integer> sumMap = new HashMap<>();
        int count = 0;
        // 遍历 SpotHistory 对象列表
        for (SpotHistory spotHistory : spotHistoryList) {
            // 解析 result 属性的 JSON 数据
            Map<String, Map<String, Integer>> result = parseResultJson(spotHistory.getResult());

            // 获取 ability 参数的值
            Map<String, Integer> ability = result.get("ability");

            // 累加 ability 参数值
            for (Map.Entry<String, Integer> entry : ability.entrySet()) {
                String parameter = entry.getKey();
                Integer value = entry.getValue();

                sumMap.put(parameter, sumMap.getOrDefault(parameter, 0) + value);
            }
            count++;
        }

        int abilityScore = 0;
        // 计算平均值
        for (Map.Entry<String, Integer> entry : sumMap.entrySet()) {
            String parameter = entry.getKey();
            Integer sum = entry.getValue();
            Integer average = sum / count;
            abilityScore += average;
            averageMap.put(parameter, average);
        }
        int abilityScoreAvg = abilityScore / 5;
        averageMap.put("abilityScore", abilityScoreAvg);
        averageMap.put("title", getTitle(abilityScoreAvg));
        map.put("data", averageMap);
        putMsg(map, Status.SUCCESS.getCode(), Status.SUCCESS.getMsg());
        return map;
    }


    @Override
    public Map<String, Object> login(String linkCode, String code, String encryptedData, String iv) {
        Map<String, Object> map = new HashMap<>(3);
        ObjectMapper objectMapper = new ObjectMapper();
//        get appId and secret
        Map<String, String> selectMapByLinkCode = linkCodeAndAppIdMapMapper.selectMapByLinkCode(linkCode);
        String appId = selectMapByLinkCode.get("appId");
        String secret = selectMapByLinkCode.get("appSecret");

        String code2SessionRes = weChatServiceClient.code2Session("authorization_code", appId, secret, code);
        Map<String, Object> sessionOpenIdMap;
        try {
            sessionOpenIdMap = objectMapper.readValue(code2SessionRes, Map.class);
            System.out.println(sessionOpenIdMap);
            String checkKeyStr = "session_key";
            String checkOpenIdStr = "openid";
            if (sessionOpenIdMap.containsKey(checkKeyStr) && sessionOpenIdMap.containsKey(checkOpenIdStr)) {
                String sessionKey = sessionOpenIdMap.get(checkKeyStr).toString();
                String openId = sessionOpenIdMap.get(checkOpenIdStr).toString();
                //decrypt
                WxBizDataCryptUtil wxBizDataCryptUtil = new WxBizDataCryptUtil(appId, sessionKey);
                JSONObject jsonObject = wxBizDataCryptUtil.decrypt(encryptedData, iv);

                QueryWrapper userQueryWrapper = new QueryWrapper();
                userQueryWrapper.eq("open_id", openId);
                User user;
                user = userMapper.selectOne(userQueryWrapper);
                user.setType(adminOrNot(linkCode, openId) ? 2 : 3);
//                check user exists
                if (user != null) {
                    user.setSessionKey(sessionKey);
                    userMapper.update(user, userQueryWrapper);
                    //Sync
                    reportUserData(appId, user, 0);
                    putMsg(map, Status.SUCCESS.getCode(), Status.SUCCESS.getMsg());
                } else {
                    user = new User();
                    user.setType(0);
                    user.setNickname("微信用户" + RandomGeneratorUtils.generateRandomChars(5));
                    user.setOpenId(openId);
                    user.setLinkCode(linkCode);
                    user.setSessionKey(sessionKey);
                    user.setUpdateTime(new Date());
                    user.setGender(jsonObject.getInt("gender"));
                    user.setCreateTime(new Date());
                    userMapper.insert(user);
                    //Sync
                    reportUserData(appId, user, 1);
                    putMsg(map, Status.SUCCESS.getCode(), Status.SUCCESS.getMsg());
                }
                //createOrUpdateToken
                String token = tokenServiceI.createOrUpdateToken(user);
                Map<String, Object> useMap = new HashMap<>(1);
                useMap.put("nickname", user.getNickname());
                useMap.put("userId", user.getId().toString());
                useMap.put("token", token);
                map.put("data", useMap);
                managementClient.reportData(linkCode, "user_count_today",1);
            } else {
                String errMsgStr = "errMsgStr";
                String reason = "未知";
                if (sessionOpenIdMap.containsKey(errMsgStr)) {
                    reason = sessionOpenIdMap.get(errMsgStr).toString();
                }
                String errMsg = "未获取到session_key和openid,登录失败,原因:" + reason;
                logger.error("login() method .message={}, jsCode={}", errMsg, code);
                putMsg(map, 400, errMsg);
                return map;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    boolean adminOrNot(String linkCode, String openId) {

        Result<Map<String, Object>> result = managementClient.adminOrNot(linkCode, openId);

        return Boolean.getBoolean(result.getData().get("adminOrNot").toString());
    }

    private void reportUserData(String appId, User user, int event) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("nickname", user.getNickname());
        map.put("openId", user.getOpenId());
        map.put("appId", appId);
        managementClient.reportUser(map, event);
    }


    private static Map<String, Map<String, Integer>> parseResultJson(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyMap();
        }
    }

    private static String getTitle(int abilityScore) {
        System.out.println(abilityScore);
        if (abilityScore < 60) {
            return "体育新生";
        }
        if (abilityScore >= 60 && abilityScore < 70) {
            return "体育优等生";
        }
        if (abilityScore >= 70 && abilityScore <= 80) {
            return "体育大佬";
        }
        if (abilityScore >= 80 && abilityScore <= 100) {
            return "体育大神";
        }
        return "体育新生";
    }
}
