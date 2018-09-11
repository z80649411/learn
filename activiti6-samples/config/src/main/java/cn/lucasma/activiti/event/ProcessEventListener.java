package cn.lucasma.activiti.event;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author Lucas Ma
 * @Date 2018/6/23 下午4:06
 * 流程监听器
 */
public class ProcessEventListener implements ActivitiEventListener {
    private static final Logger logger = LoggerFactory.getLogger(ProcessEventListener.class);

    @Override
    public void onEvent(ActivitiEvent event) {
        ActivitiEventType type = event.getType();
        //ActivitiEventType 是流程启动
        if (ActivitiEventType.PROCESS_STARTED.equals(type)) {
            logger.info("流程启动 {} \t 实例id {}", type, event.getProcessInstanceId());
        } else if (ActivitiEventType.PROCESS_COMPLETED.equals(type)) {
            //ActivitiEventType 是流程结束
            logger.info("流程结束 {} \t 实例id {}", type, event.getProcessInstanceId());
        }
    }

    @Override
    public boolean isFailOnException() {
        return false;
    }
}
