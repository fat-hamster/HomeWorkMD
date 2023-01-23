package com.dmgpersonal.homeworkmd

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.TaskStackBuilder
import androidx.fragment.app.Fragment
import com.dmgpersonal.homeworkmd.databinding.FragmentSettingsBinding
import com.google.android.material.chip.ChipGroup


class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.chipGroup.setOnCheckedStateChangeListener { chipGroup: ChipGroup, _ ->
            when(chipGroup.checkedChipId) {
                R.id.default_theme -> MainActivity.appTheme = R.style.AppTheme
                R.id.indigo_theme -> MainActivity.appTheme = R.style.IndigoTheme
                R.id.pink_theme -> MainActivity.appTheme = R.style.PinkTheme
            }
            recreateAll()
        }

        binding.chipClose.setOnCloseIconClickListener {
            Toast.makeText(
                context,
                "Close is Clicked",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun recreateAll() {
        // смена темы работает только так. при activity.recreate() происходит зацикливание
        TaskStackBuilder.create(requireActivity())
            .addNextIntent(Intent(activity, MainActivity::class.java))
            .addNextIntent(activity?.intent!!)
            .startActivities()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = SettingsFragment()
    }
}