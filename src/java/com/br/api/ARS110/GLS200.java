/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.api.ARS110;

import MForms.Utils.MNEHelper;
import MForms.Utils.MNEProtocol;
import com.br.api.connect.ConnectDB2;
import com.br.api.data.GBVAR;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jilasak
 */
@WebServlet(name = "GLS200", urlPatterns = {"/GLS200"})
@MultipartConfig
public class GLS200 extends HttpServlet {

public static String DBPRD = GBVAR.DBPRD;
//    public static String DBPRD = "M3FDBTST";

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
            out.println("<title>Servlet GLS200</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GLS200 at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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

        if (request.getParameter("CONDITION").equalsIgnoreCase("CANCEL") && request.getParameter("vRCNO") != null) {

            System.out.println("------------------------------------------------------------------------------------");
            
            String LoginUrlConnectionm3 = "https://bkrmvxm3.bangkokranch.com:21008/mne/servlet/MvxMCSvt"; //PRD
//            String LoginUrlConnectionm3 = "https://bkrmvxm3.bangkokranch.com:22008/mne/servlet/MvxMCSvt"; //TST
            String LoginM3User = "MVXSECOFR";
            String LoginM3Password = "lawson@90";
            String LoginAppServer = "192.200.9.190";
            
            
            int LoginAppPort = GBVAR.DBPORT;
//            int LoginAppPort = 16305; // TST

            int CONO = Integer.parseInt(session.getAttribute("cono").toString().trim());
            String DIVI = session.getAttribute("divi").toString().trim();
            try {
                ClassGetData cgd = new ClassGetData();
                ClassSetData csd = new ClassSetData();
                int cono = CONO;
                String divi = DIVI;

                String RCNO = request.getParameter("vRCNO").trim();

                MNEHelper mne = new MNEHelper(LoginAppServer, LoginAppPort, LoginUrlConnectionm3);
                mne.logInToM3(cono, divi, LoginM3User, LoginM3Password);

                if (!mne.logInToM3(cono, divi, LoginM3User, LoginM3Password)) {
                    System.out.println("Can not login to M3 system");
                }

                String GLS200 = mne.runM3Pgm("GLS200");

                if ((GLS200).equals("")) {
                    System.out.println(" ไม่สามารถเปิดโปรแกรม GLS200 ได้");
                    mne.closeProgram(GLS200);
                }
                ResultSet RSL_GR = GetReceipt(cono, divi, RCNO);

                if (RSL_GR.next()) {
                    if (mne.panel.equals("GLS200/A1")) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WAYEA4", RSL_GR.getString("HC_YEAR").trim());
                        mne.setField("WWQTTP", "1");
                        mne.setField("FCS", "WAYEA4");
                        mne.setField("WAVONO", RSL_GR.getString("HC_VCNO").trim());
                        mne.pressKey(MNEProtocol.KeyEnter);

                        mne.setField("CMDTP", "LSTOPT");
                        mne.setField("CMDVAL", "18");
                        mne.setField("SELROWS", "R1");
                        mne.setField("WAYEA4", RSL_GR.getString("HC_YEAR").trim());
                        mne.setField("FCS", "WAYEA4");
                        mne.setField("WWQTTP", "1");
                        mne.setField("WAVONO", RSL_GR.getString("HC_VCNO").trim());
                        mne.selectOption("18");
                        System.out.println(mne.getMsg());

                    }
                    if (mne.panel.equals("GLS900/E")) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WWYEA4", RSL_GR.getString("HC_YEAR").trim());
                        mne.setField("FCS", "WAYEA4");
                        mne.setField("WWVONO", RSL_GR.getString("HC_VCNO").trim());
                        mne.pressKey(MNEProtocol.KeyEnter);
                    }
                    if (mne.panel.equals("GLS900/F")) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WWVTXT", "REV" + RSL_GR.getString("HC_VCNO").trim());
                        mne.setField("FCS", "WWVTXT");
                        mne.pressKey(MNEProtocol.KeyEnter);
                    }
                    System.out.println(mne.getMsg());
                    if (mne.panel.equals("GLS900/E")) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "F3");
                        mne.pressKey(MNEProtocol.KeyF03);
                    }
                    if (mne.panel.equals("GLS200/A1")) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "F3");
                        mne.pressKey(MNEProtocol.KeyF03);
                    }
                }

            } catch (Exception e) {
                System.out.println(e.toString());
            }
        
        }
        

    }

    public ResultSet GetReceipt(int cono, String divi, String RCNO) {

        try {
            ResultSet RsDataReceipt;
            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();
            String Sql1 = "SELECT HR_CONO, HR_DIVI, HC_RCNO, HC_TRDT, HC_PYNO,\n"
                    + "HC_PYDT, HC_CHKNO, HC_VCNO ,SUBSTRING(HC_TRDT,1,4) AS HC_YEAR\n"
                    + "FROM " + DBPRD + ".HR_RECEIPT\n" ///PRD
                    + "WHERE HR_CONO = '" + cono + "'\n"
                    + "AND HR_DIVI = '" + divi.trim() + "'\n"
                    + "AND HC_STS = '3'\n"
                    + "AND HC_RCNO = '" + RCNO.trim() + "'\n"
                    + "AND HC_VCNO != '' ";

            System.out.println(Sql1);
            RsDataReceipt = sta.executeQuery(Sql1);

            return RsDataReceipt;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;

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
