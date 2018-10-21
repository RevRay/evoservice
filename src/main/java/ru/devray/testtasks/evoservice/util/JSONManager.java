package ru.devray.testtasks.evoservice.util;

import com.sun.jersey.api.client.ClientResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.List;

public class JSONManager {

    static JSONParser jsonParser = new JSONParser();

    /**
     * Gets attribute value by attribute name
     * @param response
     * @param tagName
     * @return
     */
    public static String getDataByTag(String response, String tagName){
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(response);
            if (jsonObject.get(tagName) != null)
                return String.valueOf(jsonObject.get(tagName));
        } catch (ParseException e) {
            //intentionally blank
        }
        throw new RuntimeException("Error parsing JSON.");
    }

    /**
     * Checks if given attributes are present in JSON
     * @param response
     * @param parameterNames
     * @return
     */
    public static boolean jsonHasParameters(String response, List<String> parameterNames){
        try {
            for (String name : parameterNames) {
                getDataByTag(response, name);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Method to check JSON structure, correct parenthesis and syntax.
     * @param response
     * @return
     */
    public static boolean jsonConsistent(String response){
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(response);
            return true;
        } catch (ParseException e) {}
        return false;
    }

    public static String[] getArrayDataByTag(ClientResponse response, String tagName){
        //TODO
        throw new UnsupportedOperationException();
    }
}
