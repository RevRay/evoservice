package ru.devray.testtasks.evoservice.util;

import com.sun.jersey.api.client.*;

import java.util.Arrays;
import java.util.List;

import static ru.devray.testtasks.evoservice.util.JSONManager.getDataByTag;
import static ru.devray.testtasks.evoservice.util.JSONManager.jsonConsistent;
import static ru.devray.testtasks.evoservice.util.JSONManager.jsonHasParameters;

public class RESTStub implements UniformInterface {

    private static RESTStub restService;

    private RESTStub(){}

    public static RESTStub getInstance(){
        if (restService == null)
            restService = new RESTStub();
        return restService;
    }

    public <T> T post(Class<T> c, Object requestEntity) throws UniformInterfaceException, ClientHandlerException {
        String request = (String) requestEntity;

        //parameters that must be present in a create request
        List<String> parameterNames = Arrays.asList(new String[]{"id", "name", "price"});

        //pool of available responses
        String responseBodySuccess = "{\"code\": 200,\"description\": \"success\"}";
        String responseBodyServerError = "{\"code\": 500,\"description\": \"Internal Server Error\"}";
        String responseBodyBadRequest = "{\"code\": 400,\"description\": \"Bad Request\"}";

        //check JSON integrity and mandatory parameters present
        if (jsonConsistent(request) && jsonHasParameters(request, parameterNames)) {
            //id, name, price - type and value checks
            try {
                long id = Long.valueOf(getDataByTag(request, "id"));
                double price = Double.valueOf(getDataByTag(request, "price"));
                String name = getDataByTag(request, "name");

                if (id < 0 || price < 0 || name.equals("")) throw new RuntimeException("invalid values");

                return c.cast(responseBodySuccess);
            } catch (Exception e) {
                return c.cast(responseBodyServerError);
            }
        } else {
            return c.cast(responseBodyBadRequest);
        }
    }

    // ------------ empty interface method implementations following ----------

    public ClientResponse head() throws ClientHandlerException {
        return null;
    }

    public <T> T options(Class<T> c) throws UniformInterfaceException, ClientHandlerException {
        return null;
    }

    public <T> T options(GenericType<T> gt) throws UniformInterfaceException, ClientHandlerException {
        return null;
    }

    public <T> T get(Class<T> c) throws UniformInterfaceException, ClientHandlerException {
        return null;
    }

    public <T> T get(GenericType<T> gt) throws UniformInterfaceException, ClientHandlerException {
        return null;
    }

    public void put() throws UniformInterfaceException, ClientHandlerException {

    }

    public void put(Object requestEntity) throws UniformInterfaceException, ClientHandlerException {

    }

    public <T> T put(Class<T> c) throws UniformInterfaceException, ClientHandlerException {
        return null;
    }

    public <T> T put(GenericType<T> gt) throws UniformInterfaceException, ClientHandlerException {
        return null;
    }

    public <T> T put(Class<T> c, Object requestEntity) throws UniformInterfaceException, ClientHandlerException {
        return null;
    }

    public <T> T put(GenericType<T> gt, Object requestEntity) throws UniformInterfaceException, ClientHandlerException {
        return null;
    }

    public void post() throws UniformInterfaceException, ClientHandlerException {

    }

    public void post(Object requestEntity) throws UniformInterfaceException, ClientHandlerException {

    }

    public <T> T post(Class<T> c) throws UniformInterfaceException, ClientHandlerException {
        return null;
    }

    public <T> T post(GenericType<T> gt) throws UniformInterfaceException, ClientHandlerException {
        return null;
    }

    public <T> T post(GenericType<T> gt, Object requestEntity) throws UniformInterfaceException, ClientHandlerException {
        return null;
    }

    public void delete() throws UniformInterfaceException, ClientHandlerException {

    }

    public void delete(Object requestEntity) throws UniformInterfaceException, ClientHandlerException {

    }

    public <T> T delete(Class<T> c) throws UniformInterfaceException, ClientHandlerException {
        return null;
    }

    public <T> T delete(GenericType<T> gt) throws UniformInterfaceException, ClientHandlerException {
        return null;
    }

    public <T> T delete(Class<T> c, Object requestEntity) throws UniformInterfaceException, ClientHandlerException {
        return null;
    }

    public <T> T delete(GenericType<T> gt, Object requestEntity) throws UniformInterfaceException, ClientHandlerException {
        return null;
    }

    public void method(String method) throws UniformInterfaceException, ClientHandlerException {

    }

    public void method(String method, Object requestEntity) throws UniformInterfaceException, ClientHandlerException {

    }

    public <T> T method(String method, Class<T> c) throws UniformInterfaceException, ClientHandlerException {
        return null;
    }

    public <T> T method(String method, GenericType<T> gt) throws UniformInterfaceException, ClientHandlerException {
        return null;
    }

    public <T> T method(String method, Class<T> c, Object requestEntity) throws UniformInterfaceException, ClientHandlerException {
        return null;
    }

    public <T> T method(String method, GenericType<T> gt, Object requestEntity) throws UniformInterfaceException, ClientHandlerException {
        return null;
    }
}
