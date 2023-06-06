export interface PropertyResponse {
  content:          Property[];
  pageable:         Pageable;
  totalPages:       number;
  totalElements:    number;
  last:             boolean;
  size:             number;
  number:           number;
  sort:             Sort;
  numberOfElements: number;
  first:            boolean;
  empty:            boolean;
}

export interface Property {
  id:            number;
  lat:           string;
  lon:           string;
  name:          string;
  title:         string;
  price:         number;
  m2:            number;
  description:   string;
  totalBedRooms: number;
  totalBaths:    number;
  propertyType:  string;
  city:          string;
}
export interface Pageable {
  sort:       Sort;
  offset:     number;
  pageNumber: number;
  pageSize:   number;
  unpaged:    boolean;
  paged:      boolean;
}

export interface Sort {
  empty:    boolean;
  sorted:   boolean;
  unsorted: boolean;
}
