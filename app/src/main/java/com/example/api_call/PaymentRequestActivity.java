package com.example.api_call;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentRequestActivity extends AppCompatActivity {

    private static final int CAMERA_PERMISSION_REQUEST_CODE = 10;
    private static final int SELECT_PICTURE = 20;

    private static final int pic_id = 123;
    private final List<GetBankResponse> listAdminBanks = new ArrayList();
    private final List<GetBankResponse> listParentBanks = new ArrayList();
    private final List<String> listAdminBanksAccount = new ArrayList();
    private final List<String> listParentBanksAccount = new ArrayList();
    private final List<String> listUserBanksAccount = new ArrayList();
    private final String PaymentAmount = "";
    private final String BankName = "";
    private final String PaymentMode = "";
    private final String CashType = "";
    private final String UserBankName = "";
    private final String BranchName = "";
    private final String BranchCode = "";
    private final String ChequeNo = " ";
    private final String TransactionNo = "";
    private final String Location = "";
    private final String PaymentDate = "";
    private final String PaymentTime = "";
    private final String UTRNumber = "";
    List<PaymentMode> paymentType = new ArrayList();
    List<String> paymentMode = new ArrayList();
    String photo_1 = " ";
    Bitmap bitmap;
    Uri imaguri;
    Uri picUri;
    RequestPaymentResponse requestPaymentResponse;
    private String branchname = "";
    private Button btn_payment_request;
    private String[] cashType;
    private CheckBox checkCredit;
    private CheckBox check_admin;
    private CheckBox check_parent;
    private EditText edit_amount_;
    private TextView edit_bank;
    private TextView edit_cashtype;
    private TextView edit_check_date;
    private EditText edit_cheque_number;
    private TextView edit_payment_date;
    private TextView edit_paymentmode;
    private EditText edit_remarks;
    private EditText edit_transactionno;
    private TextView edit_user_bank;
    private EditText edit_utr_no;
    private ImageView image_slip;
    private FrameLayout layout_cash;
    private FrameLayout layout_transaction;
    private FrameLayout layout_userbank;
    private FrameLayout layout_utr;
    private FrameLayout linear_dd_date;
    private FrameLayout linear_dd_number;
    private int mDay;
    private int mMonth;
    private int mYear;
    private Calendar myCalendar;
    private String[] paymentTypeCode;
    private ProgressDialog progressDialog;
    private String strBankCode;
    private String strPaymentTypeCode;
    private List<CompanyBankResponse> listUserBanks = new ArrayList();
    private String checkCreditString = "false";
    private String UserId = "";
    private String PayId = "";
    private String BankId = "";
    private String picturepath;

    private TextView TV_ReceiptURL;

    public static String getPath(Context context, Uri uri) {
        String result = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, proj, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndexOrThrow(proj[0]);
                result = cursor.getString(column_index);
            }
            cursor.close();
        }
        if (result == null) {
            result = "Not found";
        }
        return result;
    }

    public static void deleteCache(Context context, File dir) {
        try {
            File dir2 = context.getCacheDir();
            dir2.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_request);
        setTitle("Payment Request");
        initComponents();
        Calendar calendar = Calendar.getInstance();
        this.myCalendar = calendar;
        this.mYear = calendar.get(1);
        this.mMonth = this.myCalendar.get(2);
        this.mDay = this.myCalendar.get(5);
        this.cashType = getResources().getStringArray(R.array.payment_request_cash_type);
        this.paymentTypeCode = getResources().getStringArray(R.array.banks_payment_request_Payment_type_code);
        this.layout_cash.setVisibility(View.GONE);
        this.layout_utr.setVisibility(View.GONE);
        getPaymentMode();
        this.check_parent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.uvapay.activities.PaymentRequestActivity.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (PaymentRequestActivity.this.check_parent.isChecked()) {
                    PaymentRequestActivity.this.check_parent.setChecked(true);
                    PaymentRequestActivity.this.check_admin.setChecked(false);
                    PaymentRequestActivity paymentRequestActivity = PaymentRequestActivity.this;
                    paymentRequestActivity.UserId = PrefUtils.getFromPrefs(paymentRequestActivity, ConstantClass.USERDETAILS.ParentId, "");
                    return;
                }
                PaymentRequestActivity.this.check_admin.setChecked(true);
                PaymentRequestActivity.this.check_parent.setChecked(false);
                PaymentRequestActivity.this.UserId = ConstantClass.MOBILESERVICEID;
            }
        });

        this.check_admin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.uvapay.activities.PaymentRequestActivity.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (PaymentRequestActivity.this.check_admin.isChecked()) {
                    PaymentRequestActivity.this.check_admin.setChecked(true);
                    PaymentRequestActivity.this.check_parent.setChecked(false);
                    PaymentRequestActivity.this.UserId = ConstantClass.MOBILESERVICEID;
                    return;
                }
                PaymentRequestActivity.this.check_parent.setChecked(true);
                PaymentRequestActivity.this.check_admin.setChecked(false);
                PaymentRequestActivity paymentRequestActivity = PaymentRequestActivity.this;
                paymentRequestActivity.UserId = PrefUtils.getFromPrefs(paymentRequestActivity, ConstantClass.USERDETAILS.ParentId, "");
            }
        });

        this.check_admin.setChecked(true);
        this.checkCredit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.uvapay.activities.PaymentRequestActivity.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (PaymentRequestActivity.this.checkCredit.isChecked()) {
                    PaymentRequestActivity.this.checkCreditString = ConstantClass.USERDETAILS.First_Time_Login;
                } else {
                    PaymentRequestActivity.this.checkCreditString = "false";
                }
            }
        });
        this.edit_paymentmode.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.PaymentRequestActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                PaymentRequestActivity paymentRequestActivity = PaymentRequestActivity.this;
                paymentRequestActivity.DisplayPaymentType(paymentRequestActivity.paymentMode);
            }
        });
        this.edit_cashtype.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.PaymentRequestActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                PaymentRequestActivity paymentRequestActivity = PaymentRequestActivity.this;
                paymentRequestActivity.DisplayCashType(paymentRequestActivity.cashType);
            }
        });
        this.edit_bank.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.PaymentRequestActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
            }
        });

        this.btn_payment_request.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.PaymentRequestActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
//                UploadImage(bitmap);
                makeMoneyRequest();
            }
        });

        this.image_slip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();

            }
        });


        this.edit_payment_date.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.PaymentRequestActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(PaymentRequestActivity.this, (int) R.style.DialogTheme), new DatePickerDialog.OnDateSetListener() { // from class: com.uvapay.activities.PaymentRequestActivity.9.1
                    @Override // android.app.DatePickerDialog.OnDateSetListener
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        PaymentRequestActivity.this.edit_payment_date.setText((monthOfYear + 1) + "/" + dayOfMonth + "/" + year);
                    }
                }, PaymentRequestActivity.this.mYear, PaymentRequestActivity.this.mMonth, PaymentRequestActivity.this.mDay);
                datePickerDialog.show();
            }
        });

        this.layout_userbank.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.PaymentRequestActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PaymentRequestActivity.this.getAllUserBanks();
            }
        });
        this.edit_check_date.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.PaymentRequestActivity.11
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(PaymentRequestActivity.this, (int) R.style.DialogTheme), new DatePickerDialog.OnDateSetListener() { // from class: com.uvapay.activities.PaymentRequestActivity.11.1
                    @Override // android.app.DatePickerDialog.OnDateSetListener
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        PaymentRequestActivity.this.edit_check_date.setText((monthOfYear + 1) + "/" + dayOfMonth + "/" + year);
                    }
                }, PaymentRequestActivity.this.mYear, PaymentRequestActivity.this.mMonth, PaymentRequestActivity.this.mDay);
                datePickerDialog.show();
            }
        });

        this.layout_userbank.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.PaymentRequestActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PaymentRequestActivity.this.getAllUserBanks();
            }
        });

    }

    private void selectImage() {
        View view_types = getLayoutInflater().inflate(R.layout.layout_image_option, (ViewGroup) null);
//        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        Dialog dialog = new Dialog(this);
        dialog.setContentView(view_types);
        dialog.setCancelable(false);
        dialog.show();
        LinearLayout linear_camera = view_types.findViewById(R.id.linear_camera);
        LinearLayout linear_gallery = view_types.findViewById(R.id.linear_gallery);
        TextView Cancel = view_types.findViewById(R.id.cancel);

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        linear_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (Build.VERSION.SDK_INT >= 24) {
                    if (ContextCompat.checkSelfPermission(PaymentRequestActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(PaymentRequestActivity.this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
                    } else {
                        try {
                            startActivityForResult(cameraintent, pic_id);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });

        linear_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Toast.makeText(PaymentRequestActivity.this, "Gallery", Toast.LENGTH_SHORT).show();
                Intent galleryintent = new Intent();
                galleryintent.setType("image/*");
                galleryintent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(galleryintent, "Select Picture"), SELECT_PICTURE);


            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri imageUri = null;
            Bitmap imageBitmap = null;

//            if (requestCode == pic_id) {
//                if (data != null && data.getExtras() != null) {
//                    Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
//                    if (imageBitmap != null) {
//                        image_slip.setImageBitmap(imageBitmap);
//                    }
//                }
//            }

            if (requestCode == pic_id && resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                if (extras != null) {
                    imageBitmap = (Bitmap) extras.get("data");
                    if (imageBitmap != null) {
                        image_slip.setImageBitmap(imageBitmap);
                        UploadImage(imageBitmap);
                    }
                }
            }

            else if (requestCode == SELECT_PICTURE) {
                if (data != null) {
                    imageUri = data.getData();
                    if (imageUri != null) {
                        image_slip.setImageURI(imageUri);
                    }
                }
            }

            if (imageUri != null ) {
                try {
                    Bitmap bitmap1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    UploadImage(bitmap1);
                } catch (IOException e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

        } else {
            Toast.makeText(this, "Operation canceled", Toast.LENGTH_SHORT).show();
        }
    }

    private void UploadImage(Bitmap bitmap) {
        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue(this);
        progressDialog.show();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        String Base64_Image = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);

        String flag = ConstantClass.PaymentFlag;
        String Uid = PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, "");
        String Did = PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, "");
        String Token = PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, "");

        HashMap<String, String> body = new HashMap<>();
        body.put("DeviceId", Did);
        body.put("Token", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, ""));
        body.put("UniqueCode", Uid);
        body.put("Base64Encode", Base64_Image);
        body.put("Flag", flag);

        ApiInterface apiservice = RetrofitHandler.getService2();
        Call<RequestPaymentResponse> call = apiservice.getuplodeimage(body);
        Log.d("TAG", "getpathimage request : " + call);
        call.enqueue(new Callback<RequestPaymentResponse>() {
            @Override
            public void onResponse(Call<RequestPaymentResponse> call, Response<RequestPaymentResponse> response) {
                progressDialog.dismiss();
                Toast.makeText(PaymentRequestActivity.this, "Response" + response, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "getpathimage response : " + response);
                if (response.isSuccessful() && response.body() != null) {

                    String IMG_URL = (String) response.body().getData();
                    PrefUtils.saveToPrefs(PaymentRequestActivity.this, ConstantClass.SERVER_IMG_NAME, IMG_URL);
                    TV_ReceiptURL.setVisibility(View.GONE);
                    TV_ReceiptURL.setText(PrefUtils.getFromPrefs(PaymentRequestActivity.this, ConstantClass.SERVER_IMG_NAME, ""));

                } else {
                    Log.e("ApiManager", "API call not successful");
                }

            }

            @Override
            public void onFailure(Call<RequestPaymentResponse> call, Throwable t) {
                Toast.makeText(PaymentRequestActivity.this, "Error Response" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("TAG", "getpathimage onFailure : " + t.getMessage());

            }
        });

    }


    private void getAllUserBanks() {
        ProgressDialog dialogue = CustomProgressDialog.getDialogue(this);
        this.progressDialog = dialogue;
        dialogue.show();
        String DeviceId = PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, "");
        String Token = PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, "");
        HashMap<String, String> body = new HashMap<>();
        body.put("DeviceId", DeviceId);
        body.put("Token", Token);
        ApiInterface apiservice = RetrofitHandler.getService();
        Call<BankListbaseResponse> call = apiservice.GetBankList(body);
        call.enqueue(new Callback<BankListbaseResponse>() { // from class: com.uvapay.activities.PaymentRequestActivity.14
            @Override // retrofit2.Callback
            public void onResponse(Call<BankListbaseResponse> call2, Response<BankListbaseResponse> response) {
                if (PaymentRequestActivity.this.progressDialog != null && PaymentRequestActivity.this.progressDialog.isShowing()) {
                    PaymentRequestActivity.this.progressDialog.dismiss();
                }
                if (response != null) {
                    PaymentRequestActivity.this.listUserBanks.clear();
                    PaymentRequestActivity.this.listUserBanks = response.body().getCompanyBankList();
                    PaymentRequestActivity.this.listUserBanksAccount.clear();
                    for (CompanyBankResponse bankData : PaymentRequestActivity.this.listUserBanks) {
                        PaymentRequestActivity.this.listUserBanksAccount.add("" + bankData.getBankName() + "\n (" + bankData.getIfsc() + ")");
                    }
                    PaymentRequestActivity paymentRequestActivity = PaymentRequestActivity.this;
                    paymentRequestActivity.DisplayUserBankList(paymentRequestActivity.listUserBanks, PaymentRequestActivity.this.listUserBanksAccount);
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<BankListbaseResponse> call2, Throwable t) {
                if (PaymentRequestActivity.this.progressDialog != null && PaymentRequestActivity.this.progressDialog.isShowing()) {
                    PaymentRequestActivity.this.progressDialog.dismiss();
                }
                PaymentRequestActivity paymentRequestActivity = PaymentRequestActivity.this;
                ConstantClass.displayMessageDialog(paymentRequestActivity, paymentRequestActivity.getString(R.string.server_problem), t.getMessage());
            }
        });
    }


    private void DisplayUserBankList(List<CompanyBankResponse> bankDataArrayList, List<String> listBanksAccount) {
        @SuppressLint("ResourceType") final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, 17367043, 16908308, listBanksAccount);
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.material_list);
        dialog.setCancelable(false);
        dialog.setTitle("Select Bank");
        ListView listView = (ListView) dialog.findViewById(R.id.list);
        EditText search_edit = (EditText) dialog.findViewById(R.id.search_edit);
        dialog.show();
        listView.setAdapter((ListAdapter) adapter);
        search_edit.addTextChangedListener(new TextWatcher() { // from class: com.uvapay.activities.PaymentRequestActivity.16
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence);
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.uvapay.activities.PaymentRequestActivity.17
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PaymentRequestActivity.this.edit_user_bank.setText(parent.getItemAtPosition(position).toString());
                PaymentRequestActivity.this.BankId = String.valueOf(((CompanyBankResponse) bankDataArrayList.get(position)).getBankId());
                dialog.cancel();
            }
        });
        handleBackPressed(dialog);
    }

    private void makeMoneyRequest() {
        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue(this);
        progressDialog.show();
        HashMap<String, String> body = new HashMap<>();
        String deviceid = PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, "");
        String token = PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, "");
        String bankid = this.BankId;
        String paymentMode = this.PayId;
        String amount = this.edit_amount_.getText().toString();
        String bankTransactionNumber = this.edit_transactionno.getText().toString();
        String uniqueCode = PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, "");
        String description = this.edit_remarks.getText().toString();

        body.put("DeviceId", deviceid);
        body.put("Token", token);
        body.put("BankId", bankid);
        body.put("PaymentMode", paymentMode);
        body.put("Amount", amount);
        body.put("BankTransactionNumber", bankTransactionNumber);
        body.put("ReceiptURL", PrefUtils.getFromPrefs(this, ConstantClass.SERVER_IMG_NAME, ""));
        body.put("UniqueCode", uniqueCode);
        body.put("Description", description);


        ApiInterface apiservice = RetrofitHandler.getService();
        Call<RequestPaymentResponse> call = apiservice.GetPaymentRequest(body);
        call.enqueue(new Callback<RequestPaymentResponse>() {
            @Override
            public void onResponse(Call<RequestPaymentResponse> call2, Response<RequestPaymentResponse> response) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                if (response != null) {
                    if (response.body().getResponseStatus().intValue() == 1) {

                        Toast.makeText(PaymentRequestActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();

                        ConstantClass.displayMessageDialog(PaymentRequestActivity.this, "Response", response.body().getRemarks() + " " + response.body().getStatus());
                        PaymentRequestActivity.this.edit_amount_.setText("");
                        PaymentRequestActivity.this.edit_check_date.setText("");
                        PaymentRequestActivity.this.edit_cheque_number.setText("");
                        PaymentRequestActivity.this.edit_payment_date.setText("");
                        PaymentRequestActivity.this.edit_bank.setText("");
                        PaymentRequestActivity.this.edit_paymentmode.setText("");
                        PaymentRequestActivity.this.edit_cashtype.setText("");
                        PaymentRequestActivity.this.edit_transactionno.setText("");
                        PaymentRequestActivity.this.edit_utr_no.setText("");
                        PaymentRequestActivity.this.edit_user_bank.setText("");
                        PaymentRequestActivity.this.edit_remarks.setText("");
                        PaymentRequestActivity.this.strBankCode = "";
                        PaymentRequestActivity.this.BankId = "";
                        PaymentRequestActivity.this.PayId = "";
                        PaymentRequestActivity.this.branchname = "";
                        PaymentRequestActivity.this.checkCredit.setChecked(false);
                        PaymentRequestActivity.this.image_slip.setImageBitmap(null);
                        return;
                    }
                    ConstantClass.displayMessageDialog(PaymentRequestActivity.this, "Response", response.body().getRemarks());
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<RequestPaymentResponse> call2, Throwable t) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                PaymentRequestActivity paymentRequestActivity = PaymentRequestActivity.this;
                ConstantClass.displayMessageDialog(paymentRequestActivity, paymentRequestActivity.getString(R.string.server_problem), t.getMessage());
            }
        });
    }

    private void DisplayCashType(String[] paymentType) {

        @SuppressLint("ResourceType") ArrayAdapter<String> adapter = new ArrayAdapter<>(this, 17367043, 16908308, paymentType);

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.material_list);
        dialog.setCancelable(false);
        dialog.setTitle("Select Bank");
        final ListView listView = (ListView) dialog.findViewById(R.id.list);
        EditText search_edit = (EditText) dialog.findViewById(R.id.search_edit);
        search_edit.setVisibility(View.VISIBLE);
        dialog.show();
        listView.setAdapter((ListAdapter) adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PaymentRequestActivity.this.edit_cashtype.setText((String) listView.getItemAtPosition(position));
                dialog.cancel();
            }
        });
        handleBackPressed(dialog);
    }

    private void DisplayPaymentType(List<String> paymentMode) {
        @SuppressLint("ResourceType") ArrayAdapter<String> adapter = new ArrayAdapter<>(this, 17367043, 16908308, paymentMode);
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.material_list);
        dialog.setCancelable(false);
        dialog.setTitle("Select Payment Mode");
        final ListView listView = (ListView) dialog.findViewById(R.id.list);
        EditText search_edit = (EditText) dialog.findViewById(R.id.search_edit);
        search_edit.setVisibility(View.VISIBLE);
        dialog.show();
        listView.setAdapter((ListAdapter) adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PaymentRequestActivity.this.edit_paymentmode.setText((String) listView.getItemAtPosition(position));
                PaymentRequestActivity paymentRequestActivity = PaymentRequestActivity.this;
                paymentRequestActivity.PayId = String.valueOf(id);
                Log.d("Name", " : " + PaymentRequestActivity.this.edit_paymentmode.getText().toString());
                Log.d("Id", " : " + id);
                dialog.cancel();
            }
        });
        handleBackPressed(dialog);
    }

    private void handleBackPressed(final Dialog mDialog) {
        mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface arg0, int keyCode, KeyEvent event) {
                if (keyCode == 4) {
                    mDialog.dismiss();
                    return true;
                }
                return false;
            }
        });
    }


    private void getPaymentMode() {
        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue(this);
        progressDialog.show();
        HashMap<String, String> body = new HashMap<>();
        body.put("DeviceId", PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, ""));
        body.put("Token", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, ""));
        ApiInterface apiservice = (ApiInterface) RetrofitHandler.getService();
        Call<PaymentModeResponse> call = apiservice.GetPaymentMode(body);
        call.enqueue(new Callback<PaymentModeResponse>() {
            @Override
            public void onResponse(Call<PaymentModeResponse> call2, Response<PaymentModeResponse> response) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                if (response.body() != null) {
                    if (response.body().getResponseStatus().intValue() == 1) {
                        PaymentRequestActivity.this.paymentType.clear();
                        if (response.body().getPaymentMode() != null || !response.body().getPaymentMode().isEmpty()) {
                            PaymentRequestActivity.this.paymentType = response.body().getPaymentMode();
                            PaymentRequestActivity.this.paymentMode.clear();
                            for (int i = 0; i < PaymentRequestActivity.this.paymentType.size(); i++) {
                                String payMode = PaymentRequestActivity.this.paymentType.get(i).getName();
                                PaymentRequestActivity.this.paymentMode.add(payMode);
                            }
                            return;
                        }
                        return;
                    }
                    ApplicationConstant.DisplayMessageDialog(PaymentRequestActivity.this, "Response", response.body().getRemarks());
                    return;
                }
                ProgressDialog progressDialog3 = progressDialog;
                if (progressDialog3 != null && progressDialog3.isShowing()) {
                    progressDialog.dismiss();
                }
                try {
                    ConstantClass.displayMessageDialog(PaymentRequestActivity.this, "Response", response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<PaymentModeResponse> call2, Throwable t) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                PaymentRequestActivity paymentRequestActivity = PaymentRequestActivity.this;
                ConstantClass.displayMessageDialog(paymentRequestActivity, paymentRequestActivity.getString(R.string.server_problem), t.getMessage());
            }
        });
    }

    private void initComponents() {
        this.edit_amount_ = (EditText) findViewById(R.id.edit_amount_);
        this.edit_remarks = (EditText) findViewById(R.id.edit_remarks);
        this.edit_paymentmode = (TextView) findViewById(R.id.edit_paymentmode);
        this.edit_cashtype = (TextView) findViewById(R.id.edit_cashtype);
        this.edit_payment_date = (TextView) findViewById(R.id.edit_payment_date);
        this.edit_cheque_number = (EditText) findViewById(R.id.edit_cheque_number);
        this.edit_check_date = (TextView) findViewById(R.id.edit_check_date);
        this.edit_bank = (TextView) findViewById(R.id.edit_bank);
        this.edit_utr_no = (EditText) findViewById(R.id.edit_utr_no);
        this.btn_payment_request = (Button) findViewById(R.id.btn_payment_request);
        this.linear_dd_date = (FrameLayout) findViewById(R.id.linear_dd_date);
        this.linear_dd_number = (FrameLayout) findViewById(R.id.linear_dd_number);
        this.layout_cash = (FrameLayout) findViewById(R.id.layout_cash);
        this.edit_transactionno = (EditText) findViewById(R.id.edit_transactionno);
        this.layout_transaction = (FrameLayout) findViewById(R.id.layout_transaction);
        this.layout_userbank = (FrameLayout) findViewById(R.id.layout_userbank);
        this.edit_user_bank = (TextView) findViewById(R.id.edit_user_bank);
        this.TV_ReceiptURL = (TextView) findViewById(R.id.TV_ReceiptURL);
        this.layout_utr = (FrameLayout) findViewById(R.id.layout_utr);
        this.image_slip = (ImageView) findViewById(R.id.image_slip);
        this.checkCredit = (CheckBox) findViewById(R.id.checkCredit);
        this.check_parent = (CheckBox) findViewById(R.id.check_parent);
        this.check_admin = (CheckBox) findViewById(R.id.check_admin);
    }


    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}