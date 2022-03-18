const { Assignments } = require('../../models/Assignments');
const { ROLES } = require('../../lib/roles/index');
const MatiereService = require('../../services/Matiere/Matiere');
const UsersService = require('../../services/User/User');

const getRelatedAssignments = async (user, start = 0, end = 0) => {
  switch (user.role) {
    case ROLES.STUDENT:
      const find = await Assignments.find({ author: user._id }, null, { skip: start, limit: end}).populate('author').populate('matiere');
      return Promise.all((find || []).map(async ({ _doc }) => {
        const user = await UsersService.findById(_doc.matiere._doc.professor);
        return {
          ..._doc,
          matiere: {
            ..._doc.matiere._doc,
            professor: {
              ...user._doc,
              password: undefined,
            },
          },
          author: {
            ..._doc.author._doc,
            password: undefined,
          },
        };
      }));
  
    case ROLES.PROFESSOR:
      const matiere = await MatiereService.findOneByProfessor(user);
      if (!matiere) throw new Error('Matiere not found');
      const findProff = await Assignments.find({ matiere: matiere._id }, null, { skip: start, limit: end }).populate('author').populate('matiere');
      return Promise.all((findProff || []).map(async ({ _doc }) => {
        const user = await UsersService.findById(_doc.matiere._doc.professor);
        return {
          ..._doc,
          matiere: {
            ..._doc.matiere._doc,
            professor: {
              ...user._doc,
              password: undefined,
            },
          },
          author: {
            ..._doc.author._doc,
            password: undefined,
          },
        };
      }));
    case ROLES.ADMIN:
      const findAdmin = await Assignments.find({}, null, { skip: start, limit: end }).populate('author').populate('matiere');
      return Promise.all((findAdmin || []).map(async ({ _doc }) => {
        const user = await UsersService.findById(_doc.matiere?.professor);
        return {
          ..._doc,
          matiere: {
            ..._doc.matiere?._doc,
            professor: {
              ...user?._doc,
              password: undefined,
            },
          },
          author: {
            ..._doc.author?._doc,
            password: undefined,
          },
        };
      }));
    default: return [];
  } 
};

const getCount = async (user) => {
  switch (user.role) {
    case ROLES.STUDENT:
      const result = await Assignments.find({ author: user._id })
        .populate('author')
        .populate('matiere')
        .count();
      return result;
  
    case ROLES.PROFESSOR:
      const matiere = await MatiereService.findOneByProfessor(user);
      if (!matiere) throw new Error('Matiere not found');
      const resultProf = await Assignments.find({ matiere: matiere._id })
        .populate('author')
        .populate('matiere')
        .count();
      return resultProf;

    case ROLES.ADMIN:
      const resultAdmin = await Assignments.find({})
        .populate('author')
        .populate('matiere')
        .count();
      return resultAdmin;
    default: return 0;
  } 
}

const create = async (user, matiereName, title, description) => {
  const matiere = await MatiereService.findOneByName(matiereName);
  if (!matiere) throw new Error('Matiere not found');

  const ret = await Assignments.create({
    author: user._id,
    matiere: matiere._id,
    title,
    description,
    reviewed: false,
  });
  return ret;
};

const update = async (user, assignmentId, updateData) => {
  const assignment = await Assignments.findOne({ _id: assignmentId });
  if (!assignment) throw new Error('Assignment not found');

  const matiere = await MatiereService.findOneById(assignment.matiere);
  if (!matiere) throw new Error('Matiere not found');
  if (user.role !== ROLES.ADMIN && matiere.professor.toString() !== user._id) throw new Error('Unauthorized');

  const updatedAssignment = await Assignments.findOneAndUpdate({ _id: assignmentId }, updateData, {upsert: true, new: true});

  return updatedAssignment;
};

const findOne = async (id) => {
  const assignment = await Assignments.findOne({ _id: id }).populate('author').populate('matiere');
  const user = await UsersService.findById(assignment.matiere.professor);
  return {
    ...assignment.toObject(),
    matiere: {
      ...assignment.matiere.toObject(),
      professor: user,
    },
  };
};

const deleteOne = async (id) => Assignments.deleteOne({ _id: id });

module.exports.getRelatedAssignments = getRelatedAssignments;
module.exports.create = create;
module.exports.update = update;
module.exports.findOne = findOne;
module.exports.getCount = getCount;
module.exports.deleteOne = deleteOne;