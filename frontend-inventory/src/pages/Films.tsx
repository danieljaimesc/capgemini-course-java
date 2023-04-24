import { useCallback, useEffect, useState } from "react";
import FilmTable from "../components/FilmTable";
import { ActorDTO } from "./Actors";
import { CategoryDTO } from "./Categories";
import { LanguageDTO } from "./Languages";

export interface FilmDTO {
  id: number;
  title: string;
  description?: string;
  rating?: string;
  releaseYear?: string;
  rentalDuration?: number;
  rentalRate?: number;
  replacementCost?: number;
  actorList?: ActorDTO[];
  categoryList?: CategoryDTO[];
  language?: LanguageDTO;
  languageVO?: LanguageDTO;
}
const API_FILMS_ENDPOINT =
  (import.meta.env.VITE_API_BASE_URL || "http://localhost:8001") +
  "/api/v1/films";

export interface PageDetails {
  pageNumber: {
    value: number;
    set: React.Dispatch<React.SetStateAction<number>>;
  };
  pageSize: {
    value: number;
    set: React.Dispatch<React.SetStateAction<number>>;
  };
  totalElements: {
    value: number;
    set: React.Dispatch<React.SetStateAction<number>>;
  };
}

export function Films() {
  const [filmList, setFilmList] = useState<FilmDTO[]>([]);
  const [pageNumber, setPageNumber] = useState<number>(0);
  const [pageSize, setPageSize] = useState<number>(20);
  const [totalElements, setTotalElements] = useState<number>(1000);

  useEffect(() => {
    console.log("Get Films List" + " " + API_FILMS_ENDPOINT);
    fetch(API_FILMS_ENDPOINT)
      .then((response) => response.json())
      .then((resJson) => {
        setFilmList(resJson.content);
        setPageNumber(resJson.pageable.pageNumber);
        setPageSize(resJson.pageable.pageSize);
        setTotalElements(resJson.totalElements);
      })
      .catch((error) => console.log(error));
  }, []);

  const pageDetails: PageDetails = {
    pageNumber: {
      value: pageNumber,
      set: setPageNumber,
    },
    pageSize: {
      value: pageSize,
      set: setPageSize,
    },
    totalElements: {
      value: totalElements,
      set: setTotalElements,
    },
  };
  const getFilmDetailsById = useCallback(
    async (filmId: number, filmIndex: number) => {
      const filmDetailsDTO: FilmDTO = await fetch(
        API_FILMS_ENDPOINT + "/" + filmId
      ).then((response) => response.json());

      setFilmList((filmList) => {
        return [
          ...filmList.slice(0, filmIndex),
          filmDetailsDTO,
          ...filmList.slice(filmIndex + 1),
        ];
      });
    },
    []
  );

  const getFilmActorListById = useCallback(
    async (filmId: number, filmIndex: number) => {
      const actorList: ActorDTO[] = await fetch(
        API_FILMS_ENDPOINT + "/" + filmId + "/actors"
      ).then((response) => response.json());

      actorList.map((item) => console.log(item.toString()));
    },
    []
  );

  const getFilmCategoryListById = useCallback(
    async (filmId: number, filmIndex: number) => {
      const cateogryList: CategoryDTO[] = await fetch(
        API_FILMS_ENDPOINT + "/" + filmId + "/categories"
      ).then((response) => response.json());

      cateogryList.map((item) => console.log(item.toString()));
    },
    []
  );

  const getFilmLanguageById = useCallback(
    async (filmId: number, filmIndex: number) => {
      console.log("GET FILM LANGUAGE");
    },
    []
  );

  const deleteFilmById = useCallback(
    async (filmId: number, filmIndex: number) => {
      console.log("DELETE FILM");
    },
    []
  );

  const editFilmById = useCallback(
    async (filmDTO: FilmDTO, filmIndex: number) => {
      console.log("EDIT FILM");
    },
    []
  );

  return (
    <>
      <FilmTable
        filmList={filmList}
        getFilmDetailsById={getFilmDetailsById}
        pageDetails={pageDetails}
      />
    </>
  );
}

export default Films;
