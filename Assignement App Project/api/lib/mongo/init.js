const mongoose = require("mongoose");
const { USER, PASSWORD, DATABASE } = process.env;

const uri = `mongodb+srv://${USER}:${PASSWORD}@cluster0.k3ur5.mongodb.net/${DATABASE}?retryWrites=true&w=majority`;
mongoose.connect(uri, { useNewUrlParser: true, useUnifiedTopology: true });

module.exports.Database = mongoose.connection;
