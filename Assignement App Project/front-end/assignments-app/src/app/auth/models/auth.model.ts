export interface LoginResult {
  token: string;
}

export enum Role {
  Etu = 'Etudiant',
  Prof = 'Professeur',
  Admin = 'Admin',
}

export interface User {
  name: string;
  email: string;
  role: Role;
  url: string;
}

export interface UserResult {
  user: User;
}
