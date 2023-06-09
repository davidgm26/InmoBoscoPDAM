import 'package:inmobosco/models/login.dart';
class User {
  String? id;
  String? username;
  String? avatar;
  String? nombre;

  User({this.id, this.username, this.avatar, this.nombre});

    User.fromLoginResponse(LoginResponse response) {
      this.id = response.id;
      this.username = response.username;
      this.avatar = response.avatar;
      this.nombre = response.nombre;
    }
}

class UserResponse extends User {

  UserResponse(id, username, nombre, avatar) : super(id: id, username: username, nombre: nombre, avatar: avatar);

  UserResponse.fromJson(Map<String, dynamic> json) {
  id = json['id'];
  username = json['username'];
  avatar = json['avatar'];
  nombre = json['nombre'];
}
  Map<String, dynamic> toJson() {
  final Map<String, dynamic> data = new Map<String, dynamic>();
  data['id'] = this.id;
  data['username'] = this.username;
  data['avatar'] = this.avatar;
  data['nombre'] = this.nombre;
  return data;
}

}
