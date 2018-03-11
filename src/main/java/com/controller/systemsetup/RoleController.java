package com.controller.systemsetup;

import com.common.result.Result;
import com.entity.Role;
import com.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 角色控制器
 *
 * @author 潘根山
 * @create 2018-02-13 15:28
 * @since 1.0.0
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/")
    public String toRoleFunction(Model model) {
        List<Role> roles = roleService.listRole();
        model.addAttribute("roles", roles);
        return "role_function";
    }

    /**
     * 删除角色信息以及角色关联表信息
     *
     * @param roleId 角色id
     */
    @PostMapping("/del")
    @ResponseBody
    public Result delRole(@RequestParam("roleId") Long roleId) {
        roleService.deleteRoleById(roleId);
        return Result.success(true);
    }

    /**
     * 查询所有的角色信息
     */
    @GetMapping("/listRole")
    @ResponseBody
    public Result listRole() {
        List<Role> roles = roleService.listRole();
        return Result.success(roles);
    }

    @PostMapping("/addRole")
    @ResponseBody
    public Result addRole(@Valid Role role) {
        roleService.insertSelective(role);
        return Result.success(role);
    }

    @PostMapping("updateRole")
    @ResponseBody
    public Result updateRole(@Valid Role role) {
        roleService.updateRoleName(role);
        return Result.success(true);
    }
}