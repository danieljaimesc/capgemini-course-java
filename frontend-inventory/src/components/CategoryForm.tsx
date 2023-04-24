import { useState } from "react";
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
import { CategoryDTO } from "../pages/Categories";

function CategoryForm(props: {
  category?: CategoryDTO;
  open: boolean;
  handleClickOpen?: () => void;
  handleClose: () => void;
}) {
  const { open, handleClose, category } = props;
  return (
    <Dialog open={open} onClose={handleClose}>
      <DialogTitle>Category Form</DialogTitle>
      <DialogContent></DialogContent>
      <DialogActions>
        <Button onClick={handleClose}>Cancel</Button>
        <Button onClick={handleClose}>Editar</Button>
      </DialogActions>
    </Dialog>
  );
}

export default CategoryForm;
