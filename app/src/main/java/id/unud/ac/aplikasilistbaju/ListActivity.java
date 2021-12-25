package id.unud.ac.aplikasilistbaju;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListActivity extends AppCompatActivity {
    private RecyclerView rv_data;
    private EditText edt_search;
    private FloatingActionButton btn_tambah;
    private DataAdapter dataAdapter;
    private AlertDialog.Builder dialog;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        dbHandler=new DBHandler(this);
        btn_tambah = findViewById(R.id.btn_tambah);
        btn_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListActivity.this, FormActivity.class));
            }
        });
        rv_data=findViewById(R.id.rv_data);
        rv_data.setLayoutManager(new LinearLayoutManager(this));
        dataAdapter=new DataAdapter(this, dbHandler.getAllDataBaju());
        rv_data.setAdapter(dataAdapter);
        dataAdapter.setDialog(new DataAdapter.Dialog() {
            @Override
            public void onClick(long id) {
                final CharSequence[] dialogItem ={"Lihat","Edit","Hapus"};
                dialog = new AlertDialog.Builder(ListActivity.this);
                dialog.setItems(dialogItem,new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Intent intent = new Intent(ListActivity.this, HasilActivity.class);
                                intent.putExtra(DBHandler.row_id_baju, id);
                                startActivity(intent);
                                break;
                            case 1:
                                Intent intent2 = new Intent(ListActivity.this, EditActivity.class);
                                intent2.putExtra(DBHandler.row_id_baju, id);
                                startActivity(intent2);
                                break;
                            case 2:
                                deleteData(id);
                                break;
                        }
                    }
                });

                dialog.show();
            }
        });


        }
    private void deleteData(long id){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Yakin Ingin Hapus Data?");
        alertDialogBuilder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                dbHandler.deleteDataBaju(id);
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

        alertDialogBuilder.setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}