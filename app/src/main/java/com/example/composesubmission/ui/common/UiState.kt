package com.example.composesubmission.ui.common

/**
 * Kelas sealed untuk merepresentasikan berbagai status UI.
 * @param T Tipe data yang dikembalikan saat sukses.
 */
sealed class UiState<out T: Any?> {

    /**
     * Status ketika UI sedang memuat data.
     */
    data object Loading : UiState<Nothing>()

    /**
     * Status ketika UI berhasil mendapatkan data.
     * @param data Data yang diperoleh.
     */
    data class Success<out T: Any>(val data: T) : UiState<T>()

    /**
     * Status ketika terjadi error dalam UI.
     * @param errorMessage Pesan kesalahan yang ditampilkan.
     */
    data class Error(val errorMessage: String) : UiState<Nothing>()
}