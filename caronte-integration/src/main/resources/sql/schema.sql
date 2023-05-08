
CREATE TABLE test_utente
(
   id               bigserial NOT NULL,	--- PK
   login            varchar(255) NOT NULL, --- UNIQUE
   password_hash    varchar(255) NOT NULL,
   nome 			varchar(255) NOT NULL,
   cognome 			varchar(255) NOT NULL
);

ALTER TABLE test_utente ADD CONSTRAINT pk_test_utente PRIMARY KEY (id);

ALTER TABLE test_utente ADD CONSTRAINT unique_test_utente_login UNIQUE (login);
   
CREATE TABLE test_gruppo
(
   id             bigserial NOT NULL,	--- PK
   id_padre       integer,	--- FK
   codice         varchar(255) NOT NULL, --- UNIQUE
   descrizione    varchar(255) NOT NULL
);

ALTER TABLE test_gruppo ADD CONSTRAINT pk_test_gruppo PRIMARY KEY (id);

ALTER TABLE test_gruppo ADD CONSTRAINT unique_test_gruppo_codice UNIQUE (codice);

ALTER TABLE test_gruppo ADD CONSTRAINT fk_test_gruppo_id_padre
	FOREIGN KEY (id_padre) REFERENCES test_gruppo (id)
	ON UPDATE NO ACTION ON DELETE NO ACTION;
  
CREATE TABLE test_autorizzazione
(
   id             bigserial NOT NULL,	--- PK
   codice         varchar(255) NOT NULL, --- UNIQUE
   descrizione    varchar(255) NOT NULL
);
ALTER TABLE test_autorizzazione ADD CONSTRAINT pk_test_autorizzazione PRIMARY KEY (id);

ALTER TABLE test_autorizzazione ADD CONSTRAINT unique_test_autorizzazione_codice UNIQUE (codice);

CREATE TABLE test_r_utente_gruppo
(
   id_utente integer NOT NULL,	--- FK
   id_gruppo integer NOT NULL	--- FK
);

ALTER TABLE test_r_utente_gruppo ADD CONSTRAINT fk_test_r_utente_gruppo_id_utente
	FOREIGN KEY (id_utente) REFERENCES test_utente (id)
	ON UPDATE NO ACTION ON DELETE NO ACTION;
  
ALTER TABLE test_r_utente_gruppo ADD CONSTRAINT fk_test_r_utente_gruppo_id_gruppo
	FOREIGN KEY (id_gruppo) REFERENCES test_gruppo (id)
	ON UPDATE NO ACTION ON DELETE NO ACTION;
  

CREATE TABLE test_r_gruppo_autorizzazione
(
   id_gruppo integer NOT NULL,	--- FK
   id_autorizzazione integer NOT NULL	--- FK
);

ALTER TABLE test_r_gruppo_autorizzazione ADD CONSTRAINT fk_test_r_gruppo_autorizzazione_id_gruppo
	FOREIGN KEY (id_gruppo) REFERENCES test_gruppo (id)
	ON UPDATE NO ACTION ON DELETE NO ACTION;
  
ALTER TABLE test_r_gruppo_autorizzazione ADD CONSTRAINT fk_test_r_gruppo_autorizzazione_id_autorizzazione
	FOREIGN KEY (id_autorizzazione) REFERENCES test_autorizzazione (id)
	ON UPDATE NO ACTION ON DELETE NO ACTION;
  
CREATE TABLE test_token_login (
    username VARCHAR(64) NOT NULL,
    series VARCHAR(64) NOT NULL,
    token VARCHAR(64) NOT NULL,
    last_used TIMESTAMP NOT NULL
);