import { useEffect, useState } from "react";
import LanguageTable from "../components/LanguageTable";

export interface LanguageDTO {
  id: number;
  name: string;
}

const API_LANGUAGES_ENDPOINT =
  (import.meta.env.VITE_API_BASE_URL || "http://localhost:8001") +
  "/api/v1/languages";

export const getLanguageList = (): Promise<LanguageDTO[]> => {
  return fetch(API_LANGUAGES_ENDPOINT).then((response) => response.json());
};

export function Languages() {
  const [languageList, setLanguageList] = useState<LanguageDTO[]>([]);
  useEffect(() => {
    console.log("Get Languages List" + " " + API_LANGUAGES_ENDPOINT);
    fetch(API_LANGUAGES_ENDPOINT)
      .then((response) => response.json())
      .then((languageList) => setLanguageList(languageList));
  }, []);
  return (
    <>
      <LanguageTable languageList={languageList} />
    </>
  );
}

export default Languages;
