package com.finance.service;

import com.finance.annotation.DataSource;
import com.finance.exception.BusinessException;
import com.finance.model.pojo.FundNet;

import java.util.List;

/**
 * Created by zt on 2016/12/10.
 */
public interface FundNetService {

    void insertOrUpdateFundNetData() throws Exception;

    @DataSource
    void batchInsertFundNetData(List<FundNet> fundNetList) throws BusinessException;

    void test();
}