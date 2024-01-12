package com.example.api_call;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/** @noinspection SingleStatementInBlock*/
public class OperatorsActivity extends AppCompatActivity implements OperatorAdapter.SelectOperatorfromBack {
    Intent intent;
    private Context mContext;
    RecyclerView operatorView;
    private ProgressDialog progressDialog;
    Toolbar toolbar;
    Context context = this;

    OperatorAdapter OperatorAdapter;

    List<Operater> operatorDataArrayList = new ArrayList();
    List<Operater> sorted_operatorList = new ArrayList();
    private String Call = "";
    private String Operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operators);
        setTitle("Operators List");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.operator_recyclerView);
        this.operatorView = recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        ProgressDialog dialogue = CustomProgressDialog.getDialogue(this);
        this.progressDialog = dialogue;
        dialogue.show();
        this.Call = getIntent().getExtras().getString("CALL");
        getOperatorList();
    }

    private void getOperatorList(){
        ApiInterface apiservice =RetrofitHandler.getService();
        Call<OperatorResponse> call = apiservice.getAllOperators();
        call.enqueue(new Callback<OperatorResponse>() { // from class: com.uvapay.activities.OperatorsActivity.1
            @Override // retrofit2.Callback
            public void onResponse(Call<OperatorResponse> call2, Response<OperatorResponse> response) {
                if (response != null) {
                    if (response.body().getResponseStatus().equals("0")) {
                        if (OperatorsActivity.this.progressDialog.isShowing() && OperatorsActivity.this.progressDialog != null) {
                            OperatorsActivity.this.progressDialog.dismiss();
                        }
//                        ConstantClass.displayMessageDialog(OperatorsActivity.this, "" + response.body().getErrorCode(), response.body().getStatus());
                        return;
                    }
                    if (OperatorsActivity.this.progressDialog.isShowing() && OperatorsActivity.this.progressDialog != null) {
                        OperatorsActivity.this.progressDialog.dismiss();
                    }
                    OperatorsActivity.this.operatorDataArrayList = response.body().getOperaters();

                    for (Operater datum : OperatorsActivity.this.operatorDataArrayList) {
                        if (OperatorsActivity.this.Call.equals("MOBILE") && datum.getServiceName() != null && datum.getServiceName().equals("MOBILE")) {
                            OperatorsActivity.this.sorted_operatorList.add(datum);
                        } else if (OperatorsActivity.this.Call.equals("DTH") && datum.getServiceName() != null && datum.getServiceName().equals("DTH")) {
                            OperatorsActivity.this.sorted_operatorList.add(datum);
                        } else if (OperatorsActivity.this.Call.equals("POSTPAID") && datum.getServiceName() != null && datum.getServiceName().equals("POSTPAID")) {
                            OperatorsActivity.this.sorted_operatorList.add(datum);
                        }
                    }


//                   try {
//
//                    for(Operater datum :OperatorsActivity.this.operatorDataArrayList) {
//                       if (OperatorsActivity.this.Call.equals("MOBILE") && datum.getServiceName() != null && datum.getServiceName().equals("MOBILE")) {
//                           OperatorsActivity.this.sorted_operatorList.add(datum);
//                       }
//                    }
//
//                   } catch (Exception e) {
//                       e.printStackTrace();
//                   }

//                    try {
//                        for (Operater datum2 : OperatorsActivity.this.operatorDataArrayList) {
//                            if (OperatorsActivity.this.Call.equals("DTH") && datum2.getServiceName() != null && datum2.getServiceName().equals("DTH")) {
//                                OperatorsActivity.this.sorted_operatorList.add(datum2);
//                            }
//                        }
//                    } catch (Exception e2) {
//                        e2.printStackTrace();
//                    }
//                    try {
//                        for (Operater datum3 : OperatorsActivity.this.operatorDataArrayList) {
//                            if (OperatorsActivity.this.Call.equals("POSTPAID") && datum3.getServiceName() != null && datum3.getServiceName().equals("POSTPAID")) {
//                                OperatorsActivity.this.sorted_operatorList.add(datum3);
//                            }
//                        }
//                    } catch (Exception e3) {
//                        e3.printStackTrace();
//                    }

                    OperatorsActivity operatorsActivity = OperatorsActivity.this;
                    OperatorAdapter operatorAdapter = new OperatorAdapter(operatorsActivity, (ArrayList) operatorsActivity.sorted_operatorList);
                    OperatorsActivity.this.operatorView.setAdapter(operatorAdapter);
                    operatorAdapter.ChooseOperator(OperatorsActivity.this);

                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<OperatorResponse> call2, Throwable t) {
                if (OperatorsActivity.this.progressDialog.isShowing() && OperatorsActivity.this.progressDialog != null) {
                    OperatorsActivity.this.progressDialog.dismiss();
                }
                OperatorsActivity operatorsActivity = OperatorsActivity.this;
                ConstantClass.displayMessageDialog(operatorsActivity, operatorsActivity.getString(R.string.server_problem), t.getMessage().toString());
            }
        });
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void selectOperatorfromlist(int position) {
        ConstantClass.datum = this.sorted_operatorList.get(position);
        if (getIntent().getExtras().getString("CALL").equals("MOBILE")) {
            Intent intent = new Intent();
            intent.putExtra("NUMBER", getIntent().getExtras().getString("NUMBER"));
            intent.putExtra("AMT", getIntent().getExtras().getString("AMT"));
            intent.putExtra("CALL", getIntent().getExtras().getString("CALL"));
            setResult(-1, intent);
            finish();
        } else if (getIntent().getExtras().getString("CALL").equals("POSTPAID")) {
            Intent intent2 = new Intent();
            intent2.putExtra("NUMBER", getIntent().getExtras().getString("NUMBER"));
            intent2.putExtra("AMT", getIntent().getExtras().getString("AMT"));
            intent2.putExtra("CALL", getIntent().getExtras().getString("CALL"));
            setResult(-1, intent2);
            finish();
        } else if (getIntent().getExtras().getString("CALL").equals("DATA_CARD")) {
            Intent intent3 = new Intent();
            intent3.putExtra("NUMBER", getIntent().getExtras().getString("NUMBER"));
            intent3.putExtra("AMT", getIntent().getExtras().getString("AMT"));
            intent3.putExtra("CALL", getIntent().getExtras().getString("CALL"));
            setResult(-1, intent3);
            finish();
        } else if (getIntent().getExtras().getString("CALL").equals("DTH")) {
            Intent intent4 = new Intent();
            intent4.putExtra("NUMBER", getIntent().getExtras().getString("NUMBER"));
            intent4.putExtra("AMT", getIntent().getExtras().getString("AMT"));
            intent4.putExtra("CALL", getIntent().getExtras().getString("CALL"));
            setResult(-1, intent4);
            finish();
        }
    }
}