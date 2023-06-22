package com.example.mils.demo.domain.milestone;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MilestoneRepository {
    @Select("select * from milestones")
    List<MilestoneEntity> findAll();

    @Select("select * from milestones where id = #{id}")
    MilestoneEntity findById(@Param("id") long id);

    @Insert("INSERT INTO milestones (name, description, deadline) VALUES (#{name}, #{description}, #{deadline})")
    void create(
        @Param("name") String name,
        @Param("description") String description,
        @Param("deadline") LocalDateTime deadline
    );

    @Update("UPDATE milestones SET name = #{name}, description = #{description}, deadline = #{deadline} WHERE id = #{id}")
    void update(
        @Param("id") long id, 
        @Param("name") String name, 
        @Param("description") String description,
        @Param("deadline") LocalDateTime deadline
    );

    @Delete("DELETE FROM milestones WHERE id = #{id}")
    void delete(@Param("id") long id);
}
