package com.example.isportshop.fragments


import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MenuTest {
     var menu : Menu = Menu()


    @Test
    fun getSearchState() {
        assertEquals(true, menu.getSearchState())
    }
    @Test
    fun isEmpty() {
        assertEquals(true, menu.isEmpty(""))
    }

}