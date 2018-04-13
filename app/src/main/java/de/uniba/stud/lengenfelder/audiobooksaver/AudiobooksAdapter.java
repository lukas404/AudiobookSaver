package de.uniba.stud.lengenfelder.audiobooksaver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class AudiobooksAdapter extends BaseAdapter {
    List<Audiobook> audiobooks;
    LayoutInflater mInflater;

    public AudiobooksAdapter(Context context, List<Audiobook> audiobooks) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.audiobooks = audiobooks;
    }

    @Override
    public int getCount() {
        return audiobooks.size();
    }

    @Override
    public Object getItem(int position) {
        return audiobooks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return audiobooks.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.main_list_view_detail, null);
        TextView titleTextView = view.findViewById(R.id.titleTextView);
        TextView descTextView = view.findViewById(R.id.descTextView);

        String title = audiobooks.get(position).getTitle();
        String desc = audiobooks.get(position).getDesc();

        titleTextView.setText(title);
        descTextView.setText(desc);

        return view;
    }
}
