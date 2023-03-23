package TestCases;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestAPI {
    @Test
        public void Getapi(){
            given().when().get("https://reqres.in/api/users?page=2").then().statusCode(200)
                    .log().body();
        }

        @Test
        public void postapi(){
            HashMap payload = new HashMap();
            payload.put("name","morpheus");
            payload.put("job", "leader");

            Response res = given().contentType("application/json").body(payload)
                    .when()
                    .post("https://reqres.in/api/users")
                    .then()
                    .statusCode(201)
                    .log().body().extract().response();


            String jsonString = res.asString();

            Assert.assertEquals(jsonString.contains("name"), true);

        }

        @Test

        public void putapi(){
            HashMap payload = new HashMap();
            payload.put("name", "morpheus");
            payload.put("job", "zion resident");


            given().contentType("application/json").body(payload)
                    .when()
                    .put("https://reqres.in/api/users/356")
                    .then()
                    .statusCode(200)
                    .log().body().body("name", equalTo("morpheus"));

        }

        @Test
        public void Deleteapi(){
            given()
                    .when()
                    .delete("https://reqres.in/api/users/356").then().statusCode(204)
                    .log().body();
        }
    }

