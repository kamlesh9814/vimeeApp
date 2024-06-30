package com.vimee.apps.utils

import android.app.DatePickerDialog
import android.content.Context
import android.widget.EditText
import java.util.*

object ConstantVar {

    fun showDate(context: Context, et2: EditText): String {
        var dat: String = ""
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(
            context,
            { view, year, monthOfYear, dayOfMonth ->
                dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                et2.setText(dat)
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
        return dat
    }

}