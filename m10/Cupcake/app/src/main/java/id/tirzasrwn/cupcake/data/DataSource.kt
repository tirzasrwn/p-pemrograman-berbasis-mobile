package id.tirzasrwn.cupcake.data

import id.tirzasrwn.cupcake.R

// this object provides a list of cupcake flavors and quantity options
object DataSource {
    val flavors =
            listOf(
                    R.string.vanilla,
                    R.string.chocolate,
                    R.string.red_velvet,
                    R.string.salted_caramel,
                    R.string.coffee
            )

    val quantityOptions =
            listOf(
                    Pair(R.string.one_cupcake, 1),
                    Pair(R.string.six_cupcakes, 6),
                    Pair(R.string.twelve_cupcakes, 12)
            )
}
