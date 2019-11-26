<%@ include file="/WEB-INF/tags/layout/includes.jsp"%>
<t:dashboard>
   
    
    <jsp:attribute name="header">
        
         <script type="text/javascript">
            window.roleId=${user.role.roleId};
            window.roleName=${user.role.roleName};
        </script>
        
        <!-- DATA TABES SCRIPT -->
        <script src=<c:url value="/resources/js/jschool/students.js"/> type="text/javascript"></script>

<!--<script src=<c:url value="https://code.jquery.com/jquery-3.3.1.js"/> type="text/javascript"></script>-->

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

<!--        <link href=<c:url value="https://cdn.jsdelivr.net/gh/xxjapp/xdialog@3/xdialog.min.css"/> rel="stylesheet" type="text/css" />
        <script src=<c:url value="https://cdn.jsdelivr.net/gh/xxjapp/xdialog@3/xdialog.min.js"/> type="text/javascript"></script>-->

    </jsp:attribute>
    <jsp:body>
        <div class="row">
            <div class="col-md-12">
                <div class="box box-default" data-collapsed="0">
                    <div class="box-header with-border">
                        <div class="box-title">
                            <span><i class="fa fa-plus"></i>
                                Student Detail
                            </span>            	
                        </div>
                    </div>

                    <div class="box-body"> 
                        <form method="post">  

                            <!--<div class="input-group">-->

                            <div class="form-group">
                                <div class="col-sm-4">
                                    <select name="search_field" class="form-control select2" id="search_field" style="width: 100%"  >
                                        <option value="">Select Field For Search</option>
                                        <option value="enrollmentNumber">Enrollment Number</option>
                                        <option value="firstName">First Name</option>
                                        <option value="mobileNumber">Mobile Number</option>
                                        <option value="emailId">Email</option>
                                        <option value="courseId.courseName">Course</option>
                                    </select>
                                </div>
                                <div class="col-sm-4">
                                    <input id="search_value" class="form-control text-input" type="text" name="search_value" value=""  placeholder="Search Here...">
                                </div>
                            </div>
                            <!--</div>-->
                        </form>
                    </div>


                    <!-- =========================== Search Datatable Start ======================== -->
                    <div class="box-body">
                        <div class="box-body table-responsive">
                            <table id="studentTable" class="table table-striped table-bordered cell-border display nowrap"  style="width:100%">
                            </table>
                        </div><!-- /.box-body -->
                    </div>

                </div>



                <!---------------------------------------------------Modal div --------------------------------------------------->            

                <!--                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#feePayModal" data-whatever="">Pay Fee</button>-->

                <div class="modal fade" id="feePayModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="modalLabel">Fee Payment</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <form method="post" id="feepaymentform" action="" class="" style="height: auto">


                                    <div class="form-group">
                                        <input type="hidden" id="csrfToken" value="${_csrf.token}"/>
                                        <input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>
                                        <!-- Get User information like userid or user name -->
                                        <input type="hidden" id="userId" value="${user.userId}"/>
                                    </div>

                                    <!--                                    <div class="form-group">
                                                                            <label class="col-sm-2 control-label" for="enrollmentNumber">Enrollment Number<span class="require-field">*</span></label>
                                                                            <div class="col-sm-8">
                                                                                <input id="enrollmentNumber" class="form-control validate[required]" type="text" value="" name="enrollmentNumber" placeholder="Enrollment Number">
                                                                            </div>
                                                                        </div>-->
                                    <div class="form-group">
                                        <input type="hidden" id="courseId" value=""/>
                                        <input type="hidden" id="batchId" value=""/>
                                        <input type="hidden" id="studentId" value=""/>

                                        <input type="hidden" id="studentName" value=""/>
                                        <input type="hidden" id="batchName" value=""/>
                                        <input type="hidden" id="courseName" value=""/>
                                        <input type="hidden" id="enrollmentNumber" value=""/>

                                        <!--                                        <label for="semesterId" class="col-form-label">Semester<span class="require-field">*</span></label>
                                                                                <select name="semesterId" class="form-control select2 validate[required]" id="semesterCombo" style="width: 100%" placeholder="Select Semester">
                                                                                    <option value=""></option>
                                                                                </select>-->
                                    </div>
                                    <div class="form-group">
                                        <label for="recipient-name" class="col-form-label">Total Fee &#8377;</label>
                                        <input type="number" disabled="disabled" class="form-control" id="total_fee" value="0">
                                    </div>


                                    <div class="form-group">
                                        <label for="recipient-name" class="col-form-label">Discount &#8377;</label>
                                        <input type="number" disabled="disabled"  class="form-control" id="discount" value="0">
                                    </div>


                                    <div class="form-group">
                                        <label for="recipient-name" class="col-form-label">Total Fee Paid till now &#8377;</label>
                                        <input type="number" disabled="disabled"  class="form-control" id="total_fee_paid" value="0">
                                    </div>

                                    <div class="form-group">
                                        <label for="recipient-name" class="col-form-label">Remaining Fee &#8377;</label>
                                        <input type="text" disabled="disabled" class="form-control" id="remaining_fee" value="0">
                                    </div>


                                    <div class="form-group">
                                        <label for="recipient-name" class="col-form-label">Currently Paying Fee &#8377;</label>
                                        <input type="number"  class="form-control" id="paying_fee" value="0">
                                    </div>

                                    <div class="form-group">
                                        <label for="recipient-name" class="col-form-label">Fee Description</label>
                                        <input type="text"  class="form-control" id="fee_description" value="" maxlength="120">
                                    </div>

                                    <div class="form-group">
                                        <label class="col-form-label" for="payment_type">Payment Mode<span class="require-field">*</span></label>
                                        <div class="form-group">
                                            <label class="radio-inline col-sm-2">
                                                <input type="radio" value="cash" class="tog validate[required]" name="payment_mode" checked="checked">Cash
                                            </label>
                                            <label class="radio-inline col-sm-2">
                                                <input type="radio" value="cheque" class="tog validate[required]" name="payment_mode">Cheque
                                            </label>
                                            <label class="radio-inline col-sm-2">
                                                <input type="radio" value="NEFT" class="tog validate[required]" name="payment_mode">NEFT
                                            </label>

                                            <!--                                            <label class="radio-inline col-sm-6">
                                                                                            <input type="text" value="" disabled="disabled" id="cheque_number" class="form-control" name="cheque_number" placeholder="Enter Cheque Number">
                                                                                            <input type="text" value="" disabled="disabled" id="cheque_bankname" class="form-control" name="cheque_bankname" placeholder="Enter Bank Name of Cheque">
                                                                                            <input type="text" value="" disabled="disabled" id="cheque_date" class="form-control" name="cheque_date" placeholder="Enter Cheque Date">
                                                                                        </label>-->
                                        </div>
                                    </div>



                                    <div class="clearfix"></div>
                                    <br/>

                                    <div class="form-group">

                                        <div id="bank_name_div">
                                            <label for="recipient-name" class="col-form-label">Bank Name</label>
                                            <input type="text"  class="form-control" id="bank_name" value="" maxlength="">
                                        </div>

                                        <div id="cheque_div">
                                            <label for="recipient-name" class="col-form-label">Cheque Number</label>
                                            <input type="text"  class="form-control" id="cheque_number" value="" maxlength="20">

                                            <label for="recipient-name" class="col-form-label">Cheque Date</label>
                                            <input type="text"  class="form-control" id="cheque_date" value="" maxlength="">
                                        </div>

                                        <div id="neft_div">
                                            <label for="recipient-name" class="col-form-label">Transaction Id</label>
                                            <input type="text"  class="form-control" id="transaction_id" value="" maxlength="">

                                            <label for="recipient-name" class="col-form-label">Transaction Date</label>
                                            <input type="text"  class="form-control" id="transaction_date" value="" maxlength="">

                                        </div>


                                    </div>



                                    <div class="clearfix"></div>

                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary" id="savebtn">Pay</button>
                                <input type="submit" class="btn btn-secondary" id="printbtn" onclick="printPage()" value="Print" style="display : none"/>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal" id="modalclosebtn">Close</button>
                            </div>
                        </div>
                    </div>
                </div>



                <!---------------------------------------------------Modal div End--------------------------------------------------->            

                <!---------------------------------------------Modal Fee Payment Receipt Start----------------------------------------->            


                <div class="modal fade" id="printModal" tabindex="-2" role="dialog" aria-labelledby="modalLabel" aria-hidden="true" style="height: auto">
                    <style>
                        @page { size: auto;  margin: 5mm; }
                    </style>
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="modalLabel">
                                    <!--Fee Payment Receipt-->
                                </h5>
                                <!--                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                                                </button>-->
                            </div>
                            <div class="modal-body">

 <!--<input type="text" class="form-control" id="username" readonly value="${user.username}">-->


                                <!-- =========================== Fee Receipt div Start ======================== -->
                                <div class="box-body table-responsive box box-primary" style="border : solid #285e8e 2px;" >
                                    <br/>
                                    <!--<img src="../../../resources/images/GIHM_LOGO.png" alt=""/>-->
                                    <center>
                                        <p style="color: #285e8e"><b>
                                                GUJARAT INSTITUTE OF HOTEL MANAGEMENT
                                                <br/>
                                                (VAID INSTITUTE OF MANAGEMENT)</b>
                                            <br/>
                                            VAID HOUSE,Next to Ashwamegh-1, Behind Sparc, Akota, Vadodara
                                            <br/>
                                            Phone : 2331112,2342699,2327060  Email : ac@gihm.in
                                            <br/>
                                            <br/>
                                        </p>
                                    </center>


                                    <!-- ${user.username}   -->
                                    <br/>

                                    <span style="color: #285e8e;float: left;">&nbsp;&nbsp;No.&nbsp;&nbsp;<span style="color: #9f191f;float: right" id="print_id">123456789</span></span> 
                                    <span style="color: #285e8e;float: right;margin-right: 2%">Date :&nbsp;&nbsp;<span style="color: #9f191f;float: right;" id="print_date">26-12-2018</span> </span> 

                                    <p style="color: #285e8e;margin-left: 2%;margin-right: 2%" ><br/><br/>
                                        <i> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            Received with thanks from 
                                            <u style="">
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <b><i><span id="print_student_name">Raj Kamal Shah</span></i></b>
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            </u>&nbsp;&nbsp;&nbsp;  Rupees 
                                            <u>
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <b><i><span id="print_paid_fee_in_words">Twenty thousands</span></i></b>
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            </u>&nbsp;&nbsp;&nbsp; by &nbsp;
                                            <u>

                                                <span id="print_payment_mode">Cash</span>

                                            </u> 
                                            &nbsp;   
                                            in 
                                            <span id="print_partial_full_payment">  Partial</span>
                                            &nbsp; payment of fees
                                            <u>
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <b></i><span id="print_total_fee">50000</span></i></b>
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;.
                                            </u> 
                                        </i>
                                    </p>

                                    <br/>

                                    &nbsp;&nbsp;&nbsp;&nbsp;

                                    <span id="print_fee_description" style="color: #285e8e;margin-left: 3%;"></span>

                                    <br/><br/>
                                    <table>
                                        <tr >
                                            <td style="width: 50%">
                                                <ul>
                                                    <li style="color: #285e8e">CHEQUE SUBJECT TO REALIZATION</li>
                                                    <li style="color: #285e8e">FEES PAID IS NON REFUNDABLE AND NON ADJUSTABLE</li>
                                                </ul></td>
                                            <td><center>
                                            <div style="color: #285e8e;border : solid #285e8e 2px;width: 70px;height: 70px;">

                                            </div></center>
                                        </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div style="color: #285e8e;border : solid #285e8e 2px;border-radius: 25px; width: 120px;height:30px ;margin-left: 3%;margin-bottom: 3%">
                                                    &nbsp;&nbsp; &#8377; &nbsp;&nbsp;
                                                    <b><span style="color: #9f191f;" id="print_paying_fee">20000</span></b>
                                                    &nbsp;&nbsp;
                                                </div>
                                            </td>

                                            <td>
                                                <span style="color: #285e8e;text-align: right;margin-bottom: 3%">
                                                    <center>Authorized Signatory </center>
                                                </span>
                                            </td>
                                        </tr>

                                    </table>
                                </div>

                                <!-- =========================== Fee Receipt div  END ========================== -->


                            </div>
                            <div class="modal-footer">

                            </div>
                        </div>
                    </div>
                </div>



                <!---------------------------------------------Modal Fee Payment Receipt End-------------------------------------------->            



                <!---------------------------------------------Modal Fee Payment History Start----------------------------------------->            


                <div class="modal fade" id="feepaymenthistory" tabindex="-3" role="dialog" aria-labelledby="modalLabel" aria-hidden="true" style="height: auto;width: 100%">
                    <style>
                        @page { size: auto;  margin: 5mm; }
                    </style>
                    <div class="modal-dialog" role="document" style="width:80%">
                        <div class="modal-content">
                            <div class="modal-header">
                                <b><h4 class="modal-title" id="modalLabel">Fee Payment History</h4></b>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">




                                <!-- =========================== Fee History div Start ======================== -->

                                <div class="box-body table-responsive box box-primary" style="border : solid #285e8e 2px;" >

                                    <table id="studentFeeHistory" class="display" style="width:100%">
                                    </table>

                                </div>

                                <!-- =========================== Fee History div END ========================== -->

                            </div>
                            <div class="modal-footer">

                            </div>
                        </div>
                    </div>
                </div>



                <!---------------------------------------------Modal Fee Payment History End-------------------------------------------->            






                <!---------------------------------------------------Edit Student Modal div  start --------------------------------------------------->            

                <div class="modal fade" id="editStudentModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="modalLabel">Edit Student Detail</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <form method="post" id="editstudentform" action="" class="" style="height: auto">


                                    <div class="form-group">
                                        <input type="hidden" id="csrfToken" value="${_csrf.token}"/>
                                        <input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>
                                        <!-- Get User information like userid or user name -->
                                        <input type="hidden" id="userId" value="${user.userId}"/>
                                    </div>

                                    <div class="form-group">

                                        <input type="hidden" id="edit_id" value=""/>

                                    </div>
                                    <div class="form-group">
                                        <label for="recipient-name" class="col-form-label">First Name</label>
                                        <input type="text"  class="form-control" id="edit_firstName" value="">
                                    </div>


                                    <div class="form-group">
                                        <label for="recipient-name" class="col-form-label">Middle Name</label>
                                        <input type="text"   class="form-control" id="edit_middleName" value="">
                                    </div>


                                    <div class="form-group">
                                        <label for="recipient-name" class="col-form-label">Last Name</label>
                                        <input type="text"   class="form-control" id="edit_lastName" value="">
                                    </div>


                                    <div class="form-group">
                                        <label for="recipient-name" class="col-form-label">Address</label>
                                        <textarea class="form-control z-depth-1 validate[required]" id="edit_address" name="address" rows="3" ></textarea>
                                    </div>


                                    <div class="form-group">
                                        <label for="recipient-name" class="col-form-label">Mobile Number</label>
                                        <input id="edit_mobileNumber" class="form-control text-input validate[required]" type="text" name="mobileNumber" value="" >
                                    </div>

                                    <div class="form-group">
                                        <label for="recipient-name" class="col-form-label">Email Id</label>
                                        <input id="edit_emailId" class="form-control validate[required]" type="email" name="emailId" value="" >
                                    </div>


                                    <div class="form-group">
                                        <label for="recipient-name" class="col-form-label">Guardian Full Name</label>
                                        <input id="edit_guardianFullName" class="form-control validate[required]" type="text" name="guardianFullName" value="" >
                                    </div>


                                    <div class="form-group">
                                        <label for="recipient-name" class="col-form-label">Guardian Full Address</label>
                                        <textarea class="form-control validate[required]" id="edit_guardianFullAddress" name="guardianFullAddress" rows="3" ></textarea>
                                    </div>


                                    <div class="form-group">
                                        <label for="recipient-name" class="col-form-label">Guardian Contact Number</label>
                                        <input id="edit_guardianMobileNumber" class="form-control text-input validate[required]" type="text" name="guardianMobileNumber" value="" >
                                    </div>

                                    <div class="form-group">
                                        <label for="recipient-name" class="col-form-label">Aadhar Number</label>
                                        <input id="edit_aadharNumber" class="form-control validate[required]" type="text" name="aadharcardnumber" value="" maxlength="12" pattern=".{12,12}" >
                                    </div>

                                    <div class="clearfix"></div>

                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary" id="editStudentbtn">Save</button>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal" id="editmodalclosebtn">Close</button>
                            </div>
                        </div>
                    </div>
                </div>



                <!---------------------------------------------------Edit Student Modal div End--------------------------------------------------->            


            </div>
                                    
             
                                    
<!-----------------------------------Confirm modal Div start------------------>            
           <div id="modalConfirmYesNo" class="modal fade" style="width:100%">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" 

                                class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 id="lblTitleConfirmYesNo" class="modal-title">Confirmation</h4>
                    </div>
                    <div class="modal-body">
                        <p id="lblMsgConfirmYesNo"></p>
                    </div>
                    <div class="modal-footer">
                        <button id="btnYesConfirmYesNo" 

                                type="button" class="btn btn-primary">Yes</button>
                        <button id="btnNoConfirmYesNo" 

                                type="button" class="btn btn-default">No</button>
                    </div>
                </div>
            </div>
        </div>
<!-----------------------------------Confirm modal Div end------------------>                          
                                    
                                    
                                    
                                    
        </div>



        


    </jsp:body>
</t:dashboard>
