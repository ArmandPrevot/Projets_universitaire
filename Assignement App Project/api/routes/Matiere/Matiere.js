const express = require("express");
const router = express.Router();
const { WithToken } = require("../../lib/middleware/withToken");
const MatiereService = require('../../services/Matiere/Matiere');


router.get("/", WithToken, async (req, res) => {
    const { body: { user } } = req;
    try {
        const ret = await MatiereService.getAll();
        res.status(200).send(ret);
    } catch (err) {
        res.status(500).send({ error: err.message });
    }
});

module.exports = router;
