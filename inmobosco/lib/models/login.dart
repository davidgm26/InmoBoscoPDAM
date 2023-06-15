class LoginResponse {
  String? id;
  String? username;
  String? nombre;
  String? createdAt;
  String? avatar;
  String? token;

  LoginResponse(
      {this.id,
      this.username,
      this.nombre,
      this.createdAt,
      this.avatar,
      this.token,
});

  LoginResponse.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    username = json['username'];
    nombre = json['nombre'];
    createdAt = json['createdAt'];
    avatar = json['avatar'];
    token = json['token'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['id'] = id;
    data['username'] = username;
    data['nombre'] = nombre;
    data['token'] = token;
    data['avatar'] = avatar;
    return data;
  }
  
}


class LoginRequest {
  String? username;
  String? password;

  LoginRequest({this.username, this.password});

  LoginRequest.fromJson(Map<String, dynamic> json) {
    username = json['username'];
    password = json['password'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['username'] = username;
    data['password'] = password;
    return data;
  }
}

