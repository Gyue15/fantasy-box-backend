package cn.edu.nju.fantasybox.mapper;

import cn.edu.nju.fantasybox.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    UserEntity select(long id);

    List<UserEntity> selectAll();
}
