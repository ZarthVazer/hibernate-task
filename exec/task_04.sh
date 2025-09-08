#!/usr/bin/env bash

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

TASK='task4'

function run_script() {
  local -r log_msg="$1"
  local -r demo="$2"
  shift 2

  echo $log_msg
  bash "${SCRIPT_DIR}/exec_task.sh" "${TASK}" $demo $@
}

CREATE_DISCIPLINE_DEMO='CreateDisciplineDemo'
run_script 'Creating discipline-1' $CREATE_DISCIPLINE_DEMO 'discipline-1'
run_script 'Creating discipline-2' $CREATE_DISCIPLINE_DEMO 'discipline-2'
run_script 'Creating discipline-3' $CREATE_DISCIPLINE_DEMO 'discipline-3'
run_script 'Creating discipline-4' $CREATE_DISCIPLINE_DEMO 'discipline-4'

DELETE_DISCIPLINE_DEMO='DeleteDisciplineDemo'
run_script 'Deleting discipline-4' $DELETE_DISCIPLINE_DEMO 4

LINK_COURSE_DISCIPLINE_DEMO='LinkCourseDisciplineDemo'
run_script 'Linking course-1 and discipline-1' $LINK_COURSE_DISCIPLINE_DEMO 1 1
run_script 'Linking course-1 and discipline-2' $LINK_COURSE_DISCIPLINE_DEMO 1 2
run_script 'Linking course-2 and discipline-2' $LINK_COURSE_DISCIPLINE_DEMO 2 2
run_script 'Linking course-3 and discipline-3' $LINK_COURSE_DISCIPLINE_DEMO 3 3

UNLINK_COURSE_DISCIPLINE_DEMO='UnlinkCourseDisciplineDemo'
run_script 'Unlinking course-1 and discipline-2' $UNLINK_COURSE_DISCIPLINE_DEMO 1 2
