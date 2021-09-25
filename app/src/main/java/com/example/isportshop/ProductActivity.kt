package com.example.isportshop

import android.app.Dialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.squareup.picasso.Picasso

class ProductActivity : AppCompatActivity() {

    lateinit var nameP : TextView
    lateinit var image : ImageView
    lateinit var description : TextView
    lateinit var price : TextView
    lateinit var stock : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        nameP=findViewById(R.id.product_name_detail)
        image=findViewById(R.id.product_image_detail)
        description=findViewById(R.id.product_description_detail)
        price=findViewById(R.id.product_price_detail)
        stock=findViewById(R.id.product_stock_detail)

        nameP.setText(intent.getStringExtra("name"))
        Picasso.get().load(intent.getStringExtra("image")).into(image)
        description.setText(intent.getStringExtra("description"))
        price.setText(intent.getStringExtra("price"))
        stock.setText(intent.getStringExtra("stock"))

    }


    public fun showDialog(v: View?){
        AlertDialog.Builder(this)
            .setMessage("Ready to add to cart")
            .setPositiveButton("OK") { p0, p1 ->
            }
            .create()
            .show()
    }
}