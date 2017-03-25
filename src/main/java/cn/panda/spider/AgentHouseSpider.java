package cn.panda.spider;

import cn.panda.dao.FiveEightHousesDao;
import cn.panda.entity.AgentHouseResource;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.QueueScheduler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lingj on 2017/3/25 0025.
 */
public class AgentHouseSpider implements PageProcessor {

    Logger logger = Logger.getLogger(AgentHouseSpider.class);
    //经纪人房源列表页
    String agentPageList = "http://ta.58.com/ershoufang/1/pn%s/";
    //经济人详情页链接
    String detailUrl = "http://ta.58.com/ershoufang/\\w+.shtml";

    //Dao
    FiveEightHousesDao fiveEightHousesDao = new FiveEightHousesDao();


    public Site getSite() {
        return site;
    }

    private Site site = Site.me().
            setCharset("utf-8").
            setRetryTimes(10).
            setSleepTime(1000 * 5).
            setTimeOut(30 * 1000).
            addHeader("Referer", "http://ta.58.com/ershoufang/").
            setUserAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");

    public void process(Page page) {

        //目标链接List
        List<String> targetPageList = new ArrayList<String>();

        //List填入数据
        for (int i = 1; i <= 70; i++) {
            String url = String.format(agentPageList, i);
            targetPageList.add(url);
        }
        //加入爬取链接list
        page.addTargetRequests(targetPageList);

        //自动获取待爬取的链接
        List<String> detailList = page.getHtml().links().regex(detailUrl).all();

        //加入目标请求链接
        page.addTargetRequests(detailList);

        //开始爬取详情

        if (page.getUrl().regex(detailUrl).match()) {



            //链接
            String url = page.getUrl().toString();
            //题目
            String title = page.getHtml().xpath("/html/head/title/text()").toString();
            //发布日期
            String publishDate = page.getHtml().xpath("//div[@class='col detailPrimary mb15']//div[@class='mainTitle']//div[@class='mtit_con c_999 f12 clearfix']//ul[@class='mtit_con_left fl']//li[@class='time']/text()").toString().trim();
            //浏览量
            String pageView = page.getHtml().xpath("//*[@id=\"totalcount\"]//text()").toString();
            //小区名
            String villageName = page.getHtml().xpath("//*[@class=\"xiaoqu_txt\"]//p[1]//a[1]//text()").toString();
            //总价
            String allPrice = page.getHtml().xpath("//div[@id='content']//section[@id='main']//div[@class='col detailPrimary mb15']//div[@class='col_sub maintop mb30 clearfix']//div[@class='col_sub sumary ']//ul[@class='suUl']//li[1]//div[@class='su_con']//span[@class='bigpri arial']/text()").toString();
            //单价
            String price = page.getHtml().xpath("//div[@class=\"col_sub sumary \"]//ul//li[1]//div[@class=\"su_con\"]//text()").toString();
            //面积
            String area1 = page.getHtml().xpath("//div[@class=\"col_sub sumary \"]//ul//li[4]//div[@class=\"su_con\"]//text()").toString();
            String area2 = page.getHtml().xpath("//div[@class=\"col_sub sumary \"]//ul//li[3]//div[@class=\"su_con\"]//text()").toString();
            //城区域
            String bigArea1 = page.getHtml().xpath("//div[@class=\"col_sub sumary \"]//ul//li[5]//a[1]//text()").toString();
            String bigArea2 = page.getHtml().xpath("//div[@class=\"col_sub sumary \"]//ul//li[4]//a[1]//text()").toString();
            //小区域
            String littleArea1 = page.getHtml().xpath("//div[@class=\"col_sub sumary \"]//ul//li[5]//a[2]//text()").toString();
            String littleArea2 = page.getHtml().xpath("//div[@class=\"col_sub sumary \"]//ul//li[4]//a[2]//text()").toString();
            //经纪人姓名
            String agentName1 = page.getHtml().xpath("//div[@class=\"col_sub sumary \"]//ul//li[8]//div[@class=\"su_con\"]//span[1]//a[1]//text()").toString();
            String agentName2 = page.getHtml().xpath("//div[@class=\"col_sub sumary \"]//ul//li[7]//div[@class=\"su_con\"]//span[1]//a[1]//text()").toString();
            String agentName3 = page.getHtml().xpath("//div[@class=\"col_sub sumary \"]//ul//li[6]//div[@class=\"su_con\"]//span[1]//a[1]//text()").toString();
            //经纪人电话
            String agentPhone1 = page.getHtml().xpath("//div[@class=\"col_sub sumary \"]//ul//li[7]//div[@class=\"su_con\"]//div[@id=\"movebar\"]//ul//li[@class=\"ico_tel\"]//div[@class=\"su_phone\"]//span[@id=\"t_phone\"]//text()").toString();
            String agentPhone2 = page.getHtml().xpath("//div[@class=\"col_sub sumary \"]//ul//li[8]//div[@class=\"su_con\"]//div[@id=\"movebar\"]//ul//li[@class=\"ico_tel\"]//div[@class=\"su_phone\"]//span[@id=\"t_phone\"]//text()").toString();
            String agentPhone3 = page.getHtml().xpath("//div[@class=\"col_sub sumary \"]//ul//li[9]//div[@class=\"su_con\"]//div[@id=\"movebar\"]//ul//li[@class=\"ico_tel\"]//div[@class=\"su_phone\"]//span[@id=\"t_phone\"]//text()").toString();
            //经纪公司
            String agentCompany = page.getHtml().xpath("//*[@id=\"broker_corp\"]//text()").toString();

            if (page.getHtml().toString().contains("访问过于平频繁，本次访问需要输入验证码")) {

                logger.info("主站58需要输入验证码了！！！");
            }

            //面积
            String area = area1;

            if (null == area1) {
                area = area2;
            }

            //城区区域
            String bigArea = bigArea1;
            if (null == bigArea1) {
                bigArea = bigArea2;
            }

            //小区域
            String littleArea = littleArea1;
            if (null == littleArea1) {
                littleArea = littleArea2;
            }

            //经纪人姓名
            String agentName = agentName1;

            if (null == agentName) {
                agentName = agentName2;
            }

            if (null == agentName) {
                agentName = agentName3;
            }

            //经纪人手机号
            String agentPhone = agentPhone1;
            if (null == agentPhone) {
                agentPhone = agentPhone2;
            }

            if (null == agentPhone) {
                agentPhone = agentPhone3;
            }


            //处理单价
            price = price.replace("  万    （","").replace("元/㎡） ","");

            //处理面积
            Pattern pattern = Pattern.compile("\\d+\\.{0,1}\\d+㎡");
            Matcher matcher = pattern.matcher(area);

            if(matcher.find()){
               area = matcher.group(0);
               area = area.replace("㎡","");
            }

            //获取图片数量
            Pattern pattern1 = Pattern.compile("\\d+图");
            Matcher matcher1 = pattern1.matcher(title);

            String picNum = "0";

            if(matcher1.find()){
                 picNum = matcher1.group(0);
                 picNum = picNum.replace("图","");
            }

            //结束爬取详情
            logger.info("\n");
            logger.info("---------------------------------------------");
            logger.info("url-->" + url);
            logger.info("title-->" + title);
            logger.info("picNum-->"+picNum);
            logger.info("publishDate-->" + publishDate);
            logger.info("pageView-->" + pageView);
            logger.info("villageName-->" + villageName);
            logger.info("allPrice-->" + allPrice);
            logger.info("price-->" + price);
            logger.info("area-->" + area);
            logger.info("bigArea-->" + bigArea);
            logger.info("littleArea-->" + littleArea);
            logger.info("agentName-->" + agentName);
            logger.info("agentPhone-->" + agentPhone);
            logger.info("agentCompany-->" + agentCompany);
            logger.info("---------------------------------------------");
            logger.info("\n");

            AgentHouseResource agentHouseResource = new AgentHouseResource();

            agentHouseResource.setUrl(url);
            agentHouseResource.setPicNum(picNum);
            agentHouseResource.setTitle(title);
            agentHouseResource.setPublishDate(publishDate);
            agentHouseResource.setPageView(pageView);
            agentHouseResource.setVillageName(villageName);
            agentHouseResource.setAllPrice(allPrice);
            agentHouseResource.setPrice(price);
            agentHouseResource.setArea(area);
            agentHouseResource.setBigArea(bigArea);
            agentHouseResource.setLittleArea(littleArea);
            agentHouseResource.setAgentName(agentName);
            agentHouseResource.setAgentPhone(agentPhone);
            agentHouseResource.setAgentCompany(agentCompany);


            try {
                fiveEightHousesDao.add(agentHouseResource);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }

    }


    public static void main(String[] args) {

        Spider.create(new AgentHouseSpider()).
                setScheduler(new QueueScheduler()).
                addUrl("http://ta.58.com/ershoufang/1").
                thread(20).
                run();

    }


    public  static void spiderRun(){
        Spider.create(new AgentHouseSpider()).
                setScheduler(new QueueScheduler()).
                addUrl("http://ta.58.com/ershoufang/1").
                thread(20).
                run();
    }
}
