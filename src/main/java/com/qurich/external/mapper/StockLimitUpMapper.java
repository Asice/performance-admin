package com.qurich.external.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.qurich.external.model.StockLimitUp;

@Mapper
public interface StockLimitUpMapper {

	@Insert("insert into stock_limit_up(stock,price_open,price_close,price_top,price_lower,price_rise,price_riseper,time)"
			+" values(#{stock},#{price_open},#{price_close},#{price_top},#{price_lower},#{price_rise},#{price_riseper},#{time})")
	int saveBean(StockLimitUp bean);
	
	
	@Select("select * from stock_limit_up where `time`=#{time}")
	List<StockLimitUp> getBeanByDate(@Param("time")String time);
	
	
}
