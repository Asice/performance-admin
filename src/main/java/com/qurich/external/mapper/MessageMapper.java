package com.qurich.external.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qurich.external.model.Message;

@Mapper
public interface MessageMapper {

	@Insert("insert into message(title,content,summary,href,`from`,create_time,time,is_del,comment,view,stock,lucene)"
			+" values(#{title},#{content},#{summary},#{href},#{from},#{create_time},#{time},#{is_del},#{comment},#{view},#{stock},#{lucene})")
	int saveBean(Message bean);
	
	@Select({"<script>",
        "SELECT * from message", 
        "WHERE is_del=0", 
        "order by time desc limit #{offset},#{rows}",
        "</script>"})
	@Results({  
	    @Result(property="bull_category",column="bull_category")
	})
	List<Message> getBeanList(@Param("offset") int offset,@Param("rows") int rows);
	
	@Select({"<script>",
        "SELECT * from message", 
        "WHERE is_del=0 and lucene=#{lucene}", 
        "order by time desc limit #{offset},#{rows}",
        "</script>"})
	@Results({  
	    @Result(property="bull_category",column="bull_category")
	})
	List<Message> getBeanListLucene(@Param("lucene") int lucene,@Param("offset") int offset,@Param("rows") int rows);
	
	@Select({"<script>",
        "SELECT count(1) from message", 
        "WHERE is_del=#{is_del}",
        "</script>"})
	int getBeanAllCount(@Param("is_del")int is_del);
	
	
	@Update(
	{"<script>",
        "update message set", 
        "view=view+FLOOR(1 +(RAND()*10)),comment=comment+1",
        "</script>"})
	int updateComment();
	
	@Update({"<script>",
		"update message set view=view+FLOOR(1 +(RAND()*10))", 
		"where id in", 
			"<foreach item='item' index='index' collection='list'",
	        "open='(' separator=',' close=')'>",
	        "#{item.id}",
	      "</foreach>",
		"</script>"})
	int updateViewList(List<Message> list);
	
	
	@Update({"<script>",
		"update message set lucene=1", 
		"where id in", 
			"<foreach item='item' index='index' collection='list'",
	        "open='(' separator=',' close=')'>",
	        "#{item.id}",
	      "</foreach>",
		"</script>"})
	int updateLuceneList(List<Message> list);
	
	
	@Select({"<script>",
        "SELECT * from message", 
        "WHERE is_del=0 and `from`=#{type}", 
        "order by time desc limit #{offset},#{rows}",
        "</script>"})
	@Results({  
	    @Result(property="bull_category",column="bull_category")
	})
	List<Message> getBeanListType(@Param("type") int type,@Param("offset") int offset,@Param("rows") int rows);

	@Select({"<script>",
        "SELECT count(1) from message", 
        "WHERE is_del=0 and `from`=#{type}",
        "</script>"})
	int getBeanAllCountType(@Param("type")int type);
	
	
	@Select("SELECT * from message where id=#{id}")
	@Results({  
	    @Result(property="bull_category",column="bull_category")
	})
	Message getBeanById(@Param("id")int id);
	
	@Update("update  message set bull_category=#{bull_category} where id=#{id}")
	int updateBullCategoryById(@Param("id")int id,@Param("bull_category")String bull_category);
	
}
