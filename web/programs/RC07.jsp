<%-- 
    Document   : Receiving
    Created on : Jun 11, 2020, 2:33:35 PM
    Author     : Jilasak
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>    
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"  content="width=device-width, initial-scale=1.0">
        <title>Receipt : DEPOSIT</title>

        <%
            String namecom = (String) session.getAttribute("comp");
        %>
        <style>
            .ui-widget *, .ui-widget input, .ui-widget select, .ui-widget button {
                font-family: 'Helvetica Neue Light', 'Open Sans', Helvetica;
                font-size: 14px;
                font-weight: 300 !important;
            }

        </style>
    </head>
    <body >
        <div class="container">
            <div class="col-md-12">
                <div class="row">
                    <div class="panel panel-default container" id="reportfield" >
                        <div class="panel-heading" style="background-color: #009a67">
                            <h3 class="panel-title"> <font color="#FFFFFF" >Receipt : <%=namecom%></font></h3>
                        </div>
                        <div class="container panel-body col-xs-12 col-sm-12 col-md-12 col-lg-12 ">
                            <div class="row ">
                                <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                    <label>User:
                                        <input type="text" class="form-control text-center" value="" id="vUser" name="vUser"  maxlength="10" readonly="true">
                                    </label>
                                </div>
                                <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                    <label>Start :
                                        <input type="text" class="form-control text-center" value="" id="vST" name="vST" maxlength="7" >
                                    </label>
                                </div>
                                <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                    <label>End :
                                        <input type="text" class="form-control text-center" value="" id="vEND" name="vEND" maxlength="7" >
                                    </label>
                                </div>
                                <br>
                                <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                    <button class="btn btn-success" id="vCreate" name="vCreate" type="button" >Set Series</button>
                                </div>
                            </div> 
                            <br>
                            <div class="row" >
                                <div id="GridSeries"></div>   
                            </div>
                        </div>
                    </div>
                </div> 
            </div> 
        </div>

    </body>

    <script type="text/javascript">

        $(document).ready(function () {
            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            var username = "<%out.print(session.getAttribute("username"));%>";
            var locations = "<%out.print(session.getAttribute("locations"));%>";
            document.getElementById("vUser").value = username;
            Call_GridReceipt();
        });



        $("#vCreate").click(function (e) {
            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            var username = "<%out.print(session.getAttribute("username"));%>";
            var locations = "<%out.print(session.getAttribute("locations"));%>";


            $.ajax({
                url: './Sync',
                type: 'POST',
                dataType: 'json',
                data: {
                    page: "SetSeries",
                    RR_CONO: cono,
                    RR_DIVI: divi,
                    RR_LCCODE: locations,
                    RR_USER: username,
                    RR_START: $("#vST").val(),
                    RR_END: $("#vEND").val(),
                    RR_COUNT: $("#vST").val()
                },
                async: false
            }).done(function (response) {
            });

//            Call_GridReceipt();
            alert("Complete");
          location.reload(0);
        });

        function Call_GridReceipt() {
            $("#GridSeries").show();
            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            var username = "<%out.print(session.getAttribute("username"));%>";
            var locations = "<%out.print(session.getAttribute("locations"));%>";

            $("#GridSeries").jsGrid({
                width: "100%",
                height: "auto",
                sorting: true,
                paging: true,
                autoload: true,
                pageSize: 5,
                pageButtonCount: 5,
                controller: {
                    loadData: function (filter) {
                        var data = $.Deferred();
                        $.ajax({
                            type: 'GET',
                            url: './Sync',
                            dataType: 'json',
                            data: {
                                page: "Grid_Series",
                                cono: cono,
                                divi: divi,
//                                username: username,
                                location: locations
                            },
                            async: false
                        }).done(function (response) {

                            data.resolve(response);
                        });
                        return data.promise();
                    }
                },
                fields: [
                    {title: "User", width: 60, name: "RR_USER", type: "text", align: "center"},
                    {title: "Start", name: "RR_START", type: "text", align: "center", width: 50},
                    {title: "End", name: "RR_END", type: "text", align: "center", width: 40},
                    {title: "Current", name: "RR_COUNT", type: "text", align: "center", width: 40},
                    {title: "Location", name: "RR_LCCODE", type: "text", align: "center", width: 40}

                ]
            });
        }

    </script>
</html>