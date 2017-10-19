package com.alexbelogurow.dbcoursework.util

import android.app.Application
import android.content.Context
import android.preference.PreferenceManager
import com.alexbelogurow.dbcoursework.model.*
import com.facebook.stetho.Stetho
import com.mikepenz.iconics.context.IconicsContextWrapper

/**
 * Created by alexbelogurow on 08.09.17.
 */
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        initializeStetho()

        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        if (!prefs.getBoolean("firstTime", false)) {
            addDoctors()
            addDiagnoses()
            addTreatments()
            addSideEffects()

            val editor = prefs.edit()
            editor.putBoolean("firstTime", true)
            editor.apply()
        }
    }

    private fun initializeStetho() {
        // Create an InitializerBuilder
        val initializerBuilder = Stetho.newInitializerBuilder(this)

        // Enable Chrome DevTools
        initializerBuilder.enableWebKitInspector(
                Stetho.defaultInspectorModulesProvider(this)
        )

        // Enable command line interface
        initializerBuilder.enableDumpapp(
                Stetho.defaultDumperPluginsProvider(this)
        )

        // Use the InitializerBuilder to generate an Initializer
        val initializer = initializerBuilder.build()

        // Initialize Stetho with the Initializer
        Stetho.initialize(initializer)
    }

    private fun addDoctors() {
        DBHandler.getInstance(this).apply {
            addDoctor(
                    Doctor("Акушерка", "27/4/2013"),
                    Person("Мамонтова Алевтина Лукьяновна", "3/11/1972", "Female"))

            addDoctor(
                    Doctor("Анестезиолог", "13/3/2015"),
                    Person("Козлов Никита Глебович", "25/2/1941", "Male"))

            addDoctor(
                    Doctor("Диетолог", "10/1/2007"),
                    Person("Логинов Семён Геннадьевич", "18/1/1981", "Male"))

            addDoctor(
                    Doctor("Кардиохирург", "4/4/2010"),
                    Person("Якушев Николай Георгьевич", "7/5/1963", "Male"))

            addDoctor(
                    Doctor("Онколог-хирург", "7/2/1999"),
                    Person("Лазарев Андрей Кимович", "19/1/1959", "Male"))

            close()
        }
    }

    private fun addDiagnoses() {
        DBHandler.getInstance(this).apply {
            addDiagnosis(Diagnosis(
                    "A01.0",
                    "Брюшной тиф"))

            addDiagnosis(Diagnosis(
                    "A02.9",
                    "Сальмонеллезная инфекция неуточненная"))

            addDiagnosis(Diagnosis(
                    "A03.2",
                    "Шигеллёз, вызванный Shigella boydii"))

            addDiagnosis(Diagnosis(
                    "A04.9",
                    "Бактериальная кишечная инфекция неуточненная"))

            addDiagnosis(Diagnosis(
                    "A05.0",
                    "Стафилококковое пищевое отравление"))

            addDiagnosis(Diagnosis(
                    "A15.2",
                    "Туберкулез лёгких, подтвержденный гистологически"))

            addDiagnosis(Diagnosis(
                    "A16.4",
                    "Туберкулез гортани, трахеи, бронхов без упоминания о бактериологическом или гистологическом подтверждении"))

            addDiagnosis(Diagnosis(
                    "A18.0",
                    "Туберкулез костей и суставов"))

            addDiagnosis(Diagnosis(
                    "A30.3",
                    "Пограничная лепра"))

            addDiagnosis(Diagnosis(
                    "A80.1",
                    "Острый паралитический полиомиелит, вызванный диким завезенным вирусом"))

            addDiagnosis(Diagnosis(
                    "B01.0",
                    "Ветряная оспа с менингитом (G02.0*)"))

            addDiagnosis(Diagnosis(
                    "B01.9",
                    "Ветряная оспа без осложнений"))

            addDiagnosis(Diagnosis(
                    "B05.2",
                    "Корь, осложненная пневмонией (J17.1*)"))

            addDiagnosis(Diagnosis(
                    "B17.1",
                    "Острый гепатит C"))

            addDiagnosis(Diagnosis(
                    "B20.0",
                    "Болезнь, вызванная ВИЧ, с проявлениями микобактериальной инфекции"))

            addDiagnosis(Diagnosis(
                    "B23.0",
                    "Острый ВИЧ-инфекционный синдром"))

            addDiagnosis(Diagnosis(
                    "B35.0",
                    "Микоз бороды и головы"))

            addDiagnosis(Diagnosis(
                    "C00.1",
                    "Злокачественное новообразование наружной поверхности нижней губы"))

            addDiagnosis(Diagnosis(
                    "C02.9",
                    "Злокачественное новообразование языка неуточненной части"))

            addDiagnosis(Diagnosis(
                    "C04.1",
                    "Злокачественное новообразование боковой части дна полости рта"))

            addDiagnosis(Diagnosis(
                    "C05.0",
                    "Злокачественное новообразование твёрдого неба"))

            addDiagnosis(Diagnosis(
                    "C11.3",
                    "Злокачественное новообразование передней стенки носоглотки"))

            addDiagnosis(Diagnosis(
                    "C16.0",
                    "Злокачественное новообразование кардии желудка"))

            addDiagnosis(Diagnosis(
                    "C30.1",
                    "Злокачественное новообразование среднего уха"))

            addDiagnosis(Diagnosis(
                    "C34.0",
                    "Злокачественное новообразование главных бронхов"))

            addDiagnosis(Diagnosis(
                    "C69.6",
                    "Злокачественное новообразование глазницы"))

            addDiagnosis(Diagnosis(
                    "J20.0",
                    "Острых бронхит, вызванный Mycoplasma pneumoniae"))

            addDiagnosis(Diagnosis(
                    "Z01.0",
                    "Обследование глаз и зрения"))

            addDiagnosis(Diagnosis(
                    "Z03.4",
                    "Наблюдение при подозрении на инфаркт миокарда"))

            addDiagnosis(Diagnosis(
                    "Z04.6",
                    "Общее психиатрическое обследование по запросу учреждения"))

            addDiagnosis(Diagnosis(
                    "Z10.0",
                    "Профессиональное медицинское обследование"))

            addDiagnosis(Diagnosis(
                    "Z13.1",
                    "Специальное скрининговое обследование с целью выявления сахарного диабета"))

            close()
        }
    }

    private fun addTreatments() {
        DBHandler.getInstance(this).apply {
            addTreatment(Treatment(
                    "Искоренение самого диагноза",
                    TreatmentType.Psychotherapeutic.type
            ))
            addTreatment(Treatment(
                    "Регулирование работы кардии желудка",
                    TreatmentType.Medication.type
            ))
            addTreatment(Treatment(
                    "Уменьшение внутреннего брюшного давления в желудочно-кишечном тракте",
                    TreatmentType.Psychotherapeutic.type
            ))

            close()
        }
    }

    private fun addSideEffects() {
        DBHandler.getInstance(this).apply {
            addSideEffect(SideEffect(
                    2,
                    "Препарат «Регидрон»",
                    "Слабость, головокружение"
            ))
//            addSideEffect(SideEffect(
//                    3,
//                    "Препарат «Регидрон2»",
//                    "Слабость, головокружение"
//            ))
        }
    }


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(IconicsContextWrapper.wrap(base))
    }
}