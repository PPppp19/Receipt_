/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.api.data;

import com.br.api.connect.ConnectDB2;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    
import java.util.Locale;

/**
 *
 * @author Wattana
 */
public class InsertDB2 {

    public static String DBPRD = GBVAR.DBPRD;
//    public static String DBPRD = "M3FDBTST";

    public static void InsertHeader(String HR_CONO, String HR_DIVI, String HC_TRDT, String HC_PYNO, String HC_USER, String HC_REAMT,
            String HC_TYPE, String HC_PYDT, String HC_CHKNO, String HC_ACCODE, String HC_BANK, String HR_BKCHG, String HC_LOCATION) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();
        SelectDB2 SDB2 = new SelectDB2();
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
//                String query = "INSERT INTO BRLDTA0100.HR_RECEIPT\n"
//                        + "(HR_CONO, HR_DIVI, HC_RCNO, HC_TRDT, HC_PYNO, HC_USER, HC_STS,HC_REAMT, HC_TYPE,  HC_PYDT,  HC_CHKNO,  HC_BANK , HC_ACCODE , HR_BKCHG , HC_LOCATION)\n"
//                        + "VALUES('" + HR_CONO.trim() + "', '" + HR_DIVI.trim() + "', (SELECT TRIM(TO_CHAR(max(HC_RCNO)+1,'00000000')) AS HC_RCNO\n"
//                        + "FROM BRLDTA0100.HR_RECEIPT \n"
//                        + "WHERE HR_CONO = '" + HR_CONO + "'\n"
//                        + "AND HR_DIVI = '" + HR_DIVI + "'), '" + HC_TRDT.replaceAll("-", "").trim() + "', '" + HC_PYNO.trim() + "', '" + HC_USER.trim() + "', '1'"
//                        + ",'" + HC_REAMT.trim() + "', '" + HC_TYPE.trim() + "','" + HC_PYDT.replaceAll("-", "").trim() + "','" + HC_CHKNO.trim() + "','" + HC_BANK.trim() + "', '" + HC_ACCODE.trim() + "' , '" + HR_BKCHG.trim() + "' , '" + HC_LOCATION.trim() + "')";
//                System.err.println(query

//                String year = HC_TRDT.toString().replaceAll("-", "").substring(2, 4);
//                int yearint = Integer.parseInt(year);
//                
//                   String getrcquery = "SELECT CASE WHEN CAST(MAX(HC_RCNO) AS DECIMAL(10,0)) > 0 THEN CAST(MAX(HC_RCNO) AS DECIMAL(10,0)) + 1\n"
//                    + "ELSE CAST(('"+yearint+"' || '000001') AS DECIMAL(10,0)) END AS HC_RCNO\n"
//                    + "FROM M3FDBTST.HR_RECEIPT hr \n"
//                    + "WHERE HR_CONO  = '"+HR_CONO.trim()+"'\n"
//                    + "AND HR_DIVI  = '"+HR_DIVI.trim()+"'\n"
//                    + "AND SUBSTRING(HC_RCNO,0,3) = '"+yearint+"'";
                String query = "INSERT INTO " + DBPRD + ".HR_RECEIPT\n"
                        + "(HR_CONO, HR_DIVI, HC_RCNO, HC_TRDT, HC_PYNO, HC_USER, HC_STS,HC_REAMT, HC_TYPE,  HC_PYDT,  HC_CHKNO,  HC_BANK , HC_ACCODE , HR_BKCHG , HC_LOCATION)\n"
                        + "VALUES('" + HR_CONO.trim() + "', '" + HR_DIVI.trim() + "', (SELECT COALESCE(MAX(HC_RCNO)+1,SUBSTRING(REPLACE(CHAR(current date, ISO),'-',''),3,2) || '000001' ) AS HC_RCNO \n"
                        + "FROM " + DBPRD + ".HR_RECEIPT\n"
                        + "WHERE HR_CONO = '" + HR_CONO.trim() + "'\n"
                        + " AND  SUBSTRING(HC_RCNO,1,1) != '3' AND HR_DIVI = '" + HR_DIVI.trim() + "'\n"
                        + "AND SUBSTRING(HC_TRDT,3,2) = SUBSTRING(REPLACE(CHAR(current date, ISO),'-',''),3,2)), '" + HC_TRDT.replaceAll("-", "").trim() + "', '" + HC_PYNO.trim() + "', '" + HC_USER.trim() + "', '1'"
                        + ",'" + HC_REAMT.trim() + "', '" + HC_TYPE.trim() + "','" + HC_PYDT.replaceAll("-", "").trim() + "','" + HC_CHKNO.trim() + "','" + HC_BANK.trim() + "', '" + HC_ACCODE.trim() + "' , '" + HR_BKCHG.trim() + "' , '" + HC_LOCATION.trim() + "')";
                stmt.execute(query);
                System.out.println(query);
            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    public static void UPDATECCLIST(String CCRCNO, String CCBY, String CCCONO, String CCDIVI, String CCRNNO, String CCREASON) throws Exception {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        Connection conn = ConnectDB2.ConnectionDB();
        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "INSERT  INTO " + DBPRD + ".RECEIPT_CCLIST\n"
                        + "(CC_RNNO,CC_RCNO,CC_REASON,CC_DATE,CC_BY,CC_CONO,CC_DIVI)\n"
                        + " VALUES ('" + CCRNNO.trim() + "','" + CCRCNO.trim() + "','" + CCREASON.trim() + "','" + dtf.format(now) + "','" + CCBY.trim() + "','" + CCCONO.trim() + "','" + CCDIVI.trim() + "')";
                System.out.println("CCCCCCCC" + query);
                stmt.execute(query);
            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    public static void InsertLine(String LR_CONO, String LR_DIVI, String LR_RCNO, String LR_INVNO, String LR_INVDT, String LR_INVAMT, String LR_REAMT) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();
        try {
            if (conn != null) {
                LR_REAMT = new DecimalFormat("0.00").format(Double.parseDouble(LR_REAMT.replaceAll(",", "").trim()));
                Statement stmt = conn.createStatement();
                String query = "INSERT INTO " + DBPRD + ".LR_LINERECEIPT\n"
                        + "(LR_CONO, LR_DIVI, LR_RCNO, LR_INVNO, LR_INVDT, LR_INVAMT, LR_REAMT, LR_STS)\n"
                        + "VALUES('" + LR_CONO.trim() + "', '" + LR_DIVI.trim() + "', '" + LR_RCNO.trim() + "', '" + LR_INVNO.trim() + "', '" + LR_INVDT.replaceAll("-", "").trim() + "',"
                        + "'" + LR_INVAMT.trim() + "', '" + LR_REAMT.trim() + "', '1')";
                System.out.println(query);
                stmt.execute(query);
            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    public static void InsertExpense(String LRE_CONO, String LRE_DIVI, String LRE_RCNO, String LRE_CODE, String LRE_ACTCODE, String LRE_AMT) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();
        SelectDB2 SDB2 = new SelectDB2();
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "INSERT INTO " + DBPRD + ".LR_LINEEXPEN\n"
                        + "(LRE_CONO, LRE_DIVI, LRE_RCNO, LRE_CODE, LRE_ACTCODE, LRE_AMT, LRE_REF1, LRE_REF2, LRE_REF3, LRE_VCTXT)\n"
                        + "VALUES('" + LRE_CONO.trim() + "', '" + LRE_DIVI.trim() + "', '" + LRE_RCNO.trim() + "', '" + LRE_CODE.trim() + "', '" + LRE_ACTCODE.trim() + "',"
                        + " '" + LRE_AMT.trim() + "', '', '', '', '')";
//                System.out.println(query);

                stmt.execute(query);
            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    public static void InsertExpensenew(String LRE_CONO, String LRE_DIVI, String LRE_RCNO, String LRE_CODE, String LRE_ACTCODE, String LRE_AMT, String FNNO) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();
        SelectDB2 SDB2 = new SelectDB2();
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "INSERT INTO " + DBPRD + ".LR_LINEEXPEN\n"
                        + "(LRE_CONO, LRE_DIVI, LRE_RCNO, LRE_CODE, LRE_ACTCODE, LRE_AMT, LRE_REF1, LRE_REF2, LRE_REF3, LRE_VCTXT, LRE_FNNO)\n"
                        + "VALUES('" + LRE_CONO.trim() + "', '" + LRE_DIVI.trim() + "', '" + LRE_RCNO.trim() + "', '" + LRE_CODE.trim() + "', '" + LRE_ACTCODE.trim() + "',"
                        + " '" + LRE_AMT.trim() + "', '', '', '', '', '" + FNNO.trim() + "')";
                System.out.println(query);

                stmt.execute(query);
            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    public static void InsertLineClear_EXPnew(String LREP_CONO, String LREP_DIVI, String LREP_RCNO, String LREP_FNNO, String LREP_INVNO, String LREP_INVDT, String LREP_INVAMT, String LREP_EXPAMT) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();
        try {
            if (conn != null) {
                LREP_EXPAMT = new DecimalFormat("0.00").format(Double.parseDouble(LREP_EXPAMT.replaceAll(",", "").trim()));
                Statement stmt = conn.createStatement();
                String query = "INSERT INTO " + DBPRD + ".LR_LINECLEAREXP\n"
                        + "(LREP_CONO, LREP_DIVI, LREP_RCNO,LREP_FNNO , LREP_INVNO, LREP_INVDT, LREP_INVAMT, LREP_EXPAMT, LREP_STS)\n"
                        + "VALUES('" + LREP_CONO.trim() + "', '" + LREP_DIVI.trim() + "', '" + LREP_RCNO.trim() + "','" + LREP_FNNO.trim() + "' , '" + LREP_INVNO.trim() + "', "
                        + " '" + LREP_INVDT.replaceAll("-", "").trim() + "', '" + LREP_INVAMT.trim() + "', '" + LREP_EXPAMT.trim() + "', '1')";
                System.out.println("insert" + query);
                stmt.execute(query);
            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    public static void InsertLineClear_EXP(String LREP_CONO, String LREP_DIVI, String LREP_RCNO, String LREP_INVNO, String LREP_INVDT, String LREP_INVAMT, String LREP_EXPAMT) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();
        try {
            if (conn != null) {
                LREP_EXPAMT = new DecimalFormat("0.00").format(Double.parseDouble(LREP_EXPAMT.replaceAll(",", "").trim()));
                Statement stmt = conn.createStatement();
                String query = "INSERT INTO " + DBPRD + ".LR_LINECLEAREXP\n"
                        + "(LREP_CONO, LREP_DIVI, LREP_RCNO, LREP_INVNO, LREP_INVDT, LREP_INVAMT, LREP_EXPAMT, LREP_STS)\n"
                        + "VALUES('" + LREP_CONO.trim() + "', '" + LREP_DIVI.trim() + "', '" + LREP_RCNO.trim() + "', '" + LREP_INVNO.trim() + "', "
                        + " '" + LREP_INVDT.replaceAll("-", "").trim() + "', '" + LREP_INVAMT.trim() + "', '" + LREP_EXPAMT.trim() + "', '1')";
                System.out.println("insert" + query);
                stmt.execute(query);
            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    public static void InsertPayerCode(String CONO, String DIVI, String HC_PYNO) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();
        String STATUS = checkPayer(CONO, DIVI, HC_PYNO);
        try {
            if (conn != null) {

                if (STATUS.equalsIgnoreCase("00")) {
                    Statement stmt = conn.createStatement();
                    String query = "INSERT INTO " + DBPRD + ".PC_PAYERCUSNO\n"
                            + "(PC_CONO, PC_DIVI, PC_PYNO, PC_STS)\n"
                            + "VALUES('" + CONO.trim() + "', '" + DIVI.trim() + "', '" + HC_PYNO.trim() + "', '20')";
                    stmt.execute(query);
                } else {
                    if (STATUS.equalsIgnoreCase("20")) {
                        STATUS = "10";
                    } else {
                        STATUS = "20";
                    }
                    Statement stmt = conn.createStatement();
                    String query = "UPDATE " + DBPRD + ".PC_PAYERCUSNO\n"
                            + "SET PC_STS= '" + STATUS.trim() + "' \n"
                            + "where PC_CONO = '" + CONO.trim() + "'  and PC_DIVI = '" + DIVI.trim() + "' and PC_PYNO = '" + HC_PYNO.trim() + "' ";
                    stmt.execute(query);
                }

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    public static void InsertReceiver(String RR_CONO, String RR_DIVI, String RR_RCNO, String RR_TYPE, String RR_DESC) throws Exception {
        System.out.println("ccccccccccccccccccccccccccccccccccccc");
        System.out.println(RR_CONO);
        System.out.println("ccccccccccccccccccccccccccccccccccccc");
        System.out.println(RR_DIVI);
        System.out.println("ccccccccccccccccccccccccccccccccccccc");
        System.out.println(RR_RCNO);
        System.out.println("ccccccccccccccccccccccccccccccccccccc");
        System.out.println(RR_TYPE);
        System.out.println("ccccccccccccccccccccccccccccccccccccc");
        System.out.println(RR_DESC);

        Connection conn = ConnectDB2.ConnectionDB();
        SelectDB2 SDB2 = new SelectDB2();
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "INSERT INTO " + DBPRD + ".RECEIPT_RECEIVER\n"
                        + "(RR_CONO, RR_DIVI, RR_RCNO, RR_DESC , RR_TYPE)\n"
                        + "VALUES('" + RR_CONO.trim() + "', '" + RR_DIVI.trim() + "', '" + RR_RCNO.trim() + "', '" + RR_DESC.trim() + "' , '" + RR_TYPE.trim() + "')";
                System.out.println(query);
                stmt.execute(query);
            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    public static String checkPayer(String CONO, String DIVI, String HC_PYNO) {
        String Status = "00";

        try {
            Connection conn = ConnectDB2.ConnectionDB();
            Statement stmt = conn.createStatement();
            String query = "SELECT PC_CONO, PC_DIVI, PC_PYNO, PC_STS "
                    + "FROM " + DBPRD + ".PC_PAYERCUSNO  "
                    + "WHERE PC_PYNO = '" + HC_PYNO.trim() + "' "
                    + "AND PC_CONO = '" + CONO.trim() + "' "
                    + "AND PC_DIVI = '" + DIVI.trim() + "'";
            ResultSet rsl = stmt.executeQuery(query);

            if (rsl.next()) {
                Status = rsl.getString("PC_STS");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return Status;
    }

    public static void SetSeries(String RR_CONO, String RR_DIVI, String RR_LCCODE, String RR_USER, String RR_START, String RR_END, String RR_COUNT) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "INSERT INTO " + DBPRD + ".RECEIPT_RUNNING\n"
                        + "(RR_CONO, RR_DIVI, RR_LCCODE, RR_USER, RR_START, RR_END, RR_COUNT)\n"
                        + "VALUES('" + RR_CONO.trim() + "', '" + RR_DIVI.trim() + "', '" + RR_LCCODE.trim() + "', '" + RR_USER.trim() + "', '" + RR_START.trim() + "', '" + RR_END.trim() + "', '" + RR_COUNT.trim() + "')";
                stmt.execute(query);
            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    public static void InsertCancel(String cono, String divi, String rcno, String reason, String action, String app1, String app2) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Statement stmt = conn.createStatement();
                String query = "INSERT INTO " + DBPRD + ".RECEIPT_CANCEL\n"
                        + "(RC_CONO, RC_DIVI, RC_RCNO, RC_CRDT, RC_REASON, RC_ACTION, RC_APP1, RC_APP2, RC_APPNOW,  RC_STS)\n"
                        + "VALUES('" + cono.trim() + "', '" + divi.trim() + "', '" + rcno.trim() + "', '" + timestamp + "', '" + reason.trim() + "', '" + action.trim() + "', '" + app1.trim() + "', '" + app2.trim() + "',"
                        + " '" + app1.trim() + "',  '20') ";
                stmt.execute(query);
            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }
}
