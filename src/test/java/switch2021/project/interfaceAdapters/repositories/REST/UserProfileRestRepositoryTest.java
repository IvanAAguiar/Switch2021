package switch2021.project.interfaceAdapters.repositories.REST;


import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserProfileRestRepositoryTest {

    @Test
    void doTestForceMonoError(){
        //Assert
        assertThrows(NullPointerException.class, () -> {
            String endpoint = "https://vs866.dei.isep.ipp.pt:8443/switchproject-1.0-SNAPSHOT/api";
            String collection = "/projects";
            WebClient webClient = mock(WebClient.class);
            WebClient.RequestHeadersUriSpec requestHeadersUriSpecMock = mock(WebClient.RequestHeadersUriSpec.class);
            when(webClient.get()).thenReturn(requestHeadersUriSpecMock);
            WebClient.RequestBodySpec requestBodySpec = mock(WebClient.RequestBodySpec.class);
            when(requestHeadersUriSpecMock.uri(endpoint + collection)).thenReturn(requestBodySpec);
            WebClient.ResponseSpec responseSpec = mock(WebClient.ResponseSpec.class);
            when(requestBodySpec.retrieve()).thenReturn(responseSpec);
            when(responseSpec.onStatus(HttpStatus::is4xxClientError, error -> Mono.empty())).thenThrow(NullPointerException.class);
            UserProfileRestRepository repository = new UserProfileRestRepository();
            //Act
            repository.findAll();
        });
    }
}