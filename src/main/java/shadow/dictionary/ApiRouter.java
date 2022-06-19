package shadow.dictionary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import shadow.dictionary.model.Word;
import shadow.dictionary.service.WordService;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;

@Configuration
public class ApiRouter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiRouter.class);

    @Bean
    public RouterFunction<ServerResponse> route(WordService wordService) {
        return RouterFunctions.nest(
            POST("/word"),
            RouterFunctions.route(
                POST("/save").and(contentType(MediaType.APPLICATION_JSON)),
                serverRequest -> serverRequest
                    .bodyToMono(Word.class)
                    .flatMap(word -> {
                        LOGGER.info("Starting to save word {}", word);
                        return wordService.save(word);
                    })
                    .flatMap(savedWord -> ServerResponse.ok().bodyValue("1234"))
                    .onErrorResume(throwable -> {
                        throwable.printStackTrace();
                        return ServerResponse.badRequest().bodyValue("");
                    })
            )
        );
    }
}
