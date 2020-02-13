package cn.edu.nju.fantasybox.mapper;

import cn.edu.nju.fantasybox.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    UserEntity select(long id);

    List<UserEntity> selectAll();

//    int insertEmail(@Param("email")String email, @Param("activeCode") String activateCode);

    UserEntity findByEmail(String email);
    UserEntity findByUserName(String username);


    int insertUser(UserEntity userEntity);

    int saveUser(UserEntity userEntity);
}
