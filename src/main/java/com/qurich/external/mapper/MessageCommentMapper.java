package com.qurich.external.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.qurich.external.model.Message;
import com.qurich.external.model.MessageComment;

@Mapper
public interface MessageCommentMapper {

	@Insert("insert into message_comment(mid,uid,content,time,username,icon,ip)"
			+" values(#{mid},#{uid},#{content},#{time},#{username},#{icon},#{ip})")
	int saveBean(MessageComment bean);
	
	@Select({"<script>",
        "SELECT * from message_comment", 
        "WHERE is_del=0 and mid=#{mid}", 
        "order by id desc limit #{offset},#{rows}",
        "</script>"})
	List<Message> getBeanList(@Param("mid") int mid,@Param("offset") int offset,@Param("rows") int rows);
	
}
