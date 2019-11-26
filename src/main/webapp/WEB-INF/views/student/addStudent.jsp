<%@ include file="/WEB-INF/tags/layout/includes.jsp"%>
<t:dashboard>
    <jsp:attribute name="header">
        <!-- DATA TABES SCRIPT -->
        <script src=<c:url value="/resources/js/jschool/addStudent.js"/> type="text/javascript"></script>
    </jsp:attribute>
    <jsp:body>
        <div class="row">
            <div class="col-md-12">
                <div class="box box-default" data-collapsed="0">
                    <div class="box-header with-border">
                        <div class="box-title">
                            <span><i class="fa fa-plus"></i>
                                Student Admission Form</span>            	
                        </div>
                    </div>
                    <div class="box-body">
                        <form name="student_form" action="" method="post" class="form-horizontal" id="student_form" enctype="multipart/form-data">
                            <div class="form-group">
                                <input type="hidden" id="csrfToken" value="${_csrf.token}"/>
                                <input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>
                                <!-- Get User information like userid or user name -->
                                <input type="hidden" id="userId" value="${user.userId}"/>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="enrollmentNumber">Enrollment Number<span class="require-field">*</span></label>
                                <div class="col-sm-8">
                                    <input id="enrollmentNumber" class="form-control validate[required]" type="text" value="" name="enrollmentNumber" placeholder="Enrollment Number">
                                </div>
                            </div>
                            
                            
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="">Enrollment Detail<span class="require-field">*</span></label>
                                
                                <div class="col-sm-3">
                                    <!--<select name="courseId" class="form-control select2 text-input validate[required]" id="courseCombo" style="width: 100%" placeholder="Select Course">-->
                                    <select name="courseId" class="form-control select2 text-input validate[required]" id="courseCombo" style="width: 100%" placeholder="Select Course">
                                        <option value=""></option>
                                    </select>
                                </div>
                                <div class="col-sm-3">
                                     <select name="batchId" class="form-control select2 text-input validate[required]" id="batchCombo" style="width: 100%" placeholder="Select Batch">
                                        <option value=""></option>
                                    </select>
                                </div>
<!--                                <div class="col-sm-2">
                                    <select name="semesterId" class="form-control select2 validate[required]" id="semesterCombo" style="width: 100%" placeholder="Select Semester">
                                        <option value=""></option>
                                    </select>
                                </div>-->
                            </div>
                            
                            <!-- 
                            
                            add html code to upload student photo
                            
                            -->
                            
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="">Student Name<span class="require-field">*</span></label>
                                <div class="col-sm-3">
                                    <input id="first_name" class="form-control validate[required] text-input" type="text" value="" name="firstName" placeholder="First Name">
                                </div>
                                <div class="col-sm-2">
                                     <input id="middle_name" class="form-control validate[required] text-input" type="text" value="" name="middleName"  placeholder="Middle Name">
                                </div>
                                <div class="col-sm-3">
                                    <input id="last_name" class="form-control validate[required] text-input" type="text" value="" name="lastName" placeholder="Last Name">
                                </div>
                            </div>
                            
                           

                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="gender">Gender<span class="require-field">*</span></label>
                                <div class="col-sm-8">
                                    <label class="radio-inline col-sm-1">
                                        <input type="radio" value="male" class="tog validate[required]" name="gender" checked="checked">Male
                                    </label>
                                    <label class="radio-inline col-sm-2">
                                        <input type="radio" value="female" class="tog validate[required]" name="gender">Female 
                                    </label>

                                </div>
                            </div>
                            
                            <div class="form-group ">
                                <label class="col-sm-2 control-label" for="birth_date">Date of birth<span class="require-field">*</span></label>
                                <div class="col-sm-8">
                                <div class="col-sm-4 input-group date" data-provide="datepicker" data-date-format="yyyy-mm-dd">
                                    <input id="birth_date" class="form-control validate[required] hasDatepicker" type="text" name="birthDate" value="">
                                    <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                                </div>
                            </div>	
                            
                            <div class="form-group ">
                                <label class="col-sm-2 control-label" for="enrollment_date">Date of Enrollment<span class="require-field">*</span></label>
                                <div class="col-sm-8">
                                <div class="col-sm-4 input-group date" data-provide="datepicker" data-date-format="yyyy-mm-dd">
                                    <input id="enrollment_date" class="form-control validate[required] hasDatepicker" type="text" name="enrollmentDate" value="">
                                    <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="address">Address<span class="require-field">*</span></label>
                                <div class="col-sm-8 shadow-textarea">
                                    <textarea class="form-control z-depth-1 validate[required]" id="address" name="address" rows="3" placeholder="Student Full Address"></textarea>
                                </div>
                            </div>
                            
                            
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="mobile_number">Mobile Number<span class="require-field">*</span></label>
                                <div class="col-sm-2">
                                    <input type="text" readonly value="+91" class="form-control text-input" name="phonecode">
                                </div>
                                <div class="col-sm-6">
                                    <input id="mobile_number" class="form-control text-input validate[required]" type="text" name="mobileNumber" value="" placeholder="Mobile Number">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="email">Email Id<span class="require-field">*</span></label>
                                <div class="col-sm-8">
                                    <input id="email" class="form-control validate[required]" type="email" name="emailId" value="" placeholder="Email">
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="guardian_full_name">Parent/Guardian Full Name<span class="require-field">*</span></label>
                                <div class="col-sm-8">
                                    <input id="guardian_full_name" class="form-control validate[required]" type="text" name="guardianFullName" value="" placeholder="Parent/Guardian Full Name ">
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="guardian_full_address">Parent/Guardian Full Address<span class="require-field">*</span></label>
                                <div class="col-sm-8 shadow-textarea">
                                    <textarea class="form-control z-depth-1 validate[required]" id="guardian_full_address" name="guardianFullAddress" rows="3" placeholder="Parent/Guardian Full Address"></textarea>
                                </div>
                            </div>
                            
                            
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="guardian_mobile_number">Parent/Guardian Mobile Number<span class="require-field">*</span></label>
                                <div class="col-sm-2">

                                    <input type="text" readonly value="+91" class="form-control text-input" name="phonecode1">
                                </div>
                                <div class="col-sm-6">
                                    <input id="guardian_mobile_number" class="form-control text-input validate[required]" type="text" name="guardianMobileNumber" value="" placeholder="Parent/Guardian Mobile Number">
                                </div>
                            </div>

                             <div class="form-group">
                                <label class="col-sm-2 control-label" for="aadharcardnumber">Aadhar Card Number<span class="require-field">*</span></label>
                                <div class="col-sm-8">
                                    <input id="aadharcardnumber" class="form-control validate[required]" type="text" name="aadharcardnumber" value="" maxlength="12" pattern=".{12,12}" placeholder="Aadhar Card Number(must be of 12 character long)">
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="username">User Name<span class="require-field">*</span></label>
                                <div class="col-sm-8">
                                    <input id="username" class="form-control validate[required]" type="text" name="username" value="" placeholder="User Name">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="password">Password<span class="require-field">*</span></label>
                                <div class="col-sm-8">
                                    <input id="password" class="form-control validate[required]" type="text" name="password" value="">
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="blood_group">Blood Group</label>
                                <div class="col-sm-8">
                                <select name="bloodGroup" class="form-control select2 text-input" id="blood_group" style="width: 18%" >
                                        <option value=""></option>
                                        <option value="AB+">AB+</option>
                                        <option value="AB-">AB-</option>
                                        <option value="O+">O+</option>
                                        <option value="O-">O-</option>
                                        <option value="A+">A+</option>
                                        <option value="A-">A-</option>
                                        <option value="B+">B+</option>
                                        <option value="B-">B-</option>
                                </select>
                                </div>
                            </div>
                            
                            
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="gender">Any Disability ?<span class="require-field">*</span></label>
                                <div class="col-sm-8">
                                    <label class="radio-inline">
                                        <input type="radio" value="no" class="tog validate[required]" name="disability" checked="checked">No
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" value="yes" class="tog validate[required]" name="disability">Yes 
                                    </label>
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for=""></label>
                                <div class="col-sm-8">
                                    <input id="disability_detail" class="form-control validate[required]" type="text" name="disabilityDetail" value="" placeholder="Disability Detail">
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="qualification">Qualification<span class="require-field">*</span></label>
                                <div class="col-sm-8">
                                    <input id="qualification" class="form-control validate[required]" type="text" name="qualification" value="">
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="total_fee">Total Fee</label>
                                <div class="col-sm-3">
                                    <input id="total_fee" class="form-control text-input" type="number" value="" disabled="disabled" name="total_fee" placeholder="" value="0">
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="discount">Discount(in rupees)<span class="require-field">*</span></label>
                                <div class="col-sm-3">
                                    <input id="discount" class="form-control validate[required] text-input" type="number" value="0" name="discount" placeholder="First Name">
                                </div>
                            </div>
                            

                            <div class="col-sm-offset-2 col-sm-8">
                                <input type="submit" value="Add Student" name="save_student" class="btn btn-success">
                            </div>


                        </form>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:dashboard>
