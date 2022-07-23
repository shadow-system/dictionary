package shadow.dictionary.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.Indexed;

@Getter
@Setter
@ToString
public class Word {

    private String id;
    @Indexed(name = "word_value_index", unique = true)
    private String value;
    private Language language;
}
