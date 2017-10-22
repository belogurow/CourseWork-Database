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
            addDiagnoses()
            addTreatments()
            addSideEffects()
            addPatients()
            addDoctors()

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

    private fun addPatients() {
        DBHandler.getInstance(this).apply {
            addPatientWithDoctor(
                    Patient("303426, г. Сысерть, ул. Вагонников 3-я, дом 22, квартира 267"),
                    Person("Васильев Алексей Кириллович", "13/4/1984", "Male"),
                    Doctor("*", "25/1/2015"),
                    Person("Куколевская Горислава Робертовна ", "26/4/1980", "Female"))

            addPatientWithDoctor(
                    Patient("243322, г. Большая Глушица, ул. Базовая, дом 71, квартира 24"),
                    Person("Кочеткова Майя Егоровна", "24/5/1965", "Female"),
                    Doctor("*", "7/2/1999"),
                    Person("Комаров Евлампий Тарасович", "18/9/1972", "Male"))

            addPatientWithDoctor(
                    Patient("143969, г. Ягодное, ул. Королёва, дом 50, квартира 196"),
                    Person("Дидиченко Елена Анатольевна", "4/11/1969", "Female"),
                    Doctor("*", "7/2/1999"),
                    Person("Афанасьева Беатриса Владиславовна ", "7/5/1976", "Female"))

            addPatientWithDoctor(
                    Patient("446731, г. Кондопога, ул. Бадюлина, дом 40, квартира 143"),
                    Person("Комаров Климент Владимирович", "8/2/1985", "Male"),
                    Doctor("*", "7/2/1999"),
                    Person("Николаева Марианна Аркадьевна", "18/12/1962", "Female"))

            addPatientWithDoctor(
                    Patient("186801, г. Чернышковский, ул. Журавлёва, дом 13, квартира 227"),
                    Person("Давыдова Ольга Платоновна", "11/2/1979", "Female"),
                    Doctor("*", "7/2/1999"),
                    Person("Журавлёв Карл Максович", "4/1/1980", "Male"))

            addPatientWithDoctor(
                    Patient("413809, г. Красное-на-Волге, ул. Авангардная, дом 25, квартира 149"),
                    Person("Петров Викторин Миронович", "21/4/1967", "Male"),
                    Doctor("*", "7/2/1999"),
                    Person("Демьянченко Амина Семеновна", "17/5/1969", "Female"))

            addPatientWithDoctor(
                    Patient("628634, г. Большая Мартыновка, ул. Базовая, дом 71, квартира 219"),
                    Person("Борисова Лия Юрьевна", "9/9/1976", "Female"),
                    Doctor("*", "7/2/1999"),
                    Person("Макарова Алёна Борисовна", "28/6/1989", "Female"))

            addPatientWithDoctor(
                    Patient("111538, г. Кондоль, ул. Бажова, дом 29, квартира 250"),
                    Person("Гусев Соломон Эдуардович", "18/5/1972", "Male"),
                    Doctor("*", "7/2/1999"),
                    Person("Веселков Лазарь Николаевич", "14/11/1974", "Male"))

            addPatientWithDoctor(
                    Patient("172377, г. Дальнее, ул. Заречная, дом 1, квартира 268"),
                    Person("Назаров Милий Матвеевич", "17/1/1966", "Male"),
                    Doctor("*", "7/2/1999"),
                    Person("Козлова Капитолина Степановна", "9/10/1968", "Female"))
            
            close()
        }
    }


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(IconicsContextWrapper.wrap(base))
    }
}