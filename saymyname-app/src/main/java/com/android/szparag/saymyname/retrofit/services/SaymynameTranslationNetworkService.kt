package com.android.szparag.saymyname.retrofit.services

import com.android.szparag.saymyname.retrofit.apis.ApiTranslationYandex
import com.android.szparag.saymyname.retrofit.services.contracts.TranslationNetworkService
import com.android.szparag.saymyname.utils.Logger
import com.android.szparag.saymyname.utils.single
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

/**
 * Created by Przemyslaw Jablonski (github.com/sharaquss, pszemek.me) on 7/5/2017.
 */
class SaymynameTranslationNetworkService(
    private val retrofit: Retrofit,
    override val NETWORK_SERVICE_API_KEY: String)
  : TranslationNetworkService {

  private val logger = Logger.create(SaymynameTranslationNetworkService::class)
  private val networkApiClient: ApiTranslationYandex = initializeNetworkApiClient()

  private fun initializeNetworkApiClient(): ApiTranslationYandex {
    return retrofit.create(ApiTranslationYandex::class.java)
  }

  //todo: languagesPair should be handled here somehow
  override fun requestTextTranslation(
      texts: List<String>,
      languagePair: String)
      : Observable<List<Pair<String, String>>> {
    logger.debug("requestTextTranslation, languagePair: $languagePair, texts: $texts")
    return networkApiClient.translate(
        key = NETWORK_SERVICE_API_KEY,
        textToTranslate = texts,
        targetLanguagesPair = languagePair)
        .single()
        .map { response -> response.toTranslatedPair(texts) }
  }

}