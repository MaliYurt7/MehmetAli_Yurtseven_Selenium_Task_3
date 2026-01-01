package com.insiderone.API.DataFiles;

public class jsonPayload {

    public static String addPets() {

        return  "{\n" +
                "  \"id\": 123456,\n" +
                "  \"category\": {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Kangal\"\n" +
                "  },\n" +
                "  \"name\": \"KARABAS\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"https://live.staticflickr.com/7712/16897536993_06f345d3da_b.jpg\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"loyal\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"pending\"\n" +
                "}";
    }



    public static String updateExistingPets() {

        return  "{\n" +
                "  \"id\": 123456,\n" +
                "  \"category\": {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Kangal\"\n" +
                "  },\n" +
                "  \"name\": \"KARABAS\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"https://live.staticflickr.com/7712/16897536993_06f345d3da_b.jpg\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"loyal\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";
    }

    public static String addInvalidStatusPets() {

        return  "{\n" +
                "  \"id\": 123456,\n" +
                "  \"category\": {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Kangal\"\n" +
                "  },\n" +
                "  \"name\": \"KARABAS\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"https://live.staticflickr.com/7712/16897536993_06f345d3da_b.jpg\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"loyal\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"NonExist\"\n" +
                "}";
    }


    public static String addInvalidIdPetStatus() {

        return  "{\n" +
                "  \"id\": 123456,\n" +
                "  \"category\": {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Kangal\"\n" +
                "  },\n" +
                "  \"name\": \"KARABAS\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"https://live.staticflickr.com/7712/16897536993_06f345d3da_b.jpg\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"loyal\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"NonExist\"\n" +
                "}";
    }

    public static String addInvalidIdPetId() {

        return  "{\n" +
                "  \"id\": \"123456\",\n" +
                "  \"category\": {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Kangal\"\n" +
                "  },\n" +
                "  \"name\": \"KARABAS\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"https://live.staticflickr.com/7712/16897536993_06f345d3da_b.jpg\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"loyal\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"Pending\"\n" +
                "}";
    }


    public static String addInvalidIdPetIdWithLetters() {

        return  "{\n" +
                "  \"id\": \"abcde\",\n" +
                "  \"category\": {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Kangal\"\n" +
                "  },\n" +
                "  \"name\": \"KARABAS\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"https://live.staticflickr.com/7712/16897536993_06f345d3da_b.jpg\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"loyal\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"Pending\"\n" +
                "}";
    }




}
