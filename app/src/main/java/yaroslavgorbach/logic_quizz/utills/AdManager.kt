package yaroslavgorbach.logic_quizz.utills

import android.app.Activity
import android.app.Application
import android.util.Log
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.admanager.AdManagerAdRequest
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import javax.inject.Inject


class AdManager @Inject constructor(private val app: Application) {

    companion object {
        private const val REWARD_AD_ID = "ca-app-pub-6043694180023070/8286279398"

        private const val TEST_REWARD_AD_ID = "ca-app-pub-3940256099942544/5224354917"
    }

    private var rewordAd: RewardedAd? = null

    fun loadRewordAd() {
        val adRequest: AdRequest = AdManagerAdRequest.Builder().build()

        RewardedAd.load(app, TEST_REWARD_AD_ID,
            adRequest, object : RewardedAdLoadCallback() {
                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    Log.i("dsdsd", "fail" + loadAdError.responseInfo.toString())
                    rewordAd = null
                }

                override fun onAdLoaded(rewardedAd: RewardedAd) {
                    Log.i("dsdsd", "loaded")
                    rewordAd = rewardedAd
                }
            })
    }

    fun showRewardAd(activity: Activity, onReword: () -> Unit) {
        Log.i("dsdsd", "null")

        if (rewordAd != null) {
            Log.i("dsdsd", "ready")
            rewordAd!!.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdShowedFullScreenContent() {
                    loadRewordAd()
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                    rewordAd = null
                    loadRewordAd()
                }

                override fun onAdDismissedFullScreenContent() {
                    rewordAd = null
                    loadRewordAd()
                }
            }

            rewordAd!!.show(activity) { rewardItem ->
                onReword()
            }
        }
    }
}