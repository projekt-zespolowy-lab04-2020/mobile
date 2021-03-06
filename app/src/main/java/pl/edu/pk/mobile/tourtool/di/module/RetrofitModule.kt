package pl.edu.pk.mobile.tourtool.di.module

import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import okhttp3.OkHttpClient
import pl.edu.pk.mobile.tourtool.retrofit.Webservice
import pl.edu.pk.mobile.tourtool.service.interceptor.TokenInterceptor
import pl.edu.pk.mobile.tourtool.util.SharedPreferencesHolder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module(
  includes = [
    ApplicationModuleBinds::class
  ]
)
object RetrofitModule {
  @JvmStatic
  @Singleton
  @Provides
  fun getWebservice(retrofit: Retrofit): Webservice =
    retrofit.create(Webservice::class.java)

  @JvmStatic
  @Singleton
  @Provides
  fun getRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
      .baseUrl(" http://188.68.236.128/api/")
      .addConverterFactory(GsonConverterFactory.create())
      .client(okHttpClient)
      .build()

  @JvmStatic
  @Singleton
  @Provides
  fun getOkHttpClient(
    tokenInterceptor: TokenInterceptor
  ): OkHttpClient {
    val okHttpBuilder = OkHttpClient()
      .newBuilder()
    return okHttpBuilder
      .addInterceptor(tokenInterceptor)
      .build()
  }

  @JvmStatic
  @Singleton
  @Provides
  fun provideTokenInterceptor(sharedPreferencesHolder: SharedPreferencesHolder): TokenInterceptor =
    TokenInterceptor(sharedPreferencesHolder)

//  //  todo: Implement it later
//  @JvmStatic
//  @Singleton
//  @get:Provides
//  val httpLoggingInterceptor: HttpLoggingInterceptor
//    get() {
//      val httpLoggingInterceptor = HttpLoggingInterceptor()
//      httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//      return httpLoggingInterceptor
//    }
}
