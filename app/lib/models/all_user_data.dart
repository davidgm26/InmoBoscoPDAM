class UserDataResponse {
  String? firstname;
  String? lastname;
  String? username;
  String? avatar;
  String? email;
  String? rol;
  String? id;
  bool? enabled;

  UserDataResponse(
      {this.firstname,
      this.lastname,
      this.username,
      this.avatar,
      this.email,
      this.rol,
      this.id,
      this.enabled});

  UserDataResponse.fromJson(Map<String, dynamic> json) {
    firstname = json['firstname'];
    lastname = json['lastname'];
    username = json['username'];
    avatar = json['avatar'];
    email = json['email'];
    rol = json['rol'];
    id = json['id'];
    enabled = json['enabled'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['firstname'] = this.firstname;
    data['lastname'] = this.lastname;
    data['username'] = this.username;
    data['avatar'] = this.avatar;
    data['email'] = this.email;
    data['rol'] = this.rol;
    data['id'] = this.id;
    data['enabled'] = this.enabled;
    return data;
  }
}