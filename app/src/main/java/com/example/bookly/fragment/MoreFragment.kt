package com.example.bookly.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookly.const.Constant.BOOK
import com.example.bookly.const.Constant.BOOK_AUTOR
import com.example.bookly.const.Constant.BOOK_GRADE
import com.example.bookly.const.Constant.BOOK_MONEY
import com.example.bookly.const.Constant.BOOK_NAME
import com.example.bookly.databinding.FragmentMoreBinding
import com.example.bookly.model.adapter.AlsoAdapter
import com.example.bookly.model.api.ApiInterface
import com.example.bookly.model.data.Also
import com.example.dotaheroyokhttp.model.utils.FragmentManagere
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MoreFragment : Fragment() {
    private lateinit var binding: FragmentMoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMoreBinding.inflate(inflater, container, false)
        setFragmentResultListener("requestKey") { key, bundle ->

            val book = bundle.getString(BOOK)
            val bookName = bundle.getString(BOOK_NAME)
            val bookAutor = bundle.getString(BOOK_AUTOR)
            val bookGrade = bundle.getDouble(BOOK_GRADE)
            val bookMoney = bundle.getDouble(BOOK_MONEY)


            Log.d("booksID", bookMoney.toString())
            with(binding){

                Picasso.get().load(book)
                    .into(imPhoto)
                tvName.text = bookName.toString()
                tvDescription.text = bookAutor.toString()
                tvGrade.text = bookGrade.toString()
                tvMoney.text = bookMoney.toString()
                imClose.setOnClickListener{
                    FragmentManagere.setFragment(MAinFragment.newInstance(), activity as AppCompatActivity)
                }
            }

        }
        Also()

        return binding.root
    }

    private fun Also(){


        val apiInterface = ApiInterface.create().getAlso("similar")
        apiInterface.enqueue(object : Callback<Also> {
            override fun onResponse(call: Call<Also>?, response: Response<Also>?) {
                Log.d("testLogsss","OnResponse Success ${response?.body()}")
                // This will pass the ArrayList to our Adapter
                val recyclerview = binding.rcAlsoLike
                recyclerview.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL,false)
                val adapter = AlsoAdapter(response?.body())
                binding.rcAlsoLike.adapter = adapter
            }

            override fun onFailure(call: Call<Also>?, t: Throwable?) {
                Log.d("testLogsss","onFailure  ${t?.message}")
            }

        })


    }

    companion object {

        @JvmStatic
        fun newInstance() = MoreFragment()
    }
}