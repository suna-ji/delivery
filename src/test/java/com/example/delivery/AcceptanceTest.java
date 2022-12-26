package com.example.delivery;

import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@DisplayName("API 검증 테스트")
@ActiveProfiles("development")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AcceptanceTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void beforeEach() {
        RestAssured.port = port;
    }

    @DisplayName("규칙에 맞지않는 비밀번호를 가지고 회원가입을 합니다. ")
    @Test
    void addUserWithInvalidPassword() {
        Map<String, Object> user = new HashMap<>();
        user.put("userId", "seona");
        user.put("userName", "지선아");
        user.put("password", "1111");

        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(user)
                .when()
                    .post("/api/1/user")
                .then()
                    .statusCode(400)
                    .log().all();
    }

    @DisplayName("규칙에 맞는 비밀번호를 가지고 회원가입을 합니다. ")
    @Test
    void addUserWithValidPassword() {
        Map<String, Object> user = new HashMap<>();
        user.put("userId", "seona");
        user.put("userName", "지선아");
        user.put("password", "abcde11234567!!@@!!");

        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(user)
                .when()
                    .post("/api/1/user")
                .then()
                    .statusCode(201)
                    .assertThat().body("userId", equalTo("seona"))
                    .log().all();
    }

    @DisplayName("더미데이터로 생성된 회원으로 로그인을 합니다.")
    @Test
    void login() {
        Map<String, Object> user = new HashMap<>();
        user.put("userId", "testUser1");
        user.put("password", "abcde123456789!!");

        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(user)
                .when()
                    .post("/api/1/user/login")
                .then()
                    .statusCode(200)
                    .log().all();
    }

    @DisplayName("헤더에 인증토큰을 추가하지 않은 상태로 배달을 조회합니다.")
    @Test
    void deliveryListWithNoAuth() {
        Map<String, Object> deliverySearchOption = new HashMap<>();
        deliverySearchOption.put("fromDateTime", "2022-12-20");
        deliverySearchOption.put("toDateTime", "2022-12-21");

        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(deliverySearchOption)
                .when()
                    .post("/api/1/delivery")
                .then()
                    .statusCode(403)
                    .log().all();
    }

    @DisplayName("헤더에 인증토큰을 추가한 상태로 배달을 조회합니다.")
    @Test
    void deliveryListWithAuth() {
        Map<String, Object> user = new HashMap<>();
        user.put("userId", "testUser1");
        user.put("password", "abcde123456789!!");

        String accessToken = RestAssured
                            .given()
                                .contentType(ContentType.JSON)
                                .body(user)
                            .when()
                                .post("/api/1/user/login")
                            .then()
                                .statusCode(200)
                                .extract()
                                .path("accessToken");

        Map<String, Object> deliverySearchOption = new HashMap<>();
        deliverySearchOption.put("fromDateTime", "2022-12-20");
        deliverySearchOption.put("toDateTime", "2022-12-21");

        RestAssured
                .given()
                    .header("Authorization", "Bearer " + accessToken)
                    .contentType(ContentType.JSON)
                    .body(deliverySearchOption)
                .when()
                    .post("/api/1/delivery")
                .then()
                    .statusCode(200)
                    .log().all();
    }

    @DisplayName("헤더에 인증토큰을 추가하지 않은 상태로 1번주소에서 2번주소로 배달을 수정합니다.")
    @Test
    void modifyDeliveryWithNoAuth() {
        Map<String, Object> doro = new HashMap<>();
        doro.put("sidoId", 2);
        doro.put("sigunguId", 2);
        doro.put("doroNameId", 2);
        doro.put("buildingNumber", 2);

        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(doro)
                .when()
                    .post("/api/1/delivery/1")
                .then()
                    .statusCode(403)
                    .log().all();
    }

    @DisplayName("헤더에 인증토큰을 추가한 상태로 1번주소에서 2번주소로 배달을 수정합니다.")
    @Test
    void modifyDeliveryWithAuth() {
        Map<String, Object> user = new HashMap<>();
        user.put("userId", "testUser1");
        user.put("password", "abcde123456789!!");

        String accessToken = RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(user)
                .when()
                    .post("/api/1/user/login")
                .then()
                    .statusCode(200)
                    .extract()
                    .path("accessToken");

        Map<String, Object> deliverySearchOption = new HashMap<>();
        deliverySearchOption.put("fromDateTime", "2022-12-20");
        deliverySearchOption.put("toDateTime", "2022-12-21");

        Map<String, Object> doro = new HashMap<>();
        doro.put("sidoId", 2);
        doro.put("sigunguId", 2);
        doro.put("doroNameId", 2);
        doro.put("buildingNumber", 2);

        RestAssured
                .given()
                    .header("Authorization", "Bearer " + accessToken)
                    .contentType(ContentType.JSON)
                    .body(doro)
                .when()
                    .put("/api/1/delivery/1")
                .then()
                    .statusCode(200)
                    .assertThat().body("doroId", equalTo(2))
                    .log().all();
    }

}
