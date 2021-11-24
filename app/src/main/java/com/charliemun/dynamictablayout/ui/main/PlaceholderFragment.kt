package com.charliemun.dynamictablayout.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.charliemun.dynamictablayout.MainActivity
import com.charliemun.dynamictablayout.R
import com.charliemun.dynamictablayout.adapters.PlaceholderItemsAdapter
import com.charliemun.dynamictablayout.databinding.FragmentMainBinding
import org.json.JSONObject

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private var _binding: FragmentMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root = binding.root


        val itemsAdapter = PlaceholderItemsAdapter()
        binding.adapter = itemsAdapter

        pageViewModel.index.observe(viewLifecycleOwner, Observer {
            if(it != null && it >= 0){
                val jsonData = (context as MainActivity).jsonArrayData
                if(jsonData != null){
                    val sectionData = jsonData.getJSONObject(it)
                    binding.heading.text = sectionData.getString("description")
                    sectionData.getJSONArray("items").let(itemsAdapter::updateList)
                }
            }
        })
        return root
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionPosition: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionPosition)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}