package com.example.api_call;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    private ImageView camera;
    private CardView card_history;
    private CardView card_password;
    private CardView card_profile;
    private CardView card_tpin;
    DashboardActivity dashboardActivity;
    private CircleImageView image_prof;
    private LinearLayout linear_retailer;
    private ProgressDialog progressDialog;
    private TextView text_address;
    private TextView text_mail;
    private TextView text_name;
    private TextView text_number;
    private TextView text_pan;
    private String call = "";
    String photo_1 = " ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("My Profile");
        PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, "");
        initComponents();
        setProfile();
        this.camera.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.ProfileActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                ProfileActivity.this.selectImage();
            }
        });
        this.card_password.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.ProfileActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                View view = ProfileActivity.this.getLayoutInflater().inflate(R.layout.layout_change_password, (ViewGroup) null);
                ImageView image_cancel = (ImageView) view.findViewById(R.id.image_cancel);
                final EditText edit_old_password = (EditText) view.findViewById(R.id.edit_old_password);
                final EditText edit_new_password = (EditText) view.findViewById(R.id.edit_new_password);
                final EditText edit_confirm_password = (EditText) view.findViewById(R.id.edit_confirm_password);
                TextView textView = (TextView) view.findViewById(R.id.text_pass_change);
                Button btn_change_password = (Button) view.findViewById(R.id.btn_change_password);
                LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.layout_hide);
//                CheckView checkView = (CheckView) view.findViewById(R.id.check);
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                final AlertDialog alertDialog = builder.create();
                alertDialog.setView(view);
                image_cancel.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.ProfileActivity.2.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v2) {
                        alertDialog.dismiss();
                    }
                });
                btn_change_password.setOnClickListener(new View.OnClickListener() { // from class: com.uvapay.activities.ProfileActivity.2.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v2) {
                        if (!ConstantClass.isNetworkAvailable(ProfileActivity.this)) {
                            ConstantClass.displayMessageDialog(ProfileActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");
                        } else if (edit_old_password.getText().toString().isEmpty()) {
                            edit_old_password.setError("Enter Old Password");
                            edit_old_password.requestFocus();
                        } else if (edit_new_password.getText().toString().isEmpty()) {
                            edit_old_password.setError(null);
                            edit_new_password.setError("Enter New Password");
                            edit_new_password.requestFocus();
                        } else if (edit_confirm_password.getText().toString().isEmpty()) {
                            edit_new_password.setError(null);
                            edit_confirm_password.setError("Enter Confirm Password");
                            edit_confirm_password.requestFocus();
                        } else if (!edit_confirm_password.getText().toString().trim().equals(edit_new_password.getText().toString().trim())) {
                            edit_confirm_password.setError("New Password & Confirm should be same");
                            edit_confirm_password.requestFocus();
                        } else if (!ConstantClass.isNetworkAvailable(ProfileActivity.this)) {
                            ConstantClass.displayMessageDialog(ProfileActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");
                        } else {
                            ProfileActivity.this.getChangePassword(edit_old_password.getText().toString(), edit_new_password.getText().toString(), edit_confirm_password.getText().toString(), alertDialog);
                        }
                    }
                });
                if (Build.VERSION.SDK_INT >= 21) {
                    alertDialog.create();
                    alertDialog.show();
                }
            }
        });


    }

    private void getChangePassword(String old_password, String new_password, String confirm, final AlertDialog alertDialog) {
        String username = PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserName, "");
        PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.UserPassword, "");
        final ProgressDialog progressDialog = CustomProgressDialog.getDialogue(this);
        progressDialog.show();
        HashMap<String, String> body = new HashMap<>();
        body.put(ConstantClass.PROFILEDETAILS.UserName_, username);
        body.put("DeviceId", PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.DeviceId, ""));
        body.put("Token", PrefUtils.getFromPrefs(this, ConstantClass.USERDETAILS.Token, ""));
        body.put("Password", new_password);
        body.put("ConfirmPassword", confirm);
        body.put("OldPassword", old_password);
        body.put("PasswordType", ConstantClass.MOBILESERVICEID);
        ProfileApiService apiservice = (ProfileApiService) RetrofitHandler.getService();
        Call<ChangePasswordResponse> call = apiservice.ChangePasswordResponse(body);
        call.enqueue(new Callback<ChangePasswordResponse>() { // from class: com.uvapay.activities.ProfileActivity.8
            @Override // retrofit2.Callback
            public void onResponse(Call<ChangePasswordResponse> call2, Response<ChangePasswordResponse> response) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                if (response.body() != null) {
                    if (response.body().getStatusCode().intValue() == 1) {
                        ApplicationConstant.displayToastMessage(ProfileActivity.this, response.body().getMessage());
                        ProfileActivity.this.startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                        ProfileActivity.this.finish();
                        return;
                    }
                    ApplicationConstant.DisplayMessageDialog(ProfileActivity.this, "Response", response.body().getMessage());
                    alertDialog.dismiss();
                    return;
                }
                ProgressDialog progressDialog3 = progressDialog;
                if (progressDialog3 != null && progressDialog3.isShowing()) {
                    progressDialog.dismiss();
                }
                try {
                    ConstantClass.displayMessageDialog(ProfileActivity.this, "Response", response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<ChangePasswordResponse> call2, Throwable t) {
                ProgressDialog progressDialog2 = progressDialog;
                if (progressDialog2 != null && progressDialog2.isShowing()) {
                    progressDialog.dismiss();
                }
                ProfileActivity profileActivity = ProfileActivity.this;
                ConstantClass.displayMessageDialog(profileActivity, profileActivity.getString(R.string.server_problem), t.getMessage().toString());
            }
        });
    }

    private void selectImage() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() { // from class: com.uvapay.activities.ProfileActivity.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    Intent takePictureIntent = new Intent("android.media.action.IMAGE_CAPTURE");
                    if (takePictureIntent.resolveActivity(ProfileActivity.this.getPackageManager()) != null) {
                        File photoFile = null;
                        try {
                            photoFile = ProfileActivity.this.createImageFile(Scopes.PROFILE, "payinProf");
                        } catch (IOException e) {
                        }
                        if (photoFile != null) {
                            Uri photoURI = FileProvider.getUriForFile(ProfileActivity.this, "com.payin.android.fileprovider", photoFile);
                            takePictureIntent.putExtra("output", photoURI);
                            ProfileActivity.this.startActivityForResult(takePictureIntent, 1);
                        }
                    }
                } else if (options[item].equals("Choose from Gallery")) {
                    Intent intent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    ProfileActivity.this.startActivityForResult(intent, 2);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void setProfile() {
        this.text_name.setText(PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.Name, "") + " " + PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.Last, ""));
        this.text_address.setText(PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.ShopAddress, ""));
        this.text_number.setText(PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.MobileNo, ""));
        this.text_mail.setText(PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.EmailId, ""));
        this.text_pan.setText(PrefUtils.getFromPrefs(this, ConstantClass.PROFILEDETAILS.PanCard, ""));
    }

    private void initComponents() {
        this.card_profile = (CardView) findViewById(R.id.card_profile);
        this.card_history = (CardView) findViewById(R.id.card_history);
        this.card_password = (CardView) findViewById(R.id.card_password);
        this.text_name = (TextView) findViewById(R.id.text_name);
        this.text_mail = (TextView) findViewById(R.id.text_mail);
        this.text_number = (TextView) findViewById(R.id.text_number);
        this.text_address = (TextView) findViewById(R.id.text_address);
        this.text_pan = (TextView) findViewById(R.id.text_pan);
        this.image_prof = (CircleImageView) findViewById(R.id.image_profile);
        this.camera = (ImageView) findViewById(R.id.camera);
        this.linear_retailer = (LinearLayout) findViewById(R.id.linear_retailer);
    }
    public File createImageFile(String file_image, String subfolder) throws IOException {
        File f = new File(Environment.getExternalStorageDirectory(), "payinApp");
        if (!f.exists()) {
            f.mkdirs();
        }
        File sub_folder = new File(f, subfolder);
        if (!sub_folder.exists()) {
            sub_folder.mkdirs();
        }
        File f1 = new File(sub_folder, file_image + ".png");
        if (!f1.exists()) {
            this.photo_1 = f1.getAbsolutePath();
            return f1;
        }
        deleteCache(this, f1);
        File f1_new = new File(sub_folder, file_image + ".png");
        this.photo_1 = f1_new.getAbsolutePath();
        return f1_new;
    }
    public static void deleteCache(Context context, File dir) {
        try {
            File dir2 = context.getCacheDir();
            dir2.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}