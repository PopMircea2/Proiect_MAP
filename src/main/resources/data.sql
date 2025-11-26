/* ------------------ CLEANUP ------------------ */
-- Disable FK checks so we can clear tables in any order
SET FOREIGN_KEY_CHECKS = 0;

DELETE FROM StaffAssignment;
DELETE FROM Ticket;
DELETE FROM SupportStaff;
DELETE FROM TechnicalOperator;
DELETE FROM Staff;
DELETE FROM Customer;
DELETE FROM Screening;
DELETE FROM Seat;
DELETE FROM Movie;
DELETE FROM Hall;
DELETE FROM Theatre;

-- Re-enable FK checks
SET FOREIGN_KEY_CHECKS = 1;


/* ------------------ THEATRE (10 rows) ------------------ */
INSERT INTO Theatre (Id, Name, City) VALUES
 ('theatre-001', 'Central Cinema', 'Bucharest'),
 ('theatre-002', 'Galaxy Theatre', 'Cluj-Napoca'),
 ('theatre-003', 'Starplex', 'Timisoara'),
 ('theatre-004', 'MovieWorld', 'Iasi'),
 ('theatre-005', 'Royal Screens', 'Constanta'),
 ('theatre-006', 'CinemaCity', 'Brasov'),
 ('theatre-007', 'MegaCine', 'Oradea'),
 ('theatre-008', 'DreamCinema', 'Sibiu'),
 ('theatre-009', 'LuxCinema', 'Arad'),
 ('theatre-010', 'Nova Theatre', 'Pitesti');

/* ------------------ HALL (10 rows) ------------------ */
INSERT INTO Hall (Id, Name, TheatreId, Capacity) VALUES
('hall-001', 'Hall A', 'theatre-001', 120),
('hall-002', 'Hall B', 'theatre-002', 150),
('hall-003', 'Premium Hall', 'theatre-003', 200),
('hall-004', 'VIP Hall', 'theatre-004', 80),
('hall-005', 'Standard Hall', 'theatre-005', 100),
('hall-006', 'Large Hall', 'theatre-006', 220),
('hall-007', 'Family Hall', 'theatre-007', 130),
('hall-008', 'IMAX Hall', 'theatre-008', 260),
('hall-009', 'Silver Hall', 'theatre-009', 95),
('hall-010', 'Golden Hall', 'theatre-010', 175);

/* ------------------ MOVIE (10 rows) ------------------ */
INSERT INTO Movie (Id, Title, DurationMin, Rating) VALUES
('movie-001', 'Inception', 148, 9.1),
('movie-002', 'Interstellar', 169, 9.0),
('movie-003', 'The Dark Knight', 152, 9.2),
('movie-004', 'Dune', 155, 8.6),
('movie-005', 'Avatar', 162, 8.0),
('movie-006', 'Matrix', 136, 8.7),
('movie-007', 'Gladiator', 155, 8.5),
('movie-008', 'Titanic', 195, 7.9),
('movie-009', 'Joker', 122, 8.4),
('movie-010', 'Blade Runner 2049', 164, 8.1);

/* ------------------ SEAT (10 rows) ------------------ */
INSERT INTO Seat (Id, HallId, RowLabel, ColumnNumber) VALUES
('seat-001', 'hall-001', 'A', 1),
('seat-002', 'hall-002', 'B', 5),
('seat-003', 'hall-003', 'C', 10),
('seat-004', 'hall-004', 'A', 3),
('seat-005', 'hall-005', 'D', 7),
('seat-006', 'hall-006', 'E', 12),
('seat-007', 'hall-007', 'F', 4),
('seat-008', 'hall-008', 'G', 9),
('seat-009', 'hall-009', 'A', 2),
('seat-010', 'hall-010', 'C', 6);

/* ------------------ SCREENING (10 rows) ------------------ */
INSERT INTO Screening (Id, HallId, MovieId, Date) VALUES
('screening-001', 'hall-001', 'movie-001', '2025-12-01 19:30:00'),
('screening-002', 'hall-002', 'movie-002', '2025-12-02 20:00:00'),
('screening-003', 'hall-003', 'movie-003', '2025-12-03 18:00:00'),
('screening-004', 'hall-004', 'movie-004', '2025-12-04 21:00:00'),
('screening-005', 'hall-005', 'movie-005', '2025-12-05 17:00:00'),
('screening-006', 'hall-006', 'movie-006', '2025-12-06 16:00:00'),
('screening-007', 'hall-007', 'movie-007', '2025-12-07 19:00:00'),
('screening-008', 'hall-008', 'movie-008', '2025-12-08 20:30:00'),
('screening-009', 'hall-009', 'movie-009', '2025-12-09 18:30:00'),
('screening-010', 'hall-010', 'movie-010', '2025-12-10 21:15:00');

/* ------------------ CUSTOMER (10 rows) ------------------ */
INSERT INTO Customer (Id, Name, BirthDate) VALUES
('customer-001', 'John Doe', '1990-05-14'),
('customer-002', 'Emily Carter', '1995-03-22'),
('customer-003', 'Michael Brown', '1988-11-10'),
('customer-004', 'Sophia Wilson', '2000-07-07'),
('customer-005', 'Daniel Evans', '1983-09-29'),
('customer-006', 'Laura Green', '1991-01-15'),
('customer-007', 'Patrick Lewis', '1979-06-24'),
('customer-008', 'Olivia Turner', '1998-09-12'),
('customer-009', 'Ryan Cooper', '1986-03-04'),
('customer-010', 'Chloe Martin', '1993-10-20');

/* ------------------ STAFF (10 rows: 5 support, 5 technical) ------------------ */
INSERT INTO Staff (Id, Name, StaffType, BirthDate, hourlyRate) VALUES
('staff-001', 'Alice Smith', 'support', '1985-08-10', 22.50),
('staff-002', 'Brian Taylor', 'technical', '1990-02-18', 30.00),
('staff-003', 'Clara Johnson', 'support', '1997-12-03', 20.75),
('staff-004', 'David Harris', 'technical', '1982-05-21', 32.00),
('staff-005', 'Eva Martinez', 'support', '1993-04-08', 21.00),
('staff-006', 'Frank Miller', 'technical', '1987-11-19', 29.50),
('staff-007', 'Grace Lee', 'support', '1994-06-14', 19.80),
('staff-008', 'Henry White', 'technical', '1981-04-22', 33.25),
('staff-009', 'Isabella Moore', 'support', '1999-02-10', 18.90),
('staff-010', 'Jack Robinson', 'technical', '1989-08-30', 31.40);

/* ------------------ SUPPORT STAFF (5 support workers) ------------------ */
INSERT INTO SupportStaff (StaffId, Role) VALUES
 ('staff-001', 'Usher'),
 ('staff-003', 'Cleaning'),
 ('staff-005', 'Security'),
 ('staff-007', 'Usher'),
 ('staff-009', 'Cleaning');

/* ------------------ TECHNICAL OPERATOR (5 technical workers) ------------------ */
INSERT INTO TechnicalOperator (StaffId, Specialization) VALUES
('staff-002', 'Projection'),
('staff-004', 'Sound'),
('staff-006', 'Projection'),
('staff-008', 'Sound'),
('staff-010', 'Projection');

/* ------------------ TICKET (10 rows) ------------------ */
INSERT INTO Ticket (Id, ScreeningId, CustomerId, SeatId, Price) VALUES
('ticket-001', 'screening-001', 'customer-001', 'seat-001', 35.00),
('ticket-002', 'screening-002', 'customer-002', 'seat-002', 40.00),
('ticket-003', 'screening-003', 'customer-003', 'seat-003', 37.50),
('ticket-004', 'screening-004', 'customer-004', 'seat-004', 45.00),
('ticket-005', 'screening-005', 'customer-005', 'seat-005', 32.00),
('ticket-006', 'screening-006', 'customer-006', 'seat-006', 38.00),
('ticket-007', 'screening-007', 'customer-007', 'seat-007', 34.50),
('ticket-008', 'screening-008', 'customer-008', 'seat-008', 44.00),
('ticket-009', 'screening-009', 'customer-009', 'seat-009', 31.90),
('ticket-010', 'screening-010', 'customer-010', 'seat-010', 46.25);

/* ------------------ STAFF ASSIGNMENT (10 rows) ------------------ */
INSERT INTO StaffAssignment (Id, ScreeningId, StaffId) VALUES
('assign-001', 'screening-001', 'staff-001'),
('assign-002', 'screening-002', 'staff-002'),
('assign-003', 'screening-003', 'staff-003'),
('assign-004', 'screening-004', 'staff-004'),
('assign-005', 'screening-005', 'staff-005'),
('assign-006', 'screening-006', 'staff-006'),
('assign-007', 'screening-007', 'staff-007'),
('assign-008', 'screening-008', 'staff-008'),
('assign-009', 'screening-009', 'staff-009'),
('assign-010', 'screening-010', 'staff-010');