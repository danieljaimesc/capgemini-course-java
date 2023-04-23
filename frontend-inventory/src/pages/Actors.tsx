import { useEffect, useState } from "react";

export interface ActorDTO {
  id: number;
  firstName: string;
  lastName: string;
}

export function Actors() {
  const API_ACTORS_ENDPOINT =
    (import.meta.env.VITE_API_BASE_URL || "http://localhost:8001") +
    "/api/v1/actors";

  const [actorList, setActorList] = useState<ActorDTO[]>([]);
  console.log("Get Actors List" + " " + API_ACTORS_ENDPOINT);
  useEffect(() => {
    fetch(API_ACTORS_ENDPOINT)
      .then((response) => response.json())
      .then((actorList) => setActorList(actorList));
  }, []);

  return (
    <>
      <div>Characters</div>
      {actorList.map((item, index) => (
        <div id={index.toString()}>
          {item.id + " " + item.firstName + " " + item.lastName}
        </div>
      ))}
    </>
  );
}

export default Actors;
