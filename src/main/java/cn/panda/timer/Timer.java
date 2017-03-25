package cn.panda.timer;

import cn.panda.entity.AgentHouseResource;
import cn.panda.spider.AgentHouseSpider;

import java.util.TimerTask;

/**
 * Created by lingj on 2017/3/25 0025.
 */
public class Timer {


    public static void main(String[] args) {

        java.util.Timer timer = new java.util.Timer();
        timer.schedule(new SpirderRun(), 0, 1000*60*15*1);


    }



    static class SpirderRun extends TimerTask{

        @Override
        public void run() {
            AgentHouseSpider.spiderRun();
        }
    }


}
