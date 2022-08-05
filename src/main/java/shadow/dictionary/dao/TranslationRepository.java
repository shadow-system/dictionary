package shadow.dictionary.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import shadow.dictionary.model.Translation;

@Repository
public interface TranslationRepository extends ReactiveMongoRepository<Translation, String> {
}
