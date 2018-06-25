package com.service.impl;

import com.dao.CwbblMapper;
import com.entity.Cwbbl;
import com.service.CwbblService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author 潘根山
 * @create 2018-05-27 16:57
 * @since 1.0.0
 */
@Service
public class CwbblServiceImpl implements CwbblService {
    @Autowired
    private CwbblMapper cwbblMapper;

    @Override
    public Cwbbl getCwbbl() {
        return cwbblMapper.getCwbbl();
    }

    @Override
    public int updateCwbbl(Cwbbl cwbbl) {
        cwbbl.setGmtModified(new Date());
        return cwbblMapper.updateCwbbl(cwbbl);
    }
}