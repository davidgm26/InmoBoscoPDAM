class UserResponse {
  String? firstname;
  String? lastname;
  String? username;
  String? avatar;
  String? email;
  String? rol;
  String? id;
  String? token;
  String? refreshToken;

  UserResponse(
      {this.firstname,
      this.lastname,
      this.username,
      this.avatar,
      this.email,
      this.rol,
      this.id,
      this.token,
      this.refreshToken});

  UserResponse.fromJson(Map<String, dynamic> json) {
    firstname = json['firstname'];
    lastname = json['lastname'];
    username = json['username'];
    avatar = json['avatar'];
    email = json['email'];
    rol = json['rol'];
    id = json['id'];
    token = json['token'];
    refreshToken = json['refreshToken'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['firstname'] = firstname;
    data['lastname'] = lastname;
    data['username'] = username;
    data['avatar'] = avatar;
    data['email'] = email;
    data['rol'] = rol;
    data['id'] = id;
    data['token'] = token;
    data['refreshToken'] = refreshToken;
    return data;
  }
}