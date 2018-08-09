package com.eml.mapper;

import com.eml.domain.Cust;

/**
 * @Auther: jerry.feng
 * @Date: 2018/8/9
 * @Description:
 */
public interface CustMapper {

    Cust findCustById(Integer id);

    int insertCust(Cust cust);
}
