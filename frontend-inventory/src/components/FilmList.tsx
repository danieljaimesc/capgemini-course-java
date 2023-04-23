import { useState } from "react";
import { FilmDTO } from "../pages/Films";
import List from "@mui/material/List";
import ListItem from "@mui/material/ListItem";
import IconButton from "@mui/material/IconButton";
import ListItemText from "@mui/material/ListItemText";
import EditIcon from "@mui/icons-material/Edit";
import DeleteIcon from "@mui/icons-material/Delete";
import Accordion from "@mui/material/Accordion";
import AccordionSummary from "@mui/material/AccordionSummary";
import AccordionDetails from "@mui/material/AccordionDetails";
import Typography from "@mui/material/Typography";

interface Props {
  filmList: FilmDTO[];
  getFilmDetailsById: (filmId: number) => Promise<void>;
}
function FilmList({ filmList, getFilmDetailsById }: Props) {
  const [checked, setChecked] = useState<number[]>([]);

  const handleToggle = (value: number) => () => {
    const currentIndex = checked.indexOf(value);
    const newChecked = [...checked];

    if (currentIndex === -1) {
      newChecked.push(value);
    } else {
      newChecked.splice(currentIndex, 1);
    }

    setChecked(newChecked);
  };

  return (
    <>
      <h1>Films</h1>
      <List
        sx={{
          width: "100%",
          maxWidth: 500,
          marginLeft: "auto",
          marginRight: "auto",
          bgcolor: "background.paper",
        }}
      >
        {filmList.map((item, index) => {
          const labelId = `checkbox-list-label-${index}`;

          return (
            <Accordion>
              <AccordionSummary
                aria-controls={"panel" + index + "a-content"}
                id={"panel" + index + "a-header"}
              >
                <ListItem key={index} disablePadding>
                  <ListItemText id={labelId} primary={item.id} />
                  <ListItemText id={labelId} primary={item.title} />
                  <IconButton
                    onClick={() => {
                      if (!item.description) getFilmDetailsById(item.id);
                    }}
                    aria-label="comments"
                  >
                    <EditIcon />
                  </IconButton>
                  <IconButton aria-label="comments">
                    <DeleteIcon />
                  </IconButton>
                </ListItem>
              </AccordionSummary>
              <AccordionDetails>
                <Typography>{item.description}</Typography>
              </AccordionDetails>
            </Accordion>
          );
        })}
      </List>
    </>
  );
}

export default FilmList;
