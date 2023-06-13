class InmuebleResponse {
  InmuebleResponse({
    required this.content,
    required this.pageable,
    required this.last,
    required this.totalPages,
    required this.totalElements,
    required this.size,
    required this.number,
    required this.sort,
    required this.first,
    required this.numberOfElements,
    required this.empty,
  });
  late final List<Inmueble> content;
  late final Pageable pageable;
  late final bool last;
  late final int totalPages;
  late final int totalElements;
  late final int size;
  late final int number;
  late final Sort sort;
  late final bool first;
  late final int numberOfElements;
  late final bool empty;
  
  InmuebleResponse.fromJson(Map<String, dynamic> json){
    content = List.from(json['content']).map((e)=>Inmueble.fromJson(e)).toList();
    pageable = Pageable.fromJson(json['pageable']);
    last = json['last'];
    totalPages = json['totalPages'];
    totalElements = json['totalElements'];
    size = json['size'];
    number = json['number'];
    sort = Sort.fromJson(json['sort']);
    first = json['first'];
    numberOfElements = json['numberOfElements'];
    empty = json['empty'];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['content'] = content.map((e)=>e.toJson()).toList();
    _data['pageable'] = pageable.toJson();
    _data['last'] = last;
    _data['totalPages'] = totalPages;
    _data['totalElements'] = totalElements;
    _data['size'] = size;
    _data['number'] = number;
    _data['sort'] = sort.toJson();
    _data['first'] = first;
    _data['numberOfElements'] = numberOfElements;
    _data['empty'] = empty;
    return _data;
  }
}

class Inmueble {
  Inmueble({
    required this.tipo,
    required this.descripcion,
    required this.precio,
    required this.ubicacion,
    required this.metrosCuadrados,
    required this.numBanios,
    required this.numHab,
  });
  late final String tipo;
  late final String descripcion;
  late final dynamic precio;
  late final String ubicacion;
  late final dynamic metrosCuadrados;
  late final int numBanios;
  late final int numHab;
  
  Inmueble.fromJson(Map<String, dynamic> json){
    tipo = json['tipo'];
    descripcion = json['descripcion'];
    precio = json['precio'];
    ubicacion = json['ubicacion'];
    metrosCuadrados = json['metrosCuadrados'];
    numBanios = json['numBanios'];
    numHab = json['numHab'];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['tipo'] = tipo;
    _data['descripcion'] = descripcion;
    _data['precio'] = precio;
    _data['ubicacion'] = ubicacion;
    _data['metrosCuadrados'] = metrosCuadrados;
    _data['numBanios'] = numBanios;
    _data['numHab'] = numHab;
    return _data;
  }
}

class Pageable {
  Pageable({
    required this.sort,
    required this.offset,
    required this.pageNumber,
    required this.pageSize,
    required this.paged,
    required this.unpaged,
  });
  late final Sort sort;
  late final int offset;
  late final int pageNumber;
  late final int pageSize;
  late final bool paged;
  late final bool unpaged;
  
  Pageable.fromJson(Map<String, dynamic> json){
    sort = Sort.fromJson(json['sort']);
    offset = json['offset'];
    pageNumber = json['pageNumber'];
    pageSize = json['pageSize'];
    paged = json['paged'];
    unpaged = json['unpaged'];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['sort'] = sort.toJson();
    _data['offset'] = offset;
    _data['pageNumber'] = pageNumber;
    _data['pageSize'] = pageSize;
    _data['paged'] = paged;
    _data['unpaged'] = unpaged;
    return _data;
  }
}

class Sort {
  Sort({
    required this.empty,
    required this.sorted,
    required this.unsorted,
  });
  late final bool empty;
  late final bool sorted;
  late final bool unsorted;
  
  Sort.fromJson(Map<String, dynamic> json){
    empty = json['empty'];
    sorted = json['sorted'];
    unsorted = json['unsorted'];
  }

  Map<String, dynamic> toJson() {
    final _data = <String, dynamic>{};
    _data['empty'] = empty;
    _data['sorted'] = sorted;
    _data['unsorted'] = unsorted;
    return _data;
  }
}