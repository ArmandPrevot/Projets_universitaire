const { Users } = require("../../models/User");
const { JWT_SECRET } = process.env;
const jwt = require("jsonwebtoken");
const { Encrypt, Decrypt } = require("../../lib/crypto");

const getAllUsers = async () => Users.find({});

const generateToken = (user) => {
  return jwt.sign({
    name: user.name,
    role: user.role,
    email: user.email,
    avatar: user.avatar || 'https://sbcf.fr/wp-content/uploads/2018/03/sbcf-default-avatar.png',
    _id: user._id,
  }, JWT_SECRET);
};

const checkCredentials = async (email, password) => {
  const user = await Users.findOne({ email });
  if (user) {
    const userPasswordDecrypted = Decrypt(user.password);
    if (password === userPasswordDecrypted) {
      return generateToken(user);
    }
  }
  return false;
};

const create = async (name, email, password, role, avatar = "https://sbcf.fr/wp-content/uploads/2018/03/sbcf-default-avatar.png") => {
  const checkEmail = await Users.findOne({ email });
  if (checkEmail) {
    return undefined;
  }

  const createdUser = await Users.create({
    name,
    email,
    password: Encrypt(password),
    role,
    avatar,
  });
  if (createdUser) {
    return createdUser;
  }
  throw new Error("User not created");
};

const findById = async (id) => Users.findOne({ _id: id });

module.exports.getAllUsers = getAllUsers;
module.exports.checkCredentials = checkCredentials;
module.exports.create = create;
module.exports.generateToken = generateToken;
module.exports.findById = findById;
