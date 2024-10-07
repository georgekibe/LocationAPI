import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class APITesting {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        /// creating a location

        String addPlaceResponse = given().log().all().queryParam("key", "qaclick123").body("{\n" +
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
                        "}")

                .when().post("/maps/api/place/add/json")

                .then().log().all().assertThat().statusCode(200).extract().response().asString();

        System.out.println("The response body" + addPlaceResponse);

        JsonPath js = new JsonPath(addPlaceResponse);
        String placeId = js.getString("place_id");

        System.out.println("The placeID is " + placeId);

        // showing the created location
        given().log().all().queryParam("key", "qaclick123").queryParam("placeId", "placeId")
                .when().get("/maps/api/place/get/json")
                .then().log().all().assertThat().statusCode(200);




    }
}

