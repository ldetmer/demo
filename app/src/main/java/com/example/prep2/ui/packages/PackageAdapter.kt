package com.example.prep2.ui.packages

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.prep2.R
import com.example.prep2.model.PackageInfo


class PackageAdapter(private val dataSet: MutableList<PackageInfo>, val packageClicked: (packageInfo: PackageInfo) -> Unit) :
    RecyclerView.Adapter<PackageAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val packageTextView: TextView

        init {
            packageTextView = view.findViewById(R.id.packageName)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.package_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.packageTextView.text = dataSet[position].name
        viewHolder.packageTextView.setOnClickListener { packageClicked.invoke(dataSet[position]) }
    }


    override fun getItemCount() = dataSet.size

    fun updatePackages(packages: List<PackageInfo>) {
        dataSet.clear()
        dataSet.addAll(packages)
    }

}
