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
                    "Непосредственное наблюдение",
                    TreatmentType.Psychotherapeutic.type))

            addTreatment(Treatment(
                    "Лечение туберкулёза медикаментами",
                    TreatmentType.Medication.type))

            addTreatment(Treatment(
                    "Проведение нескольких тестов для проверки четкости зрения на каждый глаз",
                    TreatmentType.Physiotherapeutic.type))

            addTreatment(Treatment(
                    "Электрокардиография",
                    TreatmentType.Psychotherapeutic.type))

            addTreatment(Treatment(
                    "Эхокардиография",
                    TreatmentType.Psychotherapeutic.type))

            addTreatment(Treatment(
                    "Анализ крови на кардиотропные белки",
                    TreatmentType.Psychotherapeutic.type))

            addTreatment(Treatment(
                    "Госпитализация",
                    TreatmentType.Physiotherapeutic.type))

            addTreatment(Treatment(
                    "Курс терапии",
                    TreatmentType.Psychotherapeutic.type))

            addTreatment(Treatment(
                    "Хирургическое лечение",
                    TreatmentType.Surgical.type))

            addTreatment(Treatment(
                    "Химиотерапия",
                    TreatmentType.Physiotherapeutic.type))

            addTreatment(Treatment(
                    "Противовирусная терапия",
                    TreatmentType.Psychotherapeutic.type))

            close()
        }
    }

    private fun addSideEffects() {
        DBHandler.getInstance(this).apply {
            addSideEffect(SideEffect(
                    2,
                    "Препарат \"Рифампицин\"",
                    "Внутривенное введение противопоказано при лёгочно-сердечной недостаточности и флебите"))

            addSideEffect(SideEffect(
                    2,
                    "Препарат \"Изониазид\"",
                    "Головная боль, головокружение, рвота"))

            addSideEffect(SideEffect(
                    5,
                    "Противопоказания для \"Эхокардиографии\"",
                    "Хронические курильщики, лица, страдающие бронхиальной астмой / хроническим бронхитом и некоторым другими заболеваниями дыхательной системы"))

            addSideEffect(SideEffect(
                    8,
                    "Препарат \"Хлорамфеникол\"",
                    "Крайне токсичен и часто оставляет тяжёлые побочные эффекты при применении внутрь"))

            close()
        }
    }

    private fun addPatients() {
        DBHandler.getInstance(this).apply {
            addPatientWithDoctor(
                    Patient("303426, г. Сысерть, ул. Вагонников 3-я, дом 22, квартира 267"),
                    Person("Васильев Алексей Кириллович", "13/4/1984", "Male"),
                    Doctor("Фтизиатр", "25/1/2015"),
                    Person("Куколевская Горислава Робертовна ", "26/4/1980", "Female"))

            addDiagnosisForPatient("A16.4", 1)
            addDiagnosisForPatient("A18.0", 1)
            addTreatmentForDiagnosis(1, "A16.4")
            addTreatmentForDiagnosis(2, "A16.4")
            addTreatmentForDiagnosis(1, "A18.0")
            addTreatmentForDiagnosis(2, "A18.0")


            addPatientWithDoctor(
                    Patient("243322, г. Большая Глушица, ул. Базовая, дом 71, квартира 24"),
                    Person("Кочеткова Майя Егоровна", "24/5/1965", "Female"),
                    Doctor("Офтальмолог", "1/3/2009"),
                    Person("Комаров Евлампий Тарасович", "18/9/1972", "Male"))

            addDiagnosisForPatient("Z01.0", 2)
            addTreatmentForDiagnosis(3, "Z01.0")

            addPatientWithDoctor(
                    Patient("143969, г. Ягодное, ул. Королёва, дом 50, квартира 196"),
                    Person("Дидиченко Елена Анатольевна", "4/11/1969", "Female"),
                    Doctor("Кардиолог", "4/8/2011"),
                    Person("Афанасьева Беатриса Владиславовна ", "7/5/1976", "Female"))

            addDiagnosisForPatient("Z03.4", 3)
            addTreatmentForDiagnosis(4, "Z03.4")
            addTreatmentForDiagnosis(5, "Z03.4")
            addTreatmentForDiagnosis(6, "Z03.4")

            addPatientWithDoctor(
                    Patient("446731, г. Кондопога, ул. Бадюлина, дом 40, квартира 143"),
                    Person("Комаров Климент Владимирович", "8/2/1985", "Male"),
                    Doctor("Гастроэнтеролог", "21/5/2017"),
                    Person("Николаева Марианна Аркадьевна", "18/12/1962", "Female"))

            addDiagnosisForPatient("A01.0", 4)
            addTreatmentForDiagnosis(7, "A01.0")
            addTreatmentForDiagnosis(8, "A01.0")


            addPatientWithDoctor(
                    Patient("186801, г. Чернышковский, ул. Журавлёва, дом 13, квартира 227"),
                    Person("Давыдова Ольга Платоновна", "11/2/1979", "Female"),
                    Doctor("Онколог", "29/5/2014"),
                    Person("Журавлёв Карл Максович", "4/1/1980", "Male"))

            addDiagnosisForPatient("C30.1", 5)
            addTreatmentForDiagnosis(9, "C30.1")
            addTreatmentForDiagnosis(10, "C30.1")


            addPatientWithDoctor(
                    Patient("413809, г. Красное-на-Волге, ул. Авангардная, дом 25, квартира 149"),
                    Person("Петров Викторин Миронович", "21/4/1967", "Male"),
                    Doctor("Гепатолог", "7/10/2013"),
                    Person("Демьянченко Амина Семеновна", "17/5/1969", "Female"))

            addDiagnosisForPatient("B17.1", 6)
            addTreatmentForDiagnosis(11, "B17.1")

            addPatientWithDoctor(
                    Patient("628634, г. Большая Мартыновка, ул. Базовая, дом 71, квартира 219"),
                    Person("Борисова Лия Юрьевна", "9/9/1976", "Female"),
                    Doctor("Фтизиатр", "3/9/2004"),
                    Person("Макарова Алёна Борисовна", "28/6/1989", "Female"))

            addDiagnosisForPatient("A18.0", 7)


            addPatientWithDoctor(
                    Patient("111538, г. Кондоль, ул. Бажова, дом 29, квартира 250"),
                    Person("Гусев Соломон Эдуардович", "18/5/1972", "Male"),
                    Doctor("Офтальмолог", "15/1/2013"),
                    Person("Веселков Лазарь Николаевич", "14/11/1974", "Male"))

            addDiagnosisForPatient("Z01.0", 8)

            addPatientWithDoctor(
                    Patient("172377, г. Дальнее, ул. Заречная, дом 1, квартира 268"),
                    Person("Назаров Милий Матвеевич", "17/1/1966", "Male"),
                    Doctor("Кардиолог", "4/12/2014"),
                    Person("Козлова Капитолина Степановна", "9/10/1968", "Female"))

            addDiagnosisForPatient("Z03.4", 9)

            close()
        }
    }


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(IconicsContextWrapper.wrap(base))
    }
}