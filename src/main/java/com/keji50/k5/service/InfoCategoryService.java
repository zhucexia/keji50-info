package com.keji50.k5.service;

import com.keji50.k5.common.utils.constants.States;
import com.keji50.k5.dao.mapper.InfoCategoryPoMapper;
import com.keji50.k5.dao.po.InfoCategoryPo;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 文章目录业务service
 *
 * @author   chao.li
 * @version  
 * @since    Ver 1.1
 * @Date	 2015年12月9日		下午3:34:50
 *
 * @see 	 
 */
@Service(value = "infoCategoryService")
public class InfoCategoryService {

    @Resource(name = "infoCategoryPoMapper")
    private InfoCategoryPoMapper infoCategoryPoMapper;
    
    public List<InfoCategoryPo> getAllCategories() {
        return infoCategoryPoMapper.getCategories(States.ONLINE.toString());
    }
}

