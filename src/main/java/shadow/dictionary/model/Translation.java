package shadow.dictionary.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Translation {

    private TranslationUnit from;
    private TranslationUnit to;
}
