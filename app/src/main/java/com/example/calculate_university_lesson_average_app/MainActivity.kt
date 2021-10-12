package com.example.calculate_university_lesson_average_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.LENGTH_SHORT
import androidx.core.view.iterator
import kotlinx.android.synthetic.main.activity_add_new_lesson.view.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //TODO : Design Düzenlenecek
    //TODO : Koddaki bug bulunacak
    //TODO : TOAST LİBRARY KULLANILACAK
    private val LESSONNAMES = arrayOf("Matematik", "Türkçe", "Algoritma", "Fizik", "Biyoloji")
    private var Lessons : ArrayList<Lesson> = ArrayList<Lesson>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var adaptor = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, LESSONNAMES)
        auto_edit_text.setAdapter(adaptor)

        if(myRootLinearLayout.childCount == 0 )
            btn_hesapla.visibility = View.INVISIBLE
        else
            btn_hesapla.visibility = View.VISIBLE


        btn_add.setOnClickListener {
            if(! auto_edit_text.text.isNullOrEmpty()) {
                var inflater = LayoutInflater.from(this) //dinamik olarak data geç
                var new_lesson_view = inflater.inflate(R.layout.activity_add_new_lesson, null) //Ben kendim eklicem rootuna


                var lessonName = auto_edit_text.text.toString()
                var lessonCredit = spn_credit.selectedItem.toString()
                var lessonNote = spn_notes.selectedItem.toString()


                new_lesson_view.newLesson.setText(lessonName)
                new_lesson_view.newSpinnerCredit.setSelection(getSpinnerIndex(spn_credit, lessonCredit))
                new_lesson_view.newSpinnerNote.setSelection(getSpinnerIndex(spn_notes, lessonNote))

                new_lesson_view.newLesson.setAdapter(adaptor)

                new_lesson_view.btn_sil.setOnClickListener {
                    myRootLinearLayout.removeView(new_lesson_view)
                    if(myRootLinearLayout.childCount == 0 )
                        btn_hesapla.visibility = View.INVISIBLE
                    else
                        btn_hesapla.visibility = View.VISIBLE
                }


                myRootLinearLayout.addView(new_lesson_view)
                btn_hesapla.visibility = View.VISIBLE
                clearScreen()
            }
            else {
                Toast.makeText(this, "Ders adı giriniz", LENGTH_LONG).show()
            }
        }

    }

    fun clearScreen() {
        auto_edit_text.setText("")
        spn_credit.setSelection(0)
        spn_notes.setSelection(0)

    }

    fun getSpinnerIndex(spinner: Spinner, selectedItem : String) : Int {
        var index : Int = 0

        for (i in 0..spinner.count) {
            if (spinner.getItemAtPosition(i).toString().equals(selectedItem)) {
                index = i
                break
            }
        }

        return index
    }

    fun calculateAverage(view: View) {

        var sumNote = 0.0
        var sumCredit = 0.0
        var counter = 0



        if(myRootLinearLayout.childCount != 0 ) {
            for (item in myRootLinearLayout) {

                var lesson : Lesson = Lesson(
                    item.newLesson.text.toString(),
                    item.newSpinnerCredit.getItemAtPosition(counter).toString().toInt(),
                    item.newSpinnerNote.getItemAtPosition(counter).toString()
                )
                counter++
                Lessons.add(lesson)
            }

            for (item in Lessons) {
                sumNote += convertLetterToValue(item.note)
                sumCredit += item.credit
            }

            Toast.makeText(this, "Ortalamanız= ${sumNote / sumCredit}", LENGTH_LONG)


        }else {
            Toast.makeText(this, "Hiçbir Ders Yok", LENGTH_SHORT)
        }



    }

    fun convertLetterToValue(item : String) : Double {
        var value : Double = 0.0
        when (item) {
            "AA" -> value = 4.0
            "BA" -> value = 3.5
            "BB" -> value = 3.0
            "BC" -> value = 2.5
            "CC" -> value = 2.0
            "DC" -> value = 1.5
            "DD" -> value = 1.0
            "FF" -> value = 0.0
        }
        return value
    }

}



//var inflater2 = LayoutInflater
//var inflater3 = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater