package com.qurich.external.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.qurich.external.model.StockNotice;

@Mapper
public interface StockNoticeMapper {

	@Insert("replace INTO stock_notice (stock, name, yjbd, yjbd_low, yjbd_up, yjbdfd, year_low, year_up, quarter_low, quarter_up, syl, yqsyl_low, yqsyl_up, peratio, yqmgsy_low, yqmgsy_up, nitoce, jrl_last, pday, quarter_day)"
			+" values(#{stock},#{name},#{yjbd},#{yjbd_low},#{yjbd_up},#{yjbdfd},#{year_low},#{year_up},#{quarter_low},#{quarter_up},#{syl},#{yqsyl_low},#{yqsyl_up},#{peratio},#{yqmgsy_low},#{yqmgsy_up},#{nitoce},#{jrl_last},#{pday},#{quarter_day})")
	int saveBean(StockNotice bean);
	
	
	@Select("select * from stock_notice where stock=#{stock} and quarter_day=#{day}")
	@Results({  
	    @Result(property="yjbd_low",column="yjbd_low"),  
	    @Result(property="yjbd_up",column="yjbd_up"),  
	    @Result(property="year_low",column="year_low"),  
	    @Result(property="year_up",column="year_up"),  
	    @Result(property="quarter_low",column="quarter_low"),  
	    @Result(property="quarter_up",column="quarter_up"),  
	    @Result(property="yqsyl_low",column="yqsyl_low"),  
	    @Result(property="yqsyl_up",column="yqsyl_up"),  
	    @Result(property="yqmgsy_low",column="yqmgsy_low"),  
	    @Result(property="yqmgsy_up",column="yqmgsy_up"), 
	    @Result(property="jrl_last",column="jrl_last"),
	    @Result(property="quarter_day",column="quarter_day")
	})
	StockNotice getBeanByStockQuarterDay(@Param("stock")String stock,@Param("day")String day);

	
	@Select({"<script>",
        "SELECT * from stock_notice", 
        "WHERE quarter_day=#{date}", 
        "order by ${field} ${sort} limit #{offset},#{rows}",
        "</script>"})
	@Results({  
		 @Result(property="yjbd_low",column="yjbd_low"),  
		    @Result(property="yjbd_up",column="yjbd_up"),  
		    @Result(property="year_low",column="year_low"),  
		    @Result(property="year_up",column="year_up"),  
		    @Result(property="quarter_low",column="quarter_low"),  
		    @Result(property="quarter_up",column="quarter_up"),  
		    @Result(property="yqsyl_low",column="yqsyl_low"),  
		    @Result(property="yqsyl_up",column="yqsyl_up"),  
		    @Result(property="yqmgsy_low",column="yqmgsy_low"),  
		    @Result(property="yqmgsy_up",column="yqmgsy_up"), 
		    @Result(property="jrl_last",column="jrl_last"),
		    @Result(property="quarter_day",column="quarter_day")
	})
	List<StockNotice> getBeanList(@Param("date") String date,@Param("field") String field,@Param("sort") String sort,@Param("offset") int offset,@Param("rows") int rows);

	
	@Select("SELECT count(id) from stock_notice WHERE quarter_day=#{date}")
	int getBeanAllCount(@Param("date") String date);
	
	@Select("SELECT * from stock_notice group by quarter_day order by quarter_day desc")
	@Results({  
	    @Result(property="quarter_day",column="quarter_day")
	})
	List<StockNotice> getBeanQuarterDayAll();
}
