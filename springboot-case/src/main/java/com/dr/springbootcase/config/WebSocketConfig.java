package com.dr.springbootcase.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 *
 * @author 邓仁波
 * @date 2018-2-6
 *
 *  启用WebSocket会造成单元测试无法正常运行
 */
@Component
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

//    @Bean
//    public ServletContextAware endpointExporterInitializer(final ApplicationContext applicationContext) {
//        return servletContext -> {
//            ServerEndpointExporter serverEndpointExporter = new ServerEndpointExporter();
//            serverEndpointExporter.setApplicationContext(applicationContext);
//            try {
//                serverEndpointExporter.afterPropertiesSet();
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        };
//    }
}