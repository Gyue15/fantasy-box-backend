package cn.edu.nju.fantasybox.configuration.converter;

import cn.edu.nju.fantasybox.entity.TagEntity;
import org.dozer.DozerConverter;

public class TagConverter extends DozerConverter<TagEntity, String> {


    public TagConverter() {
        super(TagEntity.class, String.class);
    }

    @Override
    public String convertTo(TagEntity tagEntity, String s) {
        return tagEntity.getTagName();
    }

    @Override
    public TagEntity convertFrom(String s, TagEntity tagEntity) {
        tagEntity = new TagEntity(-1, s, -1);
        return tagEntity;
    }
}
