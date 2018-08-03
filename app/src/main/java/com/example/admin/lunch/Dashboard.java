package com.example.admin.lunch;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.admin.lunch.fragment.MyProfile;

public class Dashboard extends AppCompatActivity {

        ImageView addChild, transaction, orderNow, viewOrCancelOrder, myProfile, changePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        getSupportActionBar().hide();
        addChild = (ImageView)findViewById(R.id.imageView1);
        transaction = (ImageView)findViewById(R.id.imageView2);
        orderNow = (ImageView)findViewById(R.id.imageView3);
        viewOrCancelOrder = (ImageView)findViewById(R.id.imageView4);
        myProfile = (ImageView)findViewById(R.id.imageView5);
        changePassword = (ImageView)findViewById(R.id.imageView6);

        addChild.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
               /* Intent intent = new Intent(Dashboard.this,AddChildActivity.class);
                startActivity(intent);*/

                                            final Dialog dialog = new Dialog(Dashboard.this);

                                            dialog.setContentView(R.layout.add_child_custom_dialog);
                                            ImageView addChild = (ImageView) dialog.findViewById(R.id.imageView7);
                                            ImageView close = (ImageView) dialog.findViewById(R.id.imageView_close);

                                            ImageView addTeacher = (ImageView) dialog.findViewById(R.id.imageView8);
                                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);


                                            close.setOnClickListener(new View.OnClickListener() {
                                                                         @Override
                                                                         public void onClick(View v) {
                                                                             dialog.dismiss();
                                                                         }
                                                                     });

                                            addChild.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Intent intent = new Intent(Dashboard.this, AddChildActivity.class);
                                                    startActivity(intent);
                                                }
                                            });

                                            addTeacher.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Intent intent = new Intent(Dashboard.this, AddTeacherActivity.class);
                                                    startActivity(intent);
                                                }
                                            });
                                            dialog.show();

                                        }


                                    });


        transaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this,Transaction.class);
                startActivity(intent);
            }
        });

        orderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this,CalenderMenuActivity.class);
                startActivity(intent);
            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this,ChangePassword.class);
                startActivity(intent);
            }
        });

        myProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, MyProfile.class);
                startActivity(intent);
            }
        });
    }
}
