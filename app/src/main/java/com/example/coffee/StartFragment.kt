
package com.example.coffee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.coffee.model.OrderViewModel
import com.example.coffee.R
import com.example.coffee.databinding.FragmentStartBinding


class StartFragment : Fragment() {

    // Binding object instance corresponding to the fragment_start.xml layout
    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment.
    private var binding: FragmentStartBinding? = null

    // Use the 'by activityViewModels()' Kotlin property delegate from the fragment-ktx artifact
    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentStartBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.startFragment = this
    }


    fun orderCupcake(quantity: Int) {
        // Update the view model with the quantity
        sharedViewModel.setQuantity(quantity)

        // If no flavor is set in the view model yet, select vanilla as default flavor
        if (sharedViewModel.hasNoFlavorSet()) {
            sharedViewModel.setFlavor(getString(R.string.vanilla))
        }

        // Navigate to the next destination to select the flavor of the cupcakes
        findNavController().navigate(R.id.action_startFragment_to_sizeFragment)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}