/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.api.ARS110;

import com.br.api.connect.ConnectDB2;
import com.br.api.data.GBVAR;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;

/**
 *
 * @author Jilasak
 */
public class ClassSetData {

    public static String DBPRD = GBVAR.DBPRD;
//            public static String DBPRD = "BRLDTA0100";
//        public static String DBPRD = "M3FDBTST";

    public void UpdataVouchernew(int CONO, String DIVI, String RCNO, String Voucher, String FNNO) throws Exception {
        Connection conn = ConnectDB2.ConnectionDB();
        try {
//MAIN INSERT  ........................................................................................................................\\\\\
            Statement stmt = conn.createStatement();
            String StrUpdate = "UPDATE " + DBPRD + ".HR_RECEIPT\n" //PRD
                    + "SET HC_VCNO='" + Voucher.trim() + "', HC_STS='3'\n"
                    + "Where HR_CONO = '" + CONO + "'"
                    + "and HR_DIVI = '" + DIVI.trim() + "'\n"
                    + "and HC_RCNO = '" + RCNO.trim() + "' AND  HC_FNNO = '" + FNNO + "'";
            stmt.execute(StrUpdate);
            System.out.println(StrUpdate);

            Statement stmt2 = conn.createStatement();
            String StrUpdate2 = "UPDATE " + DBPRD + ".HEAD_RECIPT \n"
                    + "SET H_VCNO  ='" + Voucher.trim() + "', H_STS ='3'\n"
                    + "Where H_CONO  = '" + CONO + "'\n"
                    + "and H_DIVI  = '" + DIVI.trim() + "'\n"
                    + "and H_RCNO  = '" + RCNO + "'";
            stmt2.execute(StrUpdate2);
            System.out.println(StrUpdate2);

            Statement stmt3 = conn.createStatement();
            String StrUpdate3 = "UPDATE " + DBPRD + ".HEAD_RECIPT \n"
                    + "SET H_VCNO  ='" + Voucher.trim() + "', H_STS ='3'\n"
                    + "Where H_CONO  = '" + CONO + "'\n"
                    + "and H_DIVI  = '" + DIVI.trim() + "'\n"
                    + "and H_CUNO  = 'HEAD'\n"
                    + "AND H_RNNO  = '" + FNNO + "' ";
            stmt3.execute(StrUpdate3);
            System.out.println(StrUpdate3);

            Statement stmt4 = conn.createStatement();
            String StrUpdate4 = "UPDATE " + DBPRD + ".BANK_MAPPING  \n"
                    + "SET BM_RCNO ='" + RCNO + "'\n"
                    + "Where BM_CONO  = '" + CONO + "'\n"
                    + "and BM_DIVI  = '" + DIVI + "'\n"
                    + "AND BM_FNNO  = '" + FNNO + "'";
            stmt4.execute(StrUpdate4);
            System.out.println(StrUpdate4);

//End Main Insert .......................................................................................................//
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    public void UpdataVoucher(int CONO, String DIVI, String RCNO, String Voucher) throws Exception {
        Connection conn = ConnectDB2.ConnectionDB();
        try {
//MAIN INSERT  ........................................................................................................................\\\\\
            Statement stmt = conn.createStatement();
            String StrUpdate = "UPDATE " + DBPRD + ".HR_RECEIPT\n" //PRD
                    + "SET HC_VCNO='" + Voucher.trim() + "', HC_STS='3'\n"
                    + "Where HR_CONO = '" + CONO + "'"
                    + "and HR_DIVI = '" + DIVI.trim() + "'\n"
                    + "and HC_RCNO = '" + RCNO.trim() + "'";
            stmt.execute(StrUpdate);

//End Main Insert .......................................................................................................//
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    public void InsertVoucherExpense(int CONO, String DIVI, String RCNO, String Voucher) throws Exception {
        Connection conn = ConnectDB2.ConnectionDB();
        try {
//MAIN INSERT  ........................................................................................................................\\\\\

            Statement stmt = conn.createStatement();
            String query = "INSERT INTO " + DBPRD + ".HR_RECEIPTEXPENSE\n"
                    + "(HR_CONO, HR_DIVI, HR_RCNO, HR_VCNO1, HR_VCNO2, HR_STS)\n"
                    + "VALUES('" + CONO + "', '" + DIVI.trim() + "', '" + RCNO.trim() + "', '" + Voucher.trim() + "', '0', '10')";
//            System.out.println(query);
            stmt.execute(query);
//End Main Insert .......................................................................................................//
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    public void InsertVoucherExpensenew(int CONO, String DIVI, String FNNO, String Voucher) throws Exception {
        Connection conn = ConnectDB2.ConnectionDB();
        try {
//MAIN INSERT  ........................................................................................................................\\\\\

            Statement stmt = conn.createStatement();
            String query = "INSERT INTO " + DBPRD + ".HR_RECEIPTEXPENSE\n"
                    + "(HR_CONO, HR_DIVI, HR_RCNO, HR_FNNO, HR_VCNO1, HR_VCNO2, HR_STS)\n"
                    + "VALUES('" + CONO + "', '" + DIVI.trim() + "','" + FNNO.trim() + "', '" + FNNO.trim() + "', '" + Voucher.trim() + "', '0', '10')";
            System.out.println(query);
            stmt.execute(query);
//End Main Insert .......................................................................................................//
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }
    
    
    public void UpdataVoucherClearnew(int CONO, String DIVI, String RCNO, String Voucher, String FNNO) throws Exception {
        Connection conn = ConnectDB2.ConnectionDB();
        try {
//MAIN INSERT  ........................................................................................................................\\\\\
            Statement stmt = conn.createStatement();
            String StrUpdate = "UPDATE " + DBPRD + ".HR_RECEIPTEXPENSE\n" //PRD
                    + "SET HR_VCNO2='" + Voucher.trim() + "', HR_STS='20'\n"
                    + "Where HR_CONO = '" + CONO + "'"
                    + "and HR_DIVI = '" + DIVI.trim() + "'\n"
                    + "and HR_FNNO = '" + FNNO.trim() + "'\n"
                    + "and HR_RCNO = '" + FNNO.trim() + "'";
            
            System.out.println("UPUPIUP " + StrUpdate );
            stmt.execute(StrUpdate);

//End Main Insert .......................................................................................................//
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }



public void UpdataVoucherClear(int CONO, String DIVI, String RCNO, String Voucher) throws Exception {
        Connection conn = ConnectDB2.ConnectionDB();
        try {
//MAIN INSERT  ........................................................................................................................\\\\\
            Statement stmt = conn.createStatement();
            String StrUpdate = "UPDATE "+DBPRD+".HR_RECEIPTEXPENSE\n" //PRD
                    + "SET HR_VCNO2='" + Voucher.trim() + "', HR_STS='20'\n"
                    + "Where HR_CONO = '" + CONO + "'"
                    + "and HR_DIVI = '" + DIVI.trim() + "'\n"
                    + "and HR_RCNO = '" + RCNO.trim() + "'";
            stmt.execute(StrUpdate);

//End Main Insert .......................................................................................................//
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }
}
