package com.service.impl;

import com.dao.WntdqkHzMapper;
import com.service.WntdqkHzService;
import com.service.WntdqkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public void createWntdqkHz(String year) {
        // 清空往年投档数据临时表
        // 向往年投档数据临时表中插入选择年的投档数据
        long startTime = System.currentTimeMillis();
        logger.info("开始生成投档临时数据");
        logger.info("开始删除投档临时数据");
        wntdqkHzMapper.deleteWntdqkHz();
        logger.info("投档临时数据删除完毕");
        logger.info("开始同步{}年投档数据备注", year);
        wntdqkService.updateBz(year);
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
}