package com.controller.systemsetup;

import com.common.result.CodeMsg;
import com.common.result.Result;
import com.entity.CandidateNum;
import com.service.CandidateNumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 考生人数控制器
 *
 * @author 潘根山
 * @create 2018-06-03 09:27
 * @since 1.0.0
 */
@Controller
@RequestMapping("/candidateNum")
public class CandidateNumController {
    @Autowired
    private CandidateNumService candidateNumService;

    @RequestMapping("/")
    public String toPage(Model model) {
        CandidateNum candidateNum = candidateNumService.getCandidateNum();
        model.addAttribute("candidateNum", candidateNum);
        return "/candidate_num";
    }

    /**
     * 更新考生人数
     *
     * @param candidateNum 冲稳保比例对象
     * @return 更新是否成功
     */
    @RequestMapping("/update")
    @ResponseBody
    public Result updateCwbbl(CandidateNum candidateNum) {
        if (candidateNum.getNum() == null || candidateNum.getNum() == 0) {
            return Result.error(CodeMsg.CANDIDATE_NUM_UNVALID);
        }
        int count = candidateNumService.updateCandidateNum(candidateNum);
        if (count == 0) {
            return Result.error(CodeMsg.OPERATION_ERROR);
        }
        return Result.success(true);
    }
}