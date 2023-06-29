package com.example.mils.demo.domain.taskLabel;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

@Mapper
public interface TaskLabelRepository {
    @Select("SELECT * FROM task_labels")
    List<TaskLabelEntity> findAll();

    @Select("SELECT * FROM task_labels WHERE id = #{id}")
    TaskLabelEntity findById(@Param("id") Long id);

    @Select("SELECT * FROM task_labels WHERE task_id = #{taskId}")
    List<TaskLabelEntity> findByTaskId(@Param("taskId") long taskId);

    @Insert("INSERT INTO task_labels (task_id, label_id) VALUES (#{taskId}, #{labelId})")
    void create(@Param("taskId") long taskId, @Param("labelId") long labelId);

    @Delete("DELETE FROM task_labels WHERE id = #{id}")
    void delete(@Param("id") long id);

    @Delete("DELETE FROM task_labels WHERE task_id = #{taskId}")
    void deleteByTaskId(@Param("taskId") long taskId);
}
