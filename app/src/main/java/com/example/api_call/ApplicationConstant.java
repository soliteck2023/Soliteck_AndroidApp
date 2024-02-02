package com.example.api_call;

import static com.example.api_call.R.drawable.*;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputFilter;
import android.util.Base64;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class ApplicationConstant {

//    public static void displayToastMessage(Context mContext, String message) {
//
//
//        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
//    }

    public static final String PRIVACY_POLICY = "privacy_agree";


    public static void displayToastMessage(Context mContext, String message) {
        Toast.makeText(mContext, message,Toast.LENGTH_LONG).show();
    }


//    public static void displayToastMessage(Activity activity,String title, String message){
//        if (activity != null){
//            AlertDialog.Builder builder1 = new AlertDialog.Builder(activity);
//            builder1.setMessage(message);
//            builder1.setCancelable(true);
//            builder1.setTitle(title);
//            builder1.setIcon(R.drawable.card);
//            builder1.setPositiveButton("Okay", new DialogInterface.OnClickListener() { // from class: com.uvapay.services.ApplicationConstant.1
//                @Override // android.content.DialogInterface.OnClickListener
//                public void onClick(DialogInterface dialog, int id) {
//                    dialog.cancel();
//                }
//            });
//            AlertDialog alert11 = builder1.create();
//            alert11.show();
//            alert11.getWindow().setLayout(1000,500);
//
//        }
//    }




    public static void setEditTextMaxLength(EditText editText, int length) {
        InputFilter[] FilterArray = {new InputFilter.LengthFilter(length)};
        editText.setFilters(FilterArray);
    }

    public static String EncodeStringToHMACSHA256(String message) {
        String hmacSha256Hex = "";
        try {
            byte[] hmacSha256Bytes = calculateHmacSha256(message, "PavoriteHash");
            hmacSha256Hex = bytesToHex(hmacSha256Bytes).toLowerCase();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
        }
        String Base64 = encodeToBase64(hmacSha256Hex).replace("\n", "");
        System.out.println("HMACSHA256 Encrypted Password: " + Base64);
        return Base64;
    }
    public static byte[] calculateHmacSha256(String message, String secretKey) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac hmacSha256 = Mac.getInstance("HmacSHA256");
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
        hmacSha256.init(keySpec);
        return hmacSha256.doFinal(message.getBytes());
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", Byte.valueOf(b)));
        }
        return sb.toString();
    }

    public static String encodeToBase64(String input) {
        byte[] inputBytes = input.getBytes();
        byte[] base64Bytes = Base64.encode(inputBytes, 0);
        return new String(base64Bytes);
    }

    public static void DisplayMessageDialog(Activity activity, String title, String message) {
        if (activity != null) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(activity);
            builder1.setMessage(message);
            builder1.setCancelable(true);
            builder1.setTitle(title);
            builder1.setIcon(card);
            builder1.setPositiveButton("Okay", new DialogInterface.OnClickListener() { // from class: com.uvapay.services.ApplicationConstant.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            AlertDialog alert11 = builder1.create();
            alert11.show();
            alert11.getWindow().setLayout(1000,600);
        }
    }
}
