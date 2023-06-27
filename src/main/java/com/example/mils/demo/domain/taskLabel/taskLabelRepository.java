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
    //タスクラベルを全て取得
    @Select("SELECT * FROM taskLabels")
    List<TaskLabelEntity> findAll();

    @Select("select * from taskLabels where id = #{id}")
    TaskEntity findById(@Param("id") long id);
    
    // 指定したtask_Idを持っているタスクラベルを全て取得
    // return List<LabelEntity>
    @Select("SELECT * FROM labels WHERE task_id = #{taskId}")
    List<TaskLabelEntity> findByTaskId(@Param("taskId") long taskId);

    // taskのIDとlabelのIDを紐づける
    @Insert("INSERT INTO taskLabels (task_id, label_id) VALUES (#{taskId}, #{labelId})")
    void create(@Param("taskId") long taskId, @Param("labelId") long labelId);

    // 紐づけ削除
    @Delete("DELETE FROM taskLabels WHERE id = #{id}")
    void delete(@Param("id") long id);
}
