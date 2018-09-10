package com.imooc.wireMock;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * Created by 邓仁波 on 2017-11-1.
 */
public class MockService {
    public static void main(String[] args) throws IOException {

        WireMock.configureFor("123.207.157.149", 8060);
//        WireMock.removeAllMappings();
        mock("/y", "1.json");
//        Product[] products = new Product[]{
//                new Product(1, "第一个商品", 1.99, 3.5, "这是第一个商品,是我在学习慕课网Angular入门实战时创建的", new String[]{"电子产品", "硬件设备"}),
//                new Product(2, "第二个商品", 2.99, 4.5, "这是第二个商品,是我在学习慕课网Angular入门实战时创建的", new String[]{"图书"}),
//                new Product(3, "第三个商品", 3.99, 2.5, "这是第三个商品,是我在学习慕课网Angular入门实战时创建的", new String[]{"电子产品"}),
//                new Product(4, "第四个商品", 4.99, 3.5, "这是第四个商品,是我在学习慕课网Angular入门实战时创建的", new String[]{"硬件设备"}),
//                new Product(5, "第五个商品", 5.99, 4.5, "这是第五个商品,是我在学习慕课网Angular入门实战时创建的", new String[]{"图书"}),
//                new Product(6, "第六个商品", 6.99, 2.5, "这是第六个商品,是我在学习慕课网Angular入门实战时创建的", new String[]{"电子产品", "硬件设备"})
//        };
//        mockObj("/products", products);
//        mockObj("/product/6", new Product(6, "第六个商品", 6.99, 2.5, "这是第六个商品,是我在学习慕课网Angular入门实战时创建的", new String[]{"电子产品", "硬件设备"}));
    }

    private static void mock(String url, String file) throws IOException {
        ClassPathResource resource = new ClassPathResource("mock/response/" + file);
        String content = StringUtils.join(FileUtils.readLines(resource.getFile(), "UTF-8").toArray(), "\n");
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo(url)).willReturn(WireMock.aResponse().withBody(content).withStatus(200)));
    }

    private static void mockObj(String url, Object o) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(o);
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo(url)).willReturn(WireMock.aResponse().withBody(s).withStatus(200)));
    }


}
