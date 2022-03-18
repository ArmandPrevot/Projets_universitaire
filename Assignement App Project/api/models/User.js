const { model, Schema } = require("mongoose");

const Users = model(
  "Users",
  Schema({
    name: String,
    email: String,
    password: String,
    role: String,
    token: String,
    avatar: String,
  })
);

module.exports.Users = Users;
