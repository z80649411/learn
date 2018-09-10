package com.dr.mybatiscase.mybatis;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 邓仁波
 * Date: 2018-05-24
 * Time: 15:42
 */

import com.dr.mybatiscase.entity.UserEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    @Select("select * from user")

    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "nickName", column = "nick_name"),
            @Result(property = "userCode", column = "user_code"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "userPwd", column = "user_pwd"),
            @Result(property = "createDate", column = "create_date"),
            @Result(property = "updateDate", column = "update_date")})
    public List<UserEntity> queryList();

    @Select("SELECT * FROM user WHERE user_id = #{userId}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "nickName", column = "nick_name"),
            @Result(property = "userCode", column = "user_code"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "userPwd", column = "user_pwd"),
            @Result(property = "createDate", column = "create_date"),
            @Result(property = "updateDate", column = "update_date")})
    UserEntity findById(@Param("userId") long userId);

    @Select({" <script>  ",
            " SELECT * FROM user ",
            "  <where>  ",
            " <if test='_parameter != 0 ' > user_id = _parameter </if> ",
            " </where> ",
            " </script> "})
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "nickName", column = "nick_name"),
            @Result(property = "userCode", column = "user_code"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "userPwd", column = "user_pwd"),
            @Result(property = "createDate", column = "create_date"),
            @Result(property = "updateDate", column = "update_date")})
    List<UserEntity> findByIds(long userId);

    @Insert("INSERT INTO USER(nick_name, user_code) VALUES(#{nickName}, #{userCode})")
    int insertParam(@Param("nickName") String nickName, @Param("userCode") String userCode);

    @Insert("INSERT INTO USER(nick_name, user_code) VALUES(#{nickName,jdbcType=VARCHAR}, #{userCode,jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);

    @Insert("insert into user(nick_name,user_code,user_name,user_pwd,create_date,update_date) values(#{nickName},#{userCode},#{userName},#{userPwd},#{createDate},#{updateDate})")
    public int insertEntity(UserEntity entity);

    @Update("UPDATE user SET nick_name=#{nickName} WHERE user_id=#{userId}")
    int updateEntity(UserEntity user);

    @Delete("DELETE FROM user WHERE user_id =#{userId}")
    int delete(Long userId);

    @Delete("DELETE FROM user WHERE user_id =#{userId}")
    int deleteEntity(UserEntity entity);
}