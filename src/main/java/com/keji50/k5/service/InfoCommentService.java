package com.keji50.k5.service;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.keji50.k5.dao.mapper.InfoCommentPoMapper;
import com.keji50.k5.dao.po.InfoCommentPo;

/**
 * 文章评论业务service
 * 
 * @author chard.huang
 * @version
 * @since Ver 1.1
 * @Date 2015年12月9日 下午3:34:50
 * 
 * @see
 */
@Service(value = "infoCommentService")
public class InfoCommentService {

	@Resource(name = "infoCommentPoMapper")
	private InfoCommentPoMapper infoCommentPoMapper;

	public List<InfoCommentPo> getCommentsByInfo(int infoId, int authorId) {
		if (infoId <= 0) {
			return Collections.emptyList();
		}

		return infoCommentPoMapper.selectByInfo(infoId, authorId);
	}
}
