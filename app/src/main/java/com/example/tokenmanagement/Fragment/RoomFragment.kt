package com.example.tokenmanagement.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tokenmanagement.R
import com.example.tokenmanagement.adapter.RoomAdapter
import com.example.tokenmanagement.repository.RoomRepository
import com.example.tokenmanagement.repository.TokenRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RoomFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RoomFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var  recyclerView: RecyclerView


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
        var view = inflater.inflate(R.layout.fragment_room, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        loadRoom(view)
        return view
    }

    private fun loadRoom(view : View) {

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val roomRepository = RoomRepository()
                val response = roomRepository.showAllRoom()
                if (response.success == true) {

                    var lstItems = response.data!!

                    withContext(Dispatchers.Main) {
                        recyclerView.adapter = context?.let { RoomAdapter(lstItems!!, it) }
                        recyclerView.layoutManager = LinearLayoutManager(context)
//                        Toast.makeText(activity, ""+lstItems, Toast.LENGTH_SHORT).show()

                    }


                }
            } catch (ex: Exception) {

                withContext(Dispatchers.Main) {
                    println(ex)
                }
            }

        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RoomFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RoomFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}