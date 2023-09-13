package com.zlht.pbr.algorithm.wcmp.service;

import com.zlht.pbr.algorithm.wcmp.dao.entity.User;

import java.util.Map;

/**
 * @author zi jian Wang
 */
public interface TokenServiceI {

    /**
     * create session
     *
     * @param user user
     * @return session string
     */
    String createOrUpdateToken(User user);


    /**
     * sign out
     *
     * @param ip
     * @param loginUser
     * @return
     */
    Map<String, Object> signOut(String ip, User loginUser);
}
