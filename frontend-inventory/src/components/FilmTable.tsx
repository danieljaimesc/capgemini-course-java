import { Fragment, useState } from "react";
import { FilmDTO } from "../pages/Films";
import Collapse from "@mui/material/Collapse";
import CircularProgress from "@mui/material/CircularProgress";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import IconButton from "@mui/material/IconButton";
import EditIcon from "@mui/icons-material/Edit";
import DeleteIcon from "@mui/icons-material/Delete";
import Typography from "@mui/material/Typography";
import Paper from "@mui/material/Paper";
import Stack from "@mui/material/Stack";
import TableContainer from "@mui/material/TableContainer";
import Table from "@mui/material/Table";
import TableRow from "@mui/material/TableRow";
import TableHead from "@mui/material/TableHead";
import TableCell from "@mui/material/TableCell";
import TableBody from "@mui/material/TableBody";
import KeyboardArrowDownIcon from "@mui/icons-material/KeyboardArrowDown";
import KeyboardArrowUpIcon from "@mui/icons-material/KeyboardArrowUp";
import FilmForm from "./FilmForm";
import { ActorDTO } from "../pages/Actors";
import { CategoryDTO } from "../pages/Categories";
import { LanguageDTO } from "../pages/Languages";
import Pagination, { PageDetails } from "../components/Pagination";

interface Props {
  filmList: FilmDTO[];
  pageDetails: PageDetails;
  getFilmDetailsById: (filmId: number, filmIndex: number) => Promise<void>;
  deleteFilmById?: (filmId: number) => Promise<void>;
  editFilmById?: (film: FilmDTO, filmIndex: number) => Promise<void>;
}

function FilmTable({ filmList, getFilmDetailsById, pageDetails }: Props) {
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
        <h1>Films</h1>
      </Stack>
      <TableContainer
        sx={{
          width: "55%",
          maxWidth: 600,
          marginLeft: "auto",
          marginRight: "auto",
          bgcolor: "background.paper",
        }}
        component={Paper}
      >
        <Table aria-label="collapsible table">
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
              <TableCell />
              <TableCell>ID</TableCell>
              <TableCell>Title</TableCell>
              <TableCell>Edit</TableCell>
              <TableCell>Delete</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {filmList.map((item, index) => (
              <FilmRow
                key={index}
                row={item}
                index={index}
                getDetails={getFilmDetailsById}
              />
            ))}
          </TableBody>
        </Table>
        <Pagination pageDetails={pageDetails} />
      </TableContainer>
      <FilmForm open={openForm} handleClose={handleClose} />
    </>
  );
}

export default FilmTable;

function FilmRow(props: {
  row: FilmDTO;
  index: number;
  getDetails: (filmId: number, filmIndex: number) => Promise<void>;
}) {
  const { row, index, getDetails } = props;
  const [expand, setExpand] = useState<boolean>(false);
  const [loading, setLoading] = useState<boolean>(true);
  const [openForm, setOpenForm] = useState<boolean>(false);
  const toggleAccordion = () => {
    setExpand(!expand);
  };

  const handleClickOpen = async () => {
    setOpenForm(true);
    if (loading) {
      await getDetails(row.id, index);
      setLoading(false);
    }
  };

  const handleClose = () => {
    setOpenForm(false);
  };

  return (
    <>
      <Fragment>
        <TableRow sx={{ "& > *": { borderBottom: "unset" } }}>
          <TableCell>
            <IconButton
              aria-label="expand row"
              size="small"
              onClick={async () => {
                toggleAccordion();
                if (loading) {
                  await getDetails(row.id, index);
                  await new Promise((r) => setTimeout(r, 700));
                  setLoading(false);
                }
              }}
            >
              {expand ? <KeyboardArrowUpIcon /> : <KeyboardArrowDownIcon />}
            </IconButton>
          </TableCell>
          <TableCell component="th" scope="row">
            {row.id}
          </TableCell>
          <TableCell component="th" scope="row">
            {row.title}
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
        <TableRow>
          <TableCell style={{ paddingBottom: 0, paddingTop: 0 }} colSpan={6}>
            <Collapse in={expand} timeout="auto" unmountOnExit>
              {loading ? (
                <Box sx={{ margin: 1 }}>
                  <Stack alignItems="center">
                    <CircularProgress />
                  </Stack>
                </Box>
              ) : (
                <>
                  <Box sx={{ margin: 1 }}>
                    <Typography variant="h6" gutterBottom component="div">
                      Details
                    </Typography>
                    <Table size="small" aria-label="details">
                      <TableRow>
                        <TableCell sx={{ verticalAlign: "top" }} variant="head">
                          Description
                        </TableCell>
                        <TableCell>{row.description}</TableCell>
                      </TableRow>
                    </Table>
                    <Table size="small" aria-label="details">
                      <TableRow>
                        <TableCell variant="head">Categories</TableCell>
                        <TableCell>
                          {row.categoryList
                            ? row.categoryList.map((item) => {
                                return (item as CategoryDTO).name + ", ";
                              })
                            : "Any categories"}
                        </TableCell>
                      </TableRow>
                      <TableRow>
                        <TableCell variant="head">Actors</TableCell>
                        <TableCell>
                          {row.actorList
                            ? row.actorList.map((item) => {
                                return (
                                  (item as ActorDTO).firstName +
                                  " " +
                                  (item as ActorDTO).lastName +
                                  ", "
                                );
                              })
                            : "Any actors"}
                        </TableCell>
                      </TableRow>
                    </Table>
                    <Table size="small" aria-label="details">
                      <TableHead>
                        <TableRow>
                          <TableCell>Release Year</TableCell>
                          <TableCell>Rating</TableCell>
                          <TableCell>Rental Duration</TableCell>
                          <TableCell>Replacement Cost ($)</TableCell>
                          <TableCell>Language</TableCell>
                          <TableCell>Language (VOSE)</TableCell>
                        </TableRow>
                      </TableHead>
                      <TableBody>
                        <TableCell>{row.releaseYear}</TableCell>
                        <TableCell>{row.rating}</TableCell>
                        <TableCell>{row.rentalDuration}</TableCell>
                        <TableCell>{row.replacementCost}</TableCell>
                        <TableCell>
                          {row.language
                            ? (row.language as LanguageDTO).name
                            : ""}
                        </TableCell>
                        <TableCell>
                          {row.languageVO
                            ? (row.languageVO as LanguageDTO).name
                            : ""}
                        </TableCell>
                      </TableBody>
                    </Table>
                  </Box>
                </>
              )}
            </Collapse>
          </TableCell>
        </TableRow>
      </Fragment>
      {!loading ? (
        <FilmForm filmDTO={row} open={openForm} handleClose={handleClose} />
      ) : (
        ""
      )}
    </>
  );
}

/**
 *
 */
