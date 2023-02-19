package org.example;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Hello world!
 *
 */
public class Client
{
    public static void main( String[] args )
    {
        final String sensorName = "Sensor666";
        registerSensor(sensorName);

        Random random = new Random();
        double minTemp = 0.0;
        double maxTemp = 45.5;
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            sendMeasurement(random.nextDouble()*maxTemp, random.nextBoolean(),sensorName);
        }

    }
    private static void registerSensor(String sensorName){
        final String URL = "http://localhost:8080/sensors/registration";
        Map<String, Object> jsonData = new HashMap<String, Object>();
        jsonData.put("name",sensorName);
        makePostRequestWithJsonData(URL,jsonData);
    }
    private static void sendMeasurement(double value, boolean raining, String sensorName){
        final String URL = "http://localhost:8080/measurements/add";
        Map<String, Object> jsonData = new HashMap<String, Object>();
        jsonData.put("value",value);
        jsonData.put("raining",raining);
        jsonData.put("sensor", Map.of("name",sensorName));
        makePostRequestWithJsonData(URL,jsonData);
    }

   private static void makePostRequestWithJsonData(String url, Map<String,Object> jsonData){
        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

       HttpEntity<Object> request = new HttpEntity<Object>(jsonData,headers);

       try {
           restTemplate.postForObject(url,request,String.class);    // отправляем
           System.out.println("Успешно отправлено на сервер");
       }catch (HttpClientErrorException e){
           System.out.println("Ошибка!!!");
           System.out.println(e.getMessage());
       }


   }
}
