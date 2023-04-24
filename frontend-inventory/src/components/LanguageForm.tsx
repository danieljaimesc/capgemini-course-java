import { useState, ChangeEvent } from "react";
import Button from "@mui/material/Button";
import SendIcon from "@mui/icons-material/Send";
import TextField from "@mui/material/TextField";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogTitle from "@mui/material/DialogTitle";
import Grid from "@mui/material/Grid";
import MenuItem from "@mui/material/MenuItem";
import { LanguageDTO } from "../pages/Languages";

function LanguageForm(props: {
  language?: LanguageDTO;
  open: boolean;
  handleClickOpen?: () => void;
  handleClose: () => void;
}) {
  const { open, handleClose, language } = props;

  const [name, setName] = useState(language ? language.name : undefined);

  const handleChangeName = (
    event: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    setName(event.target.value as string);
  };

  const cleanForm = () => {
    setName(language ? language.name : undefined);
    handleClose();
  };

  return (
    <Dialog open={open} onClose={handleClose}>
      <DialogTitle>Form Language</DialogTitle>
      <DialogContent>
        <Grid container spacing={1}>
          <Grid xs={12} item>
            <TextField
              required
              margin="dense"
              label="Name"
              type="text"
              defaultValue={name ? name : undefined}
              onChange={handleChangeName}
              variant="filled"
            />
          </Grid>
        </Grid>
      </DialogContent>
      <DialogActions>
        <Button variant="contained" endIcon={<SendIcon />} onClick={cleanForm}>
          {language ? "Edit" : "Create"}
        </Button>
      </DialogActions>
    </Dialog>
  );
}

export default ActorEdit;
