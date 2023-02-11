package br.com.alura.orgs

import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import br.com.alura.orgs.database.AppDataBase
import br.com.alura.orgs.ui.activity.FormProdutoActivity
import br.com.alura.orgs.ui.activity.ListaProdutoActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProdutoActivityTest {

    @Before
    fun preparaAmbiente(){
        AppDataBase.instance(InstrumentationRegistry.getInstrumentation().targetContext).clearAllTables()
    }

    @Test
    fun Should_show_appName(){
        //onde buscar
        launch(ListaProdutoActivity::class.java)

        //o que buscar
        onView(withText("Orgs"))

            //verificar se existe
        .check(matches(isDisplayed()))
    }

    @Test
    fun Should_showFieldsToCreate_a_Product(){

        launch(FormProdutoActivity::class.java)

        onView(withId(R.id.name_input)).check(matches(isDisplayed()))
        onView(withId(R.id.desc_input)).check(matches(isDisplayed()))
        onView(withId(R.id.item_value_input)).check(matches(isDisplayed()))
        onView(withId(R.id.botao_salvar)).check(matches(isDisplayed()))

    }

    @Test
    fun Should_FillFieldsAndSave(){

        launch(ListaProdutoActivity::class.java)

        onView(withId(R.id.extended_fab)).perform(click())

        onView(withId(R.id.name_input_edit_text)).perform(
            typeText("Banana"),
        closeSoftKeyboard()
        )
        onView(withId(R.id.desc_input_edit_text)).perform(
            typeText("Banana prata"),
        closeSoftKeyboard()
        )
        onView(withId(R.id.item_value_input_edit_text)).perform(typeText("6.99"),
        closeSoftKeyboard()
        )
        onView(withId(R.id.botao_salvar)).perform(click())

        onView(withText("Banana")).check(matches(isDisplayed()))

    }

    @Test
    fun naoSalva(){
        launch(FormProdutoActivity::class.java)

        onView(withId(R.id.name_input_edit_text)).perform(
            scrollTo(),
            typeText("Carambola"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.desc_input_edit_text)).perform(
            typeText("Carambola"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.item_value_input_edit_text)).perform(typeText("101.99"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.botao_salvar)).perform(click())

        launch(ListaProdutoActivity::class.java)

        onView(withText("Carambola")).check(ViewAssertions.doesNotExist())
    }

}