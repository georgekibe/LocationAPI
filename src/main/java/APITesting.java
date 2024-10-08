import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class APITesting {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        System.out.println("================== CREATING A LOCATION =================================================================================");

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


        System.out.println("===================UPDATING A LOCATION ================================================================================");

        // updating a location

        String newAddress = "70 Nairobi walk, Kenya";

        given().log().all().queryParam("place_id", "placeID").queryParam("key", "qaclick123").body("{\n" +
                        "    \"place_id\": \"" + placeId + "\",\n" +
                        "    \"address\": \"" + newAddress + "\",\n" +
                        "    \"key\": \"qaclick123\"\n" +
                        "}")
                .when().put("/maps/api/place/update/json")
                .then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated")); // equalTo from hamcrest


        System.out.println("===================GETTING LOCATION DETAILS================================================================================");

        // getting the details for the created location

        given().log().all().queryParam("key", "qaclick123").queryParam("placeId", "placeId")
                .when().get("/maps/api/place/get/json")
                .then().log().all().assertThat().statusCode(200);


        String getResponse = given().queryParam("key", "qaclick123").queryParam("place_id", placeId)
                .when().get("/maps/api/place/get/json")
                .then().log().all().assertThat().statusCode(200).extract().asString();

        JsonPath js2 = new JsonPath(getResponse);
        System.out.println(js2);
        String getActualAddress = js2.getString("address");
        System.out.println("The old address " + getActualAddress);

        Assert.assertEquals(getActualAddress, newAddress); // testng assertion to confirm address updated successfully


        System.out.println("=======================DELETING LOCATION============================================================================");

        // Deleting a location

        String deleteResponse = given().log().all().queryParam("key", "qaclick123").body("{\n" +
                        "\n" +
                        "    \"place_id\":\"" + placeId + "\"\n" +
                        "}\n").
                when().delete("/maps/api/place/delete/json").
                then().log().all().assertThat().statusCode(200).extract().asString();

        JsonPath js1 = new JsonPath(deleteResponse);
        String statusEntry = js1.getString("status");

        System.out.println("The status for delete is " + statusEntry);

        System.out.println("===================================================================================================");


    }
}

