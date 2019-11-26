<%@ include file="/WEB-INF/tags/layout/includes.jsp"%>
<t:dashboard>
    <jsp:attribute name="header">
        <!-- DATA TABES SCRIPT -->
        <script src=<c:url value="/resources/js/jschool/viewFeeToStudent.js"/> type="text/javascript"></script>
        
        
          <link href=<c:url value="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css"/> rel="stylesheet" type="text/css" />
        <link href=<c:url value="https://cdn.datatables.net/buttons/1.6.0/css/buttons.dataTables.min.css"/> rel="stylesheet" type="text/css" />

        <script src=<c:url value="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"/> type="text/javascript"></script>
        <script src=<c:url value="https://cdn.datatables.net/buttons/1.6.0/js/dataTables.buttons.min.js"/> type="text/javascript"></script>
        <script src=<c:url value="https://cdn.datatables.net/buttons/1.6.0/js/buttons.flash.min.js"/> type="text/javascript"></script>
        <script src=<c:url value="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"/> type="text/javascript"></script>
        <script src=<c:url value="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"/> type="text/javascript"></script>
        <script src=<c:url value="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"/> type="text/javascript"></script>
        <script src=<c:url value="https://cdn.datatables.net/buttons/1.6.0/js/buttons.html5.min.js"/> type="text/javascript"></script>
        <script src=<c:url value="https://cdn.datatables.net/buttons/1.6.0/js/buttons.print.min.js"/> type="text/javascript"></script>
    </jsp:attribute>
    <jsp:body>
        <div class="row">
            <div class="col-md-12">



            </div>
           <div class="box-body">
                <form name="student_result_form" action="" method="post" class="form-horizontal" id="student_result_form" enctype="multipart/form-data">
                            <div class="form-group">
                                 <input type="hidden" name="studentId" id="studentId" value="${studentId}"/>
                                <input type="hidden" id="csrfToken" value="${_csrf.token}"/>
                                <input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>
                                <!-- Get User information like userid or user name -->
                                <input type="hidden" id="userId" value="${user.userId}"/>
                            </div>
                </form>
                <div class="box-body table-responsive">
                    <table id="studentFeeHistory" class="table table-striped table-bordered cell-border display"  style="width:100%">
                    </table>
                </div><!-- /.box-body -->
            </div>



        </div>
    </div>
</jsp:body>
</t:dashboard>
