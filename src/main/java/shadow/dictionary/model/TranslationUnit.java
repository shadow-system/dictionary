package shadow.dictionary.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TranslationUnit {

    private String id;
    private String value;
    private Language language;
}
