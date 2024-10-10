package com.elbarody.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.elbarody.domain.helper.Response
import com.elbarody.domain.model.CitiesListDataModel
import com.elbarody.domain.model.CityDataModel
import com.elbarody.domain.model.CityWeatherData
import com.elbarody.domain.model.ForecastDailyItem
import com.elbarody.domain.model.ForecastHourItem
import com.elbarody.domain.model.ForecastModel
import com.elbarody.domain.usecases.cityusecase.GetCityUseCase
import com.elbarody.domain.usecases.forecastusecase.GetForecastUseCase
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.clearInvocations
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.Assert.assertEquals
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ForecastViewModelTest {

    val mockCityWeatherData = CityWeatherData(
        countryName = "Egypt",
        cityName = "Cairo",
        sunrise = "6:00 AM",
        sunset = "6:30 PM"
    )

    val mockForecastHourList = listOf(
        ForecastHourItem(time = "12:00 PM", temp = "30°C", condition = "Clear", icon = "clear.png"),
        ForecastHourItem(time = "3:00 PM", temp = "32°C", condition = "Sunny", icon = "sunny.png")
    )

    val mockForecastDailyList = listOf(
        ForecastDailyItem(
            date = "2024-10-10", tempMax = "32°C", tempMin = "22°C",
            forecastHourList = mockForecastHourList
        )
    )

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var forecastUseCase: GetForecastUseCase

    @Mock
    private lateinit var cityUseCase: GetCityUseCase

    private lateinit var viewModel: ForecastViewModel
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = ForecastViewModel(forecastUseCase, cityUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearInvocations(forecastUseCase, cityUseCase)
    }

    @Test
    fun `getForecast returns success response`() = runTest {
        val forecastModel = ForecastModel(cityWeatherData = mockCityWeatherData, forecastDailyList = mockForecastDailyList)
        whenever(forecastUseCase.invoke(any(), any())).thenReturn(Response.Success(forecastModel))

        viewModel.handleEvent(ForecastContract.Event.OnCityClicked("Cairo", 30.0, 31.0))

        val state = viewModel.uiState.value.forecastState
        assert(state is ForecastContract.ForecastUiState.DisplayForecast)
        assertEquals(
            forecastModel,
            (state as ForecastContract.ForecastUiState.DisplayForecast).forecast
        )
    }

    @Test
    fun `getForecast returns error response`() = runTest {
        val errorMessage = "Network Error"
        whenever(forecastUseCase.invoke(any(), any())).thenReturn(Response.Error(errorMessage))

        viewModel.handleEvent(ForecastContract.Event.OnCityClicked("Cairo", 30.0, 31.0))

        val state = viewModel.uiState.value.forecastState
        assert(state is ForecastContract.ForecastUiState.DisplayError)
        assertEquals(
            errorMessage,
            (state as ForecastContract.ForecastUiState.DisplayError).errorMessage
        )
    }

    @Test
    fun `getCities returns success response`() = runTest {
        val mockCityDataModelList = listOf(
            CityDataModel(cityName = "Cairo", id = 1, lat = 30.033333, lon = 31.233334),
            CityDataModel(cityName = "Alexandria", id = 2, lat = 31.21564, lon = 29.95527)
        )

        val mockCitiesListDataModel = CitiesListDataModel(citiesList = mockCityDataModelList)
        whenever(cityUseCase.invoke()).thenReturn(Response.Success(mockCitiesListDataModel))

        viewModel.handleEvent(ForecastContract.Event.OnStart)

        val state = viewModel.uiState.value.forecastState
        assert(state is ForecastContract.ForecastUiState.DisplayCities)
        assertEquals(mockCitiesListDataModel, (state as ForecastContract.ForecastUiState.DisplayCities).cities)
    }

    @Test
    fun `getCities returns error response`() = runTest {
        val errorMessage = "Cities fetch error"
        whenever(cityUseCase.invoke()).thenReturn(Response.Error(errorMessage))

        viewModel.handleEvent(ForecastContract.Event.OnStart)

        val state = viewModel.uiState.value.forecastState
        assert(state is ForecastContract.ForecastUiState.DisplayError)
        assertEquals(
            errorMessage,
            (state as ForecastContract.ForecastUiState.DisplayError).errorMessage
        )
    }
}