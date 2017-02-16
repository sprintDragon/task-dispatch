package org.sprintdragon.task.dispatch.web;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.sprintdragon.task.dispatch.core.StatMonitor;

import javax.annotation.Resource;

/**
 * Created by wangdi on 16-12-19.
 */
@RestController
@RequestMapping("/stat")
public class StatController {

    @Resource
    StatMonitor statMonitor;

    @RequestMapping(value = "/view",
            method = RequestMethod.GET,
            produces = "application/json; charset=UTF-8")
    public String statAll() {
        return JSON.toJSONString(statMonitor.getMonitor());
    }

}
