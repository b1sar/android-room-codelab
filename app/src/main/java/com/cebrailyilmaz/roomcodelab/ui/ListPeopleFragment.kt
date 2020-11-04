package com.cebrailyilmaz.roomcodelab.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cebrailyilmaz.roomcodelab.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListPeopleFragment() : Fragment() {
    private lateinit var personViewModel: PersonViewModel
    private lateinit var fab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            println()
        }
        personViewModel = ViewModelProvider(this).get(PersonViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = PersonListAdapter(view.context)
        recyclerView.adapter = adapter

        //adapter will be updated on data change, we subscribed to the viewmodel
        personViewModel.allPeeople.observe(viewLifecycleOwner, Observer {people ->
            people?.let { adapter.setPeople(it) }
        })
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        fab = view.findViewById(R.id.fab)
        fab.setOnClickListener( View.OnClickListener {
            setOnFabClicked(it)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_people, container, false)
    }

    private fun setOnFabClicked(it: View?) {
        val action = ListPeopleFragmentDirections.actionListPeopleFragmentToAddPersonFragment()
        it?.findNavController()?.navigate(action)
    }
}