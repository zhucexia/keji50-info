package com.keji50.k5.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.keji50.k5.common.utils.PageUtils;
import com.keji50.k5.dao.mapper.InfoPoMapper;
import com.keji50.k5.dao.po.InfoPo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * 文章查询服务
 *
 * @author   chao.li
 * @version  
 * @since    Ver 1.1
 * @Date	 2015年12月9日		下午4:44:27
 *
 * @see 	 
 */
@Service(value = "infoService")
public class InfoService {
    
    @Resource(name = "infoPoMapper")
    private InfoPoMapper infoPoMapper;
    
    public Page<InfoPo> getInfosByCondition(Map<String, Object> conditions) {
        PageUtils.initPageInfo(conditions);
        PageHelper.startPage((Integer) conditions.remove(PageUtils.PAGE_NUM), (Integer) conditions.remove(PageUtils.PAGE_SIZE));
        
        return infoPoMapper.selectByCondition(conditions);
    }
    
    
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-context.xml");
        InfoService service = (InfoService)applicationContext.getBean("infoService");
        Map<String, Object> conditions = new HashMap<String, Object>();
        conditions.put("authorId", 8);
        Page<InfoPo> page = service.getInfosByCondition(conditions);
        List<InfoPo> list = page.getResult();
        
        System.out.println(list.size());
    }
    
}

