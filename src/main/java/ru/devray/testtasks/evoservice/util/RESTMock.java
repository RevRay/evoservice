package ru.devray.testtasks.evoservice.util;

import com.sun.jersey.api.client.WebResource;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RESTMock {

    private static WebResource restService;

    private RESTMock(){}

    public static WebResource getInstance(){
        if (restService != null) {
            return restService;
        } else {
            init();
            return restService;
        }
    }
    
    private static void init(){
        //REST mock setup
        //TODO: move hardcoded values to external file
        String responseBodySuccess = "{\"code\": 200,\"description\": \"success\"}";
        String responseBodyServerError = "{\"code\": 500,\"description\": \"Internal Server Error\"}";
        String responseBodyBadRequest = "{\"code\": 400,\"description\": \"Bad Request\"}";

        String test1RequestBody = "{ \"id\": 76699046, \"name\": \"apple\", \"price\": \"12.10\"}";
        String test2RequestBody = "{ \"id\" : -600, \"name\" : \"cucumber\" , \"price\" : 8.20}";
        String test7RequestBody = "{ \"id\" : 14001029, \"name\" : \"cucumber\" , \"price\" : \"unexpected_string_value\"}";
        String test8RequestBody = "{ \"name\" : \"cucumber\" , \"price\" : 8.20}";
        String test12RequestBody = "{}";

        restService = mock(WebResource.class);
        when(restService.post(String.class, test1RequestBody)).thenReturn(responseBodySuccess);
        when(restService.post(String.class, test2RequestBody)).thenReturn(responseBodyServerError);
        when(restService.post(String.class, test7RequestBody)).thenReturn(responseBodyServerError);
        when(restService.post(String.class, test8RequestBody)).thenReturn(responseBodyBadRequest);
        when(restService.post(String.class, test12RequestBody)).thenReturn(responseBodyBadRequest);

        // при большом количестве кейсов есть смысл отказаться от мок-объекта с хардкодом,
        // и использовать кастомный класс-наследник REST-ресурса с ограниченной логикой,
        // переопределяющий используемые методы родителя.
        // (вместо условного возврата ответа по предикату thenReturn из Mockito)
    }
}
