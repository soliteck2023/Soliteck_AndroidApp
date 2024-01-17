package com.example.api_call;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class raisecompliant extends AppCompatActivity {

    TextView Date;
    TextView Ref_no;
    EditText discription;
    Button summit,cancel;
    List<TransactionReport> listSatetments;
    private String TXNID = "";
    private String NUMBER = "";
    private String text_subject = "";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raisecompliant);

        Date = findViewById(R.id.date);
        Ref_no = findViewById(R.id.ref);
        discription = findViewById(R.id.edit_msg);
        summit = findViewById(R.id.btn_submit);
        cancel = findViewById(R.id.btn_cancel);

        try {
            this.TXNID = getIntent().getExtras().getString("TXNID");
        } catch (Exception e) {
            e.printStackTrace();
            this.TXNID = "";
        }
        try {
            this.NUMBER = getIntent().getExtras().getString("NUMBER");
        } catch (Exception e2) {
            e2.printStackTrace();
            this.NUMBER = "";
        }
        summit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                raiseComplaint();
            }
        });



    }

    private void raiseComplaint() {
        PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, "");
        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue(this);
        progressDialog.show();
        HashMap<String, String> body = new HashMap<>();
        body.put(ConstantClass.PROFILEDETAILS.UserName_, PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, ""));
        body.put("Password", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserPassword, ""));
        body.put("Subject", this.text_subject);
        body.put("Message", this.discription.getText().toString());
        body.put("TxnID", this.TXNID);
        ApiInterface apiInterface = RetrofitHandler.getService();
        Call<RaiseComplainResponse> objbanklist = apiInterface.RaiseComplain(body);

        objbanklist.enqueue(new Callback<RaiseComplainResponse>() { // from class: com.uvapay.activities.HelpSupportActivity.3
            @Override
            public void onResponse(Call<RaiseComplainResponse> call, Response<RaiseComplainResponse> response) {

                Toast.makeText(raisecompliant.this, "Raise Complaint", Toast.LENGTH_SHORT).show();

               ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
//                if (response.body().getStatusCode().equals(ConstantClass.MOBILESERVICEID)) {
//                    View view = raisecompliant.this.getLayoutInflater().inflate(R.layout.layout_successful, (ViewGroup) null);
//                    AlertDialog.Builder builder = new AlertDialog.Builder(raisecompliant.this);
//                    builder.setView(view);
//                   CheckView checkView = (CheckView) view.findViewById(R.id.check);
//                    TextView text_remark = (TextView) view.findViewById(R.id.text_remark);
//                    ImageView image_failed = (ImageView) view.findViewById(R.id.image_failed);
//                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() { // from class: com.uvapay.activities.HelpSupportActivity.3.1
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    });
//                    builder.create();
//                    builder.show();
//                    checkView.setVisibility(View.VISIBLE);
//                    image_failed.setVisibility(View.GONE);
//                    text_remark.setText(response.body().getMessage().toString());
//                    checkView.check();
//                    raisecompliant.this.discription.setText("");
//                    return;
//                }
//                View view2 = raisecompliant.this.getLayoutInflater().inflate(R.layout.layout_successful, (ViewGroup) null);
//                AlertDialog.Builder builder2 = new AlertDialog.Builder(raisecompliant.this);
//                builder2.setView(view2);
//                TextView text_remark2 = (TextView) view2.findViewById(R.id.text_remark);
//                ImageView image_failed2 = (ImageView) view2.findViewById(R.id.image_failed);
//                builder2.setPositiveButton("Ok", new DialogInterface.OnClickListener() { // from class: com.uvapay.activities.HelpSupportActivity.3.2
//                    @Override // android.content.DialogInterface.OnClickListener
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//                builder2.create();
//                builder2.show();
//               ((CheckView) view2.findViewById(R.id.check)).setVisibility(View.GONE);
//                image_failed2.setVisibility(View.VISIBLE);
//                text_remark2.setText(response.body().getMessage().toString());
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<RaiseComplainResponse> call, Throwable t) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                raisecompliant helpSupportActivity = raisecompliant.this;
                ConstantClass.displayMessageDialog(helpSupportActivity, helpSupportActivity.getString(R.string.server_problem), t.getMessage().toString());
            }
        });
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}