package cn.edu.nju.fantasybox.mapper;

import cn.edu.nju.fantasybox.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    User select(long id);

    List<User> selectAll();
}
