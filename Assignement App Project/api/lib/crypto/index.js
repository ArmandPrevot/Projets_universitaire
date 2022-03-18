const Crypto = require("crypto-js");
const { AES_SECRET } = process.env;

const Encrypt = (password) => {
  let encJson = Crypto.AES.encrypt(
    JSON.stringify(password),
    AES_SECRET
  ).toString();
  let encData = Crypto.enc.Base64.stringify(Crypto.enc.Utf8.parse(encJson));
  return encData;
};

const Decrypt = (password) => {
  let decData = Crypto.enc.Base64.parse(password).toString(Crypto.enc.Utf8);
  let bytes = Crypto.AES.decrypt(decData, AES_SECRET).toString(Crypto.enc.Utf8);
  return JSON.parse(bytes);
};

module.exports.Encrypt = Encrypt;
module.exports.Decrypt = Decrypt;
