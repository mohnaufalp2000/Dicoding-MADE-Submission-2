package com.naufal.techmedia.core.di

import androidx.room.Room
import com.naufal.techmedia.core.data.NewsRepository
import com.naufal.techmedia.core.data.source.local.LocalDataSource
import com.naufal.techmedia.core.data.source.local.room.ConfigDB
import com.naufal.techmedia.core.data.source.remote.RemoteDataSource
import com.naufal.techmedia.core.data.source.remote.network.ApiService
import com.naufal.techmedia.core.domain.repository.INewsRepository
import com.naufal.techmedia.core.util.AppExecutors
import com.naufal.techmedia.core.util.Constant
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<ConfigDB>().newsDao() }
    single {
        val passphrase : ByteArray = SQLiteDatabase.getBytes("tech".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            ConfigDB::class.java,
            "TechNews.db"
        ).fallbackToDestructiveMigration().openHelperFactory(factory).build()
    }
}

val networkModule = module {
    single {
        val hostName = "newsapi.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostName, "sha256/LAlZB272xQABCgeTFXzq0MuyQTFpu4lb7LOBjVoJdrE=")
            .add(hostName, "sha256/c5XTqkOxoXqc60M3HuT9fgmfeexiP2+Q8BD3+6abZYU=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single{
        val retrofit = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()

        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<INewsRepository> { NewsRepository(get(), get(), get()) }
}