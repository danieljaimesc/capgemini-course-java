package com.springsakila.application;

import com.springsakila.inventory.application.CatalogService;
import com.springsakila.inventory.domain.contracts.repositories.CategoryRepository;
import com.springsakila.inventory.domain.contracts.repositories.CharacterRepository;
import com.springsakila.inventory.domain.contracts.repositories.FilmRepository;
import com.springsakila.inventory.domain.contracts.repositories.LanguageRepository;
import com.springsakila.inventory.domain.entities.Category;
import com.springsakila.inventory.domain.entities.Language;
import com.springsakila.inventory.infrastructure.dto.CharacterDTO;
import com.springsakila.inventory.infrastructure.dto.FilmDTO;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CatalogServiceTest {
    @Autowired
    private CatalogService catalogService;

    @MockBean
    private FilmRepository filmRepository;
    @MockBean
    private CharacterRepository characterRepository;
    @MockBean
    private CategoryRepository categoryRepository;
    @MockBean
    private LanguageRepository languageRepository;


    @Nested
    class OK {

        @Test
        void testNews_ReturnPropertiesAndNewsOfEachServiceAreEquals() {
            Timestamp date = null;
            List<FilmDTO> filmDTOList =
                    filmRepository.findByLastUpdateGreaterThanEqualOrderByLastUpdate(date).stream().map(FilmDTO::from).toList();
            List<CharacterDTO> characterDTOList =
                    characterRepository.findByLastUpdateGreaterThanEqualOrderByLastUpdate(date).stream().map(CharacterDTO::from).toList();
            List<Category> categoryList = categoryRepository.findByLastUpdateGreaterThanEqualOrderByLastUpdate(date);
            List<Language> languageList = languageRepository.findByLastUpdateGreaterThanEqualOrderByLastUpdate(date);
            var newsDTO = catalogService.news(date);
            assertAll("Verify NewsDTO properties and list of each Repository",
                    () -> assertEquals(filmDTOList, newsDTO.getFilms()),
                    () -> assertEquals(characterDTOList, newsDTO.getCharacters()),
                    () -> assertEquals(categoryList, newsDTO.getCategories()),
                    () -> assertEquals(languageList, newsDTO.getLanguages()));
        }
    }

    @Nested
    class KO {
        @Test
        void news() {

        }
    }

}