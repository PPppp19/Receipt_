<%-- 
    Document   : index
    Created on : Jun 13, 2019, 4:29:51 PM
    Author     : Wattana
--%>

<%@page import="com.br.api.data.Utility"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        String msg2 = request.getParameter("msg2");
        session.setAttribute("msg2", msg2);
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="assets/Bootstrap-3.3.7/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="fonts/iconic/css/material-design-iconic-font.min.css">
        <link rel="stylesheet" type="text/css" href="assets/animate/animate.css">
        <link rel="stylesheet" type="text/css" href="assets/css-hamburgers/hamburgers.min.css">
        <link rel="stylesheet" type="text/css" href="assets/animsition/css/animsition.min.css">
        <!--<link rel="stylesheet" type="text/css" href="assets/select2/select2.min.css">-->
        <link rel="stylesheet" type="text/css" href="assets/util.css">
        <link rel="stylesheet" type="text/css" href="assets/main.css">
        <script type="text/javascript" src="assets/jQuery-3.3.1/jquery-3.3.1.min.js"></script>
        <script type="text/javascript" src="assets/Bootstrap-3.3.7/js/bootstrap.min.js"></script>
        <script src="assets/animsition/js/animsition.min.js"></script>
        <script src="assets/Bootstrap-3.3.7/js/popper.js"></script>
        <script src="assets/Bootstrap-3.3.7/js/bootstrap.min.js"></script>
        <!--<script src="assets/select2/select2.min.js"></script>-->
        <script src="assets/countdowntime/countdowntime.js"></script>
        <script src="assets/main.js"></script>
        <title>Receipt Login</title>
        <link rel="icon" type="image/icon" href="images/duck.jpg" />
    </head>

    <body>

        <div class="container-login100" style="background-color: whitesmoke">
            <div class="wrap-login100 p-l-55 p-r-55 p-t-80 p-b-30">
                <form class="login100-form validate-form" action="Login" method="POST">
                    <span class="login100-form-title p-b-20" style="font-size: 20px;font-weight: bold;">
                        Program Receipt Login
                    </span>
                    <div class="m-b-20">
                        <img src="images/duck.jpg" class="center-block" width="180" height="180" alt="duck"/>
                    </div>
                    <div class="wrap-input100 validate-input m-b-20" data-validate="Enter username">
                        <input class="input100" type="text" name="username"  id="vUsername" placeholder="Username">
                        <span class="focus-input100"></span>
                    </div>

                    <div class="wrap-input100 validate-input m-b-25" data-validate = "Enter password">
                        <input class="input100" type="password" name="password" id="vPassword" placeholder="Password">
                        <span class="focus-input100"></span>
                    </div>
                    

                    <div class="wrap-input100 validate-input m-b-25">
                        <select class="form-control form-control-user" name="company" id="vCompany" onchange="Location(this.value)">
                            <option value="" selected="selected">Select Company</option>
                        </select>
                    </div>
                    <div class="wrap-input100 validate-input m-b-25">
                        <select class="form-control form-control-user" name="location" id="location">
                        </select>
                    </div>
                    
                    
                              <div class="container-login100-form-btn">
                        <button class="login100-form-btn" disabled="true" id="Login">
                            Sign In
                        </button>
                    </div>
                    
                    <br><!-- comment -->
                    
                    <p style="color: red">${msg}</p>
                    <div class="row container col-md-12" style="font-size: 13px; width: 280px;">
                        <div style="width: 140px;">
                            <a href="http://192.200.9.94:8080/UserRequest/?Destination=ChangePW" target="_blank"><li style="color: blue;"><u>Change Password</u></li></a>  
                        </div>  
                    </div>
                    <div class="row container col-md-12" style="font-size: 13px; width: 280px; padding-bottom: 15px;">
                        <div style="width: 140px;">
                            <!--<lable>Change Password</lable>-->       
                            <a href="http://192.200.9.94:8080/UserRequest/?Destination=RequestUser" target="_blank"><li style="color: blue;"><u>Sign-Up</u></li></a>  
                        </div>  
                    </div><br><br><br>
                    
            
                        <div class="form-group">
                            <div style="width: 140px;">
                                <a href="#" onclick="window.open('http://192.200.9.106:8080/RECEIPTnew2/ReceiptNew2_MANUAL.pdf', '_blank', 'fullscreen=yes'); return true;" ><li style="color: blue;"><u>คู่มือการใช้งานโปรแกรม_Receipt_PROGRAM_MANUAL</u></li></a>        

                            </div>  
                        </div>
                           <div class="form-group">
                            <div style="width: 140px;">
                                <a href="#" onclick="window.open('http://192.200.9.106:8080/RECEIPTnew2/BANK_MAPPING_MANUAL.pdf', '_blank', 'fullscreen=yes'); return true;" ><li style="color: blue;"><u>คู่มือการใช้งานโปรแกรม_Receipt_FM(เงินโอน)</u></li></a>        

                            </div>  
                        </div>
                           <div class="form-group">
                            <div style="width: 140px;">
                                <a href="#" onclick="window.open('http://192.200.9.106:8080/RECEIPTnew2/BANK_MAPPING_EXP_MANUAL.pdf', '_blank', 'fullscreen=yes'); return true;" ><li style="color: blue;"><u>คู่มือการใช้งานโปรแกรม_Receipt_FM(เงินโอน expense)</u></li></a>        

                            </div>  
                        </div>
                  

                </form>
            </div>

            <button hidden="true" onclick="window.open('http://192.200.9.94:8080/UserRequest/?Destination=ChangePW')" id="go">Continue</button>
        </div>
    </body>
</html>

<script>

    $(document).ready(function () {
    <% System.out.println("Path : " + request.getContextPath());%>

        $.ajax({
            url: "./Sync",
            type: "GET",
            dataType: "json",
            data: {
                page: "Company"
            },
            success: function (getdata) {
                $.each(getdata.data, function (i, obj) {
                    var div_data = "<option value=" + obj.COMPANY + ">" + obj.CCCONO + " : " + obj.CCDIVI + " : " + obj.CCCONM + "</option>";
                    //console.log(div_data)
                    $(div_data).appendTo('#vCompany');
                });
            }
        });




        var msg2 = $("#ChangePW").val();
        if (msg2 === "ChangePW") {
            $("#go").click();
        }


        if (navigator.userAgent.indexOf("AppleWebKit") !== -1)
        {
            document.getElementById("Login").disabled = false;
        } else {
            $("#Login").css('background-color', '#949691');
            alert("Programs not support in Internet Explorer. \nPlease open in Google Chrome.");
        }


    });


    $('#vUsername').on('input', function (evt) {
        $(this).val(function (_, val) {
            return val.toUpperCase();
        });
    });

    function Location(val) {
        let text = val;
        const myArray = text.split(":");
        let cono = myArray[0];
        let divi = myArray[1];
        $("#location").empty();
        $.ajax({
            url: "./Sync",
            type: "GET",
            dataType: "json",
            data: {
                page: "Location",
                cono: cono,
                divi: divi
            },
            success: function (getdata) {
                $.each(getdata.data, function (i, obj) {
                    var div_data = "<option value=" + obj.RL_LCCODE + ">" + obj.RL_LCDESC + "</option>";
                    //console.log(div_data)
                    $(div_data).appendTo('#location');
                });
            }
        });
    }



</script>
