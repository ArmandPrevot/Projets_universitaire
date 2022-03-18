const express = require("express");
const router = express.Router();
const { WithToken } = require("../../lib/middleware/withToken");
const {
  WithRoleStudent,
  WithRoleProfessor,
  WithRoleAdmin,
} = require("../../lib/middleware/withRole");
const AssignmentsService = require('../../services/Assignments/Assignments');
const MatiereService = require('../../services/Matiere/Matiere');
const UserService = require('../../services/User/User');
const { ROLES } = require("../../lib/roles");


router.get("/", WithToken, async (req, res) => {
    const { body: { user } } = req;
    const { start, end } = req.query;
    try {
        const ret = await AssignmentsService.getRelatedAssignments(user, start, end);
        const count = await AssignmentsService.getCount(user);
        res.status(200).send({
            assignments: ret,
            count,
        });
    } catch (err) {
        res.status(500).send({ error: err.message });
    }
});

router.get("/:id", WithToken, async (req, res) => {
    const { body: { user } } = req;
    const { id } = req.params;
    try {
        if (!id) throw new Error("id is required");
        const ret = await AssignmentsService.findOne(id);
        res.status(200).send(ret);
    } catch (err) {
        res.status(500).send({ error: err.message });
    }
});

router.post("/", WithToken, WithRoleStudent, async (req, res) => {
    const { body: { user, matiereName, title, description } } = req;
    try {
        const ret = await AssignmentsService.create(user, matiereName, title, description);
        res.status(200).send(ret);
    } catch (err) {
        res.status(500).send({error: err.message });
    }
});

router.put("/:assignmentId", WithToken, WithRoleProfessor, async (req, res) => {
    const { body: { user, grade, comments, matiereName, description, title, reviewed } } = req;
    const { assignmentId } = req.params;

    try {
        if (user.role === ROLES.PROFESSOR) {
            const ret = await AssignmentsService.update(user, assignmentId, { grade, comments, reviewed });
            res.status(200).send(ret);

        } else if (user.role === ROLES.ADMIN) {
            const matiere = await MatiereService.findOneByName(matiereName);
            if (!matiere) throw new Error('Matiere not found');

            const ret = await AssignmentsService.update(user, assignmentId, { grade, matiere: matiere._id, comments, description, title, reviewed });
            res.status(200).send(ret);
        }
    } catch (err) {
        res.status(500).send({ error: err.message });
    }
    
});

router.delete("/:assignmentId", WithToken, WithRoleAdmin, async (req, res) => {
    const { assignmentId } = req.params;
    try {
        const ret = await AssignmentsService.deleteOne(assignmentId);
        res.status(200).send(ret);
    } catch (err) {
        res.status(500).send({ error: err.message });
    }
});

module.exports = router;
