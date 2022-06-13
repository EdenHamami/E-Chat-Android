package com.example.echat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.ContactViewHolder> {
    private RecyclerViewClickListener listener;

    class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView contactName;
        private final TextView lastMassage;
        private final TextView time;
        private final ImageView image;

        private ContactViewHolder(View itemView){
            super((itemView));
            contactName=itemView.findViewById(R.id.contactName);
            lastMassage=itemView.findViewById(R.id.lastMessage);
            time=itemView.findViewById(R.id.time);
            image=itemView.findViewById(R.id.ContactImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
listener.onClick(v,getAdapterPosition());
        }
    }
    private final LayoutInflater mInflater;
    private List<Contact> contacts;

    public ContactsListAdapter(Context context,RecyclerViewClickListener listener){
        mInflater=LayoutInflater.from(context);
        this.listener=listener;
    }
    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
     View itemView=mInflater.inflate(R.layout.activity_list_item,parent,false);
    return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        if(contacts!=null){
            final Contact current=contacts.get(position);
            holder.contactName.setText(current.getName());
            holder.lastMassage.setText(current.getLast());
            holder.time.setText(current.getLastdate());
        }
    }
public void setContacts(List<Contact> s){
        contacts=s;
        notifyDataSetChanged();
}
    @Override
    public int getItemCount() {
        if(contacts!=null){
            return contacts.size();
        }
        else return 0;
    }
    public interface RecyclerViewClickListener{
        void onClick(View view,int position);
    }
}

