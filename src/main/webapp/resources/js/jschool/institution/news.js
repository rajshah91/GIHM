
/*
 * registration function with jquery ajax 
 */
$(document).ready(function ($) {

    newsDatatable();

   $("#addNewsForm").submit(function (event) {

        event.preventDefault();
        var status = jbf.form.validate('#addNewsForm');
        if (!status) {
            return;
        }
        // get form data
        var data = {}
        data["title"] = $("#newstitle").val();
        data["description"] = $("#newsdesc").val();
        url = "news/add";

        $.ajax({
            type: "POST",
            url: url,
            data: JSON.stringify(data),
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function (resonse) {
                var message = resonse.message;
                //success notification
                success(message);

                newsDatatable();
                document.getElementById("addNewsForm").reset();
            },
            error: function (e) {
                console.log("ERROR: ", e);
                error("Add failed");


            }
        });

    });


    function newsDatatable(param) {
        var url = 'news/loadallnews?active=true';
        $('#newsTable').dataTable({
            destroy: true,
            data: jbf.ajax.load(url, param),
            columns: [{
                    title: 'Title',
                    data: 'title'
                }, {
                    title: 'Description',
                    data: 'description'
                }, {
                    title: 'Publish Date',
                    data: 'dataCreateTime',
                    "type": "date",
                    "render": function (data) {
                        var date = new Date(data);
                        var month = date.getMonth() + 1;
                        return (date.getDate() >= 10 ? date.getDate() : "0" + date.getDate()) + "-" + (month >= 10 ? month : "0" + month) + "-" + date.getFullYear();
                        //return date;
                    }
                },{
                    title: '',
                    data: null,
                    class: 'notexport',
                    render: function (data, type, row) {
                        var dataToSend = {};
                        dataToSend["id"] = row.id;
                        return "<button class='btn-primary notexport' id='statuschangebtn' data-id=" + JSON.stringify(dataToSend) + " data-toggle='modal' data-target='' data-whatever='' onclick='confirmStatusChange(" + row.id + ");'>Disable</button>";
                    }
                }
            ],
            dom: 'Bfrtip',
            buttons: [
                {
                    extend: 'csvHtml5',
                    title: 'News Export',
                    exportOptions: {
                        columns: ':visible:not(.notexport)'
                    }
                },
                {
                    extend: 'excelHtml5',
                    title: 'News Export',
                    exportOptions: {
                        columns: ':visible:not(.notexport)'
                    }
                },
                {
                    extend: 'pdfHtml5',
                    title: 'News Export',
                    exportOptions: {
                        columns: ':visible:not(.notexport,.notexportpdf)'
                    },
                    orientation: 'portrait',
                    pageSize: 'a4'
                }
            ],
            columnDefs: [
                {"Subject Title": "dt-center", "targets": "_all"}
            ]
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
        var newsId = myDataJSON.id;

        var $confirm = $("#modalConfirmYesNo");
        $confirm.modal('show');
        $("#lblTitleConfirmYesNo").html("Confirmation");
        $("#lblMsgConfirmYesNo").html("Are you sure you want to disable this News?");

        $("#btnYesConfirmYesNo").off('click').click(function () {
            $confirm.modal("hide");

            var data = {};
            data["id"] = newsId;
            var url = "news/changestatus";

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
                        newsDatatable();
                    } else if (response.success == false && response.error == true) {
                        error(message);
                    }

                },
                error: function (e) {
                    console.log("ERROR: ", e);
                    error("Status Change failed");
                }
            });

        });
        $("#btnNoConfirmYesNo").off('click').click(function () {
            $confirm.modal("hide");
            return false;
        });
    });


});