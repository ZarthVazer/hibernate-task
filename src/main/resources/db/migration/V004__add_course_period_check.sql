ALTER TABLE course
    ADD CONSTRAINT chk_course_period
        CHECK (start_at IS NULL OR end_at IS NULL OR end_at > start_at);
