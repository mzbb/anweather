package com.anweather.app.util;

import android.text.TextUtils;

import com.anweather.app.model.AnWeatherDB;
import com.anweather.app.model.City;
import com.anweather.app.model.County;
import com.anweather.app.model.Province;

/**
 * Created by mzbb on 2016/5/22.
 */
public class Utility {
    /**
     * 解析和处理从服务器返回的省级数据
     */
    public synchronized static boolean handleProvincesResponse(AnWeatherDB anWeatherDB,String response){
        if (!TextUtils.isEmpty(response)){
            String [] allProvinces = response.split(",");
            if (allProvinces!=null&&allProvinces.length>0){
                for (String p : allProvinces){
                    String [] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    //将解析出来的数据存储到Province表
                    anWeatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 解析和处理返回的市级数据
     */
    public static boolean handleCitiesResponse(AnWeatherDB anWeatherDB,String response,int provinceId){
        if (!TextUtils.isEmpty(response)) {
            String[] allCities = response.split(",");
                if (allCities !=null &&allCities.length>0){
                    for (String c : allCities){
                        String[] array = c.split("\\|");
                        City city = new City();
                        city.setCityCode(array[0]);
                        city.setCityName(array[1]);
                        city.setProvinceId(provinceId);
                        //将解析出来的数据存到City表
                        anWeatherDB.saveCity(city);
                    }
                    return true;
                }

            }
        return false;
        }



    /**
     * 解析和出来从服务器返回的县级数据
     */

    public static boolean handleCountiesResponse(AnWeatherDB anWeatherDB,String response,int cityId){
        if (!TextUtils.isEmpty(response)){
            String[] allCounties = response.split(",");
            if (allCounties!=null&&allCounties.length>0){
                for (String c : allCounties){
                    String [] array =c.split("\\|");
                    County county = new County();
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    county.setCityId(cityId);
                    //将解析出来的数据存储到County表
                    anWeatherDB.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }
}
