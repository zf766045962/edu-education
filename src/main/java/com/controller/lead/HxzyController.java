package com.controller.lead;

import com.common.result.Result;
import com.dao.HxzyMapper;
import com.entity.Hxzy;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 潘根山
 * @create 2018-03-22 22:12
 * @since 1.0.0
 */
@Controller
@RequestMapping("/hzxy")
public class HxzyController {
    @Autowired
    private HxzyMapper hxzyMapper;

    /**
     * 查询考生候选志愿
     *
     * @param candidateId 考生ID
     * @param currentPage 当前页
     * @param pageSize    每页显示的条数
     * @return 候选志愿集合
     */
    @GetMapping("/search")
    @ResponseBody
    public Result listHzxy(
            @RequestParam("pageSize") int pageSize
            , @RequestParam("currentPage") int currentPage
            , @RequestParam("candidateId") Long candidateId) {
        PageHelper.startPage(currentPage, pageSize);
        List<Hxzy> hxzyList = hxzyMapper.listHxzyByCandidateId(candidateId);
        PageInfo<Hxzy> pageInfo = new PageInfo<>(hxzyList);
        return Result.page(pageInfo.getList(), pageInfo.getTotal());
    }

    /**
     * 清空考生候选志愿
     *
     * @param candidateId 考生ID
     * @return result
     */
    @PostMapping("/clear")
    @ResponseBody
    public Result clearAll(@RequestParam("candidateId") Long candidateId) {
        hxzyMapper.deleteAllByCandidateId(candidateId);
        return Result.success(true);
    }

    /**
     * 删除一个或多个候选志愿
     *
     * @param ids 候选志愿集合 id1,id2
     * @return result
     */
    @PostMapping("/delete")
    @ResponseBody
    public Result delete(@RequestParam("ids") String ids) {
        String[] strs = ids.split(",");
        List<Long> idList = new ArrayList<>();
        for (String s : strs) {
            idList.add(Long.parseLong(s));
        }
        hxzyMapper.deleteByIds(idList);
        return Result.success(true);
    }
    //todo 收藏志愿
    //todo 取消志愿
    //todo 导出志愿
}