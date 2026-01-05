package com.insiderone.utilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.HashMap;
import java.util.Properties;


public class ConfigurationReader {

    static Properties properties;
    static Properties jsonProperties;

    static String jsonPath;

    static {

        try {
            String path = "configuration.properties";
            jsonPath="configuration.json";
            FileInputStream input = new FileInputStream(path);
            FileInputStream jsonInput = new FileInputStream(jsonPath);
            properties = new Properties();
            properties.load(input);
            jsonProperties =new Properties();
            jsonProperties.load(jsonInput);

            input.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static String get(String keyName) {

        System.out.println("properties.getProperty(keyName) = " + properties.getProperty(keyName));
        return properties.getProperty(keyName);
    }

    public static HashMap<String, String> getTestEnvironmentDetails(String testEnvironment) {
        JSONParser jsonParser = new JSONParser();
        HashMap<String, String> testEnvironmentDataSet = new HashMap<String, String>();

        try (Reader reader = new FileReader(jsonPath))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray testEnvironmentDetailsArray= (JSONArray) obj;
            //System.out.println(testEnvironmentDetailsArray);

            //Iterate over JSON array
            testEnvironmentDataSet= fetchEnvironmentDetails(testEnvironmentDetailsArray,testEnvironment);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return testEnvironmentDataSet;
    }

    private static HashMap<String, String> fetchEnvironmentDetails(JSONArray testEnvironmentDetailsArray, String testEnvironment) {
        HashMap<String,String> testEnvironmentDetails = new HashMap<String, String>();
        for ( Object environmentObj : testEnvironmentDetailsArray) {
            JSONObject testEnvironmentObject = (JSONObject) ((JSONObject)environmentObj).get("environment");
            if (testEnvironmentObject!=null) {
                String environment = (String) testEnvironmentObject.get("environmentName");
                System.out.println("INSIDE FETCH ENV**********"+environment);
                if(environment!=null && environment.equalsIgnoreCase(testEnvironment)){
                    System.out.println(environment);
                    testEnvironmentDetails.put("environment",environment);

                    //Get Test website name
                    String url = (String) testEnvironmentObject.get("url");
                    testEnvironmentDetails.put("url",url);
                    System.out.println(url);

                    //Get careersUrl website url
                    String careersUrl = (String) testEnvironmentObject.get("careersUrl");
                    testEnvironmentDetails.put("careersUrl",careersUrl);
                    System.out.println(careersUrl);

                    //Get Test jobsLeverUrl information
                    String jobsLeverUrl = (String) testEnvironmentObject.get("jobsLeverUrl");
                    testEnvironmentDetails.put("jobsLeverUrl",jobsLeverUrl);
                    System.out.println(jobsLeverUrl);

                    //Get qaJobTitle information
                    String qaJobTitle = (String) testEnvironmentObject.get("qaJobTitle");
                    testEnvironmentDetails.put("qaJobTitle",qaJobTitle);
                    System.out.println(qaJobTitle);

                    //Get apiBaseUrl information
                    String apiBaseUrl = (String) testEnvironmentObject.get("apiBaseUrl");
                    testEnvironmentDetails.put("apiBaseUrl",apiBaseUrl);
                    System.out.println(apiBaseUrl);
                    break;

                }
            }
        }
        return testEnvironmentDetails;
    }

}