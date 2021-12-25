package id.unud.ac.aplikasilistbaju;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HasilActivity extends AppCompatActivity {

    TextView HBrand, HJenis, HUkuran, HJumlah;
    long id;
    private Button btnKembali;
    DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);

        dbHandler = new DBHandler(this);
        HBrand = (TextView) findViewById(R.id.hasil_brand);
        HJenis = (TextView) findViewById(R.id.hasil_jenis);
        HUkuran = (TextView) findViewById(R.id.hasil_ukuran);
        HJumlah = (TextView) findViewById(R.id.hasil_jumlah);

        Intent intent=getIntent();
        id=intent.getLongExtra(DBHandler.row_id_baju,0);
        getDataDB();
//        Intent i = getIntent();
//        HBrand.setText(i.getExtras().getString("brand"));
//        HJenis.setText(i.getExtras().getString("jenis"));
//        HUkuran.setText(i.getExtras().getString("ukuran"));
//        HJumlah.setText(i.getExtras().getString("jumlah"));

        btnKembali = findViewById(R.id.btn_kembali);
        btnKembali.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ListActivity.class);
                startActivity(i);
            }
        });

    }

    private void getDataDB(){
        Cursor cursor = dbHandler.getDataBaju(id);
        if(cursor.moveToFirst()){
            String brand = cursor.getString((Integer) cursor.getColumnIndex(DBHandler.row_brand));
            String jenis = cursor.getString((Integer) cursor.getColumnIndex(DBHandler.row_jenis));
            String ukuran = cursor.getString((Integer) cursor.getColumnIndex(DBHandler.row_ukuran));
            String jumlah = cursor.getString((Integer) cursor.getColumnIndex(DBHandler.row_jumlah));

            HBrand.setText(brand);
            HJenis.setText(jenis);
            HUkuran.setText(ukuran);
            HJumlah.setText(jumlah);

            Toast.makeText(this, brand, Toast.LENGTH_SHORT).show();
        }
    }

    //activity lifecycle
    @Override
    protected void onStart(){
        super.onStart();

        Toast.makeText(this, "Proses Berhasil", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume(){
        super.onResume();
        Toast.makeText(this, "Resume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause(){
        super.onPause();

        Toast.makeText(this, "Pause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop(){
        super.onStop();

        Toast.makeText(this, "Selamat Tinggal", Toast.LENGTH_SHORT).show();
    }
}