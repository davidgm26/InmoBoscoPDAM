import 'package:get_it/get_it.dart';
import 'package:injectable/injectable.dart';
import 'package:inmobosco/config/locator.dart';
import 'package:inmobosco/models/models.dart';
import 'package:inmobosco/repositories/property_repository.dart';
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
    String? token = _localStorageService.getFromDisk("user_token");
    PropertyResponse response = await _propertyRepository.fetchProperties(page,token!);

    return response;
  }
}
