package ru.devray.testtasks.evoservice;

import com.sun.jersey.api.client.UniformInterface;
import com.sun.jersey.api.client.WebResource;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;
import ru.devray.testtasks.evoservice.util.RESTMock;
import ru.devray.testtasks.evoservice.util.RESTStub;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;

import static ru.devray.testtasks.evoservice.util.JSONManager.getDataByTag;

@Description(value = "Тесты на добавление товара с параметрами")
public class RestTest {

    //REST service instance
    UniformInterface restService;

    /*
    Ниже приведенные тесты имеют одинаковую логику, при большом количестве однородных тестов можно
    использовать общий шаблонный метод в сочетании с @DataProvider:

    @DataProvider(name = "entityData")
    public Object[][] provideData(){
        return new Object[][]{{request, expectedCode, expectedDescription},{...},{...}};
    }

    @Test(dataProvider = "entityData")
    public void testTemplate(String request, String expectedCode, String expectedDescription){
        String response = restService.post(String.class, request);
        Assert.assertEquals(getDataByTag(response, "code") , expectedCode);
        Assert.assertEquals(getDataByTag(response, "description") , expectedDescription);
    }
    */

    @BeforeClass
    public void setUp() {

        //hard stub
        restService = RESTStub.getInstance();

        //Mockito
        //restService = RESTMock.getInstance();

    }

    @Title("Тест 1 - валидные данные")
    @Test(description = "valid data")
    public void test1(){
        String requestBody = "{ \"id\": 76699046, \"name\": \"apple\", \"price\": \"12.10\"}";
        String response = restService.post(String.class, requestBody);
        Assert.assertEquals(getDataByTag(response, "code") , "200");
        Assert.assertEquals(getDataByTag(response, "description") , "success");
    }

    @Title("Тест 2 - невалидный id (отрицательное значение)")
    @Test(description = "invalid id value")
    public void test2() {
        String requestBody = "{ \"id\" : -600, \"name\" : \"cucumber\" , \"price\" : 8.20}";
        String response = restService.post(String.class, requestBody);
        Assert.assertEquals(getDataByTag(response, "code") , "500");
        Assert.assertEquals(getDataByTag(response, "description") , "Internal Server Error");
    }

    @Title("Тест 7 - невалидный price (тип)")
    @Test(description = "invalid price type")
    public void test7() {
        String requestBody = "{ \"id\" : 14001029, \"name\" : \"cucumber\" , \"price\" : \"unexpected_string_value\"}";
        String response = restService.post(String.class, requestBody);
        Assert.assertEquals(getDataByTag(response, "code") , "500");
        Assert.assertEquals(getDataByTag(response, "description") , "Internal Server Error");
    }

    @Title("Тест 8 - неполные данные - отсутствует id")
    @Test(description = "data missing id")
    public void test8() {
        String requestBody = "{ \"name\" : \"cucumber\" , \"price\" : 8.20}";
        String response = restService.post(String.class, requestBody);
        Assert.assertEquals(getDataByTag(response, "code") , "400");
        Assert.assertEquals(getDataByTag(response, "description") , "Bad Request");
    }

    @Title("Тест 12 - пустой набор данных {}")
    @Test(description = "empty data")
    public void test12() {
        String requestBody = "{}";
        String response = restService.post(String.class, requestBody);
        Assert.assertEquals(getDataByTag(response, "code") , "400");
        Assert.assertEquals(getDataByTag(response, "description") , "Bad Request");
    }

    @Title("Тест 13 - добавление уже существующего товара")
    @Test(description = "duplicate product")
    public void test13() {
        if (true) throw new SkipException("Тест не реализован");
    }

    @AfterClass
    public void tearDown(){

    }

}
