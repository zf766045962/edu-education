package com.controller;

import com.common.result.CodeMsg;
import com.common.result.Result;
import com.service.RoleFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色控制器
 *
 * @author 潘根山
 * @create 2018-02-23 20:03
 * @since 1.0.0
 */
@Controller
@RequestMapping("/roleFunction")
public class RoleFunctionController {
    @Autowired
    RoleFunctionService roleFunctionService;

    /**
     * 根据roleId 查询 系统功能
     *
     * @param roleId 角色id
     * @return 系统功能
     */
    @GetMapping("/findFunctionCode")
    @ResponseBody
    public Result findFunctionCode(@RequestParam("roleId") Long roleId) {
        List<String> list = roleFunctionService.findFunctionById(roleId);
        return Result.success(list);
    }

    /**
     * 更新角色功能
     *
     * @param roleId 角色id
     * @param codes  功能字符串，以逗号隔开
     */
    @PostMapping("/updateFunctionCode")
    @ResponseBody
    public Result updateFunctionCode(
            @RequestParam("roleId") Long roleId
            , @RequestParam("codes") String codes) {
        String[] codeStrs = codes.split(",");
        if (codeStrs.length == 0) {
            return Result.error(CodeMsg.EMPTY_FUNCTION_CODE);
        }
        roleFunctionService.updateFunctionCode(roleId, codeStrs);
        return Result.success(true);
    }
}
