package com.example.isportshop.fragments

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.isportshop.R
import com.example.isportshop.classes.Product
import com.example.isportshop.classes.ProductsAdapter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.DocumentSnapshot




// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Cart.newInstance] factory method to
 * create an instance of this fragment.
 */
class Cart : Fragment() {
    var itemsList = arrayListOf<String>()
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var recyclerView : RecyclerView
    private lateinit var gridLayoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        arguments?.let {

            if(it.containsKey("userCart")){
                Log.d("fragment",it.getString("userCart").toString())
                //Obtain info from the database
                var doc=it.getString("userCart").toString()
                val db = Firebase.firestore
                db.collection("users").document(doc)
                    .get()
                    .addOnSuccessListener { document ->
                        var data = document?.data
                        Log.d("PROFILE", "${data.toString()}")

                        this.itemsList = document["cartItems"] as ArrayList<String>


                        //name.text = document["name"].toString()
                    }
                    .addOnFailureListener { e ->
                        Log.w("FIREBASE", "Error on read the document", e)
                    }
            }

        }


        recyclerView=view.findViewById(R.id.recycler_view_cart)
        gridLayoutManager = GridLayoutManager(context, 2)
        recyclerView.layoutManager = gridLayoutManager
        var listProduct = arrayListOf<Product>()
        val db = Firebase.firestore

        //Obtener items de la bd

        //val document: DocumentSnapshot = task.getResult()




        //Search by name divided
        Firebase.firestore.collection("items").get()
            .addOnSuccessListener { documents ->
                for (document in documents) {

                    if(!listProduct.contains(Product(
                            document["name"].toString(),
                            document["description"].toString(),
                            document["price"].toString().toDouble(),
                            document["image"].toString(),
                            document["stock"].toString().toInt()
                        ))
                    ) {
                        var nameDocument = document["name"].toString()
                        for (item in this.itemsList) {
                            Log.d("entroooooooooo", item)
                            if (nameDocument.equals(item)) {  /*Si no funciona, cambiar aqui*/
                                listProduct.add(
                                    Product(
                                        document["name"].toString(),
                                        document["description"].toString(),
                                        document["price"].toString().toDouble(),
                                        document["image"].toString(),
                                        document["stock"].toString().toInt()
                                    )
                                );
                            }
                        }
                    }
                }
                recyclerView.adapter = ProductsAdapter(listProduct)
                Log.d(ContentValues.TAG, "Successful GET of products on names")
            }.addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
            }


    }




    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Cart.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Cart().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}