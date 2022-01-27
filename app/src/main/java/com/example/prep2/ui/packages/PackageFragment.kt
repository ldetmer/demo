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

import com.example.prep2.listeners.PackageSeletedListener
import com.example.prep2.databinding.FragmentHomeBinding
import com.example.prep2.model.PackageInfo
import dagger.hilt.android.AndroidEntryPoint

const val SELECTED_PAGE = "selected_page"
@AndroidEntryPoint
class PackageFragment : Fragment(), PackageSeletedListener {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!
    private val packageViewModel: PackageViewModel by activityViewModels()
    private var selectedPage = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val customAdapter  = PackageAdapter(mutableListOf(), this);

        packageViewModel.currentPackages.observe(viewLifecycleOwner, { packages ->
            customAdapter.dataSet.clear()
            customAdapter.dataSet.addAll(packages)
            customAdapter.notifyDataSetChanged()

        })

        val recyclerView = binding.recyclerView

        val llm = LinearLayoutManager(activity)
        llm.orientation = LinearLayoutManager.VERTICAL
        recyclerView.setLayoutManager(llm)

        recyclerView.adapter = customAdapter

        savedInstanceState?.getString(SELECTED_PAGE)?.let {
            packageViewModel.setPackage(it)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun update(packageInfo: PackageInfo) {
        packageViewModel.setPackage(packageInfo)
        selectedPage = packageInfo.name
        view?.let {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_dashboardFragment)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(SELECTED_PAGE, selectedPage)
    }
}