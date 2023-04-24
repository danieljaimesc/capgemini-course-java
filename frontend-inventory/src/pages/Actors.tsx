import { useEffect, useState } from "react";
import { PageDetails } from "../components/Pagination";
import ActorTable from "../components/ActorTable";

export interface ActorDTO {
  id: number;
  firstName: string;
  lastName: string;
}

const API_ACTORS_ENDPOINT =
  (import.meta.env.VITE_API_BASE_URL || "http://localhost:8001") +
  "/api/v1/actors";

export function Actors() {
  const [actorList, setActorList] = useState<ActorDTO[]>([]);
  const [pageNumber, setPageNumber] = useState<number>(0);
  const [pageSize, setPageSize] = useState<number>(20);
  const [totalElements, setTotalElements] = useState<number>(1000);

  console.log("Get Actors List" + " " + API_ACTORS_ENDPOINT);
  useEffect(() => {
    fetch(API_ACTORS_ENDPOINT)
      .then((response) => response.json())
      .then((resJson) => {
        setActorList(resJson.content);
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

  return (
    <>
      <ActorTable actorList={actorList} pageDetails={pageDetails} />
    </>
  );
}

export default Actors;
