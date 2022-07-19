package com.example.bookly.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookly.const.Constant
import com.example.bookly.databinding.FragmentMAinBinding
import com.example.bookly.model.adapter.CarouselAdapter
import com.example.bookly.model.adapter.MainAdapter
import com.example.bookly.model.api.ApiInterface
import com.example.bookly.model.data.BestSellers
import com.example.bookly.model.data.BestSellersItem
import com.example.bookly.model.data.Carousel
import com.example.dotaheroyokhttp.model.utils.FragmentManagere
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class MAinFragment : Fragment() {
   private lateinit var binding: FragmentMAinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMAinBinding.inflate(inflater,container,false)
        BooksHeroy()
        Carousel()
        return binding.root
    }

    private fun BooksHeroy(){


        val apiInterface = ApiInterface.create().getBest("best")
        apiInterface.enqueue(object : Callback<BestSellers>, MainAdapter.ItemClicklistener{
            override fun onResponse(call: Call<BestSellers>?, response: Response<BestSellers>?) {
                Log.d("testLogs","OnResponse Success ${response?.body()}")
                // This will pass the ArrayList to our Adapter
                val recyclerview = binding.rcView
                recyclerview.layoutManager = GridLayoutManager(activity,1)
                val adapter = MainAdapter(response?.body(),this)
                binding.rcView.adapter = adapter
            }

            override fun onFailure(call: Call<BestSellers>?, t: Throwable?) {
                Log.d("testLogs","onFailure  ${t?.message}")
            }

            override fun onItemClick(position: Int, book: BestSellersItem) {
                requireActivity().supportFragmentManager.setFragmentResult(
                    "requestKey",
                    bundleOf(
                        Constant.BOOK to book.image,
                        Constant.BOOK_NAME to book.title,
                        Constant.BOOK_AUTOR to book.author,
                        Constant.BOOK_GRADE to book.rate.score,
                        Constant.BOOK_MONEY to book.price,

                    )

                )
                Log.d("booksID", book.price.toString())
                FragmentManagere.setFragment(MoreFragment.newInstance(), activity as AppCompatActivity)
            }
        })


    }
    private fun Carousel(){


        val apiInterface = ApiInterface.create().getCarousel("carousel")
        apiInterface.enqueue(object : Callback<Carousel>{
            override fun onResponse(call: Call<Carousel>?, response: Response<Carousel>?) {
                Log.d("testLogss","OnResponse Success ${response?.body()}")
                // This will pass the ArrayList to our Adapter
                val recyclerview = binding.rcCarouse
                recyclerview.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL,false)
                val adapter = CarouselAdapter(response?.body())
                binding.rcCarouse.adapter = adapter
            }

            override fun onFailure(call: Call<Carousel>?, t: Throwable?) {
                Log.d("testLogs","onFailure  ${t?.message}")
            }

        })


    }


    companion object {

        @JvmStatic
        fun newInstance() = MAinFragment()
    }
}