import { useState } from "react";
import Menu from "./components/Menu";
import Home from "./pages/Home";
import Films from "./pages/Films";
import Catalog from "./pages/Catalog";
import Actors from "./pages/Actors";
import Languages from "./pages/Languages";
import Categories from "./pages/Categories";
import { Route, Routes } from "react-router-dom";

function App() {
  const [main, setPage] = useState<number>(0);

  const menu = [
    { text: "Home", url: "/" },
    { text: "News", url: "/news" },
    { text: "Actors", url: "/actors" },
    { text: "Films", url: "/films" },
    { text: "Categories", url: "/categories" },
    { text: "Languages", url: "/languages" },
  ];

  return (
    <>
      <Menu menu={menu} />
      <Routes>
        <Route path="/news" element={<Catalog />} />
        <Route path="/" element={<Home />} />
        <Route path="/actors" element={<Actors />} />
        <Route path="/films" element={<Films />} />
        <Route path="/categories" element={<Categories />} />
        <Route path="/languages" element={<Languages />} />
      </Routes>
    </>
  );
}

export default App;
