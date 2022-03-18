const { model, Schema } = require("mongoose");


const Assignments = model(
  "Assignments",
  Schema({
    author: { type: Schema.Types.ObjectId, ref: 'Users' },
    matiere: { type: Schema.Types.ObjectId, ref: 'Matieres' },
    description: String,
    title: String,
    grade: Number,
    reviewed: Boolean,
    comments: String,
    date: { type: Date, default: Date.now },
  })
);

module.exports.Assignments = Assignments;
