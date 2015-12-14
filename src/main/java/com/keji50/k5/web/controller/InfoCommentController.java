package com.keji50.k5.web.controller;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
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

	@RequestMapping(value = "/ajax/{id}", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject ajaxCommentsByInfo(@PathVariable("id") int infoId,
			HttpServletRequest request) {
		if (infoId <= 0) {
			return WebUtils.toResponse(Collections.emptyList());
		}
		int authorId = 0;
		try {
			authorId = Integer.parseInt(request.getParameter(Constants.AUTHOR_ID));
		} catch (Exception e) {

		}

		try {
			List<InfoCommentPo> comments = infoCommentService.getCommentsByInfo(infoId, authorId);
			return WebUtils.toResponse(comments);
		} catch (Exception e) {
			return WebUtils.toFailedResponse();
		}
	}
}
