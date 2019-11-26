$(document).ready(function ($) {
   
   studentFeeHistoryDatatable($("#studentId").val());
   
  
    function studentFeeHistoryDatatable(studentId) {

        var tbl1 = $('#studentFeeHistory').dataTable({
            "processing": true,
            "destroy": true,
            "responsive": true,
            "ajax": {
                "url": "getstudentfeehistory",
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
                    title: 'Receipt No',
                    data: 'id'
                },
                {
                    title: 'Mode of Payment',
                    data: 'paymentMode'
                }, {
                    title: 'Payment Detail',
                    data: 'paymentDetail'
                }, {
                    title: 'Amount',
                    data: 'amountPaid'
                },
                {
                    title: 'Fee Description',
                    data: 'feeDescription'
                },{
                    title: 'Paid On',
                    data: 'paymentDate',
                    "type": "date",
                    "render": function (data) {
                        var date = new Date(data);
                        var month = date.getMonth() + 1;
                        return (date.getDate() >= 10 ? date.getDate() : "0" + date.getDate()) + "-" + (month >= 10 ? month : "0" + month) + "-" + date.getFullYear();
                        //return date;
                    }
                }
            ],
//            "dom": 'T<"clear">lfrtip'
            dom: 'Bfrtip',
            buttons: [
                {
                    extend: 'csvHtml5',
                    title: 'Student Fee Export',
                    exportOptions: {
                        columns: ':visible:not(.notexport)',
                        orthogonal: 'export'
                    }
                },
                {
                    extend: 'excelHtml5',
                    title: 'Student Fee Export',
                    exportOptions: {
                        columns: ':visible:not(.notexport)',
                        orthogonal: 'export'
                    }
                }
            ]
        });
    };

});