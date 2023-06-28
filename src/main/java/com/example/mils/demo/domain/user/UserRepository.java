package com.example.mils.demo.domain.user;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserRepository {
    @Select("select * from users")
    List<UserEntity> findAll();

    @Select("select * from users where id = #{id}")
    UserEntity findById(long id);

    @Select("select * from users where email = #{email}")
    Optional<UserEntity> findByEmail(@Param("email") String email);

    @Insert("INSERT INTO users (email, password, authorities) VALUES (#{email}, #{password}, #{authorities})")
    void create(
            @Param("email") String email,
            @Param("password") String password,
            @Param("authorities") String authorities
        );
    
    @Update("UPDATE users SET email = #{email}, password = #{password} WHERE id = #{id}")
    void update(
        @Param("id") long id,
        @Param("email") String email,
        @Param("password") String password
    );
}