package com.keji50.k5.dao.po;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import lombok.Data;

import org.apache.commons.lang.StringUtils;

/**
 * 资讯文章信息
 *
 * @author chao.li
 * @version
 * @since Ver 1.1
 * @Date 2015年12月9日 下午2:09:29
 *
 * @see
 */
@Data
public class InfoPo {

    /**
     * 文章id
     */
    private int id;

    /**
     * 文章类别
     */
    private InfoCategoryPo infoCategory;

    /**
     * 作者
     */
    private AccountPo author;

    /**
     * 标题
     */
    private String title;

    /**
     * 文章展示图片
     */
    private String image;

    /**
     * 文章简述
     */
    private String shortDesc;

    /**
     * 文章全文
     */
    private String content;

    /**
     * 文章标签 格式 ： 标签1|标签2|标签3
     */
    private String tags;

    /**
     * 是否首页推荐 1是 0不是
     */
    private String suggest;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 文章状态 c:草稿 s:已审核 d:删除
     */
    private String state;

    public List<String> getTags() {
        List<String> tagList = Collections.emptyList();
        if (StringUtils.isNotEmpty(tags)) {
            tagList = Arrays.asList(StringUtils.split(tags, '|'));
        }
        return tagList;
    }

    public String getCreateDate() {
        return createDate == null ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createDate);
    }
}
