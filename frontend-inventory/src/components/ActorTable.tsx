import { Fragment, useState } from "react";
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
import { ActorDTO } from "../pages/Actors";
import Pagination, { PageDetails } from "../components/Pagination";

interface Props {
  actorList: ActorDTO[];
  pageDetails: PageDetails;
  editActorById?: (actor: ActorDTO, actorIndex: number) => Promise<void>;
  deleteActorById?: (actorId: number, actorIndex: number) => Promise<void>;
}

function ActorTable({ actorList, pageDetails }: Props) {
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
    </>
  );
}

function ActorRow(props: { row: ActorDTO; index: number }) {
  const { row, index } = props;
  return (
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

export default ActorTable;
