package shadow.dictionary.api.model;

import lombok.Getter;
import lombok.Setter;
import shadow.dictionary.model.Word;

@Getter
@Setter
public class TranslationRequest {

    private Word word;
    private Word translation;
}
