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
        <title>Receipt : Cash Check Key</title>

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
                        <div class="panel-heading" style="background-color: #3366ff">
                            <h3 class="panel-title"> <font color="#FFFFFF" >Cash Check Key  : <%=namecom%></font></h3>
                        </div>
                        <div class="container panel-body col-xs-12 col-sm-12 col-md-12 col-lg-12 ">
                            <div class="row ">
                                <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                    <label>Customer Code :
                                        <input type="text" class="form-control text-center" value="" id="vPAYER" name="vPAYER" onblur="SearchPY(this.value);" maxlength="10" readonly="true">
                                        <!--TH28050014-->
                                    </label>
                                </div>
                                <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                    <label>Customer Name :
                                        <input type="text" class="form-control text-center" value="" id="vPAYERNAME" name="vPAYERNAME" readonly="true" >
                                    </label>
                                </div>
                                <div class="col-xs-4 col-sm-4 col-md-3 col-lg-2">
                                    <label>Amount Receive :
                                        <input type="text" class="form-control text-center" value="" id="vREAMT" name="vREAMT" onblur="formatNumber(this.value, this.id)" >
                                    </label>
                                </div>
                                <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                    <label>Transaction Date :
                                        <input type="date" class="form-control" id="vTRDT" name="vTRDT">
                                    </label>
                                </div>
                                <br>
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
                                    <label style="width: 100%">Bank Transfer :
                                        <select class="form-control form-control-user" name="vBKTF" id="vBKTF"  >
                                            <option value="" selected="selected">Select Bank</option>
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
                            <div class="row" >
                                <form  method="POST" enctype="multipart/form-data" acceptcharset="UTF-8" id="MyForm" >
                                    <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">
                                        <label>Running Number :
                                            <input type="text" class="form-control text-center"  value="" id="vRCNO" name="vRCNO" maxlength="8">
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

                                    </div>
                                    <br>
                                    <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
                                        <button class="btn btn-info" id="vSearch" name="vSearch" type="button">Search Receipt</button>
                                    </div>
                                    <button class="btn btn-danger" id="vComplete" name="vComplete" type="button" style="position: absolute;right: 5px;">POST M3</button>
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
                            <br>
                            <div class="row" >
                                <div id="GridReceipt" ></div>   
                            </div>
                        </div>
                    </div>
                </div> 
            </div> 
            <!--<button type="button" id="few" onclick="CheckInvoice()">few</button>-->
        </div>

    </body>

    <script type="text/javascript">
        $("#vUpdate").hide();
        $("#vRefresh").hide();

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


        $("#vRefresh").click(function (e) {
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
                $('#vBKTF').empty().append('<option selected="selected" value="">Select bank</option>');

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




        $(document).ready(function () {
            $("#FormDetail").hide();
            document.getElementById("vComplete").style.display = "none";

            $("#GridReceipt").hide();

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
                    page: "SearchReceipt_Check",
                    CONO: cono,
                    DIVI: divi,
                    RCNO: "" + $("#vRCNO").val(),
                    locations: locations
                },
                async: false
            }).done(function (response) {
                console.log("response");
                $('#MyForm').trigger("reset");
                $.each(response, function (i, obj) {
                    document.getElementById("vRCNO").value = obj.HC_RCNO;
                    document.getElementById("vPAYER").value = obj.HC_PYNO;
                    formatNumber(obj.HC_REAMT, 'vREAMT');
                    document.getElementById("vReceiver").value = obj.RR_DESC;
                    document.getElementById("vTRDT").value = obj.HC_TRDT;
                    document.getElementById("vPAYERNAME").value = obj.OKCUNM;
                    console.log(obj.HC_TYPE);
                    if (obj.HC_TYPE === "TRANSFER") {
                        document.getElementById("transfer").checked = true;
                        radiobtn();
                        document.getElementById("vBKCHG").value = obj.HR_BKCHG;
                        document.getElementById("vDTTF").value = obj.SHC_PYDT;
                    } else if (obj.HC_TYPE === "CHECK") {
                        document.getElementById("check").checked = true;
                        radiobtn();
                        document.getElementById("vCHKNO").value = obj.HC_CHKNO;
                        document.getElementById("vDTPM").value = obj.SHC_PYDT;
                        document.getElementById("vCHCHG").value = obj.HR_BKCHG;
                    }
                    $('#vTRDT').prop('readonly', true);
                    $('#vRCNO').prop('readonly', true);
//                    $('#vREAMT').prop('readonly', true);
                    $("#vRefresh").show();
                    $("#vUpdate").show();
                    Call_GridReceipt();
                    Cal_LineAmount();
                });

                document.getElementById("vComplete").style.display = "";
                if (document.getElementById("vRCNO").value === "") {
                    alert("ไม่สามารถเรียกใบเสร็จนี้ได้ โปรดทำการตรวจสอบข้อมูล !!");
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
            if (radioValue === 'CHECK') {
                HC_PYDT = $("#vDTPM").val();
                HC_BANK = $("#vBKCH").val();
                HC_CHKNO = $("#vCHKNO").val();
                if ($("#vDTPM").val() === "" || HC_BANK === "") {
                    alert("Please check data !");
                    return;
//                    HC_PYDT = "0";
                }
                HR_BKCHG = $("#vCHCHG").val();
            } else if (radioValue === 'TRANSFER') {
                HC_PYDT = $("#vDTTF").val();
                var input = $("#vBKTF").val();
                var fields = input.split(',');
                HC_ACCODE = fields[0];
                HC_BANK = fields[1];
                if ($("#vDTTF").val() === "" || $("#vBKTF").val() === "") {
                    alert("Please check data !");
                    return;
                }
                HR_BKCHG = $("#vBKCHG").val();
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
//            location.reload(0);
        });


        $("#vComplete").click(function (e) {
            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            var form = $('#MyForm')[0];
            // Create an FormData object 
            var data = new FormData(form);

            data.append("REPRINT", "POSTM3");
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

            if (CountInvoice() === "0") {
                alert("Can't print because the receipt doesn't have a invoice !!");
                return;
            }

            if (CheckInvoice() === 0) {
//                alert("Print");
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
//                                alert(obj.Voucher);
                                alert(obj.Voucher + " \n" + "Fix Receipt Number : " + obj.Fix_Running);
                                location.reload(0);
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
//                    console.log(msg);
                    console.log(count);

                });
            });

            if (count > 0) {
                alert(msg + " is lock. Please unlock this invoice.");
            }

            return count;
        }


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
                    console.log(check);
                });
            });
//            if (check === "0") {
//                alert("Please check data !");
//            }

            return check;
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

    </script>
</html>