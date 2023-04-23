import { useCallback, useEffect, useState } from "react";
import FilmList from "../components/FilmList";
export interface FilmDTO {
  id: number;
  title: string;
  description?: string;
  rating?: string;
  releaseYear?: string;
  rentalDuration?: number;
  rentalRate?: number;
  replacementCost?: number;
  actorList?: Array<number>;
  categoryList?: Array<number>;
  language?: string;
  languageVO?: string;
}
interface PageDetails {
  pageNumber: number;
  totalPages?: number;
  pageSize: number;
  totalElements?: number;
}

export function Films() {
  const API_FILMS_ENDPOINT =
    (import.meta.env.VITE_API_BASE_URL || "http://localhost:8001") +
    "/api/v1/films";

  const [filmList, setFilmList] = useState<FilmDTO[]>([]);
  const [pageDetails, setPageDetails] = useState<PageDetails>({
    pageNumber: 0,
    pageSize: 20,
  });

  useEffect(() => {
    console.log("Get Films List" + " " + API_FILMS_ENDPOINT);
    fetch(API_FILMS_ENDPOINT)
      .then((response) => response.json())
      .then((resJson) => {
        setFilmList(resJson.content);
        setPageDetails({
          pageNumber: resJson.pageable.pageNumber,
          pageSize: resJson.pageable.pageSize,
          totalPages: resJson.totalPages,
          totalElements: resJson.totalElements,
        });
      })
      .catch((error) => console.log(error));
  }, []);

  const getFilmDetailsById = useCallback(async (filmId: number) => {
    
    const filmDTO: FilmDTO = await fetch(
      API_FILMS_ENDPOINT + "/" + filmId
    ).then((response) => response.json());

    setFilmList((filmList) => {
      const index = filmList.findIndex((item) => item.id == filmId);
      return [
        ...filmList.slice(0, index),
        filmDTO,
        ...filmList.slice(index + 1),
      ];
    });
  }, []);

  return (
    <>
      <FilmList filmList={filmList} getFilmDetailsById={getFilmDetailsById} />
    </>
  );
}

export default Films;
