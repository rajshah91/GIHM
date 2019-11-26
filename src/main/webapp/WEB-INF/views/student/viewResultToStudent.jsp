<%@ include file="/WEB-INF/tags/layout/includes.jsp"%>
<t:dashboard>
    <jsp:attribute name="header">
        <!-- DATA TABES SCRIPT -->
        <script src=<c:url value="/resources/js/jschool/viewResultToStudent.js"/> type="text/javascript"></script>

        <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="https://cdn.datatables.net/buttons/1.5.2/js/dataTables.buttons.min.js" type="text/javascript"></script>
        <script src="https://cdn.datatables.net/buttons/1.5.2/js/buttons.print.min.js" type="text/javascript"></script>

        <link href='https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css' rel="stylesheet" type="text/css" />
        <link href='https://cdn.datatables.net/buttons/1.5.2/css/buttons.dataTables.min.css' rel="stylesheet" type="text/css" />

        <style>
            .dataTables_scrollHeadInner {
                width:100% !important;
                padding: 0 !important;
            }
        </style>
    </jsp:attribute>

    <jsp:body>
        <div class="row">
            <div class="col-md-12">
                <div class="box box-default" data-collapsed="0">
                    <div class="box-header with-border">
                        <div class="box-title">
                            <span><i class="fa fa-check-circle"></i>
                                View My Result</span>            	
                        </div>
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

                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="course">Course<span class="require-field">*</span></label>
                                <div class="col-sm-3">
                                    <select name="courseId" class="form-control select2 text-input validate[required]" id="courseCombo" style="width: 100%" placeholder="Select Course">
                                        <option value=""></option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="batch">Batch<span class="require-field">*</span></label>
                                <div class="col-sm-3">
                                    <select name="batchId" class="form-control select2 text-input validate[required]" id="batchCombo" style="width: 100%" placeholder="Select Batch">
                                        <option value=""></option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="semester">Semester<span class="require-field">*</span></label>
                                <div class="col-sm-3">
                                    <select name="semesterId" class="form-control select2 validate[required]" id="semesterCombo" style="width: 100%" placeholder="Select Semester">
                                        <option value=""></option>
                                    </select>
                                </div>
                            </div>

                            <div class="col-sm-offset-2 col-sm-8">
                                <input type="submit" value="View Result" name="view_result" class="btn btn-success">
                            </div>
                            


                        </form>


                    </div>

                    <!-- =========================== Search Datatable Start ======================== -->
                    <div class="box-body" id="">
                        <div class="box-body table-responsive">
                            <!--                            <table id="studentAttendanceTable" class="table table-striped table-bordered cell-border display"  style="width:100%">
                                                        </table>-->
                        </div><!-- /.box-body -->


                        <!--  START OF  DATA TABLE STATIC DATA  -->
                        <div id="tablediv" style="overflow: scroll">
                            
                        </div>


                        <!--   END OF DATA TABLE STATIC DATA  -->
                        
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:dashboard>
