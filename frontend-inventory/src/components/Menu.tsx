import { useState } from "react";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import "bootstrap/dist/css/bootstrap.min.css";
import { Link } from "react-router-dom";
interface Props {
  menu: Array<{ text: string; url: string; component: JSX.Element }>;
}
function Menu({ menu }: Props) {
  return (
    <>
      <Navbar bg="dark" variant="dark">
        <Container>
          <Nav className="me-auto">
            {menu.length !== 0 ? (
              menu.map((item, index) => (
                <div key={index}>
                  <Nav.Link>
                    <Link to={item.url}>{item.text}</Link>
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
    </>
  );
}

export default Menu;
