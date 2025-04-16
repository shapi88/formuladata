-- Update meetings table
ALTER TABLE meetings
    ALTER COLUMN date_start TYPE TIMESTAMP WITH TIME ZONE;

-- Update sessions table
ALTER TABLE sessions
    ALTER COLUMN date_start TYPE TIMESTAMP WITH TIME ZONE,
    ALTER COLUMN date_end TYPE TIMESTAMP WITH TIME ZONE;

-- Update laps table
ALTER TABLE laps
    ALTER COLUMN date_start TYPE TIMESTAMP WITH TIME ZONE;

-- Update car_data table
ALTER TABLE car_data
    ALTER COLUMN date TYPE TIMESTAMP WITH TIME ZONE;

-- Update intervals table
ALTER TABLE intervals
    ALTER COLUMN date TYPE TIMESTAMP WITH TIME ZONE;

-- Update positions table
ALTER TABLE positions
    ALTER COLUMN date TYPE TIMESTAMP WITH TIME ZONE;