import { Fragment, useState } from "react";
import { LanguageDTO } from "../pages/Languages";
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
import LanguageForm from "./CategoryForm";

interface Props {
  languageList: LanguageDTO[];
  editLanguageById?: (
    language: LanguageDTO,
    languageIndex: number
  ) => Promise<void>;
  deleteLanguageById?: (
    languageId: number,
    languageIndex: number
  ) => Promise<void>;
}

function LanguageTable({ languageList }: Props) {
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
        <h1>Languages</h1>
      </Stack>
      <TableContainer
        sx={{
          width: "30%",
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
            {languageList.map((item, index) => (
              <LanguageRow key={index} row={item} index={index} />
            ))}
          </TableBody>
        </Table>
      </TableContainer>
      <LanguageForm open={openForm} handleClose={handleClose} />
    </>
  );
}

function LanguageRow(props: { row: LanguageDTO; index: number }) {
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
      <LanguageForm open={openForm} category={row} categoryIndex={index} handleClose={handleClose} />
    </>
  );
}

export default LanguageTable;
