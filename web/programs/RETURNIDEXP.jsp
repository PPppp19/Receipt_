<%-- 
    Document   : RETURNBM
    Created on : Sep 12, 2023, 1:25:24 PM
    Author     : Phongsathon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

    <style>

        .center {
            margin: auto;
            width: 50%;
            padding: 10px;
        }


    </style>
    <body>


        <div class="center">


            <div class="center">
                <h3 for="ID"> EXP ID :</h3>
                <input type="text" class="form-control text-center"  id="vID" name="vID" maxlength="8" >
            </div>
            <div class="center">
                <button class="btn btn-danger " id="vRETURN" name="vRETURN" type="button">RETURN EXP</button>
            </div>
        </div>
    </body>
</html>

<script>



    $("#vRETURN").click(function (e) {


        if (checksts()) {
            if (confirm("Confirm to Return ID: '" + $("#vID").val() + "'") === true) {
                var cono = <%out.print(session.getAttribute("cono"));%>
                var divi = <%out.print(session.getAttribute("divi"));%>
                $.ajax({
                    url: './Sync',
                    type: 'GET',
                    data: {
                        page: "RETURNEXP",
                        CONO: cono,
                        DIVI: divi,
                        ID: $("#vID").val()

                    },
                    async: false
                }).done(function (response) {

                    alert("RETURNED");
                });

            } else {

            }
        } else {

            alert("ID status ไม่ใช่ EXP");
            return;
        }

    });

    function checksts() {

        var chksts = false;
        var cono = <%out.print(session.getAttribute("cono"));%>
        var divi = <%out.print(session.getAttribute("divi"));%>
        $.ajax({
            url: './Sync',
            type: 'GET',
            dataType: 'json',
            data: {
                page: "CHECKEXP",
                CONO: cono,
                DIVI: divi,
                ID: $("#vID").val()

            },
            async: false
        }).done(function (response) {

            chksts = response;
        });

        return chksts;

    }


</script> 
