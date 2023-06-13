import 'package:get_it/get_it.dart';
import 'package:injectable/injectable.dart';
import 'package:inmobosco/config/locator.dart';
import 'package:inmobosco/models/property_response.dart';
import 'package:inmobosco/repositories/inmbueble_repository.dart';
import 'package:inmobosco/services/localstorage_service.dart';

@singleton
class PropertyService {
  late PropertyRepository _propertyRepository;
  late LocalStorageService _localStorageService;

  PropertyService() {
    _propertyRepository = getIt<PropertyRepository>();

    GetIt.I
        .getAsync<LocalStorageService>()
        .then((value) => _localStorageService = value);
  }


  Future<PropertyResponse> getAllProperties(page) async{
    String? token = _localStorageService.getFromDisk("token");
    PropertyResponse response = await _propertyRepository.fetchProperties(page,token!);

    return response;
  }

    Future<Property> getOneProperty(page,id) async{
    String? token = _localStorageService.getFromDisk("token");
    Property response = await _propertyRepository.getOneProperty(id,token!);

    return response;
  }
}
