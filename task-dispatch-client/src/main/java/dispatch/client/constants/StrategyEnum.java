package dispatch.client.constants;

import javax.management.timer.Timer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangdi on 16-12-13.
 */
public enum StrategyEnum {
    AFTER_DISTANCE_1_MINUTE(11, "等时长(1min)", Timer.ONE_MINUTE),
    AFTER_DISTANCE_5_MINUTE(12, "等时长(5min)", 5 * Timer.ONE_MINUTE),
    AFTER_DISTANCE_10_MINUTE(13, "等时长(10min)", 10 * Timer.ONE_MINUTE),
    AFTER_DISTANCE_1_HOUR(14, "等时长(1hour)", Timer.ONE_HOUR),
    AFTER_DISTANCE_24_HOUR(15, "等时长(24hour)", Timer.ONE_DAY),
    GROW(20, "递进时长", null);
    private Integer code;
    private String desc;
    private Long distance;

    StrategyEnum(Integer code, String desc, Long distance) {
        this.code = code;
        this.desc = desc;
        this.distance = distance;
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

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public static StrategyEnum getValue(Integer code) {
        for (StrategyEnum cate : StrategyEnum.values()) {
            if (cate.getCode() == code) {
                return cate;
            }
        }
        return null;
    }

    public static Map<Object, Object> getMap() {
        Map<Object, Object> m = new HashMap<Object, Object>();
        for (StrategyEnum cate : StrategyEnum.values()) {
            m.put(cate.getCode(), cate.getDesc());
        }
        return m;
    }
}
