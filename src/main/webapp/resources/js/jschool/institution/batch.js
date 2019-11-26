
/*
 * registration function with jquery ajax 
 */
$(document).ready(function ($) {
    //call class initialized

//	datatable load at page load
    batchDatatable();

    $("#addBatchForm").submit(function (event) {

        event.preventDefault();
        var status = jbf.form.validate('#addBatchForm');
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
            var data = {}
            data["batch"] = $("#batch").val();
            data["active"] = $('#active_inactive').prop('checked');
            url = "batch/add";

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
                        batchDatatable();
                        document.getElementById("addBatchForm").reset();
                    } else if (response.success == false && response.error == true) {
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


    function batchDatatable(param) {
        var url = 'batch/load';
        $('#batchTable').dataTable({
            destroy: true,
            data: jbf.ajax.load(url, param),
            columns: [{
                    title: 'Batch',
                    data: 'batch'
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
                },
                {
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
                    title: 'Batch Data Export',
                    exportOptions: {
                        columns: ':visible:not(.notexport)'
                    }
                },
                {
                    extend: 'excelHtml5',
                    title: 'Batch Data Export',
                    exportOptions: {
                        columns: ':visible:not(.notexport)'
                    }
                },
                {
                    extend: 'pdfHtml5',
                    title: 'Batch Data Export',
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
    };

    $(document).on("click", "#statuschangebtn", function () {
        var myDataJSON = $(this).data('id');
        var batchId = myDataJSON.id;
        var currentStatus = myDataJSON.active;
        var newStatus = currentStatus == "true" ? false : true;

        var $confirm = $("#modalConfirmYesNo");
        $confirm.modal('show');
        $("#lblTitleConfirmYesNo").html("Confirmation");
        $("#lblMsgConfirmYesNo").html("Are you sure you want to change the status for this batch?");

        $("#btnYesConfirmYesNo").off('click').click(function () {
            $confirm.modal("hide");

            // get form data
            var data = {}
            data["id"] = batchId;
            data["active"] = newStatus;
            var url = "batch/changestatus";

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
                        batchDatatable();
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