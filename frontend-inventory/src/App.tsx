import { useState } from "react";
import Menu from "./components/Menu";
import Home from "./pages/Home";
import Films from "./pages/Films";
import Catalog from "./pages/Catalog";
import Characters from "./pages/Characters";
import Languages from "./pages/Languages";
import Categories from "./pages/Categories";

function App() {
  const [main, setPage] = useState<number>(0);

  const menu = [
    { text: "Home", url: "/home", component: <Home /> },
    { text: "News", url: "/news", component: <Catalog /> },
    { text: "Character", url: "/characters", component: <Characters /> },
    { text: "Films", url: "/films", component: <Films /> },
    { text: "Languages", url: "/languages", component: <Categories /> },
    { text: "Languages", url: "/languages", component: <Languages /> },
  ];

  return (
    <>
      <Menu
        menu={menu}
        actualPage={main}
        onSelectMenu={(main) => setPage(main)}
      />
      {menu[main].component}
    </>
  );
}

export default App;
