package com.keji50.k5.service.system;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.keji50.k5.common.utils.constants.States;
import com.keji50.k5.dao.mapper.InfoCategoryPoMapper;
import com.keji50.k5.dao.mapper.InfoPoMapper;
import com.keji50.k5.dao.po.InfoCategoryPo;
import com.keji50.k5.dao.po.InfoPo;
import com.keji50.k5.service.InfoCategoryService;
import com.keji50.k5.service.InfoService;

/**
 * 后台定时任务， 每隔1分钟更新文章栏目和热门文章
 * 
 * @author sophia
 *
 */
@Service
public class ScheduledService implements InitializingBean, DisposableBean {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledService.class);

    @Resource(name = "infoCategoryPoMapper")
    private InfoCategoryPoMapper infoCategoryPoMapper;

    @Resource(name = "infoPoMapper")
    private InfoPoMapper infoPoMapper;

    @Resource(name = "infoCategoryService")
    private InfoCategoryService infoCategoryService;

    @Resource(name = "infoService")
    private InfoService infoService;

    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

    @Override
    public void afterPropertiesSet() throws Exception {
        executorService.scheduleWithFixedDelay(new ScheduledServiceRunnable(), 5, 30, TimeUnit.SECONDS);
        logger.info("schedule service start");
    }

    private class ScheduledServiceRunnable implements Runnable {

        @Override
        public void run() {
            // 更新文章分类目录
            try {
                List<InfoCategoryPo> infoCategories = infoCategoryPoMapper.getCategories(States.ONLINE.toString());
                if (!CollectionUtils.isEmpty(infoCategories)) {
                    infoCategoryService.setInfoCatetories(infoCategories);

                    logger.info("schedule service update infoCategories {}", infoCategories);
                }
            } catch (Exception e) {

            }

            // 更新热门文章
            try {
                List<InfoPo> hotInfos = infoPoMapper.selectHots();
                if (!CollectionUtils.isEmpty(hotInfos)) {
                    infoService.setHotInfos(hotInfos);

                    logger.info("schedule service update hotInfos {}", hotInfos);
                }
            } catch (Exception e) {

            }

            // 更新首页广告栏文章
            try {
                List<InfoPo> suggestInfos = infoPoMapper.selectSuggests();
                if (!CollectionUtils.isEmpty(suggestInfos)) {
                    infoService.setSuggestInfos(suggestInfos);

                    logger.info("schedule service update suggestInfos {}", suggestInfos);
                }
            } catch (Exception e) {

            }
        }

    }

    @Override
    public void destroy() throws Exception {
        executorService.shutdown();
        logger.info("schedule service stop");
    }

}
