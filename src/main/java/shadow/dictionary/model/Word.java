package shadow.dictionary.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Word {

    private final String value;
    private final Language language;

    @JsonCreator
    public Word(@JsonProperty("value") String value, @JsonProperty("language") Language language) {
        this.value = value;
        this.language = language;
    }
}
