package io.quarkus;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
@QuarkusTestResource(WireMockAccountService.class)
public class TransactionServiceTest   {
    // Execute the same tests but in packaged mode.
    @Test
    public void testTransaction(){
        given()
            .contentType(ContentType.JSON)
            .body(("152.57"))
                .when().post("/transactions/{accountNumber}",1651351)
                .then()
                .statusCode(200);
    }
}
