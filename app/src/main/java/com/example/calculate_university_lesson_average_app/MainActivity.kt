package com.example.calculate_university_lesson_average_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Spinner
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import kotlinx.android.synthetic.main.activity_add_new_lesson.view.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(myRootLinearLayout.childCount == 0 )
            btn_hesapla.visibility = View.INVISIBLE
        else
            btn_hesapla.visibility = View.VISIBLE



        btn_add.setOnClickListener {
            if(! auto_edit_text.text.isNullOrEmpty()) {
                var inflater = LayoutInflater.from(this)
                var new_lesson_view = inflater.inflate(R.layout.activity_add_new_lesson, null) //Ben kendim eklicem rootuna


                var lessonName = auto_edit_text.text.toString()
                var lessonCredit = spn_credit.selectedItem.toString()
                var lessonNote = spn_notes.selectedItem.toString()


                new_lesson_view.newLesson.setText(lessonName)
                new_lesson_view.newSpinnerCredit.setSelection(getSpinnerIndex(spn_credit, lessonCredit))
                new_lesson_view.newSpinnerNote.setSelection(getSpinnerIndex(spn_notes, lessonNote))

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

    fun calculateAverage(v : View) {

    }
}



//var inflater2 = LayoutInflater
//var inflater3 = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater