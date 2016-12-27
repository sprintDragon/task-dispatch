package org.sprintdragon.task.dispatch.biz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.sprintdragon.task.dispatch.DispatchApplication;
import org.sprintdragon.task.dispatch.biz.taskinfo.domain.TaskInfo;
import org.sprintdragon.task.dispatch.biz.taskinfo.service.TaskInfoExecuteService;

import javax.annotation.Resource;

/**
 * Created by wangdi on 16-12-27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DispatchApplication.class, value = "spring.profiles.active=dev")
public class TaskInfoExecuteServiceTest {

    @Resource
    TaskInfoExecuteService taskInfoExecuteService;

    @Test
    public void testExecute() throws Exception {
        try {
            taskInfoExecuteService.execute(new TaskInfo());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
