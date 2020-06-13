package com.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.entity.Admins;
import com.entity.Logs;
public interface LogsDao {
   

    int deleteByPrimaryKey(Integer id);

    int insert(Logs record);

    Logs selectByPrimaryKey(Integer id);

 

    int updateByPrimaryKey(Logs record);
    
    
    @Select("select * from logs where roleid!=2  order by id desc limit #{begin}, #{size}")
   	public List<Logs> getListOperateList(@Param("begin")int begin, @Param("size")int size);
    
    @Select("select count(*) from logs where roleid!=2")
	long getTotalNotClient();
    
    
    @Select("select * from logs where roleid=2  order by id desc limit #{begin}, #{size}")
   	public List<Logs> getListClientOperateList(@Param("begin")int begin, @Param("size")int size);
    
    
    @Select("select count(*) from logs where roleid=2")
	long getTotalClient();
} 