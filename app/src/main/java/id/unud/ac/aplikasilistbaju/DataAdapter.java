package id.unud.ac.aplikasilistbaju;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder>{

    private Context mContext;
    private Cursor mCursor;
    private Dialog dialog;


    public interface Dialog{
        void onClick(long id);
    }

    public void setDialog(Dialog dialog){
        this.dialog = dialog;
    }


    public DataAdapter(Context context, Cursor cursor) {
        this.mContext = context;
        this.mCursor = cursor;
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.card_data, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder( DataAdapter.DataViewHolder holder, int position) {

        if(!mCursor.moveToPosition(position)){
            return;
        }
        String brand = mCursor.getString((Integer) mCursor.getColumnIndex(DBHandler.row_brand));
        String jenis = mCursor.getString((Integer) mCursor.getColumnIndex(DBHandler.row_jenis));
        String ukuran = mCursor.getString((Integer) mCursor.getColumnIndex(DBHandler.row_ukuran));
        String jumlah = mCursor.getString((Integer) mCursor.getColumnIndex(DBHandler.row_jumlah));
        long id = mCursor.getLong((Integer) mCursor.getColumnIndex(DBHandler.row_id_baju));

        holder.itemView.setTag(id);
        holder.tv_brand.setText(brand);
        holder.tv_jenis.setText(jenis);
        holder.tv_ukuran.setText(ukuran);
        holder.tv_jumlah.setText(jumlah);

    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_brand, tv_jenis, tv_ukuran, tv_jumlah;


        public DataViewHolder( View itemView) {
            super(itemView);

            tv_brand = itemView.findViewById(R.id.tv_brand);
            tv_jenis = itemView.findViewById(R.id.tv_jenis);
            tv_ukuran = itemView.findViewById(R.id.tv_ukuran);
            tv_jumlah = itemView.findViewById(R.id.tv_jumlah);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    long position = (long) itemView.getTag();
                    if(dialog!=null){
                        dialog.onClick(position);
                    }
                }
            });

        }
    }


    public void swapCursor(Cursor newCrusor) {
        if(mCursor != null){
            mCursor.close();
        }

        mCursor = newCrusor;

        if(newCrusor != null){
            this.notifyDataSetChanged();
        }
    }

}
