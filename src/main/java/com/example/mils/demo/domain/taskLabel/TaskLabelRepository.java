package com.example.mils.demo.domain.taskLabel;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

import com.example.mils.demo.domain.task.TaskEntity;

@Mapper
public interface TaskLabelRepository {
    
    // taskのIDで紐づいたラベルを全て取得
    // return List<TaskLabelEntity>
    @Select("SELECT * FROM labels WHERE task_id = #{taskId}")
    List<TaskLabelEntity> findByTaskId(@Param("taskId") long taskId);

    // taskのIDとlabelのIDを紐づける
    @Insert("INSERT INTO taskLabels (task_id, label_id) VALUES (#{taskId}, #{labelId})")
    void create(@Param("taskId") long taskId, @Param("labelId") long labelId);

    // 紐づけ削除
    @Delete("DELETE FROM taskLabels WHERE id = #{id}")
    void delete(@Param("id") long id);
}
