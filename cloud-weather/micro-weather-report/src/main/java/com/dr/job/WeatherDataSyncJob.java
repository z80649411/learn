package com.dr.job;

import com.dr.service.CityDataService;
import com.dr.service.WeatherDataService;
import com.dr.vo.City;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

@Slf4j
public class WeatherDataSyncJob extends QuartzJobBean {
    @Autowired
    private CityDataService cityDataService;
    @Autowired
    private WeatherDataService weatherDataService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        log.info("WeatherDataSyncJob in.............");
        List<City> cityList = null;
        try {
            cityList = cityDataService.listCity();
        } catch (Exception e) {
            log.error("err", e);
        }
        assert cityList != null;
        cityList.forEach(c -> {
            weatherDataService.syncDateByCityId(c.getCityId());
        });

    }
}
