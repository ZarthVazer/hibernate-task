CREATE TABLE discipline_course
(
    discipline_id BIGINT NOT NULL REFERENCES discipline (id) ON DELETE CASCADE,
    course_id     BIGINT NOT NULL REFERENCES course (id) ON DELETE CASCADE,
    grading       VARCHAR,
    PRIMARY KEY (discipline_id, course_id)
);

CREATE INDEX idx_discipline_course_course_id ON discipline_course (course_id);
