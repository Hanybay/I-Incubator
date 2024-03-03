CREATE DATABASE proto1;

CREATE TABLE entreprise (
    identreprise INT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL
);


CREATE TABLE utilisateur (
    idutilisateur INT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    prenom VARCHAR(255) NOT NULL,
    identreprise INT,
    rh BOOLEAN,
    FOREIGN KEY (identreprise) REFERENCES entreprise(identreprise)
);


CREATE TABLE conversation (
    idconversation INT PRIMARY KEY,
    titre VARCHAR(255) NOT NULL
);


CREATE TABLE message (
    idmessage INT PRIMARY KEY,
    texte TEXT NOT NULL,
    idutilisateur INT,
    idconversation INT,
    date_message DATETIME NOT NULL,
    FOREIGN KEY (idutilisateur) REFERENCES utilisateur(idutilisateur),
    FOREIGN KEY (idconversation) REFERENCES conversation(idconversation)
);


INSERT INTO entreprise (identreprise, nom) VALUES
(1, 'Entreprise A'),
(2, 'Entreprise B');


INSERT INTO utilisateur (idutilisateur, nom, prenom, identreprise) VALUES
(1, 'Doe', 'John', 1),
(2, 'Smith', 'Alice', 1),
(3, 'Johnson', 'Bob', 2);


INSERT INTO conversation (idconversation, titre) VALUES
(1, 'Discussion générale'),
(2, 'Projet X Planning');

INSERT INTO message (idmessage, texte, idutilisateur, idconversation, date_message) VALUES
(1, 'Bonjour à tous !', 1, 1, NOW()),
(2, 'Quel est le statut du projet X ?', 2, 2, NOW()),
(3, 'Nous sommes en bonne voie pour le projet X.', 3, 2, NOW());
