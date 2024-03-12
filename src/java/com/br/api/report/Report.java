/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.api.report;

import com.br.api.ARS110.ARS110;
import com.br.api.ARS110.ClassFormatData;
import com.br.api.ARS110.ClassGetData;
import com.br.api.connect.ConnectDB2;
import com.br.api.data.GBVAR;
import com.br.api.data.SelectDB2;
import com.br.api.data.UpdateDB2;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.PrinterName;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Wattana
 */
@WebServlet(name = "Report", urlPatterns = {"/Report"})
@MultipartConfig
public class Report extends HttpServlet {

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
            out.println("<title>Servlet Report</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Report at " + request.getContextPath() + "</h1>");
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

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(true);

        if (request.getParameter("vRCNO") != null && request.getParameter("vReport").equalsIgnoreCase("RP01")) {
            try {

                File reportFile = new File(getServletContext().getRealPath("Report/EPRC_001.jasper"));
                ClassGetData cgd = new ClassGetData();

                //  ClassGetReport cgr = new ClassGetReport();
                int CONO = Integer.parseInt(session.getAttribute("cono").toString().trim());
                String DIVI = session.getAttribute("divi").toString().trim();
                String RCNO = request.getParameter("vRCNO").trim();
                String Location = session.getAttribute("locations").toString().trim();

                if (Location.equalsIgnoreCase("DC_CM")) {
//                    reportFile = new File(getServletContext().getRealPath("Report/EPRC_002.jasper"));
                    reportFile = new File(getServletContext().getRealPath("Report/EPRC_0012.jasper"));

                }
                
                 if (Location.equalsIgnoreCase("NSD")) {
//                    reportFile = new File(getServletContext().getRealPath("Report/EPRC_002.jasper"));
                    reportFile = new File(getServletContext().getRealPath("Report/EPRC_0012.jasper"));

                }

                String HC_TYPE = cgd.GET_ReceiptType_Reprint(CONO, DIVI, RCNO);
                Double AMT = Cal_LineAmountnew(CONO, DIVI, RCNO);
                String THB = WordCurrency(String.format("%.2f", AMT));

                if (HC_TYPE.equalsIgnoreCase("TRANSFER_EXP") || HC_TYPE.equalsIgnoreCase("CHECK_EXP")) {
                    ResultSet rsl = cgd.CheckTypeExpense(CONO, DIVI, RCNO);
                    AMT = Cal_LineAmount_Expense(CONO, DIVI, RCNO);
                    THB = WordCurrency(String.format("%.2f", AMT));
                    if (rsl.next()) {
                        reportFile = new File(getServletContext().getRealPath("Report/EPRC_003.jasper"));
                    }

                } else if (HC_TYPE.equalsIgnoreCase("CASH_DEPOSIT") || HC_TYPE.equalsIgnoreCase("TRANSFER_DEPOSIT") || HC_TYPE.equalsIgnoreCase("CHECK_DEPOSIT")) {
                    System.out.println("DEPOSITDEPOSITDEPOSITDEPOSITDEPOSITDEPOSITDEPOSITDEPOSITDEPOSITDEPOSITDEPOSITDEPOSITDEPOSITDEPOSITDEPOSITDEPOSITDEPOSITDEPOSITDEPOSITDEPOSITDEPOSITDEPOSITDEPOSITDEPOSIT");
                    reportFile = new File(getServletContext().getRealPath(GBVAR.DPNAME));
                }

                Map parameterss = new HashMap();
                parameterss.put("CONO", CONO);
                parameterss.put("DIVI", DIVI);
                parameterss.put("RCNO", RCNO);
                parameterss.put("THB", THB);

                Connection conn = ConnectDB2.ConnectionDB();
                byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameterss, conn);
                response.setContentType("application/pdf");
                response.setContentLength(bytes.length);
                ServletOutputStream ouputStream = response.getOutputStream();

                ouputStream.write(bytes, 0, bytes.length);
                ouputStream.flush();
                ouputStream.close();

            } catch (JRException ex) {
                Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (request.getParameter("vFNCODE") != null && request.getParameter("vReport").equalsIgnoreCase("RP01")) {

            System.out.println("GET SECOND PRINT");
            /*
            try {
                

                File reportFile = new File(getServletContext().getRealPath("Report/EPRC_001.jasper"));
                ClassGetData cgd = new ClassGetData();

                //  ClassGetReport cgr = new ClassGetReport();
                int CONO = Integer.parseInt(session.getAttribute("cono").toString().trim());
                String DIVI = session.getAttribute("divi").toString().trim();
                String RCNO = request.getParameter("vRCNO").trim();
                String Location = session.getAttribute("locations").toString().trim();

                if (Location.equalsIgnoreCase("DC_CM")) {
                    reportFile = new File(getServletContext().getRealPath("Report/EPRC_002.jasper"));

                }

                String HC_TYPE = cgd.GET_ReceiptType_Reprint(CONO, DIVI, RCNO);
                Double AMT = Cal_LineAmountnew(CONO, DIVI, RCNO);
                String THB = WordCurrency(String.format("%.2f", AMT));

                if (HC_TYPE.equalsIgnoreCase("TRANSFER_EXP") || HC_TYPE.equalsIgnoreCase("CHECK_EXP")) {
                    ResultSet rsl = cgd.CheckTypeExpense(CONO, DIVI, RCNO);
                    AMT = Cal_LineAmount_Expense(CONO, DIVI, RCNO);
                    THB = WordCurrency(String.format("%.2f", AMT));
                    if (rsl.next()) {
                        reportFile = new File(getServletContext().getRealPath("Report/EPRC_003.jasper"));
                    }

                } else if (HC_TYPE.equalsIgnoreCase("CASH_DEPOSIT") || HC_TYPE.equalsIgnoreCase("TRANSFER_DEPOSIT") || HC_TYPE.equalsIgnoreCase("CHECK_DEPOSIT")) {
                    reportFile = new File(getServletContext().getRealPath("Report/EPRC_004.jasper"));
                }

                Map parameterss = new HashMap();
                parameterss.put("CONO", CONO);
                parameterss.put("DIVI", DIVI);
                parameterss.put("RCNO", RCNO);
                parameterss.put("THB", THB);

                Connection conn = ConnectDB2.ConnectionDB();
                byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameterss, conn);
                response.setContentType("application/pdf");
                response.setContentLength(bytes.length);
                ServletOutputStream ouputStream = response.getOutputStream();

                ouputStream.write(bytes, 0, bytes.length);
                ouputStream.flush();
                ouputStream.close();
                

            } catch (JRException ex) {
                Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
            }
             */
        } else if (request.getParameter("vRCNO") != null && request.getParameter("vReport").equalsIgnoreCase("RP012")) {
            try {

                System.out.println("-----------------------------------------------------");
                System.out.println("RP012");

                File reportFile = new File(getServletContext().getRealPath("Report/EPRC_0012.jasper"));
                ClassGetData cgd = new ClassGetData();

                //  ClassGetReport cgr = new ClassGetReport();
                int CONO = Integer.parseInt(session.getAttribute("cono").toString().trim());

                String CONOtxt = session.getAttribute("cono").toString().trim();
                String DIVI = session.getAttribute("divi").toString().trim();
                String RCNO = request.getParameter("vRCNO").trim();
                String Location = session.getAttribute("locations").toString().trim();
                String FNNO = SelectDB2.Call_FNNO(CONOtxt, DIVI, RCNO);

                String checkBox = request.getParameter("last");
                boolean LAST = false;

                System.out.println(checkBox);
                if (checkBox.equalsIgnoreCase("TRUE")) {
                    LAST = true;
                } else {
                    LAST = false;
                }

                if (Location.equalsIgnoreCase("DC_CM")) {
//                    reportFile = new File(getServletContext().getRealPath("Report/EPRC_002.jasper"));
                    reportFile = new File(getServletContext().getRealPath("Report/EPRC_0012.jasper"));

                }
                
                 if (Location.equalsIgnoreCase("NSD")) {
//                    reportFile = new File(getServletContext().getRealPath("Report/EPRC_002.jasper"));
                    reportFile = new File(getServletContext().getRealPath("Report/EPRC_0012.jasper"));

                }

                String HC_TYPE = cgd.GET_ReceiptType_Reprint(CONO, DIVI, RCNO);
                Double AMT = Cal_LineAmountnew(CONO, DIVI, RCNO);
                String THB = WordCurrency(String.format("%.2f", AMT));

                if (HC_TYPE.equalsIgnoreCase("TRANSFER_EXP") || HC_TYPE.equalsIgnoreCase("CHECK_EXP")) {
                    ResultSet rsl = cgd.CheckTypeExpense(CONO, DIVI, RCNO);
                    AMT = Cal_LineAmount_Expense(CONO, DIVI, RCNO);
                    THB = WordCurrency(String.format("%.2f", AMT));
                    if (rsl.next()) {
                        reportFile = new File(getServletContext().getRealPath("Report/EPRC_0013.jasper"));
                    }

                } else if (HC_TYPE.equalsIgnoreCase("CASH_DEPOSIT") || HC_TYPE.equalsIgnoreCase("TRANSFER_DEPOSIT") || HC_TYPE.equalsIgnoreCase("CHECK_DEPOSIT")) {
                    reportFile = new File(getServletContext().getRealPath(GBVAR.DPNAME));
                }

                System.out.println(reportFile);

                Map parameterss = new HashMap();
                parameterss.put("CONO", CONO);
                parameterss.put("DIVI", DIVI);
                parameterss.put("RCNO", RCNO);
                parameterss.put("THB", THB);
                parameterss.put("FNNO", FNNO);
                parameterss.put("LAST", LAST);

                System.out.println("-----------------------------------------------------");
                System.out.println(LAST);

                System.out.println(reportFile.getPath());
                Connection conn = ConnectDB2.ConnectionDB();
                byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameterss, conn);
                response.setContentType("application/pdf");
                response.setContentLength(bytes.length);
                ServletOutputStream ouputStream = response.getOutputStream();

                ouputStream.write(bytes, 0, bytes.length);
                ouputStream.flush();
                ouputStream.close();

            } catch (JRException ex) {
                Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (request.getParameter("vRCNO") != null && request.getParameter("vReport").equalsIgnoreCase("RP013")) {
            try {

                System.out.println("-----------------------------------------------------");
                System.out.println("RP013");

                File reportFile = new File(getServletContext().getRealPath("Report/EPRC_0013.jasper"));
                ClassGetData cgd = new ClassGetData();

                //  ClassGetReport cgr = new ClassGetReport();
                int CONO = Integer.parseInt(session.getAttribute("cono").toString().trim());

                String CONOtxt = session.getAttribute("cono").toString().trim();
                String DIVI = session.getAttribute("divi").toString().trim();
                String RCNO = request.getParameter("vRCNO").trim();
                String Location = session.getAttribute("locations").toString().trim();
                String FNNO = SelectDB2.Call_FNNO(CONOtxt, DIVI, RCNO);

                String checkBox = request.getParameter("last");
                String checkbl = request.getParameter("checkbl");
//                String checkbl ="TRUE"; 

                boolean LAST = false;
                boolean CHECKBL = false;

                System.out.println(checkBox);
                if (checkBox.equalsIgnoreCase("TRUE")) {
                    LAST = true;
                } else {
                    LAST = false;
                }
                if (checkbl.equalsIgnoreCase("TRUE")) {
                    CHECKBL = true;
                } else {
                    CHECKBL = false;
                }

                if (Location.equalsIgnoreCase("DC_CM")) {
//                    reportFile = new File(getServletContext().getRealPath("Report/EPRC_002.jasper"));
                    reportFile = new File(getServletContext().getRealPath("Report/EPRC_0013.jasper"));

                }

                String HC_TYPE = cgd.GET_ReceiptType_Reprint(CONO, DIVI, RCNO);
                Double AMT = Cal_LineAmountnew(CONO, DIVI, RCNO);
                String THB = WordCurrency(String.format("%.2f", AMT));

                if (HC_TYPE.equalsIgnoreCase("TRANSFER_EXP") || HC_TYPE.equalsIgnoreCase("CHECK_EXP")) {
                    ResultSet rsl = cgd.CheckTypeExpense(CONO, DIVI, RCNO);
                    AMT = Cal_LineAmount_Expense(CONO, DIVI, RCNO);
                    THB = WordCurrency(String.format("%.2f", AMT));
                    if (rsl.next()) {
                        reportFile = new File(getServletContext().getRealPath("Report/EPRC_0013.jasper"));
                    }

                } else if (HC_TYPE.equalsIgnoreCase("CASH_DEPOSIT") || HC_TYPE.equalsIgnoreCase("TRANSFER_DEPOSIT") || HC_TYPE.equalsIgnoreCase("CHECK_DEPOSIT")) {
                    reportFile = new File(getServletContext().getRealPath(GBVAR.DPNAME));
                }

                System.out.println(reportFile);

                Map parameterss = new HashMap();
                parameterss.put("CONO", CONO);
                parameterss.put("DIVI", DIVI);
                parameterss.put("RCNO", RCNO);
                parameterss.put("THB", THB);
                parameterss.put("FNNO", FNNO);
                parameterss.put("LAST", LAST);
                parameterss.put("CHECKBL", CHECKBL);

                System.out.println("-----------------------------------------------------");
                System.out.println(LAST);

                System.out.println(reportFile.getPath());
                Connection conn = ConnectDB2.ConnectionDB();
                byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameterss, conn);
                response.setContentType("application/pdf");
                response.setContentLength(bytes.length);
                ServletOutputStream ouputStream = response.getOutputStream();

                ouputStream.write(bytes, 0, bytes.length);
                ouputStream.flush();
                ouputStream.close();

            } catch (JRException ex) {
                Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (request.getParameter("vReport").equalsIgnoreCase("RP02")) {
            try {
                File reportFile = new File(getServletContext().getRealPath("Report/RC_002.jasper"));
                //  ClassGetReport cgr = new ClassGetReport();
                int CONO = Integer.parseInt(session.getAttribute("cono").toString().trim());
                String DIVI = session.getAttribute("divi").toString().trim();
                String fdate = request.getParameter("fdate").replaceAll("-", "");
                String tdate = request.getParameter("tdate").replaceAll("-", "");
                String type = request.getParameter("vTYPE").trim();
                String locations = request.getParameter("location").trim();

                Map parameterss = new HashMap();
                parameterss.put("cono", CONO);
                parameterss.put("divi", DIVI);
                parameterss.put("fromdate", Integer.parseInt(fdate));
                parameterss.put("todate", Integer.parseInt(tdate));
                parameterss.put("type", type);
                parameterss.put("locations", locations);

                Connection conn = ConnectDB2.ConnectionDB();
                byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameterss, conn);
                response.setContentType("application/pdf");
                response.setContentLength(bytes.length);
                ServletOutputStream ouputStream = response.getOutputStream();

                ouputStream.write(bytes, 0, bytes.length);
                ouputStream.flush();
                ouputStream.close();

            } catch (JRException ex) {
                Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (request.getParameter("vReport").equalsIgnoreCase("RP03_EXCEL")) {

            String path = getServletContext().getRealPath("/Report/");
            int CONO = Integer.parseInt(session.getAttribute("cono").toString().trim());
            String DIVI = session.getAttribute("divi").toString().trim();
            String fdate = request.getParameter("fdate").replaceAll("-", "");
            String tdate = request.getParameter("tdate").replaceAll("-", "");

            String locations = request.getParameter("location").trim();
            Map parameterss = new HashMap();

            parameterss.put("cono", CONO);
            parameterss.put("divi", DIVI);
            parameterss.put("fromdate", Integer.parseInt(fdate));
            parameterss.put("todate", Integer.parseInt(tdate));
            parameterss.put("locations", locations);

            JasperDesign jasperDesign;
            try {
                jasperDesign = JRXmlLoader.load(path + "RC_003_XLSX.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                Connection connaaa = null;
                try {
                    connaaa = ConnectDB2.ConnectionDB();
                } catch (Exception ex) {
                    Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
                }
                JasperPrint jasp = JasperFillManager.fillReport(jasperReport, parameterss, connaaa);
                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + "RC_003.xlsx" + "\"");
                JRXlsxExporter exporterXls = new JRXlsxExporter();
                ServletOutputStream ouputStream = response.getOutputStream();
                exporterXls.setParameter(JRExporterParameter.JASPER_PRINT, jasp);
                exporterXls.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
                exporterXls.exportReport();
                ouputStream.flush();
                ouputStream.close();
            } catch (Exception ex) {
                Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (request.getParameter("vReport").equalsIgnoreCase("RP03_PDF")) {
            try {
                File reportFile = new File(getServletContext().getRealPath("Report/RC_003.jasper"));
                //  ClassGetReport cgr = new ClassGetReport();
                int CONO = Integer.parseInt(session.getAttribute("cono").toString().trim());
                String DIVI = session.getAttribute("divi").toString().trim();
                String fdate = request.getParameter("fdate").replaceAll("-", "");
                String tdate = request.getParameter("tdate").replaceAll("-", "");
                String locations = request.getParameter("location").trim();

                Map parameterss = new HashMap();
                parameterss.put("cono", CONO);
                parameterss.put("divi", DIVI);
                parameterss.put("fromdate", Integer.parseInt(fdate));
                parameterss.put("todate", Integer.parseInt(tdate));
                parameterss.put("locations", locations);

                Connection conn = ConnectDB2.ConnectionDB();
                byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameterss, conn);
                response.setContentType("application/pdf");
                response.setContentLength(bytes.length);
                ServletOutputStream ouputStream = response.getOutputStream();

                ouputStream.write(bytes, 0, bytes.length);
                ouputStream.flush();
                ouputStream.close();

            } catch (JRException ex) {
                Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (request.getParameter("vReport").equalsIgnoreCase("RP04_EXCEL")) {

            String path = getServletContext().getRealPath("/Report/");
            int CONO = Integer.parseInt(session.getAttribute("cono").toString().trim());
            String DIVI = session.getAttribute("divi").toString().trim();
            String fdate = request.getParameter("fdate").replaceAll("-", "");
            String tdate = request.getParameter("tdate").replaceAll("-", "");

            String locations = request.getParameter("location").trim();
            Map parameterss = new HashMap();

            parameterss.put("cono", CONO);
            parameterss.put("divi", DIVI);
            parameterss.put("fromdate", Integer.parseInt(fdate));
            parameterss.put("todate", Integer.parseInt(tdate));
            parameterss.put("locations", locations);

            JasperDesign jasperDesign;
            try {
                jasperDesign = JRXmlLoader.load(path + "RC_004_XLSX.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                Connection connaaa = null;
                try {
                    connaaa = ConnectDB2.ConnectionDB();
                } catch (Exception ex) {
                    Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
                }
                JasperPrint jasp = JasperFillManager.fillReport(jasperReport, parameterss, connaaa);
                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + "RC_004.xlsx" + "\"");
                JRXlsxExporter exporterXls = new JRXlsxExporter();
                ServletOutputStream ouputStream = response.getOutputStream();
                exporterXls.setParameter(JRExporterParameter.JASPER_PRINT, jasp);
                exporterXls.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
                exporterXls.exportReport();
                ouputStream.flush();
                ouputStream.close();
            } catch (Exception ex) {
                Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (request.getParameter("vReport").equalsIgnoreCase("RP04_PDF")) {
            try {
                File reportFile = new File(getServletContext().getRealPath("Report/RC_004_XLSX.jasper"));
                //  ClassGetReport cgr = new ClassGetReport();
                int CONO = Integer.parseInt(session.getAttribute("cono").toString().trim());
                String DIVI = session.getAttribute("divi").toString().trim();
                String fdate = request.getParameter("fdate").replaceAll("-", "");
                String tdate = request.getParameter("tdate").replaceAll("-", "");
                String locations = request.getParameter("location").trim();

                Map parameterss = new HashMap();
                parameterss.put("cono", CONO);
                parameterss.put("divi", DIVI);
                parameterss.put("fromdate", Integer.parseInt(fdate));
                parameterss.put("todate", Integer.parseInt(tdate));
                parameterss.put("locations", locations);

                System.out.println(CONO);
                System.out.println(DIVI);
                System.out.println(Integer.parseInt(fdate));
                System.out.println(Integer.parseInt(tdate));
                System.out.println(locations);

                Connection conn = ConnectDB2.ConnectionDB();
                byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameterss, conn);
                response.setContentType("application/pdf");
                response.setContentLength(bytes.length);
                ServletOutputStream ouputStream = response.getOutputStream();

                ouputStream.write(bytes, 0, bytes.length);
                ouputStream.flush();
                ouputStream.close();

            } catch (JRException ex) {
                Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (request.getParameter("vReport").equalsIgnoreCase("RP05_PDF")) {
            try {
                File reportFile = new File(getServletContext().getRealPath("Report/RC_005.jasper"));
                //  ClassGetReport cgr = new ClassGetReport();
                int CONO = Integer.parseInt(session.getAttribute("cono").toString().trim());
                String DIVI = session.getAttribute("divi").toString().trim();
                String fdate = request.getParameter("fdate").replaceAll("-", "");
                String tdate = request.getParameter("tdate").replaceAll("-", "");
                String year = request.getParameter("fdate").replaceAll("-", "").substring(0, 4);
                System.out.println(year + "" + Integer.parseInt(fdate));

                Map parameterss = new HashMap();
                parameterss.put("cono", CONO);
                parameterss.put("divi", DIVI);
                parameterss.put("from_date", Integer.parseInt(fdate));
                parameterss.put("to_date", Integer.parseInt(tdate));

                parameterss.put("year", year);
                Connection conn = ConnectDB2.ConnectionDB();
                byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameterss, conn);
                response.setContentType("application/pdf");
                response.setContentLength(bytes.length);
                ServletOutputStream ouputStream = response.getOutputStream();

                ouputStream.write(bytes, 0, bytes.length);
                ouputStream.flush();
                ouputStream.close();

            } catch (JRException ex) {
                Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
            }
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
//        processRequest(request, response);
        HttpSession session = request.getSession(true);

        String rc = request.getParameter("vRCNO");
        String pp = request.getParameter("REPRINT");
        String vFN = request.getParameter("vFNCODE");

        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx)");
        System.out.println("rc :" + rc);
        System.out.println("pp : " + pp);
        System.out.println("vFN : " + vFN);

        if (request.getParameter("vRCNO") != null && request.getParameter("REPRINT").equalsIgnoreCase("NONE")) {
            try {
                System.out.println("FIRST PRINT");

                int CONO = Integer.parseInt(session.getAttribute("cono").toString().trim());
                String DIVI = session.getAttribute("divi").toString().trim();
                String RCNO = request.getParameter("vRCNO").trim();
                String PRINTER = request.getParameter("vPrinter").trim();
                Double AMT = Cal_LineAmount(CONO, DIVI, RCNO);
                String Str_AMT = String.format("%.2f", AMT);
                String THB = WordCurrency(Str_AMT);

                String USER = session.getAttribute("username").toString().trim();
                String location = session.getAttribute("locations").toString().trim();
                String vcno = "-", Fix_Running = "-";
                String FNNO = request.getParameter("vFNNO").trim();

                UpdateDB2.Update_Status(CONO, DIVI, RCNO, "2", "PRINT");
//                ARS110 AR = new ARS110();
                System.out.println("pppppppppppppppppppppppppppp)");
                System.out.println(vcno);
                System.out.println(Fix_Running);
                System.out.println(CONO);
                System.out.println(DIVI);
                System.out.println(RCNO);

                System.out.println("pppppppppppppppppppppppppppp)");

//                AR.AUTO_ARS110(CONO, DIVI, RCNO);
                ResultSet rsl = SelectDB2.CheckVCStepPrint(session.getAttribute("cono").toString().trim(), DIVI, RCNO, USER, location);
                if (rsl.next()) {
                    vcno = rsl.getString("HC_VCNO").trim();
                    Fix_Running = rsl.getString("RR_COUNT").trim();

                    System.out.println("vccccccccccccccccccccccc)");
                    System.out.println(vcno);
                }
                if (!Fix_Running.equalsIgnoreCase("-")) {
                    if (!PRINTER.equalsIgnoreCase("RECEIPT PRINTER3")) {
//                        preprint(CONO, DIVI, RCNO, THB, PRINTER);
                        preprintnewfull(CONO, DIVI, RCNO, THB, PRINTER, Boolean.TRUE, FNNO);

                    }
                    UpdateDB2.Update_Running_Fix(String.valueOf(CONO), DIVI, RCNO, location, Fix_Running);
                    UpdateDB2.Update_Running_Master(String.valueOf(CONO), DIVI, location, Fix_Running, USER, RCNO);
                    System.out.println(RCNO + "   " + vcno);
                } else {
                    System.out.println(RCNO + " ไม่มีปริ้นนะ");
                }

            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        } else if (request.getParameter("vRCNO") != null && request.getParameter("REPRINT").equalsIgnoreCase("REPRINT2")) {

            System.out.println("REPRINT2 PRINT");

            try {
                System.out.println("REPRINT2");
                int CONO = Integer.parseInt(session.getAttribute("cono").toString().trim());
                String DIVI = session.getAttribute("divi").toString().trim();
                String RCNO = request.getParameter("vRCNO").trim();
                String FNNO = request.getParameter("vFNNO").trim();

                String CHECKBL = request.getParameter("vCHECKBL");

                String PATIAL = request.getParameter("vPATIAL");

                System.out.println("CHECKBL : " + CHECKBL);
                System.out.println("PATIAL : " + PATIAL);

                boolean CHECKBL2 = false;
                if (CHECKBL != null) {

                    CHECKBL2 = true;
                }

                boolean PATIAL2 = false;
                if (PATIAL != null) {

                    PATIAL2 = true;
                }

                String PRINTER = request.getParameter("vPrinter").trim();

                Double AMT = Cal_LineAmountnew(CONO, DIVI, RCNO);
                String THB = WordCurrency(String.format("%.2f", AMT));
                ClassGetData cgd = new ClassGetData();
                String HC_TYPE = cgd.GET_ReceiptType_Reprint(CONO, DIVI, RCNO);

                ////////
                ResultSet rsl_detail = SelectDB2.Reprint_Detail(String.valueOf(CONO).trim(), DIVI, RCNO);
                String HC_VCNO = "-", RR_COUNT = "-", HC_USER = "-", HC_LOCATION = "-", HC_FIXNO = "-";
                if (rsl_detail.next()) {
                    HC_FIXNO = rsl_detail.getString("HC_FIXNO").trim();
                    RR_COUNT = rsl_detail.getString("RR_COUNT").trim();
                    HC_USER = rsl_detail.getString("HC_USER").trim();
                    HC_LOCATION = rsl_detail.getString("HC_LOCATION").trim();
                }
                if (!RR_COUNT.equalsIgnoreCase("-") && HC_FIXNO.equalsIgnoreCase("-")) {
                    if (HC_TYPE.equalsIgnoreCase("TRANSFER_EXP") || HC_TYPE.equalsIgnoreCase("CHECK_EXP") || HC_TYPE.equalsIgnoreCase("CASH_EXP")) {
                        ResultSet rsl = cgd.CheckTypeExpense(CONO, DIVI, RCNO);
                        AMT = Cal_LineAmount_Expense(CONO, DIVI, RCNO);
                        THB = WordCurrency(String.format("%.2f", AMT));
                        if (rsl.next()) {

                            System.out.println("111111111111111111111");

                            //preprint_expense(CONO, DIVI, RCNO, THB, PRINTER);
                            preprintnewfull2(CONO, DIVI, RCNO, THB, PRINTER, PATIAL2, FNNO, CHECKBL2);

                        } else {
                            if (HC_TYPE.equalsIgnoreCase("CASH_EXP")) {
                                //preprint_expense(CONO, DIVI, RCNO, THB, PRINTER);
                            } else {
                                //preprint(CONO, DIVI, RCNO, THB, PRINTER);
                            }
                        }
                    } else if (HC_TYPE.equalsIgnoreCase("CASH_DEPOSIT") || HC_TYPE.equalsIgnoreCase("TRANSFER_DEPOSIT") || HC_TYPE.equalsIgnoreCase("CHECK_DEPOSIT")) {
                        preprint_DEPOSIT(CONO, DIVI, RCNO, THB, PRINTER, PATIAL2, FNNO, CHECKBL2);

                    } else {
                        //preprint(CONO, DIVI, RCNO, THB, PRINTER);
                    }
                    //UpdateDB2.Update_Running_Fix(String.valueOf(CONO), DIVI, RCNO, HC_LOCATION, RR_COUNT);
                    //UpdateDB2.Update_Running_Master(String.valueOf(CONO), DIVI, HC_LOCATION, RR_COUNT, HC_USER, RCNO);
                } else if (!HC_FIXNO.equalsIgnoreCase("-")) {
                    if (HC_TYPE.equalsIgnoreCase("TRANSFER_EXP") || HC_TYPE.equalsIgnoreCase("CHECK_EXP") || HC_TYPE.equalsIgnoreCase("CASH_EXP")) {
                        ResultSet rsl = cgd.CheckTypeExpensenew(CONO, DIVI, FNNO);
                        AMT = Cal_LineAmount_Expense(CONO, DIVI, RCNO);
                        THB = WordCurrency(String.format("%.2f", AMT));
                        if (rsl.next()) {
                            //preprint_expense(CONO, DIVI, RCNO, THB, PRINTER);

                            System.out.println("222222222222222222");
                            preprintnewfull_EXP2(CONO, DIVI, RCNO, THB, PRINTER, PATIAL2, FNNO, CHECKBL2);

                        } else {
                            if (HC_TYPE.equalsIgnoreCase("CASH_EXP")) {

                                System.out.println("333333333333333333333");
                                preprintnewfull_EXP(CONO, DIVI, RCNO, THB, PRINTER, PATIAL2, FNNO);
                            } else {

                                System.out.println("4444444444444444444444");
                                preprintnewfull_EXP2(CONO, DIVI, RCNO, THB, PRINTER, PATIAL2, FNNO, CHECKBL2);
                            }
                        }
                    } else if (HC_TYPE.equalsIgnoreCase("CASH_DEPOSIT") || HC_TYPE.equalsIgnoreCase("TRANSFER_DEPOSIT") || HC_TYPE.equalsIgnoreCase("CHECK_DEPOSIT")) {
                        //preprint_DEPOSIT(CONO, DIVI, RCNO, THB, PRINTER);
                        preprint_DEPOSIT(CONO, DIVI, RCNO, THB, PRINTER, PATIAL2, FNNO, CHECKBL2);

                    } else {
                        // preprintnew(CONO, DIVI, RCNO, THB, PRINTER);
                        System.out.println("xxxxxxxooooooooooxxxxxxxxxx");
                        preprintnewfull2(CONO, DIVI, RCNO, THB, PRINTER, PATIAL2, FNNO, CHECKBL2);
                    }
                } else {
                    System.out.println(RCNO + " ไม่มีปริ้นนะ");

                }
                ////
//                if (HC_TYPE.equalsIgnoreCase("TRANSFER_EXP") || HC_TYPE.equalsIgnoreCase("CHECK_EXP")) {
//                    ResultSet rsl = cgd.CheckTypeExpense(CONO, DIVI, RCNO);
//                    AMT = Cal_LineAmount_Expense(CONO, DIVI, RCNO);
//                    THB = WordCurrency(String.format("%.2f", AMT));
//                    if (rsl.next()) {
////                        preprint_expense(CONO, DIVI, RCNO, THB, PRINTER);
//                        System.out.println("55555555555555555555555");
//                        preprintnewfull_EXP2(CONO, DIVI, RCNO, THB, PRINTER, PATIAL2, FNNO, CHECKBL2);
//                    } else {
//
//                        System.out.println("66666666666666666666");
////                        preprint(CONO, DIVI, RCNO, THB, PRINTER);
//                        preprintnewfull_EXP2(CONO, DIVI, RCNO, THB, PRINTER, PATIAL2, FNNO, CHECKBL2);
//                    }
//                } else if (HC_TYPE.equalsIgnoreCase("CASH_DEPOSIT") || HC_TYPE.equalsIgnoreCase("TRANSFER_DEPOSIT") || HC_TYPE.equalsIgnoreCase("CHECK_DEPOSIT")) {
//
//                    System.out.println("777777777777777777777777");
//                    preprint_DEPOSIT(CONO, DIVI, RCNO, THB, PRINTER);
//                } else {
////                    preprintnew(CONO, DIVI, RCNO, THB, PRINTER);
//
//                    System.out.println("88888888888888888888888");
//                    preprintnewfull2(CONO, DIVI, RCNO, THB, PRINTER, PATIAL2, FNNO, CHECKBL2);
//
//////todo doto
//                }
            } catch (Exception ex) {
                Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (request.getParameter("vRCNO") != null && request.getParameter("REPRINT").equalsIgnoreCase("REPRINT")) {

            System.out.println("second PRINT");
            /*
            try {
                System.out.println("REPRINT");
                int CONO = Integer.parseInt(session.getAttribute("cono").toString().trim());
                String DIVI = session.getAttribute("divi").toString().trim();
                String RCNO = request.getParameter("vRCNO").trim();
                String PRINTER = request.getParameter("vPrinter").trim();

                Double AMT = Cal_LineAmount(CONO, DIVI, RCNO);
                String THB = WordCurrency(String.format("%.2f", AMT));
                ClassGetData cgd = new ClassGetData();
                String HC_TYPE = cgd.GET_ReceiptType_Reprint(CONO, DIVI, RCNO);

                ////////
                ResultSet rsl_detail = SelectDB2.Reprint_Detail(String.valueOf(CONO).trim(), DIVI, RCNO);
                String HC_VCNO = "-", RR_COUNT = "-", HC_USER = "-", HC_LOCATION = "-", HC_FIXNO = "-";
                if (rsl_detail.next()) {
                    HC_FIXNO = rsl_detail.getString("HC_FIXNO").trim();
                    RR_COUNT = rsl_detail.getString("RR_COUNT").trim();
                    HC_USER = rsl_detail.getString("HC_USER").trim();
                    HC_LOCATION = rsl_detail.getString("HC_LOCATION").trim();
                }
                if (!RR_COUNT.equalsIgnoreCase("-") && HC_FIXNO.equalsIgnoreCase("-")) {
                    if (HC_TYPE.equalsIgnoreCase("TRANSFER_EXP") || HC_TYPE.equalsIgnoreCase("CHECK_EXP") || HC_TYPE.equalsIgnoreCase("CASH_EXP")) {
                        ResultSet rsl = cgd.CheckTypeExpense(CONO, DIVI, RCNO);
                        AMT = Cal_LineAmount_Expense(CONO, DIVI, RCNO);
                        THB = WordCurrency(String.format("%.2f", AMT));
                        if (rsl.next()) {
                            preprint_expense(CONO, DIVI, RCNO, THB, PRINTER);
                        } else {
                            if (HC_TYPE.equalsIgnoreCase("CASH_EXP")) {
                                preprint_expense(CONO, DIVI, RCNO, THB, PRINTER);
                            } else {
                                preprint(CONO, DIVI, RCNO, THB, PRINTER);
                            }
                        }
                    } else if (HC_TYPE.equalsIgnoreCase("CASH_DEPOSIT") || HC_TYPE.equalsIgnoreCase("TRANSFER_DEPOSIT") || HC_TYPE.equalsIgnoreCase("CHECK_DEPOSIT")) {
                        preprint_DEPOSIT(CONO, DIVI, RCNO, THB, PRINTER);
                    } else {
                        preprint(CONO, DIVI, RCNO, THB, PRINTER);
                    }
                    UpdateDB2.Update_Running_Fix(String.valueOf(CONO), DIVI, RCNO, HC_LOCATION, RR_COUNT);
                    UpdateDB2.Update_Running_Master(String.valueOf(CONO), DIVI, HC_LOCATION, RR_COUNT, HC_USER, RCNO);
//                    System.out.println(RCNO + "  Reprint " + HC_VCNO);
                } else if (!HC_FIXNO.equalsIgnoreCase("-")) {
                    if (HC_TYPE.equalsIgnoreCase("TRANSFER_EXP") || HC_TYPE.equalsIgnoreCase("CHECK_EXP") || HC_TYPE.equalsIgnoreCase("CASH_EXP")) {
                        ResultSet rsl = cgd.CheckTypeExpense(CONO, DIVI, RCNO);
                        AMT = Cal_LineAmount_Expense(CONO, DIVI, RCNO);
                        THB = WordCurrency(String.format("%.2f", AMT));
                        if (rsl.next()) {
                            preprint_expense(CONO, DIVI, RCNO, THB, PRINTER);
                        } else {
                            if (HC_TYPE.equalsIgnoreCase("CASH_EXP")) {
                                preprint_expense(CONO, DIVI, RCNO, THB, PRINTER);
                            } else {
                                preprint(CONO, DIVI, RCNO, THB, PRINTER);
                            }
                        }
                    } else if (HC_TYPE.equalsIgnoreCase("CASH_DEPOSIT") || HC_TYPE.equalsIgnoreCase("TRANSFER_DEPOSIT") || HC_TYPE.equalsIgnoreCase("CHECK_DEPOSIT")) {
                        preprint_DEPOSIT(CONO, DIVI, RCNO, THB, PRINTER);
                    } else {
                        preprint(CONO, DIVI, RCNO, THB, PRINTER);
                    }
                } else {
                    System.out.println(RCNO + " ไม่มีปริ้นนะ");

                }
                ////////
//                if (HC_TYPE.equalsIgnoreCase("TRANSFER_EXP") || HC_TYPE.equalsIgnoreCase("CHECK_EXP")) {
//                    ResultSet rsl = cgd.CheckTypeExpense(CONO, DIVI, RCNO);
//                    AMT = Cal_LineAmount_Expense(CONO, DIVI, RCNO);
//                    THB = WordCurrency(String.format("%.2f", AMT));
//                    if (rsl.next()) {
//                        preprint_expense(CONO, DIVI, RCNO, THB, PRINTER);
//                    } else {
//                        preprint(CONO, DIVI, RCNO, THB, PRINTER);
//                    }
//                } else if (HC_TYPE.equalsIgnoreCase("CASH_DEPOSIT") || HC_TYPE.equalsIgnoreCase("TRANSFER_DEPOSIT") || HC_TYPE.equalsIgnoreCase("CHECK_DEPOSIT")) {
//                    preprint_DEPOSIT(CONO, DIVI, RCNO, THB, PRINTER);
//                } else {
//                    preprint(CONO, DIVI, RCNO, THB, PRINTER);
//                }
            } catch (Exception ex) {
                Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
            }
             */

        } else if (request.getParameter("vRCNO") != null && request.getParameter("REPRINT").equalsIgnoreCase("POSTM3")) {

            System.out.println("THrid PRINT");

            /*
            try {
                String RCNO = request.getParameter("vRCNO").trim();
                int CONO = Integer.parseInt(session.getAttribute("cono").toString().trim());
                String DIVI = session.getAttribute("divi").toString().trim();
                ARS110 AR = new ARS110();
                AR.AUTO_ARS110_KeyBack(CONO, DIVI, RCNO);

            } catch (Exception ex) {
                Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
            }
            
             
        } else if (request.getParameter("vRCNO") != null && request.getParameter("REPRINT").equalsIgnoreCase("EXPENSE")) {

            System.out.println("Forth PRINT");
            
            try {
                ClassGetData cgd = new ClassGetData();
                String RCNO = request.getParameter("vRCNO").trim();
                String TYPEPOST = request.getParameter("TYPEPOST").trim();
                int CONO = Integer.parseInt(session.getAttribute("cono").toString().trim());
                String DIVI = session.getAttribute("divi").toString().trim();
                String PRINTER = request.getParameter("vPrinter").trim();
                Double AMT = Cal_LineAmount_Expense(CONO, DIVI, RCNO);
                String THB = WordCurrency(String.format("%.2f", AMT));

                UpdateDB2 UPDT = new UpdateDB2();
                UPDT.Update_Status(CONO, DIVI, RCNO, "2", "PRINT");

                ARS110 AR = new ARS110();
                AR.AUTO_ARS110_EXPENSE(CONO, DIVI, RCNO, TYPEPOST);

                String USER = session.getAttribute("username").toString().trim();
                String location = session.getAttribute("locations").toString().trim();
                String vcno = "-", Fix_Running = "-", HC_FIXNO = "-";
                ResultSet rsl2 = SelectDB2.CheckVCStepPrint(session.getAttribute("cono").toString().trim(), DIVI, RCNO, USER, location);
                if (rsl2.next()) {
                    vcno = rsl2.getString("HC_VCNO").trim();
                    Fix_Running = rsl2.getString("RR_COUNT").trim();
                    HC_FIXNO = rsl2.getString("HC_FIXNO").trim();

                }
                if (!Fix_Running.equalsIgnoreCase("-") && HC_FIXNO.equalsIgnoreCase("-")) {
                    ResultSet rsl = cgd.CheckTypeExpense(CONO, DIVI, RCNO);
                    if (rsl.next()) {
                        preprint_expense(CONO, DIVI, RCNO, THB, PRINTER);
                    } else {
                        preprint(CONO, DIVI, RCNO, THB, PRINTER);
                    }
                    UpdateDB2.Update_Running_Fix(String.valueOf(CONO), DIVI, RCNO, location, Fix_Running);
                    UpdateDB2.Update_Running_Master(String.valueOf(CONO), DIVI, location, Fix_Running, USER, RCNO);
                    System.out.println(vcno);
                }
//                ResultSet rsl = cgd.CheckTypeExpense(CONO, DIVI, RCNO);
//                if (rsl.next()) {
//                    preprint_expense(CONO, DIVI, RCNO, THB, PRINTER);
//                } else {
//                    preprint(CONO, DIVI, RCNO, THB, PRINTER);
//                }

            } catch (Exception ex) {
                Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
            }
             */
        } else if (request.getParameter("vFNCODE") != null && request.getParameter("REPRINT").equalsIgnoreCase("CLEAR_EXPENSE")) {

            System.out.println("FIFTH PRINT");

            try {

                String CONO1 = session.getAttribute("cono").toString();
                String FNNO = request.getParameter("vFNCODE").trim();
                ArrayList<String> arrRC = new ArrayList<>();
                int CONO = Integer.parseInt(session.getAttribute("cono").toString().trim());
                String DIVI = session.getAttribute("divi").toString().trim();
                JSONArray responseRC = SelectDB2.getrctoddl(CONO1, DIVI, FNNO);

                for (int i = 0; i < responseRC.length(); i++) {
                    JSONObject obj = responseRC.getJSONObject(i);
                    String RCNO = obj.getString("H_RCNO");

                    arrRC.add(RCNO);

                    System.out.println(RCNO);
                }
                ARS110 AR = new ARS110();
                ARS110.AUTO_ARS110_CLEAR_EXPENSEnew(CONO, DIVI, arrRC, FNNO);

            } catch (Exception ex) {
                Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (request.getParameter("vFNCODE") != null && request.getParameter("REPRINT").equalsIgnoreCase("DEPOSIT")) {

            System.out.println("SIXTH PRINT");

            //todo deposit  
            try {
                System.out.println("DEPOSIT PRINT");

                ArrayList<String> arrRC = new ArrayList<>();
                String CONO1 = session.getAttribute("cono").toString();
                int CONO = Integer.parseInt(session.getAttribute("cono").toString().trim());
                String FNNO = request.getParameter("vFNCODE").trim();
                String DIVI = session.getAttribute("divi").toString().trim();
                String PRINTER = request.getParameter("vPrinter").trim();
                Double AMT = Cal_LineAmountnewBANK(CONO, DIVI, FNNO);
                String Str_AMT = String.format("%.2f", AMT);
                String THB = WordCurrency(Str_AMT);
                String aa = request.getParameter("vCHECKBL");

                System.out.println("DEPOSIT PART");
                System.out.println("----------------------------------");
                System.out.println(CONO1);
                System.out.println(CONO);
                System.out.println(FNNO);
                System.out.println(DIVI);
                System.out.println(PRINTER);
                System.out.println(AMT);
                System.out.println(Str_AMT);
                System.out.println(THB);
                System.out.println(aa);
                System.out.println("----------------------------------");

                // todo check 
                
                JSONArray responseRC = SelectDB2.getrctoddl(CONO1, DIVI, FNNO);

                System.out.println(responseRC);
                UpdateDB2 UPDT = new UpdateDB2();

                for (int i = 0; i < responseRC.length(); i++) {
                    JSONObject obj = responseRC.getJSONObject(i);
                    String RCNO = obj.getString("H_RCNO");

                    arrRC.add(RCNO);

                    System.out.println(RCNO);
                    UPDT.Update_Status(CONO, DIVI, RCNO, "4", "PRINT");
                }

                System.out.println(arrRC);

                ARS110 AR = new ARS110();
                AR.AUTO_ARS110_DEPOSITnew(CONO, DIVI, arrRC, FNNO);

                Boolean overPay = false;
                Boolean bankCH = false;
                Boolean checkLAST = false;
                Boolean CHECKBL = false;

                if (aa != null) {

                    CHECKBL = true;
                }

//                String PATIAL = request.getParameter("vPATIAL");
                boolean CHECKBL2 = false;
                if (CHECKBL != null) {

                    CHECKBL2 = true;
                }

                System.out.println("arrRC SIZE() : " + arrRC.size());
                for (int counter = 0; counter < arrRC.size(); counter++) {

                    System.out.println("arrRC SIZE() : " + arrRC.size());
                    System.out.println("counter : " + counter);

                    if (counter == arrRC.size() - 1) {

                        overPay = true;
                        bankCH = true;
                        checkLAST = false;

                    }

                    if (arrRC.size() == 1) {

                        checkLAST = false;
                    }

                    String USER = session.getAttribute("username").toString().trim();
                    String location = session.getAttribute("locations").toString().trim();
                    String vcno = "-", Fix_Running = "-";
                    ResultSet rsl = SelectDB2.CheckVCStepPrint(session.getAttribute("cono").toString().trim(), DIVI, arrRC.get(counter), USER, location);

                    System.out.println("****************");
                    System.out.println(CONO);
                    System.out.println("****************");
                    System.out.println(DIVI);
                    System.out.println(FNNO);
                    System.out.println(PRINTER);
                    System.out.println(USER);
                    System.out.println(location);
                    System.out.println(vcno);
                    System.out.println(Fix_Running);
                    System.out.println(AMT);
                    System.out.println(Str_AMT);
                    System.out.println(THB);
                    System.out.println(CHECKBL);
                    System.out.println(CHECKBL2);

                    System.out.println(overPay);
                    System.out.println(bankCH);
                    System.out.println(checkLAST);
                    
                    System.out.println(arrRC.get(counter));

                    if (rsl.next()) {
                        vcno = rsl.getString("HC_VCNO").trim();
                        Fix_Running = rsl.getString("RR_COUNT").trim();
                    }
                    if (!Fix_Running.equalsIgnoreCase("-")) {
                        if (!PRINTER.equalsIgnoreCase("RECEIPT PRINTER3")) {
                            //       preprint_DEPOSIT(CONO, DIVI, RCNO, THB, PRINTER);
                            preprint_DEPOSIT(CONO, DIVI, arrRC.get(counter), THB, PRINTER, checkLAST, FNNO, CHECKBL2);

                        }
                        UpdateDB2.Update_Running_Fix(String.valueOf(CONO), DIVI, arrRC.get(counter), location, Fix_Running);
                        UpdateDB2.Update_Running_Master(String.valueOf(CONO), DIVI, location, Fix_Running, USER, arrRC.get(counter));
                        System.out.println(arrRC.get(counter) + "   " + vcno);
                    } else {
                        System.out.println(arrRC.get(counter) + " ไม่มีปริ้นนะ");
                    }

                }
                
                 // todo check 

                /*
                ARS110 AR = new ARS110();
                AR.AUTO_ARS110_DEPOSIT(CONO, DIVI, RCNO);

                String USER = session.getAttribute("username").toString().trim();
                String location = session.getAttribute("locations").toString().trim();
                String vcno = "-", Fix_Running = "-";
                ResultSet rsl = SelectDB2.CheckVCStepPrint(session.getAttribute("cono").toString().trim(), DIVI, RCNO, USER, location);
                if (rsl.next()) {
                    vcno = rsl.getString("HC_VCNO").trim();
                    Fix_Running = rsl.getString("RR_COUNT").trim();
                }
                if (!Fix_Running.equalsIgnoreCase("-")) {
                    if (!PRINTER.equalsIgnoreCase("RECEIPT PRINTER3")) {
                 //       preprint_DEPOSIT(CONO, DIVI, RCNO, THB, PRINTER);
                           preprint_DEPOSIT(CONO, DIVI, RCNO, THB, PRINTER, PATIAL2, FNNO, CHECKBL2);

                    }
                    UpdateDB2.Update_Running_Fix(String.valueOf(CONO), DIVI, RCNO, location, Fix_Running);
                    UpdateDB2.Update_Running_Master(String.valueOf(CONO), DIVI, location, Fix_Running, USER, RCNO);
                    System.out.println(RCNO + "   " + vcno);
                } else {
                    System.out.println(RCNO + " ไม่มีปริ้นนะ");
                }
                
                 */
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }

        } else if (request.getParameter("REPRINT").equalsIgnoreCase("NONE") && request.getParameter("vFNCODE") != null) {
            System.out.println("SEVEN PRINT");

            try {
                System.out.println("SEVEN PRINT");

                String CONO1 = session.getAttribute("cono").toString();
                int CONO = Integer.parseInt(session.getAttribute("cono").toString().trim());
                String DIVI = session.getAttribute("divi").toString().trim();
                String FNNO = request.getParameter("vFNCODE").trim();
                ArrayList<String> arrRC = new ArrayList<>();
                String PRINTER = request.getParameter("vPrinter").trim();
                Double AMT = Cal_LineAmountnewBANK(CONO, DIVI, FNNO);
                String Balance = request.getParameter("vBalance").trim();

                String Str_AMT = String.format("%.2f", AMT);
                String THB = WordCurrency(Str_AMT);

                String USER = session.getAttribute("username").toString().trim();
                String location = session.getAttribute("locations").toString().trim();
                String vcno = "-", Fix_Running = "-";

                System.out.println("****************");
                System.out.println(CONO);
                System.out.println("****************");
                System.out.println(DIVI);
                System.out.println(FNNO);
                System.out.println(PRINTER);
                System.out.println(USER);
                System.out.println(location);
                System.out.println(vcno);
                System.out.println(Fix_Running);
                System.out.println(AMT);
                System.out.println(Str_AMT);
                System.out.println(THB);

                String aa = request.getParameter("vCHECKBL");
                System.out.println(aa);

//                var CHECKBL = document.getElementById("vCHECKBL").checked ;
//            var url = " Report?vRCNO=" + vRCNO + "&last=TRUE&checkbl="+CHECKBL+"&vReport=RP013";
                JSONArray responseRC = SelectDB2.getrctoddl(CONO1, DIVI, FNNO);

                System.out.println(responseRC);

                for (int i = 0; i < responseRC.length(); i++) {
                    JSONObject obj = responseRC.getJSONObject(i);
                    String RCNO = obj.getString("H_RCNO");

                    arrRC.add(RCNO);

                    //   UpdateDB2.Update_Status(CONO, DIVI, RCNO, "2", "PRINT");
                    System.out.println(RCNO);
                }

                ARS110 AR = new ARS110();
                ARS110.AUTO_ARS110new(CONO, DIVI, arrRC, FNNO);

                Boolean overPay = false;
                Boolean bankCH = false;
                Boolean checkLAST = false;
                Boolean CHECKBL = false;

                if (aa != null) {

                    CHECKBL = true;
                }

                System.out.println("arrRC SIZE() : " + arrRC.size());
                for (int counter = 0; counter < arrRC.size(); counter++) {

                    System.out.println("arrRC SIZE() : " + arrRC.size());
                    System.out.println("counter : " + counter);

                    if (counter == arrRC.size() - 1) {

                        overPay = true;
                        bankCH = true;
                        checkLAST = false;

                    }

                    if (arrRC.size() == 1) {

                        checkLAST = false;
                    }

                    //?????
                    System.out.println("?????????????????????????????");

                    System.out.println(session.getAttribute("cono").toString().trim());
                    System.out.println(DIVI);
                    System.out.println(arrRC.get(counter));
                    System.out.println(USER);
                    System.out.println(location);

                    System.out.println(CHECKBL);
                    System.out.println("??????ccccvvvvv");

                    System.out.println("?????????????????????????????");

                    ResultSet rsl = SelectDB2.CheckVCStepPrint(session.getAttribute("cono").toString().trim(), DIVI, arrRC.get(counter), USER, location);

                    if (rsl.next()) {
                        vcno = rsl.getString("HC_VCNO").trim();
                        Fix_Running = rsl.getString("RR_COUNT").trim();

                        System.out.println("vccccccccccccccccccccccc)");
                        System.out.println(vcno);
                    }
                    if (!Fix_Running.equalsIgnoreCase("-")) {
                        if (!PRINTER.equalsIgnoreCase("RECEIPT PRINTER3")) {
//                            preprint(CONO, DIVI, arrRC.get(counter), THB, PRINTER,checkLAST);
                            preprintnewfull2(CONO, DIVI, arrRC.get(counter), THB, PRINTER, checkLAST, FNNO, CHECKBL);

                            System.out.println("-------------------------------");
                            System.out.println(CONO);
                            System.out.println(DIVI);
                            System.out.println(arrRC.get(counter));
                            System.out.println(THB);
                            System.out.println(PRINTER);
                            System.out.println(checkLAST);
                            System.out.println(FNNO);

                            System.out.println("-------------------------------");

                        }
                        UpdateDB2.Update_Running_Fix(String.valueOf(CONO), DIVI, arrRC.get(counter), location, Fix_Running);
                        UpdateDB2.Update_Running_Master(String.valueOf(CONO), DIVI, location, Fix_Running, USER, arrRC.get(counter));
                        System.out.println(arrRC.get(counter) + "   " + vcno);
                    } else {
                        System.out.println(arrRC.get(counter) + " ไม่มีปริ้นนะ");
                    }

                }

            } catch (Exception ex) {
                System.out.println(ex.toString());
            }

        } else if (request.getParameter("vFNCODE") != null && request.getParameter("REPRINT").equalsIgnoreCase("POSTM3")) {

            System.out.println("Eight PRINT");

            try {
//                String RCNO = request.getParameter("vRCNO").trim();
                String CONO1 = session.getAttribute("cono").toString();
                String FNNO = request.getParameter("vFNCODE").trim();
                int CONO = Integer.parseInt(session.getAttribute("cono").toString().trim());
                ArrayList<String> arrRC = new ArrayList<>();
                String DIVI = session.getAttribute("divi").toString().trim();

                System.out.println(FNNO);
                System.out.println(CONO);
                System.out.println(DIVI);

                JSONArray responseRC = SelectDB2.getrctoddl2keyback(CONO1, DIVI, FNNO);

                System.out.println(responseRC);

                for (int i = 0; i < responseRC.length(); i++) {
                    JSONObject obj = responseRC.getJSONObject(i);
                    String RCNO = obj.getString("H_RCNO");

                    arrRC.add(RCNO);

                    System.out.println(RCNO);
                }

                System.out.println(arrRC);

                ARS110 AR = new ARS110();
                ARS110.AUTO_ARS110_KeyBacknew(CONO, DIVI, arrRC, FNNO);
            } catch (Exception ex) {
                Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (request.getParameter("vFNCODE") != null && request.getParameter("REPRINT").equalsIgnoreCase("EXPENSE")) {

            System.out.println("NINE PRINT1");

            try {

                String CONO1 = session.getAttribute("cono").toString();
                ClassGetData cgd = new ClassGetData();
//                String RCNO = request.getParameter("vRCNO").trim();
                String TYPEPOST = request.getParameter("TYPEPOST").trim();
                int CONO = Integer.parseInt(session.getAttribute("cono").toString().trim());
                String DIVI = session.getAttribute("divi").toString().trim();
                String FNNO = request.getParameter("vFNCODE").trim();
                String PRINTER = request.getParameter("vPrinter").trim();
                ArrayList<String> arrRC = new ArrayList<>();

                System.out.println("------------------------------");

                JSONArray responseRC = SelectDB2.getrctoddl(CONO1, DIVI, FNNO);
                UpdateDB2 UPDT = new UpdateDB2();
                System.out.println(responseRC);

                String aa = request.getParameter("vCHECKBL");
                System.out.println(aa);

                for (int i = 0; i < responseRC.length(); i++) {
                    JSONObject obj = responseRC.getJSONObject(i);
                    String RCNO = obj.getString("H_RCNO");

                    arrRC.add(RCNO);
                    UPDT.Update_Status(CONO, DIVI, RCNO, "4", "PRINT");

                    System.out.println(RCNO);
                }

                System.out.println(CONO1);
                System.out.println(arrRC.size());
                System.out.println(TYPEPOST);
                System.out.println(CONO);
                System.out.println(DIVI);
                System.out.println(PRINTER);

                Double AMT = Cal_LineAmountnewBANK(CONO, DIVI, FNNO);
                String THB = WordCurrency(String.format("%.2f", AMT));

                System.out.println(THB);

                System.out.println("--------------before access to m3----------------");
                System.out.println(CONO);
                System.out.println(DIVI);
                System.out.println(arrRC);
                System.out.println(TYPEPOST);
                System.out.println(FNNO);
                System.out.println("-------------------------------------------------");

                ARS110 AR = new ARS110();

                ARS110.AUTO_ARS110_EXPENSEnew(CONO, DIVI, arrRC, TYPEPOST, FNNO);
                Boolean overPay = true;
                Boolean bankCH = true;
                Boolean checkLAST = true;
                Boolean checkPATIAL = false;
                Boolean CHECKBL = false;

                if (aa != null) {

                    CHECKBL = true;
                }

                System.out.println("arrRC SIZE() : " + arrRC.size());
//                 ResultSet rslchkacc = SelectDB2.CheckAccode(CONO1, DIVI, FNNO, THB, CONO1);
                for (int counter = 0; counter < arrRC.size(); counter++) {

                    System.out.println("arrRC SIZE() : " + arrRC.size());
                    System.out.println("counter : " + counter);

                    if (counter == arrRC.size() - 1) {

                        overPay = true;
                        bankCH = true;
                        checkLAST = true;

                    }

                    if (arrRC.size() == 1) {

                        checkLAST = true;
                    }

                    String USER = session.getAttribute("username").toString().trim();
                    String location = session.getAttribute("locations").toString().trim();
                    String vcno = "-", Fix_Running = "-", HC_FIXNO = "-";
                    ResultSet rsl2 = SelectDB2.CheckVCStepPrint(session.getAttribute("cono").toString().trim(), DIVI, arrRC.get(counter), USER, location);

                    if (rsl2.next()) {
                        vcno = rsl2.getString("HC_VCNO").trim();
                        Fix_Running = rsl2.getString("RR_COUNT").trim();
                        HC_FIXNO = rsl2.getString("HC_FIXNO").trim();

                    }

                    if (!Fix_Running.equalsIgnoreCase("-") && HC_FIXNO.equalsIgnoreCase("-")) {
                        ResultSet rsl = cgd.CheckTypeExpensenew(CONO, DIVI, FNNO);
                        if (rsl.next()) {
//                        preprint_expense(CONO, DIVI, RCNO, THB, PRINTER);

                            if (TYPEPOST.equalsIgnoreCase("Partial")) {
                                preprintnewfull_EXP2(CONO, DIVI, arrRC.get(counter), THB, PRINTER, true, FNNO, CHECKBL);

                            } else {
                                preprintnewfull_EXP2(CONO, DIVI, arrRC.get(counter), THB, PRINTER, false, FNNO, CHECKBL);

                            }

                        } else {
                            if (TYPEPOST.equalsIgnoreCase("Partial")) {
                                preprintnewfull_EXP2(CONO, DIVI, arrRC.get(counter), THB, PRINTER, true, FNNO, CHECKBL);

                            } else {
                                preprintnewfull_EXP2(CONO, DIVI, arrRC.get(counter), THB, PRINTER, false, FNNO, CHECKBL);

                            }
//                        preprint(CONO, DIVI, RCNO, THB, PRINTER);
                        }

                        UpdateDB2.Update_Running_Fix(String.valueOf(CONO), DIVI, arrRC.get(counter), location, Fix_Running);
                        UpdateDB2.Update_Running_Master(String.valueOf(CONO), DIVI, location, Fix_Running, USER, arrRC.get(counter));
                        System.out.println(vcno);
                    }

                }
            } catch (Exception ex) {
                Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            System.out.println("sssss");
        }

        System.out.println("END");

    }

    public static Double Cal_LineAmount(int CONO, String DIVI, String FNNO) {
        Double Amt = 0.00;
        String RCSET = "";
        int CountR = 0;
        try {
            Connection conn = ConnectDB2.ConnectionDB();
            ClassFormatData cfd = new ClassFormatData();
            if (conn != null) {

                Statement stmt2 = conn.createStatement();
                String query2 = "SELECT COUNT(H_RCNO) FROM " + DBPRD + ".HEAD_RECIPT hr\n"
                        + "WHERE  H_RNNO  = '230000054' \n"
                        + "AND H_CONO  = '10'\n"
                        + "AND H_DIVI  = '101'\n"
                        + "AND H_STS  = '1' AND H_CUNO != 'HEAD' ";
                System.out.println(query2);

                ResultSet mRes2 = stmt2.executeQuery(query2);

                int row = 0;

                while (mRes2.next()) {

                    CountR = mRes2.getInt(1);
                }

                Statement stmt1 = conn.createStatement();
                String query1 = "SELECT H_RCNO FROM " + DBPRD + ".HEAD_RECIPT hr\n"
                        + "WHERE  H_RNNO  = '" + FNNO + "' \n"
                        + "AND H_CONO  = '" + CONO + "'\n"
                        + "AND H_DIVI  = '" + DIVI + "'\n"
                        + "AND H_STS  = '1' AND H_CUNO != 'HEAD' ";
                System.out.println(query1);

                System.out.println("aaaaaaaaaaaaaaaaaaaa");

                ResultSet mRes1 = stmt1.executeQuery(query1);

                while (mRes1.next()) {

                    row++;
                    RCSET += "'" + mRes1.getString(1) + "'";
                    if (row <= CountR - 1) {
                        RCSET += ",";
                    }
                }
                System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxx");
                System.out.println(RCSET);
                System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxx");
                Statement stmt = conn.createStatement();
                String query = "SELECT  SUM(LR_REAMT) AS  LR_INVAMT\n"
                        + "FROM " + DBPRD + ".LR_LINERECEIPT\n"
                        + "WHERE LR_CONO = '" + CONO + "'\n"
                        + "AND LR_DIVI = '" + DIVI + "'\n"
                        + "AND LR_RCNO IN  ( " + RCSET + ")";
                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);
                while (mRes.next()) {
                    Amt += mRes.getDouble(1);
                }
                Amt = cfd.Double2digitReturn(Amt);
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        return Amt;
    }

    public static Double Cal_LineAmountnewBANK(int CONO, String DIVI, String FNNO) {
        Double Amt = 0.00;
        String RCSET = "";
        JSONArray RCNOtxt = new JSONArray();
        try {
            Connection conn = ConnectDB2.ConnectionDB();
            ClassFormatData cfd = new ClassFormatData();
            if (conn != null) {
                Statement stmt0 = conn.createStatement();
                String query0 = "SELECT  H_RCNO  FROM  " + DBPRD + ".HEAD_RECIPT hr  \n"
                        + "WHERE  H_CUNO  != 'HEAD'\n"
                        + "AND H_RNNO  = '" + FNNO + "'";
                ResultSet mRes0 = stmt0.executeQuery(query0);

                System.out.println(query0);
                while (mRes0.next()) {

                    RCNOtxt.put(mRes0.getString("H_RCNO"));
                }

                for (int i = 0; i < RCNOtxt.length(); i++) {

                    RCSET += "'" + RCNOtxt.get(i) + "'";
                    if (i != RCNOtxt.length() - 1) {
                        RCSET += ",";
                    }
                }

                Statement stmt = conn.createStatement();
                String query = "SELECT SUM(LR_REAMT) AS  LR_INVAMT\n"
                        + "FROM " + DBPRD + ".LR_LINERECEIPT\n"
                        + "WHERE LR_CONO = '" + CONO + "'\n"
                        + "AND LR_DIVI = '" + DIVI.trim() + "'\n"
                        + "AND LR_RCNO IN (" + RCSET.trim() + ")";
                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);
                while (mRes.next()) {
                    Amt += mRes.getDouble(1);
                }
                Amt = cfd.Double2digitReturn(Amt);
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        return Amt;
    }

    public static Double Cal_LineAmountnew(int CONO, String DIVI, String RCNO) {
        Double Amt = 0.00;
        try {
            Connection conn = ConnectDB2.ConnectionDB();
            ClassFormatData cfd = new ClassFormatData();
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "SELECT SUM(LR_REAMT) AS  LR_INVAMT\n"
                        + "FROM " + DBPRD + ".LR_LINERECEIPT\n"
                        + "WHERE LR_CONO = '" + CONO + "'\n"
                        + "AND LR_DIVI = '" + DIVI.trim() + "'\n"
                        + "AND LR_RCNO = '" + RCNO.trim() + "'";
//                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);
                while (mRes.next()) {
                    Amt += mRes.getDouble(1);
                }
                Amt = cfd.Double2digitReturn(Amt);
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        return Amt;
    }

    public static Double Cal_LineAmount_Expense(int CONO, String DIVI, String RCNO) {
        Double Amt = 0.00;
        try {
            Connection conn = ConnectDB2.ConnectionDB();
            if (conn != null) {
                Statement stmt = conn.createStatement();
//                String query = "SELECT HC_REAMT\n"
//                        + "FROM BRLDTA0100.HR_RECEIPT\n"
//                        + "WHERE HR_CONO = '" + CONO + "'\n"
//                        + "AND HR_DIVI = '" + DIVI.trim() + "'\n"
//                        + "AND HC_RCNO = '" + RCNO.trim() + "'";

                String query = "SELECT SUM(LR_INVAMT) AS LR_INVAMT\n"
                        + "FROM " + DBPRD + ".LR_LINERECEIPT\n"
                        + "WHERE LR_CONO = '" + CONO + "'\n"
                        + "AND LR_DIVI = '" + DIVI.trim() + "'\n"
                        + "AND LR_RCNO = '" + RCNO.trim() + "'";

                ResultSet mRes = stmt.executeQuery(query);
                while (mRes.next()) {
                    Amt += mRes.getDouble(1);
                }

            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        return Amt;
    }

    public static String WordCurrency(String Money) {
        DecimalFormat df = new DecimalFormat("#.00");
        String bathTxt, n, bathTH = "";
        Double amount;

        bathTxt = Money.replaceAll("-", "");
        amount = Double.parseDouble(Money.replaceAll("-", ""));

        bathTxt = df.format(amount);
        String[] num = {"ศูนย์", "หนึ่ง", "สอง", "สาม", "สี่", "ห้า", "หก", "เจ็ด", "แปด", "เก้า", "สิบ"};
        String[] number = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] rank = {"", "สิบ", "ร้อย", "พัน", "หมื่น", "แสน", "ล้าน"};
        String[] temp = bathTxt.split("[.]");
        String intVal = temp[0];
        String deciVal = temp[1];
        String strTrillion = "";
        Boolean _IsTrillion = intVal.length() > 7;
        int check = 0;
        if (_IsTrillion) {
            strTrillion = intVal.substring(0, intVal.length() - 6);
            bathTH = WordCurrency2(strTrillion) + "ล้าน";
            intVal = intVal.substring(strTrillion.length());
            check++;
        }

        if (Double.parseDouble(bathTxt) == 0) {
            bathTH = "ศูนย์บาท";
        } else {
            try {
                for (int i = 0; i < intVal.length(); i++) {
                    n = intVal.substring(i, i + 1);
                    if (Integer.parseInt(n) != 0) {
                        if ((i == (intVal.length() - 1)) && (n.indexOf("1") > -1) && intVal.length() > 1) {
                            bathTH += "เอ็ด";
                        } else if ((i == (intVal.length() - 2)) && (n.indexOf("2") > -1)) {
                            bathTH += "ยี่";
                        } else if ((i == (intVal.length() - 2)) && (n.indexOf("1") > -1)) {
                            bathTH += "";
                        } else {
                            bathTH += num[Integer.parseInt(n)];
                        }
                        bathTH += rank[(intVal.length() - i) - 1];
                    } else if (i == 0) {
                        bathTH += num[Integer.parseInt(n)];
                    }

                }

            } catch (Exception e) {
                System.out.println(e);
            }

            if (deciVal.length() > 0 && Integer.parseInt(deciVal) != 0) {
                bathTH += "บาท";
                for (int i = 0; i < deciVal.length(); i++) {
//                    System.out.print(deciVal.substring(0 + i, 1 + i));
                    n = deciVal.substring(i, i + 1);
                    if (Integer.parseInt(n) != 0) {
                        if ((i == (deciVal.length() - 1)) && (n.indexOf("1") > -1) && deciVal.length() > 1) {
                            if (!deciVal.substring(0, 0 + 1).equalsIgnoreCase("0")) {
                                bathTH += "เอ็ด";
                            } else {
                                bathTH += num[Integer.parseInt(n)];
                            }
                        } else if ((i == (deciVal.length() - 2)) && (n.indexOf("2") > -1)) {
                            bathTH += "ยี่";
                        } else if ((i == (deciVal.length() - 2)) && (n.indexOf("1") > -1)) {
                            bathTH += "";
                        } else {
                            bathTH += num[Integer.parseInt(n)];
                        }
                        bathTH += rank[(deciVal.length() - i) - 1];
                    }
                }
                bathTH += "สตางค์";
            } else {
                bathTH += "บาท";
            }

        }
        return bathTH;

    }

    public static String WordCurrency2(String Money) {
        DecimalFormat df = new DecimalFormat("#.00");
        String bathTxt, n, bathTH = "";
        Double amount;
        bathTxt = Money.replaceAll("-", "");
        amount = Double.parseDouble(Money.replaceAll("-", ""));

        bathTxt = df.format(amount);
        String[] num = {"ศูนย์", "หนึ่ง", "สอง", "สาม", "สี่", "ห้า", "หก", "เจ็ด", "แปด", "เก้า", "สิบ"};
        String[] number = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String[] rank = {"", "สิบ", "ร้อย", "พัน", "หมื่น", "แสน", "ล้าน"};
        String[] temp = bathTxt.split("[.]");
        String intVal = temp[0];
        String deciVal = temp[1];
        if (Double.parseDouble(bathTxt) == 0) {
            bathTH = "ศูนย์บาท";
        } else {
            try {
                for (int i = 0; i < intVal.length(); i++) {
                    n = intVal.substring(i, i + 1);
                    if (Integer.parseInt(n) != 0) {
                        if ((i == (intVal.length() - 1)) && (n.indexOf("1") > -1) && intVal.length() > 1) {
                            bathTH += "เอ็ด";
                        } else if ((i == (intVal.length() - 2)) && (n.indexOf("2") > -1)) {
                            bathTH += "ยี่";
                        } else if ((i == (intVal.length() - 2)) && (n.indexOf("1") > -1)) {
                            bathTH += "";
                        } else {
                            bathTH += num[Integer.parseInt(n)];
                        }
                        bathTH += rank[(intVal.length() - i) - 1];
                    } else if (i == 0) {
                        bathTH += num[Integer.parseInt(n)];
                    }

                }
            } catch (Exception e) {
                System.out.println(e);
            }

        }
        return bathTH;

    }

    public void preprint(int CONO, String DIVI, String RCNO, String THB, String PRINTER, Boolean checkLAST) {

        String report, printer;
        File reportFile = new File(getServletContext().getRealPath("Report/EPRC_0012.jrxml"));
//        printer = "RECEIPT PRINTER";
        printer = PRINTER.replaceAll("_", " ");
        System.out.println(printer);

        try {
            Connection conn = ConnectDB2.ConnectionDB();
            try {
                // Parameters
                Map parameterss = new HashMap();
                parameterss.put("CONO", CONO);
                parameterss.put("DIVI", DIVI);
                parameterss.put("RCNO", RCNO);
                parameterss.put("THB", THB);
                parameterss.put("checkLAST", checkLAST);

                System.out.println(CONO);
                System.out.println(DIVI);
                System.out.println(RCNO);
                System.out.println(THB);
                System.out.println(checkLAST);

                try {

                    String jcm = JasperCompileManager.compileReportToFile(reportFile.getPath());
                    JasperPrint print = JasperFillManager.fillReport(jcm, parameterss, conn);
                    PrinterJob printerJob = PrinterJob.getPrinterJob();
                    PageFormat pageFormat = PrinterJob.getPrinterJob().defaultPage();
                    printerJob.defaultPage(pageFormat);
                    int selectedService = 0;
                    AttributeSet attributeSet = new HashPrintServiceAttributeSet(new PrinterName(printer, null));
                    PrintService[] printService = PrintServiceLookup.lookupPrintServices(null, attributeSet);
                    System.out.println("END1");
                    try {
                        System.out.println(selectedService);

                        System.out.println(printService);
                        printerJob.setPrintService(printService[selectedService]);

                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    JRPrintServiceExporter exporter;
                    PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
//                    printRequestAttributeSet.add(MediaSizeName.ISO_A4);
                    printRequestAttributeSet.add(new Copies(1));

                    System.out.println("END");
                    // these are deprecated
                    exporter = new JRPrintServiceExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, printService[selectedService]);
                    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printService[selectedService].getAttributes());
                    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
                    exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
                    exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
                    exporter.exportReport();

                } catch (JRException e) {
                    e.printStackTrace();
                }
/////

/////
                conn.close();

            } catch (Exception ex) {
                ex.printStackTrace();

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void preprintnewfull2(int CONO, String DIVI, String RCNO, String THB, String PRINTER, Boolean checkLAST, String FNNO, Boolean CHECKBL) {

        System.out.println(CONO);
        System.out.println(DIVI);
        System.out.println(RCNO);
        System.out.println(THB);
        System.out.println(PRINTER);

        String report, printer;
        File reportFile = new File(getServletContext().getRealPath("Report/EPRC_0013.jrxml"));
//        printer = "RECEIPT PRINTER";
        printer = PRINTER.replaceAll("_", " ");
        System.out.println(printer);
        System.out.println(reportFile);

        try {
            Connection conn = ConnectDB2.ConnectionDB();
            try {
                // Parameters
                Map parameterss = new HashMap();
                parameterss.put("CONO", CONO);
                parameterss.put("DIVI", DIVI);
                parameterss.put("RCNO", RCNO);
                parameterss.put("THB", THB);
                parameterss.put("FNNO", FNNO);
                parameterss.put("LAST", checkLAST);
                parameterss.put("CHECKBL", CHECKBL);

                System.out.println(CONO);
                System.out.println(DIVI);
                System.out.println(RCNO);
                System.out.println(THB);

                try {

                    String jcm = JasperCompileManager.compileReportToFile(reportFile.getPath());
                    JasperPrint print = JasperFillManager.fillReport(jcm, parameterss, conn);
                    PrinterJob printerJob = PrinterJob.getPrinterJob();
                    PageFormat pageFormat = PrinterJob.getPrinterJob().defaultPage();
                    printerJob.defaultPage(pageFormat);
                    int selectedService = 0;
                    AttributeSet attributeSet = new HashPrintServiceAttributeSet(new PrinterName(printer, null));
                    PrintService[] printService = PrintServiceLookup.lookupPrintServices(null, attributeSet);
                    System.out.println("END1");
                    try {
                        System.out.println(selectedService);

                        System.out.println(printService);
                        printerJob.setPrintService(printService[selectedService]);

                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    JRPrintServiceExporter exporter;
                    PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
//                    printRequestAttributeSet.add(MediaSizeName.ISO_A4);
                    printRequestAttributeSet.add(new Copies(1));

                    System.out.println("END");
                    // these are deprecated
                    exporter = new JRPrintServiceExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, printService[selectedService]);
                    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printService[selectedService].getAttributes());
                    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
                    exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
                    exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
                    exporter.exportReport();

                } catch (JRException e) {
                    e.printStackTrace();
                }
/////

/////
                conn.close();

            } catch (Exception ex) {
                ex.printStackTrace();

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void preprintnewfull(int CONO, String DIVI, String RCNO, String THB, String PRINTER, Boolean checkLAST, String FNNO) {

        System.out.println(CONO);
        System.out.println(DIVI);
        System.out.println(RCNO);
        System.out.println(THB);
        System.out.println(PRINTER);

        String report, printer;
        File reportFile = new File(getServletContext().getRealPath("Report/EPRC_0013.jrxml"));
//        printer = "RECEIPT PRINTER";
        printer = PRINTER.replaceAll("_", " ");
        System.out.println(printer);
        System.out.println(reportFile);

        try {
            Connection conn = ConnectDB2.ConnectionDB();
            try {
                // Parameters
                Map parameterss = new HashMap();
                parameterss.put("CONO", CONO);
                parameterss.put("DIVI", DIVI);
                parameterss.put("RCNO", RCNO);
                parameterss.put("THB", THB);
                parameterss.put("FNNO", FNNO);
                parameterss.put("LAST", checkLAST);

                System.out.println(CONO);
                System.out.println(DIVI);
                System.out.println(RCNO);
                System.out.println(THB);

                try {

                    String jcm = JasperCompileManager.compileReportToFile(reportFile.getPath());
                    JasperPrint print = JasperFillManager.fillReport(jcm, parameterss, conn);
                    PrinterJob printerJob = PrinterJob.getPrinterJob();
                    PageFormat pageFormat = PrinterJob.getPrinterJob().defaultPage();
                    printerJob.defaultPage(pageFormat);
                    int selectedService = 0;
                    AttributeSet attributeSet = new HashPrintServiceAttributeSet(new PrinterName(printer, null));
                    PrintService[] printService = PrintServiceLookup.lookupPrintServices(null, attributeSet);
                    System.out.println("END1");
                    try {
                        System.out.println(selectedService);

                        System.out.println(printService);
                        printerJob.setPrintService(printService[selectedService]);

                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    JRPrintServiceExporter exporter;
                    PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
//                    printRequestAttributeSet.add(MediaSizeName.ISO_A4);
                    printRequestAttributeSet.add(new Copies(1));

                    System.out.println("END");
                    // these are deprecated
                    exporter = new JRPrintServiceExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, printService[selectedService]);
                    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printService[selectedService].getAttributes());
                    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
                    exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
                    exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
                    exporter.exportReport();

                } catch (JRException e) {
                    e.printStackTrace();
                }
/////

/////
                conn.close();

            } catch (Exception ex) {
                ex.printStackTrace();

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void preprintnewfull_EXP(int CONO, String DIVI, String RCNO, String THB, String PRINTER, Boolean checkLAST, String FNNO) {

        System.out.println(CONO);
        System.out.println(DIVI);
        System.out.println(RCNO);
        System.out.println(THB);
        System.out.println(PRINTER);

        String report, printer;
        File reportFile = new File(getServletContext().getRealPath("Report/EPRC_0013.jrxml"));
//        printer = "RECEIPT PRINTER";
        printer = PRINTER.replaceAll("_", " ");
        System.out.println(printer);
        System.out.println(reportFile);

        try {
            Connection conn = ConnectDB2.ConnectionDB();
            try {
                // Parameters
                Map parameterss = new HashMap();
                parameterss.put("CONO", CONO);
                parameterss.put("DIVI", DIVI);
                parameterss.put("RCNO", RCNO);
                parameterss.put("THB", THB);
                parameterss.put("FNNO", FNNO);
                parameterss.put("LAST", checkLAST);

                System.out.println(CONO);
                System.out.println(DIVI);
                System.out.println(RCNO);
                System.out.println(THB);

                try {

                    String jcm = JasperCompileManager.compileReportToFile(reportFile.getPath());
                    JasperPrint print = JasperFillManager.fillReport(jcm, parameterss, conn);
                    PrinterJob printerJob = PrinterJob.getPrinterJob();
                    PageFormat pageFormat = PrinterJob.getPrinterJob().defaultPage();
                    printerJob.defaultPage(pageFormat);
                    int selectedService = 0;
                    AttributeSet attributeSet = new HashPrintServiceAttributeSet(new PrinterName(printer, null));
                    PrintService[] printService = PrintServiceLookup.lookupPrintServices(null, attributeSet);
                    System.out.println("END1");
                    try {
                        System.out.println(selectedService);

                        System.out.println(printService);
                        printerJob.setPrintService(printService[selectedService]);

                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    JRPrintServiceExporter exporter;
                    PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
//                    printRequestAttributeSet.add(MediaSizeName.ISO_A4);
                    printRequestAttributeSet.add(new Copies(1));

                    System.out.println("END");
                    // these are deprecated
                    exporter = new JRPrintServiceExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, printService[selectedService]);
                    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printService[selectedService].getAttributes());
                    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
                    exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
                    exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
                    exporter.exportReport();

                } catch (JRException e) {
                    e.printStackTrace();
                }
/////

/////
                conn.close();

            } catch (Exception ex) {
                ex.printStackTrace();

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void preprintnewfull_EXP2(int CONO, String DIVI, String RCNO, String THB, String PRINTER, Boolean checkLAST, String FNNO, Boolean CHECKBL) {

        System.out.println(CONO);
        System.out.println(DIVI);
        System.out.println(RCNO);
        System.out.println(THB);
        System.out.println(PRINTER);

        String report, printer;
        File reportFile = new File(getServletContext().getRealPath("Report/EPRC_0013.jrxml"));
//        printer = "RECEIPT PRINTER";
        printer = PRINTER.replaceAll("_", " ");
        System.out.println(printer);
        System.out.println(reportFile);

        try {
            Connection conn = ConnectDB2.ConnectionDB();
            try {
                // Parameters
                Map parameterss = new HashMap();
                parameterss.put("CONO", CONO);
                parameterss.put("DIVI", DIVI);
                parameterss.put("RCNO", RCNO);
                parameterss.put("THB", THB);
                parameterss.put("FNNO", FNNO);
                parameterss.put("LAST", checkLAST);
                parameterss.put("CHECKBL", CHECKBL);

                System.out.println(CONO);
                System.out.println(DIVI);
                System.out.println(RCNO);
                System.out.println(THB);

                try {

                    String jcm = JasperCompileManager.compileReportToFile(reportFile.getPath());
                    JasperPrint print = JasperFillManager.fillReport(jcm, parameterss, conn);
                    PrinterJob printerJob = PrinterJob.getPrinterJob();
                    PageFormat pageFormat = PrinterJob.getPrinterJob().defaultPage();
                    printerJob.defaultPage(pageFormat);
                    int selectedService = 0;
                    AttributeSet attributeSet = new HashPrintServiceAttributeSet(new PrinterName(printer, null));
                    PrintService[] printService = PrintServiceLookup.lookupPrintServices(null, attributeSet);
                    System.out.println("END1");
                    try {
                        System.out.println(selectedService);

                        System.out.println(printService);
                        printerJob.setPrintService(printService[selectedService]);

                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    JRPrintServiceExporter exporter;
                    PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
//                    printRequestAttributeSet.add(MediaSizeName.ISO_A4);
                    printRequestAttributeSet.add(new Copies(1));

                    System.out.println("END");
                    // these are deprecated
                    exporter = new JRPrintServiceExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, printService[selectedService]);
                    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printService[selectedService].getAttributes());
                    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
                    exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
                    exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
                    exporter.exportReport();

                } catch (JRException e) {
                    e.printStackTrace();
                }
/////

/////
                conn.close();

            } catch (Exception ex) {
                ex.printStackTrace();

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void preprintnewfull_EXP_P(int CONO, String DIVI, String RCNO, String THB, String PRINTER, Boolean checkLAST, String FNNO) {

        System.out.println(CONO);
        System.out.println(DIVI);
        System.out.println(RCNO);
        System.out.println(THB);
        System.out.println(PRINTER);

        String report, printer;
        File reportFile = new File(getServletContext().getRealPath("Report/EPRC_0013P.jrxml"));
//        printer = "RECEIPT PRINTER";
        printer = PRINTER.replaceAll("_", " ");
        System.out.println(printer);
        System.out.println(reportFile);

        try {
            Connection conn = ConnectDB2.ConnectionDB();
            try {
                // Parameters
                Map parameterss = new HashMap();
                parameterss.put("CONO", CONO);
                parameterss.put("DIVI", DIVI);
                parameterss.put("RCNO", RCNO);
                parameterss.put("THB", THB);
                parameterss.put("FNNO", FNNO);
                parameterss.put("LAST", checkLAST);

                System.out.println(CONO);
                System.out.println(DIVI);
                System.out.println(RCNO);
                System.out.println(THB);

                try {

                    String jcm = JasperCompileManager.compileReportToFile(reportFile.getPath());
                    JasperPrint print = JasperFillManager.fillReport(jcm, parameterss, conn);
                    PrinterJob printerJob = PrinterJob.getPrinterJob();
                    PageFormat pageFormat = PrinterJob.getPrinterJob().defaultPage();
                    printerJob.defaultPage(pageFormat);
                    int selectedService = 0;
                    AttributeSet attributeSet = new HashPrintServiceAttributeSet(new PrinterName(printer, null));
                    PrintService[] printService = PrintServiceLookup.lookupPrintServices(null, attributeSet);
                    System.out.println("END1");
                    try {
                        System.out.println(selectedService);

                        System.out.println(printService);
                        printerJob.setPrintService(printService[selectedService]);

                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    JRPrintServiceExporter exporter;
                    PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
//                    printRequestAttributeSet.add(MediaSizeName.ISO_A4);
                    printRequestAttributeSet.add(new Copies(1));

                    System.out.println("END");
                    // these are deprecated
                    exporter = new JRPrintServiceExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, printService[selectedService]);
                    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printService[selectedService].getAttributes());
                    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
                    exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
                    exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
                    exporter.exportReport();

                } catch (JRException e) {
                    e.printStackTrace();
                }
/////

/////
                conn.close();

            } catch (Exception ex) {
                ex.printStackTrace();

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void preprintnew(int CONO, String DIVI, String RCNO, String THB, String PRINTER) {

        System.out.println(CONO);
        System.out.println(DIVI);
        System.out.println(RCNO);
        System.out.println(THB);
        System.out.println(PRINTER);

        String report, printer;
        File reportFile = new File(getServletContext().getRealPath("Report/EPRC_0012.jrxml"));
//        printer = "RECEIPT PRINTER";
        printer = PRINTER.replaceAll("_", " ");
        System.out.println(printer);
        System.out.println(reportFile);

        try {
            Connection conn = ConnectDB2.ConnectionDB();
            try {
                // Parameters
                Map parameterss = new HashMap();
                parameterss.put("CONO", CONO);
                parameterss.put("DIVI", DIVI);
                parameterss.put("RCNO", RCNO);
                parameterss.put("THB", THB);
//                parameterss.put("BALANCE", Balance);

                System.out.println(CONO);
                System.out.println(DIVI);
                System.out.println(RCNO);
                System.out.println(THB);

                try {

                    String jcm = JasperCompileManager.compileReportToFile(reportFile.getPath());
                    JasperPrint print = JasperFillManager.fillReport(jcm, parameterss, conn);
                    PrinterJob printerJob = PrinterJob.getPrinterJob();
                    PageFormat pageFormat = PrinterJob.getPrinterJob().defaultPage();
                    printerJob.defaultPage(pageFormat);
                    int selectedService = 0;
                    AttributeSet attributeSet = new HashPrintServiceAttributeSet(new PrinterName(printer, null));
                    PrintService[] printService = PrintServiceLookup.lookupPrintServices(null, attributeSet);
                    System.out.println("END1");
                    try {
                        System.out.println(selectedService);

                        System.out.println(printService);
                        printerJob.setPrintService(printService[selectedService]);

                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    JRPrintServiceExporter exporter;
                    PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
//                    printRequestAttributeSet.add(MediaSizeName.ISO_A4);
                    printRequestAttributeSet.add(new Copies(1));

                    System.out.println("END");
                    // these are deprecated
                    exporter = new JRPrintServiceExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, printService[selectedService]);
                    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printService[selectedService].getAttributes());
                    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
                    exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
                    exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
                    exporter.exportReport();

                } catch (JRException e) {
                    e.printStackTrace();
                }
/////

/////
                conn.close();

            } catch (Exception ex) {
                ex.printStackTrace();

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void preprint(int CONO, String DIVI, String RCNO, String THB, String PRINTER) {

        String report, printer;
        File reportFile = new File(getServletContext().getRealPath("Report/EPRC_001.jrxml"));

        System.out.println(reportFile);
//        printer = "RECEIPT PRINTER";
        printer = PRINTER.replaceAll("_", " ");
        System.out.println(printer);

        try {
            Connection conn = ConnectDB2.ConnectionDB();
            try {
                // Parameters
                Map parameterss = new HashMap();
                parameterss.put("CONO", CONO);
                parameterss.put("DIVI", DIVI);
                parameterss.put("RCNO", RCNO);
                parameterss.put("THB", THB);

                System.out.println(CONO);
                System.out.println(DIVI);
                System.out.println(RCNO);
                System.out.println(THB);

                try {

                    String jcm = JasperCompileManager.compileReportToFile(reportFile.getPath());
                    JasperPrint print = JasperFillManager.fillReport(jcm, parameterss, conn);
                    PrinterJob printerJob = PrinterJob.getPrinterJob();
                    PageFormat pageFormat = PrinterJob.getPrinterJob().defaultPage();
                    printerJob.defaultPage(pageFormat);
                    int selectedService = 0;
                    AttributeSet attributeSet = new HashPrintServiceAttributeSet(new PrinterName(printer, null));
                    PrintService[] printService = PrintServiceLookup.lookupPrintServices(null, attributeSet);
                    System.out.println("END1");
                    try {
                        System.out.println(selectedService);

                        System.out.println(printService);
                        printerJob.setPrintService(printService[selectedService]);

                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    JRPrintServiceExporter exporter;
                    PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
//                    printRequestAttributeSet.add(MediaSizeName.ISO_A4);
                    printRequestAttributeSet.add(new Copies(1));

                    System.out.println("END");
                    // these are deprecated
                    exporter = new JRPrintServiceExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, printService[selectedService]);
                    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printService[selectedService].getAttributes());
                    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
                    exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
                    exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
                    exporter.exportReport();

                } catch (JRException e) {
                    e.printStackTrace();
                }
/////

/////
                conn.close();

            } catch (Exception ex) {
                ex.printStackTrace();

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void preprint_expense(int CONO, String DIVI, String RCNO, String THB, String PRINTER) {

        String report, printer;
        File reportFile = new File(getServletContext().getRealPath("Report/EPRC_003.jrxml"));
//        printer = "RECEIPT PRINTER";
        printer = PRINTER.replaceAll("_", " ");
        System.out.println(printer);

        try {
            Connection conn = ConnectDB2.ConnectionDB();
            try {
                // Parameters
                Map parameterss = new HashMap();
                parameterss.put("CONO", CONO);
                parameterss.put("DIVI", DIVI);
                parameterss.put("RCNO", RCNO);
                parameterss.put("THB", THB);

                try {

                    String jcm = JasperCompileManager.compileReportToFile(reportFile.getPath());
                    JasperPrint print = JasperFillManager.fillReport(jcm, parameterss, conn);
                    PrinterJob printerJob = PrinterJob.getPrinterJob();
                    PageFormat pageFormat = PrinterJob.getPrinterJob().defaultPage();
                    printerJob.defaultPage(pageFormat);
                    int selectedService = 0;
                    AttributeSet attributeSet = new HashPrintServiceAttributeSet(new PrinterName(printer, null));
                    PrintService[] printService = PrintServiceLookup.lookupPrintServices(null, attributeSet);

                    try {
                        printerJob.setPrintService(printService[selectedService]);

                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    JRPrintServiceExporter exporter;
                    PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
//                    printRequestAttributeSet.add(MediaSizeName.ISO_A4);
                    printRequestAttributeSet.add(new Copies(1));

                    // these are deprecated
                    exporter = new JRPrintServiceExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, printService[selectedService]);
                    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printService[selectedService].getAttributes());
                    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
                    exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
                    exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
                    exporter.exportReport();

                } catch (JRException e) {
                    e.printStackTrace();
                }

                conn.close();

            } catch (Exception ex) {
                ex.printStackTrace();

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void preprint_DEPOSIT(int CONO, String DIVI, String RCNO, String THB, String PRINTER, Boolean checkLAST, String FNNO, Boolean CHECKBL) {

        System.out.println("// PARAMETER");
        ////ppppp 
        System.out.println("------------------------------");
        System.out.println(CONO);
        System.out.println(DIVI);
        System.out.println(RCNO);
        System.out.println(THB);
        System.out.println(PRINTER);
        System.out.println(checkLAST);
        System.out.println(FNNO);
        System.out.println(CHECKBL);
        System.out.println("------------------------------");
        
   

        String report, printer;
        File reportFile = new File(getServletContext().getRealPath("Report/EPRC_004.jrxml"));
//        printer = "RECEIPT PRINTER";
        printer = PRINTER.replaceAll("_", " ");
        System.out.println(printer);

        try {
            Connection conn = ConnectDB2.ConnectionDB();
            try {
                // Parameters
                Map parameterss = new HashMap();

                parameterss.put("CONO", CONO);
                parameterss.put("DIVI", DIVI);
                parameterss.put("RCNO", RCNO);
                parameterss.put("THB", THB);
                parameterss.put("FNNO", FNNO);
                parameterss.put("LAST", checkLAST);
                parameterss.put("CHECKBL", CHECKBL);
                try {
//error here 
                    String jcm = JasperCompileManager.compileReportToFile(reportFile.getPath());
                    JasperPrint print = JasperFillManager.fillReport(jcm, parameterss, conn);
                    PrinterJob printerJob = PrinterJob.getPrinterJob();
                    PageFormat pageFormat = PrinterJob.getPrinterJob().defaultPage();
                    printerJob.defaultPage(pageFormat);
                    int selectedService = 0;
                    AttributeSet attributeSet = new HashPrintServiceAttributeSet(new PrinterName(printer, null));
                    PrintService[] printService = PrintServiceLookup.lookupPrintServices(null, attributeSet);

                    try {
                        printerJob.setPrintService(printService[selectedService]);

                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    JRPrintServiceExporter exporter;
                    PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
//                    printRequestAttributeSet.add(MediaSizeName.ISO_A4);
                    printRequestAttributeSet.add(new Copies(1));

                    // these are deprecated
                    exporter = new JRPrintServiceExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, printService[selectedService]);
                    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printService[selectedService].getAttributes());
                    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
                    exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
                    exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
                    exporter.exportReport();

                } catch (JRException e) {
                    e.printStackTrace();
                }

                conn.close();

            } catch (Exception ex) {
                ex.printStackTrace();

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
