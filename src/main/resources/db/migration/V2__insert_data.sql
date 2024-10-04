

INSERT INTO utilisateurs (nom)
VALUES ('Achille');

INSERT INTO avis (message, utilisateur_id) VALUES ('Comprendre SPRING', (SELECT id FROM utilisateurs WHERE nom = 'Achille'));


INSERT INTO utilisateurs (nom)
VALUES ('MBOUGUENG');

INSERT INTO avis (message, utilisateur_id) VALUES ('Bien débuter', (SELECT id FROM utilisateurs WHERE nom = 'MBOUGUENG'));
