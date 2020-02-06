package cn.edu.nju.fantasybox.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagEntity {

    private long id;

    private String tagName;

    private long productId;
}
