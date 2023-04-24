import { Fragment, useState } from "react";
import { LanguageDTO } from "../pages/Languages";
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
    </>
  );
}

function LanguageRow(props: { row: LanguageDTO; index: number }) {
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

export default LanguageTable;
