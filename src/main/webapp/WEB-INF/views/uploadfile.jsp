<%-- 
    Document   : temp
    Created on : Dec 27, 2018, 5:33:50 PM
    Author     : raj.shah
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
        <script src="https://code.jquery.com/jquery-3.3.1.js" type="text/javascript"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="https://cdn.datatables.net/buttons/1.5.2/js/dataTables.buttons.min.js" type="text/javascript"></script>
        <script src="https://cdn.datatables.net/buttons/1.5.2/js/buttons.print.min.js" type="text/javascript"></script>

        <link href='https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css' rel="stylesheet" type="text/css" />
        <link href='https://cdn.datatables.net/buttons/1.5.2/css/buttons.dataTables.min.css' rel="stylesheet" type="text/css" />
        
    </head>
    <body><h2>Single File Upload</h2><br/><br/>
        <form method="POST" action="uploadfilepage/uploadFile" enctype="multipart/form-data">
		File to upload: <input type="file" name="file"><br /> 
		Name: <input type="text" name="name"><br /> <br /> 
		<input type="submit" value="Upload"> Press here to upload the file!
	</form>	
        <br/><br/>
        <hr><br><br><h2>Multiple File Upload</h2><br/><br/>
        <form method="POST" action="uploadfilepage/uploadMultipleFile" enctype="multipart/form-data">
		File1 to upload: <input type="file" name="file"><br /> 
		Name1: <input type="text" name="name"><br /> <br /> 
		File2 to upload: <input type="file" name="file"><br /> 
		Name2: <input type="text" name="name"><br /> <br />
		<input type="submit" value="Upload"> Press here to upload the file!
	</form>
    </body>
</html>
