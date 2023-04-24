import { Fragment, useState } from "react";
import { CategoryDTO } from "../pages/Categories";
import IconButton from "@mui/material/IconButton";
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
    </>
  );
}

function CategoryRow(props: { row: CategoryDTO; index: number }) {
  const { row, index } = props;

  return (
    <Fragment>
      <TableRow sx={{ "& > *": { borderBottom: "unset" } }}>
        <TableCell component="th" scope="row">
          {row.id}
        </TableCell>
        <TableCell component="th" scope="row">
          {row.name}
        </TableCell>
        <TableCell>
          <IconButton>
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
  );
}

export default CategoryTable;
