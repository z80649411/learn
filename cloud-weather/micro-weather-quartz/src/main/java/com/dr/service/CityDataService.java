package com.dr.service;

import com.dr.vo.City;

import java.util.List;

/**
 * City Data Service.
 *
 * @author <a href="https://waylau.com">Way Lau</a>
 * @since 1.0.0 2017年11月23日
 */
public interface CityDataService {

    /**
     * 获取City列表
     *
     * @return
     * @throws Exception
     */
    List<City> listCity() throws Exception;
}
