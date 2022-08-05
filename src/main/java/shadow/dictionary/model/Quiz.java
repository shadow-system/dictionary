package shadow.dictionary.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Quiz {

    private List<Translation> translations;
}
