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
                        <div class="panel-heading" style="background-color: #000000">
                            <h3 class="panel-title"> <font color="#FFFFFF" >Receipt : <%=namecom%></font></h3>
                        </div>
                        <div class="container panel-body col-xs-12 col-sm-12 col-md-12 col-lg-12 " style="background-color: white">
                            <div class="row ">
                                <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                    <label>Receipt Number :
                                        <input type="text" class="form-control text-center" value="" id="vRCNO" name="vRCNO"  maxlength="8">
                                    </label>
                                </div>
                                <br>
                                <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                    <button class="btn btn-success" id="vSearch" name="vSearch" type="button" >Search</button>
                                </div>
                            </div> 
                            <br>
                            <div class="row" >
                                <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                    <label>Customer Code :
                                        <input type="text" class="form-control text-center" value="" id="vCUNO" name="vCUNO" readonly="true" >
                                    </label>
                                </div>
                                <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                    <label>Customer Name :
                                        <input type="text" class="form-control text-center" value="" id="vCUNM" name="vCUNM" readonly="true" >
                                    </label>
                                </div>
                                <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                    <label>Amount Receipt :
                                        <input type="text" class="form-control text-center" value="0.00" id="vAMT" name="vAMT" readonly="true" >
                                    </label>
                                </div>
                                <div class="col-xs-4 col-md-3 col-lg-2">
                                    <label style="width: 100%">Select Approval :
                                        <select class="form-control form-control-user" name="APP1" id="APP1" >
                                            <option value="-" selected="">Select Approval</option>
                                            <option value="0101273">WEETAR_KIT</option>
                                        </select>
                                    </label>
                                </div>
                                <div class="col-xs-4 col-md-3 col-lg-2">
                                    <label style="width: 100%">Select Approval :
                                        <select class="form-control form-control-user" name="APP2" id="APP2" >
                                            <option value="-" selected="">Select Approval</option>
                                            <option value="0101142">WUTINA_ULI</option>
                                            
                                        </select>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div id="GridReceipt"></div>   
                            </div>
                            <div class="row">
                                <label>Reason :</label>
                                <textarea class="form-control col-xs-12 col-sm-12 col-md-12 col-lg-12" id="vReason" name="vReason" maxlength="200"></textarea>
                            </div>
                            <div class="row" >
                                <label>Action :</label>
                                <textarea class="form-control col-xs-12 col-sm-12 col-md-12 col-lg-12" id="vAction" name="vAction" maxlength="200"></textarea>
                            </div><br>
                            <div class="row" id="send">
                                <button id="vCreate" class="btn  col-xs-12 col-sm-12 col-md-12 col-lg-12" style="font-weight: bold;text-align: center;color: red;background-color: black">Send to cancel</button>
                            </div>
                            <div class="row" hidden="true" id="update">
                                <button id="vUpdate"  class="btn  col-xs-12 col-sm-12 col-md-12 col-lg-12" style="font-weight: bold;text-align: center;color: white;background-color: #003eff">Update to cancel</button>
                            </div>

                        </div>
                    </div>
                </div> 
                <div class="row">
                    <div id="GridCancel"></div>
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
            var auth = "<%out.print(session.getAttribute("auth"));%>";
            if (auth !== "ROLL") {
                alert("Your not auth this page");
                window.location.href = "./";
                return;
            }
            gridCancel();
        });

        $("#vCreate").click(function (e) {
            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            var username = "<%out.print(session.getAttribute("username"));%>";

            var APP1 = $("#APP1").val();
            var APP2 = $("#APP2").val();

            if (APP1 === '-' || APP2 === '-') {
                alert("Please select approval.");
                return;
            }

            if ($("#vRCNO").val() !== "" && $("#vReason").val() !== "" && $("#vAction".val() !== "")) {
                $.ajax({
                    url: './Sync',
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        page: "InsertCancel",
                        CONO: cono,
                        DIVI: divi,
                        RCNO: $("#vRCNO").val(),
                        REASON: $("#vReason").val(),
                        ACTION: $("#vAction").val(),
                        APP1: APP1,
                        APP2: APP2
                    },
                    async: false
                }).done(function (response) {
                });
                alert("สำเร็จ");
                location.reload(0);
            } else {
                alert("Please check data");
            }
        });



        $("#vSearch").click(function (e) {
            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            var username = "<%out.print(session.getAttribute("username"));%>";
            var locations = "<%out.print(session.getAttribute("locations"));%>";
            $.ajax({
                type: 'GET',
                url: './Sync',
                dataType: 'json',
                data: {
                    page: "SearchReceipt_Cancel",
                    CONO: cono,
                    DIVI: divi,
                    RCNO: "" + $("#vRCNO").val(),
                    locations: locations
                },
                async: false
            }).done(function (response) {
                console.log(response);
                $.each(response, function (i, obj) {
//                    document.getElementById("vRCNO").value = obj.HC_RCNO;
                    document.getElementById("vCUNO").value = obj.HC_PYNO;
                    formatNumber(obj.HC_REAMT, 'vAMT');
                    document.getElementById("vCUNM").value = obj.okcunm;
                    Call_GridReceipt();
                });
                if (document.getElementById("vCUNM").value === "") {
                    alert("ไม่สามารถเรียกใบเสร็จนี้ได้ โปรดทำการตรวจสอบสถานะหรือโลเคชั่น !!");
                    location.reload(0);
                }
            });
        });

        function formatNumber(num, id) {
            num = num.replace(/,/g, '');
            num = parseFloat(num);
            num = num.toFixed(2);
            return document.getElementById(id).value = num.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
        }

        function unformat(num) {
            return num.replace(/,/g, '');
        }

        function Call_GridReceipt() {
            $("#GridReceipt").show();
            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            $("#GridReceipt").jsGrid({
                width: "100%",
                height: "250",
                inserting: false,
                editing: false,
                sorting: true,
                paging: true,
                autoload: true,
                pageSize: 5,
                pageButtonCount: 10,
                controller: {
                    loadData: function (filter) {
                        var data = $.Deferred();
                        $.ajax({
                            type: 'GET',
                            url: './Sync',
                            dataType: 'json',
                            data: {
                                page: "Grid_Receipt",
                                cono: cono,
                                divi: divi,
                                rcno: "" + $("#vRCNO").val()
                            },
                            async: false
                        }).done(function (response) {
                            response = $.grep(response, function (item) {
                                return (!filter.LR_INVNO || (item.LR_INVNO.indexOf(filter.LR_INVNO) > -1))
                                        && (!filter.LR_INVDT || (item.LR_INVDT.indexOf(filter.LR_INVDT) > -1))
                                        && (!filter.LR_INVAMT || (item.LR_INVAMT.indexOf(filter.LR_INVAMT) > -1));
                            });
                            data.resolve(response);
                        });
                        return data.promise();
                    }
                },
                fields: [
                    {title: "Invoice No", width: 60, name: "LR_INVNO", type: "text", align: "center", editing: false},
                    {title: "Invoice Date", name: "LR_INVDT", type: "text", align: "center", editing: false, width: 50},
                    {title: "Invoice Amount", name: "LR_INVAMT", type: "text", align: "right", editing: false,
                        itemTemplate: function (value) {
                            return  value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                        }, width: 40},
                    {title: "Amount Receive", name: "LR_REAMT", type: "text", align: "right", editing: true, filtering: false,
                        itemTemplate: function (value) {
                            return  value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                        }, width: 40}
                ]
            });
        }

        function gridCancel() {

            $("#GridCancel").jsGrid({
                width: "100%",
                height: "300",
                sorting: true,
                autoload: true,
                pageSize: 5,
                pageButtonCount: 5,
                rowClick: function (args) {
                    showDetailsDialog1("GET", args.item);
                },
                controller: {
                    loadData: function (filter) {
                        var data = $.Deferred();
                        var cono = <%out.print(session.getAttribute("cono"));%>
                        var divi = <%out.print(session.getAttribute("divi"));%>
                        $.ajax({
                            type: 'GET',
                            url: './Sync',
                            dataType: 'json',
                            data: {
                                page: "Grid_Cancel",
                                cono: cono,
                                divi: divi
                            },
                            async: false
                        }).done(function (response) {
                            data.resolve(response);
                        });
                        return data.promise();
                    }
                },
                fields: [
                    {title: "Receipt", width: 60, name: "RC_RCNO", type: "text", align: "center", editing: false},
                    {title: "Customer Code", width: 60, name: "HC_PYNO", type: "text", align: "center", editing: false},
                    {title: "Receipt Amount", name: "HC_REAMT", type: "text", align: "right", editing: false,
                        itemTemplate: function (value) {
                            return  value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                        }, width: 40},
                    {title: "Status", name: "Status", type: "text", align: "center", editing: false, width: 50}

                ]
            });


        }


        var showDetailsDialog1 = function (dialogType, client) {

            if (client.RC_STS === "20") {

                document.getElementById("vRCNO").value = client.RC_RCNO;
                var cono = <%out.print(session.getAttribute("cono"));%>
                var divi = <%out.print(session.getAttribute("divi"));%>
                var username = "<%out.print(session.getAttribute("username"));%>";
                var locations = "<%out.print(session.getAttribute("locations"));%>";

                $.ajax({
                    type: 'GET',
                    url: './Sync',
                    dataType: 'json',
                    data: {
                        page: "SearchReceipt_Cancel",
                        CONO: cono,
                        DIVI: divi,
                        RCNO: client.RC_RCNO,
                        locations: locations
                    },
                    async: false
                }).done(function (response) {
                    console.log(response);
                    $.each(response, function (i, obj) {
//                    document.getElementById("vRCNO").value = obj.HC_RCNO;
                        document.getElementById("vCUNO").value = obj.HC_PYNO;
                        formatNumber(obj.HC_REAMT, 'vAMT');
                        document.getElementById("vCUNM").value = obj.okcunm;
                        $("#send").hide();
                        $("#update").show();
                        Call_GridReceipt();
                    });
                    if (document.getElementById("vCUNM").value === "") {
                        alert("ไม่สามารถเรียกใบเสร็จนี้ได้ โปรดทำการตรวจสอบสถานะหรือโลเคชั่น !!");
                        location.reload(0);
                    }
                });
            } else {
                alert("อยู่ในสถานะไม่สามารถแก้ไขได้");
            }



        };


        $("#vUpdate").click(function (e) {
            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            var username = "<%out.print(session.getAttribute("username"));%>";

            var APP1 = $("#APP1").val();
            var APP2 = $("#APP2").val();

            if (APP1 === '-' || APP2 === '-') {
                alert("Please select approval.");
                return;
            }

            if ($("#vRCNO").val() !== "" && $("#vReason").val() !== "" && $("#vAction").val() !== "") {
                $.ajax({
                    url: './Sync',
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        page: "UpdateCancel",
                        CONO: cono,
                        DIVI: divi,
                        RCNO: $("#vRCNO").val(),
                        REASON: $("#vReason").val(),
                        ACTION: $("#vAction").val(),
                        APP1: APP1,
                        APP2: APP2
                    },
                    async: false
                }).done(function (response) {
                });
                alert("สำเร็จ");
                location.reload(0);
            } else {
                alert("Please check data");
            }
        });

    </script>
</html>