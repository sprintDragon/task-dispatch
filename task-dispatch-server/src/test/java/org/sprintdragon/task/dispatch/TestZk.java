package org.sprintdragon.task.dispatch;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by wangdi on 17-1-9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DispatchApplication.class, value = "spring.profiles.active=dev")
public class TestZk {

    @Autowired
    CuratorFramework client;

    @Test
    public void testClient() throws Exception {
        for(String str:client.usingNamespace("disp").getChildren().forPath("")){
            System.out.println("##" + str);
        }
    }

    @Test
    public void testCreatNode() throws Exception {
        client.usingNamespace("disp").create().withMode(CreateMode.PERSISTENT).forPath("/192.168.1.2", "192.168.1.2".getBytes());
        client.usingNamespace("disp").create().withMode(CreateMode.PERSISTENT).forPath("/192.168.1.3", "192.168.1.3".getBytes());
    }

    @Test
    public void testDelete() throws Exception {
        client.usingNamespace("disp").delete().forPath("/disp");
    }
}
