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
        <title>Receipt : Report RP04</title>
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
                            <h3 class="panel-title"> <font color="#FFFFFF" >Receipt : Report RP04</font></h3>
                        </div>
                        <div class="container panel-body col-xs-12 col-sm-12 col-md-12 col-lg-12 ">
                            <form  method="GET"  id="MyForm" action="./Report" >
                                <div class="row col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                    <div class=" col-xs-offset-4 col-lg-3 col-md-3">
                                        <label>From date :</label>
                                        <input class="form-control" type="date" id="fdate" name="fdate">
                                    </div>
                                     <div class=" col-xs-offset-4 col-lg-3 col-md-3">
                                        <label>To date :</label>
                                        <input class="form-control" type="date" id="tdate" name="tdate">
                                    </div>
                                </div> 
                                <br> <br> <br><br>
                                <div class="row col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                    <div class="col-md-offset-5 col-lg-5 col-md-5" style="width: 60%">
                                        <button class="btn btn-danger form-control " style="width: 25%" id="vReport" name="vReport" type="submit" value="RP05_PDF">GET PDF</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div> 
            </div> 
        </div>
    </body>

    <script type="text/javascript">

      
         

    </script>

</html>
