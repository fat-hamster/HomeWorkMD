package com.dmgpersonal.homeworkmd.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.dmgpersonal.homeworkmd.*
import com.dmgpersonal.homeworkmd.databinding.FragmentPictureOfTheDayBinding
import com.dmgpersonal.homeworkmd.fragments.SettingsFragment
import com.dmgpersonal.homeworkmd.model.PictureOfTheDayData
import com.dmgpersonal.homeworkmd.model.PictureOfTheDayViewModel
import com.dmgpersonal.homeworkmd.ui.pagerview.ApiActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior

class PictureOfTheDayFragment : Fragment() {

    private var _binding: FragmentPictureOfTheDayBinding? = null
    private val binding get() = _binding!!
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private var description = ""
    private var descpriptionHeader = ""

    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(PictureOfTheDayViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View {
        viewModel.getData().observe(viewLifecycleOwner) { renderData(it) }
        _binding = FragmentPictureOfTheDayBinding.inflate(inflater, container, false)

        return binding.root
    }

    private fun renderData(data: PictureOfTheDayData) {
        when(data) {
            is PictureOfTheDayData.Success -> {
                val serverResponseData = data.serverResponseData
                val url = serverResponseData.url
                if(url.isNullOrEmpty()) {
                    toast("Link is empty")
                } else {
                    binding.imageView.load(url) {
                        lifecycle(this@PictureOfTheDayFragment)
                        error(R.drawable.ic_load_error_vector)
                        placeholder(R.drawable.ic_no_photo_vector)
                        crossfade(true)
                        description = data.serverResponseData.explanation.toString()
                        descpriptionHeader = data.serverResponseData.title.toString()
                    }
                }
            }
            is PictureOfTheDayData.Loading -> {
                binding.imageView.load(R.drawable.loading_state)
            }
            is PictureOfTheDayData.Error -> {
                toast(data.error.message)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBottomSheetBehavior(view.findViewById(R.id.bottom_sheet_container))
        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://en.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            })
        }
        setBottomAppBar(view)
        binding.fab.setOnClickListener {
            //
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                activity?.let {
                    BottomNavigationDrawerFragment().show(it.supportFragmentManager,
                        "tag")
                }
            }
            R.id.app_bar_fav -> {
                activity?.let {
                    startActivity(Intent(it, ViewPagerActivity::class.java))
                }
            }
            R.id.app_bar_more -> {
                if(bottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                } else {
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                }
            }
            R.id.app_bar_settings ->
                activity?.supportFragmentManager?.beginTransaction()
                    ?.add(R.id.container, SettingsFragment.newInstance())
                    ?.addToBackStack(null)
                    ?.commit()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setBottomAppBar(view: View) {
        val context = activity as MainActivity
        context.setSupportActionBar(view.findViewById(R.id.bottom_app_bar))
        setHasOptionsMenu(true)
    }

    private fun setBottomSheetBehavior(bottomSheet: ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        bottomSheet.findViewById<TextView>(R.id.bottomSheetDescriptionHeader).text = descpriptionHeader

        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    //BottomSheetBehavior.STATE_DRAGGING -> TODO("not implemented")
                    //BottomSheetBehavior.STATE_COLLAPSED -> TODO("not implemented")
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        bottomSheet.findViewById<TextView>(R.id.bottomSheetDescription).text = description
                    }
                    //BottomSheetBehavior.STATE_HALF_EXPANDED -> TODO("not implemented")
                    //BottomSheetBehavior.STATE_HIDDEN -> TODO("not implemented")
                    //BottomSheetBehavior.STATE_SETTLING -> TODO("not implemented")
                }
            }
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                //TODO("not implemented")
            }
        })
    }

    private fun Fragment.toast(string: String?) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).apply {
            setGravity(Gravity.BOTTOM, 0, 250)
            show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = PictureOfTheDayFragment()
    }
}