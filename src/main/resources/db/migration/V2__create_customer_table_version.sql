-- Füge Version-Spalte für Optimistic Locking hinzu
ALTER TABLE customer
    ADD version BIGINT NOT NULL DEFAULT 0;

-- Setze alle bestehenden Versionen auf 0 (falls Daten existieren)
UPDATE customer
SET version = 0
WHERE version IS NULL;