import { useState } from "react";
import { FilmDTO } from "../pages/Films";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import DialogTitle from "@mui/material/DialogTitle";
import InputLabel from "@mui/material/InputLabel";
import Select, { SelectChangeEvent } from "@mui/material/Select";
import MenuItem from "@mui/material/MenuItem";

function FilmEdit(props: {
  filmDTO: FilmDTO;
  open: boolean;
  handleClickOpen?: () => void;
  handleClose: () => void;
}) {
  const { open, handleClose, filmDTO } = props;
  const [rating, setRating] = useState(filmDTO.rating ? filmDTO.rating : "");
  const handleChange = (event: SelectChangeEvent) => {
    setRating(event.target.value as string);
  };
  console.log(filmDTO.rating);
  return (
    <Dialog open={open} onClose={handleClose}>
      <DialogTitle>Edit Film</DialogTitle>
      <DialogContent>
        <TextField
          margin="dense"
          id="title"
          label="Title"
          type="text"
          value={filmDTO.title}
          fullWidth
          variant="standard"
        />
        <TextField
          margin="dense"
          id="description"
          label="Description"
          type="text"
          value={filmDTO.description}
          multiline
          fullWidth
          variant="standard"
        />
        <InputLabel id="demo-simple-select-label">Rating</InputLabel>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          value={rating}
          label="Rating"
          onChange={handleChange}
        >
          <MenuItem value={"G"}>GENERAL AUDIENCES</MenuItem>
          <MenuItem value={"PG"}>PARENTAL GUIDANCE SUGGESTED</MenuItem>
          <MenuItem value={"PG-13"}>PARENTS STRONGLY CAUTIONED</MenuItem>
          <MenuItem value={"R"}>RESTRICTED</MenuItem>
          <MenuItem value={"NC-17"}>ADULTS ONLY</MenuItem>
        </Select>
      </DialogContent>
      <DialogActions>
        <Button onClick={handleClose}>Cancel</Button>
        <Button onClick={handleClose}>Editar</Button>
      </DialogActions>
    </Dialog>
  );
}

export default FilmEdit;
