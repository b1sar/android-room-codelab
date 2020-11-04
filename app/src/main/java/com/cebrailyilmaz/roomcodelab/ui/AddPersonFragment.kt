package com.cebrailyilmaz.roomcodelab.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.cebrailyilmaz.roomcodelab.data.Person
import com.cebrailyilmaz.roomcodelab.R

class AddPersonFragment : Fragment() {
    private lateinit var personViewModel: PersonViewModel

    private lateinit var fName: EditText
    private lateinit var lName: EditText
    private lateinit var age: EditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            println()
        }
        personViewModel = ViewModelProvider(this).get(PersonViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fName = view.findViewById(R.id.person_firstname_input)
        lName = view.findViewById(R.id.person_lastname_input)
        age = view.findViewById(R.id.person_age_input)
        saveButton = view.findViewById(R.id.save_button)

        saveButton.setOnClickListener(View.OnClickListener {
            onSaveButtonClicked(it)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_person, container, false)
    }

    private fun onSaveButtonClicked(it: View?) {
        personViewModel.insert(Person((8..100).random(), fName.text.toString(), lName.text.toString(), age.text.toString().toInt()))
        val action = AddPersonFragmentDirections.actionAddPersonFragmentToListPeopleFragment()
        it?.findNavController()?.navigate(action)
    }

}