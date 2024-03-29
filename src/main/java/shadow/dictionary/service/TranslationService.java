package shadow.dictionary.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import shadow.dictionary.dao.TranslationRepository;

@Slf4j
@Service
@AllArgsConstructor
public class TranslationService {

    private final TranslationRepository translationRepository;
}
