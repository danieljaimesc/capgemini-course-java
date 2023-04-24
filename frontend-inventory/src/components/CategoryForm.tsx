import { useState, ChangeEvent } from "react";
import Button from "@mui/material/Button";
import SendIcon from "@mui/icons-material/Send";
import TextField from "@mui/material/TextField";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogTitle from "@mui/material/DialogTitle";
import Grid from "@mui/material/Grid";
import { CategoryDTO } from "../pages/Categories";

function CategoryForm(props: {
  category?: CategoryDTO;
  categoryIndex?: number;
  open: boolean;
  handleClose: () => void;
}) {
  const { open, handleClose, category } = props;
  const [categoryToSend, setCategoryToSend] = useState<CategoryDTO>();
  const [name, setName] = useState(category ? category.name : undefined);

  const handleChangeName = (
    event: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    setName(event.target.value as string);
  };

  const cleanForm = () => {
    setName(category ? category.name : undefined);
    handleClose();
  };

  return (
    <Dialog open={open} onClose={cleanForm}>
      <DialogTitle>Category Form</DialogTitle>
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
      <Button onClick={cleanForm}>Close</Button>
        <Button variant="contained" endIcon={<SendIcon />} onClick={cleanForm}>
          {category ? "Edit" : "Create"}
        </Button>
      </DialogActions>
    </Dialog>
  );
}

export default CategoryForm;
