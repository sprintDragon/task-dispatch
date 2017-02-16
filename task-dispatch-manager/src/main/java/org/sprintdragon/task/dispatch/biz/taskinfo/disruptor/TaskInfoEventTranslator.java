package org.sprintdragon.task.dispatch.biz.taskinfo.disruptor;

import com.lmax.disruptor.EventTranslator;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wangdi on 16-7-4.
 */
public class TaskInfoEventTranslator implements EventTranslator<TaskSysKey> {

    public final static Logger logger = LoggerFactory.getLogger(TaskInfoEventTranslator.class);

    TaskSysKey traceCell;

    public TaskInfoEventTranslator(TaskSysKey traceCell) {
        this.traceCell = traceCell;
    }

    @Override
    public void translateTo(TaskSysKey event, long sequence) {
        try {
            PropertyUtils.copyProperties(event, traceCell);
        } catch (Exception e) {
            logger.error("translateTo copyProperties error event={},sequence={}", event, sequence, e);
        }
        //logger.info("push kafka event={},sequence={}", event, sequence);
    }

}
