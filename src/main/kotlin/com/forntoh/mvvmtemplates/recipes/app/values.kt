package com.forntoh.mvvmtemplates.recipes.app

const val colors = """<resources>
    <color name="purple_200">#FFBB86FC</color>
    <color name="purple_500">#FF6200EE</color>
    <color name="purple_700">#FF3700B3</color>
    
    <color name="teal_200">#FF03DAC5</color>
    <color name="teal_700">#FF018786</color>
    
    <color name="red_200">#cf6679</color>
    <color name="red_600">#b00020</color>
    
    <color name="black">#FF000000</color>
    <color name="white">#FFFFFFFF</color>
    
    <color name="black_800">#121212</color>
</resources>"""

const val dimens = """<resources>
    <!-- Default screen margins, per the Android Design guidelines. -->
    <dimen name="activity_horizontal_margin">16dp</dimen>
    <dimen name="activity_vertical_margin">16dp</dimen>

    <dimen name="layout_padding_4dp">4dp</dimen>
    <dimen name="layout_padding_8dp">8dp</dimen>
    <dimen name="layout_padding_10dp">10dp</dimen>
    <dimen name="layout_padding_15dp">15dp</dimen>
    <dimen name="layout_padding_16dp">16dp</dimen>
    <dimen name="layout_padding_18dp">18dp</dimen>
    <dimen name="layout_padding_32dp">32dp</dimen>
    <dimen name="layout_padding_24dp">24dp</dimen>
    <dimen name="layout_padding_30dp">30dp</dimen>

    <dimen name="default_corner_radius">12dp</dimen>
    <dimen name="default_card_elevation">2dp</dimen>

</resources>"""

const val shape = """<resources>

    <style name="ShapeAppearance.App.SmallComponent" parent="ShapeAppearance.MaterialComponents.SmallComponent">
        <item name="cornerFamily">rounded</item>
        <item name="cornerSize">@dimen/default_corner_radius</item>
    </style>

    <style name="ShapeAppearance.App.MediumComponent" parent="ShapeAppearance.MaterialComponents.MediumComponent">
        <item name="cornerFamily">rounded</item>
        <item name="cornerSize">@dimen/default_corner_radius</item>
    </style>

    <style name="ShapeAppearance.App.LargeComponent" parent="ShapeAppearance.MaterialComponents.LargeComponent">
        <item name="cornerFamily">rounded</item>
        <item name="cornerSize">@dimen/default_corner_radius</item>
    </style>

    <style name="CardView.Rounded.Top" parent="ShapeAppearance.MaterialComponents.MediumComponent">
        <item name="cornerSizeTopRight">@dimen/default_corner_radius</item>
        <item name="cornerSizeTopLeft">@dimen/default_corner_radius</item>
        <item name="cornerSizeBottomLeft">0dp</item>
        <item name="cornerSizeBottomRight">0dp</item>
        <item name="cornerFamily">rounded</item>
    </style>

    <style name="Circle.70" parent="ShapeAppearance.App.SmallComponent">
        <item name="cornerSize">35dp</item>
    </style>

</resources>"""

fun styles(appName: String) = """<resources>

    <style name="Theme.$appName.ActionBar" parent="ThemeOverlay.AppCompat.DayNight.ActionBar" />

    <style name="Theme.$appName.Toolbar" parent="ThemeOverlay.AppCompat.DayNight" />

    <style name="Theme.$appName.Button" parent="Widget.MaterialComponents.Button">
        <item name="android:minHeight">64dp</item>
        <item name="backgroundTint">@color/primary_button_selector</item>
        <item name="android:maxLines">1</item>
        <item name="android:ellipsize">end</item>
        <item name="paddingStart">0dp</item>
        <item name="paddingEnd">0dp</item>
    </style>

    <style name="Theme.$appName.TextView" parent="android:Widget.TextView">
        <item name="android:includeFontPadding">false</item>
    </style>

    <style name="Theme.$appName.CardView" parent="Widget.MaterialComponents.CardView">
        <item name="rippleColor">?attr/colorControlHighlight</item>
        <item name="cardElevation">@dimen/default_card_elevation</item>
        <item name="strokeColor">?attr/colorSecondary</item>
    </style>

    <style name="Theme.$appName.RecyclerView" parent="Widget.MaterialComponents.CardView">
        <item name="android:overScrollMode">never</item>
    </style>

    <style name="Theme.$appName.TextInput" parent="Widget.MaterialComponents.TextInputLayout.OutlinedBox">
        <item name="errorIconDrawable">@null</item>
    </style>

</resources>"""

fun themes(appName: String) = """<resources xmlns:tools="http://schemas.android.com/tools">

    <style name="Theme.$appName" parent="Base.Theme.$appName">
        <!-- Primary brand color. -->
        <item name="colorPrimary">@color/purple_500</item>
        <item name="colorPrimaryVariant">@color/purple_700</item>
        <item name="colorOnPrimary">@color/white</item>

        <!-- Secondary brand color. -->
        <item name="colorSecondary">@color/teal_200</item>
        <item name="colorSecondaryVariant">@color/teal_700</item>
        <item name="colorOnSecondary">@color/black</item>

        <item name="colorSurface">@color/white</item>
        <item name="colorOnSurface">@color/black</item>
        
        <item name="colorError">@color/red_600</item>
        <item name="colorOnError">@color/white</item>

        <item name="android:colorBackground">@color/white</item>
        <item name="colorOnBackground">@color/black</item>

        <item name="colorControlHighlight">?attr/colorSecondary</item>
    </style>

    <style name="Base.Theme.$appName" parent="Theme.MaterialComponents.DayNight.NoActionBar">

        <!-- <item name="android:statusBarColor">@android:color/transparent</item> -->
        <!-- <item name="android:navigationBarColor">@android:color/transparent</item> -->

        <item name="textInputStyle">@style/Theme.$appName.TextInput</item>
        <item name="recyclerViewStyle">@style/Theme.$appName.RecyclerView</item>
        <item name="materialButtonStyle">@style/Theme.$appName.Button</item>
        <item name="android:textViewStyle">@style/Theme.$appName.TextView</item>
        <item name="materialCardViewStyle">@style/Theme.$appName.CardView</item>

        <item name="android:actionBarStyle">@style/Theme.$appName.ActionBar</item>
        <item name="android:toolbarStyle">@style/Theme.$appName.Toolbar</item>

        <item name="textAppearanceBody1">@style/TextAppearance.MdcTypographyStyles.Body1</item>
        <item name="textAppearanceBody2">@style/TextAppearance.MdcTypographyStyles.Body2</item>
        <item name="textAppearanceButton">@style/TextAppearance.MdcTypographyStyles.Button</item>
        <item name="textAppearanceCaption">@style/TextAppearance.MdcTypographyStyles.Caption</item>
        <item name="textAppearanceOverline">@style/TextAppearance.MdcTypographyStyles.Overline
        </item>
        <item name="textAppearanceHeadline1">@style/TextAppearance.MdcTypographyStyles.Headline1
        </item>
        <item name="textAppearanceHeadline2">@style/TextAppearance.MdcTypographyStyles.Headline2
        </item>
        <item name="textAppearanceHeadline3">@style/TextAppearance.MdcTypographyStyles.Headline3
        </item>
        <item name="textAppearanceHeadline4">@style/TextAppearance.MdcTypographyStyles.Headline4
        </item>
        <item name="textAppearanceHeadline5">@style/TextAppearance.MdcTypographyStyles.Headline5
        </item>
        <item name="textAppearanceHeadline6">@style/TextAppearance.MdcTypographyStyles.Headline6
        </item>
        <item name="textAppearanceSubtitle1">@style/TextAppearance.MdcTypographyStyles.Subtitle1
        </item>
        <item name="textAppearanceSubtitle2">@style/TextAppearance.MdcTypographyStyles.Subtitle2
        </item>

        <item name="shapeAppearanceSmallComponent">@style/ShapeAppearance.App.SmallComponent</item>
        <item name="shapeAppearanceMediumComponent">@style/ShapeAppearance.App.MediumComponent</item>
        <item name="shapeAppearanceLargeComponent">@style/ShapeAppearance.App.LargeComponent</item>

        <item name="md_color_title">?attr/colorAccent</item>
        <item name="md_color_content">?attr/colorOnSurface</item>
        <item name="md_color_button_text">?attr/colorAccent</item>
        <item name="md_background_color">?attr/colorSurface</item>
        <item name="md_corner_radius">@dimen/default_corner_radius</item>
        <!-- <item name="md_font_title">@font/</item>
        <item name="md_font_button">@font/</item>
        <item name="md_font_body">@font/</item>
        <item name="md_button_casing">literal</item> -->

        <item name="windowActionBar">false</item>
        <item name="windowNoTitle">true</item>

        <item name="android:windowLightStatusBar" tools:ignore="NewApi">?attr/isLightTheme</item>
        <item name="android:windowLightNavigationBar" tools:ignore="NewApi">?attr/isLightTheme</item>

        <item name="android:windowDrawsSystemBarBackgrounds">true</item>
    </style>

</resources>"""

fun themesNight(appName: String) = """<resources>

    <style name="Theme.$appName" parent="Base.Theme.$appName">
        <!-- Primary brand color. -->
        <item name="colorPrimary">@color/purple_200</item>
        <item name="colorPrimaryVariant">@color/purple_700</item>
        <item name="colorOnPrimary">@color/black</item>

        <!-- Secondary brand color. -->
        <item name="colorSecondary">@color/teal_200</item>
        <item name="colorSecondaryVariant">@color/teal_200</item>
        <item name="colorOnSecondary">@color/black</item>

        <item name="colorSurface">@color/black</item>
        <item name="colorOnSurface">@color/white</item>

        <item name="colorError">@color/red_200</item>
        <item name="colorOnError">@color/black</item>
        
        <item name="android:colorBackground">@color/black</item>
        <item name="colorOnBackground">@color/white</item>

        <item name="colorControlHighlight">?attr/colorSecondary</item>
    </style>
</resources>"""

const val type = """<resources>

    <style name="TextAppearance.MdcTypographyStyles.Headline1" parent="TextAppearance.MaterialComponents.Headline1">
        <item name="fontFamily">@font/</item>
        <item name="android:fontFamily">@font/</item>
        <item name="android:textSize">96sp</item>
        <item name="lineHeight">92dp</item>
    </style>

    <style name="TextAppearance.MdcTypographyStyles.Headline2" parent="TextAppearance.MaterialComponents.Headline2">
        <item name="fontFamily">@font/</item>
        <item name="android:fontFamily">@font/</item>
        <item name="android:textSize">60sp</item>
        <item name="lineHeight">56dp</item>
    </style>

    <style name="TextAppearance.MdcTypographyStyles.Headline3" parent="TextAppearance.MaterialComponents.Headline3">
        <item name="fontFamily">@font/</item>
        <item name="android:fontFamily">@font/</item>
        <item name="android:textSize">48sp</item>
        <item name="lineHeight">44dp</item>
    </style>

    <style name="TextAppearance.MdcTypographyStyles.Headline4" parent="TextAppearance.MaterialComponents.Headline4">
        <item name="fontFamily">@font/</item>
        <item name="android:fontFamily">@font/</item>
        <item name="android:textSize">34sp</item>
        <item name="lineHeight">30dp</item>
    </style>

    <style name="TextAppearance.MdcTypographyStyles.Headline5" parent="TextAppearance.MaterialComponents.Headline5">
        <item name="fontFamily">@font/</item>
        <item name="android:fontFamily">@font/</item>
        <item name="android:textSize">24sp</item>
        <item name="lineHeight">20dp</item>
    </style>

    <style name="TextAppearance.MdcTypographyStyles.Headline6" parent="TextAppearance.MaterialComponents.Headline6">
        <item name="fontFamily">@font/</item>
        <item name="android:fontFamily">@font/</item>
        <item name="android:textSize">20sp</item>
        <item name="lineHeight">16dp</item>
    </style>

    <style name="TextAppearance.MdcTypographyStyles.Subtitle1" parent="TextAppearance.MaterialComponents.Subtitle1">
        <item name="fontFamily">@font/</item>
        <item name="android:fontFamily">@font/</item>
        <item name="android:textSize">16sp</item>
    </style>

    <style name="TextAppearance.MdcTypographyStyles.Subtitle2" parent="TextAppearance.MaterialComponents.Subtitle2">
        <item name="fontFamily">@font/</item>
        <item name="android:fontFamily">@font/</item>
        <item name="android:textSize">14sp</item>
    </style>

    <style name="TextAppearance.MdcTypographyStyles.Body1" parent="TextAppearance.MaterialComponents.Body1">
        <item name="fontFamily">@font/</item>
        <item name="android:fontFamily">@font/</item>
        <item name="android:textSize">17sp</item>
    </style>

    <style name="TextAppearance.MdcTypographyStyles.Body2" parent="TextAppearance.MaterialComponents.Body2">
        <item name="fontFamily">@font/</item>
        <item name="android:fontFamily">@font/</item>
        <item name="android:textSize">15sp</item>
    </style>

    <style name="TextAppearance.MdcTypographyStyles.Button" parent="TextAppearance.MaterialComponents.Button">
        <item name="fontFamily">@font/</item>
        <item name="android:fontFamily">@font/</item>
        <item name="android:textSize">20sp</item>
        <item name="android:textAllCaps">false</item>
    </style>

    <style name="TextAppearance.MdcTypographyStyles.Caption" parent="TextAppearance.MaterialComponents.Caption">
        <item name="fontFamily">@font/</item>
        <item name="android:fontFamily">@font/</item>
        <item name="android:textSize">13sp</item>
    </style>

    <style name="TextAppearance.MdcTypographyStyles.Overline" parent="TextAppearance.MaterialComponents.Overline">
        <item name="fontFamily">@font/</item>
        <item name="android:fontFamily">@font/</item>
        <item name="android:textSize">10sp</item>
    </style>

</resources>"""