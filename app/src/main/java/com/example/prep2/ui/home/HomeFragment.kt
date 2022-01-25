package com.example.prep2.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prep2.PackageViewModel
import com.example.prep2.PrepApp
import com.example.prep2.R
import com.example.prep2.UpdaterListener
import com.example.prep2.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(), UpdaterListener {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //@Inject
    //lateinit var packageViewModel1: PackageViewModel

 //  @Inject
   // lateinit var  packageViewModel: PackageViewModel by viewModels()

    private val packageViewModel: PackageViewModel by activityViewModels()
    private var selectedPage = ""
   //private val packageViewModel: PackageViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       // (activity?.application as PrepApp).appComponent.inject(this)
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
      textView.text = savedInstanceState?.getString("test")
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            selectedPage = it
            textView.text = it
        })
        val customAdapter  = CustomAdapter(mutableListOf(), this);

      //  val packageViewModel : PackageViewModel by activityViewModels()
       //packageViewModel = packageViewModel
        println("packageViewmodel "  + packageViewModel)
        packageViewModel.currentPackages.observe(viewLifecycleOwner, Observer<List<String>>{ packages ->
            customAdapter.dataSet.clear()
            customAdapter.dataSet.addAll(packages)
            customAdapter.notifyDataSetChanged()

        })

        val recyclerView = binding.recyclerView

        val llm = LinearLayoutManager(activity)
        llm.orientation = LinearLayoutManager.VERTICAL
        recyclerView.setLayoutManager(llm)

        recyclerView.adapter = customAdapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun update(name:String) {
        println("got here 2" + name)
        packageViewModel.setPackage(name)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("test", selectedPage)
    }
}