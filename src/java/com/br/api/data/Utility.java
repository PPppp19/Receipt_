/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.api.data;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Wattana
 */
public class Utility {

    public static String getCompany() throws JSONException {

        JSONObject mJsonObj = new JSONObject();
        JSONArray mJsonArr = new JSONArray();

        try {
            mJsonArr = SelectDB2.Company();
            //System.out.println(mJsonArr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mJsonArr.length() > 0) {
            mJsonObj.put("data", mJsonArr);
            return mJsonObj.toString();
        } else {
            mJsonObj.put("status", "404");
            mJsonObj.put("message", "Data not found.");
            return mJsonObj.toString();
        }

    }

    public static String List_Get_Receiver_Driver(String cono, String divi, String rcno) throws JSONException {

        JSONObject mJsonObj = new JSONObject();
        JSONArray mJsonArr = new JSONArray();

        try {
            mJsonArr = SelectDB2.List_Get_Receiver_Driver(cono, divi, rcno);
            //System.out.println(mJsonArr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mJsonArr.length() > 0) {
            mJsonObj.put("data", mJsonArr);
            return mJsonObj.toString();
        } else {
            mJsonObj.put("status", "404");
            mJsonObj.put("message", "Data not found.");
            return mJsonObj.toString();
        }

    }

    public static String getLocation(String cono, String divi) throws JSONException {

        JSONObject mJsonObj = new JSONObject();
        JSONArray mJsonArr = new JSONArray();

        try {
            mJsonArr = SelectDB2.Location(cono, divi);
            //System.out.println(mJsonArr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mJsonArr.length() > 0) {
            mJsonObj.put("data", mJsonArr);
            return mJsonObj.toString();
        } else {
            mJsonObj.put("status", "404");
            mJsonObj.put("message", "Data not found.");
            return mJsonObj.toString();
        }

    }

    public static String List_Bank(String type, String code, String cono, String divi) throws JSONException {

        JSONObject mJsonObj = new JSONObject();
        JSONArray mJsonArr = new JSONArray();

        try {
            mJsonArr = SelectDB2.List_Bank(type, code, cono, divi);
            //System.out.println(mJsonArr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mJsonArr.length() > 0) {
            mJsonObj.put("data", mJsonArr);
            return mJsonObj.toString();
        } else {
            mJsonObj.put("status", "404");
            mJsonObj.put("message", "Data not found.");
            return mJsonObj.toString();
        }

    }

    public static String List_Eexpense(String cono, String divi) throws JSONException {

        JSONObject mJsonObj = new JSONObject();
        JSONArray mJsonArr = new JSONArray();

        try {
            mJsonArr = SelectDB2.List_Eexpense(cono, divi);
            //System.out.println(mJsonArr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mJsonArr.length() > 0) {
            mJsonObj.put("data", mJsonArr);
            return mJsonObj.toString();
        } else {
            mJsonObj.put("status", "404");
            mJsonObj.put("message", "Data not found.");
            return mJsonObj.toString();
        }

    }

    public static String List_Accode(String cono, String divi, String code) throws JSONException {

        JSONObject mJsonObj = new JSONObject();
        JSONArray mJsonArr = new JSONArray();

        try {
            mJsonArr = SelectDB2.List_Accode(cono, divi, code);
            //System.out.println(mJsonArr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mJsonArr.length() > 0) {
            mJsonObj.put("data", mJsonArr);
            return mJsonObj.toString();
        } else {
            mJsonObj.put("status", "404");
            mJsonObj.put("message", "Data not found.");
            return mJsonObj.toString();
        }

    }

    public static String List_Receiver(String cono, String divi, String type) throws JSONException {

        JSONObject mJsonObj = new JSONObject();
        JSONArray mJsonArr = new JSONArray();

        try {
            mJsonArr = SelectDB2.List_Receiver(cono, divi, type);
            //System.out.println(mJsonArr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mJsonArr.length() > 0) {
            mJsonObj.put("data", mJsonArr);
            return mJsonObj.toString();
        } else {
            mJsonObj.put("status", "404");
            mJsonObj.put("message", "Data not found.");
            return mJsonObj.toString();
        }

    }

}
