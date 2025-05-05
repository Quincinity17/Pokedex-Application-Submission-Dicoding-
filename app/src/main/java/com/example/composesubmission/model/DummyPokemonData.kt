package com.example.composesubmission.model

import com.dicoding.pokemonapp.model.Stat
import com.dicoding.pokemonapp.model.Type
import com.example.composesubmission.R

object DummyPokemonData {
    val dummyPokemonList = listOf(
        Pokemon(
            id = 1,
            name = "Bulbasaur",
            imageUrl = R.drawable.img_bulbasaur,
            type = listOf(Type("Grass", "#78C850"), Type("Poison", "#A040A0")),
            stats = listOf(
                Stat("HP", 45),
                Stat("Attack", 49),
                Stat("Defense", 49),
                Stat("Speed", 45)
            ),
            moves = listOf("Tackle", "Vine Whip", "Poison Powder"),
            prevEvoPokemon = 0,
            pastvEvoPokemon = 2
        ),
        Pokemon(
            id = 2,
            name = "Ivysaur",
            imageUrl = R.drawable.img_ivysaur,
            type = listOf(Type("Grass", "#78C850"), Type("Poison", "#A040A0")),
            stats = listOf(
                Stat("HP", 60),
                Stat("Attack", 62),
                Stat("Defense", 63),
                Stat("Speed", 60)
            ),
            moves = listOf("Razor Leaf", "Sleep Powder", "Solar Beam"),
            prevEvoPokemon = 1,
            pastvEvoPokemon = 3
        ),
        Pokemon(
            id = 3,
            name = "Venusaur",
            imageUrl = R.drawable.img_venusaur,
            type = listOf(Type("Grass", "#78C850"), Type("Poison", "#A040A0")),
            stats = listOf(
                Stat("HP", 80),
                Stat("Attack", 82),
                Stat("Defense", 83),
                Stat("Speed", 80)
            ),
            moves = listOf("Solar Beam", "Earthquake", "Sludge Bomb"),
            prevEvoPokemon = 2,
            pastvEvoPokemon = 0
        ),
        Pokemon(
            id = 4,
            name = "Charmander",
            imageUrl = R.drawable.img_charmander,
            type = listOf(Type("Fire", "#F08030")),
            stats = listOf(
                Stat("HP", 39),
                Stat("Attack", 52),
                Stat("Defense", 43),
                Stat("Speed", 65)
            ),
            moves = listOf("Scratch", "Ember", "Flamethrower"),
            prevEvoPokemon = 0,
            pastvEvoPokemon = 5
        ),
        Pokemon(
            id = 5,
            name = "Charmeleon",
            imageUrl = R.drawable.img_charmeleon,
            type = listOf(Type("Fire", "#F08030")),
            stats = listOf(
                Stat("HP", 58),
                Stat("Attack", 64),
                Stat("Defense", 58),
                Stat("Speed", 80)
            ),
            moves = listOf("Flame Burst", "Slash", "Dragon Claw"),
            prevEvoPokemon = 4,
            pastvEvoPokemon = 6
        ),
        Pokemon(
            id = 6,
            name = "Charizard",
            imageUrl = R.drawable.img_charizard,
            type = listOf(Type("Fire", "#F08030"), Type("Flying", "#A890F0")),
            stats = listOf(
                Stat("HP", 78),
                Stat("Attack", 84),
                Stat("Defense", 78),
                Stat("Speed", 100)
            ),
            moves = listOf("Flamethrower", "Air Slash", "Dragon Claw"),
            prevEvoPokemon = 5,
            pastvEvoPokemon = 0
        ),
        Pokemon(
            id = 7,
            name = "Squirtle",
            imageUrl = R.drawable.img_squirtle,
            type = listOf(Type("Water", "#6890F0")),
            stats = listOf(
                Stat("HP", 44),
                Stat("Attack", 48),
                Stat("Defense", 65),
                Stat("Speed", 43)
            ),
            moves = listOf("Tackle", "Water Gun", "Bubble"),
            prevEvoPokemon = 0,
            pastvEvoPokemon = 8
        ),
        Pokemon(
            id = 8,
            name = "Wartortle",
            imageUrl = R.drawable.img_wartortle,
            type = listOf(Type("Water", "#6890F0")),
            stats = listOf(
                Stat("HP", 59),
                Stat("Attack", 63),
                Stat("Defense", 80),
                Stat("Speed", 58)
            ),
            moves = listOf("Bite", "Water Pulse", "Rapid Spin"),
            prevEvoPokemon = 7,
            pastvEvoPokemon = 9
        ),
        Pokemon(
            id = 9,
            name = "Blastoise",
            imageUrl = R.drawable.img_blastoise,
            type = listOf(Type("Water", "#6890F0")),
            stats = listOf(
                Stat("HP", 79),
                Stat("Attack", 83),
                Stat("Defense", 100),
                Stat("Speed", 78)
            ),
            moves = listOf("Hydro Pump", "Ice Beam", "Surf"),
            prevEvoPokemon = 0,
            pastvEvoPokemon = 8
        ),
        Pokemon(
            id = 10,
            name = "Caterpie",
            imageUrl = R.drawable.img_caterpie,
            type = listOf(Type("Bug", "#A8B820")),
            stats = listOf(
                Stat("HP", 45),
                Stat("Attack", 30),
                Stat("Defense", 35),
                Stat("Speed", 45)
            ),
            moves = listOf("Tackle", "String Shot"),
            prevEvoPokemon = 0,
            pastvEvoPokemon = 11
        ),
        Pokemon(
            id = 11,
            name = "Metapod",
            imageUrl = R.drawable.img_metapod,
            type = listOf(Type("Bug", "#A8B820")),
            stats = listOf(
                Stat("HP", 50),
                Stat("Attack", 20),
                Stat("Defense", 55),
                Stat("Speed", 30)
            ),
            moves = listOf("Harden"),
            prevEvoPokemon = 10,
            pastvEvoPokemon = 12
        ),
        Pokemon(
            id = 12,
            name = "Butterfree",
            imageUrl = R.drawable.img_butterfree,
            type = listOf(Type("Bug", "#A8B820"), Type("Flying", "#A890F0")),
            stats = listOf(
                Stat("HP", 60),
                Stat("Attack", 45),
                Stat("Defense", 50),
                Stat("Speed", 70)
            ),
            moves = listOf("Gust", "Psybeam", "Silver Wind"),
            prevEvoPokemon = 11,
            pastvEvoPokemon = 0
        ),
        Pokemon(
            id = 13,
            name = "Weedle",
            imageUrl = R.drawable.img_weedle,
            type = listOf(Type("Bug", "#A8B820"), Type("Poison", "#A040A0")),
            stats = listOf(
                Stat("HP", 40),
                Stat("Attack", 35),
                Stat("Defense", 30),
                Stat("Speed", 50)
            ),
            moves = listOf("Poison Sting", "String Shot"),
            prevEvoPokemon = 0,
            pastvEvoPokemon = 14
        ),
        Pokemon(
            id = 14,
            name = "Kakuna",
            imageUrl = R.drawable.img_kakuna,
            type = listOf(Type("Bug", "#A8B820"), Type("Poison", "#A040A0")),
            stats = listOf(
                Stat("HP", 45),
                Stat("Attack", 25),
                Stat("Defense", 50),
                Stat("Speed", 35)
            ),
            moves = listOf("Harden"),
            prevEvoPokemon = 13,
            pastvEvoPokemon = 15
        ),
        Pokemon(
            id = 15,
            name = "Beedrill",
            imageUrl = R.drawable.img_beedrill,
            type = listOf(Type("Bug", "#A8B820"), Type("Poison", "#A040A0")),
            stats = listOf(
                Stat("HP", 65),
                Stat("Attack", 90),
                Stat("Defense", 40),
                Stat("Speed", 75)
            ),
            moves = listOf("Twineedle", "Poison Jab", "X-Scissor"),
            prevEvoPokemon = 0,
            pastvEvoPokemon = 14
        ),
        Pokemon(
            id = 16,
            name = "Pidgey",
            imageUrl = R.drawable.img_pidgey,
            type = listOf(Type("Normal", "#A8A878"), Type("Flying", "#A890F0")),
            stats = listOf(
                Stat("HP", 40),
                Stat("Attack", 45),
                Stat("Defense", 40),
                Stat("Speed", 56)
            ),
            moves = listOf("Gust", "Quick Attack", "Wing Attack"),
            prevEvoPokemon = 0,
            pastvEvoPokemon = 17
        ),
        Pokemon(
            id = 17,
            name = "Pidgeotto",
            imageUrl = R.drawable.img_pidgeotto,
            type = listOf(Type("Normal", "#A8A878"), Type("Flying", "#A890F0")),
            stats = listOf(
                Stat("HP", 63),
                Stat("Attack", 60),
                Stat("Defense", 55),
                Stat("Speed", 71)
            ),
            moves = listOf("Gust", "Quick Attack", "Twister"),
            prevEvoPokemon = 16,
            pastvEvoPokemon = 18
        ),
        Pokemon(
            id = 18,
            name = "Pidgeot",
            imageUrl = R.drawable.img_pidgeot,
            type = listOf(Type("Normal", "#A8A878"), Type("Flying", "#A890F0")),
            stats = listOf(
                Stat("HP", 83),
                Stat("Attack", 80),
                Stat("Defense", 75),
                Stat("Speed", 101)
            ),
            moves = listOf("Wing Attack", "Aerial Ace", "Hurricane"),
            prevEvoPokemon = 17,
            pastvEvoPokemon = 19
        )

    )
}