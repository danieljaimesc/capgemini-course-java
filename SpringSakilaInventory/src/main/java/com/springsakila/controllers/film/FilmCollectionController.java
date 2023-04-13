package com.springsakila.controllers.film;

import com.springsakila.inventory.domain.contracts.services.FilmService;
import com.springsakila.inventory.infrastructure.dto.FilmDetailsDTO;
import com.springsakila.inventory.infrastructure.dto.FilmShortDTO;
import com.springsakila.inventory.shared.PaginationConverter;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/films")
public class FilmCollectionController {
    @Autowired
    private FilmService filmService;

    @GetMapping
    public Page<FilmShortDTO> getAll(Pageable pageable, @RequestParam(defaultValue = "short") String mode) {
        return filmService.getByProjection(pageable, FilmShortDTO.class);
    }


    @GetMapping(params = "mode=details")
    @Transactional
    public Page<FilmDetailsDTO> getAllDetails(@Parameter(hidden = true) Pageable pageable,
                                              @RequestParam(defaultValue = "short") String mode) {
        List<FilmDetailsDTO> filmDetailsDTOList = filmService.getAll().stream().map(FilmDetailsDTO::from).toList();
        return PaginationConverter.paginateList(pageable, filmDetailsDTOList);
    }
}
