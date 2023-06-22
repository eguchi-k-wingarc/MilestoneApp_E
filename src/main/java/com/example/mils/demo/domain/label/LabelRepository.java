package com.example.mils.demo.domain.label;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface LabelRepository {  //ラベル操作用インターフェース

    
    @Select("select * from labels")
    List<LabelEntity> findAll();

    @Select("select * from labels where id = #{id}")
    LabelEntity findById(@Param("id") long id);

    @Insert("insert into labels (name) values (#{name})")
    void insert(@Param("name") String name);
}
