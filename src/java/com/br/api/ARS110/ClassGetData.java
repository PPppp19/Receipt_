/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.api.ARS110;

import com.br.api.connect.ConnectDB2;
import com.br.api.data.GBVAR;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jilasak
 */
public class ClassGetData {

    public static String DBPRD = GBVAR.DBPRD;
    public static String M3DB = GBVAR.M3DB;
//        public static String DBPRD = "M3FDBTST";

    public ResultSet GetReceipt(int CONO, String DIVI, String RCNO, String TYPE) {

        try {
            ResultSet RsDataReceipt;
            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();
            String Sql1 = "SELECT HR_CONO, HR_DIVI, HC_RCNO, HC_TRDT, HC_PYNO,\n"
                    + "HC_REAMT, HC_TYPE, HC_BANK, HC_ACCODE,\n"
                    + "HC_PYDT, HC_CHKNO, HC_USER, COALESCE(HR_BKCHG,0) AS HR_BKCHG \n"
                    + "FROM " + DBPRD + ".HR_RECEIPT\n" ///PRD
                    + "WHERE HR_CONO = '" + CONO + "'\n"
                    + "AND HR_DIVI = '" + DIVI.trim() + "'\n"
                    + "AND HC_STS = '2'\n"
                    + "AND HC_TYPE = '" + TYPE.trim() + "'\n"
                    + "AND HC_RCNO = '" + RCNO.trim() + "'";

            RsDataReceipt = sta.executeQuery(Sql1);

            System.out.println("nnnnnnnnnnn)");

            System.out.println(TYPE);
            System.out.println(CONO);
            System.out.println(DIVI);
            System.out.println(RCNO);
            System.out.println(Sql1);

            System.out.println("nnnnnnnnnnnnnnnnn)");

            return RsDataReceipt;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;

    }

    public ResultSet GetReceiptnewdeposit(int CONO, String DIVI, String RCNO, String TYPE) {

        try {
            ResultSet RsDataReceipt;
            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();
            String Sql1 = "SELECT HR_CONO, HR_DIVI, HC_RCNO, HC_TRDT, HC_PYNO,\n"
                    + "HC_REAMT, HC_TYPE, HC_BANK, HC_ACCODE,\n"
                    + "HC_PYDT, HC_CHKNO, HC_USER, COALESCE(HR_BKCHG,0) AS HR_BKCHG \n"
                    + "FROM " + DBPRD + ".HR_RECEIPT\n" ///PRD
                    + "WHERE HR_CONO = '" + CONO + "'\n"
                    + "AND HR_DIVI = '" + DIVI.trim() + "'\n"
                    + "AND HC_STS = '4'\n"
                    + "AND HC_TYPE = '" + TYPE.trim() + "'\n"
                    + "AND HC_RCNO = '" + RCNO.trim() + "'";

            RsDataReceipt = sta.executeQuery(Sql1);

            System.out.println("nnnnnnnnnnn)");

            System.out.println(TYPE);
            System.out.println(CONO);
            System.out.println(DIVI);
            System.out.println(RCNO);
            System.out.println(Sql1);

            System.out.println("nnnnnnnnnnnnnnnnn)");

            return RsDataReceipt;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;

    }

    public ResultSet GetReceiptnew4(int CONO, String DIVI, String RCNO, String TYPE) {

        try {
            ResultSet RsDataReceipt;
            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();
            String Sql1 = "SELECT HR_CONO, HR_DIVI, HC_RCNO, HC_TRDT, HC_PYNO,\n"
                    + "HC_REAMT, HC_TYPE, HC_BANK, HC_ACCODE,\n"
                    + "HC_PYDT, HC_CHKNO, HC_USER, COALESCE(HR_BKCHG,0) AS HR_BKCHG \n"
                    + "FROM " + DBPRD + ".HR_RECEIPT\n" ///PRD
                    + "WHERE HR_CONO = '" + CONO + "'\n"
                    + "AND HR_DIVI = '" + DIVI.trim() + "'\n"
                    + "AND HC_STS = '4'\n"
                    + "AND HC_TYPE = '" + TYPE.trim() + "'\n"
                    + "AND HC_RCNO = '" + RCNO.trim() + "'";

            RsDataReceipt = sta.executeQuery(Sql1);

            System.out.println("nnnnnnnnnnn)");

            System.out.println(TYPE);
            System.out.println(CONO);
            System.out.println(DIVI);
            System.out.println(RCNO);
            System.out.println(Sql1);

            System.out.println("nnnnnnnnnnnnnnnnn)");

            return RsDataReceipt;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;

    }

    public ResultSet GetReceiptnew3(int CONO, String DIVI, String RCNO, String TYPE) {

        try {
            ResultSet RsDataReceipt;
            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();
            String Sql1 = "SELECT HR_CONO, HR_DIVI, HC_RCNO, HC_TRDT, HC_PYNO,\n"
                    + "HC_REAMT, HC_TYPE, HC_BANK, HC_ACCODE,\n"
                    + "HC_PYDT, HC_CHKNO, HC_USER, COALESCE(HR_BKCHG,0) AS HR_BKCHG \n"
                    + "FROM " + DBPRD + ".HR_RECEIPT\n" ///PRD
                    + "WHERE HR_CONO = '" + CONO + "'\n"
                    + "AND HR_DIVI = '" + DIVI.trim() + "'\n"
                    + "AND HC_STS = '3'\n"
                    + "AND HC_TYPE = '" + TYPE.trim() + "'\n"
                    + "AND HC_RCNO = '" + RCNO.trim() + "'";

            RsDataReceipt = sta.executeQuery(Sql1);

            System.out.println("nnnnnnnnnnn)");

            System.out.println(TYPE);
            System.out.println(CONO);
            System.out.println(DIVI);
            System.out.println(RCNO);
            System.out.println(Sql1);

            System.out.println("nnnnnnnnnnnnnnnnn)");

            return RsDataReceipt;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;

    }

    public ResultSet GetReceiptnew(int CONO, String DIVI, String RCNO, String TYPE) {

        try {
            ResultSet RsDataReceipt;
            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();
            String Sql1 = "SELECT HR_CONO, HR_DIVI, HC_RCNO, HC_TRDT, HC_PYNO,\n"
                    + "HC_REAMT, HC_TYPE, HC_BANK, HC_ACCODE,\n"
                    + "HC_PYDT, HC_CHKNO, HC_USER, COALESCE(HR_BKCHG,0) AS HR_BKCHG \n"
                    + "FROM " + DBPRD + ".HR_RECEIPT\n" ///PRD
                    + "WHERE HR_CONO = '" + CONO + "'\n"
                    + "AND HR_DIVI = '" + DIVI.trim() + "'\n"
                    + "AND HC_STS = '2'\n"
                    + "AND HC_TYPE = '" + TYPE.trim() + "'\n"
                    + "AND HC_RCNO = '" + RCNO.trim() + "'";

            RsDataReceipt = sta.executeQuery(Sql1);

            System.out.println("nnnnnnnnnnn)");

            System.out.println(TYPE);
            System.out.println(CONO);
            System.out.println(DIVI);
            System.out.println(RCNO);
            System.out.println(Sql1);

            System.out.println("nnnnnnnnnnnnnnnnn)");

            return RsDataReceipt;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;

    }

    public ResultSet LineReceiptnew(int CONO, String DIVI, ArrayList<String> arrRC) {

        String RCNOSET = "";
        try {

            for (int i = 0; i < arrRC.size(); i++) {

                RCNOSET += "'" + arrRC.get(i) + "'";

                System.out.println(i + ":" + arrRC.size());
                if (i != arrRC.size() - 1) {

                    RCNOSET += ",";
                }
            }

            ResultSet RsDataReceipt;
            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();
            String Sql1 = "SELECT LR_CONO, LR_DIVI, LR_RCNO,LR_INVNO, LR_INVDT, LR_INVAMT, LR_REAMT, LR_STS \n"//PRD
                    + "FROM " + DBPRD + ".LR_LINERECEIPT\n" //PRD
                    + "WHERE LR_CONO = '" + CONO + "'\n"
                    + "AND LR_DIVI = '" + DIVI.trim() + "'\n"
                    + "AND LR_RCNO in (" + RCNOSET + ") ORDER BY LR_INVDT  DESC";

            System.out.println(Sql1);
            RsDataReceipt = sta.executeQuery(Sql1);

            return RsDataReceipt;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;

    }

    public ResultSet LineReceipt(int CONO, String DIVI, String RCNO) {

        try {
            ResultSet RsDataReceipt;
            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();
            String Sql1 = "SELECT LR_CONO, LR_DIVI, LR_RCNO,LR_INVNO, LR_INVDT, LR_INVAMT, LR_REAMT, LR_STS \n"//PRD
                    + "FROM " + DBPRD + ".LR_LINERECEIPT\n" //PRD
                    + "WHERE LR_CONO = '" + CONO + "'\n"
                    + "AND LR_DIVI = '" + DIVI.trim() + "'\n"
                    + "AND LR_RCNO = '" + RCNO.trim() + "' ORDER BY LR_INVDT  DESC";

            RsDataReceipt = sta.executeQuery(Sql1);

            return RsDataReceipt;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;

    }

    public Double Call_Balancenew(int CONO, String DIVI, ArrayList<String> arrRC) {
        Double BL = 0.00;
        String RCNOSET = "";
        Double TOTAL1 = 0.00;
        Double TOTAL2 = 0.00;

        try {

            for (int i = 0; i < arrRC.size(); i++) {

                RCNOSET += "'" + arrRC.get(i) + "'";

                System.out.println(i + ":" + arrRC.size());
                if (i != arrRC.size() - 1) {

                    RCNOSET += ",";
                }
            }

            ResultSet RsDataReceipt;
            ResultSet RsDataReceipt2;
            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();
            String Sql1 = "SELECT CAST(SUM(SUMSUM)AS DOUBLE) as TOTAL1 FROM  \n"
                    + "( SELECT SUM(LR_REAMT) AS SUMSUM ,LR_RCNO, LR_CONO,LR_DIVI  FROM " + DBPRD + ".LR_LINERECEIPT \n"
                    + "WHERE  LR_CONO = '" + CONO + "'\n"
                    + "AND  LR_DIVI = '" + DIVI + "'\n"
                    + "AND LR_RCNO IN  (" + RCNOSET + ")\n"
                    + "GROUP BY LR_RCNO,LR_CONO, LR_DIVI ) AS LR \n"
                    + "LEFT JOIN \n"
                    + "(SELECT * FROM " + DBPRD + ".HR_RECEIPT\n"
                    + ") AS HR \n"
                    + "ON  HR.HC_RCNO = LR.LR_RCNO AND  HR.HR_CONO = LR.LR_CONO AND  HR.HR_DIVI = LR.LR_DIVI";
            System.out.println(Sql1);
            RsDataReceipt = sta.executeQuery(Sql1);
            if (RsDataReceipt.next()) {
                TOTAL1 = RsDataReceipt.getDouble("TOTAL1");

            }
            System.out.println(TOTAL1);

            Statement sta2 = conn.createStatement();
            String Sql2 = "SELECT CAST(HC_REAMT as DOUBLE) AS TOTAL2  FROM " + DBPRD + ".HR_RECEIPT WHERE  HC_RCNO  IN (" + RCNOSET + ") AND  HR_CONO  = '"+CONO+"' LIMIT 1  ";
            RsDataReceipt2 = sta2.executeQuery(Sql2);
            System.out.println(Sql2 + "xxxxx");

            if (RsDataReceipt2.next()) {
                TOTAL2 = RsDataReceipt2.getDouble("TOTAL2");
            }

            System.out.println(TOTAL2);

//            BL = TOTAL2 - TOTAL1;
            BL = Double.valueOf(String.format("%.2f", TOTAL2)) - Double.valueOf(String.format("%.2f", TOTAL1));
            Double.valueOf(String.format("%.2f", BL));

            System.out.println("-----------------------------");
            System.out.println(TOTAL2);
            System.out.println(TOTAL1);

            System.out.println(BL);
            System.out.println(Double.valueOf(String.format("%.2f", BL)));

            System.out.println("-----------------------------end");

//            return BL;
            return Double.valueOf(String.format("%.2f", BL));
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;

    }

    public Double Call_Balance(int CONO, String DIVI, String RCNO) {
        Double BL = 0.00;
        try {
            ResultSet RsDataReceipt;
            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();
            String Sql1 = "SELECT F.HC_REAMT-F.LR_REAMT AS balance\n"
                    + "FROM (\n"
                    + "SELECT HC_REAMT,SUM(LR_REAMT) AS LR_REAMT\n"
                    + "FROM " + DBPRD + ".HR_RECEIPT," + DBPRD + ".LR_LINERECEIPT\n" // PRD
                    + "WHERE HR_CONO = LR_CONO\n"
                    + "AND HR_DIVI = LR_DIVI\n"
                    + "AND HC_RCNO = '" + RCNO.trim() + "'\n"
                    + "AND HR_CONO = '" + CONO + "'\n "
                    + "AND HR_DIVI = '" + DIVI.trim() + "'\n "
                    + "AND HC_RCNO = LR_RCNO\n"
                    + "GROUP BY HC_REAMT ) AS F";
            RsDataReceipt = sta.executeQuery(Sql1);
            if (RsDataReceipt.next()) {
                BL = RsDataReceipt.getDouble("balance");
            }
            return BL;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;

    }

    public String COST_SALEMAN(int CONO, String payer) {
        String costc = "S601";
        try {
            ResultSet RsDataReceipt;
            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();
            String Sql1 = "SELECT OKSMCD,JUUSID,JUTX40,COALESCE(JUDEPT,'') AS  JUDEPT \n"
                    + "FROM M3FDBPRD.OCUSMA,m3fdbprd.CMNUSR\n"
                    + "WHERE OKCONO= '" + CONO + "'\n"
                    + "AND OKSTAT = '20' \n"
                    + "AND OKCUNO ='" + payer.trim() + "'\n"
                    + "AND OKSMCD = JUUSID";
            RsDataReceipt = sta.executeQuery(Sql1);
            if (RsDataReceipt.next()) {
                if (CONO == 600) {
                    costc = "WS601";
                } else {
                    costc = RsDataReceipt.getString("JUDEPT").trim();
                }
            }
            if (costc.equalsIgnoreCase("")) {
                if (CONO == 600) {
                    costc = "WS601";
                } else {
                    costc = "S601";
                }
            }
            return costc;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return costc;

    }

    public String GET_ReceiptTypedeposi(int CONO, String DIVI, String RCNO) {
        String HC_TYPE = "";

        try {

            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();
            String Sql1 = "SELECT HC_TYPE \n"
                    + "FROM " + DBPRD + ".HR_RECEIPT\n"
                    + "WHERE HR_CONO = '" + CONO + "'\n"
                    + "AND HR_DIVI = '" + DIVI.trim() + "'\n"
                    + "AND HC_RCNO = '" + RCNO.trim() + "'\n"
                    + "AND HC_STS = '4'";
            ResultSet rsl = sta.executeQuery(Sql1);
            if (rsl.next()) {
                HC_TYPE = rsl.getString("HC_TYPE").trim();
            }
            return HC_TYPE;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        return HC_TYPE;
    }

    public String GET_ReceiptType(int CONO, String DIVI, String RCNO) {
        String HC_TYPE = "";

        try {

            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();
            String Sql1 = "SELECT HC_TYPE \n"
                    + "FROM " + DBPRD + ".HR_RECEIPT\n"
                    + "WHERE HR_CONO = '" + CONO + "'\n"
                    + "AND HR_DIVI = '" + DIVI.trim() + "'\n"
                    + "AND HC_RCNO = '" + RCNO.trim() + "'\n"
                    + "AND HC_STS = '2'";
            ResultSet rsl = sta.executeQuery(Sql1);
            if (rsl.next()) {
                HC_TYPE = rsl.getString("HC_TYPE").trim();
            }
            return HC_TYPE;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        return HC_TYPE;
    }

    public String GET_ReceiptTypeCLEAREXP(int CONO, String DIVI, String RCNO) {
        String HC_TYPE = "";

        try {

            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();
            String Sql1 = "SELECT HC_TYPE \n"
                    + "FROM " + DBPRD + ".HR_RECEIPT\n"
                    + "WHERE HR_CONO = '" + CONO + "'\n"
                    + "AND HR_DIVI = '" + DIVI.trim() + "'\n"
                    + "AND HC_RCNO = '" + RCNO.trim() + "'\n"
                    + "AND HC_STS = '3'";

            System.out.println(Sql1);
            ResultSet rsl = sta.executeQuery(Sql1);
            if (rsl.next()) {
                HC_TYPE = rsl.getString("HC_TYPE").trim();
            }
            return HC_TYPE;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        return HC_TYPE;
    }

    public String GET_ReceiptTypeEXP(int CONO, String DIVI, String RCNO) {
        String HC_TYPE = "";

        try {

            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();
            String Sql1 = "SELECT HC_TYPE \n"
                    + "FROM " + DBPRD + ".HR_RECEIPT\n"
                    + "WHERE HR_CONO = '" + CONO + "'\n"
                    + "AND HR_DIVI = '" + DIVI.trim() + "'\n"
                    + "AND HC_RCNO = '" + RCNO.trim() + "'\n"
                    + "AND HC_STS = '4'";
            ResultSet rsl = sta.executeQuery(Sql1);
            if (rsl.next()) {
                HC_TYPE = rsl.getString("HC_TYPE").trim();
            }
            return HC_TYPE;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        return HC_TYPE;
    }

    public String GET_ReceiptType_Reprint(int CONO, String DIVI, String RCNO) {
        String HC_TYPE = "";

        try {
            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();
            String Sql1 = "SELECT HC_TYPE \n"
                    + "FROM " + DBPRD + ".HR_RECEIPT\n"
                    + "WHERE HR_CONO = '" + CONO + "'\n"
                    + "AND HR_DIVI = '" + DIVI.trim() + "'\n"
                    + "AND HC_RCNO = '" + RCNO.trim() + "'\n"
                    + "AND HC_STS IN ('2','3')";
            ResultSet rsl = sta.executeQuery(Sql1);
            if (rsl.next()) {
                HC_TYPE = rsl.getString("HC_TYPE").trim();
            }
            return HC_TYPE;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return HC_TYPE;
    }

    public ResultSet LineExpense(int CONO, String DIVI, String RCNO) {

        try {
            ResultSet RsDataReceipt1;
            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();
            String Sql1 = "SELECT LRE_CONO, LRE_DIVI, LRE_RCNO, LRE_CODE, LRE_ACTCODE, LRE_AMT, RE_OPTION\n"
                    + "            FROM " + DBPRD + ".LR_LINEEXPEN," + DBPRD + ".RECEIPT_EXPENSE\n"
                    + "            WHERE LRE_CONO = '" + CONO + "'\n"
                    + "            AND LRE_DIVI = '" + DIVI.trim() + "'\n"
                    + "            AND LRE_RCNO = '" + RCNO.trim() + "'\n"
                    + "            AND LRE_CONO = RE_CONO\n"
                    + "            AND LRE_DIVI = RE_DIVI\n"
                    + "            AND LRE_CODE = RE_CODE \n"
                    + "   ORDER  BY LRE_AMT DESC ";

            RsDataReceipt1 = sta.executeQuery(Sql1);

            System.out.println(Sql1);

//            System.out.println(RsDataReceipt);
//            ResultSetMetaData rsmd = RsDataReceipt.getMetaData();
//            int columnsNumber = rsmd.getColumnCount();
//            while (RsDataReceipt.next()) {
//                for (int i = 1; i <= columnsNumber; i++) {
//                    if (i > 1) {
//                        System.out.print(",  ");
//                    }
//                    String columnValue = RsDataReceipt.getString(i);
//                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
//                }
//                System.out.println("");
//            }
            return RsDataReceipt1;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;

    }

    public double sumLineExpensenew(int CONO, String DIVI, String FNNO) {

        try {
            double amtp = 0;
            ResultSet RsDataReceipt1;
            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();
            String Sql1 = "SELECT sum(LRE_AMT) as SUMPP \n"
                    + "            FROM " + DBPRD + ".LR_LINEEXPEN," + DBPRD + ".RECEIPT_EXPENSE\n"
                    + "            WHERE LRE_CONO = '" + CONO + "'\n"
                    + "            AND LRE_DIVI = '" + DIVI.trim() + "'\n"
                    + "            AND LRE_FNNO = '" + FNNO.trim() + "'\n"
                    + "            AND LRE_CONO = RE_CONO\n"
                    + "            AND LRE_DIVI = RE_DIVI\n"
                    + "            AND LRE_CODE = RE_CODE \n"
                    + "  ";

            System.out.println(Sql1);

            RsDataReceipt1 = sta.executeQuery(Sql1);

            RsDataReceipt1 = sta.executeQuery(Sql1);
            if (RsDataReceipt1.next()) {
                amtp = RsDataReceipt1.getDouble("SUMPP");
            }

            System.out.println(Sql1);

//            System.out.println(RsDataReceipt);
//            ResultSetMetaData rsmd = RsDataReceipt.getMetaData();
//            int columnsNumber = rsmd.getColumnCount();
//            while (RsDataReceipt.next()) {
//                for (int i = 1; i <= columnsNumber; i++) {
//                    if (i > 1) {
//                        System.out.print(",  ");
//                    }
//                    String columnValue = RsDataReceipt.getString(i);
//                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
//                }
//                System.out.println("");
//            }
            return amtp;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return 0;

    }

    public ResultSet LineExpensenew(int CONO, String DIVI, String FNNO) {

        try {
            ResultSet RsDataReceipt1;
            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();
            String Sql1 = "SELECT LRE_CONO, LRE_DIVI, LRE_RCNO, LRE_CODE, LRE_ACTCODE, LRE_AMT, RE_OPTION\n"
                    + "            FROM " + DBPRD + ".LR_LINEEXPEN," + DBPRD + ".RECEIPT_EXPENSE\n"
                    + "            WHERE LRE_CONO = '" + CONO + "'\n"
                    + "            AND LRE_DIVI = '" + DIVI.trim() + "'\n"
                    + "            AND LRE_FNNO = '" + FNNO.trim() + "'\n"
                    + "            AND LRE_CONO = RE_CONO\n"
                    + "            AND LRE_DIVI = RE_DIVI\n"
                    + "            AND LRE_CODE = RE_CODE \n"
                    + "   ORDER  BY LRE_AMT DESC ";

            System.out.println(Sql1);

            RsDataReceipt1 = sta.executeQuery(Sql1);

            System.out.println(Sql1);

//            System.out.println(RsDataReceipt);
//            ResultSetMetaData rsmd = RsDataReceipt.getMetaData();
//            int columnsNumber = rsmd.getColumnCount();
//            while (RsDataReceipt.next()) {
//                for (int i = 1; i <= columnsNumber; i++) {
//                    if (i > 1) {
//                        System.out.print(",  ");
//                    }
//                    String columnValue = RsDataReceipt.getString(i);
//                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
//                }
//                System.out.println("");
//            }
            return RsDataReceipt1;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;

    }

    public String Expense_CustomerName(int CONO, String code) {
        String Customer = "";
        try {
            ResultSet RsDataExpense;
            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();
            String Sql1 = "SELECT TRIM(OKALCU) AS OKALCU  \n"
                    + "FROM M3FDBPRD.OCUSMA \n"
                    + "WHERE OKCUNO = '" + code.trim() + "' \n"
                    + "AND OKCONO = '" + CONO + "' \n"
                    + "AND OKSTAT = '20'";
            RsDataExpense = sta.executeQuery(Sql1);
            while (RsDataExpense.next()) {
                Customer = RsDataExpense.getString("OKALCU").trim();
            }

            return Customer;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return Customer;

    }

    public ResultSet LineClearExpensenew(int CONO, String DIVI, String FNNO) {

        try {
            ResultSet RsDataReceipt;
            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();

            String Sql1 = "SELECT LREP_CONO, LREP_DIVI, LREP_RCNO, LREP_INVNO, LREP_INVDT, LREP_INVAMT, LREP_EXPAMT, LREP_STS \n"
                    + "                   FROM " + DBPRD + ".LR_LINECLEAREXP , " + DBPRD + ".HEAD_RECIPT  \n"
                    + "                   WHERE LREP_CONO = '" + CONO + "'\n"
                    + "                   AND LREP_DIVI = '" + DIVI + "'\n"
                    + "                   AND LREP_FNNO = '" + FNNO + "'\n"
                    + "                   AND LREP_CONO = H_CONO\n"
                    + "                   AND LREP_DIVI = H_DIVI\n"
                    + "                   AND LREP_RCNO = H_RCNO\n"
                    + "                   ORDER  BY LREP_INVAMT DESC";

            System.out.println(Sql1);
            RsDataReceipt = sta.executeQuery(Sql1);

            return RsDataReceipt;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;

    }

    public ResultSet LineClearExpense(int CONO, String DIVI, String RCNO) {

        try {
            ResultSet RsDataReceipt;
            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();

            String Sql1 = "SELECT LREP_CONO, LREP_DIVI, LREP_RCNO, LREP_INVNO, LREP_INVDT, LREP_INVAMT, LREP_EXPAMT, LREP_STS \n"
                    + "FROM " + DBPRD + ".LR_LINECLEAREXP\n"
                    + "WHERE LREP_CONO = '" + CONO + "'\n"
                    + "AND LREP_DIVI = '" + DIVI.trim() + "'\n"
                    + "AND LREP_RCNO = '" + RCNO.trim() + "'";

            RsDataReceipt = sta.executeQuery(Sql1);

            return RsDataReceipt;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;

    }

    public String Expense_text(int CONO, String DIVI, String CODE) {
        String txt = "";
        try {
            ResultSet RsDataExpense;
            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();
            String Sql1 = "SELECT RE_DESC\n"
                    + "FROM " + DBPRD + ".RECEIPT_EXPENSE\n"
                    + "WHERE RE_CONO = '" + CONO + "'\n"
                    + "AND RE_DIVI = '" + DIVI.trim() + "'\n"
                    + "AND RE_STATUS = '10'\n"
                    + "AND RE_CODE = '" + CODE.trim() + "'";
            RsDataExpense = sta.executeQuery(Sql1);
            while (RsDataExpense.next()) {
                txt = RsDataExpense.getString("RE_DESC").trim();
            }

            return txt;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return txt;

    }

    public Double Call_Expensenew(int CONO, String DIVI, String FNNO) {
        Double BL = 0.00;
        try {
            ResultSet RsDataReceipt;
            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();
            String Sql1 = "SELECT COALESCE(SUM(LRE_AMT),0) AS AMTEXPENSE\n"
                    + "FROM " + DBPRD + ".LR_LINEEXPEN\n"
                    + "WHERE LRE_CONO = '" + CONO + "'\n"
                    + "AND LRE_DIVI = '" + DIVI.trim() + "'\n"
                    + "AND LRE_FNNO = '" + FNNO.trim() + "'";
            RsDataReceipt = sta.executeQuery(Sql1);
            System.out.println(Sql1);
            if (RsDataReceipt.next()) {
                BL = RsDataReceipt.getDouble("AMTEXPENSE");
            }

            System.out.println("-----------------------------");
            System.out.println(BL);
            System.out.println("-----------------------------END2");

//                        return BL;
            return Double.valueOf(String.format("%.2f", BL));
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;

    }

    public Double Call_Expense(int CONO, String DIVI, String RCNO) {
        Double BL = 0.00;
        try {
            ResultSet RsDataReceipt;
            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();
            String Sql1 = "SELECT COALESCE(SUM(LRE_AMT),0) AS AMTEXPENSE\n"
                    + "FROM " + DBPRD + ".LR_LINEEXPEN\n"
                    + "WHERE LRE_CONO = '" + CONO + "'\n"
                    + "AND LRE_DIVI = '" + DIVI.trim() + "'\n"
                    + "AND LRE_RCNO = '" + RCNO.trim() + "'";
            RsDataReceipt = sta.executeQuery(Sql1);
            if (RsDataReceipt.next()) {
                BL = RsDataReceipt.getDouble("AMTEXPENSE");
            }
            return BL;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;

    }

    public String GET_ReceiptType_Expense(int CONO, String DIVI, String RCNO) {
        String HC_TYPE = "";

        try {

            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();
            String Sql1 = "SELECT HC_TYPE \n"
                    + "FROM " + DBPRD + ".HR_RECEIPT\n"
                    + "WHERE HR_CONO = '" + CONO + "'\n"
                    + "AND HR_DIVI = '" + DIVI.trim() + "'\n"
                    + "AND HC_RCNO = '" + RCNO.trim() + "'\n"
                    + "AND HC_STS = '3'\n"
                    + "AND HC_VCNO != ''";
            ResultSet rsl = sta.executeQuery(Sql1);
            if (rsl.next()) {
                HC_TYPE = rsl.getString("HC_TYPE").trim();
            }
            return HC_TYPE;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        return HC_TYPE;
    }

    public ResultSet GetReceipt_EXP(int CONO, String DIVI, String RCNO, String TYPE) {

        try {
            ResultSet RsDataReceipt;
            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();
            String Sql1 = "SELECT HR_CONO, HR_DIVI, HC_RCNO, HC_TRDT, HC_PYNO,\n"
                    + "HC_REAMT, HC_TYPE, HC_BANK, HC_ACCODE,\n"
                    + "HC_PYDT, HC_CHKNO, HC_USER, COALESCE(HR_BKCHG,0) AS HR_BKCHG \n"
                    + "FROM " + DBPRD + ".HR_RECEIPT\n" ///PRD
                    + "WHERE HR_CONO = '" + CONO + "'\n"
                    + "AND HR_DIVI = '" + DIVI.trim() + "'\n"
                    + "AND HC_STS = '3'\n"
                    + "AND HC_TYPE = '" + TYPE.trim() + "'\n"
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

    public ResultSet GetReceiptUpdateOCUSMA(int CONO, String DIVI, String RCNO, String TYPE) {

        try {
            ResultSet RsDataReceipt;
            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();
            String Sql1 = "SELECT HR_CONO, HR_DIVI, HC_RCNO, HC_TRDT, HC_PYNO,\n"
                    + "HC_REAMT, HC_TYPE, HC_BANK, HC_ACCODE,\n"
                    + "HC_PYDT, HC_CHKNO, HC_USER, COALESCE(HR_BKCHG,0) AS HR_BKCHG \n"
                    + "FROM " + DBPRD + ".HR_RECEIPT\n" ///PRD
                    + "WHERE HR_CONO = '" + CONO + "'\n"
                    + "AND HR_DIVI = '" + DIVI.trim() + "'\n"
                    + "AND HC_TYPE = '" + TYPE.trim() + "'\n"
                    + "AND HC_RCNO = '" + RCNO.trim() + "'";

            System.out.println(Sql1);
            RsDataReceipt = sta.executeQuery(Sql1);

            return RsDataReceipt;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;

    }

    public ResultSet CheckTypeExpense(int CONO, String DIVI, String RCNO) {

        try {
            ResultSet RsDataReceipt;
            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();
            String Sql1 = "SELECT HR_CONO, HR_DIVI, HR_RCNO, HR_VCNO1, HR_VCNO2, HR_STS\n"
                    + "FROM " + DBPRD + ".HR_RECEIPTEXPENSE\n"
                    + "WHERE HR_CONO = '" + CONO + "'\n"
                    + "AND HR_DIVI = '" + DIVI.trim() + "'\n"
                    + "AND HR_RCNO = '" + RCNO.trim() + "'";

            RsDataReceipt = sta.executeQuery(Sql1);
            System.out.println(Sql1);

            return RsDataReceipt;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;

    }

    public ResultSet CheckTypeExpensenew(int CONO, String DIVI, String FNNO) {

        try {
            ResultSet RsDataReceipt;
            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();
            String Sql1 = "SELECT HR_CONO, HR_DIVI, HR_RCNO, HR_VCNO1, HR_VCNO2, HR_STS\n"
                    + "FROM " + DBPRD + ".HR_RECEIPTEXPENSE\n"
                    + "WHERE HR_CONO = '" + CONO + "'\n"
                    + "AND HR_DIVI = '" + DIVI.trim() + "'\n"
                    + "AND HR_FNNO = '" + FNNO.trim() + "'";

            RsDataReceipt = sta.executeQuery(Sql1);

            return RsDataReceipt;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;

    }

    public ResultSet GET_FNC_REF1(int CONO, String DIVI, String RCNO) {

        try {
            ResultSet RsDataReceipt;
            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();

            String Sql1 = "SELECT A.*,B.RL_FNC,B.RL_REF1\n"
                    + "FROM (\n"
                    + "SELECT HR_CONO, HR_DIVI, HC_RCNO,CASE WHEN  HC_TYPE = 'TRANSFER_EXP' THEN 'TRANSFER' \n"
                    + "WHEN  HC_TYPE = 'CASH_DEPOSIT' THEN 'CASH'\n"
                    + "WHEN  HC_TYPE = 'CASH_EXP' THEN 'CASH'\n"
                    + "WHEN  HC_TYPE = 'TRANSFER_DEPOSIT' THEN 'TRANSFER'\n"
                    +"WHEN  HC_TYPE = 'CHECK_DEPOSIT' THEN 'CHECK'\n"
                    + "WHEN HC_TYPE = 'CHECK_EXP' THEN 'CHECK' ELSE HC_TYPE END AS HC_TYPE ,COALESCE(HC_LOCATION,'LS') AS HC_LOCATION\n"
                    + "FROM " + DBPRD + ".HR_RECEIPT\n"
                    + "WHERE HC_RCNO = '" + RCNO.trim() + "'\n"
                    + "AND HR_CONO = '" + CONO + "'\n"
                    + "AND HR_DIVI = '" + DIVI.trim() + "'\n"
                    + ") A LEFT JOIN (\n"
                    + "SELECT RL_CONO, RL_DIVI, RL_FNC, RL_LCCODE, RL_LCDESC, RL_FNCDESC, RL_TYPE, RL_REF1, RL_STS\n"
                    + "FROM " + DBPRD + ".RECEIPT_LOCAFNC \n"
                    + "WHERE RL_CONO = '" + CONO + "'\n"
                    + "AND RL_DIVI = '" + DIVI.trim() + "'\n"
                    + "AND RL_STS = '20'\n"
                    + ") B ON B.RL_CONO = A.HR_CONO AND B.RL_DIVI = A.HR_DIVI AND B.RL_TYPE =  A.HC_TYPE AND A.HC_LOCATION = B.RL_LCCODE";

            RsDataReceipt = sta.executeQuery(Sql1);

            System.out.println("ssssssssssss)");

            System.out.println(CONO);
            System.out.println(DIVI);
            System.out.println(RCNO);
            System.out.println(Sql1);

            System.out.println("ssssssssssssssss)");
                 System.out.println(Sql1);

            return RsDataReceipt;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;

    }

    public ResultSet GetDetailDEPOSIT(int CONO, String DIVI, String INVNO, String INVDT) {

        try {
            ResultSet RsDataReceipt;
            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();

            String Sql1 = "select a.uacono,a.uaivdt,a.uacuno,substr(a.okcunm,1,30) okcunm,a.uapyno,a.uaivno,a.uhivla,\n"
                    + "CASE WHEN substr(a.okcunm,1,30) LIKE '%เงินสด%' THEN trim(a.uacuno) || ' ' || trim(l.tltx60) ELSE substr(a.okcunm,1,30) END AS VCTXT\n"
                    + ",COALESCE(l.tltx60,'-/-') AS Remark\n"
                    + "from (\n"
                    + "SELECT  distinct oapotx,uacono,uaivdt,uaortp,uacuno,substr(okcunm,1,30) okcunm,uapyno,uhdudt,uatepy,uaorno,uaivno,uhivla,oksmcd,uawhlo,okcucl   \n"
                    + "FROM " + M3DB + ".odhead o," + M3DB + ".oinvoh h," + M3DB + ".ocusma," + M3DB + ".oohead   \n"
                    + "WHERE uaivdt = '" + INVDT.trim() + "'\n"
                    + "and o.uaivno = h.uhivno   \n"
                    + "and o.uayea4 = h.uhyea4   \n"
                    + "AND UACONO = '" + CONO + "'   \n"
                    + "AND UACONO = uhcono\n"
                    + "AND uacono = oacono\n"
                    + "AND uaorno = oaorno\n"
                    + "AND UACONO = okcono   \n"
                    + "AND uacuno = okcuno   \n"
                    + "AND UAIVNO in('" + INVNO.trim() + "') \n"
                    //                    + "AND UAIVNO in('7390810') \n"
                    + "AND substr(uaortp,3,1) IN ('9') --Credit Note    \n"
                    + "AND uawhlo IN ('A11','A80','A81','A82','A83','A84','A85','A86','A87','F11','F31')         \n"
                    + ") a  \n"
                    + "LEFT JOIN  " + M3DB + ".OSYTXL AS L ON  L.TLLINO = 1 and L.tlcono = a.UACONO  AND a.oapotx = L.tltxid     \n"
                    + "ORDER BY uaivdt,uaortp";

            System.out.println(" Get DT DEPOSIT " + Sql1);
            RsDataReceipt = sta.executeQuery(Sql1);

            return RsDataReceipt;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;

    }

}
