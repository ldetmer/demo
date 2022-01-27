package com.example.prep2.ui.packages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.prep2.PackageViewModel
import com.example.prep2.databinding.FragmentPackageDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

const val SELECTED_PAGE = "selected_page"

@AndroidEntryPoint
class PackageDetailsFragment : Fragment() {

    private var _binding: FragmentPackageDetailsBinding? = null

    private val binding get() = _binding!!

    private val packageViewModel: PackageViewModel by activityViewModels()

    private var selectedPage = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPackageDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val packageName: TextView = binding.packageName
        val packageVersion: TextView = binding.packageVersion
        val minimumVersion: TextView = binding.packageMinimumVersion

        packageViewModel.currentSelectedPackage.observe(viewLifecycleOwner, Observer {
            selectedPage = it.name
            packageName.text = it.name
            packageVersion.text = it.version
            minimumVersion.text = it.minimunVersion
        })

        savedInstanceState?.getString(SELECTED_PAGE)?.let {
            packageViewModel.setPackage(it)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(SELECTED_PAGE, selectedPage)
    }
}