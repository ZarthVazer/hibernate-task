#!/usr/bin/env bash

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

TASK='task5'

function run_script() {
  local -r log_msg="$1"
  local -r demo="$2"
  shift 2

  echo $log_msg
  bash "${SCRIPT_DIR}/exec_task.sh" "${TASK}" $demo $@
}

UPDATE_GRADING_DEMO='UpdatingDisciplineCourseRelationDemo'
run_script 'Setting grading for course-1 discipline-1 to TEST' $UPDATE_GRADING_DEMO 1 1 'TEST'
run_script 'Setting grading for course-2 discipline-2 to EXAM' $UPDATE_GRADING_DEMO 2 2 'EXAM'
run_script 'Setting grading for course-3 discipline-3 to REPORT' $UPDATE_GRADING_DEMO 3 3 'REPORT'

SEARCH_DISCIPLINES_BY_COURSE_AND_GRADING_DEMO='SearchDisciplineByCourseAndGradingDemo'
run_script 'Search for course-1 and TEST' $SEARCH_DISCIPLINES_BY_COURSE_AND_GRADING_DEMO 1 'TEST'
run_script 'Search for course-1 and EXAM' $SEARCH_DISCIPLINES_BY_COURSE_AND_GRADING_DEMO 1 'EXAM'
run_script 'Search for course-2 and EXAM' $SEARCH_DISCIPLINES_BY_COURSE_AND_GRADING_DEMO 2 'EXAM'
run_script 'Search for course-2 and REPORT' $SEARCH_DISCIPLINES_BY_COURSE_AND_GRADING_DEMO 2 'REPORT'
run_script 'Search for course-3 and REPORT' $SEARCH_DISCIPLINES_BY_COURSE_AND_GRADING_DEMO 3 'REPORT'
run_script 'Search for course-3 and NONE' $SEARCH_DISCIPLINES_BY_COURSE_AND_GRADING_DEMO 3 'NONE'
