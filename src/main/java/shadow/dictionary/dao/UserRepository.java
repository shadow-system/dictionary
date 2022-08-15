package shadow.dictionary.dao;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import shadow.dictionary.model.User;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

    Mono<User> findUserByLogin(String login);

    Mono<Void> deleteUserByLogin(String login);
}
