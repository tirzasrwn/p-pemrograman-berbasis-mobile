package id.tirzasrwn.pokemon.model

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
