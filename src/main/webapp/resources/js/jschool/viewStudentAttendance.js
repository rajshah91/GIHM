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

    $('#month').select2({
        placeholder: "Select Month"
    });

    $("#courseCombo").change(function () {
        populateSemester();
    });

    $("form#student_attendance_form").submit(function (event) {
        event.preventDefault();
        var courseId = $("#courseCombo").val();
        var batchId = $("#batchCombo").val();
        var semesterId = $("#semesterCombo").val();
        var month = $("#month").val();
        if (courseId != "" && batchId != "" && semesterId != "" && month != "") {
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
                "url": "viewAttendance/getStudentAttendance",
                "type": "POST",
                "data": function (d) {
                    d.courseId = $("#courseCombo").val();
                    d.batchId = $("#batchCombo").val();
                    d.semesterId = $("#semesterCombo").val();
                    d.month = $("#month").val();
                }
            },
            "columns": [
                {
                    data: null,
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
                    title: '1',
                    data: 'a1'
                }, {
                    title: '2',
                    data: 'a2'
                }, {
                    title: '3',
                    data: 'a3'
                }, {
                    title: '4',
                    data: 'a4'
                }, {
                    title: '5',
                    data: 'a5'
                }, {
                    title: '6',
                    data: 'a6'
                }, {
                    title: '7',
                    data: 'a7'
                }, {
                    title: '8',
                    data: 'a8'
                }, {
                    title: '9',
                    data: 'a9'
                }, {
                    title: '10',
                    data: 'a10'
                }, {
                    title: '11',
                    data: 'a11'
                }, {
                    title: '12',
                    data: 'a12'
                }, {
                    title: '13',
                    data: 'a13'
                }, {
                    title: '14',
                    data: 'a14'
                }, {
                    title: '15',
                    data: 'a15'
                }, {
                    title: '16',
                    data: 'a16'
                }, {
                    title: '17',
                    data: 'a17'
                }, {
                    title: '18',
                    data: 'a18'
                }, {
                    title: '19',
                    data: 'a19'
                }, {
                    title: '20',
                    data: 'a20'
                }, {
                    title: '21',
                    data: 'a21'
                }, {
                    title: '22',
                    data: 'a22'
                }, {
                    title: '23',
                    data: 'a23'
                }, {
                    title: '24',
                    data: 'a24'
                }, {
                    title: '25',
                    data: 'a25'
                }, {
                    title: '26',
                    data: 'a26'
                }, {
                    title: '27',
                    data: 'a27'
                }, {
                    title: '28',
                    data: 'a28'
                }, {
                    title: '29',
                    data: 'a29'
                }, {
                    title: '30',
                    data: 'a30'
                }, {
                    title: '31',
                    data: 'a31'
                }, {
                    title: 'Total Days in Month',
                    data: 'totalDaysInMonth'
                }, {
                    title: 'Total Holidays in Month',
                    data: 'totalHolidaysInMonth'
                }, {
                    title: 'Total Working Days in Month',
                    data: 'totalWorkingDaysInMonth'
                }, {
                    title: 'Total Presence',
                    data: 'totalPresentCount'
                }, {
                    title: 'Total Absence',
                    data: 'totalAbsentCount'
                }, {
                    title: 'Total Leaves Taken',
                    data: 'totalLeaveCount'
                }
            ],
            dom: 'Bfrtip',
            buttons: [
                {
                    extend: 'csvHtml5',
                    title: 'Student Attendance Export',
                    exportOptions: {
                        columns: ':visible:not(.notexport)',
                        orthogonal: 'export'
                    }
                },
                {
                    extend: 'excelHtml5',
                    title: 'Student Attendance Export',
                    exportOptions: {
                        columns: ':visible:not(.notexport)',
                        orthogonal: 'export'
                    }
                }
            ],
//            "scrollCollapse": true,
//            "scrollX" : "2000px",
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