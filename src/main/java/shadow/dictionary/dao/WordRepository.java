package shadow.dictionary.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import shadow.dictionary.model.Word;

@Repository
public interface WordRepository extends ReactiveMongoRepository<Word, String> {
}
