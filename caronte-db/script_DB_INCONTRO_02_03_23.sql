
-- Tabella per il versioning delle domande
CREATE TABLE caronte.car_d_versione_domanda (
	id_versione_domanda serial4 NOT NULL,
	data_inizio_vesione timestamptz NOT NULL,
	data_fine_versione timestamptz NULL,
	note varchar(500) NULL,
	CONSTRAINT pk_d_versione_domanda PRIMARY KEY (id_versione_domanda)
);

-- ATTENZIONE : INSERIRE LE DATE CORRETTE PER DATA_FINE_VERSIONE e DATA_INIZIO_VERSIONE in PRODUZIONE
INSERT INTO caronte.car_d_versione_domanda
(id_versione_domanda, data_inizio_vesione, data_fine_versione, note)
VALUES(1, '2020-01-01 00:00:00.000', '2023-04-13 01:00:00.000', 'Gestione versioning Domande pre modifiche richieste in Incontro_02_03_23');
INSERT INTO caronte.car_d_versione_domanda
(id_versione_domanda, data_inizio_vesione, data_fine_versione, note)
VALUES(2, '2023-04-14 01:00:00.000', NULL, 'Gestione versioning Domande con le modifiche richieste in Incontro_02_03_23');


-- AGGIUNTO CAMPO DATA DA VISUALIZZARE NEL TAB GESTIONE DOMANDA PASSAPORTO
ALTER TABLE caronte.car_t_domanda ADD data_autorizzazione_passaporto timestamptz NULL;

-- AGGIUNTA COLONNA CON LA DESCRIZIONE DELLO STATO DEL CENTRO AZIENDALE
ALTER TABLE caronte.car_d_stato_azienda ADD desc_centro_az varchar NULL;
COMMENT ON COLUMN caronte.car_d_stato_azienda.desc_centro_az IS 'descrizione stato associato al centro aziendale';

-- Auto-generated SQL script #202304201436
UPDATE caronte.car_d_stato_azienda
	SET desc_centro_az='Sospeso'
	WHERE id_stato_azienda=2;
UPDATE caronte.car_d_stato_azienda
	SET desc_centro_az='Revocato'
	WHERE id_stato_azienda=3;
UPDATE caronte.car_d_stato_azienda
	SET desc_centro_az='Cancellato'
	WHERE id_stato_azienda=4;
UPDATE caronte.car_d_stato_azienda
	SET desc_centro_az='Attivo'
	WHERE id_stato_azienda=1;
	
	
-- AGGIUNTE COLONNE LEGATE ALLA DOMANDA ED AL CENTRO AZIENDALE PER IL TAB GESTIONE

ALTER TABLE caronte.car_r_domanda_centro_az ADD id_ispettore int4 NULL;
ALTER TABLE caronte.car_r_domanda_centro_az ADD CONSTRAINT fk_r_domanda_centro_az_03 FOREIGN KEY (id_ispettore) REFERENCES caronte.car_t_ispettore(id_ispettore);

ALTER TABLE caronte.car_r_domanda_centro_az ADD codice_fitok varchar(20) NULL;



-- AGGIUNTA COLONNA PER LA TIPOLOGIA PASSAPORTO VISUALIZZATA NEL TAB GESTIONE DELLA DOMANDA PASSASPORTO
ALTER TABLE caronte.car_t_domanda ADD id_tipologia_passaporto int4 NULL DEFAULT null;
ALTER TABLE caronte.car_t_domanda ADD CONSTRAINT fk_t_domanda_10 FOREIGN KEY (id_tipologia_passaporto) REFERENCES caronte.car_d_tipologia_passaporto(id_tipologia_passaporto);


--Creazione nuova tabella CAR_T_ITER_TARFFA_SPED
CREATE TABLE caronte.car_t_iter_tariffa_sped (
	id_iter_tariffa_sped bigserial NOT NULL,
	tariffa numeric(12, 3) NOT NULL,
	id_domanda int8 NULL,
	id_spedizioniere int8 NOT NULL,
	data_inizio timestamptz NOT NULL,
	data_fine timestamptz NULL
);
ALTER TABLE caronte.car_t_iter_tariffa_sped ADD CONSTRAINT pk_t_iter_tariffa PRIMARY KEY (id_iter_tariffa_sped);
ALTER TABLE caronte.car_t_iter_tariffa_sped ADD CONSTRAINT fk_t_iter_tariffa_01 FOREIGN KEY (id_domanda) REFERENCES caronte.car_t_domanda(id_domanda);
ALTER TABLE caronte.car_t_iter_tariffa_sped ADD CONSTRAINT fk_t_iter_tariffa_02 FOREIGN KEY (id_spedizioniere) REFERENCES caronte.car_t_spedizioniere(id_spedizioniere);

--Creazione nuova tabella CAR_T_ITER_TIPO PASSAPORTO:
CREATE TABLE caronte.car_t_iter_tipo_passaporto (
	id_iter_tipo_passaporto bigserial NOT NULL,
	id_tipologia_passaporto int8 NOT NULL,
	id_domanda int8 NULL,
	id_centro_aziendale int8 NULL,
	data_inizio timestamptz NOT NULL,
	data_fine timestamptz NULL
);

ALTER TABLE caronte.car_t_iter_tipo_passaporto ADD CONSTRAINT pk_t_iter_tipo_passaporto PRIMARY KEY (id_iter_tipo_passaporto);
ALTER TABLE caronte.car_t_iter_tipo_passaporto ADD CONSTRAINT fk_t_iter_tipo_passaporto_01 FOREIGN KEY (id_tipologia_passaporto) REFERENCES caronte.car_d_tipologia_passaporto(id_tipologia_passaporto);
ALTER TABLE caronte.car_t_iter_tipo_passaporto ADD CONSTRAINT fk_t_iter_tipo_passaporto_02 FOREIGN KEY (id_domanda) REFERENCES caronte.car_t_domanda(id_domanda);
ALTER TABLE caronte.car_t_iter_tipo_passaporto ADD CONSTRAINT fk_t_iter_tipo_passaporto_03 FOREIGN KEY (id_centro_aziendale) REFERENCES caronte.car_t_centro_aziendale(id_centro_aziendale);


