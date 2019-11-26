/*
 * registration function with jquery ajax 
 */
$(document).ready(function ($) {

    var courseSemesterMap = new Map();
    getAllCourse();
    getAllBatch();
//    studentResultDatatable();

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

    $("form#student_result_form").submit(function (event) {
        event.preventDefault();
        var courseId = $("#courseCombo").val();
        var batchId = $("#batchCombo").val();
        var semesterId = $("#semesterCombo").val();

        if (courseId != "" && batchId != "" && semesterId != "") {
            fetchStudentResultData();
        }
    });



    $("#print_result").on('click', function (e) {
        var divToPrint = document.getElementById("tablediv");
        newWin = window.open("");
        newWin.document.write(divToPrint.outerHTML);
        newWin.print();
        newWin.close();
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


    function fetchStudentResultData() {
        var data = {};
        data["batchId"] = $("#batchCombo").val();
        data["courseId"] = $("#courseCombo").val();
        data["semesterId"] = $("#semesterCombo").val();

        var url = "viewResult/getStudentResult";

        $.ajax({
            url: url,
            type: "GET",
//            data: JSON.stringify(data),

            data: data,
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                if (response.success === true) {
                    var data = response.data;
                    if (data !== null && data !== undefined && data !== "" && data.length > 0) {
                        prepareResultTableFromJSON(data);
                    }

                } else {
                    error(response.message);
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
                error("fetchStudentResultData failed");
            }
        });
    }

    function prepareResultTableFromJSON(data) {
        var tableBaseStr = "<table id='iddatatable' style='width:100%' border='1' class='table table-striped  display no-footer dataTable' role='grid'>";
        tableBaseStr += "<thead><tr>";

        var emptyColFormat = "<th style='text-align: center' rowspan='1' colspan='1'></th>";
        var addEmptyColumnStr = emptyColFormat + emptyColFormat + emptyColFormat;

        var tableDataStr = "<tbody>";

        for (var i = 0; i < data.length; i++) {
            var obj = data[i];

            var index = (i) + 1;
            var oddEven = (index % 2 === 0 ? "style='background-color: #FFF';text-align: center" : "style='background-color: #CCC;text-align: center'");
            var currentRowStr = "<tr role='row' " + oddEven + "><th>" + index + "</th>";

            if ("studentName" in obj) {
                currentRowStr += "<th style='text-align: center' class='obj.studentName'>" + obj.studentName + "</th>";
            }
            if ("enrollmentNo" in obj) {
                currentRowStr += "<th style='text-align: center' class='obj.enrollmentNo'>" + obj.enrollmentNo + "</th>";
            }
            if ("studentResultJson" in obj) {
                var studentResultJson = obj.studentResultJson;
                var parsedJson = JSON.parse(studentResultJson);

                var subjectStr = "";
                var theoryPractialGradeStr = "<tr>";
                var examDateStr = "<tr>";
                var twthprStr = "<tr>";
                var twthprValueStr = "<tr>";

                subjectStr += addEmptyColumnStr;
                theoryPractialGradeStr += addEmptyColumnStr;
                examDateStr += addEmptyColumnStr;
                twthprStr += addEmptyColumnStr;

                twthprValueStr += "<th rowspan='' style='text-align: center' colspan=''>No</th>";
                twthprValueStr += "<th rowspan='' style='text-align: center' colspan=''>Name</th>";
                twthprValueStr += "<th rowspan='' style='text-align: center' colspan=''>Enrollment No</th>";


                for (var j = 0; j < parsedJson.length; j++) {

                    var internalJsonObj = parsedJson[j];
                    var colSpanRow1 = 0;
                    if (i === 0) {
                        if ("theory" in internalJsonObj) {
                            colSpanRow1 = 5;
                        }
                        if ("practical" in internalJsonObj) {
                            colSpanRow1 += 4;
                        }
                        if ("finalGrade" in internalJsonObj) {
                            colSpanRow1 = 3;
                        }
                    }

                    if ("subject" in internalJsonObj) {
                        if (i === 0) {
                            subjectStr += ("<th colspan='" + colSpanRow1 + "' style='text-align: center' class='internalJsonObj.subject'>" + internalJsonObj.subject + "</th>");
                        }
                    }

                    if ("theory" in internalJsonObj) {
                        var theoryObj = internalJsonObj.theory;
                        if (i === 0) {
                            theoryPractialGradeStr += ("<th colspan='5' style='text-align: center' class='THEORY'>THEORY</th>");

                            examDateStr += ("<th colspan='5' style='text-align: center' class='theoryObj.examDate'>" + theoryObj.examDate + "</th>");

                            twthprStr += ("<th style='text-align: center' class='TW'>TW</th>");
                            twthprStr += ("<th style='text-align: center' class='TH'>TH</th>");
                            twthprStr += ("<th style='text-align: center' class='Total'>Total</th>");
                            twthprStr += ("<th style='text-align: center' class='%'>%</th>");
                            twthprStr += ("<th style='text-align: center' class='Grade'>Grade</th>");

                            twthprValueStr += ("<th style='text-align: center' class='theoryObj.ofTW'>" + theoryObj.ofTW + "</th>");
                            twthprValueStr += ("<th style='text-align: center' class='theoryObj.ofTH'>" + theoryObj.ofTH + "</th>");
                            twthprValueStr += ("<th style='text-align: center' class='theoryObj.ofTotal'>" + theoryObj.ofTotal + "</th>");
                            twthprValueStr += ("<th style='text-align: center' class='empty_%_th_0'>&nbsp;</th>");// %
                            twthprValueStr += ("<th style='text-align: center' class='empty_grade_th_0'>&nbsp;</th>");// Grade
                        }
                        currentRowStr += "<th style='text-align: center' class='theoryObj.tw'>" + theoryObj.tw + "</th>";
                        currentRowStr += "<th style='text-align: center' class='theoryObj.th'>" + theoryObj.th + "</th>";
                        currentRowStr += "<th style='text-align: center' class='theoryObj.total'>" + theoryObj.total + "</th>";
                        currentRowStr += "<th style='text-align: center' class='theoryObj.percentage'>" + theoryObj.percentage + "</th>";
                        currentRowStr += "<th style='text-align: center' class='theoryObj.grade'>" + theoryObj.grade + "</th>";
                    }
                    if ("practical" in internalJsonObj) {
                        var practicalObj = internalJsonObj.practical;
                        if (i === 0) {
                            theoryPractialGradeStr += ("<th colspan='4' style='text-align: center' class='PRACTICAL'>PRACTICAL</th>");

                            examDateStr += ("<th colspan='4' style='text-align: center' class='practicalObj.examDate'>" + practicalObj.examDate + "</th>");

                            twthprStr += ("<th style='text-align: center' class='PR'>PR</th>");
                            twthprStr += ("<th style='text-align: center' class=Total''>Total</th>");
                            twthprStr += ("<th style='text-align: center' class='%'>%</th>");
                            twthprStr += ("<th style='text-align: center' class='Grade'>Grade</th>");

                            twthprValueStr += ("<th style='text-align: center' class='practicalObj.ofPR'>" + practicalObj.ofPR + "</th>");
                            twthprValueStr += ("<th style='text-align: center' class='practicalObj.ofTotal'>" + practicalObj.ofTotal + "</th>");
                            twthprValueStr += ("<th style='text-align: center' class='empty_%_pr_0'>&nbsp;</th>");// %
                            twthprValueStr += ("<th style='text-align: center' class='empty_grade_pr_0'>&nbsp;</th>");// Grade
                        }

                        currentRowStr += "<th style='text-align: center' class='practicalObj.pr'>" + practicalObj.pr + "</th>";
                        currentRowStr += "<th style='text-align: center' class='practicalObj.total'>" + practicalObj.total + "</th>";
                        currentRowStr += "<th style='text-align: center' class='practicalObj.percentage'>" + practicalObj.percentage + "</th>";
                        currentRowStr += "<th style='text-align: center' class='practicalObj.grade'>" + practicalObj.grade + "</th>";
                    }
                    if ("finalGrade" in internalJsonObj) {
                        var finalGradeObj = internalJsonObj.finalGrade;
                        if (i === 0) {
                            subjectStr += ("<th colspan='" + colSpanRow1 + "' style='text-align: center' class='FINAL GRADE'>FINAL GRADE</th>");

                            theoryPractialGradeStr += ("<th colspan='3' style='text-align: center' class='empty_as_theory_practical'></th>");// empty as theory/practical
                            examDateStr += ("<th colspan='3' style='text-align: center' class='empty_as_examdate'></th>");// empty as examdate

                            twthprStr += ("<th style='text-align: center' class='Total'>Total</th>");
                            twthprStr += ("<th style='text-align: center' class='%'>%</th>");
                            twthprStr += ("<th style='text-align: center' class='Grade'>Grade</th>");

                            twthprValueStr += ("<th style='text-align: center' class='finalGradeObj.ofTotal'>" + finalGradeObj.ofTotal + "</th>");
                            twthprValueStr += ("<th style='text-align: center' class='empty_%_fg_0'>&nbsp;</th>");// %
                            twthprValueStr += ("<th style='text-align: center' class='empty_grade_fg_0'>&nbsp;</th>");// Grade
                        }
                        currentRowStr += "<th style='text-align: center' class='finalGradeObj.total'>" + finalGradeObj.total + "</th>";
                        currentRowStr += "<th style='text-align: center' class='finalGradeObj.percentage'>" + finalGradeObj.percentage + "</th>";
                        currentRowStr += "<th style='text-align: center' class='finalGradeObj.grade'>" + finalGradeObj.grade + "</th>";
                    }
//                    console.log(parsedJson[j]); // Object with id and time
                }
                currentRowStr += "</tr>";
                tableDataStr += currentRowStr;
                if (i === 0) {
                    subjectStr += "</tr>";
                    theoryPractialGradeStr += "</tr>";
                    examDateStr += "</tr>";
                    twthprStr += "</tr>";
                    twthprValueStr += "</tr>";

                    tableBaseStr += (subjectStr + theoryPractialGradeStr + examDateStr + twthprStr + twthprValueStr);
                    tableBaseStr += "</tr></thead>";
                }
            }
        }
        tableDataStr += "</tbody></table>";
        $("#tablediv").html(tableBaseStr + tableDataStr);

        $('#iddatatable').DataTable({
            dom: 'Bfrtip',
            buttons: [
                {
                    extend: 'csvHtml5',
                    title: 'Student Result Export',
                    exportOptions: {
                        columns: ':visible:not(.notexport)'
                    }
                },
                {
                    extend: 'excelHtml5',
                    title: 'Student Result Export',
                    exportOptions: {
                        columns: ':visible:not(.notexport)'
                    }
                }
//                ,
//                {
//                    extend: 'pdfHtml5',
//                    title: 'Student Result Export',
//                    exportOptions: {
//                        columns: ':visible:not(.notexport,.notexportpdf)'
//                    },
//                    orientation: 'landscape',
//                    pageSize: 'LEGAL'
//                }
            ],
            columnDefs: [
                {"Subject Title": "dt-center", "targets": "_all"}
            ]
        });
    }


});