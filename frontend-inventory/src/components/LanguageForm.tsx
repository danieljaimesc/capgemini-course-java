import { useState } from "react";
import Button from "@mui/material/Button";
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

function LanguageForm(props: {
  language?: LanguageDTO;
  open: boolean;
  handleClickOpen?: () => void;
  handleClose: () => void;
}) {
  const { open, handleClose, language } = props;
  return (
    <Dialog open={open} onClose={handleClose}>
      <DialogTitle>Form Language</DialogTitle>
      <DialogContent></DialogContent>
      <DialogActions>
        <Button onClick={handleClose}>Cancel</Button>
        <Button onClick={handleClose}>Editar</Button>
      </DialogActions>
    </Dialog>
  );
}

export default ActorEdit;
