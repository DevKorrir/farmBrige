package dev.korryr.farmbrige.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = TealGreen,
    onPrimary = White,
    primaryContainer = DarkTeal,
    onPrimaryContainer = LightLeafGreen,
    secondary = Amber,
    onSecondary = DeepSoil,
    secondaryContainer = HarvestOrange,
    onSecondaryContainer = White,
    tertiary = SeedPurple,
    onTertiary = White,
    tertiaryContainer = SeedPurple.copy(alpha = 0.7f),
    onTertiaryContainer = White,
    error = AlertRed,
    onError = White,
    errorContainer = AlertRed.copy(alpha = 0.7f),
    onErrorContainer = White,
    background = DarkBackground,
    onBackground = PrimaryTextDark,
    surface = DarkSurface,
    onSurface = PrimaryTextDark,
    surfaceVariant = DarkElevated,
    onSurfaceVariant = SecondaryTextDark,
    outline = SecondaryTextDark.copy(alpha = 0.5f)
)

private val LightColorScheme = lightColorScheme(
    primary = TealGreen,
    onPrimary = White,
    primaryContainer = LightLeafGreen.copy(alpha = 0.3f),
    onPrimaryContainer = DarkTeal,
    secondary = Amber,
    onSecondary = DeepSoil,
    secondaryContainer = Amber.copy(alpha = 0.3f),
    onSecondaryContainer = FertileEarthBrown,
    tertiary = SeedPurple,
    onTertiary = White,
    tertiaryContainer = SeedPurple.copy(alpha = 0.1f),
    onTertiaryContainer = SeedPurple,
    error = AlertRed,
    onError = White,
    errorContainer = AlertRed.copy(alpha = 0.1f),
    onErrorContainer = AlertRed,
    background = SoftWhite,
    onBackground = PrimaryTextLight,
    surface = LightGray,
    onSurface = PrimaryTextLight,
    surfaceVariant = White,
    onSurfaceVariant = SecondaryTextLight,
    outline = SecondaryTextLight.copy(alpha = 0.5f)
    )

@Composable
fun FarmBrigeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme

    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}