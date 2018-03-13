package com.controller.systemsetup;

import com.common.result.Result;
import com.dao.SystemFunctionMapper;
import com.entity.SystemFunction;
import com.vo.LoginUser;
import com.vo.TreeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统功能控制器
 *
 * @author 潘根山
 * @create 2018-02-13 13:27
 * @since 1.0.0
 */
@Controller
@RequestMapping("/systemFunction")
public class SystemFunctionController {
    @Autowired
    private SystemFunctionMapper systemFunctionMapper;

    @GetMapping("/lisUsertFunction")
    @ResponseBody
    public Result listUserFunction(LoginUser loginUser) {
        List<SystemFunction> systemFunctions = systemFunctionMapper.listSystemFunctionByRoleId(loginUser.getLoginRoleId());
        return Result.success(systemFunctions);
    }

    @GetMapping("/tree")
    @ResponseBody
    public Result listUserFunctionAll() {
        List<SystemFunction> systemFunctions = systemFunctionMapper.listSystemFunction();
        List<TreeVo> treeVos = new ArrayList<>();
        TreeVo treeVo;
        for (SystemFunction systemFunction : systemFunctions) {
            treeVo = new TreeVo();
            treeVo.setOpen(true);
            treeVo.setId(Long.parseLong(systemFunction.getFunctionCode()));
            treeVo.setName(systemFunction.getFunctionName());
            treeVo.setpId(Integer.parseInt(systemFunction.getParentId()));
            treeVos.add(treeVo);
        }
        return Result.success(treeVos);
    }
}