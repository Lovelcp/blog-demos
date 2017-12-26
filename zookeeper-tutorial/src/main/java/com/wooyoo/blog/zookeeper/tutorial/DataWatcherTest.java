package com.wooyoo.blog.zookeeper.tutorial;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

public class DataWatcherTest {

    private static class ZKDataListener implements IZkDataListener {

        public void handleDataChange(String dataPath, Object data) throws Exception {
            // create 和 write都会触发
            System.out.println("dataPath:" + dataPath + " data changed. new data:" + data);
        }

        public void handleDataDeleted(String dataPath) throws Exception {
            System.out.println("dataPath:" + dataPath + " deleted");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String zkServers = "localhost:2181";
        ZkClient zkClient = new ZkClient(zkServers, 10000, 10000);
        System.out.println("connected ok");

        String nodePath = "/testData";

        zkClient.subscribeDataChanges(nodePath, new ZKDataListener());

        if (zkClient.exists(nodePath)) {
            zkClient.delete(nodePath);
        }

        zkClient.create(nodePath, "hello", CreateMode.EPHEMERAL);
        zkClient.writeData(nodePath, "world");
        zkClient.delete(nodePath);

        Thread.sleep(3000);
    }
}
