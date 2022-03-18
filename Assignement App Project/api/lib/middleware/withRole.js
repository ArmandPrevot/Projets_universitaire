const jwt = require('jsonwebtoken');
const { JWT_SECRET } = process.env;
const { ROLES } = require('../roles');

const WithRoleAdmin = (req, res, next) => {
    const { user: { role } } = req.body;
    if (!role) { return res.status(401).json({ message: 'No role provided' }); }
    
    if (role === ROLES.ADMIN) {
        next();
    } else { 
        res.status(401).json({ message: 'Invalid role' });
    }
};

const WithRoleStudent = (req, res, next) => {
    const { user: { role } } = req.body;
    if (!role) { return res.status(401).json({ message: 'No role provided' }); }
    
    if (role === ROLES.ADMIN || role === ROLES.STUDENT) {
        next();
    } else { 
        res.status(401).json({ message: 'Invalid role' });
    }
};

const WithRoleProfessor = (req, res, next) => {
    const { user: { role } } = req.body;
    if (!role) { return res.status(401).json({ message: 'No role provided' }); }
    
    if (role === ROLES.ADMIN || role === ROLES.PROFESSOR) {
        next();
    } else { 
        res.status(401).json({ message: 'Invalid role' });
    }
};

module.exports.WithRoleAdmin = WithRoleAdmin;
module.exports.WithRoleStudent = WithRoleStudent;
module.exports.WithRoleProfessor = WithRoleProfessor;