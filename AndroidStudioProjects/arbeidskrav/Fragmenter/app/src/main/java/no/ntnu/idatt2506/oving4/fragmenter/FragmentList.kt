package no.ntnu.idatt2506.oving4.fragmenter

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.ListFragment


class FragmentList : ListFragment() {
	private var mListener: OnFragmentInteractionListener? = null
	private var titles: Array<String> = arrayOf()
	private var description: Array<String> = arrayOf()
	private var picture: TypedArray? = null
	private var currentPosition = -1

	override fun onCreate(savedInstanceState: Bundle?){
		super.onCreate(savedInstanceState)
		titles = resources.getStringArray(R.array.titles)
		description = resources.getStringArray(R.array.descriptions)
		picture = resources.obtainTypedArray(R.array.pictures)
		listAdapter = activity?.let {
			ArrayAdapter(it, android.R.layout.simple_list_item_1, android.R.id.text1, titles)
		}
    }
	fun nextPressed() {
		if (currentPosition==titles.size-1) currentPosition = 0
		else if (currentPosition == -1) currentPosition = 0
		else currentPosition++
		mListener!!.onFragmentInteraction(description!![currentPosition], picture!!.getDrawable(currentPosition))
	}

	fun previousPressed() {
		if(currentPosition==0) currentPosition = 2
		else if (currentPosition == -1) currentPosition = 2
		else currentPosition--
		mListener!!.onFragmentInteraction(description!![currentPosition], picture!!.getDrawable(currentPosition))
	}

	override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
		super.onListItemClick(l, v, position, id)
		mListener!!.onFragmentInteraction(description!![position], picture!!.getDrawable(position))
		currentPosition = position
	}

	interface OnFragmentInteractionListener {
		fun onFragmentInteraction(description: String?, picture: Drawable?)
	}

	override fun onAttach(context: Context) {
		super.onAttach(context)
		mListener = try {
			activity as OnFragmentInteractionListener
		} catch (e: ClassCastException) {
			throw ClassCastException(
				"$activity must implement OnFragmentInteractionListener"
			)
		}
	}
	override fun onDetach() {
		super.onDetach()
		mListener = null
	}
}
