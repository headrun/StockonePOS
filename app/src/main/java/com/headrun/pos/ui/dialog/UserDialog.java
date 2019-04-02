package com.headrun.pos.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.headrun.pos.R;

public class UserDialog {

    private Context context;
    private Dialog dialog;
    private ImageView img_cross;
    private EditText et_user_name;
    private EditText et_user_no;
    private EditText et_user_email;
    private Button btn_add_user;


    public UserDialog(Context context) {
        this.context = context;
        init();
    }

    private void init(){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.user_dialog);
        dialog.setCancelable(false);

        img_cross = dialog.findViewById(R.id.img_cross);
        et_user_name = dialog.findViewById(R.id.et_user_name);
        et_user_no = dialog.findViewById(R.id.et_user_no);
        et_user_email = dialog.findViewById(R.id.et_user_email);
        btn_add_user = dialog.findViewById(R.id.btn_add_user);


        dialog.setCanceledOnTouchOutside(true);

        if(dialog.getWindow() != null) {
//            dialog.getWindow().getAttributes().windowAnimations = R.style.Slide_Up_Down;
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }

        img_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.hide();
            }
        });
    }


    public void show(){
        try {
            dialog.show();
        } catch (Exception e){

        }
    }

    public void close(){
        dialog.hide();
    }
}
