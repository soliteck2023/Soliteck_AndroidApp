package com.example.api_call;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public class ConstantClass {

    public static final String IMAGEWEBSERVICEURL = "https://payin.co.in/images/Recharge/Operators/";
    public static Operater datum = new  Operater();
    public static final String MOBILESERVICEID = "1";
    public static String circle = MOBILESERVICEID;
    public static String setType = "";


    public static void displayToastMessage(Context mContext, String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    public static void displayMessageDialog(Context context, String title, String message) {
        if (context != null) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setTitle(title);
            builder1.setMessage(message);
            builder1.setCancelable(true);
            builder1.setPositiveButton("Okay", new DialogInterface.OnClickListener() { // from class: com.uvapay.ConstantClass.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
    }
    public static boolean isNetworkAvailable(Context context) {
        @SuppressLint("WrongConstant") ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("Connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public class PROFILEDETAILS {
        public static final String Aadhar = "aaadhar";
        public static final String AccountNo = "AccountNo";
        public static final String Balance = "balance";
        public static final String BanksName = "BanksName";
        public static final String CityId = "CityId";
        public static final String CityName = "CityName";
        public static final String DIST_PROFILE_IMAGE = "dist_profile_image";
        public static final String DeviceId = "deviceId";
        public static final String EmailId = "EmailId";
        public static final String Firebase_Token = "firebase_token";
        public static final String FirmName = "FirmName";
        public static final String IDPROOFNUMBER = "idproofnumber";
        public static final String IDPROOFTYPE = "idprooftype";
        public static final String IFSCCode = "IFSCCode";
        public static final String IsTerms = "isTerms";
        public static final String KYCStatus = "KYCStatus";
        public static final String Last = "last_";
        public static final String Middle = "middle_";
        public static final String MobileNo = "MobileNo";
        public static final String Name = "name_";
        public static final String OwnerName = "OwnerName";
        public static final String PROFILE_IMAGE = "profile_image";
        public static final String PanCard = "PanCard";
        public static final String ParentName = "ParentName";
        public static final String PermanentAddress = "PermanentAddress";
        public static final String PinCode = "PinCode";
        public static final String REFER_BY = "REFER_BY";
        public static final String RefrenceNumber = "RefrenceNumber";
        public static final String SchemeId = "schemeId";
        public static final String ShopAddress = "ShopAddress";
        public static final String ShopName = "ShopName";
        public static final String StateId = "StateId";
        public static final String StateName = "StateName";
        public static final String UserId = "UserId";
        public static final String UserName_ = "UserName";
        public static final String UserType = "UserType";
        public static final String WhiteUser = "WhiteUser";
        public static final String imgUrl = "imgUrl";
        public static final String zipcode = "zipcode";

        public PROFILEDETAILS() {

//            ConstantClass.this = this$0;
        }
    }

    public class USERDETAILS {
        public static final String AEPSBalance = "AEPSBalance";
        public static final String APP_LOGO = "app_logo_";
        public static final String EncreptedUserPassword = "encript_password";
        public static final String First_Time_Login = "true";
        public static final String FlagRemember = "remember";
        public static final String TransactionIds = "TransactionIds";
        public static final String LastSeen = "LastSeen";
        public static final String LoginPassword = "login_password";
        public static final String MainBalance = "MainBalance";
        public static final String News = "News";
        public static final String OwnerName = "OwnerName";
        public static final String ParentId = "parent_id";
        public static final String Token = "token";
        public static final String UserName = "username";
        public static final String UserPassword = "password";
        public static final String Circle = "Circle";
        public static final String UserType = "usertype";

        public USERDETAILS() {

//            ConstantClass.this = this$0;
        }
    }
}
