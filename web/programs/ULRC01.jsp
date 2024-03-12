<%-- 
    Document   : ULRC01
    Created on : Apr 4, 2023, 2:06:54 PM
    Author     : Phongsathon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body class="container">
        <div class="container-fluid ">
            <div class="row shadow">


                <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">
                    <label for="type" >INVOICE No.</label>
                    <input type="text" class="form-control text-center "  id="invno" name="invno" >
                </div>
                <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">
                    <label for="type" >SECURE CODE</label>
                    <input type="text" class="form-control text-center " disabled="true"  id="seccode" name="seccode" >
                </div>
                <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">
                    <label for="type"><br></label>
                    <button  type="button" name="searchinv"  id="searchinv"  class="form-control btn-primary" value="searchinv" style="width: 157px;">INV. STATUS</button> 
                    <br>
                    <button  type="button" name="unlockinv"  id="unlockinv"  class="form-control btn-success" value="unlockinv" style="width: 157px; visibility: hidden">UNLOCK INVOICE</button> 
                </div>
                <div class="col-xs-2 col-sm-3 col-md-3 col-lg-2">

                    <H2 id="ststxtLOCK" style=" width: 100%; background-color: red;color: white; visibility: hidden">INVOICE LOCK.</H2>
                    <H2 id="ststxtUNLOCK" style=" width: 100%; background-color: green;color: white;visibility: hidden">INVOICE OK.</H2>

                </div>
            </div>
            <hr>
            <div class="row">
                <div id="grid" class="shadow"></div>

            </div>


        </div>
    </div>
</body>
</html>

<script>

    var invsts = "0";
    $("#searchinv").click(function (e) {
        var cono = <%out.print(session.getAttribute("cono"));%>;
        var divi = <%out.print(session.getAttribute("divi"));%>;
        var invno = document.getElementById("invno").value;

//    alert(invno);
        if (invno != "" && invno.length > 4) {
            $.ajax({
                type: 'GET',
                url: './Sync',
                dataType: 'text',
                data: {
                    page: "CheckINVSTS",
                    cono: cono,
                    divi: divi,
                    invno: invno



                },
                async: false
            }).done(function (response) {
                invsts = response;
                showbutton(response);
            });
        }else{
            alert("Please input valid invoice");
        }
    });
    function showbutton(response) {

        var locktxt = document.getElementById("ststxtLOCK");
        var unlocktxt = document.getElementById("ststxtUNLOCK");
        var unlockbtn = document.getElementById("unlockinv");
        if (response === '9')
        {
            locktxt.style.visibility = 'visible';
            unlocktxt.style.visibility = 'hidden';
            unlockbtn.style.visibility = 'visible';
        } else {
            locktxt.style.visibility = 'hidden';
            unlocktxt.style.visibility = 'visible';
            unlockbtn.style.visibility = 'hidden';
        }

    }


    $("#unlockinv").click(function (e) {
        if (confirm("Are you sure to Delete? ") === true) {
            var cono = <%out.print(session.getAttribute("cono"));%>;
            var divi = <%out.print(session.getAttribute("divi"));%>;
            var invno = document.getElementById("invno").value;
            var locktxt = document.getElementById("ststxtLOCK");
            var unlocktxt = document.getElementById("ststxtUNLOCK");
            var unlockbtn = document.getElementById("unlockinv");
            var seccode = document.getElementById("seccode").value;


            var baseseccode = split(invno);

//            if (seccode === baseseccode && seccode != "" && baseseccode != "") {

                $.ajax({
                    type: 'GET',
                    url: './Sync',
                    data: {
                        page: "UNLOCKINVOICE",
                        cono: cono,
                        divi: divi,
                        invno: invno

                    },
                    async: false
                }).done(function () {
                    alert("UNLOCKED");
                    locktxt.style.visibility = 'hidden';
                    unlocktxt.style.visibility = 'visible';
                    unlockbtn.style.visibility = 'hidden';
                });


//            } else {
//                alert("SECURE CODE INVALLID");
//            }
        } else {
        }

    });

    function split(str) {

        const date = new Date();
        var DAY = date.getDate();
        var result = [];
        var dictionary = ["O", "I", "O", "I", "O", "I", "O", "I", "O", "I"];
        var j = 0;
        var k = 0;
        for (i = 0; i <= str.length; i++) {

            k += 1;
            result[j] = str.substring(i, k);
            j++;
        }

        let iw = 0;
        seccode = "";

        while (iw < result.length - 1) {

            seccode += dictionary[result[iw]]
            iw++;
        }


        return DAY + seccode;
    }





</script>
