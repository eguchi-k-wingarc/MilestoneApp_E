package com.example.mils.demo.domain.task;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface TaskRepository {
    @Select("select * from tasks")
    List<TaskEntity> findAll();

    @Select("select * from tasks where id = #{id}")
    TaskEntity findById(@Param("id") long id);

    @Select("select * from tasks where milestone_id = #{milestoneId}")
    List<TaskEntity> findByMilestoneId(@Param("milestoneId") Long milestoneId); // TODO: nullを許可しないlong型に変更する

    @Insert("INSERT INTO tasks (milestone_id, name, description, deadline) VALUES (#{milestoneId}, #{name}, #{description}, #{deadline})")
    void create(@Param("milestoneId") long milestoneId, @Param("name") String name,
            @Param("description") String description, @Param("deadline") LocalDateTime deadline);

    @Update("UPDATE tasks SET name = #{name}, description = #{description}, deadline = #{deadline} WHERE id = #{id}")
    void update(
            @Param("id") long id,
            @Param("name") String name,
            @Param("description") String description,
            @Param("deadline") LocalDateTime deadline
        );

    @Update("UPDATE tasks SET is_complete = #{isComplete} WHERE id = #{id}")
    void updateIsComplete(
        @Param("id") long id,
        @Param("isComplete") Boolean isComplete
    );
        

    @Delete("DELETE FROM tasks WHERE id = #{id}")
    void delete(@Param("id") long id);
}
