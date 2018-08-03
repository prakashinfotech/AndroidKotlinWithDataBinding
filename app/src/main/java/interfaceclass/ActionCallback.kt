package interfaceclass

import android.view.View
import model.LoginEntitiy
import retrofit2.Response

/**
 * Created by
 * Prakash Software Solution
 * on 12/30/2017.
 */
/**
 * These are all Action method which is used to trigger Particular event
 */
interface ActionCallback {
    fun onClick(view: View)
    fun onClickAnotherActivity(view: View)
    fun onCrashReport(view: View)
}