package com.jhonarendra.restoran.customer;

/**
 * Created by Jhonarendra on 10/31/2018.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddContactActivity extends AppCompatActivity {
    private Button btnAdd;
    private EditText etContactName,etContactNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        setTitle("Add Contact");

        etContactName=findViewById(R.id.et_contact_name);
        etContactNumber=findViewById(R.id.et_contact_number);

        btnAdd=findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addContact();
            }
        });
    }

    private void addContact(){
        String name=etContactName.getText().toString();
        String number=etContactNumber.getText().toString();

        Toast.makeText(this, "Name : "+name+", number: "+number, Toast.LENGTH_SHORT).show();
    }
}

