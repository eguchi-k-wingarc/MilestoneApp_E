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

    @Select("select * from tasks where id = #{milestoneId}")
    List<TaskEntity> findByMilestoneId(@Param("milestoneId") long milestoneId);

    @Insert("INSERT INTO tasks (name, description) VALUES (#{name}, #{description})")
    void create(@Param("name") String name, @Param("description") String description);
}
