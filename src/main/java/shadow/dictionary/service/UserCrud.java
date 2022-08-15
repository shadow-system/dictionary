package shadow.dictionary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import shadow.dictionary.api.model.NewUserRequest;
import shadow.dictionary.api.model.UpdateUserRequest;
import shadow.dictionary.dao.UserRepository;
import shadow.dictionary.exception.UserNotFoundException;
import shadow.dictionary.model.User;

@Service
public class UserCrud {

    private final UserRepository repository;

    @Autowired
    public UserCrud(UserRepository repository) {
        this.repository = repository;
    }

    public Mono<User> create(NewUserRequest request) {
        return Mono.defer(() -> {
            User user = new User(request.getLogin(), request.getPassword());
            return repository.save(user);
        });
    }

    public Mono<Void> delete(String login) {
        return repository.deleteUserByLogin(login);
    }

    public Mono<User> read(String login) {
        return repository.findUserByLogin(login);
    }

    public Mono<User> update(UpdateUserRequest request) {
        return Mono
            .defer(() -> repository.findUserByLogin(request.getOldLogin()))
            .flatMap(user -> {
                var updatedUser = new User(user.getId(), request.getLogin(), request.getPassword());
                return repository.save(updatedUser);
            })
            .switchIfEmpty(Mono.error(new UserNotFoundException()));
    }
}
