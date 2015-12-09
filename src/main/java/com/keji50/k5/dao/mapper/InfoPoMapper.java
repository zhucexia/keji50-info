package com.keji50.k5.dao.mapper;

import com.github.pagehelper.Page;
import com.keji50.k5.dao.po.InfoPo;
import java.util.Map;

/**
 * 文章持久层接口
 *
 * @author chao.li
 * @version
 * @since Ver 1.1
 * @Date 2015年12月9日 下午2:38:12
 *
 * @see
 */
public interface InfoPoMapper {

    Page<InfoPo> selectByCondition(Map<String, Object> conditions);

}
