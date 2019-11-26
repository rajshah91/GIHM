
/*
 * registration function with jquery ajax 
 */
$(document).ready(function ($) {

    var courseSemesterMap = new Map();
    getAllCourse();
    getAllBatch();
    loadSubjects();
    courseSubjectDatatable();

    $("#courseCombo").change(function () {
        populateSemester();
    });

    $("#addCourseSubjectForm").submit(function (event) {

        event.preventDefault();
        var status = jbf.form.validate('#addCourseSubjectForm');
        if (!status) {
            return;
        }
        
        var $confirm = $("#modalConfirmYesNo");
        $confirm.modal('show');
        $("#lblTitleConfirmYesNo").html("Confirmation");
        $("#lblMsgConfirmYesNo").html("Are you sure you want to save this data?");


        $("#btnYesConfirmYesNo").off('click').click(function () {
            $confirm.modal("hide");

            // get form data
            var data = {};
            data["courseId"] = $("#courseCombo").val();
            data["semesterId"] = $("#semesterCombo").val();
            data["batchId"] = $("#batchCombo").val();
            data["subjectIds"] = $("#subjectCombo").val();
            var url = "courseSubject/addcoursesubject";

            $.ajax({
                type: "POST",
                url: url,
                async: true, 
                data: JSON.stringify(data),
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                success: function (response) {
                    var message = response.message;
                    if (response.success == true) {
                        success(message);
                        courseSubjectDatatable();
                        document.getElementById("addCourseSubjectForm").reset();
                        $('#courseCombo').val("").trigger('change');
                        $('#semesterCombo').val("").trigger('change');
                        $('#batchCombo').val("").trigger('change');
//                                    $('#subjectCombo').val(null).trigger('change');
                    } else {
                        error(message);
                    }
                },
                error: function (e) {
                    console.log("ERROR: ", e);
                    error("Add failed");
                }
            });


        });
        $("#btnNoConfirmYesNo").off('click').click(function () {
            $confirm.modal("hide");
            return false;
        });

    });


    function courseSubjectDatatable(param) {
        var url = 'courseSubject/loadcoursesubjectforview';
        $('#courseSubjectTable').dataTable({
            destroy: true,
            data: jbf.ajax.load(url, param),
            type: "POST",
            columns: [{
                    title: 'Course Name',
                    data: 'courseName'
                }, {
                    title: 'Batch',
                    data: 'batchName'
                }
                , {
                    title: 'Semester',
                    data: 'semester'
                }, {
                    title: 'Subjects',
                    data: 'subName'
                }
            ],
            dom: 'Bfrtip',
            buttons: [
                {
                    extend: 'csvHtml5',
                    title: 'Course-Subject Data Export',
                    exportOptions: {
                        columns: ':visible:not(.notexport)'
                    }
                },
                {
                    extend: 'excelHtml5',
                    title: 'Course-Subject Data Export',
                    exportOptions: {
                        columns: ':visible:not(.notexport)'
                    }
                },
                {
                    extend: 'pdfHtml5',
                    title: 'Course-Subject Data Export',
                    exportOptions: {
                        columns: ':visible:not(.notexport,.notexportpdf)'
                    },
                    orientation: 'portrait',
                    pageSize: 'a4'
                }
            ],
            columnDefs: [
                {"Subject Title": "dt-center", "targets": "_all"}
            ],
            "sScrollY": "400px",
            "sScrollX": "100%",
            "sScrollXInner": "1000px",
            "bScrollCollapse": true
//            ,rowGroup: {
//                 dataSrc: 'subName'
//            }
        });
    }
    ;

    function getAllCourse() {

        var url = 'course/loadallcourse?active=true';
        $.ajax({
            type: "GET",
            url: url,
            async: true, 
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

        var url = 'batch/load?active=true';
        $.ajax({
            type: "GET",
            url: url,
            async: true, 
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

    function loadSubjects() {
        var url = 'subject/load?active=true';
        $.ajax({
            url: url,
            async: true, 
            dataType: 'json',
            data: {
//                'searchCourseName': $("#courseName").val()
            },
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                var data = response.data;
                for (var i = 0; i < data.length; i++) {
                    $("#subjectCombo").append("<option value='" + data[i].subId + "'>" + data[i].subTitle + "</option>");
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
                error("Subject Load failed");
            }
        });
    }
});