-- Add i1_speed column to positions table
ALTER TABLE laps
    ADD COLUMN i1_speed FLOAT;

-- Add i2_speed column to laps table
ALTER TABLE laps
    ADD COLUMN i2_speed FLOAT;

-- Add st_speed column to laps table
ALTER TABLE positions
    ADD COLUMN st_speed FLOAT;

