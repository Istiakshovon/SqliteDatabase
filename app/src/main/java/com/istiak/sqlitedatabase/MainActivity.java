package com.istiak.sqlitedatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etxtId,etxtName,etxtPhone;
    Button btnInsert,btnView,btnUpdate,btnDelete;

    SqliteDB mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etxtId=findViewById(R.id.etxt_id);
        etxtName=findViewById(R.id.etxt_name);
        etxtPhone=findViewById(R.id.etxt_phone);

        btnInsert=findViewById(R.id.btn_insert);


        mydb=new SqliteDB(MainActivity.this);



       btnInsert.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               String id=etxtId.getText().toString().trim();
               String name=etxtName.getText().toString().trim();
               String phone=etxtPhone.getText().toString().trim();

               if (id.isEmpty())
               {
                   etxtId.setError("oiii id input deee!");
                   etxtId.requestFocus();
               }

               else  if (name.isEmpty())
               {
                   etxtName.setError("oiii name input deee!");
                   etxtName.requestFocus();
               }

               else if (phone.length()!=11)
               {
                   etxtPhone.setError("oiii phone number vhul!");
                   etxtPhone.requestFocus();
               }


               else
               {
                   boolean check=mydb.insertData(id,name,phone);

                   if (check==true)
                   {
                       Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                   }
                   else
                   {
                       Toast.makeText(MainActivity.this, "Data not insert.Try again!", Toast.LENGTH_SHORT).show();
                   }
               }


           }
       });



}



    //for data
    public void viewData(View v)
    {
        Cursor result=mydb.display();
        if(result.getCount()==0)
        {
            Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
        }

        else
        {
            StringBuffer buffer=new StringBuffer();
            result.moveToFirst();  //to point first row

            do {

                buffer.append("ID: "+result.getString(0)+"\n");
                buffer.append("Name: "+result.getString(1)+"\n");
                buffer.append("Phone: "+result.getString(2)+"\n");

            }while (result.moveToNext());

            showData("Information",buffer.toString());
        }


    }



    public void showData(String title,String data)
    {

        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle(title);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setCancelable(true);
        dialog.setMessage(data);
        dialog.show();
    }



    //for update info
    public void updateData(View v)
    {
        String id=etxtId.getText().toString();
        String name=etxtName.getText().toString();
        String phone=etxtPhone.getText().toString();

        if (id.isEmpty())
        {
            etxtId.setError("oiii id input deee!");
            etxtId.requestFocus();
        }

        else  if (name.isEmpty())
        {
            etxtName.setError("oiii name input deee!");
            etxtName.requestFocus();
        }

        else if (phone.isEmpty())
        {
            etxtPhone.setError("oiii phone input deee!");
            etxtPhone.requestFocus();
        }


        else
        {
            boolean check=mydb.updateData(id,name,phone);

            if (check==true)
            {
                Toast.makeText(MainActivity.this, "Data update successfully", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(MainActivity.this, "Data not update.Try again!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    //for deleting
    public void deleteData(View v)
    {
        String id=etxtId.getText().toString();
        int check=mydb.deleteData(id);
        if(check==1)
        {
            Toast.makeText(this, "Data Deleted Sucessfully", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Data not deleted", Toast.LENGTH_SHORT).show();
        }
    }



}
