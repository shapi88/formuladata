// Entity Tables
Table continent {
  id varchar(100) [pk]
  code varchar(2) [unique, not null]
  name varchar(100) [unique, not null]
  demonym varchar(100) [not null]
}

Table country {
  id varchar(100) [pk]
  alpha2_code varchar(2) [unique, not null]
  alpha3_code varchar(3) [unique, not null]
  name varchar(100) [unique, not null]
  demonym varchar(100)
  continent_id varchar(100) [not null]
}

Table driver {
  id varchar(100) [pk]
  name varchar(100) [not null]
  first_name varchar(100) [not null]
  last_name varchar(100) [not null]
  full_name varchar(100) [not null]
  abbreviation varchar(3) [not null]
  permanent_number varchar(2)
  gender varchar(6) [not null]
  date_of_birth date [not null]
  date_of_death date
  place_of_birth varchar(100) [not null]
  country_of_birth_country_id varchar(100) [not null]
  nationality_country_id varchar(100) [not null]
  second_nationality_country_id varchar(100)
  best_championship_position integer
  best_starting_grid_position integer
  best_race_result integer
  total_championship_wins integer [not null]
  total_race_entries integer [not null]
  total_race_starts integer [not null]
  total_race_wins integer [not null]
  total_race_laps integer [not null]
  total_podiums integer [not null]
  total_points numeric(8,2) [not null]
  total_championship_points numeric(8,2) [not null]
  total_pole_positions integer [not null]
  total_fastest_laps integer [not null]
  total_driver_of_the_day integer [not null]
  total_grand_slams integer [not null]
}

Table constructor {
  id varchar(100) [pk]
  name varchar(100) [not null]
  full_name varchar(100) [not null]
  country_id varchar(100) [not null]
  best_championship_position integer
  best_starting_grid_position integer
  best_race_result integer
  total_championship_wins integer [not null]
  total_race_entries integer [not null]
  total_race_starts integer [not null]
  total_race_wins integer [not null]
  total_1_and_2_finishes integer [not null]
  total_race_laps integer [not null]
  total_podiums integer [not null]
  total_podium_races integer [not null]
  total_points numeric(8,2) [not null]
  total_championship_points numeric(8,2) [not null]
  total_pole_positions integer [not null]
  total_fastest_laps integer [not null]
}

Table engine_manufacturer {
  id varchar(100) [pk]
  name varchar(100) [not null]
  country_id varchar(100) [not null]
  best_championship_position integer
  best_starting_grid_position integer
  best_race_result integer
  total_championship_wins integer [not null]
  total_race_entries integer [not null]
  total_race_starts integer [not null]
  total_race_wins integer [not null]
  total_race_laps integer [not null]
  total_podiums integer [not null]
  total_podium_races integer [not null]
  total_points numeric(8,2) [not null]
  total_championship_points numeric(8,2) [not null]
  total_pole_positions integer [not null]
  total_fastest_laps integer [not null]
}

Table tyre_manufacturer {
  id varchar(100) [pk]
  name varchar(100) [not null]
  country_id varchar(100) [not null]
  best_starting_grid_position integer
  best_race_result integer
  total_race_entries integer [not null]
  total_race_starts integer [not null]
  total_race_wins integer [not null]
  total_race_laps integer [not null]
  total_podiums integer [not null]
  total_podium_races integer [not null]
  total_pole_positions integer [not null]
  total_fastest_laps integer [not null]
}

Table entrant {
  id varchar(100) [pk]
  name varchar(100) [not null]
}

Table chassis {
  id varchar(100) [pk]
  constructor_id varchar(100) [not null]
  name varchar(100) [not null]
  full_name varchar(100) [not null]
}

Table engine {
  id varchar(100) [pk]
  engine_manufacturer_id varchar(100) [not null]
  name varchar(100) [not null]
  full_name varchar(100) [not null]
  capacity numeric(2,1)
  configuration varchar(100)
  aspiration varchar(100)
}

Table grand_prix {
  id varchar(100) [pk]
  name varchar(100) [not null]
  full_name varchar(100) [not null]
  short_name varchar(100) [not null]
  abbreviation varchar(3) [not null]
  country_id varchar(100)
  total_races_held integer [not null]
}

Table circuit {
  id varchar(100) [pk]
  name varchar(100) [not null]
  full_name varchar(100) [not null]
  previous_names varchar(255)
  type varchar(6) [not null]
  direction varchar(14) [not null]
  place_name varchar(100) [not null]
  country_id varchar(100) [not null]
  latitude numeric(10,6) [not null]
  longitude numeric(10,6) [not null]
  length numeric(6,3) [not null]
  turns integer [not null]
  total_races_held integer [not null]
}

// Relationship Tables
Table driver_family_relationship {
  driver_id varchar(100) [pk, not null] 
  position_display_order integer [pk, not null]
  other_driver_id varchar(100) [not null]
  type varchar(50) [not null]
}

Table constructor_chronology {
  constructor_id varchar(100) [pk, not null]
  position_display_order integer [pk, not null]
  other_constructor_id varchar(100) [not null]
  year_from integer [not null]
  year_to integer
}

// Season Tables
Table season {
  year integer [pk]
}

Table season_entrant {
  year integer [pk, not null]
  entrant_id varchar(100) [pk, not null]
  country_id varchar(100) [not null]
}

Table season_entrant_constructor {
  year integer [pk, not null]
  entrant_id varchar(100) [pk, not null]
  constructor_id varchar(100) [pk, not null]
  engine_manufacturer_id varchar(100) [pk, not null]
}

Table season_entrant_chassis {
  year integer [pk, not null]
  entrant_id varchar(100) [pk, not null]
  constructor_id varchar(100) [pk, not null]
  engine_manufacturer_id varchar(100) [pk, not null]
  chassis_id varchar(100) [pk, not null]
}

Table season_entrant_engine {
  year integer [pk, not null]
  entrant_id varchar(100) [pk, not null]
  constructor_id varchar(100) [pk, not null]
  engine_manufacturer_id varchar(100) [pk, not null]
  engine_id varchar(100) [pk, not null]
}

Table season_entrant_tyre_manufacturer {
  year integer [pk, not null]
  entrant_id varchar(100) [pk, not null]
  constructor_id varchar(100) [pk, not null]
  engine_manufacturer_id varchar(100) [pk, not null]
  tyre_manufacturer_id varchar(100) [pk, not null]
}

Table season_entrant_driver {
  year integer [pk, not null]
  entrant_id varchar(100) [pk, not null]
  constructor_id varchar(100) [pk, not null]
  engine_manufacturer_id varchar(100) [pk, not null]
  driver_id varchar(100) [pk, not null]
  rounds varchar(100)
  rounds_text varchar(100)
  test_driver boolean [not null]
}

Table season_constructor {
  year integer [pk, not null]
  constructor_id varchar(100) [pk, not null]
  position_number integer
  position_text varchar(4)
  best_starting_grid_position integer
  best_race_result integer
  total_race_entries integer [not null]
  total_race_starts integer [not null]
  total_race_wins integer [not null]
  total_1_and_2_finishes integer [not null]
  total_race_laps integer [not null]
  total_podiums integer [not null]
  total_podium_races integer [not null]
  total_points numeric(8,2) [not null]
  total_pole_positions integer [not null]
  total_fastest_laps integer [not null]
}

Table season_driver {
  year integer [pk, not null]
  driver_id varchar(100) [pk, not null]
  position_number integer
  position_text varchar(4)
  best_starting_grid_position integer
  best_race_result integer
  total_race_entries integer [not null]
  total_race_starts integer [not null]
  total_race_wins integer [not null]
  total_race_laps integer [not null]
  total_podiums integer [not null]
  total_points numeric(8,2) [not null]
  total_pole_positions integer [not null]
  total_fastest_laps integer [not null]
  total_driver_of_the_day integer [not null]
  total_grand_slams integer [not null]
}

Table season_engine_manufacturer {
  year integer [pk, not null]
  engine_manufacturer_id varchar(100) [pk, not null]
  position_number integer
  position_text varchar(4)
  best_starting_grid_position integer
  best_race_result integer
  total_race_entries integer [not null]
  total_race_starts integer [not null]
  total_race_wins integer [not null]
  total_race_laps integer [not null]
  total_podiums integer [not null]
  total_podium_races integer [not null]
  total_points numeric(8,2) [not null]
  total_pole_positions integer [not null]
  total_fastest_laps integer [not null]
}

Table season_tyre_manufacturer {
  year integer [pk, not null]
  tyre_manufacturer_id varchar(100) [pk, not null]
  best_starting_grid_position integer
  best_race_result integer
  total_race_entries integer [not null]
  total_race_starts integer [not null]
  total_race_wins integer [not null]
  total_race_laps integer [not null]
  total_podiums integer [not null]
  total_podium_races integer [not null]
  total_pole_positions integer [not null]
  total_fastest_laps integer [not null]
}

Table season_constructor_standing {
  year integer [pk, not null]
  position_display_order integer [pk, not null]
  position_number integer
  position_text varchar(4) [not null]
  constructor_id varchar(100) [not null]
  engine_manufacturer_id varchar(100) [not null]
  points numeric(8,2) [not null]
}

Table season_driver_standing {
  year integer [pk, not null]
  position_display_order integer [pk, not null]
  position_number integer
  position_text varchar(4) [not null]
  driver_id varchar(100) [not null]
  points numeric(8,2) [not null]
}

// Race Tables
Table race {
  id integer [pk]
  year integer [pk, not null]
  round integer [pk, not null]
  date date [not null]
  time varchar
  grand_prix_id varchar(100) [not null]
  official_name varchar(100) [not null]
  qualifying_format varchar(20) [not null]
  sprint_qualifying_format varchar(20)
  circuit_id varchar(100) [not null]
  circuit_type varchar(6) [not null]
  direction varchar(14) [not null]
  course_length numeric(6,3) [not null]
  turns integer [not null]
  laps integer [not null]
  distance numeric(6,3) [not null]
  scheduled_laps integer
  scheduled_distance numeric(6,3)
  drivers_championship_decider boolean
  constructors_championship_decider boolean
  pre_qualifying_date date
  pre_qualifying_time varchar(5)
  free_practice_1_date date
  free_practice_1_time varchar(5)
  free_practice_2_date date
  free_practice_2_time varchar(5)
  free_practice_3_date date
  free_practice_3_time varchar(5)
  free_practice_4_date date
  free_practice_4_time varchar(5)
  qualifying_1_date date
  qualifying_1_time varchar(5)
  qualifying_2_date date
  qualifying_2_time varchar(5)
  qualifying_date date
  qualifying_time varchar(5)
  sprint_qualifying_date date
  sprint_qualifying_time varchar(5)
  sprint_race_date date
  sprint_race_time varchar(5)
  warming_up_date date
  warming_up_time varchar(5)
}

Table race_data {
  race_id integer [pk, not null]
  type varchar(50) [pk, not null]
  position_display_order integer [pk, not null]
  position_number integer
  position_text varchar(4) [not null]
  driver_number varchar(3) [not null]
  driver_id varchar(100) [not null]
  constructor_id varchar(100) [not null]
  engine_manufacturer_id varchar(100) [not null]
  tyre_manufacturer_id varchar(100) [not null]
  practice_time varchar(20)
  practice_time_millis integer
  practice_gap varchar(20)
  practice_gap_millis integer
  practice_interval varchar(20)
  practice_interval_millis integer
  practice_laps integer
  qualifying_time varchar(20)
  qualifying_time_millis integer
  qualifying_q1 varchar(20)
  qualifying_q1_millis integer
  qualifying_q2 varchar(20)
  qualifying_q2_millis integer
  qualifying_q3 varchar(20)
  qualifying_q3_millis integer
  qualifying_gap varchar(20)
  qualifying_gap_millis integer
  qualifying_interval varchar(20)
  qualifying_interval_millis integer
  qualifying_laps integer
  starting_grid_position_qualification_position_number integer
  starting_grid_position_qualification_position_text varchar(4)
  starting_grid_position_grid_penalty varchar(20)
  starting_grid_position_grid_penalty_positions integer
  starting_grid_position_time varchar(20)
  starting_grid_position_time_millis integer
  race_shared_car boolean
  race_laps integer
  race_time varchar(20)
  race_time_millis integer
  race_time_penalty varchar(20)
  race_time_penalty_millis integer
  race_gap varchar(20)
  race_gap_millis integer
  race_gap_laps integer
  race_interval varchar(20)
  race_interval_millis integer
  race_reason_retired varchar(100)
  race_points numeric(8,2)
  race_pole_position boolean
  race_qualification_position_number integer
  race_qualification_position_text varchar(4)
  race_grid_position_number integer
  race_grid_position_text varchar(2)
  race_positions_gained integer
  race_pit_stops integer
  race_fastest_lap boolean
  race_driver_of_the_day boolean
  race_grand_slam boolean
  fastest_lap_lap integer
  fastest_lap_time varchar(20)
  fastest_lap_time_millis integer
  fastest_lap_gap varchar(20)
  fastest_lap_gap_millis integer
  fastest_lap_interval varchar(20)
  fastest_lap_interval_millis integer
  pit_stop_stop integer
  pit_stop_lap integer
  pit_stop_time varchar(20)
  pit_stop_time_millis integer
  driver_of_the_day_percentage numeric(4,1)
}

Table race_constructor_standing {
  race_id integer [pk,not null]
  position_display_order integer [pk,not null]
  position_number integer
  position_text varchar(4) [not null]
  constructor_id varchar(100) [not null]
  engine_manufacturer_id varchar(100) [not null]
  points numeric(8,2) [not null]
  positions_gained integer
}

Table race_driver_standing {
  race_id integer [pk, not null]
  position_display_order integer [pk, not null]
  position_number integer
  position_text varchar(4) [not null]
  driver_id varchar(100) [not null]
  points numeric(8,2) [not null]
  positions_gained integer
}

// Utility Table
Table flyway_schema_history {
  installed_rank integer [pk]
  version varchar(50)
  description varchar(200) [not null]
  type varchar(20) [not null]
  script varchar(1000) [not null]
  checksum integer
  installed_by varchar(100) [not null]
  installed_on timestamp [not null]
  execution_time integer [not null]
  success boolean [not null]
}

// Foreign Key Relationships
Ref: country.continent_id > continent.id
Ref: driver.country_of_birth_country_id > country.id
Ref: driver.nationality_country_id > country.id
Ref: driver.second_nationality_country_id > country.id
Ref: constructor.country_id > country.id
Ref: engine_manufacturer.country_id > country.id
Ref: tyre_manufacturer.country_id > country.id
Ref: grand_prix.country_id > country.id
Ref: circuit.country_id > country.id
Ref: chassis.constructor_id > constructor.id
Ref: engine.engine_manufacturer_id > engine_manufacturer.id
Ref: driver_family_relationship.driver_id > driver.id
Ref: driver_family_relationship.other_driver_id > driver.id
Ref: constructor_chronology.constructor_id > constructor.id
Ref: constructor_chronology.other_constructor_id > constructor.id
Ref: season_entrant.year > season.year
Ref: season_entrant.entrant_id > entrant.id
Ref: season_entrant.country_id > country.id
Ref: season_entrant_constructor.year > season.year
Ref: season_entrant_constructor.entrant_id > entrant.id
Ref: season_entrant_constructor.constructor_id > constructor.id
Ref: season_entrant_constructor.engine_manufacturer_id > engine_manufacturer.id
Ref: season_entrant_chassis.year > season.year
Ref: season_entrant_chassis.entrant_id > entrant.id
Ref: season_entrant_chassis.constructor_id > constructor.id
Ref: season_entrant_chassis.engine_manufacturer_id > engine_manufacturer.id
Ref: season_entrant_chassis.chassis_id > chassis.id
Ref: season_entrant_engine.year > season.year
Ref: season_entrant_engine.entrant_id > entrant.id
Ref: season_entrant_engine.constructor_id > constructor.id
Ref: season_entrant_engine.engine_manufacturer_id > engine_manufacturer.id
Ref: season_entrant_engine.engine_id > engine.id
Ref: season_entrant_tyre_manufacturer.year > season.year
Ref: season_entrant_tyre_manufacturer.entrant_id > entrant.id
Ref: season_entrant_tyre_manufacturer.constructor_id > constructor.id
Ref: season_entrant_tyre_manufacturer.engine_manufacturer_id > engine_manufacturer.id
Ref: season_entrant_tyre_manufacturer.tyre_manufacturer_id > tyre_manufacturer.id
Ref: season_entrant_driver.year > season.year
Ref: season_entrant_driver.entrant_id > entrant.id
Ref: season_entrant_driver.constructor_id > constructor.id
Ref: season_entrant_driver.engine_manufacturer_id > engine_manufacturer.id
Ref: season_entrant_driver.driver_id > driver.id
Ref: season_constructor.year > season.year
Ref: season_constructor.constructor_id > constructor.id
Ref: season_driver.year > season.year
Ref: season_driver.driver_id > driver.id
Ref: season_engine_manufacturer.year > season.year
Ref: season_engine_manufacturer.engine_manufacturer_id > engine_manufacturer.id
Ref: season_tyre_manufacturer.year > season.year
Ref: season_tyre_manufacturer.tyre_manufacturer_id > tyre_manufacturer.id
Ref: season_constructor_standing.year > season.year
Ref: season_constructor_standing.constructor_id > constructor.id
Ref: season_constructor_standing.engine_manufacturer_id > engine_manufacturer.id
Ref: season_driver_standing.year > season.year
Ref: season_driver_standing.driver_id > driver.id
Ref: race.year > season.year
Ref: race.grand_prix_id > grand_prix.id
Ref: race.circuit_id > circuit.id
Ref: race_data.race_id > race.id
Ref: race_data.driver_id > driver.id
Ref: race_data.constructor_id > constructor.id
Ref: race_data.engine_manufacturer_id > engine_manufacturer.id
Ref: race_data.tyre_manufacturer_id > tyre_manufacturer.id
Ref: race_constructor_standing.race_id > race.id
Ref: race_constructor_standing.constructor_id > constructor.id
Ref: race_constructor_standing.engine_manufacturer_id > engine_manufacturer.id
Ref: race_driver_standing.race_id > race.id
Ref: race_driver_standing.driver_id > driver.id