package com.kolomeitsev.doodler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kolomeitsev.doodler.data.Doodle
import com.kolomeitsev.doodler.data.DoodleViewModel
import java.io.File

class MainActivity : AppCompatActivity(),
    DoodleFragment.OnListFragmentInteractionListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(
                R.id.placeholder,
                DoodleFragment.newInstance()
            )
            .commit()

        val addDoodleButton : FloatingActionButton = findViewById(R.id.addDoodleButton)
        addDoodleButton.setOnClickListener {
            val intent = Intent(this, DoodleActivity::class.java)
            startActivity(intent);
        }
    }

    override fun onListFragmentClick(doodle: Doodle) {
        val intent = Intent(this, DoodleActivity::class.java).apply {
            putExtra("doodlePath", doodle.path)
        }
        startActivity(intent);
    }

    override fun onListFragmentSwipe(doodle: Doodle) {
        File(doodle.path).delete()
        ViewModelProvider(this).get(DoodleViewModel::class.java).delete(doodle)
    }

}
