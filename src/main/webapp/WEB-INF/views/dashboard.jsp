<%@ include file="/WEB-INF/tags/layout/includes.jsp"%>


<t:dashboard>
    <jsp:attribute name="header">

        <style>

            #slider {
                position: relative;
                overflow: hidden;
                margin: 20px auto 0 auto;
                border-radius: 4px;
            }

            #slider ul {
                position: relative;
                margin: 0;
                padding: 0;
                /*height: 200px;*/
                height: 500px;
                /*width: inherit;*/
                list-style: none;
            }

            #slider ul li {
                position: relative;
                display: block;
                float: left;
                margin: 0;
                padding: 0;
                width: 750px;
                height: 500px;
                max-width: 100%;
                max-height: 100%;
                /*background: #ccc;*/
                text-align: center;
                line-height: 300px;
            }

            a.control_prev, a.control_next {
                position: absolute;
                top: 40%;
                z-index: 999;
                display: block;
                padding: 4% 3%;
                width: auto;
                height: auto;
                background: #2a2a2a;
                color: #fff;
                text-decoration: none;
                font-weight: 600;
                font-size: 18px;
                opacity: 0.8;
                cursor: pointer;
            }

            a.control_prev:hover, a.control_next:hover {
                opacity: 1;
                -webkit-transition: all 0.2s ease;
            }

            a.control_prev {
                border-radius: 0 2px 2px 0;
            }

            a.control_next {
                right: 0;
                border-radius: 2px 0 0 2px;
            }

            .slider_option {
                position: relative;
                margin: 10px auto;
                width: 160px;
                font-size: 18px;
            }


        </style>

        <!-- Page specific script -->
        <script type="text/javascript">
            $(function () {

                var studentId = $("#studentId").val();
                if (studentId !== null && studentId !== undefined && studentId !== "" ) {
                    var data = {};
                    data["studentId"] = studentId;
                    var url = "dashboard/student/dashboarddetail?id=" + studentId;
                    $.ajax({
                        type: "POST",
                        url: url,
                        dataType: 'json',
                        contentType: "application/json; charset=utf-8",
                        success: function (response) {
                            if (response.success === true) {
                                $("#courseName").html(response.course);
                                $("#batch").html(response.batch);
                                $("#totalfee").html(response.totalfee);
                                $("#remainingfee").html(response.remainingfee);
                                
                                loadNews();
                            } else {
                                console.log("ERROR: ", e);
                            }
                        },
                        error: function (e) {
                            console.log("ERROR: ", e);
                        }
                    });
                    
                }else{
                    var url = "dashboard/news/dashboarddetail";
                    $.ajax({
                        type: "POST",
                        url: url,
                        dataType: 'json',
                        contentType: "application/json; charset=utf-8",
                        success: function (response) {
                            if (response.success === true) {
                                $("#totalcourse").html(response.course);
                                $("#totalbatch").html(response.batch);
                                $("#totalstudent").html(response.student);
                            } else {
                                console.log("ERROR: ", e);
                            }
                        },
                        error: function (e) {
                            console.log("ERROR: ", e);
                        }
                    });
                }
                

                /* initialize the calendar
                 -----------------------------------------------------------------*/
                //Date for the calendar events (dummy data)
                var date = new Date();
                var d = date.getDate(),
                        m = date.getMonth(),
                        y = date.getFullYear();
                $('#calendar').fullCalendar({
                    header: {
                        left: 'prev,next today',
                        center: 'title',
                        right: 'month,agendaWeek,agendaDay'
                    },
                    buttonText: {
                        today: 'today',
                        month: 'month',
                        week: 'week',
                        day: 'day'
                    }

                });

                function loadNews(){
                     var newsurl = 'dashboard/news/loadallnews?active=true';
                    $.ajax({
                        type: "GET",
                        url: newsurl,
                        dataType: 'json',
                        contentType: "application/json; charset=utf-8",
                        success: function (response) {
                            if (response.success == true && response.data != null && response.data != "") {
                                var marqueehtml = "";
                                var normalSpace="&nbsp;&nbsp;&nbsp;&nbsp;";
                                var bigSpace=normalSpace+normalSpace+normalSpace;
                                marqueehtml += "<div>";
                                for(var i=0;i< response.data.length ; i++){
                                    var date = new Date(response.data[i].dataCreateTime);
                                    var month = date.getMonth() + 1;
                                    var finalDate =(date.getDate() >= 10 ? date.getDate() : "0" + date.getDate()) + "-" + (month >= 10 ? month : "0" + month) + "-" + date.getFullYear();
                                    
                                    marqueehtml += "<span class='fa fa-newspaper-o' style='color:red'>"+ normalSpace+ finalDate +"</span>"+normalSpace ;
                                    marqueehtml += "<span>"+response.data[i].title +"</span>"+normalSpace ;
                                    marqueehtml += "<span>"+response.data[i].description +"</span>"+bigSpace ;
                                    
                                }
                                marqueehtml += "</div>";
                                $("#marqueeid").html(marqueehtml);
                            } else {
                                console.log("ERROR: ", e);
                            }
                        },
                        error: function (e) {
                            console.log("ERROR: ", e);
                        }
                    });
                }


                $('#checkbox').change(function () {
                    setInterval(function () {
                        moveRight();
                    }, 3000);
                });

                var slideCount = $('#slider ul li').length;
                var slideWidth = $('#slider ul li').width();
                var slideHeight = $('#slider ul li').height();
                var sliderUlWidth = slideCount * slideWidth;

                $('#slider').css({width: slideWidth, height: slideHeight});

                $('#slider ul').css({width: sliderUlWidth, marginLeft: -slideWidth});

                $('#slider ul li:last-child').prependTo('#slider ul');

                function moveLeft() {
                    $('#slider ul').animate({
                        left: +slideWidth
                    }, 200, function () {
                        $('#slider ul li:last-child').prependTo('#slider ul');
                        $('#slider ul').css('left', '');
                    });
                }
                ;

                function moveRight() {
                    $('#slider ul').animate({
                        left: -slideWidth
                    }, 200, function () {
                        $('#slider ul li:first-child').appendTo('#slider ul');
                        $('#slider ul').css('left', '');
                    });
                }
                ;

                $('a.control_prev').click(function () {
                    moveLeft();
                });

                $('a.control_next').click(function () {
                    moveRight();
                });



            });
        </script>
    </jsp:attribute>

    <jsp:body>
        <%--<sec:authorize access="hasRole('ADMIN') or hasRole('STUDENT') or hasRole('SUBADMIN') or hasRole('CLERK') or hasRole('ATTENDANCE') or hasRole('RESULT')">--%>
        <%--<sec:authorize access="hasAuthority('read')">--%>
        <!-- Info boxes -->
        <div class="row" style="">
            <sec:authorize access="hasRole('ADMIN') or hasRole('SUBADMIN') or hasRole('CLERK') or hasRole('ATTENDANCE') or hasRole('RESULT')">
                
                <div class="col-md-3 col-sm-6 col-xs-12">
                    <div class="info-box teacher">
                        <div class="info-box-stats">
                            <span class="info-box-title">Total Course</span>
                            <p class="counter" id="totalcourse"></p>
                        </div>
                        <!-- /.info-box-content -->
                    </div>
                    <!-- /.info-box -->
                </div>

                <div class="col-md-3 col-sm-6 col-xs-12">
                    <div class="info-box attendence">
                        <div class="info-box-stats">
                            <span class="info-box-title">Total Batch</span>
                            <p class="counter" id="totalbatch"></p>
                        </div>
                        <!-- /.info-box-content -->
                    </div>
                    <!-- /.info-box -->
                </div>

                <div class="col-md-3 col-sm-6 col-xs-12">
                    <div class="info-box parent">
                        <div class="info-box-stats">

                            <span class="info-box-title">Total Students</span>
                            <p class="counter" id="totalstudent"></p>

                        </div>
                        <!-- /.info-box-content -->
                    </div>
                    <!-- /.info-box -->
                </div>
    
            </sec:authorize>


            <!-- fix for small devices only -->
            <!--<div class="clearfix visible-sm-block"></div>-->
            <sec:authorize access="hasRole('STUDENT')">

                <div class="col-md-3 col-sm-6 col-xs-12">
                    <div class="info-box teacher">
                        <div class="info-box-stats">
                            <span class="info-box-title">Course </span>
                            <p class="counter" id="courseName"></p>
                        </div>
                        <!-- /.info-box-content -->
                    </div>
                    <!-- /.info-box -->
                </div>

                <div class="col-md-3 col-sm-6 col-xs-12">
                    <div class="info-box parent">
                        <div class="info-box-stats">
                            <span class="info-box-title">Batch </span>
                            <p class="counter" id="batch"></p>
                        </div>
                        <!-- /.info-box-content -->
                    </div>
                    <!-- /.info-box -->
                </div>

                <div class="col-md-3 col-sm-6 col-xs-12">
                    <div class="info-box attendence">
                        <div class="info-box-stats">

                            <span class="info-box-title">Total Fees</span>
                            <p class="counter" id="totalfee"></p>

                        </div>
                        <!-- /.info-box-content -->
                    </div>
                    <!-- /.info-box -->
                </div>

                <div class="col-md-3 col-sm-6 col-xs-12">
                    <div class="info-box student">
                        <div class="info-box-stats">

                            <span class="info-box-title">Remaining Fees</span>
                            <p class="counter" id="remainingfee"></p>

                        </div>
                        <!-- /.info-box-content -->
                    </div>
                    <!-- /.info-box -->
                </div>

                                        <!----> 
                <marquee style="color: #FFFFFF;height: auto;font-family: inherit;font-size: x-large" bgcolor="#222d32" direction="left" id='marqueeid'>
                    
                </marquee>

            </sec:authorize>


        </div>
        <!-- /.row -->

        <!-- =======================Info boxes End =================================== -->




        <!--   SLIDE SHOW DIV START   -->               

        <sec:authorize access="hasRole('STUDENT')">

            <input type="hidden" name="studentId" id="studentId" value="${studentId}"/>
            <input type="hidden" id="csrfToken" value="${_csrf.token}"/>
            <input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>
            <!-- Get User information like userid or user name -->
            <input type="hidden" id="userId" value="${user.userId}"/>

            <div id="slider">
                <a href="#" class="control_next">>></a>
                <a href="#" class="control_prev"><</a>
                <ul>

                    <li><img src="<c:url value='resources/images/GIHM/HDC_8511.JPG' />" style="max-width:100%;max-height:100%; object-fit: contain"/></li>
                    <li><img src="<c:url value='resources/images/GIHM/_DSC8351.JPG' />" style="max-width:100%;max-height:100%; object-fit: contain"/></li>
                    <li><img src="<c:url value='resources/images/GIHM/_DSC8358.JPG' />" style="max-width:100%;max-height:100%; object-fit: contain"/></li>
                    <li><img src="<c:url value='resources/images/GIHM/_DSC8378.JPG' />" style="max-width:100%;max-height:100%; object-fit: contain"/></li>
                    <li><img src="<c:url value='resources/images/GIHM/_DSC8382.JPG' />" style="max-width:100%;max-height:100%; object-fit: contain"/></li>
                    <li><img src="<c:url value='resources/images/GIHM/_DSC8392.JPG' />" style="max-width:100%;max-height:100%; object-fit: contain"/></li>
                    <li><img src="<c:url value='resources/images/GIHM/_DSC8402.JPG' />" style="max-width:100%;max-height:100%; object-fit: contain"/></li>



                </ul>  
            </div>

            <div class="slider_option">
                <input type="checkbox" id="checkbox">
                <label for="checkbox">Autoplay</label>
            </div> 
        </sec:authorize>

        <!--   SLIDE SHOW DIV END   --> 

        <sec:authorize access="hasRole('ADMIN') or hasRole('SUBADMIN') or hasRole('CLERK') or hasRole('ATTENDANCE') or hasRole('RESULT')">
            <img src="<c:url value='resources/images/GIHM/y.png' />" style="max-width:100%;max-height:100%; object-fit: contain"/>
        </sec:authorize>


        <!-------- ----------------  END  ------------------------->
        <div class="row" > 






            <!--style="background-image: url(/resources/images/y.png)"-->

            <!--                    <div class="col-md-8" >
                                    <div class="box box-primary">
                                        <div class="box-body no-padding">
                                             THE CALENDAR 
                                            <div id="calendar"></div>
                                        </div>
                                         /.box-body 
                                    </div>
                                     /. box 
                                </div>-->


            <!-- /.col -->



            <!--                    
                                <div class="col-md-4">
                                    <div class="panel panel-white">
                                        <div class="panel-heading">
                                            <h3 class="panel-title">Notice</h3>
            
                                        </div>
                                        <div class="panel-body">
                                            <div class="events">
                                                <div class="calendar-event"> 
                                                    <p>
                                                        Farewell party					</p>
                                                </div>
                                            </div>
                                            <div class="events">
                                                <div class="calendar-event"> 
                                                    <p>
                                                        Summer vacation					</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
            
            
                                    <div class="panel panel-white">
                                        <div class="panel-heading">
                                            <h3 class="panel-title">Holiday List</h3>
            
                                        </div>
                                        <div class="panel-body">
                                            <div class="events">
                                                <div class="calendar-event"> 
                                                    <p>
                                                        New Year Day					</p>
                                                </div>
                                            </div>
                                            <div class="events">
                                                <div class="calendar-event"> 
                                                    <p>
                                                        Good Friday					</p>
                                                </div>
                                            </div>
                                            <div class="events">
                                                <div class="calendar-event"> 
                                                    <p>
                                                        Summer Vacation					</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
            
                                </div>
                                
                                
            -->



            <!-- /.col -->
        </div>

        <!-------- ----------------  END  ------------------------->

        <!-- /.row -->







        <%--</sec:authorize>--%>
        <%--</sec:authorize>--%>
    </jsp:body>
</t:dashboard>
