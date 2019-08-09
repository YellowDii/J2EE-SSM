package nju.software.ssm.dao;


import nju.software.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {
    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "psword",column = "psword"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "statusStr",column = "statusStr"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,
                    many =@Many(select = "nju.software.ssm.dao.IRoleDao.findRoleByUserId"))
    })
    public UserInfo findByUsername(String username) throws Exception;

    @Select("select * from users")
    public List<UserInfo> findAll() throws Exception;

    //注意更改password属性名，应该是psword
    @Update("insert into users(email,username,psword,phoneNum,status)" +
            " values(#{email},#{username},#{psword},#{phoneNum},#{status})")
    void save(UserInfo userInfo) throws Exception;

    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "psword",column = "psword"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "statusStr",column = "statusStr"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,
                    many =@Many(select = "nju.software.ssm.dao.IRoleDao.findRoleByUserId"))
    }
    )
    UserInfo findById(Integer id) throws Exception;
}
