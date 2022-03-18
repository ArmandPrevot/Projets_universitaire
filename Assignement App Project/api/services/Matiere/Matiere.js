const { Matiere } = require("../../models/Matiere");

const findOneByProfessor = async (professor) => Matiere.findOne({ professor: professor._id });

const findOneByName = async (nom) => Matiere.findOne({ nom });

const findOneById = async(id) => Matiere.findOne({ _id: id });

const create = async(
    nom,
    description,
    logo = "https://i.pinimg.com/736x/b7/ab/72/b7ab722d2e9ec6ed41423dcbf54b9f3c.jpg",
    professor,
) => Matiere.create({
    nom,
    description,
    logo,
    professor,
});

const getAll = async () => {
    const matieres = await Matiere.find({}).populate('professor');
    return matieres.map(matiere => {
        return {
            ...matiere._doc,
            professor: {
                ...matiere.professor._doc,
                password: undefined,
            },
        };
    });
}

module.exports.findOneByProfessor = findOneByProfessor;
module.exports.findOneByName = findOneByName;
module.exports.findOneById = findOneById;
module.exports.create = create;
module.exports.getAll = getAll;