package com.example.prep2.ui.packages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.prep2.PackageViewModel
import com.example.prep2.R

import com.example.prep2.databinding.FragmentPackagesBinding
import com.example.prep2.model.PackageInfo
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PackageFragment : Fragment() {

    private var _binding: FragmentPackagesBinding? = null

    private val binding get() = _binding!!
    private val packageViewModel: PackageViewModel by activityViewModels()

    //setup onclick listener for item in list
    val onPackageClicked: (packageInfo: PackageInfo) -> Unit = { packageInfo ->
        packageViewModel.setPackage(packageInfo)
        view?.let {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_dashboardFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPackagesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val packageAdapter  = PackageAdapter(mutableListOf(), onPackageClicked);

        val recyclerView = binding.recyclerView

        val llm = LinearLayoutManager(activity)
        llm.orientation = LinearLayoutManager.VERTICAL
        recyclerView.setLayoutManager(llm)
        recyclerView.adapter = packageAdapter

        //observe any updates to package list and update display here
        packageViewModel.currentPackages.observe(viewLifecycleOwner, { packages ->
            packageAdapter.updatePackages(packages)
            packageAdapter.notifyDataSetChanged()

        })

        return root
    }

    override fun onResume() {
        super.onResume()
        //anytime screen comes back into focus refresh packages
        packageViewModel.getPackages()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}