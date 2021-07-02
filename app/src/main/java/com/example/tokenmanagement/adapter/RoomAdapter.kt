package com.example.tokenmanagement.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tokenmanagement.R
import com.example.tokenmanagement.entity.Room
import com.example.tokenmanagement.entity.Token

class RoomAdapter(
    val lstRoom : List<Room>,
    val context : Context
): RecyclerView.Adapter<RoomAdapter.RoomViewHolder>()  {

    class RoomViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val tvRoomname : TextView
        val tvCreatedby : TextView
        val tvActivetoken : TextView
        val tvPeople : TextView

        init{
            tvRoomname = view.findViewById(R.id.tvRoomname)
            tvCreatedby = view.findViewById(R.id.tvCreatedby)
            tvActivetoken = view.findViewById(R.id.tvActivetoken)
            tvPeople = view.findViewById(R.id.tvPeople)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.room_view, parent, false)
        return RoomViewHolder(view)    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val roomInformation = lstRoom[position]
        holder.tvRoomname.text = roomInformation.room_name
        holder.tvCreatedby.text = roomInformation.createdBy
        holder.tvActivetoken.text = roomInformation.active_token
        holder.tvPeople.text = roomInformation.people
    }

    override fun getItemCount(): Int {
        return lstRoom.size

    }

}