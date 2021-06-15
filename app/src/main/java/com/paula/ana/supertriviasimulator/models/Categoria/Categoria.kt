package com.paula.ana.supertriviasimulator.models.Categoria


data class Categoria (
    var id: String,
    var name: String
){

    companion object {
        fun getAll(): List<Categoria>{
            return listOf(
                    Categoria("1", "ab"),
                    Categoria("1", "ac"),
                    Categoria("1", "ad"),
                    Categoria("1", "ae"),
                    Categoria("1", "af")
            )
        }
    }
    override fun equals(other: Any?) = other is Categoria && this.id == other.id
}