const jwt = require('jsonwebtoken');
const { JWT_SECRET } = process.env;

const WithToken = (req, res, next) => {
    const token = req.headers['authorization'];
    if (!token) { return res.status(401).json({ message: 'No token provided' }); }

    const decoded = jwt.verify(token, JWT_SECRET);
    if (decoded) {
        req.body.user = decoded;
        next();
    } else {
        res.status(401).json({ message: 'Invalid token' });
    }
};

module.exports.WithToken = WithToken;