<<<<<<< HEAD:app/src/main/java/com/apellikka/runmint/ui/composables/navigation/Screen.kt
<<<<<<< HEAD:app/src/main/java/com/apellikka/runmint/ui/composables/navigation/Screen.kt
package com.apellikka.runmint.ui.composables.navigation
=======
package com.apellikka.runmint.ui
>>>>>>> c21757e (fixup! Add functionality for getting run stats WIP):app/src/main/java/com/apellikka/runmint/ui/Screen.kt
=======
package com.apellikka.runmint.activities
>>>>>>> a44e724 (Add data class for weekly stats):app/src/main/java/com/apellikka/runmint/ui/Screen.kt

import androidx.annotation.StringRes
import com.apellikka.runmint.R

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Home : Screen("home", R.string.home)
    object Log : Screen("log", R.string.log)
    object Plan : Screen("plan", R.string.plan)
    object Stats : Screen("stats", R.string.stats)
}