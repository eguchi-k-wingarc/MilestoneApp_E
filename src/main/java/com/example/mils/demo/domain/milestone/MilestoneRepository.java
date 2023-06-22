package com.example.mils.demo.domain.milestone;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MilestoneRepository {
    @Select("select * from milestones")
    List<MilestoneEntity> findAll();

    @Select("select * from milestones where id = #{id}")
    MilestoneEntity findById(@Param("id") long id);

    @Insert("INSERT INTO milestones (name, description) VALUES (#{name}, #{description})")
    void insert(@Param("name") String name, @Param("description") String description);
}
