import { useState } from "react";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import "bootstrap/dist/css/bootstrap.min.css";

interface Props {
  menu: Array<{ text: string; url: string }>;
  actualPage: number;
  onSelectMenu: (index: number) => void;
}
function Menu({ menu, actualPage, onSelectMenu }: Props) {
  return (
    <Navbar bg="dark" variant="dark">
      <Container>
        <Nav className="me-auto">
          {menu.length !== 0 ? (
            menu.map((item, index) => (
              <div key={index}>
                <Nav.Link
                  onClick={(ev) => {
                    ev.preventDefault();
                    onSelectMenu(index);
                  }}
                  href={item.url}
                >
                  {item.text}
                </Nav.Link>
              </div>
            ))
          ) : (
            <div>
              <Nav.Link href="/home">Home</Nav.Link>
            </div>
          )}
        </Nav>
      </Container>
    </Navbar>
  );
}

export default Menu;
