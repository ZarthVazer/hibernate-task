#!/usr/bin/env bash

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

TASK='task3'

function run_script() {
  local -r log_msg="$1"
  local -r demo="$2"
  shift 2

  echo $log_msg
  bash "${SCRIPT_DIR}/exec_task.sh" "${TASK}" $demo $@
}

UPDATE_COURSE_START_DEMO='UpdateCourseStartAtDemo'
run_script 'Updating course-1 start_at to 2024-12-03T10:15:30+01:00' $UPDATE_COURSE_START_DEMO 1 '2024-12-03T10:15:30+01:00'
run_script 'Updating course-2 start_at to 2025-12-03T10:15:30+01:00' $UPDATE_COURSE_START_DEMO 2 '2025-12-03T10:15:30+01:00'
run_script 'Updating course-3 start_at to 2023-12-03T10:15:30+01:00' $UPDATE_COURSE_START_DEMO 3 '2023-12-03T10:15:30+01:00'

UPDATE_COURSE_END_DEMO='UpdateCourseEndAtDemo'
run_script 'Updating course-2 end_at to 2026-12-03T10:15:30+01:00' $UPDATE_COURSE_END_DEMO 2 '2026-12-03T10:15:30+01:00'
run_script 'Updating course-3 end_at to 2024-12-03T10:15:30+01:00' $UPDATE_COURSE_END_DEMO 3 '2024-12-03T10:15:30+01:00'

SEARCH_ACTIVE_COURSES_DEMO='SearchActiveCoursesDemo'
run_script 'Search active courses' $SEARCH_ACTIVE_COURSES_DEMO

SEARCH_FUTURE_COURSES_DEMO='SearchFutureCoursesDemo'
run_script 'Search future courses' $SEARCH_FUTURE_COURSES_DEMO