package com.example.wyklad
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter


class MenuAdapter (
    context: Context,
    var programName: Array<String>,
    var images: IntArray
) :
    ArrayAdapter<String?>(context, R.layout.one_option, R.id.textView, programName) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var singleItem = convertView

        var holder: MenuViewHolder? = null
        if (singleItem == null) {
            val layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            singleItem = layoutInflater.inflate(R.layout.one_option, parent, false)
            holder = MenuViewHolder(singleItem)
            singleItem!!.setTag(holder)
        } else {
            holder = singleItem.tag as MenuViewHolder
        }
        holder.itemImage.setImageResource(images[position])
        holder.option.setText(programName[position])
        singleItem.setOnClickListener {
            if (programName[position]=="Gra losujaca"){
                val intent = Intent(context,Logowanie::class.java)
                context.startActivity(intent)
            }
            if (programName[position]=="Temperatura"){
                val intent = Intent(context,Temperature::class.java)
                context.startActivity(intent)
            }
            if (programName[position]=="Poziom światła"){
                val intent = Intent(context,Swiatlo::class.java)
                context.startActivity(intent)
            }
            if (programName[position]=="Poziomica"){
                val intent = Intent(context, Poziomica2::class.java)
                context.startActivity(intent)
            }
            if (programName[position]=="Wilgotność"){
                val intent = Intent(context, Wilgotnosc::class.java)
                context.startActivity(intent)
            }
            if (programName[position]=="Ciśnienie"){
                val intent = Intent(context, Cisnienie::class.java)
                context.startActivity(intent)
            }
            if (programName[position]=="Kompas"){
                val intent = Intent(context, Kompas::class.java)
                context.startActivity(intent)
            }
        }
        return singleItem!!
    }
}

