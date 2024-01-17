package com.example.api_call;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineScope;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PaymentRequestActivity extends AppCompatActivity {

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
    List<PaymentMode> paymentType = new ArrayList();
    List<String> paymentMode = new ArrayList();
    private List<GetBankResponse> listAdminBanks = new ArrayList();
    private List<GetBankResponse> listParentBanks = new ArrayList();
    private List<CompanyBankResponse> listUserBanks = new ArrayList();
    private List<String> listAdminBanksAccount = new ArrayList();
    private List<String> listParentBanksAccount = new ArrayList();
    private List<String> listUserBanksAccount = new ArrayList();
    private String branchname = "";
    String photo_1 = " ";
    private String checkCreditString = "false";
    private String PaymentAmount = "";
    private String BankName = "";
    private String PaymentMode = "";
    private String CashType = "";
    private String UserBankName = "";
    private String BranchName = "";
    private String BranchCode = "";
    private String ChequeNo = " ";
    private String TransactionNo = "";
    private String Location = "";
    private String PaymentDate = "";
    private String PaymentTime = "";
    private String UTRNumber = "";
    private String UserId = "";
    private String PayId = "";
    private String BankId = "";

    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_request);
        setTitle("Payment Request");
        initComponents();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
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

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @SuppressLint("NewApi")
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK){
                    Intent data = result.getData();
                    Uri uri = data.getData();
                    try{
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                        image_slip.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                }
            }

        });
        this.btn_payment_request.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.PaymentRequestActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {

//
//                ByteArrayOutputStream byteArrayOutputStream;
//                byteArrayOutputStream = new ByteArrayOutputStream();
//                if (bitmap != null){
//                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
//                    byte [] bytes = byteArrayOutputStream.toByteArray();
////                    final String base64Image = Base64.encodeToString(bytes, Base64.DEFAULT);
//                }
//                else {
//                    Toast.makeText(PaymentRequestActivity.this, "Select Image FIrst", Toast.LENGTH_SHORT).show();
//                }
                PaymentRequestActivity.this.makeMoneyRequest();
            }
        });

        this.image_slip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                activityResultLauncher.launch(intent);

            }
        });
//        this.image_slip.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.PaymentRequestActivity.8
//            @Override // android.view.View.OnClickListener
//            public void onClick(View view) {
//                PaymentRequestActivity.this.selectImage();
//            }
//        });

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
                ConstantClass.displayMessageDialog(paymentRequestActivity, paymentRequestActivity.getString(R.string.server_problem), t.getMessage().toString());
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

//    private void selectImage() {
//        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Add Photo!");
//        builder.setItems(options, new DialogInterface.OnClickListener() { // from class: com.uvapay.activities.PaymentRequestActivity.23
//            @Override // android.content.DialogInterface.OnClickListener
//            public void onClick(DialogInterface dialog, int item) {
//                if (!options[item].equals("Take Photo")) {
//                    if (options[item].equals("Choose from Gallery")) {
//                        Intent intent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                        PaymentRequestActivity.this.startActivityForResult(intent, 2);
//                        return;
//                    } else if (options[item].equals("Cancel")) {
//                        dialog.dismiss();
//                        return;
//                    } else {
//                        return;
//                    }
//                }
//                Intent takePictureIntent = new Intent("android.media.action.IMAGE_CAPTURE");
//                if (takePictureIntent.resolveActivity(PaymentRequestActivity.this.getPackageManager()) != null) {
//                    File photoFile = null;
//                    try {
//                        photoFile = PaymentRequestActivity.this.createImageFile("payment_slip", "Images");
//                    } catch (IOException e) {
//                    }
//                    if (photoFile != null) {
//                        Uri photoURI = FileProvider.getUriForFile(PaymentRequestActivity.this, "com.payinapp_label.android.fileprovider", photoFile);
//                        takePictureIntent.putExtra("output", photoURI);
//                        PaymentRequestActivity.this.startActivityForResult(takePictureIntent, 1);
//                    }
//                }
//            }
//        });
//        builder.show();
//    }

//    private File createImageFile(String file_image, String subfolder) throws IOException {
//        File f = new File(Environment.getExternalStorageDirectory(), "payinApp");
//        if (!f.exists()) {
//            f.mkdirs();
//        }
//        File sub_folder = new File(f, subfolder);
//        if (!sub_folder.exists()) {
//            sub_folder.mkdirs();
//        }
//        File f1 = new File(sub_folder, file_image + ".png");
//        if (!f1.exists()) {
//            this.photo_1 = f1.getAbsolutePath();
//            return f1;
//        }
//        deleteCache(this, f1);
//        File f1_new = new File(sub_folder, file_image + ".png");
//        this.photo_1 = f1_new.getAbsolutePath();
//        return f1_new;
//    }
    public static void deleteCache(Context context, File dir) {
        try {
            File dir2 = context.getCacheDir();
            dir2.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void makeMoneyRequest() {
        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue(this);
        progressDialog.show();
        HashMap<String, String> body = new HashMap<>();
        body.put("DeviceId", PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, ""));
        body.put("Token", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, ""));
        body.put("PaymentMode", this.PayId);
        body.put("BankId", this.BankId);
        body.put("Amount", this.edit_amount_.getText().toString());
        body.put("BankTransactionNumber", this.edit_transactionno.getText().toString());
        ByteArrayOutputStream byteArrayOutputStream;
        byteArrayOutputStream = new ByteArrayOutputStream();
        if (bitmap != null){
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        }
        else {
            Toast.makeText(PaymentRequestActivity.this, "Select Image FIrst", Toast.LENGTH_SHORT).show();
        }
        byte [] bytes = byteArrayOutputStream.toByteArray();
        final String base64Image = Base64.encodeToString(bytes, Base64.DEFAULT);
        body.put("ReceiptURL",base64Image);
        body.put("UniqueCode", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, ""));
        body.put("Description", this.edit_remarks.getText().toString());
//        ApiInterface apiservice = RetrofitHandler.getService();
//        Call<RequestPaymentResponse> call = apiservice.GetPaymentRequest(body);
//        call.enqueue(new Callback<RequestPaymentResponse>() { // from class: com.uvapay.activities.PaymentRequestActivity.13
//            @Override // retrofit2.Callback
//            public void onResponse(Call<RequestPaymentResponse> call2, Response<RequestPaymentResponse> response) {
//                ProgressDialog progressDialog2 = progressDialog;
//                if (progressDialog2 != null && progressDialog2.isShowing()) {
//                    progressDialog.dismiss();
//                }
//                if (response != null) {
//                    if (response.body().getResponseStatus().intValue() == 1) {
//                        ConstantClass.displayMessageDialog(PaymentRequestActivity.this, "Response", response.body().getRemarks() + " " + response.body().getStatus());
//                        PaymentRequestActivity.this.edit_amount_.setText("");
//                        PaymentRequestActivity.this.edit_check_date.setText("");
//                        PaymentRequestActivity.this.edit_cheque_number.setText("");
//                        PaymentRequestActivity.this.edit_payment_date.setText("");
//                        PaymentRequestActivity.this.edit_bank.setText("");
//                        PaymentRequestActivity.this.edit_paymentmode.setText("");
//                        PaymentRequestActivity.this.edit_cashtype.setText("");
//                        PaymentRequestActivity.this.edit_transactionno.setText("");
//                        PaymentRequestActivity.this.edit_utr_no.setText("");
//                        PaymentRequestActivity.this.edit_user_bank.setText("");
//                        PaymentRequestActivity.this.edit_remarks.setText("");
//                        PaymentRequestActivity.this.strBankCode = "";
//                        PaymentRequestActivity.this.BankId = "";
//                        PaymentRequestActivity.this.PayId = "";
//                        PaymentRequestActivity.this.branchname = "";
//                        PaymentRequestActivity.this.checkCredit.setChecked(false);
//                        PaymentRequestActivity.this.image_slip.setImageBitmap(null);
//                        return;
//                    }
//                    ConstantClass.displayMessageDialog(PaymentRequestActivity.this, "Response", response.body().getRemarks());
//                }
//            }
//
//            @Override // retrofit2.Callback
//            public void onFailure(Call<RequestPaymentResponse> call2, Throwable t) {
//                ProgressDialog progressDialog2 = progressDialog;
//                if (progressDialog2 != null && progressDialog2.isShowing()) {
//                    progressDialog.dismiss();
//                }
//                PaymentRequestActivity paymentRequestActivity = PaymentRequestActivity.this;
//                ConstantClass.displayMessageDialog(paymentRequestActivity, paymentRequestActivity.getString(R.string.server_problem), t.getMessage().toString());
//            }
//        });
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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.uvapay.activities.PaymentRequestActivity.20
            @Override // android.widget.AdapterView.OnItemClickListener
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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.uvapay.activities.PaymentRequestActivity.19
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PaymentRequestActivity.this.edit_paymentmode.setText((String) listView.getItemAtPosition(position));
                PaymentRequestActivity paymentRequestActivity = PaymentRequestActivity.this;
                paymentRequestActivity.PayId = String.valueOf(paymentRequestActivity.paymentType.get(position).getId());
                Log.i("Name", " : " + PaymentRequestActivity.this.edit_paymentmode.getText().toString());
                Log.i("Id", " : " + PaymentRequestActivity.this.PayId);
                dialog.cancel();
            }
        });
        handleBackPressed(dialog);
    }
    private void handleBackPressed(final Dialog mDialog) {
        mDialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.uvapay.activities.PaymentRequestActivity.21
            @Override // android.content.DialogInterface.OnKeyListener
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
        call.enqueue(new Callback<PaymentModeResponse>() { // from class: com.uvapay.activities.PaymentRequestActivity.12
            @Override // retrofit2.Callback
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

            @Override // retrofit2.Callback
            public void onFailure(Call<PaymentModeResponse> call2, Throwable t) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                PaymentRequestActivity paymentRequestActivity = PaymentRequestActivity.this;
                ConstantClass.displayMessageDialog(paymentRequestActivity, paymentRequestActivity.getString(R.string.server_problem), t.getMessage().toString());
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
        this.layout_utr = (FrameLayout) findViewById(R.id.layout_utr);
        this.image_slip = (ImageView) findViewById(R.id.image_slip);
        this.checkCredit = (CheckBox) findViewById(R.id.checkCredit);
        this.check_parent = (CheckBox) findViewById(R.id.check_parent);
        this.check_admin = (CheckBox) findViewById(R.id.check_admin);
    }

//    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Throwable t;
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == -1) {
//            if (requestCode == 1) {
//                try {
//                    File file = new File(this.photo_1);
//                    long length_check = file.length() / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
//                    if (length_check > 400) {
//                        Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
//                        bitmap.compress(Bitmap.CompressFormat.JPEG, 3, new FileOutputStream(file));
//                    }
//                    long length = file.length() / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
//                    Toast.makeText(this, "Image size is " + length + "kb", 0).show();
//                    if (length > 400) {
//                        ApplicationConstant.DisplayMessageDialog(this, "Check image Size", "Image size should be less than 400kb");
//                    } else {
//                        Glide.with((FragmentActivity) this).load(this.photo_1).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(this.image_slip);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            } else if (requestCode == 2) {
//                try {
//                    Uri selectedImage = data.getData();
//                    String[] filePath = {"_data"};
//                    Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
//                    c.moveToFirst();
//                    int columnIndex = c.getColumnIndex(filePath[0]);
//                    String picturePath = c.getString(columnIndex);
//                    File file2 = new File(picturePath);
//                    this.photo_1 = picturePath;
//                    c.close();
//                    long length2 = file2.length() / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
//                    Toast.makeText(this, "Image size is " + length2 + "kb", 0).show();
//                    if (length2 <= 400) {
//                        Glide.with((FragmentActivity) this).load(picturePath).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(this.image_slip);
//                        return;
//                    }
//                    BitmapFactory.decodeFile(picturePath);
//                    File file22 = new File(this.photo_1);
//                    try {
//                        Bitmap bitmap2 = BitmapFactory.decodeFile(file22.getPath());
//                        try {
//                            bitmap2.compress(Bitmap.CompressFormat.JPEG, 3, new FileOutputStream(file22));
//                        } catch (Throwable th) {
//                            t = th;
//                            Log.e("ERROR", "Error compressing file." + t.toString());
//                            t.printStackTrace();
//                            Glide.with((FragmentActivity) this).load(this.photo_1).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(this.image_slip);
//                        }
//                    } catch (Throwable th2) {
//                        t = th2;
//                    }
//                    Glide.with((FragmentActivity) this).load(this.photo_1).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(this.image_slip);
//                } catch (Exception e2) {
//                    e2.printStackTrace();
//                }
//            }
//        }
//    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}