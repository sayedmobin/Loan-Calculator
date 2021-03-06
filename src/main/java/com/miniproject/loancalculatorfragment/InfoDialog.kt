package com.miniproject.loancalculatorfragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class InfoDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(activity)
            .setTitle("Credit")
            .setMessage("ISTE 456 Mini Project By Sayed Mobin")
            .create()
    }

}