package cn.lucasma.activiti.event;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author Lucas Ma
 * 自定义事件监听器
 */
public class CustomEventListener implements ActivitiEventListener {
    private static final Logger logger = LoggerFactory.getLogger(CustomEventListener.class);

    @Override
    public void onEvent(ActivitiEvent event) {
        ActivitiEventType eventType = event.getType();

        if(ActivitiEventType.CUSTOM.equals(eventType)){
            logger.info("监听到自定义事件 {} \t {}",eventType,event.getProcessInstanceId());
        }
    }

    @Override
    public boolean isFailOnException() {
        return false;
    }
}
