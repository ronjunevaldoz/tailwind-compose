package io.github.ronjunevaldoz.tailwind.showcase.sections

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import io.github.ronjunevaldoz.tailwind.icons.outline.AcademicCap
import io.github.ronjunevaldoz.tailwind.icons.outline.ArrowDownTray
import io.github.ronjunevaldoz.tailwind.icons.outline.Bell
import io.github.ronjunevaldoz.tailwind.icons.outline.Bolt
import io.github.ronjunevaldoz.tailwind.icons.outline.Bookmark
import io.github.ronjunevaldoz.tailwind.icons.outline.Calendar
import io.github.ronjunevaldoz.tailwind.icons.outline.Camera
import io.github.ronjunevaldoz.tailwind.icons.outline.ChatBubbleLeft
import io.github.ronjunevaldoz.tailwind.icons.outline.Check
import io.github.ronjunevaldoz.tailwind.icons.outline.Clock
import io.github.ronjunevaldoz.tailwind.icons.outline.Cog6Tooth
import io.github.ronjunevaldoz.tailwind.icons.outline.CreditCard
import io.github.ronjunevaldoz.tailwind.icons.outline.Envelope
import io.github.ronjunevaldoz.tailwind.icons.outline.Eye
import io.github.ronjunevaldoz.tailwind.icons.outline.Fire
import io.github.ronjunevaldoz.tailwind.icons.outline.Gift
import io.github.ronjunevaldoz.tailwind.icons.outline.GlobeAlt
import io.github.ronjunevaldoz.tailwind.icons.outline.Heart
import io.github.ronjunevaldoz.tailwind.icons.outline.Home
import io.github.ronjunevaldoz.tailwind.icons.outline.Key
import io.github.ronjunevaldoz.tailwind.icons.outline.LockClosed
import io.github.ronjunevaldoz.tailwind.icons.outline.MagnifyingGlass
import io.github.ronjunevaldoz.tailwind.icons.outline.MapPin
import io.github.ronjunevaldoz.tailwind.icons.outline.Microphone
import io.github.ronjunevaldoz.tailwind.icons.outline.Moon
import io.github.ronjunevaldoz.tailwind.icons.outline.MusicalNote
import io.github.ronjunevaldoz.tailwind.icons.outline.PaperAirplane
import io.github.ronjunevaldoz.tailwind.icons.outline.Pencil
import io.github.ronjunevaldoz.tailwind.icons.outline.Photo
import io.github.ronjunevaldoz.tailwind.icons.outline.Play
import io.github.ronjunevaldoz.tailwind.icons.outline.Printer
import io.github.ronjunevaldoz.tailwind.icons.outline.QrCode
import io.github.ronjunevaldoz.tailwind.icons.outline.ShieldCheck
import io.github.ronjunevaldoz.tailwind.icons.outline.ShoppingCart
import io.github.ronjunevaldoz.tailwind.icons.outline.Signal
import io.github.ronjunevaldoz.tailwind.icons.outline.Sparkles
import io.github.ronjunevaldoz.tailwind.icons.outline.Star
import io.github.ronjunevaldoz.tailwind.icons.outline.Sun
import io.github.ronjunevaldoz.tailwind.icons.outline.Trash
import io.github.ronjunevaldoz.tailwind.icons.outline.Trophy
import io.github.ronjunevaldoz.tailwind.icons.outline.Truck
import io.github.ronjunevaldoz.tailwind.icons.outline.User
import io.github.ronjunevaldoz.tailwind.icons.outline.VideoCamera
import io.github.ronjunevaldoz.tailwind.icons.outline.Wifi
import io.github.ronjunevaldoz.tailwind.core.TwColors
import io.github.ronjunevaldoz.tailwind.modifiers.gap2
import io.github.ronjunevaldoz.tailwind.modifiers.gap4
import io.github.ronjunevaldoz.tailwind.modifiers.isTwDarkTheme
import io.github.ronjunevaldoz.tailwind.modifiers.textSlate500
import io.github.ronjunevaldoz.tailwind.modifiers.textWhite
import io.github.ronjunevaldoz.tailwind.modifiers.textXs
import io.github.ronjunevaldoz.tailwind.modifiers.twDark

/**
 * A representative sample of the 324 Heroicons (github.com/tailwindlabs/heroicons, MIT,
 * Tailwind Labs) Outline icons compiled by `tailwind-icons-outline`. Not exhaustive here --
 * see `tailwind/icons-outline/src/commonMain/kotlin/.../icons/outline/` for the full set,
 * one `ImageVector` per `.kt` file, generated (never hand-edited) via
 * `scripts/codegen/generate_icons.py`. `tailwind-icons-outline` is deliberately not part
 * of the `tailwind-compose` facade -- it's an opt-in dependency, same as this showcase
 * itself demonstrates by depending on it directly. Named with the `-outline` suffix (not
 * bare `tailwind-icons`) so a future sibling for another Heroicons style (Solid/Mini/Micro)
 * has an unambiguous name from day one.
 */
private val SAMPLE_ICONS: List<Pair<String, ImageVector>> =
    listOf(
        "AcademicCap" to AcademicCap,
        "ArrowDownTray" to ArrowDownTray,
        "Bell" to Bell,
        "Bolt" to Bolt,
        "Bookmark" to Bookmark,
        "Calendar" to Calendar,
        "Camera" to Camera,
        "ChatBubbleLeft" to ChatBubbleLeft,
        "Check" to Check,
        "Clock" to Clock,
        "Cog6Tooth" to Cog6Tooth,
        "CreditCard" to CreditCard,
        "Envelope" to Envelope,
        "Eye" to Eye,
        "Fire" to Fire,
        "Gift" to Gift,
        "GlobeAlt" to GlobeAlt,
        "Heart" to Heart,
        "Home" to Home,
        "Key" to Key,
        "LockClosed" to LockClosed,
        "MagnifyingGlass" to MagnifyingGlass,
        "MapPin" to MapPin,
        "Microphone" to Microphone,
        "Moon" to Moon,
        "MusicalNote" to MusicalNote,
        "PaperAirplane" to PaperAirplane,
        "Pencil" to Pencil,
        "Photo" to Photo,
        "Play" to Play,
        "Printer" to Printer,
        "QrCode" to QrCode,
        "ShieldCheck" to ShieldCheck,
        "ShoppingCart" to ShoppingCart,
        "Signal" to Signal,
        "Sparkles" to Sparkles,
        "Star" to Star,
        "Sun" to Sun,
        "Trash" to Trash,
        "Trophy" to Trophy,
        "Truck" to Truck,
        "User" to User,
        "VideoCamera" to VideoCamera,
        "Wifi" to Wifi,
    )

@Suppress("ktlint:standard:function-naming")
@Composable
fun IconsShowcase() {
    ShowcaseSection(
        title = "Icons — Heroicons (Outline), 324 icons via tailwind-icons-outline",
        code =
            """
            // tailwind-icons-outline is opt-in, not part of the tailwind-compose facade:
            // implementation("io.github.ronjunevaldoz:tailwind-icons-outline:<version>")

            import io.github.ronjunevaldoz.tailwind.icons.outline.Bell
            import io.github.ronjunevaldoz.tailwind.icons.outline.Heart
            import io.github.ronjunevaldoz.tailwind.icons.outline.AcademicCap
            // ...321 more, one ImageVector per icon

            Icon(imageVector = Bell, contentDescription = "Bell")
            Icon(imageVector = Heart, contentDescription = "Heart")
            """.trimIndent(),
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.size(400.dp),
            horizontalArrangement = gap4(),
            verticalArrangement = gap4(),
        ) {
            items(SAMPLE_ICONS) { (label, icon) ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = gap2(),
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = label,
                        modifier = Modifier.size(24.dp),
                        tint = if (isTwDarkTheme()) TwColors.slate300 else TwColors.slate700,
                    )
                    Text(
                        text = label,
                        style =
                            MaterialTheme.typography.bodySmall
                                .textXs()
                                .textSlate500()
                                .twDark { textWhite() },
                    )
                }
            }
        }
    }
}
