package com.zlht.pbr.algorithm.wcmp.service;

import com.zlht.pbr.algorithm.wcmp.dao.entity.User;

import java.util.Map;

/**
 * @author zi jian Wang
 */
public interface SessionServiceI {

    /**
     * create session
     *
     * @param user user
     * @param ip   ip
     * @return session string
     */
    String createSession(User user, String ip);


    /**
     * sign out
     *
     * @param ip
     * @param loginUser
     * @return
     */
    Map<String, Object> signOut(String ip, User loginUser);
}
