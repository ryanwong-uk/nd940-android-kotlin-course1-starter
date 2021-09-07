package com.udacity.shoestore.shoelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding

class ShoeListFragment : Fragment() {

    private var _binding: FragmentShoeListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ShoeListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShoeListBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(ShoeListViewModel::class.java)
        binding.shoeListViewModel = viewModel

        viewModel.eventShouldGoLoginScreen.observe(viewLifecycleOwner,
            { shouldGoLoginScreen ->
                if (shouldGoLoginScreen) {
                    onNavigateToLogin()
                }
            })

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Since only on this screen we would show the menu, the event is handled here
    // This can make use of the ViewModel and navigation
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logout) {
            viewModel.logout()
            return true;
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onNavigateToLogin() {
        findNavController().navigate(R.id.action_shoeListFragment_to_loginFragment)
        viewModel.onGoLoginScreenComplete()
    }
}