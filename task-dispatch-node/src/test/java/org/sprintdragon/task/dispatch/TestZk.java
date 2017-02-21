package org.sprintdragon.task.dispatch;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.sprintdragon.task.dispatch.node.DispatchApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangdi on 17-1-9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DispatchApplication.class, value = "spring.profiles.active=dev")
public class TestZk {

    @Autowired
    CuratorFramework curator;

    @Test
    public void testClient() throws Exception {

//        for (String str : curator.usingNamespace("disp").getChildren().forPath("/")) {
//            System.out.println("##" + str);
//        }
    }

    @Test
    public void testCreatNode() throws Exception {
        curator.usingNamespace("disp").create().withMode(CreateMode.PERSISTENT).forPath("/192.168.1.2", "192.168.1.2".getBytes());
        curator.usingNamespace("disp").create().withMode(CreateMode.PERSISTENT).forPath("/192.168.1.3", "192.168.1.3".getBytes());
    }

    @Test
    public void testDelete() throws Exception {
        curator.usingNamespace("disp").delete().forPath("/disp");
    }

    @Test
    public void testWatch() throws Exception {
        ExecutorService pool = Executors.newCachedThreadPool();
        //设置节点的cache
        TreeCache treeCache = new TreeCache(curator, "/");
        //设置监听器和处理过程
        treeCache.getListenable().addListener(new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                ChildData data = event.getData();
                if(data !=null){
                    switch (event.getType()) {
                        case NODE_ADDED:
                            System.out.println("NODE_ADDED : "+ data.getPath() +"  数据:"+ new String(data.getData()));
                            break;
                        case NODE_REMOVED:
                            System.out.println("NODE_REMOVED : "+ data.getPath() +"  数据:"+ new String(data.getData()));
                            break;
                        case NODE_UPDATED:
                            System.out.println("NODE_UPDATED : "+ data.getPath() +"  数据:"+ new String(data.getData()));
                            break;

                        default:
                            break;
                    }
                }else{
                    System.out.println( "data is null : "+ event.getType());
                }
            }
        });
        //开始监听
        treeCache.start();
    }

    public static void main(String[] args) {

    }


}
