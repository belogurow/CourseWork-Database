package com.alexbelogurow.dbcoursework

import android.app.Application
import android.R.id.edit
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.alexbelogurow.dbcoursework.DataBase.DBHandler
import com.alexbelogurow.dbcoursework.Model.Diagnosis
import com.alexbelogurow.dbcoursework.Model.Doctor
import com.alexbelogurow.dbcoursework.Model.Person


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

            // mark first time has runned.
            val editor = prefs.edit()
            editor.putBoolean("firstTime", true)
            editor.apply()
        }
    }

    private fun addDoctors() {
        val dbHandler = DBHandler.getInstance(this)

        dbHandler.addDoctor(
                Doctor("Акушерка","2013/4/27"),
                Person("Мамонтова Алевтина Лукьяновна", "1972/11/3", "Female")
        )
        dbHandler.addDoctor(
                Doctor("Анестезиолог", "2015/3/13"),
                Person("Козлов Никита Глебович", "1941/2/25", "Male")
        )
        dbHandler.addDoctor(
                Doctor("Диетолог", "2007/1/10"),
                Person("Логинов Семён Геннадьевич", "1981/0/18", "Male")
        )
        dbHandler.addDoctor(
                Doctor("Кардиохирург", "2010/4/4"),
                Person("Якушев Николай Георгьевич", "1963/5/7", "Male")
        )
        dbHandler.addDoctor(
                Doctor("Онколог-хирург", "1999/2/7"),
                Person("Лазарев Андрей Кимович", "1959/1/19", "Male")
        )
        dbHandler.close()
    }

    private fun addDiagnoses() {
        val dbHandler = DBHandler.getInstance(this)

        dbHandler.addDiagnosis(Diagnosis(
                "C16.0",
                "Злокачественное новообразование кардии желудка"
        ))
        /*
        dbHandler.addDiagnosis(Diagnosis(
                "C69.6",
                "Злокачественное новообразование глазницы"
        ))
        */
        dbHandler.addDiagnosis(Diagnosis(
                "A15.2",
                "Туберкулез лёгких, подтвержденный гистологически"
        ))
        dbHandler.addDiagnosis(Diagnosis(
                "A16.2",
                "Туберкулез лёгких без упоминания о бактериологическом или гистологическом подтверждении"
        ))
        dbHandler.addDiagnosis(Diagnosis(
                "A17.8",
                "Туберкулез нервной системы других локализаций"
        ))
        dbHandler.addDiagnosis(Diagnosis(
                "A83.6",
                "Болезнь, вызванная вирусом Роцио"
        ))
        dbHandler.addDiagnosis(Diagnosis(
                "A84.1",
                "Центральноевропейский клещевой энцефалит"
        ))
        dbHandler.addDiagnosis(Diagnosis(
                "B01.9",
                "Ветряная оспа без осложнений"
        ))
        dbHandler.addDiagnosis(Diagnosis(
                "J20.0",
                "Острых бронхит, вызванный Mycoplasma pneumoniae"
        ))
        dbHandler.addDiagnosis(Diagnosis(
                "Z01.0",
                "Обследование глаз и зрения"
        ))
        dbHandler.addDiagnosis(Diagnosis(
                "Z03.4",
                "Наблюдение при подозрении на инфаркт миокарда"
        ))
        dbHandler.addDiagnosis(Diagnosis(
                "Z04.6",
                "Общее психиатрическое обследование по запросу учреждения"
        ))
        dbHandler.addDiagnosis(Diagnosis(
                "Z10.0",
                "Профессиональное медицинское обследование"
        ))
        dbHandler.addDiagnosis(Diagnosis(
                "Z13.1",
                "Специальное скрининговое обследование с целью выявления сахарного диабета"
        ))

        dbHandler.close()
    }
}