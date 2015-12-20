package com.keji50.k5.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.keji50.k5.common.utils.WebUtils;
import com.keji50.k5.common.utils.constants.Constants;
import com.keji50.k5.dao.po.InfoCommentPo;
import com.keji50.k5.service.InfoCommentService;

@Controller
@RequestMapping(value = "/comment")
public class InfoCommentController {

	@Resource(name = "infoCommentService")
	private InfoCommentService infoCommentService;

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject ajaxGetComments(@PathVariable("id") int infoId, HttpServletRequest request) {
		if (infoId <= 0) {
			return WebUtils.toFailedResponse();
		}
		int authorId = 0;
		try {
			authorId = Integer.parseInt(request.getParameter(Constants.AUTHOR_ID));
		} catch (Exception e) {

		}

		try {
			List<InfoCommentPo> comments = infoCommentService.getCommentsByInfo(infoId, authorId);
			return WebUtils.toResponse(comments, request);
		} catch (Exception e) {
		    e.printStackTrace();
			return WebUtils.toFailedResponse();
		}
	}
	
	@RequestMapping(value = "/post/{id}", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject ajaxPostCommment(@PathVariable("id") int infoId,
			HttpServletRequest request) {
		if (infoId <= 0) {
			return WebUtils.toFailedResponse();
		}

		int authorId = getAuthorId(request);
		if (authorId <= 0) {
			JSONObject response = WebUtils.toFailedResponse();
			response.put(WebUtils.KEY_MESSAGE, "invalid param author_id");
			return response;
		}

		String toAuthor = StringUtils.trimWhitespace(request
				.getParameter(Constants.TO_AUTHOR));
		String content = StringUtils.trimWhitespace(request
				.getParameter(Constants.CONTENT));
		InfoCommentPo comment = new InfoCommentPo(authorId, infoId);
		comment.setToAuthor(toAuthor);
		comment.setContent(content);
		try {
			if (infoCommentService.saveComment(comment)) {
				return WebUtils.toResponse(comment, request);
			} else {
				return WebUtils.toFailedResponse();
			}
		} catch (Exception e) {
			return WebUtils.toFailedResponse();
		}
	}

	private int getAuthorId(HttpServletRequest request) {
		String authorId = request.getParameter(Constants.AUTHOR_ID);

		if (StringUtils.isEmpty(authorId)) {
			return 0;
		}
		try {
			return Integer.parseInt(authorId);
		} catch (Exception e) {
			return 0;
		}
	}
}
