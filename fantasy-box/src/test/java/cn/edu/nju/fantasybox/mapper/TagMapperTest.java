package cn.edu.nju.fantasybox.mapper;

import cn.edu.nju.fantasybox.entity.TagEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TagMapperTest {
    @Autowired
    TagMapper tagMapper;

    @Test
    @Transactional
    public void testInsertAllTags(){
        List<TagEntity> tagEntities = new ArrayList<>();
        for (int i = 6; i < 11 ; i++) {
            TagEntity tagEntity = new TagEntity();
            tagEntity.setTagName("tag"+i);
            tagEntity.setProductId(1);
            tagEntities.add(tagEntity);
        }
        int res = tagMapper.insertAll(tagEntities);
        System.out.println(res);
    }

}
