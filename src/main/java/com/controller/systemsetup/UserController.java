package com.controller.systemsetup;

import com.common.result.CodeMsg;
import com.common.result.Result;
import com.entity.Role;
import com.entity.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.RoleService;
import com.service.UserService;
import com.util.MD5Util;
import com.vo.LoginUser;
import com.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * 用户控制器
 *
 * @author 潘根山
 * @create 2018-02-12 10:14
 * @since 1.0.0
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("/")
    public String toUser(Model model) {
        List<Role> roleList = roleService.listRole();
        model.addAttribute("roleList", roleList);
        return "user_list";
    }

    /**
     * 跳转到修改密码页面
     */
    @RequestMapping("/password")
    public String toUpdatePassword() {
        return "password_update";
    }

    /**
     * 查询用户列表
     *
     * @param currentPage 当前页
     * @param pageSize    每页显示的条数
     * @param user        查询数据
     * @return userList
     */
    @RequestMapping("/listUser")
    @ResponseBody
    public Result listUser(
            @RequestParam("currentPage") int currentPage
            , @RequestParam("pageSize") int pageSize
            , User user) {
        PageHelper.startPage(currentPage, pageSize);
        List<UserVo> userList = userService.listUser(user);
        PageInfo<UserVo> pageInfo = new PageInfo<>(userList);
        return Result.page(pageInfo.getList(), pageInfo.getTotal());
    }

    /**
     * 根据id查询用户
     *
     * @param id 用户id
     */
    @GetMapping("/getUser")
    @ResponseBody
    public Result getUser(@RequestParam("id") long id) {
        User user = userService.getUserById(id);
        return Result.success(user);
    }

    @GetMapping("/getUserName")
    @ResponseBody
    public Result getUserBySession(LoginUser loginUser) {
        return Result.success(loginUser);
    }

    /**
     * 新增用户
     *
     * @param user user
     */
    @PostMapping("/addUser")
    @ResponseBody
    public Result<Boolean> createUser(@Valid User user) {
        //判断用户名是否存在
        int count = userService.existsUsername(user.getUserName());
        if (count > 0) {
            return Result.error(CodeMsg.USERNAME_EXIST);
        }
        Date date = new Date();
        user.setGmtCreate(date);
        user.setGmtModified(date);
        user.setLastLoginDate(date);
        //随机生成salt
        user.setSalt(MD5Util.randomSalt());
        user.setPassword(MD5Util.createDefaultPassword(user.getSalt()));
        userService.insertSelective(user);
        return Result.success(true);
    }

    /**
     * 修改用户
     *
     * @param user nickName
     *             roleId
     */
    @PostMapping("/editUser")
    @ResponseBody
    public Result<Boolean> editUser(@Valid User user) {
        Date date = new Date();
        user.setGmtModified(date);
        userService.updateUser(user);
        return Result.success(true);
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     */
    @PostMapping("/delUser")
    @ResponseBody
    public Result<Boolean> delUser(@RequestParam("id") Long id) {
        userService.deleteByPrimaryKey(id);
        return Result.success(true);
    }

    /**
     * 重置密码
     *
     * @param id 用户id
     */
    @PostMapping("/resetPassword")
    @ResponseBody
    public Result<Boolean> resetPassword(@RequestParam("id") Long id) {
        String salt = userService.getSaltById(id);
        if (salt == null) {
            Result.error(CodeMsg.USER_NOT_EXISTS);
        }
        User user = new User();
        user.setId(id);
        user.setPassword(MD5Util.createDefaultPassword(salt));
        userService.updatePassword(user);
        return Result.success(true);
    }

    /**
     * 修改密码
     *
     * @param oldPassword 原密码
     * @param newPassword 新密码
     */
    @PostMapping("/updatePassword")
    @ResponseBody
    public Result<Boolean> updatePassword(
            @RequestParam("oldPassword") String oldPassword
            , @RequestParam("newPassword") String newPassword, LoginUser loginUser) {
        User user = userService.getPasswordAndSaltById(loginUser.getLoginId());
        if (user == null) {
            return Result.error(CodeMsg.USER_NOT_EXISTS);
        }
        String password = MD5Util.formPassToDBPass(oldPassword, user.getSalt());
        if (!password.equals(user.getPassword())) {
            return Result.error(CodeMsg.OLD_PASSWORD_ERROR);
        }
        User updateUser = new User();
        updateUser.setPassword(MD5Util.formPassToDBPass(newPassword, user.getSalt()));
        updateUser.setId(loginUser.getLoginId());
        userService.updatePassword(updateUser);
        return Result.success(true);
    }
}