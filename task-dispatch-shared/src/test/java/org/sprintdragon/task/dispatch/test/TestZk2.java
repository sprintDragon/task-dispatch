package org.sprintdragon.task.dispatch.test;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Created by wangdi on 17-1-9.
 */
public class TestZk2 {

    public static void main(String[] args) {
        String zookeeperConnectionString = "192.168.1.2:2181";
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(zookeeperConnectionString, retryPolicy);
        client.start();
        System.out.println("##" + client);
    }


}
