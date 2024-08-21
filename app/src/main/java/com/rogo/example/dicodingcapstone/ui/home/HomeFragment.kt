package com.rogo.example.dicodingcapstone.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rogo.example.core.data.source.Resource
import com.rogo.example.core.ui.MovieAdapter
import com.rogo.example.dicodingcapstone.R
import com.rogo.example.dicodingcapstone.databinding.FragmentHomeBinding
import com.rogo.example.dicodingcapstone.ui.detail.DetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var nowAdapter: MovieAdapter
    private lateinit var popularAdapter: MovieAdapter
    private lateinit var topAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        initAdapter()
        toFav()
    }

    private fun toFav() {
        binding.favBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
        }
    }

    private fun observeData() {
        viewModel.getMovieNow.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.apply {
                        rvMovieNowPlaying.visibility = View.GONE
                        pb.visibility = View.VISIBLE
                        error.tvError.visibility = View.GONE
                    }
                }

                is Resource.Success -> {
                    binding.apply {
                        rvMovieNowPlaying.visibility = View.VISIBLE
                        pb.visibility = View.GONE
                        error.tvError.visibility = View.GONE
                    }
                    nowAdapter.differ.submitList(it.data)
                }

                is Resource.Error -> {
                    binding.apply {
                        rvMovieTopRated.visibility = View.GONE
                        pb.visibility = View.GONE
                        error.tvError.visibility = View.VISIBLE
                    }
                }
            }
        }
        viewModel.getMoviePopular.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.apply {
                        rvMoviePopular.visibility = View.GONE
                        pb.visibility = View.VISIBLE
                        error.tvError.visibility = View.GONE
                    }
                }

                is Resource.Success -> {
                    binding.apply {
                        rvMoviePopular.visibility = View.VISIBLE
                        pb.visibility = View.GONE
                        error.tvError.visibility = View.GONE
                    }
                    popularAdapter.differ.submitList(it.data)
                }

                is Resource.Error -> {
                    binding.apply {
                        rvMoviePopular.visibility = View.GONE
                        pb.visibility = View.GONE
                        error.tvError.visibility = View.VISIBLE
                    }
                }
            }
        }
        viewModel.getMovieTopRated.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.apply {
                        rvMovieTopRated.visibility = View.GONE
                        pb.visibility = View.VISIBLE
                        error.tvError.visibility = View.GONE
                    }
                }

                is Resource.Success -> {
                    binding.apply {
                        rvMovieTopRated.visibility = View.VISIBLE
                        pb.visibility = View.GONE
                        error.tvError.visibility = View.GONE
                    }
                    topAdapter.differ.submitList(it.data)
                }

                is Resource.Error -> {
                    binding.apply {
                        rvMovieTopRated.visibility = View.GONE
                        pb.visibility = View.GONE
                        error.tvError.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun initAdapter() {
        nowAdapter = MovieAdapter {
            findNavController().navigate(
                R.id.action_homeFragment_to_detailFragment,
                Bundle().apply { putParcelable(DetailFragment.EXTRA_MOVIE, it) })
        }
        popularAdapter = MovieAdapter {
            findNavController().navigate(
                R.id.action_homeFragment_to_detailFragment,
                Bundle().apply { putParcelable(DetailFragment.EXTRA_MOVIE, it) })
        }
        topAdapter = MovieAdapter {
            findNavController().navigate(
                R.id.action_homeFragment_to_detailFragment,
                Bundle().apply { putParcelable(DetailFragment.EXTRA_MOVIE, it) })
        }
        binding.rvMovieNowPlaying.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = nowAdapter
        }
        binding.rvMoviePopular.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = popularAdapter
        }
        binding.rvMovieTopRated.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = topAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}