export interface LoginResult {
  access_token: string;
}

export interface Illustration {}

export interface Illustrations {
  illustrations: Illustration[];
}

export interface IllustrationAdd {
  file: FormData;
}

export interface Annonce {
  id: number;
  title: string;
  description: string;
  images: FormData;
  price: number;
}

export interface Annonces {
  annonces: Annonce[];
}

export interface AnnonceAdd {
  title: string;
  description: string;
  images: Illustration[];
  price: number;
}

export interface User {
  id: number;
  username: string;
  annonces: Annonce[];
}

export interface Users {
  users: User[];
}

export interface UserLogin {
  username: string;
  password: string;
}

// RESULTS
export interface UserResult {
  id: number;
  username: string;
  role: string[];
  annonces: AnnonceResult[];
}

export interface UserDisplay {
  id: number;
  username: string;
  role: string[];
  annonces: number;
  password?: string;
}

export interface UserToModify {
  username: string;
  role: string[];
}

export interface UserAnnonceIdResult {
  id: number;
}

export interface AnnonceResult {
  id: number;
  title: string;
  description: string;
  price: number;
  dateCreated: string;
  lastUpdated: string;
  images: ImageResult[];
}

export interface AnnonceDisplay {
  id: number;
  title: string;
  description: string;
  price: number;
  nbImage: number;
  images: FormData;
}

export interface ImageResult {
  filename: string;
  id: number;
}
