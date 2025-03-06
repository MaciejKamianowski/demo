package kamianowski.maciej.demo.rest;

import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.core.MediaType;
import kamianowski.maciej.demo.rest.AuthMsg.Owner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@QuarkusTest
public class GithubResourceTest {

    // Mock GithubService
    private GithubService githubService;

    @BeforeEach
    public void setup() {
        githubService = Mockito.mock(GithubService.class);
        QuarkusMock.installMockForType(githubService, GithubService.class);
    }

    @Test
    public void testGetRepositories_HappyPath() {
        // Given
        String username = "testuser";
        List<AuthMsg> mockRepositories = List.of(
                new AuthMsg("repo1", new Owner("owner1"), List.of(new Branch("main")), false),
                new AuthMsg("repo2", new Owner("owner2"), List.of(new Branch("develop")), true)
        );

        // Konfiguracja mocka
        when(githubService.getRepositories(anyString()))
                .thenReturn(Uni.createFrom().item(mockRepositories));

        // When
        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON)
                .when()
                .get("/github/repos/" + username)
                .then()
                .statusCode(200) // Sprawdzamy, czy status to 200 OK
                .extract()
                .response();

        System.out.println(response.asString());
        
        // Then
        response.then()
                .body("size()", equalTo(1)) // Tylko jedno repo nie jest forkiem
                .body("[0].repositoryName", equalTo("repo1")) // Sprawdzamy nazwę repo
                .body("[0].ownerName", equalTo("owner1")) // Sprawdzamy właściciela
                .body("[0].branches[0].branchName", equalTo("main")); // Sprawdzamy gałąź
    }
}
