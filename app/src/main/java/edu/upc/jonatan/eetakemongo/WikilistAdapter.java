package edu.upc.jonatan.eetakemongo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.upc.jonatan.eetakemongo.Entity.etakemons;

public class WikilistAdapter extends ArrayAdapter {

    private final Context context;

    public WikilistAdapter(Context context, List objects) {
        super(context, R.layout.activity_wikilist, objects);
        this.context = context;
    }
    private static class Elements {
        ImageView icon;
        TextView name;
        TextView tipo;
    }
    @Override
    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = convertView;
        final etakemons eta1 = (etakemons) getItem(position);
        WikilistAdapter.Elements element;

        if(null == itemView) {
            itemView = inflater.inflate(R.layout.activity_wikilist_adapter, parent, false);
            element = new WikilistAdapter.Elements();
            element.icon = (ImageView) itemView.findViewById(R.id.icono);
            element.name = (TextView) itemView.findViewById(R.id.etakemonName);
            element.tipo = (TextView) itemView.findViewById(R.id.etakemonTipo);
            itemView.setTag(element);
        } else {
            element = (WikilistAdapter.Elements) itemView.getTag();
        }
        if (eta1 != null) {
            int pictureID = context.getResources().getIdentifier(eta1.getTipo().toLowerCase(), "drawable", context.getPackageName());
            if(pictureID != 0) {
                element.icon.setImageResource(pictureID);
            } else {
                element.icon.setImageResource(R.drawable.error_picture);
            }
            element.name.setText(eta1.getName());
            element.tipo.setText(eta1.getTipo());
        }
        return itemView;
    }
}
