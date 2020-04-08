package cn.edu.nju.fantasybox.util;

import cn.edu.nju.fantasybox.configuration.converter.TagConverter;
import cn.edu.nju.fantasybox.entity.TagEntity;

public class TagConvertTest {
    TagConverter tagConverter = new TagConverter();
    public void test(){
        TagEntity tagEntity = new TagEntity();
        tagConverter.convertFrom("24",tagEntity);
    }

}
