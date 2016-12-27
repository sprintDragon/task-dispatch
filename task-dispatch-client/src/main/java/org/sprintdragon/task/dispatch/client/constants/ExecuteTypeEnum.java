package org.sprintdragon.task.dispatch.client.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangdi on 16-12-13.
 */
public enum ExecuteTypeEnum {
    MQ(1, "JMQ"),
    TASK_ENGINE(2, "TASK_ENGINE"),
    HTTP(3, "HTTP");
    private Integer code;
    private String desc;

    ExecuteTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static ExecuteTypeEnum getValue(Integer code) {
        for (ExecuteTypeEnum cate : ExecuteTypeEnum.values()) {
            if (cate.getCode() == code) {
                return cate;
            }
        }
        return null;
    }

    public static Map<Object, Object> getMap() {
        Map<Object, Object> m = new HashMap<Object, Object>();
        for (ExecuteTypeEnum cate : ExecuteTypeEnum.values()) {
            m.put(cate.getCode(), cate.getDesc());
        }
        return m;
    }
}
