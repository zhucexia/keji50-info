package com.keji50.k5.dao.mapper;

import java.util.List;

import com.keji50.k5.dao.po.InfoCommentPo;

public interface InfoCommentPoMapper {

	/**
	 * 查询文章评论信息
	 * 
	 * @param infoId
	 *            文章id 必须
	 * @param authorId
	 *            评论人id，默认传0。 当存在评论人id时， 返回该文章下所有审核通过的评论信息和该评论人的等待审核的评论信息
	 * @return
	 */
	List<InfoCommentPo> selectByInfo(int infoId, int authorId);

}
