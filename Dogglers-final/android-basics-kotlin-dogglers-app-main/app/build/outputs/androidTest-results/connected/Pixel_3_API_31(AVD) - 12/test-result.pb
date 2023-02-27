
Ø
^
VerticalListTestscom.example.dogglersrecycler_view_item_count2Û Øü¿Õª>:ˆ ØüÄö‰¥"˘

logcatandroid„
‡D:\DH\Kotlin-Training\Dogglers-final\android-basics-kotlin-dogglers-app-main\app\build\outputs\androidTest-results\connected\Pixel_3_API_31(AVD) - 12\logcat-com.example.dogglers.VerticalListTests-recycler_view_item_count.txt"¬

device-infoandroidß
§D:\DH\Kotlin-Training\Dogglers-final\android-basics-kotlin-dogglers-app-main\app\build\outputs\androidTest-results\connected\Pixel_3_API_31(AVD) - 12\device-info.pb"√

device-info.meminfoandroid†
ùD:\DH\Kotlin-Training\Dogglers-final\android-basics-kotlin-dogglers-app-main\app\build\outputs\androidTest-results\connected\Pixel_3_API_31(AVD) - 12\meminfo"√

device-info.cpuinfoandroid†
ùD:\DH\Kotlin-Training\Dogglers-final\android-basics-kotlin-dogglers-app-main\app\build\outputs\androidTest-results\connected\Pixel_3_API_31(AVD) - 12\cpuinfoœ
n
VerticalListTestscom.example.dogglers(vertical_scroll_content_at_last_position2˜ ØüÄ∑ú∞:˘ Øü¿¥†."â

logcatandroidÛ
D:\DH\Kotlin-Training\Dogglers-final\android-basics-kotlin-dogglers-app-main\app\build\outputs\androidTest-results\connected\Pixel_3_API_31(AVD) - 12\logcat-com.example.dogglers.VerticalListTests-vertical_scroll_content_at_last_position.txt"¬

device-infoandroidß
§D:\DH\Kotlin-Training\Dogglers-final\android-basics-kotlin-dogglers-app-main\app\build\outputs\androidTest-results\connected\Pixel_3_API_31(AVD) - 12\device-info.pb"√

device-info.meminfoandroid†
ùD:\DH\Kotlin-Training\Dogglers-final\android-basics-kotlin-dogglers-app-main\app\build\outputs\androidTest-results\connected\Pixel_3_API_31(AVD) - 12\meminfo"√

device-info.cpuinfoandroid†
ùD:\DH\Kotlin-Training\Dogglers-final\android-basics-kotlin-dogglers-app-main\app\build\outputs\androidTest-results\connected\Pixel_3_API_31(AVD) - 12\cpuinfo€;
Y
VerticalListTestscom.example.dogglersvertical_scrolling2˘ Øü¿∞åΩ:˙ Øü¿Òæ∞¥4
Ñandroidx.test.espresso.base.DefaultFailureHandler$AssertionFailedWithCauseError: '(view has effective visibility <VISIBLE> and view.getGlobalVisibleRect() to return non-empty rectangle)' doesn't match the selected view.
Expected: (view has effective visibility <VISIBLE> and view.getGlobalVisibleRect() to return non-empty rectangle)
Got: view.getGlobalVisibleRect() returned empty rectangle
View Details: MaterialTextView{id=2131230886, res-name=dog_name, visibility=VISIBLE, width=1014, height=74, has-focus=false, has-focusable=false, has-window-focus=true, is-clickable=false, is-enabled=true, is-focused=false, is-focusable=false, is-layout-requested=false, is-selected=false, layout-params=androidx.constraintlayout.widget.ConstraintLayout$LayoutParams@ba7cf0a, tag=null, root-is-layout-requested=false, has-input-connection=false, x=22.0, y=556.0, text=Faye, input-type=0, ime-target=false, has-links=false}

at dalvik.system.VMStack.getThreadStackTrace(Native Method)
at java.lang.Thread.getStackTrace(Thread.java:1724)
at androidx.test.espresso.base.DefaultFailureHandler.getUserFriendlyError(DefaultFailureHandler.java:12)
at androidx.test.espresso.base.DefaultFailureHandler.handle(DefaultFailureHandler.java:7)
at androidx.test.espresso.ViewInteraction.waitForAndHandleInteractionResults(ViewInteraction.java:5)
at androidx.test.espresso.ViewInteraction.check(ViewInteraction.java:12)
at com.example.dogglers.VerticalListTests.vertical_scrolling(VerticalListTests.kt:56)
... 29 trimmed
Caused by: junit.framework.AssertionFailedError: '(view has effective visibility <VISIBLE> and view.getGlobalVisibleRect() to return non-empty rectangle)' doesn't match the selected view.
Expected: (view has effective visibility <VISIBLE> and view.getGlobalVisibleRect() to return non-empty rectangle)
Got: view.getGlobalVisibleRect() returned empty rectangle
View Details: MaterialTextView{id=2131230886, res-name=dog_name, visibility=VISIBLE, width=1014, height=74, has-focus=false, has-focusable=false, has-window-focus=true, is-clickable=false, is-enabled=true, is-focused=false, is-focusable=false, is-layout-requested=false, is-selected=false, layout-params=androidx.constraintlayout.widget.ConstraintLayout$LayoutParams@ba7cf0a, tag=null, root-is-layout-requested=false, has-input-connection=false, x=22.0, y=556.0, text=Faye, input-type=0, ime-target=false, has-links=false}

at androidx.test.espresso.matcher.ViewMatchers.assertThat(ViewMatchers.java:16)
at androidx.test.espresso.assertion.ViewAssertions$MatchesViewAssertion.check(ViewAssertions.java:11)
at androidx.test.espresso.ViewInteraction$SingleExecutionViewAssertion.check(ViewInteraction.java:2)
at androidx.test.espresso.ViewInteraction$2.call(ViewInteraction.java:12)
at androidx.test.espresso.ViewInteraction$2.call(ViewInteraction.java:1)
at java.util.concurrent.FutureTask.run(FutureTask.java:266)
at android.os.Handler.handleCallback(Handler.java:938)
at android.os.Handler.dispatchMessage(Handler.java:99)
at android.os.Looper.loopOnce(Looper.java:201)
at android.os.Looper.loop(Looper.java:288)
at android.app.ActivityThread.main(ActivityThread.java:7839)
at java.lang.reflect.Method.invoke(Native Method)
at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:548)
at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:1003)$junit.framework.AssertionFailedErrorÑandroidx.test.espresso.base.DefaultFailureHandler$AssertionFailedWithCauseError: '(view has effective visibility <VISIBLE> and view.getGlobalVisibleRect() to return non-empty rectangle)' doesn't match the selected view.
Expected: (view has effective visibility <VISIBLE> and view.getGlobalVisibleRect() to return non-empty rectangle)
Got: view.getGlobalVisibleRect() returned empty rectangle
View Details: MaterialTextView{id=2131230886, res-name=dog_name, visibility=VISIBLE, width=1014, height=74, has-focus=false, has-focusable=false, has-window-focus=true, is-clickable=false, is-enabled=true, is-focused=false, is-focusable=false, is-layout-requested=false, is-selected=false, layout-params=androidx.constraintlayout.widget.ConstraintLayout$LayoutParams@ba7cf0a, tag=null, root-is-layout-requested=false, has-input-connection=false, x=22.0, y=556.0, text=Faye, input-type=0, ime-target=false, has-links=false}

at dalvik.system.VMStack.getThreadStackTrace(Native Method)
at java.lang.Thread.getStackTrace(Thread.java:1724)
at androidx.test.espresso.base.DefaultFailureHandler.getUserFriendlyError(DefaultFailureHandler.java:12)
at androidx.test.espresso.base.DefaultFailureHandler.handle(DefaultFailureHandler.java:7)
at androidx.test.espresso.ViewInteraction.waitForAndHandleInteractionResults(ViewInteraction.java:5)
at androidx.test.espresso.ViewInteraction.check(ViewInteraction.java:12)
at com.example.dogglers.VerticalListTests.vertical_scrolling(VerticalListTests.kt:56)
... 29 trimmed
Caused by: junit.framework.AssertionFailedError: '(view has effective visibility <VISIBLE> and view.getGlobalVisibleRect() to return non-empty rectangle)' doesn't match the selected view.
Expected: (view has effective visibility <VISIBLE> and view.getGlobalVisibleRect() to return non-empty rectangle)
Got: view.getGlobalVisibleRect() returned empty rectangle
View Details: MaterialTextView{id=2131230886, res-name=dog_name, visibility=VISIBLE, width=1014, height=74, has-focus=false, has-focusable=false, has-window-focus=true, is-clickable=false, is-enabled=true, is-focused=false, is-focusable=false, is-layout-requested=false, is-selected=false, layout-params=androidx.constraintlayout.widget.ConstraintLayout$LayoutParams@ba7cf0a, tag=null, root-is-layout-requested=false, has-input-connection=false, x=22.0, y=556.0, text=Faye, input-type=0, ime-target=false, has-links=false}

at androidx.test.espresso.matcher.ViewMatchers.assertThat(ViewMatchers.java:16)
at androidx.test.espresso.assertion.ViewAssertions$MatchesViewAssertion.check(ViewAssertions.java:11)
at androidx.test.espresso.ViewInteraction$SingleExecutionViewAssertion.check(ViewInteraction.java:2)
at androidx.test.espresso.ViewInteraction$2.call(ViewInteraction.java:12)
at androidx.test.espresso.ViewInteraction$2.call(ViewInteraction.java:1)
at java.util.concurrent.FutureTask.run(FutureTask.java:266)
at android.os.Handler.handleCallback(Handler.java:938)
at android.os.Handler.dispatchMessage(Handler.java:99)
at android.os.Looper.loopOnce(Looper.java:201)
at android.os.Looper.loop(Looper.java:288)
at android.app.ActivityThread.main(ActivityThread.java:7839)
at java.lang.reflect.Method.invoke(Native Method)
at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:548)
at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:1003)"Û

logcatandroid›
⁄D:\DH\Kotlin-Training\Dogglers-final\android-basics-kotlin-dogglers-app-main\app\build\outputs\androidTest-results\connected\Pixel_3_API_31(AVD) - 12\logcat-com.example.dogglers.VerticalListTests-vertical_scrolling.txt"¬

device-infoandroidß
§D:\DH\Kotlin-Training\Dogglers-final\android-basics-kotlin-dogglers-app-main\app\build\outputs\androidTest-results\connected\Pixel_3_API_31(AVD) - 12\device-info.pb"√

device-info.meminfoandroid†
ùD:\DH\Kotlin-Training\Dogglers-final\android-basics-kotlin-dogglers-app-main\app\build\outputs\androidTest-results\connected\Pixel_3_API_31(AVD) - 12\meminfo"√

device-info.cpuinfoandroid†
ùD:\DH\Kotlin-Training\Dogglers-final\android-basics-kotlin-dogglers-app-main\app\build\outputs\androidTest-results\connected\Pixel_3_API_31(AVD) - 12\cpuinfo“
p
VerticalListTestscom.example.dogglers)vertical_scroll_content_at_first_position2˚ ØüÄï˛§:¸ ØüÄ≤øö"ä

logcatandroidÙ
ÒD:\DH\Kotlin-Training\Dogglers-final\android-basics-kotlin-dogglers-app-main\app\build\outputs\androidTest-results\connected\Pixel_3_API_31(AVD) - 12\logcat-com.example.dogglers.VerticalListTests-vertical_scroll_content_at_first_position.txt"¬

device-infoandroidß
§D:\DH\Kotlin-Training\Dogglers-final\android-basics-kotlin-dogglers-app-main\app\build\outputs\androidTest-results\connected\Pixel_3_API_31(AVD) - 12\device-info.pb"√

device-info.meminfoandroid†
ùD:\DH\Kotlin-Training\Dogglers-final\android-basics-kotlin-dogglers-app-main\app\build\outputs\androidTest-results\connected\Pixel_3_API_31(AVD) - 12\meminfo"√

device-info.cpuinfoandroid†
ùD:\DH\Kotlin-Training\Dogglers-final\android-basics-kotlin-dogglers-app-main\app\build\outputs\androidTest-results\connected\Pixel_3_API_31(AVD) - 12\cpuinfo*ß
c
test-results.logOcom.google.testing.platform.runtime.android.driver.AndroidInstrumentationDriver±
ÆD:\DH\Kotlin-Training\Dogglers-final\android-basics-kotlin-dogglers-app-main\app\build\outputs\androidTest-results\connected\Pixel_3_API_31(AVD) - 12\testlog\test-results.log 2
text/plain