package com.keji50.k5.dao.mapper;

import java.util.List;
import java.util.Map;
import com.github.pagehelper.Page;
import com.keji50.k5.dao.po.InfoPo;

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

	/**
	 * 根据搜索条件分页查找文章
	 * 
	 * @param conditions
	 * @return
	 */
	Page<InfoPo> selectByCondition(Map<String, Object> conditions);

	/**
	 * 根据文章id获取文章
	 * 
	 * @param id
	 * @return
	 */
	InfoPo selectById(int id);

	/**
	 * 获取首页推荐文章列表
	 * 
	 * @return
	 */
	List<InfoPo> selectSuggests();
	
	/**
	 * 获取热门文章列表
	 * 
	 * @return
	 */
	List<InfoPo> selectHots();

	/**
	 * 查询用户最新文章列表
	 * 
	 * @param authorId
	 * @return
	 */
	Page<InfoPo> selectByAuthor(int authorId);
}
