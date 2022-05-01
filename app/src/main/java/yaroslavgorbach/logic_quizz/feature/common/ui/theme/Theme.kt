package yaroslavgorbach.logic_quizz.feature.common.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import yaroslavgorbach.logic_quizz.feature.common.ui.theme.*

private val DarkColorPalette = darkColors(
    primary = Blue200,
    primaryVariant = Blue700,
    secondary = Pink200,
    onPrimary = LightDark
)

private val LightColorPalette = lightColors(
    primary = Blue400,
    primaryVariant = Blue700,
    secondary = Pink200,
    onPrimary = LightGray
    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    */
)

@Composable
fun LogicPuzzleTheme(isDarkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (isDarkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val systemUiController = rememberSystemUiController()

    systemUiController.setSystemBarsColor(
        color = if (isDarkTheme) DarkColorPalette.background else LightColorPalette.background,
        darkIcons = isDarkTheme.not()
    )

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}