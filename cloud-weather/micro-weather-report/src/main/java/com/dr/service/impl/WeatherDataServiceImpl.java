package com.dr.service.impl;

import com.dr.service.WeatherDataService;
import com.dr.vo.WeatherResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;

@Service
@Slf4j
public class WeatherDataServiceImpl implements WeatherDataService {
    private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";
    private static final long TIME_OUT = 10L;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public WeatherResponse getDataByCityId(String cityId) {
        String uri = WEATHER_URI + "citykey=" + cityId;
        return this.doGetWeahter(uri);
    }

    @Override
    public WeatherResponse getDataByCityName(String cityName) {
        String uri = WEATHER_URI + "city=" + cityName;
        return this.doGetWeahter(uri);
    }

    @Override
    public void syncDateByCityId(String cityId) {
        String uri = WEATHER_URI + "citykey=" + cityId;
        this.saveWeatherData(uri);
    }

    /**
     * 把天气数据放入缓存
     *
     * @param uri
     */
    private void saveWeatherData(String uri) {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();

        // 调用服务接口来获取
        ResponseEntity<byte[]> response = restTemplate.getForEntity(uri, byte[].class);

        if (response.getStatusCodeValue() == 200) {
            byte[] result = response.getBody();

            InputStream inputStream = new ByteArrayInputStream(result);
            try {
                String strBody = zipInputStream(inputStream);
                ops.set(uri, strBody, TIME_OUT, TimeUnit.SECONDS);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 数据写入缓存

    }

    private WeatherResponse doGetWeahter(String uri) {
        //先查缓存 ，缓存有取缓存 没有掉接口
        String strBody = null;
        WeatherResponse resp = null;
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ObjectMapper mapper = new ObjectMapper();

        if (stringRedisTemplate.hasKey(uri)) {
            log.info("redis get {}", uri);
            strBody = ops.get(uri);
        } else {
            ResponseEntity<byte[]> response = restTemplate.getForEntity(uri, byte[].class);

            if (response.getStatusCodeValue() == 200) {
                byte[] result = response.getBody();

                InputStream inputStream = new ByteArrayInputStream(result);
                try {
                     strBody = zipInputStream(inputStream);
                    ops.set(uri, strBody, TIME_OUT, TimeUnit.SECONDS);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        try {
            resp = mapper.readValue(strBody, WeatherResponse.class);
        } catch (IOException e) {
            log.error("err", e);
        }

        return resp;
    }

    private String zipInputStream(InputStream is) throws IOException {
        GZIPInputStream gzip = new GZIPInputStream(is);
        BufferedReader in = new BufferedReader(new InputStreamReader(gzip, "UTF-8"));
        StringBuilder buffer = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null)
            buffer.append(line).append("\n");
        is.close();
        return buffer.toString();
    }
}
