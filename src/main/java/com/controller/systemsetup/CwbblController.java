package com.controller.systemsetup;

import com.common.result.CodeMsg;
import com.common.result.Result;
import com.entity.Cwbbl;
import com.service.CwbblService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 冲稳保比例控制器
 *
 * @author 潘根山
 * @create 2018-05-27 17:00
 * @since 1.0.0
 */
@Controller
@RequestMapping("/cwbbl")
public class CwbblController {
    @Autowired
    private CwbblService cwbblService;

    @RequestMapping("/")
    public String toPage(Model model) {
        Cwbbl cwbbl = cwbblService.getCwbbl();
        model.addAttribute("cwbbl", cwbbl);
        return "/cwbbl";
    }

    /**
     * 更新冲稳保比例
     *
     * @param cwbbl 冲稳保比例对象
     * @return 更新是否成功
     */
    @RequestMapping("/update")
    @ResponseBody
    public Result updateCwbbl(Cwbbl cwbbl) {
        int count = cwbblService.updateCwbbl(cwbbl);
        if (count == 0) {
            return Result.error(CodeMsg.OPERATION_ERROR);
        }
        return Result.success(true);
    }
}