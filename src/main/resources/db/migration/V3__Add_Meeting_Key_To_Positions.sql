-- Add meeting_key column to positions table
ALTER TABLE positions
    ADD COLUMN meeting_key INTEGER;

-- Populate meeting_key from sessions
UPDATE positions p
SET meeting_key = s.meeting_key
FROM sessions s
WHERE p.session_key = s.session_key;

-- Make meeting_key non-nullable (optional, ensure all rows have valid values)
ALTER TABLE positions
    ALTER COLUMN meeting_key SET NOT NULL;

-- Add foreign key constraint referencing meetings table
ALTER TABLE positions
    ADD CONSTRAINT fk_positions_meeting
    FOREIGN KEY (meeting_key)
    REFERENCES meetings (meeting_key);