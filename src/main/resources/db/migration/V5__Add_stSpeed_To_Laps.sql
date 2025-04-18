-- Add st_speed column to laps table
ALTER TABLE laps
    ADD COLUMN st_speed FLOAT;

-- Add st_speed column to laps table
ALTER TABLE positions
    DROP COLUMN st_speed;

