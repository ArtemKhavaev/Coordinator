package ru.skillbranch.coordinator

import android.os.Bundle
import android.widget.Checkable
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_bottombar.*
import ru.skillbranch.coordinator.extensions.dpToPx
import ru.skillbranch.coordinator.extensions.init

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupFabs()
        setupBottombar()
    }

    private fun setupFabs(){
        mini1.init(dpToPx(-48), dpToPx(-48))
        mini2.init(dpToPx(0), dpToPx(-64))
        mini3.init(dpToPx(48), dpToPx(-48))

        fab.setOnClickListener {
            if (mini1.isOrWillBeShown) mini1.hide() else mini1.show()
            if (mini2.isOrWillBeShown) mini2.hide() else mini2.show()
            if (mini3.isOrWillBeShown) mini3.hide() else mini3.show()
        }
    }

    private fun setupBottombar() {
        btn_like.setOnClickListener {
            it as Checkable
            it.toggle()
            Snackbar.make(it, if (it.isChecked) "set like" else "unset like" , Snackbar.LENGTH_LONG)
                .setAnchorView(bottombar)
                .show()
        }

        btn_settings.setOnClickListener {
            it as Checkable
            it.toggle()
            if (it.isChecked) submenu.open() else submenu.close()
        }
    }
}
