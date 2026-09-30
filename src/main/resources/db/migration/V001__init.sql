CREATE TABLE department
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE student
(
    id            BIGSERIAL PRIMARY KEY,
    name          VARCHAR NOT NULL,
    active        BOOLEAN NOT NULL DEFAULT FALSE,
    department_id BIGINT REFERENCES department (id)
);

CREATE INDEX idx_student_department_id ON student (department_id);

CREATE TABLE course
(
    id       BIGSERIAL PRIMARY KEY,
    name     VARCHAR NOT NULL,
    active   BOOLEAN NOT NULL DEFAULT FALSE,
    start_at TIMESTAMPTZ,
    end_at   TIMESTAMPTZ
);

CREATE TABLE discipline
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR NOT NULL
);
