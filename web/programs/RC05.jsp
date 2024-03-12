<%-- 
    Document   : Rollback
    Created on : Feb 18, 2021, 3:38:46 PM
    Author     : Jilasak
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>  

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"  content="width=device-width, initial-scale=1.0">
        <title>Receipt : Customer By Invoice</title>
        <style>
            .ui-widget *, .ui-widget input, .ui-widget select, .ui-widget button {
                font-family: 'Helvetica Neue Light', 'Open Sans', Helvetica;
                font-size: 14px;
                font-weight: 300 !important;
            }.center {


                text-align: center;
            }

        </style>
    </head>
    <body>
        <div class="container">
            <div class="col-md-12">
                <div class="row">
                    <div class="panel panel-default container">
                        <div class="panel-heading" style="background-color: #999999">
                            <h3 class="panel-title"> <font color="#FFFFFF" >Receipt : Customer By Invoice</font></h3>
                        </div>
                        <div class="container panel-body col-xs-12 col-sm-12 col-md-12 col-lg-12 ">
                            <div class="row">
                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                    <div class="center">
                                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                            <label>Payer Code : </label>
                                            <input type="text" name="vPYNO" id="vPYNO"  style="width: 15%;text-align: center" value="TH" maxlength="10"> 
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                    <div class="center">
                                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                            <button class="btn btn-success " style="width: 25%" id="vSubmit" name="vSubmit" type="button">Add Payer Code</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <br>
                        </div>
                    </div>
                </div> 
            </div> 
        </div>
    </body>

    <script type="text/javascript">

        $("#vSubmit").click(function (e) {
            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            if ($("#vPYNO").val().length === 10) {
                $.ajax({
                    url: './Sync',
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        page: "InsertPayerCode",
                        HR_CONO: cono,
                        HR_DIVI: divi,
                        HC_PYNO: $("#vPYNO").val()
                    },
                    async: false
                });
                alert("Complete");
                location.reload(0);
            } else {
                alert("Error. Please check data!!");
            }
        });

    </script>

</html>
