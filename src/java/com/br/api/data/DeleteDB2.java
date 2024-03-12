/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.api.data;

import com.br.api.connect.ConnectDB2;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Jilasak
 */
public class DeleteDB2 {
    
    
        public static String DBPRD = GBVAR.DBPRD;
//        public static String DBPRD = "M3FDBTST";
    
     public static void UNLOCKINVOICE(String cono, String divi, String invno) throws Exception {
        Connection conn = ConnectDB2.ConnectionDB();
        try {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "DELETE  FROM M3FDBprd.FCR040\n"
//                        + "WHERE ACCONO='"+cono+"' \n"
//                        + "AND ACDIVI='"+divi+"' \n"
                        + "WHERE ACCINO='"+invno.trim()+"' \n"
                        + " ";
                
                
                stmt.executeUpdate(query);
   
                System.out.println(query);
            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
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


    public static void Delete_Line(String LR_CONO, String LR_DIVI, String LR_RCNO, String LR_INVNO) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "DELETE FROM "+DBPRD+".LR_LINERECEIPT\n"
                        + "WHERE LR_CONO = '" + LR_CONO.trim() + "' AND LR_DIVI = '" + LR_DIVI.trim() + "' AND LR_RCNO = '" + LR_RCNO.trim() + "' AND LR_INVNO = '" + LR_INVNO.trim() + "'";
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

    public static void Delete_Expense(String CONO, String DIVI, String RCNO, String CODE, String ActCode) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "DELETE FROM "+DBPRD+".LR_LINEEXPEN\n"
                        + "WHERE LRE_CONO = '" + CONO.trim() + "' AND LRE_DIVI = '" + DIVI.trim() + "' AND LRE_RCNO = '" + RCNO.trim() + "' AND LRE_CODE = '" + CODE.trim() + "' AND "
                        + "LRE_ACTCODE = '" + ActCode.trim() + "'";
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

    public static void Delete_LineClearExpense(String LREP_CONO, String LREP_DIVI, String LREP_RCNO, String LREP_INVNO) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "DELETE FROM "+DBPRD+".LR_LINECLEAREXP\n"
                        + "WHERE LREP_CONO = '" + LREP_CONO.trim() + "' AND LREP_DIVI = '" + LREP_DIVI.trim() + "' AND LREP_RCNO = '" + LREP_RCNO.trim() + "' "
                        + " AND LREP_INVNO = '" + LREP_INVNO.trim() + "'";
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

}
