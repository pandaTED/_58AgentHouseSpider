package cn.panda.entity;

/**
 * Created by lingj on 2017/3/25 0025.
 */
public class AgentHouseResource {

    Long id;
    String picNum;
    String url;
    String title;
    String publishDate;
    String pageView;
    String villageName;
    String allPrice;
    String price;
    String area;
    String bigArea;
    String littleArea;
    String agentName;
    String agentPhone;
    String agentCompany;
    Integer isDelete;

    //toString
    @Override
    public String toString() {
        return "AgentHouseResource{" +
                "id=" + id +
                ", picNum='" + picNum + '\'' +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", pageView='" + pageView + '\'' +
                ", villageName='" + villageName + '\'' +
                ", allPrice='" + allPrice + '\'' +
                ", price='" + price + '\'' +
                ", area='" + area + '\'' +
                ", bigArea='" + bigArea + '\'' +
                ", littleArea='" + littleArea + '\'' +
                ", agentName='" + agentName + '\'' +
                ", agentPhone='" + agentPhone + '\'' +
                ", agentCompany='" + agentCompany + '\'' +
                ", isDelete=" + isDelete +
                '}';
    }

    //getter and setter
    public String getPicNum() {
        return picNum;
    }

    public void setPicNum(String picNum) {
        this.picNum = picNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getPageView() {
        return pageView;
    }

    public void setPageView(String pageView) {
        this.pageView = pageView;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(String allPrice) {
        this.allPrice = allPrice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBigArea() {
        return bigArea;
    }

    public void setBigArea(String bigArea) {
        this.bigArea = bigArea;
    }

    public String getLittleArea() {
        return littleArea;
    }

    public void setLittleArea(String littleArea) {
        this.littleArea = littleArea;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentPhone() {
        return agentPhone;
    }

    public void setAgentPhone(String agentPhone) {
        this.agentPhone = agentPhone;
    }

    public String getAgentCompany() {
        return agentCompany;
    }

    public void setAgentCompany(String agentCompany) {
        this.agentCompany = agentCompany;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
