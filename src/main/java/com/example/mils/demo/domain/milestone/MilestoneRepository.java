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
    
    @Insert("INSERT INTO milestones (milestone, description) VALUES (#{milestone}, #{description})")
    void insert(@Param("milestone") String milestone, @Param("description") String description);
}
