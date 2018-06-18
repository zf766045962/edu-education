package com.service.impl;

import com.dao.UserMapper;
import com.entity.User;
import com.service.UserService;
import com.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 潘根山
 * @create 2018-02-12 10:23
 * @since 1.0.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserVo> listUser(User user) {
        return userMapper.listUser(user);
    }

    @Override
    public void insertSelective(User user) {
        userMapper.insertSelective(user);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    @Override
    public UserVo getUserByName(String userName) {
        return userMapper.getUserByName(userName);
    }

    @Override
    public void updateLastLoginDate(Long id) {
        userMapper.updateLastLoginDate(id);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public String getSaltById(Long id) {
        return userMapper.getSaltById(id);
    }

    @Override
    public void updatePassword(User user) {
        userMapper.updatePassword(user);
    }

    @Override
    public User getPasswordAndSaltById(Long id) {
        return userMapper.getPasswordAndSaltById(id);
    }

    @Override
    public int existsUsername(String userName) {
        return userMapper.existsUserName(userName);
    }
}