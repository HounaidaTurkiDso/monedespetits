package com.example.hounayda.lemondedespetits;
import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class quiz1 extends Activity {
    DatabaseHelper myDb;
    EditText editnom,editprénom,editage,editsexe;
    Button btnAddData;
    Button aff ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz1);
        myDb = new DatabaseHelper(this);
aff =(Button)findViewById(R.id.aff);
        editnom = (EditText)findViewById(R.id.editnom);
        editprénom = (EditText)findViewById(R.id.editprénom);
        editage = (EditText)findViewById(R.id.editage);

        editsexe =(EditText)findViewById(R.id.editsexe);


        btnAddData = (Button)findViewById(R.id.button2);
        AddData();
        viewall();
    }

    public  void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(editnom.getText().toString(),
                                editprénom.getText().toString(),
                                editage.getText().toString(), editsexe.getText().toString());
                        if (isInserted = true)
                            Toast.makeText(quiz1.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(quiz1.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
public  void viewall(){



    aff.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            Cursor res = myDb.getAllData();
            if(res.getCount()==0){
                showMessage("error","Nothing Found ");

                return;

            }

            StringBuffer buffer = new StringBuffer();
               while (res.moveToNext()){
                   buffer.append("Code :" + res.getString(0)+"\n" );
                   buffer.append("Nom :" + res.getString(1)+"\n" );
                   buffer.append("Prénom :" + res.getString(2)+"\n" );
                   buffer.append("AGE :" + res.getString(3)+"\n" );
                   buffer.append("SEXE :" + res.getString(4)+"\n" );




               }


            //show
            showMessage("Data",buffer.toString());
        }
    });

}

    public void showMessage(String title ,String Message) {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);

        builder.setTitle("Data");
        builder.setMessage(Message);
        builder.show();
    }
}

