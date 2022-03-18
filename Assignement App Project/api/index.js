require('dotenv').config();
const express = require('express');
const bodyParser = require('body-parser')
const app = express();
const { PORT } = process.env;
const apiRoutes = require('./routes/api');
const assignmentsRoutes = require('./routes/Assignments/Assignments');
const matieresRoutes = require('./routes/Matiere/Matiere');
const cors = require('cors')
const { Database } = require('./lib/mongo/init');

app.use(bodyParser.urlencoded({ extended: false }))
app.use(bodyParser.json())
app.use(cors())

app.use('/api/v1', apiRoutes);
app.use('/api/v1/assignments', assignmentsRoutes);
app.use('/api/v1/matieres', matieresRoutes);

app.listen(PORT, () => {
  console.log(`API run on: ${PORT}!`)
});

Database.on('connected', () => console.log('MongoDB connected'));
Database.on('error', (err) => console.log('MongoDB error:', err));