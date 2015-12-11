/**
 * InfoCategoryPoMapper.java
 * com.keji50.k5.dao.mapper
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 2015年12月9日 		chao.li
 *
 * Copyright (c) 2015, Howbuy Rights Reserved.
 */

package com.keji50.k5.dao.mapper;

import com.keji50.k5.dao.po.InfoCategoryPo;
import java.util.List;

/**
 * 文章栏目持久层操作接口
 *
 * @author chao.li
 * @version
 * @since Ver 1.1
 * @Date 2015年12月9日 下午2:37:26
 *
 * @see
 */
public interface InfoCategoryPoMapper {

    List<InfoCategoryPo> getCategories(String state);
}
