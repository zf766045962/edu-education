package com.dao;

import com.entity.User;
import com.vo.UserVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户 repository
 *
 * @author 潘根山
 * @create 2018-02-12 09:58
 * @since 1.0.0
 */
@Repository
public interface UserMapper {
    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 新增用户
     *
     * @param record
     * @return
     */
    int insertSelective(User record);

    /**
     * 通过id查询用户
     *
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     * 更新用户数据
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 查询用户列表
     *
     * @param user
     * @return
     */
    List<UserVo> listUser(User user);

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return
     */
    UserVo getUserByName(String userName);

    /**
     * 更新登录时间
     *
     * @param id 用户id
     */
    @Select("update user set last_login_date=now() where id=#{id}")
    void updateLastLoginDate(@Param("id") Long id);

    /**
     * 更新用户信息
     *
     * @param user userName
     *             roleId
     *             lastLoginDate
     *             id
     */
    @Update("update user set nick_name=#{nickName},role_id=#{roleId},gmt_modified=now() where id=#{id}")
    void updateUser(User user);

    /**
     * 查询用户随机盐
     *
     * @param id 用户id
     * @return salt
     */
    @Select("select salt from user where id=#{id}")
    String getSaltById(@Param("id") Long id);

    /**
     * 更新密码
     *
     * @param user password
     *             id
     */
    @Update("update user set password=#{password} where id=#{id}")
    void updatePassword(User user);

    /**
     * 根据用户id查询密码,盐
     *
     * @param id 用户id
     * @return 用户密码, salt
     */
    @Select("select password,salt from user where id=#{id}")
    User getPasswordAndSaltById(@Param("id") Long id);
}