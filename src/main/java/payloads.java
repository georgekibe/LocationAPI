public class payloads {

    public static String addPlaceBody(){
        return "{\n" +
                "  \"location\": {\n" +
                "    \"lat\": -38.383494,\n" +
                "    \"lng\": 33.427362\n" +
                "  },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"name\": \"Frontline house\",\n" +
                "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                "  \"address\": \"29, side layout, cohen 09\",\n" +
                "  \"types\": [\n" +
                "    \"shoe park\",\n" +
                "    \"shop\"\n" +
                "  ],\n" +
                "  \"website\": \"http://google.com\",\n" +
                "  \"language\": \"French-IN\"\n" +
                "}";
    }

    public static String updateLocationBody(){
        return "{\n" +
                "    \"place_id\": \"cbeef2741cce7486303e61bbc3df2945\",\n" +
                "    \"address\": \"705 winter walk, Kenya\",\n" +
                "    \"key\": \"qaclick123\"\n" +
                "}";
    }



}