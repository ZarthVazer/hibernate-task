#!/usr/bin/env bash

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

TASK='task1'

function run_script() {
  local -r log_msg="$1"
  local -r demo="$2"
  shift 2

  echo $log_msg
  bash "${SCRIPT_DIR}/exec_task.sh" "${TASK}" $demo $@
}

CREATE_COURSE_DEMO='CreateCourseDemo'
run_script 'Adding course-1' $CREATE_COURSE_DEMO 'course-1'
run_script 'Adding course-2' $CREATE_COURSE_DEMO 'course-2'
run_script 'Adding course-3' $CREATE_COURSE_DEMO 'course-3'
run_script 'Adding course-4' $CREATE_COURSE_DEMO 'course-4'

CREATE_STUDENT_DEMO='CreateStudentDemo'
run_script 'Adding student-1' $CREATE_STUDENT_DEMO 'student-1'
run_script 'Adding student-2' $CREATE_STUDENT_DEMO 'student-2'
run_script 'Adding student-3' $CREATE_STUDENT_DEMO 'student-3'
run_script 'Adding student-4' $CREATE_STUDENT_DEMO 'student-4'

DELETE_STUDENT_DEMO='DeleteStudentDemo'
run_script 'Deleting student-4' $DELETE_STUDENT_DEMO 4

DELETE_COURSE_DEMO='DeleteCourseDemo'
run_script 'Deleting course-4' $DELETE_COURSE_DEMO 4

FIND_STUDENT_DEMO='FindStudentDemo'
run_script 'Searching for student-1' $FIND_STUDENT_DEMO 1

FIND_COURSE_DEMO='FindCourseDemo'
run_script 'Searching for course-1' $FIND_COURSE_DEMO 1

ACTIVATE_STUDENT_DEMO='ActivateStudentDemo'
run_script 'Activating student-1' $ACTIVATE_STUDENT_DEMO 1

ACTIVATE_COURSE_DEMO='ActivateCourseDemo'
run_script 'Activating course-1' $ACTIVATE_COURSE_DEMO 1

run_script 'Searching for student-1' $FIND_STUDENT_DEMO 1
run_script 'Searching for course-1' $FIND_COURSE_DEMO 1