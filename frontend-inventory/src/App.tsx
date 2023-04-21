import { useState } from "react";
import Menu from "./components/Menu";
import Home from "./pages/Home";
import Films from "./pages/Films";
import Catalog from "./pages/Catalog";
import Characters from "./pages/Characters";
import Languages from "./pages/Languages";
import Categories from "./pages/Categories";
import { Route, Routes } from "react-router-dom";

function App() {
  const [main, setPage] = useState<number>(0);

  const menu = [
    { text: "Home", url: "/", component: <Home /> },
    { text: "News", url: "/news", component: <Catalog /> },
    { text: "Character", url: "/characters", component: <Characters /> },
    { text: "Films", url: "/films", component: <Films /> },
    { text: "Categories", url: "/categories", component: <Categories /> },
    { text: "Languages", url: "/languages", component: <Languages /> },
  ];

  return (
    <>
      <Menu menu={menu} />
      <Routes>
        <Route path="/news" element={<Catalog />} />
        <Route path="/" element={<Home />} />
        <Route path="/characters" element={<Characters />} />
        <Route path="/films" element={<Films />} />
        <Route path="/categories" element={<Categories />} />
        <Route path="/languages" element={<Languages />} />
      </Routes>
    </>
  );
}

export default App;
