package shadow.dictionary.api.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import shadow.dictionary.model.Word;
import shadow.dictionary.service.WordService;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

/**
 * Controller for word API
 */
@Configuration
public class WordRouter {

    @Bean
    public RouterFunction<ServerResponse> wordRoutes(WordService wordService) {
        return RouterFunctions
            .route(
                POST("/word/save").and(contentType(MediaType.APPLICATION_JSON)),
                serverRequest -> saveWord(wordService, serverRequest)
            )
            .andRoute(
                DELETE("/word").and(contentType(MediaType.APPLICATION_JSON)),
                serverRequest -> deleteWord(wordService, serverRequest)
            );
    }

    private Mono<ServerResponse> saveWord(WordService wordService, ServerRequest serverRequest) {
        return serverRequest
            .bodyToMono(Word.class)
            .flatMap(wordService::save)
            .flatMap(savedWord -> ServerResponse.ok().bodyValue(""))
            .onErrorResume(throwable -> {
                throwable.printStackTrace();
                return ServerResponse.badRequest().bodyValue("");
            });
    }

    private Mono<ServerResponse> deleteWord(WordService wordService, ServerRequest serverRequest) {
        return serverRequest
            .bodyToMono(Word.class)
            .flatMap(wordService::delete)
            .flatMap(savedWord -> ServerResponse.ok().bodyValue(""))
            .onErrorResume(throwable -> {
                throwable.printStackTrace();
                return ServerResponse.badRequest().bodyValue("");
            });
    }
}
