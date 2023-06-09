export interface UserResponse {
  content:          User[];
  pageable:         Pageable;
  last:             boolean;
  totalPages:       number;
  totalElements:    number;
  size:             number;
  number:           number;
  sort:             Sort;
  first:            boolean;
  numberOfElements: number;
  empty:            boolean;
}

export interface User {
  id:               string;
  rol:              string;
  firstname:        string;
  lastname:         string;
  username:         string;
  password:         string;
  dni:              string;
  avatar:           string;
  phoneNumber:      string;
  email:            string;
  birthdate:        Date;
  createdAt:        Date;
  accountNonLocked: boolean;
  enabled:          boolean;
}

export interface Pageable {
  sort:       Sort;
  offset:     number;
  pageNumber: number;
  pageSize:   number;
  paged:      boolean;
  unpaged:    boolean;
}

export interface Sort {
  empty:    boolean;
  sorted:   boolean;
  unsorted: boolean;
}
