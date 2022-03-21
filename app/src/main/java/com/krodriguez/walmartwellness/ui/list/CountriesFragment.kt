package com.krodriguez.walmartwellness.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.krodriguez.walmartwellness.R
import com.krodriguez.walmartwellness.data.remote.state.APIState
import com.krodriguez.walmartwellness.databinding.FragmentListBinding
import com.krodriguez.walmartwellness.presentation.CountriesViewModel
import com.krodriguez.walmartwellness.ui.ToolbarExtension
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CountriesFragment : Fragment() {

    private lateinit var binding: FragmentListBinding

    @Inject lateinit var countriesAdapter: CountriesAdapter

    private val viewModel: CountriesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        initSwipeRefresh()
        initAdapter()
        observeCountries()
    }

    private fun initAdapter() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = countriesAdapter
        }
    }

    private fun observeCountries() {
        viewModel.countriesLiveData.value?.let { dataState ->
            renderStates(dataState)
        } ?: run {
            viewModel.getCountries()
        }

        viewModel.countriesLiveData.observe(viewLifecycleOwner) { dataState ->
            renderStates(dataState)
        }
    }

    private fun renderStates(dataState: APIState?) {
        when (dataState) {
            is APIState.Loading -> {
                binding.apply {
                    pbLoading.visibility = View.VISIBLE
                    tvError.visibility = View.GONE
                    recyclerView.visibility = View.GONE
                }
            }
            is APIState.Success -> {
                if (dataState.data.isEmpty()) {
                    showErrorData(error = getString(R.string.empty_items))
                } else {
                    binding.apply {
                        pbLoading.visibility = View.GONE
                        tvError.visibility = View.GONE
                        recyclerView.visibility = View.VISIBLE
                        srlList.isRefreshing = false
                    }

                    dataState.data.let { list ->
                        countriesAdapter.updateDataSet(list)
                    }
                }
            }
            is APIState.Empty -> {
                showErrorData(error = dataState.error)
            }
            is APIState.Error -> {
                showErrorData(error = dataState.error)
            }
            else -> {
                showErrorData(error = "Unknown Error")
            }
        }
    }

    private fun showErrorData(error: String) {
        binding.apply {
            tvError.text = error
            tvError.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
            pbLoading.visibility = View.GONE
            srlList.isRefreshing = false
        }
    }

    private fun initToolbar() {
        with(ToolbarExtension) {
            setupToolbar(
                requireActivity(),
                binding.include.toolbar,
                getString(R.string.menu_list),
                false
            )
        }
    }

    private fun initSwipeRefresh() {
        binding.srlList.apply {
            setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.colorPrimaryDark))
            setOnRefreshListener {
                viewModel.getCountries()
            }
        }
    }
}