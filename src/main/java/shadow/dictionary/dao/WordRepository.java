package shadow.dictionary.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import shadow.dictionary.model.Language;
import shadow.dictionary.model.Word;

@Repository
public interface WordRepository extends ReactiveMongoRepository<Word, String> {

    Mono<Word> deleteWordByValueAndLanguage(String value, Language language);
}
