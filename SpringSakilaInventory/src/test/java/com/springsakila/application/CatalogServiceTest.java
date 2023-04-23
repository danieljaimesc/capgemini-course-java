package com.springsakila.application;

import com.springsakila.inventory.application.CatalogService;
import com.springsakila.inventory.domain.contracts.repositories.ActorRepository;
import com.springsakila.inventory.domain.contracts.repositories.CategoryRepository;
import com.springsakila.inventory.domain.contracts.repositories.FilmRepository;
import com.springsakila.inventory.domain.contracts.repositories.LanguageRepository;
import com.springsakila.inventory.domain.entities.Category;
import com.springsakila.inventory.domain.entities.Language;
import com.springsakila.inventory.infrastructure.dto.ActorDTO;
import com.springsakila.inventory.infrastructure.dto.FilmShortDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
class CatalogServiceTest {
    @MockBean
    private CatalogService catalogService;

    @MockBean
    private FilmRepository filmRepository;
    @MockBean
    private ActorRepository actorRepository;
    @MockBean
    private CategoryRepository categoryRepository;
    @MockBean
    private LanguageRepository languageRepository;


    @Nested
    class OK {

        @Test
        void testNews_ReturnPropertiesAndNewsOfEachServiceAreEquals() {
            Timestamp date = null;
            List<FilmShortDTO> filmShortDTOList =
                    filmRepository.findByLastUpdateGreaterThanEqualOrderByLastUpdate(date).stream().map(FilmShortDTO::from).toList();
            List<ActorDTO> actorDTOList =
                    actorRepository.findByLastUpdateGreaterThanEqualOrderByLastUpdate(date).stream().map(ActorDTO::from).toList();
            List<Category> categoryList = categoryRepository.findByLastUpdateGreaterThanEqualOrderByLastUpdate(date);
            List<Language> languageList = languageRepository.findByLastUpdateGreaterThanEqualOrderByLastUpdate(date);
            var newsDTO = catalogService.news(date);
            assertAll("Verify NewsDTO properties and list of each Repository",
                    () -> Assertions.assertIterableEquals(filmShortDTOList, newsDTO.getFilmList()),
                    () -> Assertions.assertIterableEquals(actorDTOList, newsDTO.getActorList()),
                    () -> Assertions.assertIterableEquals(categoryList, newsDTO.getCategoryList()),
                    () -> Assertions.assertIterableEquals(languageList, newsDTO.getLanguageList()));
        }
    }

    @Nested
    class KO {
        @Test
        void news() {

        }
    }

}