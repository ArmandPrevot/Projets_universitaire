const { model, Schema } = require("mongoose");

const Matiere = model(
  "Matieres",
  Schema({
    nom: String,
    description: String,
    logo: String,
    professor: { type: Schema.Types.ObjectId, ref: 'Users' },
  })
);

module.exports.Matiere = Matiere;
