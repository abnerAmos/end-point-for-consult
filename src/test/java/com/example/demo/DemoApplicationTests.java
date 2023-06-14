package com.example.demo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ServiceApplicationTests {

	// Captura a porta gerada em uma variável.
	@LocalServerPort
	private int port;

	@Test
	void mustReturnStatusOk_WhenConsultList() {
		RestAssured
			.given()
				.basePath("/test/list")
				.port(port)
				.accept(ContentType.JSON)
			.when()
				.get()
			.then()
				.statusCode(HttpStatus.OK.value());

	}

}
