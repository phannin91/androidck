package com.phan.instagram.screens.profilesettings

import android.os.Bundle
import com.phan.instagram.R
import com.phan.instagram.screens.common.BaseActivity
import com.phan.instagram.screens.common.setupAuthGuard
import kotlinx.android.synthetic.main.activity_profile_settings.*

class ProfileSettingsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_settings)

        setupAuthGuard {
            val viewModel = initViewModel<ProfileSettingsViewModel>()
            sign_out_text.setOnClickListener { viewModel.signOut() }
            back_image.setOnClickListener { finish() }
        }
    }
}
