---
--- Verifica delle credenziali
---
SELECT * FROM test_utente WHERE login = 'utente2' AND password_hash = 'mypass';

---
--- Caricamento delle authorithies
---
SELECT q1.* FROM (
  --- credenziali di login
  SELECT
    'CREDENTIALS' as grant_type,
    id, 
    login || '@PNDUAA' AS codice,
    nome || ' ' || cognome AS descrizione
  FROM
    test_utente WHERE id = 3 --- #{ID_UTENTE}
  UNION
  --- autorizzazioni associate (FLATTENED)
  SELECT 
    'AUTHORIZATION' as grant_type,
    id, 
    'ROLE_' || codice, 
    descrizione 
  FROM test_autorizzazione WHERE id IN (
    SELECT DISTINCT(id_autorizzazione) FROM test_r_gruppo_autorizzazione WHERE id_gruppo IN (
      WITH RECURSIVE gruppi(id_gruppo_padre, id_gruppo) AS (
          SELECT id_padre, id FROM test_gruppo WHERE id IN (
            SELECT id_gruppo FROM test_r_utente_gruppo r WHERE r.id_utente = (
              SELECT id FROM test_utente WHERE id = 3 --- #{ID_UTENTE}
            )
          )
		  UNION ALL
          SELECT p.id_padre, p.id FROM gruppi pr, test_gruppo p WHERE pr.id_gruppo_padre = p.id
        )
      SELECT DISTINCT(id_gruppo) FROM gruppi ORDER BY id_gruppo ASC
    )
  )
  UNION
  --- gruppi associati (FLATTENED)
  SELECT
    'GROUP' as grant_type,
    id, 
    'GROUP_' || codice as codice, 
    descrizione 
  FROM (
    SELECT id, codice, descrizione FROM test_gruppo WHERE id IN (
      WITH RECURSIVE gruppi(id_gruppo_padre, codice, id_gruppo) AS (
          SELECT id_padre, codice, id FROM test_gruppo WHERE id IN (
            SELECT id_gruppo FROM test_r_utente_gruppo r WHERE r.id_utente = (
              SELECT id FROM test_utente WHERE id = 3 --- #{ID_UTENTE}
            )
          )
		  UNION ALL 
		  SELECT p.id_padre, p.codice, p.id FROM gruppi pr, test_gruppo p WHERE pr.id_gruppo_padre = p.id
        )
      SELECT DISTINCT(id_gruppo) FROM gruppi ORDER BY id_gruppo ASC
    ) 
  ) q0
) q1
ORDER BY grant_type, id, codice, descrizione;
