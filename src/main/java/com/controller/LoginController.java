package com.controller;

import com.common.Constants;
import com.common.result.CodeMsg;
import com.common.result.Result;
import com.entity.SystemOpeningTime;
import com.service.SystemOpeningTimeService;
import com.service.UserService;
import com.util.MD5Util;
import com.vo.LoginUser;
import com.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 登录控制器
 *
 * @author 潘根山
 * @create 2018-02-12 10:13
 * @since 1.0.0
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private SystemOpeningTimeService openingTimeService;

    @RequestMapping("/")
    public String toLogin() {
        return "login";
    }

    @PostMapping("/do_login")
    @ResponseBody
    public Result login(
            @RequestParam("userName") String userName
            , @RequestParam("password") String password
            , HttpSession session) {
        UserVo user = userService.getUserByName(userName);
        if (user == null) {
            return Result.error(CodeMsg.USER_NOT_EXISTS);
        }
        String dealPassword = MD5Util.formPassToDBPass(password, user.getSalt());
        if (!dealPassword.equals(user.getPassword())) {
            return Result.error(CodeMsg.LOGIN_PASSWORD_ERROR);
        }
        // 判断用户是否为超级用户，管理员无需判断系统是否开放,暂时以包含admin前缀的用户名为超级用户
        if (!Constants.USER_PREFIX.equals(userName)) {
            // 判断系统是否开放
            SystemOpeningTime openingTime = openingTimeService.getSystemOpeningTime();
            if (openingTime == null) {
                return Result.error(CodeMsg.SYSTEM_NOT_OPEN);
            }
            boolean result = checkSystemOpening(openingTime.getStartTime(), openingTime.getEndTime());
            //系统未开放
            if (!result) {
                return Result.error(CodeMsg.SYSTEM_NOT_OPEN);
            }
        }
        LoginUser loginUser = new LoginUser();
        loginUser.setLoginNickName(user.getNickName());
        loginUser.setLoginRoleId(user.getRoleId());
        loginUser.setLoginId(user.getId());
        loginUser.setLoginUserName(userName);
        loginUser.setLoginRoleName(user.getRoleName());
        session.setAttribute(Constants.SESSION_NAME, loginUser);
        //更新登录时间
        userService.updateLastLoginDate(user.getId());
        return Result.success(true);
    }

    @RequestMapping("/logout")
    @ResponseBody
    public Result<Boolean> logout(HttpServletRequest request) {
        request.getSession().removeAttribute(Constants.SESSION_NAME);
        request.getSession().invalidate();
        return Result.success(true);
    }

    private boolean checkSystemOpening(String startTime, String endTime) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        startTime = year + startTime;
        endTime = year + endTime;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            long start = sdf.parse(startTime).getTime();
            long end = sdf.parse(endTime).getTime();
            long now = System.currentTimeMillis();
            return now >= start && now <= end;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}