INSERT INTO coach (id, first_name, last_name) VALUES (10, 'Coach', 'Best')
INSERT INTO athlete (id, coach_id, first_name, last_name) VALUES (10, 10, 'Triathlete', 'First')
INSERT INTO athlete (id, coach_id,  first_name, last_name) VALUES (11, 10, 'Runner', 'Second')
INSERT INTO athlete (id, coach_id,  first_name, last_name) VALUES (12, 10, 'Ultra-runner', 'Third')
INSERT INTO training_plan (coach_id, id, description, name, training_type, training_plan_status) VALUES (10, 10,'swim regeneration', 'constant 1000m', 'SWIM', 'TEMPLATE')
INSERT INTO training_plan (coach_id, id, description, name, training_type, training_plan_status) VALUES (10, 11,'swim hard', '10x500m hard', 'SWIM', 'TEMPLATE')
INSERT INTO training_plan (coach_id, id, description, name, training_type, training_plan_status) VALUES (10, 12,'light cycling', '40k', 'BIKE', 'TEMPLATE')
INSERT INTO training_plan (coach_id, id, description, name, training_type, training_plan_status) VALUES (10, 13,'1h gym', 'according to plan', 'WEIGHT', 'TEMPLATE')
INSERT INTO training_plan (coach_id, athlete_id, id, description, name, training_type, training_plan_status, planned_date) VALUES (10, 10, 14,'swim regeneration', 'constant 1000m', 'SWIM', 'PLANNED', '2024-01-15')
INSERT INTO training_plan (coach_id, athlete_id, id, description, name, training_type, training_plan_status, planned_date) VALUES (10, 11, 15,'swim hard', '10x500m hard', 'SWIM', 'PLANNED', '2024-01-16')
INSERT INTO training_plan (coach_id, athlete_id, id, description, name, training_type, training_plan_status, planned_date) VALUES (10, 12, 16,'light cycling', '40k', 'BIKE', 'PLANNED', '2024-01-17')
INSERT INTO training_plan (coach_id, athlete_id, id, description, name, training_type, training_plan_status, planned_date) VALUES (10, 12, 17,'1h gym', 'according to plan', 'WEIGHT', 'PLANNED', '2024-01-19')
INSERT INTO training_realization (id, athlete_id, realization_date, rpe_level, feelings, realization_description) VALUES (10, 10, '2024-01-10', 3, 'NORMAL', 'done as in the plan')
--INSERT INTO stage (id, sequence , training_plan_id, description, stage_type, heart_rate, distance_in_meters, time_in_seconds, power) VALUES (10, 1, 14, 'test stage', 'BIKE', 10, 10, 10, 10)
INSERT INTO stage (id, sequence , training_plan_id, description, stage_type, heart_rate, distance_in_meters, time_in_seconds, pace_in_seconds_per_km) VALUES (11, 1, 14, 'test stage', 'RUN', 10, 10, 10, 12)