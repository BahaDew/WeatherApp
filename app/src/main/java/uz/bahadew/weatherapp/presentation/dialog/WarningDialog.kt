package uz.bahadew.weatherapp.presentation.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.bahadew.weatherapp.R
import uz.bahadew.weatherapp.databinding.DialogWarningBinding


class WarningDialog : DialogFragment(R.layout.dialog_warning) {
    private val binding by viewBinding(DialogWarningBinding::bind)
    private var onClickRetry: (() -> Unit)? = null
    private var message = ""
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnRetry.setOnClickListener {
            onClickRetry?.invoke()
        }
    }

    public fun setOnClickRetry(onClickRetry: () -> Unit) {
        this.onClickRetry = onClickRetry
    }

    public fun setMessage(message: String) {
        this.message = message
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }
}