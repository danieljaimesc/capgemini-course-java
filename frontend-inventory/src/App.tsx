import { useState } from "react";
import Menu from "./components/Menu";
import Home from "./pages/Home";

function App() {
  const [main, setPage] = useState<number>(0);

  const menu = [
    { text: "Home", url: "/home", component: <Home /> },
    //{ text: "Home", url: "/home", component: <Home /> },
    //{ text: "News", url: "/news", component: <Home /> },
    //{ text: "Character", url: "/characters", component: <Home /> },
    //{ text: "Films", url: "/films", component: <Home /> },
    //{ text: "Languages", url: "/languages", component: <Home /> },
  ];

  return (
    <>
      <Menu menu={menu} />
      {menu[main].component}
    </>
  );
}

export default App;
