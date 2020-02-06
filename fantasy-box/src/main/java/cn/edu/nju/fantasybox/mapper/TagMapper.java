package cn.edu.nju.fantasybox.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagMapper {

    List<String> selectAllByCount();
}
