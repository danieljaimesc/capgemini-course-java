import { useEffect, useState } from "react";
import CategoryTable from "../components/CategoryTable";

export interface CategoryDTO {
  id: number;
  name: string;
}
export function Categories() {
  const API_CATEGORIES_ENDPOINT =
    (import.meta.env.VITE_API_BASE_URL || "http://localhost:8001") +
    "/api/v1/categories";

  const [categoryList, setCategoryList] = useState<CategoryDTO[]>([]);

  useEffect(() => {
    console.log("Get Categories List" + " " + API_CATEGORIES_ENDPOINT);
    fetch(API_CATEGORIES_ENDPOINT)
      .then((response) => response.json())
      .then((categoryList) => setCategoryList(categoryList));
  }, []);

  return (
    <>
      <CategoryTable categoryList={categoryList} />
    </>
  );
}

export default Categories;
