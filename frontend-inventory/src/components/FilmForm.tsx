import { useState, ChangeEvent, useEffect } from "react";
import { FilmDTO } from "../pages/Films";
import Button from "@mui/material/Button";
import SendIcon from "@mui/icons-material/Send";
import TextField from "@mui/material/TextField";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import DialogTitle from "@mui/material/DialogTitle";
import InputLabel from "@mui/material/InputLabel";
import FormControl from "@mui/material/FormControl";
import Select, { SelectChangeEvent } from "@mui/material/Select";
import MenuItem from "@mui/material/MenuItem";
import Grid from "@mui/material/Grid";
import { LanguageDTO, getLanguageList } from "../pages/Languages";

function FilmForm(props: {
  filmDTO?: FilmDTO;
  open: boolean;
  handleClose: () => void;
}) {
  const { open, handleClose, filmDTO } = props;
  const [languageList, setLanguageList] = useState<LanguageDTO[]>();

  useEffect(() => {
    getLanguageList().then((respJson) => setLanguageList(respJson));
  }, []);

  const [film, setFilm] = useState<FilmDTO>();

  const [title, setTitle] = useState(
    filmDTO && filmDTO.title ? filmDTO.title : undefined
  );

  const [description, setDescription] = useState(
    filmDTO && filmDTO.description ? filmDTO.description : undefined
  );

  const [rating, setRating] = useState(
    filmDTO && filmDTO.rating ? filmDTO.rating : undefined
  );

  const [releaseYear, setReleaseYear] = useState(
    filmDTO && filmDTO.releaseYear ? filmDTO.releaseYear : undefined
  );

  const [rentalDuration, setRentalDuration] = useState(
    filmDTO && filmDTO.rentalDuration ? filmDTO.rentalDuration : undefined
  );

  const [rentalRate, setRentalRate] = useState(
    filmDTO && filmDTO.rentalRate ? filmDTO.rentalRate : undefined
  );

  const [replacementCost, setReplacementCost] = useState(
    filmDTO && filmDTO.replacementCost ? filmDTO.replacementCost : undefined
  );

  const [language, setLanguage] = useState(
    filmDTO && filmDTO.language ? filmDTO.language : undefined
  );

  const [languageVO, setLanguageVO] = useState(
    filmDTO && filmDTO.languageVO ? filmDTO.languageVO : undefined
  );

  const handleChangeTitle = (
    event: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    setTitle(event.target.value as string);
  };

  const handleChangeDescription = (
    event: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    setDescription(event.target.value as string);
  };

  const handleChangeRating = (event: SelectChangeEvent) => {
    setRating(event.target.value as string);
  };

  const handleChangeReleaseYear = (
    event: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    setReleaseYear(event.target.value as string);
  };

  const handleChangeRentalDuration = (
    event: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    setRentalDuration(event.target.value as unknown as number);
  };

  const handleChangeRentalRate = (
    event: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    setRentalRate(event.target.value as unknown as number);
  };

  const handleChangeReplacementCost = (
    event: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    setReplacementCost(event.target.value as unknown as number);
  };

  const handleChangeLanguage = (event: SelectChangeEvent<number>) => {
    setLanguage(
      languageList?.find(({ id }) => id === (event.target.value as number))
    );
  };

  const handleChangeLanguageVO = (event: SelectChangeEvent<number>) => {
    setLanguageVO(
      languageList?.find(({ id }) => id === (event.target.value as number))
    );
  };

  const cleanForm = () => {
    setTitle(filmDTO && filmDTO.title ? filmDTO.title : undefined);
    setDescription(
      filmDTO && filmDTO.description ? filmDTO.description : undefined
    );
    setRating(filmDTO && filmDTO.rating ? filmDTO.rating : undefined);
    setReleaseYear(
      filmDTO && filmDTO.releaseYear ? filmDTO.releaseYear : undefined
    );
    setRentalDuration(
      filmDTO && filmDTO.rentalDuration ? filmDTO.rentalDuration : undefined
    );
    setRentalRate(
      filmDTO && filmDTO.rentalRate ? filmDTO.rentalRate : undefined
    );
    setReplacementCost(
      filmDTO && filmDTO.replacementCost ? filmDTO.replacementCost : undefined
    );
    setLanguage(filmDTO && filmDTO.language ? filmDTO.language : undefined);
    setLanguageVO(
      filmDTO && filmDTO.languageVO ? filmDTO.languageVO : undefined
    );
    handleClose();
  };

  return (
    <Dialog open={open} onClose={cleanForm}>
      <DialogTitle>Film Form</DialogTitle>
      <DialogContent>
        <Grid container spacing={1}>
          <Grid xs={12} item>
            <TextField
              label="Title"
              type="text"
              defaultValue={title}
              fullWidth
              variant="filled"
              required
              onChange={handleChangeTitle}
            />
          </Grid>
          <Grid xs={12} item>
            <TextField
              margin="dense"
              label="Description"
              type="text"
              defaultValue={description}
              multiline
              fullWidth
              rows={2}
              onChange={handleChangeDescription}
              variant="filled"
            />
          </Grid>
          <Grid xs={4} item>
            <TextField
              label="Release Year"
              type="number"
              defaultValue={releaseYear}
              onChange={handleChangeReleaseYear}
              variant="filled"
            />
          </Grid>
          <Grid xs={4} item>
            <TextField
              label="Rental Duration"
              type="number"
              defaultValue={rentalDuration}
              onChange={handleChangeRentalDuration}
              variant="filled"
            />
          </Grid>
          <Grid xs={4} item>
            <TextField
              label="Rental Rate"
              defaultValue={rentalRate}
              onChange={handleChangeRentalRate}
              type="number"
              variant="filled"
            />
          </Grid>
          <Grid xs={12} item>
            <FormControl variant="filled" fullWidth>
              <InputLabel id="rating-select-label">Rating</InputLabel>
              <Select
                labelId="rating-select-label"
                id="demo-simple-select"
                defaultValue={rating}
                label="Rating"
                onChange={handleChangeRating}
              >
                <MenuItem value={"G"}>GENERAL AUDIENCES</MenuItem>
                <MenuItem value={"PG"}>PARENTAL GUIDANCE SUGGESTED</MenuItem>
                <MenuItem value={"PG-13"}>PARENTS STRONGLY CAUTIONED</MenuItem>
                <MenuItem value={"R"}>RESTRICTED</MenuItem>
                <MenuItem value={"NC-17"}>ADULTS ONLY</MenuItem>
              </Select>
            </FormControl>
          </Grid>
          <Grid xs={4} item>
            <TextField
              label="Replacement Cost"
              defaultValue={replacementCost}
              type="number"
              variant="filled"
              onChange={handleChangeReplacementCost}
            />
          </Grid>
          <Grid xs={4} item>
            <FormControl variant="filled" fullWidth>
              <InputLabel id="language-select-label">Language</InputLabel>
              <Select
                labelId="language-select-label"
                id="language-select"
                defaultValue={
                  language ? (language as LanguageDTO).id : undefined
                }
                label="Language"
                onChange={handleChangeLanguage}
              >
                {languageList?.map((item) => (
                  <MenuItem value={item.id}>{item.name}</MenuItem>
                ))}
              </Select>
            </FormControl>
          </Grid>
          <Grid xs={4} item>
            <FormControl variant="filled" fullWidth>
              <InputLabel id="languageVO-select-label">
                Language (VOSE)
              </InputLabel>
              <Select
                labelId="languageVO-select-label"
                id="languageVO-select"
                defaultValue={
                  languageVO ? (languageVO as LanguageDTO).id : undefined
                }
                label="Language (VOSE)"
                onChange={handleChangeLanguageVO}
              >
                {languageList?.map((item) => (
                  <MenuItem value={item.id}>{item.name}</MenuItem>
                ))}
              </Select>
            </FormControl>
          </Grid>
        </Grid>
      </DialogContent>
      <DialogActions>
        <Button variant="contained" endIcon={<SendIcon />} onClick={cleanForm}>
          {filmDTO ? "Edit" : "Create"}
        </Button>
        <Button onClick={cleanForm}>Close</Button>
      </DialogActions>
    </Dialog>
  );
}

export default FilmForm;
