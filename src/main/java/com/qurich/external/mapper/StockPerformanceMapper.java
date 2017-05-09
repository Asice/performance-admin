package com.qurich.external.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.qurich.external.model.StockPerformance;

@Mapper
public interface StockPerformanceMapper {

	
	@Insert("replace INTO stock_performance (stock, name, mgsy, yysr, yysr_year, yysr_quarter, jrl, jrl_year, jrl_quarter, mgjzc, jzcsyl, mgxjll, mlv, pday, quarter_day)"
			+" values(#{stock},#{name},#{mgsy},#{yysr},#{yysr_year},#{yysr_quarter},#{jrl},#{jrl_year},#{jrl_quarter},#{mgjzc},#{jzcsyl},#{mgxjll},#{mlv},#{pday},#{quarter_day})")
	int saveBean(StockPerformance bean);
	
	@Select("select * from stock_performance where stock=#{stock} and quarter_day=#{day}")
	@Results({  
	    @Result(property="yysr_year",column="yysr_year"),  
	    @Result(property="yysr_quarter",column="yysr_quarter"),  
	    @Result(property="jrl_year",column="jrl_year"),  
	    @Result(property="jrl_quarter",column="jrl_quarter"),  
	    @Result(property="quarter_day",column="quarter_day")
	})
	StockPerformance getBeanByStockQuarterDay(@Param("stock")String stock,@Param("day")String day);

	
	@Select({"<script>",
        "SELECT * from stock_performance", 
        "WHERE quarter_day=#{date}", 
        "order by ${field} ${sort} limit #{offset},#{rows}",
        "</script>"})
	@Results({  
		@Result(property="yysr_year",column="yysr_year"),  
	    @Result(property="yysr_quarter",column="yysr_quarter"),  
	    @Result(property="jrl_year",column="jrl_year"),  
	    @Result(property="jrl_quarter",column="jrl_quarter"),  
	    @Result(property="quarter_day",column="quarter_day")
	})
	List<StockPerformance> getBeanList(@Param("date") String date,@Param("field") String field,@Param("sort") String sort,@Param("offset") int offset,@Param("rows") int rows);

	
	@Select("SELECT count(id) from stock_performance WHERE quarter_day=#{date}")
	int getBeanAllCount(@Param("date") String date);
	
	@Select("SELECT * from stock_performance group by quarter_day order by quarter_day desc")
	@Results({  
	    @Result(property="quarter_day",column="quarter_day")
	})
	List<StockPerformance> getBeanQuarterDayAll();

}
