<%@ include file="/WEB-INF/tags/layout/includes.jsp"%>
<t:dashboard>
    <jsp:attribute name="header">
        <!-- DATA TABES SCRIPT -->
        <script src=<c:url value="/resources/js/jschool/uploadStudentResult.js"/> type="text/javascript"></script>
    </jsp:attribute>
    <jsp:body>
        <div class="row">
            <div class="col-md-12">
                <div class="box box-default" data-collapsed="0">
                    <div class="box-header with-border">
                        <div class="box-title">
                            <span><i class="fa fa-check-circle"></i>
                                Upload Student Result</span>            	
                        </div>
                    </div>
                    <div class="box-body">
                        <form name="student_result_form" action="uploadStudentResult" method="post" class="form-horizontal" id="student_result_form" enctype="multipart/form-data">
                            <div class="form-group">
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

<!--                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="month"><span class="require-field">*</span></label>
                                <div class="col-sm-3">
                                  
                                </div>
                                
                            </div>-->

                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="file">Select File To Upload<span class="require-field">*</span></label>
                                <div class="col-sm-3 ">
                                    <input type="file" name="file"  class="form-control z-depth-1 " id="file" >
                                </div>
                            </div>

                            <div class="col-sm-offset-2 col-sm-8">
                                 <input type="button" value="Download Sample File" name="sample_file_download" id="sample_file_download" class="btn btn-warning">
                                <!--<input type="submit" value="Upload Result" name="save_student_result" class="btn btn-success">-->
                                <input type="submit" value="Upload Result" name="save_student_result" class="btn btn-success">
                            </div>


                        </form>
                            <br><br/>
                           
                  <!--          <div>
                                <h3><b>Note for Uploading Sheet :</b></h3>
                                <ul>
                                    <li>Do not change the sheet format</li>
                                    <li>Fill your details in the exported sheet for specified month</li>
                                    <li>Do not add any extra column into sheet</li>
                                    <li>Remove extra date column from Sheet
                                        <br/>Ex. 29 from February month Sheet if it is non-leap year.</li>
                                    <li>If you are uploading partial attendance of month,then remove extra date columns from Sheet.
                                        <br/>Ex. if you are uploading attendance of January month and upto data 8th then, remove 9 to 31 date column from sheet.</li>
                                    <li>Do not fill empty values in sheet.Use below values for filling Attendance Sheet.
                                        <ul><b>
                                            <li style="color: red">Absent  : A</li>
                                            <li style="color: green">Present : P</li>
                                            <li style="color: greenyellow">Holiday : H</li>
                                            <li style="color: blue">Leave   : L</li></b>
                                        </ul>
                                    </li>
                                </ul>
                            </div>    -->
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:dashboard>
