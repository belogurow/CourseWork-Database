package com.alexbelogurow.dbcoursework

import android.app.Application
import android.preference.PreferenceManager
import com.alexbelogurow.dbcoursework.DataBase.DBHandler
import com.alexbelogurow.dbcoursework.models.*


/**
 * Created by alexbelogurow on 08.09.17.
 */
class App: Application() {
    override fun onCreate() {
        super.onCreate()

        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        if (!prefs.getBoolean("firstTime", false)) {
            addDoctors()
            addDiagnoses()
            addTreatments()

            // mark first time has runned.
            val editor = prefs.edit()
            editor.putBoolean("firstTime", true)
            editor.apply()
        }
    }

    private fun addDoctors() {
        DBHandler.getInstance(this).apply {
            addDoctor(
                    Doctor("Акушерка", "2013/4/27"),
                    Person("Мамонтова Алевтина Лукьяновна", "1972/11/3", "Female"))

            addDoctor(
                    Doctor("Анестезиолог", "2015/3/13"),
                    Person("Козлов Никита Глебович", "1941/2/25", "Male"))

            addDoctor(
                    Doctor("Диетолог", "2007/1/10"),
                    Person("Логинов Семён Геннадьевич", "1981/0/18", "Male"))

            addDoctor(
                    Doctor("Кардиохирург", "2010/4/4"),
                    Person("Якушев Николай Георгьевич", "1963/5/7", "Male"))

            addDoctor(
                    Doctor("Онколог-хирург", "1999/2/7"),
                    Person("Лазарев Андрей Кимович", "1959/1/19", "Male"))

            close()
        }
    }

    private fun addDiagnoses() {
        val dbHandler = DBHandler.getInstance(this)

        DBHandler.getInstance(this).apply {
            addDiagnosis(Diagnosis(
                    "C16.0",
                    "Злокачественное новообразование кардии желудка"))

            addDiagnosis(Diagnosis(
                    "C69.6",
                    "Злокачественное новообразование глазницы"))

            addDiagnosis(Diagnosis(
                    "A15.2",
                    "Туберкулез лёгких, подтвержденный гистологически"))

            addDiagnosis(Diagnosis(
                    "A16.2",
                    "Туберкулез лёгких без упоминания о бактериологическом или гистологическом подтверждении"))

            addDiagnosis(Diagnosis(
                    "A17.8",
                    "Туберкулез нервной системы других локализаций"))

            addDiagnosis(Diagnosis(
                    "A83.6",
                    "Болезнь, вызванная вирусом Роцио"))

            addDiagnosis(Diagnosis(
                    "A84.1",
                    "Центральноевропейский клещевой энцефалит"))

            addDiagnosis(Diagnosis(
                    "B01.9",
                    "Ветряная оспа без осложнений"))

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
                    "Уменьшение внутреннего брюшного давления в желудочно-кишечном тракте",
                    TreatmentType.Psychotherapeutic.type
            ))
            addTreatment(Treatment(
                    "Регулирование работы кардии желудка",
                    TreatmentType.Medication.type
            ))

            close()
        }
    }
}