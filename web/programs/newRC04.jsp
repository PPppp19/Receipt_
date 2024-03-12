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
        <title>Receipt : Receipt</title>

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

                <div class="row" >
                    <div id="GridClearExpense" ></div>   
                </div>

                <div class="row">
                    <div class="panel panel-default container" id="reportfield" style="display: none">
                        <div class="panel-heading" style="background-color: #999999">
                            <h3 class="panel-title"> <font color="#FFFFFF" >Receipt Expense: <%=namecom%></font></h3>
                        </div>
                        <div class="container panel-body col-xs-12 col-sm-12 col-md-12 col-lg-12 ">
                            <div class="row ">

                                <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                    <label>Payer Code :
                                        <input type="text" class="form-control text-center" value="" id="vPAYER" name="vPAYER" onblur="SearchPY(this.value);" maxlength="10">
                                    </label>
                                </div>
                                <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                    <label>Customer Name :
                                        <input type="text" class="form-control text-center" value="" id="vPAYERNAME" name="vPAYERNAME" readonly="true" >
                                        <!--TH28050014-->
                                    </label>
                                </div>
                                <div class="col-xs-4 col-sm-4 col-md-3 col-lg-2">
                                    <label>Amount Receive :
                                        <input type="text" class="form-control text-center" value="" id="vREAMT" name="vREAMT" onblur="formatNumber(this.value, this.id)" readonly="true">
                                    </label>
                                </div>
                                <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                    <label>Transaction Date :
                                        <input type="date" class="form-control" id="vTRDT" name="vTRDT" readonly="true">
                                    </label>
                                </div>
                                <br>

                            </div> 
                            <br>
                            <div class="row">
                                <div class="form-group">
                                    <div class="col-md-12">
                                        <label class="col-md-2 text-right" >TYPE :</label>
                                        <div class="col-md-4">
                                            <input  type="radio" name="optionsRadio" checked="checked" id="transfer" value="TRANSFER_EXP" onclick="radiobtn()">&nbsp;<label class="control-label">TRANSFER</label>
                                        </div>
                                        <div class="col-md-4">
                                            <input  type="radio" name="optionsRadio" id="check" value="CHECK_EXP" onclick="radiobtn()">&nbsp;<label class="control-label">CHECK</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <br>
                            <div class="row " id="row_transfer" hidden="true">


                                <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                    <label>Date Transfer :
                                        <input type="date" class="form-control text-center"  id="vDTTF" name="vDTTF" readonly="true"> 
                                    </label>
                                </div>
                                <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                    <label>Bank Transfer :
                                        <input type="text" class="form-control text-center"  id="vBKTF" name="vBKTF" readonly="true"> 
                                    </label>
                                </div>
                                <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                    <label>Bank Charge :
                                        <input type="text" class="form-control text-center"  id="vBKCHG" name="vBKCHG" min="0" value="0.00" readonly="true"> 
                                    </label>
                                </div>

                                <br>
                            </div>


                            <div class="row " id="row_check" hidden="true">
                                <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                    <label>Date Payment :
                                        <input type="date" class="form-control text-center"  id="vDTPM" name="vDTPM" readonly="true">
                                    </label>
                                </div>
                                <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                    <label>Bank Transfer :
                                        <input type="text" class="form-control text-center"  id="vBKCH" name="vBKCH" readonly="true"> 
                                    </label>
                                </div>
                                <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                    <label>Check  Number :
                                        <input type="text" class="form-control text-center"  id="vCHKNO" name="vCHKNO" readonly="true">
                                    </label>
                                </div>
                                <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                    <label>Bank Charge :
                                        <input type="text" class="form-control text-center"  id="vCHCHG" name="vCHCHG" min="0" value="0.00" readonly="true"> 
                                    </label>
                                </div>
                                <br>
                            </div>
                            <div class="row" >
                                <form  method="POST" enctype="multipart/form-data" acceptcharset="UTF-8" id="MyForm" >
                                    <!--<form  method="GET"  id="MyForm" action="./Report" >-->
                                    <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                        <label>ID :
                                            <input type="text" class="form-control text-center"  value="" id="vFNCODE" name="vFNCODE" maxlength="9" >
                                        </label>
                                    </div>

                                    <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                        <label style="width: 100%">Customer No. :
                                            <select class="form-control form-control-user" name="vCus" id="vCus" onchange="Call_twinGrid()" >
                                                <option value="none,none" selected="selected">Select Customer</option>
                                            </select>
                                        </label>
                                    </div>

                                    <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                        <label>Running Number :
                                            <input type="text" class="form-control text-center"  value="" id="vRCNO" name="vRCNO" maxlength="8" readonly="true">
                                        </label>
                                    </div>

                                    <br>
                                    <br>
                                    <br>



                                    <div hidden="true" class="col-xs-5 col-sm-4 col-md-3 col-lg-2">
                                        <label>Line Amount Receive :
                                            <input type="text" class="form-control text-center" readonly="true" value="0.00" id="vLR_AMT" name="vLR_AMT" readonly="true">
                                        </label>
                                    </div>
                                    <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                        <label>Amount Expense :
                                            <input type="text" class="form-control text-center"  id="vEXPENSE" name="vEXPENSE"  value="0.00" readonly="true" readonly="true"> 
                                        </label>
                                    </div>
                                    <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                        <label>Balance Amount :
                                            <input type="text" class="form-control text-center"  value="0.00" id="vBalance" name="vBalance" >
                                        </label>
                                    </div>
                                    <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                        <label>Amount Clear Expense :
                                            <input type="text" class="form-control text-center" readonly="true" value="0.00" id="vClear_AMT" name="vClear_AMT" readonly="true">
                                        </label>
                                    </div>
                                    <br>
                                    <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2" hidden="true">
                                        <button class="btn btn-info" id="vSearch" name="vSearch" type="button">Search Receipt</button>
                                    </div>
                                    <!--<button class="btn btn-danger" id="vComplete" name="vComplete" type="submit" style="position: absolute;right: 5px;">PRINT</button>-->
                                    <br>

                                </form>
                            </div>
                            <div class="row">
                                <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
                                    <br>
                                    <button class="btn btn-danger" id="vComplete" name="vComplete" type="button" style="width: 100%">Clear Expense</button>
                                </div>
                            </div>
                            <br>
                            <br>
                            <div class="row" >
                                <div id="CustomerGrid"></div>   
                            </div>
                            <div class="row" >
                                <div id="GridLineClearExpense" ></div>   
                            </div>
                            <div class="row" >
                                <div id="LinereceiveGrid"></div>   
                            </div>
                            <br>
                            <div class="row" >
                                <div id="GridExpense" ></div>   
                            </div>
                        </div>
                    </div>
                </div> 
            </div> 
            <!--<button type="button" id="few" onclick="CheckInvoice()">few</button>-->
        </div>

    </body>

    <script type="text/javascript">
        $(document).ready(function () {
            Call_GridClearExpense();
        });

        function Call_GridClearExpense() {
            $("#GridClearExpense").show();

            $("#GridClearExpense").jsGrid({
                width: "100%",
                height: "300",
                filtering: true,
                inserting: false,
                editing: true,
                sorting: true,
                paging: true,
                autoload: true,
                pageSize: 5,
                pageButtonCount: 5,
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
                                page: "Call_GridClearExpense",
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
                    {title: "ID.", width: 60, name: "H_RNNO", type: "text", align: "center", editing: false},
                    {title: "PAYER.", width: 60, name: "H_PYNO", type: "text", align: "center", editing: false},
//                    {title: "CUSTOMER NO.", width: 60, name: "H_CUNO", type: "text", align: "center", editing: false},
//                    {title: "Receipt No.", width: 60, name: "HR_RCNO", type: "text", align: "center", editing: false},
                    {title: "Receipt Type", width: 60, name: "HC_TYPE", type: "text", align: "center", editing: false},
                    {title: "Receipt Bank", name: "HC_BANK", type: "text", align: "center", editing: false, width: 50},
                    {title: "Receipt Amount", name: "HC_REAMT", type: "text", align: "right", editing: false,
                        itemTemplate: function (value) {
                            return  value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                        }, width: 40},
                    {title: "Receipt Voucher 1", name: "HR_VCNO1", type: "text", align: "center", editing: false, width: 50}
                ],
                rowClick: function (args) {
                    console.log(args.item);
                    showDetailsDialog1("GET", args.item);
                }
            });

        }


        var showDetailsDialog1 = function (dialogType, client) {
            $("#GridClearExpense").hide();

            document.getElementById('vPAYER').value = client.H_PYNO;
            document.getElementById('vFNCODE').value = client.H_RNNO;



            SearchPY(client.H_PYNO);
            SearchReceipt(client.H_RNNO);
            Call_CustomerDDL_CLEAREXPENSE();

            Call_Receipt();
            Call_CustomerGrid();
            GridLineClearExpense();
            Cal_Expense();
            Cal_LineAmountClearExpense();
            Call_LineGrid();
            Call_GridExpense();




            document.getElementById("reportfield").style.display = "";



        };
        function  Call_twinGrid() {

            //   if (document.getElementById("vRCNO").value != "")
            {
                Call_CustomerGrid();

                Call_Receipt();
                Call_LineGrid();

                Cal_Expense();
                Call_GridExpense();
                $("#CustomerGrid").jsGrid("loadData");
                $("#GridLineClearExpense").jsGrid("loadData");

                var rc = $("#vRCNO").val();

                if (rc !== "") {
                    $("#vCreaterc").hide();

                } else {
                    $("#vCreaterc").show();
                }


            }
        }

        function  Call_CustomerGrid() {


            $("#CustomerGrid").show();
            $("#CustomerGrid").jsGrid({
                width: "100%",
                height: "300",
                filtering: true,
                inserting: false,
                editing: true,
                sorting: true,
                paging: true,
                autoload: true,
                pageSize: 5,
                pageButtonCount: 5,
                controller: {
                    loadData: function (filter) {
                        var data = $.Deferred();
                        var cono = <%out.print(session.getAttribute("cono"));%>
                        var divi = <%out.print(session.getAttribute("divi"));%>
                        var cuno = $("#vCus").val();
                        $.ajax({
                            type: 'GET',
                            url: './Sync',
                            dataType: 'json',
                            data: {
                                page: "Grid_ARS200_EXPnew",
                                cono: cono,
                                divi: divi,
                                payer: "" + $("#vPAYER").val(),
                                rcno: "" + $("#vRCNO").val(),
                                customer: cuno
                            },
                            async: false
                        }).done(function (response) {
//                            console.log(response);
                            response = $.grep(response, function (item) {
                                return (!filter.ESCINO || (item.ESCINO.indexOf(filter.ESCINO) > -1))
                                        && (!filter.ESCINO || (item.ESCINO.indexOf(filter.ESCINO) > -1))
                                        && (!filter.ESCUAM || (item.ESCUAM.indexOf(filter.ESCUAM) > -1));
                            });
                            data.resolve(response);
                        });
                        return data.promise();
                    },
                    updateItem: function (item) {

                        console.log(item);



                        var cono = <%out.print(session.getAttribute("cono"));%>
                        var divi = <%out.print(session.getAttribute("divi"));%>
                        item.page = "InsertLineClear_EXPnew";
                        item.LREP_CONO = cono;
                        item.LREP_DIVI = divi;
                        item.LREP_RCNO = $("#vRCNO").val();
                        item.LREP_FNNO = $("#vFNCODE").val();
                        var LineAMT = unformat(document.getElementById("vLR_AMT").value);
                        var RECEIVE = item.RECEIVE.replace(/,/g, '');
                        var HeadAmount = unformat(document.getElementById("vREAMT").value);
                        var Balance = item.Balance.replace(/,/g, '');
                        if (checkNegative(RECEIVE, Balance) === false) {
                            alert("Can't enter this receive");
                            return;
                        }
                        if (RECEIVE < 0.00) {
                            RECEIVE = RECEIVE * (-1);
                        }
                        const x = new Date(item.ESIVDT);
                        const y = new Date(document.getElementById("vTRDT").value);
                        if (x > y) {
                            alert("The invoice date is more than the receipt date !!");
                            return;
                        }

                        $.ajax({
                            type: "POST",
                            url: "./Sync",
                            dataType: 'json',
                            async: false,
                            cache: false,
                            data: item
                        });
                        GridLineClearExpense();
//                        Cal_LineAmountnew();
                        Cal_LineAmountClearExpense();
                        Cal_Expense();
                        $("#CustomerGrid").jsGrid("loadData");
                        $("#GridLineClearExpense").jsGrid("loadData");


                    }
                },
                fields: [
                    {type: "control", width: 30, deleteButton: false},
                    {title: "Invoice No", width: 60, name: "ESCINO", type: "text", align: "center", editing: false},
                    {title: "Customer Code", width: 60, name: "ESCUNO", type: "text", align: "center", editing: false},
                    {title: "Invoice Date", name: "ESIVDT", type: "text", align: "center", editing: false, width: 50},
                    {title: "Invoice Amount", name: "Balance", type: "text", align: "right", editing: false,
                        itemTemplate: function (value) {
                            return  value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                        }, width: 40},
                    {title: "Amount Receive", name: "RECEIVE", type: "text", align: "right", editing: true, filtering: false,
                        itemTemplate: function (value) {
                            return  value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                        }, width: 40},
                    {title: "Receipt No", name: "RCNO", type: "text", align: "center", editing: false, width: 50},
                ]
            });
        }

        function Call_LineGrid() {



            $("#LinereceiveGrid").show();
            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            $("#LinereceiveGrid").jsGrid({
                width: "100%",
                height: "300",
                filtering: true,
                inserting: false,
                editing: true,
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
                    },
                    updateItem: function (item) {

                        //todo linegrid 



                        var cono = <%out.print(session.getAttribute("cono"));%>
                        var divi = <%out.print(session.getAttribute("divi"));%>
                        console.log(item);
                        item.page = "Update_Line";
                        item.LR_CONO = cono;
                        item.LR_DIVI = divi;
                        item.LR_RCNO = $("#vRCNO").val();
                        var LineAMT = unformat(document.getElementById("vLR_AMT").value);
                        var RECEIVE = item.LR_INVAMT.replace(/,/g, '');
                        var HeadAmount = unformat(document.getElementById("vREAMT").value);
                        var Balance = item.LR_REAMT.replace(/,/g, '');
                        if (checkNegative(RECEIVE, Balance) === false) {
                            alert("Can't enter this receive");
                            $("#GridReceipt").jsGrid("loadData");
                            return;
                        }
                        if (RECEIVE < 0.00) {
                            RECEIVE = RECEIVE * (-1);
                        }

                        $.ajax({
                            type: "POST",
                            url: "./Sync",
                            dataType: 'json',
                            async: false,
                            cache: false,
                            data: item
                        });
                        $("#CustomerGrid").jsGrid("loadData");
                        $("#LinereceiveGrid").jsGrid("loadData");
                        Cal_LineAmountnew();


                    },
                    deleteItem: function (item) {
                        var cono = <%out.print(session.getAttribute("cono"));%>
                        var divi = <%out.print(session.getAttribute("divi"));%>
                        item.page = "Delete_Line";
                        item.LR_CONO = cono;
                        item.LR_DIVI = divi;
                        item.LR_RCNO = $("#vRCNO").val();
                        $.ajax({
                            type: "POST",
                            url: "./Sync",
                            async: false,
                            cache: false,
                            data: item
                        });
                        $("#CustomerGrid").jsGrid("loadData");
                        $("#LinereceiveGrid").jsGrid("loadData");
                        Cal_LineAmountnew();
                    }
                },
                fields: [
//                        {type: "control", width: 30},
                    {title: "Invoice No", width: 60, name: "LR_INVNO", type: "text", align: "center", editing: false},
//                    {title: "Customer Code", width: 60, name: "ESCUNO", type: "text", align: "center", editing: false},
                    {title: "Invoice Date", name: "LR_INVDT", type: "text", align: "center", editing: false, width: 50},
                    {title: "Invoice Amount", name: "LR_INVAMT", type: "text", align: "right", editing: false,
                        itemTemplate: function (value) {
                            return  value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                        }, width: 40},
                    {title: "Amount Receive", name: "LR_REAMT", type: "text", align: "right", editing: false, filtering: false,
                        itemTemplate: function (value) {
                            return  value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                        }, width: 40},
                    {title: "Receipt No.", name: "RCNO", type: "text", align: "center", editing: false, width: 50}
                ]
            });
        }
        function  Call_Receipt() {

            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            var cuno = $("#vCus").val();
            var fnno = document.getElementById("vFNCODE").value;

            $.ajax({
                type: 'GET',
                url: './Sync',
                dataType: 'text',
                data: {
                    page: "callrcno",
                    cono: cono,
                    divi: divi,
                    fnno: fnno,
                    cuno: cuno
                },
                async: false
            }).done(function (response) {

                document.getElementById("vRCNO").value = response;

                var rcno = $("#vRCNO").val();
                //alert(aa);








            });
        }
        function  Call_CustomerDDL_CLEAREXPENSE2() {


            $("#vCus").empty();
            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>

            $.ajax({
                type: 'GET',
                url: './Sync',
                dataType: 'json',
                data: {
                    page: "Grid_ARS200_cus_CLEAREXPENSE",
                    cono: cono,
                    divi: divi,
                    payer: "" + $("#vPAYER").val(),
                    FNNO: $("#vFNCODE").val()
                },
                async: false
            }).done(function (response) {
                //alert(response);

//todo PP


                if ($("#vBKTF").val() === '') {
                    $("#vCreaterc").hide();

                }
                if ($("#vBKTF").val() === 'none,none') {
                    $("#vCreaterc").hide();

                }
                if (response === '') {

                    $("#vCreaterc").hide();

                } else {
                    if ($("#vRCNO").val() === '') {

                    } else {
                        $("#vCreaterc").hide();
                    }
                }

//            

            });
        }

        function  Call_CustomerDDL_CLEAREXPENSE() {


            $("#vCus").empty();
            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>

            $.ajax({
                type: 'GET',
                url: './Sync',
                dataType: 'json',
                data: {
                    page: "Grid_ARS200_cus_CLEAREXPENSE",
                    cono: cono,
                    divi: divi,
                    payer: "" + $("#vPAYER").val(),
                    FNNO: $("#vFNCODE").val()
                },
                async: false
            }).done(function (response) {
                //alert(response);

//todo PP


                if ($("#vBKTF").val() === '') {
                    $("#vCreaterc").hide();

                }
                if ($("#vBKTF").val() === 'none,none') {
                    $("#vCreaterc").hide();

                }
                if (response === '') {

                    $("#vCreaterc").hide();

                } else {
                    if ($("#vRCNO").val() === '') {

                    } else {
                        $("#vCreaterc").hide();
                    }
                }

//            
                $.each(response, function (i, obj) {
                    var div_data = "<option value=" + obj.H_CUNO + ">" + obj.H_CUNO + "</option>";
                    $(div_data).appendTo('#vCus');
                });
            });
        }


        function SearchPY(code) {
            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            $.ajax({
                url: './Sync',
                type: 'GET',
                dataType: 'json',
                data: {
                    page: "SetnamePayer",
                    cono: cono,
                    divi: divi,
                    code: code
                },
                async: false
            }).done(function (response) {
                $.each(response, function (i, obj) {
                    document.getElementById("vPAYERNAME").value = obj.PAYERNAME;
                });
            });
            radiobtn();
        }


        function radiobtn() {
            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>

            var radioValue = $("input[name='optionsRadio']:checked").val();
            $("#row_check").hide();
            $("#row_transfer").hide();
            if (radioValue === 'CHECK') {
                $("#row_check").show();
                $('#vBKCH').empty().append('<option selected="selected" value="">Select bank</option>');
                $.ajax({
                    url: "./Sync",
                    type: "GET",
                    dataType: "json",
                    data: {
                        page: "List_Bank",
                        type: "CHECK",
                        code: $("#vPAYER").val(),
                        cono: cono,
                        divi: divi
                    },
                    success: function (getdata) {
                        $.each(getdata.data, function (i, obj) {
                            var div_data = "<option value=" + obj.BANKNAME + ">" + obj.BANKNAME + " : " + obj.BANKDESC + "</option>";
                            $(div_data).appendTo('#vBKCH');
                        });
                    }
                });
            } else if (radioValue === 'TRANSFER') {
                $("#row_transfer").show();
                $('#vBKTF').empty().append('<option selected="selected" value="NONE,NONE">Select bank</option>');
                $.ajax({
                    url: "./Sync",
                    type: "GET",
                    dataType: "json",
                    data: {
                        page: "List_Bank",
                        type: "TRANSFER",
                        code: $("#vPAYER").val(),
                        cono: cono,
                        divi: divi
                    },
                    success: function (getdata) {
                        $.each(getdata.data, function (i, obj) {
                            var div_data = "<option value=" + obj.ACCCODE + "," + obj.BANKNAME + ">" + obj.BANKDESC + "</option>";
                            $(div_data).appendTo('#vBKTF');
                        });


                    }
                });
            }
        }


        function SearchReceipt(RNNO) {
            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            document.getElementById("reportfield").style.display = "";

            $.ajax({
                type: 'GET',
                url: './Sync',
                dataType: 'json',
                data: {
                    page: "SearchReceipt_ClearExpensenew",
                    CONO: cono,
                    DIVI: divi,
                    RCNO: RNNO
                },
                async: false
            }).done(function (response) {
                console.log("response");
                $('#MyForm').trigger("reset");
                $.each(response, function (i, obj) {
                    formatNumber(obj.HC_REAMT, 'vREAMT');
                    document.getElementById("vTRDT").value = obj.HC_TRDT;
                    document.getElementById("vFNCODE").value = RNNO;
                    console.log(obj.HC_TYPE);
                    if (obj.HC_TYPE === "TRANSFER_EXP") {
                        document.getElementById("transfer").checked = true;
                        $("#row_transfer").show();
                        document.getElementById("vBKCHG").value = obj.HR_BKCHG;
                        document.getElementById("vDTTF").value = obj.SHC_PYDT;
                        document.getElementById("vBKTF").value = obj.HC_BANK;

                    } else if (obj.HC_TYPE === "CHECK_EXP") {
                        document.getElementById("check").checked = true;
                        $("#row_check").show();
                        document.getElementById("vCHKNO").value = obj.HC_CHKNO;
                        document.getElementById("vDTPM").value = obj.SHC_PYDT;
                        document.getElementById("vCHCHG").value = obj.HR_BKCHG;
                        document.getElementById("vBKCH").value = obj.HC_BANK;

                    }
                    $('#vTRDT').prop('readonly', true);
                    $("#vComplete").show();
//                    Call_GridReceipt();
//                    Cal_LineAmount();
//                    Cal_Expense();
//                    Call_GridARS200();
//                    GridLineClearExpense();
//                    Cal_LineAmountClearExpense();
                });
            });

        }

        function  Call_GridARS200() {
            $("#GridARS200").show();

            $("#GridARS200").jsGrid({
                width: "100%",
                height: "300",
                filtering: true,
                inserting: false,
                editing: true,
                sorting: true,
                paging: true,
                autoload: true,
                pageSize: 5,
                pageButtonCount: 5,
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
                                page: "Grid_ARS200_EXP",
                                cono: cono,
                                divi: divi,
                                payer: "" + $("#vPAYER").val(),
                                rcno: "" + $("#vRCNO").val()
                            },
                            async: false
                        }).done(function (response) {
//                            console.log(response);
                            response = $.grep(response, function (item) {
                                return (!filter.ESCINO || (item.ESCINO.indexOf(filter.ESCINO) > -1))
                                        && (!filter.ESCINO || (item.ESCINO.indexOf(filter.ESCINO) > -1))
                                        && (!filter.ESCUAM || (item.ESCUAM.indexOf(filter.ESCUAM) > -1));
                            });
                            data.resolve(response);
                        });
                        return data.promise();
                    },
                    updateItem: function (item) {
                        var cono = <%out.print(session.getAttribute("cono"));%>
                        var divi = <%out.print(session.getAttribute("divi"));%>
                        item.page = "InsertLineClear_EXP";
                        item.LREP_CONO = cono;
                        item.LREP_DIVI = divi;
                        item.LREP_RCNO = $("#vRCNO").val();

                        var LineAMT = unformat(document.getElementById("vLR_AMT").value);
                        var RECEIVE = item.RECEIVE.replace(/,/g, '');
                        var HeadAmount = unformat(document.getElementById("vREAMT").value);
                        var Balance = item.Balance.replace(/,/g, '');

                        if (checkNegative(RECEIVE, Balance) === false) {
                            alert("Can't enter this receive");
                            return;
                        }
                        if (RECEIVE < 0.00) {
                            RECEIVE = RECEIVE * (-1);
                        }
//                        if ((parseFloat(LineAMT) + parseFloat(RECEIVE)) <= parseFloat(HeadAmount)) {
                        $.ajax({
                            type: "POST",
                            url: "./Sync",
                            dataType: 'json',
                            async: false,
                            cache: false,
                            data: item
                        });
                        Cal_LineAmountClearExpense();
                        $("#GridARS200").jsGrid("loadData");
                        $("#GridReceipt").jsGrid("loadData");
                        $("#GridLineClearExpense").jsGrid("loadData");
                    }
                },
                fields: [
                    {type: "control", width: 30, deleteButton: false},
                    {title: "Invoice No", width: 60, name: "ESCINO", type: "text", align: "center", editing: false},
                    {title: "Customer Code", width: 60, name: "ESCUNO", type: "text", align: "center", editing: false},
                    {title: "Invoice Date", name: "ESIVDT", type: "text", align: "center", editing: false, width: 50},
                    {title: "Invoice Amount", name: "ESCUAM", type: "text", align: "right", editing: false,
                        itemTemplate: function (value) {
                            return  value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                        }, width: 40},
                    {title: "Amount Receive", name: "RECEIVE", type: "text", align: "right", editing: true, filtering: false,
                        itemTemplate: function (value) {
                            return  value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                        }, width: 40}
//                            ,{title: "Remark", name: "Remark", type: "text", align: "left", filtering: false, editing: true}
                ]
            });

        }


        function Cal_LineAmountnew() {
            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            $.ajax({
                type: 'GET',
                url: './Sync',
                dataType: 'json',
                data: {
                    page: "Cal_LineAmountnew",
                    CONO: cono,
                    DIVI: divi,
                    RCNO: "" + $("#vRCNO").val(),
                    FNNO: document.getElementById("vFNCODE").value
                },
                async: false
            }).done(function (response) {
                $.each(response, function (i, obj) {
                    var balance = $("#vREAMT").val();
                    balance = unformat(balance);
                    balance = parseFloat(balance) - obj.LineAmout;
                    //document.getElementById("vLR_AMT").value = formatNumber(obj.LineAmout, "vLR_AMT");
                    document.getElementById("vBalance").value = formatNumber("" + balance, "vBalance");

//                    console.log("vBalance " + balance);
                });
            });
        }



        function GridLineClearExpense() {
            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            $("#GridLineClearExpense").show();

            $("#GridLineClearExpense").jsGrid({
                width: "100%",
                height: "300",
                filtering: true,
                inserting: false,
                editing: true,
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
                                page: "GridLineClearExpense",
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
                    },
                    updateItem: function (item) {
                        var cono = <%out.print(session.getAttribute("cono"));%>
                        var divi = <%out.print(session.getAttribute("divi"));%>
                        console.log(item);
                        item.page = "Update_LineClearExpense";
                        item.LREP_CONO = cono;
                        item.LREP_DIVI = divi;
                        item.LREP_RCNO = $("#vRCNO").val();


                        var LineAMT = unformat(document.getElementById("vLR_AMT").value);
                        var RECEIVE = item.LREP_INVAMT.replace(/,/g, '');
                        var HeadAmount = unformat(document.getElementById("vREAMT").value);
                        var Balance = item.LREP_EXPAMT.replace(/,/g, '');

                        if (checkNegative(RECEIVE, Balance) === false) {
                            alert("Can't enter this receive");
                            $("#GridLineClearExpense").jsGrid("loadData");
                            return;
                        }
                        if (RECEIVE < 0.00) {
                            RECEIVE = RECEIVE * (-1);
                        }

                        $.ajax({
                            type: "POST",
                            url: "./Sync",
                            dataType: 'json',
                            async: false,
                            cache: false,
                            data: item
                        });

                        $("#CustomerGrid").jsGrid("loadData");
                        $("#GridLineClearExpense").jsGrid("loadData");

                        Cal_LineAmountClearExpense();

                    },
                    deleteItem: function (item) {
                        var cono = <%out.print(session.getAttribute("cono"));%>
                        var divi = <%out.print(session.getAttribute("divi"));%>
                        item.page = "Delete_LineClearExpense";
                        item.LREP_CONO = cono;
                        item.LREP_DIVI = divi;
                        item.LREP_RCNO = $("#vRCNO").val();
                        $.ajax({
                            type: "POST",
                            url: "./Sync",
                            async: false,
                            cache: false,
                            data: item
                        });
                        $("#CustomerGrid").jsGrid("loadData");
                        $("#GridLineClearExpense").jsGrid("loadData");
                        Cal_LineAmountClearExpense();


                    }
                },
                fields: [
                    {type: "control", width: 30},
                    {title: "Invoice No", width: 60, name: "LREP_INVNO", type: "text", align: "center", editing: false},
//                    {title: "Customer Code", width: 60, name: "ESCUNO", type: "text", align: "center", editing: false},
                    {title: "Invoice Date", name: "LREP_INVDT", type: "text", align: "center", editing: false, width: 50},
                    {title: "Invoice Amount", name: "LREP_INVAMT", type: "text", align: "right", editing: false,
                        itemTemplate: function (value) {
                            return  value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                        }, width: 40},
                    {title: "Amount Receive", name: "LREP_EXPAMT", type: "text", align: "right", editing: true, filtering: false,
                        itemTemplate: function (value) {
                            return  value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                        }, width: 40}
                ]
            });

        }



        function Call_GridReceipt() {
            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            $("#GridReceipt").show();

            $("#GridReceipt").jsGrid({
                width: "100%",
                height: "300",
                filtering: true,
                inserting: false,
                editing: false,
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
//                    {title: "Customer Code", width: 60, name: "ESCUNO", type: "text", align: "center", editing: false},
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

        function Call_GridExpense() {
            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            $("#GridExpense").show();

            $("#GridExpense").jsGrid({
                width: "100%",
                height: "300",
                filtering: true,
                inserting: false,
                editing: false,
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
                                page: "Grid_Expensenew",
                                cono: cono,
                                divi: divi,
                                fnno: "" + $("#vFNCODE").val()
                            },
                            async: false
                        }).done(function (response) {
                            data.resolve(response);
                        });
                        return data.promise();
                    }
                },
                fields: [
                    {title: "Description", width: 100, name: "ACTDESC", type: "text", align: "left", editing: false},
//                    {title: "Customer Code", width: 60, name: "ESCUNO", type: "text", align: "center", editing: false},
                    {title: "Account Code", name: "LRE_ACTCODE", type: "text", align: "center", editing: false, width: 50},
                    {title: "Expense", name: "LRE_AMT", type: "text", align: "right", editing: false,
                        itemTemplate: function (value) {
                            return  value.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
                        }, width: 40}
                ]
            });

        }


        function Cal_LineAmount() {
            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            $.ajax({
                type: 'GET',
                url: './Sync',
                dataType: 'json',
                data: {
                    page: "Cal_LineAmount",
                    CONO: cono,
                    DIVI: divi,
                    RCNO: "" + $("#vRCNO").val()
                },
                async: false
            }).done(function (response) {
                $.each(response, function (i, obj) {
                    var balance = $("#vREAMT").val();
                    balance = unformat(balance);
                    balance = parseFloat(balance) - obj.LineAmout;
                    document.getElementById("vLR_AMT").value = formatNumber(obj.LineAmout, "vLR_AMT");
                    document.getElementById("vBalance").value = formatNumber("" + balance, "vBalance");

//                     console.log("vBalance " + balance);
                });
            });
        }

        function Cal_Expense() {
            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            $.ajax({
                type: 'GET',
                url: './Sync',
                dataType: 'json',
                data: {
                    page: "AmountExpensenew",
                    CONO: cono,
                    DIVI: divi,
                    FNNO: "" + $("#vFNCODE").val()
                },
                async: false
            }).done(function (response) {
                $.each(response, function (i, obj) {
                    console.log(obj.AMTEXPENSE);
                    document.getElementById("vEXPENSE").value = formatNumber("" + obj.AMTEXPENSE, "vEXPENSE");

                });
            });
        }


        function formatNumber(num, id) {
            num = num.replace(/,/g, '');
            num = parseFloat(num);
            num = num.toFixed(2);
            return document.getElementById(id).value = num.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
        }

        function unformat(num) {
            return num.replace(/,/g, '');
        }


        $("#vComplete").click(function (e) {

            alert($("#vFNCODE").val());

            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            var form = $('#MyForm')[0];
            // Create an FormData object 
            var data = new FormData(form);

            console.log(data);

            data.append("REPRINT", "CLEAR_EXPENSE");


            document.getElementById("vComplete").disabled = true;
            $.ajax({
                type: "POST",
                enctype: 'multipart/form-data',
                url: "./Report",
                data: data,
                processData: false,
                contentType: false,
                cache: false,
//                timeout: 600000,
                async: true,
                success: function (data) {
                    $.ajax({
                        type: 'GET',
                        url: './Sync',
                        dataType: 'json',
                        data: {
                            page: "CheckVoucher_EXPnew",
                            FNNO: $("#vFNCODE").val(),
                            cono: cono,
                            divi: divi
                        },
                        async: false
                    }).done(function (response) {
                        $.each(response, function (i, obj) {
                            alert(obj.Voucher);
                            location.reload(0);
                        });
                    });

                },
                error: function (e) {
                    alert("ล้มเหลว");
                }
            });


        });


        function checkNegative(num1, num2) {
            var str1 = "" + num1;
            var n1 = str1.indexOf("-");
            var str2 = "" + num2;
            var n2 = str2.indexOf("-");
            console.log(str1 + "-" + n1);
            console.log(str2 + "-" + n2);

            if (n1 !== n2) {
                return false;
            } else {
                return true;
            }

        }



        function Cal_LineAmountClearExpense() {
            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            $.ajax({
                type: 'GET',
                url: './Sync',
                dataType: 'json',
                data: {
                    page: "Cal_LineAmountClearExpense",
                    CONO: cono,
                    DIVI: divi,
                    RCNO: "" + $("#vRCNO").val()
                },
                async: false
            }).done(function (response) {
                $.each(response, function (i, obj) {

                    var HC_REAMT = unformat($("#vREAMT").val());
                    var LINE_AMT = unformat($("#vLR_AMT").val());
                    var EXP_AMT = unformat($("#vEXPENSE").val());
                    var CLEAR_EXP = unformat(obj.LREP_EXPAMT);
//                    var balance = (parseFloat(HC_REAMT) + parseFloat(EXP_AMT)) - parseFloat(LINE_AMT);
//                    balance = parseFloat(balance) - parseFloat(obj.LREP_EXPAMT);

                    var balance = parseFloat(EXP_AMT) - (CLEAR_EXP);

                    document.getElementById("vBalance").value = formatNumber("" + balance, "vBalance");
//                    document.getElementById("vClear_AMT").value = (formatNumber(obj.LREP_EXPAMT, "vClear_AMT") - formatNumber(EXP_AMT,"vEXPENSE")).toFixed(2);;

                    document.getElementById("vClear_AMT").value = formatNumber(obj.LREP_EXPAMT, "vClear_AMT");

                    console.log("EXP_AMT " + parseFloat(EXP_AMT));
                     console.log("CLEAR_EXP " + parseFloat(CLEAR_EXP));
                     console.log("balance " + balance);
                });
            });
        }

    </script>
</html>