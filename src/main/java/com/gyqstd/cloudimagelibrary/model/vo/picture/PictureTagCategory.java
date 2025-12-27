package com.gyqstd.cloudimagelibrary.model.vo.picture;

import lombok.Data;

import java.util.List;

/**
 * 图片标签分类列表视图
 * @author GuYuqi
 * @version 1.0
 */
@Data
public class PictureTagCategory {

    /**
     * 标签列表
     */
    private List<String> tagList;

    /**
     * 分类列表
     */
    private List<String> categoryList;
}
