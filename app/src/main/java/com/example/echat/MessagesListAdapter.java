package com.example.echat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

public class MessagesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    List<Message> list;
    public static final int MESSAGE_TYPE_IN = 1;
    public static final int MESSAGE_TYPE_OUT = 2;
    String username;

    public MessagesListAdapter(Context context, List<Message> list, String username) { // you can pass other parameters in constructor
        this.context = context;
        this.list = list;
        this.username = username;
    }

    private class MessageInViewHolder extends RecyclerView.ViewHolder {

        TextView messageTV, dateTV;

        MessageInViewHolder(final View itemView) {
            super(itemView);
            messageTV = itemView.findViewById(R.id.message_text);
            dateTV = itemView.findViewById(R.id.date_text);
        }

        void bind(int position) {
            Message message = list.get(position);
            messageTV.setText(message.getContent());
            dateTV.setText(message.getCreated());
        }
    }

    private class MessageOutViewHolder extends RecyclerView.ViewHolder {

        TextView messageTV, dateTV;

        MessageOutViewHolder(final View itemView) {
            super(itemView);
            messageTV = itemView.findViewById(R.id.message_text);
            dateTV = itemView.findViewById(R.id.date_text);
        }

        void bind(int position) {
            Message message = list.get(position);
            messageTV.setText(message.getContent());
            dateTV.setText(message.getCreated());
        }
    }
    public void setMessages(List<Message> s){
        list=s;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == MESSAGE_TYPE_OUT) {
            return new MessageInViewHolder(LayoutInflater.from(context).inflate(R.layout.item_text_in, parent, false));
        }
        return new MessageOutViewHolder(LayoutInflater.from(context).inflate(R.layout.item_text_out, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == MESSAGE_TYPE_OUT) {
            ((MessageInViewHolder) holder).bind(position);
        } else {
            ((MessageOutViewHolder) holder).bind(position);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (Objects.equals(list.get(position).getFrom(), username)) {
            return MESSAGE_TYPE_OUT;

        }
        return MESSAGE_TYPE_IN;
    }
}