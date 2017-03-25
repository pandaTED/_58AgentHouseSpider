package cn.panda.dao;

import cn.panda.daofactory.HouseSpiderFactory;
import cn.panda.entity.AgentHouseResource;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Administrator on 2016/5/27 0027.
 */
public class FiveEightHousesDao {

    Logger logger = Logger.getLogger(FiveEightHousesDao.class);

    HouseSpiderFactory houseSpiderFactory = new HouseSpiderFactory();

    //add方法
    public void add(AgentHouseResource agentHouseResource) throws ClassNotFoundException, SQLException {
        //获取连接
        Connection connection = houseSpiderFactory.getConnection();

        //设置为不主动提交
        connection.setAutoCommit(false);

        String sql = "insert IGNORE into agenthouseresource (url, title, publishDate, pageView, villageName, allPrice, price, area, bigArea, littleArea, agentName, agentPhone, agentCompany, isDelete,picNum) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1,agentHouseResource.getUrl());
        preparedStatement.setString(2,agentHouseResource.getTitle());
        preparedStatement.setString(3,agentHouseResource.getPublishDate());
        preparedStatement.setString(4,agentHouseResource.getPageView());
        preparedStatement.setString(5,agentHouseResource.getVillageName());
        preparedStatement.setString(6,agentHouseResource.getAllPrice());
        preparedStatement.setString(7,agentHouseResource.getPrice());
        preparedStatement.setString(8,agentHouseResource.getArea());
        preparedStatement.setString(9,agentHouseResource.getBigArea());
        preparedStatement.setString(10,agentHouseResource.getLittleArea());
        preparedStatement.setString(11,agentHouseResource.getAgentName());
        preparedStatement.setString(12,agentHouseResource.getAgentPhone());
        preparedStatement.setString(13,agentHouseResource.getAgentCompany());
        preparedStatement.setInt(14,0);
        preparedStatement.setString(15,agentHouseResource.getPicNum());

        try{
           preparedStatement.execute();
           connection.commit();

        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            preparedStatement.close();
        }

    }



}
