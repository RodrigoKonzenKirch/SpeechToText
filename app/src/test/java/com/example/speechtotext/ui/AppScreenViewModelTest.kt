package com.example.speechtotext.ui

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test


class AppScreenViewModelTest(){

    lateinit var viewModel: AppScreenViewModel

    @Before
    fun setUp(){
        viewModel = AppScreenViewModel()
    }

    @Test
    fun `outputText should start empty`(){
        assertThat(viewModel.outputText.value).isEmpty()

    }

    @Test
    fun `setOutputText called then outputText value should be updated`(){
        val expectedValue = "Test"

        viewModel.setOutputText("")
        assertThat(viewModel.outputText.value).isEmpty()

        viewModel.setOutputText(expectedValue)
        assertThat(viewModel.outputText.value).isEqualTo(expectedValue)

    }

}