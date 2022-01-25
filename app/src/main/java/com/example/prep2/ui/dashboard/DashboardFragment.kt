package com.example.prep2.ui.dashboard

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
import com.example.prep2.PackageViewModel
import com.example.prep2.PrepApp
import com.example.prep2.R
import com.example.prep2.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

   // @Inject
    //lateinit var  packageViewModel: PackageViewModel
 //  private val packageViewModel: PackageViewModel by viewModels()
    private val packageViewModel: PackageViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       // (activity?.application as PrepApp).appComponent.inject(this)
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
      //  val packageViewModel: PackageViewModel by activityViewModels()
        println("packageViewmodel "  + packageViewModel)
        packageViewModel.currentSelectedPackage.observe(viewLifecycleOwner, Observer {
            println("got here" + it)
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}