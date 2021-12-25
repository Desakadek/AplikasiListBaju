package id.unud.ac.aplikasilistbaju;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class FormActivity extends AppCompatActivity {
    String brand;
    String jenis;
    String ukuran;
    String jumlah;

    DBHandler dbHandler;
    int jml = 0;
    EditText edBrand;
    RadioButton rbKaos, rbPolo, rbKemeja;
    CheckBox cbS, cbM, cbL, cbXL;
    SeekBar sbSeekbar;
    public TextView Jumlah;
    @SuppressLint("WrongViewCast")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        dbHandler = new DBHandler(this);

        edBrand = (EditText) findViewById(R.id.ed_brand);
        rbKaos = (RadioButton) findViewById(R.id.rb_kaos);
        rbPolo = (RadioButton) findViewById(R.id.rb_polo);
        rbKemeja = (RadioButton) findViewById(R.id.rb_kemeja);

        cbS = (CheckBox) findViewById(R.id.cb_s);
        cbM = (CheckBox) findViewById(R.id.cb_m);
        cbL = (CheckBox) findViewById(R.id.cb_l);
        cbXL = (CheckBox) findViewById(R.id.cb_xl);

        final TextView textJumlah = findViewById(R.id.txt_ptjml);
        SeekBar seekBar = findViewById(R.id.seekbar);


        textJumlah.setText(seekBar.getProgress() + "/" + seekBar.getMax());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textJumlah.setText(progress + "/" + seekBar.getMax());
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                jml = jml + (progressValue - progress);
                progress = progressValue;
                textJumlah.setText(jml + "/50");
                Jumlah = textJumlah;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
        });

    }

    public void inputData(View view) {
        brand = edBrand.getText().toString();
        jenis = "";
        ukuran = "";
        jumlah = Jumlah.getText().toString();



        if (rbKaos.isChecked()) {
            jenis += "Kaos";
        }
        if (rbPolo.isChecked()) {
            jenis += "Polo";
        }
        if (rbKemeja.isChecked()) {
           jenis += "Kemeja";
        }


        if (cbS.isChecked()) {
            ukuran += "S ";
        }
        if (cbM.isChecked()) {
            ukuran += "M ";
        }
        if (cbL.isChecked()) {
            ukuran += "L ";
        }
        if (cbXL.isChecked()) {
            ukuran += "XL ";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Berhasil Menambahkan Data");
        String finalJenis = jenis;
        String finalUkuran = ukuran;
        builder.setMessage("Brand Baju\t\t\t\t\t: " + String.valueOf(brand) + "\n" +
                "Jenis\t\t\t\t\t\t\t\t: " + String.valueOf(jenis) + "\n" +
                "Ukuran\t\t\t\t\t\t\t: " + String.valueOf(ukuran) + "\n" +
                "Jumlah\t\t\t\t\t\t: " + String.valueOf(jumlah) + "\n").setPositiveButton("Lanjut",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        openHasilActivity(brand, finalJenis, finalUkuran, jumlah);}
                }
        );

        AlertDialog dialoghasil = builder.create();
        dialoghasil.show();
    }


    public void openHasilActivity(String brand, String jenis, String ukuran, String jumlah){
        ContentValues values = new ContentValues();
        values.put(DBHandler.row_brand, brand);
        values.put(DBHandler.row_jenis, jenis);
        values.put(DBHandler.row_ukuran, ukuran);
        values.put(DBHandler.row_jumlah, jumlah);

        dbHandler.insertDataBaju(values);

        Intent i = new Intent(FormActivity.this, ListActivity.class);
        startActivity(i);
//        Intent i = new Intent(FormActivity.this, HasilActivity.class);
//        i.putExtra("brand", brand);
//        i.putExtra("jenis", jenis);
//        i.putExtra("ukuran", ukuran);
//        i.putExtra("jumlah", jumlah);
//        startActivity(i);

    }
}