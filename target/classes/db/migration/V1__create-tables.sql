
CREATE TABLE utilisateurs
(
    id          serial PRIMARY KEY,
    nom         varchar(100)
);


CREATE TABLE avis
(
    id          serial PRIMARY KEY,
    message     TEXT,
    utilisateur_id     INT,
    creation    timestamp NOT NULL DEFAULT NOW(),
    FOREIGN KEY (utilisateur_id) REFERENCES utilisateurs (id)
);
