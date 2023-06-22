package com.example.mils.demo.domain.task;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TaskRepository {
    @Select("select * from tasks")
    List<TaskEntity> findAll();

    @Select("select * from tasks where id = #{id}")
    TaskEntity findById(@Param("id") long id);

    @Select("select * from tasks where milestone_id = #{milestoneId}")
    List<TaskEntity> findByMilestoneId(@Param("milestoneId") Long milestoneId); // TODO: nullを許可しないlong型に変更する

    @Insert("INSERT INTO tasks (name, description) VALUES (#{name}, #{description})")
    void create(@Param("name") String name, @Param("description") String description);
}
