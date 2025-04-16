-- Meetings table
CREATE TABLE meetings (
    meeting_key INTEGER PRIMARY KEY,
    meeting_name VARCHAR(255),
    location VARCHAR(255),
    country_name VARCHAR(255),
    circuit_short_name VARCHAR(255),
    date_start TIMESTAMP
);

-- Sessions table
CREATE TABLE sessions (
    session_key INTEGER PRIMARY KEY,
    meeting_key INTEGER,
    session_type VARCHAR(50),
    session_name VARCHAR(255),
    date_start TIMESTAMP,
    date_end TIMESTAMP,
    FOREIGN KEY (meeting_key) REFERENCES meetings(meeting_key)
);

-- Drivers table
CREATE TABLE drivers (
    driver_number INTEGER PRIMARY KEY,
    full_name VARCHAR(255),
    team_name VARCHAR(255),
    team_colour VARCHAR(50),
    country_code VARCHAR(10)
);

-- Laps table
CREATE TABLE laps (
    lap_id SERIAL PRIMARY KEY,
    session_key INTEGER,
    driver_number INTEGER,
    lap_number INTEGER,
    lap_duration FLOAT,
    sector_1_duration FLOAT,
    sector_2_duration FLOAT,
    sector_3_duration FLOAT,
    is_pit_out_lap BOOLEAN,
    date_start TIMESTAMP,
    FOREIGN KEY (session_key) REFERENCES sessions(session_key),
    FOREIGN KEY (driver_number) REFERENCES drivers(driver_number)
);

-- Car Data table
CREATE TABLE car_data (
    car_data_id SERIAL PRIMARY KEY,
    session_key INTEGER,
    driver_number INTEGER,
    date TIMESTAMP,
    rpm INTEGER,
    speed INTEGER,
    gear INTEGER,
    throttle FLOAT,
    brake FLOAT,
    drs BOOLEAN,
    x_position FLOAT,
    y_position FLOAT,
    z_position FLOAT,
    FOREIGN KEY (session_key) REFERENCES sessions(session_key),
    FOREIGN KEY (driver_number) REFERENCES drivers(driver_number)
);

-- Intervals table
CREATE TABLE intervals (
    interval_id SERIAL PRIMARY KEY,
    session_key INTEGER,
    driver_number INTEGER,
    gap_to_leader FLOAT,
    interval_to_ahead FLOAT,
    date TIMESTAMP,
    FOREIGN KEY (session_key) REFERENCES sessions(session_key),
    FOREIGN KEY (driver_number) REFERENCES drivers(driver_number)
);

-- Positions table
CREATE TABLE positions (
    position_id SERIAL PRIMARY KEY,
    session_key INTEGER,
    driver_number INTEGER,
    position INTEGER,
    date TIMESTAMP,
    FOREIGN KEY (session_key) REFERENCES sessions(session_key),
    FOREIGN KEY (driver_number) REFERENCES drivers(driver_number)
);