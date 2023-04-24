import { useState, ChangeEvent } from "react";
import Button from "@mui/material/Button";
import SendIcon from "@mui/icons-material/Send";
import TextField from "@mui/material/TextField";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogTitle from "@mui/material/DialogTitle";
import Grid from "@mui/material/Grid";
import { ActorDTO } from "../pages/Actors";

function ActorForm(props: {
  actor?: ActorDTO;
  actorIndex?: number;
  open: boolean;
  handleClose: () => void;
}) {
  const { open, handleClose, actor } = props;

  const [actorToSend, setActorToSend] = useState<ActorDTO>();

  const [firstName, setFirstName] = useState(
    actor ? actor.firstName : undefined
  );

  const [lastName, setLastName] = useState(actor ? actor.lastName : undefined);

  const handleChangeFirstName = (
    event: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    setFirstName(event.target.value as string);
  };

  const handleChangeLastName = (
    event: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    setLastName(event.target.value as string);
  };

  const cleanForm = () => {
    setFirstName(actor ? actor.firstName : undefined);
    setLastName(actor ? actor.lastName : undefined);
    handleClose();
  };
  return (
    <Dialog open={open} onClose={handleClose}>
      <DialogTitle>Actor Form</DialogTitle>
      <DialogContent>
        <Grid container spacing={1}>
          <Grid xs={6} item>
            <TextField
              required
              margin="dense"
              label="First Name"
              type="text"
              defaultValue={firstName ? firstName : undefined}
              onChange={handleChangeFirstName}
              variant="filled"
            />
          </Grid>
          <Grid xs={6} item>
            <TextField
              required
              margin="dense"
              label="Last Name"
              type="text"
              defaultValue={lastName ? lastName : undefined}
              onChange={handleChangeLastName}
              variant="filled"
            />
          </Grid>
        </Grid>
      </DialogContent>
      <DialogActions>
        <Button onClick={cleanForm}>Close</Button>
        <Button variant="contained" endIcon={<SendIcon />} onClick={cleanForm}>
          {actor ? "Edit" : "Create"}
        </Button>
      </DialogActions>
    </Dialog>
  );
}

export default ActorForm;
