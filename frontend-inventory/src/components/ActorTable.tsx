import { Fragment, useState } from "react";
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
import { ActorDTO } from "../pages/Actors";
import Pagination, { PageDetails } from "../components/Pagination";
import ActorForm from "./ActorForm";

interface Props {
  actorList: ActorDTO[];
  pageDetails: PageDetails;
  editActorById?: (actor: ActorDTO, actorIndex: number) => Promise<void>;
  deleteActorById?: (actorId: number, actorIndex: number) => Promise<void>;
}

function ActorTable({ actorList, pageDetails }: Props) {
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
        <h1>Actors</h1>
      </Stack>
      <TableContainer
        sx={{
          width: "50%",
          maxWidth: 600,
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
              <TableCell>First Name</TableCell>
              <TableCell>Last Name</TableCell>
              <TableCell>Edit</TableCell>
              <TableCell>Delete</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {actorList.map((item, index) => (
              <ActorRow key={index} row={item} index={index} />
            ))}
          </TableBody>
        </Table>
        <Pagination pageDetails={pageDetails} />
      </TableContainer>
      <ActorForm open={openForm} handleClose={handleClose} />
    </>
  );
}

function ActorRow(props: { row: ActorDTO; index: number }) {
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
            {row.firstName}
          </TableCell>
          <TableCell component="th" scope="row">
            {row.lastName}
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
      <ActorForm open={openForm} actor={row} handleClose={handleClose} />
    </>
  );
}

export default ActorTable;
