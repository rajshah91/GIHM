/*
 * registration function with jquery ajax 
 */
$(document).ready(function ($) {

    studentAttendanceDatatable();

    function studentAttendanceDatatable() {

        var tbl = $('#studentAttendanceTable').dataTable({
            "processing": true,
            "destroy": true,
            "ajax": {
                "url": "viewAttendanceToStudent/getStudentAttendance",
                "type": "POST",
                "data": function (d) {
                    d.studentId = $("#studentId").val();
                }
            },
            "columns": [
                {
                    data: null,
                    sortable: false,
                    visible:false,
                    render: function (data, type, row, meta) {
                        return meta.row + meta.settings._iDisplayStart + 1;
                    }
                },
                {
                    title: 'Course',
                    data: 'courseName'
                }, {
                    title: 'Batch',
                    data: 'batchName'
                },{
                    title: 'Semester',
                    data: 'semesterId'
                }, {
                    title: 'Month',
                    data: 'monthName'
                },{
                    title: '',
                    data: null,
                    visible:false,
                    render: function (data, type, row, meta) {
                        var monthArr = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
                        return monthArr.indexOf(row.monthName);
                    }
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
            "order": [[ "2", "desc" ],[ "3", "asc" ],[ "5", "asc" ]],
            "scrollCollapse": true,
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
                
            ]
        });
        
    };
    
    

});