package com.lberdiales.activitylauncher

import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract

// Inspired in https://github.com/DylanCaiCoding/ActivityResultLauncher

class ActivityLauncher<I, O>(
    caller: ActivityResultCaller,
    contract: ActivityResultContract<I, O>
) {

    private val launcher: ActivityResultLauncher<I>
    private var callback: ActivityResultCallback<O>? = null

    init {
        launcher = caller.registerForActivityResult(contract) { result ->
            callback?.let {
                it.onActivityResult(result)
                callback = null
            }
        }
    }

    fun launch(input: I, callback: ActivityResultCallback<O>) {
        this.callback = callback
        launcher.launch(input)
    }
}
