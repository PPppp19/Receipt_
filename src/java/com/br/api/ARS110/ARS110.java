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
import com.br.api.data.SelectDB2;
import com.br.api.data.UpdateDB2;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Jilasak
 */
public class ARS110 {

    public static void AUTO_ARS110new(int CONO, String DIVI, ArrayList<String> arrRC, String FNNO) throws Exception {

        ClassGetData cgd = new ClassGetData();

        String HC_TYPE = cgd.GET_ReceiptType(CONO, DIVI, arrRC.get(0));

        System.out.println(HC_TYPE);

        if (HC_TYPE.equalsIgnoreCase("CASH")) {
            ARS110_CASHnew(CONO, DIVI, arrRC, HC_TYPE, FNNO);
        } else if (HC_TYPE.equalsIgnoreCase("TRANSFER")) {
            ARS110_TRANSFERnew(CONO, DIVI, arrRC, HC_TYPE, FNNO);
        } else if (HC_TYPE.equalsIgnoreCase("CHECK")) {
            ARS110_CHECKnew(CONO, DIVI, arrRC, HC_TYPE, FNNO);

            for (int counter = 0; counter < arrRC.size(); counter++) {
                Update_OCUSMAnew(CONO, DIVI, arrRC.get(0), HC_TYPE, "2", FNNO);
            }
        } else {
            System.out.println("NOT AUTO");
        }

    }

    public static void AUTO_ARS110(int CONO, String DIVI, String RCNO) throws Exception {

        ClassGetData cgd = new ClassGetData();

        String HC_TYPE = cgd.GET_ReceiptType(CONO, DIVI, RCNO);
        System.out.println("rrrrrrrrrrr)");

        System.out.println(CONO);
        System.out.println(DIVI);
        System.out.println(RCNO);

        System.out.println(HC_TYPE);

        System.out.println("rrrrrrrrrrrrrrrrrrrrrr)");

        if (HC_TYPE.equalsIgnoreCase("CASH")) {
            ARS110_CASH(CONO, DIVI, RCNO, HC_TYPE);
        } else if (HC_TYPE.equalsIgnoreCase("TRANSFER")) {
            ARS110_TRANSFER(CONO, DIVI, RCNO, HC_TYPE);
        } else if (HC_TYPE.equalsIgnoreCase("CHECK")) {
            ARS110_CHECK(CONO, DIVI, RCNO, HC_TYPE);
            Update_OCUSMA(CONO, DIVI, RCNO, HC_TYPE, "2");
        } else {
            System.out.println("NOT AUTO");
        }
    }

    public static void AUTO_ARS110_DEPOSITnew(int CONO, String DIVI, ArrayList<String> arrRC, String FNNO) throws Exception {

        //todo 
        ClassGetData cgd = new ClassGetData();

        String HC_TYPE = cgd.GET_ReceiptTypedeposi(CONO, DIVI, arrRC.get(0));

        System.out.println(HC_TYPE);
        if (HC_TYPE.equalsIgnoreCase("CASH_DEPOSIT")) {

            System.out.println("ARS110_CASH_DEPOSITnew");

            ARS110_CASH_DEPOSITnew(CONO, DIVI, arrRC, HC_TYPE, FNNO);

        } else if (HC_TYPE.equalsIgnoreCase("TRANSFER_DEPOSIT")) {

            //todo
            ARS110_TRANSFER_DEPOSITnew(CONO, DIVI, arrRC, HC_TYPE, FNNO);
        } else if (HC_TYPE.equalsIgnoreCase("CHECK_DEPOSIT")) {

            ARS110_CHECK_DEPOSITnew(CONO, DIVI, arrRC, HC_TYPE, FNNO);

//            ARS110_CHECK_DEPOSIT(CONO, DIVI, RCNO, HC_TYPE);
//           Update_OCUSMA(CONO, DIVI, RCNO, HC_TYPE, "2");
            for (int i = 0; i < arrRC.size(); i++) {

                Update_OCUSMA(CONO, DIVI, arrRC.get(i), HC_TYPE, "2");

            }
        } else {
            System.out.println("NOT AUTO");
        }
    }

    public static void AUTO_ARS110_DEPOSIT(int CONO, String DIVI, String RCNO) throws Exception {

        ClassGetData cgd = new ClassGetData();

        String HC_TYPE = cgd.GET_ReceiptType(CONO, DIVI, RCNO);

        if (HC_TYPE.equalsIgnoreCase("CASH_DEPOSIT")) {
            ARS110_CASH_DEPOSIT(CONO, DIVI, RCNO, HC_TYPE);
        } else if (HC_TYPE.equalsIgnoreCase("TRANSFER_DEPOSIT")) {
            ARS110_TRANSFER_DEPOSIT(CONO, DIVI, RCNO, HC_TYPE);
        } else if (HC_TYPE.equalsIgnoreCase("CHECK_DEPOSIT")) {
            ARS110_CHECK_DEPOSIT(CONO, DIVI, RCNO, HC_TYPE);
            Update_OCUSMA(CONO, DIVI, RCNO, HC_TYPE, "2");
        } else {
            System.out.println("NOT AUTO");
        }
    }

    public static void AUTO_ARS110_KeyBacknew(int CONO, String DIVI, ArrayList<String> arrRC, String FNNO) throws Exception {

        ClassGetData cgd = new ClassGetData();

        String HC_TYPE = cgd.GET_ReceiptType(CONO, DIVI, arrRC.get(0));

        if (HC_TYPE.equalsIgnoreCase("TRANSFER")) {
            ARS110_TRANSFER_KEY_BACKnew(CONO, DIVI, arrRC, HC_TYPE, FNNO);
        } else if (HC_TYPE.equalsIgnoreCase("CHECK")) {
            ARS110_CHECK_KEY_BACKnew(CONO, DIVI, arrRC, HC_TYPE, FNNO);

            for (int i = 0; i < arrRC.size(); i++) {

                Update_OCUSMA(CONO, DIVI, arrRC.get(i), HC_TYPE, "2");

            }

        } else {
            System.out.println("NOT AUTO");
        }

        //////////////////////// ARS110_TRANSFERnew(CONO, DIVI, arrRC, HC_TYPE, FNNO);
        ///////////////////////
    }

    public static void AUTO_ARS110_EXPENSEnew(int CONO, String DIVI, ArrayList<String> arrRC, String TYPEPOST, String FNNO) throws Exception {

        ClassGetData cgd = new ClassGetData();

        String HC_TYPE = cgd.GET_ReceiptTypeEXP(CONO, DIVI, arrRC.get(0));

        System.out.println("EXP EXP EXP");
        System.out.println(HC_TYPE);
        System.out.println(TYPEPOST);
        if (HC_TYPE.equalsIgnoreCase("TRANSFER_EXP") && TYPEPOST.equalsIgnoreCase("Partial")) {
            ARS110_TRANSFER_EXPENSE_PARTAILnew(CONO, DIVI, arrRC, HC_TYPE, FNNO);

        } else if (HC_TYPE.equalsIgnoreCase("TRANSFER_EXP") && TYPEPOST.equalsIgnoreCase("Full")) {
            ARS110_TRANSFER_EXPENSE_FULLnew(CONO, DIVI, arrRC, HC_TYPE, FNNO);

        } else if (HC_TYPE.equalsIgnoreCase("CHECK_EXP") && TYPEPOST.equalsIgnoreCase("Partial")) {

            ARS110_CHECK_EXPENSE_PARTAILnew(CONO, DIVI, arrRC, HC_TYPE, FNNO);

            for (int counter = 0; counter < arrRC.size(); counter++) {
                Update_OCUSMAnew(CONO, DIVI, arrRC.get(0), HC_TYPE, "2", FNNO);
            }

        } else if (HC_TYPE.equalsIgnoreCase("CHECK_EXP") && TYPEPOST.equalsIgnoreCase("Full")) {

            System.out.println("fffffffffffffff");
            ARS110_CHECK_EXPENSE_FULLnew(CONO, DIVI, arrRC, HC_TYPE, FNNO);

            for (int counter = 0; counter < arrRC.size(); counter++) {
                Update_OCUSMAnew(CONO, DIVI, arrRC.get(0), HC_TYPE, "2", FNNO);
            }

        } else if (HC_TYPE.equalsIgnoreCase("CASH_EXP") && TYPEPOST.equalsIgnoreCase("Full")) {
            ARS110_CASH_EXPENSEnew(CONO, DIVI, arrRC, HC_TYPE, FNNO);
            System.out.println("ARS110_CASH_EXPENSE");
        } else {
            System.out.println("NOT AUTO");
        }
    }

    public static void AUTO_ARS110_KeyBack(int CONO, String DIVI, String RCNO) throws Exception {

        ClassGetData cgd = new ClassGetData();

        String HC_TYPE = cgd.GET_ReceiptType(CONO, DIVI, RCNO);

        if (HC_TYPE.equalsIgnoreCase("TRANSFER")) {
            ARS110_TRANSFER_KEY_BACK(CONO, DIVI, RCNO, HC_TYPE);
        } else if (HC_TYPE.equalsIgnoreCase("CHECK")) {
            ARS110_CHECK_KEY_BACK(CONO, DIVI, RCNO, HC_TYPE);
            Update_OCUSMA(CONO, DIVI, RCNO, HC_TYPE, "2");
        } else {
            System.out.println("NOT AUTO");
        }
    }

    public static void AUTO_ARS110_EXPENSE(int CONO, String DIVI, String RCNO, String TYPEPOST) throws Exception {

        ClassGetData cgd = new ClassGetData();

        String HC_TYPE = cgd.GET_ReceiptType(CONO, DIVI, RCNO);

        if (HC_TYPE.equalsIgnoreCase("TRANSFER_EXP") && TYPEPOST.equalsIgnoreCase("Partial")) {

            ARS110_TRANSFER_EXPENSE_PARTAIL(CONO, DIVI, RCNO, HC_TYPE);
        } else if (HC_TYPE.equalsIgnoreCase("TRANSFER_EXP") && TYPEPOST.equalsIgnoreCase("Full")) {

            ARS110_TRANSFER_EXPENSE_FULL(CONO, DIVI, RCNO, HC_TYPE);
        } else if (HC_TYPE.equalsIgnoreCase("CHECK_EXP") && TYPEPOST.equalsIgnoreCase("Partial")) {
            ARS110_CHECK_EXPENSE_PARTAIL(CONO, DIVI, RCNO, HC_TYPE);
            Update_OCUSMA(CONO, DIVI, RCNO, HC_TYPE, "2");
        } else if (HC_TYPE.equalsIgnoreCase("CHECK_EXP") && TYPEPOST.equalsIgnoreCase("Full")) {

            ARS110_CHECK_EXPENSE_FULL(CONO, DIVI, RCNO, HC_TYPE);
            Update_OCUSMA(CONO, DIVI, RCNO, HC_TYPE, "2");
        } else if (HC_TYPE.equalsIgnoreCase("CASH_EXP") && TYPEPOST.equalsIgnoreCase("Full")) {
            ARS110_CASH_EXPENSE(CONO, DIVI, RCNO, HC_TYPE);
            System.out.println("ARS110_CASH_EXPENSE");
        } else {
            System.out.println("NOT AUTO");
        }
    }

    public static void AUTO_ARS110_CLEAR_EXPENSEnew(int CONO, String DIVI, ArrayList<String> arrRC, String FNNO) {

        ClassGetData cgd = new ClassGetData();

        String HC_TYPE = cgd.GET_ReceiptTypeCLEAREXP(CONO, DIVI, arrRC.get(0));
        if (HC_TYPE.equalsIgnoreCase("TRANSFER_EXP")) {

            ARS110_TRANSFER_CLEAR_EXPENSEV2new(CONO, DIVI, arrRC, HC_TYPE, FNNO);

        } else if (HC_TYPE.equalsIgnoreCase("CHECK_EXP")) {

            ARS110_CHECK_CLEAR_EXPENSEnew(CONO, DIVI, arrRC, HC_TYPE, FNNO);
        } else {
            System.out.println("NOT AUTO");
        }
    }

    public static void AUTO_ARS110_CLEAR_EXPENSE(int CONO, String DIVI, String RCNO) {

        ClassGetData cgd = new ClassGetData();

        String HC_TYPE = cgd.GET_ReceiptType_Expense(CONO, DIVI, RCNO);

        if (HC_TYPE.equalsIgnoreCase("TRANSFER_EXP")) {

            ARS110_TRANSFER_CLEAR_EXPENSEV2(CONO, DIVI, RCNO, HC_TYPE);

        } else if (HC_TYPE.equalsIgnoreCase("CHECK_EXP")) {

            ARS110_CHECK_CLEAR_EXPENSE(CONO, DIVI, RCNO, HC_TYPE);
        } else {
            System.out.println("NOT AUTO");
        }
    }

    //////////////////////////// new for ID ///////////////////////////////////
    //todo  ArrayList<String> arrRC 
    public static void ARS110_CASHnew(int CONO, String DIVI, ArrayList<String> arrRC, String TYPE, String FNNO) {
        String LoginUrlConnectionm3 = GBVAR.LoginUrlConnectionm3;
        String LoginM3User = "MVXSECOFR";
        String LoginM3Password = "lawson@90";
        String LoginAppServer = "192.200.9.190";
        int LoginAppPort = GBVAR.DBPORT;

//        int LoginAppPort = 16305; // TST
        System.out.println("ARS110_CASHnew");

        try {
            ClassGetData cgd = new ClassGetData();
            ClassSetData csd = new ClassSetData();
            int cono = CONO;
            String divi = DIVI.trim();
            String lastinv = "";

            MNEHelper mne = new MNEHelper(LoginAppServer, LoginAppPort, LoginUrlConnectionm3);
            mne.logInToM3(cono, divi, LoginM3User, LoginM3Password);

            if (!mne.logInToM3(cono, divi, LoginM3User, LoginM3Password)) {
                System.out.println("Can not login to M3 system");
            }

            String ARS110 = mne.runM3Pgm("ARS110");

            if ((ARS110).equals("")) {
                System.out.println(" ไม่สามารถเปิดโปรแกรม ARS110 ได้");
                mne.closeProgram(ARS110);
            }

            ResultSet RSL_GR = cgd.GetReceiptnew(CONO, DIVI, arrRC.get(0), TYPE);
            ResultSet RSL_FNC = cgd.GET_FNC_REF1(CONO, DIVI, arrRC.get(0));

            String FNC = "", REF1 = "";
            while (RSL_FNC.next()) {
                FNC = RSL_FNC.getString("RL_FNC").trim();
                REF1 = RSL_FNC.getString("RL_REF1").trim();
            }

            System.out.println("Running");
            ClassFormatData cfm = new ClassFormatData();

            if (mne.panel.equals("ARS110/B")) {
                mne.setField("CMDTP", "KEY");
                mne.setField("CMDVAL", "ENTER");
                mne.setField("FCS", "WAFNCN");
                mne.setField("WAFNCN", FNC);
                mne.pressKey(MNEProtocol.KeyEnter);

                mne.setField("CMDTP", "1"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                mne.setField("SELROWS", "R1"); //CMDTP
                mne.setField("FCS", "WAFNCN"); //CMDVAL ENTER
                mne.setField("WAFNCN", FNC); //FCS W1OBKV
//              mne.pressKey(MNEProtocol.KeyEnter);
                mne.selectOption("1");
                System.out.println(mne.getMsg());
            }

            while (RSL_GR.next()) {

                System.out.println(RSL_GR.getString("HC_REAMT").trim());

                if (mne.panel.equals("ARS112/E")) {
                    // System.out.println("CRS610/E");
                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("FCS", "WEAIT6"); //
                    mne.setField("WECUAM", RSL_GR.getString("HC_REAMT").trim()); //amt
                    mne.setField("WEACDT", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //customer
                    mne.setField("WEDTP5", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //invoice no
                    mne.setField("WEAIT4", REF1); //ACC date
                    mne.setField("WEAIT5", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //ACC date
                    mne.setField("WEAIT6", FNNO); //ACC date

                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.getMsg());
                    // JOptionPane.showMessageDialog(null, mne.getMsg());

                }
                if (mne.panel.equals("ARS110/F1")) {
                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim()); //
                    mne.setField("FCS", "WFPYNO"); //
                    mne.pressKey(MNEProtocol.KeyEnter);

                    //getallinvoice
                    ResultSet rsl_line = cgd.LineReceiptnew(CONO, DIVI, arrRC);
                    System.out.println("getallinv");

                    while (rsl_line.next()) {

                        System.out.println("getallinv111111111111");
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
//                        mne.setField("WFPYNO", "TH28050014");
                        mne.setField("FCS", "WFCUCD");
                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());
                        mne.pressKey(MNEProtocol.KeyEnter);

                        mne.setField("CMDTP", "LSTOPT");
                        mne.setField("CMDVAL", "11");
                        mne.setField("FCS", "WFCINO");
                        mne.setField("SELROWS", "R1");
                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());
                        mne.selectOption("11");
                        System.out.println(mne.getMsg());

                        if (mne.getMsg() != null) {
                            System.out.println(mne.getMsg());
                            mne.pressKey(MNEProtocol.KeyEnter);
                            System.out.println(RSL_GR.getString("HC_RCNO").trim());
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "F18");
                            mne.setField("FCS", "WFCINO");
                            mne.pressKey("F18");
                            mne.pressKey(MNEProtocol.KeyEnter);
                            mne.pressKey(MNEProtocol.KeyEnter);
                            mne.closeProgram(ARS110);
                        }

                        if (mne.panel.equals("ARS110/H")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WHCUAM");
                            mne.setField("WHCUAM", rsl_line.getString("LR_REAMT").trim());
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                        lastinv = rsl_line.getString("LR_INVNO").trim();
                    }

                    System.out.println("getallinv2222222222222");

                    Double BL = cgd.Call_Balancenew(CONO, DIVI, arrRC);

                    System.out.println("getallinv33333333333");
                    System.out.println("getall :" + BL);
                    System.out.println("getallinv444444444444");
                    if (BL > 2) {
                        if (mne.panel.equals("ARS110/F1")) {

                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WFCINO", lastinv);

                            mne.setField("CMDTP", "LSTOPT");
                            mne.setField("CMDVAL", "12");
                            mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                            mne.setField("FCS", "WFCINO");
                            mne.setField("SELROWS", "R1");
                            mne.setField("WFCINO", lastinv);
                            mne.selectOption("12");

                        }

                        if (mne.panel.equals("ARS110/I")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WIAIT3");
                            mne.setField("WIAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.setField("WIAIT3", "NON");
                            mne.pressKey(MNEProtocol.KeyEnter);

                            System.out.println("getallinv555555555555");

                        }

                    } else if ((BL <= 2 && BL >= -2) && BL != 0) {
                        System.out.println("getallinv66666666666");
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFSLOP", "23");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.panel.equals("ARS110/G1")) {
                            if (mne.getMsg() != null) {
                                mne.pressKey(MNEProtocol.KeyEnter);
                            }
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WGAIT2");
                            mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.pressKey(MNEProtocol.KeyEnter);

                            System.out.println("getallinv8888888888888");

                        }
                    }

                    System.out.println("getallinv99999999999");

                    mne.setField("CMDTP", "KEY");
                    mne.setField("CMDVAL", "F3");
                    mne.pressKey(MNEProtocol.KeyF03);

                    System.out.println("xxxxxxx1233");

                    if (mne.getMsg().trim().equalsIgnoreCase("Left to distribute does not equal 0")) {

                        System.out.println("1111111111");
                        System.out.println(mne.getMsg());
                        mne.pressKey(MNEProtocol.KeyEnter);
                        System.out.println(RSL_GR.getString("HC_RCNO").trim());
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "F18");
                        mne.setField("FCS", "WFCINO");
                        mne.pressKey("F18");
                        mne.pressKey(MNEProtocol.KeyEnter);
                        mne.pressKey(MNEProtocol.KeyEnter);
                    }

                    if (mne.getMsg() != null) {
                        System.out.println(mne.getMsg());
                        System.out.println("getallinv12121212121");
                    }

                    System.out.println("getallinv9xzzzzzzzzzzz");
                    String[] Vouchermss = mne.getMsg().toString().split(" ");
                    String Voucher = Vouchermss[2].trim();

                    if (Voucher.length() == 8) {

                        for (int counter = 0; counter < arrRC.size(); counter++) {
                            csd.UpdataVouchernew(CONO, DIVI, arrRC.get(counter), Voucher, FNNO);
                        }
                    }
                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.panel);
                }
            }
            System.out.println("getallinv101010");

            mne.closeProgram(ARS110);

        } catch (Exception e) {
            System.out.println("getallinv8998989989");

            System.out.println(e.toString());

        }

    }

    public static void ARS110_TRANSFERnew(int CONO, String DIVI, ArrayList<String> arrRC, String TYPE, String FNNO) {

        String LoginUrlConnectionm3 = GBVAR.LoginUrlConnectionm3;
        String LoginM3User = "MVXSECOFR";
        String LoginM3Password = "lawson@90";
        String LoginAppServer = "192.200.9.190";
        System.out.println("mmmmmmmmmmmm)");

        System.out.println(TYPE);
        System.out.println(CONO);
        System.out.println(DIVI);
        System.out.println(arrRC);

        System.out.println("mmmmmmmmmmmmmmmm)");

        int LoginAppPort = GBVAR.DBPORT;
//        int LoginAppPort = 16305; // TST

        try {
            ClassGetData cgd = new ClassGetData();
            ClassSetData csd = new ClassSetData();
            int cono = CONO;
            String divi = DIVI.trim();
            String lastinv = "";

            MNEHelper mne = new MNEHelper(LoginAppServer, LoginAppPort, LoginUrlConnectionm3);
            mne.logInToM3(cono, divi, LoginM3User, LoginM3Password);

            if (!mne.logInToM3(cono, divi, LoginM3User, LoginM3Password)) {
                System.out.println("Can not login to M3 system");
            }

            String ARS110 = mne.runM3Pgm("ARS110");

            if ((ARS110).equals("")) {
                System.out.println(" ไม่สามารถเปิดโปรแกรม ARS110 ได้");
                mne.closeProgram(ARS110);
            }
            ResultSet RSL_FNC = cgd.GET_FNC_REF1(CONO, DIVI, arrRC.get(0));
            ResultSet RSL_GR = cgd.GetReceiptnew(CONO, DIVI, arrRC.get(0), TYPE);

            String FNC = "", REF1 = "";

            while (RSL_FNC.next()) {
                FNC = RSL_FNC.getString("RL_FNC").trim();
                REF1 = RSL_FNC.getString("RL_REF1").trim();
            }

            System.out.println("RunningNEW");
            ClassFormatData cfm = new ClassFormatData();

            if (mne.panel.equals("ARS110/B")) {
                mne.setField("CMDTP", "KEY");
                mne.setField("CMDVAL", "ENTER");
                mne.setField("FCS", "WAFNCN");
                mne.setField("WAFNCN", FNC);
                mne.pressKey(MNEProtocol.KeyEnter);

                mne.setField("CMDTP", "1"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                mne.setField("SELROWS", "R1"); //CMDTP
                mne.setField("FCS", "WAFNCN"); //CMDVAL ENTER
                mne.setField("WAFNCN", FNC); //FCS W1OBKV
//              mne.pressKey(MNEProtocol.KeyEnter);
                mne.selectOption("1");
                System.out.println(mne.getMsg());
            }
            while (RSL_GR.next()) {

                if (mne.panel.equals("ARS112/E")) {
                    // System.out.println("CRS610/E");
                    if (RSL_GR.getString("HC_BANK").trim().equalsIgnoreCase("NONE")) {
                        continue;
                    }

                    Double HC_REAMT = RSL_GR.getDouble("HC_REAMT");

                    HC_REAMT = cfm.Double2digitReturn(HC_REAMT);
                    System.out.println("xxxxx");
                    System.out.println(HC_REAMT);

/////
                    if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                        System.out.println("CASE BANK CHARGE");
                        HC_REAMT = HC_REAMT - RSL_GR.getDouble("HR_BKCHG");
                        HC_REAMT = cfm.Double2digitReturn(HC_REAMT);
                        System.out.println("mmmmmmmmmmmmmmmmmm");
                        System.out.println(RSL_GR.getDouble("HR_BKCHG"));
                        System.out.println(HC_REAMT);
                        System.out.println("mmmmmmmmmmmmmmmmmm");
                    }
/////

                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("FCS", "WEAIT6"); //
                    mne.setField("WEAIT1", RSL_GR.getString("HC_ACCODE").trim()); // ACCOUNT CODE

                    mne.setField("WECUAM", String.format("%.2f", HC_REAMT)); //amt
                    mne.setField("WEACDT", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //
                    mne.setField("WEDTP5", cfm.SubDateForM3(RSL_GR.getString("HC_PYDT"))); //
                    mne.setField("WEAIT4", REF1); //ACC date
                    mne.setField("WEAIT5", cfm.SubDateForM3(RSL_GR.getString("HC_PYDT"))); //
                    //mne.setField("WEAIT6", RSL_GR.getString("HC_RCNO").trim()); //
                    mne.setField("WEAIT6", FNNO); //

                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.getMsg());
                    // JOptionPane.showMessageDialog(null, mne.getMsg());

                }
                if (mne.panel.equals("ARS110/F1")) {
                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim()); //
                    mne.setField("FCS", "WFPYNO"); //
                    mne.pressKey(MNEProtocol.KeyEnter);

                    ////BANK CHARGE
                    if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {

                        mne.setField("CMDTP", "KEY"); //CMDTP KEY
                        mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                        mne.setField("WFSLOP", "21");
                        mne.setField("FCS", "WFSLOP");
                        mne.setField("WFQTTP", "1");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.getMsg() != null) {
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                        if (mne.panel.equals("ARS110/G1")) {
                            mne.setField("CMDTP", "KEY"); //CMDTP KEY
                            mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WGCUAM", RSL_GR.getString("HR_BKCHG").trim());
                            mne.setField("WGAIT7", "ARS110");
                            mne.setField("FCS", "WGCUAM");
                            mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                    }
////
//todo invoice set 
                    System.out.println("yyyy");
                    ResultSet rsl_line = cgd.LineReceiptnew(CONO, DIVI, arrRC);
                    while (rsl_line.next()) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
//                        mne.setField("WFPYNO", "TH28050014");
                        mne.setField("FCS", "WFCUCD");
                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());
                        mne.pressKey(MNEProtocol.KeyEnter);

                        mne.setField("CMDTP", "LSTOPT");
                        mne.setField("CMDVAL", "11");
                        mne.setField("FCS", "WFCINO");
                        mne.setField("SELROWS", "R1");
                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());
                        mne.selectOption("11");
                        System.out.println(mne.getMsg());

                        if (mne.getMsg() != null) {
                            System.out.println(mne.getMsg());
                            mne.pressKey(MNEProtocol.KeyEnter);
//                            System.out.println(RSL_GR.getString("HC_RCNO").trim());
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "F18");
                            mne.setField("FCS", "WFCINO");
                            mne.pressKey("F18");
                            mne.pressKey(MNEProtocol.KeyEnter);
                            mne.pressKey(MNEProtocol.KeyEnter);
//                            mne.closeProgram(ARS110);
                        }

                        if (mne.panel.equals("ARS110/H")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WHCUAM");
                            mne.setField("WHCUAM", rsl_line.getString("LR_REAMT").trim());
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                        lastinv = rsl_line.getString("LR_INVNO").trim();
                    }

                    Double BL = cgd.Call_Balancenew(CONO, DIVI, arrRC);

                    System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
                    System.out.println(BL);
                    System.out.println(RSL_GR.getDouble("HR_BKCHG"));
                    //BL = BL + RSL_GR.getDouble("HR_BKCHG");
                    System.out.println(cfm.Double2digitReturn(BL));

                    System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
                    if (BL > 2) {
                        System.out.println("-------1------------------");

                        if (mne.panel.equals("ARS110/F1")) {

                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WFCINO", lastinv);

                            mne.setField("CMDTP", "LSTOPT");
                            mne.setField("CMDVAL", "12");
                            mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                            mne.setField("FCS", "WFCINO");
                            mne.setField("SELROWS", "R1");
                            mne.setField("WFCINO", lastinv);
                            mne.selectOption("12");

                        }

                        if (mne.panel.equals("ARS110/I")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WIAIT3");
                            mne.setField("WIAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.setField("WIAIT3", "NON");
                            mne.pressKey(MNEProtocol.KeyEnter);

                        }

                    } else if ((BL <= 2 && BL >= -2) && BL != 0) {
                        System.out.println("------2-------------------");

                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFSLOP", "23");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.panel.equals("ARS110/G1")) {

                            //** PPppppppppppp
                            if (mne.getMsg() != null) {
                                mne.pressKey(MNEProtocol.KeyEnter);

                                mne.setField("CMDTP", "KEY");
                                mne.setField("CMDVAL", "ENTER");
                                mne.setField("FCS", "WGAIT2");

                                if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                                    mne.setField("WGCUAM", cfm.Double2digitReturn(BL));
                                }
                            } else {
                                mne.setField("CMDTP", "KEY");
                                mne.setField("CMDVAL", "ENTER");
                                mne.setField("FCS", "WGAIT2");

                                if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                                    mne.setField("WGCUAM", cfm.Double2digitReturn(BL) * -1);
                                }
                            }

                            mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                    }
                    System.out.println("---------00----------------");

                    mne.setField("CMDTP", "KEY");
                    mne.setField("CMDVAL", "F3");
                    mne.pressKey(MNEProtocol.KeyF03);

                      System.out.println(mne.getMsg());

                    if (mne.getMsg().trim().equalsIgnoreCase("Left to distribute does not equal 0")) {
                        System.out.println(mne.getMsg());

                         System.out.println("eeorr");
                        mne.pressKey(MNEProtocol.KeyEnter);
                        System.out.println(RSL_GR.getString("HC_RCNO").trim());
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "F18");
                        mne.setField("FCS", "WFCINO");
                        mne.pressKey("F18");
                        mne.pressKey(MNEProtocol.KeyEnter);
                        mne.pressKey(MNEProtocol.KeyEnter);
                    }

                    if (mne.getMsg() != null) {
                        System.out.println(mne.getMsg());
                        System.out.println("-------8------------------");

                    }

                    String[] Vouchermss = mne.getMsg().toString().split(" ");
                    String Voucher = Vouchermss[2].trim();

                    System.out.println("Voucher is : " + Voucher);
                    System.out.println("Voucher lenght is : " + Voucher.length());
                    if (Voucher.length() == 8) {

                        for (int counter = 0; counter < arrRC.size(); counter++) {

                            System.out.println("arrRC is : " + arrRC.get(counter));
                            csd.UpdataVouchernew(CONO, DIVI, arrRC.get(counter), Voucher, FNNO);
                        }
                    }
                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.panel);
                }

            }
            mne.closeProgram(ARS110);

        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("----------9---------------");

        }

    }

    //todo  ArrayList<String> arrRC 
    public static void ARS110_CHECKnew(int CONO, String DIVI, ArrayList<String> arrRC, String TYPE, String FNNO) {
        String LoginUrlConnectionm3 = GBVAR.LoginUrlConnectionm3;
        String LoginM3User = "MVXSECOFR";
        String LoginM3Password = "lawson@90";
        String LoginAppServer = "192.200.9.190";
        int LoginAppPort = GBVAR.DBPORT;
//        int LoginAppPort = 16305; // TST

        try {
            ClassGetData cgd = new ClassGetData();
            ClassSetData csd = new ClassSetData();
            int cono = CONO;
            String divi = DIVI.trim();
            String lastinv = "";

            MNEHelper mne = new MNEHelper(LoginAppServer, LoginAppPort, LoginUrlConnectionm3);
            mne.logInToM3(cono, divi, LoginM3User, LoginM3Password);

            if (!mne.logInToM3(cono, divi, LoginM3User, LoginM3Password)) {
                System.out.println("Can not login to M3 system");
            }

            String ARS110 = mne.runM3Pgm("ARS110");

            if ((ARS110).equals("")) {
                System.out.println(" ไม่สามารถเปิดโปรแกรม ARS110 ได้");
                mne.closeProgram(ARS110);
            }

            ResultSet RSL_GR = cgd.GetReceiptnew(CONO, DIVI, arrRC.get(0), TYPE);

            ResultSet RSL_FNC = cgd.GET_FNC_REF1(CONO, DIVI, arrRC.get(0));
            String FNC = "", REF1 = "";
            while (RSL_FNC.next()) {
                FNC = RSL_FNC.getString("RL_FNC").trim();
                REF1 = RSL_FNC.getString("RL_REF1").trim();
            }

            for (int counter = 0; counter < arrRC.size(); counter++) {
                Update_OCUSMAnew(CONO, DIVI, arrRC.get(counter), TYPE, "1", FNNO);
            }
            System.out.println("Running");
            ClassFormatData cfm = new ClassFormatData();

            if (mne.panel.equals("ARS110/B")) {
                mne.setField("CMDTP", "KEY");
                mne.setField("CMDVAL", "ENTER");
                mne.setField("FCS", "WAFNCN");
                mne.setField("WAFNCN", FNC);
                mne.pressKey(MNEProtocol.KeyEnter);

                mne.setField("CMDTP", "1"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                mne.setField("SELROWS", "R1"); //CMDTP
                mne.setField("FCS", "WAFNCN"); //CMDVAL ENTER
                mne.setField("WAFNCN", FNC); //FCS W1OBKV
//              mne.pressKey(MNEProtocol.KeyEnter);
                mne.selectOption("1");
                System.out.println(mne.getMsg());
            }

            while (RSL_GR.next()) {

                if (mne.panel.equals("ARS112/E")) {
                    // System.out.println("CRS610/E");
                    Double HC_REAMT = RSL_GR.getDouble("HC_REAMT");

                    if (RSL_GR.getString("HC_CHKNO").trim().equalsIgnoreCase("")) {
                        continue;
                    }

                    if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                        HC_REAMT = HC_REAMT - RSL_GR.getDouble("HR_BKCHG");
                    }

                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("FCS", "WEDRRN"); //
                    mne.setField("WEAIT1", RSL_GR.getString("HC_ACCODE").trim()); // ACCOUNT CODE
                    mne.setField("WEPYNO", RSL_GR.getString("HC_PYNO").trim()); //
                    mne.setField("WECUAM", String.format("%.2f", HC_REAMT)); //amt
                    mne.setField("WEDTP5", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //
                    mne.setField("WEACDT", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //
                    mne.setField("WEDRRN", RSL_GR.getString("HC_CHKNO").trim()); //ACC date
                    mne.setField("WEDUDT", cfm.SubDateForM3(RSL_GR.getString("HC_PYDT"))); //
                    mne.setField("WEBKID", RSL_GR.getString("HC_BANK").trim()); //
                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.getMsg());
                    // JOptionPane.showMessageDialog(null, mne.getMsg());
                    if (mne.getMsg() != null) {
                        mne.pressKey(MNEProtocol.KeyEnter);
                        continue;
                    }

                }
                if (mne.panel.equals("ARS110/F1")) {
                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim()); //
                    mne.setField("FCS", "WFPYNO"); //
                    mne.pressKey(MNEProtocol.KeyEnter);

                    ////BANK CHARGE
                    if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {

                        mne.setField("CMDTP", "KEY"); //CMDTP KEY
                        mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                        mne.setField("WFSLOP", "21");
                        mne.setField("FCS", "WFSLOP");
                        mne.setField("WFQTTP", "1");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.getMsg() != null) {
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                        if (mne.panel.equals("ARS110/G1")) {
                            mne.setField("CMDTP", "KEY"); //CMDTP KEY
                            mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WGCUAM", RSL_GR.getString("HR_BKCHG").trim());
                            mne.setField("WGAIT7", "ARS110");
                            mne.setField("FCS", "WGCUAM");
                            mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                    }
////

                    ResultSet rsl_line = cgd.LineReceiptnew(CONO, DIVI, arrRC);
                    while (rsl_line.next()) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
//                        mne.setField("WFPYNO", "TH28050014");
                        mne.setField("FCS", "WFCINO");
                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.getMsg() != null) {
                            System.out.println(mne.getMsg());
                            mne.pressKey(MNEProtocol.KeyEnter);
                            System.out.println(RSL_GR.getString("HC_RCNO").trim());
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "F18");
                            mne.setField("FCS", "WFCINO");
                            mne.pressKey("F18");
                            mne.pressKey(MNEProtocol.KeyEnter);
                            mne.pressKey(MNEProtocol.KeyEnter);
                            mne.closeProgram(ARS110);
                        }

                        mne.setField("CMDTP", "LSTOPT");
                        mne.setField("CMDVAL", "11");
                        mne.setField("FCS", "WFCINO");
                        mne.setField("SELROWS", "R1");
                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());
                        mne.selectOption("11");
                        System.out.println(mne.getMsg());

                        if (mne.panel.equals("ARS110/H")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WHCUAM");
                            mne.setField("WHCUAM", rsl_line.getString("LR_REAMT").trim());
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                        lastinv = rsl_line.getString("LR_INVNO").trim();
                    }

                    Double BL = cgd.Call_Balancenew(CONO, DIVI, arrRC);
//                    BL = BL + RSL_GR.getDouble("HR_BKCHG");
                    if (BL > 2) {
                        if (mne.panel.equals("ARS110/F1")) {

                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WFCINO", lastinv);

                            mne.setField("CMDTP", "LSTOPT");
                            mne.setField("CMDVAL", "12");
                            mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                            mne.setField("FCS", "WFCINO");
                            mne.setField("SELROWS", "R1");
                            mne.setField("WFCINO", lastinv);
                            mne.selectOption("12");

                        }

//                        mne.setField("CMDTP", "KEY");
//                        mne.setField("CMDVAL", "ENTER");
////                        mne.setField("WFPYNO", "TH28050014");
//                        mne.setField("FCS", "WFCINO");
//                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());
//                        mne.pressKey(MNEProtocol.KeyEnter);
                        if (mne.panel.equals("ARS110/I")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WIAIT3");
                            mne.setField("WIAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.setField("WIAIT3", "NON");
                            mne.pressKey(MNEProtocol.KeyEnter);

                        }

                    } else if ((BL <= 2 && BL >= -2) && BL != 0) {
//                    } else if (BL <= 2 && BL>0) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFSLOP", "23");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.panel.equals("ARS110/G1")) {

                            if (mne.getMsg() != null) {
                                mne.pressKey(MNEProtocol.KeyEnter);
                            }

                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WGAIT2");

                            if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                                mne.setField("WGCUAM", cfm.Double2digitReturn(BL));
                            }

                            mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                    }

                    mne.setField("CMDTP", "KEY");
                    mne.setField("CMDVAL", "F3");
                    mne.pressKey(MNEProtocol.KeyF03);

                    if (mne.getMsg().trim().equalsIgnoreCase("Left to distribute does not equal 0")) {
                        System.out.println(mne.getMsg());
                        mne.pressKey(MNEProtocol.KeyEnter);
                        System.out.println(RSL_GR.getString("HC_RCNO").trim());
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "F18");
                        mne.setField("FCS", "WFCINO");
                        mne.pressKey("F18");
                        mne.pressKey(MNEProtocol.KeyEnter);
                        mne.pressKey(MNEProtocol.KeyEnter);
                    }

                    if (mne.getMsg() != null) {
                        System.out.println(mne.getMsg());
                    }
                    String[] Vouchermss = mne.getMsg().toString().split(" ");
                    String Voucher = Vouchermss[2].trim();

                    if (Voucher.length() == 8) {
                        for (int counter = 0; counter < arrRC.size(); counter++) {
                            csd.UpdataVouchernew(CONO, DIVI, arrRC.get(counter), Voucher, FNNO);
                        }
                    }
                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.panel);
                }

            }
            mne.closeProgram(ARS110);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    //todo  ArrayList<String> arrRC 
    public static void Update_OCUSMAnew(int CONO, String DIVI, String RCNO, String Type, String check, String FNNO) throws Exception {
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            Statement stmt = conn.createStatement();
            ClassGetData cgd = new ClassGetData();
            ResultSet RSL_GR = cgd.GetReceiptUpdateOCUSMA(CONO, DIVI, RCNO, Type);
            String cuno = "";
            String query = "";

            System.out.println(" VALUE OF FBBO pppppppppppppppppppppppppppppppppppppp:  " + FNNO);

            while (RSL_GR.next()) {
                cuno = RSL_GR.getString("HC_PYNO").trim();
            }

            if (check.equals("1")) {
                query = "UPDATE " + GBVAR.M3DB + ".OCUSMA\n"
                        + "SET OKCCUS = '" + FNNO.trim() + "' \n"
                        + "WHERE OKCONO = '10' AND OKCUNO = '" + cuno.trim() + "' ";
            } else {
                query = "UPDATE " + GBVAR.M3DB + ".OCUSMA\n"
                        + "SET OKCCUS = ' ' \n"
                        + "WHERE OKCONO = '10' AND OKCUNO = '" + cuno.trim() + "' ";
            }
            System.out.println(query);
            stmt.execute(query);
        } catch (Exception ex) {
            Logger.getLogger(ARS110.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    //////////////////////////////// END OF NEW ID ///////////////////////////////////////////
    public static void ARS110_CASH(int CONO, String DIVI, String RCNO, String TYPE) {
        String LoginUrlConnectionm3 = GBVAR.LoginUrlConnectionm3;
        String LoginM3User = "MVXSECOFR";
        String LoginM3Password = "lawson@90";
        String LoginAppServer = "192.200.9.190";
        int LoginAppPort = GBVAR.DBPORT;
//        int LoginAppPort = 16305; // TST

        try {
            ClassGetData cgd = new ClassGetData();
            ClassSetData csd = new ClassSetData();
            int cono = CONO;
            String divi = DIVI.trim();
            String lastinv = "";

            MNEHelper mne = new MNEHelper(LoginAppServer, LoginAppPort, LoginUrlConnectionm3);
            mne.logInToM3(cono, divi, LoginM3User, LoginM3Password);

            if (!mne.logInToM3(cono, divi, LoginM3User, LoginM3Password)) {
                System.out.println("Can not login to M3 system");
            }

            String ARS110 = mne.runM3Pgm("ARS110");

            if ((ARS110).equals("")) {
                System.out.println(" ไม่สามารถเปิดโปรแกรม ARS110 ได้");
                mne.closeProgram(ARS110);
            }

            ResultSet RSL_GR = cgd.GetReceipt(CONO, DIVI, RCNO, TYPE);

            ResultSet RSL_FNC = cgd.GET_FNC_REF1(CONO, DIVI, RCNO);
            String FNC = "", REF1 = "";
            while (RSL_FNC.next()) {
                FNC = RSL_FNC.getString("RL_FNC").trim();
                REF1 = RSL_FNC.getString("RL_REF1").trim();
            }

            System.out.println("Running");
            ClassFormatData cfm = new ClassFormatData();

            if (mne.panel.equals("ARS110/B")) {
                mne.setField("CMDTP", "KEY");
                mne.setField("CMDVAL", "ENTER");
                mne.setField("FCS", "WAFNCN");
                mne.setField("WAFNCN", FNC);
                mne.pressKey(MNEProtocol.KeyEnter);

                mne.setField("CMDTP", "1"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                mne.setField("SELROWS", "R1"); //CMDTP
                mne.setField("FCS", "WAFNCN"); //CMDVAL ENTER
                mne.setField("WAFNCN", FNC); //FCS W1OBKV
//              mne.pressKey(MNEProtocol.KeyEnter);
                mne.selectOption("1");
                System.out.println(mne.getMsg());
            }

            while (RSL_GR.next()) {

                if (mne.panel.equals("ARS112/E")) {
                    // System.out.println("CRS610/E");
                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("FCS", "WEAIT6"); //
                    mne.setField("WECUAM", RSL_GR.getString("HC_REAMT").trim()); //amt
                    mne.setField("WEACDT", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //customer
                    mne.setField("WEDTP5", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //invoice no
                    mne.setField("WEAIT4", REF1); //ACC date
                    mne.setField("WEAIT5", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //ACC date
                    mne.setField("WEAIT6", RSL_GR.getString("HC_RCNO").trim()); //ACC date

                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.getMsg());
                    // JOptionPane.showMessageDialog(null, mne.getMsg());
                    System.out.println("222222222222222");
                }
                if (mne.panel.equals("ARS110/F1")) {
                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim()); //
                    mne.setField("FCS", "WFPYNO"); //
                    mne.pressKey(MNEProtocol.KeyEnter);

                    ResultSet rsl_line = cgd.LineReceipt(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim());
                    while (rsl_line.next()) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
//                        mne.setField("WFPYNO", "TH28050014");
                        mne.setField("FCS", "WFCUCD");
                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());
                        mne.pressKey(MNEProtocol.KeyEnter);

                        mne.setField("CMDTP", "LSTOPT");
                        mne.setField("CMDVAL", "11");
                        mne.setField("FCS", "WFCINO");
                        mne.setField("SELROWS", "R1");
                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());
                        System.out.println("11111111111111111");

                        mne.selectOption("11");
                        System.out.println(mne.getMsg());

                        if (mne.getMsg() != null) {
                            System.out.println(mne.getMsg());
                            mne.pressKey(MNEProtocol.KeyEnter);
                            System.out.println(RSL_GR.getString("HC_RCNO").trim());
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "F18");
                            mne.setField("FCS", "WFCINO");
                            mne.pressKey("F18");
                            mne.pressKey(MNEProtocol.KeyEnter);
                            mne.pressKey(MNEProtocol.KeyEnter);
                            mne.closeProgram(ARS110);
                        }

                        if (mne.panel.equals("ARS110/H")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WHCUAM");
                            mne.setField("WHCUAM", rsl_line.getString("LR_REAMT").trim());
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                        lastinv = rsl_line.getString("LR_INVNO").trim();
                    }

                    Double BL = cgd.Call_Balance(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim());
                    if (BL > 2) {
                        if (mne.panel.equals("ARS110/F1")) {

                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WFCINO", lastinv);

                            mne.setField("CMDTP", "LSTOPT");
                            mne.setField("CMDVAL", "12");
                            mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                            mne.setField("FCS", "WFCINO");
                            mne.setField("SELROWS", "R1");
                            mne.setField("WFCINO", lastinv);
                            mne.selectOption("12");

                        }

                        if (mne.panel.equals("ARS110/I")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WIAIT3");
                            mne.setField("WIAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.setField("WIAIT3", "NON");
                            mne.pressKey(MNEProtocol.KeyEnter);

                        }

                    } else if ((BL <= 2 && BL >= -2) && BL != 0) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFSLOP", "23");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.panel.equals("ARS110/G1")) {
                            if (mne.getMsg() != null) {
                                mne.pressKey(MNEProtocol.KeyEnter);
                            }
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WGAIT2");
                            mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                    }

                    mne.setField("CMDTP", "KEY");
                    mne.setField("CMDVAL", "F3");
                    mne.pressKey(MNEProtocol.KeyF03);

                    if (mne.getMsg().trim().equalsIgnoreCase("Left to distribute does not equal 0")) {
                        System.out.println(mne.getMsg());
                        mne.pressKey(MNEProtocol.KeyEnter);
                        System.out.println(RSL_GR.getString("HC_RCNO").trim());
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "F18");
                        mne.setField("FCS", "WFCINO");
                        mne.pressKey("F18");
                        mne.pressKey(MNEProtocol.KeyEnter);
                        mne.pressKey(MNEProtocol.KeyEnter);
                    }

                    if (mne.getMsg() != null) {
                        System.out.println(mne.getMsg());
                    }
                    String[] Vouchermss = mne.getMsg().toString().split(" ");
                    String Voucher = Vouchermss[2].trim();

                    if (Voucher.length() == 8) {
                        csd.UpdataVoucher(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim(), Voucher);
                    }
                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.panel);
                }
            }

            mne.closeProgram(ARS110);

        } catch (Exception e) {
            System.out.println(e.toString());

        }

    }

    public static void ARS110_TRANSFER(int CONO, String DIVI, String RCNO, String TYPE) {
        String LoginUrlConnectionm3 = GBVAR.LoginUrlConnectionm3;
        String LoginM3User = "MVXSECOFR";
        String LoginM3Password = "lawson@90";
        String LoginAppServer = "192.200.9.190";
        System.out.println("mmmmmmmmmmmm)");

        System.out.println(TYPE);
        System.out.println(CONO);
        System.out.println(DIVI);
        System.out.println(RCNO);

        System.out.println("mmmmmmmmmmmmmmmm)");

        int LoginAppPort = GBVAR.DBPORT;
//        int LoginAppPort = 16305; // TST

        try {
            ClassGetData cgd = new ClassGetData();
            ClassSetData csd = new ClassSetData();
            int cono = CONO;
            String divi = DIVI.trim();
            String lastinv = "";

            MNEHelper mne = new MNEHelper(LoginAppServer, LoginAppPort, LoginUrlConnectionm3);
            mne.logInToM3(cono, divi, LoginM3User, LoginM3Password);

            if (!mne.logInToM3(cono, divi, LoginM3User, LoginM3Password)) {
                System.out.println("Can not login to M3 system");
            }

            String ARS110 = mne.runM3Pgm("ARS110");

            if ((ARS110).equals("")) {
                System.out.println(" ไม่สามารถเปิดโปรแกรม ARS110 ได้");
                mne.closeProgram(ARS110);
            }

            ResultSet RSL_GR = cgd.GetReceipt(CONO, DIVI, RCNO, TYPE);

            ResultSet RSL_FNC = cgd.GET_FNC_REF1(CONO, DIVI, RCNO);
            String FNC = "", REF1 = "";
            while (RSL_FNC.next()) {
                FNC = RSL_FNC.getString("RL_FNC").trim();
                REF1 = RSL_FNC.getString("RL_REF1").trim();
            }

            System.out.println("Running");
            ClassFormatData cfm = new ClassFormatData();

            if (mne.panel.equals("ARS110/B")) {
                mne.setField("CMDTP", "KEY");
                mne.setField("CMDVAL", "ENTER");
                mne.setField("FCS", "WAFNCN");
                mne.setField("WAFNCN", FNC);
                mne.pressKey(MNEProtocol.KeyEnter);

                mne.setField("CMDTP", "1"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                mne.setField("SELROWS", "R1"); //CMDTP
                mne.setField("FCS", "WAFNCN"); //CMDVAL ENTER
                mne.setField("WAFNCN", FNC); //FCS W1OBKV
//              mne.pressKey(MNEProtocol.KeyEnter);
                mne.selectOption("1");
                System.out.println(mne.getMsg());
            }

            while (RSL_GR.next()) {

                if (mne.panel.equals("ARS112/E")) {
                    // System.out.println("CRS610/E");
                    if (RSL_GR.getString("HC_BANK").trim().equalsIgnoreCase("NONE")) {
                        continue;
                    }

                    Double HC_REAMT = RSL_GR.getDouble("HC_REAMT");

                    HC_REAMT = cfm.Double2digitReturn(HC_REAMT);

/////
                    if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {

                        System.out.println("CASE BANK CHARGE");
                        HC_REAMT = HC_REAMT - RSL_GR.getDouble("HR_BKCHG");
                        HC_REAMT = cfm.Double2digitReturn(HC_REAMT);
                    }
/////

                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("FCS", "WEAIT6"); //
                    mne.setField("WEAIT1", RSL_GR.getString("HC_ACCODE").trim()); // ACCOUNT CODE

                    mne.setField("WECUAM", String.format("%.2f", HC_REAMT)); //amt
                    mne.setField("WEACDT", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //
                    mne.setField("WEDTP5", cfm.SubDateForM3(RSL_GR.getString("HC_PYDT"))); //
                    mne.setField("WEAIT4", REF1); //ACC date
                    mne.setField("WEAIT5", cfm.SubDateForM3(RSL_GR.getString("HC_PYDT"))); //
                    mne.setField("WEAIT6", RSL_GR.getString("HC_RCNO").trim()); //

                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.getMsg());
                    // JOptionPane.showMessageDialog(null, mne.getMsg());

                }
                if (mne.panel.equals("ARS110/F1")) {
                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim()); //
                    mne.setField("FCS", "WFPYNO"); //
                    mne.pressKey(MNEProtocol.KeyEnter);

                    ////BANK CHARGE
                    if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {

                        mne.setField("CMDTP", "KEY"); //CMDTP KEY
                        mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                        mne.setField("WFSLOP", "21");
                        mne.setField("FCS", "WFSLOP");
                        mne.setField("WFQTTP", "1");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.getMsg() != null) {
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                        if (mne.panel.equals("ARS110/G1")) {
                            mne.setField("CMDTP", "KEY"); //CMDTP KEY
                            mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WGCUAM", RSL_GR.getString("HR_BKCHG").trim());
                            mne.setField("WGAIT7", "ARS110");
                            mne.setField("FCS", "WGCUAM");
                            mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                    }
////

                    ResultSet rsl_line = cgd.LineReceipt(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim());
                    while (rsl_line.next()) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
//                        mne.setField("WFPYNO", "TH28050014");
                        mne.setField("FCS", "WFCUCD");
                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());
                        mne.pressKey(MNEProtocol.KeyEnter);

                        mne.setField("CMDTP", "LSTOPT");
                        mne.setField("CMDVAL", "11");
                        mne.setField("FCS", "WFCINO");
                        mne.setField("SELROWS", "R1");
                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());
                        mne.selectOption("11");
                        System.out.println(mne.getMsg());

                        if (mne.getMsg() != null) {
                            System.out.println(mne.getMsg());
                            mne.pressKey(MNEProtocol.KeyEnter);
                            System.out.println(RSL_GR.getString("HC_RCNO").trim());
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "F18");
                            mne.setField("FCS", "WFCINO");
                            mne.pressKey("F18");
                            mne.pressKey(MNEProtocol.KeyEnter);
                            mne.pressKey(MNEProtocol.KeyEnter);
//                            mne.closeProgram(ARS110);
                        }

                        if (mne.panel.equals("ARS110/H")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WHCUAM");
                            mne.setField("WHCUAM", rsl_line.getString("LR_REAMT").trim());
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                        lastinv = rsl_line.getString("LR_INVNO").trim();
                    }

                    Double BL = cgd.Call_Balance(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim());
//                    BL = BL + RSL_GR.getDouble("HR_BKCHG");
                    if (BL > 2) {
                        if (mne.panel.equals("ARS110/F1")) {

                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WFCINO", lastinv);

                            mne.setField("CMDTP", "LSTOPT");
                            mne.setField("CMDVAL", "12");
                            mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                            mne.setField("FCS", "WFCINO");
                            mne.setField("SELROWS", "R1");
                            mne.setField("WFCINO", lastinv);
                            mne.selectOption("12");

                        }

                        if (mne.panel.equals("ARS110/I")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WIAIT3");
                            mne.setField("WIAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.setField("WIAIT3", "NON");
                            mne.pressKey(MNEProtocol.KeyEnter);

                        }

                    } else if ((BL <= 2 && BL >= -2) && BL != 0) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFSLOP", "23");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.panel.equals("ARS110/G1")) {

                            //** PPppppppppppp
                            if (mne.getMsg() != null) {
                                mne.pressKey(MNEProtocol.KeyEnter);

                                mne.setField("CMDTP", "KEY");
                                mne.setField("CMDVAL", "ENTER");
                                mne.setField("FCS", "WGAIT2");

                                if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                                    mne.setField("WGCUAM", cfm.Double2digitReturn(BL));
                                }
                            } else {
                                mne.setField("CMDTP", "KEY");
                                mne.setField("CMDVAL", "ENTER");
                                mne.setField("FCS", "WGAIT2");

                                if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                                    mne.setField("WGCUAM", cfm.Double2digitReturn(BL) * -1);
                                }
                            }

                            mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                    }

                    mne.setField("CMDTP", "KEY");
                    mne.setField("CMDVAL", "F3");
                    mne.pressKey(MNEProtocol.KeyF03);

                    if (mne.getMsg().trim().equalsIgnoreCase("Left to distribute does not equal 0")) {
                        System.out.println(mne.getMsg());
                        mne.pressKey(MNEProtocol.KeyEnter);
                        System.out.println(RSL_GR.getString("HC_RCNO").trim());
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "F18");
                        mne.setField("FCS", "WFCINO");
                        mne.pressKey("F18");
                        mne.pressKey(MNEProtocol.KeyEnter);
                        mne.pressKey(MNEProtocol.KeyEnter);
                    }

                    if (mne.getMsg() != null) {
                        System.out.println(mne.getMsg());
                    }
                    String[] Vouchermss = mne.getMsg().toString().split(" ");
                    String Voucher = Vouchermss[2].trim();

                    if (Voucher.length() == 8) {
                        csd.UpdataVoucher(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim(), Voucher);
                    }
                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.panel);
                }

            }

            mne.closeProgram(ARS110);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public static void ARS110_TRANSFER_KEY_BACKnew(int CONO, String DIVI, ArrayList<String> arrRC, String TYPE, String FNNO) {
        String LoginUrlConnectionm3 = GBVAR.LoginUrlConnectionm3;

        String LoginM3User = "MVXSECOFR";
        String LoginM3Password = "lawson@90";
        String LoginAppServer = "192.200.9.190";
        int LoginAppPort = GBVAR.DBPORT;
//        int LoginAppPort = 16305; // TST

        try {
            ClassGetData cgd = new ClassGetData();
            ClassSetData csd = new ClassSetData();
            int cono = CONO;
            String divi = DIVI.trim();
            String lastinv = "";

            MNEHelper mne = new MNEHelper(LoginAppServer, LoginAppPort, LoginUrlConnectionm3);
            mne.logInToM3(cono, divi, LoginM3User, LoginM3Password);

            if (!mne.logInToM3(cono, divi, LoginM3User, LoginM3Password)) {
                System.out.println("Can not login to M3 system");
            }

            String ARS110 = mne.runM3Pgm("ARS110");

            if ((ARS110).equals("")) {
                System.out.println(" ไม่สามารถเปิดโปรแกรม ARS110 ได้");
                mne.closeProgram(ARS110);
            }

            ResultSet RSL_GR = cgd.GetReceiptnew(CONO, DIVI, arrRC.get(0), TYPE);
            ResultSet RSL_FNC = cgd.GET_FNC_REF1(CONO, DIVI, arrRC.get(0));

            String FNC = "", REF1 = "";
            while (RSL_FNC.next()) {
                FNC = RSL_FNC.getString("RL_FNC").trim();
                REF1 = RSL_FNC.getString("RL_REF1").trim();
            }
            System.out.println("Running");
            ClassFormatData cfm = new ClassFormatData();

            if (mne.panel.equals("ARS110/B")) {
                mne.setField("CMDTP", "KEY");
                mne.setField("CMDVAL", "ENTER");
                mne.setField("FCS", "WAFNCN");
                mne.setField("WAFNCN", FNC);
                mne.pressKey(MNEProtocol.KeyEnter);

                mne.setField("CMDTP", "1"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                mne.setField("SELROWS", "R1"); //CMDTP
                mne.setField("FCS", "WAFNCN"); //CMDVAL ENTER
                mne.setField("WAFNCN", FNC); //FCS W1OBKV
//              mne.pressKey(MNEProtocol.KeyEnter);
                mne.selectOption("1");
                System.out.println(mne.getMsg());
            }

            while (RSL_GR.next()) {

                if (mne.panel.equals("ARS112/E")) {
                    // System.out.println("CRS610/E");
                    if (RSL_GR.getString("HC_BANK").trim().equalsIgnoreCase("NONE")) {
                        continue;
                    }

                    Double HC_REAMT = RSL_GR.getDouble("HC_REAMT");

                    HC_REAMT = cfm.Double2digitReturn(HC_REAMT);

/////
                    if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                        System.out.println("CASE BANK CHARGE");
                        HC_REAMT = HC_REAMT - RSL_GR.getDouble("HR_BKCHG");
                        HC_REAMT = cfm.Double2digitReturn(HC_REAMT);
                    }
/////

                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("FCS", "WEAIT6"); //
                    mne.setField("WEAIT1", RSL_GR.getString("HC_ACCODE").trim()); // ACCOUNT CODE

                    mne.setField("WECUAM", String.format("%.2f", HC_REAMT)); //amt
//                    mne.setField("WEACDT", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //
                    mne.setField("WEDTP5", cfm.SubDateForM3(RSL_GR.getString("HC_PYDT"))); //
                    mne.setField("WEAIT4", REF1); //ACC date
                    mne.setField("WEAIT5", cfm.SubDateForM3(RSL_GR.getString("HC_PYDT"))); //
                    mne.setField("WEAIT6", FNNO); //

                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.getMsg());
                    // JOptionPane.showMessageDialog(null, mne.getMsg());

                }
                if (mne.panel.equals("ARS110/F1")) {
                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim()); //
                    mne.setField("FCS", "WFPYNO"); //
                    mne.pressKey(MNEProtocol.KeyEnter);

                    ////BANK CHARGE
                    if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {

                        mne.setField("CMDTP", "KEY"); //CMDTP KEY
                        mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                        mne.setField("WFSLOP", "21");
                        mne.setField("FCS", "WFSLOP");
                        mne.setField("WFQTTP", "1");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.getMsg() != null) {
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                        if (mne.panel.equals("ARS110/G1")) {
                            mne.setField("CMDTP", "KEY"); //CMDTP KEY
                            mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WGCUAM", RSL_GR.getString("HR_BKCHG").trim());
                            mne.setField("WGAIT7", "ARS110");
                            mne.setField("FCS", "WGCUAM");
                            mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                    }
////
                    ResultSet rsl_line = cgd.LineReceiptnew(CONO, DIVI, arrRC);
                    while (rsl_line.next()) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
//                        mne.setField("WFPYNO", "TH28050014");
                        mne.setField("FCS", "WFCUCD");
                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());
                        mne.pressKey(MNEProtocol.KeyEnter);

                        mne.setField("CMDTP", "LSTOPT");
                        mne.setField("CMDVAL", "11");
                        mne.setField("FCS", "WFCINO");
                        mne.setField("SELROWS", "R1");
                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());
                        mne.selectOption("11");
                        System.out.println(mne.getMsg());

                        if (mne.getMsg() != null) {
                            System.out.println(mne.getMsg());
                            mne.pressKey(MNEProtocol.KeyEnter);
                            System.out.println(RSL_GR.getString("HC_RCNO").trim());
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "F18");
                            mne.setField("FCS", "WFCINO");
                            mne.pressKey("F18");
                            mne.pressKey(MNEProtocol.KeyEnter);
                            mne.pressKey(MNEProtocol.KeyEnter);
//                            mne.closeProgram(ARS110);
                        }

                        if (mne.panel.equals("ARS110/H")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WHCUAM");
                            mne.setField("WHCUAM", rsl_line.getString("LR_REAMT").trim());
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                        lastinv = rsl_line.getString("LR_INVNO").trim();
                    }

                    Double BL = cgd.Call_Balancenew(CONO, DIVI, arrRC);
//                    BL = BL + RSL_GR.getDouble("HR_BKCHG");
                    if (BL > 2) {
                        if (mne.panel.equals("ARS110/F1")) {

                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WFCINO", lastinv);

                            mne.setField("CMDTP", "LSTOPT");
                            mne.setField("CMDVAL", "12");
                            mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                            mne.setField("FCS", "WFCINO");
                            mne.setField("SELROWS", "R1");
                            mne.setField("WFCINO", lastinv);
                            mne.selectOption("12");

                        }

                        if (mne.panel.equals("ARS110/I")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WIAIT3");
                            mne.setField("WIAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.setField("WIAIT3", "NON");
                            mne.pressKey(MNEProtocol.KeyEnter);

                        }

                    } else if ((BL <= 2 && BL >= -2) && BL != 0) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFSLOP", "23");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.panel.equals("ARS110/G1")) {

                            if (mne.getMsg() != null) {
                                mne.pressKey(MNEProtocol.KeyEnter);

                                mne.setField("CMDTP", "KEY");
                                mne.setField("CMDVAL", "ENTER");
                                mne.setField("FCS", "WGAIT2");

                                if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                                    mne.setField("WGCUAM", cfm.Double2digitReturn(BL));
                                }
                            } else {
                                mne.setField("CMDTP", "KEY");
                                mne.setField("CMDVAL", "ENTER");
                                mne.setField("FCS", "WGAIT2");

                                if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                                    mne.setField("WGCUAM", cfm.Double2digitReturn(BL) * -1);
                                }
                            }

                            mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                    }

                    mne.setField("CMDTP", "KEY");
                    mne.setField("CMDVAL", "F3");
                    mne.pressKey(MNEProtocol.KeyF03);

                    if (mne.getMsg().trim().equalsIgnoreCase("Left to distribute does not equal 0")) {
                        System.out.println(mne.getMsg());
                        mne.pressKey(MNEProtocol.KeyEnter);
                        System.out.println(RSL_GR.getString("HC_RCNO").trim());
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "F18");
                        mne.setField("FCS", "WFCINO");
                        mne.pressKey("F18");
                        mne.pressKey(MNEProtocol.KeyEnter);
                        mne.pressKey(MNEProtocol.KeyEnter);
                    }

                    if (mne.getMsg() != null) {
                        System.out.println(mne.getMsg());
                    }
                    String[] Vouchermss = mne.getMsg().toString().split(" ");
                    String Voucher = Vouchermss[2].trim();

                    if (Voucher.length() == 8) {
                        csd.UpdataVoucher(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim(), Voucher);
                    }
                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.panel);
                }

            }

            mne.closeProgram(ARS110);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public static void ARS110_TRANSFER_KEY_BACK(int CONO, String DIVI, String RCNO, String TYPE) {
        String LoginUrlConnectionm3 = GBVAR.LoginUrlConnectionm3;
        String LoginM3User = "MVXSECOFR";
        String LoginM3Password = "lawson@90";
        String LoginAppServer = "192.200.9.190";
        int LoginAppPort = GBVAR.DBPORT;
//        int LoginAppPort = 16305; // TST

        try {
            ClassGetData cgd = new ClassGetData();
            ClassSetData csd = new ClassSetData();
            int cono = CONO;
            String divi = DIVI.trim();
            String lastinv = "";

            MNEHelper mne = new MNEHelper(LoginAppServer, LoginAppPort, LoginUrlConnectionm3);
            mne.logInToM3(cono, divi, LoginM3User, LoginM3Password);

            if (!mne.logInToM3(cono, divi, LoginM3User, LoginM3Password)) {
                System.out.println("Can not login to M3 system");
            }

            String ARS110 = mne.runM3Pgm("ARS110");

            if ((ARS110).equals("")) {
                System.out.println(" ไม่สามารถเปิดโปรแกรม ARS110 ได้");
                mne.closeProgram(ARS110);
            }

            ResultSet RSL_GR = cgd.GetReceipt(CONO, DIVI, RCNO, TYPE);

            ResultSet RSL_FNC = cgd.GET_FNC_REF1(CONO, DIVI, RCNO);
            String FNC = "", REF1 = "";
            while (RSL_FNC.next()) {
                FNC = RSL_FNC.getString("RL_FNC").trim();
                REF1 = RSL_FNC.getString("RL_REF1").trim();
            }
            System.out.println("Running");
            ClassFormatData cfm = new ClassFormatData();

            if (mne.panel.equals("ARS110/B")) {
                mne.setField("CMDTP", "KEY");
                mne.setField("CMDVAL", "ENTER");
                mne.setField("FCS", "WAFNCN");
                mne.setField("WAFNCN", FNC);
                mne.pressKey(MNEProtocol.KeyEnter);

                mne.setField("CMDTP", "1"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                mne.setField("SELROWS", "R1"); //CMDTP
                mne.setField("FCS", "WAFNCN"); //CMDVAL ENTER
                mne.setField("WAFNCN", FNC); //FCS W1OBKV
//              mne.pressKey(MNEProtocol.KeyEnter);
                mne.selectOption("1");
                System.out.println(mne.getMsg());
            }

            while (RSL_GR.next()) {

                if (mne.panel.equals("ARS112/E")) {
                    // System.out.println("CRS610/E");
                    if (RSL_GR.getString("HC_BANK").trim().equalsIgnoreCase("NONE")) {
                        continue;
                    }

                    Double HC_REAMT = RSL_GR.getDouble("HC_REAMT");

                    HC_REAMT = cfm.Double2digitReturn(HC_REAMT);

/////
                    if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                        System.out.println("CASE BANK CHARGE");
                        HC_REAMT = HC_REAMT - RSL_GR.getDouble("HR_BKCHG");
                        HC_REAMT = cfm.Double2digitReturn(HC_REAMT);
                    }
/////

                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("FCS", "WEAIT6"); //
                    mne.setField("WEAIT1", RSL_GR.getString("HC_ACCODE").trim()); // ACCOUNT CODE

                    mne.setField("WECUAM", String.format("%.2f", HC_REAMT)); //amt
//                    mne.setField("WEACDT", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //
                    mne.setField("WEDTP5", cfm.SubDateForM3(RSL_GR.getString("HC_PYDT"))); //
                    mne.setField("WEAIT4", REF1); //ACC date
                    mne.setField("WEAIT5", cfm.SubDateForM3(RSL_GR.getString("HC_PYDT"))); //
                    mne.setField("WEAIT6", RSL_GR.getString("HC_RCNO").trim()); //

                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.getMsg());
                    // JOptionPane.showMessageDialog(null, mne.getMsg());

                }
                if (mne.panel.equals("ARS110/F1")) {
                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim()); //
                    mne.setField("FCS", "WFPYNO"); //
                    mne.pressKey(MNEProtocol.KeyEnter);

                    ////BANK CHARGE
                    if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {

                        mne.setField("CMDTP", "KEY"); //CMDTP KEY
                        mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                        mne.setField("WFSLOP", "21");
                        mne.setField("FCS", "WFSLOP");
                        mne.setField("WFQTTP", "1");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.getMsg() != null) {
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                        if (mne.panel.equals("ARS110/G1")) {
                            mne.setField("CMDTP", "KEY"); //CMDTP KEY
                            mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WGCUAM", RSL_GR.getString("HR_BKCHG").trim());
                            mne.setField("WGAIT7", "ARS110");
                            mne.setField("FCS", "WGCUAM");
                            mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                    }
////

                    ResultSet rsl_line = cgd.LineReceipt(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim());
                    while (rsl_line.next()) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
//                        mne.setField("WFPYNO", "TH28050014");
                        mne.setField("FCS", "WFCUCD");
                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());
                        mne.pressKey(MNEProtocol.KeyEnter);

                        mne.setField("CMDTP", "LSTOPT");
                        mne.setField("CMDVAL", "11");
                        mne.setField("FCS", "WFCINO");
                        mne.setField("SELROWS", "R1");
                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());
                        mne.selectOption("11");
                        System.out.println(mne.getMsg());

                        if (mne.getMsg() != null) {
                            System.out.println(mne.getMsg());
                            mne.pressKey(MNEProtocol.KeyEnter);
                            System.out.println(RSL_GR.getString("HC_RCNO").trim());
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "F18");
                            mne.setField("FCS", "WFCINO");
                            mne.pressKey("F18");
                            mne.pressKey(MNEProtocol.KeyEnter);
                            mne.pressKey(MNEProtocol.KeyEnter);
//                            mne.closeProgram(ARS110);
                        }

                        if (mne.panel.equals("ARS110/H")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WHCUAM");
                            mne.setField("WHCUAM", rsl_line.getString("LR_REAMT").trim());
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                        lastinv = rsl_line.getString("LR_INVNO").trim();
                    }

                    Double BL = cgd.Call_Balance(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim());
//                    BL = BL + RSL_GR.getDouble("HR_BKCHG");
                    if (BL > 2) {
                        if (mne.panel.equals("ARS110/F1")) {

                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WFCINO", lastinv);

                            mne.setField("CMDTP", "LSTOPT");
                            mne.setField("CMDVAL", "12");
                            mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                            mne.setField("FCS", "WFCINO");
                            mne.setField("SELROWS", "R1");
                            mne.setField("WFCINO", lastinv);
                            mne.selectOption("12");

                        }

                        if (mne.panel.equals("ARS110/I")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WIAIT3");
                            mne.setField("WIAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.setField("WIAIT3", "NON");
                            mne.pressKey(MNEProtocol.KeyEnter);

                        }

                    } else if ((BL <= 2 && BL >= -2) && BL != 0) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFSLOP", "23");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.panel.equals("ARS110/G1")) {

                            if (mne.getMsg() != null) {
                                mne.pressKey(MNEProtocol.KeyEnter);

                                mne.setField("CMDTP", "KEY");
                                mne.setField("CMDVAL", "ENTER");
                                mne.setField("FCS", "WGAIT2");

                                if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                                    mne.setField("WGCUAM", cfm.Double2digitReturn(BL));
                                }
                            } else {
                                mne.setField("CMDTP", "KEY");
                                mne.setField("CMDVAL", "ENTER");
                                mne.setField("FCS", "WGAIT2");

                                if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                                    mne.setField("WGCUAM", cfm.Double2digitReturn(BL) * -1);
                                }
                            }

                            mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                    }

                    mne.setField("CMDTP", "KEY");
                    mne.setField("CMDVAL", "F3");
                    mne.pressKey(MNEProtocol.KeyF03);

                    if (mne.getMsg().trim().equalsIgnoreCase("Left to distribute does not equal 0")) {
                        System.out.println(mne.getMsg());
                        mne.pressKey(MNEProtocol.KeyEnter);
                        System.out.println(RSL_GR.getString("HC_RCNO").trim());
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "F18");
                        mne.setField("FCS", "WFCINO");
                        mne.pressKey("F18");
                        mne.pressKey(MNEProtocol.KeyEnter);
                        mne.pressKey(MNEProtocol.KeyEnter);
                    }

                    if (mne.getMsg() != null) {
                        System.out.println(mne.getMsg());
                    }
                    String[] Vouchermss = mne.getMsg().toString().split(" ");
                    String Voucher = Vouchermss[2].trim();

                    if (Voucher.length() == 8) {
                        csd.UpdataVoucher(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim(), Voucher);
                    }
                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.panel);
                }

            }

            mne.closeProgram(ARS110);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public static void ARS110_CHECK(int CONO, String DIVI, String RCNO, String TYPE) {
//        String LoginUrlConnectionm3 = "https://bkrmvxm3.bangkokranch.com:21008/mne/servlet/MvxMCSvt"; //PRD
        String LoginUrlConnectionm3 = GBVAR.LoginUrlConnectionm3;
        String LoginM3User = "MVXSECOFR";
        String LoginM3Password = "lawson@90";
        String LoginAppServer = "192.200.9.190";
        int LoginAppPort = GBVAR.DBPORT;
//        int LoginAppPort = 16305; // TST

        try {
            ClassGetData cgd = new ClassGetData();
            ClassSetData csd = new ClassSetData();
            int cono = CONO;
            String divi = DIVI.trim();
            String lastinv = "";

            MNEHelper mne = new MNEHelper(LoginAppServer, LoginAppPort, LoginUrlConnectionm3);
            mne.logInToM3(cono, divi, LoginM3User, LoginM3Password);

            if (!mne.logInToM3(cono, divi, LoginM3User, LoginM3Password)) {
                System.out.println("Can not login to M3 system");
            }

            String ARS110 = mne.runM3Pgm("ARS110");

            if ((ARS110).equals("")) {
                System.out.println(" ไม่สามารถเปิดโปรแกรม ARS110 ได้");
                mne.closeProgram(ARS110);
            }

            ResultSet RSL_GR = cgd.GetReceipt(CONO, DIVI, RCNO, TYPE);

            ResultSet RSL_FNC = cgd.GET_FNC_REF1(CONO, DIVI, RCNO);
            String FNC = "", REF1 = "";
            while (RSL_FNC.next()) {
                FNC = RSL_FNC.getString("RL_FNC").trim();
                REF1 = RSL_FNC.getString("RL_REF1").trim();
            }
            Update_OCUSMA(CONO, DIVI, RCNO, TYPE, "1");
            System.out.println("Running");
            ClassFormatData cfm = new ClassFormatData();

            if (mne.panel.equals("ARS110/B")) {
                mne.setField("CMDTP", "KEY");
                mne.setField("CMDVAL", "ENTER");
                mne.setField("FCS", "WAFNCN");
                mne.setField("WAFNCN", FNC);
                mne.pressKey(MNEProtocol.KeyEnter);

                mne.setField("CMDTP", "1"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                mne.setField("SELROWS", "R1"); //CMDTP
                mne.setField("FCS", "WAFNCN"); //CMDVAL ENTER
                mne.setField("WAFNCN", FNC); //FCS W1OBKV
//              mne.pressKey(MNEProtocol.KeyEnter);
                mne.selectOption("1");
                System.out.println(mne.getMsg());
            }

            while (RSL_GR.next()) {

                if (mne.panel.equals("ARS112/E")) {
                    // System.out.println("CRS610/E");
                    Double HC_REAMT = RSL_GR.getDouble("HC_REAMT");

                    if (RSL_GR.getString("HC_CHKNO").trim().equalsIgnoreCase("")) {
                        continue;
                    }

                    if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                        HC_REAMT = HC_REAMT - RSL_GR.getDouble("HR_BKCHG");
                    }

                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("FCS", "WEDRRN"); //
                    mne.setField("WEAIT1", RSL_GR.getString("HC_ACCODE").trim()); // ACCOUNT CODE
                    mne.setField("WEPYNO", RSL_GR.getString("HC_PYNO").trim()); //
                    mne.setField("WECUAM", String.format("%.2f", HC_REAMT)); //amt
                    mne.setField("WEDTP5", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //
                    mne.setField("WEACDT", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //
                    mne.setField("WEDRRN", RSL_GR.getString("HC_CHKNO").trim()); //ACC date
                    mne.setField("WEDUDT", cfm.SubDateForM3(RSL_GR.getString("HC_PYDT"))); //
                    mne.setField("WEBKID", RSL_GR.getString("HC_BANK").trim()); //
                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.getMsg());
                    // JOptionPane.showMessageDialog(null, mne.getMsg());
                    if (mne.getMsg() != null) {
                        mne.pressKey(MNEProtocol.KeyEnter);
                        continue;
                    }

                }
                if (mne.panel.equals("ARS110/F1")) {
                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim()); //
                    mne.setField("FCS", "WFPYNO"); //
                    mne.pressKey(MNEProtocol.KeyEnter);

                    ////BANK CHARGE
                    if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {

                        mne.setField("CMDTP", "KEY"); //CMDTP KEY
                        mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                        mne.setField("WFSLOP", "21");
                        mne.setField("FCS", "WFSLOP");
                        mne.setField("WFQTTP", "1");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.getMsg() != null) {
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                        if (mne.panel.equals("ARS110/G1")) {
                            mne.setField("CMDTP", "KEY"); //CMDTP KEY
                            mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WGCUAM", RSL_GR.getString("HR_BKCHG").trim());
                            mne.setField("WGAIT7", "ARS110");
                            mne.setField("FCS", "WGCUAM");
                            mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                    }
////

                    ResultSet rsl_line = cgd.LineReceipt(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim());
                    while (rsl_line.next()) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
//                        mne.setField("WFPYNO", "TH28050014");
                        mne.setField("FCS", "WFCINO");
                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.getMsg() != null) {
                            System.out.println(mne.getMsg());
                            mne.pressKey(MNEProtocol.KeyEnter);
                            System.out.println(RSL_GR.getString("HC_RCNO").trim());
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "F18");
                            mne.setField("FCS", "WFCINO");
                            mne.pressKey("F18");
                            mne.pressKey(MNEProtocol.KeyEnter);
                            mne.pressKey(MNEProtocol.KeyEnter);
                            mne.closeProgram(ARS110);
                        }

                        mne.setField("CMDTP", "LSTOPT");
                        mne.setField("CMDVAL", "11");
                        mne.setField("FCS", "WFCINO");
                        mne.setField("SELROWS", "R1");
                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());
                        mne.selectOption("11");
                        System.out.println(mne.getMsg());

                        if (mne.panel.equals("ARS110/H")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WHCUAM");
                            mne.setField("WHCUAM", rsl_line.getString("LR_REAMT").trim());
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                        lastinv = rsl_line.getString("LR_INVNO").trim();
                    }

                    Double BL = cgd.Call_Balance(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim());
//                    BL = BL + RSL_GR.getDouble("HR_BKCHG");
                    if (BL > 2) {
                        if (mne.panel.equals("ARS110/F1")) {

                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WFCINO", lastinv);

                            mne.setField("CMDTP", "LSTOPT");
                            mne.setField("CMDVAL", "12");
                            mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                            mne.setField("FCS", "WFCINO");
                            mne.setField("SELROWS", "R1");
                            mne.setField("WFCINO", lastinv);
                            mne.selectOption("12");

                        }

//                        mne.setField("CMDTP", "KEY");
//                        mne.setField("CMDVAL", "ENTER");
////                        mne.setField("WFPYNO", "TH28050014");
//                        mne.setField("FCS", "WFCINO");
//                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());
//                        mne.pressKey(MNEProtocol.KeyEnter);
                        if (mne.panel.equals("ARS110/I")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WIAIT3");
                            mne.setField("WIAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.setField("WIAIT3", "NON");
                            mne.pressKey(MNEProtocol.KeyEnter);

                        }

                    } else if ((BL <= 2 && BL >= -2) && BL != 0) {
//                    } else if (BL <= 2 && BL>0) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFSLOP", "23");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.panel.equals("ARS110/G1")) {

                            if (mne.getMsg() != null) {
                                mne.pressKey(MNEProtocol.KeyEnter);
                            }

                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WGAIT2");

                            if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                                mne.setField("WGCUAM", cfm.Double2digitReturn(BL));
                            }

                            mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                    }

                    mne.setField("CMDTP", "KEY");
                    mne.setField("CMDVAL", "F3");
                    mne.pressKey(MNEProtocol.KeyF03);

                    if (mne.getMsg().trim().equalsIgnoreCase("Left to distribute does not equal 0")) {
                        System.out.println(mne.getMsg());
                        mne.pressKey(MNEProtocol.KeyEnter);
                        System.out.println(RSL_GR.getString("HC_RCNO").trim());
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "F18");
                        mne.setField("FCS", "WFCINO");
                        mne.pressKey("F18");
                        mne.pressKey(MNEProtocol.KeyEnter);
                        mne.pressKey(MNEProtocol.KeyEnter);
                    }

                    if (mne.getMsg() != null) {
                        System.out.println(mne.getMsg());
                    }
                    String[] Vouchermss = mne.getMsg().toString().split(" ");
                    String Voucher = Vouchermss[2].trim();

                    if (Voucher.length() == 8) {
                        csd.UpdataVoucher(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim(), Voucher);
                    }
                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.panel);
                }

            }
            mne.closeProgram(ARS110);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public static void ARS110_CHECK_KEY_BACK(int CONO, String DIVI, String RCNO, String TYPE) {
//        String LoginUrlConnectionm3 = "https://bkrmvxm3.bangkokranch.com:21008/mne/servlet/MvxMCSvt"; //PRD
        String LoginUrlConnectionm3 = GBVAR.LoginUrlConnectionm3;
        String LoginM3User = "MVXSECOFR";
        String LoginM3Password = "lawson@90";
        String LoginAppServer = "192.200.9.190";
        int LoginAppPort = GBVAR.DBPORT;
//        int LoginAppPort = 16305; // TST

        try {
            ClassGetData cgd = new ClassGetData();
            ClassSetData csd = new ClassSetData();
            int cono = CONO;
            String divi = DIVI.trim();
            String lastinv = "";

            MNEHelper mne = new MNEHelper(LoginAppServer, LoginAppPort, LoginUrlConnectionm3);
            mne.logInToM3(cono, divi, LoginM3User, LoginM3Password);

            if (!mne.logInToM3(cono, divi, LoginM3User, LoginM3Password)) {
                System.out.println("Can not login to M3 system");
            }

            String ARS110 = mne.runM3Pgm("ARS110");

            if ((ARS110).equals("")) {
                System.out.println(" ไม่สามารถเปิดโปรแกรม ARS110 ได้");
                mne.closeProgram(ARS110);
            }

            ResultSet RSL_GR = cgd.GetReceipt(CONO, DIVI, RCNO, TYPE);

            ResultSet RSL_FNC = cgd.GET_FNC_REF1(CONO, DIVI, RCNO);
            String FNC = "", REF1 = "";
            while (RSL_FNC.next()) {
                FNC = RSL_FNC.getString("RL_FNC").trim();
                REF1 = RSL_FNC.getString("RL_REF1").trim();
            }

            Update_OCUSMA(CONO, DIVI, RCNO, TYPE, "1");
            System.out.println("Running");
            ClassFormatData cfm = new ClassFormatData();

            if (mne.panel.equals("ARS110/B")) {
                mne.setField("CMDTP", "KEY");
                mne.setField("CMDVAL", "ENTER");
                mne.setField("FCS", "WAFNCN");
                mne.setField("WAFNCN", FNC);
                mne.pressKey(MNEProtocol.KeyEnter);

                mne.setField("CMDTP", "1"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                mne.setField("SELROWS", "R1"); //CMDTP
                mne.setField("FCS", "WAFNCN"); //CMDVAL ENTER
                mne.setField("WAFNCN", FNC); //FCS W1OBKV
//              mne.pressKey(MNEProtocol.KeyEnter);
                mne.selectOption("1");
                System.out.println(mne.getMsg());
            }

            while (RSL_GR.next()) {

                if (mne.panel.equals("ARS112/E")) {
                    // System.out.println("CRS610/E");
                    Double HC_REAMT = cfm.Double2digitReturn(RSL_GR.getDouble("HC_REAMT"));

                    if (RSL_GR.getString("HC_CHKNO").trim().equalsIgnoreCase("")) {
                        continue;
                    }

                    if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                        HC_REAMT = HC_REAMT - RSL_GR.getDouble("HR_BKCHG");
                    }

                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("FCS", "WEDRRN"); //
                    mne.setField("WEAIT1", RSL_GR.getString("HC_ACCODE").trim()); // ACCOUNT CODE
                    mne.setField("WEPYNO", RSL_GR.getString("HC_PYNO").trim()); //
                    mne.setField("WECUAM", String.format("%.2f", HC_REAMT)); //amt
                    mne.setField("WEDTP5", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //
//                    mne.setField("WEACDT", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //
                    mne.setField("WEDRRN", RSL_GR.getString("HC_CHKNO").trim()); //ACC date
                    mne.setField("WEDUDT", cfm.SubDateForM3(RSL_GR.getString("HC_PYDT"))); //
                    mne.setField("WEBKID", RSL_GR.getString("HC_BANK").trim()); //
                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.getMsg());
                    // JOptionPane.showMessageDialog(null, mne.getMsg());
                    if (mne.getMsg() != null) {
                        mne.pressKey(MNEProtocol.KeyEnter);
                        continue;
                    }

                }
                if (mne.panel.equals("ARS110/F1")) {
                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim()); //
                    mne.setField("FCS", "WFPYNO"); //
                    mne.pressKey(MNEProtocol.KeyEnter);

                    ////BANK CHARGE
                    if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {

                        mne.setField("CMDTP", "KEY"); //CMDTP KEY
                        mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                        mne.setField("WFSLOP", "21");
                        mne.setField("FCS", "WFSLOP");
                        mne.setField("WFQTTP", "1");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.getMsg() != null) {
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                        if (mne.panel.equals("ARS110/G1")) {
                            mne.setField("CMDTP", "KEY"); //CMDTP KEY
                            mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WGCUAM", RSL_GR.getString("HR_BKCHG").trim());
                            mne.setField("WGAIT7", "ARS110");
                            mne.setField("FCS", "WGCUAM");
                            mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                    }
////

                    ResultSet rsl_line = cgd.LineReceipt(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim());
                    while (rsl_line.next()) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
//                        mne.setField("WFPYNO", "TH28050014");
                        mne.setField("FCS", "WFCINO");
                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.getMsg() != null) {
                            System.out.println(mne.getMsg());
                            mne.pressKey(MNEProtocol.KeyEnter);
                            System.out.println(RSL_GR.getString("HC_RCNO").trim());
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "F18");
                            mne.setField("FCS", "WFCINO");
                            mne.pressKey("F18");
                            mne.pressKey(MNEProtocol.KeyEnter);
                            mne.pressKey(MNEProtocol.KeyEnter);
                            mne.closeProgram(ARS110);
                        }

                        mne.setField("CMDTP", "LSTOPT");
                        mne.setField("CMDVAL", "11");
                        mne.setField("FCS", "WFCINO");
                        mne.setField("SELROWS", "R1");
                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());
                        mne.selectOption("11");
                        System.out.println(mne.getMsg());

                        if (mne.panel.equals("ARS110/H")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WHCUAM");
                            mne.setField("WHCUAM", rsl_line.getString("LR_REAMT").trim());
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                        lastinv = rsl_line.getString("LR_INVNO").trim();
                    }

                    Double BL = cgd.Call_Balance(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim());
//                    BL = BL + RSL_GR.getDouble("HR_BKCHG");
                    if (BL > 2) {
                        if (mne.panel.equals("ARS110/F1")) {

                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WFCINO", lastinv);

                            mne.setField("CMDTP", "LSTOPT");
                            mne.setField("CMDVAL", "12");
                            mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                            mne.setField("FCS", "WFCINO");
                            mne.setField("SELROWS", "R1");
                            mne.setField("WFCINO", lastinv);
                            mne.selectOption("12");

                        }

//                        mne.setField("CMDTP", "KEY");
//                        mne.setField("CMDVAL", "ENTER");
////                        mne.setField("WFPYNO", "TH28050014");
//                        mne.setField("FCS", "WFCINO");
//                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());
//                        mne.pressKey(MNEProtocol.KeyEnter);
                        if (mne.panel.equals("ARS110/I")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WIAIT3");
                            mne.setField("WIAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.setField("WIAIT3", "NON");
                            mne.pressKey(MNEProtocol.KeyEnter);

                        }

                    } else if ((BL <= 2 && BL >= -2) && BL != 0) {
//                    } else if (BL <= 2 && BL>0) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFSLOP", "23");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.panel.equals("ARS110/G1")) {

                            if (mne.getMsg() != null) {
                                mne.pressKey(MNEProtocol.KeyEnter);
                            }

                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WGAIT2");

                            if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                                mne.setField("WGCUAM", cfm.Double2digitReturn(BL));
                            }

                            mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                    }

                    mne.setField("CMDTP", "KEY");
                    mne.setField("CMDVAL", "F3");
                    mne.pressKey(MNEProtocol.KeyF03);

                    if (mne.getMsg().trim().equalsIgnoreCase("Left to distribute does not equal 0")) {
                        System.out.println(mne.getMsg());
                        mne.pressKey(MNEProtocol.KeyEnter);
                        System.out.println(RSL_GR.getString("HC_RCNO").trim());
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "F18");
                        mne.setField("FCS", "WFCINO");
                        mne.pressKey("F18");
                        mne.pressKey(MNEProtocol.KeyEnter);
                        mne.pressKey(MNEProtocol.KeyEnter);
                    }

                    if (mne.getMsg() != null) {
                        System.out.println(mne.getMsg());
                    }
                    String[] Vouchermss = mne.getMsg().toString().split(" ");
                    String Voucher = Vouchermss[2].trim();

                    if (Voucher.length() == 8) {
                        csd.UpdataVoucher(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim(), Voucher);
                    }
                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.panel);
                }

            }
            mne.closeProgram(ARS110);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public static void ARS110_CHECK_KEY_BACKnew(int CONO, String DIVI, ArrayList<String> arrRC, String TYPE, String FNNO) {
//        String LoginUrlConnectionm3 = "https://bkrmvxm3.bangkokranch.com:21008/mne/servlet/MvxMCSvt"; //PRD
        String LoginUrlConnectionm3 = GBVAR.LoginUrlConnectionm3;
        String LoginM3User = "MVXSECOFR";
        String LoginM3Password = "lawson@90";
        String LoginAppServer = "192.200.9.190";
        int LoginAppPort = GBVAR.DBPORT;
//        int LoginAppPort = 16305; // TST

        try {
            ClassGetData cgd = new ClassGetData();
            ClassSetData csd = new ClassSetData();
            int cono = CONO;
            String divi = DIVI.trim();
            String lastinv = "";

            MNEHelper mne = new MNEHelper(LoginAppServer, LoginAppPort, LoginUrlConnectionm3);
            mne.logInToM3(cono, divi, LoginM3User, LoginM3Password);

            if (!mne.logInToM3(cono, divi, LoginM3User, LoginM3Password)) {
                System.out.println("Can not login to M3 system");
            }

            String ARS110 = mne.runM3Pgm("ARS110");

            if ((ARS110).equals("")) {
                System.out.println(" ไม่สามารถเปิดโปรแกรม ARS110 ได้");
                mne.closeProgram(ARS110);
            }

            ResultSet RSL_GR = cgd.GetReceiptnew(CONO, DIVI, arrRC.get(0), TYPE);
            ResultSet RSL_FNC = cgd.GET_FNC_REF1(CONO, DIVI, arrRC.get(0));
            String FNC = "", REF1 = "";
            while (RSL_FNC.next()) {
                FNC = RSL_FNC.getString("RL_FNC").trim();
                REF1 = RSL_FNC.getString("RL_REF1").trim();
            }

            for (int i = 0; i < arrRC.size(); i++) {

                Update_OCUSMAnew(CONO, DIVI, arrRC.get(i), TYPE, "1", FNNO);

            }

            System.out.println("Running");
            ClassFormatData cfm = new ClassFormatData();

            if (mne.panel.equals("ARS110/B")) {
                mne.setField("CMDTP", "KEY");
                mne.setField("CMDVAL", "ENTER");
                mne.setField("FCS", "WAFNCN");
                mne.setField("WAFNCN", FNC);
                mne.pressKey(MNEProtocol.KeyEnter);

                mne.setField("CMDTP", "1"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                mne.setField("SELROWS", "R1"); //CMDTP
                mne.setField("FCS", "WAFNCN"); //CMDVAL ENTER
                mne.setField("WAFNCN", FNC); //FCS W1OBKV
//              mne.pressKey(MNEProtocol.KeyEnter);
                mne.selectOption("1");
                System.out.println(mne.getMsg());
            }

            while (RSL_GR.next()) {

                if (mne.panel.equals("ARS112/E")) {
                    // System.out.println("CRS610/E");
                    Double HC_REAMT = cfm.Double2digitReturn(RSL_GR.getDouble("HC_REAMT"));

                    if (RSL_GR.getString("HC_CHKNO").trim().equalsIgnoreCase("")) {
                        continue;
                    }

                    if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                        HC_REAMT = HC_REAMT - RSL_GR.getDouble("HR_BKCHG");
                    }

                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("FCS", "WEDRRN"); //
                    mne.setField("WEAIT1", RSL_GR.getString("HC_ACCODE").trim()); // ACCOUNT CODE
                    mne.setField("WEPYNO", RSL_GR.getString("HC_PYNO").trim()); //
                    mne.setField("WECUAM", String.format("%.2f", HC_REAMT)); //amt
                    mne.setField("WEDTP5", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //
//                    mne.setField("WEACDT", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //
                    mne.setField("WEDRRN", RSL_GR.getString("HC_CHKNO").trim()); //ACC date
                    mne.setField("WEDUDT", cfm.SubDateForM3(RSL_GR.getString("HC_PYDT"))); //
                    mne.setField("WEBKID", RSL_GR.getString("HC_BANK").trim()); //
                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.getMsg());
                    // JOptionPane.showMessageDialog(null, mne.getMsg());
                    if (mne.getMsg() != null) {
                        mne.pressKey(MNEProtocol.KeyEnter);
                        continue;
                    }

                }
                if (mne.panel.equals("ARS110/F1")) {
                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim()); //
                    mne.setField("FCS", "WFPYNO"); //
                    mne.pressKey(MNEProtocol.KeyEnter);

                    ////BANK CHARGE
                    if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {

                        mne.setField("CMDTP", "KEY"); //CMDTP KEY
                        mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                        mne.setField("WFSLOP", "21");
                        mne.setField("FCS", "WFSLOP");
                        mne.setField("WFQTTP", "1");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.getMsg() != null) {
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                        if (mne.panel.equals("ARS110/G1")) {
                            mne.setField("CMDTP", "KEY"); //CMDTP KEY
                            mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WGCUAM", RSL_GR.getString("HR_BKCHG").trim());
                            mne.setField("WGAIT7", "ARS110");
                            mne.setField("FCS", "WGCUAM");
                            mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                    }
////

                    ResultSet rsl_line = cgd.LineReceiptnew(CONO, DIVI, arrRC);
                    while (rsl_line.next()) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
//                        mne.setField("WFPYNO", "TH28050014");
                        mne.setField("FCS", "WFCINO");
                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.getMsg() != null) {
                            System.out.println(mne.getMsg());
                            mne.pressKey(MNEProtocol.KeyEnter);
                            System.out.println(RSL_GR.getString("HC_RCNO").trim());
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "F18");
                            mne.setField("FCS", "WFCINO");
                            mne.pressKey("F18");
                            mne.pressKey(MNEProtocol.KeyEnter);
                            mne.pressKey(MNEProtocol.KeyEnter);
                            mne.closeProgram(ARS110);
                        }

                        mne.setField("CMDTP", "LSTOPT");
                        mne.setField("CMDVAL", "11");
                        mne.setField("FCS", "WFCINO");
                        mne.setField("SELROWS", "R1");
                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());
                        mne.selectOption("11");
                        System.out.println(mne.getMsg());

                        if (mne.panel.equals("ARS110/H")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WHCUAM");
                            mne.setField("WHCUAM", rsl_line.getString("LR_REAMT").trim());
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                        lastinv = rsl_line.getString("LR_INVNO").trim();
                    }

                    Double BL = cgd.Call_Balancenew(CONO, DIVI, arrRC);
//                    BL = BL + RSL_GR.getDouble("HR_BKCHG");
                    if (BL > 2) {
                        if (mne.panel.equals("ARS110/F1")) {

                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WFCINO", lastinv);

                            mne.setField("CMDTP", "LSTOPT");
                            mne.setField("CMDVAL", "12");
                            mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                            mne.setField("FCS", "WFCINO");
                            mne.setField("SELROWS", "R1");
                            mne.setField("WFCINO", lastinv);
                            mne.selectOption("12");

                        }

//                        mne.setField("CMDTP", "KEY");
//                        mne.setField("CMDVAL", "ENTER");
////                        mne.setField("WFPYNO", "TH28050014");
//                        mne.setField("FCS", "WFCINO");
//                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());
//                        mne.pressKey(MNEProtocol.KeyEnter);
                        if (mne.panel.equals("ARS110/I")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WIAIT3");
                            mne.setField("WIAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.setField("WIAIT3", "NON");
                            mne.pressKey(MNEProtocol.KeyEnter);

                        }

                    } else if ((BL <= 2 && BL >= -2) && BL != 0) {
//                    } else if (BL <= 2 && BL>0) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFSLOP", "23");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.panel.equals("ARS110/G1")) {

                            if (mne.getMsg() != null) {
                                mne.pressKey(MNEProtocol.KeyEnter);
                            }

                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WGAIT2");

                            if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                                mne.setField("WGCUAM", cfm.Double2digitReturn(BL));
                            }

                            mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                    }

                    mne.setField("CMDTP", "KEY");
                    mne.setField("CMDVAL", "F3");
                    mne.pressKey(MNEProtocol.KeyF03);

                    if (mne.getMsg().trim().equalsIgnoreCase("Left to distribute does not equal 0")) {
                        System.out.println(mne.getMsg());
                        mne.pressKey(MNEProtocol.KeyEnter);
                        System.out.println(RSL_GR.getString("HC_RCNO").trim());
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "F18");
                        mne.setField("FCS", "WFCINO");
                        mne.pressKey("F18");
                        mne.pressKey(MNEProtocol.KeyEnter);
                        mne.pressKey(MNEProtocol.KeyEnter);
                    }

                    if (mne.getMsg() != null) {
                        System.out.println(mne.getMsg());
                    }
                    String[] Vouchermss = mne.getMsg().toString().split(" ");
                    String Voucher = Vouchermss[2].trim();

                    if (Voucher.length() == 8) {
                        csd.UpdataVoucher(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim(), Voucher);
                    }
                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.panel);
                }

            }
            mne.closeProgram(ARS110);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    ///////////////////////////// EXPENSE FULL //////////////////////////////////////////////
    public static void ARS110_TRANSFER_EXPENSE_FULLnew(int CONO, String DIVI, ArrayList<String> arrRC, String TYPE, String FNNO) {
//        String LoginUrlConnectionm3 = "https://bkrmvxm3.bangkokranch.com:21008/mne/servlet/MvxMCSvt"; //PRD
        String LoginUrlConnectionm3 = GBVAR.LoginUrlConnectionm3;
        String LoginM3User = "MVXSECOFR";
        String LoginM3Password = "lawson@90";
        String LoginAppServer = "192.200.9.190";
        int LoginAppPort = GBVAR.DBPORT;
//        int LoginAppPort = 16305; // TST

        try {
            ClassGetData cgd = new ClassGetData();
            ClassSetData csd = new ClassSetData();
            int cono = CONO;
            String divi = DIVI.trim();
            String lastinv = "";

            MNEHelper mne = new MNEHelper(LoginAppServer, LoginAppPort, LoginUrlConnectionm3);
            mne.logInToM3(cono, divi, LoginM3User, LoginM3Password);

            if (!mne.logInToM3(cono, divi, LoginM3User, LoginM3Password)) {
                System.out.println("Can not login to M3 system");
            }

            String ARS110 = mne.runM3Pgm("ARS110");

            if ((ARS110).equals("")) {
                System.out.println(" ไม่สามารถเปิดโปรแกรม ARS110 ได้");
                mne.closeProgram(ARS110);
            }

            ResultSet RSL_GR = cgd.GetReceiptnew4(CONO, DIVI, arrRC.get(0), TYPE);

            ResultSet RSL_FNC = cgd.GET_FNC_REF1(CONO, DIVI, arrRC.get(0));
            String FNC = "", REF1 = "";
            while (RSL_FNC.next()) {
                FNC = RSL_FNC.getString("RL_FNC").trim();
                REF1 = RSL_FNC.getString("RL_REF1").trim();
            }

            System.out.println("Running");
            ClassFormatData cfm = new ClassFormatData();

            if (mne.panel.equals("ARS110/B")) {
                mne.setField("CMDTP", "KEY");
                mne.setField("CMDVAL", "ENTER");
                mne.setField("FCS", "WAFNCN");
                mne.setField("WAFNCN", FNC);
                mne.pressKey(MNEProtocol.KeyEnter);
                System.out.println(FNC);

                mne.setField("CMDTP", "1"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                mne.setField("SELROWS", "R1"); //CMDTP
                mne.setField("FCS", "WAFNCN"); //CMDVAL ENTER
                mne.setField("WAFNCN", FNC); //FCS W1OBKV
//              mne.pressKey(MNEProtocol.KeyEnter);
                mne.selectOption("1");
                System.out.println(mne.getMsg());
                System.out.println(FNC);
            }

            while (RSL_GR.next()) {

                if (mne.panel.equals("ARS112/E")) {
                    // System.out.println("CRS610/E");
                    Double HC_REAMT = RSL_GR.getDouble("HC_REAMT");
                    System.out.println(HC_REAMT);

/////
                    if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                        System.out.println("CASE BANK CHARGE");
                        HC_REAMT = HC_REAMT - RSL_GR.getDouble("HR_BKCHG");
                    }
/////

                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("FCS", "WEAIT6"); //
                    mne.setField("WEAIT1", RSL_GR.getString("HC_ACCODE").trim()); // ACCOUNT CODE
                    System.out.println(RSL_GR.getString("HC_ACCODE").trim());
                    mne.setField("WECUAM", String.format("%.2f", HC_REAMT)); //amt
                    System.out.println(String.format("%.2f", HC_REAMT));
                    mne.setField("WEACDT", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //
                    mne.setField("WEDTP5", cfm.SubDateForM3(RSL_GR.getString("HC_PYDT"))); //
                    mne.setField("WEAIT4", REF1); //ACC date
                    System.out.println(REF1);

                    mne.setField("WEAIT5", cfm.SubDateForM3(RSL_GR.getString("HC_PYDT"))); //
                    System.out.println(cfm.SubDateForM3(RSL_GR.getString("HC_TRDT")));
                    System.out.println(cfm.SubDateForM3(RSL_GR.getString("HC_PYDT")));
                    mne.setField("WEAIT6", FNNO); //
                    //System.out.println(RSL_GR.getString("HC_RCNO").trim());

                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.getMsg() + "ERRRRR");
                    // JOptionPane.showMessageDialog(null, mne.getMsg());

                }
                if (mne.panel.equals("ARS110/F1")) {
                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim()); //
                    System.out.println(RSL_GR.getString("HC_PYNO").trim());
                    mne.setField("FCS", "WFPYNO"); //
                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(RSL_GR.getDouble("HR_BKCHG"));

                    ////BANK CHARGE
                    System.out.println("barrrrrrrrrrrrrrrrr");
                    if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {

                        System.out.println("ffffffffffffffuuuuuuuuuuuuuuuuu");
                        mne.setField("CMDTP", "KEY"); //CMDTP KEY
                        mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                        mne.setField("WFSLOP", "21");
                        mne.setField("FCS", "WFSLOP");
                        mne.setField("WFQTTP", "1");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.getMsg() != null) {
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                        if (mne.panel.equals("ARS110/G1")) {
                            mne.setField("CMDTP", "KEY"); //CMDTP KEY
                            mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WGCUAM", RSL_GR.getString("HR_BKCHG").trim());
                            mne.setField("WGAIT7", "ARS110");
                            mne.setField("FCS", "WGCUAM");
                            mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                    }
////

//////// EXPENSE ////////
                    ResultSet rsl_expense1 = cgd.LineExpensenew(CONO, DIVI, FNNO);
//                    ResultSet rsl_expense2 = cgd.LineExpense(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim());

                    while (rsl_expense1.next()) {

                        String LRE_AMT = rsl_expense1.getString("LRE_AMT").trim();
                        String LRE_ACTCODE = rsl_expense1.getString("LRE_ACTCODE").trim();
                        String LRE_CODE = rsl_expense1.getString("LRE_CODE").trim();

                        mne.setField("CMDTP", "KEY"); //CMDTP KEY
                        mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());

                        mne.setField("WFSLOP", rsl_expense1.getString("RE_OPTION").trim());
                        System.out.println(RSL_GR.getString("HC_PYNO").trim());
                        System.out.println(rsl_expense1.getString("RE_OPTION").trim());

                        mne.setField("FCS", "WFSLOP");
                        mne.setField("WFQTTP", "1");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.getMsg() != null) {
                            System.out.println("************* mne.getMsg() *************");
                            System.out.println(mne.getMsg());

                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                        System.out.println("************* ROUND 0 *************");
                        System.out.println(LRE_AMT);
                        System.out.println(LRE_ACTCODE);
                        System.out.println("**********************************");

                        if (mne.panel.equals("ARS110/G1")) {
                            System.out.println("************* ROUND 1 *************");
                            System.out.println(LRE_AMT);
                            System.out.println(LRE_ACTCODE);

//                            System.out.println(rsl_expense1.getString("LRE_AMT").trim());
//                            System.out.println(rsl_expense1.getString("LRE_ACTCODE").trim());
                            System.out.println("**********************************");

                            mne.setField("CMDTP", "KEY"); //CMDTP KEY
                            mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                            mne.setField("CMDVAL", "ENTER");
                            System.out.println("************* ROUND 1.5 *************");
                            System.out.println(LRE_AMT);
                            System.out.println("**********************************");

                            mne.setField("WGCUAM", LRE_AMT);

                            mne.setField("WGAIT1", LRE_ACTCODE);
                            mne.setField("WGAIT4", cgd.Expense_CustomerName(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.setField("WGVTXT", cgd.Expense_text(CONO, DIVI, LRE_CODE));
                            System.out.println("************* mne.getMsg() *************");
                            System.out.println(mne.getMsg());
                            System.out.println("************* ROUND 2 *************");
                            System.out.println(LRE_AMT);
                            System.out.println(LRE_ACTCODE);
                            System.out.println(cgd.Expense_CustomerName(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            System.out.println(cgd.Expense_text(CONO, DIVI, LRE_CODE));

//                            System.out.println(rsl_expense1.getString("LRE_AMT").trim());
//                            System.out.println(rsl_expense1.getString("LRE_ACTCODE").trim());
                            System.out.println("**********************************");

//                            mne.setField("WGAIT4", "");
//                            mne.setField("WGVTXT", "");
                            System.out.println(rsl_expense1.getString("LRE_AMT").trim());
                            System.out.println(rsl_expense1.getString("LRE_ACTCODE").trim());
//                            System.out.println(cgd.Expense_CustomerName(CONO, RSL_GR.getString("HC_PYNO")));
//                            System.out.println(cgd.Expense_text(CONO, DIVI, rsl_expense1.getString("LRE_CODE").trim()));

                            mne.setField("FCS", "WGCUAM");

                            System.out.println(rsl_expense1.getString("LRE_CODE").trim());
                            System.out.println(rsl_expense1.getString("LRE_CODE").trim());
                            System.out.println(cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));

                            if (!rsl_expense1.getString("LRE_CODE").trim().equalsIgnoreCase("56") && !rsl_expense1.getString("LRE_CODE").trim().equalsIgnoreCase("57") && !rsl_expense1.getString("LRE_CODE").trim().equalsIgnoreCase("70")) {
                                mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            }

//                            if (rsl_expense.getString("LRE_CODE").trim().equalsIgnoreCase("70")) {
//                                mne.setField("WGAIT3", "COVID");
//                            }
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                    }

/////////////////////////
                    ResultSet rsl_line = cgd.LineReceiptnew(CONO, DIVI, arrRC);

                    while (rsl_line.next()) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
//                        mne.setField("WFPYNO", "TH28050014");
                        mne.setField("FCS", "WFCUCD");
                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());

                        System.out.println(rsl_line.getString("LR_INVNO").trim());
                        mne.pressKey(MNEProtocol.KeyEnter);

                        mne.setField("CMDTP", "LSTOPT");
                        mne.setField("CMDVAL", "11");
                        mne.setField("FCS", "WFCINO");
                        mne.setField("SELROWS", "R1");
                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());
                        mne.selectOption("11");
                        System.out.println(mne.getMsg());

                        if (mne.getMsg() != null) {
                            System.out.println(mne.getMsg());
                            mne.pressKey(MNEProtocol.KeyEnter);
                            //  System.out.println(RSL_GR.getString("HC_RCNO").trim());
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "F18");
                            mne.setField("FCS", "WFCINO");
                            mne.pressKey("F18");
                            mne.pressKey(MNEProtocol.KeyEnter);
                            mne.pressKey(MNEProtocol.KeyEnter);
//                            mne.closeProgram(ARS110);
                        }
                        System.out.println("mariooooooooooooooo");

                        if (mne.panel.equals("ARS110/H")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WHCUAM");
                            mne.setField("WHCUAM", rsl_line.getString("LR_REAMT").trim());
                            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                            System.out.println(rsl_line.getString("LR_REAMT").trim());
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                        System.out.println("mariooooooooooooooo");
                        System.out.println(rsl_line.getString("LR_INVNO").trim());

                        lastinv = rsl_line.getString("LR_INVNO").trim();
                    }

                    Double BL = cgd.Call_Balancenew(CONO, DIVI, arrRC);

                    //todo callexp
                    BL += cgd.Call_Expensenew(CONO, DIVI, FNNO.trim());
                    BL = cfm.Double2digitReturn(BL);
                    System.out.println("Luigi");
                    System.out.println(BL);
                    if (BL > 2) {
                        if (mne.panel.equals("ARS110/F1")) {

                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WFCINO", lastinv);

                            mne.setField("CMDTP", "LSTOPT");
                            mne.setField("CMDVAL", "12");
                            mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                            mne.setField("FCS", "WFCINO");
                            mne.setField("SELROWS", "R1");
                            mne.setField("WFCINO", lastinv);
                            mne.selectOption("12");

                        }

                        if (mne.panel.equals("ARS110/I")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WIAIT3");
                            mne.setField("WIAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.setField("WIAIT3", "NON");
                            mne.pressKey(MNEProtocol.KeyEnter);
                            System.out.println("marioooooooooocccccccccccccccooooo");

                        }

                    } else if ((BL <= 2 && BL >= -2) && BL != 0) {
//                    } else if (BL <= 2 && BL>0) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFSLOP", "23");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.panel.equals("ARS110/G1")) {

                            if (mne.getMsg() != null) {
                                mne.pressKey(MNEProtocol.KeyEnter);

                                mne.setField("CMDTP", "KEY");
                                mne.setField("CMDVAL", "ENTER");
                                mne.setField("FCS", "WGAIT2");

                                if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                                    mne.setField("WGCUAM", cfm.Double2digitReturn(BL));
                                }
                            } else {
                                mne.setField("CMDTP", "KEY");
                                mne.setField("CMDVAL", "ENTER");
                                mne.setField("FCS", "WGAIT2");

                                if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                                    mne.setField("WGCUAM", cfm.Double2digitReturn(BL * -1));
                                }
                            }

                            mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                    }
                    System.out.println("marioooooooooocccccccccccccccooooo");
                    System.out.println(mne.getMsg());
                    mne.setField("CMDTP", "KEY");
                    mne.setField("CMDVAL", "F3");
                    mne.pressKey(MNEProtocol.KeyF03);
                    System.out.println(mne.getMsg());

                    System.out.println("binnnnnnnnnnnnnnnngo");

                    if (mne.getMsg().trim().equalsIgnoreCase("Left to distribute does not equal 0")) {
                        System.out.println(mne.getMsg());
                        System.out.println(mne.panel);
                        mne.pressKey(MNEProtocol.KeyEnter);
                        System.out.println(RSL_GR.getString("HC_RCNO").trim());
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "F18");
                        mne.setField("FCS", "WFCINO");
                        mne.pressKey("F18");
                        System.out.println(mne.panel);

                        mne.pressKey(MNEProtocol.KeyEnter);
                        System.out.println(mne.getMsg());
                        System.out.println(mne.getMsg());
                        System.out.println(mne.panel);
                        mne.pressKey(MNEProtocol.KeyEnter);

                    }

                    if (mne.getMsg() != null) {
                        System.out.println(mne.getMsg());
                    }
                    String[] Vouchermss = mne.getMsg().toString().split(" ");
                    String Voucher = Vouchermss[2].trim();

                    if (Voucher.length() == 8) {

                        //todo exp
                        for (int counter = 0; counter < arrRC.size(); counter++) {

                            System.out.println("arrRC is : " + arrRC.get(counter));
                            csd.UpdataVouchernew(CONO, DIVI, arrRC.get(counter), Voucher, FNNO);

                        }
                    }

                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.panel);
                }

            }
            System.out.println("xxxxxxxyyyyyyyyyyyy");

            mne.closeProgram(ARS110);
            System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzdddddddddd");

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    ///////////////////////////// EXPENSE FULL //////////////////////////////////////////////
    public static void ARS110_TRANSFER_EXPENSE_FULL(int CONO, String DIVI, String RCNO, String TYPE) {
//        String LoginUrlConnectionm3 = "https://bkrmvxm3.bangkokranch.com:21008/mne/servlet/MvxMCSvt"; //PRD
        String LoginUrlConnectionm3 = GBVAR.LoginUrlConnectionm3;
        String LoginM3User = "MVXSECOFR";
        String LoginM3Password = "lawson@90";
        String LoginAppServer = "192.200.9.190";
        int LoginAppPort = GBVAR.DBPORT;
//        int LoginAppPort = 16305; // TST

        try {
            ClassGetData cgd = new ClassGetData();
            ClassSetData csd = new ClassSetData();
            int cono = CONO;
            String divi = DIVI.trim();
            String lastinv = "";

            MNEHelper mne = new MNEHelper(LoginAppServer, LoginAppPort, LoginUrlConnectionm3);
            mne.logInToM3(cono, divi, LoginM3User, LoginM3Password);

            if (!mne.logInToM3(cono, divi, LoginM3User, LoginM3Password)) {
                System.out.println("Can not login to M3 system");
            }

            String ARS110 = mne.runM3Pgm("ARS110");

            if ((ARS110).equals("")) {
                System.out.println(" ไม่สามารถเปิดโปรแกรม ARS110 ได้");
                mne.closeProgram(ARS110);
            }

            ResultSet RSL_GR = cgd.GetReceipt(CONO, DIVI, RCNO, TYPE);

            ResultSet RSL_FNC = cgd.GET_FNC_REF1(CONO, DIVI, RCNO);
            String FNC = "", REF1 = "";
            while (RSL_FNC.next()) {
                FNC = RSL_FNC.getString("RL_FNC").trim();
                REF1 = RSL_FNC.getString("RL_REF1").trim();
            }

            System.out.println("Running");
            ClassFormatData cfm = new ClassFormatData();

            if (mne.panel.equals("ARS110/B")) {
                mne.setField("CMDTP", "KEY");
                mne.setField("CMDVAL", "ENTER");
                mne.setField("FCS", "WAFNCN");
                mne.setField("WAFNCN", FNC);
                mne.pressKey(MNEProtocol.KeyEnter);
                System.out.println(FNC);

                mne.setField("CMDTP", "1"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                mne.setField("SELROWS", "R1"); //CMDTP
                mne.setField("FCS", "WAFNCN"); //CMDVAL ENTER
                mne.setField("WAFNCN", FNC); //FCS W1OBKV
//              mne.pressKey(MNEProtocol.KeyEnter);
                mne.selectOption("1");
                System.out.println(mne.getMsg());
                System.out.println(FNC);
            }

            while (RSL_GR.next()) {

                if (mne.panel.equals("ARS112/E")) {
                    // System.out.println("CRS610/E");
                    Double HC_REAMT = RSL_GR.getDouble("HC_REAMT");
                    System.out.println(HC_REAMT);

/////
                    if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                        System.out.println("CASE BANK CHARGE");
                        HC_REAMT = HC_REAMT - RSL_GR.getDouble("HR_BKCHG");
                    }
/////

                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("FCS", "WEAIT6"); //
                    mne.setField("WEAIT1", RSL_GR.getString("HC_ACCODE").trim()); // ACCOUNT CODE
                    System.out.println(RSL_GR.getString("HC_ACCODE").trim());
                    mne.setField("WECUAM", String.format("%.2f", HC_REAMT)); //amt
                    System.out.println(String.format("%.2f", HC_REAMT));
                    mne.setField("WEACDT", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //
                    mne.setField("WEDTP5", cfm.SubDateForM3(RSL_GR.getString("HC_PYDT"))); //
                    mne.setField("WEAIT4", REF1); //ACC date
                    System.out.println(REF1);

                    mne.setField("WEAIT5", cfm.SubDateForM3(RSL_GR.getString("HC_PYDT"))); //
                    System.out.println(cfm.SubDateForM3(RSL_GR.getString("HC_TRDT")));
                    System.out.println(cfm.SubDateForM3(RSL_GR.getString("HC_PYDT")));
                    mne.setField("WEAIT6", RSL_GR.getString("HC_RCNO").trim()); //
                    System.out.println(RSL_GR.getString("HC_RCNO").trim());

                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.getMsg() + "ERRRRR");
                    // JOptionPane.showMessageDialog(null, mne.getMsg());

                }
                if (mne.panel.equals("ARS110/F1")) {
                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim()); //
                    System.out.println(RSL_GR.getString("HC_PYNO").trim());
                    mne.setField("FCS", "WFPYNO"); //
                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(RSL_GR.getDouble("HR_BKCHG"));

                    ////BANK CHARGE
                    System.out.println("barrrrrrrrrrrrrrrrr");
                    if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {

                        System.out.println("ffffffffffffffuuuuuuuuuuuuuuuuu");
                        mne.setField("CMDTP", "KEY"); //CMDTP KEY
                        mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                        mne.setField("WFSLOP", "21");
                        mne.setField("FCS", "WFSLOP");
                        mne.setField("WFQTTP", "1");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.getMsg() != null) {
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                        if (mne.panel.equals("ARS110/G1")) {
                            mne.setField("CMDTP", "KEY"); //CMDTP KEY
                            mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WGCUAM", RSL_GR.getString("HR_BKCHG").trim());
                            mne.setField("WGAIT7", "ARS110");
                            mne.setField("FCS", "WGCUAM");
                            mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                    }
////

//////// EXPENSE ////////
                    ResultSet rsl_expense1 = cgd.LineExpensenew(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim());
                    ResultSet rsl_expense2 = cgd.LineExpensenew(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim());

                    while (rsl_expense1.next()) {

                        String LRE_AMT = rsl_expense1.getString("LRE_AMT").trim();
                        String LRE_ACTCODE = rsl_expense1.getString("LRE_ACTCODE").trim();
                        String LRE_CODE = rsl_expense1.getString("LRE_CODE").trim();

                        mne.setField("CMDTP", "KEY"); //CMDTP KEY
                        mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());

                        mne.setField("WFSLOP", rsl_expense1.getString("RE_OPTION").trim());
                        System.out.println(RSL_GR.getString("HC_PYNO").trim());
                        System.out.println(rsl_expense1.getString("RE_OPTION").trim());

                        mne.setField("FCS", "WFSLOP");
                        mne.setField("WFQTTP", "1");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.getMsg() != null) {
                            System.out.println("************* mne.getMsg() *************");
                            System.out.println(mne.getMsg());

                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                        System.out.println("************* ROUND 0 *************");
                        System.out.println(LRE_AMT);
                        System.out.println(LRE_ACTCODE);
                        System.out.println("**********************************");

                        if (mne.panel.equals("ARS110/G1")) {
                            System.out.println("************* ROUND 1 *************");
                            System.out.println(LRE_AMT);
                            System.out.println(LRE_ACTCODE);

//                            System.out.println(rsl_expense1.getString("LRE_AMT").trim());
//                            System.out.println(rsl_expense1.getString("LRE_ACTCODE").trim());
                            System.out.println("**********************************");

                            mne.setField("CMDTP", "KEY"); //CMDTP KEY
                            mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                            mne.setField("CMDVAL", "ENTER");
                            System.out.println("************* ROUND 1.5 *************");
                            System.out.println(LRE_AMT);
                            System.out.println("**********************************");

                            mne.setField("WGCUAM", LRE_AMT);

                            mne.setField("WGAIT1", LRE_ACTCODE);
                            mne.setField("WGAIT4", cgd.Expense_CustomerName(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.setField("WGVTXT", cgd.Expense_text(CONO, DIVI, LRE_CODE));
                            System.out.println("************* mne.getMsg() *************");
                            System.out.println(mne.getMsg());
                            System.out.println("************* ROUND 2 *************");
                            System.out.println(LRE_AMT);
                            System.out.println(LRE_ACTCODE);
                            System.out.println(cgd.Expense_CustomerName(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            System.out.println(cgd.Expense_text(CONO, DIVI, LRE_CODE));

//                            System.out.println(rsl_expense1.getString("LRE_AMT").trim());
//                            System.out.println(rsl_expense1.getString("LRE_ACTCODE").trim());
                            System.out.println("**********************************");

//                            mne.setField("WGAIT4", "");
//                            mne.setField("WGVTXT", "");
                            System.out.println(rsl_expense1.getString("LRE_AMT").trim());
                            System.out.println(rsl_expense1.getString("LRE_ACTCODE").trim());
//                            System.out.println(cgd.Expense_CustomerName(CONO, RSL_GR.getString("HC_PYNO")));
//                            System.out.println(cgd.Expense_text(CONO, DIVI, rsl_expense1.getString("LRE_CODE").trim()));

                            mne.setField("FCS", "WGCUAM");

                            System.out.println(rsl_expense1.getString("LRE_CODE").trim());
                            System.out.println(rsl_expense1.getString("LRE_CODE").trim());
                            System.out.println(cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));

                            if (!rsl_expense1.getString("LRE_CODE").trim().equalsIgnoreCase("56") && !rsl_expense1.getString("LRE_CODE").trim().equalsIgnoreCase("57") && !rsl_expense1.getString("LRE_CODE").trim().equalsIgnoreCase("70")) {
                                mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            }

//                            if (rsl_expense.getString("LRE_CODE").trim().equalsIgnoreCase("70")) {
//                                mne.setField("WGAIT3", "COVID");
//                            }
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                    }

/////////////////////////
                    ResultSet rsl_line = cgd.LineReceipt(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim());

                    while (rsl_line.next()) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
//                        mne.setField("WFPYNO", "TH28050014");
                        mne.setField("FCS", "WFCUCD");
                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());

                        System.out.println(rsl_line.getString("LR_INVNO").trim());
                        mne.pressKey(MNEProtocol.KeyEnter);

                        mne.setField("CMDTP", "LSTOPT");
                        mne.setField("CMDVAL", "11");
                        mne.setField("FCS", "WFCINO");
                        mne.setField("SELROWS", "R1");
                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());
                        mne.selectOption("11");
                        System.out.println(mne.getMsg());

                        if (mne.getMsg() != null) {
                            System.out.println(mne.getMsg());
                            mne.pressKey(MNEProtocol.KeyEnter);
                            System.out.println(RSL_GR.getString("HC_RCNO").trim());
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "F18");
                            mne.setField("FCS", "WFCINO");
                            mne.pressKey("F18");
                            mne.pressKey(MNEProtocol.KeyEnter);
                            mne.pressKey(MNEProtocol.KeyEnter);
//                            mne.closeProgram(ARS110);
                        }
                        System.out.println("mariooooooooooooooo");

                        if (mne.panel.equals("ARS110/H")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WHCUAM");
                            mne.setField("WHCUAM", rsl_line.getString("LR_REAMT").trim());
                            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                            System.out.println(rsl_line.getString("LR_REAMT").trim());
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                        System.out.println("mariooooooooooooooo");
                        System.out.println(rsl_line.getString("LR_INVNO").trim());

                        lastinv = rsl_line.getString("LR_INVNO").trim();
                    }

                    Double BL = cgd.Call_Balance(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim());
                    BL += cgd.Call_Expense(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim());
                    BL = cfm.Double2digitReturn(BL);
                    System.out.println("Luigi1");
                    System.out.println(BL);
                    if (BL > 2) {
                        if (mne.panel.equals("ARS110/F1")) {

                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WFCINO", lastinv);

                            mne.setField("CMDTP", "LSTOPT");
                            mne.setField("CMDVAL", "12");
                            mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                            mne.setField("FCS", "WFCINO");
                            mne.setField("SELROWS", "R1");
                            mne.setField("WFCINO", lastinv);
                            mne.selectOption("12");

                        }

                        if (mne.panel.equals("ARS110/I")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WIAIT3");
                            mne.setField("WIAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.setField("WIAIT3", "NON");
                            mne.pressKey(MNEProtocol.KeyEnter);
                            System.out.println("marioooooooooocccccccccccccccooooo");

                        }

                    } else if ((BL <= 2 && BL >= -2) && BL != 0) {
//                    } else if (BL <= 2 && BL>0) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFSLOP", "23");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.panel.equals("ARS110/G1")) {

                            if (mne.getMsg() != null) {
                                mne.pressKey(MNEProtocol.KeyEnter);

                                mne.setField("CMDTP", "KEY");
                                mne.setField("CMDVAL", "ENTER");
                                mne.setField("FCS", "WGAIT2");

                                if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                                    mne.setField("WGCUAM", cfm.Double2digitReturn(BL));
                                }
                            } else {
                                mne.setField("CMDTP", "KEY");
                                mne.setField("CMDVAL", "ENTER");
                                mne.setField("FCS", "WGAIT2");

                                if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                                    mne.setField("WGCUAM", cfm.Double2digitReturn(BL * -1));
                                }
                            }

                            mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                    }
                    System.out.println("marioooooooooocccccccccccccccooooo");
                    System.out.println(mne.getMsg());
                    mne.setField("CMDTP", "KEY");
                    mne.setField("CMDVAL", "F3");
                    mne.pressKey(MNEProtocol.KeyF03);
                    System.out.println(mne.getMsg());

                    System.out.println("binnnnnnnnnnnnnnnngo");

                    if (mne.getMsg().trim().equalsIgnoreCase("Left to distribute does not equal 0")) {
                        System.out.println(mne.getMsg());
                        System.out.println(mne.panel);
                        mne.pressKey(MNEProtocol.KeyEnter);
                        System.out.println(RSL_GR.getString("HC_RCNO").trim());
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "F18");
                        mne.setField("FCS", "WFCINO");
                        mne.pressKey("F18");
                        System.out.println(mne.panel);

                        mne.pressKey(MNEProtocol.KeyEnter);
                        System.out.println(mne.getMsg());
                        System.out.println(mne.getMsg());
                        System.out.println(mne.panel);
                        mne.pressKey(MNEProtocol.KeyEnter);

                    }

                    if (mne.getMsg() != null) {
                        System.out.println(mne.getMsg());
                    }
                    String[] Vouchermss = mne.getMsg().toString().split(" ");
                    String Voucher = Vouchermss[2].trim();

                    if (Voucher.length() == 8) {
                        //todo exp 

//                        csd.UpdataVoucher(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim(), Voucher);
                    }

                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.panel);
                }

            }
            System.out.println("xxxxxxxyyyyyyyyyyyy");

            mne.closeProgram(ARS110);
            System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzdddddddddd");

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public static void ARS110_TRANSFER_EXPENSE_PARTAILnew(int CONO, String DIVI, ArrayList<String> arrRC, String TYPE, String FNNO) {
//        String LoginUrlConnectionm3 = "https://bkrmvxm3.bangkokranch.com:21008/mne/servlet/MvxMCSvt"; //PRD
        String LoginUrlConnectionm3 = GBVAR.LoginUrlConnectionm3;
        String LoginM3User = "MVXSECOFR";
        String LoginM3Password = "lawson@90";
        String LoginAppServer = "192.200.9.190";
        int LoginAppPort = GBVAR.DBPORT;
//        int LoginAppPort = 16305; // TST

        System.out.println("PARTAIL IN ");
        try {
            ClassGetData cgd = new ClassGetData();
            ClassSetData csd = new ClassSetData();
            int cono = CONO;
            String divi = DIVI.trim();
            String lastinv = "";

            MNEHelper mne = new MNEHelper(LoginAppServer, LoginAppPort, LoginUrlConnectionm3);
            mne.logInToM3(cono, divi, LoginM3User, LoginM3Password);

            if (!mne.logInToM3(cono, divi, LoginM3User, LoginM3Password)) {
                System.out.println("Can not login to M3 system");
            }

            String ARS110 = mne.runM3Pgm("ARS110");

            if ((ARS110).equals("")) {
                System.out.println(" ไม่สามารถเปิดโปรแกรม ARS110 ได้");
                mne.closeProgram(ARS110);
            }

            ResultSet RSL_GR = cgd.GetReceiptnew4(CONO, DIVI, arrRC.get(0), TYPE);
            ResultSet RSL_FNC = cgd.GET_FNC_REF1(CONO, DIVI, arrRC.get(0));

            String FNC = "", REF1 = "";
            while (RSL_FNC.next()) {
                FNC = RSL_FNC.getString("RL_FNC").trim();
                REF1 = RSL_FNC.getString("RL_REF1").trim();
            }

            System.out.println("Running");
            ClassFormatData cfm = new ClassFormatData();

            if (mne.panel.equals("ARS110/B")) {
                mne.setField("CMDTP", "KEY");
                mne.setField("CMDVAL", "ENTER");
                mne.setField("FCS", "WAFNCN");
                mne.setField("WAFNCN", FNC);
                mne.pressKey(MNEProtocol.KeyEnter);

                mne.setField("CMDTP", "1"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                mne.setField("SELROWS", "R1"); //CMDTP
                mne.setField("FCS", "WAFNCN"); //CMDVAL ENTER
                mne.setField("WAFNCN", FNC); //FCS W1OBKV
//              mne.pressKey(MNEProtocol.KeyEnter);
                mne.selectOption("1");
                System.out.println(mne.getMsg());
            }

            while (RSL_GR.next()) {

                if (mne.panel.equals("ARS112/E")) {
                    // System.out.println("CRS610/E");
                    Double HC_REAMT = RSL_GR.getDouble("HC_REAMT");

//                    HC_REAMT -= cgd.Call_Expense(CONO,divi,RSL_GR.getString("HC_RCNO").trim());
/////
                    if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                        System.out.println("CASE BANK CHARGE");
                        HC_REAMT = HC_REAMT - RSL_GR.getDouble("HR_BKCHG");
                    }
/////
//                    System.out.println(HC_REAMT);

                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("FCS", "WEAIT6"); //
                    mne.setField("WEAIT1", RSL_GR.getString("HC_ACCODE").trim()); // ACCOUNT CODE

                    mne.setField("WECUAM", String.format("%.2f", HC_REAMT)); //amt
                    mne.setField("WEACDT", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //
                    mne.setField("WEDTP5", cfm.SubDateForM3(RSL_GR.getString("HC_PYDT"))); //
                    mne.setField("WEAIT4", REF1); //ACC date
                    mne.setField("WEAIT5", cfm.SubDateForM3(RSL_GR.getString("HC_PYDT"))); //
                    mne.setField("WEAIT6", FNNO); //

                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.getMsg());
                    // JOptionPane.showMessageDialog(null, mne.getMsg());

                }

                //////////////////  AR   ///////////////////////////////////             
                if (mne.panel.equals("ARS110/F1")) {
                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim()); //
                    mne.setField("FCS", "WFPYNO"); //
                    mne.pressKey(MNEProtocol.KeyEnter);

                    ////BANK CHARGE
                    if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {

                        mne.setField("CMDTP", "KEY"); //CMDTP KEY
                        mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                        mne.setField("WFSLOP", "21");
                        mne.setField("FCS", "WFSLOP");
                        mne.setField("WFQTTP", "1");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.getMsg() != null) {
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                        if (mne.panel.equals("ARS110/G1")) {
                            mne.setField("CMDTP", "KEY"); //CMDTP KEY
                            mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WGCUAM", RSL_GR.getString("HR_BKCHG").trim());
                            mne.setField("WGAIT7", "ARS110");
                            mne.setField("FCS", "WGCUAM");
                            mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                    }
////
                    ResultSet rsl_line = cgd.LineReceiptnew(CONO, DIVI, arrRC);
                    while (rsl_line.next()) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
//                        mne.setField("WFPYNO", "TH28050014");
                        mne.setField("FCS", "WFCUCD");
                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());
                        mne.pressKey(MNEProtocol.KeyEnter);

                        mne.setField("CMDTP", "LSTOPT");
                        mne.setField("CMDVAL", "11");
                        mne.setField("FCS", "WFCINO");
                        mne.setField("SELROWS", "R1");
                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());
                        mne.selectOption("11");
                        System.out.println(mne.getMsg());

                        if (mne.getMsg() != null) {
                            System.out.println(mne.getMsg());
                            mne.pressKey(MNEProtocol.KeyEnter);
                            System.out.println(RSL_GR.getString("HC_RCNO").trim());
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "F18");
                            mne.setField("FCS", "WFCINO");
                            mne.pressKey("F18");
                            mne.pressKey(MNEProtocol.KeyEnter);
                            mne.pressKey(MNEProtocol.KeyEnter);
//                            mne.closeProgram(ARS110);
                        }

                        if (mne.panel.equals("ARS110/H")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WHCUAM");
                            mne.setField("WHCUAM", rsl_line.getString("LR_REAMT").trim());
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                        lastinv = rsl_line.getString("LR_INVNO").trim();
                    }

                    Double BL = cgd.Call_Balancenew(CONO, DIVI, arrRC);

//                                         SH ////
//                    mne.setField("CMDTP", "KEY");
//                    mne.setField("CMDVAL", "ENTER");
//                    mne.setField("WFSLOP", "23");
//                    mne.pressKey(MNEProtocol.KeyEnter);
//
//                    if (mne.panel.equals("ARS110/G1")) {
//                        if (mne.getMsg() != null) {
//                            mne.pressKey(MNEProtocol.KeyEnter);
//                            mne.setField("CMDTP", "KEY");
//                            mne.setField("CMDVAL", "ENTER");
//                            mne.setField("FCS", "WGAIT2");
//
//                        } else {
//                            mne.setField("CMDTP", "KEY");
//                            mne.setField("CMDVAL", "ENTER");
//                            mne.setField("FCS", "WGAIT2");
//                        }
//                        mne.setField("WIAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
//                        mne.pressKey(MNEProtocol.KeyEnter);
//                    }
/// SH ////
                    if (BL > 2) {
                        if (mne.panel.equals("ARS110/F1")) {

                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WFCINO", lastinv);

                            mne.setField("CMDTP", "LSTOPT");
                            mne.setField("CMDVAL", "12");
                            mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                            mne.setField("FCS", "WFCINO");
                            mne.setField("SELROWS", "R1");
                            mne.setField("WFCINO", lastinv);
                            mne.selectOption("12");

                        }

                        if (mne.panel.equals("ARS110/I")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WIAIT3");
                            mne.setField("WIAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.setField("WIAIT3", "NON");
                            mne.pressKey(MNEProtocol.KeyEnter);

                        }

                    } else if ((BL <= 2 && BL >= -2) && BL != 0) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFSLOP", "23");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.panel.equals("ARS110/G1")) {

                            if (mne.getMsg() != null) {
                                mne.pressKey(MNEProtocol.KeyEnter);

                                mne.setField("CMDTP", "KEY");
                                mne.setField("CMDVAL", "ENTER");
                                mne.setField("FCS", "WGAIT2");

                                if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                                    mne.setField("WGCUAM", cfm.Double2digitReturn(BL));
                                }
                            } else {
                                mne.setField("CMDTP", "KEY");
                                mne.setField("CMDVAL", "ENTER");
                                mne.setField("FCS", "WGAIT2");

                                if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                                    mne.setField("WGCUAM", cfm.Double2digitReturn(BL * -1));
                                }
                            }

                            mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                    }
                    mne.setField("CMDTP", "KEY");
                    mne.setField("CMDVAL", "F3");
                    mne.pressKey(MNEProtocol.KeyF03);

                    if (mne.getMsg().trim().equalsIgnoreCase("Left to distribute does not equal 0")) {
                        System.out.println(mne.getMsg());
                        mne.pressKey(MNEProtocol.KeyEnter);
                        System.out.println(RSL_GR.getString("HC_RCNO").trim());
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "F18");
                        mne.setField("FCS", "WFCINO");
                        mne.pressKey("F18");
                        mne.pressKey(MNEProtocol.KeyEnter);
                        mne.pressKey(MNEProtocol.KeyEnter);
                    }

                    if (mne.getMsg() != null) {
                        System.out.println(mne.getMsg());
                    }
                    String[] Vouchermss = mne.getMsg().toString().split(" ");
                    String Voucher = Vouchermss[2].trim();

                    if (Voucher.length() == 8) {
                        for (int counter = 0; counter < arrRC.size(); counter++) {

                            System.out.println("arrRC is : " + arrRC.get(counter));
                            csd.UpdataVouchernew(CONO, DIVI, arrRC.get(counter), Voucher, FNNO);

                        }
                        csd.InsertVoucherExpensenew(CONO, DIVI, FNNO.trim(), Voucher);
//                        csd.UpdataVoucher(CONO, DIVI, RCNO.trim(), Voucher);
//                        csd.InsertVoucherExpense(CONO, DIVI, RCNO.trim(), Voucher);
                    }
                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.panel);
                }

//////////////////////////////////////
            }

            mne.closeProgram(ARS110);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public static void ARS110_TRANSFER_EXPENSE_PARTAIL(int CONO, String DIVI, String RCNO, String TYPE) {
//        String LoginUrlConnectionm3 = "https://bkrmvxm3.bangkokranch.com:21008/mne/servlet/MvxMCSvt"; //PRD
        String LoginUrlConnectionm3 = GBVAR.LoginUrlConnectionm3;
        String LoginM3User = "MVXSECOFR";
        String LoginM3Password = "lawson@90";
        String LoginAppServer = "192.200.9.190";
        int LoginAppPort = GBVAR.DBPORT;
//        int LoginAppPort = 16305; // TST

        try {
            ClassGetData cgd = new ClassGetData();
            ClassSetData csd = new ClassSetData();
            int cono = CONO;
            String divi = DIVI.trim();
            String lastinv = "";

            MNEHelper mne = new MNEHelper(LoginAppServer, LoginAppPort, LoginUrlConnectionm3);
            mne.logInToM3(cono, divi, LoginM3User, LoginM3Password);

            if (!mne.logInToM3(cono, divi, LoginM3User, LoginM3Password)) {
                System.out.println("Can not login to M3 system");
            }

            String ARS110 = mne.runM3Pgm("ARS110");

            if ((ARS110).equals("")) {
                System.out.println(" ไม่สามารถเปิดโปรแกรม ARS110 ได้");
                mne.closeProgram(ARS110);
            }

            ResultSet RSL_GR = cgd.GetReceipt(CONO, DIVI, RCNO, TYPE);

            ResultSet RSL_FNC = cgd.GET_FNC_REF1(CONO, DIVI, RCNO);
            String FNC = "", REF1 = "";
            while (RSL_FNC.next()) {
                FNC = RSL_FNC.getString("RL_FNC").trim();
                REF1 = RSL_FNC.getString("RL_REF1").trim();
            }

            System.out.println("Running");
            ClassFormatData cfm = new ClassFormatData();

            if (mne.panel.equals("ARS110/B")) {
                mne.setField("CMDTP", "KEY");
                mne.setField("CMDVAL", "ENTER");
                mne.setField("FCS", "WAFNCN");
                mne.setField("WAFNCN", FNC);
                mne.pressKey(MNEProtocol.KeyEnter);

                mne.setField("CMDTP", "1"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                mne.setField("SELROWS", "R1"); //CMDTP
                mne.setField("FCS", "WAFNCN"); //CMDVAL ENTER
                mne.setField("WAFNCN", FNC); //FCS W1OBKV
//              mne.pressKey(MNEProtocol.KeyEnter);
                mne.selectOption("1");
                System.out.println(mne.getMsg());
            }

            while (RSL_GR.next()) {

                if (mne.panel.equals("ARS112/E")) {
                    // System.out.println("CRS610/E");
                    Double HC_REAMT = RSL_GR.getDouble("HC_REAMT");

//                    HC_REAMT -= cgd.Call_Expense(CONO,divi,RSL_GR.getString("HC_RCNO").trim());
/////
                    if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                        System.out.println("CASE BANK CHARGE");
                        HC_REAMT = HC_REAMT - RSL_GR.getDouble("HR_BKCHG");
                    }
/////
//                    System.out.println(HC_REAMT);

                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("FCS", "WEAIT6"); //
                    mne.setField("WEAIT1", RSL_GR.getString("HC_ACCODE").trim()); // ACCOUNT CODE

                    mne.setField("WECUAM", String.format("%.2f", HC_REAMT)); //amt
                    mne.setField("WEACDT", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //
                    mne.setField("WEDTP5", cfm.SubDateForM3(RSL_GR.getString("HC_PYDT"))); //
                    mne.setField("WEAIT4", REF1); //ACC date
                    mne.setField("WEAIT5", cfm.SubDateForM3(RSL_GR.getString("HC_PYDT"))); //
                    mne.setField("WEAIT6", RSL_GR.getString("HC_RCNO").trim()); //

                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.getMsg());
                    // JOptionPane.showMessageDialog(null, mne.getMsg());

                }
                if (mne.panel.equals("ARS110/F1")) {
                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim()); //
                    mne.setField("FCS", "WFPYNO"); //
                    mne.pressKey(MNEProtocol.KeyEnter);

                    ////BANK CHARGE
                    if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {

                        mne.setField("CMDTP", "KEY"); //CMDTP KEY
                        mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                        mne.setField("WFSLOP", "21");
                        mne.setField("FCS", "WFSLOP");
                        mne.setField("WFQTTP", "1");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.getMsg() != null) {
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                        if (mne.panel.equals("ARS110/G1")) {
                            mne.setField("CMDTP", "KEY"); //CMDTP KEY
                            mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WGCUAM", RSL_GR.getString("HR_BKCHG").trim());
                            mne.setField("WGAIT7", "ARS110");
                            mne.setField("FCS", "WGCUAM");
                            mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                    }
////

                    ResultSet rsl_line = cgd.LineReceipt(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim());
                    while (rsl_line.next()) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
//                        mne.setField("WFPYNO", "TH28050014");
                        mne.setField("FCS", "WFCUCD");
                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());
                        mne.pressKey(MNEProtocol.KeyEnter);

                        mne.setField("CMDTP", "LSTOPT");
                        mne.setField("CMDVAL", "11");
                        mne.setField("FCS", "WFCINO");
                        mne.setField("SELROWS", "R1");
                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());
                        mne.selectOption("11");
                        System.out.println(mne.getMsg());

                        if (mne.getMsg() != null) {
                            System.out.println(mne.getMsg());
                            mne.pressKey(MNEProtocol.KeyEnter);
                            System.out.println(RSL_GR.getString("HC_RCNO").trim());
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "F18");
                            mne.setField("FCS", "WFCINO");
                            mne.pressKey("F18");
                            mne.pressKey(MNEProtocol.KeyEnter);
                            mne.pressKey(MNEProtocol.KeyEnter);
//                            mne.closeProgram(ARS110);
                        }

                        if (mne.panel.equals("ARS110/H")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WHCUAM");
                            mne.setField("WHCUAM", rsl_line.getString("LR_REAMT").trim());
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                        lastinv = rsl_line.getString("LR_INVNO").trim();
                    }

                    Double BL = cgd.Call_Balance(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim());

//                                         SH ////
//                    mne.setField("CMDTP", "KEY");
//                    mne.setField("CMDVAL", "ENTER");
//                    mne.setField("WFSLOP", "23");
//                    mne.pressKey(MNEProtocol.KeyEnter);
//
//                    if (mne.panel.equals("ARS110/G1")) {
//                        if (mne.getMsg() != null) {
//                            mne.pressKey(MNEProtocol.KeyEnter);
//                            mne.setField("CMDTP", "KEY");
//                            mne.setField("CMDVAL", "ENTER");
//                            mne.setField("FCS", "WGAIT2");
//
//                        } else {
//                            mne.setField("CMDTP", "KEY");
//                            mne.setField("CMDVAL", "ENTER");
//                            mne.setField("FCS", "WGAIT2");
//                        }
//                        mne.setField("WIAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
//                        mne.pressKey(MNEProtocol.KeyEnter);
//                    }
/// SH ////
                    if (BL > 2) {
                        if (mne.panel.equals("ARS110/F1")) {

                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WFCINO", lastinv);

                            mne.setField("CMDTP", "LSTOPT");
                            mne.setField("CMDVAL", "12");
                            mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                            mne.setField("FCS", "WFCINO");
                            mne.setField("SELROWS", "R1");
                            mne.setField("WFCINO", lastinv);
                            mne.selectOption("12");

                        }

                        if (mne.panel.equals("ARS110/I")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WIAIT3");
                            mne.setField("WIAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.setField("WIAIT3", "NON");
                            mne.pressKey(MNEProtocol.KeyEnter);

                        }

                    } else if ((BL <= 2 && BL >= -2) && BL != 0) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFSLOP", "23");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.panel.equals("ARS110/G1")) {

                            if (mne.getMsg() != null) {
                                mne.pressKey(MNEProtocol.KeyEnter);

                                mne.setField("CMDTP", "KEY");
                                mne.setField("CMDVAL", "ENTER");
                                mne.setField("FCS", "WGAIT2");

                                if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                                    mne.setField("WGCUAM", cfm.Double2digitReturn(BL));
                                }
                            } else {
                                mne.setField("CMDTP", "KEY");
                                mne.setField("CMDVAL", "ENTER");
                                mne.setField("FCS", "WGAIT2");

                                if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                                    mne.setField("WGCUAM", cfm.Double2digitReturn(BL));
                                }
                            }

                            mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                    }
                    mne.setField("CMDTP", "KEY");
                    mne.setField("CMDVAL", "F3");
                    mne.pressKey(MNEProtocol.KeyF03);

                    if (mne.getMsg().trim().equalsIgnoreCase("Left to distribute does not equal 0")) {
                        System.out.println(mne.getMsg());
                        mne.pressKey(MNEProtocol.KeyEnter);
                        System.out.println(RSL_GR.getString("HC_RCNO").trim());
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "F18");
                        mne.setField("FCS", "WFCINO");
                        mne.pressKey("F18");
                        mne.pressKey(MNEProtocol.KeyEnter);
                        mne.pressKey(MNEProtocol.KeyEnter);
                    }

                    if (mne.getMsg() != null) {
                        System.out.println(mne.getMsg());
                    }
                    String[] Vouchermss = mne.getMsg().toString().split(" ");
                    String Voucher = Vouchermss[2].trim();

                    if (Voucher.length() == 8) {
                        csd.UpdataVoucher(CONO, DIVI, RCNO.trim(), Voucher);
                        csd.InsertVoucherExpense(CONO, DIVI, RCNO.trim(), Voucher);
                    }
                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.panel);
                }

            }

            mne.closeProgram(ARS110);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public static void ARS110_TRANSFER_CLEAR_EXPENSEV2new(int CONO, String DIVI, ArrayList<String> arrRC, String TYPE, String FNNO) {
        String LoginUrlConnectionm3 = GBVAR.LoginUrlConnectionm3;
        String LoginM3User = "MVXSECOFR";
        String LoginM3Password = "lawson@90";
        String LoginAppServer = "192.200.9.190";
        int LoginAppPort = GBVAR.DBPORT;
//        int LoginAppPort = 16305; // TST

        try {
            ClassGetData cgd = new ClassGetData();
            ClassSetData csd = new ClassSetData();
            ClassFormatData cfd = new ClassFormatData();
            int cono = CONO;
            String divi = DIVI.trim();
            String lastinv = "";

            MNEHelper mne = new MNEHelper(LoginAppServer, LoginAppPort, LoginUrlConnectionm3);
            mne.logInToM3(cono, divi, LoginM3User, LoginM3Password);

            if (!mne.logInToM3(cono, divi, LoginM3User, LoginM3Password)) {
                System.out.println("Can not login to M3 system");
            }

            String ARS110 = mne.runM3Pgm("ARS110");

            if ((ARS110).equals("")) {
                System.out.println(" ไม่สามารถเปิดโปรแกรม ARS110 ได้");
                mne.closeProgram(ARS110);
            }

            ResultSet RSL_GR = cgd.GetReceiptnew3(CONO, DIVI, arrRC.get(0), TYPE);
            ResultSet RSL_FNC = cgd.GET_FNC_REF1(CONO, DIVI, arrRC.get(0));

            String FNC = "", REF1 = "";
            while (RSL_FNC.next()) {
                FNC = RSL_FNC.getString("RL_FNC").trim();
                REF1 = RSL_FNC.getString("RL_REF1").trim();
            }
            System.out.println("Running");
            ClassFormatData cfm = new ClassFormatData();

            if (mne.panel.equals("ARS110/B")) {
                mne.setField("CMDTP", "KEY");
                mne.setField("CMDVAL", "ENTER");
                mne.setField("FCS", "WAFNCN");
                mne.setField("WAFNCN", FNC);
                mne.pressKey(MNEProtocol.KeyEnter);

                mne.setField("CMDTP", "1"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                mne.setField("SELROWS", "R1"); //CMDTP
                mne.setField("FCS", "WAFNCN"); //CMDVAL ENTER
                mne.setField("WAFNCN", FNC); //FCS W1OBKV
//              mne.pressKey(MNEProtocol.KeyEnter);
                mne.selectOption("1");
//                System.out.println(mne.getMsg());
            }

            while (RSL_GR.next()) {

                if (mne.panel.equals("ARS112/E")) {
                    // System.out.println("CRS610/E");
                    Double HC_REAMT = 0.00;

                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("FCS", "WEAIT6"); //
                    mne.setField("WEAIT1", RSL_GR.getString("HC_ACCODE").trim()); // ACCOUNT CODE

                    mne.setField("WECUAM", HC_REAMT); //amt
                    System.out.println(HC_REAMT);
                    System.out.println("ZZ");

//                    mne.setField("WEACDT", "311022"); // Accounting Date
//                    System.out.println("311022");
                    mne.setField("WEDTP5", cfm.SubDateForM3(RSL_GR.getString("HC_PYDT"))); //
                    mne.setField("WEAIT4", REF1); //ACC date
                    mne.setField("WEAIT5", cfm.SubDateForM3(RSL_GR.getString("HC_PYDT"))); //
                    mne.setField("WEAIT6", FNNO); //

                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.getMsg());
                    // JOptionPane.showMessageDialog(null, mne.getMsg());

                }
                if (mne.panel.equals("ARS110/F1")) {
                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim()); //
                    mne.setField("FCS", "WFPYNO"); //
                    mne.pressKey(MNEProtocol.KeyEnter);

//////// EXPENSE ////////
                    ResultSet rsl_expense = cgd.LineExpensenew(CONO, DIVI, FNNO);
                    while (rsl_expense.next()) {

                        System.out.println("Check value-----------xx-------------------");
                        System.out.println(rsl_expense.getString("LRE_AMT").trim());
                        System.out.println("Check value----------xx--------------------");

                        mne.setField("CMDTP", "KEY"); //CMDTP KEY
                        mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                        mne.setField("WFSLOP", rsl_expense.getString("RE_OPTION").trim());
                        mne.setField("FCS", "WFSLOP");
                        mne.setField("WFQTTP", "1");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.getMsg() != null) {
                            System.out.println("Checknull " + mne.panel);
                            System.out.println(rsl_expense.getString("LRE_AMT").trim());
                            System.out.println(mne.getMsg());
                            mne.pressKey(MNEProtocol.KeyEnter);

                        } else {
                            System.out.println("isnull " + mne.panel);
                        }

                        if (mne.panel.equals("ARS110/G1")) {

                            System.out.println(rsl_expense.getString("LRE_AMT").trim());
                            mne.setField("CMDTP", "KEY"); //CMDTP KEY
                            mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WGCUAM", rsl_expense.getString("LRE_AMT").trim());
                            System.out.println("Check value------------------------------");
                            System.out.println(rsl_expense.getString("LRE_AMT").trim());
                            System.out.println("Check value------------------------------");

                            mne.setField("WGAIT1", rsl_expense.getString("LRE_ACTCODE").trim());
                            mne.setField("WGAIT4", cgd.Expense_CustomerName(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.setField("WGVTXT", cgd.Expense_text(CONO, DIVI, rsl_expense.getString("LRE_CODE").trim()));

                            mne.setField("FCS", "WGCUAM");
                            if (!rsl_expense.getString("LRE_CODE").trim().equalsIgnoreCase("56") && !rsl_expense.getString("LRE_CODE").trim().equalsIgnoreCase("57") && !rsl_expense.getString("LRE_CODE").trim().equalsIgnoreCase("70")) {
                                mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            }
// S503   S601
//                            if (rsl_expense.getString("LRE_CODE").trim().equalsIgnoreCase("70")) {
//                                mne.setField("WGAIT3", "COVID");
//                            }

                            mne.pressKey(MNEProtocol.KeyEnter);

                        }

                    }
                    System.out.println("Check " + mne.panel);
                    System.out.println(mne.getMsg());

                    ResultSet rsl_line = cgd.LineClearExpensenew(CONO, DIVI, FNNO.trim());

                    while (rsl_line.next()) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                        mne.setField("FCS", "WFCUCD");
                        mne.setField("WFCINO", rsl_line.getString("LREP_INVNO").trim());
                        mne.pressKey(MNEProtocol.KeyEnter);

                        mne.setField("CMDTP", "LSTOPT");
                        mne.setField("CMDVAL", "11");
                        mne.setField("FCS", "WFCINO");
                        mne.setField("SELROWS", "R1");
                        mne.setField("WFCINO", rsl_line.getString("LREP_INVNO").trim());
                        mne.selectOption("11");
                        System.out.println(mne.panel);

                        System.out.println(mne.getMsg());

                        if (mne.getMsg() != null) {
                            System.out.println(mne.getMsg());
                            mne.pressKey(MNEProtocol.KeyEnter);
                            System.out.println(RSL_GR.getString("HC_RCNO").trim());
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "F18");
                            mne.setField("FCS", "WFCINO");
                            mne.pressKey("F18");
                            mne.pressKey(MNEProtocol.KeyEnter);
                            mne.pressKey(MNEProtocol.KeyEnter);
//                            mne.closeProgram(ARS110);
                        }

                        if (mne.panel.equals("ARS110/H")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WHCUAM");
                            mne.setField("WHCUAM", cfd.Double2digitReturn(rsl_line.getDouble("LREP_EXPAMT")));
                            mne.pressKey(MNEProtocol.KeyEnter);

                            System.out.println(cfd.Double2digitReturn(rsl_line.getDouble("LREP_EXPAMT")));
                        }
//                        lastinv = rsl_line.getString("LR_INVNO").trim();
                    }

//                    Double BL = cgd.Call_Balance(RSL_GR.getString("HC_RCNO").trim());
//                    BL += cgd.Call_Expense(RSL_GR.getString("HC_RCNO").trim());
//// SH ////
//                    mne.setField("CMDTP", "KEY");
//                    mne.setField("CMDVAL", "ENTER");
//                    mne.setField("WFSLOP", "23");
//                    mne.pressKey(MNEProtocol.KeyEnter);
//
//                    if (mne.panel.equals("ARS110/G1")) {
//                        if (mne.getMsg() != null) {
//                            mne.pressKey(MNEProtocol.KeyEnter);
//                            mne.setField("CMDTP", "KEY");
//                            mne.setField("CMDVAL", "ENTER");
//                            mne.setField("FCS", "WGAIT2");
//
//                        } else {
//                            mne.setField("CMDTP", "KEY");
//                            mne.setField("CMDVAL", "ENTER");
//                            mne.setField("FCS", "WGAIT2");
//                        }
//                        mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
//                        mne.pressKey(MNEProtocol.KeyEnter);
//                        if (mne.getMsg() != null) {
//                            if (mne.getMsg().trim().equalsIgnoreCase("Amount must be entered")) {
//                                mne.pressKey(MNEProtocol.KeyEnter);
//                                mne.setField("CMDTP", "KEY");
//                                mne.setField("CMDVAL", "F12");
//                                mne.setField("FCS", "WGCUAM");
//                                mne.pressKey("F12");
//                            }
//                        }
//                    }
///// SH ////
                    mne.setField("CMDTP", "KEY");
                    mne.setField("CMDVAL", "F3");
                    mne.pressKey(MNEProtocol.KeyF03);
                    System.out.println("PP");
                    System.out.println(mne.getMsg());
                    System.out.println("PP");
                    if (mne.getMsg() != null) {
                        if (mne.getMsg().trim().equalsIgnoreCase("Left to distribute does not equal 0")) {
                            System.out.println(mne.getMsg());
                            mne.pressKey(MNEProtocol.KeyEnter);
//                            System.out.println(RSL_GR.getString("HC_RCNO").trim());
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "F18");
                            mne.setField("FCS", "WFCINO");
                            mne.pressKey("F18");
                            mne.pressKey(MNEProtocol.KeyEnter);
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                    }

                    if (mne.getMsg() != null) {
                        System.out.println(mne.getMsg());
                    }
                    String[] Vouchermss = mne.getMsg().toString().split(" ");
                    String Voucher = Vouchermss[2].trim();

                    if (Voucher.length() == 8) {

                        for (int counter = 0; counter < arrRC.size(); counter++) {

                            csd.UpdataVoucherClearnew(CONO, DIVI, arrRC.get(counter), Voucher, FNNO);
                        }
                    }
                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.panel);
                }

            }

            mne.closeProgram(ARS110);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public static void ARS110_TRANSFER_CLEAR_EXPENSEV2(int CONO, String DIVI, String RCNO, String TYPE) {
        String LoginUrlConnectionm3 = GBVAR.LoginUrlConnectionm3;
        String LoginM3User = "MVXSECOFR";
        String LoginM3Password = "lawson@90";
        String LoginAppServer = "192.200.9.190";
        int LoginAppPort = GBVAR.DBPORT;
//        int LoginAppPort = 16305; // TST

        try {
            ClassGetData cgd = new ClassGetData();
            ClassSetData csd = new ClassSetData();
            ClassFormatData cfd = new ClassFormatData();
            int cono = CONO;
            String divi = DIVI.trim();
            String lastinv = "";

            MNEHelper mne = new MNEHelper(LoginAppServer, LoginAppPort, LoginUrlConnectionm3);
            mne.logInToM3(cono, divi, LoginM3User, LoginM3Password);

            if (!mne.logInToM3(cono, divi, LoginM3User, LoginM3Password)) {
                System.out.println("Can not login to M3 system");
            }

            String ARS110 = mne.runM3Pgm("ARS110");

            if ((ARS110).equals("")) {
                System.out.println(" ไม่สามารถเปิดโปรแกรม ARS110 ได้");
                mne.closeProgram(ARS110);
            }

            ResultSet RSL_GR = cgd.GetReceipt_EXP(CONO, DIVI, RCNO, TYPE);

            ResultSet RSL_FNC = cgd.GET_FNC_REF1(CONO, DIVI, RCNO);
            String FNC = "", REF1 = "";
            while (RSL_FNC.next()) {
                FNC = RSL_FNC.getString("RL_FNC").trim();
                REF1 = RSL_FNC.getString("RL_REF1").trim();
            }
            System.out.println("Running");
            ClassFormatData cfm = new ClassFormatData();

            if (mne.panel.equals("ARS110/B")) {
                mne.setField("CMDTP", "KEY");
                mne.setField("CMDVAL", "ENTER");
                mne.setField("FCS", "WAFNCN");
                mne.setField("WAFNCN", FNC);
                mne.pressKey(MNEProtocol.KeyEnter);

                mne.setField("CMDTP", "1"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                mne.setField("SELROWS", "R1"); //CMDTP
                mne.setField("FCS", "WAFNCN"); //CMDVAL ENTER
                mne.setField("WAFNCN", FNC); //FCS W1OBKV
//              mne.pressKey(MNEProtocol.KeyEnter);
                mne.selectOption("1");
//                System.out.println(mne.getMsg());
            }

            while (RSL_GR.next()) {

                if (mne.panel.equals("ARS112/E")) {
                    // System.out.println("CRS610/E");
                    Double HC_REAMT = 0.00;

                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("FCS", "WEAIT6"); //
                    mne.setField("WEAIT1", RSL_GR.getString("HC_ACCODE").trim()); // ACCOUNT CODE

                    mne.setField("WECUAM", HC_REAMT); //amt
                    System.out.println(HC_REAMT);
                    System.out.println("ZZ");

//                    mne.setField("WEACDT", "311022"); // Accounting Date
//                    System.out.println("311022");
                    mne.setField("WEDTP5", cfm.SubDateForM3(RSL_GR.getString("HC_PYDT"))); //
                    mne.setField("WEAIT4", REF1); //ACC date
                    mne.setField("WEAIT5", cfm.SubDateForM3(RSL_GR.getString("HC_PYDT"))); //
                    mne.setField("WEAIT6", RSL_GR.getString("HC_RCNO").trim()); //

                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.getMsg());
                    // JOptionPane.showMessageDialog(null, mne.getMsg());

                }
                if (mne.panel.equals("ARS110/F1")) {
                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim()); //
                    mne.setField("FCS", "WFPYNO"); //
                    mne.pressKey(MNEProtocol.KeyEnter);

//////// EXPENSE ////////
                    ResultSet rsl_expense = cgd.LineExpense(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim());
                    while (rsl_expense.next()) {

                        System.out.println("Check value-----------xx-------------------");
                        System.out.println(rsl_expense.getString("LRE_AMT").trim());
                        System.out.println("Check value----------xx--------------------");

                        mne.setField("CMDTP", "KEY"); //CMDTP KEY
                        mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                        mne.setField("WFSLOP", rsl_expense.getString("RE_OPTION").trim());
                        mne.setField("FCS", "WFSLOP");
                        mne.setField("WFQTTP", "1");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.getMsg() != null) {
                            System.out.println("Checknull " + mne.panel);
                            System.out.println(rsl_expense.getString("LRE_AMT").trim());
                            System.out.println(mne.getMsg());
                            mne.pressKey(MNEProtocol.KeyEnter);

                        } else {
                            System.out.println("isnull " + mne.panel);
                        }

                        if (mne.panel.equals("ARS110/G1")) {

                            System.out.println(rsl_expense.getString("LRE_AMT").trim());
                            mne.setField("CMDTP", "KEY"); //CMDTP KEY
                            mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WGCUAM", rsl_expense.getString("LRE_AMT").trim());
                            System.out.println("Check value------------------------------");
                            System.out.println(rsl_expense.getString("LRE_AMT").trim());
                            System.out.println("Check value------------------------------");

                            mne.setField("WGAIT1", rsl_expense.getString("LRE_ACTCODE").trim());
                            mne.setField("WGAIT4", cgd.Expense_CustomerName(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.setField("WGVTXT", cgd.Expense_text(CONO, DIVI, rsl_expense.getString("LRE_CODE").trim()));

                            mne.setField("FCS", "WGCUAM");
                            if (!rsl_expense.getString("LRE_CODE").trim().equalsIgnoreCase("56") && !rsl_expense.getString("LRE_CODE").trim().equalsIgnoreCase("57") && !rsl_expense.getString("LRE_CODE").trim().equalsIgnoreCase("70")) {
                                mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            }
// S503   S601
//                            if (rsl_expense.getString("LRE_CODE").trim().equalsIgnoreCase("70")) {
//                                mne.setField("WGAIT3", "COVID");
//                            }

                            mne.pressKey(MNEProtocol.KeyEnter);

                        }

                    }
                    System.out.println("Check " + mne.panel);
                    System.out.println(mne.getMsg());

                    ResultSet rsl_line = cgd.LineClearExpense(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim());

                    while (rsl_line.next()) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                        mne.setField("FCS", "WFCUCD");
                        mne.setField("WFCINO", rsl_line.getString("LREP_INVNO").trim());
                        mne.pressKey(MNEProtocol.KeyEnter);

                        mne.setField("CMDTP", "LSTOPT");
                        mne.setField("CMDVAL", "11");
                        mne.setField("FCS", "WFCINO");
                        mne.setField("SELROWS", "R1");
                        mne.setField("WFCINO", rsl_line.getString("LREP_INVNO").trim());
                        mne.selectOption("11");
                        System.out.println(mne.panel);

                        System.out.println(mne.getMsg());

                        if (mne.getMsg() != null) {
                            System.out.println(mne.getMsg());
                            mne.pressKey(MNEProtocol.KeyEnter);
                            System.out.println(RSL_GR.getString("HC_RCNO").trim());
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "F18");
                            mne.setField("FCS", "WFCINO");
                            mne.pressKey("F18");
                            mne.pressKey(MNEProtocol.KeyEnter);
                            mne.pressKey(MNEProtocol.KeyEnter);
//                            mne.closeProgram(ARS110);
                        }

                        if (mne.panel.equals("ARS110/H")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WHCUAM");
                            mne.setField("WHCUAM", cfd.Double2digitReturn(rsl_line.getDouble("LREP_EXPAMT")));
                            mne.pressKey(MNEProtocol.KeyEnter);

                            System.out.println(cfd.Double2digitReturn(rsl_line.getDouble("LREP_EXPAMT")));
                        }
//                        lastinv = rsl_line.getString("LR_INVNO").trim();
                    }

//                    Double BL = cgd.Call_Balance(RSL_GR.getString("HC_RCNO").trim());
//                    BL += cgd.Call_Expense(RSL_GR.getString("HC_RCNO").trim());
//// SH ////
//                    mne.setField("CMDTP", "KEY");
//                    mne.setField("CMDVAL", "ENTER");
//                    mne.setField("WFSLOP", "23");
//                    mne.pressKey(MNEProtocol.KeyEnter);
//
//                    if (mne.panel.equals("ARS110/G1")) {
//                        if (mne.getMsg() != null) {
//                            mne.pressKey(MNEProtocol.KeyEnter);
//                            mne.setField("CMDTP", "KEY");
//                            mne.setField("CMDVAL", "ENTER");
//                            mne.setField("FCS", "WGAIT2");
//
//                        } else {
//                            mne.setField("CMDTP", "KEY");
//                            mne.setField("CMDVAL", "ENTER");
//                            mne.setField("FCS", "WGAIT2");
//                        }
//                        mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
//                        mne.pressKey(MNEProtocol.KeyEnter);
//                        if (mne.getMsg() != null) {
//                            if (mne.getMsg().trim().equalsIgnoreCase("Amount must be entered")) {
//                                mne.pressKey(MNEProtocol.KeyEnter);
//                                mne.setField("CMDTP", "KEY");
//                                mne.setField("CMDVAL", "F12");
//                                mne.setField("FCS", "WGCUAM");
//                                mne.pressKey("F12");
//                            }
//                        }
//                    }
///// SH ////
                    mne.setField("CMDTP", "KEY");
                    mne.setField("CMDVAL", "F3");
                    mne.pressKey(MNEProtocol.KeyF03);
                    System.out.println("PP");
                    System.out.println(mne.getMsg());
                    System.out.println("PP");
                    if (mne.getMsg() != null) {
                        if (mne.getMsg().trim().equalsIgnoreCase("Left to distribute does not equal 0")) {
                            System.out.println(mne.getMsg());
                            mne.pressKey(MNEProtocol.KeyEnter);
                            System.out.println(RSL_GR.getString("HC_RCNO").trim());
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "F18");
                            mne.setField("FCS", "WFCINO");
                            mne.pressKey("F18");
                            mne.pressKey(MNEProtocol.KeyEnter);
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                    }

                    if (mne.getMsg() != null) {
                        System.out.println(mne.getMsg());
                    }
                    String[] Vouchermss = mne.getMsg().toString().split(" ");
                    String Voucher = Vouchermss[2].trim();

                    if (Voucher.length() == 8) {

                        csd.UpdataVoucherClear(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim(), Voucher);
                    }
                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.panel);
                }

            }

            mne.closeProgram(ARS110);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public static void ARS110_CHECK_EXPENSE_FULLnew(int CONO, String DIVI, ArrayList<String> arrRC, String TYPE, String FNNO) {
        String LoginUrlConnectionm3 = GBVAR.LoginUrlConnectionm3;
        String LoginM3User = "MVXSECOFR";
        String LoginM3Password = "lawson@90";
        String LoginAppServer = "192.200.9.190";
        int LoginAppPort = GBVAR.DBPORT;
//        int LoginAppPort = 16305; // TST

        try {
            ClassGetData cgd = new ClassGetData();
            ClassSetData csd = new ClassSetData();
            int cono = CONO;
            String divi = DIVI.trim();
            String lastinv = "";

            MNEHelper mne = new MNEHelper(LoginAppServer, LoginAppPort, LoginUrlConnectionm3);
            mne.logInToM3(cono, divi, LoginM3User, LoginM3Password);

            if (!mne.logInToM3(cono, divi, LoginM3User, LoginM3Password)) {
                System.out.println("Can not login to M3 system");
            }

            String ARS110 = mne.runM3Pgm("ARS110");

            if ((ARS110).equals("")) {
                System.out.println(" ไม่สามารถเปิดโปรแกรม ARS110 ได้");
                mne.closeProgram(ARS110);
                System.out.println("1aaaaaaaaaaaaaaaaaaaaaaaaaaa");
            }

            ResultSet RSL_GR = cgd.GetReceiptnew4(CONO, DIVI, arrRC.get(0), TYPE);

            ResultSet RSL_FNC = cgd.GET_FNC_REF1(CONO, DIVI, arrRC.get(0));
            String FNC = "", REF1 = "";
            while (RSL_FNC.next()) {
                FNC = RSL_FNC.getString("RL_FNC").trim();
                REF1 = RSL_FNC.getString("RL_REF1").trim();
            }

            for (int counter = 0; counter < arrRC.size(); counter++) {
                Update_OCUSMAnew(CONO, DIVI, arrRC.get(0), TYPE, "1", FNNO);
            }

            System.out.println("Running");
            ClassFormatData cfm = new ClassFormatData();

            System.out.println("2aaaaaaaaaaaaaaaaaaaaaaaaaaa");

            if (mne.panel.equals("ARS110/B")) {
                mne.setField("CMDTP", "KEY");
                mne.setField("CMDVAL", "ENTER");
                mne.setField("FCS", "WAFNCN");
                mne.setField("WAFNCN", FNC);
                mne.pressKey(MNEProtocol.KeyEnter);

                mne.setField("CMDTP", "1"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                mne.setField("SELROWS", "R1"); //CMDTP
                mne.setField("FCS", "WAFNCN"); //CMDVAL ENTER
                mne.setField("WAFNCN", FNC); //FCS W1OBKV
//              mne.pressKey(MNEProtocol.KeyEnter);
                mne.selectOption("1");
                System.out.println(mne.getMsg());

                System.out.println("3aaaaaaaaaaaaaaaaaaaaaaaaaaa");

            }

            while (RSL_GR.next()) {

                if (RSL_GR.getString("HC_CHKNO").trim().equalsIgnoreCase("")) {
                    System.out.println("4aaaaaaaaaaaaaaaaaaaaaaaaaaa");

                    continue;
                }

                if (mne.panel.equals("ARS112/E")) {

                    System.out.println("5aaaaaaaaaaaaaaaaaaaaaaaaaaa");

                    // System.out.println("CRS610/E");
                    Double HC_REAMT = cfm.Double2digitReturn(RSL_GR.getDouble("HC_REAMT"));

                    if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                        HC_REAMT = HC_REAMT - RSL_GR.getDouble("HR_BKCHG");

                        System.out.println("6aaaaaaaaaaaaaaaaaaaaaaaaaaa");

                    }

                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("FCS", "WEDRRN"); //
                    mne.setField("WEAIT1", RSL_GR.getString("HC_ACCODE").trim()); // ACCOUNT CODE
                    mne.setField("WEPYNO", RSL_GR.getString("HC_PYNO").trim()); //
                    mne.setField("WECUAM", String.format("%.2f", HC_REAMT)); //amt
                    mne.setField("WEDTP5", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //
                    mne.setField("WEACDT", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //
                    mne.setField("WEDRRN", RSL_GR.getString("HC_CHKNO").trim()); //ACC date
                    mne.setField("WEDUDT", cfm.SubDateForM3(RSL_GR.getString("HC_PYDT"))); //
                    mne.setField("WEBKID", RSL_GR.getString("HC_BANK").trim()); //
                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.getMsg());

                    System.out.println("7aaaaaaaaaaaaaaaaaaaaaaaaaaa");

                    // JOptionPane.showMessageDialog(null, mne.getMsg());
                    if (mne.getMsg() != null) {
                        mne.pressKey(MNEProtocol.KeyEnter);
                        System.out.println("8aaaaaaaaaaaaaaaaaaaaaaaaaaa");

                        continue;
                    }

                }
                if (mne.panel.equals("ARS110/F1")) {
                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim()); //
                    mne.setField("FCS", "WFPYNO"); //
                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println("9aaaaaaaaaaaaaaaaaaaaaaaaaaa");

                    ////BANK CHARGE
                    if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {

                        System.out.println("10aaaaaaaaaaaaaaaaaaaaaaaaaaa");

                        mne.setField("CMDTP", "KEY"); //CMDTP KEY
                        mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                        mne.setField("WFSLOP", "21");
                        mne.setField("FCS", "WFSLOP");
                        mne.setField("WFQTTP", "1");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.getMsg() != null) {
                            mne.pressKey(MNEProtocol.KeyEnter);

                            System.out.println("11aaaaaaaaaaaaaaaaaaaaaaaaaaa");

                        }

                        if (mne.panel.equals("ARS110/G1")) {
                            mne.setField("CMDTP", "KEY"); //CMDTP KEY
                            mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WGCUAM", RSL_GR.getString("HR_BKCHG").trim());
                            mne.setField("WGAIT7", "ARS110");
                            mne.setField("FCS", "WGCUAM");
                            mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.pressKey(MNEProtocol.KeyEnter);
                            System.out.println("12aaaaaaaaaaaaaaaaaaaaaaaaaaa");

                        }

                    }
////

//////// EXPENSE ////////
                    ResultSet rsl_expense = cgd.LineExpensenew(CONO, DIVI, FNNO);
                    while (rsl_expense.next()) {

                        mne.setField("CMDTP", "KEY"); //CMDTP KEY
                        mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                        mne.setField("WFSLOP", rsl_expense.getString("RE_OPTION").trim());
                        mne.setField("FCS", "WFSLOP");
                        mne.setField("WFQTTP", "1");
                        mne.pressKey(MNEProtocol.KeyEnter);
                        System.out.println("13aaaaaaaaaaaaaaaaaaaaaaaaaaa");

                        if (mne.getMsg() != null) {
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                        if (mne.panel.equals("ARS110/G1")) {
                            mne.setField("CMDTP", "KEY"); //CMDTP KEY
                            mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WGCUAM", rsl_expense.getString("LRE_AMT").trim());
                            mne.setField("WGAIT1", rsl_expense.getString("LRE_ACTCODE").trim());
                            mne.setField("WGAIT4", cgd.Expense_CustomerName(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.setField("WGVTXT", cgd.Expense_text(CONO, DIVI, rsl_expense.getString("LRE_CODE").trim()));

                            mne.setField("FCS", "WGCUAM");
                            System.out.println("1a4aaaaaaaaaaaaaaaaaaaaaaaaaa");

                            if (!rsl_expense.getString("LRE_CODE").trim().equalsIgnoreCase("56") && !rsl_expense.getString("LRE_CODE").trim().equalsIgnoreCase("57") && !rsl_expense.getString("LRE_CODE").trim().equalsIgnoreCase("70")) {
                                mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                                System.out.println("15aaaaaaaaaaaaaaaaaaaaaaaaaaa");

                            }

//                            if (rsl_expense.getString("LRE_CODE").trim().equalsIgnoreCase("70")) {
//                                mne.setField("WGAIT3", "COVID");
//                            }
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                    }

/////////////////////////LineReceiptnew
                    ResultSet rsl_line = cgd.LineReceiptnew(CONO, DIVI, arrRC);

//                    ResultSet rsl_line = cgd.LineReceipt(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim());
                    while (rsl_line.next()) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
//                        mne.setField("WFPYNO", "TH28050014");
                        mne.setField("FCS", "WFCINO");
                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());
                        mne.pressKey(MNEProtocol.KeyEnter);
                        System.out.println("18aaaaaaaaaaaaaaaaaaaaaaaaaaa");

                        if (mne.getMsg() != null) {
                            System.out.println(mne.getMsg());
                            mne.pressKey(MNEProtocol.KeyEnter);
                            System.out.println(RSL_GR.getString("HC_RCNO").trim());
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "F18");
                            mne.setField("FCS", "WFCINO");
                            mne.pressKey("F18");
                            mne.pressKey(MNEProtocol.KeyEnter);
                            mne.pressKey(MNEProtocol.KeyEnter);
                            mne.closeProgram(ARS110);

                            System.out.println("19aaaaaaaaaaaaaaaaaaaaaaaaaaa");

                        }

                        mne.setField("CMDTP", "LSTOPT");
                        mne.setField("CMDVAL", "11");
                        mne.setField("FCS", "WFCINO");
                        mne.setField("SELROWS", "R1");
                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());
                        mne.selectOption("11");
                        System.out.println(mne.getMsg());
                        System.out.println("20aaaaaaaaaaaaaaaaaaaaaaaaaa");

                        if (mne.panel.equals("ARS110/H")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WHCUAM");
                            mne.setField("WHCUAM", rsl_line.getString("LR_REAMT").trim());
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                        lastinv = rsl_line.getString("LR_INVNO").trim();
                    }
                    Double BL = cgd.Call_Balancenew(CONO, DIVI, arrRC);
                    BL += cgd.Call_Expensenew(CONO, DIVI, FNNO.trim());

                    System.out.println("-----------------------BL = ------------------------");
                    System.out.println(BL);

                    System.out.println("----------------------------------------------------");

                    if (BL > 2) {
                        if (mne.panel.equals("ARS110/F1")) {

                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WFCINO", lastinv);

                            mne.setField("CMDTP", "LSTOPT");
                            mne.setField("CMDVAL", "12");
                            mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                            mne.setField("FCS", "WFCINO");
                            mne.setField("SELROWS", "R1");
                            mne.setField("WFCINO", lastinv);
                            mne.selectOption("12");
                            System.out.println("122aaaaaaaaaaaaaaaaaaaaaaaaaaa");

                        }

//                        
                        if (mne.panel.equals("ARS110/I")) {
                            mne.setField("CMDTP", "KEY");

                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WIAIT3");
                            mne.setField("WIAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.setField("WIAIT3", "NON");
                            mne.pressKey(MNEProtocol.KeyEnter);

                            System.out.println("551aaaaaaaaaaaaaaaaaaaaaaaaaaa");

                        }

                    } else if ((BL <= 2 && BL >= -2) && BL != 0) {

                        System.out.println("1a5776aaaaaaaaaaaaaaaaaaaaaaaaaa");

                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFSLOP", "23");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.panel.equals("ARS110/G1")) {

                            System.out.println("9871aaaaaaaaaaaaaaaaaaaaaaaaaaa");

                            if (mne.getMsg() != null) {
                                mne.pressKey(MNEProtocol.KeyEnter);
                            }

                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WGAIT2");

                            if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                                mne.setField("WGCUAM", cfm.Double2digitReturn(BL));
                                System.out.println("9999991aaaaaaaaaaaaaaaaaaaaaaaaaaa");

                            }

                            mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                    }

                    mne.setField("CMDTP", "KEY");
                    mne.setField("CMDVAL", "F3");
                    mne.pressKey(MNEProtocol.KeyF03);

                    if (mne.getMsg().trim().equalsIgnoreCase("Left to distribute does not equal 0")) {
                        System.out.println(mne.getMsg());
                        mne.pressKey(MNEProtocol.KeyEnter);
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "F18");
                        mne.setField("FCS", "WFCINO");
                        mne.pressKey("F18");
                        mne.pressKey(MNEProtocol.KeyEnter);
                        mne.pressKey(MNEProtocol.KeyEnter);
                    }

                    if (mne.getMsg() != null) {
                        System.out.println(mne.getMsg());
                    }
                    String[] Vouchermss = mne.getMsg().toString().split(" ");
                    String Voucher = Vouchermss[2].trim();

                    if (Voucher.length() == 8) {

                        for (int counter = 0; counter < arrRC.size(); counter++) {

                            System.out.println("arrRC is : " + arrRC.get(counter));
                            csd.UpdataVouchernew(CONO, DIVI, arrRC.get(counter), Voucher, FNNO);

                        }
                    }
                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.panel);
                }

            }

            mne.closeProgram(ARS110);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    /*
    
    public static void ARS110_CHECK_EXPENSE_FULLnew(int CONO, String DIVI, ArrayList<String> arrRC, String TYPE, String FNNO) {

        ClassGetData cgd = new ClassGetData();
        ClassSetData csd = new ClassSetData();
        int cono = CONO;
        String divi = DIVI.trim();
        String lastinv = "";

        ResultSet RSL_GR = cgd.GetReceiptnew4(CONO, DIVI, arrRC.get(0), TYPE);

        System.out.println("---------------RSL_GR--------------------");

        System.out.println(RSL_GR);

        System.out.println("-----------------------------------");

        ResultSet RSL_FNC = cgd.GET_FNC_REF1(CONO, DIVI, arrRC.get(0));

        System.out.println("----------------RSL_FNC-------------------");

        System.out.println(RSL_FNC);

        System.out.println("-----------------------------------");

        System.out.println("Running");
        ClassFormatData cfm = new ClassFormatData();

        ResultSet rsl_expense = cgd.LineExpensenew(CONO, DIVI, FNNO);

        System.out.println("--------------rsl_expense---------------------");
        System.out.println(rsl_expense);

        System.out.println("-----------------------------------");

        ResultSet rsl_line = cgd.LineReceiptnew(CONO, DIVI, arrRC);

        try {

            while (rsl_line.next()) {
                System.out.println("*******************************");
                System.out.println(rsl_line.getString("LR_INVNO").trim());
                System.out.println("*******************************");

            }
        } catch (Exception e) {

            System.out.println(e.toString());

        }

        System.out.println("--------------rsl_line---------------------");
        System.out.println(rsl_line);
        System.out.println("-----------------------------------");

        Double BL = cgd.Call_Balancenew(CONO, DIVI, arrRC);

        System.out.println("---------------BL----Call_Balancenew----------------");
        System.out.println(BL);
        System.out.println("-----------------------------------");

        //BL += cgd.Call_Expensenew(CONO, DIVI, FNNO.trim());
        System.out.println("------------BL------Call_Expensenew-----------------");
        System.out.println(cgd.Call_Expensenew(CONO, DIVI, FNNO.trim()));
        System.out.println(BL);

        double aa = BL + cgd.Call_Expensenew(CONO, DIVI, FNNO.trim());

        System.out.println(BL + " + " + cgd.Call_Expensenew(CONO, DIVI, FNNO.trim()) + " = " + aa);
        System.out.println("-----------------------------------");

        if (BL > 2) {

            System.out.println("BL >>>>>>2");

        }
        else if ((BL <= 2 && BL >= -2) && BL != 0) {

            System.out.println("(BL <= 2 && BL >= -2) && BL != 0");
        } else {
            System.out.println("ELSE = 0 ");
        }

    }
     */
    public static void ARS110_CHECK_EXPENSE_FULL(int CONO, String DIVI, String RCNO, String TYPE) {
        String LoginUrlConnectionm3 = GBVAR.LoginUrlConnectionm3;
        String LoginM3User = "MVXSECOFR";
        String LoginM3Password = "lawson@90";
        String LoginAppServer = "192.200.9.190";
        int LoginAppPort = GBVAR.DBPORT;
//        int LoginAppPort = 16305; // TST

        try {
            ClassGetData cgd = new ClassGetData();
            ClassSetData csd = new ClassSetData();
            int cono = CONO;
            String divi = DIVI.trim();
            String lastinv = "";

            MNEHelper mne = new MNEHelper(LoginAppServer, LoginAppPort, LoginUrlConnectionm3);
            mne.logInToM3(cono, divi, LoginM3User, LoginM3Password);

            if (!mne.logInToM3(cono, divi, LoginM3User, LoginM3Password)) {
                System.out.println("Can not login to M3 system");
            }

            String ARS110 = mne.runM3Pgm("ARS110");

            if ((ARS110).equals("")) {
                System.out.println(" ไม่สามารถเปิดโปรแกรม ARS110 ได้");
                mne.closeProgram(ARS110);
            }

            ResultSet RSL_GR = cgd.GetReceipt(CONO, DIVI, RCNO, TYPE);
            ResultSet RSL_FNC = cgd.GET_FNC_REF1(CONO, DIVI, RCNO);
            String FNC = "", REF1 = "";
            while (RSL_FNC.next()) {
                FNC = RSL_FNC.getString("RL_FNC").trim();
                REF1 = RSL_FNC.getString("RL_REF1").trim();
            }

            Update_OCUSMA(CONO, DIVI, RCNO, TYPE, "1");
            System.out.println("Running");
            ClassFormatData cfm = new ClassFormatData();

            if (mne.panel.equals("ARS110/B")) {
                mne.setField("CMDTP", "KEY");
                mne.setField("CMDVAL", "ENTER");
                mne.setField("FCS", "WAFNCN");
                mne.setField("WAFNCN", FNC);
                mne.pressKey(MNEProtocol.KeyEnter);

                mne.setField("CMDTP", "1"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                mne.setField("SELROWS", "R1"); //CMDTP
                mne.setField("FCS", "WAFNCN"); //CMDVAL ENTER
                mne.setField("WAFNCN", FNC); //FCS W1OBKV
//              mne.pressKey(MNEProtocol.KeyEnter);
                mne.selectOption("1");
                System.out.println(mne.getMsg());
            }

            while (RSL_GR.next()) {

                if (RSL_GR.getString("HC_CHKNO").trim().equalsIgnoreCase("")) {
                    continue;
                }

                if (mne.panel.equals("ARS112/E")) {
                    // System.out.println("CRS610/E");
                    Double HC_REAMT = cfm.Double2digitReturn(RSL_GR.getDouble("HC_REAMT"));

                    if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                        HC_REAMT = HC_REAMT - RSL_GR.getDouble("HR_BKCHG");
                    }

                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("FCS", "WEDRRN"); //
                    mne.setField("WEAIT1", RSL_GR.getString("HC_ACCODE").trim()); // ACCOUNT CODE
                    mne.setField("WEPYNO", RSL_GR.getString("HC_PYNO").trim()); //
                    mne.setField("WECUAM", String.format("%.2f", HC_REAMT)); //amt
                    mne.setField("WEDTP5", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //
                    mne.setField("WEACDT", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //
                    mne.setField("WEDRRN", RSL_GR.getString("HC_CHKNO").trim()); //ACC date
                    mne.setField("WEDUDT", cfm.SubDateForM3(RSL_GR.getString("HC_PYDT"))); //
                    mne.setField("WEBKID", RSL_GR.getString("HC_BANK").trim()); //
                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.getMsg());
                    // JOptionPane.showMessageDialog(null, mne.getMsg());
                    if (mne.getMsg() != null) {
                        mne.pressKey(MNEProtocol.KeyEnter);
                        continue;
                    }

                }
                if (mne.panel.equals("ARS110/F1")) {
                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim()); //
                    mne.setField("FCS", "WFPYNO"); //
                    mne.pressKey(MNEProtocol.KeyEnter);

                    ////BANK CHARGE
                    if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {

                        mne.setField("CMDTP", "KEY"); //CMDTP KEY
                        mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                        mne.setField("WFSLOP", "21");
                        mne.setField("FCS", "WFSLOP");
                        mne.setField("WFQTTP", "1");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.getMsg() != null) {
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                        if (mne.panel.equals("ARS110/G1")) {
                            mne.setField("CMDTP", "KEY"); //CMDTP KEY
                            mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WGCUAM", RSL_GR.getString("HR_BKCHG").trim());
                            mne.setField("WGAIT7", "ARS110");
                            mne.setField("FCS", "WGCUAM");
                            mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                    }
////

//////// EXPENSE ////////
                    ResultSet rsl_expense = cgd.LineExpense(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim());
                    while (rsl_expense.next()) {

                        mne.setField("CMDTP", "KEY"); //CMDTP KEY
                        mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                        mne.setField("WFSLOP", rsl_expense.getString("RE_OPTION").trim());
                        mne.setField("FCS", "WFSLOP");
                        mne.setField("WFQTTP", "1");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.getMsg() != null) {
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                        if (mne.panel.equals("ARS110/G1")) {
                            mne.setField("CMDTP", "KEY"); //CMDTP KEY
                            mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WGCUAM", rsl_expense.getString("LRE_AMT").trim());
                            mne.setField("WGAIT1", rsl_expense.getString("LRE_ACTCODE").trim());
                            mne.setField("WGAIT4", cgd.Expense_CustomerName(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.setField("WGVTXT", cgd.Expense_text(CONO, DIVI, rsl_expense.getString("LRE_CODE").trim()));

                            mne.setField("FCS", "WGCUAM");
                            if (!rsl_expense.getString("LRE_CODE").trim().equalsIgnoreCase("56") && !rsl_expense.getString("LRE_CODE").trim().equalsIgnoreCase("57") && !rsl_expense.getString("LRE_CODE").trim().equalsIgnoreCase("70")) {
                                mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            }

//                            if (rsl_expense.getString("LRE_CODE").trim().equalsIgnoreCase("70")) {
//                                mne.setField("WGAIT3", "COVID");
//                            }
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                    }

/////////////////////////
                    ResultSet rsl_line = cgd.LineReceipt(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim());
                    while (rsl_line.next()) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
//                        mne.setField("WFPYNO", "TH28050014");
                        mne.setField("FCS", "WFCINO");
                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.getMsg() != null) {
                            System.out.println(mne.getMsg());
                            mne.pressKey(MNEProtocol.KeyEnter);
                            System.out.println(RSL_GR.getString("HC_RCNO").trim());
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "F18");
                            mne.setField("FCS", "WFCINO");
                            mne.pressKey("F18");
                            mne.pressKey(MNEProtocol.KeyEnter);
                            mne.pressKey(MNEProtocol.KeyEnter);
                            mne.closeProgram(ARS110);
                        }

                        mne.setField("CMDTP", "LSTOPT");
                        mne.setField("CMDVAL", "11");
                        mne.setField("FCS", "WFCINO");
                        mne.setField("SELROWS", "R1");
                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());
                        mne.selectOption("11");
                        System.out.println(mne.getMsg());

                        if (mne.panel.equals("ARS110/H")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WHCUAM");
                            mne.setField("WHCUAM", rsl_line.getString("LR_REAMT").trim());
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                        lastinv = rsl_line.getString("LR_INVNO").trim();
                    }
                    Double BL = cgd.Call_Balance(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim());
                    BL += cgd.Call_Expense(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim());

                    if (BL > 2) {
                        if (mne.panel.equals("ARS110/F1")) {

                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WFCINO", lastinv);

                            mne.setField("CMDTP", "LSTOPT");
                            mne.setField("CMDVAL", "12");
                            mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                            mne.setField("FCS", "WFCINO");
                            mne.setField("SELROWS", "R1");
                            mne.setField("WFCINO", lastinv);
                            mne.selectOption("12");

                        }

//                        
                        if (mne.panel.equals("ARS110/I")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WIAIT3");
                            mne.setField("WIAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.setField("WIAIT3", "NON");
                            mne.pressKey(MNEProtocol.KeyEnter);

                        }

                    } else if ((BL <= 2 && BL >= -2) && BL != 0) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFSLOP", "23");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.panel.equals("ARS110/G1")) {

                            if (mne.getMsg() != null) {
                                mne.pressKey(MNEProtocol.KeyEnter);
                            }

                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WGAIT2");

                            if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                                mne.setField("WGCUAM", cfm.Double2digitReturn(BL));
                            }

                            mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                    }

                    mne.setField("CMDTP", "KEY");
                    mne.setField("CMDVAL", "F3");
                    mne.pressKey(MNEProtocol.KeyF03);

                    if (mne.getMsg().trim().equalsIgnoreCase("Left to distribute does not equal 0")) {
                        System.out.println(mne.getMsg());
                        mne.pressKey(MNEProtocol.KeyEnter);
                        System.out.println(RSL_GR.getString("HC_RCNO").trim());
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "F18");
                        mne.setField("FCS", "WFCINO");
                        mne.pressKey("F18");
                        mne.pressKey(MNEProtocol.KeyEnter);
                        mne.pressKey(MNEProtocol.KeyEnter);
                    }

                    if (mne.getMsg() != null) {
                        System.out.println(mne.getMsg());
                    }
                    String[] Vouchermss = mne.getMsg().toString().split(" ");
                    String Voucher = Vouchermss[2].trim();

                    if (Voucher.length() == 8) {
                        csd.UpdataVoucher(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim(), Voucher);
                    }
                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.panel);
                }

            }

            mne.closeProgram(ARS110);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public static void ARS110_CHECK_EXPENSE_PARTAILnew(int CONO, String DIVI, ArrayList<String> arrRC, String TYPE, String FNNO) {
        String LoginUrlConnectionm3 = GBVAR.LoginUrlConnectionm3;
        String LoginM3User = "MVXSECOFR";
        String LoginM3Password = "lawson@90";
        String LoginAppServer = "192.200.9.190";
        int LoginAppPort = GBVAR.DBPORT;
//        int LoginAppPort = 16305; // TST

        try {
            ClassGetData cgd = new ClassGetData();
            ClassSetData csd = new ClassSetData();
            int cono = CONO;
            String divi = DIVI.trim();
            String lastinv = "";

            MNEHelper mne = new MNEHelper(LoginAppServer, LoginAppPort, LoginUrlConnectionm3);
            mne.logInToM3(cono, divi, LoginM3User, LoginM3Password);

            if (!mne.logInToM3(cono, divi, LoginM3User, LoginM3Password)) {
                System.out.println("Can not login to M3 system");
            }

            String ARS110 = mne.runM3Pgm("ARS110");

            if ((ARS110).equals("")) {
                System.out.println(" ไม่สามารถเปิดโปรแกรม ARS110 ได้");
                mne.closeProgram(ARS110);
            }

            ResultSet RSL_GR = cgd.GetReceiptnew4(CONO, DIVI, arrRC.get(0), TYPE);

            ResultSet RSL_FNC = cgd.GET_FNC_REF1(CONO, DIVI, arrRC.get(0));
            String FNC = "", REF1 = "";
            while (RSL_FNC.next()) {
                FNC = RSL_FNC.getString("RL_FNC").trim();
                REF1 = RSL_FNC.getString("RL_REF1").trim();
            }

            for (int counter = 0; counter < arrRC.size(); counter++) {
                Update_OCUSMAnew(CONO, DIVI, arrRC.get(0), TYPE, "1", FNNO);
            }
            System.out.println("Running");
            ClassFormatData cfm = new ClassFormatData();

            if (mne.panel.equals("ARS110/B")) {
                mne.setField("CMDTP", "KEY");
                mne.setField("CMDVAL", "ENTER");
                mne.setField("FCS", "WAFNCN");
                mne.setField("WAFNCN", FNC);
                mne.pressKey(MNEProtocol.KeyEnter);

                mne.setField("CMDTP", "1"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                mne.setField("SELROWS", "R1"); //CMDTP
                mne.setField("FCS", "WAFNCN"); //CMDVAL ENTER
                mne.setField("WAFNCN", FNC); //FCS W1OBKV
//              mne.pressKey(MNEProtocol.KeyEnter);
                mne.selectOption("1");
                System.out.println(mne.getMsg());
            }

            while (RSL_GR.next()) {

                if (RSL_GR.getString("HC_CHKNO").trim().equalsIgnoreCase("")) {
                    continue;
                }

                if (mne.panel.equals("ARS112/E")) {
                    // System.out.println("CRS610/E");
                    Double HC_REAMT = cfm.Double2digitReturn(RSL_GR.getDouble("HC_REAMT"));

                    if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                        HC_REAMT = HC_REAMT - RSL_GR.getDouble("HR_BKCHG");
                    }

                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("FCS", "WEDRRN"); //
                    mne.setField("WEAIT1", RSL_GR.getString("HC_ACCODE").trim()); // ACCOUNT CODE
                    mne.setField("WEPYNO", RSL_GR.getString("HC_PYNO").trim()); //
                    mne.setField("WECUAM", HC_REAMT); //amt
                    mne.setField("WEDTP5", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //
                    mne.setField("WEACDT", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //
                    mne.setField("WEDRRN", RSL_GR.getString("HC_CHKNO").trim()); //ACC date
                    mne.setField("WEDUDT", cfm.SubDateForM3(RSL_GR.getString("HC_PYDT"))); //
                    mne.setField("WEBKID", RSL_GR.getString("HC_BANK").trim()); //
                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.getMsg());
                    // JOptionPane.showMessageDialog(null, mne.getMsg());
                    if (mne.getMsg() != null) {
                        mne.pressKey(MNEProtocol.KeyEnter);
                        continue;
                    }

                }
                if (mne.panel.equals("ARS110/F1")) {
                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim()); //
                    mne.setField("FCS", "WFPYNO"); //
                    mne.pressKey(MNEProtocol.KeyEnter);

                    ////BANK CHARGE
                    if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {

                        mne.setField("CMDTP", "KEY"); //CMDTP KEY
                        mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                        mne.setField("WFSLOP", "21");
                        mne.setField("FCS", "WFSLOP");
                        mne.setField("WFQTTP", "1");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.getMsg() != null) {
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                        if (mne.panel.equals("ARS110/G1")) {
                            mne.setField("CMDTP", "KEY"); //CMDTP KEY
                            mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WGCUAM", RSL_GR.getString("HR_BKCHG").trim());
                            mne.setField("WGAIT7", "ARS110");
                            mne.setField("FCS", "WGCUAM");
                            mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                    }
////

                    ResultSet rsl_line = cgd.LineReceiptnew(CONO, DIVI, arrRC);
                    while (rsl_line.next()) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
//                        mne.setField("WFPYNO", "TH28050014");
                        mne.setField("FCS", "WFCINO");
                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.getMsg() != null) {
                            System.out.println(mne.getMsg());
                            mne.pressKey(MNEProtocol.KeyEnter);
                            System.out.println(RSL_GR.getString("HC_RCNO").trim());
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "F18");
                            mne.setField("FCS", "WFCINO");
                            mne.pressKey("F18");
                            mne.pressKey(MNEProtocol.KeyEnter);
                            mne.pressKey(MNEProtocol.KeyEnter);
                            mne.closeProgram(ARS110);
                        }

                        mne.setField("CMDTP", "LSTOPT");
                        mne.setField("CMDVAL", "11");
                        mne.setField("FCS", "WFCINO");
                        mne.setField("SELROWS", "R1");
                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());
                        mne.selectOption("11");
                        System.out.println(mne.getMsg());

                        if (mne.panel.equals("ARS110/H")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WHCUAM");
                            mne.setField("WHCUAM", rsl_line.getString("LR_REAMT").trim());
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                        lastinv = rsl_line.getString("LR_INVNO").trim();
                    }

                    Double BL = cgd.Call_Balancenew(CONO, DIVI, arrRC);
//                    BL += cgd.Call_Expense(RSL_GR.getString("HC_RCNO").trim());

                    if (BL > 2) {
                        if (mne.panel.equals("ARS110/F1")) {

                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WFCINO", lastinv);

                            mne.setField("CMDTP", "LSTOPT");
                            mne.setField("CMDVAL", "12");
                            mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                            mne.setField("FCS", "WFCINO");
                            mne.setField("SELROWS", "R1");
                            mne.setField("WFCINO", lastinv);
                            mne.selectOption("12");

                        }

                        if (mne.panel.equals("ARS110/I")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WIAIT3");
                            mne.setField("WIAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.setField("WIAIT3", "NON");
                            mne.pressKey(MNEProtocol.KeyEnter);

                        }

                    } else if ((BL <= 2 && BL >= -2) && BL != 0) {
//                    } else if (BL <= 2 && BL>0) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFSLOP", "23");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.panel.equals("ARS110/G1")) {

                            if (mne.getMsg() != null) {
                                mne.pressKey(MNEProtocol.KeyEnter);
                            }

                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WGAIT2");

                            if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                                mne.setField("WGCUAM", cfm.Double2digitReturn(BL));
                            }

                            mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                    }

                    mne.setField("CMDTP", "KEY");
                    mne.setField("CMDVAL", "F3");
                    mne.pressKey(MNEProtocol.KeyF03);

                    if (mne.getMsg().trim().equalsIgnoreCase("Left to distribute does not equal 0")) {
                        System.out.println(mne.getMsg());
                        mne.pressKey(MNEProtocol.KeyEnter);
                        System.out.println(RSL_GR.getString("HC_RCNO").trim());
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "F18");
                        mne.setField("FCS", "WFCINO");
                        mne.pressKey("F18");
                        mne.pressKey(MNEProtocol.KeyEnter);
                        mne.pressKey(MNEProtocol.KeyEnter);
                    }

                    if (mne.getMsg() != null) {
                        System.out.println(mne.getMsg());
                    }
                    String[] Vouchermss = mne.getMsg().toString().split(" ");
                    String Voucher = Vouchermss[2].trim();

                    if (Voucher.length() == 8) {
                        for (int counter = 0; counter < arrRC.size(); counter++) {

                            System.out.println("arrRC is : " + arrRC.get(counter));
                            csd.UpdataVouchernew(CONO, DIVI, arrRC.get(counter), Voucher, FNNO);

                        }
                        csd.InsertVoucherExpensenew(CONO, DIVI, FNNO.trim(), Voucher);
                    }
                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.panel);
                }

            }

            mne.closeProgram(ARS110);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public static void ARS110_CHECK_EXPENSE_PARTAIL(int CONO, String DIVI, String RCNO, String TYPE) {
        String LoginUrlConnectionm3 = GBVAR.LoginUrlConnectionm3;
        String LoginM3User = "MVXSECOFR";
        String LoginM3Password = "lawson@90";
        String LoginAppServer = "192.200.9.190";
        int LoginAppPort = GBVAR.DBPORT;
//        int LoginAppPort = 16305; // TST

        try {
            ClassGetData cgd = new ClassGetData();
            ClassSetData csd = new ClassSetData();
            int cono = CONO;
            String divi = DIVI.trim();
            String lastinv = "";

            MNEHelper mne = new MNEHelper(LoginAppServer, LoginAppPort, LoginUrlConnectionm3);
            mne.logInToM3(cono, divi, LoginM3User, LoginM3Password);

            if (!mne.logInToM3(cono, divi, LoginM3User, LoginM3Password)) {
                System.out.println("Can not login to M3 system");
            }

            String ARS110 = mne.runM3Pgm("ARS110");

            if ((ARS110).equals("")) {
                System.out.println(" ไม่สามารถเปิดโปรแกรม ARS110 ได้");
                mne.closeProgram(ARS110);
            }

            ResultSet RSL_GR = cgd.GetReceipt(CONO, DIVI, RCNO, TYPE);

            ResultSet RSL_FNC = cgd.GET_FNC_REF1(CONO, DIVI, RCNO);
            String FNC = "", REF1 = "";
            while (RSL_FNC.next()) {
                FNC = RSL_FNC.getString("RL_FNC").trim();
                REF1 = RSL_FNC.getString("RL_REF1").trim();
            }

            Update_OCUSMA(CONO, DIVI, RCNO, TYPE, "1");
            System.out.println("Running");
            ClassFormatData cfm = new ClassFormatData();

            if (mne.panel.equals("ARS110/B")) {
                mne.setField("CMDTP", "KEY");
                mne.setField("CMDVAL", "ENTER");
                mne.setField("FCS", "WAFNCN");
                mne.setField("WAFNCN", FNC);
                mne.pressKey(MNEProtocol.KeyEnter);

                mne.setField("CMDTP", "1"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                mne.setField("SELROWS", "R1"); //CMDTP
                mne.setField("FCS", "WAFNCN"); //CMDVAL ENTER
                mne.setField("WAFNCN", FNC); //FCS W1OBKV
//              mne.pressKey(MNEProtocol.KeyEnter);
                mne.selectOption("1");
                System.out.println(mne.getMsg());
            }

            while (RSL_GR.next()) {

                if (RSL_GR.getString("HC_CHKNO").trim().equalsIgnoreCase("")) {
                    continue;
                }

                if (mne.panel.equals("ARS112/E")) {
                    // System.out.println("CRS610/E");
                    Double HC_REAMT = cfm.Double2digitReturn(RSL_GR.getDouble("HC_REAMT"));

                    if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                        HC_REAMT = HC_REAMT - RSL_GR.getDouble("HR_BKCHG");
                    }

                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("FCS", "WEDRRN"); //
                    mne.setField("WEAIT1", RSL_GR.getString("HC_ACCODE").trim()); // ACCOUNT CODE
                    mne.setField("WEPYNO", RSL_GR.getString("HC_PYNO").trim()); //
                    mne.setField("WECUAM", HC_REAMT); //amt
                    mne.setField("WEDTP5", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //
                    mne.setField("WEACDT", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //
                    mne.setField("WEDRRN", RSL_GR.getString("HC_CHKNO").trim()); //ACC date
                    mne.setField("WEDUDT", cfm.SubDateForM3(RSL_GR.getString("HC_PYDT"))); //
                    mne.setField("WEBKID", RSL_GR.getString("HC_BANK").trim()); //
                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.getMsg());
                    // JOptionPane.showMessageDialog(null, mne.getMsg());
                    if (mne.getMsg() != null) {
                        mne.pressKey(MNEProtocol.KeyEnter);
                        continue;
                    }

                }
                if (mne.panel.equals("ARS110/F1")) {
                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim()); //
                    mne.setField("FCS", "WFPYNO"); //
                    mne.pressKey(MNEProtocol.KeyEnter);

                    ////BANK CHARGE
                    if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {

                        mne.setField("CMDTP", "KEY"); //CMDTP KEY
                        mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                        mne.setField("WFSLOP", "21");
                        mne.setField("FCS", "WFSLOP");
                        mne.setField("WFQTTP", "1");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.getMsg() != null) {
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                        if (mne.panel.equals("ARS110/G1")) {
                            mne.setField("CMDTP", "KEY"); //CMDTP KEY
                            mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WGCUAM", RSL_GR.getString("HR_BKCHG").trim());
                            mne.setField("WGAIT7", "ARS110");
                            mne.setField("FCS", "WGCUAM");
                            mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                    }
////

                    ResultSet rsl_line = cgd.LineReceipt(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim());
                    while (rsl_line.next()) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
//                        mne.setField("WFPYNO", "TH28050014");
                        mne.setField("FCS", "WFCINO");
                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.getMsg() != null) {
                            System.out.println(mne.getMsg());
                            mne.pressKey(MNEProtocol.KeyEnter);
                            System.out.println(RSL_GR.getString("HC_RCNO").trim());
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "F18");
                            mne.setField("FCS", "WFCINO");
                            mne.pressKey("F18");
                            mne.pressKey(MNEProtocol.KeyEnter);
                            mne.pressKey(MNEProtocol.KeyEnter);
                            mne.closeProgram(ARS110);
                        }

                        mne.setField("CMDTP", "LSTOPT");
                        mne.setField("CMDVAL", "11");
                        mne.setField("FCS", "WFCINO");
                        mne.setField("SELROWS", "R1");
                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());
                        mne.selectOption("11");
                        System.out.println(mne.getMsg());

                        if (mne.panel.equals("ARS110/H")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WHCUAM");
                            mne.setField("WHCUAM", rsl_line.getString("LR_REAMT").trim());
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                        lastinv = rsl_line.getString("LR_INVNO").trim();
                    }

                    Double BL = cgd.Call_Balance(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim());
//                    BL += cgd.Call_Expense(RSL_GR.getString("HC_RCNO").trim());

                    if (BL > 2) {
                        if (mne.panel.equals("ARS110/F1")) {

                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WFCINO", lastinv);

                            mne.setField("CMDTP", "LSTOPT");
                            mne.setField("CMDVAL", "12");
                            mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                            mne.setField("FCS", "WFCINO");
                            mne.setField("SELROWS", "R1");
                            mne.setField("WFCINO", lastinv);
                            mne.selectOption("12");

                        }

                        if (mne.panel.equals("ARS110/I")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WIAIT3");
                            mne.setField("WIAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.setField("WIAIT3", "NON");
                            mne.pressKey(MNEProtocol.KeyEnter);

                        }

                    } else if ((BL <= 2 && BL >= -2) && BL != 0) {
//                    } else if (BL <= 2 && BL>0) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFSLOP", "23");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.panel.equals("ARS110/G1")) {

                            if (mne.getMsg() != null) {
                                mne.pressKey(MNEProtocol.KeyEnter);
                            }

                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WGAIT2");

                            if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                                mne.setField("WGCUAM", cfm.Double2digitReturn(BL));
                            }

                            mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                    }

                    mne.setField("CMDTP", "KEY");
                    mne.setField("CMDVAL", "F3");
                    mne.pressKey(MNEProtocol.KeyF03);

                    if (mne.getMsg().trim().equalsIgnoreCase("Left to distribute does not equal 0")) {
                        System.out.println(mne.getMsg());
                        mne.pressKey(MNEProtocol.KeyEnter);
                        System.out.println(RSL_GR.getString("HC_RCNO").trim());
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "F18");
                        mne.setField("FCS", "WFCINO");
                        mne.pressKey("F18");
                        mne.pressKey(MNEProtocol.KeyEnter);
                        mne.pressKey(MNEProtocol.KeyEnter);
                    }

                    if (mne.getMsg() != null) {
                        System.out.println(mne.getMsg());
                    }
                    String[] Vouchermss = mne.getMsg().toString().split(" ");
                    String Voucher = Vouchermss[2].trim();

                    if (Voucher.length() == 8) {
                        csd.UpdataVoucher(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim(), Voucher);
                        csd.InsertVoucherExpense(CONO, DIVI, RCNO.trim(), Voucher);
                    }
                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.panel);
                }

            }

            mne.closeProgram(ARS110);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public static void ARS110_CHECK_CLEAR_EXPENSEnew(int CONO, String DIVI, ArrayList<String> arrRC, String TYPE, String FNNO) {
        String LoginUrlConnectionm3 = GBVAR.LoginUrlConnectionm3;
        String LoginM3User = "MVXSECOFR";
        String LoginM3Password = "lawson@90";
        String LoginAppServer = "192.200.9.190";
        int LoginAppPort = GBVAR.DBPORT;
//        int LoginAppPort = 16305; // TST

        try {
            ClassGetData cgd = new ClassGetData();
            ClassSetData csd = new ClassSetData();
            ClassFormatData cfd = new ClassFormatData();
            int cono = CONO;
            String divi = DIVI.trim();
            String lastinv = "";

            MNEHelper mne = new MNEHelper(LoginAppServer, LoginAppPort, LoginUrlConnectionm3);
            mne.logInToM3(cono, divi, LoginM3User, LoginM3Password);

            if (!mne.logInToM3(cono, divi, LoginM3User, LoginM3Password)) {
                System.out.println("Can not login to M3 system");
            }

            String ARS110 = mne.runM3Pgm("ARS110");

            if ((ARS110).equals("")) {
                System.out.println(" ไม่สามารถเปิดโปรแกรม ARS110 ได้");
                mne.closeProgram(ARS110);
            }

            ResultSet RSL_GR = cgd.GetReceipt_EXP(CONO, DIVI, arrRC.get(0), TYPE);

//            ResultSet RSL_FNC = cgd.GET_FNC_REF1(CONO, DIVI, RCNO);
//            String FNC = "", REF1 = "";
//            while (RSL_FNC.next()) {
//                FNC = RSL_FNC.getString("RL_FNC").trim();
//                REF1 = RSL_FNC.getString("RL_REF1").trim();
//            }
            System.out.println("Running");
            ClassFormatData cfm = new ClassFormatData();

            if (mne.panel.equals("ARS110/B")) {
                mne.setField("CMDTP", "KEY");
                mne.setField("CMDVAL", "ENTER");
                mne.setField("FCS", "WAFNCN");
                mne.setField("WAFNCN", "611");
                mne.pressKey(MNEProtocol.KeyEnter);

                mne.setField("CMDTP", "1"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                mne.setField("SELROWS", "R1"); //CMDTP
                mne.setField("FCS", "WAFNCN"); //CMDVAL ENTER
                mne.setField("WAFNCN", "611"); //FCS W1OBKV
//              mne.pressKey(MNEProtocol.KeyEnter);
                mne.selectOption("1");
                System.out.println(mne.getMsg());
            }

            while (RSL_GR.next()) {

                if (mne.panel.equals("ARS112/E")) {
                    // System.out.println("CRS610/E");
                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("FCS", "WEAIT6"); //
                    mne.setField("WECUAM", "0.00"); //amt
//                    mne.setField("WEACDT", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //customer
                    mne.setField("WEDTP5", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //invoice no
                    mne.setField("WEAIT4", "L/S"); //ACC date
                    mne.setField("WEAIT5", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //ACC date
                    mne.setField("WEAIT6", FNNO); //ACC date

                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.getMsg());
                    // JOptionPane.showMessageDialog(null, mne.getMsg());

                }
                if (mne.panel.equals("ARS110/F1")) {
                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim()); //
                    mne.setField("FCS", "WFPYNO"); //
                    mne.pressKey(MNEProtocol.KeyEnter);

                    //////// EXPENSE ////////
                    ResultSet rsl_expense = cgd.LineExpensenew(CONO, DIVI, FNNO);
                    while (rsl_expense.next()) {

                        mne.setField("CMDTP", "KEY"); //CMDTP KEY
                        mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                        mne.setField("WFSLOP", rsl_expense.getString("RE_OPTION").trim());
                        mne.setField("FCS", "WFSLOP");
                        mne.setField("WFQTTP", "1");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.getMsg() != null) {
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                        if (mne.panel.equals("ARS110/G1")) {
                            mne.setField("CMDTP", "KEY"); //CMDTP KEY
                            mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WGCUAM", rsl_expense.getString("LRE_AMT").trim());
                            mne.setField("WGAIT1", rsl_expense.getString("LRE_ACTCODE").trim());
                            mne.setField("WGAIT4", cgd.Expense_CustomerName(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.setField("WGVTXT", cgd.Expense_text(CONO, DIVI, rsl_expense.getString("LRE_CODE").trim()));

                            mne.setField("FCS", "WGCUAM");

                            if (!rsl_expense.getString("LRE_CODE").trim().equalsIgnoreCase("56") && !rsl_expense.getString("LRE_CODE").trim().equalsIgnoreCase("57") && !rsl_expense.getString("LRE_CODE").trim().equalsIgnoreCase("70")) {
                                mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            }
//                            if (rsl_expense.getString("LRE_CODE").trim().equalsIgnoreCase("70")) {
//                                mne.setField("WGAIT3", "COVID");
//                            }

                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                    }

/////////////////////////1
//                    Double ExpenseBalance = cfd.Double2digitReturn(cgd.Call_Expense(RSL_GR.getString("HC_RCNO").trim()));
                    ResultSet rsl_line = cgd.LineClearExpensenew(CONO, DIVI, FNNO.trim());

                    while (rsl_line.next()) {

                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                        mne.setField("FCS", "WFCUCD");
                        mne.setField("WFCINO", rsl_line.getString("LREP_INVNO").trim());
                        mne.pressKey(MNEProtocol.KeyEnter);

                        mne.setField("CMDTP", "LSTOPT");
                        mne.setField("CMDVAL", "11");
                        mne.setField("FCS", "WFCINO");
                        mne.setField("SELROWS", "R1");
                        mne.setField("WFCINO", rsl_line.getString("LREP_INVNO").trim());
                        mne.selectOption("11");
                        System.out.println(mne.getMsg());

                        if (mne.getMsg() != null) {
                            System.out.println(mne.getMsg());
                            mne.pressKey(MNEProtocol.KeyEnter);
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "F18");
                            mne.setField("FCS", "WFCINO");
                            mne.pressKey("F18");
                            mne.pressKey(MNEProtocol.KeyEnter);
                            mne.pressKey(MNEProtocol.KeyEnter);
//                            mne.closeProgram(ARS110);
                        }

                        if (mne.panel.equals("ARS110/H")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WHCUAM");
                            mne.setField("WHCUAM", cfd.Double2digitReturn(rsl_line.getDouble("LREP_EXPAMT")));
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
//                        lastinv = rsl_line.getString("LR_INVNO").trim();
                    }

//                    double sumpp = cgd.sumLineExpensenew(CONO, DIVI, FNNO);
//                    double amtp = rsl_line.getDouble("LREP_INVAMT") - sumpp;
//                    if (amtp >= -2 && amtp <= 2) {
                    //// SH ////
//                    mne.setField("CMDTP", "KEY");
//                    mne.setField("CMDVAL", "ENTER");
//                    mne.setField("WFSLOP", "23");
//                    mne.pressKey(MNEProtocol.KeyEnter);
//
//                    if (mne.panel.equals("ARS110/G1")) {
//                        if (mne.getMsg() != null) {
//                            mne.pressKey(MNEProtocol.KeyEnter);
//                            mne.setField("CMDTP", "KEY");
//                            mne.setField("CMDVAL", "ENTER");
//                            mne.setField("FCS", "WGAIT2");
//
//                        } else {
//                            mne.setField("CMDTP", "KEY");
//                            mne.setField("CMDVAL", "ENTER");
//                            mne.setField("FCS", "WGAIT2");
//                        }
//                        mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
//                        mne.pressKey(MNEProtocol.KeyEnter);
//                        if (mne.getMsg() != null) {
//                            if (mne.getMsg().trim().equalsIgnoreCase("Amount must be entered")) {
//                                mne.pressKey(MNEProtocol.KeyEnter);
//                                mne.setField("CMDTP", "KEY");
//                                mne.setField("CMDVAL", "F12");
//                                mne.setField("FCS", "WGCUAM");
//                                mne.pressKey("F12");
//                            }
//                        }
//                    }
///// SH ////
//                    }
                    mne.setField("CMDTP", "KEY");
                    mne.setField("CMDVAL", "F3");
                    mne.pressKey(MNEProtocol.KeyF03);

                    if (mne.getMsg().trim().equalsIgnoreCase("Left to distribute does not equal 0")) {
                        System.out.println(mne.getMsg());
                        mne.pressKey(MNEProtocol.KeyEnter);
                        System.out.println(RSL_GR.getString("HC_RCNO").trim());
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "F18");
                        mne.setField("FCS", "WFCINO");
                        mne.pressKey("F18");
                        mne.pressKey(MNEProtocol.KeyEnter);
                        mne.pressKey(MNEProtocol.KeyEnter);
                    }

                    if (mne.getMsg() != null) {
                        System.out.println(mne.getMsg());
                    }
                    String[] Vouchermss = mne.getMsg().toString().split(" ");
                    String Voucher = Vouchermss[2].trim();

                    if (Voucher.length() == 8) {
                        for (int counter = 0; counter < arrRC.size(); counter++) {

                            csd.UpdataVoucherClearnew(CONO, DIVI, arrRC.get(counter), Voucher, FNNO);
                        }
                    }
                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.panel);
                }
            }

            mne.closeProgram(ARS110);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public static void ARS110_CHECK_CLEAR_EXPENSE(int CONO, String DIVI, String RCNO, String TYPE) {
        String LoginUrlConnectionm3 = GBVAR.LoginUrlConnectionm3;
        String LoginM3User = "MVXSECOFR";
        String LoginM3Password = "lawson@90";
        String LoginAppServer = "192.200.9.190";
        int LoginAppPort = GBVAR.DBPORT;
//        int LoginAppPort = 16305; // TST

        try {
            ClassGetData cgd = new ClassGetData();
            ClassSetData csd = new ClassSetData();
            ClassFormatData cfd = new ClassFormatData();
            int cono = CONO;
            String divi = DIVI.trim();
            String lastinv = "";

            MNEHelper mne = new MNEHelper(LoginAppServer, LoginAppPort, LoginUrlConnectionm3);
            mne.logInToM3(cono, divi, LoginM3User, LoginM3Password);

            if (!mne.logInToM3(cono, divi, LoginM3User, LoginM3Password)) {
                System.out.println("Can not login to M3 system");
            }

            String ARS110 = mne.runM3Pgm("ARS110");

            if ((ARS110).equals("")) {
                System.out.println(" ไม่สามารถเปิดโปรแกรม ARS110 ได้");
                mne.closeProgram(ARS110);
            }

            ResultSet RSL_GR = cgd.GetReceipt_EXP(CONO, DIVI, RCNO, TYPE);

//            ResultSet RSL_FNC = cgd.GET_FNC_REF1(CONO, DIVI, RCNO);
//            String FNC = "", REF1 = "";
//            while (RSL_FNC.next()) {
//                FNC = RSL_FNC.getString("RL_FNC").trim();
//                REF1 = RSL_FNC.getString("RL_REF1").trim();
//            }
            System.out.println("Running");
            ClassFormatData cfm = new ClassFormatData();

            if (mne.panel.equals("ARS110/B")) {
                mne.setField("CMDTP", "KEY");
                mne.setField("CMDVAL", "ENTER");
                mne.setField("FCS", "WAFNCN");
                mne.setField("WAFNCN", "611");
                mne.pressKey(MNEProtocol.KeyEnter);

                mne.setField("CMDTP", "1"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                mne.setField("SELROWS", "R1"); //CMDTP
                mne.setField("FCS", "WAFNCN"); //CMDVAL ENTER
                mne.setField("WAFNCN", "611"); //FCS W1OBKV
//              mne.pressKey(MNEProtocol.KeyEnter);
                mne.selectOption("1");
                System.out.println(mne.getMsg());
            }

            while (RSL_GR.next()) {

                if (mne.panel.equals("ARS112/E")) {
                    // System.out.println("CRS610/E");
                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("FCS", "WEAIT6"); //
                    mne.setField("WECUAM", "0.00"); //amt
//                    mne.setField("WEACDT", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //customer
                    mne.setField("WEDTP5", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //invoice no
                    mne.setField("WEAIT4", "L/S"); //ACC date
                    mne.setField("WEAIT5", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //ACC date
                    mne.setField("WEAIT6", RSL_GR.getString("HC_RCNO").trim()); //ACC date

                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.getMsg());
                    // JOptionPane.showMessageDialog(null, mne.getMsg());

                }
                if (mne.panel.equals("ARS110/F1")) {
                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim()); //
                    mne.setField("FCS", "WFPYNO"); //
                    mne.pressKey(MNEProtocol.KeyEnter);

                    //////// EXPENSE ////////
                    ResultSet rsl_expense = cgd.LineExpense(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim());
                    while (rsl_expense.next()) {

                        mne.setField("CMDTP", "KEY"); //CMDTP KEY
                        mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                        mne.setField("WFSLOP", rsl_expense.getString("RE_OPTION").trim());
                        mne.setField("FCS", "WFSLOP");
                        mne.setField("WFQTTP", "1");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.getMsg() != null) {
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                        if (mne.panel.equals("ARS110/G1")) {
                            mne.setField("CMDTP", "KEY"); //CMDTP KEY
                            mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WGCUAM", rsl_expense.getString("LRE_AMT").trim());
                            mne.setField("WGAIT1", rsl_expense.getString("LRE_ACTCODE").trim());
                            mne.setField("WGAIT4", cgd.Expense_CustomerName(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.setField("WGVTXT", cgd.Expense_text(CONO, DIVI, rsl_expense.getString("LRE_CODE").trim()));

                            mne.setField("FCS", "WGCUAM");

                            if (!rsl_expense.getString("LRE_CODE").trim().equalsIgnoreCase("56") && !rsl_expense.getString("LRE_CODE").trim().equalsIgnoreCase("57") && !rsl_expense.getString("LRE_CODE").trim().equalsIgnoreCase("70")) {
                                mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            }
//                            if (rsl_expense.getString("LRE_CODE").trim().equalsIgnoreCase("70")) {
//                                mne.setField("WGAIT3", "COVID");
//                            }

                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                    }

/////////////////////////
//                    Double ExpenseBalance = cfd.Double2digitReturn(cgd.Call_Expense(RSL_GR.getString("HC_RCNO").trim()));
                    ResultSet rsl_line = cgd.LineClearExpense(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim());

                    while (rsl_line.next()) {

                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                        mne.setField("FCS", "WFCUCD");
                        mne.setField("WFCINO", rsl_line.getString("LREP_INVNO").trim());
                        mne.pressKey(MNEProtocol.KeyEnter);

                        mne.setField("CMDTP", "LSTOPT");
                        mne.setField("CMDVAL", "11");
                        mne.setField("FCS", "WFCINO");
                        mne.setField("SELROWS", "R1");
                        mne.setField("WFCINO", rsl_line.getString("LREP_INVNO").trim());
                        mne.selectOption("11");
                        System.out.println(mne.getMsg());

                        if (mne.getMsg() != null) {
                            System.out.println(mne.getMsg());
                            mne.pressKey(MNEProtocol.KeyEnter);
                            System.out.println(RSL_GR.getString("HC_RCNO").trim());
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "F18");
                            mne.setField("FCS", "WFCINO");
                            mne.pressKey("F18");
                            mne.pressKey(MNEProtocol.KeyEnter);
                            mne.pressKey(MNEProtocol.KeyEnter);
//                            mne.closeProgram(ARS110);
                        }

                        if (mne.panel.equals("ARS110/H")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WHCUAM");
                            mne.setField("WHCUAM", cfd.Double2digitReturn(rsl_line.getDouble("LREP_EXPAMT")));
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
//                        lastinv = rsl_line.getString("LR_INVNO").trim();
                    }

                    //// SH ////
//                    mne.setField("CMDTP", "KEY");
//                    mne.setField("CMDVAL", "ENTER");
//                    mne.setField("WFSLOP", "23");
//                    mne.pressKey(MNEProtocol.KeyEnter);
//
//                    if (mne.panel.equals("ARS110/G1")) {
//                        if (mne.getMsg() != null) {
//                            mne.pressKey(MNEProtocol.KeyEnter);
//                            mne.setField("CMDTP", "KEY");
//                            mne.setField("CMDVAL", "ENTER");
//                            mne.setField("FCS", "WGAIT2");
//
//                        } else {
//                            mne.setField("CMDTP", "KEY");
//                            mne.setField("CMDVAL", "ENTER");
//                            mne.setField("FCS", "WGAIT2");
//                        }
//                        mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
//                        mne.pressKey(MNEProtocol.KeyEnter);
//                        if (mne.getMsg() != null) {
//                            if (mne.getMsg().trim().equalsIgnoreCase("Amount must be entered")) {
//                                mne.pressKey(MNEProtocol.KeyEnter);
//                                mne.setField("CMDTP", "KEY");
//                                mne.setField("CMDVAL", "F12");
//                                mne.setField("FCS", "WGCUAM");
//                                mne.pressKey("F12");
//                            }
//                        }
//                    }
///// SH ////
                    mne.setField("CMDTP", "KEY");
                    mne.setField("CMDVAL", "F3");
                    mne.pressKey(MNEProtocol.KeyF03);

                    if (mne.getMsg().trim().equalsIgnoreCase("Left to distribute does not equal 0")) {
                        System.out.println(mne.getMsg());
                        mne.pressKey(MNEProtocol.KeyEnter);
                        System.out.println(RSL_GR.getString("HC_RCNO").trim());
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "F18");
                        mne.setField("FCS", "WFCINO");
                        mne.pressKey("F18");
                        mne.pressKey(MNEProtocol.KeyEnter);
                        mne.pressKey(MNEProtocol.KeyEnter);
                    }

                    if (mne.getMsg() != null) {
                        System.out.println(mne.getMsg());
                    }
                    String[] Vouchermss = mne.getMsg().toString().split(" ");
                    String Voucher = Vouchermss[2].trim();

                    if (Voucher.length() == 8) {
                        csd.UpdataVoucherClear(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim(), Voucher);
                    }
                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.panel);
                }
            }

            mne.closeProgram(ARS110);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public static void Update_OCUSMA(int CONO, String DIVI, String RCNO, String Type, String check) throws Exception {
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            Statement stmt = conn.createStatement();
            ClassGetData cgd = new ClassGetData();
            ResultSet RSL_GR = cgd.GetReceiptUpdateOCUSMA(CONO, DIVI, RCNO, Type);
            String cuno = "";
            String query = "";

            while (RSL_GR.next()) {
                cuno = RSL_GR.getString("HC_PYNO").trim();
            }

            if (check.equals("1")) {
                query = "UPDATE " + GBVAR.M3DB + ".OCUSMA\n"
                        + "SET OKCCUS = '" + RCNO.trim() + "' \n"
                        + "WHERE OKCONO = '10' AND OKCUNO = '" + cuno.trim() + "' ";
            } else {
                query = "UPDATE " + GBVAR.M3DB + ".OCUSMA\n"
                        + "SET OKCCUS = ' ' \n"
                        + "WHERE OKCONO = '10' AND OKCUNO = '" + cuno.trim() + "' ";
            }
            System.out.println(query);
            stmt.execute(query);
        } catch (Exception ex) {
            Logger.getLogger(ARS110.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public static void ARS110_CASH_DEPOSITnew(int CONO, String DIVI, ArrayList<String> arrRC, String TYPE, String FNNO) {
        String LoginUrlConnectionm3 = GBVAR.LoginUrlConnectionm3;
        String LoginM3User = "MVXSECOFR";
        String LoginM3Password = "lawson@90";
        String LoginAppServer = "192.200.9.190";
        int LoginAppPort = GBVAR.DBPORT;
//        int LoginAppPort = 16305; // TST

        try {
            ClassGetData cgd = new ClassGetData();
            ClassSetData csd = new ClassSetData();
            int cono = CONO;
            String divi = DIVI.trim();
            String lastinv = "";

            MNEHelper mne = new MNEHelper(LoginAppServer, LoginAppPort, LoginUrlConnectionm3);
            mne.logInToM3(cono, divi, LoginM3User, LoginM3Password);

            if (!mne.logInToM3(cono, divi, LoginM3User, LoginM3Password)) {
                System.out.println("Can not login to M3 system");
            }

            String ARS110 = mne.runM3Pgm("ARS110");

            if ((ARS110).equals("")) {
                System.out.println(" ไม่สามารถเปิดโปรแกรม ARS110 ได้");
                mne.closeProgram(ARS110);
            }

            //todocashdeposit
            ResultSet RSL_GR = cgd.GetReceiptnewdeposit(CONO, DIVI, arrRC.get(0), TYPE);
            ResultSet RSL_FNC = cgd.GET_FNC_REF1(CONO, DIVI, arrRC.get(0));
            String FNC = "", REF1 = "";
            while (RSL_FNC.next()) {
                FNC = RSL_FNC.getString("RL_FNC").trim();
                REF1 = RSL_FNC.getString("RL_REF1").trim();
            }

            System.out.println("Running");
            ClassFormatData cfm = new ClassFormatData();

            if (mne.panel.equals("ARS110/B")) {
                mne.setField("CMDTP", "KEY");
                mne.setField("CMDVAL", "ENTER");
                mne.setField("FCS", "WAFNCN");
                mne.setField("WAFNCN", FNC);
                mne.pressKey(MNEProtocol.KeyEnter);

                mne.setField("CMDTP", "1"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                mne.setField("SELROWS", "R1"); //CMDTP
                mne.setField("FCS", "WAFNCN"); //CMDVAL ENTER
                mne.setField("WAFNCN", FNC); //FCS W1OBKV
//              mne.pressKey(MNEProtocol.KeyEnter);
                mne.selectOption("1");
                System.out.println(mne.getMsg());
            }

            while (RSL_GR.next()) {

                if (mne.panel.equals("ARS112/E")) {
                    // System.out.println("CRS610/E");
                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("FCS", "WEAIT6"); //
                    mne.setField("WECUAM", RSL_GR.getString("HC_REAMT").trim()); //amt
                    mne.setField("WEACDT", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //customer
                    mne.setField("WEDTP5", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //invoice no
                    mne.setField("WEAIT4", REF1); //ACC date
                    mne.setField("WEAIT5", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //ACC date
                    mne.setField("WEAIT6", FNNO); //ACC date

                    mne.setField("WEVTXT", RSL_GR.getString("HC_PYNO").trim()); //ACC date

                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.getMsg());
                    // JOptionPane.showMessageDialog(null, mne.getMsg());

                }
                if (mne.panel.equals("ARS110/F1")) {
                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim()); //
                    mne.setField("FCS", "WFPYNO"); //
                    mne.pressKey(MNEProtocol.KeyEnter);

                    ResultSet rsl_line = cgd.LineReceiptnew(CONO, DIVI, arrRC);
                    if (rsl_line.next()) {
                        ResultSet rsl_DEPOSIT = cgd.GetDetailDEPOSIT(CONO, DIVI, rsl_line.getString("LR_INVNO").trim(), rsl_line.getString("LR_INVDT").trim());

                        if (rsl_DEPOSIT.next()) {
                            if (!rsl_DEPOSIT.getString("Remark").trim().equals("-/-")) {
                                mne.setField("CMDTP", "KEY");
                                mne.setField("CMDVAL", "ENTER");
                                mne.setField("WFSLOP", "26");
                                mne.pressKey(MNEProtocol.KeyEnter);
                                String[] Remark = rsl_DEPOSIT.getString("Remark").trim().split("/");

                                System.out.println("show remark");
                                System.out.println(Remark);
                                System.out.println(Remark[0]);
                                if (mne.panel.equals("ARS110/G1")) {
                                    mne.setField("CMDTP", "KEY");
                                    mne.setField("CMDVAL", "ENTER");
                                    mne.setField("FCS", "WGAIT2");

                                    mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                                    mne.setField("WGAIT4", Remark[0]);
                                    mne.setField("WGAIT5", rsl_DEPOSIT.getString("uaivno").trim());
                                    mne.setField("WGAIT6", "01");

                                    mne.setField("WGVTXT", rsl_DEPOSIT.getString("VCTXT").trim());
                                    mne.pressKey(MNEProtocol.KeyEnter);

                                }
                            }
                        }
                    }
                    mne.setField("CMDTP", "KEY");
                    mne.setField("CMDVAL", "F3");
                    mne.pressKey(MNEProtocol.KeyF03);

                    if (mne.getMsg().trim().equalsIgnoreCase("Left to distribute does not equal 0")) {
                        System.out.println(mne.getMsg());
                        mne.pressKey(MNEProtocol.KeyEnter);
                        System.out.println(RSL_GR.getString("HC_RCNO").trim());
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "F18");
                        mne.setField("FCS", "WFCINO");
                        mne.pressKey("F18");
                        mne.pressKey(MNEProtocol.KeyEnter);
                        mne.pressKey(MNEProtocol.KeyEnter);
                    }

                    if (mne.getMsg() != null) {
                        System.out.println(mne.getMsg());
                    }
                    String[] Vouchermss = mne.getMsg().toString().split(" ");
                    String Voucher = Vouchermss[2].trim();

                    for (int counter = 0; counter < arrRC.size(); counter++) {
                        csd.UpdataVouchernew(CONO, DIVI, arrRC.get(counter), Voucher, FNNO);
                    }
                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.panel);
                }
            }

            mne.closeProgram(ARS110);

        } catch (Exception e) {
            System.out.println(e.toString());

        }

    }

    public static void ARS110_CASH_DEPOSIT(int CONO, String DIVI, String RCNO, String TYPE) {

        String LoginUrlConnectionm3 = GBVAR.LoginUrlConnectionm3;
        String LoginM3User = "MVXSECOFR";
        String LoginM3Password = "lawson@90";
        String LoginAppServer = "192.200.9.190";
        int LoginAppPort = GBVAR.DBPORT;
//        int LoginAppPort = 16305; // TST

        try {
            ClassGetData cgd = new ClassGetData();
            ClassSetData csd = new ClassSetData();
            int cono = CONO;
            String divi = DIVI.trim();
            String lastinv = "";

            MNEHelper mne = new MNEHelper(LoginAppServer, LoginAppPort, LoginUrlConnectionm3);
            mne.logInToM3(cono, divi, LoginM3User, LoginM3Password);

            if (!mne.logInToM3(cono, divi, LoginM3User, LoginM3Password)) {
                System.out.println("Can not login to M3 system");
            }

            String ARS110 = mne.runM3Pgm("ARS110");

            if ((ARS110).equals("")) {
                System.out.println(" ไม่สามารถเปิดโปรแกรม ARS110 ได้");
                mne.closeProgram(ARS110);
            }

            ResultSet RSL_GR = cgd.GetReceipt(CONO, DIVI, RCNO, TYPE);

            ResultSet RSL_FNC = cgd.GET_FNC_REF1(CONO, DIVI, RCNO);
            String FNC = "", REF1 = "";
            while (RSL_FNC.next()) {
                FNC = RSL_FNC.getString("RL_FNC").trim();
                REF1 = RSL_FNC.getString("RL_REF1").trim();
            }

            System.out.println("Running");
            ClassFormatData cfm = new ClassFormatData();

            if (mne.panel.equals("ARS110/B")) {
                mne.setField("CMDTP", "KEY");
                mne.setField("CMDVAL", "ENTER");
                mne.setField("FCS", "WAFNCN");
                mne.setField("WAFNCN", FNC);
                mne.pressKey(MNEProtocol.KeyEnter);

                mne.setField("CMDTP", "1"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                mne.setField("SELROWS", "R1"); //CMDTP
                mne.setField("FCS", "WAFNCN"); //CMDVAL ENTER
                mne.setField("WAFNCN", FNC); //FCS W1OBKV
//              mne.pressKey(MNEProtocol.KeyEnter);
                mne.selectOption("1");
                System.out.println(mne.getMsg());
            }

            while (RSL_GR.next()) {

                if (mne.panel.equals("ARS112/E")) {
                    // System.out.println("CRS610/E");
                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("FCS", "WEAIT6"); //
                    mne.setField("WECUAM", RSL_GR.getString("HC_REAMT").trim()); //amt
                    mne.setField("WEACDT", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //customer
                    mne.setField("WEDTP5", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //invoice no
                    mne.setField("WEAIT4", REF1); //ACC date
                    mne.setField("WEAIT5", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //ACC date
                    mne.setField("WEAIT6", RSL_GR.getString("HC_RCNO").trim()); //ACC date

                    mne.setField("WEVTXT", RSL_GR.getString("HC_PYNO").trim()); //ACC date

                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.getMsg());
                    // JOptionPane.showMessageDialog(null, mne.getMsg());

                }
                if (mne.panel.equals("ARS110/F1")) {
                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim()); //
                    mne.setField("FCS", "WFPYNO"); //
                    mne.pressKey(MNEProtocol.KeyEnter);

                    ResultSet rsl_line = cgd.LineReceipt(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim());
                    if (rsl_line.next()) {
                        ResultSet rsl_DEPOSIT = cgd.GetDetailDEPOSIT(CONO, DIVI, rsl_line.getString("LR_INVNO").trim(), rsl_line.getString("LR_INVDT").trim());

                        if (rsl_DEPOSIT.next()) {
                            if (!rsl_DEPOSIT.getString("Remark").trim().equals("-/-")) {
                                mne.setField("CMDTP", "KEY");
                                mne.setField("CMDVAL", "ENTER");
                                mne.setField("WFSLOP", "26");
                                mne.pressKey(MNEProtocol.KeyEnter);
                                String[] Remark = rsl_DEPOSIT.getString("Remark").trim().split("/");

                                if (mne.panel.equals("ARS110/G1")) {
                                    mne.setField("CMDTP", "KEY");
                                    mne.setField("CMDVAL", "ENTER");
                                    mne.setField("FCS", "WGAIT2");

                                    mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                                    mne.setField("WGAIT4", Remark[0]);
                                    mne.setField("WGAIT5", rsl_DEPOSIT.getString("uaivno").trim());
                                    mne.setField("WGAIT6", "01");

                                    mne.setField("WGVTXT", rsl_DEPOSIT.getString("VCTXT").trim());
                                    mne.pressKey(MNEProtocol.KeyEnter);

                                }
                            }
                        }
                    }
                    mne.setField("CMDTP", "KEY");
                    mne.setField("CMDVAL", "F3");
                    mne.pressKey(MNEProtocol.KeyF03);

                    if (mne.getMsg().trim().equalsIgnoreCase("Left to distribute does not equal 0")) {
                        System.out.println(mne.getMsg());
                        mne.pressKey(MNEProtocol.KeyEnter);
                        System.out.println(RSL_GR.getString("HC_RCNO").trim());
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "F18");
                        mne.setField("FCS", "WFCINO");
                        mne.pressKey("F18");
                        mne.pressKey(MNEProtocol.KeyEnter);
                        mne.pressKey(MNEProtocol.KeyEnter);
                    }

                    if (mne.getMsg() != null) {
                        System.out.println(mne.getMsg());
                    }
                    String[] Vouchermss = mne.getMsg().toString().split(" ");
                    String Voucher = Vouchermss[2].trim();

                    if (Voucher.length() == 8) {
                        csd.UpdataVoucher(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim(), Voucher);
                    }
                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.panel);
                }
            }

            mne.closeProgram(ARS110);

        } catch (Exception e) {
            System.out.println(e.toString());

        }

    }

    public static void ARS110_TRANSFER_DEPOSITnew(int CONO, String DIVI, ArrayList<String> arrRC, String TYPE, String FNNO) {

        String LoginUrlConnectionm3 = GBVAR.LoginUrlConnectionm3;
        String LoginM3User = "MVXSECOFR";
        String LoginM3Password = "lawson@90";
        String LoginAppServer = "192.200.9.190";
        int LoginAppPort = GBVAR.DBPORT;
//        int LoginAppPort = 16305; // TST

        try {
            ClassGetData cgd = new ClassGetData();
            ClassSetData csd = new ClassSetData();
            int cono = CONO;
            String divi = DIVI.trim();
            String lastinv = "";

            MNEHelper mne = new MNEHelper(LoginAppServer, LoginAppPort, LoginUrlConnectionm3);
            mne.logInToM3(cono, divi, LoginM3User, LoginM3Password);

            if (!mne.logInToM3(cono, divi, LoginM3User, LoginM3Password)) {
                System.out.println("Can not login to M3 system");
            }

            String ARS110 = mne.runM3Pgm("ARS110");

            if ((ARS110).equals("")) {
                System.out.println(" ไม่สามารถเปิดโปรแกรม ARS110 ได้");
                mne.closeProgram(ARS110);
            }

            ResultSet RSL_GR = cgd.GetReceiptnewdeposit(CONO, DIVI, arrRC.get(0), TYPE);
            ResultSet RSL_FNC = cgd.GET_FNC_REF1(CONO, DIVI, arrRC.get(0));

            String FNC = "", REF1 = "";
            while (RSL_FNC.next()) {
                FNC = RSL_FNC.getString("RL_FNC").trim();
                REF1 = RSL_FNC.getString("RL_REF1").trim();
            }

            System.out.println("Running");
            ClassFormatData cfm = new ClassFormatData();

            if (mne.panel.equals("ARS110/B")) {
                mne.setField("CMDTP", "KEY");
                mne.setField("CMDVAL", "ENTER");
                mne.setField("FCS", "WAFNCN");
                mne.setField("WAFNCN", FNC);
                mne.pressKey(MNEProtocol.KeyEnter);

                mne.setField("CMDTP", "1"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                mne.setField("SELROWS", "R1"); //CMDTP
                mne.setField("FCS", "WAFNCN"); //CMDVAL ENTER
                mne.setField("WAFNCN", FNC); //FCS W1OBKV
//              mne.pressKey(MNEProtocol.KeyEnter);
                mne.selectOption("1");
                System.out.println(mne.getMsg());
            }

            while (RSL_GR.next()) {

                if (mne.panel.equals("ARS112/E")) {
                    // System.out.println("CRS610/E");
                    if (RSL_GR.getString("HC_BANK").trim().equalsIgnoreCase("NONE")) {
                        continue;
                    }

                    Double HC_REAMT = RSL_GR.getDouble("HC_REAMT");

                    HC_REAMT = cfm.Double2digitReturn(HC_REAMT);

/////
                    if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                        System.out.println("CASE BANK CHARGE");
                        HC_REAMT = HC_REAMT - RSL_GR.getDouble("HR_BKCHG");
                        HC_REAMT = cfm.Double2digitReturn(HC_REAMT);
                    }
/////

                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("FCS", "WEAIT6"); //
                    mne.setField("WEAIT1", RSL_GR.getString("HC_ACCODE").trim()); // ACCOUNT CODE

                    mne.setField("WECUAM", String.format("%.2f", HC_REAMT)); //amt
                    mne.setField("WEACDT", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //
                    mne.setField("WEDTP5", cfm.SubDateForM3(RSL_GR.getString("HC_PYDT"))); //
                    mne.setField("WEAIT4", REF1); //ACC date
                    mne.setField("WEAIT5", cfm.SubDateForM3(RSL_GR.getString("HC_PYDT"))); //
                    mne.setField("WEAIT6", FNNO); //

                    mne.setField("WEVTXT", RSL_GR.getString("HC_PYNO").trim());

                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.getMsg());
                    // JOptionPane.showMessageDialog(null, mne.getMsg());

                }
                if (mne.panel.equals("ARS110/F1")) {
                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim()); //
                    mne.setField("FCS", "WFPYNO"); //
                    mne.pressKey(MNEProtocol.KeyEnter);

                    ////BANK CHARGE
                    if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                        mne.setField("CMDTP", "KEY"); //CMDTP KEY
                        mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                        mne.setField("WFSLOP", "21");
                        mne.setField("FCS", "WFSLOP");
                        mne.setField("WFQTTP", "1");
                        mne.pressKey(MNEProtocol.KeyEnter);
                        if (mne.getMsg() != null) {
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                        if (mne.panel.equals("ARS110/G1")) {
                            mne.setField("CMDTP", "KEY"); //CMDTP KEY
                            mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WGCUAM", RSL_GR.getString("HR_BKCHG").trim());
                            mne.setField("WGAIT7", "ARS110");
                            mne.setField("FCS", "WGCUAM");
                            mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                    }

                    ResultSet rsl_line = cgd.LineReceiptnew(CONO, DIVI, arrRC);
                    if (rsl_line.next()) {
                        ResultSet rsl_DEPOSIT = cgd.GetDetailDEPOSIT(CONO, DIVI, rsl_line.getString("LR_INVNO").trim(), rsl_line.getString("LR_INVDT").trim());

                        if (rsl_DEPOSIT.next()) {
                            if (!rsl_DEPOSIT.getString("Remark").trim().equals("-/-")) {
                                mne.setField("CMDTP", "KEY");
                                mne.setField("CMDVAL", "ENTER");
                                mne.setField("WFSLOP", "26");
                                mne.pressKey(MNEProtocol.KeyEnter);
                                String[] Remark = rsl_DEPOSIT.getString("Remark").trim().split("/");

                                if (mne.panel.equals("ARS110/G1")) {
                                    mne.setField("CMDTP", "KEY");
                                    mne.setField("CMDVAL", "ENTER");
                                    mne.setField("FCS", "WGAIT2");

                                    mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                                    mne.setField("WGAIT4", Remark[0]);
                                    mne.setField("WGAIT5", rsl_DEPOSIT.getString("uaivno").trim());
                                    mne.setField("WGAIT6", "01");

                                    mne.setField("WGVTXT", rsl_DEPOSIT.getString("VCTXT").trim());
                                    mne.pressKey(MNEProtocol.KeyEnter);

                                }
                            }
                        }
                    }

                    mne.setField("CMDTP", "KEY");
                    mne.setField("CMDVAL", "F3");
                    mne.pressKey(MNEProtocol.KeyF03);

                    if (mne.getMsg().trim().equalsIgnoreCase("Left to distribute does not equal 0")) {
                        System.out.println(mne.getMsg());
                        mne.pressKey(MNEProtocol.KeyEnter);
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "F18");
                        mne.setField("FCS", "WFCINO");
                        mne.pressKey("F18");
                        mne.pressKey(MNEProtocol.KeyEnter);
                        mne.pressKey(MNEProtocol.KeyEnter);
                    }

                    if (mne.getMsg() != null) {
                        System.out.println(mne.getMsg());
                    }
                    String[] Vouchermss = mne.getMsg().toString().split(" ");
                    String Voucher = Vouchermss[2].trim();

                    if (Voucher.length() == 8) {
                        for (int counter = 0; counter < arrRC.size(); counter++) {
                            csd.UpdataVouchernew(CONO, DIVI, arrRC.get(counter), Voucher, FNNO);
                    
                        }
                    }
                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.panel);
                }

            }

            mne.closeProgram(ARS110);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public static void ARS110_TRANSFER_DEPOSIT(int CONO, String DIVI, String RCNO, String TYPE) {
        String LoginUrlConnectionm3 = GBVAR.LoginUrlConnectionm3;
        String LoginM3User = "MVXSECOFR";
        String LoginM3Password = "lawson@90";
        String LoginAppServer = "192.200.9.190";
        int LoginAppPort = GBVAR.DBPORT;
//        int LoginAppPort = 16305; // TST

        try {
            ClassGetData cgd = new ClassGetData();
            ClassSetData csd = new ClassSetData();
            int cono = CONO;
            String divi = DIVI.trim();
            String lastinv = "";

            MNEHelper mne = new MNEHelper(LoginAppServer, LoginAppPort, LoginUrlConnectionm3);
            mne.logInToM3(cono, divi, LoginM3User, LoginM3Password);

            if (!mne.logInToM3(cono, divi, LoginM3User, LoginM3Password)) {
                System.out.println("Can not login to M3 system");
            }

            String ARS110 = mne.runM3Pgm("ARS110");

            if ((ARS110).equals("")) {
                System.out.println(" ไม่สามารถเปิดโปรแกรม ARS110 ได้");
                mne.closeProgram(ARS110);
            }

            ResultSet RSL_GR = cgd.GetReceipt(CONO, DIVI, RCNO, TYPE);

            ResultSet RSL_FNC = cgd.GET_FNC_REF1(CONO, DIVI, RCNO);
            String FNC = "", REF1 = "";
            while (RSL_FNC.next()) {
                FNC = RSL_FNC.getString("RL_FNC").trim();
                REF1 = RSL_FNC.getString("RL_REF1").trim();
            }

            System.out.println("Running");
            ClassFormatData cfm = new ClassFormatData();

            if (mne.panel.equals("ARS110/B")) {
                mne.setField("CMDTP", "KEY");
                mne.setField("CMDVAL", "ENTER");
                mne.setField("FCS", "WAFNCN");
                mne.setField("WAFNCN", FNC);
                mne.pressKey(MNEProtocol.KeyEnter);

                mne.setField("CMDTP", "1"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                mne.setField("SELROWS", "R1"); //CMDTP
                mne.setField("FCS", "WAFNCN"); //CMDVAL ENTER
                mne.setField("WAFNCN", FNC); //FCS W1OBKV
//              mne.pressKey(MNEProtocol.KeyEnter);
                mne.selectOption("1");
                System.out.println(mne.getMsg());
            }

            while (RSL_GR.next()) {

                if (mne.panel.equals("ARS112/E")) {
                    // System.out.println("CRS610/E");
                    if (RSL_GR.getString("HC_BANK").trim().equalsIgnoreCase("NONE")) {
                        continue;
                    }

                    Double HC_REAMT = RSL_GR.getDouble("HC_REAMT");

                    HC_REAMT = cfm.Double2digitReturn(HC_REAMT);

/////
                    if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                        System.out.println("CASE BANK CHARGE");
                        HC_REAMT = HC_REAMT - RSL_GR.getDouble("HR_BKCHG");
                        HC_REAMT = cfm.Double2digitReturn(HC_REAMT);
                    }
/////

                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("FCS", "WEAIT6"); //
                    mne.setField("WEAIT1", RSL_GR.getString("HC_ACCODE").trim()); // ACCOUNT CODE

                    mne.setField("WECUAM", String.format("%.2f", HC_REAMT)); //amt
                    mne.setField("WEACDT", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //
                    mne.setField("WEDTP5", cfm.SubDateForM3(RSL_GR.getString("HC_PYDT"))); //
                    mne.setField("WEAIT4", REF1); //ACC date
                    mne.setField("WEAIT5", cfm.SubDateForM3(RSL_GR.getString("HC_PYDT"))); //
                    mne.setField("WEAIT6", RSL_GR.getString("HC_RCNO").trim()); //

                    mne.setField("WEVTXT", RSL_GR.getString("HC_PYNO").trim());

                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.getMsg());
                    // JOptionPane.showMessageDialog(null, mne.getMsg());

                }
                if (mne.panel.equals("ARS110/F1")) {
                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim()); //
                    mne.setField("FCS", "WFPYNO"); //
                    mne.pressKey(MNEProtocol.KeyEnter);

                    ////BANK CHARGE
                    if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                        mne.setField("CMDTP", "KEY"); //CMDTP KEY
                        mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                        mne.setField("WFSLOP", "21");
                        mne.setField("FCS", "WFSLOP");
                        mne.setField("WFQTTP", "1");
                        mne.pressKey(MNEProtocol.KeyEnter);
                        if (mne.getMsg() != null) {
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                        if (mne.panel.equals("ARS110/G1")) {
                            mne.setField("CMDTP", "KEY"); //CMDTP KEY
                            mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WGCUAM", RSL_GR.getString("HR_BKCHG").trim());
                            mne.setField("WGAIT7", "ARS110");
                            mne.setField("FCS", "WGCUAM");
                            mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                    }

                    ResultSet rsl_line = cgd.LineReceipt(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim());
                    if (rsl_line.next()) {
                        ResultSet rsl_DEPOSIT = cgd.GetDetailDEPOSIT(CONO, DIVI, rsl_line.getString("LR_INVNO").trim(), rsl_line.getString("LR_INVDT").trim());

                        if (rsl_DEPOSIT.next()) {
                            if (!rsl_DEPOSIT.getString("Remark").trim().equals("-/-")) {
                                mne.setField("CMDTP", "KEY");
                                mne.setField("CMDVAL", "ENTER");
                                mne.setField("WFSLOP", "26");
                                mne.pressKey(MNEProtocol.KeyEnter);
                                String[] Remark = rsl_DEPOSIT.getString("Remark").trim().split("/");

                                if (mne.panel.equals("ARS110/G1")) {
                                    mne.setField("CMDTP", "KEY");
                                    mne.setField("CMDVAL", "ENTER");
                                    mne.setField("FCS", "WGAIT2");

                                    mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                                    mne.setField("WGAIT4", Remark[0]);
                                    mne.setField("WGAIT5", rsl_DEPOSIT.getString("uaivno").trim());
                                    mne.setField("WGAIT6", "01");

                                    mne.setField("WGVTXT", rsl_DEPOSIT.getString("VCTXT").trim());
                                    mne.pressKey(MNEProtocol.KeyEnter);

                                }
                            }
                        }
                    }

                    mne.setField("CMDTP", "KEY");
                    mne.setField("CMDVAL", "F3");
                    mne.pressKey(MNEProtocol.KeyF03);

                    if (mne.getMsg().trim().equalsIgnoreCase("Left to distribute does not equal 0")) {
                        System.out.println(mne.getMsg());
                        mne.pressKey(MNEProtocol.KeyEnter);
                        System.out.println(RSL_GR.getString("HC_RCNO").trim());
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "F18");
                        mne.setField("FCS", "WFCINO");
                        mne.pressKey("F18");
                        mne.pressKey(MNEProtocol.KeyEnter);
                        mne.pressKey(MNEProtocol.KeyEnter);
                    }

                    if (mne.getMsg() != null) {
                        System.out.println(mne.getMsg());
                    }
                    String[] Vouchermss = mne.getMsg().toString().split(" ");
                    String Voucher = Vouchermss[2].trim();

                    if (Voucher.length() == 8) {
                        csd.UpdataVoucher(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim(), Voucher);
                    }
                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.panel);
                }

            }

            mne.closeProgram(ARS110);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public static void ARS110_CHECK_DEPOSITnew(int CONO, String DIVI, ArrayList<String> arrRC, String TYPE, String FNNO) {
        String LoginUrlConnectionm3 = GBVAR.LoginUrlConnectionm3;
        String LoginM3User = "MVXSECOFR";
        String LoginM3Password = "lawson@90";
        String LoginAppServer = "192.200.9.190";
        int LoginAppPort = GBVAR.DBPORT;
//        int LoginAppPort = 16305; // TST

        try {
            ClassGetData cgd = new ClassGetData();
            ClassSetData csd = new ClassSetData();
            int cono = CONO;
            String divi = DIVI.trim();
            String lastinv = "";

            MNEHelper mne = new MNEHelper(LoginAppServer, LoginAppPort, LoginUrlConnectionm3);
            mne.logInToM3(cono, divi, LoginM3User, LoginM3Password);

            if (!mne.logInToM3(cono, divi, LoginM3User, LoginM3Password)) {
                System.out.println("Can not login to M3 system");
            }

            String ARS110 = mne.runM3Pgm("ARS110");

            if ((ARS110).equals("")) {
                System.out.println(" ไม่สามารถเปิดโปรแกรม ARS110 ได้");
                mne.closeProgram(ARS110);
            }
            ResultSet RSL_GR = cgd.GetReceiptnewdeposit(CONO, DIVI, arrRC.get(0), TYPE);
            ResultSet RSL_FNC = cgd.GET_FNC_REF1(CONO, DIVI, arrRC.get(0));

            String FNC = "", REF1 = "";
            while (RSL_FNC.next()) {
                FNC = RSL_FNC.getString("RL_FNC").trim();
                REF1 = RSL_FNC.getString("RL_REF1").trim();
            }
            Update_OCUSMAnew(cono, divi, FNNO, TYPE, REF1, FNNO);
            System.out.println("Running");
            ClassFormatData cfm = new ClassFormatData();

            if (mne.panel.equals("ARS110/B")) {
                mne.setField("CMDTP", "KEY");
                mne.setField("CMDVAL", "ENTER");
                mne.setField("FCS", "WAFNCN");
                mne.setField("WAFNCN", FNC);
                mne.pressKey(MNEProtocol.KeyEnter);

                mne.setField("CMDTP", "1"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                mne.setField("SELROWS", "R1"); //CMDTP
                mne.setField("FCS", "WAFNCN"); //CMDVAL ENTER
                mne.setField("WAFNCN", FNC); //FCS W1OBKV
//              mne.pressKey(MNEProtocol.KeyEnter);
                mne.selectOption("1");
                System.out.println(mne.getMsg());
            }

            while (RSL_GR.next()) {

                if (mne.panel.equals("ARS112/E")) {
                    // System.out.println("CRS610/E");
                    Double HC_REAMT = RSL_GR.getDouble("HC_REAMT");

                    if (RSL_GR.getString("HC_CHKNO").trim().equalsIgnoreCase("")) {
                        continue;
                    }

                    if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                        HC_REAMT = HC_REAMT - RSL_GR.getDouble("HR_BKCHG");
                    }

                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("FCS", "WEDRRN"); //
                    mne.setField("WEAIT1", RSL_GR.getString("HC_ACCODE").trim()); // ACCOUNT CODE
                    mne.setField("WEPYNO", RSL_GR.getString("HC_PYNO").trim()); //
                    mne.setField("WECUAM", String.format("%.2f", HC_REAMT)); //amt
                    mne.setField("WEDTP5", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //
                    mne.setField("WEACDT", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //
                    mne.setField("WEDRRN", RSL_GR.getString("HC_CHKNO").trim()); //ACC date
                    mne.setField("WEDUDT", cfm.SubDateForM3(RSL_GR.getString("HC_PYDT"))); //
                    mne.setField("WEBKID", RSL_GR.getString("HC_BANK").trim()); //
                    mne.setField("WEVTXT", RSL_GR.getString("HC_PYNO").trim());

                    System.out.println(RSL_GR.getString("HC_PYNO"));
                    System.out.println(String.format("%.2f", HC_REAMT));
                    System.out.println(RSL_GR.getString("HC_TRDT"));
                    System.out.println(RSL_GR.getString("HC_CHKNO").trim());
                    System.out.println(RSL_GR.getString("HC_PYDT"));
                    System.out.println(RSL_GR.getString("HC_BANK").trim());
                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.getMsg());
                    // JOptionPane.showMessageDialog(null, mne.getMsg());
                    if (mne.getMsg() != null) {
                        mne.pressKey(MNEProtocol.KeyEnter);
                        System.out.println(mne.getMsg());
                        continue;
                    }

                }
                if (mne.panel.equals("ARS110/F1")) {
                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim()); //
                    mne.setField("FCS", "WFPYNO"); //
                    mne.pressKey(MNEProtocol.KeyEnter);

                    ////BANK CHARGE
                    if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {

                        mne.setField("CMDTP", "KEY"); //CMDTP KEY
                        mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                        mne.setField("WFSLOP", "21");
                        mne.setField("FCS", "WFSLOP");
                        mne.setField("WFQTTP", "1");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.getMsg() != null) {
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                        if (mne.panel.equals("ARS110/G1")) {
                            mne.setField("CMDTP", "KEY"); //CMDTP KEY
                            mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WGCUAM", RSL_GR.getString("HR_BKCHG").trim());
                            mne.setField("WGAIT7", "ARS110");
                            mne.setField("FCS", "WGCUAM");
                            mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                    }
////
                    ResultSet rsl_line = cgd.LineReceiptnew(CONO, DIVI, arrRC);

                    if (rsl_line.next()) {
                        ResultSet rsl_DEPOSIT = cgd.GetDetailDEPOSIT(CONO, DIVI, rsl_line.getString("LR_INVNO").trim(), rsl_line.getString("LR_INVDT").trim());
                        if (rsl_DEPOSIT.next()) {
                            if (!rsl_DEPOSIT.getString("Remark").trim().equals("-/-")) {
                                mne.setField("CMDTP", "KEY");
                                mne.setField("CMDVAL", "ENTER");
                                mne.setField("WFSLOP", "26");
                                mne.pressKey(MNEProtocol.KeyEnter);
                                String[] Remark = rsl_DEPOSIT.getString("Remark").trim().split("/");

                                if (mne.panel.equals("ARS110/G1")) {
                                    mne.setField("CMDTP", "KEY");
                                    mne.setField("CMDVAL", "ENTER");
                                    mne.setField("FCS", "WGAIT2");

                                    mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                                    mne.setField("WGAIT4", Remark[0]);
                                    mne.setField("WGAIT5", rsl_DEPOSIT.getString("uaivno").trim());
                                    mne.setField("WGAIT6", "01");

                                    mne.setField("WGVTXT", rsl_DEPOSIT.getString("VCTXT").trim());
                                    mne.pressKey(MNEProtocol.KeyEnter);

                                }
                            }
                        }
                    }

                    mne.setField("CMDTP", "KEY");
                    mne.setField("CMDVAL", "F3");
                    System.out.println(mne.getMsg());

                    mne.pressKey(MNEProtocol.KeyF03);
                    System.out.println(mne.getMsg());

                    if (mne.getMsg().trim().equalsIgnoreCase("Left to distribute does not equal 0")) {
                        System.out.println(mne.getMsg());
                        mne.pressKey(MNEProtocol.KeyEnter);
//                        System.out.println(RSL_GR.getString("HC_RCNO").trim());
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "F18");
                        mne.setField("FCS", "WFCINO");
                        mne.pressKey("F18");
                        mne.pressKey(MNEProtocol.KeyEnter);
                        mne.pressKey(MNEProtocol.KeyEnter);
                    }

                    if (mne.getMsg() != null) {
                        System.out.println(mne.getMsg());
                    }
                    String[] Vouchermss = mne.getMsg().toString().split(" ");
                    String Voucher = Vouchermss[2].trim();

                    if (Voucher.length() == 8) {
                        for (int counter = 0; counter < arrRC.size(); counter++) {

//                            csd.UpdataVoucher(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim(), Voucher);
                            csd.UpdataVouchernew(CONO, DIVI, arrRC.get(counter), Voucher, FNNO);

                        }
                    }
                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.panel);
                }

            }
            mne.closeProgram(ARS110);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public static void ARS110_CHECK_DEPOSIT(int CONO, String DIVI, String RCNO, String TYPE) {
        String LoginUrlConnectionm3 = GBVAR.LoginUrlConnectionm3;
        String LoginM3User = "MVXSECOFR";
        String LoginM3Password = "lawson@90";
        String LoginAppServer = "192.200.9.190";
        int LoginAppPort = GBVAR.DBPORT;
//        int LoginAppPort = 16305; // TST

        try {
            ClassGetData cgd = new ClassGetData();
            ClassSetData csd = new ClassSetData();
            int cono = CONO;
            String divi = DIVI.trim();
            String lastinv = "";

            MNEHelper mne = new MNEHelper(LoginAppServer, LoginAppPort, LoginUrlConnectionm3);
            mne.logInToM3(cono, divi, LoginM3User, LoginM3Password);

            if (!mne.logInToM3(cono, divi, LoginM3User, LoginM3Password)) {
                System.out.println("Can not login to M3 system");
            }

            String ARS110 = mne.runM3Pgm("ARS110");

            if ((ARS110).equals("")) {
                System.out.println(" ไม่สามารถเปิดโปรแกรม ARS110 ได้");
                mne.closeProgram(ARS110);
            }

            ResultSet RSL_GR = cgd.GetReceipt(CONO, DIVI, RCNO, TYPE);

            ResultSet RSL_FNC = cgd.GET_FNC_REF1(CONO, DIVI, RCNO);
            String FNC = "", REF1 = "";
            while (RSL_FNC.next()) {
                FNC = RSL_FNC.getString("RL_FNC").trim();
                REF1 = RSL_FNC.getString("RL_REF1").trim();
            }
            Update_OCUSMA(CONO, DIVI, RCNO, TYPE, "1");
            System.out.println("Running");
            ClassFormatData cfm = new ClassFormatData();

            if (mne.panel.equals("ARS110/B")) {
                mne.setField("CMDTP", "KEY");
                mne.setField("CMDVAL", "ENTER");
                mne.setField("FCS", "WAFNCN");
                mne.setField("WAFNCN", FNC);
                mne.pressKey(MNEProtocol.KeyEnter);

                mne.setField("CMDTP", "1"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                mne.setField("SELROWS", "R1"); //CMDTP
                mne.setField("FCS", "WAFNCN"); //CMDVAL ENTER
                mne.setField("WAFNCN", FNC); //FCS W1OBKV
//              mne.pressKey(MNEProtocol.KeyEnter);
                mne.selectOption("1");
                System.out.println(mne.getMsg());
            }

            while (RSL_GR.next()) {

                if (mne.panel.equals("ARS112/E")) {
                    // System.out.println("CRS610/E");
                    Double HC_REAMT = RSL_GR.getDouble("HC_REAMT");

                    if (RSL_GR.getString("HC_CHKNO").trim().equalsIgnoreCase("")) {
                        continue;
                    }

                    if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {
                        HC_REAMT = HC_REAMT - RSL_GR.getDouble("HR_BKCHG");
                    }

                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("FCS", "WEDRRN"); //
                    mne.setField("WEAIT1", RSL_GR.getString("HC_ACCODE").trim()); // ACCOUNT CODE
                    mne.setField("WEPYNO", RSL_GR.getString("HC_PYNO").trim()); //
                    mne.setField("WECUAM", String.format("%.2f", HC_REAMT)); //amt
                    mne.setField("WEDTP5", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //
                    mne.setField("WEACDT", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //
                    mne.setField("WEDRRN", RSL_GR.getString("HC_CHKNO").trim()); //ACC date
                    mne.setField("WEDUDT", cfm.SubDateForM3(RSL_GR.getString("HC_PYDT"))); //
                    mne.setField("WEBKID", RSL_GR.getString("HC_BANK").trim()); //
                    mne.setField("WEVTXT", RSL_GR.getString("HC_PYNO").trim());
                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.getMsg());
                    // JOptionPane.showMessageDialog(null, mne.getMsg());
                    if (mne.getMsg() != null) {
                        mne.pressKey(MNEProtocol.KeyEnter);
                        continue;
                    }

                }
                if (mne.panel.equals("ARS110/F1")) {
                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim()); //
                    mne.setField("FCS", "WFPYNO"); //
                    mne.pressKey(MNEProtocol.KeyEnter);

                    ////BANK CHARGE
                    if (RSL_GR.getDouble("HR_BKCHG") != 0.00) {

                        mne.setField("CMDTP", "KEY"); //CMDTP KEY
                        mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                        mne.setField("WFSLOP", "21");
                        mne.setField("FCS", "WFSLOP");
                        mne.setField("WFQTTP", "1");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.getMsg() != null) {
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                        if (mne.panel.equals("ARS110/G1")) {
                            mne.setField("CMDTP", "KEY"); //CMDTP KEY
                            mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WGCUAM", RSL_GR.getString("HR_BKCHG").trim());
                            mne.setField("WGAIT7", "ARS110");
                            mne.setField("FCS", "WGCUAM");
                            mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                    }
////
                    ResultSet rsl_line = cgd.LineReceipt(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim());
                    if (rsl_line.next()) {
                        ResultSet rsl_DEPOSIT = cgd.GetDetailDEPOSIT(CONO, DIVI, rsl_line.getString("LR_INVNO").trim(), rsl_line.getString("LR_INVDT").trim());
                        if (rsl_DEPOSIT.next()) {
                            if (!rsl_DEPOSIT.getString("Remark").trim().equals("-/-")) {
                                mne.setField("CMDTP", "KEY");
                                mne.setField("CMDVAL", "ENTER");
                                mne.setField("WFSLOP", "26");
                                mne.pressKey(MNEProtocol.KeyEnter);
                                String[] Remark = rsl_DEPOSIT.getString("Remark").trim().split("/");

                                if (mne.panel.equals("ARS110/G1")) {
                                    mne.setField("CMDTP", "KEY");
                                    mne.setField("CMDVAL", "ENTER");
                                    mne.setField("FCS", "WGAIT2");

                                    mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                                    mne.setField("WGAIT4", Remark[0]);
                                    mne.setField("WGAIT5", rsl_DEPOSIT.getString("uaivno").trim());
                                    mne.setField("WGAIT6", "01");

                                    mne.setField("WGVTXT", rsl_DEPOSIT.getString("VCTXT").trim());
                                    mne.pressKey(MNEProtocol.KeyEnter);

                                }
                            }
                        }
                    }

                    mne.setField("CMDTP", "KEY");
                    mne.setField("CMDVAL", "F3");
                    mne.pressKey(MNEProtocol.KeyF03);

                    if (mne.getMsg().trim().equalsIgnoreCase("Left to distribute does not equal 0")) {
                        System.out.println(mne.getMsg());
                        mne.pressKey(MNEProtocol.KeyEnter);
                        System.out.println(RSL_GR.getString("HC_RCNO").trim());
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "F18");
                        mne.setField("FCS", "WFCINO");
                        mne.pressKey("F18");
                        mne.pressKey(MNEProtocol.KeyEnter);
                        mne.pressKey(MNEProtocol.KeyEnter);
                    }

                    if (mne.getMsg() != null) {
                        System.out.println(mne.getMsg());
                    }
                    String[] Vouchermss = mne.getMsg().toString().split(" ");
                    String Voucher = Vouchermss[2].trim();

                    if (Voucher.length() == 8) {
                        csd.UpdataVoucher(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim(), Voucher);
                    }
                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.panel);
                }

            }
            mne.closeProgram(ARS110);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public static void ARS110_CASH_EXPENSEnew(int CONO, String DIVI, ArrayList<String> arrRC, String TYPE, String FNNO) {
//        String LoginUrlConnectionm3 = "https://bkrmvxm3.bangkokranch.com:21008/mne/servlet/MvxMCSvt"; //PRD
        String LoginUrlConnectionm3 = GBVAR.LoginUrlConnectionm3;

        String LoginM3User = "MVXSECOFR";
        String LoginM3Password = "lawson@90";
        String LoginAppServer = "192.200.9.190";
        int LoginAppPort = GBVAR.DBPORT;
//        int LoginAppPort = 16305; // TST

        try {
            ClassGetData cgd = new ClassGetData();
            ClassSetData csd = new ClassSetData();
            int cono = CONO;
            String divi = DIVI.trim();
            String lastinv = "";

            MNEHelper mne = new MNEHelper(LoginAppServer, LoginAppPort, LoginUrlConnectionm3);
            mne.logInToM3(cono, divi, LoginM3User, LoginM3Password);

            if (!mne.logInToM3(cono, divi, LoginM3User, LoginM3Password)) {
                System.out.println("Can not login to M3 system");
            }

            String ARS110 = mne.runM3Pgm("ARS110");

            if ((ARS110).equals("")) {
                System.out.println(" ไม่สามารถเปิดโปรแกรม ARS110 ได้");
                mne.closeProgram(ARS110);
            }

            ResultSet RSL_GR = cgd.GetReceiptnew4(CONO, DIVI, arrRC.get(0), TYPE);

            ResultSet RSL_FNC = cgd.GET_FNC_REF1(CONO, DIVI, arrRC.get(0));
            String FNC = "", REF1 = "";
            while (RSL_FNC.next()) {
                FNC = RSL_FNC.getString("RL_FNC").trim();
                REF1 = RSL_FNC.getString("RL_REF1").trim();
            }

            System.out.println("Running");
            ClassFormatData cfm = new ClassFormatData();

            if (mne.panel.equals("ARS110/B")) {
                mne.setField("CMDTP", "KEY");
                mne.setField("CMDVAL", "ENTER");
                mne.setField("FCS", "WAFNCN");
                mne.setField("WAFNCN", FNC);
                mne.pressKey(MNEProtocol.KeyEnter);

                mne.setField("CMDTP", "1"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                mne.setField("SELROWS", "R1"); //CMDTP
                mne.setField("FCS", "WAFNCN"); //CMDVAL ENTER
                mne.setField("WAFNCN", FNC); //FCS W1OBKV
//              mne.pressKey(MNEProtocol.KeyEnter);
                mne.selectOption("1");
                System.out.println(mne.getMsg());
            }

            while (RSL_GR.next()) {

                if (mne.panel.equals("ARS112/E")) {
                    // System.out.println("CRS610/E");
                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("FCS", "WEAIT6"); //
                    mne.setField("WECUAM", RSL_GR.getString("HC_REAMT").trim()); //amt
                    mne.setField("WEACDT", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //customer
                    mne.setField("WEDTP5", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //invoice no
                    mne.setField("WEAIT4", REF1); //ACC date
                    mne.setField("WEAIT5", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //ACC date
                    mne.setField("WEAIT6", FNNO); //ACC date

                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.getMsg());
                    // JOptionPane.showMessageDialog(null, mne.getMsg());

                }
                if (mne.panel.equals("ARS110/F1")) {
                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim()); //
                    mne.setField("FCS", "WFPYNO"); //
                    mne.pressKey(MNEProtocol.KeyEnter);

                    //////// EXPENSE ////////
                    ResultSet rsl_expense = cgd.LineExpensenew(CONO, DIVI, FNNO);
                    while (rsl_expense.next()) {

                        mne.setField("CMDTP", "KEY"); //CMDTP KEY
                        mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                        mne.setField("WFSLOP", rsl_expense.getString("RE_OPTION").trim());
                        mne.setField("FCS", "WFSLOP");
                        mne.setField("WFQTTP", "1");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.getMsg() != null) {
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                        if (mne.panel.equals("ARS110/G1")) {
                            mne.setField("CMDTP", "KEY"); //CMDTP KEY
                            mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WGCUAM", rsl_expense.getString("LRE_AMT").trim());
                            mne.setField("WGAIT1", rsl_expense.getString("LRE_ACTCODE").trim());
                            mne.setField("WGAIT4", cgd.Expense_CustomerName(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.setField("WGVTXT", cgd.Expense_text(CONO, DIVI, rsl_expense.getString("LRE_CODE").trim()));

                            mne.setField("FCS", "WGCUAM");

                            if (!rsl_expense.getString("LRE_CODE").trim().equalsIgnoreCase("56") && !rsl_expense.getString("LRE_CODE").trim().equalsIgnoreCase("57") && !rsl_expense.getString("LRE_CODE").trim().equalsIgnoreCase("70")) {
                                mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            }

                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                    }

/////////////////////////
                    ResultSet rsl_line = cgd.LineReceiptnew(CONO, DIVI, arrRC);
                    while (rsl_line.next()) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
//                        mne.setField("WFPYNO", "TH28050014");
                        mne.setField("FCS", "WFCUCD");
                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());
                        mne.pressKey(MNEProtocol.KeyEnter);

                        mne.setField("CMDTP", "LSTOPT");
                        mne.setField("CMDVAL", "11");
                        mne.setField("FCS", "WFCINO");
                        mne.setField("SELROWS", "R1");
                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());
                        mne.selectOption("11");
                        System.out.println(mne.getMsg());

                        if (mne.getMsg() != null) {
                            System.out.println(mne.getMsg());
                            mne.pressKey(MNEProtocol.KeyEnter);
//                            System.out.println(RSL_GR.getString("HC_RCNO").trim());
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "F18");
                            mne.setField("FCS", "WFCINO");
                            mne.pressKey("F18");
                            mne.pressKey(MNEProtocol.KeyEnter);
                            mne.pressKey(MNEProtocol.KeyEnter);
                            mne.closeProgram(ARS110);
                        }

                        if (mne.panel.equals("ARS110/H")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WHCUAM");
                            mne.setField("WHCUAM", rsl_line.getString("LR_REAMT").trim());
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                        lastinv = rsl_line.getString("LR_INVNO").trim();
                    }

                    Double BL = cgd.Call_Balancenew(CONO, DIVI, arrRC);
                    BL += cgd.Call_Expensenew(CONO, DIVI, FNNO.trim());

                    if (BL > 2) {
                        if (mne.panel.equals("ARS110/F1")) {

                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WFCINO", lastinv);

                            mne.setField("CMDTP", "LSTOPT");
                            mne.setField("CMDVAL", "12");
                            mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                            mne.setField("FCS", "WFCINO");
                            mne.setField("SELROWS", "R1");
                            mne.setField("WFCINO", lastinv);
                            mne.selectOption("12");

                        }

                        if (mne.panel.equals("ARS110/I")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WIAIT3");
                            mne.setField("WIAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.setField("WIAIT3", "NON");
                            mne.pressKey(MNEProtocol.KeyEnter);

                        }

                    } else if ((BL <= 2 && BL >= -2) && BL != 0) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFSLOP", "23");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.panel.equals("ARS110/G1")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WGAIT2");
                            mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                    }

                    mne.setField("CMDTP", "KEY");
                    mne.setField("CMDVAL", "F3");
                    mne.pressKey(MNEProtocol.KeyF03);

                    if (mne.getMsg().trim().equalsIgnoreCase("Left to distribute does not equal 0")) {
                        System.out.println(mne.getMsg());
                        mne.pressKey(MNEProtocol.KeyEnter);
                        System.out.println(RSL_GR.getString("HC_RCNO").trim());
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "F18");
                        mne.setField("FCS", "WFCINO");
                        mne.pressKey("F18");
                        mne.pressKey(MNEProtocol.KeyEnter);
                        mne.pressKey(MNEProtocol.KeyEnter);
                    }

                    if (mne.getMsg() != null) {
                        System.out.println(mne.getMsg());
                    }
                    String[] Vouchermss = mne.getMsg().toString().split(" ");
                    String Voucher = Vouchermss[2].trim();

                    if (Voucher.length() == 8) {
                        csd.UpdataVoucher(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim(), Voucher);
                    }
                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.panel);
                }
            }

            mne.closeProgram(ARS110);

        } catch (Exception e) {
            System.out.println(e.toString());

        }

    }

    public static void ARS110_CASH_EXPENSE(int CONO, String DIVI, String RCNO, String TYPE) {
//        String LoginUrlConnectionm3 = "https://bkrmvxm3.bangkokranch.com:21008/mne/servlet/MvxMCSvt"; //PRD
        String LoginUrlConnectionm3 = GBVAR.LoginUrlConnectionm3;

        String LoginM3User = "MVXSECOFR";
        String LoginM3Password = "lawson@90";
        String LoginAppServer = "192.200.9.190";
        int LoginAppPort = GBVAR.DBPORT;
//        int LoginAppPort = 16305; // TST

        try {
            ClassGetData cgd = new ClassGetData();
            ClassSetData csd = new ClassSetData();
            int cono = CONO;
            String divi = DIVI.trim();
            String lastinv = "";

            MNEHelper mne = new MNEHelper(LoginAppServer, LoginAppPort, LoginUrlConnectionm3);
            mne.logInToM3(cono, divi, LoginM3User, LoginM3Password);

            if (!mne.logInToM3(cono, divi, LoginM3User, LoginM3Password)) {
                System.out.println("Can not login to M3 system");
            }

            String ARS110 = mne.runM3Pgm("ARS110");

            if ((ARS110).equals("")) {
                System.out.println(" ไม่สามารถเปิดโปรแกรม ARS110 ได้");
                mne.closeProgram(ARS110);
            }

            ResultSet RSL_GR = cgd.GetReceipt(CONO, DIVI, RCNO, TYPE);

            ResultSet RSL_FNC = cgd.GET_FNC_REF1(CONO, DIVI, RCNO);
            String FNC = "", REF1 = "";
            while (RSL_FNC.next()) {
                FNC = RSL_FNC.getString("RL_FNC").trim();
                REF1 = RSL_FNC.getString("RL_REF1").trim();
            }

            System.out.println("Running");
            ClassFormatData cfm = new ClassFormatData();

            if (mne.panel.equals("ARS110/B")) {
                mne.setField("CMDTP", "KEY");
                mne.setField("CMDVAL", "ENTER");
                mne.setField("FCS", "WAFNCN");
                mne.setField("WAFNCN", FNC);
                mne.pressKey(MNEProtocol.KeyEnter);

                mne.setField("CMDTP", "1"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                mne.setField("SELROWS", "R1"); //CMDTP
                mne.setField("FCS", "WAFNCN"); //CMDVAL ENTER
                mne.setField("WAFNCN", FNC); //FCS W1OBKV
//              mne.pressKey(MNEProtocol.KeyEnter);
                mne.selectOption("1");
                System.out.println(mne.getMsg());
            }

            while (RSL_GR.next()) {

                if (mne.panel.equals("ARS112/E")) {
                    // System.out.println("CRS610/E");
                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("FCS", "WEAIT6"); //
                    mne.setField("WECUAM", RSL_GR.getString("HC_REAMT").trim()); //amt
                    mne.setField("WEACDT", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //customer
                    mne.setField("WEDTP5", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //invoice no
                    mne.setField("WEAIT4", REF1); //ACC date
                    mne.setField("WEAIT5", cfm.SubDateForM3(RSL_GR.getString("HC_TRDT"))); //ACC date
                    mne.setField("WEAIT6", RSL_GR.getString("HC_RCNO").trim()); //ACC date

                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.getMsg());
                    // JOptionPane.showMessageDialog(null, mne.getMsg());

                }
                if (mne.panel.equals("ARS110/F1")) {
                    mne.setField("CMDTP", "KEY"); //CMDTP KEY
                    mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                    mne.setField("CMDVAL", "ENTER"); //
                    mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim()); //
                    mne.setField("FCS", "WFPYNO"); //
                    mne.pressKey(MNEProtocol.KeyEnter);

                    //////// EXPENSE ////////
                    ResultSet rsl_expense = cgd.LineExpense(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim());
                    while (rsl_expense.next()) {

                        mne.setField("CMDTP", "KEY"); //CMDTP KEY
                        mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                        mne.setField("WFSLOP", rsl_expense.getString("RE_OPTION").trim());
                        mne.setField("FCS", "WFSLOP");
                        mne.setField("WFQTTP", "1");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.getMsg() != null) {
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                        if (mne.panel.equals("ARS110/G1")) {
                            mne.setField("CMDTP", "KEY"); //CMDTP KEY
                            mne.setField("APPSRV", "BKRMVXM3.BANGKOKRANCH.COM:16303"); //APPSRV BKRMVXM3.BANGKOKRANCH.COM:16303
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WGCUAM", rsl_expense.getString("LRE_AMT").trim());
                            mne.setField("WGAIT1", rsl_expense.getString("LRE_ACTCODE").trim());
                            mne.setField("WGAIT4", cgd.Expense_CustomerName(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.setField("WGVTXT", cgd.Expense_text(CONO, DIVI, rsl_expense.getString("LRE_CODE").trim()));

                            mne.setField("FCS", "WGCUAM");

                            if (!rsl_expense.getString("LRE_CODE").trim().equalsIgnoreCase("56") && !rsl_expense.getString("LRE_CODE").trim().equalsIgnoreCase("57") && !rsl_expense.getString("LRE_CODE").trim().equalsIgnoreCase("70")) {
                                mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            }

                            mne.pressKey(MNEProtocol.KeyEnter);
                        }

                    }

/////////////////////////
                    ResultSet rsl_line = cgd.LineReceipt(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim());
                    while (rsl_line.next()) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
//                        mne.setField("WFPYNO", "TH28050014");
                        mne.setField("FCS", "WFCUCD");
                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());
                        mne.pressKey(MNEProtocol.KeyEnter);

                        mne.setField("CMDTP", "LSTOPT");
                        mne.setField("CMDVAL", "11");
                        mne.setField("FCS", "WFCINO");
                        mne.setField("SELROWS", "R1");
                        mne.setField("WFCINO", rsl_line.getString("LR_INVNO").trim());
                        mne.selectOption("11");
                        System.out.println(mne.getMsg());

                        if (mne.getMsg() != null) {
                            System.out.println(mne.getMsg());
                            mne.pressKey(MNEProtocol.KeyEnter);
                            System.out.println(RSL_GR.getString("HC_RCNO").trim());
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "F18");
                            mne.setField("FCS", "WFCINO");
                            mne.pressKey("F18");
                            mne.pressKey(MNEProtocol.KeyEnter);
                            mne.pressKey(MNEProtocol.KeyEnter);
                            mne.closeProgram(ARS110);
                        }

                        if (mne.panel.equals("ARS110/H")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WHCUAM");
                            mne.setField("WHCUAM", rsl_line.getString("LR_REAMT").trim());
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                        lastinv = rsl_line.getString("LR_INVNO").trim();
                    }
                    Double BL = cgd.Call_Balance(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim());
                    BL += cgd.Call_Expense(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim());

                    if (BL > 2) {
                        if (mne.panel.equals("ARS110/F1")) {

                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("WFCINO", lastinv);

                            mne.setField("CMDTP", "LSTOPT");
                            mne.setField("CMDVAL", "12");
                            mne.setField("WFPYNO", RSL_GR.getString("HC_PYNO").trim());
                            mne.setField("FCS", "WFCINO");
                            mne.setField("SELROWS", "R1");
                            mne.setField("WFCINO", lastinv);
                            mne.selectOption("12");

                        }

                        if (mne.panel.equals("ARS110/I")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WIAIT3");
                            mne.setField("WIAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.setField("WIAIT3", "NON");
                            mne.pressKey(MNEProtocol.KeyEnter);

                        }

                    } else if ((BL <= 2 && BL >= -2) && BL != 0) {
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "ENTER");
                        mne.setField("WFSLOP", "23");
                        mne.pressKey(MNEProtocol.KeyEnter);

                        if (mne.panel.equals("ARS110/G1")) {
                            mne.setField("CMDTP", "KEY");
                            mne.setField("CMDVAL", "ENTER");
                            mne.setField("FCS", "WGAIT2");
                            mne.setField("WGAIT2", cgd.COST_SALEMAN(CONO, RSL_GR.getString("HC_PYNO").trim()));
                            mne.pressKey(MNEProtocol.KeyEnter);
                        }
                    }

                    mne.setField("CMDTP", "KEY");
                    mne.setField("CMDVAL", "F3");
                    mne.pressKey(MNEProtocol.KeyF03);

                    if (mne.getMsg().trim().equalsIgnoreCase("Left to distribute does not equal 0")) {
                        System.out.println(mne.getMsg());
                        mne.pressKey(MNEProtocol.KeyEnter);
//                        System.out.println(RSL_GR.getString("HC_RCNO").trim());
                        mne.setField("CMDTP", "KEY");
                        mne.setField("CMDVAL", "F18");
                        mne.setField("FCS", "WFCINO");
                        mne.pressKey("F18");
                        mne.pressKey(MNEProtocol.KeyEnter);
                        mne.pressKey(MNEProtocol.KeyEnter);
                    }

                    if (mne.getMsg() != null) {
                        System.out.println(mne.getMsg());
                    }
                    String[] Vouchermss = mne.getMsg().toString().split(" ");
                    String Voucher = Vouchermss[2].trim();

                    if (Voucher.length() == 8) {
                        csd.UpdataVoucher(CONO, DIVI, RSL_GR.getString("HC_RCNO").trim(), Voucher);
                    }
                    mne.pressKey(MNEProtocol.KeyEnter);
                    System.out.println(mne.panel);
                }
            }

            mne.closeProgram(ARS110);

        } catch (Exception e) {
            System.out.println(e.toString());

        }

    }

}
