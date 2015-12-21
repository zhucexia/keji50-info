package com.keji50.k5.service;

import com.keji50.k5.common.utils.constants.Constants;

import com.keji50.k5.dao.mapper.InfoCommentPoMapper;
import com.keji50.k5.dao.po.InfoCommentPo;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

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
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put(Constants.INFO_ID, infoId);
        condition.put(Constants.AUTHOR_ID, authorId);

        return infoCommentPoMapper.selectByCondition(condition);
    }

    public boolean saveComment(InfoCommentPo comment) {
        return infoCommentPoMapper.insert(comment) > 0;
    }
    
    public InfoCommentPo getCommentById(int id) {
        return infoCommentPoMapper.selectById(id);
    }
}
