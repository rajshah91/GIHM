/*
 * registration function with jquery ajax 
 */
$(document).ready(function ($) {
    
    var query = window.location.search.substring(1);
    var myParam = location.search.split('success=')[1] ? location.search.split('success=')[1] : "" ;
    if(myParam != ""){
        if(myParam == "true"){
            success("Data Uploaded Successfully");
        }else if(myParam == "false"){
            error("Data Upload failed.Please try again.");
        }
    }

    var courseSemesterMap = new Map();
    getAllCourse();
    getAllBatch();

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

    $("#sample_file_download").click(function () {
        var month = $("#month").val();
        if (month !== null && month !== "") {
            var url = "downloadsamplefile?month=" + month;
            $.ajax({
                type: 'GET',
                url: url,
                processData: false,
                success: function (data) {
                    window.location = url;
                },
                error: function (xhr) {
                    console.log(' Error:  >>>> ' + JSON.stringify(xhr));
                }
            });
        }

    });


    $("form#student_attendance_form").submit(function (event) {
        
        var courseId=$("#courseCombo").val();
        var batchId=$("#batchCombo").val();
        var semesterId=$("#semesterCombo").val();
        var month=$("#month").val();
        
        var status = jbf.form.validate('#student_attendance_form');
        if (!status) {
            return;
            event.preventDefault();
        }else{
            if(batchId != "" && courseId != "" && semesterId != "" && month != ""){
                $(this).unbind(event);
            }
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

});