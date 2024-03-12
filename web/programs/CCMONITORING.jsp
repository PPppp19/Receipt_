<%-- 
    Document   : history
    Created on : Nov 7, 2022, 3:32:21 PM
    Author     : Phongsathon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <body style="background-color:powderblue;">
        <style>




            .center {
                position: absolute;
                left: 50%;
                top: 50%;
                transform: translate(-50%, -50%);
            }


            .header {
                padding: 60px;
                text-align: center;
                background: #19bfde;
                color: white;
                font-size: 30px;
                height: 180px;
                background-image: linear-gradient(#CB1B45, #CB1B45);


            }

            .jsgrid-header-row>.jsgrid-header-cell {

                background-image: linear-gradient(#CB1B45, #CB1B45);

                font-size: 1.2em;
                color: white;
                font-weight: normal;
                align-content: center;
                text-align: center;
                align-self: center;
            }

            .jsgrid-grid-body{

                color: #161637;
                text-align: center;
            }


            .modal {

            }


        </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>



    <div  id="jsGrid"></div>

    <!--        <div   id="jsGrid_item"  style=" height: 50px"></div>   -->


    <div id="frm_sublot" class="modal" style="position: fixed;
         left: 0;
         top: 0;
         width: 100%;
         height: 100%;
         overflow: auto;
         background-color: rgb(0,0,0);
         background-color: rgba(0,0,0,0.4);  "  >
        <div class="modal-content">
            <div class="panel panel-default ">
                <div class="panel-heading" style="background-color: #CB1B45; border-radius: 2.5px;margin-left: -15px;">
                    <span class="close" style=" color: #CB1B45">&times;</span>
                    <!--                          &nbsp;&nbsp;&nbsp;<input type="checkbox" id="isactive" name="isactive" value="isactive" style="display: inline-block; size: 15px;">               -->
                    <label class="panel-title" style="margin-left: 10px; font-size: 22px" > <font id="tabletitle" color="#FFFFFF">Edit Lot Table</font></label>

                </div>

                <div class=" panel-body " >
                    <div class="row">
                        <!--                            <div id="jsGrid_item"></div>-->
                        <div id="grid"></div>
                        <!--                        <div id="sgrid"></div>-->

                    </div>
                </div>

            </div>
        </div>
    </div>


</body>


</html>


<script>

    var clients = [

        {"H_RNNO": "Otto Clay"}
    ];

    var clientsitem = [
        {"HC_FNNO": "Otto Clay"}
    ];


    $(document).ready(function () {

        runGrid();


    });




    $(function () {


        $("#grid").jsGrid({
            width: "100%",
            height: "400px",
            filtering: false,
            editing: false,
            sorting: true,
            paging: true,
            data: clientsitem,
            fields: [

                {title: "ID", name: "HC_FNNO", type: "text"},
                {title: "RCNO", name: "HC_RCNO", type: "text"},
                {title: "DATE", name: "HC_TRDT", type: "text"},
                {title: "PAYER", name: "HC_PYNO", type: "text"},
                {title: "AMOUNT", name: "HC_REAMT", type: "text"},
                {title: "TYPE", name: "HC_TYPE", type: "text"},

                {title: "BANK", name: "HC_BANK", type: "text"},
                {title: "ACCOIDE", name: "HC_ACCODE", type: "text"},
                {title: "PYDATE", name: "HC_PYDT", type: "text"},
                {title: "CHK_NO", name: "HC_CHKNO", type: "text"},
                {title: "USER", name: "HC_USER", type: "text"},
                {title: "VCNO", name: "HC_VCNO", type: "text"},

                {title: "STS", name: "HC_STS", type: "text"},
                {title: "B.CHARGE", name: "HR_BKCHG", type: "text"},
                {title: "LOCATION", name: "HC_LOCATION", type: "text"},
                {title: "FIXNO.", name: "HC_FIXNO", type: "text"}



            ]
        });
    }
    );


    ///////////////



    function runGrid() {
        var cono = "<%out.print(session.getAttribute("cono"));%>";
        var divi = "<%out.print(session.getAttribute("divi"));%>";

        $.ajax({
            type: 'GET',
            dataType: 'json',
            url: './Sync',
            async: false,
            data: {
                page: "CCMONITORING",
                CONO: cono,
                DIVI: divi
            },
            success: function (data) {


                clients = data;
            },
            error: function (e) {
                alert('Error occured');
                console.log(e);
            }
        });
        $("#jsGrid").jsGrid(
                {
                    width: "100%",
                    height: "605",
                    filtering: true,
                    inserting: false,
                    editing: false,
                    sorting: true,
                    paging: true,
                    data: clients,
                    controller: {

                        loadData: function (filter) {
                            var data = $.Deferred();
                            $.ajax({
                                type: 'GET',
                                dataType: 'json',
                                url: './Sync',
                                async: false,
                                data: {
                                    page: "CCMONITORING",
                                    CONO: cono,
                                    DIVI: divi
                                },
                                success: function (data) {


                                    clients = data;
                                },
                                error: function (e) {
                                    alert('Error occured');
                                    console.log(e);
                                }
                            }).done(function (response) {
                                response = $.grep(response, function (item) {

                                    return (!filter.CC_CONO || (item.CC_CONO.indexOf(filter.CC_CONO) > -1))
                                            && (!filter.CC_DIVI || (item.CC_DIVI.indexOf(filter.CC_DIVI) > -1))
                                            && (!filter.CC_RNNO || (item.CC_RNNO.indexOf(filter.CC_RNNO) > -1))
                                            && (!filter.CC_RCNO || (item.CC_RCNO.indexOf(filter.CC_RCNO) > -1))
                                            && (!filter.CC_REASON || (item.CC_REASON.indexOf(filter.CC_REASON) > -1))
                                               && (!filter.CC_DATE || (item.CC_DATE.indexOf(filter.CC_DATE) > -1))
                                                  && (!filter.CC_BY || (item.CC_BY.indexOf(filter.CC_BY) > -1)

                                                    );
                                });
                                data.resolve(response);
                            });
                            return data.promise();
                            /////////////////////

                        }

                    }
                    ,
                    fields: [
                        {title: "CONO", name: "CC_CONO", type: "text", width: 3},
                        {title: "DIVI", name: "CC_DIVI", type: "text", width: 3},
                        {title: "ID", name: "CC_RNNO", type: "text", width: 3},
                        {title: "Receipt NO.", name: "CC_RCNO", type: "text", width: 3},
                        {title: "Cancel Reasons", name: "CC_REASON", type: "text", width: 3},
                        {title: "Cancel Date", name: "CC_DATE", type: "text", width: 3},
                        {title: "Cancel User", name: "CC_BY", type: "text", width: 3 },

                    ]
                }
        );
    }

    var modal = document.getElementById("frm_sublot");
    var span_1 = document.getElementsByClassName("close")[0];
    span_1.onclick = function () {
        modal.style.display = "none";
    };

    /*
     
     
     
     
     
     
     
     
     
     
     
     DateField.prototype = new jsGrid.Field({
     sorter: function (date1, date2) {
     return new Date(date1) - new Date(date2);
     },
     
     itemTemplate: function (value) {
     const today = new Date(value);
     const yyyy = today.getFullYear();
     let mm = today.getMonth() + 1; // Months start at 0!
     let dd = today.getDate();
     
     if (dd < 10)
     dd = '0' + dd;
     if (mm < 10)
     mm = '0' + mm;
     
     const formattedToday = dd + '/' + mm + '/' + yyyy;
     
     return formattedToday;
     },
     
     filterTemplate: function () {
     var grid = this._grid;
     
     var now = new Date();
     this._fromPicker = $("<input placeholder=" + '"' + "MM/DD/YYYY" + '"' + ">").datepicker({defaultDate: now.setFullYear(now.getFullYear() - 1)});
     
     this._fromPicker.on("change", function (e) {
     //                if (e.which === 13) {
     grid.search();
     e.preventDefault();
     //}
     });
     
     this._fromPicker.on("Close", function (e) {
     //                if (e.which === 13) {
     grid.search();
     e.preventDefault();
     //}
     });
     
     
     return $("<div>").append(this._fromPicker);
     
     
     },
     
     insertTemplate: function (value) {
     return this._insertPicker = $("<input>").datepicker({defaultDate: new Date()});
     },
     
     editTemplate: function (value) {
     return this._editPicker = $("<input>").datepicker().datepicker("setDate", new Date(value));
     
     },
     
     insertValue: function () {
     return this._insertPicker.datepicker("getDate").toISOString();
     },
     
     editValue: function () {
     return this._editPicker.datepicker("getDate").toISOString();
     },
     
     filterValue: function () {
     
     const today = new Date(this._fromPicker.datepicker("getDate"));
     const yyyy = today.getFullYear();
     let mm = today.getMonth() + 1; // Months start at 0!
     let dd = today.getDate();
     
     if (dd < 10)
     dd = '0' + dd;
     if (mm < 10)
     mm = '0' + mm;
     
     const formattedToday = dd + '/' + mm + '/' + yyyy;
     
     
     return {
     from: formattedToday
     //                from:this._fromPicker.datepicker("getDate")
     
     };
     }
     });
     
     jsGrid.fields.date = DateField;
     
     
     var countries = [
     {Name: "ALL", Id: ""},
     {Name: "Requester", Id: "00"},
     {Name: "Department Head", Id: "01"},
     {Name: "Storing", Id: "11"},
     {Name: "Returned", Id: "R00"},
     {Name: "Reject", Id: "99"},
     {Name: "Posted", Id: "21"},
     {Name: "Completed", Id: "22"}
     
     ];
     */

</script>