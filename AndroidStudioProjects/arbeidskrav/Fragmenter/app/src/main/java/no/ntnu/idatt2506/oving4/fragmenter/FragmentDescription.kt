package no.ntnu.idatt2506.oving4.fragmenter

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment


class FragmentDescription : Fragment() {

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(R.layout.fragment_description, container, false);
    }

	fun setDescription(description: String?, picture: Drawable?){
		requireView().findViewById<ImageView>(R.id.pic).setImageDrawable(picture)
		requireView().findViewById<TextView>(R.id.des).text = description
	}
}
