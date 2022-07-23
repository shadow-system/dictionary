package shadow.dictionary.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import shadow.dictionary.dao.WordRepository;
import shadow.dictionary.exception.WordIsNotUniqueException;
import shadow.dictionary.model.Word;

@Service
public class WordService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WordService.class);
    private final WordRepository repository;

    @Autowired
    public WordService(WordRepository repository) {
        this.repository = repository;
    }

    public Mono<Boolean> save(Word word) {
        return Mono
            .defer(() -> repository.save(word))
            .map(savedWord -> true)
            .doOnSuccess(savedWord -> LOGGER.info("Successfully saved the word " + word))
            .doOnError(throwable -> LOGGER.info("Failed to save the word"))
            .onErrorReturn(false);
    }

    public Mono<Boolean> delete(Word word) {
        return Mono
            .defer(() -> repository.deleteWordByValueAndLanguage(word.getValue(), word.getLanguage()))
            .map(unused -> true)
            .doOnSuccess(savedWord -> LOGGER.info("Successfully deleted the word " + word))
            .doOnError(throwable -> LOGGER.info("Failed to delete the word"))
            .onErrorReturn(false);
    }

    public Mono<Word> find(Word word) throws WordIsNotUniqueException {
        return Flux
            .defer(() -> repository.findAll(Example.of(word, ExampleMatcher.matchingAll())))
            .collectList()
            .map(words -> {
                if (words.size() > 1) {
                    throw new WordIsNotUniqueException();
                }
                return words.get(0);
            })
            .doOnError(throwable -> LOGGER.info("Failed to retrieve word"));
    }
}
