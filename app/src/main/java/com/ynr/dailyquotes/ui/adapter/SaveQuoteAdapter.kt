package com.ynr.dailyquotes.ui.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.ynr.dailyquotes.R
import com.ynr.dailyquotes.ui.activity.SaveActivity
import com.ynr.dailyquotes.database.Quote

class SaveQuoteAdapter(

    private val context: SaveActivity,
    private val getList: List<Quote>

    ) : RecyclerView.Adapter<SaveQuoteAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.save_quotes_item,parent,false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.quotes.text = getList[position].quote
        holder.author.text = getList[position].author

        holder.btnDelete.setOnClickListener {

            Log.e("Save", "onBindViewHolder: ${getList[position].id}")

            val alertDialog = AlertDialog.Builder(context).create()

            val view = LayoutInflater.from(context).inflate(R.layout.custom_dialog,null)
            val btnOk = view.findViewById<MaterialButton>(R.id.btnOk)
            val btnCancel = view.findViewById<MaterialButton>(R.id.btnCancel)
            alertDialog.setView(view)

            alertDialog.setCanceledOnTouchOutside(false)

            btnOk.setOnClickListener{

                context.onItemClick(
                    Quote(
                        getList[position].id,
                        getList[position].quote,
                        getList[position].author
                    )
                )

                notifyItemChanged(position)

                alertDialog.dismiss()

            }

            btnCancel.setOnClickListener {
                alertDialog.dismiss()
            }

            alertDialog.show()
        }

        holder.btnShare.setOnClickListener {

            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, getList[position].quote)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            context.startActivity(shareIntent)

        }

    }


    override fun getItemCount() : Int {

        return getList.size

    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        val cardItem : MaterialCardView = itemView.findViewById(R.id.cardItem)
        val quotes : TextView = itemView.findViewById(R.id.quotes)
        val author : TextView = itemView.findViewById(R.id.author)
        val btnDelete : AppCompatImageButton = itemView.findViewById(R.id.btnDelete)
        val btnShare : AppCompatImageButton = itemView.findViewById(R.id.btnShare)

    }

}