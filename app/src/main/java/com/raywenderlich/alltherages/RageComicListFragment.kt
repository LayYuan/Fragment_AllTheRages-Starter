

package com.raywenderlich.alltherages

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raywenderlich.alltherages.databinding.RecyclerItemRageComicBinding


class RageComicListFragment : Fragment() {

  private lateinit var imageResIds: IntArray
  private lateinit var names: Array<String>
  private lateinit var descriptions: Array<String>
  private lateinit var urls: Array<String>

  //This field is a reference to the fragment’s listener, which will be the activity.
  private lateinit var listener: OnRageComicSelected

  companion object {

    fun newInstance(): RageComicListFragment {
      return RageComicListFragment()
    }
  }

  //CLY
  //onAttach() contains code that accesses the resources you need via the Context to which the fragment is attached.
  override fun onAttach(context: Context?) {
    super.onAttach(context)

    //This initializes the listener reference.
    //You wait until onAttach() to ensure that the fragment actually attached itself. Then you verify
    // that the activity implements the OnRageComicSelected interface via instanceof.
    if (context is OnRageComicSelected) {
      listener = context
    } else {
      throw ClassCastException(context.toString() + " must implement OnRageComicSelected.")
    }

    // Get rage face names and descriptions.
    val resources = context!!.resources
    names = resources.getStringArray(R.array.names)
    descriptions = resources.getStringArray(R.array.descriptions)
    urls = resources.getStringArray(R.array.urls)

    // Get rage face images.
    val typedArray = resources.obtainTypedArray(R.array.images)
    val imageCount = names.size
    imageResIds = IntArray(imageCount)
    for (i in 0..imageCount - 1) {
      imageResIds[i] = typedArray.getResourceId(i, 0)
    }
    typedArray.recycle()
  }


  //inflate the view hierarchy of RageComicListFragment, which contains a RecyclerView, and perform some setup.
  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {

    val view: View = inflater!!.inflate(R.layout.fragment_rage_comic_list, container,
            false)
    val activity = activity
    val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view) as RecyclerView
    recyclerView.layoutManager = GridLayoutManager(activity, 2)
    recyclerView.adapter = RageComicAdapter(activity)
    return view
  }

  internal inner class RageComicAdapter(context: Context) : RecyclerView.Adapter<ViewHolder>() {

    private val layoutInflater: LayoutInflater

    init {
      layoutInflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
      val recyclerItemRageComicBinding = RecyclerItemRageComicBinding.inflate(layoutInflater,
          viewGroup, false)
      return ViewHolder(recyclerItemRageComicBinding.root, recyclerItemRageComicBinding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
      val comic = Comic(imageResIds[position], names[position],
          descriptions[position], urls[position])
      viewHolder.setData(comic)

      //This adds a View.OnClickListener to each Rage Comic so that it invokes the callback on the
      // listener (the activity) to pass along the selection.
      viewHolder.itemView.setOnClickListener { listener.onRageComicSelected(comic) }
    }

    override fun getItemCount(): Int {
      return names.size
    }
  }

  internal inner class ViewHolder constructor(itemView: View,
                                              val recyclerItemRageComicBinding:
                                              RecyclerItemRageComicBinding) :
      RecyclerView.ViewHolder(itemView) {

    fun setData(comic: Comic) {
      recyclerItemRageComicBinding.comic = comic
    }
  }

  //defines a listener interface for the activity to listen to the fragment.
  //The activity will implement this interface, and the fragment will invoke the onRageComicSelected()
  // when an item is selected, passing the selection to the activity.

  interface OnRageComicSelected {
    fun onRageComicSelected(comic: Comic)
  }

}
