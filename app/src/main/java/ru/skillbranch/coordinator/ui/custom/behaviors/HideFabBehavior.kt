package ru.skillbranch.coordinator.ui.custom.behaviors

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.skillbranch.coordinator.ui.custom.CustomFab

class HideFabBehavior() : CoordinatorLayout.Behavior<FloatingActionButton>(){
    //need secondary constructor if set behavior from xml
    constructor(ctx:Context, attrs:AttributeSet) : this()

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: FloatingActionButton,
        dependency: View
    ): Boolean {
        dependency as CustomFab
        return if(dependency.isChecked && dependency.isOrWillBeHidden) { child.hide(); true }
        else if(dependency.isChecked) { child.show(); true }
        else false
    }

}