/*
 * registration function with jquery ajax 
 */
$(document).ready(function ($) {

    var courseSemesterMap = new Map();
    getAllCourse();
    getAllBatch();
    studentAttendanceDatatable();

    $('#courseCombo').select2({
        placeholder: "Select Course"
    });

    $('#batchCombo').select2({
        placeholder: "Select Batch"
    });

    $('#semesterCombo').select2({
        placeholder: "Select Semester"
    });

    $("#courseCombo").change(function () {
        populateSemester();
    });

    $("form#student_attendance_form").submit(function (event) {
        event.preventDefault();
        var courseId = $("#courseCombo").val();
        var batchId = $("#batchCombo").val();
        var semesterId = $("#semesterCombo").val();
        if (courseId != "" && batchId != "" && semesterId != "") {
            studentAttendanceDatatable();
        }
    });

    function getAllCourse() {

        var url = 'loadallcourse?active=true';
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'json',
            data: {
//                'searchCourseName': $("#courseName").val()
            },
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                var data = response.data;
                for (var i = 0; i < data.length; i++) {
                    courseSemesterMap.set(data[i].courseName, data[i].totalSemester);
                    $("#courseCombo").append("<option value='" + data[i].id + "'>" + data[i].courseName + "</option>");
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
                error("Course Load failed");
            }
        });
    }

    function getAllBatch() {

        var url = 'loadallbatch?active=true';
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'json',
            data: {
//                'searchCourseName': $("#courseName").val()
            },
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                var data = response.data;
                for (var i = 0; i < data.length; i++) {
                    $("#batchCombo").append("<option value='" + data[i].id + "'>" + data[i].batch + "</option>");
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
                error("Batch Load failed");
            }
        });
    }

    function populateSemester() {
        var selectedCourse = $("#courseCombo :selected").text();
        var totalSem = Number(courseSemesterMap.get(selectedCourse));
        $("#semesterCombo").html("");
        for (var i = 1; i <= totalSem; i++) {
            $("#semesterCombo").append("<option value='" + i + "'>" + i + "</option>");
        }
    }


    function studentAttendanceDatatable() {

        var tbl = $('#studentAttendanceTable').dataTable({
            "processing": true,
            "destroy": true,
            "ajax": {
                "url": "viewAttendance/getAggregateStudentAttendance",
                "type": "POST",
                "data": function (d) {
                    d.courseId = $("#courseCombo").val();
                    d.batchId = $("#batchCombo").val();
                    d.semesterId = $("#semesterCombo").val();
                }
            },
            "columns": [
                {
                    data: null,
                    title:'No',
                    sortable: false,
                    render: function (data, type, row, meta) {
                        return meta.row + meta.settings._iDisplayStart + 1;
                    }
                },
                {
                    title: 'Enrollment No',
                    data: 'enrollmentNumber'
                }, {
                    title: 'Student Name',
                    data: 'studentName'
                }, {
                    title: 'Total Working Days in Semester',
                    data: 'totalWorkingDaysInMonth'
                }, {
                    title: 'Total Present',
                    data: 'totalPresentCount'
                }, {
                    title: 'Total Leaves Taken',
                    data: 'totalLeaveCount'
                }, {
                    title: 'Total Absence',
                    data: 'totalAbsentCount'
                }, {
                    title: 'Total Aggregate (%)',
                    data: 'totalAggregatePercentage'
                }
            ],
            dom: 'Bfrtip',
            buttons: [
                {
                    extend: 'csvHtml5',
                    title: 'Student Aggregate Attendance Export',
                    exportOptions: {
                        columns: ':visible:not(.notexport)',
                        orthogonal: 'export'
                    }
                },
                {
                    extend: 'excelHtml5',
                    title: 'Student Aggregate Attendance Export',
                    exportOptions: {
                        columns: ':visible:not(.notexport)',
                        orthogonal: 'export'
                    }
                }
            ],
            "scrollCollapse": true,
            "scrollX" : "2000px",
//            "scrollY":"500px",
            "columnDefs": [
                {
                    "targets": "_all",
                    "createdCell": function (td, cellData, rowData, row, col) {
                        if (cellData === 'P') {
                            $(td).css('color', 'green');
                        }else if(cellData === "A"){
                            $(td).css('color', 'red');
                            $(td).css('font-weight', 'bold');
                        }else if(cellData === "L"){
                            $(td).css('color', 'blue');
                            $(td).css('font-weight', 'bold');
                        }else if(cellData === "H"){
                            $(td).css('color', 'greenyellow');
                            $(td).css('font-weight', 'bold');
                        }else if(cellData === null || cellData === "null"){
                            $(td).css('background', 'gray');
                        }
                    }
                },
//                { 
//                    "targets": [31,32,33],
//                    visible: false 
//                }
                
            ]
        });
        
    };
    
    

});