$(document).ready(function ($) {
    
//    alert(window.roleId);
    jbf.combo.loadClass('#classCombo', 'insClass/load');
    var courseSemesterMap = new Map();
    var courseBatchFee = null;
    studentDatatable();
    getAllCourse();
    loadCourseFee();

    $("#bank_name_div").hide();
    $("#cheque_div").hide();
    $("#neft_div").hide();

    $("#savebtn").prop("disabled", true);

    $('#search_field').select2({
        placeholder: "Select Field For Search"
    });

    $("#search_value").on('input', function () {
        if ($("#search_field").val() !== "") {
            studentDatatable();
        }
    });

    $("#paying_fee").on('change', function () {
        if ($("#paying_fee").val() == "" || $("#paying_fee").val() == 0) {
            $("#savebtn").prop("disabled", true);
        } else {
            $("#savebtn").prop("disabled", false);
        }
    });

    $("#paying_fee").on('input', function () {
        if ($("#paying_fee").val() !== "" && $("#paying_fee").val() !== 0 && $("#paying_fee").val() > Number($("#remaining_fee").val())) {
            error("Currently Paying Fee can not be greater than Remaining Fee");
            $("#paying_fee").val("0");
            return false;
        }
    });

    $('input[type=radio][name=payment_mode]').change(function () {
        resetFeePaymentMode();
        if (this.value === 'cheque') {
//            $("#cheque_number").prop("disabled", false);
            $("#bank_name_div").show();
            $("#cheque_div").show();
            $("#neft_div").hide();
        } else if (this.value === 'cash') {
            $("#bank_name_div").hide();
            $("#cheque_div").hide();
            $("#neft_div").hide();
        } else if (this.value === "NEFT") {
            $("#bank_name_div").show();
            $("#cheque_div").hide();
            $("#neft_div").show();
        }
    });

    resetFeePaymentMode = function () {
        $("#cheque_number").val("");
        $("#bank_name").val("");
        $("#cheque_date").val("");
        $("#transaction_id").val("");
        $("#transaction_date").val("");
    }

    printPage = function () {
        var studentName = $("#studentName").val();
        var batchName = $("#batchName").val();
        var courseName = $("#courseName").val();
        var enrollmentNumber = $("#enrollmentNumber").val();
        var studentDetail = "<b>" + studentName + "</b> (" + enrollmentNumber + ")<br/>" + courseName + "  " + batchName;
        var todayDate = new Date().toISOString().slice(0, 10);

//        alert(toWords(20000)); 

        $("#r1c1").html(studentDetail);
        $("#r1c2").html($("#paying_fee").val());
        $("#r1c3").html($("input[name='payment_mode']:checked").val());
        $("#r1c4").html(todayDate);

        var divToPrint = document.getElementById('printModal');
        newWin = window.open();
        newWin.document.write(divToPrint.innerHTML);
        newWin.location.reload();
        newWin.focus();
        newWin.print();
        newWin.close();
    };

    $(document).on("click", "#feebtn", function () {
        var myDataJSON = $(this).data('id');
        var batchId = myDataJSON.b;
        var studentId = myDataJSON.id;
        var courseId = myDataJSON.c;

        $("#total_fee").val("0");
        $("#remaining_fee").val("0");
        $("#discount").val("0");
        $("#total_fee_paid").val("0");
        $("#paying_fee").val("0");
//        $("#cheque_number").prop("disabled", true);
        $("#studentName").val("");
        $("#batchName").val("");
        $("#courseName").val("");
        $("#enrollmentNumber").val("");
        $("#print_fee_description").val("");

        $("#courseId").val(courseId);
        $("#batchId").val(batchId);
        $("#studentId").val(studentId);

        getRemainingFeeForStudent(studentId, courseId, batchId);

    });


    $(document).on("click", "#feehistorybtn", function () {
        var myDataJSON = $(this).data('id');
        var studentId = myDataJSON.id;
        studentFeeHistoryDatatable(studentId);

    });

    $(document).on("click", "#feedeletebtn", function () {
        var myDataJSON = $(this).data('id');
        var feePaymentId = myDataJSON.id;
        var studentId = myDataJSON.studentId;
        deleteStudentFeeRecord(feePaymentId, studentId);
    });

    function loadCourseFee() {
        var url = 'courseFee/loadcoursefee';
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                var data = response.data;
                courseBatchFee = data;
            },
            error: function (e) {
                console.log("ERROR: ", e);
                error("Course Fee Load failed");
            }
        });
    }
    ;

    function getAllCourse() {

        var url = 'student/loadallcourse?active=true';
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                var data = response.data;
                for (var i = 0; i < data.length; i++) {
                    courseSemesterMap.set(Number(data[i].id), Number(data[i].totalSemester));
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
                error("Course Load failed");
            }
        });
    }

    function studentDatatable(param) {

        var tbl = $('#studentTable').dataTable({
            "processing": true,
            "destroy": true,
//            "responsive": true,
//            "serverSide": true,
            "ajax": {
                "url": "student/loadallstudents?active=true",
                "type": "POST",
                "data": function (d) {
                    d.searchType = $("#search_field").val();
                    d.searchText = $("#search_value").val();
                }
            },
            "columns": [
                {
                    title: 'Enrollment No',
                    data: 'enrollmentNumber'
                }, {
                    title: 'Course',
                    data: 'courseName',
                }, {
                    title: 'Batch',
                    data: 'batchName'
                }, {
                    title: 'Student Name',
                    data: 'firstName',
                    render: function (data, type, row) {
                        return row.firstName + ' ' + row.lastName;
                    }
                }, {
                    title: 'Gender',
                    data: 'gender'
                }, {
                    title: 'Mobile',
                    data: 'mobileNumber'
                }, {
                    title: 'Birth Date',
                    data: 'birthDate'
                }, {
                    title: 'Enrollment Date',
                    data: 'enrollmentDate'
                }, {
                    title: 'Address',
                    data: 'address'
                }, {
                    title: 'Email',
                    data: 'emailId'
                }, {
                    title: 'Blood Group',
                    data: 'bloodGroup'
                }, {
                    title: 'Aadhar Number',
                    data: 'aadharNumber'
                },
                {
                    title: 'Guardian Name',
                    data: 'guardianFullName',
                    class: 'notexportpdf'
                }, {
                    title: 'Guardian Address',
                    data: 'guardianFullAddress',
                    class: 'notexportpdf'
                }, {
                    title: 'Guardian Contact No.',
                    data: 'guardianMobileNumber',
                    class: 'notexportpdf'
                },
                {
                    title: '',
                    data: null,
                    class: 'notexport',
                    render: function (data, type, row) {
                        var dataToSend = {};
                        dataToSend["id"] = row.id;
                        return "<button class='btn-primary notexport' id='editbtn' data-id=" + JSON.stringify(dataToSend) + " data-toggle='modal' data-target='#editStudentModal' data-whatever='' onclick='fetchDataForEditModal(" + row.id + ");'>Edit</button>";
                    }
                }, {
                    title: '',
                    data: null,
                    class: 'notexport',
                    render: function (data, type, row) {
                        var dataToSend = {};
                        dataToSend["id"] = row.id;
                        dataToSend["c"] = row.courseId;
                        dataToSend["b"] = row.batchId;
//                        console.log("JSON :" + JSON.stringify(dataToSend));
                        return "<button class='btn-primary notexport' id='feebtn' data-id=" + JSON.stringify(dataToSend) + " data-toggle='modal' data-target='#feePayModal' data-whatever=''>Pay Fee</button>";
                    }
                }, {
                    title: '',
                    data: null,
                    class: 'notexport',
                    render: function (data, type, row) {
                        var dataToSend = {};
                        dataToSend["id"] = row.id;
                        return "<button class='btn-primary notexport' id='feehistorybtn' data-id=" + JSON.stringify(dataToSend) + " data-toggle='modal' data-target='#feepaymenthistory' data-whatever=''>View Fee History</button>";
                    }
                }, {
                    title: '',
                    data: null,
                    class: 'notexport',
                    render: function (data, type, row) {
                        var dataToSend = {};
                        dataToSend["id"] = row.id;
                        return "<button class='btn-primary notexport' id='disablestudentbtn' data-id=" + JSON.stringify(dataToSend) + " data-toggle='modal' data-target='' data-whatever='' >Disable</button>";
                    }
                }
            ],
//            "dom": 'T<"clear">lfrtip',
            dom: 'Bfrtip',
            buttons: [
//                'copy', 'csv', 'excel', 'pdf', 'print'
//                 'csv', 'excel', 'pdf', 'print'
                {
                    extend: 'csvHtml5',
                    title: 'Student Data Export',
                    exportOptions: {
                        columns: ':visible:not(.notexport)',
                        orthogonal: 'export'
                    }
                },
                {
                    extend: 'excelHtml5',
                    title: 'Student Data Export',
                    exportOptions: {
                        columns: ':visible:not(.notexport)',
                        orthogonal: 'export'
                    }
                },
                {
                    extend: 'pdfHtml5',
                    title: 'Student Data Export',
                    exportOptions: {
//                        columns: [0,1],
                        columns: ':visible:not(.notexport,.notexportpdf)'
                    },
                    orientation: 'landscape',
                    pageSize: 'LEGAL'
                }
            ],
//            scrollCollapse: true,
            "columnDefs": [
                {
                    "targets": 4,
                    "createdCell": function (td, cellData, rowData, row, col) {
                        if (rowData.gender === 'female') {
                            $(td).css('color', 'red')
                        } else if (rowData.gender === 'male') {
                            $(td).css('color', 'green')
                        }
                    }
                },
                {
                    "targets": [15,18],
                    "visible": (window.roleId == "1") ? true : false
                },
                {
                    "targets": [16,17],
                    "visible": (window.roleId == "1" || window.roleId == "4") ? true : false
                }
//                {
//                    "targets": 9,
//                    "data": null,
//                    "defaultContent": "<button>Click!</button>"
//                }
            ]

        });

    }
    ;

    fetchDataForEditModal = function (studentId) {
//        alert(window.roleId);
//        if(window.roleId == "3"){
//            return false;
//        }
        
        var data = {};
        data["studentId"] = studentId;
        var url = "student/getstudentdetail?studentId=" + studentId;
        $.ajax({
            type: "POST",
            url: url,
//            data: JSON.stringify(data),
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                if (response.success === true) {
                    var data = response.data;
                    $("#edit_id").val(data.id);
                    $("#edit_firstName").val(data.firstName);
                    $("#edit_middleName").val(data.middleName);
                    $("#edit_lastName").val(data.lastName);
                    $("#edit_address").val(data.address);
                    $("#edit_mobileNumber").val(data.mobileNumber);
                    $("#edit_emailId").val(data.emailId);
                    $("#edit_guardianFullName").val(data.guardianFullName);
                    $("#edit_guardianFullAddress").val(data.guardianFullAddress);
                    $("#edit_guardianMobileNumber").val(data.guardianMobileNumber);
                    $("#edit_aadharNumber").val(data.aadharNumber);

                } else {
                    error(response.message);
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
                error("Student data load failed");
            }
        });
    }

    $("#editStudentbtn").on('click', function (e) {

        var $confirm = $("#modalConfirmYesNo");
        $confirm.modal('show');
        $("#lblTitleConfirmYesNo").html("Confirmation");
        $("#lblMsgConfirmYesNo").html("Are you sure you want to update this data?");
        $("#btnYesConfirmYesNo").off('click').click(function () {
            $confirm.modal("hide");
            var data = {};
            data["id"] = $("#edit_id").val();
            data["firstName"] = $("#edit_firstName").val();
            data["middleName"] = $("#edit_middleName").val();
            data["lastName"] = $("#edit_lastName").val();
            data["address"] = $("#edit_address").val();
            data["mobileNumber"] = $("#edit_mobileNumber").val();
            data["emailId"] = $("#edit_emailId").val();
            data["guardianFullName"] = $("#edit_guardianFullName").val();
            data["guardianFullAddress"] = $("#edit_guardianFullAddress").val();
            data["guardianMobileNumber"] = $("#edit_guardianMobileNumber").val();
            data["aadharNumber"] = $("#edit_aadharNumber").val();

            var url = "student/editstudent";

            $.ajax({
                type: "POST",
                url: url,
                data: JSON.stringify(data),
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                success: function (response) {
                    if (response.success === true) {
                        success(response.message);

                        $("#editmodalclosebtn").trigger("click");
                        studentDatatable();
                        document.getElementById("editstudentform").reset();
                    } else {
                        error("Student data update failed");
                    }
                },
                error: function (e) {
                    console.log("ERROR: ", e);
                    error("Something went wrong");
                }
            });
        });
        $("#btnNoConfirmYesNo").off('click').click(function () {
            $confirm.modal("hide");
            return false;
        });

    });


    function getRemainingFeeForStudent(studentId, courseId, batchId) {
        var data = {};
        data["studentId"] = studentId;
        data["courseId"] = courseId;
        data["batchId"] = batchId;

        var url = "student/getremainingfeeforstudent";

        $.ajax({
            type: "POST",
            url: url,
            data: JSON.stringify(data),
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                if (response.success === true) {
                    var data = response.data;
                    $("#total_fee").val(data.totalFee);
                    $("#discount").val(data.discount);
                    $("#remaining_fee").val(data.remainingFee);
                    $("#total_fee_paid").val(data.amountPaid);

                    $("#studentName").val(data.studentName);
                    $("#batchName").val(data.batchName);
                    $("#courseName").val(data.courseName);
                    $("#enrollmentNumber").val(data.enrollmentNumber);

                    if (data.remainingFee == 0 || data.remainingFee == "0") {
                        $("#savebtn").prop("disabled", true);
                        $("#paying_fee").prop("disabled", true);
                    } else {
                        $("#paying_fee").prop("disabled", false);
                    }

                } else {
                    error(response.message);
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
                error("Remaining fee load failed");
            }
        });
    }

    $("#savebtn").on('click', function () {
        var data = {};
        data["studentId"] = $("#studentId").val();
        data["courseId"] = $("#courseId").val();
        data["batchId"] = $("#batchId").val();
        data["amountPaid"] = $("#paying_fee").val();
        data["feeDescription"] = $("#fee_description").val();
        data["paymentMode"] = $("input[name='payment_mode']:checked").val();
        var payment_mode = $("input[name='payment_mode']:checked").val();
        var paymentDetail = "";

        if (payment_mode === "cheque") {
            paymentDetail += ("Bank Name : " + $("#bank_name").val() + "<br/>");
            paymentDetail += ("Cheque Number : " + $("#cheque_number").val() + "<br/>");
            paymentDetail += ("Cheque Date : " + $("#cheque_date").val());
        } else if (payment_mode === "NEFT") {
            paymentDetail += ("Bank Name : " + $("#bank_name").val() + "<br/>");
            paymentDetail += ("Transaction Id : " + $("#transaction_id").val() + "<br/>");
            paymentDetail += ("Transaction Date : " + $("#transaction_date").val());
        }

        data["paymentDetail"] = paymentDetail;

        var isPartial = ($("#remaining_fee").val() - $("#paying_fee").val()) > 0 ? "Partial" : "Full";
        var url = "student/paystudentfee";

        $.ajax({
            type: "POST",
            url: url,
            data: JSON.stringify(data),
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                if (response.success === true) {
                    success(response.message);
                    var feePaymentId = response.payment_id;
                    $("#print_id").html(feePaymentId);
                    $("#print_date").html(new Date().toISOString().slice(0, 10));
                    $("#print_student_name").html($("#studentName").val());
                    $("#print_paid_fee_in_words").html(toWords($("#paying_fee").val()));
                    $("#print_paying_fee").html($("#paying_fee").val());
                    $("#print_payment_mode").html($("input[name='payment_mode']:checked").val());
                    $("#print_partial_full_payment").html(isPartial);
                    $("#print_total_fee").html($("#total_fee").val());
                    $("#print_fee_description").html($("#fee_description").val());

                    $("#modalclosebtn").trigger("click");
                    //$("#feepaymodalclosebtn").trigger("click");
                    $("#printbtn").trigger("click");
                    $("#savebtn").prop("disabled", true);

                    document.getElementById("feepaymentform").reset();
                } else {
                    error("Fee Payment failed");
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
                error("Something went wrong");
            }
        });
    });
    
    
    $(document).on("click", "#disablestudentbtn", function () {
        var myDataJSON = $(this).data('id');
        var studentId = myDataJSON.id;

        var $confirm = $("#modalConfirmYesNo");
        $confirm.modal('show');
        $("#lblTitleConfirmYesNo").html("Confirmation");
        $("#lblMsgConfirmYesNo").html("Once you disable the student,he/she can't be enabled and can't be shown anywhere in application.Are you sure you want to disable ?");

        $("#btnYesConfirmYesNo").off('click').click(function () {
            $confirm.modal("hide");

            // get form data
            var data = {}
            data["id"] = studentId;
            var url = "student/disable?id="+studentId;

            $.ajax({
                type: "GET",
                url: url,
//                data: JSON.stringify(data),
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                success: function (response) {
                    var message = response.message;
                    if (response.success == true) {
                        success(message);
                        studentDatatable();
                    } else if (response.success == false && response.error == true) {
                        error(message);
                    }

                },
                error: function (e) {
                    console.log("ERROR: ", e);
                    error("Couldn't be disabled.");
                }
            });

        });
        $("#btnNoConfirmYesNo").off('click').click(function () {
            $confirm.modal("hide");
            return false;
        });
    });


    function studentFeeHistoryDatatable(studentId) {

        var tbl1 = $('#studentFeeHistory').dataTable({
            "processing": true,
            "destroy": true,
            "responsive": true,
            "ajax": {
                "url": "student/getstudentfeehistory",
                "type": "POST",
                "data": function (d) {
                    d.studentId = studentId;
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
                    title: 'Receipt No.',
                    data: 'id'
                },
                {
                    title: 'Mode of Payment',
                    data: 'paymentMode'
                },
                {
                    title: 'Payment Detail',
                    data: 'paymentDetail'
                },
                {
                    title: 'Amount',
                    data: 'amountPaid'
                },
                {
                    title: 'Fee Description',
                    data: 'feeDescription'
                }, {
                    title: 'Paid On',
                    data: 'paymentDate',
                    "type": "date",
                    "render": function (data) {
                        var date = new Date(data);
                        var month = date.getMonth() + 1;
                        return (date.getDate() >= 10 ? date.getDate() : "0" + date.getDate()) + "-" + (month >= 10 ? month : "0" + month) + "-" + date.getFullYear();
                        //return date;
                    }
                }, {
                    title: '',
                    data: null,
                    render: function (data, type, row) {
                        var dataToSend = {};
                        dataToSend["id"] = row.id;
                        dataToSend["studentId"] = row.studentId;
                        return "<button class='btn-primary' id='feedeletebtn' data-id=" + JSON.stringify(dataToSend) + " data-toggle='modal' data-target='#' data-whatever=''>Undo</button>";
                    }
                }
            ],
            "dom": 'T<"clear">lfrtip'
        });
    };

    function deleteStudentFeeRecord(feePaymentId, studentId) {
        var url = "student/deletefeerecord";
        var data = {};
        data["feePaymentId"] = feePaymentId.toString();

        $.ajax({
            type: "GET",
            url: url,
            data: data,
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                if (response.success === true) {
                    success(response.message);
                    studentFeeHistoryDatatable(studentId);
                } else {
                    error(response.message);
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
                error("Something went wrong");
            }
        });
    };


    var th = ['', 'Thousand', 'Million', 'Billion', 'Trillion'];
    var dg = ['Zero', 'One', 'Two', 'Three', 'Four', 'Five', 'Six', 'Seven', 'Eight', 'Nine'];
    var tn = ['Ten', 'Eleven', 'Twelve', 'Thirteen', 'Fourteen', 'Fifteen', 'Sixteen', 'Seventeen', 'Eighteen', 'Nineteen'];
    var tw = ['Twenty', 'Thirty', 'Forty', 'Fifty', 'Sixty', 'Seventy', 'Eighty', 'Ninety'];

    function toWords(s) {
        s = s.toString();
        s = s.replace(/[\, ]/g, '');
        if (s != parseFloat(s))
            return 'not a number';
        var x = s.indexOf('.');
        if (x == -1)
            x = s.length;
        if (x > 15)
            return 'too big';
        var n = s.split('');
        var str = '';
        var sk = 0;
        for (var i = 0; i < x; i++) {
            if ((x - i) % 3 == 2) {
                if (n[i] == '1') {
                    str += tn[Number(n[i + 1])] + ' ';
                    i++;
                    sk = 1;
                } else if (n[i] != 0) {
                    str += tw[n[i] - 2] + ' ';
                    sk = 1;
                }
            } else if (n[i] != 0) {
                str += dg[n[i]] + ' ';
                if ((x - i) % 3 == 0)
                    str += 'Hundred ';
                sk = 1;
            }
            if ((x - i) % 3 == 1) {
                if (sk)
                    str += th[(x - i - 1) / 3] + ' ';
                sk = 0;
            }
        }
        if (x != s.length) {
            var y = s.length;
            str += 'point ';
            for (var i = x + 1; i < y; i++)
                str += dg[n[i]] + ' ';
        }
        return str.replace(/\s+/g, ' ');
    }
});