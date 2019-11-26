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
        data["studentId"] = $("#studentId").val();

        var url = "viewResultToStudent/getStudentResult";

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
                    }else{
                      $("#tablediv").html("");  
                    }
                } else {
                    error(response.message);
                    $("#tablediv").html("");
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
                error("fetchStudentResultData failed");
                $("#tablediv").html("");
            }
        });
    }

    function prepareResultTableFromJSON(data) {
        var tableBaseStr = "<table id='example' style='width:100%' border='1' class='table table-striped  display no-footer dataTable' role='grid'>";
        tableBaseStr += "<thead><tr>";
        tableBaseStr += "<th rowspan='5' style='text-align: center'>No</th>";
        tableBaseStr += "<th rowspan='5' style='text-align: center'>Name</th>";
        tableBaseStr += "<th rowspan='5' style='text-align: center'>Enrollment No</th>";
        
        var tableDataStr="<tbody>";

        for (var i = 0; i < data.length; i++) {
            var obj = data[i];
            
            var index= (i) + 1;
            var oddEven= (index % 2 === 0 ? "style='background-color: #FFF';text-align: center" : "style='background-color: #CCC;text-align: center'");
            var currentRowStr="<tr role='row' "+oddEven+"><th>"+index+"</th>";
            
            if ("studentName" in obj) {
                currentRowStr += "<th style='text-align: center'>"+obj.studentName+"</th>";
            }
            if ("enrollmentNo" in obj) {
                currentRowStr += "<th style='text-align: center'>"+obj.enrollmentNo+"</th>";
            }
            if ("studentResultJson" in obj) {
                var studentResultJson = obj.studentResultJson;
                var parsedJson = JSON.parse(studentResultJson);

                var subjectStr = "";
                var theoryPractialGradeStr = "<tr>";
                var examDateStr = "<tr>";
                var twthprStr = "<tr>";
                var twthprValueStr = "<tr>";
                for (var j = 0; j < parsedJson.length; j++) {
                    
                    var internalJsonObj = parsedJson[j];
                    var colSpanRow1=0;
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
                            subjectStr += ("<th colspan='"+colSpanRow1+"' style='text-align: center'>" + internalJsonObj.subject + "</th>");
                        }
                    }
                        
                    if ("theory" in internalJsonObj) {
                        var theoryObj = internalJsonObj.theory;
                        if (i === 0) {
                            theoryPractialGradeStr += ("<th colspan='5' style='text-align: center'>THEORY</th>");

                            examDateStr += ("<th colspan='5' style='text-align: center'>" + theoryObj.examDate + "</th>");

                            twthprStr += ("<th style='text-align: center'>TW</th>");
                            twthprStr += ("<th style='text-align: center'>TH</th>");
                            twthprStr += ("<th style='text-align: center'>Total</th>");
                            twthprStr += ("<th style='text-align: center'>%</th>");
                            twthprStr += ("<th style='text-align: center'>Grade</th>");

                            twthprValueStr += ("<th style='text-align: center'>" + theoryObj.ofTW + "</th>");
                            twthprValueStr += ("<th style='text-align: center'>" + theoryObj.ofTH + "</th>");
                            twthprValueStr += ("<th style='text-align: center'>" + theoryObj.ofTotal + "</th>");
                            twthprValueStr += ("<th style='text-align: center'></th>");// %
                            twthprValueStr += ("<th style='text-align: center'></th>");// Grade
                        }
                        currentRowStr += "<th style='text-align: center'>"+theoryObj.tw+"</th>";
                        currentRowStr += "<th style='text-align: center'>"+theoryObj.th+"</th>";
                        currentRowStr += "<th style='text-align: center'>"+theoryObj.total+"</th>";
                        currentRowStr += "<th style='text-align: center'>"+theoryObj.percentage+"</th>";
                        currentRowStr += "<th style='text-align: center'>"+theoryObj.grade+"</th>";
                    }
                    if ("practical" in internalJsonObj) {
                         var practicalObj = internalJsonObj.practical;
                        if (i === 0) {
                            theoryPractialGradeStr += ("<th colspan='4' style='text-align: center'>PRACTICAL</th>");
                           
                            examDateStr += ("<th colspan='4' style='text-align: center'>" + practicalObj.examDate + "</th>");

                            twthprStr += ("<th style='text-align: center'>PR</th>");
                            twthprStr += ("<th style='text-align: center'>Total</th>");
                            twthprStr += ("<th style='text-align: center'>%</th>");
                            twthprStr += ("<th style='text-align: center'>Grade</th>");

                            twthprValueStr += ("<th style='text-align: center'>" + practicalObj.ofPR + "</th>");
                            twthprValueStr += ("<th style='text-align: center'>" + practicalObj.ofTotal + "</th>");
                            twthprValueStr += ("<th style='text-align: center'></th>");// %
                            twthprValueStr += ("<th style='text-align: center'></th>");// Grade
                        }
                        
                        currentRowStr += "<th style='text-align: center'>"+practicalObj.pr+"</th>";
                        currentRowStr += "<th style='text-align: center'>"+practicalObj.total+"</th>";
                        currentRowStr += "<th style='text-align: center'>"+practicalObj.percentage+"</th>";
                        currentRowStr += "<th style='text-align: center'>"+practicalObj.grade+"</th>";
                    }
                    if ("finalGrade" in internalJsonObj) {
                        var finalGradeObj = internalJsonObj.finalGrade;
                        if (i === 0) {
                            subjectStr += ("<th colspan='" + colSpanRow1 + "' style='text-align: center'>FINAL GRADE</th>");

                            theoryPractialGradeStr += ("<th colspan='3' style='text-align: center'></th>");// empty as theory/practical
                            examDateStr += ("<th colspan='3' style='text-align: center'></th>");// empty as examdate

                            twthprStr += ("<th style='text-align: center'>Total</th>");
                            twthprStr += ("<th style='text-align: center'>%</th>");
                            twthprStr += ("<th style='text-align: center'>Grade</th>");

                            twthprValueStr += ("<th style='text-align: center'>" + finalGradeObj.ofTotal + "</th>");
                            twthprValueStr += ("<th style='text-align: center'></th>");// %
                            twthprValueStr += ("<th style='text-align: center'></th>");// Grade
                        }
                        currentRowStr += "<th style='text-align: center'>"+finalGradeObj.total+"</th>";
                        currentRowStr += "<th style='text-align: center'>"+finalGradeObj.percentage+"</th>";
                        currentRowStr += "<th style='text-align: center'>"+finalGradeObj.grade+"</th>";
                    }
//                    console.log(parsedJson[j]); // Object with id and time
                }
                currentRowStr += "</tr>";
                tableDataStr += currentRowStr;
                if (i === 0) {                     
                    subjectStr += "</tr>";
                    theoryPractialGradeStr+= "</tr>";
                    examDateStr+= "</tr>";
                    twthprStr+= "</tr>";
                    twthprValueStr+= "</tr>";
                    
                    tableBaseStr += (subjectStr + theoryPractialGradeStr + examDateStr + twthprStr + twthprValueStr);
                    tableBaseStr += "</tr></thead>";
                }
            }
        }
        tableDataStr += "</tbody></table>";
        $("#tablediv").html(tableBaseStr + tableDataStr);
    }

});