package com.awakelab.oskar.apitelefonosv1.data.local

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class MovilDataBaseTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var breedsDao: MovilDao
    private lateinit var db: MovilDataBase

    @Before  //inicializar la ddbb
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, MovilDataBase::class.java).build()
        breedsDao = db.getMovilDao()
    }

    @After  //Cerrar lo inicializado
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertBreeds_empty() = runBlocking {
        // Given
        val breedList = listOf<MovilEntity>()

        // When
        breedsDao.insertMoviles(breedList)

        // Then A  comprueba que hay una lista y esta vacia. esto luego de haber insertado  bredList
        val it = breedsDao.getAllMoviles().getOrAwaitValue()
        assertThat(it).isNotNull()
        assertThat(it).isEmpty()

        // Then B
        breedsDao.getAllMoviles().observeForever {
            assertThat(it).isNotNull()  // assertNotEquals(it, null)
            assertThat(it).isEmpty()    // assertEquals(it.size, 0)
        }
    }

    @Test
    fun insertBreeds_happyCase_1element() = runBlocking {
        // Given
        val breedList = listOf(MovilEntity(0, "Nombre", 10000, "img"))

        // When
        breedsDao.insertMoviles(breedList)

        // Then   (assertThat = valida esto!!!!!
        breedsDao.getAllMoviles().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(1)
        }
    }

    @Test
    fun insertBreeds_happyCase_3elements() = runBlocking {
        // Given
        val breedList = listOf(
            MovilEntity(0, "Nombre1", 10000, "img1"),
            MovilEntity(1, "Nombre2", 20000, "img2"),
            MovilEntity(2, "Nombre3", 30000, "img3"),
        )

        // When
        breedsDao.insertMoviles(breedList)

        // Then
        breedsDao.getAllMoviles().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(3)
        }
    }
}

//Se crea una funcion de extension,
@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {},
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(value: T) {
            data = value
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)

    try {
        afterObserve.invoke()

        // Don't wait indefinitely if the LiveData is not set.
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set.")
        }

    } finally {
        this.removeObserver(observer)
    }

    @Suppress("UNCHECKED_CAST")
    return data as T

}
