package com.example.retrofit_ejercicio01.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit_ejercicio01.Modelos.Response;
import com.example.retrofit_ejercicio01.Modelos.Usuario;
import com.example.retrofit_ejercicio01.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsuarioVH> {
    private List<Usuario> objects;
    private Context contect;
    private int resource;


    public UsersAdapter(List<Usuario> objects, Context contect, int resource) {
        this.objects = objects;
        this.contect = contect;
        this.resource = resource;
    }

    @NonNull
    @Override
    public UsersAdapter.UsuarioVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View usuarioView = LayoutInflater.from(contect).inflate(resource,null);
        usuarioView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new UsuarioVH(usuarioView);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.UsuarioVH holder, int position) {
        Usuario usuario = objects.get(position);
        holder.lblNombreYApellido.setText(usuario.getFirstName() + " "+ usuario.getLastName());
        holder.lblId.setText(String.valueOf(usuario.getId()));
        holder.lblEmail.setText(usuario.getEmail());
        Picasso.get()
                .load(usuario.getAvatar())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.imgAvatar);
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class UsuarioVH extends RecyclerView.ViewHolder {

        TextView lblNombreYApellido;
        TextView lblId;
        TextView lblEmail;
        ImageView imgAvatar;

        public UsuarioVH(@NonNull View itemView) {
            super(itemView);

            lblNombreYApellido = itemView.findViewById(R.id.lblNombreYApellidoCard);
            lblId = itemView.findViewById(R.id.lblIdCard);
            lblEmail = itemView.findViewById(R.id.lblEmailCard);
            imgAvatar = itemView.findViewById(R.id.imgImagenAvatar);
        }
    }
}
