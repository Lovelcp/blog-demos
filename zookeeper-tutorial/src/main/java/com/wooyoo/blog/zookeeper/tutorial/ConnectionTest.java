package com.wooyoo.blog.zookeeper.tutorial;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

public class ConnectionTest {
    public static void main(String[] args) {
        String zkServers = "localhost:2181";
        ZkClient zkClient = new ZkClient(zkServers, 10000, 10000);
        System.out.println("connected ok");

        User user = new User();
        user.setId(1);
        user.setName("testUser");

        /*
         * "/testUserNode" :节点的地址
         * user：数据的对象
         * CreateMode.PERSISTENT：创建的节点类型
         */
        String path = zkClient.create("/testUserNode", user, CreateMode.PERSISTENT);
        //输出创建节点的路径
        System.out.println("created path:" + path);
    }
}
