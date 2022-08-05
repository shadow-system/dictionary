package shadow.dictionary.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shadow.dictionary.service.TranslationService;
import shadow.dictionary.service.WordService;

//TODO: remove controllers in case of all functionality replacement by router class
@RestController
@RequestMapping("/api/v1/controller/translation")
@AllArgsConstructor
@Slf4j
public class TranslationController {

    private final WordService wordService;
    private final TranslationService translationService;

    @GetMapping("/{id}")
    public void getTranslationById(@RequestParam String id) {

    }
}
