<%-- 
    Document   : index
    Created on : Jun 14, 2019, 9:32:52 AM
    Author     : Wattana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <%
        if (session.getAttribute("cono") == null) {
            response.sendRedirect("login.jsp");
        }
        String username = (String) session.getAttribute("username");
        String locations = (String) session.getAttribute("locations");
    %>



    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="assets/jQuery-3.3.1/jquery-ui.min.css">
        <link rel="stylesheet" type="text/css" href="assets/Bootstrap-3.3.7/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="assets/jsgrid-1.5.3/dist/jsgrid.min.css" />
        <link rel="stylesheet" type="text/css" href="assets/jsgrid-1.5.3/dist/jsgrid-theme.min.css">
        <script type="text/javascript" src="assets/jQuery-3.3.1/jquery-3.3.1.min.js"></script>
        <script type="text/javascript" src="assets/Bootstrap-3.3.7/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="assets/jQuery-3.3.1/jquery-ui.min.js"></script>
        <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
        <script type="text/javascript" src="assets/jsgrid-1.5.3/dist/jsgrid.min.js"></script>
        <script src="assets/jsgrid-1.5.3/src/jsgrid.core.js"></script>
        <script src="assets/jsgrid-1.5.3/src/jsgrid.validation.js"></script>
        <script src="assets/jsgrid-1.5.3/src/jsgrid.load-indicator.js"></script>
        <script src="assets/jsgrid-1.5.3/src/jsgrid.load-strategies.js"></script>
        <script src="assets/jsgrid-1.5.3/src/jsgrid.sort-strategies.js"></script>
        <script src="assets/jsgrid-1.5.3/src/jsgrid.field.js"></script>
        <script src="assets/jsgrid-1.5.3/src/fields/jsgrid.field.text.js"></script>
        <script src="assets/jsgrid-1.5.3/src/fields/jsgrid.field.number.js"></script>
        <script src="assets/jsgrid-1.5.3/src/fields/jsgrid.field.select.js"></script>
        <script src="assets/jsgrid-1.5.3/src/fields/jsgrid.field.checkbox.js"></script>
        <script src="assets/jsgrid-1.5.3/src/fields/jsgrid.field.control.js"></script>
        <link rel="icon" type="image/icon" href="images/duck.jpg" />
        <script src='assets/select2/js/select2.min.js' type='text/javascript'></script>
        <link href='assets/select2/css/select2.min.css' rel='stylesheet' type='text/css'>

    </head>
    <body style="background-color: whitesmoke;">
        <div>
            <nav class="navbar navbar-default navigation-clean" style="background-color: #cccccc" >
                <div class="container">
                    <div class="navbar-header"><a class="navbar-brand" href="./" style="color: black;font-weight: bold">Programs Receipt :   <font color="#0E5EDC" style="font-size: 16px" ><%=username%> : <%=locations%></font></a><button data-toggle="collapse" class="navbar-toggle collapsed" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button></div>
                    <div class="collapse navbar-collapse" id="navcol-1">
                        <ul class="nav navbar-nav navbar-right">
                            <li role="presentation"></li>
                            <!--                            <li class="dropdown" ><a class="dropdown-toggle" aria-expanded="false" href="?page=Main" style="color: black">Receipt</a></li>
                                                        <li class="dropdown" ><a class="dropdown-toggle" aria-expanded="false" href="?page=RC02" style="color: black">Cash Check Key</a></li>-->

                            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false" href="./"style="color: black">Programs&nbsp;<span class="caret"></span></a>
                                <ul class="dropdown-menu" role="menu">
                                    <!--<li role="presentation"><a class='dropdown-item'  href="?page=Main">Receipt : NORMAL</a></li>-->
                                    <!--<li role="presentation"><a class='dropdown-item'  href="?page=RC02">Receipt : Cash Check Key</a></li>-->
                                    <!--<li role="presentation"><a class='dropdown-item'  href="?page=RC03">Receipt : Expense</a></li>-->    
                                    <!--<li role="presentation"><a class='dropdown-item'  href="?page=RC04">Receipt : Clear Expense</a></li>-->   
                                    <!--<li role="presentation"><a class='dropdown-item'  href="?page=RC05">Receipt : Customer By Invoice</a></li>-->    
                                    <!--<li role="presentation"><a class='dropdown-item'  href="?page=RC06">Receipt : เงินจอง</a></li>-->    
                                    <!--<li role="presentation"><a class='dropdown-item'  href="?page=RC08">Receipt : Cancel Receipt</a></li>-->
                                    <!--<li role="presentation"><a class='dropdown-item'  href="?page=RC09">Receipt : BANK MAPPING</a></li>-->
                                    <li role="presentation"><a  id="normalzone"  class='dropdown-item'  href="?page=new_Main">Receipt : NEW NORMAL</a></li>
                                    <li role="presentation"><a  id="cashcheckkeyzone"  class='dropdown-item'  href="?page=newRC02">Receipt : NEW Cash Check Key</a></li>
                                    <li role="presentation"><a  id="expendzone"  class='dropdown-item'  href="?page=newRC03">Receipt : NEW EXPENSE</a></li>
                                    <li role="presentation"><a  id="clearexpendzone"  class='dropdown-item'  href="?page=newRC04">Receipt : NEW CLEAR EXPENSE</a></li>
                                                                        <li role="presentation"><a id="depoditzone"   class='dropdown-item'  href="?page=newRC06">Receipt : NEW DEPOSIT</a></li>
                                    <li role="presentation"><a class='dropdown-item'  href="?page=RC07">Receipt : Set Series Receipt</a></li> 
                                    <li role="presentation"><a class='dropdown-item'  href="?page=newRC09">Receipt : NEW BANK MAPPING</a></li>
                                    <li role="presentation"><a id="bankmappingexpensezone"   class='dropdown-item'  href="?page=newRC10">Receipt : NEW BANK MAPPING EXP</a></li>
                                    <li role="presentation"><a id="ิbankmappingdepositzone"   class='dropdown-item'  href="?page=newRC11">Receipt : NEW BANK MAPPING DEPOSIT</a></li>

                                </ul>
                            </li>

                            <li class="dropdown" ><a class="dropdown-toggle" aria-expanded="false" href="?page=Rollback" style="color: black">Rollback</a></li>
                            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false" href="./" style="color: black">Report&nbsp;<span class="caret"></span></a>
                                <ul class="dropdown-menu" role="menu">
                                    <li role="presentation"><a class='dropdown-item'  href="?page=RP01">R01 : By Receipt/Voucher.</a></li>
                                    <li role="presentation"><a class='dropdown-item'  href="?page=RP02">R02 : By Date</a></li>
                                    <li role="presentation"><a class='dropdown-item'  href="?page=RP03">R03 : L/S Receipt</a></li>
                                    <li role="presentation"><a class='dropdown-item'  href="?page=RP04">R04 : L/S Summary</a></li>
                                    <li role="presentation"><a class='dropdown-item'  href="?page=RP05">R05 : Daily Cheque Clearing</a></li>

                                </ul>
                            </li>
                            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false" href="./" style="color: black">MONITORING&nbsp;<span class="caret"></span></a>
                                <ul class="dropdown-menu" role="menu">
                                    <li role="presentation"><a class='dropdown-item'  href="?page=MONITORING"> ID MONITOR </a></li>
                                    <li role="presentation"><a class='dropdown-item'  href="?page=CCMONITORING"> รายการที่ยกเลิก  </a></li>

                                </ul>
                            </li>
                            
                            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false" href="./" style="color: black">Utilities&nbsp;<span class="caret"></span></a>
                                <ul class="dropdown-menu" role="menu">
                                    <li role="presentation"><a class='dropdown-item'  href="?page=ULRC01">UNLOCK Invoice</a></li>
                                    <li role="presentation"><a class='dropdown-item'  href="?page=RETURNID">RETURN ID</a></li>
                                    <li role="presentation"><a class='dropdown-item'  href="?page=RETURNIDEXP">RETURN EXPEND</a></li>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                    <form action="Logout" method="POST">
                                        <input class="alert-danger" type="submit" value="Sign Out">
                                    </form>
                                </a>
                            </li>
                        </ul>
                    </div>
                    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" rel='stylesheet' type='text/css'>
                </div>
            </nav>
        </div>
        <section id="programs">
            <div class='centerDiv' id="vSection"></div>
        </section>
    </body>
</html>

<script type="text/javascript">

    <% if (request.getParameter("page") != null) {%>
    $("#vSection").load("./programs/<%=request.getParameter("page").toString()%>.jsp");
    <%  } else {  %>
    $("#vSection").load("./programs/new_Main.jsp");
    <% }%>

    $('.dropdown-item').on('click', function () {
        console.log(this.name);
        $("#vSection").load("./programs/" + this.name + ".jsp");
    });
    
    
    
    
     $(document).ready(function () {

        var username = "<%out.print(session.getAttribute("username"));%>";
        var cono = "<%out.print(session.getAttribute("cono"));%>";
        var normal = document.getElementById("normalzone");
        var cashcheckkey = document.getElementById("cashcheckkeyzone");
        var expense = document.getElementById("expendzone");
        var clearexpense = document.getElementById("clearexpendzone");
        var deposit = document.getElementById("depoditzone");
        var bankmappingexp = document.getElementById("bankmappingexpensezone");
        var bankmappingdeposit = document.getElementById("ิbankmappingdepositzone");


 if (cono === "600")
        {
            

            normal.style.visibility = "Hidden";
            cashcheckkey.style.visibility = "Hidden";
            expense.style.visibility = "Hidden";
            clearexpense.style.visibility = "Hidden";
            deposit.style.visibility = "Hidden";
            bankmappingexp.style.visibility = "Hidden";
             bankmappingdeposit.style.visibility = "Hidden";

        } else if (cono === "500")
        {

               normal.style.visibility = "Hidden";
            cashcheckkey.style.visibility = "Hidden";
            expense.style.visibility = "Hidden";
            clearexpense.style.visibility = "Hidden";
            deposit.style.visibility = "Hidden";
            bankmappingexp.style.visibility = "Hidden";
             bankmappingdeposit.style.visibility = "Hidden";
        }




    });

</script>