CREATE TABLE grade
(
    student_id    BIGINT NOT NULL REFERENCES student (id) ON DELETE CASCADE,
    discipline_id BIGINT NOT NULL REFERENCES discipline (id) ON DELETE CASCADE,
    value         INT NULL CHECK (value BETWEEN 0 AND 100),
    PRIMARY KEY (student_id, discipline_id)
);

CREATE INDEX idx_grade_discipline_id ON grade (discipline_id);
