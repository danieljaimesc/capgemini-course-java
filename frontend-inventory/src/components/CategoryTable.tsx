import { Fragment, useState } from "react";
import { CategoryDTO } from "../pages/Categories";
import IconButton from "@mui/material/IconButton";
import Button from "@mui/material/Button";
import EditIcon from "@mui/icons-material/Edit";
import DeleteIcon from "@mui/icons-material/Delete";
import Paper from "@mui/material/Paper";
import Stack from "@mui/material/Stack";
import TableContainer from "@mui/material/TableContainer";
import Table from "@mui/material/Table";
import TableRow from "@mui/material/TableRow";
import TableHead from "@mui/material/TableHead";
import TableCell from "@mui/material/TableCell";
import TableBody from "@mui/material/TableBody";
import CategoryForm from "./CategoryForm";

interface Props {
  categoryList: CategoryDTO[];
  editCategoryById?: (
    actor: CategoryDTO,
    categoryIndex: number
  ) => Promise<void>;
  deleteCategoryById?: (
    categoryId: number,
    categoryIndex: number
  ) => Promise<void>;
}

function CategoryTable({ categoryList }: Props) {
  const [openForm, setOpenForm] = useState<boolean>(false);

  const handleClickOpen = () => {
    setOpenForm(true);
  };

  const handleClose = () => {
    setOpenForm(false);
  };
  return (
    <>
      <Stack alignItems="center">
        <h1>Categories</h1>
      </Stack>
      <TableContainer
        sx={{
          width: "40%",
          maxWidth: 500,
          marginLeft: "auto",
          marginRight: "auto",
          bgcolor: "background.paper",
        }}
        component={Paper}
      >
        <Table>
          <TableHead>
            <TableRow>
              <TableCell align="center" colSpan={5}>
                <Button
                  variant="contained"
                  color="success"
                  onClick={handleClickOpen}
                >
                  Create
                </Button>
              </TableCell>
            </TableRow>
            <TableRow>
              <TableCell>ID</TableCell>
              <TableCell>Name</TableCell>
              <TableCell>Edit</TableCell>
              <TableCell>Delete</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {categoryList.map((item, index) => (
              <CategoryRow key={index} row={item} index={index} />
            ))}
          </TableBody>
        </Table>
      </TableContainer>
      <CategoryForm open={openForm} handleClose={handleClose} />
    </>
  );
}

function CategoryRow(props: { row: CategoryDTO; index: number }) {
  const { row, index } = props;
  const [openForm, setOpenForm] = useState<boolean>(false);

  const handleClickOpen = () => {
    setOpenForm(true);
  };

  const handleClose = () => {
    setOpenForm(false);
  };

  return (
    <>
      <Fragment>
        <TableRow sx={{ "& > *": { borderBottom: "unset" } }}>
          <TableCell component="th" scope="row">
            {row.id}
          </TableCell>
          <TableCell component="th" scope="row">
            {row.name}
          </TableCell>
          <TableCell>
            <IconButton onClick={handleClickOpen}>
              <EditIcon />
            </IconButton>
          </TableCell>
          <TableCell>
            <IconButton>
              <DeleteIcon />
            </IconButton>
          </TableCell>
        </TableRow>
      </Fragment>
      <CategoryForm
        open={openForm}
        category={row}
        categoryIndex={index}
        handleClose={handleClose}
      />
    </>
  );
}

export default CategoryTable;
