/*
 * registration function with jquery ajax 
 */
$(document).ready(function ($) {

    var courseSemesterMap = new Map();
    getAllCourse();
    getAllBatch();
    
    $('#courseCombo').select2({
        placeholder: "Select Course"
    });
    
    $('#batchCombo').select2({
        placeholder: "Select Batch"
    });

    $("#courseCombo").change(function () {
        populateCourseFee();
    });
    
    $("#batchCombo").change(function () {
        populateCourseFee();
    });

    $("#mobile_number").change(function () {
        $("#username").val($("#mobile_number").val());
    });
    
    $("#enrollmentNumber").change(function () {
    	$("#password").val(generatePasswordForStudent());
    });
    
    $("#birth_date").change(function () {
    	$("#password").val(generatePasswordForStudent());
    });
    
    $("#birth_date").select(function () {
    	$("#password").val(generatePasswordForStudent());
    });
    
    
    $("#disability_detail").hide();

    $('input[type=radio][name=disability]').change(function () {
        if (this.value === 'yes') {
            $("#disability_detail").show();
        } else if (this.value === 'no') {
            $("#disability_detail").hide();
        }
    });


    $("#student_form").submit(function (event) {

        // form redirect stop
        event.preventDefault();
        var status = jbf.form.validate('#student_form');
        if (!status) {
            return;
        }


        // get form data
        var data = {};
        data["enrollmentNumber"] = $("#enrollmentNumber").val();
        data["courseId"] = $("#courseCombo").val();
        data["batchId"] = $("#batchCombo").val();
        data["qualification"] = $("#qualification").val();
        data["firstName"] = $("#first_name").val();
        data["middleName"] = $("#middle_name").val();
        data["lastName"] = $("#last_name").val();
        data["gender"] = $("input[name='gender']:checked").val();
        data["birthDate"] = $("#birth_date").val();
        data["enrollmentDate"] = $("#enrollment_date").val();
        data["address"] = $("#address").val();
        data["mobileNumber"] = $("#mobile_number").val();
        data["emailId"] = $("#email").val();
        data["guardianFullName"] = $("#guardian_full_name").val();
        data["guardianFullAddress"] = $("#guardian_full_address").val();
        data["guardianMobileNumber"] = $("#guardian_mobile_number").val();
        data["username"] = $("#username").val();
        data["aadharNumber"] = $("#aadharcardnumber").val();
        data["password"] = $("#password").val();
        data["bloodGroup"] = $("#blood_group").val();
        data["disability"] =$("input[name='disability']:checked").val();
        data["disabilityDetail"] = $("#disability_detail").val();
        data["discount"] = $("#discount").val();
        
        var url = "enrollstudent";

        $.ajax({
            type: "POST",
            url: url,
            data: JSON.stringify(data),
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                var message = response.message;
                //success notification
                if(response.success === true){
                    success(message);
                    document.getElementById("student_form").reset();
                    $('#courseCombo').val("").trigger('change');
                    $('#batchCombo').val("").trigger('change');
                    $("#password").val("");
                }else{
                    error(message);
                }
                
            },
            error: function (e) {
                console.log("ERROR: ", e);
                error("Add failed");
            }
        });

    });

    function generateRandomPassword() {
        return Math.random().toString(36).slice(-8);
    }
    
    function generatePasswordForStudent() {
    	var enrollmentNumber=$("#enrollmentNumber").val();
    	var birthDate=$("#birth_date").val();
    	if(enrollmentNumber != "" && birthDate != ""){
    		var bday = birthDate.split("-");
        	var dd = bday[2];
        	var mm = bday[1]; 
        	var yyyy = bday[0];
        	 
        	return dd+mm+yyyy+enrollmentNumber;
    	}else{
    		return "";
    	}
    }

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

    function populateCourseFee() {
        var selectedCourseId = $("#courseCombo").val();
        var selectedBatchId = $("#batchCombo").val();
        if (selectedBatchId != "" && selectedBatchId != null && selectedCourseId != null && selectedCourseId != "") {
            var data = {};
            data["courseId"] = selectedCourseId;
            data["batchId"] = selectedBatchId;

            var url = 'getfeeforcourse';
            $.ajax({
                type: "POST",
                url: url,
                dataType: 'json',
                data:  JSON.stringify(data),
                contentType: "application/json; charset=utf-8",
                success: function (response) {
                    if(response.success === true){
                        var data = response.data;
                        $("#total_fee").val(data.feeAmount);
                    }else{
                        error(response.message);
                    }
                    
                },
                error: function (e) {
                    console.log("ERROR: ", e);
                    error("Fee Load failed");
                }
            });
        }
    }

});