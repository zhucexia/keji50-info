package com.keji50.k5.web.controller;

import com.github.pagehelper.Page;
import com.keji50.k5.common.utils.constants.Command;
import com.keji50.k5.common.utils.constants.Constants;
import com.keji50.k5.dao.po.InfoPo;
import com.keji50.k5.service.InfoCategoryService;
import com.keji50.k5.service.InfoService;
import java.util.Collections;
import java.util.List;
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
        // 首页文章
        model.addAttribute(Constants.RESPONSE_INFOS, infoService.getInfos(0, Command.NEXT));
        // 热门文章
        model.addAttribute(Constants.RESPONSE_INFOS_HOT, infoService.getHotInfos());
        // 首页动态栏推荐文章
        model.addAttribute(Constants.RESPONSE_INFOS_SUGGEST, infoService.getSuggestInfos());

        return "index";
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

        return "detail";
    }

    @RequestMapping(value = "/p", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<InfoPo> ajaxInfos(HttpServletRequest request) {
        return infoService.getInfos(getInfoOffset(request), getCommand(request));
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<InfoPo> ajaxInfosByCategory(@PathVariable("id") int infocategoryId, HttpServletRequest request) {
        if (infocategoryId <= 0) {
            return Collections.emptyList();
        }

        return infoService.getInfosByCategory(infocategoryId, getInfoOffset(request), getCommand(request));
    }

    @RequestMapping(value = "/author/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<InfoPo> ajaxInfosByAuthor(@PathVariable("id") int authorId, HttpServletRequest request) {
        if (authorId <= 0) {
            return Collections.emptyList();
        }

        return infoService.getInfosByAuthor(authorId, getInfoOffset(request), getCommand(request));
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
