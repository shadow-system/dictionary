package shadow.dictionary.api.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;
import shadow.dictionary.api.model.DeleteUserRequest;
import shadow.dictionary.api.model.NewUserRequest;
import shadow.dictionary.api.model.UpdateUserRequest;
import shadow.dictionary.service.UserService;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

/**
 * Controller for User API
 */
@Configuration
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> userRoutes(UserService crud) {
        return RouterFunctions
            .nest(RequestPredicates.path("/users"),
                RouterFunctions
                    .route(POST("/new"), serverRequest -> processNewUserServerRequest(serverRequest, crud))
                    .andRoute(DELETE(""), serverRequest -> processDeleteUserServerRequest(serverRequest, crud))
                    .andRoute(PATCH(""), serverRequest -> processUpdateUserServerRequest(serverRequest, crud))
                    .andRoute(GET(""), serverRequest -> processGetUserServerRequest(serverRequest, crud))
            );
    }

    private Mono<ServerResponse> processNewUserServerRequest(ServerRequest request, UserService crud) {
        return request
            .bodyToMono(NewUserRequest.class)
            .flatMap(crud::create)
            .flatMap(user -> ServerResponse.ok().bodyValue(user));
    }

    private Mono<ServerResponse> processDeleteUserServerRequest(ServerRequest request, UserService crud) {
        return request
            .bodyToMono(DeleteUserRequest.class)
            .flatMap(deleteUserRequest -> crud.delete(deleteUserRequest.getUsername()))
            .flatMap(noResult -> ServerResponse.ok().bodyValue(""));
    }

    private Mono<ServerResponse> processUpdateUserServerRequest(ServerRequest request, UserService crud) {
        return request
            .bodyToMono(UpdateUserRequest.class)
            .flatMap(crud::update)
            .flatMap(user -> ServerResponse.ok().bodyValue(user));
    }

    private Mono<ServerResponse> processGetUserServerRequest(ServerRequest request, UserService crud) {
        return request
            .bodyToMono(UpdateUserRequest.class)
            .flatMap(updateUserRequest -> crud.read(updateUserRequest.getUsername()))
            .flatMap(user -> ServerResponse.ok().bodyValue(user));
    }
}
