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
        <title>Receipt : Report RP01</title>
        <style>
            .ui-widget *, .ui-widget input, .ui-widget select, .ui-widget button {
                font-family: 'Helvetica Neue Light', 'Open Sans', Helvetica;
                font-size: 14px;
                font-weight: 300 !important;
            }
            .center {


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
                            <h3 class="panel-title"> <font color="#FFFFFF" >Receipt : Report RP01</font></h3>
                        </div>
                        <div class="container panel-body col-xs-12 col-sm-12 col-md-12 col-lg-12 ">
                            <form  method="GET"  id="MyForm" action="./Report" >
                                <div class="row">
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                        <div class="center">
                                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                <label>Receipt No./ Voucher : </label>
                                                <input type="text" name="vRCNO" id="vRCNO"  style="width: 15%;text-align: center" value="21000000" maxlength="8" min="8"> 
                                                <input type="hidden" name="last" id="last"  style="width: 15%;text-align: center" value="FALSE"> 

                                                &nbsp;
                                                <input type="checkbox" id="myCheck" onclick="myFunction()">
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <br>
                                <div class="row">
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                        <div class="center">
                                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                <button class="btn btn-success " style="width: 25%" id="vReport" name="vReport" type="submit" value="RP01">GET REPORT</button>
                                                <button class="btn btn-success " style="width: 25%" id="vReport" name="vReport" type="submit" value="RP012">GET REPORT012</button>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <br>
                            </form>
                        </div>
                    </div>
                </div> 
            </div> 
        </div>
    </body>

    <script type="text/javascript">
        var CCHECK = false;
        function myFunction() {
            var checkBox = document.getElementById("myCheck");
            if (checkBox.checked == true) {
                document.getElementById("last").value = 'TRUE';
            } else {
                document.getElementById("last").value = 'FALSE';
            }
        }


    </script>

</html>
