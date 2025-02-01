package com.google.codelabs.mdc.kotlin.shrine

import android.os.Bundle
import android.text.Editable
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.codelabs.mdc.kotlin.shrine.databinding.ShrLoginFragmentBinding

/**
 * Fragment representing the login screen for Shrine.
 */
class LoginFragment : Fragment() {

    private var _binding: ShrLoginFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment using View Binding
        _binding = ShrLoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set an error if the password is less than 8 characters.
        binding.nextButton.setOnClickListener {
            if (!isPasswordValid(binding.passwordEditText.text)) {
                binding.passwordTextInput.error = getString(R.string.shr_error_password)
            } else {
                binding.passwordTextInput.error = null // Clear the error
                (activity as NavigationHost).navigateTo(ProductGridFragment(), false) // Navigate to the next Fragment
            }
        }

        // Clear the error once more than 8 characters are typed.
        binding.passwordEditText.setOnKeyListener { _, _, event ->
            if (event.action == KeyEvent.ACTION_UP && isPasswordValid(binding.passwordEditText.text)) {
                binding.passwordTextInput.error = null // Clear the error
            }
            false
        }
    }

    /*
        In reality, this will have more complex logic including, but not limited to, actual
        authentication of the username and password.
     */
    private fun isPasswordValid(text: Editable?): Boolean {
        return text != null && text.length >= 8
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Avoid memory leaks
    }
}
