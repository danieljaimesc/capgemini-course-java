import { useEffect, useState } from "react";
import { ActorDTO } from "./Actors";
import { FilmDTO } from "./Films";
import { LanguageDTO } from "./Languages";
import { CategoryDTO } from "./Categories";

interface CatalogDTO {
  filmList: FilmDTO[];
  actorList: ActorDTO[];
  languageList: LanguageDTO[];
  categoryList: CategoryDTO[];
}

function Catalog() {
  const API_CATALOG_ENDPOINT =
    (import.meta.env.VITE_API_BASE_URL || "http://localhost:8001") +
    "/api/v1/catalog";

  const [catalog, setCatalog] = useState<CatalogDTO>({
    filmList: [],
    actorList: [],
    languageList: [],
    categoryList: [],
  });

  useEffect(() => {
    console.log("Get Catalog" + " " + API_CATALOG_ENDPOINT);
    fetch(API_CATALOG_ENDPOINT)
      .then((response) => response.json())
      .then((catalog) => setCatalog(catalog));
  }, []);
  return (
    <>
      <h1>Catalog</h1>
      <br />
      <h5>Films</h5>
      {catalog.filmList.map((item, index) => (
        <div id={index.toString()}>{item.id + " " + item.title}</div>
      ))}
      <br />
      <h5>Actors</h5>
      {catalog.actorList.map((item, index) => (
        <div id={index.toString()}>
          {item.id + " " + item.firstName + " " + item.lastName}
        </div>
      ))}
      <br />
      <h5>Categories</h5>
      {catalog.categoryList.map((item, index) => (
        <div id={index.toString()}>{item.id + " " + item.name}</div>
      ))}
      <br />
      <h5>Languages</h5>
      {catalog.languageList.map((item, index) => (
        <div id={index.toString()}>{item.id + " " + item.name}</div>
      ))}
    </>
  );
}

export default Catalog;
