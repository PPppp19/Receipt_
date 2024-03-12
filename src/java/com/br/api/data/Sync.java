/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.api.data;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.codehaus.jettison.json.JSONException;

/**
 *
 * @author Wattana
 */
public class Sync extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Sync</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Sync at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            if (request.getParameter("page").equals("Company")) {
                try {
                    out.print(Utility.getCompany());
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("callrcno")) {
                try {
                    out.print(SelectDB2.callrcno(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("fnno"), request.getParameter("cuno")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("RETURNEXP")) {
                try {

                    UpdateDB2.RETURNEXP(request.getParameter("CONO"), request.getParameter("DIVI"),
                            request.getParameter("ID")
                    );

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("CHECKEXP")) {
                try {

                    out.print(SelectDB2.CHECKEXP(request.getParameter("CONO"), request.getParameter("DIVI"),
                            request.getParameter("ID"))
                    );
                    out.flush();

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("CHECKSTS")) {
                try {

                    out.print(SelectDB2.CHECKSTS(request.getParameter("CONO"), request.getParameter("DIVI"),
                            request.getParameter("ID"))
                    );
                    out.flush();

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("RETURNBM")) {

                try {

                    boolean isBM = SelectDB2.CHKISBM(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("ID"));

                    if (isBM == true) {

                        UpdateDB2.RETURNBM(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("ID"));

                    } else {

                        UpdateDB2.RETURNRC(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("ID"));

//                        System.out.println("fuckkkkkk");
//                        System.out.println("CASE NORMAL");
//                        UpdateDB2.RETURNBM(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("ID"));
                    }

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("UNLOCKINVOICE")) {
                try {
                    DeleteDB2.UNLOCKINVOICE(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("invno"));

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("SAVELOSTRC")) {
                try {

                    UpdateDB2.SAVELOSTRC(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("CUNO"), request.getParameter("LOSTRC"),
                            request.getParameter("PYNO"), request.getParameter("HC_TRDT"),
                            request.getParameter("HC_REAMT"), request.getParameter("HC_TYPE"),
                            request.getParameter("HC_PYDT"), request.getParameter("HC_CHKNO"),
                            request.getParameter("HC_BANK"), request.getParameter("HC_ACCODE"),
                            request.getParameter("HC_USER"), request.getParameter("HR_BKCHG"),
                            request.getParameter("locations")
                    );

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("CALLLOSTRC")) {
                try {
                    out.print(SelectDB2.CALLLOSTRC(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("ID")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("CALLLOSTRC2")) {
                try {
                    out.print(SelectDB2.CALLLOSTRC2(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("ID")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("CheckINVSTS")) {
                try {
                    out.print(SelectDB2.CheckINVSTS(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("invno")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Grid_ARS200")) {
                try {
                    out.print(SelectDB2.Grid_ARS200(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("payer"), request.getParameter("rcno")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Grid_ARS200_EXP")) {
                try {
                    out.print(SelectDB2.Grid_ARS200_EXP(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("payer"), request.getParameter("rcno")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Grid_Receipt")) {
                try {
                    out.print(SelectDB2.Grid_Receipt(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("rcno")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Grid_Expense")) {
                try {
                    out.print(SelectDB2.Grid_Expense(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("rcno")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Grid_Expensenew")) {
                try {
                    out.print(SelectDB2.Grid_Expensenew(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("fnno")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Cal_LineAmount")) {
//                System.out.println("SearchInvoice");
                try {
                    out.print(SelectDB2.Cal_LineAmount(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("RCNO"), request.getParameter("FNNO")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Cal_LineAmountnew")) {
//                System.out.println("SearchInvoice");
                try {
                    out.print(SelectDB2.Cal_LineAmountnew(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("RCNO"), request.getParameter("FNNO")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("CheckInvoice")) {
                System.out.println("CheckInvoice");
                try {
//                    out.print(SelectDB2.CheckInvoice(request.getParameter("CONO"), request.getParameter("CUSNO"), request.getParameter("INVNO")));
                    out.print(SelectDB2.CheckInvoice(request.getParameter("CONO"), request.getParameter("INVNO")));

                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("GET_RUNNING")) {
                System.out.println("CheckInvoice");
                try {
//                    out.print(SelectDB2.CheckInvoice(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("CUSNO")));
                    out.print(SelectDB2.CheckInvoice(request.getParameter("CONO"), request.getParameter("INVNO")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                     else if (request.getParameter("page").equals("CCMONITORING")) {
                try {
                    out.print(SelectDB2.CCMONITORING(request.getParameter("CONO"), request.getParameter("DIVI")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            
            else if (request.getParameter("page").equals("MONITORING")) {
                try {
                    out.print(SelectDB2.MONITORING(request.getParameter("CONO"), request.getParameter("DIVI")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("MONITORINGRC")) {
                try {
                    out.print(SelectDB2.MONITORINGRC(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("FNNO")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("checkreturn")) {
                try {
                    out.print(SelectDB2.checkreturn(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("FNNO")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("SearchReturnReceipt")) {
                try {
                    out.print(SelectDB2.SearchReturnReceipt(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("FNNO")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("SearchCustomerReceipt")) {
                try {
                    out.print(SelectDB2.SearchCustomerReceipt(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("FNNO"), request.getParameter("locations")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("SearchReceiptdepositnew")) {
                try {

                    System.out.println(request.getParameter("CONO"));
                    System.out.println(request.getParameter("DIVI"));
                    System.out.println(request.getParameter("RCNO"));
                    System.out.println(request.getParameter("LOCATION"));
                    out.print(SelectDB2.SearchReceiptdepositnew(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("RCNO"), request.getParameter("LOCATION")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("SearchReceiptcashnew")) {
                try {

                    System.out.println(request.getParameter("CONO"));
                    System.out.println(request.getParameter("DIVI"));
                    System.out.println(request.getParameter("RCNO"));
                    System.out.println(request.getParameter("LOCATION"));
                    out.print(SelectDB2.SearchReceiptcashnew(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("RCNO"), request.getParameter("LOCATION")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("SearchReceipt")) {
                try {
                    out.print(SelectDB2.SearchReceipt(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("RCNO"), request.getParameter("locations"), request.getParameter("FNNO")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("SearchReceipt00")) {
                try {
                    out.print(SelectDB2.SearchReceipt00(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("RCNO"), request.getParameter("locations")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("SearchReceipt_Cancel")) {
                try {
                    out.print(SelectDB2.SearchReceipt_Cancel(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("RCNO"), request.getParameter("locations")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("SearchReceipt_RC03")) {
                try {
                    out.print(SelectDB2.SearchReceipt_RC03(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("RCNO"), request.getParameter("locations")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("SearchReceipt_DEPOSIT")) {
                try {
                    out.print(SelectDB2.SearchReceipt_DEPOSIT(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("RCNO"), request.getParameter("locations")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("SearchReceipt_Check")) {
                try {
                    out.print(SelectDB2.SearchReceipt_Check(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("RCNO"), request.getParameter("locations")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("SetnamePayer")) {
                try {
                    out.print(SelectDB2.SetnamePayer(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("code")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("List_Bank")) {
                try {
                    out.print(Utility.List_Bank(request.getParameter("type"), request.getParameter("code"), request.getParameter("cono").trim(), request.getParameter("divi").trim()));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("CheckInvoiceLock")) {
                try {
                    out.print(SelectDB2.CheckInvoiceLock(request.getParameter("RCNO"), request.getParameter("cono").trim(), request.getParameter("divi").trim()));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("List_Eexpense")) {
                try {
                    out.print(Utility.List_Eexpense(request.getParameter("cono").trim(), request.getParameter("divi").trim()));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("List_Accode")) {
                try {
                    out.print(Utility.List_Accode(request.getParameter("cono").trim(), request.getParameter("divi").trim(), request.getParameter("code")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("CheckVoucher")) {
                try {
                    out.print(SelectDB2.CheckVoucher(request.getParameter("RCNO"), request.getParameter("cono"), request.getParameter("divi")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("CheckVouchernew")) {
                try {
                    out.print(SelectDB2.CheckVouchernew(request.getParameter("FNNO"), request.getParameter("cono"), request.getParameter("divi")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Call_GridClearExpense")) {
                try {
                    out.print(SelectDB2.Call_GridClearExpense(request.getParameter("cono"), request.getParameter("divi")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("SearchReceipt_ClearExpensenew")) {
                try {
                    out.print(SelectDB2.SearchReceipt_ClearExpensenew(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("RCNO")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("SearchReceipt_ClearExpense")) {
                try {
                    out.print(SelectDB2.SearchReceipt_ClearExpense(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("RCNO")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("AmountExpense")) {
                try {
                    out.print(SelectDB2.AmountExpense(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("RCNO")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("AmountExpensenew")) {
                try {
                    out.print(SelectDB2.AmountExpensenew(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("FNNO")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("CheckVoucher_EXP")) {
                try {
                    out.print(SelectDB2.CheckVoucher_EXP(request.getParameter("RCNO"), request.getParameter("cono"), request.getParameter("divi")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("CheckVoucher_EXPnew")) {
                try {
                    out.print(SelectDB2.CheckVoucher_EXPnew(request.getParameter("FNNO"), request.getParameter("cono"), request.getParameter("divi")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("checkamt")) {
                try {
                    out.print(SelectDB2.checkamt(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("RCNO"), request.getParameter("ID")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("CheckCreateReceipt")) {
                try {
                    out.print(SelectDB2.CheckCreateReceipt(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("RCNO")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("GridLineClearExpense")) {
                try {
                    out.print(SelectDB2.GridLineClearExpense(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("rcno")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Cal_LineAmountClearExpense")) {
                try {
                    out.print(SelectDB2.Cal_LineAmountClearExpense(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("RCNO")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Location")) {
                try {
                    out.print(Utility.getLocation(request.getParameter("cono").trim(), request.getParameter("divi").trim()));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Get_Receiver_Driver")) {
                try {
                    out.print(SelectDB2.Get_Receiver_Driver(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("rcno")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("List_Get_Receiver_Driver")) {
                try {
                    out.print(Utility.List_Get_Receiver_Driver(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("rcno")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Get_Receiver_Sale")) {
                try {
                    out.print(SelectDB2.Get_Receiver_Sale(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("pyno")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("List_Receiver")) {
                try {
                    out.print(Utility.List_Receiver(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("type")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("CheckCountReceiver")) {
                try {
                    out.print(SelectDB2.CheckCountReceiver(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("rcno")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("CheckCountReceivernew")) {
                try {
                    out.print(SelectDB2.CheckCountReceivernew(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("FNNO")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Grid_Series")) {
                try {
//                    out.print(SelectDB2.Grid_Series(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("username"), request.getParameter("location")));
                    out.print(SelectDB2.Grid_Series(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("location")));

                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("CheckRunningPrint")) {
                try {
                    out.print(SelectDB2.CheckRunningPrint(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("username"), request.getParameter("locations")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Grid_Cancel")) {
                try {
                    out.print(SelectDB2.Grid_Cancel(request.getParameter("cono"), request.getParameter("divi")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("CountInvoice")) {
                try {
                    out.print(SelectDB2.CountInvoice(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("rcno")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("CountInvoicenew")) {
                try {
                    out.print(SelectDB2.CountInvoicenew(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("FNNO")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Call_GridBankMapping")) {
                try {
                    out.print(SelectDB2.Call_GridBankMapping(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("location"), request.getParameter("type")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Call_GridBankMappingnew")) {
                try {
                    out.print(SelectDB2.Call_GridBankMappingnew(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("location"), request.getParameter("type")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Call_GridBankMappingnewEXP")) {
                try {
                    System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
                    out.print(SelectDB2.Call_GridBankMappingnewEXP(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("location"), request.getParameter("type")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Call_GridBankMappingnewDEPOSIT")) {
                try {
                    System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
                    out.print(SelectDB2.Call_GridBankMappingnewDEPOSIT(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("location"), request.getParameter("type")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("SearchReceipt_HeadMappingnew")) {
                try {
                    out.print(SelectDB2.SearchReceipt_HeadMappingnew(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("FNNO"), request.getParameter("LOCATION")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("SearchReceipcash")) {
                try {
                    out.print(SelectDB2.SearchReceipcash(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("RCNO"), request.getParameter("LOCATION")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("SHOWCUSTOMER")) {
                try {
                    out.print(SelectDB2.SHOWCUSTOMER(request.getParameter("cono")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("SearchReceipt_BankMappingnew")) {
                try {
                    out.print(SelectDB2.SearchReceipt_BankMappingnew(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("FNNO"), request.getParameter("LOCATION")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Grid_ARS200bycus")) {
                try {
                    out.print(SelectDB2.Grid_ARS200bycus(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("payer"), request.getParameter("rcno"), request.getParameter("customer")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Grid_ARS200_EXPnew")) {
                try {
                    out.print(SelectDB2.Grid_ARS200_EXPnew(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("payer"), request.getParameter("rcno"), request.getParameter("customer")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Grid_ARS200_cus_CLEAREXPENSE")) {
                try {
                    out.print(SelectDB2.Grid_ARS200_cus_CLEAREXPENSE(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("payer"), request.getParameter("FNNO")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Grid_ARS200_cus")) {
                try {
                    out.print(SelectDB2.Grid_ARS200_cus(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("payer"), request.getParameter("rcno"), request.getParameter("FNNO")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("getrctoddl")) {
                try {
                    out.print(SelectDB2.getrctoddl(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("FNNO")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("SearchReceipt_BankMapping")) {
                try {
                    out.print(SelectDB2.SearchReceipt_BankMapping(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("RCNO"), request.getParameter("LOCATION")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Getrcno")) {

                try {

                    System.out.println("aaaaaaaaaaaazzzzzaaaaaaaaaaaaaaaaaaaaaa");

                    out.print(SelectDB2.Getrcno(request.getParameter("cono"),
                            request.getParameter("divi"),
                            request.getParameter("FNNO"),
                            request.getParameter("CUNO"),
                            request.getParameter("PYNO"),
                            request.getParameter("HC_TRDT"),
                            request.getParameter("HC_REAMT"),
                            request.getParameter("HC_TYPE"),
                            request.getParameter("HC_PYDT"),
                            request.getParameter("HC_CHKNO"),
                            request.getParameter("HC_BANK"),
                            request.getParameter("HC_ACCODE"),
                            request.getParameter("HC_USER"),
                            request.getParameter("HR_BKCHG"),
                            request.getParameter("locations")
                    ));
                    out.flush();
//                    } else {
//                        System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbb");

//                        out.print(SelectDB2.checkRCNO(request.getParameter("cono"),
//                                request.getParameter("divi"),
//                                request.getParameter("FNNO"),
//                                request.getParameter("CUNO"),
//                                request.getParameter("PYNO"),
//                                request.getParameter("HC_TRDT"),
//                                request.getParameter("HC_REAMT"),
//                                request.getParameter("HC_TYPE"),
//                                request.getParameter("HC_PYDT"),
//                                request.getParameter("HC_CHKNO"),
//                                request.getParameter("HC_BANK"),
//                                request.getParameter("HC_ACCODE"),
//                                request.getParameter("HC_USER"),
//                                request.getParameter("HR_BKCHG"),
//                                request.getParameter("HC_LOCATION")));
//                        out.flush();
//                    }
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("GetrcnoPREVIOUS")) {

                try {

                    System.out.println("aaaaaaaaaaaazzzzzaaaaaaaaaaaaaaaaaaaaaa");

                    out.print(SelectDB2.GetrcnoPREVIOUS(request.getParameter("cono"),
                            request.getParameter("divi"),
                            request.getParameter("FNNO"),
                            request.getParameter("CUNO"),
                            request.getParameter("PYNO"),
                            request.getParameter("HC_TRDT"),
                            request.getParameter("HC_REAMT"),
                            request.getParameter("HC_TYPE"),
                            request.getParameter("HC_PYDT"),
                            request.getParameter("HC_CHKNO"),
                            request.getParameter("HC_BANK"),
                            request.getParameter("HC_ACCODE"),
                            request.getParameter("HC_USER"),
                            request.getParameter("HR_BKCHG"),
                            request.getParameter("locations")
                    ));
                    out.flush();
//                    } else {
//                        System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbb");

//                        out.print(SelectDB2.checkRCNO(request.getParameter("cono"),
//                                request.getParameter("divi"),
//                                request.getParameter("FNNO"),
//                                request.getParameter("CUNO"),
//                                request.getParameter("PYNO"),
//                                request.getParameter("HC_TRDT"),
//                                request.getParameter("HC_REAMT"),
//                                request.getParameter("HC_TYPE"),
//                                request.getParameter("HC_PYDT"),
//                                request.getParameter("HC_CHKNO"),
//                                request.getParameter("HC_BANK"),
//                                request.getParameter("HC_ACCODE"),
//                                request.getParameter("HC_USER"),
//                                request.getParameter("HR_BKCHG"),
//                                request.getParameter("HC_LOCATION")));
//                        out.flush();
//                    }
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Getfncode")) {
                try {
                    out.print(SelectDB2.Getfncode(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("pyno"), request.getParameter("trdt")));
                    out.flush();

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        if (session.getAttribute("cono") == null) {
            response.sendRedirect("login.jsp");
        }

        try {
            if (request.getParameter("page").equals("InsertHeader")) {
                SelectDB2 SDB2 = new SelectDB2();
//                String HC_RCNO = SDB2.GET_RUNNING(request.getParameter("HR_CONO"), request.getParameter("HR_DIVI"));
                try {
                    InsertDB2.InsertHeader(request.getParameter("HR_CONO"), request.getParameter("HR_DIVI"), request.getParameter("HC_TRDT"), request.getParameter("HC_PYNO"),
                            request.getParameter("HC_USER"), request.getParameter("HC_REAMT"),
                            request.getParameter("HC_TYPE"), request.getParameter("HC_PYDT"), request.getParameter("HC_CHKNO"), request.getParameter("HC_ACCODE"), request.getParameter("HC_BANK"),
                            request.getParameter("HR_BKCHG"), request.getParameter("locations"));
//                    InsertDB2.InsertReceiver(request.getParameter("HR_CONO"), request.getParameter("HR_DIVI"), HC_RCNO, request.getParameter("RR_Receiver"));

                } catch (Exception e) {
                    System.out.println(e.toString());
                }

                try {

                    String RCNO = SDB2.GET_RUNNINGAFTERCREATE(request.getParameter("HR_CONO"), request.getParameter("HR_DIVI"), request.getParameter("HC_TRDT"), request.getParameter("HC_PYNO"),
                            request.getParameter("HC_USER"), request.getParameter("HC_REAMT"));
//                    System.err.println(RCNO);
                    out.print(SelectDB2.ShowReceiveNO(RCNO, request.getParameter("HR_CONO"), request.getParameter("HR_DIVI"), request.getParameter("HC_PYNO")));
                    out.flush();
                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            } else if (request.getParameter("page").equals("InsertLine")) {
                try {
                    SelectDB2 SDB2 = new SelectDB2();
                    ResultSet rsl = SDB2.CheckInvoiceWithSupp(request.getParameter("LR_CONO"), request.getParameter("LR_DIVI"), request.getParameter("LR_RCNO"), request.getParameter("ESCINO"));
                    if (rsl.next()) {
                        InsertDB2.InsertLine(request.getParameter("LR_CONO"), request.getParameter("LR_DIVI"), request.getParameter("LR_RCNO"), request.getParameter("ESCINO"),
                                request.getParameter("ESIVDT"), request.getParameter("ESCUAM"), request.getParameter("RECEIVE"));
                        System.out.println("Pass");
                    } else {
                        System.out.println("Fails");
                    }

                } catch (Exception e) {
                    System.out.println(e.toString());
                }

            } else if (request.getParameter("page").equals("Update_Line")) {
                try {
                    UpdateDB2.Update_Line(request.getParameter("LR_CONO"), request.getParameter("LR_DIVI"), request.getParameter("LR_RCNO"), request.getParameter("LR_INVNO"),
                            request.getParameter("LR_REAMT"));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            } else if (request.getParameter("page").equals("Delete_Line")) {
                try {
                    DeleteDB2.Delete_Line(request.getParameter("LR_CONO"), request.getParameter("LR_DIVI"), request.getParameter("LR_RCNO"), request.getParameter("LR_INVNO"));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            } else if (request.getParameter("page").equals("UPDATECCLIST")) {
                try {
                    InsertDB2.UPDATECCLIST(request.getParameter("CCRCNO"),
                            request.getParameter("CCBY"),
                            request.getParameter("CCCONO"),
                            request.getParameter("CCDIVI"),
                            request.getParameter("CCRNNO"),
                            request.getParameter("CCREASON"));
                    

                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            } else if (request.getParameter("page").equals("CancelIDBM")) {
                try {
                    System.out.println("kuuuuu");
                    UpdateDB2.CancelIDBM(request.getParameter("cono"), request.getParameter("divi"), request.getParameter("id"), request.getParameter("username"));

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            else if (request.getParameter("page").equals("Rollback")) {
                try {
                    int CONO = Integer.parseInt(request.getParameter("cono").trim());
                    UpdateDB2.Update_Status(CONO, request.getParameter("divi"), request.getParameter("RCNO"), request.getParameter("STS"), request.getParameter("TYPE"));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }

                try {
                    out.print(SelectDB2.CheckSTS(request.getParameter("cono").trim(), request.getParameter("divi").trim(), request.getParameter("RCNO")));
                    out.flush();
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            } else if (request.getParameter("page").equals("SETHEADER")) {
                try {
                    UpdateDB2.SETHEADER(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("FNNO"), request.getParameter("PYNO"));

                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            } else if (request.getParameter("page").equals("UpdateHeader")) {
                try {
                    UpdateDB2.UpdateHeader(request.getParameter("HR_CONO"), request.getParameter("HR_DIVI"), request.getParameter("HC_PYNO"), request.getParameter("HC_REAMT"), request.getParameter("HC_TYPE"), request.getParameter("HC_PYDT"),
                            request.getParameter("HC_CHKNO"), request.getParameter("HC_BANK"), request.getParameter("HC_ACCODE"), request.getParameter("HR_BKCHG"), request.getParameter("HR_RCNO"));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            } else if (request.getParameter("page").equals("UpdateHeaderExpense")) {
                try {
                    UpdateDB2.UpdateHeaderExpense(request.getParameter("HR_CONO"), request.getParameter("HR_DIVI"), request.getParameter("HC_PYNO"), request.getParameter("HC_REAMT"), request.getParameter("HC_TYPE"), request.getParameter("HC_PYDT"),
                            request.getParameter("HC_CHKNO"), request.getParameter("HC_BANK"), request.getParameter("HC_ACCODE"), request.getParameter("HR_BKCHG"), request.getParameter("HR_RCNO"), request.getParameter("HC_USER"));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            } else if (request.getParameter("page").equals("UpdateHeaderDEPOSIT")) {
                try {
                    UpdateDB2.UpdateHeaderDEPOSIT(request.getParameter("HR_CONO"), request.getParameter("HR_DIVI"), request.getParameter("HC_PYNO"), request.getParameter("HC_REAMT"), request.getParameter("HC_TYPE"), request.getParameter("HC_PYDT"),
                            request.getParameter("HC_CHKNO"), request.getParameter("HC_BANK"), request.getParameter("HC_ACCODE"), request.getParameter("HR_BKCHG"), request.getParameter("HR_RCNO"), request.getParameter("HC_USER"));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            } else if (request.getParameter("page").equals("InsertExpensenew")) {
                try {
                    InsertDB2.InsertExpensenew(request.getParameter("LRE_CONO"), request.getParameter("LRE_DIVI"), request.getParameter("LRE_RCNO"), request.getParameter("LRE_CODE"),
                            request.getParameter("LRE_ACTCODE"), request.getParameter("LRE_AMT"), request.getParameter("FNNO"));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            } else if (request.getParameter("page").equals("InsertExpense")) {
                try {
                    InsertDB2.InsertExpense(request.getParameter("LRE_CONO"), request.getParameter("LRE_DIVI"), request.getParameter("LRE_RCNO"), request.getParameter("LRE_CODE"),
                            request.getParameter("LRE_ACTCODE"), request.getParameter("LRE_AMT"));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            } else if (request.getParameter("page").equals("Delete_Expense")) {
                try {
                    DeleteDB2.Delete_Expense(request.getParameter("LRE_CONO"), request.getParameter("LRE_DIVI"), request.getParameter("LRE_RCNO"), request.getParameter("LRE_CODE"), request.getParameter("LRE_ACTCODE"));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            } else if (request.getParameter("page").equals("InsertLineClear_EXPnew")) {
                try {
                    InsertDB2.InsertLineClear_EXPnew(request.getParameter("LREP_CONO"), request.getParameter("LREP_DIVI"), request.getParameter("LREP_RCNO"), request.getParameter("LREP_FNNO"), request.getParameter("ESCINO"),
                            request.getParameter("ESIVDT"), request.getParameter("ESCUAM"), request.getParameter("RECEIVE"));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            } else if (request.getParameter("page").equals("InsertLineClear_EXP")) {
                try {
                    InsertDB2.InsertLineClear_EXP(request.getParameter("LREP_CONO"), request.getParameter("LREP_DIVI"), request.getParameter("LREP_RCNO"), request.getParameter("ESCINO"),
                            request.getParameter("ESIVDT"), request.getParameter("ESCUAM"), request.getParameter("RECEIVE"));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            } else if (request.getParameter("page").equals("Update_LineClearExpense")) {
                try {
                    UpdateDB2.Update_LineClearExpense(request.getParameter("LREP_CONO"), request.getParameter("LREP_DIVI"), request.getParameter("LREP_RCNO"), request.getParameter("LREP_INVNO"),
                            request.getParameter("LREP_EXPAMT"));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            } else if (request.getParameter("page").equals("Delete_LineClearExpense")) {
                try {
                    DeleteDB2.Delete_LineClearExpense(request.getParameter("LREP_CONO"), request.getParameter("LREP_DIVI"), request.getParameter("LREP_RCNO"), request.getParameter("LREP_INVNO"));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            } else if (request.getParameter("page").equals("InsertPayerCode")) {
                try {
                    InsertDB2.InsertPayerCode(request.getParameter("HR_CONO"), request.getParameter("HR_DIVI"), request.getParameter("HC_PYNO"));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            } else if (request.getParameter("page").equals("InsertReceiver")) {
                try {
                    System.out.println("InsertReceiver : ppppppppppppppppp");
                    int check = SelectDB2.CheckReceiver(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("RCNO"));
                    if (check == 0) {
                        InsertDB2.InsertReceiver(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("RCNO"), request.getParameter("TYPE"), request.getParameter("RECEIVER"));
                    } else {
                        UpdateDB2.Update_Receiver(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("RCNO"), request.getParameter("TYPE"), request.getParameter("RECEIVER"));
                    }
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            } else if (request.getParameter("page").equals("SetSeries")) {
                try {
                    int check = SelectDB2.Check_Running_User(request.getParameter("RR_CONO"), request.getParameter("RR_DIVI"), request.getParameter("RR_LCCODE"), request.getParameter("RR_USER"));
                    if (check == 0) {
                        InsertDB2.SetSeries(request.getParameter("RR_CONO"), request.getParameter("RR_DIVI"), request.getParameter("RR_LCCODE"), request.getParameter("RR_USER"), request.getParameter("RR_START"),
                                request.getParameter("RR_END"), request.getParameter("RR_COUNT"));
                    } else {
                        UpdateDB2.Update_SeriesReceipt(request.getParameter("RR_CONO"), request.getParameter("RR_DIVI"), request.getParameter("RR_LCCODE"), request.getParameter("RR_USER"), request.getParameter("RR_START"),
                                request.getParameter("RR_END"), request.getParameter("RR_COUNT"));
                    }
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            } else if (request.getParameter("page").equals("InsertCancel")) {
                try {
                    InsertDB2.InsertCancel(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("RCNO"), request.getParameter("REASON"), request.getParameter("ACTION"),
                            request.getParameter("APP1"), request.getParameter("APP2"));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            } else if (request.getParameter("page").equals("UpdateCancel")) {
                try {
                    UpdateDB2.UpdateCancel(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("RCNO"), request.getParameter("REASON"), request.getParameter("ACTION"),
                            request.getParameter("APP1"), request.getParameter("APP2"));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            } else if (request.getParameter("page").equals("updateRequester")) {
                try {
                    UpdateDB2.updateRequester(request.getParameter("CONO"), request.getParameter("DIVI"), request.getParameter("RCNO"), request.getParameter("USER"));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            } else if (request.getParameter("page").equals("UpdateTransactionDate")) {
                try {
                    UpdateDB2.UpdateTransactionDate(request.getParameter("HR_CONO"), request.getParameter("HR_DIVI"), request.getParameter("HC_TRDT"), request.getParameter("HR_RCNO"), request.getParameter("BANK"), request.getParameter("LCODE"));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            } else if (request.getParameter("page").equals("UpdateTransactionDatenew")) {
                try {
                    UpdateDB2.UpdateTransactionDatenew(request.getParameter("HR_CONO"), request.getParameter("HR_DIVI"), request.getParameter("HC_TRDT"), request.getParameter("HR_RCNO"), request.getParameter("BANK"), request.getParameter("LCODE"), request.getParameter("HR_BKCHG"), request.getParameter("FNNO"), request.getParameter("AMT"));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
