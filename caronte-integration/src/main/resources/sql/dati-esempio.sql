
INSERT INTO test_autorizzazione (codice, descrizione) 
VALUES 
  ('LOGIN', 'Accesso al sistema'),
  ('READ', 'Accesso in lettura'),
  ('WRITE', 'Accesso in scrittura')
;

INSERT INTO test_gruppo (id_padre, codice, descrizione) 
VALUES 
  (NULL, 'REGISTERED', 'Tutti gli utenti registrati')
;

INSERT INTO test_gruppo (id_padre, codice, descrizione) 
VALUES 
  (1, 'OBSERVER', 'Utenti consultatori')
;

INSERT INTO test_gruppo (id_padre, codice, descrizione) 
VALUES 
  (2, 'CONTRIBUTOR', 'Utenti contribuenti')
;

INSERT INTO test_gruppo (id_padre, codice, descrizione) 
VALUES 
  (1, 'ADMIN', 'Utenti amministratori')
;

INSERT INTO test_utente (login, password_hash, nome, cognome)
VALUES
  ('utente1', 'mypass', 'Utente', '1'),
  ('utente2', 'mypass', 'Utente', '2'),
  ('admin', 'mypass', 'Utente', 'Amministratore')
;

INSERT INTO test_r_utente_gruppo (id_utente, id_gruppo) 
VALUES 
  (1, 2),
  (2, 3),
  (3, 4)
;

INSERT INTO test_r_gruppo_autorizzazione (id_gruppo, id_autorizzazione) 
VALUES 
  (1, 1),
  (2, 2),
  (3, 3),
  (4, 2),
  (4, 3)
;