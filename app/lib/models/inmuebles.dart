class InmuebleResponse {
  late List<Inmueble> inmueble;
  late bool last;
  late int totalPages;
  late int totalElements;
  late int size;
  late int number;
  late bool first;
  late int numberOfElements;
  late bool empty;

  InmuebleResponse(
      {
      required this.inmueble,
      required this.last,
      required this.totalPages,
      required this.totalElements,
      required this.size,
      required this.number,
      required this.first,
      required this.numberOfElements,
      required this.empty});

  InmuebleResponse.fromJson(Map<String, dynamic> json) {
    if (json['Inmueble'] != null) {
      inmueble = List<Inmueble>.empty();
      json['Inmueble'].forEach((v) {
        inmueble.add(new Inmueble.fromJson(v));
      });
    }

    last = json['last'];
    totalPages = json['totalPages'];
    totalElements = json['totalElements'];
    size = json['size'];
    number = json['number'];
    first = json['first'];
    numberOfElements = json['numberOfElements'];
    empty = json['empty'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    if (this.inmueble != null) {
      data['Inmueble'] = this.inmueble.map((v) => v.toJson()).toList();
    }
    data['last'] = this.last;
    data['totalPages'] = this.totalPages;
    data['totalElements'] = this.totalElements;
    data['size'] = this.size;
    data['number'] = this.number;
    data['first'] = this.first;
    data['numberOfElements'] = this.numberOfElements;
    data['empty'] = this.empty;
    return data;
  }
}

class Inmueble {
  late String tipo;
  late String descripcion;
  late int precio;
  late String ubicacion;
  late double metrosCuadrados;
  late int numBanios;
  late int numHab;

  Inmueble({
      required this.tipo,
      required this.descripcion,
      required this.precio,
      required this.ubicacion,
      required this.metrosCuadrados,
      required this.numBanios,
      required this.numHab});

  Inmueble.fromJson(Map<String, dynamic> json) {
    tipo = json['tipo'];
    descripcion = json['descripcion'];
    precio = json['precio'];
    ubicacion = json['ubicacion'];
    metrosCuadrados = json['metrosCuadrados'];
    numBanios = json['numBanios'];
    numHab = json['numHab'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['tipo'] = this.tipo;
    data['descripcion'] = this.descripcion;
    data['precio'] = this.precio;
    data['ubicacion'] = this.ubicacion;
    data['metrosCuadrados'] = this.metrosCuadrados;
    data['numBanios'] = this.numBanios;
    data['numHab'] = this.numHab;
    return data;
  }
}
