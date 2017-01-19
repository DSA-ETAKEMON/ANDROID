package edu.upc.jonatan.eetakemongo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.upc.jonatan.eetakemongo.Entity.User;
import edu.upc.jonatan.eetakemongo.Entity.etakemons;

public class RankingAdapter extends ArrayAdapter {

    private final Context context;

    public RankingAdapter(Context context, List objects) {
        super(context, R.layout.activity_etakedex, objects);
        this.context = context;
    }
    private static class Elements {
        ImageView image;
        TextView name;
        TextView puntos;
    }
    @Override
    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = convertView;
        final User user1 = (User) getItem(position);
        Elements element;

        if(null == itemView) {
            itemView = inflater.inflate(R.layout.activity_eetakedex_adapter, parent, false);
            element = new Elements();
            element.image = (ImageView) itemView.findViewById(R.id.icono);
            element.name = (TextView) itemView.findViewById(R.id.userNick);
            element.puntos = (TextView) itemView.findViewById(R.id.userPuntuacion);
            itemView.setTag(element);
        } else {
            element = (Elements) itemView.getTag();
        }
        if (user1 != null) {
            element.image.setImageResource(R.drawable.pokeball);
            element.name.setText(user1.getNick());
            element.puntos.setText(user1.getPuntuacionTotal());
        }
        return itemView;
    }
}
