<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<!-- =========================== Admin Role Menu Section START ========================= -->

<sec:authorize access="hasRole('ADMIN')">

    <li class="header">MAIN NAVIGATION</li>
    <li><a href='<c:url value="/home"/>'><i class="fa fa-home"></i> <span>Home</span></a></li>
        <%-- <li><a href='<c:url value="/dashboard"/>'><i class="fa fa-dashboard"></i> <span>Dashboard</span></a></li> --%>
    <li class="treeview">
        <a href="#"><i class="fa fa-group"></i> <span>Student</span> <i class="fa fa-angle-left pull-right"></i></a>
        <ul class="treeview-menu">
            <li><a href='<c:url value="/dashboard/student/add"/>'><i class="fa fa-user"></i>Enroll Student</a></li>
            <li><a href='<c:url value="/dashboard/student"/>'><i class="fa fa-info-circle"></i>Student Information</a></li>
            <li><a href='<c:url value="/dashboard/student/attendance"/>'><i class="fa fa-check-square-o"></i> <span>Upload Attendance</span></a></li>
            <li><a href='<c:url value="/dashboard/student/viewAttendance"/>'><i class="fa fa-search-plus"></i> <span>View Attendance</span></a></li>
            <li><a href='<c:url value="/dashboard/student/viewAggregateAttendance"/>'><i class="fa fa-check-circle"></i> <span>View Aggregate Attendance</span></a></li>
            <li><a href='<c:url value="/dashboard/student/uploadResult"/>'><i class="fa fa-file-text"></i> <span>Upload Result</span></a></li>
            <li><a href='<c:url value="/dashboard/student/viewResult"/>'><i class="fa fa-search-plus"></i> <span>View Result</span></a></li>
            <!-- <li><a href="#">Student Promotion</a></li> -->
        </ul>
    </li>
   
    <li class="treeview">
        <a href="#"><i class="fa fa-gear "></i> <span>Setup</span> <i class="fa fa-angle-left pull-right"></i></a>
        <ul class="treeview-menu">
           
            <li class="treeview">
                <a href="#"><i class="fa fa-cubes"></i> <span>Batch</span> <i class="fa fa-angle-down pull-right"></i></a>
                <ul class="treeview-menu">
                    <li><a href='<c:url value="/dashboard/batch"/>'><i class="fa fa-plus-circle"></i> <span>Add Batch</span></a></li>
                </ul>
            </li>

            <li class="treeview">
                <a href="#"><i class="fa fa-book"></i> <span>Subject</span> <i class="fa fa-angle-down pull-right"></i></a>
                <ul class="treeview-menu">
                    <li><a href='<c:url value="/dashboard/subject"/>'><i class="fa fa-plus-circle"></i> <span>Add Subject</span></a></li>
                </ul>
            </li>

            <li class="treeview">
                <a href="#"><i class="fa fa-pencil-square-o"></i> <span>Course</span> <i class="fa fa-angle-down pull-right"></i></a>
                <ul class="treeview-menu">
                    <li><a href='<c:url value="/dashboard/course"/>'><i class="fa fa-plus-circle"></i> <span>Add Course</span></a></li>
                    <li><a href='<c:url value="/dashboard/courseSubject"/>'><i class="fa fa-sitemap"></i> <span>Course Subject Mapping</span></a></li>
                    <li><a href='<c:url value="/dashboard/courseFee"/>'><i class="fa fa-sitemap"></i> <span>Course Fee Mapping</span></a></li>
                </ul>
            </li>
            
            <li class="treeview">
                <a href="#"><i class="fa fa-newspaper-o"></i> <span>News</span> <i class="fa fa-angle-down pull-right"></i></a>
                <ul class="treeview-menu">
                    <li><a href='<c:url value="/dashboard/news"/>'><i class="fa fa-plus-circle"></i> <span>Add News</span></a></li>
                </ul>
            </li>

        </ul>
    </li>
    
   
</sec:authorize>
<!-- =========================== Admin Role Menu Section END =========================== -->


<!-- =========================== Student Role Menu Section START ========================= -->

<sec:authorize access="hasRole('STUDENT')">
    <li class="header">MAIN NAVIGATION</li>
    <li><a href='<c:url value="/home"/>'><i class="fa fa-home"></i> <span>Home</span></a></li>
    <li><a href='<c:url value="/dashboard/student/viewFeeToStudent"/>'><i class="fa fa-info-circle"></i>View Fee Detail</a></li>
    <li><a href='<c:url value="/dashboard/student/viewAttendanceToStudent"/>'><i class="fa fa-check-square-o"></i> <span>View My Attendance</span></a></li>
    <li><a href='<c:url value="/dashboard/student/viewResultToStudent"/>'><i class="fa fa-search-plus"></i> <span>View Result</span></a></li>
</sec:authorize>
<!-- =========================== Student Role Menu Section END =========================== -->




<!-- =========================== Sub-Admin Role Menu Section START ========================= -->

<sec:authorize access="hasRole('SUBADMIN')">
   
     <li class="header">MAIN NAVIGATION</li>
    <li><a href='<c:url value="/home"/>'><i class="fa fa-home"></i> <span>Home</span></a></li>
        <%-- <li><a href='<c:url value="/dashboard"/>'><i class="fa fa-dashboard"></i> <span>Dashboard</span></a></li> --%>
    <li class="treeview">
        <a href="#"><i class="fa fa-group"></i> <span>Student</span> <i class="fa fa-angle-left pull-right"></i></a>
        <ul class="treeview-menu">
            <li><a href='<c:url value="/dashboard/student"/>'><i class="fa fa-info-circle"></i>View Student Information</a></li>
            <li><a href='<c:url value="/dashboard/student/viewAttendance"/>'><i class="fa fa-search-plus"></i> <span>View Attendance</span></a></li>
            <li><a href='<c:url value="/dashboard/student/viewAggregateAttendance"/>'><i class="fa fa-check-circle"></i> <span>View Aggregate Attendance</span></a></li>
            <li><a href='<c:url value="/dashboard/student/viewResult"/>'><i class="fa fa-search-plus"></i> <span>View Result</span></a></li>
        </ul>
    </li>
  
    
</sec:authorize>
<!-- =========================== Sub-Admin  Role Menu Section END =========================== -->



<!-- =========================== Clerk Role Menu Section START ========================= -->

<sec:authorize access="hasRole('CLERK')">
   
     <li class="header">MAIN NAVIGATION</li>
    <li><a href='<c:url value="/home"/>'><i class="fa fa-home"></i> <span>Home</span></a></li>
        <%-- <li><a href='<c:url value="/dashboard"/>'><i class="fa fa-dashboard"></i> <span>Dashboard</span></a></li> --%>
    <li class="treeview">
        <a href="#"><i class="fa fa-group"></i> <span>Student</span> <i class="fa fa-angle-left pull-right"></i></a>
        <ul class="treeview-menu">
            <li><a href='<c:url value="/dashboard/student"/>'><i class="fa fa-info-circle"></i>View Student Information</a></li>
        </ul>
    </li>
  
    
</sec:authorize>
<!-- =========================== Clerk  Role Menu Section END =========================== -->



<!-- =========================== Attendance Role Menu Section START ========================= -->

<sec:authorize access="hasRole('ATTENDANCE')">
   
     <li class="header">MAIN NAVIGATION</li>
    <li><a href='<c:url value="/home"/>'><i class="fa fa-home"></i> <span>Home</span></a></li>
        <%-- <li><a href='<c:url value="/dashboard"/>'><i class="fa fa-dashboard"></i> <span>Dashboard</span></a></li> --%>
    <li class="treeview">
        <a href="#"><i class="fa fa-group"></i> <span>Student</span> <i class="fa fa-angle-left pull-right"></i></a>
        <ul class="treeview-menu">
            <li><a href='<c:url value="/dashboard/student/attendance"/>'><i class="fa fa-check-square-o"></i> <span>Upload Attendance</span></a></li>
            <li><a href='<c:url value="/dashboard/student/viewAttendance"/>'><i class="fa fa-search-plus"></i> <span>View Attendance</span></a></li>
        </ul>
        
    </li>
  
    
</sec:authorize>
<!-- =========================== Attendance  Role Menu Section END =========================== -->

<!-- =========================== Result Role Menu Section START ========================= -->

<sec:authorize access="hasRole('RESULT')">
   
     <li class="header">MAIN NAVIGATION</li>
    <li><a href='<c:url value="/home"/>'><i class="fa fa-home"></i> <span>Home</span></a></li>
        <%-- <li><a href='<c:url value="/dashboard"/>'><i class="fa fa-dashboard"></i> <span>Dashboard</span></a></li> --%>
    <li class="treeview">
        <a href="#"><i class="fa fa-group"></i> <span>Student</span> <i class="fa fa-angle-left pull-right"></i></a>
        <ul class="treeview-menu">
             <li><a href='<c:url value="/dashboard/student/uploadResult"/>'><i class="fa fa-file-text"></i> <span>Upload Result</span></a></li>
            <li><a href='<c:url value="/dashboard/student/viewResult"/>'><i class="fa fa-search-plus"></i> <span>View Result</span></a></li>
           
        </ul>
    </li>
  
    
</sec:authorize>
<!-- =========================== Result  Role Menu Section END =========================== -->






<!-- =========================== Teacher Role Menu Section START ========================= -->


<sec:authorize access="hasRole('TEACHER')">

</sec:authorize>
<!-- =========================== Teacher Role Menu Section END =========================== -->



<!-- =========================== Staff Role Menu Section START ========================= -->

<sec:authorize access="hasRole('STAFF')">


</sec:authorize>
<!-- =========================== Staff Role Menu Section END =========================== -->


<!-- =========================== DEVELOPER Role Menu Section START ========================= -->

<sec:authorize access="hasRole('DEVELOPER')">

</sec:authorize>
<!-- =========================== DEVELOPER Role Menu Section END =========================== -->


