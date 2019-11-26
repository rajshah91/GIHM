
/*
 * registration function with jquery ajax 
 */
$(document).ready(function ($) {

    courseDatatable();
//	loadSubjects();

    $("#courseName").keyup(function () {
        searchCourseName();
    });

    $("#addClassForm").submit(function (event) {

        event.preventDefault();
        var status = jbf.form.validate('#addClassForm');
        if (!status) {
            return;
        }
        // get form data
        var data = {}
        data["courseName"] = $("#courseName").val();
        data["totalSemester"] = $("#semester").val();
        data["active"] = $('#active_inactive').prop('checked');
        url = "course/add";

        /*
         * this part for csrf token now closed but dont removed from code
         * apply future in code 
         */

        /*var token = $('#csrfToken').val();
         var header = $('#csrfHeader').val();*/
        /*	
         * if in spring application csrf enable
         * send csrf parameter in header otherwise 405 error
         */
        $.ajax({
            type: "POST",
            url: url,
            data: JSON.stringify(data),
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            /*beforeSend: function(xhr) {
             xhr.setRequestHeader("Accept", "application/json");
             xhr.setRequestHeader("Content-Type", "application/json");
             xhr.setRequestHeader(header, token);
             },*/
            success: function (resonse) {
                var message = resonse.message;
                //success notification
                success(message);

                courseDatatable();
                document.getElementById("addClassForm").reset()
            },
            error: function (e) {
                console.log("ERROR: ", e);
                error("Add failed");


            }
        });

    });


    function courseDatatable(param) {
        var url = 'course/loadallcourse';
        $('#classTable').dataTable({
            destroy: true,
            data: jbf.ajax.load(url, param),
            columns: [{
                    title: 'Course Name',
                    data: 'courseName'
                }, {
                    title: 'Total Semester',
                    data: 'totalSemester'
                }, {
                    title: 'Status',
                    data: 'active',
                    render: function (status) {
                        if (status == "true") {
                            return "Active";
                        } else {
                            return "Inactive";
                        }
                    }
                },{
                    title: 'Change Status',
                    data: null,
                    class: 'notexport',
                    render: function (data, type, row) {
                        var dataToSend = {};
                        dataToSend["id"] = row.id;
                        dataToSend["active"] = row.active;
                        var changeStatusMessage = row.active == "true" ? "De-Activate" : "Activate";
                        return "<button class='btn-primary notexport' id='statuschangebtn' data-id=" + JSON.stringify(dataToSend) + " data-toggle='modal' data-target='' data-whatever='' onclick='confirmStatusChange(" + row.id + ");'>" + changeStatusMessage + "</button>";
                    }
                }
            ],
            dom: 'Bfrtip',
            buttons: [
                {
                    extend: 'csvHtml5',
                    title: 'Course Data Export',
                    exportOptions: {
                        columns: ':visible:not(.notexport)'
                    }
                },
                {
                    extend: 'excelHtml5',
                    title: 'Course Data Export',
                    exportOptions: {
                        columns: ':visible:not(.notexport)'
                    }
                },
                {
                    extend: 'pdfHtml5',
                    title: 'Course Data Export',
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
        });
    }
    ;

    function searchCourseName() {

        var url = 'course/searchcoursebyname';
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'json',
            data: {
                'searchCourseName': $("#courseName").val()
            },
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                var data1 = response.data;
                $("#courseHelp").html(data1).fadeIn("slow").fadeOut(90000);
            },
            error: function (e) {
                console.log("ERROR: ", e);
                error("Load failed");
            }
        });
    }
    
    
     $(document).on("click", "#statuschangebtn", function () {
        var myDataJSON = $(this).data('id');
        var courseId = myDataJSON.id;
        var currentStatus = myDataJSON.active;
        var newStatus = currentStatus == "true" ? false : true;

        var $confirm = $("#modalConfirmYesNo");
        $confirm.modal('show');
        $("#lblTitleConfirmYesNo").html("Confirmation");
        $("#lblMsgConfirmYesNo").html("Are you sure you want to change the status for this batch?");

        $("#btnYesConfirmYesNo").off('click').click(function () {
            $confirm.modal("hide");

            // get form data
            var data = {};
            data["id"] = courseId;
            data["active"] = newStatus;
            var url = "course/changestatus";

            $.ajax({
                type: "POST",
                url: url,
                data: JSON.stringify(data),
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                success: function (response) {
                    var message = response.message;
                    if (response.success == true) {
                        success(message);
                        courseDatatable();
                    } else if (response.success == false && response.error == true) {
                        error(message);
                    }

                },
                error: function (e) {
                    console.log("ERROR: ", e);
                    error("Updation failed");
                }
            });

        });
        $("#btnNoConfirmYesNo").off('click').click(function () {
            $confirm.modal("hide");
            return false;
        });
    });


});