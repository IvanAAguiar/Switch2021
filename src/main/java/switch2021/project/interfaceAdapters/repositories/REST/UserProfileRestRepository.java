package switch2021.project.interfaceAdapters.repositories.REST;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import switch2021.project.dataModel.REST.UserProfileRestDTO;
import javax.net.ssl.SSLException;
import java.util.Collections;
import java.util.List;


@Repository
public class UserProfileRestRepository {

    public static final String ENDPOINT = "https://vs866.dei.isep.ipp.pt:8443/switchproject-1.0-SNAPSHOT/api";
    public static final String COLLECTION = "/profiles";

    public List<UserProfileRestDTO> findAll() throws SSLException {

        SslContext context = SslContextBuilder.forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();

        HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(context));

        WebClient webClient = WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl(ENDPOINT)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", ENDPOINT))
                .build();

       Mono<List<UserProfileRestDTO>> userProfileRestDTO;

        try {
            userProfileRestDTO = webClient
                    .get()
                    .uri(ENDPOINT + COLLECTION)
                    .retrieve()

                    .onStatus(HttpStatus::is4xxClientError, error -> {
                        return Mono.empty();
                    })

                    .bodyToFlux(UserProfileRestDTO.class)

                    .onErrorReturn(null)

                    .doOnError(throwable -> {
                        System.out.println(throwable.getMessage());
                    })
                    .collectList()
                    .log();
        } catch (Exception e) {
            userProfileRestDTO = null;
        }

        if (userProfileRestDTO != null)
            return userProfileRestDTO.block();
        else
            return
                    Collections.EMPTY_LIST;
    }
}
