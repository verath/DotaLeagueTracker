package app.verath.dotaleaguetracker.leaguelist

import android.text.Editable
import android.text.TextWatcher

/**
 * SimpleTextWatcher provides default implementation of TextWatcher. Useful to
 * only override a specific TextWatcher method.
 */
open class SimpleTextWatcher : TextWatcher {
    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

}