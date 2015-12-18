package com.keji50.k5.web.controller;

import org.apache.commons.collections.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.keji50.k5.common.utils.WebUtils;
import com.keji50.k5.common.utils.constants.Command;
import com.keji50.k5.common.utils.constants.Constants;
import com.keji50.k5.dao.po.AccountPo;
import com.keji50.k5.dao.po.InfoPo;
import com.keji50.k5.service.InfoCategoryService;
import com.keji50.k5.service.InfoService;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 *
 * @author chao.li
 * @version
 * @since Ver 1.1
 * @Date 2015年12月10日 下午12:16:44
 *
 * @see
 */
@Controller
@RequestMapping(value = "")
public class InfoController {

    @Resource(name = "infoService")
    private InfoService infoService;

    @Resource(name = "infoCategoryService")
    private InfoCategoryService infoCategoryService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        // 文章目录
        model.addAttribute(Constants.RESPONSE_INFO_CATEGORIES, infoCategoryService.getInfoCatetories());

        List<InfoPo> infos = infoService.getInfos(0, Command.NEXT);
        // 首页文章
        model.addAttribute(Constants.RESPONSE_INFOS, infos);
        // 最后一篇文章的id
        if (CollectionUtils.isNotEmpty(infos)) {
            model.addAttribute(Constants.INFO_OFFSET, infos.get(infos.size() - 1).getId());
        }

        // 热门文章
        model.addAttribute(Constants.RESPONSE_INFOS_HOT, infoService.getHotInfos());
        // 首页动态栏推荐文章
        List<InfoPo> suggestInfos = infoService.getSuggestInfos();
        if (CollectionUtils.isNotEmpty(suggestInfos)) {
            model.addAttribute(Constants.RESPONSE_INFOS_SUGGEST, suggestInfos);
        }
        // 资讯站主页标题
        model.addAttribute(Constants.RESPONSE_TITLE, "资讯首页_科技50");
        return "page/index/index";
    }

    @RequestMapping(value = "/p/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable("id") int infoId, Model model) {
        if (infoId <= 0) {
            return "404";
        }

        // 获取文章， 文章不存在则跳转到404页面
        InfoPo info = infoService.getInfoById(infoId);
        if (info == null) {
            return "404";
        }

        Page<InfoPo> infosNearby = infoService.getInfosNearby(info.getAuthor().getId());
        // 文章详情
        model.addAttribute(Constants.RESPONSE_INFO, info);
        // 用户文章总数量
        model.addAttribute(Constants.RESPONSE_INFOS_TOTAL, infosNearby.getTotal());
        // 用户最近发表的文章
        model.addAttribute(Constants.RESPONSE_INFOS_NEARBY, infosNearby.getResult());
        // 热门文章
        model.addAttribute(Constants.RESPONSE_INFOS_HOT, infoService.getHotInfos());
        // 资讯详情页标题
        model.addAttribute(Constants.RESPONSE_TITLE, info.getTitle() + "_科技50");
        return "page/detail/detail";
    }

    @RequestMapping(value = "/a/{id}", method = RequestMethod.GET)
    public String authorInfos(@PathVariable("id") int authorId, Model model) {
        if (authorId <= 0) {
            return "404";
        }
        // 获取作者信息， 不存在则跳转到404
        AccountPo author = infoService.getAuthorById(authorId);
        if (author == null) {
            return "404";
        }

        Map<String, Object> conditions = new HashMap<String, Object>();
        conditions.put(Constants.AUTHOR_ID, authorId);
        Page<InfoPo> infos = infoService.getInfosByCondition(conditions);

        // 作者信息
        model.addAttribute(Constants.RESPONSE_AUTHOR, author);
        // 作者总文章数
        model.addAttribute(Constants.RESPONSE_INFOS_TOTAL, infos.getTotal());
        // 作者文章列表
        model.addAttribute(Constants.RESPONSE_INFOS, infos.getResult());
        // 最后一篇文章的偏移量
        if (CollectionUtils.isNotEmpty(infos)) {
            model.addAttribute(Constants.INFO_OFFSET, infos.get(infos.size() - 1).getId());
        }
        // 热门文章列表
        model.addAttribute(Constants.RESPONSE_INFOS_HOT, infoService.getHotInfos());
        // 作者详情页标题
        model.addAttribute(Constants.RESPONSE_TITLE, author.getNickname() + "的文章_科技50");
        return "page/author/author";
    }

    @RequestMapping(value = "/p", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject ajaxInfos(HttpServletRequest request) {
        try {
            List<InfoPo> infos = infoService.getInfos(getInfoOffset(request), getCommand(request));
            return WebUtils.toResponse(infos, request);
        } catch (Exception e) {
            return WebUtils.toFailedResponse();
        }

    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject ajaxInfosByCategory(@PathVariable("id") int infocategoryId, HttpServletRequest request) {
        if (infocategoryId <= 0) {
            return WebUtils.toResponse(Collections.emptyList(), request);
        }

        try {
            List<InfoPo> infos = infoService.getInfosByCategory(infocategoryId, getInfoOffset(request), getCommand(request));
            return WebUtils.toResponse(infos, request);
        } catch (Exception e) {
            return WebUtils.toFailedResponse();
        }
    }

    @RequestMapping(value = "/author/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject ajaxInfosByAuthor(@PathVariable("id") int authorId, HttpServletRequest request) {
        if (authorId <= 0) {
            return WebUtils.toResponse(Collections.emptyList(), request);
        }
        try {
            List<InfoPo> infos = infoService.getInfosByAuthor(authorId, getInfoOffset(request), getCommand(request));
            return WebUtils.toResponse(infos, request);
        } catch (Exception e) {
            return WebUtils.toFailedResponse();
        }

    }

    private int getInfoOffset(HttpServletRequest request) {
        String offset = request.getParameter(Constants.INFO_OFFSET);
        if (StringUtils.isEmpty(offset)) {
            return 0;
        }
        try {
            return Integer.parseInt(offset);
        } catch (Exception e) {
            return 0;
        }
    }

    private Command getCommand(HttpServletRequest request) {
        return Command.from(request.getParameter(Constants.COMMAND));
    }

}
