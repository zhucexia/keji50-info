package com.keji50.k5.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.keji50.k5.common.utils.PageUtils;
import com.keji50.k5.dao.mapper.InfoPoMapper;
import com.keji50.k5.dao.po.InfoPo;

/**
 * 文章查询服务
 * 
 * @author chao.li
 * @version
 * @since Ver 1.1
 * @Date 2015年12月9日 下午4:44:27
 * 
 * @see
 */
@Service(value = "infoService")
public class InfoService {

	@Resource(name = "infoPoMapper")
	private InfoPoMapper infoPoMapper;
	
	// 热门文章
	@Setter @Getter
	private volatile List<InfoPo> hotInfos;

	/**
	 * 获取系统推荐文章列表
	 * 
	 * @return
	 */
	public List<InfoPo> getSuggestInfos() {
		return infoPoMapper.selectSuggests();
	}

	/**
	 * 根据条件查询文章列表信息
	 * 
	 * @param conditions
	 * @return
	 */
	public Page<InfoPo> getInfosByCondition(Map<String, Object> conditions) {
		PageUtils.initPageInfo(conditions);
		PageHelper.startPage((Integer) conditions.remove(PageUtils.PAGE_NUM), (Integer) conditions.remove(PageUtils.PAGE_SIZE));

		return infoPoMapper.selectByCondition(conditions);
	}

	/**
	 * 查询文章信息详情
	 * 
	 * @param id
	 * @return
	 */
	public InfoPo getInfoById(int id) {
		if (id <= 0) {
			return null;
		}

		return infoPoMapper.selectById(id);
	}
	
	/**
	 * 查询当前文章的下一篇文章
	 * @param id
	 * @return
	 */
	public InfoPo getNextInfoById(int id) {
		if (id <= 0) {
			return null;
		}
		
		return infoPoMapper.selectNextById(id);
	}

	/**
	 * 获取作者最近文章集合
	 * 
	 * @param authorId
	 * @return
	 */
	public Page<InfoPo> getInfosNearby(int authorId) {
		if (authorId <= 0) {
			return null;
		}

		// 取用户最近发表的3篇文章
		PageHelper.startPage(1, 3);
		return infoPoMapper.selectByAuthor(authorId);
	}

	public static void main(String[] args) throws Exception {
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"spring-context.xml");
		InfoService service = (InfoService) applicationContext
				.getBean("infoService");
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("authorId", 8);
		Page<InfoPo> page = service.getInfosByCondition(conditions);
		List<InfoPo> list = page.getResult();

		for (InfoPo info : list) {
			System.out.println(info);
		}

		System.out.println(service.getNextInfoById(1));
	}

}
