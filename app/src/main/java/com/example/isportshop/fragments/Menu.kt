package com.example.isportshop.fragments

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.isportshop.R
import com.example.isportshop.classes.Product
import com.example.isportshop.classes.ProductDataSource
import com.example.isportshop.classes.ProductsAdapter
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Menu.newInstance] factory method to
 * create an instance of this fragment.
 */
class Menu : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var stateSearch = true

    lateinit var recyclerView : RecyclerView
    lateinit var progressBar : ProgressBar
    lateinit var searchBar : TextInputEditText

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
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchBar=view.findViewById(R.id.searchBar)
        recyclerView=view.findViewById(R.id.recycler_view)
        progressBar=view.findViewById(R.id.progressBar)
        gridLayoutManager = GridLayoutManager(context, 2)
        recyclerView.layoutManager = gridLayoutManager
        var listProduct = arrayListOf<Product>()
        val db = Firebase.firestore
        db.collection("items")
            .get()
            .addOnSuccessListener { documents ->
                val list= arrayListOf<Product>()
                //listProduct.clear()
                for (document in documents) {
                    list.add(
                        Product(
                            document.id,
                            document["name"].toString(),
                            document["description"].toString(),
                            document["price"].toString().toDouble(),
                            document["image"].toString(),
                            document["stock"].toString().toInt()
                        )
                    )
                }

                listProduct = list.clone() as ArrayList<Product>
                recyclerView.adapter = ProductsAdapter(listProduct)
                Log.d(ContentValues.TAG, "Successful GET of products")
                progressBar.visibility = View.GONE
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
            }
    }


    fun isEmpty(word: String): Boolean{
        if(word == ""){
            return true;
        }
        return false;
    }

    fun setSearchState(newState : Boolean){
        this.stateSearch = newState
    }

    fun getSearchState(): Boolean{
        return this.stateSearch;
    }

    fun searchMethodC( term: String): Boolean{
        var terms = term.lowercase().split(" ")
        var listProduct = arrayListOf<Product>()



        if(isEmpty(term)){
            Firebase.firestore.collection("items")
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        listProduct.add(
                            Product(
                                document.id,
                                document["name"].toString(),
                                document["description"].toString(),
                                document["price"].toString().toDouble(),
                                document["image"].toString(),
                                document["stock"].toString().toInt()
                            )
                        )
                    }
                    recyclerView.adapter = ProductsAdapter(listProduct)
                    Log.d(ContentValues.TAG, "Successful GET of products")
                    setSearchState(true)
                    progressBar.visibility = View.GONE
                }
                .addOnFailureListener { exception ->
                    Log.w(ContentValues.TAG, "Error getting documents: ", exception)
                    setSearchState(false)
                }
        }else{
            //Search by name explicit
            Firebase.firestore.collection("items").get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        if(!listProduct.contains(Product(
                                document.id,
                                document["name"].toString(),
                                document["description"].toString(),
                                document["price"].toString().toDouble(),
                                document["image"].toString(),
                                document["stock"].toString().toInt()
                            ))
                        ) {
                            if(document["name"].toString().lowercase().equals(term)) {
                                listProduct.add(
                                    Product(
                                        document.id,
                                        document["name"].toString(),
                                        document["description"].toString(),
                                        document["price"].toString().toDouble(),
                                        document["image"].toString(),
                                        document["stock"].toString().toInt()
                                    )
                                );
                            }}
                    }
                    Log.d(ContentValues.TAG, "Successful GET of products on names")
                    setSearchState(true);
                }.addOnFailureListener { exception ->
                    Log.w(ContentValues.TAG, "Error getting documents: ", exception)
                    setSearchState(false)
                }

            //Search by name divided
            Firebase.firestore.collection("items").get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        if(!listProduct.contains(Product(
                                document.id,
                                document["name"].toString(),
                                document["description"].toString(),
                                document["price"].toString().toDouble(),
                                document["image"].toString(),
                                document["stock"].toString().toInt()
                            ))
                        ) {
                            var nameDocument = document["name"].toString().lowercase().split(" ")
                            for (word in terms) {
                                if (nameDocument.indexOf(word) != -1 && !listProduct.contains(Product(
                                        document.id,
                                        document["name"].toString(),
                                        document["description"].toString(),
                                        document["price"].toString().toDouble(),
                                        document["image"].toString(),
                                        document["stock"].toString().toInt()
                                    ))) {
                                    listProduct.add(
                                        Product(
                                            document.id,
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
                    Log.d(ContentValues.TAG, "Successful GET of products on names")
                    setSearchState(true)
                }.addOnFailureListener { exception ->
                    Log.w(ContentValues.TAG, "Error getting documents: ", exception)
                    setSearchState(false)
                }

            //Search by category
            Firebase.firestore.collection("items").whereArrayContainsAny("category", terms).get().addOnSuccessListener { documents ->
                for (document in documents) {
                    if(!listProduct.contains(Product(
                            document.id,
                            document["name"].toString(),
                            document["description"].toString(),
                            document["price"].toString().toDouble(),
                            document["image"].toString(),
                            document["stock"].toString().toInt()
                        ))
                    ) {
                        listProduct.add(
                            Product(
                                document.id,
                                document["name"].toString(),
                                document["description"].toString(),
                                document["price"].toString().toDouble(),
                                document["image"].toString(),
                                document["stock"].toString().toInt()
                            )
                        );
                    }
                }
                recyclerView.adapter = ProductsAdapter(listProduct)
                Log.d(ContentValues.TAG, "Successful GET of products on categories")
                progressBar.visibility = View.GONE
                setSearchState(true)
            }.addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
                setSearchState(false)
            }
        }
        return getSearchState();
    }

    fun searchMethod(){
        var term = searchBar.text.toString().lowercase()
        progressBar.visibility = View.VISIBLE
        searchMethodC(term);
    }

    override fun onResume() {
        chargeItems()
        super.onResume()
    }

    private fun chargeItems(){
        gridLayoutManager = GridLayoutManager(context, 2)
        recyclerView.layoutManager = gridLayoutManager
        var listProduct = arrayListOf<Product>()
        val db = Firebase.firestore
        db.collection("items")
            .get()
            .addOnSuccessListener { documents ->
                val list= arrayListOf<Product>()
                //listProduct.clear()
                for (document in documents) {
                    list.add(
                        Product(
                            document.id,
                            document["name"].toString(),
                            document["description"].toString(),
                            document["price"].toString().toDouble(),
                            document["image"].toString(),
                            document["stock"].toString().toInt()
                        )
                    )
                }

                listProduct = list.clone() as ArrayList<Product>
                recyclerView.adapter = ProductsAdapter(listProduct)
                Log.d(ContentValues.TAG, "Successful GET of products")
                progressBar.visibility = View.GONE
            }
            .addOnFailureListener { exception ->
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
         * @return A new instance of fragment Menu.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Menu().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

