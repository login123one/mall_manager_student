package com.fxs.mapper;

import org.apache.ibatis.annotations.Param;

import com.fxs.bean.T_MALL_USER_ACCOUNT;

public interface UserMapper {

	T_MALL_USER_ACCOUNT selectUser(@Param("yh_mch")String yh_mch,@Param("yh_mm") String yh_mm);

}
