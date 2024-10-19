package id.tirzasrwn.affirmations.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/** [Affirmation] is the data class to represent the Affirmation text and imageResourceId */
// data class is like struct in go or c
data class Affirmation(@StringRes val stringResourceId: Int, @DrawableRes val imageResourceId: Int)
