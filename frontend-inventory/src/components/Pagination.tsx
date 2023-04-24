import {} from "react";
import TablePagination from "@mui/material/TablePagination";
import Stack from "@mui/material/Stack";

export interface PageDetails {
  pageNumber: {
    value: number;
    set: React.Dispatch<React.SetStateAction<number>>;
  };
  pageSize: {
    value: number;
    set: React.Dispatch<React.SetStateAction<number>>;
  };
  totalElements: {
    value: number;
    set: React.Dispatch<React.SetStateAction<number>>;
  };
}

interface Props {
  pageDetails: PageDetails;
}

const handleChangePage = (
  event: React.MouseEvent<HTMLButtonElement> | null,
  newPage: number
) => {
  //setPageNumber(newPage);
};

const handleChangeRowsPerPage = (
  event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
) => {
  //setPageSize(parseInt(event.target.value, 10));
  //setPageNumber(0);
};

function Pagination({ pageDetails }: Props) {
  return (
    <Stack
      sx={{
        ".MuiTablePagination-root": {
          display: "flex",
          justifyContent: "center",
        },
      }}
    >
      <TablePagination
        component="div"
        count={pageDetails.totalElements.value}
        page={pageDetails.pageNumber.value}
        onPageChange={handleChangePage}
        rowsPerPage={pageDetails.pageSize.value}
        onRowsPerPageChange={handleChangeRowsPerPage}
      />
    </Stack>
  );
}

export default Pagination;
