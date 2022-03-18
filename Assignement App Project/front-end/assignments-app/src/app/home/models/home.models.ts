export interface Assignement {
  _id: string;
  title: string;
  date: Date;
  reviewed: boolean;
  description: string;
  grade: number;
  comments: string;
  author: User;
  matiere: Matiere;
}

export interface AssignementResult {
  assignments: Assignement[];
  count: number;
}

export interface AssignementResultPost {
  _id: string;
  title: string;
  date: Date;
  reviewed: boolean;
  description: string;
  author: string;
  matiere: string;
}

export interface Matiere {
  _id: string;
  nom: string;
  logo: string;
  professor: User;
  description: string;
}

export interface CreateAssignement {
  matiere_nom: string;
  matiere_img: string;
  prof_name: string;
}

export interface User {
  _id: string;
  name: string;
  email: string;
  role: string;
  avatar: string;
}

export interface AssignementCard {
  _id: string;
  title: string;
  date: Date | string;
  rendu: boolean;
  note: number;
  auteur_name: string;
  auteur_role: string;
  matiere_name: string;
  matiere_logo: string;
  prof_avatar: string;
  desc: string;
}

export interface AssignementCardLists {
  listReviewed: AssignementCard[];
  listNotReviewed: AssignementCard[];
  count: number;
}

export enum Matiere_E {
  BDD = 'Base de données',
  GRAILS = 'Grails',
  ANDROID = 'Android',
  GESTION = 'Gestion de projet',
  NFC = 'NFC',
}

export interface Remarque {
  auteur: string;
  contenu: string;
}
