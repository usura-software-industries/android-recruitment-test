package dog.snow.androidrecruittest.common.injection

import dagger.Module
import dagger.Provides
import dog.snow.androidrecruittest.BuildConfig
import dog.snow.androidrecruittest.common.api.ItemApi
import dog.snow.androidrecruittest.common.api.RetrofitItemApi
import dog.snow.androidrecruittest.common.database.ItemDatabase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import dog.snow.androidrecruittest.common.database.RealmItemDatabase
import dog.snow.androidrecruittest.common.repository.ItemRepositoryRepository
import dog.snow.androidrecruittest.common.repository.ItemRepositoryRepositoryImpl
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class DaoModule {

    companion object {
        private const val API_BASE_URL = "api_base_url"
    }

    @Provides
    @Singleton
    @Named(API_BASE_URL)
    fun providesLocationServiceAPI(): String = "http://localhost:8080/api/"
    //^ TODO set url

    @Provides
    @Singleton
    fun provideRetrofit(@Named(API_BASE_URL) api: String): Retrofit = Retrofit.Builder()
        .baseUrl(api)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .apply {
            if (BuildConfig.DEBUG) {
                client(
                    OkHttpClient.Builder()
                        .addInterceptor(HttpLoggingInterceptor()
                            .apply {
                                level = HttpLoggingInterceptor.Level.BODY
                            })
                        .build()
                )
            }
        }.build()

    @Provides
    @Singleton
    fun providesApi(retrofit: Retrofit): ItemApi = RetrofitItemApi(retrofit)

    @Provides
    @Singleton
    fun providesDatabase(): ItemDatabase = RealmItemDatabase()

    @Provides
    @Singleton
    fun providesRepository(api: ItemApi, database: ItemDatabase): ItemRepositoryRepository =
        ItemRepositoryRepositoryImpl(api, database)
}
