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
        <title>Receipt : Rollback</title>
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
                            <h3 class="panel-title"> <font color="#FFFFFF" >Receipt : Rollback</font></h3>
                        </div>
                        <div class="container panel-body col-xs-12 col-sm-12 col-md-12 col-lg-12 ">
                            <form  method="POST" enctype="multipart/form-data" acceptcharset="UTF-8" id="MyForm" >
                                <div class="row">
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                        <div class="center">
                                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                <label>Receipt No. : </label>
                                                <input type="text" name="vRCNO" id="vRCNO"  style="width: 15%;text-align: center" value="24000000" maxlength="8" min="8"> 
                                            </div>
                                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                <label>ID : </label>
                                                <input type="text" name="vFNNO" id="vFNNO"  style="width: 15%;text-align: center" value="67000000" maxlength="8" min="8"> 
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <div class="row">


                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                        <div class="center">
                                            <input type="checkbox" id="vCHECKBL" name="vCHECKBL" value="TRUE" >
                                            <label for="vehicle1">OVERPAY</label><br>
                                        </div>
                                    </div>
                                    <br> 
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                        <div class="center">
                                            <input type="checkbox" id="vPATIAL" name="vPATIAL" value="TRUE" >
                                            <label for="vehicle1">PARTIAL</label><br>
                                        </div>
                                    </div>
                                    <br> 
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                        <div class="center">
                                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                <label style="width: 100%">Select Printer :
                                                    <select  name="vPrinter" id="vPrinter">
                                                        <option value="RECEIPT PRINTER">PRINTER 1</option>
                                                        <option value="RECEIPT PRINTER2">PRINTER 2</option>
                                                    </select>
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-4 col-sm-3 col-md-3 col-lg-2">

                                </div>                                
                                <br>
                                <div class="row">
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                        <div class="center">
                                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                <!--<button class="btn btn-success " style="width: 25%" id="REPRINT" name="REPRINT" type="button">REPRINT</button>-->
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <div class="row">
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                        <div class="center">
                                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                <button class="btn btn-success " style="width: 25%" id="REPRINT2" name="REPRINT2" type="button">REPRINT</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <div class="row">
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                        <div class="center">
                                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                <!--<button class="btn btn-info " style="width: 25%" id="vRollback" name="vRollback" type="button">Roll Back Receipt</button>-->
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                        <div class="center">
                                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                <h2> เหตุผลที่ยกเลิก</h2>
                                                <textarea id="vreason" name="vreason" rows="4" cols="50">
                                                </textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <br>

                                <div class="row">
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                        <div class="center">
                                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                                <button class="btn btn-danger " style="width: 25%" id="vCancel" name="vCancel" type="button">Cancel Receipt</button>
                                            </div>
                                        </div>
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
        $(document).ready(function () {
            var auth = "<%out.print(session.getAttribute("auth"));%>";
            if (auth !== "ROLL") {
                alert("Your not auth this page");
                window.location.href = "./";
                return;
            }
        });


        $("#REPRINT").click(function (e) {
            var form = $('#MyForm')[0];
            // Create an FormData object 
            var data = new FormData(form);
            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            data.append("REPRINT", "REPRINT");
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
//                    alert("สำเร็จ");
//                    location.reload(0);
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
                            location.reload(0);
                        });
                    });
                },
                error: function (e) {
                    alert("ล้มเหลว");
                }
            });

        });


        $("#REPRINT2").click(function (e) {
            var form = $('#MyForm')[0];
            // Create an FormData object 
            var data = new FormData(form);
            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            data.append("REPRINT", "REPRINT2");
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
//                    alert("สำเร็จ");
//                    location.reload(0);
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
                            location.reload(0);
                        });
                    });
                },
                error: function (e) {
                    alert("ล้มเหลว");
                }
            });

        });

        $("#vRollback").click(function (e) {
            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            var RCNO = $("#vRCNO").val();
            if (RCNO !== "") {
                $.ajax({
                    url: './Sync',
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        page: "Rollback",
                        RCNO: RCNO,
                        STS: "1",
                        TYPE: "Rollback",
                        cono: cono,
                        divi: divi
                    },
                    async: false
                }).done(function (response) {
                    $.each(response, function (i, obj) {
                        var msg = obj.HC_STS;
                        alert(msg);
                        location.reload(0);
                    });
                });
            } else {
                alert("Plase check data!!");
            }
        });


        $("#vCancel").click(function (e) {



            $.ajax({
                url: './Sync',
                type: 'POST',
                dataType: 'json',
                data: {
                    page: "Rollback",
                    RCNO: RCNO,
                    STS: "1",
                    TYPE: "Rollback",
                    cono: cono,
                    divi: divi
                },
                async: false
            }).done(function (response) {
                $.each(response, function (i, obj) {
                    var msg = obj.HC_STS;
                    alert(msg);
                    location.reload(0);
                });
            });



            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            var ccby = "<%out.print(session.getAttribute("username"));%>"
            var ccrnno = document.getElementById("vFNNO").value;
            var ccreason = document.getElementById("vreason").value;

            var msg;
            var RCNO = $("#vRCNO").val();
            var form = $('#MyForm')[0];
            // Create an FormData object 
            var data = new FormData(form);
            data.append("CONDITION", "CANCEL");
            var answer = window.confirm("Are you sure  want to cancel this receipt : " + RCNO + " ?");
            if (answer === true) {
                if (RCNO !== "" && RCNO.length === 8) {

                    $.ajax({
                        url: './Sync',
                        type: 'POST',
                        data: {
                            page: "UPDATECCLIST",
                            CCRCNO: RCNO,
                            CCBY: ccby,
                            CCCONO: cono,
                            CCDIVI: divi,
                            CCRNNO: ccrnno,
                            CCREASON: ccreason
                        },
                        async: false
                    }).done(function (response) {

                        console.log("UPDATED CCLIST COMPLETE.");
                    });
                    
                    
                    
            var cono = <%out.print(session.getAttribute("cono"));%>
            var divi = <%out.print(session.getAttribute("divi"));%>
            var ID = document.getElementById("vFNNO").value
            var username =  "<%out.print(session.getAttribute("username"));%>"
            
            

           alert(cono + divi + ID + username )

            $.ajax({
                type: "GET",
                url: "./Sync",
                async: false,
                data: {
                    page: "CancelIDBM",
                    cono: cono,
                    divi: divi,
                    id:  ID,
                    username: username,
                 
                },
                success: function (result) {

                    //alert("CANCELED");
                }
            });


// todo

                    $.ajax({
                        type: "POST",
                        enctype: 'multipart/form-data',
                        url: "./GLS200",
                        data: data,
                        processData: false,
                        contentType: false,
                        cache: false,
                        async: false,
                        success: function (data) {
                            $.ajax({
                                url: './Sync',
                                type: 'POST',
                                dataType: 'json',
                                data: {
                                    page: "Rollback",
                                    RCNO: RCNO,
                                    STS: "9",
                                    TYPE: "Cancel",
                                    cono: cono,
                                    divi: divi
                                },
                                async: false
                            }).done(function (response) {
                                $.each(response, function (i, obj) {
                                    msg = obj.HC_STS;
                                    alert(msg);
                                });
                            });
                        },
                        error: function (e) {
                            alert("ล้มเหลว");
                        }
                    });
                    location.reload(0);

// todo

                } else {
                    alert("Plase check data!!");
                }
            }

        });
    </script>

</html>
