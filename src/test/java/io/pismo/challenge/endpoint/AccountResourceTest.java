package io.pismo.challenge.endpoint;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;
import javax.ws.rs.core.Response.Status;

import org.junit.jupiter.api.Test;

import io.pismo.challenge.bean.AccountRequestDTO;
import io.pismo.challenge.bean.AccountResponseDTO;
import io.pismo.challenge.bean.ErrorResponseDTO;
import io.pismo.challenge.domain.DocumentType;
import io.pismo.challenge.exception.AccountNotFoundException;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class AccountResourceTest {

	@Test
	public void create() {
		var response = given().contentType(ContentType.JSON).when().body(AccountRequestDTO.builder().documentNumber("12345678901").build())
				.post("/api/accounts").then().statusCode(Status.CREATED.getStatusCode()).extract().body().as(AccountResponseDTO.class);

		assertSoftly(softly -> {
			softly.assertThat(response.getId()).isNotNull();
			softly.assertThat(response.getDocumentNumber()).isEqualTo("12345678901");
			softly.assertThat(response.getDocumentType()).isEqualTo(DocumentType.INDIVIDUAL.code());
			softly.assertThat(response.getAvailableCredit()).isEqualTo(BigDecimal.ZERO);
		});
	}

	@Test
	public void createWithInvalidDocumentNumber() {
		var response = given().contentType(ContentType.JSON).when().body(AccountRequestDTO.builder().documentNumber("123456789").build()).post("/api/accounts")
				.then().statusCode(Status.BAD_REQUEST.getStatusCode()).extract().body().as(ErrorResponseDTO[].class);

		assertThat(response).hasSize(1).allSatisfy(error -> {
			assertSoftly(softly -> {
				softly.assertThat(error.getPath()).isEqualTo("documentNumber");
				softly.assertThat(error.getMessage()).isEqualTo("must match \"^([0-9]{11}|[0-9]{14})$\"");
				softly.assertThat(error.getValue()).isEqualTo("123456789");
				softly.assertThat(error.getKind()).isEqualTo("FIELD");
			});
		});
	}

}
