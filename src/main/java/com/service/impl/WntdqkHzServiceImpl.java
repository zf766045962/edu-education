package com.service.impl;

import com.common.Constants;
import com.dao.WntdqkHzMapper;
import com.entity.Wntdqk;
import com.service.RecruitStudentsPlanService;
import com.service.WntdqkHzService;
import com.service.WntdqkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 潘根山
 * @create 2018-04-26 21:18
 * @since 1.0.0
 */
@Service
public class WntdqkHzServiceImpl implements WntdqkHzService {
    private static final Logger logger = LoggerFactory.getLogger(WntdqkHzService.class);
    @Autowired
    private WntdqkHzMapper wntdqkHzMapper;
    @Autowired
    private WntdqkService wntdqkService;
    @Autowired
    private RecruitStudentsPlanService recruitStudentsPlanService;

    @Override
    public void createWntdqkHz(String year) {
        // 清空往年投档数据临时表
        // 向往年投档数据临时表中插入选择年的投档数据
        long startTime = System.currentTimeMillis();
        logger.info("开始删除投档临时数据");
        wntdqkHzMapper.deleteWntdqkHz();
        logger.info("投档临时数据删除完毕");
        logger.info("开始同步{}年投档数据备注", year);
        wntdqkService.updateBz(year, year);
        logger.info("{}年投档数据备注同步完成", year);
        // 生成段数描述数据
        logger.info("开始生成段数描述数据");
        wntdqkService.generateDsms(year);
        logger.info("段数描述数据生成完毕");
        // 更新段数描述数据
        logger.info("开始更新段数描述数据");
        wntdqkService.updateDsms(year);
        logger.info("更新段数描述数据完毕");
        logger.info("更新段数描述数据完毕");
        // 清空临时表
        logger.info("开始删除投档临时数据");
        wntdqkHzMapper.deleteWntdqkHz();
        logger.info("投档临时数据删除完毕");
        // 将正式表的数据插入到临时表中
        logger.info("开始更新投档数据临时表数据");
        wntdqkHzMapper.insertWntdqkHz(year);
        logger.info("更新投档数据临时表数据完毕");
        long endTime = System.currentTimeMillis();
        logger.info("生成完毕，共计用时:{}s", (double) (endTime - startTime) / 1000);
    }

    @Override
    public void splitWntdqk(String year) {
        //情况临时表
        wntdqkHzMapper.deleteWntdqkHz();
        dealWithWntdqkHz(year);
    }

    private void dealWithWntdqkHz(String year) {
        List<Wntdqk> list = wntdqkService.findZymc(year);
        if (list.size() > 0) {
            List<Wntdqk> tempList = new ArrayList<>();
            Wntdqk wntdqkNew;
            for (Wntdqk wntdqk : list) {
                String bz = wntdqk.getBz();
                bz = bz.substring(bz.indexOf("$") + 1, bz.lastIndexOf("$"));
                String[] strs = bz.split("、");
                for (String s : strs) {
                    wntdqkNew = new Wntdqk();
                    setWntdqk(wntdqkNew, wntdqk, s);
                    tempList.add(wntdqkNew);
                }
            }
            //插入到临时表
            dealWithIsertWntdqkTemp(tempList);
            //根据备注删除数据
            wntdqkService.deleteWntdqkByBz(year);
        }
        //将临时表中的数据放回真实表中
        wntdqkService.insertTempToReal();
        //删除临时表数据
        wntdqkService.deleteWntdqkTemp();
    }

    private void dealWithIsertWntdqkTemp(List<Wntdqk> list) {
        int len = list.size();
        if (len <= Constants.EXCEL_BATCH_SIZE) {
            wntdqkService.insertWntdqkTempBatch(list);
        }
        List<Wntdqk> temp = new ArrayList<>(Constants.EXCEL_BATCH_SIZE);
        for (int i = 0; i < len; i++) {
            if (i > 0 && i % Constants.EXCEL_BATCH_SIZE == 0) {
                wntdqkService.insertWntdqkTempBatch(temp);
                temp.clear();
            }
            temp.add(list.get(i));
        }
        if (temp.size() > 0) {
            wntdqkService.insertWntdqkTempBatch(temp);
        }
        temp.clear();
        list.clear();
        list = null;
        temp = null;
    }

    private void setWntdqk(Wntdqk wntdkNew, Wntdqk wntdqk, String s) {
        wntdkNew.setSchoolCode(wntdqk.getSchoolCode());
        wntdkNew.setSchoolName(wntdqk.getSchoolName());
        wntdkNew.setZydm(wntdqk.getZydm());
        wntdkNew.setZymc(s);
        wntdkNew.setKl(wntdqk.getKl());
        wntdkNew.setNf(wntdqk.getNf());
        wntdkNew.setTdrs(wntdqk.getTdrs());
        wntdkNew.setPjf(wntdqk.getPjf());
        wntdkNew.setZdf(wntdqk.getZdf());
        wntdkNew.setTdmc(wntdqk.getTdmc());
        wntdkNew.setPc(wntdqk.getPc());
        wntdkNew.setCkzs(wntdqk.getCkzs());
        wntdkNew.setBz(wntdqk.getBz());
        wntdkNew.setDs(wntdqk.getDs());
        wntdkNew.setDsms(wntdqk.getDsms());
        wntdkNew.setZsjhs(wntdqk.getZsjhs());
        wntdkNew.setZszymc(wntdqk.getZszymc());
    }
}