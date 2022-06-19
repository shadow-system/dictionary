package shadow.dictionary.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import shadow.dictionary.dao.WordRepository;
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
            .defer(() -> {
                LOGGER.info("Saving word " + word);
                return repository.save(word);
            })
            .map(savedWord -> {
                LOGGER.info("Successfully saved the word " + word);
                return true;
            })
            .onErrorResume(throwable -> {
                LOGGER.info("Failed to save the word");
                return Mono.just(false);
            });
    }
}
