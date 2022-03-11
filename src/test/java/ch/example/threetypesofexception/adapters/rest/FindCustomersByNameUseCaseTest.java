package ch.example.threetypesofexception.adapters.rest;

import ch.example.threetypesofexception.adapters.rest.exceptionhandling.ErrorResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FindCustomersByNameUseCaseTest {
    @Autowired
    private TestRestTemplate template;

    @Test
    public void getCustomerByName_mUster_returns2Elements() {
        ResponseEntity<CustomerDto[]> response = template.getForEntity("/customers?name=mUster", CustomerDto[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(response.getBody()).hasSize(2);
        assertThat(response.getBody()).anyMatch(customerDto -> customerDto.customerName().equals("Mustermann") && customerDto.title().equals("MR"));
        assertThat(response.getBody()).anyMatch(customerDto -> customerDto.customerName().equals("Musterfrau") && customerDto.title().equals("MRS"));
    }

    @Test
    public void getCustomerByName_Test_FailsWithConsistencyExcpetion() {
        ResponseEntity<ErrorResponseDto> response = template.getForEntity("/customers?name=test", ErrorResponseDto.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().errorMessage()).contains("UNKNOWN TITLE");
    }

    @Test
    public void getCustomerByName_T_FailsWithApplicationException() {
        ResponseEntity<ErrorResponseDto> response = template.getForEntity("/customers?name=t", ErrorResponseDto.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().errorMessage()).contains("CUSTOMER NAME MUST HAVE AT LEAST TWO CHARACTERS");
    }

    @Test
    public void getCustomerByName_NotExisting_ReturnsEmptyList() {
        ResponseEntity<CustomerDto[]> response = template.getForEntity("/customers?name=not_existing", CustomerDto[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(0);
    }
}