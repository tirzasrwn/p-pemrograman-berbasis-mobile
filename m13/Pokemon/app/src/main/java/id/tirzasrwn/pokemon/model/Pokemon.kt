package id.tirzasrwn.pokemon.model


// learning material: mvvm architecture - model - data object
// data class Pokemon is a data object that represents a Pokemon
data class Pokemon(
    val name: String,
    val url: String
) {
    val id: Int
        get() = url
            .trimEnd('/')
            .split("/")
            .last()
            .toIntOrNull() ?: 0
}
