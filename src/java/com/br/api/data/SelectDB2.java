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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
//import javafx.scene.control.Alert;
import org.codehaus.jettison.json.JSONArray;

/**
 *
 * @author Wattana
 */
public class SelectDB2 {

    public static String DBPRD = GBVAR.DBPRD;
    public static String M3DB = GBVAR.M3DB;
//    public static String DBPRD = "M3FDBTST";

    public static boolean CHECKEXP(String CONO, String DIVI, String ID) throws Exception {

        boolean chksts = false;
        String sts = "0";
        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT   COALESCE(H_STS,'1') , H_RCNO  FROM " + DBPRD + ".HEAD_RECIPT\n"
                        + "WHERE H_RNNO  = '" + ID.trim() + "' AND H_CONO  = '" + CONO.trim() + "' AND H_DIVI  = '" + DIVI.trim() + "' ORDER BY  H_RCNO  DESC";

                ResultSet mRes = stmt.executeQuery(query);

                System.out.println(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

                    sts = mRes.getString("H_TYPE");

                }

                if (sts.equalsIgnoreCase("TRANSFER_EXP")) {

                    chksts = true;
                } else if (sts.equalsIgnoreCase("CHECK_EXP")) {

                    chksts = true;
                } else if (sts.equalsIgnoreCase("CASH_EXP")) {

                    chksts = true;
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

        return chksts;

    }

    public static boolean CHECKSTS(String CONO, String DIVI, String ID) throws Exception {

        boolean chksts = false;
        String sts = "0";
        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT DISTINCT   COALESCE(H_STS,'1') AS H_STS , H_RCNO FROM " + DBPRD + ".HEAD_RECIPT hr \n"
                        + "WHERE H_RNNO  = '" + ID.trim() + "' AND H_CONO = '" + CONO.trim() + "' AND H_DIVI = '" + DIVI.trim() + "' ORDER BY H_RCNO DESC";

                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

                    sts = mRes.getString("H_STS");

                }

                if (!sts.equalsIgnoreCase("3")) {

                    chksts = true;
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

        return chksts;

    }

    public static JSONArray MONITORINGRC(String CONO, String DIVI, String FNNO) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT COALESCE(HR_CONO,'-'),COALESCE(HR_DIVI,'-'),\n"
                        + "COALESCE(HC_RCNO,'-'),COALESCE(HC_TRDT,'-'),\n"
                        + "COALESCE(HC_PYNO,'-'),COALESCE(HC_REAMT,'-'),\n"
                        + "COALESCE(HC_TYPE,'-'),COALESCE(HC_BANK,'-'),\n"
                        + "COALESCE(HC_ACCODE,'-'),COALESCE(HC_PYDT,'-'),\n"
                        + "COALESCE(HC_CHKNO,'-'),COALESCE(HC_USER,'-'),\n"
                        + "COALESCE(HC_VCNO,'-'),COALESCE(HC_STS,'-'),\n"
                        + "COALESCE(HR_BKCHG,'-'),COALESCE(HC_LOCATION,'-'),\n"
                        + "COALESCE(HC_FIXNO,'-'),COALESCE(HC_FNNO,'-')\n"
                        + "FROM  " + DBPRD + ".HR_RECEIPT hr \n"
                        + "WHERE HC_FNNO  = '" + FNNO.trim() + "'\n"
                        + "AND HR_CONO  = '" + CONO.trim() + "'\n"
                        + "AND HR_DIVI  = '" + DIVI.trim() + "'";
                ResultSet mRes = stmt.executeQuery(query);

                System.out.println(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

                    mMap.put("HR_CONO", mRes.getString(1).trim());
                    mMap.put("HR_DIVI", mRes.getString(2).trim());
                    mMap.put("HC_RCNO", mRes.getString(3).trim());
                    mMap.put("HC_TRDT", mRes.getString(4).trim());
                    mMap.put("HC_PYNO", mRes.getString(5).trim());
                    mMap.put("HC_REAMT", mRes.getString(6).trim());
                    mMap.put("HC_TYPE", mRes.getString(7).trim());
                    mMap.put("HC_BANK", mRes.getString(8).trim());
                    mMap.put("HC_ACCODE", mRes.getString(9).trim());
                    mMap.put("HC_PYDT", mRes.getString(10).trim());
                    mMap.put("HC_CHKNO", mRes.getString(11).trim());
                    mMap.put("HC_USER", mRes.getString(12).trim());
                    mMap.put("HC_VCNO", mRes.getString(13).trim());
                    mMap.put("HC_STS", mRes.getString(14).trim());
                    mMap.put("HR_BKCHG", mRes.getString(15).trim());
                    mMap.put("HC_LOCATION", mRes.getString(16).trim());
                    mMap.put("HC_FIXNO", mRes.getString(17).trim());
                    mMap.put("HC_FNNO", mRes.getString(18).trim());

                    mJSonArr.put(mMap);

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

        return mJSonArr;

    }

    
    public static JSONArray CCMONITORING(String CONO, String DIVI) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT CC_RNNO, CC_RCNO,CC_REASON, CC_DATE,CC_BY,CC_CONO,CC_DIVI FROM  " + DBPRD + ".RECEIPT_CCLIST \n"
                        + "WHERE  CC_CONO  = '" + CONO.trim() + "' AND CC_DIVI  = '" + DIVI.trim() + "' ORDER BY  CC_DATE DESC";
                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);


                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

                    mMap.put("CC_RNNO", mRes.getString(1).trim());
                    mMap.put("CC_RCNO", mRes.getString(2).trim());
                    mMap.put("CC_REASON", mRes.getString(3).trim());
                    mMap.put("CC_DATE", mRes.getString(4).trim());
                    mMap.put("CC_BY", mRes.getString(5).trim());
                    mMap.put("CC_CONO", mRes.getString(6).trim());
                    mMap.put("CC_DIVI", mRes.getString(7).trim());

                    mJSonArr.put(mMap);

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

        return mJSonArr;

    }
    public static JSONArray MONITORING(String CONO, String DIVI) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT DISTINCT  COALESCE(H_CONO,'-'),COALESCE(H_DIVI,'-'),COALESCE(H_RNNO,'-') as H_RNNO,COALESCE(H_PYNO,'-'),COALESCE(H_STS,'-'),COALESCE(H_VCNO,'-')  FROM  " + DBPRD + ".HEAD_RECIPT \n"
                        + "WHERE  H_CONO  = '" + CONO.trim() + "' AND H_DIVI  = '" + DIVI.trim() + "' ORDER BY  H_RNNO DESC";
                ResultSet mRes = stmt.executeQuery(query);

                System.out.println(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

                    mMap.put("H_CONO", mRes.getString(1).trim());
                    mMap.put("H_DIVI", mRes.getString(2).trim());
                    mMap.put("H_RNNO", mRes.getString(3).trim());
                    mMap.put("H_PYNO", mRes.getString(4).trim());
                    mMap.put("H_STS", mRes.getString(5).trim());
                    mMap.put("H_VCNO", mRes.getString(6).trim());

                    mJSonArr.put(mMap);

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

        return mJSonArr;

    }

    public static JSONArray CALLLOSTRC(String CONO, String DIVI, String ID) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
        try {
            if (conn != null) {

                System.out.println("CALLLOSTRC");
                Statement stmt = conn.createStatement();
                String query = "SELECT H_RCNO  FROM " + DBPRD + ".HEAD_RECIPT hr WHERE H_RNNO  = '" + ID.trim() + "' AND H_CONO  = '" + CONO.trim() + "' AND H_DIVI  = '" + DIVI.trim() + "' AND H_CUNO IS  NULL AND SUBSTRING(H_RCNO,1,1) != '9' OR H_RNNO  = '" + ID.trim() + "' AND H_CONO  = '" + CONO.trim() + "' AND H_DIVI  = '" + DIVI.trim() + "' AND H_CUNO = '' AND SUBSTRING(H_RCNO,1,1) != '9'";

                ResultSet mRes = stmt.executeQuery(query);

                System.out.println(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

                    mMap.put("H_RCNO", mRes.getString(1).trim());

                    mJSonArr.put(mMap);

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

        return mJSonArr;

    }

    public static JSONArray CALLLOSTRC2(String CONO, String DIVI, String ID) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
        try {
            if (conn != null) {

                System.out.println("CALLLOSTRC");
                Statement stmt = conn.createStatement();
                String query = "SELECT H_RCNO  FROM " + DBPRD + ".HEAD_RECIPT hr WHERE H_RNNO  = '" + ID.trim() + "' AND H_CONO  = '" + CONO.trim() + "' AND H_DIVI  = '" + DIVI.trim() + "' AND H_CUNO IS  NULL AND SUBSTRING(H_RCNO,1,1) != '9' OR H_RNNO  = '" + ID.trim() + "' AND H_CONO  = '" + CONO.trim() + "' AND H_DIVI  = '" + DIVI.trim() + "' AND H_CUNO = 'HEAD' AND SUBSTRING(H_RCNO,1,1) != '9'";

                ResultSet mRes = stmt.executeQuery(query);

                System.out.println(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

                    mMap.put("H_RCNO", mRes.getString(1).trim());

                    mJSonArr.put(mMap);

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

        return mJSonArr;

    }

    public static String SearchReturnReceipt(String CONO, String DIVI, String FNNO) throws Exception {

        System.out.println(CONO);
        System.out.println(DIVI);
        System.out.println(FNNO);

        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxx");

        String RS = "";
        Connection conn = ConnectDB2.ConnectionDB();
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT  * \n"
                        + "FROM (\n"
                        + "SELECT * FROM " + DBPRD + ".HEAD_RECIPT  \n"
                        + ") AS a \n"
                        + "LEFT JOIN \n"
                        + "(\n"
                        + "SELECT * FROM " + DBPRD + ".HR_RECEIPT \n"
                        + ") AS b\n"
                        + "ON a.H_RNNO  = b.HC_FNNO \n"
                        + "WHERE  a.h_RNNO = '" + FNNO.trim() + "'\n"
                        + "AND a.H_CONO = '" + CONO.trim() + "'\n"
                        + "AND a.H_DIVI = '" + DIVI.trim() + "'";

                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

                    RS = mRes.getString("RS");

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

        return RS;

    }

    public static String checkreturn(String CONO, String DIVI, String FNNO) throws Exception {

        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxx");

        System.out.println(CONO);
        System.out.println(DIVI);
        System.out.println(FNNO);

        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxx");

        String RS = "";
        Connection conn = ConnectDB2.ConnectionDB();
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT H_PYNO,\n"
                        + "CASE \n"
                        + "WHEN H_PYNO IS NULL   THEN 'RETURNED' WHEN H_PYNO =''  THEN 'RETURNED'\n"
                        + "ELSE 'SEARCH'\n"
                        + "END AS RS\n"
                        + "FROM  " + DBPRD + ".HEAD_RECIPT hr \n"
                        + "WHERE H_RNNO  = '" + FNNO.trim() + "'\n"
                        + "AND H_CONO = '" + CONO.trim() + "'\n"
                        + "AND H_DIVI  = '" + DIVI.trim() + "'  ORDER BY RS  ";

                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

                    RS = mRes.getString("RS");

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

        return RS;

    }

    public static boolean CHKISBM(String CONO, String DIVI, String ID) throws Exception {

        int ccountbm = 0;
        boolean isBM = false;
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT  COUNT(BM_FNNO) AS CCBANK_MAPPING  FROM " + DBPRD + ".BANK_MAPPING bm  \n"
                        + "WHERE  BM_FNNO  = '" + ID.trim() + "' AND BM_CONO = '" + CONO.trim() + "' AND BM_DIVI  = '" + DIVI.trim() + "'";

                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    ccountbm = mRes.getInt("CCBANK_MAPPING");
                }

                if (ccountbm > 0) {
                    isBM = true;
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

        return isBM;

    }

    public static JSONArray SHOWCUSTOMER(String cono) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT OKCUNO \n"
                        + "FROM " + M3DB + ".OCUSMA\n"
                        + "WHERE  OKCUNO  LIKE '%TH%' \n"
                        + "AND OKCONO   = '" + cono.trim() + "' AND OKSTAT = '20' AND OKCUNO != ''";

                ResultSet mRes = stmt.executeQuery(query);

                System.out.println(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

                    mMap.put("OKCUNO", mRes.getString(1).trim());

                    mJSonArr.put(mMap);

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

        return mJSonArr;

    }

    public static boolean checkamt(String CONO, String DIVI, String RCNO, String ID) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        Statement stmt = conn.createStatement();

        Statement stmt1 = conn.createStatement();

        double BM_AMT = 0;
        double BM_BANKCHARGE = 0;

        double RCAMT = 0;

        boolean isEQUAL = false;

        try {
            if (conn != null) {

                String query0 = "SELECT  BM_AMOUNT,BM_BKCHARGE FROM " + DBPRD + ".BANK_MAPPING bm  \n"
                        + "WHERE BM_FNNO  = '" + ID.trim() + "' AND  BM_CONO  = '" + CONO.trim() + "'AND  BM_DIVI  = '" + DIVI.trim() + "'";

                System.out.println(query0);
                ResultSet mRes = stmt.executeQuery(query0);

                while (mRes.next()) {

                    if (mRes.getString(1) != null) {
                        BM_AMT = mRes.getDouble(1);
                    } else {
                        BM_AMT = 0;
                    }
                    if (mRes.getString(2) != null) {
                        BM_BANKCHARGE = mRes.getDouble(2);
                    } else {
                        BM_BANKCHARGE = 0;
                    }
                }

                String query1 = "SELECT HC_REAMT  FROM " + DBPRD + ".HR_RECEIPT hr \n"
                        + "WHERE HC_FNNO  = '" + ID.trim() + "'AND  HR_CONO  = '" + CONO.trim() + "'AND  HR_DIVI  = '" + DIVI.trim() + "'";

                System.out.println(query1);
                ResultSet mRes1 = stmt1.executeQuery(query1);

                while (mRes1.next()) {
                    if (mRes1.getString(1) != null) {
                        RCAMT = mRes1.getDouble(1);
                    } else {
                        RCAMT = 0;
                    }
                }

                System.out.println(BM_AMT);
                System.out.println(BM_BANKCHARGE);

                System.out.println((BM_AMT + BM_BANKCHARGE));
                System.out.println(RCAMT);
                double total_AMT = BM_AMT + BM_BANKCHARGE;

                String str_BMAMT = String.format("%.2f", total_AMT);
                String str_RCAMT = String.format("%.2f", RCAMT);

                if (str_BMAMT.equalsIgnoreCase(str_RCAMT)) {

                    isEQUAL = true;
                } else {
                    isEQUAL = false;

                }

                System.out.println(isEQUAL);
                return isEQUAL;

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

        return isEQUAL;

    }

    public static String Call_FNNO(String CONO, String DIVI, String RCNO) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        Statement stmt = conn.createStatement();
        String FNNO = "";
        try {
            if (conn != null) {

                String query0 = "SELECT H_RNNO \n"
                        + " FROM  " + DBPRD + ".HEAD_RECIPT hr \n"
                        + "WHERE H_RCNO  = '" + RCNO + "'\n"
                        + "AND H_CONO  = '" + CONO + "'\n"
                        + "AND H_DIVI  = '" + DIVI + "'";

                System.out.println(query0);
                ResultSet mRes = stmt.executeQuery(query0);

                while (mRes.next()) {
                    if (!mRes.getString("H_RNNO").equalsIgnoreCase("")) {
                        FNNO = mRes.getString("H_RNNO");
                    } else {
                        FNNO = "0";
                    }
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

        return FNNO;

    }

    public static int getcheckupdate(String cono, String divi, String fnno, String cuno, String pyno) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        Statement stmt = conn.createStatement();
        int count = 0;
        try {
            if (conn != null) {

                String query0 = "SELECT COUNT(H_RCNO) as check FROM   " + DBPRD + ".HEAD_RECIPT\n"
                        + "WHERE   SUBSTRING(H_RCNO,1,1) != '2' AND   SUBSTRING(H_RCNO,1,1) != '3' AND H_RCNO  != 'RUNO' AND H_RCNO  != 'RCNO'\n"
                        + "AND  H_RNNO  = '" + fnno + "'\n"
                        + "AND  H_PYNO  = '" + pyno.toUpperCase() + "' AND  H_CONO  = '" + cono + "' AND  H_DIVI  = '" + divi + "'\n"
                        + "AND H_CUNO  = 'HEAD'";

                System.out.println(query0);
                ResultSet mRes = stmt.executeQuery(query0);

                while (mRes.next()) {

                    count = mRes.getInt("check");

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

        return count;

    }

    public static String callrcno(String cono, String divi, String fnno, String cuno) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        Statement stmt = conn.createStatement();
        String RCNO = "";
        try {
            if (conn != null) {

                String query0 = "SELECT COALESCE(NULLIF(H_RCNO,'RCNO'),'-') AS RCNO  FROM  " + DBPRD + ".HEAD_RECIPT hr\n"
                        + "WHERE H_RNNO  = '" + fnno + "'\n"
                        + "AND H_CUNO  = '" + cuno + "'\n"
                        + "AND H_CONO  = '" + cono + "'\n"
                        + "AND H_DIVI  = '" + divi + "' AND H_RCNO != 'RCNO' AND H_RCNO != '' AND H_RCNO is NOT NULL";

                System.out.println(query0);
                ResultSet mRes = stmt.executeQuery(query0);

                while (mRes.next()) {
                    if (!mRes.getString("RCNO").equalsIgnoreCase("")) {
                        RCNO = mRes.getString("RCNO");
                    } else {
                        RCNO = "0";
                    }
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

        return RCNO;

    }

    public static JSONArray Grid_ARS200bycus(String cono, String divi, String payer, String rcno, String customer) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
//        payer = "TH01010016";
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT C.*,SUBSTR(D.ESIVDT,1,4) || '-' || SUBSTR(D.ESIVDT,5,2)|| '-' || SUBSTR(D.ESIVDT,7,2) as ESIVDT, C.Balance as RECEIVE \n"
                        + "FROM \n"
                        + "(\n"
                        + "SELECT A.ESPYNO,A.ESCUNO,A.ESCINO,A.ESINYR,A.ESCUCD,A.ESCUAM,COALESCE(b.LineAMT,0) AS LineAMT,(COALESCE(A.ESCUAM,0) - COALESCE(b.LineAMT,0)) AS Balance\n"
                        + "FROM \n"
                        + "(\n"
                        + "SELECT ESPYNO,ESCUNO,ESCINO,ESINYR,ESCUCD,SUM(ESCUAM) as ESCUAM\n"
                        + "FROM " + M3DB + ".FSLEDG\n"
                        + "WHERE ESCONO = '" + cono.trim() + "'\n"
                        + "AND ESDIVI = '" + divi.trim() + "'\n"
                        + "AND ESPYNO = '" + payer.trim() + "'\n"
                        + "AND ESCUNO = '" + customer.trim() + "' \n"
                        + "AND ESCINO NOT IN (SELECT LR_INVNO\n"
                        + "FROM " + DBPRD + ".HR_RECEIPT ," + DBPRD + ".LR_LINERECEIPT \n"
                        + "WHERE HC_STS = '2' \n"
                        + "AND HC_PYNO = '" + payer.trim() + "'\n"
                        + "AND HC_RCNO  = LR_RCNO AND LR_INVAMT - LR_REAMT = 0) \n"
                        + "GROUP BY ESPYNO,ESCUNO,ESCINO,ESINYR,ESCUCD\n"
                        + "HAVING  SUM(ESCUAM) <> 0\n"
                        + ") A LEFT JOIN (\n"
                        + "SELECT LR_INVNO,sum(LR_REAMT) AS LineAMT \n"
                        + "FROM " + DBPRD + ".LR_LINERECEIPT\n"
                        + "WHERE LR_CONO = '" + cono.trim() + "'\n"
                        + "AND LR_DIVI = '" + divi.trim() + "'\n"
                        + "AND LR_RCNO = '" + rcno.trim() + "' \n"
                        + "GROUP BY LR_INVNO,LR_RCNO\n"
                        + ") B ON B.LR_INVNO = A.ESCINO\n"
                        + ") C LEFT JOIN (\n"
                        + "  SELECT  DISTINCT ESPYNO,ESCUNO,ESCINO,ESIVDT,ESINYR,ESCUCD\n"
                        + "FROM " + M3DB + ".FSLEDG \n"
                        + "WHERE ESCONO =  '" + cono.trim() + "'\n"
                        + "AND ESDIVI = '" + divi.trim() + "'\n"
                        + ") D ON D.ESCUNO = C.ESCUNO AND D.ESCINO = C.ESCINO\n ";
//                        + "ORDER BY ESIVDT asc";

                ResultSet mRes = stmt.executeQuery(query);

                System.out.println("xxxxxxxxxxxx AGS 2000000000000 xxxxxxxxxxxx");

                System.out.println(query);

                System.out.println("xxxxxxxxxxxxxxxxxxxxxxxx");

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

                    Double CheckData = mRes.getDouble(8);
                    if (CheckData != 0.00) {
                        mMap.put("ESPYNO", mRes.getString(1).trim());
                        mMap.put("ESCUNO", mRes.getString(2).trim());
                        mMap.put("ESCINO", mRes.getString(3).trim());
                        mMap.put("ESINYR", mRes.getString(4).trim());
                        mMap.put("ESCUCD", mRes.getString(5).trim());
                        mMap.put("ESCUAM", mRes.getString(6).trim());
                        mMap.put("LineAMT", mRes.getString(7).trim());
                        mMap.put("Balance", mRes.getString(8).trim());
                        mMap.put("ESIVDT", mRes.getString(9).trim());
                        mMap.put("RECEIVE", mRes.getString(10).trim());

                        mJSonArr.put(mMap);
                    }

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

        return mJSonArr;

    }

    public static JSONArray Grid_ARS200_cus_CLEAREXPENSE(String cono, String divi, String payer, String FNNO) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
//        payer = "TH01010016";
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();

                String query = "SELECT H_CUNO  FROM " + DBPRD + ".HEAD_RECIPT hr  \n"
                        + "WHERE H_RNNO  = '" + FNNO + "'\n"
                        + "AND H_PYNO  = '" + payer + "'\n"
                        + "AND H_CONO  = '" + cono + "' and H_STS = '3' \n"
                        + "AND H_DIVI  = '" + divi + "'";

                ResultSet mRes = stmt.executeQuery(query);

                System.out.println("cusxxxxxxxxx    xxxxxxxxxxxxxxx");

                System.out.println(query);

                System.out.println("cusxxxxxxxxx    xxxxxxxxxxxxxxx");

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

                    mMap.put("H_CUNO", mRes.getString(1).trim());

                    mJSonArr.put(mMap);

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

        return mJSonArr;

    }

    public static JSONArray Grid_ARS200_cus(String cono, String divi, String payer, String rcno, String FNNO) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
//        payer = "TH01010016";
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();

//                String query = "SELECT DISTINCT  ESCUNO   \n"
//                        + "FROM "+DBPRD+".FSLEDG\n"
//                        + "WHERE ESCONO = '"+cono.trim()+"'\n"
//                        + "AND ESDIVI = '"+divi.trim()+"'\n"
//                        + "AND ESPYNO = '"+payer.trim()+"'"; 
                String query = "SELECT DISTINCT  c.ESCUNO as ESCUNO,  HC_RCNO\n"
                        + "FROM \n"
                        + "(\n"
                        + "SELECT A.ESPYNO,A.ESCUNO,A.ESCINO,A.ESINYR,A.ESCUCD,A.ESCUAM,COALESCE(b.LineAMT,0) AS LineAMT,(COALESCE(A.ESCUAM,0) - COALESCE(b.LineAMT,0)) AS Balance\n"
                        + "FROM \n"
                        + "(\n"
                        + "SELECT ESPYNO,ESCUNO,ESCINO,ESINYR,ESCUCD,SUM(ESCUAM) as ESCUAM\n"
                        + "FROM " + M3DB + ".FSLEDG\n"
                        + "WHERE ESCONO = '" + cono.trim() + "'\n"
                        + "AND ESDIVI = '" + divi.trim() + "'\n"
                        + "AND ESPYNO = '" + payer.trim() + "'\n"
                        + "AND ESCINO NOT IN (SELECT LR_INVNO\n"
                        + "FROM " + DBPRD + ".HR_RECEIPT ," + DBPRD + ".LR_LINERECEIPT \n"
                        + "WHERE HC_STS = '2' \n"
                        + "AND HC_PYNO = '" + payer.trim() + "'\n"
                        + "AND HC_RCNO  = LR_RCNO ) \n"
                        + "GROUP BY ESPYNO,ESCUNO,ESCINO,ESINYR,ESCUCD\n"
                        + "HAVING  SUM(ESCUAM) <> 0\n"
                        + ") A LEFT JOIN (\n"
                        + "SELECT LR_INVNO,sum(LR_REAMT) AS LineAMT \n"
                        + "FROM " + DBPRD + ".LR_LINERECEIPT\n"
                        + "WHERE LR_CONO = '" + cono.trim() + "'\n"
                        + "AND LR_DIVI = '" + divi.trim() + "'\n"
                        + "AND LR_RCNO = '" + rcno.trim() + "' \n"
                        + "GROUP BY LR_INVNO,LR_RCNO\n"
                        + ") B ON B.LR_INVNO = A.ESCINO\n"
                        + ") C LEFT JOIN (\n"
                        + "  SELECT  DISTINCT ESPYNO,ESCUNO,ESCINO,ESIVDT,ESINYR,ESCUCD\n"
                        + "FROM " + M3DB + ".FSLEDG \n"
                        + "WHERE ESCONO =  '" + cono.trim() + "'\n"
                        + "AND ESDIVI = '" + divi.trim() + "'\n"
                        + ") D ON D.ESCUNO = C.ESCUNO AND D.ESCINO = C.ESCINO \n"
                        + "LEFT JOIN \n"
                        + "(\n"
                        + "SELECT  HC_RCNO,b.H_CUNO  FROM  " + DBPRD + ".HR_RECEIPT a, " + DBPRD + ".HEAD_RECIPT b\n"
                        + "WHERE HC_FNNO = '" + FNNO + "'\n"
                        + "AND b.H_RNNO  =  a.HC_FNNO AND b.H_CONO  = a.HR_CONO AND b.H_DIVI  = a.HR_DIVI AND b.H_PYNO  = a.HC_PYNO AND b.H_RCNO  = a.HC_RCNO\n"
                        + ") HR\n"
                        + "ON  hr.H_CUNO = D.ESCUNO\n"
                        + "ORDER  BY  HR.HC_RCNO ,ESCUNO \n ";
//                        + "ORDER BY ESIVDT asc";

                ResultSet mRes = stmt.executeQuery(query);

                System.out.println("cusxxxxxxxxx    xxxxxxxxxxxxxxx");

                System.out.println(query);

                System.out.println("cusxxxxxxxxx    xxxxxxxxxxxxxxx");

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

                    mMap.put("ESCUNO", mRes.getString(1).trim());

                    mJSonArr.put(mMap);

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

        return mJSonArr;

    }

    public static JSONArray getrctoddl2keyback(String cono, String divi, String FNNO) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT  H_RCNO  FROM " + DBPRD + ".HEAD_RECIPT hr  \n"
                        + "WHERE  H_STS  = '1' and H_RNNO = '" + FNNO + "' AND H_CUNO  != 'HEAD'";

                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

                    mMap.put("H_RCNO", mRes.getString(1).trim());

                    mJSonArr.put(mMap);

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

        return mJSonArr;

    }

    public static JSONArray getrctoddl2(String cono, String divi, String FNNO) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT  H_RCNO  FROM " + DBPRD + ".HEAD_RECIPT hr  \n"
                        + "WHERE  H_STS  = '2' and H_RNNO = '" + FNNO + "' AND H_CUNO  != 'HEAD'";

                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

                    mMap.put("H_RCNO", mRes.getString(1).trim());

                    mJSonArr.put(mMap);

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

        return mJSonArr;

    }

    public static JSONArray getrctoddl(String cono, String divi, String FNNO) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT  H_RCNO  FROM " + DBPRD + ".HEAD_RECIPT hr  \n"
                        + "WHERE  H_STS  IN ('1','3') and H_RNNO = '" + FNNO + "' AND H_CUNO  != 'HEAD' and h_cono = '" + cono + "' and h_DIVI = '" + divi + "'";

                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

                    mMap.put("H_RCNO", mRes.getString(1).trim());

                    mJSonArr.put(mMap);

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

        return mJSonArr;

    }
    
    
     public static String Getrcno(String cono, String divi, String FNNO, String CUNO, String PYNO, String HC_TRDT, String HC_REAMT, String HC_TYPE, String HC_PYDT,
            String HC_CHKNO, String HC_BANK, String HC_ACCODE, String HC_USER, String HR_BKCHG, String locations) throws Exception {

        System.out.println("******************************x");
        System.out.println(cono);
        System.out.println(divi);
        System.out.println(FNNO);
        System.out.println(CUNO);
        System.out.println(PYNO);

        System.out.println(HC_TRDT);
        System.out.println(HC_REAMT);
        System.out.println(HC_TYPE);
        System.out.println(HC_PYDT);
        System.out.println(HC_CHKNO);
        System.out.println(HC_BANK);
        System.out.println(HC_ACCODE);
        System.out.println(HC_USER);
        System.out.println(HR_BKCHG);
        System.out.println(locations);

        System.out.println("******************************");

        String stats = "";
        JSONArray mJSonArr = new JSONArray();

        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        Statement stmt = conn.createStatement();
        String RCNO = "";
        Double Amt_Include_BKCHG = Double.parseDouble(HC_REAMT) + Double.parseDouble(HR_BKCHG);
        int check = SelectDB2.getcheckupdate(cono, divi, FNNO, CUNO, PYNO);
        String query = "";
        System.out.println("xxxxxxxnumnum \n" + check);
        try {

            
                        String year = HC_TRDT.toString().replaceAll("-", "").substring(2, 4);

            if(HC_TYPE.equalsIgnoreCase("TRANSFER") ||HC_TYPE.equalsIgnoreCase("TRANSFER_EXP") ||HC_TYPE.equalsIgnoreCase("TRANSFER_DEPOSIT")){
            
                            year = HC_PYDT.toString().replaceAll("-", "").substring(2, 4);

            }
     
            

            int yearint = Integer.parseInt(year);

            String getrcquery = "SELECT CASE WHEN CAST(MAX(HC_RCNO) AS DECIMAL(10,0)) > 0 THEN CAST(MAX(HC_RCNO) AS DECIMAL(10,0)) + 1\n"
                    + "ELSE CAST(('"+yearint+"' || '000001') AS DECIMAL(10,0)) END AS HC_RCNO\n"
                    + "FROM "+DBPRD+".HR_RECEIPT hr \n"
                    + "WHERE HR_CONO  = '"+cono.trim()+"'\n"
                    + "AND HR_DIVI  = '"+divi.trim()+"'\n"
                    + "AND SUBSTRING(HC_RCNO,0,3) = '"+yearint+"'";
            
            System.out.println("get=-----------" + getrcquery);

            if (conn != null) {

                if (check != 0) {

                    query = "UPDATE  " + DBPRD + ".HEAD_RECIPT  \n"
                            + "SET H_TYPE = '" + HC_TYPE + "', H_CUNO = '" + CUNO.toUpperCase() + "',H_STS = '1' ,  H_RCNO  = (" + getrcquery + ") , H_LOCATION = '" + locations + "'\n"
                            + "WHERE H_RNNO  = '" + FNNO + "'\n"
                            + "AND H_CUNO  = 'HEAD' \n"
                            + "AND H_PYNO  = '" + PYNO.toUpperCase() + "'";

                } else {

                    query = "INSERT INTO " + DBPRD + ".HEAD_RECIPT (H_CONO, H_DIVI, H_RNNO, H_CUNO, H_PYNO, H_RCNO, H_STS, H_TYPE, H_LOCATION)\n"
                            + "VALUES ('" + cono + "', '" + divi + "', '" + FNNO + "', '" + CUNO.toUpperCase() + "', '" + PYNO.toUpperCase()
                            + "',(" + getrcquery + "), '1' , '" + HC_TYPE + "','" + locations + "')";

//                      query = "INSERT INTO " + DBPRD + ".HEAD_RECIPT (H_CONO, H_DIVI, H_RNNO, H_CUNO, H_PYNO, H_RCNO, H_STS)\n"
//                            + "VALUES ('" + cono + "', '" + divi + "', '" + FNNO + "', '" + CUNO.toUpperCase() + "', '" + PYNO.toUpperCase()
//                            + "',(SELECT COALESCE(MAX(HC_RCNO)+1,SUBSTRING(REPLACE(CHAR("+HC_TRDT+"),'-',''),3,2) || '000001' ) AS H_RCNO FROM " + DBPRD + ".HR_RECEIPT WHERE  SUBSTRING(HC_RCNO,1,1) != '3'), '1')";
                }

                System.out.println("xxxxxxx \n" + query);

                stmt.execute(query);

                String query0 = "SELECT  H_RCNO   FROM " + DBPRD + ".HEAD_RECIPT WHERE  H_RNNO = '" + FNNO + "' AND  H_CUNO = '" + CUNO + "' AND H_PYNO = '" + PYNO + "' AND H_CONO = '" + cono + "' AND H_DIVI = '" + divi + "' ";

                System.out.println("query0" + query0);

                ResultSet mRes = stmt.executeQuery(query0);

                while (mRes.next()) {
                    RCNO = mRes.getString("H_RCNO");
                    stats = mRes.getString("H_RCNO");
                }

                //todo 
//                if (locations.equalsIgnoreCase("FM") && HC_ACCODE == null && HC_BANK == null) {
//
//                    HC_ACCODE = "1AA2114";
//                    HC_BANK = "BBL";
//                }

                String query1 = "INSERT INTO " + DBPRD + ".HR_RECEIPT\n"
                        + "(HC_FNNO, HR_CONO, HR_DIVI, HC_RCNO, HC_TRDT, HC_PYNO, HC_USER, HC_STS,HC_REAMT, HC_TYPE,  HC_PYDT,  HC_CHKNO,  HC_BANK , HC_ACCODE , HR_BKCHG , HC_LOCATION)\n"
                        + "VALUES('" + FNNO + "','" + cono.trim() + "', '" + divi.trim() + "', '" + RCNO + "' \n"
                        + ", '" + HC_TRDT.replaceAll("-", "").trim() + "', '" + PYNO.trim() + "', '" + HC_USER.trim() + "', '2'"
                        + ",'" + Amt_Include_BKCHG + "', '" + HC_TYPE.trim() + "','" + HC_PYDT.replaceAll("-", "").trim() + "','" + HC_CHKNO.trim() + "','" + HC_BANK.trim() + "', '" + HC_ACCODE.trim() + "' , '" + HR_BKCHG.trim() + "' , '" + locations.trim() + "')";
                System.out.println("CheckINVSTS \n" + query1);
                stmt.execute(query1);

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

        return stats;

    }

    public static String GetrcnoPREVIOUS(String cono, String divi, String FNNO, String CUNO, String PYNO, String HC_TRDT, String HC_REAMT, String HC_TYPE, String HC_PYDT,
            String HC_CHKNO, String HC_BANK, String HC_ACCODE, String HC_USER, String HR_BKCHG, String locations) throws Exception {

        System.out.println("******************************x");
        System.out.println(cono);
        System.out.println(divi);
        System.out.println(FNNO);
        System.out.println(CUNO);
        System.out.println(PYNO);

        System.out.println(HC_TRDT);
        System.out.println(HC_REAMT);
        System.out.println(HC_TYPE);
        System.out.println(HC_PYDT);
        System.out.println(HC_CHKNO);
        System.out.println(HC_BANK);
        System.out.println(HC_ACCODE);
        System.out.println(HC_USER);
        System.out.println(HR_BKCHG);
        System.out.println(locations);

        System.out.println("******************************");

        String stats = "";
        JSONArray mJSonArr = new JSONArray();

        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        Statement stmt = conn.createStatement();
        String RCNO = "";
        Double Amt_Include_BKCHG = Double.parseDouble(HC_REAMT) + Double.parseDouble(HR_BKCHG);
        int check = SelectDB2.getcheckupdate(cono, divi, FNNO, CUNO, PYNO);
        String query = "";
        System.out.println("xxxxxxxnumnum \n" + check);
        try {

            
                        String year = HC_TRDT.toString().replaceAll("-", "").substring(2, 4);

          
     
            

            int yearint = Integer.parseInt(year);

            String getrcquery = "SELECT CASE WHEN CAST(MAX(HC_RCNO) AS DECIMAL(10,0)) > 0 THEN CAST(MAX(HC_RCNO) AS DECIMAL(10,0)) + 1\n"
                    + "ELSE CAST(('"+yearint+"' || '000001') AS DECIMAL(10,0)) END AS HC_RCNO\n"
                    + "FROM "+DBPRD+".HR_RECEIPT hr \n"
                    + "WHERE HR_CONO  = '"+cono.trim()+"'\n"
                    + "AND HR_DIVI  = '"+divi.trim()+"'\n"
                    + "AND SUBSTRING(HC_RCNO,0,3) = '"+yearint+"'";
            
            System.out.println("get=-----------" + getrcquery);

            if (conn != null) {

                if (check != 0) {

                    query = "UPDATE  " + DBPRD + ".HEAD_RECIPT  \n"
                            + "SET H_TYPE = '" + HC_TYPE + "', H_CUNO = '" + CUNO.toUpperCase() + "',H_STS = '1' ,  H_RCNO  = (" + getrcquery + ") , H_LOCATION = '" + locations + "'\n"
                            + "WHERE H_RNNO  = '" + FNNO + "'\n"
                            + "AND H_CUNO  = 'HEAD' \n"
                            + "AND H_PYNO  = '" + PYNO.toUpperCase() + "'";

                } else {

                    query = "INSERT INTO " + DBPRD + ".HEAD_RECIPT (H_CONO, H_DIVI, H_RNNO, H_CUNO, H_PYNO, H_RCNO, H_STS, H_TYPE, H_LOCATION)\n"
                            + "VALUES ('" + cono + "', '" + divi + "', '" + FNNO + "', '" + CUNO.toUpperCase() + "', '" + PYNO.toUpperCase()
                            + "',(" + getrcquery + "), '1' , '" + HC_TYPE + "','" + locations + "')";

//                      query = "INSERT INTO " + DBPRD + ".HEAD_RECIPT (H_CONO, H_DIVI, H_RNNO, H_CUNO, H_PYNO, H_RCNO, H_STS)\n"
//                            + "VALUES ('" + cono + "', '" + divi + "', '" + FNNO + "', '" + CUNO.toUpperCase() + "', '" + PYNO.toUpperCase()
//                            + "',(SELECT COALESCE(MAX(HC_RCNO)+1,SUBSTRING(REPLACE(CHAR("+HC_TRDT+"),'-',''),3,2) || '000001' ) AS H_RCNO FROM " + DBPRD + ".HR_RECEIPT WHERE  SUBSTRING(HC_RCNO,1,1) != '3'), '1')";
                }

                System.out.println("xxxxxxx \n" + query);

                stmt.execute(query);

                String query0 = "SELECT  H_RCNO   FROM " + DBPRD + ".HEAD_RECIPT WHERE  H_RNNO = '" + FNNO + "' AND  H_CUNO = '" + CUNO + "' AND H_PYNO = '" + PYNO + "' AND H_CONO = '" + cono + "' AND H_DIVI = '" + divi + "' ";

                System.out.println("query0" + query0);

                ResultSet mRes = stmt.executeQuery(query0);

                while (mRes.next()) {
                    RCNO = mRes.getString("H_RCNO");
                    stats = mRes.getString("H_RCNO");
                }

                //todo 
//                if (locations.equalsIgnoreCase("FM")) {
//
//                    HC_ACCODE = "1AA2114";
//                    HC_BANK = "BBL";
//                }

                String query1 = "INSERT INTO " + DBPRD + ".HR_RECEIPT\n"
                        + "(HC_FNNO, HR_CONO, HR_DIVI, HC_RCNO, HC_TRDT, HC_PYNO, HC_USER, HC_STS,HC_REAMT, HC_TYPE,  HC_PYDT,  HC_CHKNO,  HC_BANK , HC_ACCODE , HR_BKCHG , HC_LOCATION)\n"
                        + "VALUES('" + FNNO + "','" + cono.trim() + "', '" + divi.trim() + "', '" + RCNO + "' \n"
                        + ", '" + HC_TRDT.replaceAll("-", "").trim() + "', '" + PYNO.trim() + "', '" + HC_USER.trim() + "', '2'"
                        + ",'" + Amt_Include_BKCHG + "', '" + HC_TYPE.trim() + "','" + HC_PYDT.replaceAll("-", "").trim() + "','" + HC_CHKNO.trim() + "','" + HC_BANK.trim() + "', '" + HC_ACCODE.trim() + "' , '" + HR_BKCHG.trim() + "' , '" + locations.trim() + "')";
                System.out.println("CheckINVSTS \n" + query1);
                stmt.execute(query1);

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

        return stats;

    }

    public static int checkRC(String cono, String divi, String fnno, String cuno) throws Exception {

        int count = 0;
        JSONArray mJSonArr = new JSONArray();

        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        Statement stmt = conn.createStatement();

        try {
            if (conn != null) {

                String query = "SELECT COUNT(H_RCNO)  as  RCCOUNT   FROM  " + DBPRD + ".HEAD_RECIPT hr \n"
                        + "WHERE H_CONO  = '" + cono + "'\n"
                        + "AND H_DIVI  = '" + divi + "'\n"
                        + "AND H_PYNO  = '" + fnno + "' \n"
                        + "AND  H_CUNO  = '" + cuno + "'";
                System.out.println("checkRC \n" + query);

                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    count = mRes.getInt(1);
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

        return count;

    }

    public static String checkRCNO(String cono, String divi, String FNNO, String CUNO, String PYNO, String HC_TRDT, String HC_REAMT, String HC_TYPE, String HC_PYDT,
            String HC_CHKNO, String HC_BANK, String HC_ACCODE, String HC_USER, String HR_BKCHG, String HC_LOCATION) throws Exception {

        String text = "";
        JSONArray mJSonArr = new JSONArray();

        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);
        Statement stmt = conn.createStatement();

        try {
            if (conn != null) {

                String query = "SELECT  H_RCNO  as  RCCOUNT   FROM  " + DBPRD + ".HEAD_RECIPT hr \n"
                        + "WHERE H_CONO  = '" + cono + "'\n"
                        + "AND H_DIVI  = '" + divi + "'\n"
                        + "AND H_PYNO  = '" + FNNO + "' \n"
                        + "AND  H_CUNO  = '" + CUNO + "' limit 1 ";
                System.out.println("checkRC \n" + query);

                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    text = mRes.getString(1);
                }

                String query1 = "INSERT INTO " + DBPRD + ".HEAD_RECIPT (H_CONO, H_DIVI, H_RNNO, H_CUNO, H_PYNO, H_RCNO)\n"
                        + "VALUES ('" + cono + "', '" + divi + "', '" + FNNO + "', '" + CUNO + "', '" + PYNO + "','" + text + "')";
                System.out.println("CheckINVSTS \n" + query1);

                stmt.execute(query1);

                String query2 = "INSERT INTO " + DBPRD + ".HR_RECEIPT\n"
                        + "(HR_CONO, HR_DIVI, HC_RCNO, HC_TRDT, HC_PYNO, HC_USER, HC_STS,HC_REAMT, HC_TYPE,  HC_PYDT,  HC_CHKNO,  HC_BANK , HC_ACCODE , HR_BKCHG , HC_LOCATION)\n"
                        + "VALUES('" + cono.trim() + "', '" + divi.trim() + "', '" + text + "' \n"
                        + ", '" + HC_TRDT.replaceAll("-", "").trim() + "', '" + PYNO.trim() + "', '" + HC_USER.trim() + "', '1'"
                        + ",'" + HC_REAMT.trim() + "', '" + HC_TYPE.trim() + "','" + HC_PYDT.replaceAll("-", "").trim() + "','" + HC_CHKNO.trim() + "','" + HC_BANK.trim() + "', '" + HC_ACCODE.trim() + "' , '" + HR_BKCHG.trim() + "' , '" + HC_LOCATION.trim() + "')";
                stmt.execute(query);
                System.out.println("CheckINVSTS \n" + query2);

                stmt.execute(query2);

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

        return text;

    }

    public static String Getfncode(String cono, String divi, String pyno, String trdt) throws Exception {

        String stats = "";
        JSONArray mJSonArr = new JSONArray();

        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();

                String year = trdt.toString().replaceAll("-", "").substring(2, 4);
//
                int yearint = Integer.parseInt(year) + 43;
//                String query = "SELECT COALESCE(MAX(H_RNNO)+1,(SUBSTRING(REPLACE(CHAR('" + trdt.trim() + "'),'-',''),2,3)+43) || '000001' ) AS H_RNNO\n"
//                        + "FROM " + DBPRD + ".HEAD_RECIPT  where  H_CONO = '" + cono.trim() + "' AND H_DIVI = '" + divi.trim() + "' ";
//                System.out.println("CheckINVSTS \n" + query);

                String query = "SELECT CASE WHEN CAST(MAX(H_RNNO) AS DECIMAL(10,0)) > 0 THEN CAST(MAX(H_RNNO) AS DECIMAL(10,0)) + 1\n"
                        + "ELSE CAST(('" + yearint + "' || '000001') AS DECIMAL(10,0)) END AS H_RNNO\n"
                        + "FROM " + DBPRD + ".HEAD_RECIPT\n"
                        + "WHERE H_CONO  = '" + cono.trim() + "'\n"
                        + "AND H_DIVI  = '" + divi.trim() + "'\n"
                        + "AND SUBSTRING(H_RNNO,0,3) = '" + yearint + "'";

                System.out.println("CheckIDDDDDDDDDD \n" + query);

                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    stats = mRes.getString("H_RNNO");
                }

                String query1 = "INSERT INTO " + DBPRD + ".HEAD_RECIPT (H_CONO, H_DIVI, H_RNNO, H_CUNO, H_PYNO, H_RCNO)\n"
                        + "VALUES ('" + cono + "', '" + divi + "', '" + stats + "', 'HEAD', '" + pyno.toUpperCase() + "', 9 || " + stats + " )";
                System.out.println("insert FN  \n" + query1);
                stmt.execute(query1);

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

        return stats;

    }

    public static String CheckINVSTS(String cono, String divi, String invno) throws Exception {

        String stats = "";
        JSONArray mJSonArr = new JSONArray();

        Connection conn = ConnectDB2.ConnectionDB();
        conn.setAutoCommit(true);

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = " SELECT acstcf FROM M3FDBprd.FCR040 WHERE  ACCINO LIKE '%" + invno.trim() + "%'";
//                String query = " SELECT acstcf FROM M3FDBprd.FCR040 WHERE ACCONO ='"+cono+"' AND ACDIVI ='"+divi+"' AND ACCINO LIKE '"+invno.trim()+"%'";
                System.out.println("CheckINVSTS \n" + query);

                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    stats = mRes.getString("ACSTCF");
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

        return stats;

    }

    public static JSONArray Company() throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT CCCONO,CCDIVI,CCCONM,'\"'|| TRIM(CCCONO) || ' : ' || TRIM(CCDIVI) || ' : ' || TRIM(CCCONM) || '\"' AS COMPANY\n"
                        + "FROM " + M3DB + ".CMNDIV\n"
                        + "WHERE CCDIVI NOT IN  ('','120','130')\n"
                        + "AND CCCONO IN ('10','600','500')\n"
                        + "ORDER BY CCCONO";
//                System.out.println("select \n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("CCCONO", mRes.getString(1).trim());
                    mMap.put("CCDIVI", mRes.getString(2).trim());
                    mMap.put("CCCONM", mRes.getString(3).trim());
                    mMap.put("COMPANY", mRes.getString(4).trim());
                    mJSonArr.put(mMap);

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

        return mJSonArr;

    }

    public static JSONArray Location(String CONO, String DIVI) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT DISTINCT RL_CONO, RL_DIVI,RL_LCCODE, RL_LCDESC\n"
                        + " FROM " + DBPRD + ".RECEIPT_LOCAFNC\n"
                        + " WHERE RL_CONO = '" + CONO.trim() + "'\n"
                        + " AND RL_DIVI ='" + DIVI.trim() + "'\n"
                           + " AND RL_DIVI !=' '\n"
                        + " AND RL_STS = '20'";
                System.out.println("SelectCompany\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("RL_CONO", mRes.getString(1).trim());
                    mMap.put("RL_DIVI", mRes.getString(2).trim());
                    mMap.put("RL_LCCODE", mRes.getString(3).trim());
                    mMap.put("RL_LCDESC", mRes.getString(4).trim());
                    mJSonArr.put(mMap);

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

        return mJSonArr;

    }

    public static JSONArray List_Bank(String type, String code, String cono, String divi) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "";
                if (type.equalsIgnoreCase("CHECK")) {
                    query = "SELECT '-' AS ACCCODE,BCBKID AS BANKNAME,BCBANA AS BANKDESC \n"
                            + "FROM " + M3DB + ".CBANAC\n"
                            + "WHERE BCCONO = '" + cono.trim() + "'\n"
                            + "  AND BCBKTP = 2\n"
                            + "  AND BCSTAT = '20'\n"
                            + "  AND BCACHO = '" + code.trim() + "' ";
                } else {
                    query = "SELECT BR_ACCODE AS ACCCODE, BR_BANK AS BANKNAME, BR_NAMEAC AS BANKDESC "
                            + "FROM " + DBPRD + ".BANKRECEIPT "
                            + "WHERE BR_CONO = '" + cono.trim() + "' "
                            + "AND  BR_STATUS = '20'";
                }

                System.out.println("select bank first\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("ACCCODE", mRes.getString(1).trim());
                    mMap.put("BANKNAME", mRes.getString(2).trim());
                    mMap.put("BANKDESC", mRes.getString(3).trim());
                    mJSonArr.put(mMap);

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

        return mJSonArr;

    }

    public static JSONArray Grid_ARS200(String cono, String divi, String payer, String rcno) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
//        payer = "TH01010016";
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT C.*,SUBSTR(D.ESIVDT,1,4) || '-' || SUBSTR(D.ESIVDT,5,2)|| '-' || SUBSTR(D.ESIVDT,7,2) as ESIVDT, C.Balance as RECEIVE \n"
                        + "FROM \n"
                        + "(\n"
                        + "SELECT A.ESPYNO,A.ESCUNO,A.ESCINO,A.ESINYR,A.ESCUCD,A.ESCUAM,COALESCE(b.LineAMT,0) AS LineAMT,(COALESCE(A.ESCUAM,0) - COALESCE(b.LineAMT,0)) AS Balance\n"
                        + "FROM \n"
                        + "(\n"
                        + "SELECT ESPYNO,ESCUNO,ESCINO,ESINYR,ESCUCD,SUM(ESCUAM) as ESCUAM\n"
                        + "FROM " + M3DB + ".FSLEDG\n"
                        + "WHERE ESCONO = '" + cono.trim() + "'\n"
                        + "AND ESDIVI = '" + divi.trim() + "'\n"
                        + "AND ESPYNO = '" + payer.trim() + "'\n"
                        + "AND ESCINO NOT IN (SELECT LR_INVNO\n"
                        + "FROM " + DBPRD + ".HR_RECEIPT ," + DBPRD + ".LR_LINERECEIPT \n"
                        + "WHERE HC_STS = '2' \n"
                        + "AND HC_PYNO = '" + payer.trim() + "'\n"
                        + "AND HC_RCNO  = LR_RCNO ) \n"
                        + "GROUP BY ESPYNO,ESCUNO,ESCINO,ESINYR,ESCUCD\n"
                        + "HAVING  SUM(ESCUAM) <> 0\n"
                        + ") A LEFT JOIN (\n"
                        + "SELECT LR_INVNO,sum(LR_REAMT) AS LineAMT \n"
                        + "FROM " + DBPRD + ".LR_LINERECEIPT\n"
                        + "WHERE LR_CONO = '" + cono.trim() + "'\n"
                        + "AND LR_DIVI = '" + divi.trim() + "'\n"
                        + "AND LR_RCNO = '" + rcno.trim() + "' \n"
                        + "GROUP BY LR_INVNO,LR_RCNO\n"
                        + ") B ON B.LR_INVNO = A.ESCINO\n"
                        + ") C LEFT JOIN (\n"
                        + "  SELECT  DISTINCT ESPYNO,ESCUNO,ESCINO,ESIVDT,ESINYR,ESCUCD\n"
                        + "FROM " + M3DB + ".FSLEDG \n"
                        + "WHERE ESCONO =  '" + cono.trim() + "'\n"
                        + "AND ESDIVI = '" + divi.trim() + "'\n"
                        + ") D ON D.ESCUNO = C.ESCUNO AND D.ESCINO = C.ESCINO\n ";
//                        + "ORDER BY ESIVDT asc";

                System.out.println("xxxxxxxxxxxxx AGS200 xxxxxxxxxxx");

                System.out.println(query);

                System.out.println("xxxxxxxxxxxxxxxxxxxxxxxx");

                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

                    Double CheckData = mRes.getDouble(8);
                    if (CheckData != 0.00) {
                        mMap.put("ESPYNO", mRes.getString(1).trim());
                        mMap.put("ESCUNO", mRes.getString(2).trim());
                        mMap.put("ESCINO", mRes.getString(3).trim());
                        mMap.put("ESINYR", mRes.getString(4).trim());
                        mMap.put("ESCUCD", mRes.getString(5).trim());
                        mMap.put("ESCUAM", mRes.getString(6).trim());
                        mMap.put("LineAMT", mRes.getString(7).trim());
                        mMap.put("Balance", mRes.getString(8).trim());
                        mMap.put("ESIVDT", mRes.getString(9).trim());
                        mMap.put("RECEIVE", mRes.getString(10).trim());

                        mJSonArr.put(mMap);
                    }

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

        return mJSonArr;

    }

    public static JSONArray Grid_ARS200_EXPnew(String cono, String divi, String payer, String rcno, String customer) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
//        payer = "TH01010016";
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = " SELECT C.*,SUBSTR(D.ESIVDT,1,4) || '-' || SUBSTR(D.ESIVDT,5,2)|| '-' || SUBSTR(D.ESIVDT,7,2) as ESIVDT, C.Balance as RECEIVE \n"
                        + "FROM \n"
                        + "(\n"
                        + "SELECT A.ESPYNO,A.ESCUNO,A.ESCINO,A.ESINYR,A.ESCUCD,A.ESCUAM,COALESCE(b.LineAMT,0) AS LineAMT,(COALESCE(A.ESCUAM,0) - COALESCE(b.LineAMT,0)) AS Balance\n"
                        + "FROM \n"
                        + "(\n"
                        + "SELECT ESPYNO,ESCUNO,ESCINO,ESINYR,ESCUCD,SUM(ESCUAM) as ESCUAM\n"
                        + "FROM " + M3DB + ".FSLEDG A," + DBPRD + ".LR_LINERECEIPT B\n"
                        + "WHERE A.ESCONO ='" + cono.trim() + "'\n"
                        + "AND A.ESDIVI = '" + divi.trim() + "'\n"
                        + "AND A.ESPYNO = '" + payer.trim() + "'\n"
                        + "AND A.ESCUNO = '" + customer.trim() + "' \n"
                        + "AND B.LR_RCNO = '" + rcno.trim() + "'\n"
                        + "AND A.ESCONO = B.LR_CONO\n"
                        + "AND A.ESDIVI = B.LR_DIVI\n"
                        + "AND B.LR_INVNO = A.ESCINO\n"
                        + "GROUP BY ESPYNO,ESCUNO,ESCINO,ESINYR,ESCUCD\n"
                        + "HAVING  SUM(ESCUAM) <> 0\n"
                        + ") A LEFT JOIN (\n"
                        + "SELECT LREP_INVNO,sum(LREP_EXPAMT) AS LineAMT \n"
                        + "FROM " + DBPRD + ".LR_LINECLEAREXP\n"
                        + "WHERE LREP_CONO = '" + cono.trim() + "'\n"
                        + "AND LREP_DIVI = '" + divi.trim() + "'\n"
                        + "AND LREP_RCNO = '" + rcno.trim() + "' \n"
                        + "GROUP BY LREP_INVNO,LREP_RCNO\n"
                        + ") B ON B.LREP_INVNO = A.ESCINO\n"
                        + ") C LEFT JOIN (\n"
                        + "  SELECT  DISTINCT ESPYNO,ESCUNO,ESCINO,ESIVDT,ESINYR,ESCUCD\n"
                        + "FROM " + M3DB + ".FSLEDG \n"
                        + "WHERE ESCONO =  '" + cono.trim() + "'\n"
                        + "AND ESDIVI = '" + divi.trim() + "'\n"
                        + ") D ON D.ESCUNO = C.ESCUNO AND D.ESCINO = C.ESCINO ORDER BY ESIVDT asc";

                System.out.println("Grid EXP : " + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    Double CheckData = mRes.getDouble(8);
                    if (CheckData != 0.00) {
                        mMap.put("ESPYNO", mRes.getString(1).trim());
                        mMap.put("ESCUNO", mRes.getString(2).trim());
                        mMap.put("ESCINO", mRes.getString(3).trim());
                        mMap.put("ESINYR", mRes.getString(4).trim());
                        mMap.put("ESCUCD", mRes.getString(5).trim());
                        mMap.put("ESCUAM", mRes.getString(6).trim());
                        mMap.put("LineAMT", mRes.getString(7).trim());
                        mMap.put("Balance", mRes.getString(8).trim());
                        mMap.put("ESIVDT", mRes.getString(9).trim());
                        mMap.put("RECEIVE", mRes.getString(10).trim());
                        mJSonArr.put(mMap);
                    }
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

        return mJSonArr;

    }

    public static JSONArray Grid_ARS200_EXP(String cono, String divi, String payer, String rcno) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
//        payer = "TH01010016";
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT C.*,SUBSTR(D.ESIVDT,1,4) || '-' || SUBSTR(D.ESIVDT,5,2)|| '-' || SUBSTR(D.ESIVDT,7,2) as ESIVDT, C.Balance as RECEIVE \n"
                        + "FROM \n"
                        + "(\n"
                        + "SELECT A.ESPYNO,A.ESCUNO,A.ESCINO,A.ESINYR,A.ESCUCD,A.ESCUAM,COALESCE(b.LineAMT,0) AS LineAMT,(COALESCE(A.ESCUAM,0) - COALESCE(b.LineAMT,0)) AS Balance\n"
                        + "FROM \n"
                        + "(\n"
                        + "SELECT ESPYNO,ESCUNO,ESCINO,ESINYR,ESCUCD,SUM(ESCUAM) as ESCUAM\n"
                        + "FROM " + M3DB + ".FSLEDG A," + DBPRD + ".LR_LINERECEIPT B\n"
                        + "WHERE A.ESCONO = '" + cono.trim() + "'\n"
                        + "AND A.ESDIVI = '" + divi.trim() + "'\n"
                        + "AND A.ESPYNO = '" + payer.trim() + "'\n"
                        + "AND B.LR_RCNO = '" + rcno.trim() + "'\n"
                        + "AND A.ESCONO = B.LR_CONO\n"
                        + "AND A.ESDIVI = B.LR_DIVI\n"
                        + "AND B.LR_INVNO = A.ESCINO\n"
                        + "GROUP BY ESPYNO,ESCUNO,ESCINO,ESINYR,ESCUCD\n"
                        + "HAVING  SUM(ESCUAM) <> 0\n"
                        + ") A LEFT JOIN (\n"
                        + "SELECT LREP_INVNO,sum(LREP_EXPAMT) AS LineAMT \n"
                        + "FROM " + DBPRD + ".LR_LINECLEAREXP\n"
                        + "WHERE LREP_CONO = '" + cono.trim() + "'\n"
                        + "AND LREP_DIVI = '" + divi.trim() + "'\n"
                        + "AND LREP_RCNO = '" + rcno.trim() + "' \n"
                        + "GROUP BY LREP_INVNO,LREP_RCNO\n"
                        + ") B ON B.LREP_INVNO = A.ESCINO\n"
                        + ") C LEFT JOIN (\n"
                        + "  SELECT  DISTINCT ESPYNO,ESCUNO,ESCINO,ESIVDT,ESINYR,ESCUCD\n"
                        + "FROM " + M3DB + ".FSLEDG \n"
                        + "WHERE ESCONO =  '" + cono.trim() + "'\n"
                        + "AND ESDIVI = '" + divi.trim() + "'\n"
                        + ") D ON D.ESCUNO = C.ESCUNO AND D.ESCINO = C.ESCINO\n"
                        + "ORDER BY ESIVDT asc";

                System.out.println("Grid EXP : " + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    Double CheckData = mRes.getDouble(8);
                    if (CheckData != 0.00) {
                        mMap.put("ESPYNO", mRes.getString(1).trim());
                        mMap.put("ESCUNO", mRes.getString(2).trim());
                        mMap.put("ESCINO", mRes.getString(3).trim());
                        mMap.put("ESINYR", mRes.getString(4).trim());
                        mMap.put("ESCUCD", mRes.getString(5).trim());
                        mMap.put("ESCUAM", mRes.getString(6).trim());
                        mMap.put("LineAMT", mRes.getString(7).trim());
                        mMap.put("Balance", mRes.getString(8).trim());
                        mMap.put("ESIVDT", mRes.getString(9).trim());
                        mMap.put("RECEIVE", mRes.getString(10).trim());
                        mJSonArr.put(mMap);
                    }
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

        return mJSonArr;

    }

    public static JSONArray Grid_Receipt(String cono, String divi, String rcno) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT LR_CONO, LR_DIVI, LR_RCNO, LR_INVNO, LR_INVDT, LR_INVAMT, LR_REAMT, LR_STS  \n"
                        + "FROM " + DBPRD + ".LR_LINERECEIPT\n"
                        + "WHERE LR_CONO = '" + cono.trim() + "'\n"
                        + "AND LR_DIVI = '" + divi.trim() + "'\n"
                        + "AND LR_RCNO = '" + rcno.trim() + "'\n"
                        + "AND LR_STS = '1'\n";

//                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("LR_CONO", mRes.getString(1).trim());
                    mMap.put("LR_DIVI", mRes.getString(2).trim());
                    mMap.put("LR_RCNO", mRes.getString(3).trim());
                    mMap.put("LR_INVNO", mRes.getString(4).trim());
                    mMap.put("LR_INVDT", mRes.getString(5).trim());
                    mMap.put("LR_INVAMT", mRes.getString(6).trim());
                    mMap.put("LR_REAMT", mRes.getString(7).trim());
                    mMap.put("LR_STS", mRes.getString(8).trim());
//                    mMap.put("ESCUNO", mRes.getString(9).trim());

                    mJSonArr.put(mMap);

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

        return mJSonArr;

    }

    public static JSONArray Cal_LineAmount(String CONO, String DIVI, String RCNO, String FNNO) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {
                Statement stmt0 = conn.createStatement();

                Statement stmt = conn.createStatement();
                String query = "SELECT SUM(LR_REAMT) as LR_REAMT  \n"
                        + "FROM " + DBPRD + ".LR_LINERECEIPT\n"
                        + "WHERE  LR_CONO = '" + CONO.trim() + "'\n"
                        + "AND LR_DIVI = '" + DIVI + "'\n"
                        + "AND LR_STS = '1'\n"
                        + "AND LR_RCNO = '" + RCNO.trim() + "'";
                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                Double Amt = 0.00;
                while (mRes.next()) {
//                    if (mRes.getDouble(1) < 0.00) {
//                        Amt += ((-1) * mRes.getDouble(1));
//                    } else {
//                        Amt += mRes.getDouble(1);
//                    }
                    Amt += mRes.getDouble(1);
                    System.out.println(Amt);

                }

                if (Amt != 0.00) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("LineAmout", String.valueOf(Amt));

                    mJSonArr.put(mMap);
                } else {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("LineAmout", String.valueOf(Amt));

                    mJSonArr.put(mMap);
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

        return mJSonArr;

    }

    public static JSONArray Cal_LineAmountnew(String CONO, String DIVI, String RCNO, String FNNO) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
        JSONArray RCNOtxt = new JSONArray();
        String rcSet = "";

        try {
            if (conn != null) {
                Statement stmt0 = conn.createStatement();

                String query0 = "SELECT  H_RCNO  FROM  " + DBPRD + ".HEAD_RECIPT hr  \n"
                        + "WHERE  H_CUNO  != 'HEAD'\n"
                        + "AND H_RNNO  = '" + FNNO + "'";

                ResultSet mRes0 = stmt0.executeQuery(query0);

                while (mRes0.next()) {

                    RCNOtxt.put(mRes0.getString("H_RCNO"));

                }

                for (int i = 0; i < RCNOtxt.length(); i++) {

                    rcSet += "'" + RCNOtxt.get(i) + "'";
                    if (i != RCNOtxt.length() - 1) {
                        rcSet += ",";
                    }
                }

                System.out.println(rcSet);

                Statement stmt = conn.createStatement();
                String query = "SELECT SUM(LR_REAMT) as LR_REAMT  \n"
                        + "FROM " + DBPRD + ".LR_LINERECEIPT\n"
                        + "WHERE  LR_CONO = '" + CONO.trim() + "'\n"
                        + "AND LR_DIVI = '" + DIVI + "'\n"
                        + "AND LR_STS = '1'\n"
                        + "AND LR_RCNO IN (" + rcSet + ")";
                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                Double Amt = 0.00;
                while (mRes.next()) {
//                    if (mRes.getDouble(1) < 0.00) {
//                        Amt += ((-1) * mRes.getDouble(1));
//                    } else {
//                        Amt += mRes.getDouble(1);
//                    }
                    Amt += mRes.getDouble(1);
                    System.out.println(Amt);

                }

                if (Amt != 0.00) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("LineAmout", String.valueOf(Amt));

                    mJSonArr.put(mMap);
                } else {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("LineAmout", String.valueOf(Amt));

                    mJSonArr.put(mMap);
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

        return mJSonArr;

    }

    public static JSONArray CheckInvoice(String CONO, String INVNO) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT COUNT(*) AS CheckInvoice \n"
                        + "FROM " + DBPRD + ".LR_LINERECEIPT\n"
                        + "WHERE LR_INVNO = '" + INVNO.trim() + "'\n"
                        + "AND LR_CONO = '" + CONO.trim() + "'\n";
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("CheckInvoice", mRes.getString(1).trim());
                    mJSonArr.put(mMap);
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

        return mJSonArr;

    }

    public static String GET_RUNNINGAFTERCREATE(String cono, String divi, String date, String payer, String user, String amt) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();
        String HC_RCNO = "";
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
//                String query = "SELECT MAX(HC_RCNO)\n"
//                        + "FROM BRLDTA0100.HR_RECEIPT \n"
//                        + "WHERE HR_CONO = '" + cono.trim() + "'\n"
//                        + "AND HR_DIVI = '" + divi.trim() + "'\n";
                String query = "SELECT COALESCE (MAX(HC_RCNO),'-') as HC_RCNO\n"
                        + "FROM " + DBPRD + ".HR_RECEIPT \n"
                        + "WHERE HR_CONO = '" + cono.trim() + "'\n"
                        + " AND  SUBSTRING(HC_RCNO,1,1) != '3' AND HR_DIVI = '" + divi.trim() + "'\n"
                        + "AND HC_USER ='" + user.trim() + "'\n"
                        + "AND HC_PYNO = '" + payer.trim() + "'\n"
                        + "AND HC_REAMT = '" + amt.trim() + "' \n"
                        + "AND HC_TRDT = '" + date.replaceAll("-", "").trim() + "' ";
//                System.out.println("GET_RUNNING\n" + query);
                ResultSet mRes = stmt.executeQuery(query);
                while (mRes.next()) {
                    HC_RCNO = mRes.getString("HC_RCNO");
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
//        HC_RCNO = "-";
        return HC_RCNO;
    }

    public static String GET_RUNNING(String cono, String divi) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();
        String HC_RCNO = "";
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT TO_CHAR(max(HC_RCNO)+1,'00000000') AS HC_RCNO\n"
                        + "FROM " + DBPRD + ".HR_RECEIPT \n"
                        + "WHERE HR_CONO = '" + cono.trim() + "'\n"
                        + "AND  SUBSTRING(HC_RCNO,1,1) != '3' AND HR_DIVI = '" + divi.trim() + "'";

//                System.out.println("GET_RUNNING\n" + query);
                ResultSet mRes = stmt.executeQuery(query);
                while (mRes.next()) {
                    HC_RCNO = mRes.getString("HC_RCNO");
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
        if (HC_RCNO.equals("")) {
            HC_RCNO = "21000001";
        }
        return HC_RCNO;

    }

    public static JSONArray ShowReceiveNO(String HC_RCNO, String CONO, String DIVI, String CUNO) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "SELECT TRIM(COALESCE(OKCUNM,'-')) AS OKCUNM FROM " + M3DB + ".OCUSMA WHERE OKCUNO = '" + CUNO.trim() + "' AND OKCONO = '" + CONO.trim() + "' ";

//                System.out.println("GET_RUNNING\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("OKCUNM", mRes.getString(1).trim());
                    mMap.put("HC_RCNO", HC_RCNO);
                    mJSonArr.put(mMap);
                }
            } else {
                System.out.println("Server can't connect.");
            }
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
        return mJSonArr;

    }

    public static JSONArray SearchCustomerReceipt(String CONO, String DIVI, String FNNO, String Location) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();

                String query = "SELECT H_RCNO,H_CUNO, H_PYNO FROM " + DBPRD + ".HEAD_RECIPT hr \n"
                        + "WHERE H_CONO  = '" + CONO + "'\n"
                        + "AND H_DIVI  = '" + DIVI + "'\n"
                        + "AND H_RNNO  = '" + FNNO + "' "
                        + "AND H_STS  = '1' AND H_CUNO != 'HEAD'";

                System.out.println(" SearchID : " + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("H_RCNO", mRes.getString(1).trim());
                    mMap.put("H_CUNO", mRes.getString(2).trim());
                    mMap.put("H_PYNO", mRes.getString(3).trim());

                    mJSonArr.put(mMap);
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

        return mJSonArr;

    }

    public static JSONArray SearchReceipt00(String CONO, String DIVI, String RCNO, String Location) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();

                String query = "SELECT E.*,COALESCE(F.RR_DESC,'-') AS  RR_DESC\n"
                        + "FROM (\n"
                        + "SELECT HR_CONO, HR_DIVI,HC_RCNO, HC_TRDT, HC_PYNO, HC_REAMT, HC_USER, HC_STS,COALESCE(OKCUNM,'NOT FOUND') as OKCUNM\n"
                        + ",HC_TYPE, HC_BANK, HC_ACCODE,HC_PYDT, HC_CHKNO,HR_BKCHG,SHC_PYDT\n"
                        + "FROM (\n"
                        + "SELECT HR_CONO, HR_DIVI, HC_RCNO, SUBSTR(HC_TRDT,1,4) || '-' || SUBSTR(HC_TRDT,5,2)|| '-' || SUBSTR(HC_TRDT,7,2) as HC_TRDT , HC_PYNO, HC_REAMT, HC_USER, HC_STS   \n"
                        + ",HC_TYPE, HC_BANK, HC_ACCODE,CASE WHEN HC_PYDT= 0 THEN '-' ELSE SUBSTR(HC_PYDT,7,2) || '-' || SUBSTR(HC_PYDT,5,2)|| '-' || SUBSTR(HC_PYDT,1,4) END AS HC_PYDT, HC_CHKNO \n"
                        + ",COALESCE(HR_BKCHG,0)  AS HR_BKCHG, SUBSTR(HC_PYDT,1,4) || '-' || SUBSTR(HC_PYDT,5,2)|| '-' || SUBSTR(HC_PYDT,7,2) as SHC_PYDT\n"
                        + "FROM " + DBPRD + ".HR_RECEIPT a ," + DBPRD + ".RECEIPT_LOCAFNC b \n"
                        + "WHERE A.HR_CONO = '" + CONO.trim() + "'\n"
                        + "and A.HR_DIVI = '" + DIVI.trim() + "'\n"
                        + "AND A.HR_CONO = B.RL_CONO\n"
                        + "AND A.HR_DIVI = B.RL_DIVI\n"
                        + "AND A.HC_TYPE LIKE '%' || B.RL_TYPE || '%'\n"
                        + "AND A.HC_TYPE NOT LIKE '%DEPOSIT' \n"
                        + "AND A.HC_LOCATION = '" + Location.trim() + "' \n"
                        + "AND B.RL_LCCODE = A.HC_LOCATION\n"
                        + "and A.HC_RCNO = '" + RCNO.trim() + "'\n"
                        //                        + "AND A.HC_VCNO IS NULL\n"
                        + "and A.HC_STS = '1'\n"
                        + ") C LEFT JOIN \n"
                        + "(\n"
                        + "	SELECT OKCONO,OKCUNO,TRIM(COALESCE(OKCUNM,'-')) AS OKCUNM \n"
                        + "	FROM " + M3DB + ".OCUSMA\n"
                        + "	WHERE OKCONO = '" + CONO.trim() + "'\n"
                        + ") D ON D.OKCONO = C.HR_CONO AND D.OKCUNO = C.HC_PYNO\n"
                        + ") E LEFT JOIN (\n"
                        + "SELECT RR_CONO, RR_DIVI, RR_RCNO, COALESCE(RR_DESC,'-') AS  RR_DESC\n"
                        + "FROM " + DBPRD + ".RECEIPT_RECEIVER\n"
                        + ") F ON F.RR_CONO = E.HR_CONO AND F.RR_DIVI = E.HR_DIVI AND F.RR_RCNO = E.HC_RCNO";

                System.out.println(" Search : " + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("HR_CONO", mRes.getString(1).trim());
                    mMap.put("HR_DIVI", mRes.getString(2).trim());
                    mMap.put("HC_RCNO", mRes.getString(3).trim());
                    mMap.put("HC_TRDT", mRes.getString(4).trim());
                    mMap.put("HC_PYNO", mRes.getString(5).trim());
                    mMap.put("HC_REAMT", mRes.getString(6).trim());
                    mMap.put("HC_USER", mRes.getString(7).trim());
                    mMap.put("HC_STS", mRes.getString(8).trim());
                    mMap.put("OKCUNM", mRes.getString(9).trim());

                    mMap.put("HC_TYPE", mRes.getString(10).trim());
                    mMap.put("HC_BANK", mRes.getString(11).trim());
                    mMap.put("HC_ACCODE", mRes.getString(12).trim());
                    mMap.put("HC_PYDT", mRes.getString(13).trim());
                    mMap.put("HC_CHKNO", mRes.getString(14).trim());
                    mMap.put("HR_BKCHG", mRes.getString(15).trim());
                    mMap.put("HC_PYDT", mRes.getString(16).trim());
                    mMap.put("RR_DESC", mRes.getString(17).trim());

                    mJSonArr.put(mMap);
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

        return mJSonArr;

    }

    public static JSONArray SearchReceipt(String CONO, String DIVI, String RCNO, String Location, String FNNO) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();

                String query = "SELECT E.*,COALESCE(F.RR_DESC,'-') AS  RR_DESC\n"
                        + "FROM (\n"
                        + "SELECT HR_CONO, HR_DIVI,HC_RCNO, HC_TRDT, HC_PYNO, HC_REAMT, HC_USER, HC_STS,COALESCE(OKCUNM,'NOT FOUND') as OKCUNM\n"
                        + ",HC_TYPE, HC_BANK, HC_ACCODE,HC_PYDT, HC_CHKNO,HR_BKCHG,SHC_PYDT\n"
                        + "FROM (\n"
                        + "SELECT HR_CONO, HR_DIVI, HC_RCNO, SUBSTR(HC_TRDT,1,4) || '-' || SUBSTR(HC_TRDT,5,2)|| '-' || SUBSTR(HC_TRDT,7,2) as HC_TRDT , HC_PYNO, HC_REAMT, HC_USER, HC_STS   \n"
                        + ",HC_TYPE, HC_BANK, HC_ACCODE,CASE WHEN HC_PYDT= 0 THEN '-' ELSE SUBSTR(HC_PYDT,7,2) || '-' || SUBSTR(HC_PYDT,5,2)|| '-' || SUBSTR(HC_PYDT,1,4) END AS HC_PYDT, HC_CHKNO \n"
                        + ",COALESCE(HR_BKCHG,0)  AS HR_BKCHG, SUBSTR(HC_PYDT,1,4) || '-' || SUBSTR(HC_PYDT,5,2)|| '-' || SUBSTR(HC_PYDT,7,2) as SHC_PYDT\n"
                        + "FROM " + DBPRD + ".HR_RECEIPT a ," + DBPRD + ".RECEIPT_LOCAFNC b \n"
                        + "WHERE A.HR_CONO = '" + CONO.trim() + "'\n"
                        + "and A.HR_DIVI = '" + DIVI.trim() + "'\n"
                        + "AND A.HR_CONO = B.RL_CONO\n"
                        + "AND A.HR_DIVI = B.RL_DIVI\n"
                        + "AND A.HC_TYPE LIKE '%' || B.RL_TYPE || '%'\n"
                        + "AND A.HC_TYPE NOT LIKE '%DEPOSIT' \n"
                        + "AND A.HC_LOCATION = '" + Location.trim() + "' \n"
                        + "AND B.RL_LCCODE = A.HC_LOCATION\n"
                        + "and A.HC_RCNO = '" + RCNO.trim() + "'\n"
                        //                        + "AND A.HC_VCNO IS NULL\n"
                        + "and A.HC_STS = '1'\n"
                        + ") C LEFT JOIN \n"
                        + "(\n"
                        + "	SELECT OKCONO,OKCUNO,TRIM(COALESCE(OKCUNM,'-')) AS OKCUNM \n"
                        + "	FROM " + M3DB + ".OCUSMA\n"
                        + "	WHERE OKCONO = '" + CONO.trim() + "'\n"
                        + ") D ON D.OKCONO = C.HR_CONO AND D.OKCUNO = C.HC_PYNO\n"
                        + ") E LEFT JOIN (\n"
                        + "SELECT RR_CONO, RR_DIVI, RR_RCNO, COALESCE(RR_DESC,'-') AS  RR_DESC\n"
                        + "FROM " + DBPRD + ".RECEIPT_RECEIVER\n"
                        + ") F ON F.RR_CONO = E.HR_CONO AND F.RR_DIVI = E.HR_DIVI AND F.RR_RCNO = E.HC_RCNO";

                System.out.println(" Search : " + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("HR_CONO", mRes.getString(1).trim());
                    mMap.put("HR_DIVI", mRes.getString(2).trim());
                    mMap.put("HC_RCNO", mRes.getString(3).trim());
                    mMap.put("HC_TRDT", mRes.getString(4).trim());
                    mMap.put("HC_PYNO", mRes.getString(5).trim());
                    mMap.put("HC_REAMT", mRes.getString(6).trim());
                    mMap.put("HC_USER", mRes.getString(7).trim());
                    mMap.put("HC_STS", mRes.getString(8).trim());
                    mMap.put("OKCUNM", mRes.getString(9).trim());

                    mMap.put("HC_TYPE", mRes.getString(10).trim());
                    mMap.put("HC_BANK", mRes.getString(11).trim());
                    mMap.put("HC_ACCODE", mRes.getString(12).trim());
                    mMap.put("HC_PYDT", mRes.getString(13).trim());
                    mMap.put("HC_CHKNO", mRes.getString(14).trim());
                    mMap.put("HR_BKCHG", mRes.getString(15).trim());
                    mMap.put("HC_PYDT", mRes.getString(16).trim());
                    mMap.put("RR_DESC", mRes.getString(17).trim());
                    mMap.put("FNNO", FNNO);

                    mJSonArr.put(mMap);
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

        return mJSonArr;

    }

    public static JSONArray SearchReceiptdepositnew(String CONO, String DIVI, String RCNO, String LOCATION) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        System.out.println(CONO);
        System.out.println(DIVI);
        System.out.println(RCNO);
        System.out.println(LOCATION);

        try {
            if (conn != null) {

//                 Statement stmt2 = conn.createStatement();
//                
//
//                String query2 = "";
//
//                ResultSet mRes2 = stmt2.executeQuery(query2);
                Statement stmt = conn.createStatement();

                String query = "SELECT E.*,COALESCE(F.RR_DESC,'-') AS  RR_DESC\n"
                        + "FROM (\n"
                        + "SELECT HR_CONO, HR_DIVI,HC_RCNO, HC_TRDT, HC_PYNO, HC_REAMT, HC_USER, HC_STS,COALESCE(OKCUNM,'NOT FOUND') as OKCUNM\n"
                        + ",HC_TYPE, HC_BANK, HC_ACCODE,HC_PYDT, HC_CHKNO,HR_BKCHG,SHC_PYDT\n"
                        + "FROM (\n"
                        + "SELECT HR_CONO, HR_DIVI, HC_RCNO, SUBSTR(HC_TRDT,1,4) || '-' || SUBSTR(HC_TRDT,5,2)|| '-' || SUBSTR(HC_TRDT,7,2) as HC_TRDT , HC_PYNO, HC_REAMT, HC_USER, HC_STS   \n"
                        + ",HC_TYPE, HC_BANK, HC_ACCODE,CASE WHEN HC_PYDT= 0 THEN '-' ELSE SUBSTR(HC_PYDT,7,2) || '-' || SUBSTR(HC_PYDT,5,2)|| '-' || SUBSTR(HC_PYDT,1,4) END AS HC_PYDT, HC_CHKNO \n"
                        + ",COALESCE(HR_BKCHG,0)  AS HR_BKCHG, SUBSTR(HC_PYDT,1,4) || '-' || SUBSTR(HC_PYDT,5,2)|| '-' || SUBSTR(HC_PYDT,7,2) as SHC_PYDT\n"
                        + "FROM " + DBPRD + ".HR_RECEIPT a ," + DBPRD + ".RECEIPT_LOCAFNC b \n"
                        + "WHERE A.HR_CONO = '" + CONO.trim() + "'\n"
                        + "and A.HR_DIVI = '" + DIVI.trim() + "'\n"
                        + "AND A.HR_CONO = B.RL_CONO\n"
                        + "AND A.HR_DIVI = B.RL_DIVI\n"
                        + "AND A.HC_TYPE LIKE '%' || B.RL_TYPE || '%'\n"
                        + "AND A.HC_TYPE LIKE '%' || B.RL_TYPE || '_DEPOSIT%'\n"
                        + "AND A.HC_LOCATION = '" + LOCATION.trim() + "' \n"
                        + "AND B.RL_LCCODE = A.HC_LOCATION\n"
                        + "and A.HC_RCNO = '" + RCNO.trim() + "'\n"
                        //                        + "AND A.HC_VCNO IS NULL\n"
                        + "and A.HC_STS = '2'\n"
                        + ") C LEFT JOIN \n"
                        + "(\n"
                        + "	SELECT OKCONO,OKCUNO,TRIM(COALESCE(OKCUNM,'-')) AS OKCUNM \n"
                        + "	FROM " + M3DB + ".OCUSMA\n"
                        + "	WHERE OKCONO = '" + CONO.trim() + "'\n"
                        + ") D ON D.OKCONO = C.HR_CONO AND D.OKCUNO = C.HC_PYNO\n"
                        + ") E LEFT JOIN (\n"
                        + "SELECT RR_CONO, RR_DIVI, RR_RCNO, COALESCE(RR_DESC,'-') AS  RR_DESC\n"
                        + "FROM " + DBPRD + ".RECEIPT_RECEIVER\n"
                        + ") F ON F.RR_CONO = E.HR_CONO AND F.RR_DIVI = E.HR_DIVI AND F.RR_RCNO = E.HC_RCNO";

                System.out.println(" Searchxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx : " + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("HR_CONO", mRes.getString(1).trim());
                    mMap.put("HR_DIVI", mRes.getString(2).trim());
                    mMap.put("HC_RCNO", mRes.getString(3).trim());
                    mMap.put("HC_TRDT", mRes.getString(4).trim());
                    mMap.put("HC_PYNO", mRes.getString(5).trim());
                    mMap.put("HC_REAMT", mRes.getString(6).trim());
                    mMap.put("HC_USER", mRes.getString(7).trim());
                    mMap.put("HC_STS", mRes.getString(8).trim());
                    mMap.put("OKCUNM", mRes.getString(9).trim());

                    mMap.put("HC_TYPE", mRes.getString(10).trim());
                    mMap.put("HC_BANK", mRes.getString(11).trim());
                    mMap.put("HC_ACCODE", mRes.getString(12).trim());
                    mMap.put("HC_PYDT", mRes.getString(13).trim());
                    mMap.put("HC_CHKNO", mRes.getString(14).trim());
                    mMap.put("HR_BKCHG", mRes.getString(15).trim());
                    mMap.put("SHC_PYDT", mRes.getString(16).trim());
                    mMap.put("RR_DESC", mRes.getString(17).trim());

                    mJSonArr.put(mMap);
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

        return mJSonArr;

    }

    public static JSONArray SearchReceiptcashnew(String CONO, String DIVI, String RCNO, String LOCATION) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        System.out.println(CONO);
        System.out.println(DIVI);
        System.out.println(RCNO);
        System.out.println(LOCATION);

        try {
            if (conn != null) {

//                 Statement stmt2 = conn.createStatement();
//                
//
//                String query2 = "";
//
//                ResultSet mRes2 = stmt2.executeQuery(query2);
                Statement stmt = conn.createStatement();

                String query = "SELECT E.*,COALESCE(F.RR_DESC,'-') AS  RR_DESC\n"
                        + "FROM (\n"
                        + "SELECT HR_CONO, HR_DIVI,HC_RCNO, HC_TRDT, HC_PYNO, HC_REAMT, HC_USER, HC_STS,COALESCE(OKCUNM,'NOT FOUND') as OKCUNM\n"
                        + ",HC_TYPE, HC_BANK, HC_ACCODE,HC_PYDT, HC_CHKNO,HR_BKCHG,SHC_PYDT\n"
                        + "FROM (\n"
                        + "SELECT HR_CONO, HR_DIVI, HC_RCNO, SUBSTR(HC_TRDT,1,4) || '-' || SUBSTR(HC_TRDT,5,2)|| '-' || SUBSTR(HC_TRDT,7,2) as HC_TRDT , HC_PYNO, HC_REAMT, HC_USER, HC_STS   \n"
                        + ",HC_TYPE, HC_BANK, HC_ACCODE,CASE WHEN HC_PYDT= 0 THEN '-' ELSE SUBSTR(HC_PYDT,7,2) || '-' || SUBSTR(HC_PYDT,5,2)|| '-' || SUBSTR(HC_PYDT,1,4) END AS HC_PYDT, HC_CHKNO \n"
                        + ",COALESCE(HR_BKCHG,0)  AS HR_BKCHG, SUBSTR(HC_PYDT,1,4) || '-' || SUBSTR(HC_PYDT,5,2)|| '-' || SUBSTR(HC_PYDT,7,2) as SHC_PYDT\n"
                        + "FROM " + DBPRD + ".HR_RECEIPT a ," + DBPRD + ".RECEIPT_LOCAFNC b \n"
                        + "WHERE A.HR_CONO = '" + CONO.trim() + "'\n"
                        + "and A.HR_DIVI = '" + DIVI.trim() + "'\n"
                        + "AND A.HR_CONO = B.RL_CONO\n"
                        + "AND A.HR_DIVI = B.RL_DIVI\n"
                        + "AND A.HC_TYPE LIKE '%' || B.RL_TYPE || '%'\n"
                        + "AND A.HC_TYPE NOT LIKE '%DEPOSIT' \n"
                        + "AND A.HC_LOCATION = '" + LOCATION.trim() + "' \n"
                        + "AND B.RL_LCCODE = A.HC_LOCATION\n"
                        + "and A.HC_RCNO = '" + RCNO.trim() + "'\n"
                        //                        + "AND A.HC_VCNO IS NULL\n"
                        + "and A.HC_STS != '3'\n"
                        + ") C LEFT JOIN \n"
                        + "(\n"
                        + "	SELECT OKCONO,OKCUNO,TRIM(COALESCE(OKCUNM,'-')) AS OKCUNM \n"
                        + "	FROM " + M3DB + ".OCUSMA\n"
                        + "	WHERE OKCONO = '" + CONO.trim() + "'\n"
                        + ") D ON D.OKCONO = C.HR_CONO AND D.OKCUNO = C.HC_PYNO\n"
                        + ") E LEFT JOIN (\n"
                        + "SELECT RR_CONO, RR_DIVI, RR_RCNO, COALESCE(RR_DESC,'-') AS  RR_DESC\n"
                        + "FROM " + DBPRD + ".RECEIPT_RECEIVER\n"
                        + ") F ON F.RR_CONO = E.HR_CONO AND F.RR_DIVI = E.HR_DIVI AND F.RR_RCNO = E.HC_RCNO";

                System.out.println(" Searchxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx : " + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("HR_CONO", mRes.getString(1).trim());
                    mMap.put("HR_DIVI", mRes.getString(2).trim());
                    mMap.put("HC_RCNO", mRes.getString(3).trim());
                    mMap.put("HC_TRDT", mRes.getString(4).trim());
                    mMap.put("HC_PYNO", mRes.getString(5).trim());
                    mMap.put("HC_REAMT", mRes.getString(6).trim());
                    mMap.put("HC_USER", mRes.getString(7).trim());
                    mMap.put("HC_STS", mRes.getString(8).trim());
                    mMap.put("OKCUNM", mRes.getString(9).trim());

                    mMap.put("HC_TYPE", mRes.getString(10).trim());
                    mMap.put("HC_BANK", mRes.getString(11).trim());
                    mMap.put("HC_ACCODE", mRes.getString(12).trim());
                    mMap.put("HC_PYDT", mRes.getString(13).trim());
                    mMap.put("HC_CHKNO", mRes.getString(14).trim());
                    mMap.put("HR_BKCHG", mRes.getString(15).trim());
                    mMap.put("SHC_PYDT", mRes.getString(16).trim());
                    mMap.put("RR_DESC", mRes.getString(17).trim());

                    mJSonArr.put(mMap);
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

        return mJSonArr;

    }

    public static JSONArray SearchReceipt_RC03(String CONO, String DIVI, String RCNO, String Location) throws Exception {

        System.out.println("SearchReceipt_RC03");

        System.out.println(CONO);
        System.out.println(DIVI);
        System.out.println(RCNO);
        System.out.println(Location);
        System.out.println("SearchReceipt_RC03");

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();

                String query = "SELECT E.*,COALESCE(F.RR_DESC,'-') AS  RR_DESC\n"
                        + "FROM (\n"
                        + "SELECT HR_CONO, HR_DIVI,HC_RCNO, HC_TRDT, HC_PYNO, HC_REAMT, HC_USER, HC_STS,OKCUNM\n"
                        + ",HC_TYPE, HC_BANK, HC_ACCODE,HC_PYDT, HC_CHKNO,HR_BKCHG,SHC_PYDT\n"
                        + "FROM (\n"
                        + "SELECT HR_CONO, HR_DIVI, HC_RCNO, SUBSTR(HC_TRDT,1,4) || '-' || SUBSTR(HC_TRDT,5,2)|| '-' || SUBSTR(HC_TRDT,7,2) as HC_TRDT , HC_PYNO, HC_REAMT, HC_USER, HC_STS   \n"
                        + ",HC_TYPE, HC_BANK, HC_ACCODE,CASE WHEN HC_PYDT= 0 THEN '-' ELSE SUBSTR(HC_PYDT,7,2) || '-' || SUBSTR(HC_PYDT,5,2)|| '-' || SUBSTR(HC_PYDT,1,4) END AS HC_PYDT, HC_CHKNO \n"
                        + ",COALESCE(HR_BKCHG,0)  AS HR_BKCHG, SUBSTR(HC_PYDT,1,4) || '-' || SUBSTR(HC_PYDT,5,2)|| '-' || SUBSTR(HC_PYDT,7,2) as SHC_PYDT\n"
                        + "FROM " + DBPRD + ".HR_RECEIPT a ," + DBPRD + ".RECEIPT_LOCAFNC b \n"
                        + "WHERE A.HR_CONO = '" + CONO.trim() + "'\n"
                        + "and A.HR_DIVI = '" + DIVI.trim() + "'\n"
                        + "AND A.HR_CONO = B.RL_CONO\n"
                        + "AND A.HR_DIVI = B.RL_DIVI\n"
                        + "AND A.HC_TYPE LIKE '%' || B.RL_TYPE || '%'\n"
                        + "AND A.HC_TYPE NOT LIKE '%DEPOSIT' \n"
                        + "AND A.HC_LOCATION = '" + Location.trim() + "' \n"
                        + "AND B.RL_LCCODE = A.HC_LOCATION\n"
                        + "and A.HC_RCNO = '" + RCNO.trim() + "'\n"
                        + "AND COALESCE(A.HC_VCNO,'') = ''\n"
                        + ") C LEFT JOIN \n"
                        + "(\n"
                        + "	SELECT OKCONO,OKCUNO,TRIM(COALESCE(OKCUNM,'-')) AS OKCUNM \n"
                        + "	FROM " + M3DB + ".OCUSMA\n"
                        + "	WHERE OKCONO = '" + CONO.trim() + "'\n"
                        + ") D ON D.OKCONO = C.HR_CONO AND D.OKCUNO = C.HC_PYNO\n"
                        + ") E LEFT JOIN (\n"
                        + "SELECT RR_CONO, RR_DIVI, RR_RCNO, COALESCE(RR_DESC,'-') AS  RR_DESC\n"
                        + "FROM " + DBPRD + ".RECEIPT_RECEIVER\n"
                        + ") F ON F.RR_CONO = E.HR_CONO AND F.RR_DIVI = E.HR_DIVI AND F.RR_RCNO = E.HC_RCNO";

                System.out.println("SearchReceipt_RC03 " + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("HR_CONO", mRes.getString(1).trim());
                    mMap.put("HR_DIVI", mRes.getString(2).trim());
                    mMap.put("HC_RCNO", mRes.getString(3).trim());
                    mMap.put("HC_TRDT", mRes.getString(4).trim());
                    mMap.put("HC_PYNO", mRes.getString(5).trim());
                    mMap.put("HC_REAMT", mRes.getString(6).trim());
                    mMap.put("HC_USER", mRes.getString(7).trim());
                    mMap.put("HC_STS", mRes.getString(8).trim());
                    mMap.put("OKCUNM", mRes.getString(9).trim());

                    mMap.put("HC_TYPE", mRes.getString(10).trim());
                    mMap.put("HC_BANK", mRes.getString(11).trim());
                    mMap.put("HC_ACCODE", mRes.getString(12).trim());
                    mMap.put("HC_PYDT", mRes.getString(13).trim());
                    mMap.put("HC_CHKNO", mRes.getString(14).trim());
                    mMap.put("HR_BKCHG", mRes.getString(15).trim());
                    mMap.put("SHC_PYDT", mRes.getString(16).trim());
                    mMap.put("RR_DESC", mRes.getString(17).trim());

                    mJSonArr.put(mMap);
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

        return mJSonArr;

    }

    public static JSONArray SearchReceipt_Check(String CONO, String DIVI, String RCNO, String locations) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
//                String query = "SELECT HR_CONO, HR_DIVI, HC_RCNO, SUBSTR(HC_TRDT,1,4) || '-' || SUBSTR(HC_TRDT,5,2)|| '-' || SUBSTR(HC_TRDT,7,2) as HC_TRDT , HC_PYNO, HC_REAMT, HC_USER, HC_STS ,TRIM(COALESCE(OKCUNM,'-')) AS OKCUNM   \n"
//                        + ",HC_TYPE, HC_BANK, HC_ACCODE,CASE WHEN HC_PYDT= 0 THEN '-' ELSE SUBSTR(HC_PYDT,7,2) || '-' || SUBSTR(HC_PYDT,5,2)|| '-' || SUBSTR(HC_PYDT,1,4) END AS HC_PYDT, HC_CHKNO \n"
//                        + ",COALESCE(HR_BKCHG,0)  AS HR_BKCHG, SUBSTR(HC_PYDT,1,4) || '-' || SUBSTR(HC_PYDT,5,2)|| '-' || SUBSTR(HC_PYDT,7,2) as SHC_PYDT\n"
//                        + "FROM BRLDTA0100.HR_RECEIPT\n"
//                        + "LEFT JOIN "+DBPRD+".OCUSMA ON OKCUNO = HC_PYNO AND OKCONO = HR_CONO  \n"
//                        + "WHERE HR_CONO = '" + CONO.trim() + "'\n"
//                        + "and HR_DIVI = '" + DIVI.trim() + "'\n"
//                        + "and HC_RCNO = '" + RCNO.trim() + "'\n"
//                        + "and HC_TYPE IN ('CHECK','TRANSFER') \n"
//                        + "and HC_STS = '2' ";
//                System.out.println(query);

                String query = "SELECT E.*,COALESCE(F.RR_DESC,'-') AS  RR_DESC\n"
                        + "FROM (\n"
                        + "SELECT HR_CONO, HR_DIVI,HC_RCNO, HC_TRDT, HC_PYNO, HC_REAMT, HC_USER, HC_STS,OKCUNM\n"
                        + ",HC_TYPE, HC_BANK, HC_ACCODE,HC_PYDT, HC_CHKNO,HR_BKCHG,SHC_PYDT\n"
                        + "FROM (\n"
                        + "SELECT HR_CONO, HR_DIVI, HC_RCNO, SUBSTR(HC_TRDT,1,4) || '-' || SUBSTR(HC_TRDT,5,2)|| '-' || SUBSTR(HC_TRDT,7,2) as HC_TRDT , HC_PYNO, HC_REAMT, HC_USER, HC_STS    \n"
                        + ",HC_TYPE, HC_BANK, HC_ACCODE,CASE WHEN HC_PYDT= 0 THEN '-' ELSE SUBSTR(HC_PYDT,7,2) || '-' || SUBSTR(HC_PYDT,5,2)|| '-' || SUBSTR(HC_PYDT,1,4) END AS HC_PYDT, HC_CHKNO \n"
                        + ",COALESCE(HR_BKCHG,0)  AS HR_BKCHG, SUBSTR(HC_PYDT,1,4) || '-' || SUBSTR(HC_PYDT,5,2)|| '-' || SUBSTR(HC_PYDT,7,2) as SHC_PYDT\n"
                        + "FROM " + DBPRD + ".HR_RECEIPT a ," + DBPRD + ".RECEIPT_LOCAFNC b \n"
                        //                        + "FROM BRLDTA0100.HR_RECEIPTV2 a ,BRLDTA0100.RECEIPT_LOCAFNC b \n"
                        + "WHERE A.HR_CONO = '" + CONO.trim() + "'\n"
                        + "and A.HR_DIVI = '" + DIVI.trim() + "'\n"
                        + "AND A.HR_CONO = B.RL_CONO\n"
                        + "AND A.HR_DIVI = B.RL_DIVI\n"
                        + "AND B.RL_TYPE = A.HC_TYPE\n"
                        + "AND A.HC_LOCATION = '" + locations.trim() + "' \n"
                        + "AND B.RL_LCCODE = A.HC_LOCATION\n"
                        + "and A.HC_RCNO = '" + RCNO.trim() + "'\n"
                        + "and HC_TYPE IN ('CHECK','TRANSFER') \n"
                        + "and HC_STS = '2' ) C LEFT JOIN \n"
                        + "(\n"
                        + "	SELECT OKCONO,OKCUNO,TRIM(COALESCE(OKCUNM,'-')) AS OKCUNM \n"
                        + "	FROM " + M3DB + ".OCUSMA\n"
                        + "	WHERE OKCONO = '" + CONO + "'\n"
                        + ") D ON D.OKCONO = C.HR_CONO AND D.OKCUNO = C.HC_PYNO\n"
                        + ") E LEFT JOIN (\n"
                        + "SELECT RR_CONO, RR_DIVI, RR_RCNO, COALESCE(RR_DESC,'-') AS  RR_DESC\n"
                        + "FROM " + DBPRD + ".RECEIPT_RECEIVER\n"
                        + ") F ON F.RR_CONO = E.HR_CONO AND F.RR_DIVI = E.HR_DIVI AND F.RR_RCNO = E.HC_RCNO";
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("HR_CONO", mRes.getString(1).trim());
                    mMap.put("HR_DIVI", mRes.getString(2).trim());
                    mMap.put("HC_RCNO", mRes.getString(3).trim());
                    mMap.put("HC_TRDT", mRes.getString(4).trim());
                    mMap.put("HC_PYNO", mRes.getString(5).trim());
                    mMap.put("HC_REAMT", mRes.getString(6).trim());
                    mMap.put("HC_USER", mRes.getString(7).trim());
                    mMap.put("HC_STS", mRes.getString(8).trim());
                    mMap.put("OKCUNM", mRes.getString(9).trim());

                    mMap.put("HC_TYPE", mRes.getString(10).trim());
                    mMap.put("HC_BANK", mRes.getString(11).trim());
                    mMap.put("HC_ACCODE", mRes.getString(12).trim());
                    mMap.put("HC_PYDT", mRes.getString(13).trim());
                    mMap.put("HC_CHKNO", mRes.getString(14).trim());
                    mMap.put("HR_BKCHG", mRes.getString(15).trim());
                    mMap.put("SHC_PYDT", mRes.getString(16).trim());
                    mMap.put("RR_DESC", mRes.getString(17).trim());

                    mJSonArr.put(mMap);
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

        return mJSonArr;

    }

    public static JSONArray SearchReceipt_DEPOSIT(String CONO, String DIVI, String RCNO, String Location) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();

                String query = "SELECT E.*,COALESCE(F.RR_DESC,'-') AS  RR_DESC\n"
                        + "FROM (\n"
                        + "SELECT HR_CONO, HR_DIVI,HC_RCNO, HC_TRDT, HC_PYNO, HC_REAMT, HC_USER, HC_STS,OKCUNM\n"
                        + ",HC_TYPE, HC_BANK, HC_ACCODE,HC_PYDT, HC_CHKNO,HR_BKCHG,SHC_PYDT\n"
                        + "FROM (\n"
                        + "SELECT HR_CONO, HR_DIVI, HC_RCNO, SUBSTR(HC_TRDT,1,4) || '-' || SUBSTR(HC_TRDT,5,2)|| '-' || SUBSTR(HC_TRDT,7,2) as HC_TRDT , HC_PYNO, HC_REAMT, HC_USER, HC_STS   \n"
                        + ",HC_TYPE, HC_BANK, HC_ACCODE,CASE WHEN HC_PYDT= 0 THEN '-' ELSE SUBSTR(HC_PYDT,7,2) || '-' || SUBSTR(HC_PYDT,5,2)|| '-' || SUBSTR(HC_PYDT,1,4) END AS HC_PYDT, HC_CHKNO \n"
                        + ",COALESCE(HR_BKCHG,0)  AS HR_BKCHG, SUBSTR(HC_PYDT,1,4) || '-' || SUBSTR(HC_PYDT,5,2)|| '-' || SUBSTR(HC_PYDT,7,2) as SHC_PYDT\n"
                        + "FROM " + DBPRD + ".HR_RECEIPT a ," + DBPRD + ".RECEIPT_LOCAFNC b \n"
                        + "WHERE A.HR_CONO = '" + CONO.trim() + "'\n"
                        + "and A.HR_DIVI = '" + DIVI.trim() + "'\n"
                        + "AND A.HR_CONO = B.RL_CONO\n"
                        + "AND A.HR_DIVI = B.RL_DIVI\n"
                        + "AND A.HC_TYPE LIKE '%' || B.RL_TYPE || '_DEPOSIT%'\n"
                        + "AND A.HC_LOCATION = '" + Location.trim() + "' \n"
                        + "AND B.RL_LCCODE = A.HC_LOCATION\n"
                        + "and A.HC_RCNO = '" + RCNO.trim() + "'\n"
                        + "and A.HC_STS = '2'\n"
                        + ") C LEFT JOIN \n"
                        + "(\n"
                        + "	SELECT OKCONO,OKCUNO,TRIM(COALESCE(OKCUNM,'-')) AS OKCUNM \n"
                        + "	FROM " + M3DB + ".OCUSMA\n"
                        + "	WHERE OKCONO = '" + CONO.trim() + "'\n"
                        + ") D ON D.OKCONO = C.HR_CONO AND D.OKCUNO = C.HC_PYNO \n"
                        + ") E LEFT JOIN (\n"
                        + "SELECT RR_CONO, RR_DIVI, RR_RCNO, COALESCE(RR_DESC,'-') AS  RR_DESC\n"
                        + "FROM " + DBPRD + ".RECEIPT_RECEIVER\n"
                        + ") F ON F.RR_CONO = E.HR_CONO AND F.RR_DIVI = E.HR_DIVI AND F.RR_RCNO = E.HC_RCNO";

                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("HR_CONO", mRes.getString(1).trim());
                    mMap.put("HR_DIVI", mRes.getString(2).trim());
                    mMap.put("HC_RCNO", mRes.getString(3).trim());
                    mMap.put("HC_TRDT", mRes.getString(4).trim());
                    mMap.put("HC_PYNO", mRes.getString(5).trim());
                    mMap.put("HC_REAMT", mRes.getString(6).trim());
                    mMap.put("HC_USER", mRes.getString(7).trim());
                    mMap.put("HC_STS", mRes.getString(8).trim());
                    mMap.put("OKCUNM", mRes.getString(9).trim());

                    mMap.put("HC_TYPE", mRes.getString(10).trim());
                    mMap.put("HC_BANK", mRes.getString(11).trim());
                    mMap.put("HC_ACCODE", mRes.getString(12).trim());
                    mMap.put("HC_PYDT", mRes.getString(13).trim());
                    mMap.put("HC_CHKNO", mRes.getString(14).trim());
                    mMap.put("HR_BKCHG", mRes.getString(15).trim());
                    mMap.put("SHC_PYDT", mRes.getString(16).trim());
                    mMap.put("RR_DESC", mRes.getString(17).trim());

                    mJSonArr.put(mMap);
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

        return mJSonArr;

    }

    public static JSONArray SetnamePayer(String cono, String divi, String code) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
        String PAYERNAME = "NOT FOUND";
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT COALESCE(OKCUNM,'-') AS PAYERNAME FROM " + M3DB + ".OCUSMA WHERE OKCONO = '" + cono.trim() + "'  AND OKCUNO = '" + code.trim() + "'";
                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    PAYERNAME = mRes.getString(1).trim();
                }
                Map<String, Object> mMap = new HashMap<>();
                mMap.put("PAYERNAME", PAYERNAME);
                mJSonArr.put(mMap);
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

        return mJSonArr;

    }

    public static JSONArray CheckSTS(String cono, String divi, String HC_RCNO) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "SELECT CASE WHEN HC_STS = '1' THEN 'ถอยสถานะสำเร็จ'  WHEN HC_STS = '2' THEN 'ถอยสถานะสำเร็จ'  WHEN HC_STS = '3' THEN  'ไม่สามารถถอยสถานะได้ ใบเสร็จถูกโพสแล้ว'  ELSE 'ใบเสร็จนี้ถูกยกเลิกแล้ว' END AS HC_STS\n"
                        + "FROM " + DBPRD + ".HR_RECEIPT\n"
                        + "WHERE HR_CONO = '" + cono.trim() + "'\n"
                        + "AND HR_DIVI = '" + divi.trim() + "'\n"
                        + "AND HC_RCNO = '" + HC_RCNO.trim() + "'";
//                System.out.println("GET_RUNNING\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("HC_STS", mRes.getString(1).trim());
                    mJSonArr.put(mMap);
                }
            } else {
                System.out.println("Server can't connect.");
            }
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
        return mJSonArr;

    }

    public static JSONArray CheckInvoiceLock(String HC_RCNO, String cono, String divi) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
        String INV = "";
        int count = 0;
        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "SELECT TRIM(LR_INVNO) AS INV \n"
                        + "FROM " + DBPRD + ".LR_LINERECEIPT,M3FDBprd.FCR040\n"
                        + "WHERE LR_RCNO = '" + HC_RCNO.trim() + "'\n"
                        + "AND LR_CONO = '" + cono.trim() + "'\n"
                        + "AND LR_DIVI = '" + divi.trim() + "'\n"
                        + "AND LR_CONO = ACCONO\n"
                        + "AND ACDIVI = LR_DIVI\n"
                        + "AND LR_INVNO = ACCINO\n";
                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);
                while (mRes.next()) {
                    INV += mRes.getString(1).trim() + " ";
                    count++;
                }

                Map<String, Object> mMap = new HashMap<>();
                mMap.put("Invoice", INV);
                mMap.put("Count", count);

                mJSonArr.put(mMap);
            } else {
                System.out.println("Server can't connect.");
            }
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
        return mJSonArr;

    }

    public static JSONArray List_Eexpense(String cono, String divi) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT RE_CONO, RE_DIVI, RE_CODE, RE_DESC, RE_ACCODE1, RE_ACCODE2, RE_OPTION, RE_STATUS\n"
                        + "            FROM " + DBPRD + ".RECEIPT_EXPENSE\n"
                        + "            WHERE RE_CONO = '" + cono.trim() + "'\n"
                        + "            AND RE_DIVI = '" + divi.trim() + "'\n"
                        + "            AND RE_STATUS = '10'";
//                System.out.println("ListBANK\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("RE_CONO", mRes.getString(1).trim());
                    mMap.put("RE_DIVI", mRes.getString(2).trim());
                    mMap.put("RE_CODE", mRes.getString(3).trim());
                    mMap.put("RE_DESC", mRes.getString(4).trim());
                    mMap.put("RE_ACCODE1", mRes.getString(5).trim());
                    mMap.put("RE_ACCODE2", mRes.getString(6).trim());
                    mMap.put("RE_OPTION", mRes.getString(7).trim());
                    mMap.put("RE_STATUS", mRes.getString(8).trim());

                    mJSonArr.put(mMap);

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

        return mJSonArr;

    }

    public static JSONArray List_Accode(String cono, String divi, String code) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();

                String query = "SELECT RE_CONO, RE_DIVI, RE_CODE, RE_DESC, RE_ACCODE1, RE_ACCODE2, RE_OPTION, RE_STATUS\n"
                        + "FROM " + DBPRD + ".RECEIPT_EXPENSE\n"
                        + "WHERE RE_CONO = '" + cono.trim() + "'\n"
                        + "AND RE_DIVI = '" + divi.trim() + "'\n"
                        + "AND RE_STATUS = '10'\n"
                        + "AND RE_CODE = '" + code.trim() + "'";

                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("RE_CONO", mRes.getString(1).trim());
                    mMap.put("RE_DIVI", mRes.getString(2).trim());
                    mMap.put("RE_CODE", mRes.getString(3).trim());
                    mMap.put("RE_DESC", mRes.getString(4).trim());
                    mMap.put("RE_ACCODE1", mRes.getString(5).trim());
                    mMap.put("RE_ACCODE2", mRes.getString(6).trim());
                    mMap.put("RE_OPTION", mRes.getString(7).trim());
                    mMap.put("RE_STATUS", mRes.getString(8).trim());
                    mJSonArr.put(mMap);

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

        return mJSonArr;

    }

    public static JSONArray Grid_Expensenew(String cono, String divi, String fnno) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT LRE_CONO, LRE_DIVI, LRE_RCNO, LRE_CODE, LRE_ACTCODE, LRE_AMT, LRE_CODE || ' : ' || RE_DESC AS ACTDESC\n"
                        + "FROM " + DBPRD + ".LR_LINEEXPEN," + DBPRD + ".RECEIPT_EXPENSE\n"
                        + "WHERE LRE_CONO = '" + cono.trim() + "'\n"
                        + "AND LRE_DIVI = '" + divi.trim() + "'\n"
                        + "AND LRE_RCNO   IN  (SELECT  HC_RCNO  FROM  " + DBPRD + ".HR_RECEIPT hr \n"
                        + "                        WHERE  HC_FNNO  = '" + fnno + "')\n"
                        + "AND LRE_CONO = RE_CONO\n"
                        + "AND LRE_DIVI = RE_DIVI\n"
                        + "AND LRE_CODE = RE_CODE";

                System.out.println("Call Expense" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("LRE_CONO", mRes.getString(1).trim());
                    mMap.put("LRE_DIVI", mRes.getString(2).trim());
                    mMap.put("LRE_RCNO", mRes.getString(3).trim());
                    mMap.put("LRE_CODE", mRes.getString(4).trim());
                    mMap.put("LRE_ACTCODE", mRes.getString(5).trim());
                    mMap.put("LRE_AMT", mRes.getString(6).trim());
                    mMap.put("ACTDESC", mRes.getString(7).trim());
//                    mMap.put("LR_STS", mRes.getString(8).trim());
//                    mMap.put("ESCUNO", mRes.getString(9).trim());

                    mJSonArr.put(mMap);

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

        return mJSonArr;

    }

    public static JSONArray Grid_Expense(String cono, String divi, String rcno) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT LRE_CONO, LRE_DIVI, LRE_RCNO, LRE_CODE, LRE_ACTCODE, LRE_AMT, LRE_CODE || ' : ' || RE_DESC AS ACTDESC\n"
                        + "FROM " + DBPRD + ".LR_LINEEXPEN," + DBPRD + ".RECEIPT_EXPENSE\n"
                        + "WHERE LRE_CONO = '" + cono.trim() + "'\n"
                        + "AND LRE_DIVI = '" + divi.trim() + "'\n"
                        + "AND LRE_RCNO = '" + rcno.trim() + "'\n"
                        + "AND LRE_CONO = RE_CONO\n"
                        + "AND LRE_DIVI = RE_DIVI\n"
                        + "AND LRE_CODE = RE_CODE";

                System.out.println("Call Expense" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("LRE_CONO", mRes.getString(1).trim());
                    mMap.put("LRE_DIVI", mRes.getString(2).trim());
                    mMap.put("LRE_RCNO", mRes.getString(3).trim());
                    mMap.put("LRE_CODE", mRes.getString(4).trim());
                    mMap.put("LRE_ACTCODE", mRes.getString(5).trim());
                    mMap.put("LRE_AMT", mRes.getString(6).trim());
                    mMap.put("ACTDESC", mRes.getString(7).trim());
//                    mMap.put("LR_STS", mRes.getString(8).trim());
//                    mMap.put("ESCUNO", mRes.getString(9).trim());

                    mJSonArr.put(mMap);

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

        return mJSonArr;

    }

    public static JSONArray CheckVouchernew(String FNNO, String cono, String divi) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
        String Voucher = "";
        String Fix_Running = "";
        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "SELECT CASE WHEN COALESCE(HC_VCNO,'') = '' THEN 'ไม่มีเลขที่ Voucher' ELSE 'Voucher : ' || HC_VCNO END AS HC_VCNO,"
                        + "CASE WHEN COALESCE(HC_FIXNO,'')  = '' THEN 'ไม่มีมีเลขใบเสร็จ กรุณาเช็คข้อมูล' ELSE HC_FIXNO END AS RUNNING\n"
                        + "FROM " + DBPRD + ".HR_RECEIPT\n"
                        + "WHERE HR_CONO = '" + cono.trim() + "'\n"
                        + "AND HR_DIVI ='" + divi.trim() + "'\n"
                        + "AND HC_FNNO = '" + FNNO.trim() + "'";

                ResultSet mRes = stmt.executeQuery(query);
                System.out.println(query);
                while (mRes.next()) {
                    Voucher = mRes.getString(1).trim();
                    Fix_Running = mRes.getString(2).trim();
                }

                Map<String, Object> mMap = new HashMap<>();
                mMap.put("Voucher", Voucher);
                mMap.put("Fix_Running", Fix_Running);
                mJSonArr.put(mMap);
            } else {
                System.out.println("Server can't connect.");
            }
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
        return mJSonArr;

    }

    public static JSONArray CheckVoucher(String HC_RCNO, String cono, String divi) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
        String Voucher = "";
        String Fix_Running = "";
        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "SELECT CASE WHEN COALESCE(HC_VCNO,'') = '' THEN 'ไม่มีเลขที่ Voucher' ELSE 'Voucher : ' || HC_VCNO END AS HC_VCNO,"
                        + "CASE WHEN COALESCE(HC_FIXNO,'')  = '' THEN 'ไม่มีมีเลขใบเสร็จ กรุณาเช็คข้อมูล' ELSE HC_FIXNO END AS RUNNING\n"
                        + "FROM " + DBPRD + ".HR_RECEIPT\n"
                        + "WHERE HR_CONO = '" + cono.trim() + "'\n"
                        + "AND HR_DIVI ='" + divi.trim() + "'\n"
                        + "AND HC_RCNO = '" + HC_RCNO.trim() + "'";

                ResultSet mRes = stmt.executeQuery(query);
                System.out.println(query);
                while (mRes.next()) {
                    Voucher = mRes.getString(1).trim();
                    Fix_Running = mRes.getString(2).trim();
                }

                Map<String, Object> mMap = new HashMap<>();
                mMap.put("Voucher", Voucher);
                mMap.put("Fix_Running", Fix_Running);
                mJSonArr.put(mMap);
            } else {
                System.out.println("Server can't connect.");
            }
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
        return mJSonArr;

    }

    public static JSONArray CheckVoucher_EXP(String HC_RCNO, String cono, String divi) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
        String Voucher = "";

        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "SELECT  CASE WHEN HR_VCNO2 = '' THEN 'ไม่มีเลขที่ Voucher' ELSE 'Voucher : ' || HR_VCNO2 END AS HR_VCNO2 \n"
                        + "FROM " + DBPRD + ".HR_RECEIPTEXPENSE\n"
                        + "WHERE HR_CONO = '" + cono.trim() + "'\n"
                        + "AND HR_DIVI = '" + divi.trim() + "'\n"
                        + "AND HR_RCNO = '" + HC_RCNO.trim() + "'";

                ResultSet mRes = stmt.executeQuery(query);
                while (mRes.next()) {
                    Voucher = mRes.getString(1).trim();
                }

                Map<String, Object> mMap = new HashMap<>();
                mMap.put("Voucher", Voucher);

                mJSonArr.put(mMap);
            } else {
                System.out.println("Server can't connect.");
            }
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
        return mJSonArr;

    }

    public static JSONArray CheckVoucher_EXPnew(String FNNO, String cono, String divi) throws Exception {

        System.out.println("---------------CheckVoucher_EXPnew-------------");
        System.out.println(FNNO);
        System.out.println(cono);
        System.out.println(divi);

        System.out.println("---------------CheckVoucher_EXPnew-------------");

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
        String Voucher = "";

        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "SELECT  CASE WHEN HR_VCNO2 = '' THEN 'ไม่มีเลขที่ Voucher' ELSE 'Voucher : ' || HR_VCNO2 END AS HR_VCNO2 \n"
                        + "FROM " + DBPRD + ".HR_RECEIPTEXPENSE\n"
                        + "WHERE HR_CONO = '" + cono.trim() + "'\n"
                        + "AND HR_DIVI = '" + divi.trim() + "'\n"
                        + "AND HR_FNNO = '" + FNNO.trim() + "'";

                ResultSet mRes = stmt.executeQuery(query);
                while (mRes.next()) {
                    Voucher = mRes.getString(1).trim();
                }

                Map<String, Object> mMap = new HashMap<>();
                mMap.put("Voucher", Voucher);

                mJSonArr.put(mMap);
            } else {
                System.out.println("Server can't connect.");
            }
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
        return mJSonArr;

    }

    public static JSONArray Call_GridClearExpense(String CONO, String DIVI) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
//                String query = "SELECT A.HR_CONO, A.HR_DIVI, A.HR_RCNO, A.HR_VCNO1,B.HC_REAMT,B.HC_TYPE, HC_BANK\n"
//                        + "FROM " + DBPRD + ".HR_RECEIPTEXPENSE A," + DBPRD + ".HR_RECEIPT B\n"
//                        + "WHERE A.HR_CONO = '" + CONO.trim() + "'\n"
//                        + "AND A.HR_DIVI = '" + DIVI.trim() + "'\n"
//                        + "AND A.HR_STS = '10'\n"
//                        + "AND B.HR_CONO = A.HR_CONO\n"
//                        + "AND A.HR_DIVI = B.HR_DIVI\n"
//                        + "AND A.HR_RCNO = B.HC_RCNO \n"
//                        + "AND B.HC_STS NOT IN ('1','9')";

                String query = "SELECT DISTINCT A.HR_CONO, A.HR_DIVI, COALESCE(A.HR_RCNO,'-'), A.HR_VCNO1,B.HC_REAMT,B.HC_TYPE, HC_BANK, C.H_RNNO,C.H_PYNO\n"
                        + "                       FROM " + DBPRD + ".HR_RECEIPTEXPENSE A, " + DBPRD + ".HR_RECEIPT B, " + DBPRD + ".HEAD_RECIPT C \n"
                        + "                       WHERE A.HR_CONO = '" + CONO.trim() + "'\n"
                        + "                      AND A.HR_DIVI = '" + DIVI.trim() + "'\n"
                        + "                       AND A.HR_STS = '10'\n"
                        + "                     AND B.HR_CONO = A.HR_CONO\n"
                        + "                      AND B.HR_CONO = C.H_CONO\n"
                        + "                       AND A.HR_DIVI = B.HR_DIVI\n"
                        + "                        AND A.HR_DIVI = C.H_DIVI\n"
                        + "                      AND C.H_RNNO = B.HC_FNNO \n"
                        + "                      AND A.HR_FNNO  = C.H_RNNO AND c.H_TYPE  IN  ('TRANSFER_EXP','CHECK_EXP')\n"
                        + "                       AND B.HC_STS NOT IN ('1','9')";

                System.out.println(query);

                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("HR_CONO", mRes.getString(1).trim());
                    mMap.put("HR_DIVI", mRes.getString(2).trim());
                    mMap.put("HR_RCNO", mRes.getString(3).trim());
                    mMap.put("HR_VCNO1", mRes.getString(4).trim());
                    mMap.put("HC_REAMT", mRes.getString(5).trim());
                    mMap.put("HC_TYPE", mRes.getString(6).trim());
                    mMap.put("HC_BANK", mRes.getString(7).trim());
                    mMap.put("H_RNNO", mRes.getString(8).trim());
                    mMap.put("H_PYNO", mRes.getString(9).trim());

                    mJSonArr.put(mMap);

                }

            } else {
                System.out.println("Server can't connect.");
            }
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
        return mJSonArr;

    }

    public static JSONArray SearchReceipt_ClearExpensenew(String CONO, String DIVI, String FNNO) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT HR_CONO, HR_DIVI, HC_RCNO, SUBSTR(HC_TRDT,1,4) || '-' || SUBSTR(HC_TRDT,5,2)|| '-' || SUBSTR(HC_TRDT,7,2) as HC_TRDT , HC_PYNO, HC_REAMT, HC_USER, HC_STS ,TRIM(COALESCE(OKCUNM,'-')) AS OKCUNM   \n"
                        + ",HC_TYPE, HC_BANK, HC_ACCODE,CASE WHEN HC_PYDT= 0 THEN '-' ELSE SUBSTR(HC_PYDT,7,2) || '-' || SUBSTR(HC_PYDT,5,2)|| '-' || SUBSTR(HC_PYDT,1,4) END AS HC_PYDT, HC_CHKNO \n"
                        + ",COALESCE(HR_BKCHG,0)  AS HR_BKCHG, SUBSTR(HC_PYDT,1,4) || '-' || SUBSTR(HC_PYDT,5,2)|| '-' || SUBSTR(HC_PYDT,7,2) as SHC_PYDT\n"
                        + "FROM " + DBPRD + ".HR_RECEIPT\n"
                        + "LEFT JOIN " + M3DB + ".OCUSMA ON OKCUNO = HC_PYNO AND OKCONO = HR_CONO  \n"
                        + "WHERE HR_CONO = '" + CONO.trim() + "'\n"
                        + "and HR_DIVI = '" + DIVI.trim() + "'\n"
                        + "and HC_FNNO = '" + FNNO.trim() + "' limit 1\n";

                System.out.println("callllllllllll" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("HR_CONO", mRes.getString(1).trim());
                    mMap.put("HR_DIVI", mRes.getString(2).trim());
                    mMap.put("HC_RCNO", mRes.getString(3).trim());
                    mMap.put("HC_TRDT", mRes.getString(4).trim());
                    mMap.put("HC_PYNO", mRes.getString(5).trim());
                    mMap.put("HC_REAMT", mRes.getString(6).trim());
                    mMap.put("HC_USER", mRes.getString(7).trim());
                    mMap.put("HC_STS", mRes.getString(8).trim());
                    mMap.put("OKCUNM", mRes.getString(9).trim());

                    mMap.put("HC_TYPE", mRes.getString(10).trim());
                    mMap.put("HC_BANK", mRes.getString(11).trim());
                    mMap.put("HC_ACCODE", mRes.getString(12).trim());
                    mMap.put("HC_PYDT", mRes.getString(13).trim());
                    mMap.put("HC_CHKNO", mRes.getString(14).trim());
                    mMap.put("HR_BKCHG", mRes.getString(15).trim());
                    mMap.put("SHC_PYDT", mRes.getString(16).trim());
                    mJSonArr.put(mMap);
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

        return mJSonArr;

    }

    public static JSONArray SearchReceipt_ClearExpense(String CONO, String DIVI, String RCNO) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT HR_CONO, HR_DIVI, HC_RCNO, SUBSTR(HC_TRDT,1,4) || '-' || SUBSTR(HC_TRDT,5,2)|| '-' || SUBSTR(HC_TRDT,7,2) as HC_TRDT , HC_PYNO, HC_REAMT, HC_USER, HC_STS ,TRIM(COALESCE(OKCUNM,'-')) AS OKCUNM   \n"
                        + ",HC_TYPE, HC_BANK, HC_ACCODE,CASE WHEN HC_PYDT= 0 THEN '-' ELSE SUBSTR(HC_PYDT,7,2) || '-' || SUBSTR(HC_PYDT,5,2)|| '-' || SUBSTR(HC_PYDT,1,4) END AS HC_PYDT, HC_CHKNO \n"
                        + ",COALESCE(HR_BKCHG,0)  AS HR_BKCHG, SUBSTR(HC_PYDT,1,4) || '-' || SUBSTR(HC_PYDT,5,2)|| '-' || SUBSTR(HC_PYDT,7,2) as SHC_PYDT\n"
                        + "FROM " + DBPRD + ".HR_RECEIPT\n"
                        + "LEFT JOIN " + M3DB + ".OCUSMA ON OKCUNO = HC_PYNO AND OKCONO = HR_CONO  \n"
                        + "WHERE HR_CONO = '" + CONO.trim() + "'\n"
                        + "and HR_DIVI = '" + DIVI.trim() + "'\n"
                        + "and HC_RCNO = '" + RCNO.trim() + "'\n";

//                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("HR_CONO", mRes.getString(1).trim());
                    mMap.put("HR_DIVI", mRes.getString(2).trim());
                    mMap.put("HC_RCNO", mRes.getString(3).trim());
                    mMap.put("HC_TRDT", mRes.getString(4).trim());
                    mMap.put("HC_PYNO", mRes.getString(5).trim());
                    mMap.put("HC_REAMT", mRes.getString(6).trim());
                    mMap.put("HC_USER", mRes.getString(7).trim());
                    mMap.put("HC_STS", mRes.getString(8).trim());
                    mMap.put("OKCUNM", mRes.getString(9).trim());

                    mMap.put("HC_TYPE", mRes.getString(10).trim());
                    mMap.put("HC_BANK", mRes.getString(11).trim());
                    mMap.put("HC_ACCODE", mRes.getString(12).trim());
                    mMap.put("HC_PYDT", mRes.getString(13).trim());
                    mMap.put("HC_CHKNO", mRes.getString(14).trim());
                    mMap.put("HR_BKCHG", mRes.getString(15).trim());
                    mMap.put("SHC_PYDT", mRes.getString(16).trim());
                    mJSonArr.put(mMap);
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

        return mJSonArr;

    }

    public static JSONArray AmountExpensenew(String CONO, String DIVI, String FNNO) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT COALESCE(SUM(LRE_AMT),0) AS AMTEXPENSE\n"
                        + "                        FROM " + DBPRD + ".LR_LINEEXPEN\n"
                        + "                        WHERE LRE_CONO = '" + CONO + "'\n"
                        + "                        AND LRE_DIVI = '" + DIVI + "'\n"
                        + "                        AND LRE_RCNO  IN  (SELECT  HC_RCNO  FROM  " + DBPRD + ".HR_RECEIPT hr \n"
                        + "                        WHERE  HC_FNNO  = '" + FNNO + "')";

//                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("AMTEXPENSE", mRes.getString(1).trim());
                    mJSonArr.put(mMap);
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

        return mJSonArr;

    }

    public static JSONArray AmountExpense(String CONO, String DIVI, String RCNO) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT COALESCE(SUM(LRE_AMT),0) AS AMTEXPENSE\n"
                        + "FROM " + DBPRD + ".LR_LINEEXPEN\n"
                        + "WHERE LRE_CONO = '" + CONO.trim() + "'\n"
                        + "AND LRE_DIVI = '" + DIVI.trim() + "'\n"
                        + "AND LRE_RCNO = '" + RCNO.trim() + "'";

//                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("AMTEXPENSE", mRes.getString(1).trim());
                    mJSonArr.put(mMap);
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

        return mJSonArr;

    }

    public static JSONArray CheckCreateReceipt(String cono, String divi, String HC_RCNO) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
        String RCNO = "0";

        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "SELECT COUNT(*) AS RCNO\n"
                        + "FROM " + DBPRD + ".HR_RECEIPT\n"
                        + "WHERE HR_CONO = '" + cono.trim() + "'\n"
                        + "AND HR_DIVI ='" + divi.trim() + "'\n"
                        + "AND HC_RCNO = '" + HC_RCNO.trim() + "'";

                ResultSet mRes = stmt.executeQuery(query);
                while (mRes.next()) {
                    RCNO = mRes.getString(1).trim();
                }

                Map<String, Object> mMap = new HashMap<>();
                mMap.put("RCNO", RCNO);

                mJSonArr.put(mMap);
            } else {
                System.out.println("Server can't connect.");
            }
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
        return mJSonArr;

    }

    public static JSONArray GridLineClearExpense(String cono, String divi, String rcno) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT LREP_CONO, LREP_DIVI, LREP_RCNO, LREP_INVNO, LREP_INVDT, LREP_INVAMT, LREP_EXPAMT, LREP_STS \n"
                        + "FROM " + DBPRD + ".LR_LINECLEAREXP\n"
                        + "WHERE LREP_CONO = '" + cono.trim() + "'\n"
                        + "AND LREP_DIVI = '" + divi.trim() + "'\n"
                        + "AND LREP_RCNO = '" + rcno.trim() + "'";

//                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("LREP_CONO", mRes.getString(1).trim());
                    mMap.put("LREP_DIVI", mRes.getString(2).trim());
                    mMap.put("LREP_RCNO", mRes.getString(3).trim());
                    mMap.put("LREP_INVNO", mRes.getString(4).trim());
                    mMap.put("LREP_INVDT", mRes.getString(5).trim());
                    mMap.put("LREP_INVAMT", mRes.getString(6).trim());
                    mMap.put("LREP_EXPAMT", mRes.getString(7).trim());
                    mMap.put("LREP_STS", mRes.getString(8).trim());
//                    mMap.put("ESCUNO", mRes.getString(9).trim());

                    mJSonArr.put(mMap);

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

        return mJSonArr;

    }

    public static JSONArray Cal_LineAmountClearExpense(String CONO, String DIVI, String RCNO) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT COALESCE(sum(LREP_EXPAMT),0) AS  LREP_EXPAMT\n"
                        + "FROM " + DBPRD + ".LR_LINECLEAREXP\n"
                        + "WHERE LREP_CONO = '" + CONO.trim() + "'\n"
                        + "AND LREP_DIVI = '" + DIVI.trim() + "'\n"
                        + "AND LREP_RCNO = '" + RCNO.trim() + "'";

                System.out.println("line amount" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("LREP_EXPAMT", mRes.getString(1).trim());
                    mJSonArr.put(mMap);
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

        return mJSonArr;

    }

    public ResultSet CheckInvoiceWithSupp(String CONO, String DIVI, String RCNO, String invoice) {

        try {
            ResultSet RsDataReceipt;
            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();
            String Sql1 = "SELECT HR_CONO,HR_DIVI,HC_PYNO,ESCINO\n"
                    + "	FROM " + DBPRD + ".HR_RECEIPT," + M3DB + ".FSLEDG \n"
                    + "	WHERE HC_RCNO  ='" + RCNO.trim() + "'\n"
                    + " AND HR_CONO = '" + CONO + "' \n"
                    + " AND HR_DIVI = '" + DIVI.trim() + "' \n"
                    + "	AND HR_CONO = ESCONO\n"
                    + "	AND HR_DIVI = ESDIVI\n"
                    //                    + "	AND ESCUNO = HC_PYNO \n"
                    + "	AND ESPYNO = HC_PYNO\n"
                    + "	AND ESCINO = '" + invoice.trim() + "'";

            RsDataReceipt = sta.executeQuery(Sql1);
            System.out.println(Sql1);

            return RsDataReceipt;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;

    }

    public static JSONArray Get_Receiver_Driver(String CONO, String DIVI, String RCNO) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {
                Locale.setDefault(Locale.US);
                Date d = new Date();
                SimpleDateFormat A = new SimpleDateFormat("yyyy");
                String year = A.format(d);
                Statement stmt = conn.createStatement();

                String query = "SELECT A.UACONO,A.UADIVI,A.CARO_METH,A.CARO_CARNO,CASE WHEN B.Bill_NCarno != '-' THEN B.Bill_NCarno ELSE  A.NCarno END AS CAR\n"
                        + "FROM (\n"
                        + "SELECT distinct UACONO,UADIVI,CARO_METH,CARO_CARNO,DCTX40 as NCarno\n"
                        + "FROM " + DBPRD + ".SAL_CARORD," + M3DB + ".odhead\n"
                        + "LEFT JOIN " + M3DB + ".dcarri ON  DCcono = '10'\n"
                        + "where UACONO = '" + CONO.trim() + "' \n"
                        + "AND UADIVI = '" + DIVI.trim() + "' \n"
                        + "AND UAYEA4 = '" + year.trim() + "'\n"
                        + "AND UAIVNO IN (\n"
                        + "SELECT COALESCE(LR_INVNO,'0') AS LR_INVNO \n"
                        + "FROM " + DBPRD + ".LR_LINERECEIPT \n"
                        + "WHERE LR_CONO = '" + CONO.trim() + "' \n"
                        + "AND LR_DIVI = '" + DIVI.trim() + "' \n"
                        + "AND LR_RCNO = '" + RCNO.trim() + "'\n"
                        + ") \n"
                        + "AND DCTRCA =  CARO_CARNO\n"
                        + "and caro_orno = uaorno\n"
                        + ") A LEFT JOIN(\n"
                        + "SELECT LDB_METH, LDB_CARNO, LDB_CONO, LDB_CUNO, LDB_IVNO,COALESCE(DCTX40,'-')  as Bill_NCarno,LDB_DATE\n"
                        + "FROM " + DBPRD + ".SAL_LDEPOSBILL\n"
                        + "LEFT JOIN " + M3DB + ".dcarri ON  DCcono = '10'\n"
                        + "WHERE LDB_IVNO IN \n"
                        + "(\n"
                        + "SELECT COALESCE(LR_INVNO,'0') AS LR_INVNO \n"
                        + "FROM " + DBPRD + ".LR_LINERECEIPT \n"
                        + "WHERE LR_CONO = '" + CONO.trim() + "' \n"
                        + "AND LR_DIVI = '" + DIVI.trim() + "' \n"
                        + "AND LR_RCNO = '" + RCNO.trim() + "'\n"
                        + ")\n"
                        + "AND DCTRCA =  LDB_CARNO\n"
                        + ")B ON  B.LDB_CONO =  A.UACONO\n"
                        + "ORDER BY B.LDB_DATE DESC \n"
                        + "LIMIT 1";

                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("UACONO", mRes.getString(1).trim());
                    mMap.put("UADIVI", mRes.getString(2).trim());
                    mMap.put("CARO_METH", mRes.getString(3).trim());
                    mMap.put("CARO_CARNO", mRes.getString(4).trim());
                    mMap.put("CAR", mRes.getString(5).trim());
//                    mMap.put("", mRes.getString(6).trim());
//                    mMap.put("", mRes.getString(7).trim());
//                    mMap.put("", mRes.getString(3).trim());
//                    mMap.put("", mRes.getString(3).trim());

                    mJSonArr.put(mMap);
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

        return mJSonArr;

    }

    public static JSONArray List_Get_Receiver_Driver(String CONO, String DIVI, String RCNO) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {
                Locale.setDefault(Locale.US);
                Date d = new Date();
                SimpleDateFormat A = new SimpleDateFormat("yyyy");
                String year = A.format(d);
                Statement stmt = conn.createStatement();
//                String query = "SELECT DISTINCT A.UACONO,A.UADIVI,CASE WHEN B.Bill_NCarno != '-' THEN B.Bill_NCarno ELSE  A.NCarno END AS CAR\n"
//                        + "FROM (\n"
//                        + "SELECT distinct UACONO,UADIVI,CARO_METH,CARO_CARNO,DCTX40 as NCarno\n"
//                        + "FROM BRLDTA0100.SAL_CARORD,"+DBPRD+".odhead\n"
//                        + "LEFT JOIN "+DBPRD+".dcarri ON  DCcono = '10'\n"
//                        + "where UACONO = '" + CONO.trim() + "' \n"
//                        + "AND UADIVI = '" + DIVI.trim() + "' \n"
//                        + "AND UAYEA4 = '" + year.trim() + "'\n"
//                        + "AND UAIVNO IN (\n"
//                        + "SELECT COALESCE(LR_INVNO,'0') AS LR_INVNO \n"
//                        + "FROM BRLDTA0100.LR_LINERECEIPT \n"
//                        + "WHERE LR_CONO = '" + CONO.trim() + "' \n"
//                        + "AND LR_DIVI = '" + DIVI.trim() + "' \n"
//                        + "AND LR_RCNO = '" + RCNO.trim() + "'\n"
//                        + ") \n"
//                        + "AND DCTRCA =  CARO_CARNO\n"
//                        + "and caro_orno = uaorno\n"
//                        + ") A LEFT JOIN(\n"
//                        + "SELECT LDB_METH, LDB_CARNO, LDB_CONO, LDB_CUNO, LDB_IVNO,COALESCE(DCTX40,'-')  as Bill_NCarno,LDB_DATE\n"
//                        + "FROM BRLDTA0100.SAL_LDEPOSBILL\n"
//                        + "LEFT JOIN "+DBPRD+".dcarri ON  DCcono = '10'\n"
//                        + "WHERE LDB_IVNO IN \n"
//                        + "(\n"
//                        + "SELECT COALESCE(LR_INVNO,'0') AS LR_INVNO \n"
//                        + "FROM BRLDTA0100.LR_LINERECEIPT \n"
//                        + "WHERE LR_CONO = '" + CONO.trim() + "' \n"
//                        + "AND LR_DIVI = '" + DIVI.trim() + "' \n"
//                        + "AND LR_RCNO = '" + RCNO.trim() + "'\n"
//                        + ")\n"
//                        + "AND DCTRCA =  LDB_CARNO\n"
//                        + ")B ON  B.LDB_CONO =  A.UACONO\n";

                String query = "SELECT DISTINCT DATAALL.UACONO,DATAALL.UADIVI,DATAALL.CAR\n"
                        + "FROM ( \n"
                        + "SELECT DISTINCT A.UACONO,A.UADIVI,CASE WHEN B.Bill_NCarno != '-' THEN B.Bill_NCarno ELSE  A.NCarno END AS CAR\n"
                        + "FROM (\n"
                        + "SELECT distinct UACONO,UADIVI,CARO_METH,CARO_CARNO,DCTX40 as NCarno\n"
                        + "FROM " + DBPRD + ".SAL_CARORD," + M3DB + ".odhead\n"
                        + "LEFT JOIN " + M3DB + ".dcarri ON  DCcono = '10'\n"
                        + "where UACONO = '" + CONO.trim() + "' \n"
                        + "AND UADIVI = '" + DIVI.trim() + "' \n"
                        + "AND UAYEA4 =  '" + year.trim() + "'\n"
                        + "AND UAIVNO IN (\n"
                        + "SELECT COALESCE(LR_INVNO,'0') AS LR_INVNO \n"
                        + "FROM " + DBPRD + ".LR_LINERECEIPT \n"
                        + "WHERE LR_CONO = '" + CONO.trim() + "' \n"
                        + "AND LR_DIVI = '" + DIVI.trim() + "' \n"
                        + "AND LR_RCNO = '" + RCNO.trim() + "'\n"
                        + ") \n"
                        + "AND DCTRCA =  CARO_CARNO\n"
                        + "and caro_orno = uaorno\n"
                        + ") A LEFT JOIN(\n"
                        + "SELECT LDB_METH, LDB_CARNO, LDB_CONO, LDB_CUNO, LDB_IVNO,COALESCE(DCTX40,'-')  as Bill_NCarno,LDB_DATE\n"
                        + "FROM " + DBPRD + ".SAL_LDEPOSBILL\n"
                        + "LEFT JOIN " + M3DB + ".dcarri ON  DCcono = '10'\n"
                        + "WHERE LDB_IVNO IN \n"
                        + "(\n"
                        + "SELECT COALESCE(LR_INVNO,'0') AS LR_INVNO \n"
                        + "FROM " + DBPRD + ".LR_LINERECEIPT \n"
                        + "WHERE LR_CONO = '" + CONO.trim() + "' \n"
                        + "AND LR_DIVI = '" + DIVI.trim() + "' \n"
                        + "AND LR_RCNO = '" + RCNO.trim() + "'\n"
                        + ")\n"
                        + "AND DCTRCA =  LDB_CARNO\n"
                        + ")B ON  B.LDB_CONO =  A.UACONO\n"
                        + "UNION \n"
                        + "SELECT DISTINCT LDB_CONO AS UACONO,'101' AS UADIVI ,COALESCE(DCTX40,'-')   AS CAR\n"
                        + "FROM " + DBPRD + ".SAL_LDEPOSBILL\n"
                        + "LEFT JOIN " + M3DB + ".dcarri ON  DCcono = '10'\n"
                        + "WHERE LDB_IVNO IN \n"
                        + "(\n"
                        + "SELECT COALESCE(LR_INVNO,'0') AS LR_INVNO \n"
                        + "FROM " + DBPRD + ".LR_LINERECEIPT \n"
                        + "WHERE LR_CONO = '" + CONO.trim() + "' \n"
                        + "AND LR_DIVI = '" + DIVI.trim() + "' \n"
                        + "AND LR_RCNO = '" + RCNO.trim() + "'\n"
                        + ")\n"
                        + "AND DCTRCA =  LDB_CARNO\n"
                        + ") AS DATAALL";

                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("UACONO", mRes.getString(1).trim());
                    mMap.put("UADIVI", mRes.getString(2).trim());
                    mMap.put("CAR", mRes.getString(3).trim());

                    mJSonArr.put(mMap);
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

        return mJSonArr;

    }

    public static JSONArray Get_Receiver_Sale(String CONO, String DIVI, String payer) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {
                Locale.setDefault(Locale.US);
                Date d = new Date();
                SimpleDateFormat A = new SimpleDateFormat("yyyy");
                String year = A.format(d);
                Statement stmt = conn.createStatement();
                String query = "SELECT OKSMCD,JUUSID,JUTX40,COALESCE(JUDEPT,'') AS  JUDEPT \n"
                        + "FROM " + M3DB + ".OCUSMA,m3fdbprd.CMNUSR\n"
                        + "WHERE OKCONO= '" + CONO + "'\n"
                        + "AND OKSTAT = '20' \n"
                        + "AND OKCUNO ='" + payer.trim() + "'\n"
                        + "AND OKSMCD = JUUSID";

//                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("OKSMCD", mRes.getString(1).trim());
                    mMap.put("JUUSID", mRes.getString(2).trim());
                    mMap.put("JUDEPT", mRes.getString(3).trim());
                    mJSonArr.put(mMap);
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

        return mJSonArr;

    }

    public static int CheckReceiver(String CONO, String DIVI, String RCNO) {
        int count = 0;
        try {
            ResultSet RsDataReceipt;
            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();
            String Sql1 = "SELECT count(*) AS check\n"
                    + "	FROM " + DBPRD + ".RECEIPT_RECEIVER\n"
                    + "	WHERE RR_CONO = '" + CONO.trim() + "'\n"
                    + "	AND RR_DIVI = '" + DIVI.trim() + "'\n"
                    + "	AND RR_RCNO = '" + RCNO.trim() + "'";

            RsDataReceipt = sta.executeQuery(Sql1);
            while (RsDataReceipt.next()) {
                count = RsDataReceipt.getInt("check");
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return count;

    }

    public static int Check_Running_User(String CONO, String DIVI, String RRCODE, String USER) {
        int count = 0;
        try {
            ResultSet RsDataReceipt;
            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();
            String Sql1 = "SELECT COUNT(*) as COUNT \n"
                    + "FROM " + DBPRD + ".RECEIPT_RUNNING \n"
                    + "WHERE RR_CONO = '" + CONO.trim() + "'\n"
                    + "AND RR_DIVI ='" + DIVI.trim() + "'\n"
                    + "AND RR_USER = '" + USER.trim() + "'\n"
                    + "AND RR_LCCODE = '" + RRCODE.trim() + "'";

            RsDataReceipt = sta.executeQuery(Sql1);
            while (RsDataReceipt.next()) {
                count = RsDataReceipt.getInt("COUNT");
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return count;

    }

    public static JSONArray CheckCountReceivernew(String CONO, String DIVI, String FNNO) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "SELECT  COALESCE(a.RR_RCNO, 'NULL') AS REC , COALESCE(b.H_RCNO ,'NULL') AS RCNO \n"
                        + "FROM (\n"
                        + "SELECT *	FROM " + DBPRD + ".RECEIPT_RECEIVER\n"
                        + ") AS a \n"
                        + "FULL outer JOIN \n"
                        + "(\n"
                        + "SELECT * FROM " + DBPRD + ".HEAD_RECIPT WHERE H_CUNO != 'HEAD' \n"
                        + ")AS b \n"
                        + "ON b.h_rcno = a.RR_RCNO \n"
                        + "WHERE  b.H_RNNO = '" + FNNO.trim() + "'\n"
                        + "AND b.H_STS  = '1'\n"
                        + "AND b.H_CONO = '" + CONO.trim() + "'\n"
                        + "AND b.H_DIVI = '" + DIVI.trim() + "'";

//                System.out.println("count\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("REC", mRes.getInt(1));
                    mMap.put("RCNO", mRes.getInt(2));

                    mJSonArr.put(mMap);
                }
            } else {
                System.out.println("Server can't connect.");
            }
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
        return mJSonArr;

    }

    public static JSONArray CheckCountReceiver(String CONO, String DIVI, String RCNO) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "SELECT count(*) AS check\n"
                        + "	FROM " + DBPRD + ".RECEIPT_RECEIVER\n"
                        + "	WHERE RR_CONO = '" + CONO.trim() + "'\n"
                        + "	AND RR_DIVI = '" + DIVI.trim() + "'\n"
                        + "	AND RR_RCNO = '" + RCNO.trim() + "'";

//                System.out.println("count\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("check", mRes.getInt(1));
                    mJSonArr.put(mMap);
                }
            } else {
                System.out.println("Server can't connect.");
            }
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
        return mJSonArr;

    }

    public static JSONArray List_Receiver(String cono, String divi, String type) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = " SELECT MR_NO, MR_CONO, MR_DIVI, MR_DESC, MR_TYPE, MR_STS\n"
                        + " FROM " + DBPRD + ".M_RECEIVER\n"
                        + " WHERE MR_CONO = '" + cono.trim() + "'\n"
                        + " AND MR_DIVI = '" + divi.trim() + "'\n"
                        + " AND MR_TYPE = '" + type.trim() + "'\n"
                        + " AND MR_STS = '1'";

//                System.out.println("ListBANK\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("MR_NO", mRes.getString(1).trim());
                    mMap.put("MR_CONO", mRes.getString(2).trim());
                    mMap.put("MR_DIVI", mRes.getString(3).trim());
                    mMap.put("MR_DESC", mRes.getString(4).trim());
                    mMap.put("MR_TYPE", mRes.getString(5).trim());
                    mMap.put("MR_STS", mRes.getString(6).trim());

                    mJSonArr.put(mMap);

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

        return mJSonArr;

    }

    public static ResultSet CheckAccode(String CONO, String DIVI, String RCNO, String USER, String Location) {
        ResultSet RSL = null;
        try {
            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();
            String Sql1 = "1AC1202";
            RSL = sta.executeQuery(Sql1);
            System.out.println("Check accode" + Sql1);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return RSL;
    }

    public static ResultSet CheckVCStepPrint(String CONO, String DIVI, String RCNO, String USER, String Location) {
        ResultSet RSL = null;
        try {
            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();
            String Sql1 = "SELECT CASE WHEN COALESCE(HC_VCNO,'') = '' THEN '-' ELSE HC_VCNO END AS HC_VCNO,CASE WHEN COALESCE(RR_COUNT,'') = '' THEN '-' ELSE RR_COUNT END AS RR_COUNT\n"
                    + ",COALESCE(HC_FIXNO ,'-') AS HC_FIXNO\n"
                    + "FROM " + DBPRD + ".HR_RECEIPT\n"
                    + "LEFT JOIN " + DBPRD + ".RECEIPT_RUNNING ON RR_CONO = HR_CONO AND RR_DIVI = HR_DIVI  AND RR_USER = '" + USER.trim() + "' AND RR_LCCODE = '" + Location.trim() + "' \n"
                    + "AND RR_COUNT BETWEEN  RR_START  AND RR_END\n"
                    + "WHERE HR_CONO = '" + CONO.trim() + "'\n"
                    + "AND HR_DIVI ='" + DIVI.trim() + "'\n"
                    + "AND HC_RCNO = '" + RCNO.trim() + "'";
            RSL = sta.executeQuery(Sql1);
            System.out.println("zzzzzzzzzzzzzzzzzzzzzz");
            System.out.println("zzzzzzzzzzzzzzzzzzzzzz" + Sql1);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return RSL;
    }

    public static JSONArray CheckRunningPrint(String CONO, String DIVI, String USER, String LOCATION) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "SELECT RR_CONO, RR_DIVI, RR_LCCODE, RR_USER, RR_START, RR_END, RR_COUNT \n"
                        + "FROM " + DBPRD + ".RECEIPT_RUNNING  \n"
                        + "WHERE RR_CONO  = '" + CONO.trim() + "'\n"
                        + "AND RR_DIVI  = '" + DIVI.trim() + "'\n"
                        + "AND RR_USER  = '" + USER.trim() + "'\n"
                        + "AND RR_LCCODE = '" + LOCATION.trim() + "'\n"
                        + "AND RR_COUNT BETWEEN  RR_START  AND RR_END";

//                System.out.println("count\n" + query);
                ResultSet mRes = stmt.executeQuery(query);

                if (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("RR_COUNT", "Y");
                    mJSonArr.put(mMap);
                } else {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("RR_COUNT", "N");
                    mJSonArr.put(mMap);
                }
            } else {
                System.out.println("Server can't connect.");
            }
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
        return mJSonArr;

    }

    public static JSONArray Grid_Series(String cono, String divi, String location) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT RR_CONO, RR_DIVI, RR_LCCODE, RR_USER, RR_START, RR_END, RR_COUNT \n"
                        + "FROM " + DBPRD + ".RECEIPT_RUNNING \n"
                        + "WHERE RR_CONO = '" + cono.trim() + "'\n"
                        + "AND RR_DIVI ='" + divi.trim() + "'\n"
                        //                        + "AND RR_USER = '" + username.trim() + "'\n"
                        + "AND RR_LCCODE = '" + location.trim() + "'";
                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("RR_CONO", mRes.getString(1).trim());
                    mMap.put("MR_CONO", mRes.getString(2).trim());
                    mMap.put("RR_LCCODE", mRes.getString(3).trim());
                    mMap.put("RR_USER", mRes.getString(4).trim());
                    mMap.put("RR_START", mRes.getString(5).trim());
                    mMap.put("RR_END", mRes.getString(6).trim());
                    mMap.put("RR_COUNT", mRes.getString(7).trim());
                    mJSonArr.put(mMap);

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

        return mJSonArr;

    }

    public static ResultSet Reprint_Detail(String CONO, String DIVI, String RCNO) {
        ResultSet RSL = null;
        try {
            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();
            String Sql1 = "SELECT HR_CONO ,HR_DIVI ,HC_RCNO ,HC_USER  ,HC_LOCATION,CASE WHEN COALESCE(RR_COUNT,'') = '' THEN '-' ELSE RR_COUNT END AS RR_COUNT\n"
                    + ",CASE WHEN COALESCE(HC_FIXNO,'') = ''  THEN '-' ELSE HC_FIXNO END AS HC_FIXNO\n"
                    + "FROM " + DBPRD + ".HR_RECEIPT \n"
                    + "LEFT JOIN " + DBPRD + ".RECEIPT_RUNNING ON RR_CONO = HR_CONO AND RR_DIVI = HR_DIVI  AND RR_USER = HC_USER AND RR_LCCODE = HC_LOCATION\n"
                    + "AND RR_COUNT BETWEEN  RR_START  AND RR_END\n"
                    + "WHERE HR_CONO ='" + CONO.trim() + "'\n"
                    + "AND HR_DIVI ='" + DIVI.trim() + "' \n"
                    + "AND HC_RCNO = '" + RCNO.trim() + "'\n"
                    + "AND HC_STS IN ('3','2')\n";
//                    + "AND HC_FIXNO is null";
            RSL = sta.executeQuery(Sql1);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return RSL;
    }

    public static int FindUserPagePaper(String cono, String divi, String rcno) {
        int page = 1;
        try {
            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();
            String sql = "SELECT COUNT(*) AS page "
                    + "FROM " + DBPRD + ".LR_LINERECEIPT ll "
                    + "WHERE LR_CONO = '" + cono.trim() + "' "
                    + "AND LR_DIVI = '" + divi.trim() + "' "
                    + "AND LR_RCNO = '" + rcno.trim() + "'";
            ResultSet rsl = sta.executeQuery(sql);
            while (rsl.next()) {
                page = rsl.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        if (page == 0) {
            page = 1;
        }
        return page;
    }

    public static JSONArray SearchReceipt_Cancel(String CONO, String DIVI, String RCNO, String Location) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();

                String query = "SELECT HR_CONO, HR_DIVI, HC_RCNO,HC_REAMT ,TRIM(o.OKCUNM) AS okcunm ,HC_PYNO\n"
                        + "FROM " + DBPRD + ".HR_RECEIPT a \n"
                        + "LEFT JOIN " + M3DB + ".OCUSMA o ON o.OKCONO = a.HR_CONO AND o.OKSTAT ='20'AND a.HC_PYNO = o.OKCUNO \n"
                        + "WHERE A.HR_CONO = '" + CONO.trim() + "'\n"
                        + "and A.HR_DIVI = '" + DIVI.trim() + "'\n"
                        + "AND A.HC_LOCATION = '" + Location.trim() + "' \n"
                        + "and A.HC_STS != '9' "
                        + "and A.HC_RCNO = '" + RCNO.trim() + "'";

//                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("HR_CONO", mRes.getString(1).trim());
                    mMap.put("HR_DIVI", mRes.getString(2).trim());
                    mMap.put("HC_RCNO", mRes.getString(3).trim());
                    mMap.put("HC_REAMT", mRes.getString(4).trim());
                    mMap.put("okcunm", mRes.getString(5).trim());
                    mMap.put("HC_PYNO", mRes.getString(6).trim());
                    mJSonArr.put(mMap);
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

        return mJSonArr;

    }

    public static JSONArray Grid_Cancel(String cono, String divi) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT A.RC_CONO, A.RC_DIVI, A.RC_RCNO, A.RC_CRDT, A.RC_REASON, A.RC_ACTION, A.RC_APP1, A.RC_APP2, A.RC_APPNOW,  A.RC_STS \n"
                        + ",CASE WHEN A.RC_STS = '20' THEN 'Wait for K.' || trim(B.ST_ENAME) || ' approve.' WHEN A.RC_STS = '30' THEN 'Wait for K.' || trim(C.ST_ENAME) || ' approve.'\n"
                        + "WHEN A.RC_STS = '50' THEN 'Approved' ELSE 'Cancel ' end as Status ,R.HC_PYNO ,R.HC_REAMT\n"
                        + "FROM " + DBPRD + ".RECEIPT_CANCEL A\n"
                        + "LEFT JOIN " + DBPRD + ".HR_RECEIPT R ON R.HR_CONO = A.RC_CONO AND A.RC_DIVI = R.HR_DIVI  AND R.HC_RCNO = A.RC_RCNO \n"
                        + "LEFT JOIN " + DBPRD + ".STAFFLIST B ON B.ST_CONO = A.RC_CONO AND B.ST_CODE = A.RC_APP1 \n"
                        + "LEFT JOIN " + DBPRD + ".STAFFLIST C ON C.ST_CONO = A.RC_CONO AND C.ST_CODE = A.RC_APP2 \n"
                        + "WHERE RC_CONO = '" + cono.trim() + "' \n"
                        + "AND RC_DIVI = '" + divi.trim() + "' \n"
                        + "ORDER BY RC_CRDT DESC ";
                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("RC_CONO", mRes.getString(1).trim());
                    mMap.put("RC_DIVI", mRes.getString(2).trim());
                    mMap.put("RC_RCNO", mRes.getString(3).trim());
                    mMap.put("RC_CRDT", mRes.getString(4).trim());
                    mMap.put("RC_REASON", mRes.getString(5).trim());
                    mMap.put("RC_ACTION", mRes.getString(6).trim());
                    mMap.put("RC_APP1", mRes.getString(7).trim());
                    mMap.put("RC_APP2", mRes.getString(8).trim());
                    mMap.put("RC_APPNOW", mRes.getString(9).trim());
                    mMap.put("RC_STS", mRes.getString(10).trim());
                    mMap.put("Status", mRes.getString(11).trim());
                    mMap.put("HC_PYNO", mRes.getString(12).trim());
                    mMap.put("HC_REAMT", mRes.getString(13).trim());

                    mJSonArr.put(mMap);
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

        return mJSonArr;

    }

    public static JSONArray CountInvoicenew(String cono, String divi, String FNNO) throws Exception {
        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();

                String query = "SELECT COALESCE(a.LR_RCNO, 'NULL') AS INV , COALESCE(b.H_RCNO ,'NULL') AS RCNO\n"
                        + "FROM (\n"
                        + "SELECT * FROM " + DBPRD + ".LR_LINERECEIPT \n"
                        + ") AS a \n"
                        + "FULL outer JOIN \n"
                        + "(\n"
                        + "SELECT * FROM " + DBPRD + ".HEAD_RECIPT hr WHERE H_RCNO != 'RCNO'\n"
                        + ")AS b \n"
                        + "ON b.h_rcno = a.LR_RCNO \n"
                        + "WHERE  b.H_RNNO = '" + FNNO.trim() + "'\n"
                        + "AND b.H_STS  = '1'\n"
                        + "AND b.H_CONO = '" + cono.trim() + "' AND b.H_rcno != 'RUNO' \n"
                        + "AND b.H_DIVI = '" + divi.trim() + "'";

                System.out.println("xoxo :" + query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("INV", mRes.getString(1).trim());
                    mMap.put("RCNO", mRes.getString(2).trim());

                    mJSonArr.put(mMap);
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

        return mJSonArr;

    }

    public static JSONArray CountInvoice(String cono, String divi, String rcno) throws Exception {
        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();

                String query = "SELECT count (*) AS invoiceCount  \n"
                        + "FROM " + DBPRD + ".LR_LINERECEIPT\n"
                        + "WHERE LR_CONO = '" + cono.trim() + "'\n"
                        + "AND LR_DIVI = '" + divi.trim() + "'\n"
                        + "AND LR_RCNO = '" + rcno.trim() + "'";

//                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("invoiceCount", mRes.getString(1).trim());
                    mJSonArr.put(mMap);
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

        return mJSonArr;

    }

    public static JSONArray Call_GridBankMappingnewDEPOSIT(String CONO, String DIVI, String LOCATION, String TYPE) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "SELECT DISTINCT hr.H_RNNO, BM_ID ,BM_CONO ,BM_DIVI ,BM_RCNO ,BM_CUNO ,BM_AMOUNT  ,BM_CUNO || ' : ' || COALESCE(b.OKCUNM,' ') as custname,BM_BANK \n"
                        + "from " + DBPRD + ".BANK_MAPPING a\n"
                        + "inner  JOIN  " + DBPRD + ".HEAD_RECIPT hr  ON a.BM_CONO = hr.H_CONO AND  H_RNNO = BM_FNNO \n"
                        + "left join " + M3DB + ".ocusma b on b.OKCONO  = a.BM_CONO  and b.OKCUNO = a.BM_CUNO \n"
                        + "where BM_CONO = '" + CONO.trim() + "'\n"
                        + "and BM_STATUS = '10' \n"
                        + "AND BM_FNNO  != '' \n"
                        + "AND BM_FNNO  IS NOT NULL  AND hr.H_TYPE ='TRANSFER_DEPOSIT'\n"
                        + "AND BM_RCNO  IS NULL  and hr.H_LOCATION = '" + LOCATION + "'  \n"
                        + "OR  BM_RCNO = '' \n"
                        + "AND BM_CONO = '" + CONO.trim() + "'\n"
                        + "and BM_STATUS = '10' \n"
                        + "AND BM_FNNO  != '' \n"
                        + "AND BM_FNNO  IS NOT NULL  AND hr.H_TYPE ='TRANSFER_DEPOSIT'\n"
                        + "and hr.H_STS = '1'  ORDER BY H_RNNO DESC   ";
                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

                    if (mRes.getString(1) != null) {
                        mMap.put("H_RNNO", mRes.getString(1).trim());

                    }
                    if (mRes.getString(2) != null) {

                        mMap.put("BM_ID", mRes.getString(2).trim());
                    }
                    if (mRes.getString(3) != null) {

                        mMap.put("BM_CONO", mRes.getString(3).trim());
                    }
                    if (mRes.getString(4) != null) {

                        mMap.put("BM_DIVI", mRes.getString(4).trim());
                    }
                    if (mRes.getString(5) != null) {

                        mMap.put("BM_RCNO", mRes.getString(5).trim());
                    }
                    if (mRes.getString(6) != null) {

                        mMap.put("BM_CUNO", mRes.getString(6).trim());
                    }
                    if (mRes.getString(7) != null) {

                        mMap.put("BM_AMOUNT", mRes.getString(7).trim());
                    }
                    if (mRes.getString(8) != null) {

                        mMap.put("custname", mRes.getString(8).trim());
                    }
                    if (mRes.getString(9) != null) {

                        mMap.put("BM_BANK", mRes.getString(9).trim());
                    }

                    mJSonArr.put(mMap);

                }

            } else {
                System.out.println("Server can't connect.");
            }
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
        return mJSonArr;

    }

    public static JSONArray Call_GridBankMappingnewEXP(String CONO, String DIVI, String LOCATION, String TYPE) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "SELECT DISTINCT hr.H_RNNO, BM_ID ,BM_CONO ,BM_DIVI ,BM_RCNO ,BM_CUNO ,BM_AMOUNT  ,BM_CUNO || ' : ' || COALESCE(b.OKCUNM,' ') as custname,BM_BANK \n"
                        + "from " + DBPRD + ".BANK_MAPPING a\n"
                        + "inner  JOIN  " + DBPRD + ".HEAD_RECIPT hr  ON a.BM_CONO = hr.H_CONO AND  H_RNNO = BM_FNNO \n"
                        + "left join " + M3DB + ".ocusma b on b.OKCONO  = a.BM_CONO  and b.OKCUNO = a.BM_CUNO \n"
                        + "where BM_CONO = '" + CONO.trim() + "'\n"
                        + "and BM_STATUS = '10' \n"
                        + "AND BM_FNNO  != '' \n"
                        + "AND BM_FNNO  IS NOT NULL  AND hr.H_TYPE ='TRANSFER_EXP'\n"
                        + "AND BM_RCNO  IS NULL  and hr.H_LOCATION = '" + LOCATION + "'  \n"
                        + "OR  BM_RCNO = '' \n"
                        + "AND BM_CONO = '" + CONO.trim() + "'\n"
                        + "and BM_STATUS = '10' \n"
                        + "AND BM_FNNO  != '' \n"
                        + "AND BM_FNNO  IS NOT NULL  AND hr.H_TYPE ='TRANSFER_EXP'\n"
                        + "and hr.H_STS = '1'  ORDER BY H_RNNO DESC   ";
                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

                    if (mRes.getString(1) != null) {
                        mMap.put("H_RNNO", mRes.getString(1).trim());

                    }
                    if (mRes.getString(2) != null) {

                        mMap.put("BM_ID", mRes.getString(2).trim());
                    }
                    if (mRes.getString(3) != null) {

                        mMap.put("BM_CONO", mRes.getString(3).trim());
                    }
                    if (mRes.getString(4) != null) {

                        mMap.put("BM_DIVI", mRes.getString(4).trim());
                    }
                    if (mRes.getString(5) != null) {

                        mMap.put("BM_RCNO", mRes.getString(5).trim());
                    }
                    if (mRes.getString(6) != null) {

                        mMap.put("BM_CUNO", mRes.getString(6).trim());
                    }
                    if (mRes.getString(7) != null) {

                        mMap.put("BM_AMOUNT", mRes.getString(7).trim());
                    }
                    if (mRes.getString(8) != null) {

                        mMap.put("custname", mRes.getString(8).trim());
                    }
                    if (mRes.getString(9) != null) {

                        mMap.put("BM_BANK", mRes.getString(9).trim());
                    }

                    mJSonArr.put(mMap);

                }

            } else {
                System.out.println("Server can't connect.");
            }
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
        return mJSonArr;

    }

    public static JSONArray Call_GridBankMappingnew(String CONO, String DIVI, String LOCATION, String TYPE) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "SELECT DISTINCT hr.H_RNNO, BM_ID ,BM_CONO ,BM_DIVI ,BM_RCNO ,BM_CUNO ,BM_AMOUNT  ,BM_CUNO || ' : ' || COALESCE(b.OKCUNM,' ') as custname,BM_BANK \n"
                        + "from " + DBPRD + ".BANK_MAPPING a\n"
                        + "inner  JOIN  " + DBPRD + ".HEAD_RECIPT hr  ON a.BM_CONO = hr.H_CONO AND  H_RNNO = BM_FNNO \n"
                        + "left join " + M3DB + ".ocusma b on b.OKCONO  = a.BM_CONO  and b.OKCUNO = a.BM_CUNO \n"
                        + "where BM_CONO = '" + CONO.trim() + "'\n"
                        + "and BM_STATUS = '10' \n"
                        //                        + "AND H_CUNO  = 'HEAD'  \n"
                        + "AND BM_FNNO  != '' \n"
                        + "AND BM_FNNO  IS NOT NULL AND hr.H_TYPE ='TRANSFER' \n"
                        + "AND BM_RCNO  IS NULL  and hr.H_LOCATION = '" + LOCATION + "'  \n"
                        + "OR  BM_RCNO = '' \n"
                        + "AND BM_CONO = '" + CONO.trim() + "'\n"
                        + "and BM_STATUS = '10' \n"
                        + "AND BM_FNNO  != '' \n"
                        + "AND BM_FNNO  IS NOT NULL  AND hr.H_TYPE ='TRANSFER'\n"
                        + "and hr.H_STS = '1'  ORDER BY H_RNNO DESC   ";
                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

                    if (mRes.getString(1) != null) {
                        mMap.put("H_RNNO", mRes.getString(1).trim());

                    }
                    if (mRes.getString(2) != null) {

                        mMap.put("BM_ID", mRes.getString(2).trim());
                    }
                    if (mRes.getString(3) != null) {

                        mMap.put("BM_CONO", mRes.getString(3).trim());
                    }
                    if (mRes.getString(4) != null) {

                        mMap.put("BM_DIVI", mRes.getString(4).trim());
                    }
                    if (mRes.getString(5) != null) {

                        mMap.put("BM_RCNO", mRes.getString(5).trim());
                    }
                    if (mRes.getString(6) != null) {

                        mMap.put("BM_CUNO", mRes.getString(6).trim());
                    }
                    if (mRes.getString(7) != null) {

                        mMap.put("BM_AMOUNT", mRes.getString(7).trim());
                    }
                    if (mRes.getString(8) != null) {

                        mMap.put("custname", mRes.getString(8).trim());
                    }
                    if (mRes.getString(9) != null) {

                        mMap.put("BM_BANK", mRes.getString(9).trim());
                    }

                    mJSonArr.put(mMap);

                }

            } else {
                System.out.println("Server can't connect.");
            }
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
        return mJSonArr;

    }

    public static JSONArray Call_GridBankMapping(String CONO, String DIVI, String LOCATION, String TYPE) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "SELECT BM_ID ,BM_CONO ,BM_DIVI ,BM_RCNO ,BM_CUNO ,BM_AMOUNT  ,BM_CUNO || ' : ' || COALESCE(b.OKCUNM,' ') as custname,BM_BANK \n"
                        + "from " + DBPRD + ".BANK_MAPPING a\n"
                        + "inner join " + DBPRD + ".HR_RECEIPT c on c.HR_CONO = a.BM_CONO and a.BM_RCNO = c.HC_RCNO \n"
                        + "left join " + M3DB + ".ocusma b on b.OKCONO  = a.BM_CONO  and b.OKCUNO = a.BM_CUNO \n"
                        + "where BM_CONO = '" + CONO.trim() + "'\n"
                        + "and BM_RCNO is not null \n"
                        + "and BM_STATUS = '10'  \n"
                        + "and c.HC_STS = '1'  AND c.HC_LOCATION = '" + LOCATION.trim() + "' and c.HC_TYPE = '" + TYPE.trim() + "' ";
                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("BM_ID", mRes.getString(1).trim());
                    mMap.put("BM_CONO", mRes.getString(2).trim());
                    mMap.put("BM_DIVI", mRes.getString(3).trim());
                    mMap.put("BM_RCNO", mRes.getString(4).trim());
                    mMap.put("BM_CUNO", mRes.getString(5).trim());
                    mMap.put("BM_AMOUNT", mRes.getString(6).trim());
                    mMap.put("custname", mRes.getString(7).trim());
                    mMap.put("BM_BANK", mRes.getString(8).trim());

                    mJSonArr.put(mMap);

                }

            } else {
                System.out.println("Server can't connect.");
            }
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
        return mJSonArr;

    }

    public static JSONArray SearchReceipt_BankMapping(String CONO, String DIVI, String RCNO, String Location) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();

                String query = "SELECT G.*,H.BM_AMOUNT ,H.BM_BKCHARGE ,H.BM_OVPAY ,H.BM_CNDN  \n"
                        + "from (\n"
                        + "SELECT E.*,COALESCE(F.RR_DESC,'-') AS  RR_DESC\n"
                        + "FROM (\n"
                        + "SELECT HR_CONO, HR_DIVI,HC_RCNO, HC_TRDT, HC_PYNO, HC_REAMT, HC_USER, HC_STS,TRIM(COALESCE(OKCUNM,'-')) AS OKCUNM \n"
                        + ",HC_TYPE, HC_BANK, HC_ACCODE,HC_PYDT, HC_CHKNO,HR_BKCHG,SHC_PYDT\n"
                        + "FROM (\n"
                        + "SELECT HR_CONO, HR_DIVI, HC_RCNO, SUBSTR(HC_TRDT,1,4) || '-' || SUBSTR(HC_TRDT,5,2)|| '-' || SUBSTR(HC_TRDT,7,2) as HC_TRDT , HC_PYNO, HC_REAMT, HC_USER, HC_STS   \n"
                        + ",HC_TYPE, HC_BANK, HC_ACCODE,CASE WHEN HC_PYDT= 0 THEN '-' ELSE SUBSTR(HC_PYDT,7,2) || '-' || SUBSTR(HC_PYDT,5,2)|| '-' || SUBSTR(HC_PYDT,1,4) END AS HC_PYDT, HC_CHKNO \n"
                        + ",COALESCE(HR_BKCHG,0)  AS HR_BKCHG, SUBSTR(HC_PYDT,1,4) || '-' || SUBSTR(HC_PYDT,5,2)|| '-' || SUBSTR(HC_PYDT,7,2) as SHC_PYDT\n"
                        + "FROM " + DBPRD + ".HR_RECEIPT a ," + DBPRD + ".RECEIPT_LOCAFNC b \n"
                        + "WHERE A.HR_CONO = '" + CONO.trim() + "'\n"
                        + "and A.HR_DIVI = '" + DIVI.trim() + "'\n"
                        + "AND A.HR_CONO = B.RL_CONO\n"
                        + "AND A.HR_DIVI = B.RL_DIVI\n"
                        + "AND A.HC_TYPE LIKE '%' || B.RL_TYPE || '%'\n"
                        + "AND A.HC_TYPE NOT LIKE '%DEPOSIT' \n"
                        + "AND A.HC_LOCATION = '" + Location.trim() + "' \n"
                        + "AND B.RL_LCCODE = A.HC_LOCATION\n"
                        + "and A.HC_RCNO = '" + RCNO.trim() + "'\n"
                        + "and A.HC_STS = '1'\n"
                        + ") C LEFT JOIN \n"
                        + "(\n"
                        + "	SELECT OKCONO,OKCUNO,TRIM(COALESCE(OKCUNM,'-')) AS OKCUNM \n"
                        + "	FROM " + M3DB + ".OCUSMA\n"
                        + "	WHERE OKCONO = '" + CONO.trim() + "'\n"
                        + ") D ON D.OKCONO = C.HR_CONO AND D.OKCUNO = C.HC_PYNO\n"
                        + ") E LEFT JOIN (\n"
                        + "SELECT RR_CONO, RR_DIVI, RR_RCNO, COALESCE(RR_DESC,'-') AS  RR_DESC\n"
                        + "FROM " + DBPRD + ".RECEIPT_RECEIVER\n"
                        + ") F ON F.RR_CONO = E.HR_CONO AND F.RR_DIVI = E.HR_DIVI AND F.RR_RCNO = E.HC_RCNO\n"
                        + ") G left join (\n"
                        + "select BM_CONO , BM_DIVI ,BM_RCNO ,BM_ACCODE ,BM_BANK,COALESCE(BM_CUNO, '-') AS  BM_CUNO ,BM_AMOUNT ,COALESCE(BM_BKCHARGE, 0) AS BM_BKCHARGE ,COALESCE(BM_OVPAY, 0) AS BM_OVPAY ,COALESCE(BM_CNDN, '-') AS BM_CNDN  \n"
                        + "from " + DBPRD + ".BANK_MAPPING\n"
                        + "where BM_CONO  = '" + CONO.trim() + "'\n"
                        + "and BM_DIVI  = '" + DIVI.trim() + "'\n"
                        + "and BM_RCNO  = '" + RCNO.trim() + "'\n"
                        + ") H on H.BM_CONO = G.HR_CONO and H.BM_DIVI = G.HR_DIVI and H.BM_RCNO = G.HC_RCNO and H.BM_BANK = G.HC_BANK  ";

//                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("HR_CONO", mRes.getString(1).trim());
                    mMap.put("HR_DIVI", mRes.getString(2).trim());
                    mMap.put("HC_RCNO", mRes.getString(3).trim());
                    mMap.put("HC_TRDT", mRes.getString(4).trim());
                    mMap.put("HC_PYNO", mRes.getString(5).trim());
                    mMap.put("HC_REAMT", mRes.getString(6).trim());
                    mMap.put("HC_USER", mRes.getString(7).trim());
                    mMap.put("HC_STS", mRes.getString(8).trim());
                    mMap.put("OKCUNM", mRes.getString(9).trim());

                    mMap.put("HC_TYPE", mRes.getString(10).trim());
                    mMap.put("HC_BANK", mRes.getString(11).trim());
                    mMap.put("HC_ACCODE", mRes.getString(12).trim());
                    mMap.put("HC_PYDT", mRes.getString(13).trim());
                    mMap.put("HC_CHKNO", mRes.getString(14).trim());
                    mMap.put("HR_BKCHG", mRes.getString(15).trim());
                    mMap.put("SHC_PYDT", mRes.getString(16).trim());
                    mMap.put("RR_DESC", mRes.getString(17).trim());
                    mMap.put("BM_AMOUNT", mRes.getString(18).trim());
                    mMap.put("BM_BKCHARGE", mRes.getString(19).trim());
                    mMap.put("BM_OVPAY", mRes.getString(20).trim());
                    mMap.put("BM_CNDN", mRes.getString(21).trim());
                    mJSonArr.put(mMap);
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

        return mJSonArr;

    }

    public static JSONArray SearchReceipt_HeadMappingnew(String CONO, String DIVI, String RCNO, String Location) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();

                String query = "SELECT G.*,H.BM_AMOUNT ,H.BM_BKCHARGE ,H.BM_OVPAY ,H.BM_CNDN  \n"
                        + "from (\n"
                        + "SELECT E.*,COALESCE(F.RR_DESC,'-') AS  RR_DESC\n"
                        + "FROM (\n"
                        + "SELECT HR_CONO, HR_DIVI,HC_RCNO, HC_TRDT, HC_PYNO, HC_REAMT, HC_USER, HC_STS,TRIM(COALESCE(OKCUNM,'-')) AS OKCUNM \n"
                        + ",HC_TYPE, HC_BANK, HC_ACCODE,HC_PYDT, HC_CHKNO,HR_BKCHG,SHC_PYDT\n"
                        + "FROM (\n"
                        + "SELECT HR_CONO, HR_DIVI, HC_RCNO, SUBSTR(HC_TRDT,1,4) || '-' || SUBSTR(HC_TRDT,5,2)|| '-' || SUBSTR(HC_TRDT,7,2) as HC_TRDT , HC_PYNO, HC_REAMT, HC_USER, HC_STS   \n"
                        + ",HC_TYPE, HC_BANK, HC_ACCODE,CASE WHEN HC_PYDT= 0 THEN '-' ELSE SUBSTR(HC_PYDT,7,2) || '-' || SUBSTR(HC_PYDT,5,2)|| '-' || SUBSTR(HC_PYDT,1,4) END AS HC_PYDT, HC_CHKNO \n"
                        + ",COALESCE(HR_BKCHG,0)  AS HR_BKCHG, SUBSTR(HC_PYDT,1,4) || '-' || SUBSTR(HC_PYDT,5,2)|| '-' || SUBSTR(HC_PYDT,7,2) as SHC_PYDT\n"
                        + "FROM " + DBPRD + ".HR_RECEIPT a ," + DBPRD + ".RECEIPT_LOCAFNC b \n"
                        + "WHERE A.HR_CONO = '" + CONO.trim() + "'\n"
                        + "and A.HR_DIVI = '" + DIVI.trim() + "'\n"
                        + "AND A.HR_CONO = B.RL_CONO\n"
                        + "AND A.HR_DIVI = B.RL_DIVI\n"
                        + "AND A.HC_TYPE LIKE '%' || B.RL_TYPE || '%'\n"
                        + "AND A.HC_TYPE NOT LIKE '%DEPOSIT' \n"
                        + "AND A.HC_LOCATION = '" + Location.trim() + "' \n"
                        + "AND B.RL_LCCODE = A.HC_LOCATION\n"
                        + "and A.HC_RCNO = '" + RCNO.trim() + "'\n"
                        + "and A.HC_STS = '1'\n"
                        + ") C LEFT JOIN \n"
                        + "(\n"
                        + "	SELECT OKCONO,OKCUNO,TRIM(COALESCE(OKCUNM,'-')) AS OKCUNM \n"
                        + "	FROM " + M3DB + ".OCUSMA\n"
                        + "	WHERE OKCONO = '" + CONO.trim() + "'\n"
                        + ") D ON D.OKCONO = C.HR_CONO AND D.OKCUNO = C.HC_PYNO\n"
                        + ") E LEFT JOIN (\n"
                        + "SELECT RR_CONO, RR_DIVI, RR_RCNO, COALESCE(RR_DESC,'-') AS  RR_DESC\n"
                        + "FROM " + DBPRD + ".RECEIPT_RECEIVER\n"
                        + ") F ON F.RR_CONO = E.HR_CONO AND F.RR_DIVI = E.HR_DIVI AND F.RR_RCNO = E.HC_RCNO\n"
                        + ") G left join (\n"
                        + "select BM_CONO , BM_DIVI ,BM_RCNO ,BM_ACCODE ,BM_BANK,COALESCE(BM_CUNO, '-') AS  BM_CUNO ,BM_AMOUNT ,COALESCE(BM_BKCHARGE, 0) AS BM_BKCHARGE ,COALESCE(BM_OVPAY, 0) AS BM_OVPAY ,COALESCE(BM_CNDN, '-') AS BM_CNDN  \n"
                        + "from " + DBPRD + ".BANK_MAPPING\n"
                        + "where BM_CONO  = '" + CONO.trim() + "'\n"
                        + "and BM_DIVI  = '" + DIVI.trim() + "'\n"
                        + "and BM_RCNO  = '" + RCNO.trim() + "'\n"
                        + ") H on H.BM_CONO = G.HR_CONO and H.BM_DIVI = G.HR_DIVI and H.BM_RCNO = G.HC_RCNO and H.BM_BANK = G.HC_BANK  ";

                System.out.println("Get all bank");

                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("HR_CONO", mRes.getString(1).trim());
                    mMap.put("HR_DIVI", mRes.getString(2).trim());
                    mMap.put("HC_RCNO", mRes.getString(3).trim());
                    mMap.put("HC_TRDT", mRes.getString(4).trim());
                    mMap.put("HC_PYNO", mRes.getString(5).trim());
                    mMap.put("HC_REAMT", mRes.getString(6).trim());
                    mMap.put("HC_USER", mRes.getString(7).trim());
                    mMap.put("HC_STS", mRes.getString(8).trim());
                    mMap.put("OKCUNM", mRes.getString(9).trim());

                    mMap.put("HC_TYPE", mRes.getString(10).trim());
                    mMap.put("HC_BANK", mRes.getString(11).trim());
                    mMap.put("HC_ACCODE", mRes.getString(12).trim());
                    mMap.put("HC_PYDT", mRes.getString(13).trim());
                    mMap.put("HC_CHKNO", mRes.getString(14).trim());
                    mMap.put("HR_BKCHG", mRes.getString(15).trim());
                    mMap.put("SHC_PYDT", mRes.getString(16).trim());
                    mMap.put("RR_DESC", mRes.getString(17).trim());
                    mMap.put("BM_AMOUNT", mRes.getString(18).trim());
                    mMap.put("BM_BKCHARGE", mRes.getString(19).trim());
                    mMap.put("BM_OVPAY", mRes.getString(20).trim());
                    mMap.put("BM_CNDN", mRes.getString(21).trim());
                    mJSonArr.put(mMap);
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

        return mJSonArr;

    }

    public static JSONArray SearchReceipcash(String CONO, String DIVI, String RCNO, String Location) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();

                String query = "select BM_CONO , BM_DIVI ,COALESCE(BM_RCNO, '-') ,BM_ACCODE ,BM_BANK,COALESCE(BM_CUNO, '-') AS  BM_CUNO ,BM_AMOUNT ,COALESCE(BM_BKCHARGE, 0) AS BM_BKCHARGE ,COALESCE(BM_OVPAY, 0) AS BM_OVPAY ,COALESCE(BM_CNDN, '-') AS BM_CNDN , SUBSTR(BM_DATE,1,4) || '-' || SUBSTR(BM_DATE,5,2)|| '-' || SUBSTR(BM_DATE,7,2) as BM_DATE  \n"
                        + "from " + DBPRD + ".BANK_MAPPING\n"
                        + "where BM_CONO  = '" + CONO + "'\n"
                        + "and BM_DIVI  = '" + DIVI + "'\n"
                        + "and BM_FNNO  = '" + RCNO.trim() + "'";

                System.out.println("Get all bank");

                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("BM_CONO", mRes.getString(1).trim());
                    mMap.put("BM_DIVI", mRes.getString(2).trim());
                    mMap.put("BM_RCNO", mRes.getString(3).trim());
                    mMap.put("BM_ACCODE", mRes.getString(4).trim());
                    mMap.put("BM_BANK", mRes.getString(5).trim());
                    mMap.put("BM_CUNO", mRes.getString(6).trim());
                    mMap.put("BM_AMOUNT", mRes.getString(7).trim());
                    mMap.put("BM_BKCHARGE", mRes.getString(8).trim());
                    mMap.put("BM_OVPAY", mRes.getString(9).trim());
                    mMap.put("BM_CNDN", mRes.getString(10).trim());
                    mMap.put("BM_DATE", mRes.getString(11).trim());

                    mJSonArr.put(mMap);
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

        return mJSonArr;

    }

    public static JSONArray SearchReceipt_BankMappingnew(String CONO, String DIVI, String FNNO, String Location) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();

                String query = "select BM_CONO , BM_DIVI ,COALESCE(BM_RCNO, '-') ,BM_ACCODE ,BM_BANK,COALESCE(BM_CUNO, '-') AS  BM_CUNO ,BM_AMOUNT ,COALESCE(BM_BKCHARGE, 0) AS BM_BKCHARGE ,COALESCE(BM_OVPAY, 0) AS BM_OVPAY ,COALESCE(BM_CNDN, '-') AS BM_CNDN , SUBSTR(BM_DATE,1,4) || '-' || SUBSTR(BM_DATE,5,2)|| '-' || SUBSTR(BM_DATE,7,2) as BM_DATE , BM_LCODE \n"
                        + "from " + DBPRD + ".BANK_MAPPING\n"
                        + "where BM_CONO  = '" + CONO + "'\n"
                        + "and BM_DIVI  = '" + DIVI + "'\n"
                        + "and BM_FNNO  = '" + FNNO.trim() + "'";

                System.out.println("Get all bank");

                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("BM_CONO", mRes.getString(1).trim());
                    mMap.put("BM_DIVI", mRes.getString(2).trim());
                    mMap.put("BM_RCNO", mRes.getString(3).trim());
                    mMap.put("BM_ACCODE", mRes.getString(4).trim());
                    mMap.put("BM_BANK", mRes.getString(5).trim());
                    mMap.put("BM_CUNO", mRes.getString(6).trim());
                    mMap.put("BM_AMOUNT", mRes.getString(7).trim());
                    mMap.put("BM_BKCHARGE", mRes.getString(8).trim());
                    mMap.put("BM_OVPAY", mRes.getString(9).trim());
                    mMap.put("BM_CNDN", mRes.getString(10).trim());
                    mMap.put("BM_DATE", mRes.getString(11).trim());
                    mMap.put("BM_LCODE", mRes.getString(12).trim());

                    mJSonArr.put(mMap);
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

        return mJSonArr;

    }
}
