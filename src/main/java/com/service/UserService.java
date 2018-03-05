package com.service;

import com.entity.User;
import com.vo.UserVo;

import java.util.List;

/**
 * @author 潘根山
 * @create 2018-02-12 10:21
 * @since 1.0.0
 */
public interface UserService {
    /**
     * 查询用户列表
     *
     * @param user
     * @return
     */
    List<UserVo> listUser(User user);

    /**
     * 新建用户
     *
     * @param user
     */
    void insertSelective(User user);

    /**
     * 更新用户数据
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 通过id查询用户
     *
     * @param id 用户id
     * @return 用户信息
     */
    User getUserById(Long id);

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户信息
     */
    User getUserByName(String userName);

    /**
     * 更新登录时间
     *
     * @param id 用户id
     */
    void updateLastLoginDate(Long id);

    /**
     * 更新用户信息
     */
    void updateUser(User user);

    /**
     * 查询用户随机盐
     *
     * @param id 用户id
     * @return salt
     */
    String getSaltById(Long id);

    /**
     * 更新密码
     *
     * @param user password
     *             id
     */
    void updatePassword(User user);


    /**
     * 根据用户id查询密码,盐
     *
     * @param id 用户id
     * @return 用户密码, salt
     */
    User getPasswordAndSaltById(Long id);
}