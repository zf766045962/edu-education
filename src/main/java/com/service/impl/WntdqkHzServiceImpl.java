package com.service.impl;

import com.dao.WntdqkHzMapper;
import com.service.WntdqkHzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 潘根山
 * @create 2018-04-26 21:18
 * @since 1.0.0
 */
@Service
public class WntdqkHzServiceImpl implements WntdqkHzService {
    @Autowired
    private WntdqkHzMapper wntdqkHzMapper;

    @Override
    public void createWntdqkHz(String year) {
        // 清空往年投档数据临时表
        // 向往年投档数据临时表中插入选择年的投档数据
        //todo 将选择年的投档数据与前两年的数据进行比较，计算参考指数和名次
        wntdqkHzMapper.deleteWntdqkHz();
        wntdqkHzMapper.insertWntdqkHz(year);
        int iYear = Integer.parseInt(year);
        String lastYear = "" + (iYear - 1);
        String beforeLastYear = "" + (iYear - 2);
    }
}