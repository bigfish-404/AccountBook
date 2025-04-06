package com.example.accountbook.repository;

import com.example.accountbook.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    // ユーザー名を通じてデータをクエリ
    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUsername(@Param("username") String username);

    // 新しいユーザーを登録するクエリ
    @Insert("INSERT INTO user (username, password, create_id) VALUES (#{username}, #{password}, #{username})")
    void registerUser(User user);
}
