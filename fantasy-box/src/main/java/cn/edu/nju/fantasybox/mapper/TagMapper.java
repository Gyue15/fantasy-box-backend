package cn.edu.nju.fantasybox.mapper;

import cn.edu.nju.fantasybox.entity.TagEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagMapper {

    List<String> selectAllByCount();

    int insertAll(List<TagEntity> tagEntities);

    List<String> search(List<String> keywords);
}
