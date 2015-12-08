package com.keji50.k5.dao.po;

import lombok.Data;

/**
 * 资讯栏目信息对象
 * 
 * @author chao.li
 * @version
 * @since Ver 1.1
 * @Date 2015年12月8日 下午4:17:50
 *
 * @see
 */
@Data
public class InfoCategoryPo {

    /**
     * 栏目id
     */
    private int id;

    /**
     * 栏目代码
     */
    private String code;

    /**
     * 栏目名称
     */
    private String name;

    /**
     * 主题颜色
     */
    private String colorCode;

    /**
     * 栏目状态
     */
    private String state;

    /**
     * 父栏目id
     */
    private int parent;

}
