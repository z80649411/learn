package com.dr.mybatiscase.mybatis;

import com.dr.mybatiscase.entity.My;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyMapper  {
    int deleteByPrimaryKey(String a);

    int insert(My record);

    int insertSelective(My record);

    My selectByPrimaryKey(String a);

    int updateByPrimaryKeySelective(My record);

    int updateByPrimaryKey(My record);

}