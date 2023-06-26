package com.example.mils.demo.domain.label;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface LabelRepository {  //ラベル操作用インターフェース

    //全てのラベルを取得する
    @Select("select * from labels")
    List<LabelEntity> findAll();

    @Select("select * from labels where id = #{id}")
    LabelEntity findById(@Param("id") Long id); //NULL値を許容しないLong型

    @Insert("insert into labels (name, color) values (#{name}, #{color})")
    void create(@Param("name") String name, @Param("color") String color);

    @Update("UPDATE labels SET name = #{name}, color = #{color} WHERE id = #{id}")
    void update(
        @Param("id") long id, 
        @Param("name") String name, 
        @Param("color") String color
    );
    
    @Delete("DELETE FROM labels WHERE id = #{id}")
    void delete(@Param("id") Long id);
}
