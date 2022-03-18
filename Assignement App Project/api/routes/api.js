const express = require("express");
const router = express.Router();
const { WithToken } = require("../lib/middleware/withToken");
const {
  WithRoleAdmin,
  WithRoleStudent,
  WithRoleProfessor,
} = require("../lib/middleware/withRole");
const UserService = require("../services/User/User");
const MatiereService = require("../services/Matiere/Matiere");
const { ROLES } = require("../lib/roles");

router.post("/login", async (req, res) => {
  const { email, password } = req.body;

  const token = await UserService.checkCredentials(email, password);
  if (token) {
    res.status(200).send({ token });
  } else {
    res.status(401).json({ message: "Invalid credentials" });
  }
});

router.post("/signup", async (req, res) => {
  const { name, email, password, role, avatar, matiereName, matiereAvatar, matiereDescription } = req.body;

  if (!email || !password || !role || !name) {
    return res.status(400).json({ message: "Missing parameters" });
  }

  try {
    let shouldCreateMatiere = false;
    if (role === ROLES.PROFESSOR) { 
      if (!matiereName || !matiereDescription) throw new Error("Missing parameters");
      const matiere = await MatiereService.findOneByName(matiereName);
      if (matiere) throw new Error("Matiere already exists");
      shouldCreateMatiere = true;
    }

    const user = await UserService.create(name, email, password, role, avatar?.length > 0 ? avatar : undefined);

    if (user) {
      if (shouldCreateMatiere) {
        await MatiereService.create(matiereName, matiereDescription, matiereAvatar.length > 0 ? matiereAvatar : undefined, user._id);
      }
      const token = await UserService.generateToken(user);
      res.status(201).send({ token });
    } else {
      res.status(400).json({ message: "Email already in use!" });
    }
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

module.exports = router;
