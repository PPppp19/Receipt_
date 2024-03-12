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
                <div class="row">
                    <div class="panel panel-default container" id="reportfield" >
                        <div class="panel-heading" style="background-color: #999999">
                            <h3 class="panel-title"> <font color="#FFFFFF" >Receipt : <%=namecom%></font></h3>
                        </div>
                        <div class="container panel-body col-xs-12 col-sm-12 col-md-12 col-lg-12 ">
                            <div class="row ">
                                <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                    <label>Customer Code :
                                        <input type="text" class="form-control text-center" value="" id="vPAYER" name="vPAYER" onblur="SearchPY(this.value);" maxlength="10">
                                    </label>
                                </div>
                                <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                    <label>Customer Name :
                                        <input type="text" class="form-control text-center" value="" id="vPAYERNAME" name="vPAYERNAME" readonly="true" >
                                    </label>
                                </div>
                                <div class="col-xs-4 col-sm-4 col-md-3 col-lg-2">
                                    <label>Amount Receive :
                                        <input type="text" class="form-control text-center" value="" id="vREAMT" name="vREAMT" onblur="formatNumber(this.value, this.id)">
                                    </label>
                                </div>
                                <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                    <label>Transaction Date :
                                        <input type="date" class="form-control" id="vTRDT" name="vTRDT">
                                    </label>
                                </div>
                                <br>
                                <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                    <button class="btn btn-success" id="vCreate" name="vCreate" type="button" >Create Receipt</button>
                                </div>
                                <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                    <button class="btn btn-success" id="vUpdate" name="vUpdate" type="button" >Update Receipt</button>
                                </div>
                            </div> 
                            <br>
                            <div class="row">
                                <div class="form-group">
                                    <div class="col-md-12">
                                        <label class="col-md-2 text-right" >TYPE :</label>
                                        <div class="col-md-4">
                                            <input  type="radio" name="optionsRadio" id="cash" value="CASH" onclick="radiobtn()">&nbsp;<label class="control-label">CASH</label>
                                        </div>
                                        <div class="col-md-4">
                                            <input  type="radio" name="optionsRadio" id="transfer" value="TRANSFER" onclick="radiobtn()">&nbsp;<label class="control-label">TRANSFER</label>
                                        </div>
                                        <div class="col-md-4">
                                            <input  type="radio" name="optionsRadio" id="check" value="CHECK" onclick="radiobtn()">&nbsp;<label class="control-label">CHECK</label>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <br>
                            <div class="row " id="row_transfer" hidden="true">
                                <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                    <label>Date Transfer :
                                        <input type="date" class="form-control text-center"  id="vDTTF" name="vDTTF" onchange="checkdate(this.value);"> 
                                    </label>
                                </div>
                                <div class="col-xs-8 col-sm-6 col-md-6 col-lg-4">
                                    <label style="width: 100%">Bank Transfer1 :
                                        <select class="form-control form-control-user" name="vBKTF" id="vBKTF" >
                                            <option value="none,none" selected="selected">Select Bank</option>
                                        </select>
                                    </label>
                                </div>
                                <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                    <label>Bank Charge :
                                        <input type="text" class="form-control text-center"  id="vBKCHG" name="vBKCHG" min="0" value="0.00"> 
                                    </label>
                                </div>
                                <br>
                            </div>
                            <div class="row " id="row_check" hidden="true">
                                <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                    <label>Date Payment :
                                        <input type="date" class="form-control text-center"  id="vDTPM" name="vDTPM">
                                    </label>
                                </div>
                                <div class="col-xs-8 col-sm-6 col-md-6 col-lg-4">
                                    <label style="width: 100%">Bank Transfer :
                                        <select class="form-control form-control-user" name="vBKCH" id="vBKCH" >
                                            <option value="" selected="selected">Select Bank</option>
                                        </select>
                                    </label>
                                </div>
                                <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                    <label>Check  Number :
                                        <input type="text" class="form-control text-center"  id="vCHKNO" name="vCHKNO">
                                    </label>
                                </div>
                                <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                    <label>Bank Charge :
                                        <input type="text" class="form-control text-center"  id="vCHCHG" name="vCHCHG" min="0" value="0.00"> 
                                    </label>
                                </div>
                                <br>
                            </div>

                            <div class="row">
                                <form  method="POST" enctype="multipart/form-data" acceptcharset="UTF-8" id="MyForm" >
                                    <!--<form  method="GET"  id="MyForm" action="./Report" >-->
                                    <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                        <label>Running Number :
                                            <input type="text" class="form-control text-center"  value="" id="vRCNO" name="vRCNO" maxlength="8" >
                                        </label>
                                    </div>
                                    <div class="col-xs-5 col-sm-4 col-md-3 col-lg-2">
                                        <label>Line Amount Receive :
                                            <input type="text" class="form-control text-center" readonly="true" value="0.00" id="vLR_AMT" name="vLR_AMT" >
                                        </label>
                                    </div>
                                    <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                        <label>Balance Amount :
                                            <input type="text" class="form-control text-center" readonly="true" value="0.00" id="vBalance" name="vBalance" >
                                        </label>
                                    </div>
                                    <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                        <label style="width: 100%">Select Printer :
                                            <select class="form-control form-control-user" name="vPrinter" id="vPrinter"">

                                            </select>
                                        </label>
                                    </div>
                                    <br>
                                    <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
                                        <button class="btn btn-info" id="vSearch" name="vSearch" type="button">Search Receipt</button>
                                    </div>
                                    <!--<button class="btn btn-danger" id="vComplete" name="vComplete" type="submit" style="position: absolute;right: 5px;">PRINT</button>-->
                                    <button class="btn btn-danger" id="vComplete" name="vComplete" type="button" style="position: absolute;right: 5px;">PRINT</button>
                                </form>
                            </div>
                            <div class="row">
                                <div class="col-xs-4 col-md-3 col-lg-2"><br>
                                    <label style="width: 100%">Select Type Receiver :
                                        <select class="form-control form-control-user" name="vTypeRec" id="vTypeRec" onchange="Receiver(this.value)">
                                            <option value="" selected="">Select Type  </option>
                                            <option value="FRONT">Front</option>
                                            <option value="SALE">Sale</option>
                                            <option value="BILL">Bill Collector</option>
                                            <option value="DRIVER">Driver</option>
                                        </select>
                                    </label>
                                </div>
                                <div class="col-xs-4 col-md-3 col-lg-2"><br>
                                    <label style="width: 100%">Receiver :
                                        <select class="form-control form-control-user" name="vRec" id="vRec" onchange="document.getElementById('vReceiver').value = this.value">
                                        </select>
                                    </label>
                                </div>
                                <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                                    <br><br>
                                    <input type="text" class="form-control col-lg-3 col-md-3" id="vReceiver" name="vReceiver" maxlength="65">
                                </div>
                                <br><br>
                                <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
                                    <button class="btn btn-info" id="vAddRec" name="vAddRec" type="button">Update Receiver</button>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                    <button class="btn btn-success" id="vRefresh" name="vRefresh" type="button">Refresh Table</button>
                                </div>
                            </div>
                            <br>
                            <div class="row" >
                                <div id="GridARS200"></div>   
                            </div>
                            <br>
                            <div class="row" >
                                <div id="GridReceipt"></div>   
                            </div>
                        </div>
                    </div>
                </div> 
            </div> 
        </div>


    </body>

    <script type="text/javascript">
        $("#vUpdate").hide();
        $("#vRefresh").hide();


        function  AddUpdateReceiver() {
            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            if ($("#vRCNO").val() !== "" && $("#vTypeRec").val() !== "") {
                $.ajax({
                    url: './Sync',
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        page: "InsertReceiver",
                        CONO: cono,
                        DIVI: divi,
                        RCNO: $("#vRCNO").val(),
                        TYPE: $("#vTypeRec").val(),
                        RECEIVER: $("#vReceiver").val()
                    },
                    async: false
                }).done(function (response) {
                });
//                alert("สำเร็จ");
            }
        }

        $("#vAddRec").click(function (e) {
            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            if ($("#vRCNO").val() !== "" && $("#vTypeRec").val() !== "") {
                $.ajax({
                    url: './Sync',
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        page: "InsertReceiver",
                        CONO: cono,
                        DIVI: divi,
                        RCNO: $("#vRCNO").val(),
                        TYPE: $("#vTypeRec").val(),
                        RECEIVER: $("#vReceiver").val()
                    },
                    async: false
                }).done(function (response) {
                });
                alert("สำเร็จ");
            } else {
                alert("Please check data");
            }

        });

        function Receiver(value) {
            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            if (value === "DRIVER") {
                $.ajax({
                    url: './Sync',
                    type: 'GET',
                    dataType: 'json',
                    data: {
                        page: "Get_Receiver_Driver",
                        cono: cono,
                        divi: divi,
                        rcno: $("#vRCNO").val()
                    },
                    async: false
                }).done(function (response) {
                    $.each(response, function (i, obj) {
                        document.getElementById("vReceiver").value = obj.CAR;
                    });
                });

                $("#vRec").empty();
                var div_data = "<option value=>" + "Select" + "</option>";
                $(div_data).appendTo('#vRec');
                $.ajax({
                    url: "./Sync",
                    type: "GET",
                    dataType: "json",
                    data: {
                        page: "List_Get_Receiver_Driver",
                        cono: cono,
                        divi: divi,
                        rcno: $("#vRCNO").val()
                    },
                    success: function (getdata) {
                        $.each(getdata.data, function (i, obj) {
                            var div_data = "<option value='" + obj.CAR + "'>" + obj.CAR + "</option>";
                            $(div_data).appendTo('#vRec');
                        });
                    }
                });


            } else if (value === "SALE") {
                $.ajax({
                    url: './Sync',
                    type: 'GET',
                    dataType: 'json',
                    data: {
                        page: "Get_Receiver_Sale",
                        cono: cono,
                        divi: divi,
                        pyno: $("#vPAYER").val()
                    },
                    async: false
                }).done(function (response) {
                    $.each(response, function (i, obj) {
                        document.getElementById("vReceiver").value = obj.JUUSID;
                    });
                });
            } else if (value === "FRONT") {
                $("#vRec").empty();
                var div_data = "<option value=>" + "Select" + "</option>";
                $(div_data).appendTo('#vRec');
                $.ajax({
                    url: "./Sync",
                    type: "GET",
                    dataType: "json",
                    data: {
                        page: "List_Receiver",
                        cono: cono,
                        divi: divi,
                        type: "FRONT"
                    },
                    success: function (getdata) {
                        $.each(getdata.data, function (i, obj) {
                            console.log(obj);
                            var div_data = "<option value='" + obj.MR_DESC + "'>" + obj.MR_DESC + "</option>";
                            $(div_data).appendTo('#vRec');
                        });
                    }
                });
            } else if (value === "BILL") {
                $("#vRec").empty();
                var div_data = "<option value=>" + "Select" + "</option>";
                $(div_data).appendTo('#vRec');
                $.ajax({
                    url: "./Sync",
                    type: "GET",
                    dataType: "json",
                    data: {
                        page: "List_Receiver",
                        cono: cono,
                        divi: divi,
                        type: "BILL"
                    },
                    success: function (getdata) {
                        $.each(getdata.data, function (i, obj) {
                            console.log(obj);
                            var div_data = "<option value='" + obj.MR_DESC + "'>" + obj.MR_DESC + "</option>";
                            $(div_data).appendTo('#vRec');
                        });
                    }
                });
            }
        }

        function checkdate(date) {

            var selectdate = date.replace(/\-/g, '');
            var today = new Date();

            var dd = today.getDate();
            var mm = today.getMonth() + 1; //January is 0!

            var yyyy = today.getFullYear();
            if (dd < 10) {
                dd = '0' + dd;
            }
            if (mm < 10) {
                mm = '0' + mm;
            }
            today = yyyy + '' + mm + '' + dd;
            if (selectdate > today) {
                today = yyyy + '-' + mm + '-' + dd;
                alert("วันที่เกินปัจจุบัน");
                document.getElementById('vDTTF').value = today;

            }

        }
//        Call_GridARS200();
//        Call_GridReceipt();


        $("#vRefresh").click(function (e) {
            Call_GridARS200();
            Call_GridReceipt();
        });

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
                            //console.log(div_data)
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


        $('#vPAYER').on('input', function (evt) {
            $(this).val(function (_, val) {
                return val.toUpperCase();
            });
        });

        $(document).ready(function () {
            $("#FormDetail").hide();
//            document.getElementById("vComplete").style.display = "none";
            $("#GridARS200").hide();
            $("#GridReceipt").hide();
            List_Printer();
            var today = new Date();
            var dd = today.getDate();
            var mm = today.getMonth() + 1; //January is 0!

            var yyyy = today.getFullYear();
            if (dd < 10) {
                dd = '0' + dd;
            }
            if (mm < 10) {
                mm = '0' + mm;
            }
            var today = yyyy + '-' + mm + '-' + dd;
            document.getElementById('vTRDT').value = today;

        });


        function List_Printer() {
            var username = "<%out.print(session.getAttribute("username"));%>";
            var locations = "<%out.print(session.getAttribute("locations"));%>";


             if ((username === "JILASA_SAM" || username === "SANTIM_PUP" || username === "TAWA_HOA" || username === "NITTAY_KOM")) {
                var div_data = "<option value=RECEIPT_PRINTER2>" + "PRINTER 2" + "</option>";
                $(div_data).appendTo('#vPrinter');
            } else if (locations === "DC_CM") {
                div_data = "<option value=RECEIPT_PRINTER3>" + "PRINTER DC/CM" + "</option>";
                $(div_data).appendTo('#vPrinter');
            } else if (locations === "HC_PCB") {
                div_data = "<option value=RECEIPT_PRINTER3>" + "PRINTER HC" + "</option>";
                $(div_data).appendTo('#vPrinter');
            } else if (locations === "FM") {
                div_data = "<option value=RECEIPT_PRINTER3>" + "PRINTER FM" + "</option>";
                $(div_data).appendTo('#vPrinter');
            } else if (locations === "OTHER" || username === "SUWANN_RAK") {
                div_data = "<option value=RECEIPT_PRINTER3>" + "PRINTER OTHER" + "</option>";
                $(div_data).appendTo('#vPrinter');
            } else {
                var div_data = "<option value=RECEIPT_PRINTER>" + "PRINTER 1" + "</option>";
                $(div_data).appendTo('#vPrinter');
                div_data = "<option value=RECEIPT_PRINTER2>" + "PRINTER 2" + "</option>";
                $(div_data).appendTo('#vPrinter');

            }


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

        $("#vCreate").click(function (e) {
            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            var username = "<%out.print(session.getAttribute("username"));%>";
            var locations = "<%out.print(session.getAttribute("locations"));%>";
            if ($("#vTRDT").val() === "" || $("#vPAYER").val().length < 10) {
                alert("Please check data !!");
                return;
            }

            var HC_PYDT = "0";
            var HC_CHKNO = "";
            var HC_BANK = "";
            var HC_ACCODE = "";
            var HR_BKCHG = 0.00;
            var radioValue = $("input[name='optionsRadio']:checked").val();

            if (radioValue === undefined) {
                alert("Please select type of receipt !!");
                return false;
            }

            if (radioValue === 'TRANSFER') {
                HC_PYDT = $("#vDTTF").val();
                var input = $("#vBKTF").val();
                var fields = input.split(',');
                HC_ACCODE = fields[0];
                HC_BANK = fields[1];
                if ($("#vDTTF").val() === "" || $("#vBKTF").val() === "") {
                    HC_PYDT = "0";
                }
                HR_BKCHG = $("#vBKCHG").val();

            } else if (radioValue === 'CHECK') {
                HC_PYDT = $("#vDTPM").val();
                HC_BANK = $("#vBKCH").val();
                HC_CHKNO = $("#vCHKNO").val();
                if ($("#vDTPM").val() === "") {
                    HC_PYDT = "0";
                }
                HR_BKCHG = $("#vCHCHG").val();

            }

            $.ajax({
                url: './Sync',
                type: 'POST',
                dataType: 'json',
                data: {
                    page: "InsertHeader",
                    HR_CONO: cono,
                    HR_DIVI: divi,
                    HC_TRDT: $("#vTRDT").val(),
                    HC_PYNO: $("#vPAYER").val(),
                    HC_REAMT: $("#vREAMT").val().replace(/,/g, ''),
                    HC_TYPE: radioValue,
                    HC_PYDT: HC_PYDT,
                    HC_CHKNO: HC_CHKNO,
                    HC_BANK: HC_BANK,
                    HC_ACCODE: HC_ACCODE,
                    HC_USER: username,
                    HR_BKCHG: HR_BKCHG,
                    locations: locations
                },
                async: false
            }).done(function (response) {
                $.each(response, function (i, obj) {
                    document.getElementById("vRCNO").value = obj.HC_RCNO;
//                    document.getElementById("vPAYERNAME").value = obj.OKCUNM;
                });
            });
//            var CheckRCNO = CheckCreateReceipt();
            if (document.getElementById("vRCNO").value === "-") {
                alert("ERROR CREATE RECEIPT !!");
                location.reload(0);
            } else {
                $("#vSearch").click();
                alert("Submit complete");
                $("#vSearch").hide();
                $("#vCreate").hide();
                $("#vUpdate").show();
                $("#vSearch").hide();

                document.getElementById("vComplete").style.display = "";
                $('#vTRDT').prop('readonly', true);
                $('#vPAYER').prop('readonly', true);
                $('#vREAMT').prop('readonly', true);
                $('#vRCNO').prop('readonly', true);
//                Call_GridARS200();
            }


        });

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
                                page: "Grid_ARS200",
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
                        item.page = "InsertLine";
                        item.LR_CONO = cono;
                        item.LR_DIVI = divi;
                        item.LR_RCNO = $("#vRCNO").val();

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

                        Call_GridReceipt();
                        Cal_LineAmount();

                        $("#GridARS200").jsGrid("loadData");
                        $("#GridReceipt").jsGrid("loadData");
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
                        }, width: 40}
//                            ,{title: "Remark", name: "Remark", type: "text", align: "left", filtering: false, editing: true}
                ]
            });

        }


        function Call_GridReceipt() {
            $("#GridReceipt").show();
            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            $("#GridReceipt").jsGrid({
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

                        $("#GridARS200").jsGrid("loadData");
                        $("#GridReceipt").jsGrid("loadData");
                        Cal_LineAmount();

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
                        $("#GridARS200").jsGrid("loadData");
                        $("#GridReceipt").jsGrid("loadData");
                        Cal_LineAmount();

                    }
                },
                fields: [
                    {type: "control", width: 30},
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


//        $("#vComplete").click(function () {
//            alert("Complete");
//            location.reload();
//        });


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
                });
            });
        }


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

        $('#vSearch').click(function (e) {


            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            var locations = "<%out.print(session.getAttribute("locations"));%>";
            $.ajax({
                type: 'GET',
                url: './Sync',
                dataType: 'json',
                data: {
                    page: "SearchReceipt00",
                    CONO: cono,
                    DIVI: divi,
                    RCNO: "" + $("#vRCNO").val(),
                    locations: locations
                },
                async: false
            }).done(function (response) {
                console.log(response);
                $('#MyForm').trigger("reset");
                $.each(response, function (i, obj) {
                    document.getElementById("vRCNO").value = obj.HC_RCNO;
                    document.getElementById("vPAYER").value = obj.HC_PYNO;
                    formatNumber(obj.HC_REAMT, 'vREAMT');
                    document.getElementById("vTRDT").value = obj.HC_TRDT;
                    document.getElementById("vReceiver").value = obj.RR_DESC;
                    document.getElementById("vPAYERNAME").value = obj.OKCUNM;
                    console.log(obj.HC_TYPE);
                    if (obj.HC_TYPE === "CASH") {
                        document.getElementById("cash").checked = true;
                    } else if (obj.HC_TYPE === "TRANSFER") {
                        document.getElementById("transfer").checked = true;
                        radiobtn();
                        document.getElementById("vBKCHG").value = obj.HR_BKCHG;
                        document.getElementById("vDTTF").value = obj.SHC_PYDT;
                        const bank = [obj.HC_ACCODE, obj.HC_BANK];
                        setTimeout(
                                function ()
                                {
                                    $("#vBKTF").val(bank.join(',')).change();
                                }, 500);

                    } else if (obj.HC_TYPE === "CHECK") {
                        document.getElementById("check").checked = true;
                        radiobtn();
                        document.getElementById("vCHKNO").value = obj.HC_CHKNO;
                        document.getElementById("vDTPM").value = obj.SHC_PYDT;
                        document.getElementById("vCHCHG").value = obj.HR_BKCHG;
                        setTimeout(
                                function ()
                                {
                                    $("#vBKCH").val(obj.HC_BANK).change();

                                }, 500);

                    } else if (obj.HC_TYPE === "CHECK_EXP" || obj.HC_TYPE === "TRANSFER_EXP") {

                        alert("ใบเสร็จนี้มีค่าใช้จ่าย!!");
                        location.reload(0);
                        return;
                    }
                    $('#vTRDT').prop('readonly', true);
                    $('#vRCNO').prop('readonly', true);
//                    $('#vREAMT').prop('readonly', true);
                    $("#vRefresh").show();
                    $("#vUpdate").show();
                    $("#vComplete").show();
                    $("#vCreate").hide();
                    Call_GridARS200();
                    Call_GridReceipt();
                    Cal_LineAmount();

                });
                if (document.getElementById("vRCNO").value === "") {
                    alert("ไม่สามารถเรียกใบเสร็จนี้ได้ โปรดทำการตรวจสอบสถานะหรือโลเคชั่น !!");
                    location.reload(0);
                }

            });


        });



        $("#vUpdate").click(function (e) {

            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            var HC_PYDT = "0";
            var HC_CHKNO = "";
            var HC_BANK = "";
            var HC_ACCODE = "";
            var HR_BKCHG = 0.00;
            var radioValue = $("input[name='optionsRadio']:checked").val();
            if (radioValue === 'TRANSFER') {
                HC_PYDT = $("#vDTTF").val();
                var input = $("#vBKTF").val();
                var fields = input.split(',');
                HC_ACCODE = fields[0];
                HC_BANK = fields[1];
                if ($("#vDTTF").val() === "" || $("#vBKTF").val() === "") {
//                    alert("Please check data !");
//                    return;
                    HC_PYDT = "0";
                }
                HR_BKCHG = $("#vBKCHG").val();

            } else if (radioValue === 'CHECK') {
                HC_PYDT = $("#vDTPM").val();
                HC_BANK = $("#vBKCH").val();
                HC_CHKNO = $("#vCHKNO").val();
                if ($("#vDTPM").val() === "") {
//                    alert("Please select date payment !");
//                    return;
                    HC_PYDT = "0";
                }
                HR_BKCHG = $("#vCHCHG").val();
            }

            if ($("#vPAYER").val().length < 10) {
                alert("Please check data !!");
                return;
            }
            $.ajax({
                url: './Sync',
                type: 'POST',
                dataType: 'json',
                data: {
                    page: "UpdateHeader",
                    HR_CONO: cono,
                    HR_DIVI: divi,
                    HC_PYNO: $("#vPAYER").val(),
                    HC_REAMT: $("#vREAMT").val().replace(/,/g, ''),
                    HC_TYPE: radioValue,
                    HC_PYDT: HC_PYDT,
                    HC_CHKNO: HC_CHKNO,
                    HC_BANK: HC_BANK,
                    HC_ACCODE: HC_ACCODE,
                    HR_BKCHG: HR_BKCHG,
                    HR_RCNO: $("#vRCNO").val()
                },

                async: false
            });
            alert("Complete");
            $("#vRefresh").click();
        });


        $("#vComplete").click(function (e) {
            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            var form = $('#MyForm')[0];
            
            // Create an FormData object 
            var data = new FormData(form);
            data.append("REPRINT", "NONE");
            var vRCNO = "" + $("#vRCNO").val();
            var radioValue = $("input[name='optionsRadio']:checked").val();
            if (radioValue !== "TRANSFER" || $("#vREAMT").val().replace(/,/g, '') !== "0.00") {
                AddUpdateReceiver();
            }

            if (radioValue !== "TRANSFER" && $("#vREAMT").val().replace(/,/g, '') !== "0.00") {
                var check = CheckCountReceiver();
                if (check === 0) {
                    alert("Please check receiver");
                    return;
                }
            }

            ////////// CHECK ////////// 
            if (CheckRunningPrint() === "N") {
                alert("หมายเลขใบเสร็จรับเงินเกินจำนวนที่กำหนด กรุณาตั้งค่าใหม่");
                return;
            }
            ////////// CHECK //////////

            ;

            if (CountInvoice() === "0") {
                alert("Can't print because the receipt doesn't have a invoice !!");
                return;
            }


            if (CheckInvoice() === 0) {
                document.getElementById("vComplete").disabled = true;
                $.ajax({
                    type: "POST",
                    enctype: 'multipart/form-data',
                    url: "./Report",
                    data: data,
                    processData: false,
                    contentType: false,
                    cache: false,
                    async: true,
                    success: function (data) {
                        $.ajax({
                            type: 'GET',
                            url: './Sync',
                            dataType: 'json',
                            data: {
                                page: "CheckVoucher",
                                RCNO: "" + $("#vRCNO").val(),
                                cono: cono,
                                divi: divi
                            },
                            async: false
                        }).done(function (response) {
                            $.each(response, function (i, obj) {
                                alert(obj.Voucher + " \n" + "Fix Receipt Number : " + obj.Fix_Running);
                                if ($("#vPrinter").val() === "RECEIPT_PRINTER3") {
                                    location.href = "/RECEIPT/Report?vRCNO=" + vRCNO + "&vReport=RP01";
                                } else {
                                    location.reload(0);
                                }
                            });
                        });
                    },
                    error: function (e) {
                        alert("ล้มเหลว");
                    }
                });
            }
        });


        function CheckInvoice() {
            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            var msg, count;
            $.ajax({
                type: 'GET',
                url: './Sync',
                dataType: 'json',
                data: {
                    page: "CheckInvoiceLock",
                    RCNO: "" + $("#vRCNO").val(),
                    cono: cono,
                    divi: divi
                },
                async: false
            }).done(function (response) {
                $.each(response, function (i, obj) {
                    msg = obj.Invoice;
                    count = obj.Count;
                });
            });
            if (count > 0) {
                alert(msg + " is lock. Please unlock this invoice.");
            }
            return count;
        }

        function CountInvoice() {
            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            var count;
            $.ajax({
                type: 'GET',
                url: './Sync',
                dataType: 'json',
                data: {
                    page: "CountInvoice",
                    rcno: "" + $("#vRCNO").val(),
                    cono: cono,
                    divi: divi
                },
                async: false
            }).done(function (response) {
                $.each(response, function (i, obj) {
                    count = obj.invoiceCount;
                });
            });
            return count;
        }


        function CheckCreateReceipt() {
            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            var count;
            $.ajax({
                type: 'GET',
                url: './Sync',
                dataType: 'json',
                data: {
                    page: "CheckCreateReceipt",
                    RCNO: "" + $("#vRCNO").val(),
                    CONO: cono,
                    DIVI: divi
                },
                async: false
            }).done(function (response) {
                $.each(response, function (i, obj) {
                    count = obj.RCNO;
                });
            });
            return count;
        }


        function CheckCountReceiver() {
            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            var check;
            $.ajax({
                type: 'GET',
                url: './Sync',
                dataType: 'json',
                data: {
                    page: "CheckCountReceiver",
                    cono: cono,
                    divi: divi,
                    rcno: "" + $("#vRCNO").val()
                },
                async: false
            }).done(function (response) {
                $.each(response, function (i, obj) {
                    check = obj.check;
                });
            });
            return check;
        }


        function CheckRunningPrint() {
            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            var username = "<%out.print(session.getAttribute("username"));%>";
            var locations = "<%out.print(session.getAttribute("locations"));%>";
            var NoRunning;
            $.ajax({
                type: 'GET',
                url: './Sync',
                dataType: 'json',
                data: {
                    page: "CheckRunningPrint",
                    cono: cono,
                    divi: divi,
                    username: username,
                    locations: locations
                },
                async: false
            }).done(function (response) {
                $.each(response, function (i, obj) {
                    console.log(obj.RR_COUNT);
                    NoRunning = obj.RR_COUNT;
                });
            });
            return NoRunning;
        }
    </script>
</html>